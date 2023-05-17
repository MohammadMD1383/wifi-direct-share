package ir.mmd.ktDev.common

import java.io.InputStream

data class FileModel(
	val name: String,
	val size: Long,
	val stream: InputStream
)
