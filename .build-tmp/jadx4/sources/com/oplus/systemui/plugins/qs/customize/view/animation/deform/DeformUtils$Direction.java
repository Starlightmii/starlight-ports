package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeformUtils$Direction {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ DeformUtils$Direction[] $VALUES;
    public static final DeformUtils$Direction DOWN;
    public static final DeformUtils$Direction LEFT;
    public static final DeformUtils$Direction LEFT_DOWN;
    public static final DeformUtils$Direction LEFT_UP;
    public static final DeformUtils$Direction RIGHT;
    public static final DeformUtils$Direction RIGHT_DOWN;
    public static final DeformUtils$Direction RIGHT_UP;
    public static final DeformUtils$Direction UP;

    static {
        DeformUtils$Direction deformUtils$Direction = new DeformUtils$Direction("RIGHT", 0);
        RIGHT = deformUtils$Direction;
        DeformUtils$Direction deformUtils$Direction2 = new DeformUtils$Direction("RIGHT_UP", 1);
        RIGHT_UP = deformUtils$Direction2;
        DeformUtils$Direction deformUtils$Direction3 = new DeformUtils$Direction("UP", 2);
        UP = deformUtils$Direction3;
        DeformUtils$Direction deformUtils$Direction4 = new DeformUtils$Direction("LEFT_UP", 3);
        LEFT_UP = deformUtils$Direction4;
        DeformUtils$Direction deformUtils$Direction5 = new DeformUtils$Direction("LEFT", 4);
        LEFT = deformUtils$Direction5;
        DeformUtils$Direction deformUtils$Direction6 = new DeformUtils$Direction("LEFT_DOWN", 5);
        LEFT_DOWN = deformUtils$Direction6;
        DeformUtils$Direction deformUtils$Direction7 = new DeformUtils$Direction("DOWN", 6);
        DOWN = deformUtils$Direction7;
        DeformUtils$Direction deformUtils$Direction8 = new DeformUtils$Direction("RIGHT_DOWN", 7);
        RIGHT_DOWN = deformUtils$Direction8;
        DeformUtils$Direction[] deformUtils$DirectionArr = {deformUtils$Direction, deformUtils$Direction2, deformUtils$Direction3, deformUtils$Direction4, deformUtils$Direction5, deformUtils$Direction6, deformUtils$Direction7, deformUtils$Direction8};
        $VALUES = deformUtils$DirectionArr;
        $ENTRIES = EnumEntriesKt.enumEntries(deformUtils$DirectionArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static DeformUtils$Direction valueOf(String str) {
        return (DeformUtils$Direction) Enum.valueOf(DeformUtils$Direction.class, str);
    }

    public static DeformUtils$Direction[] values() {
        return (DeformUtils$Direction[]) $VALUES.clone();
    }
}
