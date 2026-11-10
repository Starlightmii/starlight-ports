package com.oplus.systemui.notification.lockscreen.immersive;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImmersiveBgContainer$childrenUpdater$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ OplusImmersiveBgContainer this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        OplusImmersiveBgContainer oplusImmersiveBgContainer = this.this$0;
        oplusImmersiveBgContainer.startAnimationToStateBarrier = false;
        OplusImmersiveBgContainer.access$updateChildren(oplusImmersiveBgContainer);
        OplusImmersiveBgContainer oplusImmersiveBgContainer2 = this.this$0;
        oplusImmersiveBgContainer2.childrenUpdateRequested = false;
        oplusImmersiveBgContainer2.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
