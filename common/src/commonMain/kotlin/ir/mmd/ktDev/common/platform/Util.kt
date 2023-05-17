package ir.mmd.ktDev.common.platform

import androidx.compose.ui.graphics.ImageBitmap

expect fun imageBitmapOf(pixels: IntArray, width: Int, height: Int): ImageBitmap
