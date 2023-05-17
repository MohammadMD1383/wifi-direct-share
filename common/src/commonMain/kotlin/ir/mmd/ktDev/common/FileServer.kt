package ir.mmd.ktDev.common

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import java.net.NetworkInterface

class FileServer(private val file: FileModel) {
	@Suppress("ExtractKtorModule")
	private val ktorServer = embeddedServer(Jetty, 0) {
		routing {
			get("/") {
				call.response.header("Content-Type", "application/octet-stream")
				call.response.header("Content-Disposition", "attachment; filename=\"${file.name}\"")
				call.response.header("Content-Length", file.size) // fixme: this doesn't work
				call.respondOutputStream {
					file.stream.copyTo(this)
				}
			}
		}
	}
	
	fun getAddresses(): List<String> {
		val hosts = mutableListOf<String>()
		val port = runBlocking { ktorServer.resolvedConnectors().first().port }
		
		NetworkInterface.getNetworkInterfaces().asIterator().forEach { networkInterface ->
			networkInterface.inetAddresses.asIterator().forEach { inetAddress ->
				val hostAddress = inetAddress.hostAddress
				if (hostAddress.startsWith("192.168.")) {
					hosts += hostAddress
				}
			}
		}
		
		return hosts.map { "$it:$port" }
	}
	
	init {
		ktorServer.start()
	}
	
	fun destroy() {
		ktorServer.stop(0, 0)
		file.stream.close()
	}
}
