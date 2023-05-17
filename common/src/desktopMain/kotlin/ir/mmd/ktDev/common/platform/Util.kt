package ir.mmd.ktDev.common.platform

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage

actual fun imageBitmapOf(pixels: IntArray, width: Int, height: Int): ImageBitmap {
	return BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).apply {
		for (y in 0 until height) {
			for (x in 0 until width) {
				setRGB(x, y, pixels[y * width + x])
			}
		}
	}.toComposeImageBitmap()
}
