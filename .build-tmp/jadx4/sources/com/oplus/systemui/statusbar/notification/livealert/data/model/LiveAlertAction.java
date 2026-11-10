package com.oplus.systemui.statusbar.notification.livealert.data.model;

import android.service.notification.StatusBarNotification;
import com.oplus.systemui.seedlingservice.mediaControl.MediaAction;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LiveAlertAction {
    public static final Companion Companion = new Companion();
    private static final String METHOD_LIVE_ALERT = "live_alert_notification";
    private static final String METHOD_LIVE_ALERT_POST = "live_alert_notification_post";
    private static final String METHOD_LIVE_ALERT_REMOVE = "live_alert_notification_remove";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Default extends LiveAlertAction {
        private static final String method = null;
        public static final Default INSTANCE = new Default();
        private static final ArrayList<StatusBarNotification> sbnList = new ArrayList<>();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Default);
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public String getMethod() {
            return method;
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public ArrayList<StatusBarNotification> getSbnList() {
            return sbnList;
        }

        public final int hashCode() {
            return -1285184853;
        }

        public String toString() {
            return MediaAction.ICON_DEFAULT;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InitAction extends LiveAlertAction {
        private final String method = LiveAlertAction.METHOD_LIVE_ALERT;
        private final ArrayList<StatusBarNotification> sbnList;

        public InitAction(ArrayList<StatusBarNotification> arrayList) {
            this.sbnList = arrayList;
        }

        public final ArrayList<StatusBarNotification> component1() {
            return this.sbnList;
        }

        public final InitAction copy(ArrayList<StatusBarNotification> arrayList) {
            return new InitAction(arrayList);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof InitAction) && Intrinsics.areEqual(this.sbnList, ((InitAction) obj).sbnList);
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public String getMethod() {
            return this.method;
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public ArrayList<StatusBarNotification> getSbnList() {
            return this.sbnList;
        }

        public final int hashCode() {
            return this.sbnList.hashCode();
        }

        public String toString() {
            return "InitAction(sbnList=" + this.sbnList + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PostAction extends LiveAlertAction {
        private final String method = LiveAlertAction.METHOD_LIVE_ALERT_POST;
        private final ArrayList<StatusBarNotification> sbnList;

        public PostAction(ArrayList<StatusBarNotification> arrayList) {
            this.sbnList = arrayList;
        }

        public final ArrayList<StatusBarNotification> component1() {
            return this.sbnList;
        }

        public final PostAction copy(ArrayList<StatusBarNotification> arrayList) {
            return new PostAction(arrayList);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof PostAction) && Intrinsics.areEqual(this.sbnList, ((PostAction) obj).sbnList);
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public String getMethod() {
            return this.method;
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public ArrayList<StatusBarNotification> getSbnList() {
            return this.sbnList;
        }

        public final int hashCode() {
            return this.sbnList.hashCode();
        }

        public String toString() {
            return "PostAction(sbnList=" + this.sbnList + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RemoveAction extends LiveAlertAction {
        private final String method = LiveAlertAction.METHOD_LIVE_ALERT_REMOVE;
        private final ArrayList<StatusBarNotification> sbnList;

        public RemoveAction(ArrayList<StatusBarNotification> arrayList) {
            this.sbnList = arrayList;
        }

        public final ArrayList<StatusBarNotification> component1() {
            return this.sbnList;
        }

        public final RemoveAction copy(ArrayList<StatusBarNotification> arrayList) {
            return new RemoveAction(arrayList);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RemoveAction) && Intrinsics.areEqual(this.sbnList, ((RemoveAction) obj).sbnList);
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public String getMethod() {
            return this.method;
        }

        @Override // com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction
        public ArrayList<StatusBarNotification> getSbnList() {
            return this.sbnList;
        }

        public final int hashCode() {
            return this.sbnList.hashCode();
        }

        public String toString() {
            return "RemoveAction(sbnList=" + this.sbnList + ")";
        }
    }

    public abstract String getMethod();

    public abstract ArrayList<StatusBarNotification> getSbnList();
}
