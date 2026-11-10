package com.oplus.systemui.notification.lockscreen.notification;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleInternalAnimHelper$AnimationAction {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ CapsuleInternalAnimHelper$AnimationAction[] $VALUES;
    public static final CapsuleInternalAnimHelper$AnimationAction BOTTOM_RIGHT_BADGE;
    public static final CapsuleInternalAnimHelper$AnimationAction CONTACT_ICON;
    public static final CapsuleInternalAnimHelper$AnimationAction NEW_CONTENT;
    public static final CapsuleInternalAnimHelper$AnimationAction NEW_TEXT;
    public static final CapsuleInternalAnimHelper$AnimationAction NEW_TITLE;
    public static final CapsuleInternalAnimHelper$AnimationAction OLD_CONTENT;
    public static final CapsuleInternalAnimHelper$AnimationAction OLD_TEXT;
    public static final CapsuleInternalAnimHelper$AnimationAction OLD_TITLE;
    public static final CapsuleInternalAnimHelper$AnimationAction TOP_RIGHT_BADGE;

    static {
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction = new CapsuleInternalAnimHelper$AnimationAction("OLD_CONTENT", 0);
        OLD_CONTENT = capsuleInternalAnimHelper$AnimationAction;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction2 = new CapsuleInternalAnimHelper$AnimationAction("NEW_CONTENT", 1);
        NEW_CONTENT = capsuleInternalAnimHelper$AnimationAction2;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction3 = new CapsuleInternalAnimHelper$AnimationAction("OLD_TITLE", 2);
        OLD_TITLE = capsuleInternalAnimHelper$AnimationAction3;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction4 = new CapsuleInternalAnimHelper$AnimationAction("OLD_TEXT", 3);
        OLD_TEXT = capsuleInternalAnimHelper$AnimationAction4;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction5 = new CapsuleInternalAnimHelper$AnimationAction("NEW_TITLE", 4);
        NEW_TITLE = capsuleInternalAnimHelper$AnimationAction5;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction6 = new CapsuleInternalAnimHelper$AnimationAction("NEW_TEXT", 5);
        NEW_TEXT = capsuleInternalAnimHelper$AnimationAction6;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction7 = new CapsuleInternalAnimHelper$AnimationAction("CONTACT_ICON", 6);
        CONTACT_ICON = capsuleInternalAnimHelper$AnimationAction7;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction8 = new CapsuleInternalAnimHelper$AnimationAction("BOTTOM_RIGHT_BADGE", 7);
        BOTTOM_RIGHT_BADGE = capsuleInternalAnimHelper$AnimationAction8;
        CapsuleInternalAnimHelper$AnimationAction capsuleInternalAnimHelper$AnimationAction9 = new CapsuleInternalAnimHelper$AnimationAction("TOP_RIGHT_BADGE", 8);
        TOP_RIGHT_BADGE = capsuleInternalAnimHelper$AnimationAction9;
        CapsuleInternalAnimHelper$AnimationAction[] capsuleInternalAnimHelper$AnimationActionArr = {capsuleInternalAnimHelper$AnimationAction, capsuleInternalAnimHelper$AnimationAction2, capsuleInternalAnimHelper$AnimationAction3, capsuleInternalAnimHelper$AnimationAction4, capsuleInternalAnimHelper$AnimationAction5, capsuleInternalAnimHelper$AnimationAction6, capsuleInternalAnimHelper$AnimationAction7, capsuleInternalAnimHelper$AnimationAction8, capsuleInternalAnimHelper$AnimationAction9};
        $VALUES = capsuleInternalAnimHelper$AnimationActionArr;
        $ENTRIES = EnumEntriesKt.enumEntries(capsuleInternalAnimHelper$AnimationActionArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static CapsuleInternalAnimHelper$AnimationAction valueOf(String str) {
        return (CapsuleInternalAnimHelper$AnimationAction) Enum.valueOf(CapsuleInternalAnimHelper$AnimationAction.class, str);
    }

    public static CapsuleInternalAnimHelper$AnimationAction[] values() {
        return (CapsuleInternalAnimHelper$AnimationAction[]) $VALUES.clone();
    }
}
