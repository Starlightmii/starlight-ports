package b0;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends RippleDrawable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f392d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y0.u f393e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Integer f394f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f395g;

    public c0(boolean z2) {
        super(ColorStateList.valueOf(-16777216), null, z2 ? new ColorDrawable(-1) : null);
        this.f392d = z2;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public final Rect getDirtyBounds() {
        if (!this.f392d) {
            this.f395g = true;
        }
        Rect dirtyBounds = super.getDirtyBounds();
        this.f395g = false;
        return dirtyBounds;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean isProjected() {
        return this.f395g;
    }
}
