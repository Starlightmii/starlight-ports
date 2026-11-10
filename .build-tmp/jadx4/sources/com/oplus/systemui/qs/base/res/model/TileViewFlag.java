package com.oplus.systemui.qs.base.res.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileViewFlag {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ TileViewFlag[] $VALUES;
    public static final TileViewFlag Active;
    public static final TileViewFlag Deform;
    public static final TileViewFlag Dragged;
    public static final TileViewFlag Inactive;
    public static final TileViewFlag Inoperable;
    public static final TileViewFlag Toggleable;
    public static final TileViewFlag Unavailable;
    private final int attr;
    private final int[] flag;
    private final int[] reverse;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Active extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Active";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Deform extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Deform";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Dragged extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Dragged";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Inactive extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Inactive";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Inoperable extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Inoperable";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Toggleable extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Toggleable";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class Unavailable extends TileViewFlag {
        @Override // java.lang.Enum
        public String toString() {
            return "Unavailable";
        }
    }

    static {
        Active active = new Active("Active", 0, 2130971402);
        Active = active;
        Deform deform = new Deform("Deform", 1, 2130971405);
        Deform = deform;
        Inactive inactive = new Inactive("Inactive", 2, 2130971411);
        Inactive = inactive;
        Inoperable inoperable = new Inoperable("Inoperable", 3, 2130971413);
        Inoperable = inoperable;
        Unavailable unavailable = new Unavailable("Unavailable", 4, 2130971420);
        Unavailable = unavailable;
        Dragged dragged = new Dragged("Dragged", 5, 2130971407);
        Dragged = dragged;
        Toggleable toggleable = new Toggleable("Toggleable", 6, 2130971419);
        Toggleable = toggleable;
        TileViewFlag[] tileViewFlagArr = {active, deform, inactive, inoperable, unavailable, dragged, toggleable};
        $VALUES = tileViewFlagArr;
        $ENTRIES = EnumEntriesKt.enumEntries(tileViewFlagArr);
    }

    public TileViewFlag(String str, int i, int i2) {
        super(str, i);
        this.attr = i2;
        this.flag = new int[]{i2};
        this.reverse = new int[]{-i2};
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static TileViewFlag valueOf(String str) {
        return (TileViewFlag) Enum.valueOf(TileViewFlag.class, str);
    }

    public static TileViewFlag[] values() {
        return (TileViewFlag[]) $VALUES.clone();
    }

    public final int getAttr() {
        return this.attr;
    }

    public final int[] getFlag() {
        return this.flag;
    }

    public final int[] getReverse() {
        return this.reverse;
    }
}
