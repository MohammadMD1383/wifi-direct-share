package ir.mmd.ktDev.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import ir.mmd.ktDev.common.platform.imageBitmapOf
import kotlin.math.roundToInt

@Composable
fun QrCode(contents: String, size: Dp) {
	val sizeInPx = LocalDensity.current.run { size.toPx().roundToInt() }
	val background = MaterialTheme.colorScheme.surface.toArgb()
	val foreground = MaterialTheme.colorScheme.onSurface.toArgb()
	val bitmap = remember {
		try {
			val matrix = QRCodeWriter().encode(contents, BarcodeFormat.QR_CODE, sizeInPx, sizeInPx)
			val pixels = IntArray(sizeInPx * sizeInPx)
			
			for (y in 0 until sizeInPx) {
				for (x in 0 until sizeInPx) {
					pixels[y * sizeInPx + x] = if (matrix[x, y]) foreground else background
				}
			}
			
			imageBitmapOf(pixels, sizeInPx, sizeInPx)
		} catch (e: Exception) {
			null
		}
	}
	
	if (bitmap != null) {
		Image(
			bitmap = bitmap,
			contentDescription = "QrCode",
			modifier = Modifier
				.size(size)
				.clip(RoundedCornerShape(16.dp)),
		)
	} else {
		Text("ERROR")
	}
}
