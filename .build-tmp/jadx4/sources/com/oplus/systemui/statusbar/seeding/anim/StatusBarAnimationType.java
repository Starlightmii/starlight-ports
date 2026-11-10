package com.oplus.systemui.statusbar.seeding.anim;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarAnimationType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ StatusBarAnimationType[] $VALUES;

    /* JADX INFO: Fake field, exist only in values array */
    StatusBarAnimationType EF5;

    static {
        StatusBarAnimationType[] statusBarAnimationTypeArr = {new StatusBarAnimationType("SHRINK_IN", 0), new StatusBarAnimationType("EXPAND_OUT", 1), new StatusBarAnimationType("SHRINK_IN_EXPAND_OUT", 2), new StatusBarAnimationType("MOVE_LEFT", 3), new StatusBarAnimationType("MOVE_RIGHT", 4)};
        $VALUES = statusBarAnimationTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(statusBarAnimationTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static StatusBarAnimationType valueOf(String str) {
        return (StatusBarAnimationType) Enum.valueOf(StatusBarAnimationType.class, str);
    }

    public static StatusBarAnimationType[] values() {
        return (StatusBarAnimationType[]) $VALUES.clone();
    }
}
