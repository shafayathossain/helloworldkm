import SwiftUI
import shared

struct ContentView: View {
    
    @EnvironmentObject var router: AppRouter<Route>
    
    let greet = Greeting().greet()

    var body: some View {
        VStack {
            Spacer()
            Text(greet)
            Button(action: {
                router.pop()
            }) {
                Text(verbatim: "Back")
            }
            Spacer()
        }
        .uipNavigationTitle("TITLE @")
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
