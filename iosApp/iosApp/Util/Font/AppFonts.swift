//
//  AppFonts.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI


//MARK: Font Extension
extension Font {
    enum Poppins {
        case black
        case blackItalic
        case bold
        case boldItalic
        case extraBold
        case extraBoldItalic
        case extraLight
        case extraLightItalic
        case italic
        case light
        case lightItalic
        case medium
        case mediumItalic
        case regular
        case semiBold
        case semiBoldItalic
        case thin
        case thinLight
        
        var value: String {
            switch self {
            case .black:
                return "Poppins-Blac"
            case .blackItalic:
                return "Poppins-BlackItalic"
            case .bold:
                return "Poppins-Bold"
            case .boldItalic:
                return "Poppins-BoldItalic"
            case .extraBold:
                return "Poppins-ExtraBold"
            case .extraBoldItalic:
                return "Poppins-ExtraBoldItalic"
            case .extraLight:
                return "Poppins-ExtraLight"
            case .extraLightItalic:
                return "Poppins-ExtraLightItalic"
            case .italic:
                return "Poppins-Italic"
            case .light:
                return "Poppins-Light"
            case .lightItalic:
                return "Poppins-LightItalic"
            case .medium:
                return "Poppins-Medium"
            case .mediumItalic:
                return "Poppins-MediumItalic"
            case .regular:
                return "Poppins-Regular"
            case .semiBold:
                return "Poppins-SemiBold"
            case .semiBoldItalic:
                return "Poppins-SemiBoldItalic"
            case .thin:
                return "Poppins-Thin"
            case .thinLight:
                return "Poppins-ThinLight"
            }
        }
    }
    
    
    static func poppins(_ type: Poppins, size: CGFloat = 26) -> Font {
        return .custom(type.value, size: size)
    }
    
}
