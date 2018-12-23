-keep class com.threatmetrix.TrustDefender.** { *; }
# Required to suppress warning messages about ThreatMetrix SDK
-dontwarn com.threatmetrix.TrustDefender.**

# ThreatMetris SDK wants OkHttp to be available like this :(
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-dontwarn javax.annotation.Nullable
-dontwarn org.conscrypt.OpenSSLProvider
-dontwarn org.conscrypt.Conscrypt
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement