//
//  DebugLog.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class DebugLog: Logger {
    func log(_ value: String) {
        print("=========================================")
        print(value)
        print("=========================================")
    }
}
