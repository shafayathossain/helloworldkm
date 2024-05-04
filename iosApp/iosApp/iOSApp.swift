import SwiftUI

@main
struct iOSApp: App {
    
    let languageManager = LanguageManager.shared
    @StateObject private var diContainer = DIRegister.initiate()
    
    var body: some Scene {
        WindowGroup {
            NavigationView {
                RoutingView()
            }
            .environmentObject(languageManager)
            .environmentObject(diContainer)
            .environment(\.locale, .init(identifier: languageManager.currentLanguage))
        }
    }
}
