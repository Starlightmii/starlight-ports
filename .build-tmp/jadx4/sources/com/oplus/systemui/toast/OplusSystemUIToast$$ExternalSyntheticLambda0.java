package com.oplus.systemui.toast;

import android.animation.Animator;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusSystemUIToast$$ExternalSyntheticLambda0 implements Consumer {
    public /* synthetic */ OplusSystemUIToast f$0;
    public /* synthetic */ OplusToastLayout f$1;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        OplusSystemUIToast oplusSystemUIToast = this.f$0;
        OplusToastLayout oplusToastLayout = this.f$1;
        oplusSystemUIToast.getClass();
        if (((Integer) obj).intValue() == 0) {
            oplusToastLayout.setVisibilityChangedListener(null);
            Animator animator = oplusSystemUIToast.mInAnimator;
            if (animator != null) {
                animator.start();
            }
        }
    }
}
