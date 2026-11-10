package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import com.android.systemui.statusbar.notification.stack.AnimationProperties;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusAnimationProperties extends AnimationProperties {
    @Override // 
    public OplusAnimationFilter getAnimationFilter() {
        return new OplusAnimationFilter();
    }
}
