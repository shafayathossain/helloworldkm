//
//  DIContainer.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class DIContainer: ObservableObject {
    
    static let shared = DIContainer()
        
    private init() {}
    
    fileprivate var services = [ObjectIdentifier : Any]()
    
    func register<Service>(
        _ type: Service.Type = Service.self,
        factory: @escaping () -> Service
    ) {
        let key = ObjectIdentifier(type)
        services[key] = factory
    }
    
    func resolve<Service>(_ type: Service.Type = Service.self) -> Service? {
        let key = ObjectIdentifier(type)
        guard let factory = services[key] as? () -> Service else { return nil }
        return factory()
    }
}
