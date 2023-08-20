import SwiftUI

@main
struct iOSApp: App {
    
    @StateObject private var diContainer = DIRegister.initiate()
    
    var body: some Scene {
        WindowGroup {
            NavigationView {
                RoutingView()
            }
            .environmentObject(diContainer)
        }
    }
}
