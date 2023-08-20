//
//  Router.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine
import UIKit


/// The `AppRouter` class provides an application-specific router for SwiftUI apps that can handle navigation changes based on route types, which are defined by the generic parameter `T`. This router acts as the central navigation controller, maintaining a stack of routes and providing functions to manipulate this stack, such as `push`, `pop` and `popTo`.
///
/// The router uses a `Logger` instance for logging navigation changes, which can be configured for debug or non-debug modes. By default, an `EmptyLog` is used, which does nothing. If the `debug` parameter is set to true in the initializer, a `DebugLog` is used which logs to the console.
///
/// The current routes stack is stored in `_routes`, which is a private property to prevent external mutation. A public `routes` computed property is provided for read access to the routes stack.
///
/// Two callback properties, `onPush` and `onPopLast`, are used to inform external components about navigation changes. These callbacks are invoked with relevant parameters when a push or pop operation occurs.
///
/// An optional initial route can be provided during initialization, which is pushed onto the stack.
///
/// Navigation can be performed by invoking the `push`, `pop` and `popTo` methods. The `push` method adds a route to the top of the stack and invokes the `onPush` callback. The `pop` method removes the last route from the stack and invokes the `onPopLast` callback. The `popTo` method removes all routes from the stack until it reaches the specified route, and then invokes the `onPopLast` callback. If the `inclusive` parameter is set to true, the specified route is also removed.
///
/// A special `onSystemPop` method is provided to handle situations where the system (or user) navigates back, for example by a swipe gesture. This method pops the last route from the stack without invoking the `onPopLast` callback.
///
/// **Example:**
///
/// Suppose you have an enumeration representing various routes in your application, such as:
///
/// ```swift
/// enum AppRoutes: Equatable {
///     case home
///     case profile
///     case settings
/// }
/// ```
/// You can create an instance of AppRouter with an initial route and enable debug mode as follows:
///  ```
///  let router = AppRouter<AppRoutes>(initial: .home, debug: true)
///  ```
public class AppRouter<T: Equatable>: ObservableObject {
    
    private let logger: Logger
    
    private var _routes: [T] = []
    
    public var routes: [T] {
        return _routes
    }
    
    var onPush: ((T) -> Void)?
    var onPushToRoot: ((T) -> Void)?
    var onPopLast: ((Int, Bool) -> Void)?
    
///   Initializes the AppRouter with an optional initial route and a debug mode.
///
///   - Parameters:
///      - initial: An optional initial route to push onto the stack.
///      - debug: A Boolean value that controls whether the router logs navigation changes to the console.
    public init(initial: T? = nil, debug: Bool = false) {
        logger = debug ? DebugLog() : EmptyLog()
        logger.log("UIPilot - Pilot Initialized.")
        
        
        if let initial = initial {
            push(initial)
        }
    }
    
    
    /**
    Pushes a new route onto the navigation stack.

    This method appends the specified route to the internal routes stack and invokes the `onPush` callback.

    - Parameters:
       - route: The route to push onto the navigation stack.
    */
    public func push(_ route: T) {
        logger.log("Pushing \(route) route.")
        self._routes.append(route)
        self.onPush?(route)
    }
    
    /**
     Clears the navigation stack and pushes a new route to the root of the application.

     This method clears the previous routes and then pushes the specified route to the root of the internal routes stack by invoking the `onPushToRoot` callback.

     - Parameters:
     - route: The route to push onto the root of the navigation stack.
     */
    public func pushToRoot(_ route: T) {
        self._routes = []
        self.onPushToRoot?(route)
    }

    /**
    Pops the last route from the navigation stack.

    This method removes the last route from the internal routes stack and invokes the `onPopLast` callback. If the routes stack is empty, the method does nothing.

    - Parameters:
       - animated: A Boolean value that controls whether the pop operation should be animated.
    */
    public func pop(animated: Bool = true) {
        if self._routes.count > 1 {
            let popped = self._routes.removeLast()
            logger.log("UIPilot - \(popped) route popped.")
            onPopLast?(1, animated)
        }
    }
    
    /**
    Pops all routes from the navigation stack until it reaches the specified route.

    This method removes routes from the top of the internal routes stack until it finds the specified route, and then invokes the `onPopLast` callback. If the `inclusive` parameter is true, it also removes the found route. If the specified route is not found in the stack, the method does nothing.

    - Parameters:
       - route: The route to pop to.
       - inclusive: A Boolean value that controls whether the specified route should also be popped.
       - animated: A Boolean value that controls whether the pop operation should be animated.
    */
    public func popTo(_ route: T, inclusive: Bool = false, animated: Bool = true) {
        logger.log("UIPilot: Popping route \(route).")
        
        if _routes.isEmpty {
            logger.log("UIPilot - Path is empty.")
            return
        }
        
        guard var found = _routes.lastIndex(where: { $0 == route }) else {
            logger.log("UIPilot - Route not found.")
            return
        }
        
        if !inclusive {
            found += 1
        }
        
        let numToPop = (found..<_routes.endIndex).count
        logger.log("UIPilot - Popping \(numToPop) routes")
        _routes.removeLast(numToPop)
        onPopLast?(numToPop, animated)
    }
    
    /**
    Handles a pop operation initiated by the system or user.

    This method removes the last route from the internal routes stack without invoking the `onPopLast` callback. If the routes stack is empty, the method does nothing.
    */
    public func onSystemPop() {
        if !self._routes.isEmpty {
            let popped = self._routes.removeLast()
            logger.log("UIPilot - \(popped) route popped by system")
        }
    }
}


/// The `AppRouterHost` is a generic `View` that wraps around the `NavigationControllerHost`, which hosts the UINavigationController. It acts as the main component that controls the navigation within the SwiftUI view hierarchy based on your app's routing logic.
///
/// The `AppRouterHost` is parameterized with a type `T` which represents the routes in your app and a `Screen` which represents the SwiftUI view associated with each route. Each route of type `T` is mapped to a `Screen` using the `routeMap` closure. This closure is used to determine which SwiftUI view to display for a given route.
///
/// It uses an `AppRouter` to manage the navigation stack. The `AppRouter` is passed to it during initialization, and it's also injected into the environment so it can be accessed by child views.
///
/// The `navigationStyle` object is used to control the appearance of the navigation bar. It holds the state of the navigation bar's title and whether the navigation bar is hidden or not. It's also injected into the environment so it can be accessed by child views.
///
/// - Parameter pilot: An instance of `AppRouter` which manages the navigation stack of your app.
/// - Parameter routeMap: A closure that takes a route of type `T` and returns a SwiftUI `Screen` associated with the route.
///
/// The body of the `AppRouterHost` contains a `NavigationControllerHost` which takes in the navigation bar title, navigation bar hidden state, the router, and the route map. This sets up the navigation controller with the appropriate settings based on the `AppRouter` and `NavigationStyle`.
///
/// The `AppRouterHost` also sets the environment objects `pilot` (the AppRouter) and `navigationStyle`, so that these objects can be accessed by any child views.
public struct AppRouterHost<T: Equatable, Screen: View>: View {
    
    @StateObject
    var navigationStyle = NavigationStyle()
    
    let pilot: AppRouter<T>
    @ViewBuilder
    let routeMap: (T) -> Screen
    
    public init(_ pilot: AppRouter<T>, @ViewBuilder _ routeMap: @escaping (T) -> Screen) {
        self.pilot = pilot
        self.routeMap = routeMap
    }
    
    public var body: some View {
        NavigationControllerHost(
            navTitle: navigationStyle.title,
            isLargeTitle: navigationStyle.titleDisplayMode == .large,
            navHidden: navigationStyle.isHidden,
            router: pilot,
            routeMap: routeMap
        )
        .environmentObject(pilot)
        .environment(\.uipNavigationStyle, navigationStyle)
        .ignoresSafeArea()
        .background(Color.Surface)
    }
}

extension View {
    public func uipNavigationBarHidden(_ hidden: Bool) -> some View {
        return modifier(NavHiddenModifier(isHidden: hidden))
    }
    
    public func uipNavigationTitle(_ title: String, _ titleDisplayMode: NavigationBarItem.TitleDisplayMode = .inline) -> some View {
        return modifier(NavTitleModifier(title: title, titleDisplayMode: titleDisplayMode))
    }
}

/**
 `NavigationStyle` is an `ObservableObject` that manages the navigation bar's state across different views in a SwiftUI application.

 This class publishes two properties: `isHidden` and `title`, which represent the visibility and the title of the navigation bar respectively.

 - `isHidden`: A Boolean value indicating whether the navigation bar should be hidden. When a view modifies this value, it also sets `isHiddenOwner` to its identifier. This allows for precise control over which view's settings are currently applied to the navigation bar. If another view tries to change `isHidden`, it can check `isHiddenOwner` to see if it has the authority to do so.

 - `isHiddenOwner`: A string identifier of the current view that last set the `isHidden` property. It's used to ensure that the correct view's `isHidden` preference is respected, especially during view transitions or in cases of asynchrony where a view's `onAppear` or `onDisappear` event may execute at an unexpected time.

 - `title`: A string representing the current title of the navigation bar. Similar to `isHidden`, when a view modifies this value, it sets `titleOwner` to its identifier, ensuring that the correct view's title preference is respected.

 - `titleOwner`: A string identifier of the current view that last set the `title` property. It is used to manage potential race conditions between different views trying to update the navigation bar's title.

 **Note**: An `ObservableObject` is a type of object that SwiftUI watches for changes. When an `@Published` property changes, SwiftUI knows to recompute any views that rely on that object.
 */
class NavigationStyle: ObservableObject {
    @Published
    var isHidden = false
    var isHiddenOwner: String = ""
    
    @Published
    var title = ""
    var titleOwner: String = ""
    
    @Published
    var titleDisplayMode: NavigationBarItem.TitleDisplayMode = .inline
    var titleDisplayModeOwner: String = ""
}


/// A `ViewModifier` that controls the visibility of the navigation bar in a SwiftUI view hierarchy.
///
/// This modifier can be used to hide or show the navigation bar based on the `isHidden` parameter.
/// The `id` and `initialValue` states are used to manage the changes in navigation bar visibility across different views.
///
/// The navigation bar visibility is controlled via an environment value `uipNavigationStyle`, which is of type `NavigationStyle`.
///
/// When this modifier is applied, the visibility of the navigation bar is initially set based on the `isHidden` parameter in the `onAppear` modifier.
/// When the view disappears (`onDisappear`), the visibility of the navigation bar is reset to its original value,
/// unless the visibility was changed by a different view (detected by comparing `id` with `uipNavigationStyle.isHiddenOwner`).
struct NavHiddenModifier: ViewModifier {
    
    /// The desired visibility state of the navigation bar.
    let isHidden: Bool
    
    /// An unique ID for the view that is applying this modifier.
    /// Used to manage the navigation bar visibility state across multiple views.
    @State var id = UUID().uuidString
    
    /// The initial visibility state of the navigation bar before this modifier is applied.
    /// This is used to restore the original state when the view disappears.
    @State var initialValue: Bool = false
    
    /// The current navigation style configuration.
    /// This is obtained from the environment and is used to change the visibility of the navigation bar.
    @Environment(\.uipNavigationStyle) var navStyle
    
    func body(content: Content) -> some View {
        
        // In case where isHidden change after onAppear
        if navStyle.isHiddenOwner == id && navStyle.isHidden != isHidden {
            DispatchQueue.main.async {
                navStyle.isHidden = isHidden
            }
        }
        
        return content
            .onAppear {
                initialValue = navStyle.isHidden
                
                navStyle.isHidden = isHidden
                navStyle.isHiddenOwner = id
            }
            .onDisappear {
                if navStyle.isHiddenOwner == id {
                    navStyle.isHidden = initialValue
                    navStyle.isHiddenOwner = ""
                }
            }
    }
}

protocol Logger {
    func log(_ value: String)
}
