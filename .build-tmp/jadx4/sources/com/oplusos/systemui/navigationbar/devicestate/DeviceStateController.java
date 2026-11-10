package com.oplusos.systemui.navigationbar.devicestate;

import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeviceStateController {
    public static final DeviceStateController sInstance;
    public AnonymousClass1 mDeviceStateCallback;
    public int mFoldState;
    public int mLastState;
    public CopyOnWriteArrayList mStateChangedListeners;

    /* JADX INFO: renamed from: com.oplusos.systemui.navigationbar.devicestate.DeviceStateController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements DeviceStateManager.DeviceStateCallback {
        public /* synthetic */ DeviceStateController this$0;

        public final void onDeviceStateChanged(DeviceState deviceState) {
            DeviceStateController deviceStateController = this.this$0;
            deviceStateController.mLastState = deviceStateController.mFoldState;
            deviceStateController.mFoldState = deviceState.getIdentifier();
            OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(this.this$0.mFoldState, "DeviceStateController", new StringBuilder("foldstate changed : current state is :"));
            CopyOnWriteArrayList copyOnWriteArrayList = this.this$0.mStateChangedListeners;
            DeviceStateController$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new DeviceStateController$1$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            copyOnWriteArrayList.forEach(externalSyntheticLambda0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface StateChangedListener {
        void onStateChanged(int i);
    }

    static {
        DeviceStateController deviceStateController = new DeviceStateController();
        deviceStateController.mFoldState = -1;
        deviceStateController.mStateChangedListeners = new CopyOnWriteArrayList();
        deviceStateController.mLastState = -1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = deviceStateController;
        VarHandle.storeStoreFence();
        deviceStateController.mDeviceStateCallback = anonymousClass1;
        VarHandle.storeStoreFence();
        sInstance = deviceStateController;
    }

    public static DeviceStateController getInstance() {
        return sInstance;
    }

    public static boolean isLargeScreenInFoldDevice(int i, int i2) {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isFoldDevice() && i > 1600 && i2 > 1600;
    }

    public final void addCallback(StateChangedListener stateChangedListener) {
        if (this.mStateChangedListeners.contains(stateChangedListener)) {
            return;
        }
        this.mStateChangedListeners.add(stateChangedListener);
    }

    public int getLastState() {
        return this.mLastState;
    }

    public int getState() {
        return this.mFoldState;
    }

    public final boolean isFolding() {
        return this.mFoldState == 0;
    }

    public final boolean isFoldingState$1() {
        int i = this.mFoldState;
        return i == 0 || i == 101;
    }

    public final boolean isOpenOrHalfOpen() {
        int i = this.mFoldState;
        return i == 2 || i == 3 || i == 99;
    }

    public final void removeCallback(StateChangedListener stateChangedListener) {
        this.mStateChangedListeners.remove(stateChangedListener);
    }
}
