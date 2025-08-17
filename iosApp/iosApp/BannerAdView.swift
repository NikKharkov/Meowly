import SwiftUI
import GoogleMobileAds

struct BannerAdView: UIViewRepresentable {
    func makeUIView(context: Context) -> BannerView {
        let bannerView = BannerView()
        bannerView.adUnitID = "ca-app-pub-3193915441713680/1224026647"
        bannerView.adSize = AdSizeLargeBanner

        let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
        if let rootViewController = windowScene?.windows.first?.rootViewController {
            bannerView.rootViewController = rootViewController
        }

        bannerView.load(Request())
        return bannerView
    }

    func updateUIView(_ uiView: BannerView, context: Context) {

    }
}