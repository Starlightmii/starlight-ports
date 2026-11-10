package com.oplus.systemui.statusbar.notification.power.supersave;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.power.LowBatteryUiController;
import com.oplus.systemui.statusbar.notification.power.OplusPowerUISoundUtil;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.receiver.AbstractReceiver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSuperSaveControllerImpl implements OplusSuperSaveController, UserTracker.Callback, KeyguardStateController.Callback {
    private static final String ALLOW_LIST = "allow_list";
    private static final String GET_POWER_CONTROL_LIST = "getPowerControlList";
    private static final String TAG = "SuperSaveControllerImpl";
    public static final String UN_STANDARD_CHARGE = "CRG";
    private static final String VISERNEO_PACKGE = "com.smartviser.viserneo";
    public int batteryLevel;
    public int batteryStatus;
    public final int batteryValueMin;
    private AbstractReceiver broadcastDispatcher;
    private final Context context;
    private OplusSuperSaveCountDownDialog enterSuperSaveDialog;
    private OplusSuperSaveCountDownNotification enterSuperSaveNotification;
    private OplusSuperSaveCountDownTimer enterSuperSaveTimerTask;
    private final Lazy enterTimerTaskListener$delegate;
    private OplusSuperSaveCountDownDialog enteredSuperSaveDialog;
    private OplusSuperSaveEnteredNotification enteredSuperSaveNotification;
    private OplusSuperSaveDialog exitSuperSaveDialog;
    private OplusSuperSaveCountDownTimer exitSuperSaveTimerTask;
    private final Lazy exitTimerTaskListener$delegate;
    private final KeyguardStateController keyguardStateController;
    private final DeviceProvisionedController mDeviceProvisionedController;
    private final Lazy notificationManager$delegate;
    public int plugType;
    private final OplusPowerUISoundUtil powerUISoundUtil;
    private OplusSubDisplaySuperSaveCountDownDialog subDisplaySuperSaveCountDownDialog;
    public int superSaveFlag;
    private final OplusSuperSaveMode superSaveMode;
    public int telecomState;
    public static final Companion Companion = new Companion();
    private static final Uri URI_POWER_CONTROL = Uri.parse("content://com.oplus.powermanager");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static NotificationManager $r8$lambda$iHYiGv8SZ2NvWW3qhxU0VL_0_fg(OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl) {
        return (NotificationManager) oplusSuperSaveControllerImpl.context.getSystemService("notification");
    }

    public OplusSuperSaveControllerImpl(Context context, UserTracker userTracker, Executor executor, KeyguardStateController keyguardStateController, OplusPowerUISoundUtil oplusPowerUISoundUtil, DeviceProvisionedController deviceProvisionedController) {
        this.context = context;
        this.keyguardStateController = keyguardStateController;
        this.powerUISoundUtil = oplusPowerUISoundUtil;
        this.mDeviceProvisionedController = deviceProvisionedController;
        OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0 oplusSuperSaveControllerImpl$$ExternalSyntheticLambda0 = new OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0(0);
        oplusSuperSaveControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.notificationManager$delegate = LazyKt__LazyJVMKt.lazy(oplusSuperSaveControllerImpl$$ExternalSyntheticLambda0);
        this.superSaveMode = new OplusSuperSaveMode(context);
        OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0 oplusSuperSaveControllerImpl$$ExternalSyntheticLambda1 = new OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0(1);
        oplusSuperSaveControllerImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.exitTimerTaskListener$delegate = LazyKt__LazyJVMKt.lazy(oplusSuperSaveControllerImpl$$ExternalSyntheticLambda1);
        OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0 oplusSuperSaveControllerImpl$$ExternalSyntheticLambda2 = new OplusSuperSaveControllerImpl$$ExternalSyntheticLambda0(2);
        oplusSuperSaveControllerImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.enterTimerTaskListener$delegate = LazyKt__LazyJVMKt.lazy(oplusSuperSaveControllerImpl$$ExternalSyntheticLambda2);
        this.batteryLevel = 100;
        this.batteryStatus = 1;
        this.batteryValueMin = SystemProperties.getInt("persist.vendor.battery_value_min", 1);
        userTracker.addCallback(this, executor);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(this);
        getNotificationManager().createNotificationChannel(new NotificationChannel(UN_STANDARD_CHARGE, context.getString(2131954805), 3));
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(new IObserverListener() { // from class: com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveControllerImpl$registerSuperPowerSaveListener$superPowerSaveListener$1
            @Override // com.oplusos.systemui.common.observer.IObserverListener
            public final void onChange(boolean z) {
                OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    OplusSuperSaveControllerImpl.Companion companion = OplusSuperSaveControllerImpl.Companion;
                    oplusSuperSaveControllerImpl.getClass();
                    Log.d("SystemUi--Common", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("superPowerSaveListener isSuperSaveMode: ", oplusSuperSaveControllerImpl.getSuperSaveState(), " ,getSuperSaveState:", "SuperSaveControllerImpl-->", OplusSuperSaveControllerImpl.isSuperSaveMode()));
                }
                if (this.this$0.getSuperSaveState() == 1) {
                    this.this$0.getClass();
                    if (!OplusSuperSaveControllerImpl.isSuperSaveMode()) {
                        this.this$0.resetAutoSuperSaveAll();
                        this.this$0.setSuperSaveState(3);
                    }
                }
                if (this.this$0.getSuperSaveState() != 1) {
                    this.this$0.getClass();
                    if (OplusSuperSaveControllerImpl.isSuperSaveMode()) {
                        this.this$0.resetAutoSuperSaveAll();
                        this.this$0.setSuperSaveState(2);
                    }
                }
            }
        });
        SystemUIThreadUtil.runOnWorkThread(new Runnable() { // from class: com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveControllerImpl.1
            @Override // java.lang.Runnable
            public final void run() {
                Object systemService = OplusSuperSaveControllerImpl.this.context.getSystemService("batterymanager");
                final BatteryManager batteryManager = systemService instanceof BatteryManager ? (BatteryManager) systemService : null;
                if (batteryManager != null) {
                    final OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl = OplusSuperSaveControllerImpl.this;
                    final int intProperty = batteryManager.getIntProperty(4);
                    int i = intProperty < 0 ? 0 : intProperty;
                    if (i > 100) {
                        i = 100;
                    }
                    oplusSuperSaveControllerImpl.batteryLevel = i;
                    oplusSuperSaveControllerImpl.superSaveFlag = oplusSuperSaveControllerImpl.superSaveMode.getAutoEnterSuperSavePowerModeFlag() ? 1 : 0;
                    SystemUIThreadUtil.runOnUiThread(new Runnable() { // from class: com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveControllerImpl$1$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            LogUtil.i$default(LogUtil.TAG_COMMON, "SuperSaveControllerImpl", KeyguardLogger$.ExternalSyntheticOutline0.m("init initLevel ", intProperty, " ,isCharging ", batteryManager.isCharging()), null, 8);
                            OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl2 = oplusSuperSaveControllerImpl;
                            oplusSuperSaveControllerImpl2.autoEnterOrExitSuperSave(oplusSuperSaveControllerImpl2.batteryLevel, oplusSuperSaveControllerImpl2.plugType != 0, batteryManager.isCharging());
                        }
                    });
                }
            }
        });
    }

    public static final void access$showEnterNotification(OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl) {
        if (oplusSuperSaveControllerImpl.enterSuperSaveNotification == null) {
            if (oplusSuperSaveControllerImpl.broadcastDispatcher == null) {
                oplusSuperSaveControllerImpl.broadcastDispatcher = new registerPendIntentBroadcast.1(oplusSuperSaveControllerImpl);
            }
            AbstractReceiver abstractReceiver = oplusSuperSaveControllerImpl.broadcastDispatcher;
            if (abstractReceiver != null) {
                abstractReceiver.register(oplusSuperSaveControllerImpl.context);
            }
            oplusSuperSaveControllerImpl.enterSuperSaveNotification = new OplusSuperSaveCountDownNotification(oplusSuperSaveControllerImpl.context, oplusSuperSaveControllerImpl.getNotificationManager(), new showEnterNotification.1(oplusSuperSaveControllerImpl));
        }
        OplusSuperSaveCountDownNotification oplusSuperSaveCountDownNotification = oplusSuperSaveControllerImpl.enterSuperSaveNotification;
        if (oplusSuperSaveCountDownNotification != null) {
            oplusSuperSaveCountDownNotification.show(10);
        }
    }

    public static final void access$showEnteredNotification(OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl) {
        if (oplusSuperSaveControllerImpl.enteredSuperSaveNotification == null) {
            oplusSuperSaveControllerImpl.enteredSuperSaveNotification = new OplusSuperSaveEnteredNotification(oplusSuperSaveControllerImpl.context, oplusSuperSaveControllerImpl.getNotificationManager(), new showEnteredNotification.1(oplusSuperSaveControllerImpl));
        }
        OplusSuperSaveEnteredNotification oplusSuperSaveEnteredNotification = oplusSuperSaveControllerImpl.enteredSuperSaveNotification;
        if (oplusSuperSaveEnteredNotification != null) {
            oplusSuperSaveEnteredNotification.show();
        }
    }

    public static final void access$startExitTimerTask(OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl) {
        if (oplusSuperSaveControllerImpl.exitSuperSaveTimerTask == null) {
            oplusSuperSaveControllerImpl.exitSuperSaveTimerTask = new OplusSuperSaveCountDownTimer();
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer = oplusSuperSaveControllerImpl.exitSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer != null) {
            oplusSuperSaveCountDownTimer.addTimerListener(oplusSuperSaveControllerImpl.getExitTimerTaskListener());
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer2 = oplusSuperSaveControllerImpl.exitSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer2 != null) {
            oplusSuperSaveCountDownTimer2.startTimer();
        }
    }

    public static final void access$unregisterPendIntentBroadcast(OplusSuperSaveControllerImpl oplusSuperSaveControllerImpl) {
        AbstractReceiver abstractReceiver = oplusSuperSaveControllerImpl.broadcastDispatcher;
        if (abstractReceiver != null) {
            abstractReceiver.unregister(oplusSuperSaveControllerImpl.context);
        }
        oplusSuperSaveControllerImpl.broadcastDispatcher = null;
    }

    private final EnterTimerTaskListener getEnterTimerTaskListener() {
        return (EnterTimerTaskListener) this.enterTimerTaskListener$delegate.getValue();
    }

    private final ExitTimerTaskListener getExitTimerTaskListener() {
        return (ExitTimerTaskListener) this.exitTimerTaskListener$delegate.getValue();
    }

    private final NotificationManager getNotificationManager() {
        return (NotificationManager) this.notificationManager$delegate.getValue();
    }

    public static boolean isSuperSaveMode() {
        return SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
    }

    public final void autoEnterOrExitSuperSave(int i, boolean z, boolean z2) {
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer;
        int i2;
        boolean normalDebug = LogUtil.getNormalDebug();
        int i3 = this.batteryValueMin;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            int i4 = this.batteryStatus;
            int superSaveState = getSuperSaveState();
            boolean zIsSuperSaveMode = isSuperSaveMode();
            StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("plugged = ", i4, " ,batteryStatus: ", "  ,batteryLevel: ", z);
            RectList$.ExternalSyntheticOutline0.m(i, i3, " ,batteryValueMin: ", " ,getSuperSaveState: ", sbM);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(superSaveState, " ,isSuperSaveMode: ", " , isCharging: ", sbM, zIsSuperSaveMode);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("SuperSaveControllerImpl-->", "SystemUi--Common", sbM, z2);
        }
        if (!isEnableSuperSaveNotice(this.context)) {
            resetAutoSuperSaveAll();
            return;
        }
        if (i > i3) {
            if (!isSuperSaveMode()) {
                resetAutoSuperSaveAll();
                if (getSuperSaveState() != 1) {
                    setSuperSaveState(0);
                    return;
                } else {
                    LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "exitSuperSave during enter superSave", null, 8);
                    setSuperSaveState(4);
                    return;
                }
            }
            OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer2 = this.enterSuperSaveTimerTask;
            if (oplusSuperSaveCountDownTimer2 != null) {
                oplusSuperSaveCountDownTimer2.cancelTimer();
            }
            resetEnteredSuperSaveNotice();
            if (this.exitSuperSaveTimerTask == null && getSuperSaveState() == 1) {
                if (this.keyguardStateController.mShowing) {
                    setSuperSaveState(4);
                    return;
                } else {
                    SystemUIThreadUtil.runOnWorkThread(new exitSuperSave.1(this.context.getString(2131956724), this));
                    return;
                }
            }
            return;
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer3 = this.exitSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer3 != null) {
            oplusSuperSaveCountDownTimer3.cancelTimer();
        }
        resetEnteredSuperSaveNotice();
        if ((z && ((i2 = this.batteryStatus) == 2 || i2 == 5)) || z2 || isSuperSaveMode() || getSuperSaveState() == 3 || (oplusSuperSaveCountDownTimer = this.enterSuperSaveTimerTask) != null) {
            return;
        }
        if (oplusSuperSaveCountDownTimer == null) {
            this.enterSuperSaveTimerTask = new OplusSuperSaveCountDownTimer();
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer4 = this.enterSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer4 != null) {
            oplusSuperSaveCountDownTimer4.addTimerListener(getEnterTimerTaskListener());
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer5 = this.enterSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer5 != null) {
            oplusSuperSaveCountDownTimer5.startTimer();
        }
    }

    public final int getSuperSaveState() {
        return this.superSaveFlag;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:34:0x0065  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:58:? A[RETURN, SYNTHETIC] */
    public final boolean isEnableSuperSaveNotice(Context context) {
        boolean zContains;
        Object obj;
        Throwable th;
        Bundle bundleCall;
        ArrayList<String> stringArrayList;
        try {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri = URI_POWER_CONTROL;
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                    try {
                        String authority = uri.getAuthority();
                        zContains = (authority == null || (bundleCall = contentProviderClientAcquireUnstableContentProviderClient.call(authority, GET_POWER_CONTROL_LIST, null, new Bundle())) == null || (stringArrayList = bundleCall.getStringArrayList(ALLOW_LIST)) == null) ? false : stringArrayList.contains(VISERNEO_PACKGE);
                        try {
                            AutoCloseableKt.closeFinally(contentProviderClientAcquireUnstableContentProviderClient, (Throwable) null);
                        } catch (Throwable th2) {
                            th = th2;
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                        }
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            AutoCloseableKt.closeFinally(contentProviderClientAcquireUnstableContentProviderClient, th3);
                            throw th4;
                        }
                    }
                } else {
                    zContains = false;
                }
                obj = Result.constructor-impl(Unit.INSTANCE);
            } else {
                zContains = false;
                obj = Result.constructor-impl(Unit.INSTANCE);
            }
        } catch (Throwable th5) {
            th = th5;
            zContains = false;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
            th = Result.exceptionOrNull-impl(obj);
            if (th != null) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "isEnableSuperSaveNotice: e ", th.getMessage(), LogUtil.TAG_COMMON, TAG);
            }
            if (LogUtil.getNormalDebug()) {
                int currentUserId = OsBinderCacheUtils.getCurrentUserId();
                FeatureOption featureOption = FeatureOption.INSTANCE;
                boolean zIsSuperPowerModeDisabled = featureOption.isSuperPowerModeDisabled(context);
                boolean zIsDisablePowerSave = featureOption.isDisablePowerSave(context);
                boolean zIsCurrentUserSetup = this.mDeviceProvisionedController.isCurrentUserSetup();
                StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("isEnableSuperSaveNotice: currentUserId: ", currentUserId, " ,isSuperPowerModeDisabled: ", " ,isDisablePowerSave: ", zIsSuperPowerModeDisabled);
                KeyguardLogger$.ExternalSyntheticOutline0.m(" ,isCurrentUserSetup:", ", inFullPermissionForBackgroundOperations: ", sbM, zIsDisablePowerSave, zIsCurrentUserSetup);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("SuperSaveControllerImpl-->", "SystemUi--Common", sbM, zContains);
            }
            if (OsBinderCacheUtils.getCurrentUserId() == 0) {
                return false;
            }
            FeatureOption featureOption2 = FeatureOption.INSTANCE;
            return featureOption2.isSuperPowerModeDisabled(context) && !featureOption2.isDisablePowerSave(context) && this.mDeviceProvisionedController.isCurrentUserSetup() && !zContains;
        }
        th = Result.exceptionOrNull-impl(obj);
        if (th != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "isEnableSuperSaveNotice: e ", th.getMessage(), LogUtil.TAG_COMMON, TAG);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            int currentUserId2 = OsBinderCacheUtils.getCurrentUserId();
            FeatureOption featureOption3 = FeatureOption.INSTANCE;
            boolean zIsSuperPowerModeDisabled2 = featureOption3.isSuperPowerModeDisabled(context);
            boolean zIsDisablePowerSave2 = featureOption3.isDisablePowerSave(context);
            boolean zIsCurrentUserSetup2 = this.mDeviceProvisionedController.isCurrentUserSetup();
            StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m("isEnableSuperSaveNotice: currentUserId: ", currentUserId2, " ,isSuperPowerModeDisabled: ", " ,isDisablePowerSave: ", zIsSuperPowerModeDisabled2);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" ,isCurrentUserSetup:", ", inFullPermissionForBackgroundOperations: ", sbM2, zIsDisablePowerSave2, zIsCurrentUserSetup2);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("SuperSaveControllerImpl-->", "SystemUi--Common", sbM2, zContains);
        }
        if (OsBinderCacheUtils.getCurrentUserId() == 0) {
            return false;
        }
        FeatureOption featureOption4 = FeatureOption.INSTANCE;
        return featureOption4.isSuperPowerModeDisabled(context) && !featureOption4.isDisablePowerSave(context) && this.mDeviceProvisionedController.isCurrentUserSetup() && !zContains;
    }

    public final boolean isSupportSuperSaveScene() {
        if (!CustomizeFeatureOption.sIsSupportMode || !isEnableSuperSaveNotice(this.context)) {
            return false;
        }
        boolean zIsSuperSaveMode = isSuperSaveMode();
        int i = this.batteryValueMin;
        if (zIsSuperSaveMode) {
            if (getSuperSaveState() != 1 || this.batteryLevel <= i) {
                return false;
            }
        } else if (this.batteryLevel > i) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public final void onKeyguardShowingChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isKeyguardShowing: ", "SuperSaveControllerImpl-->", "SystemUi--Common", this.keyguardStateController.mShowing);
        }
        if (this.keyguardStateController.mShowing) {
            return;
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer = this.enterSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer != null && this.telecomState == 0 && this.enterSuperSaveDialog == null && this.subDisplaySuperSaveCountDownDialog == null) {
            showEnterSuperSaveDialog(oplusSuperSaveCountDownTimer.getCurrentTimeValue());
        }
        if (this.enteredSuperSaveNotification != null) {
            String string = this.context.getResources().getString(2131955289);
            String string2 = this.context.getString(2131955288);
            if (this.enteredSuperSaveDialog == null) {
                this.enteredSuperSaveDialog = new OplusSuperSaveCountDownDialog(this.context, new showEnteredSuperSaveDialog.1(this));
            }
            OplusSuperSaveCountDownDialog oplusSuperSaveCountDownDialog = this.enteredSuperSaveDialog;
            if (oplusSuperSaveCountDownDialog != null) {
                oplusSuperSaveCountDownDialog.show(string, string2);
            }
            OplusSuperSaveEnteredNotification oplusSuperSaveEnteredNotification = this.enteredSuperSaveNotification;
            if (oplusSuperSaveEnteredNotification != null) {
                oplusSuperSaveEnteredNotification.hide();
            }
            LowBatteryUiController.getInstance().dismissAll();
        }
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    @Override // com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveController
    public void onReceiverChange(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
            this.batteryLevel = intent.getIntExtra("level", 100);
            this.batteryStatus = intent.getIntExtra(ZoomDCSManager.STATUS, 1);
            int intExtra = intent.getIntExtra("plugged", 0);
            this.plugType = intExtra;
            autoEnterOrExitSuperSave(this.batteryLevel, intExtra != 0, false);
            return;
        }
        if (!Intrinsics.areEqual(action, "android.intent.action.ACTION_POWER_CONNECTED") && !Intrinsics.areEqual(action, "android.intent.action.ACTION_POWER_DISCONNECTED")) {
            if (Intrinsics.areEqual(action, "android.intent.action.PHONE_STATE")) {
                SystemUIThreadUtil.runOnWorkThread(new onPhoneStateChange.1(this));
            }
        } else if (Intrinsics.areEqual(action, "android.intent.action.ACTION_POWER_CONNECTED")) {
            OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer = this.enterSuperSaveTimerTask;
            if (oplusSuperSaveCountDownTimer != null) {
                oplusSuperSaveCountDownTimer.cancelTimer();
            }
            resetEnteredSuperSaveNotice();
        }
    }

    public void onUserChanged(int i, Context context) {
        super.onUserChanged(i, context);
        resetAutoSuperSaveAll();
        if (i == 0) {
            setSuperSaveState(0);
            autoEnterOrExitSuperSave(this.batteryLevel, this.plugType != 0, false);
        }
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public final void resetAutoSuperSaveAll() {
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer = this.enterSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer != null) {
            oplusSuperSaveCountDownTimer.cancelTimer();
        }
        OplusSuperSaveCountDownTimer oplusSuperSaveCountDownTimer2 = this.exitSuperSaveTimerTask;
        if (oplusSuperSaveCountDownTimer2 != null) {
            oplusSuperSaveCountDownTimer2.cancelTimer();
        }
        resetEnteredSuperSaveNotice();
    }

    public final void resetEnteredSuperSaveNotice() {
        OplusSuperSaveCountDownDialog oplusSuperSaveCountDownDialog = this.enteredSuperSaveDialog;
        if (oplusSuperSaveCountDownDialog != null) {
            oplusSuperSaveCountDownDialog.hide();
        }
        OplusSuperSaveEnteredNotification oplusSuperSaveEnteredNotification = this.enteredSuperSaveNotification;
        if (oplusSuperSaveEnteredNotification != null) {
            oplusSuperSaveEnteredNotification.hide();
        }
    }

    public final void setSuperSaveState(int i) {
        this.superSaveFlag = i;
        if (i == 1) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "setSuperSaveState enterSuperSaveMode", null, 8);
            this.superSaveMode.enterSuperSaveMode();
            return;
        }
        if (i == 2 || i == 3) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "setSuperSaveState reset superSaveModleFlag state: ", " ", "SuperSaveControllerImpl-->", "SystemUi--Common");
            }
            this.superSaveMode.resetAutoEnterSupperSavePowerModeFlag();
            return;
        }
        if (i != 4) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "setSuperSaveState exitSuperSaveMode", null, 8);
        this.superSaveMode.exitSuperSaveMode();
    }

    public final void showEnterSuperSaveDialog(int i) {
        String string = this.context.getString(2131955287);
        String quantityString = this.context.getResources().getQuantityString(2131820573, i, Integer.valueOf(i));
        if (this.enterSuperSaveDialog == null) {
            this.enterSuperSaveDialog = new OplusSuperSaveCountDownDialog(this.context, new showEnterSuperSaveDialog.1(this));
        }
        if (this.subDisplaySuperSaveCountDownDialog == null) {
            if (OplusSubDisplayUtils.Companion.getInstance(this.context).getSupportSubDisplay()) {
                this.subDisplaySuperSaveCountDownDialog = new OplusSubDisplaySuperSaveCountDownDialog(this.context, new showEnterSuperSaveDialog.2(this));
            }
        }
        OplusSuperSaveCountDownDialog oplusSuperSaveCountDownDialog = this.enterSuperSaveDialog;
        if (oplusSuperSaveCountDownDialog != null) {
            oplusSuperSaveCountDownDialog.show(string, quantityString);
        }
        OplusSubDisplaySuperSaveCountDownDialog oplusSubDisplaySuperSaveCountDownDialog = this.subDisplaySuperSaveCountDownDialog;
        if (oplusSubDisplaySuperSaveCountDownDialog != null) {
            oplusSubDisplaySuperSaveCountDownDialog.show(i);
        }
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
