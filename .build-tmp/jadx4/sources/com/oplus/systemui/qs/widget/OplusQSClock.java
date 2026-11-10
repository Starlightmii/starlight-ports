package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.statusbar.policy.Clock;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSClock extends Clock implements SystemTypefaceHelper.SystemUIFontCallback {
    public int mPaddingEnd;

    public OplusQSClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaddingEnd = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final float calculateMinutesTextWidth() {
        int dimensionPixelSize = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172971);
        CharSequence text = getText();
        String string = text == null ? "" : text.toString();
        TextPaint paint = getPaint();
        paint.setTextSize(dimensionPixelSize);
        return paint.measureText(string);
    }

    public int getSecondsWidthExtraPadding() {
        return this.mPaddingEnd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        this.mPaddingEnd = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131171863);
        super.onAttachedToWindow();
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        SystemTypefaceHelper companion2 = companion.getInstance(((TextView) this).mContext);
        SystemTypefaceHelper companion3 = companion.getInstance(((TextView) this).mContext);
        Objects.requireNonNull(companion3);
        OplusQSClock$$ExternalSyntheticLambda0 oplusQSClock$$ExternalSyntheticLambda0 = new OplusQSClock$$ExternalSyntheticLambda0();
        oplusQSClock$$ExternalSyntheticLambda0.f$0 = companion3;
        VarHandle.storeStoreFence();
        companion2.addCallback(this, oplusQSClock$$ExternalSyntheticLambda0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onConfigurationChanged(Configuration configuration) {
        if (getResources().getConfiguration().fontScale != configuration.fontScale) {
            Resources resources = getResources();
            getResources();
            resources.updateConfiguration(configuration, Resources.getSystem().getDisplayMetrics());
        }
        setTextSize(0, ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172971));
        this.mPaddingEnd = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131171863);
        super.onConfigurationChanged(configuration);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SystemTypefaceHelper.Companion.getInstance(((TextView) this).mContext).removeCallback(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.widget.TextView*/.onFinishInflate();
        setTextSize(0, ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172971));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        if (((Clock) this).mShowSeconds) {
            super.onMeasure(i, i2);
        } else {
            updateMinWidth();
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(getMinimumWidth(), 1073741824), i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void reloadDimens() {
        setTextSize(0, ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172971));
        this.mPaddingEnd = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131171863);
        refreshWidth();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateMinWidth() {
        if (((Clock) this).mShowSeconds) {
            return;
        }
        int iCalculateMinutesTextWidth = (int) (calculateMinutesTextWidth() + this.mPaddingEnd);
        if (getMinimumWidth() != iCalculateMinutesTextWidth) {
            setMinimumWidth(iCalculateMinutesTextWidth);
        }
    }

    public OplusQSClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaddingEnd = 0;
    }
}
