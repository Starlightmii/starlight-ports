package com.oplus.systemui.plugins.qs.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewGroupKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsToggleSliderLayout extends OplusQsBaseToggleSliderLayout implements IEditableView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsToggleSliderLayout";
    public boolean isEditMode;
    private OneHandedEnableProvider oneHandedEnableProvider;
    private final SpanSize size;
    private final Lazy snapshotPaint$delegate;
    private Bitmap snapshotWithTheme;
    private final Lazy springAnimation$delegate;
    public float transitionFraction;
    public final float viewRadius;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        OplusQsToggleSliderLayout$$ExternalSyntheticLambda0 oplusQsToggleSliderLayout$$ExternalSyntheticLambda0 = new OplusQsToggleSliderLayout$$ExternalSyntheticLambda0();
        oplusQsToggleSliderLayout$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusQsToggleSliderLayout$$ExternalSyntheticLambda0);
        this.snapshotPaint$delegate = LazyKt__LazyJVMKt.lazy(new OplusQsToggleSliderLayout$$ExternalSyntheticLambda1());
        this.size = SpanCache.INSTANCE.spanSize(1, 2);
        setWillNotDraw(false);
        this.viewRadius = getRadius();
    }

    public static final void access$endTransition(OplusQsToggleSliderLayout oplusQsToggleSliderLayout, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("endTransition: ", "OplusQsToggleSliderLayout-->", "SystemUi--QuickSettings", z);
        }
        Bitmap bitmap = oplusQsToggleSliderLayout.snapshotWithTheme;
        if (bitmap != null) {
            bitmap.recycle();
        }
        oplusQsToggleSliderLayout.snapshotWithTheme = null;
        OplusQsVerticalSeekBar slider = oplusQsToggleSliderLayout.getSlider();
        if (slider != null && slider.isThemeDisabled != z) {
            slider.isThemeDisabled = z;
            slider.updateColor(slider.isSupportMixColor, false);
        }
        Object toggleView = oplusQsToggleSliderLayout.getToggleView();
        com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView oplusQsVolumeIconView = toggleView instanceof com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView ? (com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView) toggleView : null;
        if (oplusQsVolumeIconView != null) {
            oplusQsVolumeIconView.disableTheme(z);
        }
        View toggleView2 = oplusQsToggleSliderLayout.getToggleView();
        ClipBrightnessView clipBrightnessView = toggleView2 instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView2 : null;
        if (clipBrightnessView != null) {
            clipBrightnessView.disableTheme(z);
        }
        Iterator it = ViewGroupKt.getChildren(oplusQsToggleSliderLayout).iterator();
        while (it.hasNext()) {
            ((View) it.next()).setTransitionAlpha(1.0f);
        }
        oplusQsToggleSliderLayout.invalidate();
    }

    private final Paint getSnapshotPaint() {
        return (Paint) this.snapshotPaint$delegate.getValue();
    }

    private final COUISpringAnimation getSpringAnimation() {
        return (COUISpringAnimation) this.springAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTransitionFraction(float f) {
        this.transitionFraction = f;
        getSnapshotPaint().setAlpha((int) ((1.0f - f) * 255.0f));
        Iterator it = ViewGroupKt.getChildren(this).iterator();
        while (it.hasNext()) {
            ((View) it.next()).setTransitionAlpha(f);
        }
        invalidate();
    }

    public boolean getEnablePressFeedback() {
        return super.getEnablePressFeedback();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public boolean getOneHandedEnable() {
        OneHandedEnableProvider oneHandedEnableProvider = this.oneHandedEnableProvider;
        if (oneHandedEnableProvider != null) {
            return oneHandedEnableProvider.getOneHandedEnable();
        }
        return false;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public float getRadius() {
        return (!QsColorUtil.isGlobalThemeApplied(((FrameLayout) this).mContext) || this.isEditMode) ? CellCalculatorManager.Companion.getInstance(getContext()).dpToPxWithInitialDensity(62.0f) / 2.0f : super.getRadius();
    }

    public float getViewRadius() {
        return this.viewRadius;
    }

    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final boolean isEditable() {
        return this.isEditMode;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.snapshotWithTheme;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, getSnapshotPaint());
        }
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isEditMode) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (getMeasuredWidth() != size || getMeasuredHeight() != size2) {
            updateCapsuleLayout$1();
        }
        super.onMeasure(i, i2);
    }

    public void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            if (z) {
                GlobalUtils.sendCancelMotionEvent(this, "cancel press");
            }
        }
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public void setIsEditable(boolean z) {
        Bitmap bitmapCreateBitmap;
        Bitmap bitmapCreateBitmap2;
        setEditMode(z);
        if (QsColorUtil.isGlobalThemeApplied(getContext())) {
            updateCornerRadius();
        }
        if (QsColorUtil.isGlobalThemeApplied(getContext())) {
            if (((COUIDynamicAnimation) getSpringAnimation()).mRunning) {
                getSpringAnimation().cancel();
                startTransition(this.transitionFraction, z);
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("preTransition: ", "OplusQsToggleSliderLayout-->", "SystemUi--QuickSettings", z);
            }
            if (z) {
                int width = getWidth();
                int height = getHeight();
                if (width <= 0 || height <= 0) {
                    bitmapCreateBitmap2 = null;
                } else {
                    bitmapCreateBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    draw(new Canvas(bitmapCreateBitmap2));
                }
                this.snapshotWithTheme = bitmapCreateBitmap2;
                setTransitionFraction(0.0f);
            }
            OplusQsVerticalSeekBar slider = getSlider();
            if (slider != null && slider.isThemeDisabled != z) {
                slider.isThemeDisabled = z;
                slider.updateColor(slider.isSupportMixColor, false);
            }
            Object toggleView = getToggleView();
            com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView oplusQsVolumeIconView = toggleView instanceof com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView ? (com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView) toggleView : null;
            if (oplusQsVolumeIconView != null) {
                oplusQsVolumeIconView.disableTheme(z);
            }
            View toggleView2 = getToggleView();
            ClipBrightnessView clipBrightnessView = toggleView2 instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView2 : null;
            if (clipBrightnessView != null) {
                clipBrightnessView.disableTheme(z);
            }
            if (!z) {
                int width2 = getWidth();
                int height2 = getHeight();
                if (width2 <= 0 || height2 <= 0) {
                    bitmapCreateBitmap = null;
                } else {
                    bitmapCreateBitmap = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888);
                    draw(new Canvas(bitmapCreateBitmap));
                }
                this.snapshotWithTheme = bitmapCreateBitmap;
                setTransitionFraction(1.0f);
                OplusQsVerticalSeekBar slider2 = getSlider();
                if (slider2 != null && !slider2.isThemeDisabled) {
                    slider2.isThemeDisabled = true;
                    slider2.updateColor(slider2.isSupportMixColor, false);
                }
                Object toggleView3 = getToggleView();
                com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView oplusQsVolumeIconView2 = toggleView3 instanceof com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView ? (com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView) toggleView3 : null;
                if (oplusQsVolumeIconView2 != null) {
                    oplusQsVolumeIconView2.disableTheme(true);
                }
                View toggleView4 = getToggleView();
                ClipBrightnessView clipBrightnessView2 = toggleView4 instanceof ClipBrightnessView ? (ClipBrightnessView) toggleView4 : null;
                if (clipBrightnessView2 != null) {
                    clipBrightnessView2.disableTheme(true);
                }
            }
            updateGlobalThemeState.1 r0 = new updateGlobalThemeState.1();
            r0.this$0 = this;
            r0.$goingToEditMode = z;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(this, r0);
        }
    }

    public final void setOneHandedEnableProvider(OneHandedEnableProvider oneHandedEnableProvider) {
        this.oneHandedEnableProvider = oneHandedEnableProvider;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.isEditMode) {
            return;
        }
        super.setPressed(z);
    }

    public final void startTransition(float f, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsToggleSliderLayout-->".concat("startTransition: " + z + " " + f));
        }
        float f2 = z ? 1.0f : 0.0f;
        getSpringAnimation().setStartValue(f);
        getSpringAnimation().animateToFinalPosition(f2);
    }

    public final void updateCapsuleLayout$1() {
        updateCornerRadius();
        CellCalculatorManager.LayoutConstraint layoutConstraint$default = CellCalculatorManager.getLayoutConstraint$default(CellCalculatorManager.Companion.getInstance(getContext()), this.size, false, this.isEditMode, 10);
        View toggleView = getToggleView();
        if (toggleView != null) {
            toggleView.setScaleX(layoutConstraint$default.getInnerContentRatio());
            toggleView.setScaleY(layoutConstraint$default.getInnerContentRatio());
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toggleView.getLayoutParams();
            layoutParams.bottomMargin = (int) (layoutConstraint$default.getInnerMarginRatio() * getResources().getDimensionPixelSize(2131172229));
            toggleView.setLayoutParams(layoutParams);
        }
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public void updateColor(boolean z, boolean z2, QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(z, z2, colorType);
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            updateCapsuleLayout$1();
        }
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final void updateCornerRadius() {
        float radius = getRadius();
        setCornerRadius(radius);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateCornerRadius: radius:" + radius, null, 8);
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ OplusQsToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public OplusQsToggleSliderLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
