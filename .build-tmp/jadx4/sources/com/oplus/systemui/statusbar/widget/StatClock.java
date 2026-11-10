package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Dumpable;
import android.view.View;
import android.widget.TextView;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.broadcast.ActionReceiver$;
import com.android.systemui.res.R;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.util.ColorUtilKt;
import com.oplus.systemui.statusbar.pipeline.shared.ui.binder.StatClockViewBinding;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatClock extends Clock implements CoordAnimIcon, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatClock";
    private static final String VISILILITY_FROM_MODEL = "visibility_from_model";
    private StatClockViewBinding binding;
    public int cachedMinutesLayoutWidth;
    public boolean clockVisibleByModel;
    public float darkIntensity;
    public boolean hideSecBySeeding;
    private String lastSecondsText;
    public int layoutLeft;
    public int paddingEnd;
    public final boolean showDark;
    public int tint;
    private final ArrayList<Rect> tmpRectList;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.tmpRectList = new ArrayList<>();
        this.layoutLeft = -1;
        this.clockVisibleByModel = true;
        this.cachedMinutesLayoutWidth = -1;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Clock, 0, 0);
        try {
            this.showDark = typedArrayObtainStyledAttributes.getBoolean(1, true);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("StatClock state: ");
        printWriter.println("  view: " + this);
        printWriter.println("  location: " + getLocationOnScreen()[0]);
        printWriter.println("  transXForCoord: " + getTransXForCoord());
        printWriter.println("  paddingStart: " + getPaddingStart() + ", paddingEnd: " + this.paddingEnd);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  secondsFixedWidth: ", getSecondsFixedWidth(), printWriter);
        printWriter.println("  cachedMinutesLayoutWidth: " + this.cachedMinutesLayoutWidth);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  measuredWidth: ", getMeasuredWidth(), printWriter);
        printWriter.println("  showSeconds: " + ((Clock) this).mShowSeconds);
        printWriter.println("  text: " + ((Object) getText()));
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  hideSecBySeeding: ", this.hideSecBySeeding);
        printWriter.println("  lastSecondsText: " + this.lastSecondsText);
        ActionReceiver$.ExternalSyntheticOutline1.m("  tint: ", ColorUtilKt.hexColorString(Integer.valueOf(getCurrentTextColor())), printWriter);
    }

    public final StatClockViewBinding getBinding() {
        return this.binding;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getSecondsWidthExtraPadding() {
        return getPaddingStart() + this.paddingEnd;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        this.paddingEnd = getContext().getResources().getDimensionPixelSize(2131173025);
        super.onAttachedToWindow();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateConfigurationChanged(configuration);
    }

    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        this.darkIntensity = f;
        if (this.showDark) {
            this.tmpRectList.clear();
            this.tmpRectList.addAll(arrayList);
            this.tint = i;
            super.onDarkChanged(arrayList, f, i);
        }
    }

    public final void onDetachedFromWindow() {
        this.binding = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super/*android.widget.TextView*/.onDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*android.widget.TextView*/.onDraw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        super/*android.widget.TextView*/.onLayout(z, i, i2, i3, i4);
        int[] locationOnScreen = getLocationOnScreen();
        if (locationOnScreen != null) {
            if (!(locationOnScreen.length == 0)) {
                int i5 = this.layoutLeft;
                int i6 = locationOnScreen[0];
                if (i5 != i6) {
                    this.layoutLeft = i6;
                    onDarkChanged((ArrayList) CollectionsKt___CollectionsKt.toMutableList(this.tmpRectList), this.darkIntensity, this.tint);
                }
            }
        }
        StatClockViewBinding statClockViewBinding = this.binding;
        if (statClockViewBinding != null) {
            int i7 = getLocationOnScreen()[0];
            if (getSecondsFixedWidth() > 0) {
                width = getSecondsFixedWidth();
            } else if (((Clock) this).mShowSeconds) {
                width = computeSecondsFixedLayoutWidth();
            } else {
                width = this.cachedMinutesLayoutWidth;
                if (width <= 0) {
                    width = getWidth();
                }
            }
            statClockViewBinding.onLayoutAreasChanged(TuplesKt.to(Integer.valueOf(i7), Integer.valueOf(this.cachedMinutesLayoutWidth + i7)), TuplesKt.to(Integer.valueOf(i7), Integer.valueOf(i7 + width)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        if (((Clock) this).mShowSeconds) {
            super.onMeasure(i, i2);
            return;
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172971);
        String string = getText().toString();
        TextPaint paint = getPaint();
        paint.setTextSize(dimensionPixelSize);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(getPaddingStart() + this.paddingEnd + ((int) paint.measureText(string)), 1073741824), i2);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = parcelable instanceof Bundle ? (Bundle) parcelable : null;
        if (bundle != null && bundle.containsKey(VISILILITY_FROM_MODEL)) {
            this.clockVisibleByModel = bundle.getBoolean(VISILILITY_FROM_MODEL);
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* JADX INFO: renamed from: onSaveInstanceState, reason: merged with bridge method [inline-methods] */
    public Bundle m472onSaveInstanceState() {
        Bundle bundle = (Bundle) super.onSaveInstanceState();
        bundle.putBoolean(VISILILITY_FROM_MODEL, this.clockVisibleByModel);
        return bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshWidth() {
        CharSequence text = getText();
        String string = text != null ? text.toString() : null;
        if (string == null) {
            string = "";
        }
        String strStripSecondsFromClockText = Clock.stripSecondsFromClockText(string);
        if (strStripSecondsFromClockText.length() == 0) {
            strStripSecondsFromClockText = "00:00";
        }
        this.cachedMinutesLayoutWidth = getSecondsWidthExtraPadding() + ((int) getPaint().measureText(strStripSecondsFromClockText));
        super.refreshWidth();
    }

    public final void reloadDimens() {
        reloadStatClockDimens();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void reloadStatClockDimens() {
        setTextSize(0, getContext().getResources().getDimensionPixelSize(2131172971));
        this.paddingEnd = getContext().getResources().getDimensionPixelSize(2131173025);
        refreshWidth();
    }

    public final void setBinding(StatClockViewBinding statClockViewBinding) {
        this.binding = statClockViewBinding;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setHideSecBySeeding(boolean z) {
        String str;
        CharSequence text;
        String string;
        if (this.hideSecBySeeding == z) {
            return;
        }
        this.hideSecBySeeding = z;
        if (z && ((Clock) this).mShowSeconds && (text = getText()) != null && (string = text.toString()) != null) {
            String strStripSecondsFromClockText = Clock.stripSecondsFromClockText(string);
            if (strStripSecondsFromClockText.length() != 0 && !TextUtils.equals(strStripSecondsFromClockText, getText())) {
                super/*android.widget.TextView*/.setText(strStripSecondsFromClockText, TextView.BufferType.NORMAL);
            }
        }
        refreshWidth();
        if (z || !((Clock) this).mShowSeconds || (str = this.lastSecondsText) == null) {
            return;
        }
        setText(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        String string;
        if (((Clock) this).mShowSeconds && charSequence != null) {
            this.lastSecondsText = charSequence.toString();
        }
        if (this.hideSecBySeeding && ((Clock) this).mShowSeconds && charSequence != null && (string = charSequence.toString()) != null) {
            charSequence = Clock.stripSecondsFromClockText(string);
        }
        if (TextUtils.equals(charSequence, getText())) {
            return;
        }
        super/*android.widget.TextView*/.setText(charSequence, bufferType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }

    public void updateConfigurationChanged(Configuration configuration) {
        Clock.refreshCachedIntegerFormat();
        reloadStatClockDimens();
    }

    public StatClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ StatClock(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public StatClock(Context context) {
        this(context, null, 0, 6, null);
    }
}
