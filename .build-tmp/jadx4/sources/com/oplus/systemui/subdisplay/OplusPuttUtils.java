package com.oplus.systemui.subdisplay;

import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.devicestate.DeviceStateRequest;
import android.os.RemoteException;
import com.android.keyguard.OplusBlackScreenGestureControllEx;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.putt.IPuttObserver;
import com.oplus.putt.OplusPuttEnterInfo;
import com.oplus.putt.OplusPuttExitInfo;
import com.oplus.putt.OplusPuttManager;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPuttUtils {
    public static final OplusPuttUtils INSTANCE = new OplusPuttUtils();
    private static final String TAG = "OnePuttUtils";
    public static volatile boolean inPuttMode;
    public static boolean isRegister;
    private static OplusPuttManager puttManager;
    private static IPuttObserver puttObserver;
    public static boolean screenOn;

    static {
        try {
            puttManager = OplusPuttManager.getInstance();
            puttObserver = new IPuttObserver.Stub() { // from class: com.oplus.systemui.subdisplay.OplusPuttUtils.1
                public void onPuttEnter(OplusPuttEnterInfo oplusPuttEnterInfo) {
                    KgdLogUtil.i(OplusPuttUtils.TAG, "onPuttEnter");
                    OplusPuttUtils oplusPuttUtils = OplusPuttUtils.INSTANCE;
                    OplusPuttUtils.inPuttMode = true;
                    ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll().onEnableChanged(true);
                }

                public void onPuttExit(OplusPuttExitInfo oplusPuttExitInfo) {
                    KgdLogUtil.i(OplusPuttUtils.TAG, "onPuttExit");
                    OplusPuttUtils oplusPuttUtils = OplusPuttUtils.INSTANCE;
                    OplusPuttUtils.inPuttMode = false;
                    ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll().onEnableChanged(true);
                }

                public void onPuttReplace(OplusPuttEnterInfo oplusPuttEnterInfo, int i) {
                    KgdLogUtil.i(OplusPuttUtils.TAG, "onPuttReplace");
                }
            };
        } catch (NoClassDefFoundError unused) {
            KgdLogUtil.e(TAG, "NoClassDefFoundError");
        }
    }

    public static final synchronized boolean enableBlackGesture() {
        return puttManager != null && screenOn && inPuttMode;
    }

    public static final synchronized boolean hasEnterPuttApp() {
        boolean z;
        try {
            z = false;
            if (puttManager != null) {
                try {
                    if (inPuttMode) {
                        OplusPuttManager oplusPuttManager = puttManager;
                        List enterPuttAppInfos = oplusPuttManager != null ? oplusPuttManager.getEnterPuttAppInfos() : null;
                        KgdLogUtil.i(TAG, "enterPuttApps:" + (enterPuttAppInfos != null ? Integer.valueOf(enterPuttAppInfos.size()) : null));
                        inPuttMode = (enterPuttAppInfos != null ? enterPuttAppInfos.size() : 0) > 0;
                        z = inPuttMode;
                    }
                } catch (RemoteException unused) {
                    KgdLogUtil.e(TAG, "hasEnterPuttApp fail: RemoteException");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public static final synchronized void registerPuttObserver() {
        try {
            if (puttManager == null) {
                return;
            }
            try {
                screenOn = true;
                hasEnterPuttApp();
                if (inPuttMode) {
                    OplusBlackScreenGestureControllEx oplusBlackScreenGestureControll = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll();
                    oplusBlackScreenGestureControll.onEnableChanged(false);
                    oplusBlackScreenGestureControll.requestGestureMode(1, "OnePuttUtils#registerPuttObserver");
                }
                if (isRegister) {
                    return;
                }
                isRegister = true;
                OplusPuttManager oplusPuttManager = puttManager;
                if (oplusPuttManager != null) {
                    oplusPuttManager.registerPuttObserver(puttObserver);
                }
            } catch (RemoteException unused) {
                KgdLogUtil.e(TAG, "registerPuttObserver fail: RemoteException");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static final synchronized boolean supportBlackGestureInPutt() {
        return enableBlackGesture() && DeviceStateController.getInstance().isOpenOrHalfOpen();
    }

    public static final synchronized void unregisterPuttObserver() {
        try {
            if (puttManager == null) {
                return;
            }
            try {
                screenOn = false;
                if (inPuttMode) {
                    ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBlackScreenGestureControll().onEnableChanged(false);
                }
                if (!isRegister) {
                    return;
                }
                isRegister = false;
                OplusPuttManager oplusPuttManager = puttManager;
                if (oplusPuttManager != null) {
                    oplusPuttManager.unregisterPuttObserver(puttObserver);
                }
            } catch (RemoteException unused) {
                KgdLogUtil.e(TAG, "unregisterPuttObserver fail: RemoteException");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static final void wakeUpSubDisplay(Context context) {
        if (puttManager == null || ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
            return;
        }
        try {
            ((DeviceStateManager) context.getSystemService("device_state")).requestState(DeviceStateRequest.newBuilder(99).build(), (Executor) null, (DeviceStateRequest.Callback) null);
        } catch (RemoteException unused) {
            KgdLogUtil.e(TAG, "wakeUpSubDisplay fail: RemoteException");
        }
    }

    public final boolean getInPuttMode() {
        return inPuttMode;
    }
}
