package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StrokeDrawable extends Drawable implements IStrokeWidthModifier {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StrokeDrawable";
    public int color;
    public int colorAlpha;
    private final Context context;
    private final Lazy oplusPathAdapter$delegate;
    private final Lazy originBoundsF$delegate;
    private final Path path = new Path();
    public float radius;
    public float strokeWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$a_EsFO-ayFOgkgaaGMLdJAfGsa4, reason: not valid java name */
    public static OplusPathAdapter m167$r8$lambda$a_EsFOayFOgkgaaGMLdJAfGsa4(StrokeDrawable strokeDrawable) {
        return new OplusPathAdapter(strokeDrawable.path, 1);
    }

    public StrokeDrawable(Context context) {
        this.context = context;
        StrokeDrawable$$ExternalSyntheticLambda0 strokeDrawable$$ExternalSyntheticLambda0 = new StrokeDrawable$$ExternalSyntheticLambda0();
        strokeDrawable$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusPathAdapter$delegate = LazyKt__LazyJVMKt.lazy(strokeDrawable$$ExternalSyntheticLambda0);
        this.colorAlpha = 153;
        this.color = ColorUtils.setAlphaComponent(context.getResources().getColor(2131103212, null), this.colorAlpha);
        this.originBoundsF$delegate = LazyKt__LazyJVMKt.lazy(new StrokeDrawable$$ExternalSyntheticLambda1());
    }

    private final OplusPathAdapter getOplusPathAdapter() {
        return (OplusPathAdapter) this.oplusPathAdapter$delegate.getValue();
    }

    private final RectF getOriginBoundsF() {
        return (RectF) this.originBoundsF$delegate.getValue();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getAlpha() > 0.0f) {
            canvas.save();
            canvas.clipPath(this.path);
            canvas.drawColor(this.color);
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.colorAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final float getRadius() {
        return this.radius;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier
    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        getOriginBoundsF().set(rect);
        RectF rectF = new RectF(rect.left - getStrokeWidth(), rect.top - getStrokeWidth(), getStrokeWidth() + rect.right, getStrokeWidth() + rect.bottom);
        this.path.reset();
        getOplusPathAdapter().addSmoothRoundRect(rectF, getStrokeWidth() + this.radius, getStrokeWidth() + this.radius, Path.Direction.CCW);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i > 153) {
            i = 153;
        }
        this.colorAlpha = i;
        this.color = ColorUtils.setAlphaComponent(this.context.getResources().getColor(2131103212, null), this.colorAlpha);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public final void setRadius(float f) {
        if (this.radius == f) {
            return;
        }
        this.radius = f;
        this.path.reset();
        getOplusPathAdapter().addSmoothRoundRect(getOriginBoundsF().left - getStrokeWidth(), getOriginBoundsF().top - getStrokeWidth(), getStrokeWidth() + getOriginBoundsF().right, getStrokeWidth() + getOriginBoundsF().bottom, getStrokeWidth() + this.radius, getStrokeWidth() + this.radius, Path.Direction.CCW);
        invalidateSelf();
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IStrokeWidthModifier
    public void setStrokeWidth(float f) {
        if (this.strokeWidth == f) {
            return;
        }
        this.strokeWidth = f;
        this.path.reset();
        OplusPathAdapter oplusPathAdapter = getOplusPathAdapter();
        float f2 = getOriginBoundsF().left - this.strokeWidth;
        float f3 = getOriginBoundsF().top - this.strokeWidth;
        float f4 = this.strokeWidth + getOriginBoundsF().right;
        float f5 = getOriginBoundsF().bottom;
        float f6 = this.strokeWidth;
        float f7 = this.radius;
        oplusPathAdapter.addSmoothRoundRect(f2, f3, f4, f5 + f6, f7 + f6, f7 + f6, Path.Direction.CCW);
        invalidateSelf();
    }
}
