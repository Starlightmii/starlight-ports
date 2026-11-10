package com.oplusos.systemui.common.view;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import com.oplus.graphics.OplusPathAdapter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmoothCornersGradientDrawable extends GradientDrawable {
    public static final Companion Companion = new Companion();
    public int colorValue;
    public boolean isAnimation;
    public boolean isDirty;
    private final OplusPathAdapter oplusPathAdapter;
    private final Paint paint;
    private final Path path;
    public float radius;
    public int roundType;
    public float weight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SmoothCornersGradientDrawable() {
        Paint paint = new Paint(1);
        paint.setDither(true);
        paint.setAntiAlias(true);
        this.paint = paint;
        Path path = new Path();
        this.path = path;
        this.oplusPathAdapter = new OplusPathAdapter(path, 1);
        this.isDirty = true;
        paint.setColor(this.colorValue);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.isAnimation) {
            super.draw(canvas);
            return;
        }
        canvas.save();
        if (this.isDirty) {
            this.isDirty = false;
            this.path.rewind();
            if (this.roundType == 1) {
                float fHeight = getBounds().height() / 2.0f;
                this.path.addRoundRect(new RectF(getBounds()), fHeight, fHeight, Path.Direction.CCW);
            } else if (this.radius == 0.0f) {
                this.path.addRect(new RectF(getBounds()), Path.Direction.CCW);
            } else {
                float f = this.weight;
                Paint paint = this.paint;
                if (f == 0.0f) {
                    float strokeWidth = paint.getStrokeWidth() / 2.0f;
                    Path path = this.path;
                    float f2 = getBounds().left + strokeWidth;
                    float f3 = getBounds().top + strokeWidth;
                    float f4 = getBounds().right - strokeWidth;
                    float f5 = getBounds().bottom - strokeWidth;
                    float f6 = this.radius;
                    path.addRoundRect(f2, f3, f4, f5, f6, f6, Path.Direction.CCW);
                } else {
                    float strokeWidth2 = paint.getStrokeWidth() / 2.0f;
                    OplusPathAdapter oplusPathAdapter = this.oplusPathAdapter;
                    float f7 = getBounds().left + strokeWidth2;
                    float f8 = getBounds().top + strokeWidth2;
                    float f9 = getBounds().right - strokeWidth2;
                    float f10 = getBounds().bottom - strokeWidth2;
                    float f11 = this.radius;
                    oplusPathAdapter.addSmoothRoundRect(f7, f8, f9, f10, f11, f11, this.isAnimation ? 1.0f : this.weight, Path.Direction.CCW);
                }
            }
        }
        this.paint.setColor(this.colorValue);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.GradientDrawable
    public ColorStateList getColor() {
        return ColorStateList.valueOf(this.colorValue);
    }

    public final float getRadius() {
        return this.radius;
    }

    public final int getRoundType() {
        return this.roundType;
    }

    public final float getWeight() {
        return this.weight;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.isDirty = true;
    }

    @Override // android.graphics.drawable.GradientDrawable
    public void setColor(int i) {
        if (this.colorValue != i) {
            this.colorValue = i;
            super.setColor(0);
        }
    }

    public final void setRoundCircleType(boolean z) {
        if (z != this.roundType) {
            this.isDirty = true;
            this.roundType = z ? 1 : 0;
            invalidateSelf();
        }
    }

    public final void setSmoothRound(float f, float f2) {
        if (this.radius == f && this.weight == f2) {
            return;
        }
        this.isDirty = true;
        this.radius = f;
        this.weight = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.GradientDrawable
    public final void setStroke(int i, int i2) {
        float f = i;
        if (f == this.paint.getStrokeWidth() && i2 == this.colorValue) {
            return;
        }
        this.paint.setStrokeWidth(f);
        Paint paint = this.paint;
        if (i > 0) {
            paint.setStyle(Paint.Style.STROKE);
        } else {
            paint.setStyle(Paint.Style.FILL);
        }
        setColor(i2);
        this.isDirty = true;
        invalidateSelf();
    }

    public final void updateAnimator(boolean z) {
        this.isAnimation = z;
        super.setColor(z ? this.colorValue : 0);
    }
}
