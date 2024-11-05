package nl.mythicproductions.socialdealdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import nl.mythicproductions.socialdealdemo.ui.navigation.RootNavigator
import nl.mythicproductions.socialdealdemo.ui.theme.SocialDealDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            SocialDealDemoTheme {
                RootNavigator()
            }
        }
    }
}