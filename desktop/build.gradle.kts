import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

group = "ir.mmd.ktDev"
version = "1.0.0"


kotlin {
	jvm {
		jvmToolchain(17)
		withJava()
	}
	
	sourceSets {
		val jvmMain by getting {
			dependencies {
				implementation(project(":common"))
				implementation(compose.desktop.currentOs)
			}
		}
		
		val jvmTest by getting
	}
}

compose.desktop {
	application {
		mainClass = "MainKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "WiFiDirectShare"
			packageVersion = "1.0.0"
		}
	}
}
