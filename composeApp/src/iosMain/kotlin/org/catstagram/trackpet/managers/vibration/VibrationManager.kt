package org.catstagram.trackpet.managers.vibration

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

actual object VibrationManager {
    private val impactGenerator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium)

    actual fun vibrate(duration: Long) {
        impactGenerator.impactOccurred()
    }
}