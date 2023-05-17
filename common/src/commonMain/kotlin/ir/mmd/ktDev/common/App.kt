package ir.mmd.ktDev.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.mmd.ktDev.common.platform.FileChooser
import ir.mmd.ktDev.common.ui.components.QrCode
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
	val fileChooser = FileChooser()
	val scope = rememberCoroutineScope()
	var fileServer by remember { mutableStateOf(null as FileServer?) }
	
	Scaffold { contentPadding ->
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.fillMaxSize()
				.padding(contentPadding)
		) {
			if (fileServer == null) {
				Button(onClick = {
					scope.launch {
						val file = fileChooser.prompt() ?: return@launch
						fileServer = FileServer(file)
					}
				}) {
					Icon(Icons.Rounded.Folder, "Folder")
					Spacer(Modifier.width(8.dp))
					Text("Browse")
				}
			} else {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier.verticalScroll(rememberScrollState())
				) {
					FlowRow(
						horizontalArrangement = Arrangement.Center,
					) {
						fileServer!!.getAddresses().forEach {
							Column(
								verticalArrangement = Arrangement.spacedBy(8.dp),
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier.padding(8.dp)
							) {
								QrCode("http://$it", 200.dp)
								Text(it)
							}
						}
					}
					
					Button(onClick = {
						fileServer!!.destroy()
						fileServer = null
					}) {
						Icon(Icons.Rounded.Close, "Close")
						Spacer(Modifier.width(8.dp))
						Text("Stop")
					}
				}
			}
		}
	}
}
