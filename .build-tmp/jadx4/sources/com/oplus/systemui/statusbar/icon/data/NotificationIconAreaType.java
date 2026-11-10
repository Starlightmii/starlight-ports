package com.oplus.systemui.statusbar.icon.data;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationIconAreaType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ NotificationIconAreaType[] $VALUES;
    public static final NotificationIconAreaType NOTIFICATION_NOT_SHOW;
    public static final NotificationIconAreaType NOTIFICATION_SHOW_ICON;
    public static final NotificationIconAreaType NOTIFICATION_SHOW_NUMBER;

    static {
        NotificationIconAreaType notificationIconAreaType = new NotificationIconAreaType("NOTIFICATION_SHOW_ICON", 0);
        NOTIFICATION_SHOW_ICON = notificationIconAreaType;
        NotificationIconAreaType notificationIconAreaType2 = new NotificationIconAreaType("NOTIFICATION_SHOW_NUMBER", 1);
        NOTIFICATION_SHOW_NUMBER = notificationIconAreaType2;
        NotificationIconAreaType notificationIconAreaType3 = new NotificationIconAreaType("NOTIFICATION_NOT_SHOW", 2);
        NOTIFICATION_NOT_SHOW = notificationIconAreaType3;
        NotificationIconAreaType[] notificationIconAreaTypeArr = {notificationIconAreaType, notificationIconAreaType2, notificationIconAreaType3};
        $VALUES = notificationIconAreaTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(notificationIconAreaTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static NotificationIconAreaType valueOf(String str) {
        return (NotificationIconAreaType) Enum.valueOf(NotificationIconAreaType.class, str);
    }

    public static NotificationIconAreaType[] values() {
        return (NotificationIconAreaType[]) $VALUES.clone();
    }
}
