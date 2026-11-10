package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CircleBatteryContentDrawable extends BatteryContentDrawableBase {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CircleBatteryContentDrawable";
    private Rect chargeIconRect;
    private Drawable chargingDrawable;
    public int chargingDrawableHeight;
    public int chargingDrawableWidth;
    private final Paint circleFramePaint;
    private RectF circleRect;
    private final Context context;
    private RectF inCircleRect;
    public int lastChargeIconId;
    public int lastHeight;
    public int lastWidth;
    private final Paint mCircleBackPaint;
    private final Paint mCircleChargingPaint;
    private final Paint mCircleFrontPaint;
    private RectF outCircleRect;

    public CircleBatteryContentDrawable(Context context, int i) {
        super(context, i);
        this.context = context;
        this.circleRect = new RectF();
        this.chargeIconRect = new Rect();
        this.inCircleRect = new RectF();
        this.outCircleRect = new RectF();
        Paint paint = new Paint(1);
        this.mCircleBackPaint = paint;
        Paint paint2 = new Paint(1);
        this.mCircleFrontPaint = paint2;
        Paint paint3 = new Paint(1);
        this.mCircleChargingPaint = paint3;
        Paint paint4 = new Paint(1);
        this.circleFramePaint = paint4;
        paint.setColor(i);
        Paint.Cap cap = Paint.Cap.BUTT;
        paint.setStrokeCap(cap);
        paint.setDither(true);
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        paint2.setStrokeCap(cap);
        paint2.setDither(true);
        paint2.setStyle(style);
        paint3.setStyle(Paint.Style.FILL);
        paint4.setStrokeCap(cap);
        paint4.setDither(true);
        paint4.setStyle(style);
        paint4.setStrokeWidth(1.0f);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBatteryLevel() == -1) {
            return;
        }
        canvas.drawArc(this.circleRect, 270.0f, (getBatteryLevel() * 3.6f) - 360.0f, false, this.mCircleBackPaint);
        canvas.drawArc(this.circleRect, 270.0f, getBatteryLevel() * 3.6f, false, this.mCircleFrontPaint);
        if (getSpecialColor()) {
            canvas.drawArc(this.inCircleRect, 0.0f, 360.0f, false, this.circleFramePaint);
            canvas.drawArc(this.outCircleRect, 0.0f, 360.0f, false, this.circleFramePaint);
        }
        if (getChargeIconId() > 0) {
            canvas.save();
            if (isRTLLayout()) {
                Rect rect = this.chargeIconRect;
                float fWidth = (rect.width() / 2) + rect.left;
                Rect rect2 = this.chargeIconRect;
                canvas.scale(-1.0f, 1.0f, fWidth, (rect2.height() / 2) + rect2.top);
            }
            Drawable drawable = this.chargingDrawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            canvas.restore();
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public void dump(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.println(TAG);
        }
        if (printWriter != null) {
            int i = this.lastWidth;
            int i2 = this.lastHeight;
            RectF rectF = this.circleRect;
            Rect rect = this.chargeIconRect;
            RectF rectF2 = this.inCircleRect;
            RectF rectF3 = this.outCircleRect;
            int i3 = this.chargingDrawableHeight;
            int i4 = this.chargingDrawableWidth;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m(" width:", i, i2, " height:", " circleRect:");
            sbM.append(rectF);
            sbM.append(" chargeIconRect:");
            sbM.append(rect);
            sbM.append(" inCircleRect:");
            sbM.append(rectF2);
            sbM.append(" outCircleRect:");
            sbM.append(rectF3);
            sbM.append(" chargingDrawableHeight:");
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(i3, i4, " chargingDrawableWidth:", " ", sbM));
        }
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        int i5 = i4 - i2;
        int i6 = i3 - i;
        if (this.lastHeight != i5 || this.lastWidth != i6) {
            this.lastHeight = i5;
            this.lastWidth = i6;
            postInvalidate.1 r4 = new postInvalidate.1();
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            scheduleSelf(r4, 20L);
        }
        float dimension = this.context.getResources().getDimension(2131172985);
        this.mCircleBackPaint.setStrokeWidth(dimension);
        this.mCircleFrontPaint.setStrokeWidth(dimension);
        setPaddingLeft(this.context.getResources().getDimension(2131172982));
        setPaddingTop(this.context.getResources().getDimension(2131172984));
        setPaddingRight(this.context.getResources().getDimension(2131172983));
        setPaddingBottom(this.context.getResources().getDimension(2131172981));
        this.context.getResources().getDimension(2131173022);
        this.context.getResources().getDimension(2131173021);
        Rect bounds = getBounds();
        float f = dimension / 2.0f;
        this.circleRect.left = getPaddingLeft() + bounds.left + f;
        this.circleRect.top = getPaddingTop() + bounds.top + f;
        this.circleRect.right = (bounds.right - f) - getPaddingRight();
        this.circleRect.bottom = (bounds.bottom - f) - getPaddingBottom();
        updateChargingIconRect();
        float strokeWidth = this.circleFramePaint.getStrokeWidth() / 2.0f;
        this.inCircleRect.left = (getPaddingLeft() + (bounds.left + dimension)) - strokeWidth;
        this.inCircleRect.right = ((bounds.right - getPaddingRight()) - dimension) + strokeWidth;
        this.inCircleRect.top = ((getPaddingTop() + bounds.top) + dimension) - strokeWidth;
        this.inCircleRect.bottom = ((bounds.bottom - getPaddingBottom()) - dimension) + strokeWidth;
        this.outCircleRect.left = getPaddingLeft() + bounds.left + strokeWidth;
        this.outCircleRect.right = (bounds.right - getPaddingRight()) - strokeWidth;
        this.outCircleRect.top = getPaddingTop() + bounds.top + strokeWidth;
        this.outCircleRect.bottom = (bounds.bottom - getPaddingBottom()) - strokeWidth;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public final void setColors(int i, int i2, int i3) {
        if (this.chargingDrawable != null) {
            int iArgb = Color.argb(230, Color.red(i3), Color.green(i3), Color.blue(i3));
            Drawable drawable = this.chargingDrawable;
            if (drawable != null) {
                drawable.setTintMode(PorterDuff.Mode.SRC_ATOP);
            }
            Drawable drawable2 = this.chargingDrawable;
            if (drawable2 != null) {
                drawable2.setTintList(ColorStateList.valueOf(iArgb));
            }
        }
        int iArgb2 = Color.argb(77, Color.red(i3), Color.green(i3), Color.blue(i3));
        this.mCircleBackPaint.setColor(i2);
        this.mCircleFrontPaint.setColor(i);
        this.mCircleChargingPaint.setColor(i3);
        this.circleFramePaint.setColor(iArgb2);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase
    public final void updateChargeIcon(int i) {
        if (i <= 0 || this.lastChargeIconId == i) {
            return;
        }
        this.lastChargeIconId = i;
        Drawable drawable = this.context.getDrawable(i);
        this.chargingDrawable = drawable;
        this.chargingDrawableWidth = drawable != null ? drawable.getIntrinsicWidth() : 0;
        Drawable drawable2 = this.chargingDrawable;
        this.chargingDrawableHeight = drawable2 != null ? drawable2.getIntrinsicHeight() : 0;
        updateChargingIconRect();
    }

    public final void updateChargingIconRect() {
        Drawable drawable;
        float fHeight = (this.circleRect.height() - this.chargingDrawableHeight) / 2.0f;
        float fWidth = (this.circleRect.width() - this.chargingDrawableWidth) / 2.0f;
        Rect rect = this.chargeIconRect;
        RectF rectF = this.circleRect;
        rect.left = (int) (rectF.left + fWidth);
        rect.top = (int) (rectF.top + fHeight);
        rect.right = (int) (rectF.right - fWidth);
        rect.bottom = (int) (rectF.bottom - fHeight);
        if (rect.width() <= 0 || this.chargeIconRect.height() <= 0 || (drawable = this.chargingDrawable) == null) {
            return;
        }
        drawable.setBounds(this.chargeIconRect);
    }
}
