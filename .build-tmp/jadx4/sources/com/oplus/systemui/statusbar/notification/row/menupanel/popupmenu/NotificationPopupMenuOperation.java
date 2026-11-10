package com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationPopupMenuOperation {
    public boolean isCloseChannel;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Operation {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Operation[] $VALUES;
        public static final Operation CANCEL_NOTIFICATION_SILENT;
        public static final Operation CANCEL_NOTIFICATION_TOP;
        public static final Operation CLOSE_NOTIFICATION;
        public static final Operation NOTIFICATION_SETTING;
        public static final Operation NOTIFICATION_SILENT;
        public static final Operation NOTIFICATION_SNOOZE;
        public static final Operation NOTIFICATION_TOP;
        public static final Operation NOTIFICATION_TO_ZOOM;

        static {
            Operation operation = new Operation("NOTIFICATION_TOP", 0);
            NOTIFICATION_TOP = operation;
            Operation operation2 = new Operation("CANCEL_NOTIFICATION_TOP", 1);
            CANCEL_NOTIFICATION_TOP = operation2;
            Operation operation3 = new Operation("NOTIFICATION_SILENT", 2);
            NOTIFICATION_SILENT = operation3;
            Operation operation4 = new Operation("NOTIFICATION_TO_ZOOM", 3);
            NOTIFICATION_TO_ZOOM = operation4;
            Operation operation5 = new Operation("NOTIFICATION_SNOOZE", 4);
            NOTIFICATION_SNOOZE = operation5;
            Operation operation6 = new Operation("CANCEL_NOTIFICATION_SILENT", 5);
            CANCEL_NOTIFICATION_SILENT = operation6;
            Operation operation7 = new Operation("NOTIFICATION_SETTING", 6);
            NOTIFICATION_SETTING = operation7;
            Operation operation8 = new Operation("CLOSE_NOTIFICATION", 7);
            CLOSE_NOTIFICATION = operation8;
            Operation[] operationArr = {operation, operation2, operation3, operation4, operation5, operation6, operation7, operation8};
            $VALUES = operationArr;
            $ENTRIES = EnumEntriesKt.enumEntries(operationArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static Operation valueOf(String str) {
            return (Operation) Enum.valueOf(Operation.class, str);
        }

        public static Operation[] values() {
            return (Operation[]) $VALUES.clone();
        }
    }

    public void doOperation(Operation operation, ExpandableNotificationRow expandableNotificationRow) {
    }

    public boolean isCloseChannel() {
        return this.isCloseChannel;
    }

    public boolean isJumpActivity(Operation operation) {
        return false;
    }

    public void setCloseChannel(boolean z) {
        this.isCloseChannel = z;
    }
}
