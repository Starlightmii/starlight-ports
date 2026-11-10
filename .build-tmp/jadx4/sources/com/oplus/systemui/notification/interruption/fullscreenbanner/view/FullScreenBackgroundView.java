package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FullScreenBackgroundView extends View {
    public final Paint mBackgroundPaint;
    public final Context mContext;
    public float mCornerRadius;
    public Drawable mIcon;
    public float mIconAlpha;
    public final int mIconHeight;
    public final Rect mIconRect;
    public final int mIconWidth;
    public final RectF mRectF;

    public FullScreenBackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCornerRadius = 0.0f;
        this.mContext = context;
        Paint paint = new Paint(1);
        this.mBackgroundPaint = paint;
        paint.setColor(context.getColor(2131102886));
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        new Paint(1).setFilterBitmap(true);
        this.mIconRect = new Rect();
        this.mRectF = new RectF();
        this.mIconWidth = context.getResources().getDimensionPixelSize(2131170809);
        this.mIconHeight = context.getResources().getDimensionPixelSize(2131170808);
        setVisibility(4);
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.mCornerRadius;
        if (f > 0.0f) {
            canvas.drawRoundRect(this.mRectF, f, f, this.mBackgroundPaint);
        }
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            drawable.setBounds(this.mIconRect);
            this.mIcon.setAlpha(Math.round(this.mIconAlpha * 255.0f));
            this.mIcon.draw(canvas);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.mIcon != null) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE || mode == 0) {
                size = Math.min(size, this.mIconWidth);
            }
            if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
                size2 = Math.min(size2, this.mIconHeight);
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateIconRect();
    }

    public void setCornerRadius(float f) {
        if (f != this.mCornerRadius) {
            this.mCornerRadius = f;
        }
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        invalidate();
    }

    public void setIconAlpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            return;
        }
        this.mIconAlpha = f;
    }

    public final void updateIconRect() {
        if (this.mIcon == null) {
            return;
        }
        int iRound = Math.round(this.mRectF.centerX());
        int iRound2 = Math.round(this.mRectF.centerY());
        int i = this.mIconWidth;
        int i2 = i / 2;
        int i3 = this.mIconHeight;
        int i4 = i3 / 2;
        this.mIconRect.set(iRound - i2, iRound2 - i4, (i - i2) + iRound, (i3 - i4) + iRound2);
    }

    public FullScreenBackgroundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FullScreenBackgroundView(Context context) {
        this(context, null);
    }
}
