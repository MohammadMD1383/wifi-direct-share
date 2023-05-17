package ir.mmd.ktDev.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val DarkColorScheme = darkColorScheme()
val LightColorScheme = lightColorScheme()

val LocalDarkTheme = compositionLocalOf { false }

@Composable
actual fun WifiDirectShareTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
	val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
	
	CompositionLocalProvider(LocalDarkTheme provides darkTheme) {
		MaterialTheme(
			colorScheme = colorScheme,
			typography = Typography,
			content = content
		)
	}
}
