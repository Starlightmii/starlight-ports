package com.oplusos.systemui.common.olivelink;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpringType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ SpringType[] $VALUES;
    public static final SpringType SPRING_ANIMATION;
    public static final SpringType SPRING_INTERPOLATOR;

    static {
        SpringType springType = new SpringType("SPRING_ANIMATION", 0);
        SPRING_ANIMATION = springType;
        SpringType springType2 = new SpringType("SPRING_INTERPOLATOR", 1);
        SPRING_INTERPOLATOR = springType2;
        SpringType[] springTypeArr = {springType, springType2};
        $VALUES = springTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(springTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static SpringType valueOf(String str) {
        return (SpringType) Enum.valueOf(SpringType.class, str);
    }

    public static SpringType[] values() {
        return (SpringType[]) $VALUES.clone();
    }
}
