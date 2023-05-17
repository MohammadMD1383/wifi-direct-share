package ir.mmd.ktDev.common.platform

import androidx.compose.runtime.Composable
import ir.mmd.ktDev.common.FileModel

expect fun interface FileChooser {
	companion object {
		@Composable
		operator fun invoke(): FileChooser
	}
	
	suspend fun prompt(): FileModel?
}
