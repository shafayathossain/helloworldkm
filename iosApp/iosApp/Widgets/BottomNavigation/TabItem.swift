//
//  TabItem.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `TabItem`
///
/// A struct that represents a tab in the `BottomNavigationView`.
///
/// - Properties:
///   - `view`: The SwiftUI view that will be displayed when the tab is selected.
///   - `icon`: A string representing the system name of the icon for the tab.
///   - `label`: The label text for the tab.
///   - `initialRoute`: The initial route for the tab's navigation stack.
///
/// - Example Usage:
/// ```
/// TabItem(view: AnyView(HomeView()), icon: "house.fill", label: "Home", initialRoute: .homeScreen)
/// ```
struct TabItem {
    
    var view: AnyView
    var icon: String
    var label: String
    var initialRoute: Route
}

