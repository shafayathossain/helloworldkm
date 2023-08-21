//
//  BottomNavigationView.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

/// `BottomNavigationView`
///
/// A SwiftUI view component that displays a tab-based navigation interface.
///
/// - Properties:
///   - `tabItems`: An array of `TabItem` objects representing each tab in the navigation view.
///   - `router`: An `AppRouter` instance that manages the navigation stack for the entire application.
///
/// - Body:
/// The body consists of a `TabView` that iterates over the `tabItems` array. For each `TabItem`,
/// it creates a tab with the specified view, icon, and label. Each tab's view is also provided with its own `TabRouter`
/// instance for managing its navigation.
///
/// - Environment:
/// The `BottomNavigationView` provides two environment objects:
/// 1. `router`: The main `AppRouter` instance for the entire application.
/// 2. `tabRouter`: A `TabRouter` instance specific to each tab.
///
/// - Example Usage:
/// ```
/// BottomNavigationView(
///     tabItems: [
///         TabItem(view: AnyView(HomeView()), icon: "house.fill", label: "Home", initialRoute: .homeScreen),
///         TabItem(view: AnyView(ProfileView()), icon: "person.fill", label: "Profile", initialRoute: .profileScreen)
///     ],
///     router: AppRouter(initial: .homeScreen)
/// )
/// ```
struct BottomNavigationView: View {
    
    var tabItems: [TabItem]
    @StateObject var router: AppRouter<Route>
    
    var body: some View {
        TabView {
            ForEach(tabItems.indices, id: \.self) { index in
                @StateObject var tabRouter = TabRouter(router: AppRouter(initial: tabItems[index].initialRoute))
                tabItems[index].view
                    .tabItem {
                        Image(systemName: tabItems[index].icon)
                        Text(tabItems[index].label)
                    }
                    .tag(tabItems[index].initialRoute)
                    .environmentObject(tabRouter)
            }
        }
        .environmentObject(router)
    }
}

