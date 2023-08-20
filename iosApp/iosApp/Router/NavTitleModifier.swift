//
//  NavTitleModifier.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// A `ViewModifier` that controls the title of the navigation bar in a SwiftUI view hierarchy.
///
/// This modifier can be used to set the navigation bar title based on the `title` parameter.
/// The `id` and `initialValue` states are used to manage the changes in navigation bar title across different views.
///
/// The navigation bar title is controlled via an environment value `uipNavigationStyle`, which is of type `NavigationStyle`.
///
/// When this modifier is applied, the navigation bar title is initially set based on the `title` parameter in the `onAppear` modifier.
/// When the view disappears (`onDisappear`), the title of the navigation bar is reset to its original value,
/// unless the title was changed by a different view (detected by comparing `id` with `uipNavigationStyle.titleOwner`).
struct NavTitleModifier: ViewModifier {
    /// The desired title of the navigation bar.
    let title: String
    let titleDisplayMode: NavigationBarItem.TitleDisplayMode
    
    /// An unique ID for the view that is applying this modifier.
    /// Used to manage the navigation bar title state across multiple views.
    @State var id = UUID().uuidString
    
    /// The initial title of the navigation bar before this modifier is applied.
    /// This is used to restore the original title when the view disappears.
    @State var initialTitleValue: String = ""
    @State var initialTitleDisplayModeValue: NavigationBarItem.TitleDisplayMode = .inline
    
    /// The current navigation style configuration.
    /// This is obtained from the environment and is used to change the title of the navigation bar.
    @Environment(\.uipNavigationStyle) var navStyle
    
    /// The `body(content:)` function is responsible for rendering the view with the
    /// applied title changes and managing the lifecycle of the navigation title.
    ///
    /// In some situations, after a view appears (in the `onAppear` method), the title might be changed by another view or process.
    /// This method checks that if this specific instance of `NavTitleModifier` (identified by `id`) is still the current owner of the
    /// navigation bar title (i.e., `navStyle.titleOwner == id`), and if the current navigation bar title (`navStyle.title`) is not
    /// equal to the desired title (`title`), it corrects this discrepancy by setting the navigation bar title to the desired title on the main queue.
    ///
    /// Consider this scenario: In a SwiftUI application, you have a navigation view with multiple subviews, say `View1`, `View2`, and `View3`.
    /// When you navigate through these views, each view has the ability to change the navigation bar's title. Now, let's say
    /// you navigate from `View1` to `View2`. `View2` sets the title to "View Two". Before the UI could reflect this change,
    /// you immediately navigate to `View3`. `View3` also sets its title to "View Three". What can happen is that,
    /// due to the asynchronous nature of UI updates, `View2`'s `onAppear` lifecycle event could occur after `View3` is already loaded.
    /// This would change the navigation bar's title to "View Two", even though you are in `View3`. The check in this method is put in
    /// place to prevent such situations. It checks whether the instance trying to set the title is still the current "owner" of the title.
    /// If it isn't the owner, it doesn't change the title, hence preventing overwriting of title set by `View3`.
    ///
    /// All UI updates must be done on the main queue to avoid race conditions or other issues related to UI inconsistency.
    ///
    /// This function behaves as follows:
    ///
    /// 1.  It first checks whether the current view is the owner of the navigation title
    ///     and if the current title does not match the desired title.
    ///     - If both conditions are met, it updates the navigation title on the main thread.
    ///
    /// 2.  It then returns the content view with two lifecycle modifiers:
    ///
    ///     - `onAppear`: This updates the navigation title to the desired title and assigns
    ///        the ownership to the current view. It also saves the initial value of the
    ///        navigation title before the change.
    ///
    ///     - `onDisappear`: This checks whether the current view is still the owner of the
    ///        navigation title and if so, resets the navigation title to its initial value.
    ///
    ///
    /// - Parameter content: The content view on which this modifier is applied.
    ///
    /// - Returns: The content view with the lifecycle and title change management.
    ///
    
    func body(content: Content) -> some View {
        // In case where title changes after onAppear
        if navStyle.titleOwner == id && navStyle.title != title {
            DispatchQueue.main.async {
                navStyle.title = title
            }
        }
        return content
            .onAppear {
                initialTitleValue = navStyle.title
                initialTitleDisplayModeValue = navStyle.titleDisplayMode
                navStyle.title = title
                navStyle.titleOwner = id
                navStyle.titleDisplayMode = titleDisplayMode
                navStyle.titleDisplayModeOwner = id
            }
            .onDisappear {
                if navStyle.titleOwner == id {
                    navStyle.title = initialTitleValue
                    navStyle.titleOwner = ""
                    navStyle.titleDisplayMode = initialTitleDisplayModeValue
                    navStyle.titleDisplayModeOwner = ""
                }
            }
    }
}
