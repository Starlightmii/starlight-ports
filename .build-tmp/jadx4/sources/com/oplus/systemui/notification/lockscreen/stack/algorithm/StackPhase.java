package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StackPhase {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ StackPhase[] $VALUES;
    public static final StackPhase SINKING_STACK;
    public static final StackPhase TOP_STACK;

    static {
        StackPhase stackPhase = new StackPhase("TOP_STACK", 0);
        TOP_STACK = stackPhase;
        StackPhase stackPhase2 = new StackPhase("SINKING_STACK", 1);
        SINKING_STACK = stackPhase2;
        StackPhase[] stackPhaseArr = {stackPhase, stackPhase2};
        $VALUES = stackPhaseArr;
        $ENTRIES = EnumEntriesKt.enumEntries(stackPhaseArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static StackPhase valueOf(String str) {
        return (StackPhase) Enum.valueOf(StackPhase.class, str);
    }

    public static StackPhase[] values() {
        return (StackPhase[]) $VALUES.clone();
    }
}
