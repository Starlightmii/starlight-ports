package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.widget.LinearLayout;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.coui.appcompat.animation.COUIEaseInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialMessageView extends LinearLayout {
    public float mExtraAlpha;
    public float mInnerAlpha;

    public PictorialMessageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        new COUIEaseInterpolator();
        this.mExtraAlpha = 1.0f;
        this.mInnerAlpha = 1.0f;
    }

    private Float getBlendAlpha() {
        return Float.valueOf(this.mInnerAlpha * this.mExtraAlpha);
    }

    private void setInnerAlpha(Float f) {
        this.mInnerAlpha = MathUtils.constrain(f.floatValue(), 0.0f, 1.0f);
        setAlpha(this.mExtraAlpha);
    }

    public abstract void applyWallpaperChange(KeyguardWallpaperInfo keyguardWallpaperInfo, boolean z);

    @Override // android.view.View
    public void setAlpha(float f) {
        this.mExtraAlpha = f;
        super.setAlpha(getBlendAlpha().floatValue());
    }

    public void setOnFingprint(boolean z) {
    }

    public void setTipsWidth(int i) {
    }

    public PictorialMessageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PictorialMessageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public PictorialMessageView(Context context) {
        this(context, null);
    }
}
