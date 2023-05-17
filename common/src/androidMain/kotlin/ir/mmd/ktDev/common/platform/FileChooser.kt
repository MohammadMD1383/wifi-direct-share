package ir.mmd.ktDev.common.platform

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import ir.mmd.ktDev.common.FileModel
import kotlinx.coroutines.channels.Channel

actual fun interface FileChooser {
	actual companion object {
		@SuppressLint("Range")
		@Composable
		actual operator fun invoke(): FileChooser {
			val channel = remember { Channel<FileModel?>() }
			val contentResolver = LocalContext.current.contentResolver
			
			val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
				var file: FileModel? = null
				
				if (uri != null) {
					contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE), null, null, null).use {
						if (it != null && it.moveToFirst()) {
							val name = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))!!
							val size = it.getLong(it.getColumnIndex(OpenableColumns.SIZE))
							
							file = FileModel(name, size, contentResolver.openInputStream(uri)!!)
						}
					}
				}
				
				channel.trySend(file)
			}
			
			return remember {
				FileChooser {
					launcher.launch("*/*")
					channel.receive()
				}
			}
		}
	}
	
	actual suspend fun prompt(): FileModel?
}
