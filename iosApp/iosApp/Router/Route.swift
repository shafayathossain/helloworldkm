//
//  Route.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum Route: Equatable, Hashable {
    case homeScreen
}


extension Route: Identifiable {
    var id: Self { self }
}
