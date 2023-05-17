import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ir.mmd.ktDev.common.App
import ir.mmd.ktDev.common.ui.theme.WifiDirectShareTheme

fun main(args: Array<String>) = application {
	val darkTheme =	args.firstOrNull()?.lowercase()?.let {
		it == "dark" || it == "d" || it == "night" || it == "n"
	} ?: false
	
	WifiDirectShareTheme(darkTheme = darkTheme) {
		Window(onCloseRequest = ::exitApplication) {
			App()
		}
	}
}
