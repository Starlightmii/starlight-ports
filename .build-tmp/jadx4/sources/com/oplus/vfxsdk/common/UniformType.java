package com.oplus.vfxsdk.common;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UniformType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ UniformType[] $VALUES;
    public static final UniformType Color;
    public static final UniformType Event;
    public static final UniformType FBO;
    public static final UniformType Float;
    public static final UniformType Int;
    public static final UniformType Range;
    public static final UniformType Texture;
    public static final UniformType UseFBO;
    public static final UniformType Vec2;
    public static final UniformType Vec3;
    public static final UniformType Vec4;

    static {
        UniformType uniformType = new UniformType("Vec2", 0);
        Vec2 = uniformType;
        UniformType uniformType2 = new UniformType("Vec3", 1);
        Vec3 = uniformType2;
        UniformType uniformType3 = new UniformType("Vec4", 2);
        Vec4 = uniformType3;
        UniformType uniformType4 = new UniformType("Color", 3);
        Color = uniformType4;
        UniformType uniformType5 = new UniformType("Int", 4);
        Int = uniformType5;
        UniformType uniformType6 = new UniformType("Range", 5);
        Range = uniformType6;
        UniformType uniformType7 = new UniformType("Float", 6);
        Float = uniformType7;
        UniformType uniformType8 = new UniformType("UseFBO", 7);
        UseFBO = uniformType8;
        UniformType uniformType9 = new UniformType("FBO", 8);
        FBO = uniformType9;
        UniformType uniformType10 = new UniformType("Texture", 9);
        Texture = uniformType10;
        UniformType uniformType11 = new UniformType("fv", 10);
        UniformType uniformType12 = new UniformType("Event", 11);
        Event = uniformType12;
        UniformType[] uniformTypeArr = {uniformType, uniformType2, uniformType3, uniformType4, uniformType5, uniformType6, uniformType7, uniformType8, uniformType9, uniformType10, uniformType11, uniformType12};
        $VALUES = uniformTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(uniformTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static UniformType valueOf(String str) {
        return (UniformType) Enum.valueOf(UniformType.class, str);
    }

    public static UniformType[] values() {
        return (UniformType[]) $VALUES.clone();
    }
}
