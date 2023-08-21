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
    
    static var displayLarge: Font = poppins(Poppins.regular, size: 57)
    
    
    static var displayMedium: Font = poppins(Poppins.regular, size: 45)
    
    
    static var displaySmall: Font = poppins(Poppins.regular, size: 36)
    
    
    static var headlineLarge: Font = poppins(Poppins.regular, size: 32)
    
    
    static var headlineMedium: Font = poppins(Poppins.regular, size: 28)
    
    
    static var headlineSmall: Font = poppins(Poppins.regular, size: 24)
    
    
    static var titleLarge: Font = poppins(Poppins.regular, size: 22)
    
    
    static var titleMedium: Font = poppins(Poppins.medium, size: 16)
    
    
    static var titleSmall: Font = poppins(Poppins.medium, size: 14)
    
    
    static var labelLarge: Font = poppins(Poppins.medium, size: 14)
    
    
    static var labelMedium: Font = poppins(Poppins.medium, size: 12)
    
    
    static var labelSmall: Font = poppins(Poppins.medium, size: 11)
    
    
    static var bodyLarge: Font = poppins(Poppins.regular, size: 16)
    
    
    static var bodyMedium: Font = poppins(Poppins.regular, size: 14)
    
    
    static var bodySmall: Font = poppins(Poppins.regular, size: 12)
    
}
