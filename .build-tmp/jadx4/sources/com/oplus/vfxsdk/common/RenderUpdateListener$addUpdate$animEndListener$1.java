package com.oplus.vfxsdk.common;

import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderUpdateListener$addUpdate$animEndListener$1 implements AnimEndListener {
    public /* synthetic */ Animator $listener;
    public /* synthetic */ RenderUpdateListener this$0;

    @Override // com.oplus.vfxsdk.common.AnimEndListener
    public final void onEnd() {
        RenderUpdateListener renderUpdateListener = this.this$0;
        String str = renderUpdateListener.TAG;
        Animator animator = this.$listener;
        Log.d(str, "AnimEndListener onEnd: " + animator.getData().getName() + " end");
        renderUpdateListener.getPlayingAnim().remove(animator);
        renderUpdateListener.getAnimEndListenerMap().remove(animator.getData().getName());
        animator.removeAnimEndListener(this);
    }
}
