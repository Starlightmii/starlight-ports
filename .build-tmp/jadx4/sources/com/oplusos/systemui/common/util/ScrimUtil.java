package com.oplusos.systemui.common.util;

import android.content.Context;
import android.os.SystemProperties;
import com.oplus.transition.ShellContinuousTransitionUtils;
import com.oplus.util.OplusPlatformLevelUtils;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ScrimUtil {
    public static final String ANIM_DYNAMIC_BLUR;
    public static final int ANIM_LEVEL;
    public static final int DEBUG_LEVEL = SystemProperties.getInt("debug.systemui.gaussblur_level", -1);
    public static final boolean IS_4GB_DEVICE;

    static {
        SystemProperties.getInt("ro.oplus.volume.gaussianlevel", -1);
        ANIM_LEVEL = SystemProperties.getInt(ShellContinuousTransitionUtils.OPLUS_ANIMATION_LEVEL_PROP, -1);
        ANIM_DYNAMIC_BLUR = SystemProperties.get("persist.sys.oplus.systemui.dynamic_blur", "false");
        IS_4GB_DEVICE = SystemProperties.getBoolean("ro.oplus.4gb_device", false);
    }

    public static int getAnimLevel() {
        int i = DEBUG_LEVEL;
        return i != -1 ? i : ANIM_LEVEL;
    }

    public static boolean isBAnimLevel() {
        int i = DEBUG_LEVEL;
        if (i != -1) {
            return i == 4;
        }
        return ANIM_LEVEL == 4;
    }

    public static boolean isBPAnimLevel() {
        int i = DEBUG_LEVEL;
        if (i != -1) {
            return i == 3;
        }
        return ANIM_LEVEL == 3;
    }

    public static boolean isHighGaussianLevel(Context context) {
        int i = DEBUG_LEVEL;
        if (i == -1) {
            int i2 = ANIM_LEVEL;
            if (i2 != -1) {
                if (i2 != 1) {
                    return false;
                }
            } else if (OplusPlatformLevelUtils.getInstance(context).getPlatformGaussianLevel() != 3) {
                return false;
            }
        } else if (i != 1) {
            return false;
        }
        return true;
    }

    public static boolean isLightOsGaussianLevel() {
        int i = DEBUG_LEVEL;
        if (i != -1) {
            return i == 3;
        }
        return ANIM_LEVEL == 3;
    }

    public static boolean isLowGaussianLevel(Context context) {
        int i = DEBUG_LEVEL;
        if (i == -1) {
            int i2 = ANIM_LEVEL;
            if (i2 != -1) {
                if (i2 < 3) {
                    return false;
                }
            } else if (OplusPlatformLevelUtils.getInstance(context).getPlatformGaussianLevel() != 1) {
                return false;
            }
        } else if (i < 3) {
            return false;
        }
        return true;
    }

    public static boolean isMiddleGaussianLevel(Context context) {
        int i = DEBUG_LEVEL;
        if (i != -1) {
            return i == 2;
        }
        int i2 = ANIM_LEVEL;
        if (i2 != -1) {
            return i2 == 2;
        }
        return OplusPlatformLevelUtils.getInstance(context).getPlatformGaussianLevel() == 2;
    }

    public static boolean isMiddleOrHighAnimLevel(Context context) {
        int i = DEBUG_LEVEL;
        if (i != -1) {
            return i <= 2;
        }
        int i2 = ANIM_LEVEL;
        if (i2 != -1) {
            return i2 <= 2;
        }
        return OplusPlatformLevelUtils.getInstance(context).getPlatformGaussianLevel() >= 2;
    }

    public static boolean isWallpaperBlurDisable() {
        if (!IS_4GB_DEVICE) {
            return false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        return NotificationFeatureOption.isWallpaperBlurDisabled;
    }
}
