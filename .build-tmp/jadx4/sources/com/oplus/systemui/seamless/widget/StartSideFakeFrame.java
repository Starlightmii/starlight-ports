package com.oplus.systemui.seamless.widget;

import android.content.Context;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class StartSideFakeFrame extends StatusBarFakeFrame {
    public int parentWidth;
    public float targetScale;

    public StartSideFakeFrame(Context context) {
        super(context, null, 6, 0);
        this.targetScale = 1.0f;
    }

    @Override // com.oplus.systemui.seamless.widget.StatusBarFakeFrame
    public final void bindRealView() {
        super.bindRealView();
        updateStartSideScale();
    }

    public final void updateStartSideScale() {
        View mView;
        int i = this.parentWidth;
        if (i != 0 && (mView = getMView()) != null) {
            setPivotX(isLayoutRtl() ? (getWidth() + i) - mView.getRight() : -mView.getLeft());
        }
        setScaleX(this.targetScale);
        setScaleY(this.targetScale);
    }
}
