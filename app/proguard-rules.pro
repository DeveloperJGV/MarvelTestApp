# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Getters y setters para propiedades usadas con serialización
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
    public *** get*();
    public void set*(***);
}

# Clases que implementan Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Nombres de métodos y clases usados en Views para eventos como onClick
-keepclassmembers class * {
    public void *(android.view.View);
}

# Clases de ViewModel para evitar que se eliminen
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Clases de Room y sus métodos para evitar eliminaciones y ofuscación
-keep @androidx.room.Entity class * {
    *;
}
-keep @androidx.room.Dao class * {
    *;
}
-keep class **_Impl { *; }

# Anotaciones para evitar que se eliminen durante la ofuscación
-keepattributes *Annotation*

# Clases de Retrofit para que no sean ofuscadas, lo que podría causar problemas con las llamadas de red
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Para inyección de dependencias, es importante mantener las clases inyectadas
-keep class * {
    @javax.inject.Inject <init>(...);
}

# Mantén las clases de EventBus
-keepclassmembers class ** {
    public void onEvent*(**);
}

# Para evitar problemas con la serialización de JSON
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Clases enum para evitar problemas con la eliminación de código
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Reglas para optimizar la eliminación de código no utilizado
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}
