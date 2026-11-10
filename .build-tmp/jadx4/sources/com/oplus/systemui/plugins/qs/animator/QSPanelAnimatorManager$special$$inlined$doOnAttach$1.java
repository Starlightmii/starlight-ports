package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import com.oplus.systemui.plugins.qs.OplusQSRootView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelAnimatorManager$special$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int $r8$classId = 0;
    public /* synthetic */ Object $this_doOnAttach;
    public /* synthetic */ QSPanelAnimatorManager this$0;

    public QSPanelAnimatorManager$special$$inlined$doOnAttach$1(OplusQSRootView oplusQSRootView, QSPanelAnimatorManager qSPanelAnimatorManager) {
        this.$this_doOnAttach = oplusQSRootView;
        this.this$0 = qSPanelAnimatorManager;
    }

    private final void onViewAttachedToWindow$com$oplus$systemui$plugins$qs$animator$QSPanelAnimatorManager$_init_$lambda$4$$inlined$doOnDetach$1(View view) {
    }

    private final void onViewDetachedFromWindow$com$oplus$systemui$plugins$qs$animator$QSPanelAnimatorManager$special$$inlined$doOnAttach$1(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                ((OplusQSRootView) this.$this_doOnAttach).removeOnAttachStateChangeListener(this);
                this.this$0.qsPanelExpandFraction.addCallback(this.this$0.qsElementShowingChangeListener);
                OplusQSRootView oplusQSRootView = this.this$0.qsRootView;
                if (!oplusQSRootView.isAttachedToWindow()) {
                    this.this$0.qsPanelExpandFraction.removeCallback(this.this$0.qsElementShowingChangeListener);
                    this.this$0.qsPanelExpandFraction.cancel();
                    this.this$0.getQsElementsChain().cancelSpring();
                } else {
                    oplusQSRootView.addOnAttachStateChangeListener(new QSPanelAnimatorManager$special$$inlined$doOnAttach$1(oplusQSRootView, this.this$0));
                }
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                break;
            default:
                ((View) this.$this_doOnAttach).removeOnAttachStateChangeListener(this);
                this.this$0.qsPanelExpandFraction.removeCallback(this.this$0.qsElementShowingChangeListener);
                this.this$0.qsPanelExpandFraction.cancel();
                this.this$0.getQsElementsChain().cancelSpring();
                break;
        }
    }

    public /* synthetic */ QSPanelAnimatorManager$special$$inlined$doOnAttach$1() {
    }
}
