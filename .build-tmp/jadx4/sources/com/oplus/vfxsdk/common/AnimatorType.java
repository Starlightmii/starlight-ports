package com.oplus.vfxsdk.common;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ AnimatorType[] $VALUES;
    public static final AnimatorType Animator;
    public static final AnimatorType Slot;

    static {
        AnimatorType animatorType = new AnimatorType("Animator", 0);
        Animator = animatorType;
        AnimatorType animatorType2 = new AnimatorType("Slot", 1);
        Slot = animatorType2;
        AnimatorType[] animatorTypeArr = {animatorType, animatorType2};
        $VALUES = animatorTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(animatorTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static AnimatorType valueOf(String str) {
        return (AnimatorType) Enum.valueOf(AnimatorType.class, str);
    }

    public static AnimatorType[] values() {
        return (AnimatorType[]) $VALUES.clone();
    }
}
