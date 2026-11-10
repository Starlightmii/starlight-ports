package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.coui.appcompat.animation.touchanimation.TouchMotionParamConfig;
import com.coui.appcompat.animation.touchanimation.param.DragDirection;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplusos.systemui.common.util.OplusRoundParams;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsDetailToggleSliderLayout extends OplusQsBaseToggleSliderLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsDetailToggleSliderLayout";
    private View hookToggleView;
    public int sliderHeight;

    /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.AbsSeekBar, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    public OplusQsDetailToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ?? slider = getSlider();
        if (slider != 0) {
            slider.setInactiveThemeRes(null);
            slider.setBackgroundColorInTheme(2131103297);
            slider.setProgressColorInTheme(2131103298);
            slider.setFocusable(true);
            slider.setDetailToggle(true);
        }
    }

    public final void applyRadius(float f, OplusRoundParams oplusRoundParams) {
        OplusQsVerticalSeekBar slider = getSlider();
        if (slider != null) {
            slider.setBackgroundRadius(f);
        }
        OplusQsVerticalSeekBar slider2 = getSlider();
        if (slider2 != null) {
            slider2.setBackgroundRoundCornerWeight(oplusRoundParams != null ? oplusRoundParams.getWeight() : 0.0f);
        }
        OplusQsVerticalSeekBar slider3 = getSlider();
        if (slider3 != null) {
            slider3.setPaddingVertical(f);
        }
        Function2 onRadiusUpdateListener = getOnRadiusUpdateListener();
        if (onRadiusUpdateListener != null) {
            onRadiusUpdateListener.invoke(Float.valueOf(f), (Object) null);
        }
    }

    public final void generateToggleViewWithLayoutId(int i, View view) {
        setToggleView(generateToggleViewInner(i));
        this.hookToggleView = view;
    }

    public final View getHookToggleView() {
        return this.hookToggleView;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public boolean getOneHandedEnable() {
        return OneHandedObserver.Companion.getInstance().getOneHandedEnable();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public TouchMotionParamConfig getParamConfig() {
        return new TouchMotionParamConfig().setAnimationParamsType(COUITouchMotionAnimator.AnimationParamsType.MIN).setMovementStrength(0.01f).setMaxDragDistance(5000.0f).setRubberBandMaxDistance(60.0f).setDragDirection(DragDirection.Y);
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public float getRadius() {
        return (!isFullCornerShape() || getWidth() <= 0) ? ((ShapeConfig) StdQSResPool.tileShapeConfig.getValue()).getRadius(getContext()) * 2.0f : getWidth() / 2.0f;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public int getSeekBarHeight() {
        int seekBarHeight = super.getSeekBarHeight();
        return seekBarHeight > 0 ? seekBarHeight : this.sliderHeight;
    }

    public final int getSliderHeight() {
        return this.sliderHeight;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final boolean isSupportStroke() {
        return QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() ? SepQSResPool.isSupportStrokeCorner() : StdQSResPool.isSupportStrokeCorner();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final boolean needDisallowInterceptTouchEvent() {
        return true;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout, android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateCornerRadius();
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public void setCornerRadius(float f) {
        OplusQsVerticalSeekBar slider = getSlider();
        if (slider != null) {
            slider.setSupportStroke(isSupportStroke());
        }
        OplusQsVerticalSeekBar slider2 = getSlider();
        if (slider2 != null) {
            slider2.setMirrorBlurScaleValue(BaseQSResPool.getMirrorBlurScaleValue());
        }
        OplusRoundParams roundParams = getRoundParams(f);
        if (QsColorUtil.isGlobalThemeApplied(getContext())) {
            applyRadius(f, null);
        } else {
            applyRadius(f, roundParams);
        }
        OplusQsVerticalSeekBar slider3 = getSlider();
        if (slider3 != null) {
            OplusQsVerticalSeekBar.Companion companion = OplusQsVerticalSeekBar.Companion;
            slider3.updateBaseMixColorDrawableRadius(((COUIVerticalSeekBar) slider3).mCurProgressRadius);
        }
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public void setDeformedTranslation(float f) {
        super.setDeformedTranslation(f);
        View view = this.hookToggleView;
        if (view != null) {
            view.setTranslationY(f);
        }
    }

    public final void setHookToggleView(View view) {
        this.hookToggleView = view;
    }

    public final void setSliderHeight(int i) {
        this.sliderHeight = i;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout
    public final void updateCornerRadius() {
        setCornerRadius(getRadius());
    }

    public OplusQsDetailToggleSliderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQsDetailToggleSliderLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ OplusQsDetailToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public OplusQsDetailToggleSliderLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
