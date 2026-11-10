package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbsOplusItemAnimator {
    public abstract OplusItemSpringAnimationHolder createAddAnimationHolder(View view);

    public abstract OplusItemSpringAnimationHolder createChangeAnimationHolder(View view);

    public abstract OplusItemSpringAnimationHolder createMoveAnimationHolder(View view, boolean z, boolean z2, int i, int i2);

    public abstract OplusItemSpringAnimationHolder createRemoveAnimationHolder(View view);
}
