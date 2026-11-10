package com.oplus.systemui.qs.base.springtransition;

import com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableTransitionAlphaTarget implements IIntTarget {
    private final WeakReference<TileTransitionDrawable> drawableWeakRef;

    public DrawableTransitionAlphaTarget(TileTransitionDrawable tileTransitionDrawable) {
        this.drawableWeakRef = new WeakReference<>(tileTransitionDrawable);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public int get() {
        TileTransitionDrawable tileTransitionDrawable = this.drawableWeakRef.get();
        if (tileTransitionDrawable != null) {
            return tileTransitionDrawable.getTransitionAlpha();
        }
        return 255;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public void set(int i) {
        TileTransitionDrawable tileTransitionDrawable = this.drawableWeakRef.get();
        if (tileTransitionDrawable != null) {
            tileTransitionDrawable.setTransitionAlpha(i);
        }
    }
}
