package com.oplus.systemui.plugins.qs.guide;

import com.effects.lingguang.LingguangManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSEffectView$releaseEffect$1$1 implements Runnable {
    public /* synthetic */ LingguangManager $it;

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSEffectView", "releaseEffect, start ", null, 8);
        this.$it.release();
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSEffectView", "releaseEffect, end", null, 8);
    }
}
