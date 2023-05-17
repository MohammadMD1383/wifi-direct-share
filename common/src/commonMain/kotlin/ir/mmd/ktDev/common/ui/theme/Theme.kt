package ir.mmd.ktDev.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
expect fun WifiDirectShareTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
