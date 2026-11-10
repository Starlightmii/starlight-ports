package com.oplus.systemui.shade;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimationType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ AnimationType[] $VALUES;
    public static final AnimationType ALPHA;
    public static final AnimationType BOTH;
    public static final AnimationType SCALE;

    static {
        AnimationType animationType = new AnimationType("ALPHA", 0);
        ALPHA = animationType;
        AnimationType animationType2 = new AnimationType("SCALE", 1);
        SCALE = animationType2;
        AnimationType animationType3 = new AnimationType("BOTH", 2);
        BOTH = animationType3;
        AnimationType[] animationTypeArr = {animationType, animationType2, animationType3};
        $VALUES = animationTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(animationTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static AnimationType valueOf(String str) {
        return (AnimationType) Enum.valueOf(AnimationType.class, str);
    }

    public static AnimationType[] values() {
        return (AnimationType[]) $VALUES.clone();
    }
}
