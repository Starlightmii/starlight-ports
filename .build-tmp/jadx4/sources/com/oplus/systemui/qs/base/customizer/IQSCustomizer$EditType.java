package com.oplus.systemui.qs.base.customizer;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IQSCustomizer$EditType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ IQSCustomizer$EditType[] $VALUES;
    public static final IQSCustomizer$EditType BOTTOM;
    public static final IQSCustomizer$EditType HIGHLIGHT;
    public static final IQSCustomizer$EditType TOP;

    static {
        IQSCustomizer$EditType iQSCustomizer$EditType = new IQSCustomizer$EditType("HIGHLIGHT", 0);
        HIGHLIGHT = iQSCustomizer$EditType;
        IQSCustomizer$EditType iQSCustomizer$EditType2 = new IQSCustomizer$EditType("TOP", 1);
        TOP = iQSCustomizer$EditType2;
        IQSCustomizer$EditType iQSCustomizer$EditType3 = new IQSCustomizer$EditType("BOTTOM", 2);
        BOTTOM = iQSCustomizer$EditType3;
        IQSCustomizer$EditType[] iQSCustomizer$EditTypeArr = {iQSCustomizer$EditType, iQSCustomizer$EditType2, iQSCustomizer$EditType3};
        $VALUES = iQSCustomizer$EditTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(iQSCustomizer$EditTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static IQSCustomizer$EditType valueOf(String str) {
        return (IQSCustomizer$EditType) Enum.valueOf(IQSCustomizer$EditType.class, str);
    }

    public static IQSCustomizer$EditType[] values() {
        return (IQSCustomizer$EditType[]) $VALUES.clone();
    }
}
