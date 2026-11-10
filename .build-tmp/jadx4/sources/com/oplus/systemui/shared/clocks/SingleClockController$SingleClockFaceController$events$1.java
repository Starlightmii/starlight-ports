package com.oplus.systemui.shared.clocks;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleClockController$SingleClockFaceController$events$1 extends OplusClockFaceEvents {
    public /* synthetic */ SingleClockController this$0;

    @Override // com.oplus.systemui.shared.clocks.OplusClockFaceEvents
    public final void onTimeTick() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "SingleClockController-->onTimeTick");
        }
        SingleClockView singleClockView = this.this$0.mLockClockContainer;
        if (singleClockView != null) {
            singleClockView.refresh(false);
        }
    }
}
