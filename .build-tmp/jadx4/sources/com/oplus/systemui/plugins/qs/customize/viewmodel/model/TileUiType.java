package com.oplus.systemui.plugins.qs.customize.viewmodel.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileUiType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ TileUiType[] $VALUES;
    public static final TileUiType DEFAULT;
    public static final TileUiType THREE_STAGE;

    static {
        TileUiType tileUiType = new TileUiType("DEFAULT", 0);
        DEFAULT = tileUiType;
        TileUiType tileUiType2 = new TileUiType("THREE_STAGE", 1);
        THREE_STAGE = tileUiType2;
        TileUiType[] tileUiTypeArr = {tileUiType, tileUiType2};
        $VALUES = tileUiTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(tileUiTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static TileUiType valueOf(String str) {
        return (TileUiType) Enum.valueOf(TileUiType.class, str);
    }

    public static TileUiType[] values() {
        return (TileUiType[]) $VALUES.clone();
    }
}
