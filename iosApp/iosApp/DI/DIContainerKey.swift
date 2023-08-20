//
//  DIContainerKey.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DIContainerKey: EnvironmentKey {
    static let defaultValue: DIContainer = DIContainer.shared
}
