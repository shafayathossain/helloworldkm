//
//  AppToggleButton.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppToggleButton: View {
    @State private var isOn: Bool = false

    var body: some View {
        VStack {
            Toggle(isOn: $isOn) {
                Text("Toggle Switch")
            }
            .labelsHidden()
            .toggleStyle(SwitchToggleStyle(tint: .Primary)) // Change the tint color to your desired color
            .padding()
        }
    }
}

struct AppToggleButton_Previews: PreviewProvider {
    static var previews: some View {
        AppToggleButton()
    }
}


