package com.oplusos.systemui.navigationbar;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.view.View;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.shared.statusbar.phone.BarTransitions;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBarBackgroundDrawable extends BarTransitions.BarBackgroundDrawable {
    public boolean isNavBarDrawable;

    public OplusBarBackgroundDrawable(Context context, int i, View view) {
        ((BarTransitions.BarBackgroundDrawable) this).mMode = -1;
        ((BarTransitions.BarBackgroundDrawable) this).mOverrideAlpha = 1.0f;
        ((BarTransitions.BarBackgroundDrawable) this).mPaint = new Paint();
        context.getResources();
        ((BarTransitions.BarBackgroundDrawable) this).mOpaqueDark = -16777216;
        ((BarTransitions.BarBackgroundDrawable) this).mOpaqueLight = -1;
        ((BarTransitions.BarBackgroundDrawable) this).mTranslucentOpaque = -16777216;
        ((BarTransitions.BarBackgroundDrawable) this).mSemiTransparent = context.getColor(17171478);
        boolean z = BarTransitions.DEBUG;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.colorError});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        ((BarTransitions.BarBackgroundDrawable) this).mWarning = color;
        ((BarTransitions.BarBackgroundDrawable) this).mGradient = context.getDrawable(i);
        if (view instanceof NavigationBarView) {
            ((BarTransitions.BarBackgroundDrawable) this).mOpaqueDark = context.getColor(2131102820);
            ((BarTransitions.BarBackgroundDrawable) this).mTranslucentOpaque = context.getColor(2131102821);
            this.isNavBarDrawable = true;
        }
    }

    public final void setNavBarDrawable(boolean z) {
        this.isNavBarDrawable = z;
    }

    public final void setOpaque(Context context) {
        ((BarTransitions.BarBackgroundDrawable) this).mOpaqueDark = context.getColor(2131102820);
        ((BarTransitions.BarBackgroundDrawable) this).mTranslucentOpaque = context.getColor(2131102821);
    }
}
