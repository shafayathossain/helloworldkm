//
//  LanguageManger.swift
//  iosApp
//
//  Created by Shafayat Hossain on 3/9/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Combine

class LanguageManager: ObservableObject {
    // The shared instance for the app
    static let shared = LanguageManager()

    // Published property that the views will observe
    @Published var currentLanguage: String = UserDefaults.standard.string(forKey: "appLanguage") ?? Bundle.main.preferredLocalizations.first ?? "en" {
        didSet {
            print("SETTING LANGUEGE \(currentLanguage)")
            UserDefaults.standard.setValue(currentLanguage, forKey: "appLanguage")
            Bundle.setLanguage(currentLanguage)
        }
    }

    private var availableLanguages: [String] {
        return Bundle.main.availableLanguages.sorted()
    }

    var availableLanguageDisplayNames: [(code: String, name: String)] {
        var names: [(code: String, name: String)] = []
        var langs = availableLanguages
        print(langs)
        for code in availableLanguages {
            if let displayName = Locale.current.localizedString(forLanguageCode: code) {
                names.append((code: code, name: displayName))
            }
        }
        
        return names.sorted { $0.name < $1.name }
    }

    private init() {
        if let savedLanguage = UserDefaults.standard.string(forKey: "appLanguage") {
            currentLanguage = savedLanguage
            Bundle.setLanguage(savedLanguage)
        }
    }
}

extension Bundle {
    var availableLanguages: [String] {
        return (infoDictionary?["CFBundleLocalizations"] as? [String]) ?? []
    }

    static func setLanguage(_ language: String) {
        defer {
            object_setClass(Bundle.main, AnyLanguageBundle.self)
        }
        objc_setAssociatedObject(Bundle.main, &kBundleKey, nil, .OBJC_ASSOCIATION_RETAIN_NONATOMIC)
    }
}

private var kBundleKey: UInt8 = 0

private final class AnyLanguageBundle: Bundle {
    override func localizedString(forKey key: String, value: String?, table tableName: String?) -> String {
        guard let path = objc_getAssociatedObject(self, &kBundleKey) as? Bundle else {
            return super.localizedString(forKey: key, value: value, table: tableName)
        }
        return path.localizedString(forKey: key, value: value, table: tableName)
    }
}
