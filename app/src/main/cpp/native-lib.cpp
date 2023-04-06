#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_ahmdalii_asteroidradar_BaseApplication_getBaseURL(
        JNIEnv *env,
        jobject) {
    std::string BASE_URL = "https://api.nasa.gov/";
    return env->NewStringUTF(BASE_URL.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_ahmdalii_asteroidradar_BaseApplication_getAPIKey(
        JNIEnv *env,
        jobject) {
    std::string API_KEY = "AwBKKZQ342F7akGJ20VTnA7l4RXQUh10hmi3HkGf";
    return env->NewStringUTF(API_KEY.c_str());
}