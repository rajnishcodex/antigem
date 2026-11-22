plugins {
    id("com.android.application")
}

android {
    namespace = "com.rajnishcodex.rajnishgem"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rajnishcodex.rajnishgem"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            // This clean-room build publishes a release APK signed with the
            // standard Android debug key, so no secret keystore is required.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
