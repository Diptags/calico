#include "jni.h"
#include "stdlib.h"

JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_rdpradiptagitayas_calico_views_extras_ExtrasListFragment_getRandomNumberFromNative(
        JNIEnv *env, jobject obj) {
    return rand() % 10;
}
