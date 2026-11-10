package com.oplus.systemui.qs.base.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AlphaOptimizedImageView extends ImageView {
    public AlphaOptimizedImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public AlphaOptimizedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlphaOptimizedImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AlphaOptimizedImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
