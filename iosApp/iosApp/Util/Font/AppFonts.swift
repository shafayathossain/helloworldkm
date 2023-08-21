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
    
    
    static private func poppins(_ type: Poppins, size: CGFloat = 26) -> Font {
        return .custom(type.value, size: size)
    }
    
    static func displayLarge() -> Font {
        return poppins(Poppins.regular, size: 57)
    }
    
    static func displayMedium() -> Font {
        return poppins(Poppins.regular, size: 45)
    }
    
    static func displaySmall() -> Font {
        return poppins(Poppins.regular, size: 36)
    }
    
    static func headlineLarge() -> Font {
        return poppins(Poppins.regular, size: 32)
    }
    
    static func headlineMedium() -> Font {
        return poppins(Poppins.regular, size: 28)
    }
    
    static func headlineSmall() -> Font {
        return poppins(Poppins.regular, size: 24)
    }
    
    static func titleLarge() -> Font {
        return poppins(Poppins.regular, size: 22)
    }
    
    static func titleMedium() -> Font {
        return poppins(Poppins.medium, size: 16)
    }
    
    static func titleSmall() -> Font {
        return poppins(Poppins.medium, size: 14)
    }
    
    static func labelLarge() -> Font {
        return poppins(Poppins.medium, size: 14)
    }
    
    static func labelMedium() -> Font {
        return poppins(Poppins.medium, size: 12)
    }
    
    static func labelSmall() -> Font {
        return poppins(Poppins.medium, size: 11)
    }
    
    static func bodyLarge() -> Font {
        return poppins(Poppins.regular, size: 16)
    }
    
    static func bodyMedium() -> Font {
        return poppins(Poppins.regular, size: 14)
    }
    
    static func bodySmall() -> Font {
        return poppins(Poppins.regular, size: 12)
    }
}
