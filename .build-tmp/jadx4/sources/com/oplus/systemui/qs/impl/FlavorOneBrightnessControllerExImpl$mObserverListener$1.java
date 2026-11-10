package com.oplus.systemui.qs.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.qs.observer.IncreaseBrightnessObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneBrightnessControllerExImpl$mObserverListener$1 implements IObserverListener {
    public /* synthetic */ FlavorOneBrightnessControllerExImpl this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onChange() called with: selfChange = ", "FlavorBrightnessController-->", "SystemUi--QuickSettings", z);
        }
        if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            setSliderValue();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(Thread.currentThread().getId(), "subThread switch to mainThread = ", "FlavorBrightnessController-->", "SystemUi--QuickSettings");
        }
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        onChange.3 r0 = new onChange.3();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        mainHandler.post(r0);
    }

    public final void setSliderValue() {
        FlavorOneBrightnessControllerExImpl flavorOneBrightnessControllerExImpl = this.this$0;
        if (flavorOneBrightnessControllerExImpl.isMultiBits) {
            IncreaseBrightnessObserver increaseBrightnessObserver = flavorOneBrightnessControllerExImpl.mIncreaseBrightnessObserver;
            Boolean boolValueOf = increaseBrightnessObserver != null ? Boolean.valueOf(increaseBrightnessObserver.isIncreaseBrightnessEnabled) : null;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "FlavorBrightnessController-->".concat("IncreaseBrightness: isOpen =" + boolValueOf + ", brightness=" + flavorOneBrightnessControllerExImpl.getBrightness()));
            }
            if (Intrinsics.areEqual(boolValueOf, Boolean.TRUE)) {
                IBrightnessSliderController iBrightnessSliderController = flavorOneBrightnessControllerExImpl.mController;
                if (iBrightnessSliderController != null) {
                    iBrightnessSliderController.setMax(flavorOneBrightnessControllerExImpl.getBrightnessMax() - flavorOneBrightnessControllerExImpl.getBrightnessMin());
                    return;
                }
                return;
            }
            IBrightnessSliderController iBrightnessSliderController2 = flavorOneBrightnessControllerExImpl.mController;
            if (iBrightnessSliderController2 != null) {
                iBrightnessSliderController2.setMax(flavorOneBrightnessControllerExImpl.getBrightnessSecMax() - flavorOneBrightnessControllerExImpl.getBrightnessMin());
            }
        }
    }
}
