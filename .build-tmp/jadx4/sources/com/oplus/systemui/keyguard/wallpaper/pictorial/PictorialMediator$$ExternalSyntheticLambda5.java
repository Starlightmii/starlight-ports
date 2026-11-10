package com.oplus.systemui.keyguard.wallpaper.pictorial;

import androidx.lifecycle.Observer;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda5 implements Observer {
    public /* synthetic */ PictorialMediator f$0;

    public final void onChanged(Object obj) {
        PictorialMediator pictorialMediator = this.f$0;
        PictorialMediator.AnonymousClass1 anonymousClass1 = pictorialMediator.mWaitingPictorialOvertimeRunnable;
        PictorialMediator.AnonymousClass3 anonymousClass3 = pictorialMediator.mHandler;
        if (((Boolean) obj).booleanValue() || !anonymousClass3.hasCallbacks(anonymousClass1)) {
            return;
        }
        KgdLogUtil.d("PictorialMediator", "waitingPictorialData trigger doLiveCheck");
        anonymousClass3.removeCallbacks(anonymousClass1);
        pictorialMediator.doLiveCheck();
    }
}
