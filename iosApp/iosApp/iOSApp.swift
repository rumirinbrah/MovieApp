import SwiftUI
import com.zzz.movie.di.initKoin

@main
struct iOSApp: App {
    init(){
        initKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}