package com.oplus.systemui.plugins.qs.guide;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualManager$guideAnimationCallback$1$onComplete$1 implements Runnable {
    public /* synthetic */ OplusQsGuideSpiritualManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualManager", "guide onComplete!!!!!", null, 8);
        this.this$0.releaseGuide();
    }
}
