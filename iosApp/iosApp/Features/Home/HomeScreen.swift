//
//  HomeScreen.swift
//  iosApp
//
//  Created by Shafayat Hossain on 20/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @EnvironmentObject var router: AppRouter<Route>
    
    let greet = Greeting().greet()

    var body: some View {
        VStack {
            Spacer()
            Text(greet)
            Button(action: {
                router.push(.dummyScreen)
            }) {
                Text(verbatim: "Go To Dummy")
            }
            Spacer()
        }
        .uipNavigationTitle("TITLE @")
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
