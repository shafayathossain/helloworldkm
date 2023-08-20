//
//  EnvironmentValues+Router.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/**
 This extension adds functionality to SwiftUI's `EnvironmentValues` structure to allow SwiftUI views to interact with the navigation bar's visibility and title.
 
 - `uipNavigationStyle`: An instance of the `NavigationStyle` class, which encapsulates the properties for navigation bar visibility and title. This property is retrieved or set using a custom environment key `NavigationStyleKey`.
 
 - `upNavigationHidden`: A `Binding` to a Boolean value that represents the visibility of the navigation bar. When this value is updated, the corresponding change is made in the navigation bar's visibility. This property is retrieved or set using a custom environment key `NavigationHiddenKey`.
 
 - `upNavigationTitle`: A `Binding` to a String value that represents the current title of the navigation bar. When this value is updated, the corresponding change is made to the navigation bar's title. This property is retrieved or set using a custom environment key `NavigationTitleKey`.
 
 In SwiftUI, `EnvironmentValues` provides a collection of environment values for a view. Custom environment keys allow you to add your own data to the environment, enabling you to share values efficiently between views in your app.
 */
extension EnvironmentValues {
    
    var uipNavigationStyle: NavigationStyle {
        get { self[NavigationStyleKey.self] }
        set {
            self[NavigationStyleKey.self] = newValue
        }
    }
    
    var upNavigationHidden: Binding<Bool> {
        get { self[NavigationHiddenKey.self] }
        set {
            self[NavigationHiddenKey.self] = newValue
        }
    }
    
    var upNavigationTitle: Binding<String> {
        get { self[NavigationTitleKey.self] }
        set {
            self[NavigationTitleKey.self] = newValue
        }
    }
}

/**
 `NavigationTitleKey` is an environment key that holds a `Binding` to a string value representing the title of the navigation bar.
 Its default value is an empty string.
 Environment keys are used in SwiftUI to define new environment values.
 */
private struct NavigationTitleKey: EnvironmentKey {
    static let defaultValue: Binding<String> = .constant("")
}

/**
 `NavigationHiddenKey` is an environment key that holds a `Binding` to a boolean value indicating whether the navigation bar is hidden or not.
 Its default value is `false`, meaning the navigation bar is visible by default.
 Environment keys are used in SwiftUI to define new environment values.
 */
private struct NavigationHiddenKey: EnvironmentKey {
    static let defaultValue: Binding<Bool> = .constant(false)
}

/**
 `NavigationStyleKey` is an environment key that holds an instance of the `NavigationStyle` class.
 Its default value is a new instance of `NavigationStyle`.
 Environment keys are used in SwiftUI to define new environment values.
 */
private struct NavigationStyleKey: EnvironmentKey {
    static let defaultValue: NavigationStyle = NavigationStyle()
}

