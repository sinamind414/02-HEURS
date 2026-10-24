# ---------------------------------------------------------------------------
# Sa'atan — R8 / ProGuard rules
# The release build runs with minification and resource shrinking enabled.
# ---------------------------------------------------------------------------

# Keep generic signatures and annotations: Moshi and Retrofit both read them.
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations, AnnotationDefault
-keepattributes *Annotation*

# Kotlin metadata is used by kotlin-reflect (Moshi KotlinJsonAdapterFactory).
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keep class kotlin.reflect.** { *; }
-dontwarn kotlin.reflect.jvm.internal.**

# --- Moshi -----------------------------------------------------------------
# Moshi ships consumer rules, but the reflective Kotlin adapters need the
# annotations kept on the model classes.
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
}
-keep @interface com.squareup.moshi.JsonQualifier
-keepclasseswithmembers class * {
    @com.squareup.moshi.JsonClass <fields>;
}
-keep,allowobfuscation @com.squareup.moshi.JsonClass class * { *; }

# API payload models are (de)serialised by name.
-keep class com.example.data.remote.** { *; }

# --- Retrofit / OkHttp -----------------------------------------------------
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
-dontwarn javax.annotation.**

# --- Room ------------------------------------------------------------------
# Room generates implementations in the same package and ships its own rules;
# keep the entities and DAOs anyway so schema lookups keep working.
-keep class com.example.data.local.** { *; }
-dontwarn androidx.room.paging.**

# --- App -------------------------------------------------------------------
# Keep the notification receiver: it is referenced from the manifest only.
-keep class com.example.notifications.** { *; }

# Strip verbose Android logging in release builds.
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
