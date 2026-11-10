package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleOldAnimType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ OplusHandleOldAnimType[] $VALUES;
    public static final OplusHandleOldAnimType EndOldLongPressAnim;
    public static final OplusHandleOldAnimType NoAnim;
    public static final OplusHandleOldAnimType ResetTransitionAnim;
    public static final OplusHandleOldAnimType StartLeftTransitionAnim;
    public static final OplusHandleOldAnimType StartOldLongPressAnim;
    public static final OplusHandleOldAnimType StartRightTransitionAnim;
    public static final OplusHandleOldAnimType StartUpTransitionAnim;

    static {
        OplusHandleOldAnimType oplusHandleOldAnimType = new OplusHandleOldAnimType("StartRightTransitionAnim", 0);
        StartRightTransitionAnim = oplusHandleOldAnimType;
        OplusHandleOldAnimType oplusHandleOldAnimType2 = new OplusHandleOldAnimType("StartLeftTransitionAnim", 1);
        StartLeftTransitionAnim = oplusHandleOldAnimType2;
        OplusHandleOldAnimType oplusHandleOldAnimType3 = new OplusHandleOldAnimType("StartUpTransitionAnim", 2);
        StartUpTransitionAnim = oplusHandleOldAnimType3;
        OplusHandleOldAnimType oplusHandleOldAnimType4 = new OplusHandleOldAnimType("ResetTransitionAnim", 3);
        ResetTransitionAnim = oplusHandleOldAnimType4;
        OplusHandleOldAnimType oplusHandleOldAnimType5 = new OplusHandleOldAnimType("StartOldLongPressAnim", 4);
        StartOldLongPressAnim = oplusHandleOldAnimType5;
        OplusHandleOldAnimType oplusHandleOldAnimType6 = new OplusHandleOldAnimType("EndOldLongPressAnim", 5);
        EndOldLongPressAnim = oplusHandleOldAnimType6;
        OplusHandleOldAnimType oplusHandleOldAnimType7 = new OplusHandleOldAnimType("NoAnim", 6);
        NoAnim = oplusHandleOldAnimType7;
        OplusHandleOldAnimType[] oplusHandleOldAnimTypeArr = {oplusHandleOldAnimType, oplusHandleOldAnimType2, oplusHandleOldAnimType3, oplusHandleOldAnimType4, oplusHandleOldAnimType5, oplusHandleOldAnimType6, oplusHandleOldAnimType7};
        $VALUES = oplusHandleOldAnimTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(oplusHandleOldAnimTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static OplusHandleOldAnimType valueOf(String str) {
        return (OplusHandleOldAnimType) Enum.valueOf(OplusHandleOldAnimType.class, str);
    }

    public static OplusHandleOldAnimType[] values() {
        return (OplusHandleOldAnimType[]) $VALUES.clone();
    }
}
