package com.oplus.systemui.plugins.qs.customize.viewmodel;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IntentTarget {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ IntentTarget[] $VALUES;
    public static final IntentTarget NON;
    public static final IntentTarget OTHER_LIST;
    public static final IntentTarget PERSONAL_LIST;

    static {
        IntentTarget intentTarget = new IntentTarget("PERSONAL_LIST", 0);
        PERSONAL_LIST = intentTarget;
        IntentTarget intentTarget2 = new IntentTarget("OTHER_LIST", 1);
        OTHER_LIST = intentTarget2;
        IntentTarget intentTarget3 = new IntentTarget("ADD_LIST", 2);
        IntentTarget intentTarget4 = new IntentTarget("NON", 3);
        NON = intentTarget4;
        IntentTarget[] intentTargetArr = {intentTarget, intentTarget2, intentTarget3, intentTarget4};
        $VALUES = intentTargetArr;
        $ENTRIES = EnumEntriesKt.enumEntries(intentTargetArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static IntentTarget valueOf(String str) {
        return (IntentTarget) Enum.valueOf(IntentTarget.class, str);
    }

    public static IntentTarget[] values() {
        return (IntentTarget[]) $VALUES.clone();
    }
}
