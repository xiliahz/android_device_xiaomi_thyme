#
# Copyright (C) 2023 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from sm8250-common
include device/xiaomi/sm8250-common/BoardConfigCommon.mk

DEVICE_PATH := device/xiaomi/thyme

BUILD_BROKEN_DUP_RULES := true

# Board
TARGET_BOARD_INFO_FILE := $(DEVICE_PATH)/board-info.txt

# Display
TARGET_SCREEN_DENSITY := 440

# Fingerprint
TARGET_SURFACEFLINGER_UDFPS_LIB := //$(DEVICE_PATH):libudfps_extension.thyme
TARGET_USES_FOD_ZPOS := true

# Kernel
TARGET_KERNEL_CONFIG += vendor/xiaomi/thyme.config

# Properties
TARGET_VENDOR_PROP += $(DEVICE_PATH)/vendor.prop

# Inherit from the proprietary version
include vendor/xiaomi/thyme/BoardConfigVendor.mk
