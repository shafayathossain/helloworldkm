//
//  RoutingView.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Foundation

struct RoutingView: View {
    
    @StateObject var router = AppRouter(initial: Route.homeScreen)
    
    var body: some View {
        
        AppRouterHost(router)  { route in
            switch route {
            case .homeScreen: HomeView()
            }
        }
    }
}
