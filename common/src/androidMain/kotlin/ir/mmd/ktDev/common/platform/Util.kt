package ir.mmd.ktDev.common.platform

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun imageBitmapOf(pixels: IntArray, width: Int, height: Int): ImageBitmap {
	return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.ARGB_8888).asImageBitmap()
}
