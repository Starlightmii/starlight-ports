package com.oplus.systemui.qs.utils;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Iris5Util {
    private static final String KEEP_ALIVE_APP_NAME = "com.oplus.multimedia.pixelworks";
    public static final String KEY_CUSTOMIZE_MULTIMEDIA_VIDEO_SUPER_RESOLUTION = "customize_multimedia_video_super_resolution";
    public static final String KEY_OSIE_IRIS5_SWITCH = "osie_iris5_switch";
    public static final String KEY_OSIE_MOTION_FLUENCY_SWITCH;
    public static final String KEY_VIDEO_SWITCH = "osie_video_display_switch";
    private static final String TAG = "Iris5Util";
    public static final Iris5Util INSTANCE = new Iris5Util();
    public static final Uri KEEP_ALIVE_PROVIDER_URI = Uri.parse("content://com.oplus.appmanager.provider.db/settings_table");

    static {
        QSFeatureOption.INSTANCE.getClass();
        KEY_OSIE_MOTION_FLUENCY_SWITCH = QSFeatureOption.isSupportIris5Rlm() ? "osie_motion_fluency_switch_rlm" : "osie_motion_fluency_switch";
    }

    public static final boolean isIris5MotionFluencySwitch(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), KEY_OSIE_MOTION_FLUENCY_SWITCH, 0) == 1;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0029  */
    /* JADX WARN: Code duplicated, block: B:15:0x002f  */
    /* JADX WARN: Code duplicated, block: B:18:0x0039  */
    /* JADX WARN: Code duplicated, block: B:20:0x0044  */
    /* JADX WARN: Code duplicated, block: B:23:0x004e  */
    /* JADX WARN: Code duplicated, block: B:8:0x0019  */
    public static final boolean isIris5Switch(Context context) {
        if (!isIris5VideoSuperResolutionSwitchSupport() && !isOsieVideoDisplaySwitchSupport()) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportIris5()) {
                if (!isIris5VideoSuperResolutionSwitchSupport()) {
                    if (isOsieVideoDisplaySwitchSupport()) {
                        QSFeatureOption.INSTANCE.getClass();
                        if (QSFeatureOption.isSupportIris5()) {
                            setIris5Switch(context, false);
                        } else {
                            setIris5Switch(context, false);
                        }
                    } else {
                        QSFeatureOption.INSTANCE.getClass();
                        if (QSFeatureOption.isSupportIris5()) {
                            setIris5Switch(context, false);
                        } else {
                            setIris5Switch(context, false);
                        }
                    }
                } else if (isOsieVideoDisplaySwitchSupport()) {
                    QSFeatureOption.INSTANCE.getClass();
                    if (QSFeatureOption.isSupportIris5()) {
                        setIris5Switch(context, false);
                    } else {
                        setIris5Switch(context, false);
                    }
                } else {
                    QSFeatureOption.INSTANCE.getClass();
                    if (QSFeatureOption.isSupportIris5()) {
                        setIris5Switch(context, false);
                    } else {
                        setIris5Switch(context, false);
                    }
                }
            }
        } else if (!isIris5VideoSuperResolutionSwitchSupport() && isIris5VideoSuperResolutionSwitch(context)) {
            setIris5Switch(context, true);
        } else if (isOsieVideoDisplaySwitchSupport() || !isIris5VideoDisplaySwitch(context)) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportIris5() || !isIris5MotionFluencySwitch(context)) {
                setIris5Switch(context, false);
            } else {
                setIris5Switch(context, true);
            }
        } else {
            setIris5Switch(context, true);
        }
        return Settings.Secure.getInt(context.getContentResolver(), KEY_OSIE_IRIS5_SWITCH, 0) == 1;
    }

    public static final boolean isIris5VideoDisplaySwitch(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), KEY_VIDEO_SWITCH, 0) == 1;
    }

    public static final boolean isIris5VideoSuperResolutionSwitch(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), KEY_CUSTOMIZE_MULTIMEDIA_VIDEO_SUPER_RESOLUTION, 0) == 1;
    }

    public static final boolean isIris5VideoSuperResolutionSwitchSupport() {
        if (OsBinderCacheUtils.getCurrentUserId() != 0) {
            return false;
        }
        QSFeatureOption.INSTANCE.getClass();
        return QSFeatureOption.isSupportVideoSuperResolution();
    }

    public static final boolean isOsieVideoDisplaySwitchSupport() {
        if (OsBinderCacheUtils.getCurrentUserId() != 0) {
            return false;
        }
        QSFeatureOption.INSTANCE.getClass();
        return QSFeatureOption.isSupportIris5() || QSFeatureOption.isSupportVideoOsie() || QSFeatureOption.isOsieAipqSupport();
    }

    public static final void setIris5Switch(Context context, boolean z) {
        Settings.Secure.putInt(context.getContentResolver(), KEY_OSIE_IRIS5_SWITCH, z ? 1 : 0);
        setIris5Switch.1 r0 = new setIris5Switch.1();
        r0.$switchState = z ? 1 : 0;
        r0.$context = context;
        VarHandle.storeStoreFence();
        ThreadUtils.postOnBackgroundThread(r0);
    }
}
