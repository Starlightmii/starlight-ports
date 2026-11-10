package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPersonalBorderDrawable extends Drawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSPersonalBorderDrawable";
    private final GradientDrawable borderDrawable;
    private final Context context;
    public int leftRightBorderMargin;
    private final Lazy qsBounds$delegate = LazyKt__LazyJVMKt.lazy(new QSPersonalBorderDrawable$$ExternalSyntheticLambda0());
    public final int strokeWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSPersonalBorderDrawable(Context context, GradientDrawable gradientDrawable) {
        this.context = context;
        this.borderDrawable = gradientDrawable;
        this.strokeWidth = context.getResources().getDimensionPixelSize(2131171768);
    }

    private final Rect getQsBounds() {
        return (Rect) this.qsBounds$delegate.getValue();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.borderDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.borderDrawable.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        getQsBounds().set(rect);
        updateBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.borderDrawable.setAlpha(i);
        invalidateSelf();
    }

    public final void setBorderColor(int i) {
        this.borderDrawable.setStroke(this.strokeWidth, i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.borderDrawable.setColorFilter(colorFilter);
    }

    public final void updateBounds() {
        this.borderDrawable.setBounds(new Rect(getQsBounds().left + this.leftRightBorderMargin, getQsBounds().top, getQsBounds().right - this.leftRightBorderMargin, getQsBounds().bottom));
    }
}
