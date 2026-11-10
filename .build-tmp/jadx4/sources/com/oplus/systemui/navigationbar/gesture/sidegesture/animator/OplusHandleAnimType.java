package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleAnimType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ OplusHandleAnimType[] $VALUES;
    public static final OplusHandleAnimType EndFadedAnim;
    public static final OplusHandleAnimType EndFollowHandAnim;
    public static final OplusHandleAnimType EndLongPressAnim;
    public static final OplusHandleAnimType StartFadedAnim;
    public static final OplusHandleAnimType StartFollowXHandAnim;
    public static final OplusHandleAnimType StartFollowYHandAnim;
    public static final OplusHandleAnimType StartLongPressAnim;

    static {
        OplusHandleAnimType oplusHandleAnimType = new OplusHandleAnimType("StartLongPressAnim", 0);
        StartLongPressAnim = oplusHandleAnimType;
        OplusHandleAnimType oplusHandleAnimType2 = new OplusHandleAnimType("EndLongPressAnim", 1);
        EndLongPressAnim = oplusHandleAnimType2;
        OplusHandleAnimType oplusHandleAnimType3 = new OplusHandleAnimType("StartFollowXHandAnim", 2);
        StartFollowXHandAnim = oplusHandleAnimType3;
        OplusHandleAnimType oplusHandleAnimType4 = new OplusHandleAnimType("EndFollowHandAnim", 3);
        EndFollowHandAnim = oplusHandleAnimType4;
        OplusHandleAnimType oplusHandleAnimType5 = new OplusHandleAnimType("StartFollowYHandAnim", 4);
        StartFollowYHandAnim = oplusHandleAnimType5;
        OplusHandleAnimType oplusHandleAnimType6 = new OplusHandleAnimType("StartFadedAnim", 5);
        StartFadedAnim = oplusHandleAnimType6;
        OplusHandleAnimType oplusHandleAnimType7 = new OplusHandleAnimType("EndFadedAnim", 6);
        EndFadedAnim = oplusHandleAnimType7;
        OplusHandleAnimType[] oplusHandleAnimTypeArr = {oplusHandleAnimType, oplusHandleAnimType2, oplusHandleAnimType3, oplusHandleAnimType4, oplusHandleAnimType5, oplusHandleAnimType6, oplusHandleAnimType7};
        $VALUES = oplusHandleAnimTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(oplusHandleAnimTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static OplusHandleAnimType valueOf(String str) {
        return (OplusHandleAnimType) Enum.valueOf(OplusHandleAnimType.class, str);
    }

    public static OplusHandleAnimType[] values() {
        return (OplusHandleAnimType[]) $VALUES.clone();
    }
}
