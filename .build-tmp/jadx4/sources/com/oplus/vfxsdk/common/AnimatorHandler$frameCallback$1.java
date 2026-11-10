package com.oplus.vfxsdk.common;

import android.view.Choreographer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorHandler$frameCallback$1 implements Choreographer.FrameCallback {
    public /* synthetic */ AnimatorHandler this$0;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AnimatorHandler animatorHandler = this.this$0;
        animatorHandler.onDrawFrame(jCurrentTimeMillis - animatorHandler.lastTime);
        if (this.this$0.playing) {
            Choreographer.getInstance().postFrameCallback(this);
        }
    }
}
