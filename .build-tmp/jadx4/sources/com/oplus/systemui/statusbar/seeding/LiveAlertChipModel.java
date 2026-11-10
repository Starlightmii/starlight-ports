package com.oplus.systemui.statusbar.seeding;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.statusbar.chips.ui.model.ChipCardModel;
import com.android.systemui.statusbar.chips.ui.model.Chronometer;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationOptions;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LiveAlertChipModel {
    public static final Companion Companion = new Companion();
    private static final Gson GSON = new Gson();
    private static final String NOTIFICATION_CHANNELID = "live_alert";
    private static final String PKG_NAME = "com.android.systemui";
    private static final String TAG = "LiveAlertChipModel";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CastToOtherDeviceChipModel extends LiveAlertChipModel {
        private final OplusLiveAlertViewModel.OplusChipType chipType;
        private final OngoingActivityChipModel model;

        public CastToOtherDeviceChipModel(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            this.chipType = oplusChipType;
            this.model = ongoingActivityChipModel;
        }

        public final OplusLiveAlertViewModel.OplusChipType component1() {
            return this.chipType;
        }

        public final OngoingActivityChipModel component2() {
            return this.model;
        }

        public final CastToOtherDeviceChipModel copy(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            return new CastToOtherDeviceChipModel(oplusChipType, ongoingActivityChipModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CastToOtherDeviceChipModel)) {
                return false;
            }
            CastToOtherDeviceChipModel castToOtherDeviceChipModel = (CastToOtherDeviceChipModel) obj;
            return this.chipType == castToOtherDeviceChipModel.chipType && Intrinsics.areEqual(this.model, castToOtherDeviceChipModel.model);
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OplusLiveAlertViewModel.OplusChipType getChipType() {
            return this.chipType;
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OngoingActivityChipModel getModel() {
            return this.model;
        }

        public final int hashCode() {
            return this.model.hashCode() + (this.chipType.hashCode() * 31);
        }

        public String toString() {
            return "CastToOtherDeviceChipModel(chipType=" + this.chipType + ", model=" + this.model + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LiveAlertSbnChipModel {
        private final OngoingActivityChipModel mode;
        private final StatusBarNotification sbn;

        public LiveAlertSbnChipModel(StatusBarNotification statusBarNotification, OngoingActivityChipModel ongoingActivityChipModel) {
            this.sbn = statusBarNotification;
            this.mode = ongoingActivityChipModel;
        }

        public final StatusBarNotification component1() {
            return this.sbn;
        }

        public final OngoingActivityChipModel component2() {
            return this.mode;
        }

        public final LiveAlertSbnChipModel copy(StatusBarNotification statusBarNotification, OngoingActivityChipModel ongoingActivityChipModel) {
            return new LiveAlertSbnChipModel(statusBarNotification, ongoingActivityChipModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveAlertSbnChipModel)) {
                return false;
            }
            LiveAlertSbnChipModel liveAlertSbnChipModel = (LiveAlertSbnChipModel) obj;
            return Intrinsics.areEqual(this.sbn, liveAlertSbnChipModel.sbn) && Intrinsics.areEqual(this.mode, liveAlertSbnChipModel.mode);
        }

        public final OngoingActivityChipModel getMode() {
            return this.mode;
        }

        public final StatusBarNotification getSbn() {
            return this.sbn;
        }

        public final int hashCode() {
            StatusBarNotification statusBarNotification = this.sbn;
            return this.mode.hashCode() + ((statusBarNotification == null ? 0 : statusBarNotification.hashCode()) * 31);
        }

        public String toString() {
            return "LiveAlertSbnChipModel(sbn=" + this.sbn + ", mode=" + this.mode + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScreenRecordChipModel extends LiveAlertChipModel {
        private final OplusLiveAlertViewModel.OplusChipType chipType;
        private final OngoingActivityChipModel model;

        public ScreenRecordChipModel(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            this.chipType = oplusChipType;
            this.model = ongoingActivityChipModel;
        }

        public final OplusLiveAlertViewModel.OplusChipType component1() {
            return this.chipType;
        }

        public final OngoingActivityChipModel component2() {
            return this.model;
        }

        public final ScreenRecordChipModel copy(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            return new ScreenRecordChipModel(oplusChipType, ongoingActivityChipModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ScreenRecordChipModel)) {
                return false;
            }
            ScreenRecordChipModel screenRecordChipModel = (ScreenRecordChipModel) obj;
            return this.chipType == screenRecordChipModel.chipType && Intrinsics.areEqual(this.model, screenRecordChipModel.model);
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OplusLiveAlertViewModel.OplusChipType getChipType() {
            return this.chipType;
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OngoingActivityChipModel getModel() {
            return this.model;
        }

        public final int hashCode() {
            return this.model.hashCode() + (this.chipType.hashCode() * 31);
        }

        public String toString() {
            return "ScreenRecordChipModel(chipType=" + this.chipType + ", model=" + this.model + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ShareToAppChipModel extends LiveAlertChipModel {
        private final OplusLiveAlertViewModel.OplusChipType chipType;
        private final OngoingActivityChipModel model;

        public ShareToAppChipModel(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            this.chipType = oplusChipType;
            this.model = ongoingActivityChipModel;
        }

        public final OplusLiveAlertViewModel.OplusChipType component1() {
            return this.chipType;
        }

        public final OngoingActivityChipModel component2() {
            return this.model;
        }

        public final ShareToAppChipModel copy(OplusLiveAlertViewModel.OplusChipType oplusChipType, OngoingActivityChipModel ongoingActivityChipModel) {
            return new ShareToAppChipModel(oplusChipType, ongoingActivityChipModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ShareToAppChipModel)) {
                return false;
            }
            ShareToAppChipModel shareToAppChipModel = (ShareToAppChipModel) obj;
            return this.chipType == shareToAppChipModel.chipType && Intrinsics.areEqual(this.model, shareToAppChipModel.model);
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OplusLiveAlertViewModel.OplusChipType getChipType() {
            return this.chipType;
        }

        @Override // com.oplus.systemui.statusbar.seeding.LiveAlertChipModel
        public OngoingActivityChipModel getModel() {
            return this.model;
        }

        public final int hashCode() {
            return this.model.hashCode() + (this.chipType.hashCode() * 31);
        }

        public String toString() {
            return "ShareToAppChipModel(chipType=" + this.chipType + ", model=" + this.model + ")";
        }
    }

    private final void setLiverAlertNotificationOption(Notification notification) {
        if (notification != null) {
            try {
                notification.extras.putString("oplusLiveAlertOptions", GSON.toJson(new OplusLiveAlertNotificationOptions(true, "com.android.systemui", 0, CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{8}), CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{8}), null)));
            } catch (JsonIOException e) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "entryToLiveAlert failed: " + e);
            }
        }
    }

    public final StatusBarNotification createStatusBarNotification(Notification notification, UserHandle userHandle) {
        return new StatusBarNotification("com.android.systemui", "com.android.systemui", getChipType().getValue(), getChipType().name(), Process.myUid(), Process.myPid(), notification, userHandle, "", System.currentTimeMillis());
    }

    public abstract OplusLiveAlertViewModel.OplusChipType getChipType();

    public abstract OngoingActivityChipModel getModel();

    /* JADX WARN: Code duplicated, block: B:11:0x0035  */
    public StatusBarNotification toNotification(Icon icon, Context context, UserHandle userHandle) {
        Icon iconCreateWithResource;
        Long lValueOf;
        long jCurrentTimeMillis;
        OngoingActivityChipModel.Active model = getModel();
        if (!(model instanceof OngoingActivityChipModel.Active)) {
            return null;
        }
        OngoingActivityChipModel.Active active = model;
        OngoingActivityChipModel.ChipIcon.SingleColorIcon icon2 = active.getIcon();
        if (!(icon2 instanceof OngoingActivityChipModel.ChipIcon.SingleColorIcon)) {
            iconCreateWithResource = null;
        } else if (icon != null) {
            iconCreateWithResource = icon;
        } else {
            com.android.systemui.common.shared.model.Icon.Resource impl = icon2.getImpl();
            if (impl instanceof com.android.systemui.common.shared.model.Icon.Resource) {
                iconCreateWithResource = Icon.createWithResource(context, impl.getResId().intValue());
            } else {
                iconCreateWithResource = null;
            }
        }
        Notification.Builder builder = new Notification.Builder(context, NOTIFICATION_CHANNELID);
        if (iconCreateWithResource != null) {
            builder.setSmallIcon(iconCreateWithResource);
        }
        OngoingActivityChipModel.Content.Timer content = active.getContent();
        if (content instanceof OngoingActivityChipModel.Content.Timer) {
            OngoingActivityChipModel.Content.Timer timer = content;
            Chronometer.Running value = timer.getValue();
            Chronometer.Running running = value instanceof Chronometer.Running ? value : null;
            if (running != null) {
                boolean z = running.isCountdown;
                SystemClockImpl timeSource = timer.getTimeSource();
                timeSource.getClass();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                long jAsElapsedRealtime = running.getEventTime().asElapsedRealtime(timeSource);
                long j = z ? jAsElapsedRealtime - jElapsedRealtime : jElapsedRealtime - jAsElapsedRealtime;
                if (j < 0) {
                    lValueOf = null;
                } else {
                    if (z) {
                        j -= 499;
                    }
                    lValueOf = Long.valueOf(j);
                }
                if (lValueOf != null) {
                    if (z) {
                        jCurrentTimeMillis = System.currentTimeMillis() + lValueOf.longValue();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() - lValueOf.longValue();
                    }
                    builder.setWhen(jCurrentTimeMillis);
                    builder.setChronometerCountDown(z);
                    builder.setUsesChronometer(true);
                    builder.setShowWhen(false);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "LiveAlertChipModel-->".concat("configureTimerChronometer: startTimeMs:" + lValueOf + ", isCountdown:" + z));
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "LiveAlertChipModel-->".concat("configureTimerChronometer: running:" + running));
            }
        } else if (content instanceof OngoingActivityChipModel.Content.Countdown) {
            builder.setWhen(((OngoingActivityChipModel.Content.Countdown) content).getSecondsUntilStarted() * 1000);
            builder.setChronometerCountDown(true);
            builder.setUsesChronometer(true);
            builder.setShowWhen(false);
        }
        ChipCardModel chipCardModel = active.getChipCardModel();
        if (chipCardModel != null) {
            Integer title = chipCardModel.getTitle();
            if (title != null) {
                builder.setContentTitle(context.getText(title.intValue()));
            }
            Integer capsuleTitle = chipCardModel.getCapsuleTitle();
            if (capsuleTitle != null) {
                builder.setShortCriticalText(context.getText(capsuleTitle.intValue()).toString());
            }
            String message = chipCardModel.getMessage();
            if (message != null) {
                builder.setContentText(message);
            }
            PendingIntent stopIntent = chipCardModel.getStopIntent();
            if (stopIntent != null) {
                builder.addAction(new Notification.Action.Builder((Icon) null, context.getText(chipCardModel.getStopActionText()), stopIntent).build());
            }
        }
        Notification notificationBuild = builder.build();
        setLiverAlertNotificationOption(notificationBuild);
        return createStatusBarNotification(notificationBuild, userHandle);
    }

    public StatusBarNotification toNotification(Context context, UserHandle userHandle) {
        return createStatusBarNotification(new Notification.Builder(context, NOTIFICATION_CHANNELID).build(), userHandle);
    }
}
