package com.oplus.systemui.separate.guide;

import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.qs.GuideEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusQsGuideEventController this$0;

    public final void onKeyguardDismissAnimationFinished() {
        OplusQsGuideEventController oplusQsGuideEventController = this.this$0;
        GuideEvent guideEvent = GuideEvent.KEYGUARD_DISMISS;
        OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
        oplusQsGuideEventController.notifyGuideEvent(guideEvent);
    }
}
