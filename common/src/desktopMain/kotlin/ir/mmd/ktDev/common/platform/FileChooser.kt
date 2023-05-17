package ir.mmd.ktDev.common.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf
import ir.mmd.ktDev.common.FileModel
import ir.mmd.ktDev.common.ui.theme.LocalDarkTheme
import javax.swing.JFileChooser

actual fun interface FileChooser {
	actual companion object {
		@Composable
		actual operator fun invoke(): FileChooser {
			val darkTheme = LocalDarkTheme.current
			
			return remember {
				FileChooser {
					if (darkTheme) {
						FlatDarkLaf.setup()
					} else {
						FlatLightLaf.setup()
					}
					
					val chooser = JFileChooser()
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						return@FileChooser FileModel(chooser.selectedFile.name, chooser.selectedFile.length(), chooser.selectedFile.inputStream())
					}
					
					return@FileChooser null
				}
			}
		}
	}
	
	actual suspend fun prompt(): FileModel?
}
