package com.oplus.systemui.preventmode;

import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeExImpl$mFoldStateListener$1 implements DeviceStateController.StateChangedListener {
    public /* synthetic */ OplusPreventModeExImpl this$0;

    @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
    public final void onStateChanged(int i) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "fold state: ", OplusPreventModeExImpl.TAG);
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        if (companion.getInstance(oplusPreventModeExImpl.context).getSupportSubDisplay()) {
            if (i != 0) {
                Handler mHandler = oplusPreventModeExImpl.getMHandler();
                if (mHandler != null) {
                    mHandler.removeMessages(2);
                }
                oplusPreventModeExImpl.setMCurrentResult(2);
                return;
            }
            oplusPreventModeExImpl.setMSubDisplayPreventModeEnable(true);
            Handler mHandler2 = oplusPreventModeExImpl.getMHandler();
            if (mHandler2 != null) {
                mHandler2.removeMessages(2);
            }
            Handler mHandler3 = oplusPreventModeExImpl.getMHandler();
            if (mHandler3 != null) {
                mHandler3.sendEmptyMessageDelayed(2, 500L);
            }
        }
    }
}
