package ir.mmd.ktDev.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import ir.mmd.ktDev.common.App
import ir.mmd.ktDev.common.ui.theme.WifiDirectShareTheme

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			WifiDirectShareTheme {
				App()
			}
		}
	}
}
