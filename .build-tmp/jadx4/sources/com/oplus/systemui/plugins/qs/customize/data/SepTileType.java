package com.oplus.systemui.plugins.qs.customize.data;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ SepTileType[] $VALUES;
    public static final SepTileType CONNECTIVITY;
    public static final SepTileType DISPLAY;
    public static final SepTileType OTHERS;
    public static final SepTileType RECOMMEND;
    public static final SepTileType TOOLS;

    static {
        SepTileType sepTileType = new SepTileType("RECOMMEND", 0);
        RECOMMEND = sepTileType;
        SepTileType sepTileType2 = new SepTileType("CONNECTIVITY", 1);
        CONNECTIVITY = sepTileType2;
        SepTileType sepTileType3 = new SepTileType("DISPLAY", 2);
        DISPLAY = sepTileType3;
        SepTileType sepTileType4 = new SepTileType("TOOLS", 3);
        TOOLS = sepTileType4;
        SepTileType sepTileType5 = new SepTileType("OTHERS", 4);
        OTHERS = sepTileType5;
        SepTileType[] sepTileTypeArr = {sepTileType, sepTileType2, sepTileType3, sepTileType4, sepTileType5};
        $VALUES = sepTileTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(sepTileTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static SepTileType valueOf(String str) {
        return (SepTileType) Enum.valueOf(SepTileType.class, str);
    }

    public static SepTileType[] values() {
        return (SepTileType[]) $VALUES.clone();
    }
}
