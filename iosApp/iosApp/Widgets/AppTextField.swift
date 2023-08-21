//
//  AppTextField.swift
//  iosApp
//
//  Created by Shafayat Hossain on 21/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AdvancedMaterialTextField: View {
    var placeholder: String = "Enter text here"
    var label: String = "Label"
    var contentType: UITextContentType? = nil
    var systemIconName: String = "xmark.circle.fill"
    var onIconClick: (() -> Void)? = nil
    var validateInput: ((_ text: String) -> Bool)? = nil
    @State var text: String = ""
    @State private var isFocused: Bool = false
    @State private var showError: Bool = false
    
    var body: some View {
        VStack(spacing: 8) {
            HStack {
                Text(label)
                    .font(.titleLarge)
                    .foregroundColor(.OnSurface)
                    .scaleEffect(0.75, anchor: .leading)
                    .padding(.horizontal, 4)
                Spacer()
            }
            
            ZStack(alignment: .leading) {
                TextField(placeholder, text: $text, onEditingChanged: { editing in
                    withAnimation {
                        self.isFocused = editing
                    }
                })
                .textContentType(contentType)
                .foregroundColor(.black)
                .frame(height: 52)
                .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 30))
                .onReceive(text.publisher.collect()) {
                    self.text = String($0.prefix(256))
                    if(validateInput != nil) {
                        showError = validateInput!(text)
                    }
                }
                .overlay(
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(showError ? Color.red : (isFocused ? Color.blue : Color.gray), lineWidth: 1)
                )
                
                if !text.isEmpty {
                    HStack {
                        Spacer()
                        Image(systemName: systemIconName)
                            .resizable()
                            .frame(width: 16, height: 16)
                            .padding(.trailing, 8)
                            .foregroundColor(.gray)
                            .onTapGesture(perform: onIconClick != nil ? onIconClick! : { text = "" })
                    }
                }
            }
            
            if showError {
                HStack {
                    Text("Error: Invalid input")
                        .font(.caption)
                        .foregroundColor(.red)
                    Spacer()
                }
            }
        }
        .padding(.horizontal)
    }
}

struct AdvancedMaterialTextField_Previews: PreviewProvider {
    static var previews: some View {
        AdvancedMaterialTextField()
    }
}
