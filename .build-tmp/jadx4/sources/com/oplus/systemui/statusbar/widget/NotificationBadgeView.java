package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hidl.base.V1_0.DebugInfo$;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.ILocationCalculatorKt;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.util.StatusBarHelper;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationBadgeView extends View implements DarkIconDispatcher.DarkReceiver, HiddenIconDispatcher.HiddenReceiver {
    public final Paint mBgPaint;
    public float mCircleRadius;
    public int mDensityDpi;
    public int mHeight;
    public final ArrayList mHiddenAreas;
    public Locale mLocale;
    public final RectF mRectF;
    public float mRoundRectHeight;
    public float mRoundRectWith;
    public boolean mShouldScale;
    public boolean mShowMaxNumber;
    public boolean mShowTwoDigits;
    public final StatusBarHelper mStatusBarHelper;
    public String mText;
    public int mTextNum;
    public float mTextOffsetX;
    public float mTextOffsetY;
    public final TextPaint mTextPaint;
    public int mTwoDigitsWidth;
    public int mWidth;

    public NotificationBadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mText = NumberFormat.getIntegerInstance().format(1L);
        this.mHiddenAreas = new ArrayList();
        this.mDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
        this.mStatusBarHelper = StatusBarHelper.Companion.getInstance(context);
        Paint paint = new Paint();
        this.mBgPaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = this.mBgPaint;
        Paint.Style style = Paint.Style.FILL;
        paint2.setStyle(style);
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mTextPaint.setStyle(style);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.mTextPaint.setFakeBoldText(true);
        this.mRectF = new RectF();
        reloadDimens$2();
        Resources resources = getResources();
        int i2 = this.mTextNum;
        setContentDescription(resources.getQuantityString(2131820564, i2, Integer.valueOf(i2)));
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println(getClass().getSimpleName().concat("state: "));
        printWriter.println("\thiddenArea=" + this.mHiddenAreas);
    }

    public void getCurrentLocale() {
        this.mLocale = ((View) this).mContext.getResources().getConfiguration().locale;
    }

    public final float getTextY(Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        return (((getMeasuredHeight() - fontMetricsInt.ascent) - fontMetricsInt.descent) / 2.0f) + (this.mStatusBarHelper.isDefaultTypeface() ? this.mTextOffsetY : 0.0f);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = ((View) this).mContext.getResources().getDisplayMetrics().densityDpi;
        if (this.mDensityDpi != i) {
            this.mDensityDpi = i;
            reloadDimens$2();
            requestLayout();
        }
        Locale locale = configuration.locale;
        Locale locale2 = this.mLocale;
        if (locale2 == null || locale2.equals(locale)) {
            return;
        }
        setText(this.mTextNum);
    }

    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        this.mBgPaint.setColor(i);
        invalidate();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        if (this.mShowMaxNumber) {
            float f = this.mRoundRectWith;
            float f2 = this.mRoundRectHeight;
            RectF rectF = this.mRectF;
            rectF.left = 0.0f;
            rectF.top = 0.0f;
            rectF.right = f;
            rectF.bottom = f2;
            canvas.save();
            float f3 = this.mRoundRectHeight * 0.5f;
            canvas.drawRoundRect(this.mRectF, f3, f3, this.mBgPaint);
            canvas.restore();
            canvas.save();
            canvas.drawText(DebugInfo$.ExternalSyntheticOutline0.m(new StringBuilder(), this.mText, "+"), this.mRoundRectWith * 0.5f, getTextY(this.mTextPaint), this.mTextPaint);
            canvas.restore();
        } else if (this.mShowTwoDigits) {
            float f4 = this.mWidth;
            float f5 = this.mRoundRectHeight;
            RectF rectF2 = this.mRectF;
            rectF2.left = 0.0f;
            rectF2.top = 0.0f;
            rectF2.right = f4;
            rectF2.bottom = f5;
            canvas.save();
            float f6 = this.mRoundRectHeight * 0.5f;
            canvas.drawRoundRect(this.mRectF, f6, f6, this.mBgPaint);
            canvas.restore();
            canvas.save();
            canvas.drawText(this.mText, (this.mWidth * 0.5f) - this.mTextOffsetX, getTextY(this.mTextPaint), this.mTextPaint);
            canvas.restore();
        } else {
            canvas.save();
            float f7 = this.mCircleRadius;
            canvas.drawCircle(f7, f7, f7, this.mBgPaint);
            canvas.restore();
            canvas.save();
            canvas.drawText(this.mText, this.mCircleRadius - this.mTextOffsetX, getTextY(this.mTextPaint), this.mTextPaint);
            canvas.restore();
        }
        canvas.restoreToCount(iSaveLayer);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public final void onHiddenChanged(ArrayList arrayList, boolean z) {
        this.mHiddenAreas.clear();
        this.mHiddenAreas.addAll(arrayList);
        this.mShouldScale = z;
        IScaledAncestorView scaledAncestorViewOrNull = ILocationCalculatorKt.getScaledAncestorViewOrNull(this);
        Pair<Integer, Integer> locationByScaledAncestorView = scaledAncestorViewOrNull != null ? ILocationCalculatorKt.getLocationByScaledAncestorView(this, scaledAncestorViewOrNull.getScaledAncestorView(), scaledAncestorViewOrNull.getScaleFraction(z), scaledAncestorViewOrNull.getPivotXDirection()) : ILocationCalculatorKt.getLocationByDefault(this);
        setAlpha(locationByScaledAncestorView != null ? HiddenIconDispatcher.Companion.isInAreas(arrayList, locationByScaledAncestorView) : false ? 0.0f : 1.0f);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (i3 == i || i2 == i4) {
            return;
        }
        onHiddenChanged(new ArrayList(this.mHiddenAreas), this.mShouldScale);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.mShowMaxNumber) {
            this.mWidth = (int) this.mRoundRectWith;
            this.mHeight = (int) this.mRoundRectHeight;
        } else if (this.mShowTwoDigits) {
            this.mWidth = this.mTwoDigitsWidth;
            this.mHeight = (int) this.mRoundRectHeight;
        } else {
            int i3 = (int) (this.mCircleRadius * 2.0f);
            this.mHeight = i3;
            this.mWidth = i3;
        }
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    public final void reloadDimens$2() {
        Resources resources = getResources();
        float dimension = resources.getDimension(2131173080);
        float dimension2 = resources.getDimension(2131173075);
        this.mCircleRadius = dimension2;
        this.mCircleRadius = (float) Math.floor(dimension2);
        this.mTwoDigitsWidth = resources.getDimensionPixelSize(2131173084);
        this.mRoundRectWith = resources.getDimension(2131173081);
        this.mTextOffsetX = resources.getDimension(2131173082);
        this.mTextOffsetY = resources.getDimension(2131173083);
        this.mRoundRectHeight = this.mCircleRadius * 2.0f;
        this.mTextPaint.setTextSize(dimension);
    }

    public void setFontTypeface(Typeface typeface) {
        this.mTextPaint.setTypeface(typeface);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }

    public void setText(int i) {
        if (i < 1) {
            setVisibility(8);
        }
        if (i > 99) {
            this.mShowMaxNumber = true;
            this.mShowTwoDigits = false;
            this.mText = NumberFormat.getIntegerInstance().format(99L);
        } else {
            this.mShowTwoDigits = i > 9;
            this.mShowMaxNumber = false;
            this.mText = NumberFormat.getIntegerInstance().format(i);
        }
        if (i > 0 && this.mTextNum != i) {
            requestLayout();
            invalidate();
        }
        this.mTextNum = i;
        Resources resources = getResources();
        int i2 = this.mTextNum;
        setContentDescription(resources.getQuantityString(2131820564, i2, Integer.valueOf(i2)));
    }

    public NotificationBadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NotificationBadgeView(Context context) {
        this(context, null);
    }
}
