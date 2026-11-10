package com.oplus.systemui.plugins.qs.animator;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelTranslationY$special$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int $r8$classId = 0;
    public /* synthetic */ View $this_doOnAttach;
    public /* synthetic */ QSPanelTranslationY this$0;

    public QSPanelTranslationY$special$$inlined$doOnAttach$1(View view, QSPanelTranslationY qSPanelTranslationY) {
        this.$this_doOnAttach = view;
        this.this$0 = qSPanelTranslationY;
    }

    private final void onViewAttachedToWindow$com$oplus$systemui$plugins$qs$animator$QSPanelTranslationY$_init_$lambda$3$$inlined$doOnDetach$1(View view) {
    }

    private final void onViewDetachedFromWindow$com$oplus$systemui$plugins$qs$animator$QSPanelTranslationY$special$$inlined$doOnAttach$1(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                QSPanelTranslationY qSPanelTranslationY = this.this$0;
                qSPanelTranslationY.trySetCurrValue(-qSPanelTranslationY.destTranslationY);
                View view2 = this.this$0.qsPanelView;
                if (!view2.isAttachedToWindow()) {
                    this.this$0.getTranslationYAnimation().cancelComplete();
                } else {
                    view2.addOnAttachStateChangeListener(new QSPanelTranslationY$special$$inlined$doOnAttach$1(view2, this.this$0));
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
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                this.this$0.getTranslationYAnimation().cancelComplete();
                break;
        }
    }

    public /* synthetic */ QSPanelTranslationY$special$$inlined$doOnAttach$1() {
    }
}
