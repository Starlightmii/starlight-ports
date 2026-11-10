package com.oplus.systemui.statusbar.notification.customcard.guidetips;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomCardGuideTipsViewController$showGuideTips$$inlined$doOnLayout$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ CustomCardGuideTipsViewController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ViewTreeObserver viewTreeObserver;
        view.removeOnLayoutChangeListener(this);
        SeedlingGuideLightView seedlingGuideLightView = this.this$0.guideView;
        if (seedlingGuideLightView != null) {
            seedlingGuideLightView.setPosition(this.this$0.hotRect);
        }
        View view2 = this.this$0.customRowView;
        if (view2 != null && (viewTreeObserver = view2.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this.this$0.layoutChangeListener);
        }
        SeedlingGuideLightView seedlingGuideLightView2 = this.this$0.guideView;
        if (seedlingGuideLightView2 != null) {
            seedlingGuideLightView2.startAnimator();
        }
    }
}
