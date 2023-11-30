import java.util.Properties
import java.io.FileInputStream
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

// Cargar las propiedades del archivo local.properties
val properties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}

// Obtener las llaves de API MARVEL
val marvelApiKeyPublic: String? = properties.getProperty("MARVEL_API_KEY_PUBLIC")
val marvelApiKeyPrivate: String? = properties.getProperty("MARVEL_API_KEY_PRIVATE")

android {
    namespace = "com.aviva.marveltestapp"
    compileSdk = 34

    // Habilitar la generación de campos BuildConfig personalizados
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }


    defaultConfig {
        applicationId = "com.aviva.marveltestapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "MARVEL_API_KEY_PUBLIC", "\"$marvelApiKeyPublic\"")
        buildConfigField("String", "MARVEL_API_KEY_PRIVATE", "\"$marvelApiKeyPrivate\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Core library de Kotlin para Android
    implementation("androidx.core:core-ktx:1.12.0")

    // Biblioteca de soporte para componentes de UI compatibles con versiones anteriores
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Componentes de Material Design
    implementation("com.google.android.material:material:1.10.0")

    // Diseño de restricción para vistas
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Bibliotecas de prueba unitaria
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit para llamadas de red RESTful
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Convertidor Gson para Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp para llamadas HTTP eficientes
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // ViewModel y LiveData para arquitectura MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Room para la persistencia de datos en SQLite
    implementation("androidx.room:room-runtime:2.3.0")
    // Procesador de anotaciones para Room
    kapt("androidx.room:room-compiler:2.3.0")

    // Dagger-Hilt para inyección de dependencias
    implementation("com.google.dagger:hilt-android:2.48.1")
    // Procesador de anotaciones para Dagger-Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    // Componentes de navegación de Jetpack para manejar la navegación en la app
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Picasso para cargar y mostrar imágenes de internet
    implementation("com.squareup.picasso:picasso:2.8")

}
