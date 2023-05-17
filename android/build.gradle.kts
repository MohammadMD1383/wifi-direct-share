plugins {
	id("org.jetbrains.compose")
	id("com.android.application")
	kotlin("android")
}

group = "ir.mmd.ktDev"
version = "1.0.0"

repositories {
	google()
	mavenCentral()
}

dependencies {
	implementation(project(":common"))
}

android {
	namespace = "ir.mmd.ktDev.android"
	compileSdk = 33
	
	defaultConfig {
		applicationId = "ir.mmd.ktDev.android"
		minSdk = 26
		targetSdk = 33
		versionCode = 1
		versionName = "1.0.0"
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	
	buildTypes {
		getByName("release") {
			isMinifyEnabled = true
			isShrinkResources = true
		}
	}
}
