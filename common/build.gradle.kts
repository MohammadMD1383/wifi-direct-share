plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
	id("com.android.library")
}

group = "ir.mmd.ktDev"
version = "1.0.0"

kotlin {
	android()
	
	jvm("desktop") {
		jvmToolchain(17)
	}
	
	sourceSets {
		val commonMain by getting {
			dependencies {
				api(compose.runtime)
				api(compose.foundation)
				api(compose.materialIconsExtended)
				api(compose.material3)
				api("com.google.zxing:core:3.5.1")
				api("io.ktor:ktor:2.3.0")
				api("io.ktor:ktor-server-jetty:2.3.0")
			}
		}
		
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}
		
		val androidMain by getting {
			dependencies {
				api("androidx.activity:activity-compose:1.7.1")
				api("androidx.appcompat:appcompat:1.6.1")
				api("androidx.core:core-ktx:1.10.1")
			}
		}
		
		val androidInstrumentedTest by getting {
			dependencies {
				implementation("junit:junit:4.13.2")
			}
		}
		
		val desktopMain by getting {
			dependencies {
				api(compose.preview)
				api("com.formdev:flatlaf:3.1.1")
			}
		}
		
		val desktopTest by getting
	}
}

android {
	compileSdk = 33
	namespace = "ir.mmd.ktDev.common"
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	
	defaultConfig {
		minSdk = 26
		compileSdk = 33
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}
