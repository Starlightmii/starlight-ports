package com.oplus.systemui.qs.base.seek;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.databinding.ObservableField;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ClipBrightnessView extends View {
    public static final Companion Companion = new Companion();
    private final NonNullObservableField<Boolean> aboveThreshold;
    private final NonNullObservableField<Boolean> autoMode;
    private Drawable brightnessIcon;
    private final Lazy brightnessIconMode$delegate;
    public float degree;
    private final NonNullObservableField<Boolean> disableTheme;
    private final NonNullObservableField<Unit> iconColorUpdate;
    public final int innerWidth;
    private final NonNullObservableField<Boolean> isGlobalTheme;
    private final NonNullObservableField<Boolean> isHighBrightnessState;
    private final NonNullObservableField<Boolean> nightMode;
    public final int padding;
    private Path path;
    public final String tag;
    private ValueAnimator valueAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Drawable $r8$lambda$TkrfWazqknhsXOp6wa6KNwhNL30(ClipBrightnessView clipBrightnessView) {
        Drawable drawable = clipBrightnessView.getContext().getDrawable(2131237072);
        if (drawable == null) {
            return null;
        }
        int i = clipBrightnessView.padding;
        drawable.setBounds(i, i, drawable.getIntrinsicWidth() + clipBrightnessView.padding, drawable.getIntrinsicHeight() + clipBrightnessView.padding);
        return drawable;
    }

    public ClipBrightnessView(Context context) {
        super(context);
        this.innerWidth = DeviceUtil.dp2px(((View) this).mContext, 11.0f);
        this.path = new Path();
        this.padding = getResources().getDimensionPixelSize(2131172230);
        ClipBrightnessView$$ExternalSyntheticLambda0 clipBrightnessView$$ExternalSyntheticLambda0 = new ClipBrightnessView$$ExternalSyntheticLambda0();
        clipBrightnessView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessIconMode$delegate = LazyKt__LazyJVMKt.lazy(clipBrightnessView$$ExternalSyntheticLambda0);
        this.tag = getClass().getSimpleName();
        NonNullObservableField<Boolean> nonNullObservableField = new NonNullObservableField<>(Boolean.valueOf(QsColorUtil.isGlobalThemeApplied(getContext())), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isGlobalTheme = nonNullObservableField;
        NonNullObservableField<Boolean> nonNullObservableField2 = new NonNullObservableField<>(Boolean.valueOf(getResources().getConfiguration().isNightModeActive()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.nightMode = nonNullObservableField2;
        Boolean bool = Boolean.FALSE;
        NonNullObservableField<Boolean> nonNullObservableField3 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.disableTheme = nonNullObservableField3;
        NonNullObservableField<Boolean> nonNullObservableField4 = new NonNullObservableField<>(Boolean.valueOf(getInitAutoMode()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.autoMode = nonNullObservableField4;
        NonNullObservableField<Boolean> nonNullObservableField5 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.aboveThreshold = nonNullObservableField5;
        NonNullObservableField<Boolean> nonNullObservableField6 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isHighBrightnessState = nonNullObservableField6;
        ClipBrightnessView$$ExternalSyntheticLambda1 clipBrightnessView$$ExternalSyntheticLambda1 = new ClipBrightnessView$$ExternalSyntheticLambda1();
        clipBrightnessView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconColorUpdate = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField, nonNullObservableField2, nonNullObservableField3, nonNullObservableField4, nonNullObservableField5, nonNullObservableField6}, false, clipBrightnessView$$ExternalSyntheticLambda1, 2, (DefaultConstructorMarker) null);
        init();
    }

    private final Drawable getBrightnessIconMode() {
        return (Drawable) this.brightnessIconMode$delegate.getValue();
    }

    private final boolean getInitAutoMode() {
        int intForUser = Settings.System.getIntForUser(((View) this).mContext.getContentResolver(), "screen_brightness_mode", 0, -2);
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, ListPopupWindow$.ExternalSyntheticOutline0.m(intForUser, "getInitAutoMode: autoMode="), null, 8);
        return intForUser != 0;
    }

    public static /* synthetic */ void setIconColorFilter$default(ClipBrightnessView clipBrightnessView, int i) {
        clipBrightnessView.setIconColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public final void disableTheme(boolean z) {
        if (z != ((Boolean) this.disableTheme.get()).booleanValue()) {
            this.disableTheme.set(Boolean.valueOf(z));
            invalidate();
        }
    }

    public final boolean getAutoMode() {
        return ((Boolean) this.autoMode.get()).booleanValue();
    }

    public final Drawable getBrightnessIcon() {
        return this.brightnessIcon;
    }

    public final float getDegree() {
        return this.degree;
    }

    public void handleDraw(Canvas canvas) {
        Drawable brightnessIconMode;
        canvas.save();
        boolean zBooleanValue = ((Boolean) this.isGlobalTheme.get()).booleanValue();
        if (!zBooleanValue && ((Boolean) this.autoMode.get()).booleanValue()) {
            canvas.clipPath(this.path);
        }
        if (!zBooleanValue) {
            canvas.rotate(this.degree, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        }
        Drawable drawable = this.brightnessIcon;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        canvas.restore();
        if (zBooleanValue || !((Boolean) this.autoMode.get()).booleanValue() || (brightnessIconMode = getBrightnessIconMode()) == null) {
            return;
        }
        brightnessIconMode.draw(canvas);
    }

    public final void init() {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "init", null, 8);
        updateIconConfig();
        Drawable drawable = this.brightnessIcon;
        if (drawable != null) {
            float intrinsicWidth = drawable.getIntrinsicWidth() + this.padding;
            float intrinsicHeight = drawable.getIntrinsicHeight() + this.padding;
            int i = this.innerWidth;
            float f = i;
            float f2 = i / 2;
            float f3 = (intrinsicWidth - f) - f2;
            float f4 = (intrinsicHeight - f) - f2;
            Path path = new Path();
            path.lineTo(this.padding, intrinsicHeight);
            path.lineTo(intrinsicWidth - this.innerWidth, intrinsicHeight);
            int i2 = this.innerWidth;
            path.lineTo(intrinsicWidth - i2, intrinsicHeight - (i2 / 2));
            float f5 = this.innerWidth;
            path.arcTo(f3, f4, f3 + f5, f4 + f5, 90.0f, -90.0f, false);
            path.lineTo(intrinsicWidth, intrinsicHeight - this.innerWidth);
            path.lineTo(intrinsicWidth, this.padding);
            float f6 = this.padding;
            path.lineTo(f6, f6);
            this.path = path;
        }
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.iconColorUpdate.attach();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.iconColorUpdate.detach();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        handleDraw(canvas);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        Drawable drawable = this.brightnessIcon;
        if (drawable == null) {
            super.onMeasure(i, i2);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Drawable brightnessIconMode = getBrightnessIconMode();
        if (brightnessIconMode != null) {
            int intrinsicWidth2 = brightnessIconMode.getIntrinsicWidth();
            if (intrinsicWidth < intrinsicWidth2) {
                intrinsicWidth = intrinsicWidth2;
            }
            int intrinsicHeight2 = brightnessIconMode.getIntrinsicHeight();
            if (intrinsicHeight < intrinsicHeight2) {
                intrinsicHeight = intrinsicHeight2;
            }
        }
        int i3 = this.padding;
        setMeasuredDimension((i3 * 2) + intrinsicWidth, (i3 * 2) + intrinsicHeight);
    }

    public final void setAutoMode(boolean z) {
        this.autoMode.set(Boolean.valueOf(z));
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        postInvalidate();
    }

    public final void setBrightnessIcon(Drawable drawable) {
        this.brightnessIcon = drawable;
    }

    public final void setIconColorFilter(int i, PorterDuff.Mode mode) {
        Drawable drawable = this.brightnessIcon;
        if (drawable != null) {
            drawable.clearColorFilter();
        }
        Drawable brightnessIconMode = getBrightnessIconMode();
        if (brightnessIconMode != null) {
            brightnessIconMode.clearColorFilter();
        }
        Drawable drawable2 = this.brightnessIcon;
        if (drawable2 != null) {
            drawable2.setColorFilter(i, mode);
        }
        Drawable brightnessIconMode2 = getBrightnessIconMode();
        if (brightnessIconMode2 != null) {
            brightnessIconMode2.setColorFilter(i, mode);
        }
    }

    public final void updateHighBrightnessState(boolean z) {
        this.isHighBrightnessState.set(Boolean.valueOf(z));
    }

    public void updateIconColor() {
        int i;
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            FlavorTwoFeatureOption.INSTANCE.getClass();
            AodClockLayout$3$.ExternalSyntheticOutline0.m("updateIconColor isSupportPacMan:", "SystemUi--QuickSettings", sbM, FlavorTwoFeatureOption.sIsSupportPacMan);
        }
        boolean zBooleanValue = ((Boolean) this.autoMode.get()).booleanValue();
        boolean zBooleanValue2 = ((Boolean) this.aboveThreshold.get()).booleanValue();
        boolean zBooleanValue3 = ((Boolean) this.nightMode.get()).booleanValue();
        String str2 = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            sbM2.append("setIconDrawable autoMode: " + this.autoMode);
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        if (!((Boolean) this.isGlobalTheme.get()).booleanValue() && ((Boolean) this.autoMode.get()).booleanValue()) {
            i = 2131237073;
        } else if (((Boolean) this.isGlobalTheme.get()).booleanValue() && ((Boolean) this.autoMode.get()).booleanValue()) {
            i = ((Boolean) this.disableTheme.get()).booleanValue() ? 2131236273 : 2131237103;
        } else {
            i = ((Boolean) this.disableTheme.get()).booleanValue() ? 2131236274 : 2131237102;
        }
        Drawable drawable = getContext().getDrawable(i);
        if (drawable != null) {
            int i2 = this.padding;
            drawable.setBounds(new Rect(i2, i2, drawable.getIntrinsicWidth() + this.padding, drawable.getIntrinsicHeight() + this.padding));
        } else {
            drawable = null;
        }
        this.brightnessIcon = drawable;
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported() && ((Boolean) this.isHighBrightnessState.get()).booleanValue()) {
            setIconColorFilter$default(this, ((View) this).mContext.getColor(2131103664));
            return;
        }
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.sIsSupportPacMan) {
            setIconColorFilter(((View) this).mContext.getColor(zBooleanValue3 ? 2131102992 : 2131102991), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        if (((Boolean) this.isGlobalTheme.get()).booleanValue()) {
            Drawable drawable2 = this.brightnessIcon;
            if (drawable2 != null) {
                drawable2.clearColorFilter();
            }
            Drawable brightnessIconMode = getBrightnessIconMode();
            if (brightnessIconMode != null) {
                brightnessIconMode.clearColorFilter();
                return;
            }
            return;
        }
        if (zBooleanValue) {
            setIconColorFilter$default(this, QsColorUtil.BRIGHTNESS_ICON_COLOR);
            return;
        }
        if (!zBooleanValue2) {
            if (ScrimUtil.isWallpaperBlurDisable() && QsColorUtil.isLowGaussianLevelColor(getContext(), ((Boolean) this.disableTheme.get()).booleanValue())) {
                setIconColorFilter$default(this, QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR);
                return;
            } else {
                if (!ScrimUtil.isLowGaussianLevel(getContext()) || QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || zBooleanValue3) {
                    setIconColorFilter$default(this, QsColorUtil.ICON_VOLUME_MEDIA_MUTE_WHITE_COLOR);
                    return;
                }
                return;
            }
        }
        String str3 = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
            sbM3.append("should use light color autoMode:" + this.autoMode);
            Log.d("SystemUi--QuickSettings", sbM3.toString());
        }
        setIconColorFilter$default(this, QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR);
    }

    public final void updateIconColorWidthMarginBottom(int i) {
        this.aboveThreshold.set(Boolean.valueOf(i >= 20));
    }

    public final void updateIconConfig() {
        this.nightMode.set(Boolean.valueOf(getResources().getConfiguration().isNightModeActive()));
        this.isGlobalTheme.set(Boolean.valueOf(QsColorUtil.isGlobalThemeApplied(getContext())));
    }

    public final void updateRotation(float f) {
        this.degree = f;
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        postInvalidate();
    }

    public final void updateRotationAnimation(float f, float f2, int i) {
        updateIconColorWidthMarginBottom(i);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.degree, f);
        this.valueAnimator = valueAnimatorOfFloat;
        if (f2 > 16.0f) {
            f2 -= 16.0f;
        }
        if (valueAnimatorOfFloat != null) {
            valueAnimatorOfFloat.setDuration((long) f2);
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            ClipBrightnessView$updateRotationAnimation$1$1 clipBrightnessView$updateRotationAnimation$1$1 = new ClipBrightnessView$updateRotationAnimation$1$1();
            clipBrightnessView$updateRotationAnimation$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(clipBrightnessView$updateRotationAnimation$1$1);
            valueAnimatorOfFloat.start();
        }
    }

    public void updateRotation(float f, int i) {
        updateIconColorWidthMarginBottom(i);
        updateRotation(f);
    }

    public ClipBrightnessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.innerWidth = DeviceUtil.dp2px(((View) this).mContext, 11.0f);
        this.path = new Path();
        this.padding = getResources().getDimensionPixelSize(2131172230);
        ClipBrightnessView$$ExternalSyntheticLambda0 clipBrightnessView$$ExternalSyntheticLambda0 = new ClipBrightnessView$$ExternalSyntheticLambda0();
        clipBrightnessView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessIconMode$delegate = LazyKt__LazyJVMKt.lazy(clipBrightnessView$$ExternalSyntheticLambda0);
        this.tag = getClass().getSimpleName();
        NonNullObservableField<Boolean> nonNullObservableField = new NonNullObservableField<>(Boolean.valueOf(QsColorUtil.isGlobalThemeApplied(getContext())), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isGlobalTheme = nonNullObservableField;
        NonNullObservableField<Boolean> nonNullObservableField2 = new NonNullObservableField<>(Boolean.valueOf(getResources().getConfiguration().isNightModeActive()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.nightMode = nonNullObservableField2;
        Boolean bool = Boolean.FALSE;
        NonNullObservableField<Boolean> nonNullObservableField3 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.disableTheme = nonNullObservableField3;
        NonNullObservableField<Boolean> nonNullObservableField4 = new NonNullObservableField<>(Boolean.valueOf(getInitAutoMode()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.autoMode = nonNullObservableField4;
        NonNullObservableField<Boolean> nonNullObservableField5 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.aboveThreshold = nonNullObservableField5;
        NonNullObservableField<Boolean> nonNullObservableField6 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isHighBrightnessState = nonNullObservableField6;
        ClipBrightnessView$$ExternalSyntheticLambda1 clipBrightnessView$$ExternalSyntheticLambda1 = new ClipBrightnessView$$ExternalSyntheticLambda1();
        clipBrightnessView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconColorUpdate = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField, nonNullObservableField2, nonNullObservableField3, nonNullObservableField4, nonNullObservableField5, nonNullObservableField6}, false, clipBrightnessView$$ExternalSyntheticLambda1, 2, (DefaultConstructorMarker) null);
        init();
    }

    public ClipBrightnessView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.innerWidth = DeviceUtil.dp2px(((View) this).mContext, 11.0f);
        this.path = new Path();
        this.padding = getResources().getDimensionPixelSize(2131172230);
        ClipBrightnessView$$ExternalSyntheticLambda0 clipBrightnessView$$ExternalSyntheticLambda0 = new ClipBrightnessView$$ExternalSyntheticLambda0();
        clipBrightnessView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessIconMode$delegate = LazyKt__LazyJVMKt.lazy(clipBrightnessView$$ExternalSyntheticLambda0);
        this.tag = getClass().getSimpleName();
        NonNullObservableField<Boolean> nonNullObservableField = new NonNullObservableField<>(Boolean.valueOf(QsColorUtil.isGlobalThemeApplied(getContext())), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isGlobalTheme = nonNullObservableField;
        NonNullObservableField<Boolean> nonNullObservableField2 = new NonNullObservableField<>(Boolean.valueOf(getResources().getConfiguration().isNightModeActive()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.nightMode = nonNullObservableField2;
        Boolean bool = Boolean.FALSE;
        NonNullObservableField<Boolean> nonNullObservableField3 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.disableTheme = nonNullObservableField3;
        NonNullObservableField<Boolean> nonNullObservableField4 = new NonNullObservableField<>(Boolean.valueOf(getInitAutoMode()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.autoMode = nonNullObservableField4;
        NonNullObservableField<Boolean> nonNullObservableField5 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.aboveThreshold = nonNullObservableField5;
        NonNullObservableField<Boolean> nonNullObservableField6 = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isHighBrightnessState = nonNullObservableField6;
        ClipBrightnessView$$ExternalSyntheticLambda1 clipBrightnessView$$ExternalSyntheticLambda1 = new ClipBrightnessView$$ExternalSyntheticLambda1();
        clipBrightnessView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.iconColorUpdate = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField, nonNullObservableField2, nonNullObservableField3, nonNullObservableField4, nonNullObservableField5, nonNullObservableField6}, false, clipBrightnessView$$ExternalSyntheticLambda1, 2, (DefaultConstructorMarker) null);
        init();
    }
}
