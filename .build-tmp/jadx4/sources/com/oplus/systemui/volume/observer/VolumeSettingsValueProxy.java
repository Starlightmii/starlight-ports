package com.oplus.systemui.volume.observer;

import android.content.Context;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class VolumeSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String SETTINGS_ANIMATION_SCALE = "animator_duration_scale";
    public static final String SETTINGS_MULTI_MEDIA_SWITCH = "multi_app_volume_switch";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getAnimationScaleSettingsState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemFloatValue(context, VolumeSettingsValueProxy.SETTINGS_ANIMATION_SCALE, 1.0f);
        }

        public final int getMultiMediaSettingsState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, VolumeSettingsValueProxy.SETTINGS_MULTI_MEDIA_SWITCH, -1);
        }

        public final void setMultiMediaSettingsState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, VolumeSettingsValueProxy.SETTINGS_MULTI_MEDIA_SWITCH, i);
        }
    }

    public static final float getAnimationScaleSettingsState(Context context) {
        return Companion.getAnimationScaleSettingsState(context);
    }

    public static final int getMultiMediaSettingsState(Context context) {
        return Companion.getMultiMediaSettingsState(context);
    }

    public static final void setMultiMediaSettingsState(Context context, int i) {
        Companion.setMultiMediaSettingsState(context, i);
    }
}
