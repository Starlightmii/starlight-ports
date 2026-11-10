package com.oplus.systemui.plugins.qs.customize.component;

import android.view.View;
import com.oplus.systemui.plugins.qs.CellAreaConfig;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSPersonalComponent this$0;

    public /* synthetic */ OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        switch (this.$r8$classId) {
            case 0:
                view.removeOnLayoutChangeListener(this);
                CellAreaConfig cellAreaConfig = this.this$0.cellAreaProvider.getCellAreaConfig();
                int measuredWidth = (((this.this$0.mView.getMeasuredWidth() != 0 ? this.this$0.mView.getMeasuredWidth() : cellAreaConfig.getWidth()) - (CellCalculatorManager.Companion.getInstance(this.this$0.getContext()).getCellSize(true).getCellWidth() * 2)) / 2) - cellAreaConfig.getEditALeftBorderMargin();
                QSPersonalBorderDrawable qsPersonalBorderDrawable = this.this$0.getQsPersonalBorderDrawable();
                qsPersonalBorderDrawable.leftRightBorderMargin = measuredWidth;
                qsPersonalBorderDrawable.updateBounds();
                qsPersonalBorderDrawable.invalidateSelf();
                break;
            case 1:
                view.removeOnLayoutChangeListener(this);
                IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener = this.this$0.nodeChangedListener;
                if (qSNodeChangeListener != null) {
                    ((QSPanelAnimatorManager.AnonymousClass1) qSNodeChangeListener).onNodeNumChanged();
                }
                break;
            default:
                view.removeOnLayoutChangeListener(this);
                IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener2 = this.this$0.nodeChangedListener;
                if (qSNodeChangeListener2 != null) {
                    ((QSPanelAnimatorManager.AnonymousClass1) qSNodeChangeListener2).onNodeNumChanged();
                }
                break;
        }
    }
}
