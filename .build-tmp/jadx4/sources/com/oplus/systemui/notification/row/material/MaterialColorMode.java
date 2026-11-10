package com.oplus.systemui.notification.row.material;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaterialColorMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ MaterialColorMode[] $VALUES;
    public static final MaterialColorMode COLOR_NOTIFICATION;
    public static final MaterialColorMode MATERIAL;
    public static final MaterialColorMode NORMAL_DARK;
    public static final MaterialColorMode NORMAL_LIGHT;
    public static final MaterialColorMode UNINITIATED;

    static {
        MaterialColorMode materialColorMode = new MaterialColorMode("UNINITIATED", 0);
        UNINITIATED = materialColorMode;
        MaterialColorMode materialColorMode2 = new MaterialColorMode("NORMAL_LIGHT", 1);
        NORMAL_LIGHT = materialColorMode2;
        MaterialColorMode materialColorMode3 = new MaterialColorMode("NORMAL_DARK", 2);
        NORMAL_DARK = materialColorMode3;
        MaterialColorMode materialColorMode4 = new MaterialColorMode("MATERIAL", 3);
        MATERIAL = materialColorMode4;
        MaterialColorMode materialColorMode5 = new MaterialColorMode("COLOR_NOTIFICATION", 4);
        COLOR_NOTIFICATION = materialColorMode5;
        MaterialColorMode[] materialColorModeArr = {materialColorMode, materialColorMode2, materialColorMode3, materialColorMode4, materialColorMode5};
        $VALUES = materialColorModeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(materialColorModeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static MaterialColorMode valueOf(String str) {
        return (MaterialColorMode) Enum.valueOf(MaterialColorMode.class, str);
    }

    public static MaterialColorMode[] values() {
        return (MaterialColorMode[]) $VALUES.clone();
    }
}
