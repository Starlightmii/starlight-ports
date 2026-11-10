package com.oplus.systemui.notification.flashlight;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.OplusCameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.room.util.CursorUtil$;
import androidx.slice.widget.RowView$;
import com.android.systemui.CoreStartable;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.keyguard.OplusKeyguardDependencyExImpl;
import com.oplus.systemui.keyguard.gesture.OplusBlackScreenGestureControllExImpl;
import com.oplus.systemui.qs.observer.FlashlightTorchValueObserver;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.flashlight.seeding.FlashlightSeedling;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FlashlightNotification implements CoreStartable, FlashlightController.FlashlightListener, StatusBarStateController.StateListener, NotifCollectionListener {
    public final Context mContext;
    public final FlashlightController mFlashlightControl;
    public final FlashlightSeedlingChecker mFlashlightSeedlingChecker;
    public final NotificationManager mNotificationManager;
    Lazy mOplusKeyguardDependencyEx;
    public final NotifPipeline mPipeline;
    public final StatusBarStateController mStatusBarStateController;
    public final AnonymousClass1 mFlashlightReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.notification.flashlight.FlashlightNotification.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null || !"com.android.systemui.ACTION_SWITCH_FLASHLIGHT".equals(intent.getAction())) {
                return;
            }
            if (intent.getBooleanExtra("intent_extra_flashlight", false)) {
                LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "onReceive: sendNotification --> click close btn in notification and update it");
                FlashlightNotification.this.sendNotification$1(false);
                FlashlightNotification flashlightNotification = FlashlightNotification.this;
                flashlightNotification.mIsDelayCancel = true;
                if (flashlightNotification.mFlashlightHandler != null) {
                    LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "onReceive: will cancel notification after 3s because close light");
                    FlashlightNotification.this.mFlashlightHandler.sendEmptyMessageDelayed(0, 3000L);
                }
                FlashlightNotification.this.mFlashlightControl.setFlashlightFromUser(false);
                return;
            }
            FlashlightNotification.this.mFlashlightControl.setFlashlightFromUser(true);
            if (1 != FlashlightNotification.this.mCurrentState) {
                LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "onReceive: sendNotification --> click reOpen btn in notification and update it");
                FlashlightNotification.this.sendNotification$1(true);
            }
            FlashlightHandler flashlightHandler = FlashlightNotification.this.mFlashlightHandler;
            if (flashlightHandler != null) {
                flashlightHandler.removeMessages(0);
            }
        }
    };
    public boolean mIsFlashlightOpen = false;
    public boolean mIsDelayCancel = false;
    public int mCurrentState = -1;
    public OplusBlackScreenGestureControllExImpl mBlackScreenGestureExt = null;
    public boolean mIsNightLightEnabled = false;
    public final AnonymousClass2 mFlashlightTorchValueObserver = new FlashlightTorchValueObserver() { // from class: com.oplus.systemui.notification.flashlight.FlashlightNotification.2
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            FlashlightNotification flashlightNotification = FlashlightNotification.this;
            if (flashlightNotification.mFlashlightControl.mLevelAdjustSupport) {
                int torchValue = QsSettingsValueProxy.getTorchValue(flashlightNotification.mContext, 0);
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(torchValue, "TorchValueChanged value= ", LogUtil.TAG_COMMON, "FlashlightNotification");
                FlashlightNotification.this.getClass();
                if ((FeatureOption.isSupportMultiModeFlashLight || torchValue != 0) && torchValue != 1 && torchValue != 2 && torchValue != 3) {
                    FlashlightNotification.this.getClass();
                    if (!FeatureOption.isSupportMultiModeFlashLight || torchValue < 0 || torchValue > 11) {
                        return;
                    }
                }
                FlashlightNotification flashlightNotification2 = FlashlightNotification.this;
                FlashlightControllerImpl flashlightControllerImpl = flashlightNotification2.mFlashlightControl;
                if (flashlightControllerImpl.isEnabled()) {
                    if (FeatureOption.isSupportMultiModeFlashLight && torchValue >= 1 && torchValue <= 11) {
                        flashlightNotification2.sendNotification();
                    }
                    if (!FeatureOption.isSupportMultiModeFlashLight && flashlightControllerImpl.getFlashlightLevel() > 2 && (torchValue == 2 || torchValue == 3)) {
                        flashlightNotification2.sendNotification();
                    }
                }
                FlashlightNotification.this.mFlashlightControl.setFlashlightTorchValue(torchValue);
            }
        }
    };
    public final FlashlightHandler mFlashlightHandler = new FlashlightHandler();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FlashlightHandler extends Handler {
        public FlashlightHandler() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "cancel notification : form cancel msg");
                FlashlightNotification.this.cancelNotification$2();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.notification.flashlight.FlashlightNotification$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.oplus.systemui.notification.flashlight.FlashlightNotification$2] */
    public FlashlightNotification(Context context, FlashlightController flashlightController, NotificationManager notificationManager, StatusBarStateController statusBarStateController, NotifPipeline notifPipeline, FlashlightSeedlingChecker flashlightSeedlingChecker) {
        this.mContext = context;
        this.mFlashlightControl = flashlightController;
        this.mNotificationManager = notificationManager;
        this.mStatusBarStateController = statusBarStateController;
        this.mPipeline = notifPipeline;
        this.mFlashlightSeedlingChecker = flashlightSeedlingChecker;
    }

    private String getThreeFlashlightTitle() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isThreeFlashSupport()) {
            return "";
        }
        boolean z = this.mIsNightLightEnabled;
        Context context = this.mContext;
        return z ? context.getString(2131955093) : context.getString(2131954864);
    }

    public final void cancelNotification$2() {
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            UserHandle userHandle = UserHandle.ALL;
            notificationManager.cancelAsUser(null, 10011, userHandle);
            this.mNotificationManager.cancelAsUser(null, 10012, userHandle);
            this.mIsDelayCancel = false;
        }
    }

    public final Notification.Builder getNotificationBuilder(boolean z) {
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "initRemoteViews: " + z);
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), 2131558986);
        Context context = this.mContext;
        String string = z ? context.getString(2131954868) : context.getString(2131954866);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isThreeFlashSupport() && this.mIsNightLightEnabled) {
            string = this.mContext.getString(2131955091);
        }
        remoteViews.setTextViewText(2131362634, string);
        remoteViews.setContentDescription(2131365855, string);
        remoteViews.setImageViewResource(2131365855, z ? 2131237156 : 2131237154);
        if (FlavorOneFeatureOption.isThreeFlashSupport() && this.mIsNightLightEnabled) {
            remoteViews.setImageViewResource(2131365855, 2131235999);
        }
        Intent intent = new Intent("com.android.systemui.ACTION_SWITCH_FLASHLIGHT");
        intent.putExtra("intent_extra_flashlight", z);
        remoteViews.setOnClickPendingIntent(2131365855, PendingIntent.getBroadcastAsUser(this.mContext, 0, intent, 201326592, UserHandle.CURRENT));
        Context context2 = this.mContext;
        String string2 = z ? context2.getString(2131954868) : context2.getString(2131954866);
        String string3 = this.mContext.getString(2131954864);
        if (FlavorOneFeatureOption.isThreeFlashSupport() && this.mIsNightLightEnabled) {
            string2 = this.mContext.getString(2131955091);
            string3 = this.mContext.getString(2131955093);
        }
        String str = string3;
        Bundle bundle = new Bundle();
        bundle.putString("seedling_event", "flashlightEvent");
        bundle.putBoolean(FlashlightSeedling.KEY_FLASHLIGHT_STATE, z);
        bundle.putString("SYSUI_HIGH_KEY", "SYSUI_HIGH_ENABLE");
        String string4 = this.mContext.getString(2131954864);
        if (FlavorOneFeatureOption.isThreeFlashSupport()) {
            boolean z2 = this.mIsNightLightEnabled;
            Context context3 = this.mContext;
            if (z2) {
                string4 = context3.getString(2131955093);
                bundle.putString(FlashlightSeedling.KEY_FLASHLIGHT_TITLE, this.mContext.getString(2131955093));
            } else {
                bundle.putString(FlashlightSeedling.KEY_FLASHLIGHT_TITLE, context3.getString(2131954864));
            }
        }
        bundle.putString("android.substName", string4);
        return getNotificationBuilder(str, string2, remoteViews, true, bundle);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    public final void onEntryInit(NotificationEntry notificationEntry) {
        boolean z;
        StatusBarNotification sbn = notificationEntry.getSbn();
        if (sbn != null) {
            if ("com.android.systemui".equals(sbn.getPackageName())) {
                z = true;
            } else {
                String packageName = sbn.getPackageName();
                if (TextUtils.isEmpty(packageName) ? false : OplusAnimationController.PACKAGE_SYSTEM.equals(packageName)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            boolean zEquals = sbn.getNotification().extras.getString("SYSUI_HIGH_KEY", "SYSUI_HIGH_DISABLE").equals("SYSUI_HIGH_ENABLE");
            int uid = sbn.getUid();
            int appId = UserHandle.getAppId(uid);
            boolean z2 = appId == 1000 || appId == 1001 || uid == 0 || z;
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isSystemNotification: ", "---pkgName : ", z2);
            sbM.append(sbn.getPackageName());
            sbM.append("---enable : ");
            sbM.append(zEquals);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OpNotificationUtils", sbM.toString());
            notificationEntry.getEx().setSysUIHighPriority(z2 && zEquals);
            notificationEntry.setBucket(17);
        }
    }

    public final void onFlashlightAvailabilityChanged(boolean z) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("onFlashlightAvailabilityChanged: ", LogUtil.TAG_COMMON, "FlashlightNotification", z);
        if (z) {
            return;
        }
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "cancel notification : not available");
        if (!this.mFlashlightSeedlingChecker.triggerFlashlightSeedling(false, getThreeFlashlightTitle())) {
            cancelNotification$2();
        }
        this.mFlashlightControl.setFlashlight(false);
        FlashlightHandler flashlightHandler = this.mFlashlightHandler;
        if (flashlightHandler != null) {
            flashlightHandler.removeMessages(0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c6  */
    public final void onFlashlightChanged(boolean z) {
        boolean z2;
        boolean z3;
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_COMMON, "FlashlightNotification", RowView$.ExternalSyntheticOutline0.m("onFlashlightChanged: ", " , mIsDelayCancel = ", z), this.mIsDelayCancel);
        this.mIsFlashlightOpen = z;
        boolean nightLightOpened = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getOplusFlashLightControllerEx().getNightLightOpened();
        this.mIsNightLightEnabled = nightLightOpened;
        if (nightLightOpened) {
            LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "nightlight notification is going to show.");
        }
        boolean z4 = this.mIsFlashlightOpen;
        FlashlightSeedlingChecker flashlightSeedlingChecker = this.mFlashlightSeedlingChecker;
        if (z4) {
            LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "mOplusKeyguardDependencyEx: " + this.mOplusKeyguardDependencyEx.get());
            if (this.mBlackScreenGestureExt == null && this.mOplusKeyguardDependencyEx.get() != null) {
                this.mBlackScreenGestureExt = ((OplusKeyguardDependencyExImpl) this.mOplusKeyguardDependencyEx.get()).getOplusBlackScreenGestureControll();
            }
            FlashlightControllerImpl flashlightControllerImpl = this.mFlashlightControl;
            if (flashlightControllerImpl == null || !(flashlightControllerImpl instanceof FlashlightControllerImpl)) {
                z2 = false;
            } else {
                FlashlightControllerImpl flashlightControllerImpl2 = flashlightControllerImpl;
                List<String> showFlashlightNotificationPackageList = CustomizeFeatureOption.getShowFlashlightNotificationPackageList();
                if (showFlashlightNotificationPackageList.isEmpty()) {
                    z3 = false;
                } else {
                    String clientPackage = OplusCameraManager.getInstance().getClientPackage();
                    Log.d("FlashlightController", "flash client package " + clientPackage + " showNotificationPackages: " + showFlashlightNotificationPackageList);
                    if (showFlashlightNotificationPackageList.contains(clientPackage)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                }
                boolean z5 = flashlightControllerImpl2.mUserInteraction || z3;
                OplusBlackScreenGestureControllExImpl oplusBlackScreenGestureControllExImpl = this.mBlackScreenGestureExt;
                boolean z6 = (oplusBlackScreenGestureControllExImpl == null || oplusBlackScreenGestureControllExImpl.isScreenOn()) ? false : true;
                if (z5 || z6) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("onFlashlightChanged: isNotSuppress?? flash light is not handle by user control ?? --> ", LogUtil.TAG_COMMON, "FlashlightNotification", z2);
            if (z2) {
                this.mIsDelayCancel = false;
                if (!flashlightSeedlingChecker.triggerFlashlightSeedling(true, getThreeFlashlightTitle())) {
                    sendNotification$1(true);
                }
                FlashlightHandler flashlightHandler = this.mFlashlightHandler;
                if (flashlightHandler != null) {
                    flashlightHandler.removeMessages(0);
                    return;
                }
                return;
            }
        }
        if (this.mIsDelayCancel) {
            LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "onFlashlightChanged: DO NOTING");
            return;
        }
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "cancel notification : light off but not from click notification close btn");
        if (flashlightSeedlingChecker.triggerFlashlightSeedling(false, getThreeFlashlightTitle())) {
            return;
        }
        cancelNotification$2();
    }

    public final void onFlashlightError() {
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "Error:onFlashlightError");
    }

    public final void onNightLightStateChanged(boolean z) {
        if (this.mIsNightLightEnabled != z) {
            this.mIsNightLightEnabled = z;
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "FlashlightNotification", RowView$.ExternalSyntheticOutline0.m("onNightLightStateChanged:", " flashlight enabled:", z), this.mIsFlashlightOpen);
            if (z || !this.mIsFlashlightOpen) {
                return;
            }
            LogUtil.d(LogUtil.TAG_QS, "FlashlightNotification", "nightlight is disabled, refresh flashlight text.");
            sendNotification$1(this.mIsFlashlightOpen);
        }
    }

    public final void onStateChanged(int i) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "newState: ", LogUtil.TAG_COMMON, "FlashlightNotification");
        this.mCurrentState = i;
    }

    public final void sendNotification(Notification.Builder builder, Notification.Builder builder2, int i) {
        NotificationChannel notificationChannel = new NotificationChannel("FLA", this.mContext.getString(2131954864), 3);
        notificationChannel.setSound(null, null);
        notificationChannel.enableVibration(false);
        notificationChannel.enableLights(false);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
        builder.setPublicVersion(builder2.build());
        this.mNotificationManager.notifyAsUser(null, i, builder.build(), UserHandle.ALL);
    }

    public final void sendNotification$1(boolean z) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("sendNotification: ", LogUtil.TAG_COMMON, "FlashlightNotification", z);
        sendNotification(getNotificationBuilder(z), getNotificationBuilder(z), 10011);
    }

    public final void start() {
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "start");
        this.mStatusBarStateController.addCallback(this);
        FlashlightControllerImpl flashlightControllerImpl = this.mFlashlightControl;
        if (flashlightControllerImpl != null) {
            flashlightControllerImpl.addCallback(this);
        }
        ((BroadcastDispatcher) DependencyEx.sDependency.getDependency(BroadcastDispatcher.class)).registerReceiver(this.mFlashlightReceiver, new IntentFilter("com.android.systemui.ACTION_SWITCH_FLASHLIGHT"), (Executor) null, UserHandle.ALL, 2, "com.android.systemui.permission.SELF");
        this.mPipeline.addCollectionListener(this);
        this.mNotificationManager.cancel(10011);
        this.mNotificationManager.cancel(10012);
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", "setTorchValueObserver()");
        registerForUserSwitch(this.mContext, -1, false);
        SuperPowerSaveSettingsObserver superPowerSaveSettingsObserver = SuperPowerSaveSettingsObserver.getInstance();
        FlashlightNotification$$ExternalSyntheticLambda0 flashlightNotification$$ExternalSyntheticLambda0 = new FlashlightNotification$$ExternalSyntheticLambda0();
        flashlightNotification$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        superPowerSaveSettingsObserver.addListener(flashlightNotification$$ExternalSyntheticLambda0);
    }

    public final void sendNotification() {
        String string = this.mContext.getString(2131955651);
        String string2 = this.mContext.getString(2131955652);
        LogUtil.d(LogUtil.TAG_COMMON, "FlashlightNotification", CursorUtil$.ExternalSyntheticOutline0.m("sendNotification: title=", string, " content=", string2));
        sendNotification(getNotificationBuilder(string, string2), getNotificationBuilder(string, string2), 10012);
    }

    public final Notification.Builder getNotificationBuilder(String str, String str2) {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("SYSUI_HIGH_KEY", "SYSUI_HIGH_ENABLE");
        String string = this.mContext.getString(2131954864);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isThreeFlashSupport() && this.mIsNightLightEnabled) {
            string = this.mContext.getString(2131955093);
        }
        bundleM.putString("android.substName", string);
        return getNotificationBuilder(str, str2, null, false, bundleM);
    }

    public final Notification.Builder getNotificationBuilder(String str, String str2, RemoteViews remoteViews, boolean z, Bundle bundle) {
        bundle.putBoolean("isFluidDevice", FeatureOption.isFluidDevice());
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isThreeFlashSupport() && this.mIsNightLightEnabled) {
            return new Notification.Builder(this.mContext, "FLA").setCustomContentView(remoteViews).setCustomBigContentView(remoteViews).setSmallIcon(2131235998).setContentTitle(str).setOngoing(z).setVisibility(1).setGroup(this.mContext.getString(2131954864)).setOnlyAlertOnce(true).setExtras(bundle).setContentText(str2);
        }
        return new Notification.Builder(this.mContext, "FLA").setCustomContentView(remoteViews).setCustomBigContentView(remoteViews).setSmallIcon(2131235719).setContentTitle(str).setOngoing(z).setVisibility(1).setGroup(this.mContext.getString(2131954864)).setOnlyAlertOnce(true).setExtras(bundle).setContentText(str2);
    }
}
