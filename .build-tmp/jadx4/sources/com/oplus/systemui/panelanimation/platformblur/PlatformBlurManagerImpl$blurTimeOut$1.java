package com.oplus.systemui.panelanimation.platformblur;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlatformBlurManagerImpl$blurTimeOut$1 implements Runnable {
    public /* synthetic */ PlatformBlurManagerImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.getStatusBarStateController().getState() != 0) {
            LogUtil.i$default(LogUtil.TAG_COMMON, "PlatformBlurManagerImpl", "state is not shade", null, 8);
            return;
        }
        this.this$0.timeoutNeedPauseBlur = true;
        LogUtil.i$default(LogUtil.TAG_COMMON, "PlatformBlurManagerImpl", "blurTimeOut run", null, 8);
        this.this$0.updatePlatformBlurState("blur timeout");
    }
}
