//
//  DIRegister.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

class DIRegister {
    
    @Environment(\.diContainer) private static var container: DIContainer

    static func initiate() -> DIContainer {
        register()
        
        return container
    }
    
    private static func register() {
//        registerHomeViewModel()
    }
    
    private static func registerDatabaseFactory() {
        container.register {
            DatabaseDriverFactory()
        }
    }
//
//    private static func registerApiClientFactory() {
//        container.register {
//            APIClientFactory(baseUrl: Bundle.main.infoDictionary?["BASE_URL"] as! String)
//        }
//    }
//
//
//    private static func registerHomeViewModel() {
//        container.register {
//            LoginViewModel(repository: self.container.resolve(AuthRepository.self)!)
//        }
//    }
}
