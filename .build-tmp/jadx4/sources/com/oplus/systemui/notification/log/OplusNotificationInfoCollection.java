package com.oplus.systemui.notification.log;

import android.hidl.base.V1_0.DebugInfo$;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.doze.DozeLogger$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationInfoCollection implements NotifCollectionListener, OnBeforeRenderListListener, CoreStartable {
    public static final Companion Companion = new Companion();
    private static final List<String> EVENT_LOG_BLACK_LIST = CollectionsKt__CollectionsJVMKt.listOf("com.oplus.olc");
    private static final String TAG = "NotificationInfoCollection";
    private final Map<NotificationEntry, OplusNotificationInfo> datas = new LinkedHashMap();
    private final OplusNotifiEventLog eventLog;
    private final InitController initController;
    private final NotifCollection notifCollection;
    private final Lazy notificationDependOnCommon;
    private final NotifPipeline pipe;
    private final StatusBarStateController statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusNotificationInfo {
        private final NotificationEntry entry;
        private final Map<String, String> interruptInfo;
        private final WakeupInfo wakeUpInfo;

        public OplusNotificationInfo(NotificationEntry notificationEntry, Map<String, String> map, WakeupInfo wakeupInfo) {
            this.entry = notificationEntry;
            this.interruptInfo = map;
            this.wakeUpInfo = wakeupInfo;
        }

        public final NotificationEntry component1() {
            return this.entry;
        }

        public final Map<String, String> component2() {
            return this.interruptInfo;
        }

        public final WakeupInfo component3() {
            return this.wakeUpInfo;
        }

        public final OplusNotificationInfo copy(NotificationEntry notificationEntry, Map<String, String> map, WakeupInfo wakeupInfo) {
            return new OplusNotificationInfo(notificationEntry, map, wakeupInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OplusNotificationInfo)) {
                return false;
            }
            OplusNotificationInfo oplusNotificationInfo = (OplusNotificationInfo) obj;
            return Intrinsics.areEqual(this.entry, oplusNotificationInfo.entry) && Intrinsics.areEqual(this.interruptInfo, oplusNotificationInfo.interruptInfo) && Intrinsics.areEqual(this.wakeUpInfo, oplusNotificationInfo.wakeUpInfo);
        }

        public final NotificationEntry getEntry() {
            return this.entry;
        }

        public final Map<String, String> getInterruptInfo() {
            return this.interruptInfo;
        }

        public final WakeupInfo getWakeUpInfo() {
            return this.wakeUpInfo;
        }

        public final int hashCode() {
            return this.wakeUpInfo.hashCode() + ((this.interruptInfo.hashCode() + (this.entry.hashCode() * 31)) * 31);
        }

        public String toString() {
            return "OplusNotificationInfo(entry=" + this.entry + ", interruptInfo=" + this.interruptInfo + ", wakeUpInfo=" + this.wakeUpInfo + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WakeupInfo {
        public boolean hasLogged;
        private Boolean isWakeup;
        private String reason;

        public WakeupInfo(boolean z, Boolean bool, String str) {
            this.hasLogged = z;
            this.isWakeup = bool;
            this.reason = str;
        }

        public final Boolean component2() {
            return this.isWakeup;
        }

        public final String component3() {
            return this.reason;
        }

        public final WakeupInfo copy(boolean z, Boolean bool, String str) {
            return new WakeupInfo(z, bool, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WakeupInfo)) {
                return false;
            }
            WakeupInfo wakeupInfo = (WakeupInfo) obj;
            return this.hasLogged == wakeupInfo.hasLogged && Intrinsics.areEqual(this.isWakeup, wakeupInfo.isWakeup) && Intrinsics.areEqual(this.reason, wakeupInfo.reason);
        }

        public final boolean getHasLogged() {
            return this.hasLogged;
        }

        public final String getReason() {
            return this.reason;
        }

        public final int hashCode() {
            int iHashCode = Boolean.hashCode(this.hasLogged) * 31;
            Boolean bool = this.isWakeup;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            String str = this.reason;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        public final Boolean isWakeup() {
            return this.isWakeup;
        }

        public final void setHasLogged(boolean z) {
            this.hasLogged = z;
        }

        public final void setReason(String str) {
            this.reason = str;
        }

        public final void setWakeup(Boolean bool) {
            this.isWakeup = bool;
        }

        public String toString() {
            boolean z = this.hasLogged;
            Boolean bool = this.isWakeup;
            String str = this.reason;
            StringBuilder sb = new StringBuilder("WakeupInfo(hasLogged=");
            sb.append(z);
            sb.append(", isWakeup=");
            sb.append(bool);
            sb.append(", reason=");
            return DebugInfo$.ExternalSyntheticOutline0.m(sb, str, ")");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.log.OplusNotificationInfoCollection$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusNotificationInfoCollection this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.pipe.addCollectionListener(this.this$0);
            this.this$0.pipe.addOnBeforeRenderListListener(this.this$0);
        }
    }

    public OplusNotificationInfoCollection(NotifPipeline notifPipeline, InitController initController, OplusNotifiEventLog oplusNotifiEventLog, StatusBarStateController statusBarStateController, Lazy lazy, NotifCollection notifCollection) {
        this.pipe = notifPipeline;
        this.initController = initController;
        this.eventLog = oplusNotifiEventLog;
        this.statusBarStateController = statusBarStateController;
        this.notificationDependOnCommon = lazy;
        this.notifCollection = notifCollection;
    }

    public static void logNotificationPosted(NotificationEntry notificationEntry, boolean z) {
        StatusBarNotification sbn = notificationEntry.getSbn();
        if (LogUtil.getNormalDebug() && sbn.getNotification() != null && sbn.getNotification().contentView == null) {
            Bundle bundle = sbn.getNotification().extras;
            Object obj = bundle != null ? bundle.get("android.title") : null;
            CharSequence charSequence = obj instanceof CharSequence ? (CharSequence) obj : null;
            Bundle bundle2 = sbn.getNotification().extras;
            Object obj2 = bundle2 != null ? bundle2.get("android.text") : null;
            CharSequence charSequence2 = obj2 instanceof CharSequence ? (CharSequence) obj2 : null;
            boolean zIsEmpty = TextUtils.isEmpty(charSequence);
            boolean zIsEmpty2 = TextUtils.isEmpty(charSequence2);
            if (zIsEmpty || zIsEmpty2) {
                String encryptionKey = notificationEntry.getEx().getEncryptionKey();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onNotificationPosted isTitleEmpty: ", " isTextEmpty: ", " isUpdate: ", zIsEmpty, zIsEmpty2);
                sbM.append(z);
                sbM.append(" key: ");
                sbM.append(encryptionKey);
                String string = sbM.toString();
                Handler handler = OplusNotificationLogger.LOG_HANDLER;
                LogUtil.i(LogUtil.TAG_NOTIFICATION, TAG, "EMPTY_NOTIFICATION-->".concat(string));
            }
        }
    }

    public final void addInterruptInfo(NotificationEntry notificationEntry, Map<String, String> map) {
        Map<String, String> interruptInfo;
        if (EVENT_LOG_BLACK_LIST.contains(notificationEntry.getSbn().getPackageName())) {
            return;
        }
        OplusNotificationInfo oplusNotificationInfo = this.datas.get(notificationEntry);
        if (oplusNotificationInfo == null || (interruptInfo = oplusNotificationInfo.getInterruptInfo()) == null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "no data for ", NotificationUtilsKt.getLogKeyNonNull(notificationEntry), LogUtil.TAG_NOTIFICATION, TAG);
        } else {
            interruptInfo.putAll(map);
        }
    }

    public final void addWakeupInfo(NotificationEntry notificationEntry, boolean z, String str) {
        if (EVENT_LOG_BLACK_LIST.contains(notificationEntry.getSbn().getPackageName())) {
            return;
        }
        this.eventLog.logNotification(OplusNotifiEventLog.TYPE_INTERRUPT, notificationEntry, DozeLogger$.ExternalSyntheticOutline0.m("WAKEUP=", "|", str, z));
    }

    public final void logEvent(NotificationEntry notificationEntry) {
        OplusNotificationEntryEx.McsInfo mcsInfo;
        Map<String, String> interruptInfo;
        OplusNotificationInfo oplusNotificationInfo = this.datas.get(notificationEntry);
        if (oplusNotificationInfo != null && (interruptInfo = oplusNotificationInfo.getInterruptInfo()) != null) {
            this.eventLog.logNotification(OplusNotifiEventLog.TYPE_INTERRUPT, notificationEntry, interruptInfo.toString());
        }
        if (MCSUtil.isMCSPkg(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getOpPkg())) {
            OplusNotifiEventLog oplusNotifiEventLog = this.eventLog;
            OplusNotificationEntryEx ex = notificationEntry.getEx();
            oplusNotifiEventLog.logNotification(OplusNotifiEventLog.TYPE_GROUP, notificationEntry, "isMcsSingleGroup=" + ((ex == null || (mcsInfo = ex.getMcsInfo()) == null) ? null : Boolean.valueOf(mcsInfo.isTopLevel)));
        }
    }

    public void onBeforeRenderList(List<? extends PipelineEntry> list) {
        for (ListEntry listEntry : this.notifCollection.getAllNotifs()) {
            listEntry.getClass();
            String diffAttacthInfoOrNull = OplusNotificationInfoCollectionKt.getDiffAttacthInfoOrNull(listEntry);
            if (diffAttacthInfoOrNull != null) {
                this.eventLog.logNotification(OplusNotifiEventLog.TYPE_ATTACH_INFO, listEntry, diffAttacthInfoOrNull);
            }
        }
    }

    public void onEntryAdded(NotificationEntry notificationEntry) {
        logEvent(notificationEntry);
        logNotificationPosted(notificationEntry, false);
    }

    public void onEntryBind(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        if (notificationEntry == null || EVENT_LOG_BLACK_LIST.contains(notificationEntry.getSbn().getPackageName())) {
            return;
        }
        this.datas.put(notificationEntry, new OplusNotificationInfo(notificationEntry, new LinkedHashMap(), new WakeupInfo(false, null, null)));
    }

    public void onEntryCleanUp(NotificationEntry notificationEntry) {
        this.datas.remove(notificationEntry);
    }

    public void onEntryInit(NotificationEntry notificationEntry) {
        this.eventLog.logInitNotification(notificationEntry);
    }

    public void onEntryRemoved(NotificationEntry notificationEntry, int i) {
        NotificationStatisticUtil.onNotificationRemoved(((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getApplicationContext(), notificationEntry, i, this.statusBarStateController.getState(), notificationEntry.getSbn().getNotification().isGroupChild());
    }

    public void onEntryUpdated(NotificationEntry notificationEntry) {
        logEvent(notificationEntry);
        logNotificationPosted(notificationEntry, true);
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}
