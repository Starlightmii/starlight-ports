package com.oplus.systemui.usb;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.hardware.usb.UsbPortStatus;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.statusbar.phone.OplusUsbDialogInfoProvider;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUsbUI implements CoreStartable {
    private static final String CAN_CHANGE_POWER_ROLE = "canChangePowerRole";
    private static final String PORT_STATUS = "portStatus";
    private static final String TAG = "OplusUsbUI";
    private static final String USB_DIALOG_INFO_PROVIDER_URI = "content://com.oplus.systemui.OplusUsbDialogInfoProvider";
    private static final String USB_PORT_CHANGED = "android.hardware.usb.action.USB_PORT_CHANGED";
    private static final String USB_VDM_DATA = "android.hardware.usb.action.OPLUS_USB_VDM_DATA";
    private static final String VALID_VDM_DATA = "0x44332211";
    private static final String VALID_VDM_DATA_PID = "0x00010007";
    private static final String VALID_VDM_DATA_VID = "0x000022d9";
    private static final String VDM_DATA = "vdmData";
    private final DelayableExecutor bgExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    private volatile Runnable mCancelRunnable;
    private final Context mContext;
    public volatile long mLastValidVdmDataTime;
    private final OplusUsbUI$mUsbDialogBlockedObserver$1 mUsbDialogBlockedObserver;
    private final OplusUsbUI$receiver$1 receiver;
    public static final Companion Companion = new Companion();
    private static final ComponentName SERVER_COMPONENT = new ComponentName("com.android.systemui", "com.oplus.systemui.usb.UsbService");
    private static final int[] SCENE_USB_RELATED = {108, 122, 131};
    private final AtomicBoolean mWaitingForVdmData = new AtomicBoolean(false);
    private final AtomicBoolean mReceivedVdmData = new AtomicBoolean(false);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ComponentName getSERVER_COMPONENT() {
            return OplusUsbUI.SERVER_COMPONENT;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.usb.OplusUsbUI$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusUsbUI this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mContext.getContentResolver().registerContentObserver(Uri.parse(OplusUsbUI.USB_DIALOG_INFO_PROVIDER_URI), false, this.this$0.mUsbDialogBlockedObserver);
                    break;
                default:
                    if (!OplusUsbBackend.getInstance(this.this$0.mContext).shouldBlockService()) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                            Log.d("SystemUi--Common", "OplusUsbUI-->onChange: block status cleared, starting cached service");
                        }
                        OplusUsbBackend oplusUsbBackend = OplusUsbBackend.getInstance(this.this$0.mContext);
                        if (oplusUsbBackend.mCachedServerIntent != null) {
                            oplusUsbBackend.mContext.startService(oplusUsbBackend.mCachedServerIntent);
                        }
                        OplusUsbBackend.getInstance(this.this$0.mContext).setIsServiceRunning(true);
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.oplus.systemui.usb.OplusUsbUI$receiver$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.oplus.systemui.usb.OplusUsbUI$mUsbDialogBlockedObserver$1] */
    public OplusUsbUI(Context context, BroadcastDispatcher broadcastDispatcher, DelayableExecutor delayableExecutor) {
        this.mContext = context;
        this.broadcastDispatcher = broadcastDispatcher;
        this.bgExecutor = delayableExecutor;
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mUsbDialogBlockedObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.usb.OplusUsbUI$mUsbDialogBlockedObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                ExecutorImpl executorImpl = this.this$0.bgExecutor;
                OplusUsbUI oplusUsbUI = this.this$0;
                OplusUsbUI.AnonymousClass1 anonymousClass1 = new OplusUsbUI.AnonymousClass1(1);
                anonymousClass1.this$0 = oplusUsbUI;
                VarHandle.storeStoreFence();
                executorImpl.execute(anonymousClass1);
            }
        };
        this.receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.usb.OplusUsbUI$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                LogUtil.i$default(LogUtil.TAG_COMMON, "OplusUsbUI", "receive: " + intent, null, 8);
                String action = intent.getAction();
                if (action != null) {
                    int iHashCode = action.hashCode();
                    if (iHashCode != 499242808) {
                        if (iHashCode == 1545922754 && action.equals("android.hardware.usb.action.OPLUS_USB_VDM_DATA")) {
                            FeatureOption.INSTANCE.getClass();
                            if (FeatureOption.isSupportGameBetopJoystick()) {
                                OplusUsbUI.access$handlerUsbVdmData(this.this$0, intent);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (action.equals("android.hardware.usb.action.USB_PORT_CHANGED")) {
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isSupportReverseCharging()) {
                            Intent intent2 = new Intent();
                            intent2.setComponent(OplusUsbUI.Companion.getSERVER_COMPONENT());
                            Context contextAccess$getCurrentUserContext = OplusUsbUI.access$getCurrentUserContext(this.this$0, context2);
                            if (contextAccess$getCurrentUserContext != null) {
                                OplusUsbUI.access$handlerUsbPortChange(this.this$0, contextAccess$getCurrentUserContext, intent, intent2);
                            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                                Log.d("SystemUi--Common", "OplusUsbUI-->handlerUsbPortChange userContext is null return");
                            }
                        }
                    }
                }
            }
        };
    }

    public static final Context access$getCurrentUserContext(OplusUsbUI oplusUsbUI, Context context) {
        try {
            Context contextCreatePackageContextAsUser = context.createPackageContextAsUser(context.getPackageName(), 0, new UserHandle(oplusUsbUI.getCurrentUserId()));
            contextCreatePackageContextAsUser.getClass();
            return contextCreatePackageContextAsUser;
        } catch (PackageManager.NameNotFoundException e) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                return null;
            }
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("Create package context as user exception: ", e.getMessage(), "OplusUsbUI-->", "SystemUi--Common");
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:57:0x010b  */
    public static final void access$handlerUsbPortChange(OplusUsbUI oplusUsbUI, Context context, Intent intent, Intent intent2) {
        if (context.getUserId() != oplusUsbUI.getCurrentUserId()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(context.getUserId(), oplusUsbUI.getCurrentUserId(), "Don't start the usb service when the user id of the current service is not the current system user id userId:", "  ,getCurrentUserId:"), null, 8);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra(CAN_CHANGE_POWER_ROLE, false);
        UsbPortStatus parcelableExtra = intent.getParcelableExtra(PORT_STATUS);
        OplusUsbBackend.getInstance(context).setCanChangePowerRole(booleanExtra);
        boolean z = (parcelableExtra != null ? parcelableExtra.getCurrentDataRole() : 0) == 1;
        boolean z2 = (parcelableExtra != null ? parcelableExtra.getCurrentDataRole() : 0) == 2;
        boolean isServiceRunning = OplusUsbBackend.getInstance(context).getIsServiceRunning();
        boolean z3 = OplusBatteryController.Companion.getInstance().getPlugged() == 1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("handlerUsbPortChange canChangePowerRole:", " ,isServiceRunning:", "  ,isAcConnected:", booleanExtra, isServiceRunning);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" ,isDataRoleHost:", " ,usbPortStatus:", sbM, z3, z);
            sbM.append(parcelableExtra);
            sbM.append(" ,isDataRoleDevice:");
            sbM.append(z2);
            Log.d("SystemUi--Common", "OplusUsbUI-->".concat(sbM.toString()));
        }
        if (parcelableExtra == null || !parcelableExtra.isConnected()) {
            if (OplusUsbBackend.getInstance(context).getServiceCurrentDataRole()) {
                OplusUsbBackend.getInstance(context).setIsServiceRunning(false);
                OplusUsbBackend.getInstance(context).mCachedServerIntent = null;
                OplusUsbDialogInfoProvider.resetCurrentBlockedValue();
                context.stopService(intent2);
            } else {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (FlavorOneFeatureOption.isFlavorOneDevice() && FlavorOneFeatureOption.sIsUsbPortSupport) {
                    OplusUsbBackend.getInstance(context).setIsServiceRunning(false);
                    OplusUsbBackend.getInstance(context).mCachedServerIntent = null;
                    OplusUsbDialogInfoProvider.resetCurrentBlockedValue();
                    context.stopService(intent2);
                }
            }
            oplusUsbUI.cancelPendingDelay();
            return;
        }
        intent2.putExtra(CAN_CHANGE_POWER_ROLE, booleanExtra);
        intent2.putExtra("currentPowerRole", parcelableExtra.getCurrentPowerRole());
        intent2.putExtra("currentDataRole", parcelableExtra.getCurrentDataRole());
        if (isServiceRunning) {
            intent2.putExtra("updatePowerState", true);
            context.startService(intent2);
            return;
        }
        if (z2 && booleanExtra && !z3) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice() && FlavorOneFeatureOption.sIsUsbPortSupport) {
                oplusUsbUI.startServiceWithChecks(context, intent2);
                return;
            }
        }
        if (z && booleanExtra && !z3) {
            oplusUsbUI.startServiceWithChecks(context, intent2);
        }
    }

    public static final void access$handlerUsbVdmData(OplusUsbUI oplusUsbUI, Intent intent) {
        String stringExtra = intent.getStringExtra(VDM_DATA);
        boolean z = false;
        if (stringExtra != null && stringExtra.length() != 0 && (StringsKt__StringsKt.contains(stringExtra, VALID_VDM_DATA, false) || (StringsKt__StringsKt.contains(stringExtra, VALID_VDM_DATA_PID, false) && StringsKt__StringsKt.contains(stringExtra, VALID_VDM_DATA_VID, false)))) {
            z = true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("handlerUsbVdmData: vdmData: ", stringExtra, ",isValidVdmData: ", z, "OplusUsbUI-->"));
        }
        if (z) {
            oplusUsbUI.mLastValidVdmDataTime = System.currentTimeMillis();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                long j = oplusUsbUI.mLastValidVdmDataTime;
                boolean z2 = oplusUsbUI.mWaitingForVdmData.get();
                StringBuilder sb = new StringBuilder("handlerUsbVdmData: record valid VDM_DATA time: ");
                sb.append(j);
                sb.append(", isWaitingForVdmData: ");
                sb.append(z2);
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(" ", "OplusUsbUI-->", "SystemUi--Common", sb);
            }
            if (oplusUsbUI.mWaitingForVdmData.get()) {
                oplusUsbUI.mReceivedVdmData.set(true);
                oplusUsbUI.cancelPendingDelay();
            }
        }
    }

    private final int getCurrentUserId() {
        return ActivityManagerWrapper.getInstance().getCurrentUserId();
    }

    public final void cancelPendingDelay() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OplusUsbUI-->cancelPendingDelay");
        }
        this.mWaitingForVdmData.set(false);
        this.mReceivedVdmData.set(true);
        Runnable runnable = this.mCancelRunnable;
        if (runnable != null) {
            runnable.run();
        }
        this.mCancelRunnable = null;
    }

    public final void start() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "OplusUsbUI  start  " + this.mContext, null, 8);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportReverseCharging()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(USB_PORT_CHANGED);
            if (FeatureOption.isSupportGameBetopJoystick()) {
                intentFilter.addAction(USB_VDM_DATA);
            }
            intentFilter.setPriority(100);
            BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, intentFilter, (Executor) null, UserHandle.ALL, 0, 48);
            ExecutorImpl executorImpl = this.bgExecutor;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            executorImpl.execute(anonymousClass1);
        }
    }

    public final void startServiceWithChecks(Context context, Intent intent) {
        if (OplusUsbBackend.getInstance(context).shouldBlockService()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusUsbUI-->startServiceWithChecks: caching service intent, should block");
            }
            OplusUsbBackend.getInstance(context).mCachedServerIntent = intent;
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isSupportGameBetopJoystick()) {
            OplusUsbBackend.getInstance(context).setIsServiceRunning(true);
            context.startService(intent);
            return;
        }
        if (System.currentTimeMillis() - this.mLastValidVdmDataTime < 300) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusUsbUI-->handleServiceStartWithDelay: recent valid VDM_DATA received, skip start UsbService");
            }
            cancelPendingDelay();
            return;
        }
        cancelPendingDelay();
        this.mReceivedVdmData.set(false);
        this.mWaitingForVdmData.set(true);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OplusUsbUI-->handleServiceStartWithDelay: start delay to wait for VDM_DATA");
        }
        DelayableExecutor delayableExecutor = this.bgExecutor;
        handleServiceStartWithDelay.3 r1 = new handleServiceStartWithDelay.3();
        r1.this$0 = this;
        r1.$context = context;
        r1.$serverIntent = intent;
        VarHandle.storeStoreFence();
        this.mCancelRunnable = delayableExecutor.executeDelayed(r1, 300L);
    }
}
