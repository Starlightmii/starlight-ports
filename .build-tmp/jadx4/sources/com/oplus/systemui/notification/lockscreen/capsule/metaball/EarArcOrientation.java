package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EarArcOrientation {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ EarArcOrientation[] $VALUES;
    public static final EarArcOrientation EAR_ARC_ORIENTATION_LEFT;
    public static final EarArcOrientation EAR_ARC_ORIENTATION_RIGHT;

    static {
        EarArcOrientation earArcOrientation = new EarArcOrientation("EAR_ARC_ORIENTATION_LEFT", 0);
        EAR_ARC_ORIENTATION_LEFT = earArcOrientation;
        EarArcOrientation earArcOrientation2 = new EarArcOrientation("EAR_ARC_ORIENTATION_RIGHT", 1);
        EAR_ARC_ORIENTATION_RIGHT = earArcOrientation2;
        EarArcOrientation[] earArcOrientationArr = {earArcOrientation, earArcOrientation2};
        $VALUES = earArcOrientationArr;
        $ENTRIES = EnumEntriesKt.enumEntries(earArcOrientationArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static EarArcOrientation valueOf(String str) {
        return (EarArcOrientation) Enum.valueOf(EarArcOrientation.class, str);
    }

    public static EarArcOrientation[] values() {
        return (EarArcOrientation[]) $VALUES.clone();
    }
}
