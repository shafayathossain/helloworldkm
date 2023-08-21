//
//  AppDropdown.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppDropdown: View {
    let options: [String]
    @State var selectedOption: Int = 0
    
    var body: some View {
        Menu {
            Picker("", selection: $selectedOption) {
                ForEach(0..<options.count, id: \.self) { index in
                    Text(options[index])
                        .padding(.vertical, 8)
                        .tag(index)
                }
            }
            .labelsHidden()
            .pickerStyle(.automatic)
        } label: {
            Button(action: {}) {
                HStack {
                    Text(selectedOption < 0 ? "Select an option" : options[selectedOption])
                        .font(Font.bodyLarge())
                        .foregroundColor(Color.OnSurfaceVariant)
                    Spacer()
                    Image(systemName: "arrowtriangle.down.fill")
                        .resizable()
                        .frame(width: 14, height: 8)
                        .foregroundColor(Color.OnSurfaceVariant)
                }
                .padding(EdgeInsets(top: 20, leading: 20, bottom: 20, trailing: 20))
                .overlay(
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(selectedOption >= 0 ? Color.Primary : Color.gray, lineWidth: 1)
                )

            }
        }
    }
}


struct AppDropdown_Previews: PreviewProvider {
    static var previews: some View {
        AppDropdown(options: ["Item 1", "Item 2", "Item 3"], selectedOption: -1)
    }
}


