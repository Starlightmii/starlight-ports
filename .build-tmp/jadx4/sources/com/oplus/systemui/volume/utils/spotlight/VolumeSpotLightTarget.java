package com.oplus.systemui.volume.utils.spotlight;

import com.coui.appcompat.spotlight.COUISpotLightEffect;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSpotLightTarget {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ VolumeSpotLightTarget[] $VALUES;
    public static final Companion Companion;
    public static final VolumeSpotLightTarget SEEK_BAR_PANEL;
    public static final VolumeSpotLightTarget SEEK_BAR_SIDE;
    public static final VolumeSpotLightTarget SMALL_BUTTON;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final VolumeSpotLightTarget forSeekBar(boolean z) {
            return z ? VolumeSpotLightTarget.SEEK_BAR_PANEL : VolumeSpotLightTarget.SEEK_BAR_SIDE;
        }
    }

    static {
        VolumeSpotLightTarget volumeSpotLightTarget = new VolumeSpotLightTarget("SEEK_BAR_SIDE", 0);
        SEEK_BAR_SIDE = volumeSpotLightTarget;
        VolumeSpotLightTarget volumeSpotLightTarget2 = new VolumeSpotLightTarget("SEEK_BAR_PANEL", 1);
        SEEK_BAR_PANEL = volumeSpotLightTarget2;
        VolumeSpotLightTarget volumeSpotLightTarget3 = new VolumeSpotLightTarget("SMALL_BUTTON", 2);
        SMALL_BUTTON = volumeSpotLightTarget3;
        VolumeSpotLightTarget[] volumeSpotLightTargetArr = {volumeSpotLightTarget, volumeSpotLightTarget2, volumeSpotLightTarget3};
        $VALUES = volumeSpotLightTargetArr;
        $ENTRIES = EnumEntriesKt.enumEntries(volumeSpotLightTargetArr);
        Companion = new Companion();
    }

    public static final VolumeSpotLightTarget forSeekBar(boolean z) {
        return Companion.forSeekBar(z);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static VolumeSpotLightTarget valueOf(String str) {
        return (VolumeSpotLightTarget) Enum.valueOf(VolumeSpotLightTarget.class, str);
    }

    public static VolumeSpotLightTarget[] values() {
        return (VolumeSpotLightTarget[]) $VALUES.clone();
    }

    public final COUISpotLightEffect.SpotLightType getSpotLightType$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return COUISpotLightEffect.SpotLightType.TYPE_TRANSLUCENT_SMALL_2;
        }
        if (iOrdinal == 1) {
            return COUISpotLightEffect.SpotLightType.TYPE_TRANSLUCENT_SMALL_1;
        }
        if (iOrdinal == 2) {
            return COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_SMALL_1;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }
}
