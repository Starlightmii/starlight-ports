package com.oplus.systemui.viewdialog.ui.viewmodel;

import com.android.systemui.animation.TransitionAnimator;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import kotlin.Pair;
import kotlin.Triple;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContentViewModel$animationManager$1 implements DetailAnimationManager.AnimationCallBack {
    public /* synthetic */ ViewDialogContentViewModel this$0;

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public final TransitionAnimator.Controller createEndController(Pair pair, Triple triple, boolean z) {
        ViewDialogAdapter viewDialogAdapter = this.this$0.adapter;
        DetailAnimationManager.AnimationCallBack animationCallBack = viewDialogAdapter instanceof DetailAnimationManager.AnimationCallBack ? (DetailAnimationManager.AnimationCallBack) viewDialogAdapter : null;
        if (animationCallBack != null) {
            return animationCallBack.createEndController(pair, triple, z);
        }
        return null;
    }

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public final TransitionAnimator.Controller createStartController(Pair pair, Triple triple, boolean z) {
        ViewDialogAdapter viewDialogAdapter = this.this$0.adapter;
        DetailAnimationManager.AnimationCallBack animationCallBack = viewDialogAdapter instanceof DetailAnimationManager.AnimationCallBack ? (DetailAnimationManager.AnimationCallBack) viewDialogAdapter : null;
        if (animationCallBack != null) {
            return animationCallBack.createStartController(pair, triple, z);
        }
        return null;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationEnd(boolean z, boolean z2) {
        ViewDialogContentViewModel viewDialogContentViewModel = this.this$0;
        ViewDialogContentViewModel.access$onDialogAnimationEnd(viewDialogContentViewModel, z, z2);
        viewDialogContentViewModel.adapter.onAnimationEnd(z, z2);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationStart(boolean z) {
        ViewDialogContentViewModel viewDialogContentViewModel = this.this$0;
        ViewDialogContentViewModel.access$onDialogAnimationStart(viewDialogContentViewModel, z);
        viewDialogContentViewModel.adapter.onAnimationStart(z);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationUpdate(float f) {
        this.this$0.setBehindViewAlpha(f);
    }
}
