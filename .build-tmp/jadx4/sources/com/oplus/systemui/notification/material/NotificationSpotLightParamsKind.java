package com.oplus.systemui.notification.material;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightParamsKind {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ NotificationSpotLightParamsKind[] $VALUES;
    public static final NotificationSpotLightParamsKind CAPSULE_CARD;
    public static final NotificationSpotLightParamsKind CLEAR_ALL_BUTTON;
    public static final NotificationSpotLightParamsKind CLOSE_ALL_BUTTON;
    public static final NotificationSpotLightParamsKind EXPAND_GROUP_HEADER_BUTTON;
    public static final NotificationSpotLightParamsKind FULLSCREEN_BANNER;
    public static final NotificationSpotLightParamsKind HEADS_LAYOUT_CARD;
    public static final NotificationSpotLightParamsKind NOTIFICATION_CARD;
    public static final NotificationSpotLightParamsKind NOTIFICATION_MENU;
    public static final NotificationSpotLightParamsKind NOTIFICATION_SEEDLING_CARD;
    public static final NotificationSpotLightParamsKind NOTIFICATION_SEEDLING_IMMERSIVE_CARD;
    public static final NotificationSpotLightParamsKind QS_SETTINGS_BUTTON;

    static {
        NotificationSpotLightParamsKind notificationSpotLightParamsKind = new NotificationSpotLightParamsKind("NOTIFICATION_CARD", 0);
        NOTIFICATION_CARD = notificationSpotLightParamsKind;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind2 = new NotificationSpotLightParamsKind("NOTIFICATION_SEEDLING_CARD", 1);
        NOTIFICATION_SEEDLING_CARD = notificationSpotLightParamsKind2;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind3 = new NotificationSpotLightParamsKind("NOTIFICATION_SEEDLING_IMMERSIVE_CARD", 2);
        NOTIFICATION_SEEDLING_IMMERSIVE_CARD = notificationSpotLightParamsKind3;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind4 = new NotificationSpotLightParamsKind("QS_SETTINGS_BUTTON", 3);
        QS_SETTINGS_BUTTON = notificationSpotLightParamsKind4;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind5 = new NotificationSpotLightParamsKind("CLEAR_ALL_BUTTON", 4);
        CLEAR_ALL_BUTTON = notificationSpotLightParamsKind5;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind6 = new NotificationSpotLightParamsKind("CLOSE_ALL_BUTTON", 5);
        CLOSE_ALL_BUTTON = notificationSpotLightParamsKind6;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind7 = new NotificationSpotLightParamsKind("EXPAND_GROUP_HEADER_BUTTON", 6);
        EXPAND_GROUP_HEADER_BUTTON = notificationSpotLightParamsKind7;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind8 = new NotificationSpotLightParamsKind("FULLSCREEN_BANNER", 7);
        FULLSCREEN_BANNER = notificationSpotLightParamsKind8;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind9 = new NotificationSpotLightParamsKind("CAPSULE_CARD", 8);
        CAPSULE_CARD = notificationSpotLightParamsKind9;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind10 = new NotificationSpotLightParamsKind("HEADS_LAYOUT_CARD", 9);
        HEADS_LAYOUT_CARD = notificationSpotLightParamsKind10;
        NotificationSpotLightParamsKind notificationSpotLightParamsKind11 = new NotificationSpotLightParamsKind("NOTIFICATION_MENU", 10);
        NOTIFICATION_MENU = notificationSpotLightParamsKind11;
        NotificationSpotLightParamsKind[] notificationSpotLightParamsKindArr = {notificationSpotLightParamsKind, notificationSpotLightParamsKind2, notificationSpotLightParamsKind3, notificationSpotLightParamsKind4, notificationSpotLightParamsKind5, notificationSpotLightParamsKind6, notificationSpotLightParamsKind7, notificationSpotLightParamsKind8, notificationSpotLightParamsKind9, notificationSpotLightParamsKind10, notificationSpotLightParamsKind11};
        $VALUES = notificationSpotLightParamsKindArr;
        $ENTRIES = EnumEntriesKt.enumEntries(notificationSpotLightParamsKindArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static NotificationSpotLightParamsKind valueOf(String str) {
        return (NotificationSpotLightParamsKind) Enum.valueOf(NotificationSpotLightParamsKind.class, str);
    }

    public static NotificationSpotLightParamsKind[] values() {
        return (NotificationSpotLightParamsKind[]) $VALUES.clone();
    }
}
