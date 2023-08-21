//
//  TabRouter.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

class TabRouter: ObservableObject {
    @Published var router: AppRouter<Route>
    
    init(router: AppRouter<Route>) {
        self.router = router
    }
}
