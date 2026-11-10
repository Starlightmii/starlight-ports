package com.oplus.systemui.qs.base.res.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TileTransitionDrawable extends TileLayerDrawable {
    private final Drawable backgroundDrawable;
    private final Drawable foregroundDrawable;
    public int transitionAlpha;

    public TileTransitionDrawable(Drawable drawable, Drawable drawable2) {
        super(new Drawable[]{drawable, drawable2});
        this.backgroundDrawable = drawable;
        this.foregroundDrawable = drawable2;
        this.transitionAlpha = 255;
        drawable.setAlpha(255);
        drawable2.setAlpha(0);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.transitionAlpha != 0) {
            this.backgroundDrawable.draw(canvas);
        }
        if (this.transitionAlpha != 255) {
            this.foregroundDrawable.draw(canvas);
        }
    }

    public final int getTransitionAlpha() {
        return this.transitionAlpha;
    }

    public final void setTransitionAlpha(int i) {
        if (this.transitionAlpha != i) {
            this.transitionAlpha = i;
            this.backgroundDrawable.setAlpha(i);
            this.foregroundDrawable.setAlpha(255 - i);
        }
    }
}
