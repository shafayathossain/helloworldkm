//
//  DummyScreen.swift
//  iosApp
//
//  Created by Shafayat Hossain on 4/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct DummyScreen: View {
    
    @EnvironmentObject var languageManager: LanguageManager

    var body: some View {
        VStack {
            Spacer()
            languageDropdown()
            Text("hello_world")
                .padding()
            Spacer()
        }
    }

    private func languageDropdown() -> some View {
        Picker(
            selection: $languageManager.currentLanguage,
            label: Text(verbatim: "Select Language")) {
                ForEach(languageManager.availableLanguageDisplayNames, id: \.code) { language in
                    Text(language.name).tag(language.code)
                }
            }
        .pickerStyle(MenuPickerStyle())
        .padding()
    }
}

struct DummyScreen_Previews: PreviewProvider {
    static var previews: some View {
        DummyScreen()
            .environmentObject(LanguageManager.shared)
            .environment(\.locale, .init(identifier: LanguageManager.shared.currentLanguage))
    }
}

