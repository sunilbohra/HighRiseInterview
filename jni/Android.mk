LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := MyApplication3
LOCAL_SRC_FILES := MovieController.h

include $(BUILD_SHARED_LIBRARY)