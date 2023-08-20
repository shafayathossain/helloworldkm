//
//  NavigationControllerHost.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `NavigationControllerHost` is a SwiftUI view which hosts a `UINavigationController`.
/// It uses `UIViewControllerRepresentable` protocol to create a UIKit view controller for use in SwiftUI.
/// This can be useful when you want to use features of `UINavigationController` which are not available directly in SwiftUI.
///
/// - Note:
/// Make sure to pass in the root view that should be displayed within the navigation controller.
///
/// Usage:
///
/// ```swift
/// NavigationView {
///     NavigationControllerHost(rootView: MySwiftUIView())
/// }
/// ```
///
/// - Parameters:
///   - rootView: The root view to display within the UINavigationController.
///
/// - Remark:
/// This class bridges the gap between SwiftUI and UIKit's UINavigationController, allowing the navigation controller to be used directly within SwiftUI.
struct NavigationControllerHost<T: Equatable, Screen: View>: UIViewControllerRepresentable {
    
    let navTitle: String
    let isLargeTitle: Bool
    let navHidden: Bool
    
    let router: AppRouter<T>
    
    @ViewBuilder
    var routeMap: (T) -> Screen
    
    
    /// This method is responsible for creating and configuring the navigation controller, which is the actual UIViewController that will be managed by SwiftUI as part of the `NavigationControllerHost` view.
    ///
    /// It ensures that the initial route set in the router is reflected in the navigation stack of the view controller, and that any subsequent updates to the router's route will correctly update the navigation stack.
    /// - Parameters:
    /// - context: A context that contains information about the current state of the system.
    ///
    /// - Returns: An instance of `UIViewController` that represents the navigation controller for SwiftUI view.
    ///
    /// **Code Explanation**:
    /// - `navigation.stackDelegate = router`: Sets the `stackDelegate` of the navigation controller to the router. This allows the navigation controller to inform the router when a view controller is popped from the navigation stack due to a user interaction.
    /// - `for path in router.routes`: The loop iterates through the initial routes set in the router, creates a `UIHostingController` for each one (using the `routeMap` to determine which SwiftUI view to create), and pushes them onto the navigation stack.
    /// - `router.onPush`: This block is called when a new route should be pushed onto the navigation stack. The route is passed as an argument to the callback, which then creates a new instance of `UIHostingController` with the view associated with the route and pushes it onto the navigation stack. This allows for the navigation between different views based on the routing logic of your application.
    /// - `router.onPushToRoot`: This block is called when a new route should be pushed to the root of the navigation stack. The route is passed as an argument to the callback, which then clears the list of view controllers and creates a new instance of `UIHostingController` with the view associated with the route and pushes it to the root of the navigation stack. This allows for the view to be at the root of the application.
    /// - `router.onPopLast`: This block is called when a number of view controllers should be popped from the navigation stack. The number of view controllers to be popped (`numToPop`) and whether the operation should be animated are passed as arguments to the callback. If the number of view controllers to be popped is equal to the current count of view controllers in the navigation stack, all view controllers are removed from the stack. Otherwise, the view controllers are popped up to the one specified by the index.
    func makeUIViewController(context: Context) -> UINavigationController {
        let navigation = PopAwareUINavigationController()
        
        navigation.popHandler = {
            router.onSystemPop()
        }
        navigation.stackSizeProvider = {
            router.routes.count
        }
        
        for path in router.routes {
            navigation.pushViewController(
                UIHostingController(rootView: routeMap(path)), animated: true
            )
        }
        
        router.onPush = { route in
            navigation.pushViewController(
                UIHostingController(rootView: routeMap(route)), animated: true
            )
        }
        
        router.onPushToRoot = { route in
            let newRoot = UIHostingController(rootView: routeMap(route))
            navigation.setViewControllers(
                [newRoot], animated: true
            )
        }
        
        router.onPopLast = { numToPop, animated in
            if numToPop == navigation.viewControllers.count {
                navigation.viewControllers = []
            } else {
                let popTo = navigation.viewControllers[navigation.viewControllers.count - numToPop - 1]
                navigation.popToViewController(popTo, animated: animated)
            }
        }
        
        return navigation
    }
    
    func updateUIViewController(_ navigation: UINavigationController, context: Context) {
        navigation.topViewController?.navigationItem.title = navTitle
        if(isLargeTitle) {
            navigation.topViewController?.navigationItem.largeTitleDisplayMode = .always
        } else {
            navigation.topViewController?.navigationItem.largeTitleDisplayMode = .never
        }

        navigation.navigationBar.prefersLargeTitles = isLargeTitle
        navigation.navigationBar.isHidden = navHidden
    }
    
    static func dismantleUIViewController(_ navigation: UINavigationController, coordinator: ()) {
        navigation.viewControllers = []
        (navigation as! PopAwareUINavigationController).popHandler = nil
    }
    
    typealias UIViewControllerType = UINavigationController
}

/// `PopAwareUINavigationController` is a subclass of `UINavigationController`.
/// It has an additional feature of detecting when the navigation stack is about to pop a controller off its stack.
/// This feature is necessary in cases where we want to perform specific tasks just before a view controller is popped.
///
/// Usage:
///
/// ```swift
/// let navigationController = PopAwareUINavigationController()
/// // add view controllers to navigationController's stack
/// navigationController.pushViewController(myViewController, animated: true)
/// ```
///
/// Implement the `navigationControllerDidPop(viewController:)` method in your view controllers to get a callback when they are about to be popped.
///
/// ```swift
/// extension MyViewController: UINavigationControllerDelegate {
///     func navigationControllerDidPop(viewController: UIViewController) {
///         // Perform actions just before the viewController is popped
///     }
/// }
/// ```
class PopAwareUINavigationController: UINavigationController, UINavigationControllerDelegate {
    var popHandler: (() -> Void)?
    var stackSizeProvider: (() -> Int)?
    
    var popGestureBeganController: UIViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.delegate = self
    }
    
    func navigationController(_ navigationController: UINavigationController, didShow viewController: UIViewController, animated: Bool) {
        
        if let stackSizeProvider = stackSizeProvider, stackSizeProvider() > navigationController.viewControllers.count {
            self.popHandler?()
        }
    }
}
