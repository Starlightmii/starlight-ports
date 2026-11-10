package com.oplus.systemui.qs.base.seek;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.coui.appcompat.animation.touchanimation.TouchMotionParamConfig;
import com.coui.appcompat.animation.touchanimation.param.DragDirection;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.qs.base.R$styleable;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.IRadiusModifier;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.Gefingerpoken;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsBaseToggleSliderLayout extends FrameLayout implements IRadiusModifier {
    public static final Companion Companion = new Companion();
    private static final PathInterpolator SLIDER_PROGRESS_ANIMATION_INTERPOLATOR = new PathInterpolator(0.17f, 0.17f, 0.0f, 1.0f);
    private static final String SLIDER_PROGRESS_ANIMATION_PROPERTYNAME = "progress";
    public int animTargetProgress;
    private final AtomicBoolean disallowParentIntercept;
    private Gefingerpoken interceptListener;
    public boolean isTouchDelegate;
    public final String logTag;
    private Function2 onRadiusUpdateListener;
    private Function3 onTouchAnimationListener;
    private final OplusQsVerticalSeekBar slider;
    private PressFeedbackHelper.QsTilePressFeedback sliderPressFeedBack;
    private ObjectAnimator sliderProgressAnim;
    private OplusQsDetailToggleTouchDelegate sliderTouchDelegate;
    private View.OnTouchListener sliderTouchListener;
    private final View.OnTouchListener sliderTouchListenerProxy;
    private View toggleView;
    private OplusQsSliderTouchAnimationDelegate touchAnimationDelegate;
    private Function0 touchDelegateEnd;
    private final OplusQsToggleSliderTouchExt touchExt;
    public float transitionRadius;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$IS6fWgxOL2OzO895V65bjevZ5W0(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout, float f, float f2, float f3) {
        Function3 function3 = oplusQsBaseToggleSliderLayout.onTouchAnimationListener;
        if (function3 != null) {
            function3.invoke(Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsBaseToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar;
        super(context, attributeSet, i, i2);
        this.logTag = getClass().getSimpleName();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.OplusQsBaseToggleSliderLayout, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        IQSSpotLightConfig iQSSpotLightConfig = (OplusQsVerticalSeekBar) View.inflate(getContext(), 2131559531, this).findViewById(2131365957);
        if (iQSSpotLightConfig != null) {
            iQSSpotLightConfig.setMoveType(2);
            iQSSpotLightConfig.setMoveDamping(1.0f);
            iQSSpotLightConfig.setLayoutDirection(0);
            iQSSpotLightConfig.setFlingLinearDamping(30.0f);
            if (z) {
                oplusQsVerticalSeekBar = iQSSpotLightConfig;
                int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172154);
                iQSSpotLightConfig.setSupportDeformation(false);
                iQSSpotLightConfig.setMaxHeightDeformed(dimensionPixelSize);
                iQSSpotLightConfig.setMaxMovingDistance(dimensionPixelSize);
                oplusQsVerticalSeekBar = iQSSpotLightConfig;
            }
        } else {
            oplusQsVerticalSeekBar = 0;
        }
        oplusQsVerticalSeekBar = iQSSpotLightConfig;
        this.slider = oplusQsVerticalSeekBar;
        setCornerRadius(getRadius());
        this.toggleView = resourceId != 0 ? generateToggleViewInner(resourceId) : null;
        this.sliderPressFeedBack = new PressFeedbackHelper.QsTilePressFeedback(this);
        this.touchExt = new OplusQsToggleSliderTouchExt(this);
        this.animTargetProgress = -1;
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName(SLIDER_PROGRESS_ANIMATION_PROPERTYNAME);
        objectAnimator.setInterpolator(SLIDER_PROGRESS_ANIMATION_INTERPOLATOR);
        this.sliderProgressAnim = objectAnimator;
        this.disallowParentIntercept = new AtomicBoolean(false);
        OplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1 oplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1 = new OplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1();
        oplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sliderTouchListenerProxy = oplusQsBaseToggleSliderLayout$sliderTouchListenerProxy$1;
        Float backgroundRadius = getBackgroundRadius();
        this.transitionRadius = backgroundRadius != null ? backgroundRadius.floatValue() : 0.0f;
    }

    public final void addOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.addOnSeekBarChangeListener(onSeekBarChangeListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventDispatchTouchEvent;
        IQSSpotLightConfig iQSSpotLightConfig;
        if (!isEditable() && (iQSSpotLightConfig = this.slider) != null) {
            SharedSpotLightEffect.onTouchEvent(iQSSpotLightConfig, motionEvent);
        }
        OplusQsDetailToggleTouchDelegate oplusQsDetailToggleTouchDelegate = this.sliderTouchDelegate;
        if (oplusQsDetailToggleTouchDelegate != null && (motionEventDispatchTouchEvent = oplusQsDetailToggleTouchDelegate.dispatchTouchEvent(motionEvent)) != null) {
            motionEvent = motionEventDispatchTouchEvent;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final View generateToggleViewInner(int i) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(2131165745), getResources().getDimensionPixelSize(2131165745), 81);
        layoutParams.setMarginsRelative(0, 0, 0, getResources().getDimensionPixelSize(2131172229));
        View viewInflate = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null);
        viewInflate.setLayoutParams(layoutParams);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172230);
        viewInflate.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        viewInflate.setBackground(getContext().getDrawable(2131232741));
        addView(viewInflate);
        return viewInflate;
    }

    public final Float getBackgroundCornerWeight() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return Float.valueOf(oplusQsVerticalSeekBar.getBackgroundCornerWeight());
        }
        return null;
    }

    public final Float getBackgroundRadius() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return Float.valueOf(oplusQsVerticalSeekBar.getBackgroundRadius());
        }
        return null;
    }

    public final Rect getBackgroundRect() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return oplusQsVerticalSeekBar.getBackgroundRect();
        }
        return null;
    }

    public final int getDistanceFromBottom(int i, int i2) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar == null) {
            return 0;
        }
        if (i2 <= 0) {
            i2 = oplusQsVerticalSeekBar.getMax();
        }
        return (int) (((i / i2) * getSeekBarHeight()) / getResources().getDisplayMetrics().density);
    }

    public final int getMax() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return oplusQsVerticalSeekBar.getMax();
        }
        return 0;
    }

    public final int getMin() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return oplusQsVerticalSeekBar.getMin();
        }
        return 0;
    }

    public final Function2 getOnRadiusUpdateListener() {
        return this.onRadiusUpdateListener;
    }

    public final Function3 getOnTouchAnimationListener() {
        return this.onTouchAnimationListener;
    }

    public boolean getOneHandedEnable() {
        return false;
    }

    public TouchMotionParamConfig getParamConfig() {
        TouchMotionParamConfig dragDirection = new TouchMotionParamConfig().setAnimationParamsType(COUITouchMotionAnimator.AnimationParamsType.MIN).setMovementStrength(0.01f).setMaxDragDistance(4000.0f).setRubberBandMaxDistance(30.0f).setDragDirection(DragDirection.Y);
        dragDirection.getClass();
        return dragDirection;
    }

    public final int getProgress() {
        if (this.sliderProgressAnim.isRunning()) {
            return this.animTargetProgress;
        }
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            return oplusQsVerticalSeekBar.getProgress();
        }
        return 0;
    }

    public float getRadius() {
        return getContext().getResources().getDimensionPixelSize(2131172172);
    }

    public final OplusRoundParams getRoundParams(float f) {
        OplusRoundParams roundParams;
        return (isFullCornerShape() || (roundParams = OplusQsSmoothRoundUtil.getRoundParams(getContext(), f)) == null) ? OplusQsSmoothRoundUtil.getFullCornerParams(f) : roundParams;
    }

    public int getSeekBarHeight() {
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            return (iQSSpotLightConfig.getHeight() - iQSSpotLightConfig.getPaddingTop()) - iQSSpotLightConfig.getPaddingBottom();
        }
        return 0;
    }

    public final Float getSeekbarHeight() {
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            return Float.valueOf(iQSSpotLightConfig.getHeight());
        }
        return null;
    }

    public final OplusQsVerticalSeekBar getSlider() {
        return this.slider;
    }

    public final OplusQsDetailToggleTouchDelegate getSliderTouchDelegate() {
        return this.sliderTouchDelegate;
    }

    public final View getToggleView() {
        return this.toggleView;
    }

    public final OplusQsSliderTouchAnimationDelegate getTouchAnimationDelegate() {
        return this.touchAnimationDelegate;
    }

    public final Function0 getTouchDelegateEnd() {
        return this.touchDelegateEnd;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IRadiusModifier
    public float getTransitionRadius() {
        return this.transitionRadius;
    }

    @Override // android.view.View
    public int getWindowAttachCount() {
        return super.getWindowAttachCount();
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isEditable() {
        return false;
    }

    public final boolean isFullCornerShape() {
        if (QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || StdQSResPool.isHighlightDefaultShape()) {
            return !BaseQSResPool.globalThemeApplied() || isEditable();
        }
        return false;
    }

    public boolean isSupportStroke() {
        return SepQSResPool.isSupportStrokeCorner();
    }

    public boolean needDisallowInterceptTouchEvent() {
        if (!GlobalUtils.hasDialogShowing()) {
            return true;
        }
        OplusQsDetailToggleTouchDelegate oplusQsDetailToggleTouchDelegate = this.sliderTouchDelegate;
        return oplusQsDetailToggleTouchDelegate != null ? oplusQsDetailToggleTouchDelegate.getRequestDisallowInterceptTouchEvent() : false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.touchExt.onAttach();
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            iQSSpotLightConfig.setOnTouchListener(this.sliderTouchListenerProxy);
        }
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            this.touchAnimationDelegate = new OplusQsSliderTouchAnimationDelegate(this.logTag, oplusQsVerticalSeekBar, getParamConfig());
        }
        OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate = this.touchAnimationDelegate;
        if (oplusQsSliderTouchAnimationDelegate != null) {
            OplusQsBaseToggleSliderLayout$$ExternalSyntheticLambda0 oplusQsBaseToggleSliderLayout$$ExternalSyntheticLambda0 = new OplusQsBaseToggleSliderLayout$$ExternalSyntheticLambda0();
            oplusQsBaseToggleSliderLayout$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            oplusQsSliderTouchAnimationDelegate.setTouchAnimationCallback(oplusQsBaseToggleSliderLayout$$ExternalSyntheticLambda0);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.touchExt.initRealSize(((FrameLayout) this).mContext);
        updateCornerRadius();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.touchExt.onDetach();
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            iQSSpotLightConfig.setOnTouchListener(null);
        }
        OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate = this.touchAnimationDelegate;
        if (oplusQsSliderTouchAnimationDelegate != null) {
            oplusQsSliderTouchAnimationDelegate.destroy();
        }
        this.touchAnimationDelegate = null;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Gefingerpoken gefingerpoken = this.interceptListener;
        return gefingerpoken != null ? gefingerpoken.onInterceptTouchEvent(motionEvent) : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate = this.touchAnimationDelegate;
        if (oplusQsSliderTouchAnimationDelegate != null) {
            oplusQsSliderTouchAnimationDelegate.handleSizeChanged(i, i2, i3, i4);
        }
    }

    public final void removeOnSeekBarChangeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.removeOnSeekBarChangeListener(onSeekBarChangeListener);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.AbsSeekBar, com.coui.appcompat.seekbar.COUIVerticalSeekBar, com.oplus.systemui.qs.base.seek.OplusQsVerticalSeekBar] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setCornerRadius(float f) {
        ?? r0 = this.slider;
        if (r0 != 0) {
            boolean zIsSupportStroke = isSupportStroke();
            OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
            if (oplusQsVerticalSeekBar != null) {
                oplusQsVerticalSeekBar.setSupportStroke(zIsSupportStroke);
            }
            OplusQsVerticalSeekBar oplusQsVerticalSeekBar2 = this.slider;
            if (oplusQsVerticalSeekBar2 != null) {
                oplusQsVerticalSeekBar2.setMirrorBlurScaleValue(BaseQSResPool.getMirrorBlurScaleValue());
            }
            OplusRoundParams roundParams = getRoundParams(f);
            boolean zIsGlobalThemeApplied = QsColorUtil.isGlobalThemeApplied(r0.getContext());
            String str = this.logTag;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("updateRadius isEditable:", isEditable(), " radius= ", f, ", roundParams= ");
            sbM.append(roundParams);
            sbM.append(", isDefaultVolumeTheme = ");
            sbM.append(!zIsGlobalThemeApplied);
            LogUtil.i$default(LogUtil.TAG_QS, str, sbM.toString(), null, 8);
            if (!zIsGlobalThemeApplied || isEditable()) {
                r0.setBackgroundRadius(roundParams.getRadius());
                r0.setBackgroundRoundCornerWeight(roundParams.getWeight());
                r0.setPaddingVertical(roundParams.getRadius());
                Function2 function2 = this.onRadiusUpdateListener;
                if (function2 != null) {
                    function2.invoke(Float.valueOf(f), roundParams);
                }
            } else {
                r0.setBackgroundRadius(f);
                r0.setBackgroundRoundCornerWeight(0.0f);
                r0.setPaddingVertical(f);
                Function2 function3 = this.onRadiusUpdateListener;
                if (function3 != null) {
                    function3.invoke(Float.valueOf(f), (Object) null);
                }
            }
            r0.updateBaseMixColorDrawableRadius(((COUIVerticalSeekBar) r0).mCurProgressRadius);
            OplusQsVerticalSeekBar oplusQsVerticalSeekBar3 = this.slider;
            if (oplusQsVerticalSeekBar3 != null) {
                oplusQsVerticalSeekBar3.setMoveDamping();
            }
        }
    }

    public final void setCustomProgressAnimDuration(float f) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setCustomProgressAnimDuration(f);
        }
    }

    public final void setDeformedListener(COUIVerticalSeekBar.OnDeformedListener onDeformedListener) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setDeformedListener(onDeformedListener);
        }
    }

    public void setDeformedTranslation(float f) {
        View view = this.toggleView;
        if (view != null) {
            view.setTranslationY(f);
        }
    }

    public final void setDisallowInterceptTouchEvent(boolean z) {
        this.disallowParentIntercept.set(z);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setSliderEnabled(false);
        setToggleViewEnabled(1.0f, false);
    }

    public void setIsEditable(boolean z) {
    }

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
    }

    public final void setLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.touchExt.setOnLongClickListener(onLongClickListener);
    }

    public final void setMax(int i) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setMax(i);
        }
    }

    public final void setMin(int i) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setMin(i);
        }
    }

    public final void setOnInterceptListener(Gefingerpoken gefingerpoken) {
        this.interceptListener = gefingerpoken;
    }

    public void setOnLayoutClickListener(View.OnClickListener onClickListener) {
        this.touchExt.setLayoutClickListener(onClickListener);
    }

    public final void setOnRadiusUpdateListener(Function2 function2) {
        this.onRadiusUpdateListener = function2;
    }

    public final void setOnToggleClickListener(View.OnClickListener onClickListener) {
        this.touchExt.setToggleClickListener(onClickListener);
    }

    public final void setOnTouchAnimationListener(Function3 function3) {
        this.onTouchAnimationListener = function3;
    }

    public final void setProgress(int i, int i2, boolean z) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (i2 == 0) {
            if (oplusQsVerticalSeekBar != null) {
                oplusQsVerticalSeekBar.setProgress(i, z);
                return;
            }
            return;
        }
        if (!z) {
            if (oplusQsVerticalSeekBar != null) {
                oplusQsVerticalSeekBar.setProgress(i);
                return;
            }
            return;
        }
        if (oplusQsVerticalSeekBar != null) {
            boolean zIsRunning = this.sliderProgressAnim.isRunning();
            if (zIsRunning && i == this.animTargetProgress) {
                LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "updateProgressWithAnim:slider animator is running ,newProgress equal to target.", null, 8);
                return;
            }
            int progress = oplusQsVerticalSeekBar.getProgress();
            if (!zIsRunning && i == progress) {
                LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "updateProgressWithAnim:slider animator not running ,newProgress equal to target.", null, 8);
                return;
            }
            this.animTargetProgress = i;
            this.sliderProgressAnim.cancel();
            this.sliderProgressAnim.setTarget(oplusQsVerticalSeekBar);
            this.sliderProgressAnim.setIntValues(progress, i);
            this.sliderProgressAnim.setDuration(300L);
            this.sliderProgressAnim.start();
        }
    }

    public final void setSliderContentDescription(String str) {
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            iQSSpotLightConfig.setContentDescription(str);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setSliderEnabled(boolean z) {
        IQSSpotLightConfig iQSSpotLightConfig = this.slider;
        if (iQSSpotLightConfig != null) {
            iQSSpotLightConfig.setAlpha(z ? 1.0f : 0.5f);
        }
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setEnabled(z);
        }
    }

    public final void setSliderTouchDelegate(OplusQsDetailToggleTouchDelegate oplusQsDetailToggleTouchDelegate) {
        this.sliderTouchDelegate = oplusQsDetailToggleTouchDelegate;
    }

    public final void setSliderTouchListener(View.OnTouchListener onTouchListener) {
        this.sliderTouchListener = onTouchListener;
    }

    public final void setToggleView(View view) {
        this.toggleView = view;
    }

    public final void setToggleViewEnabled(float f, boolean z) {
        View view = this.toggleView;
        if (view != null) {
            if (!z) {
                f = 0.5f;
            }
            view.setAlpha(f);
        }
        View view2 = this.toggleView;
        if (view2 != null) {
            view2.setEnabled(z);
        }
    }

    public final void setTouchAnimationDelegate(OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate) {
        this.touchAnimationDelegate = oplusQsSliderTouchAnimationDelegate;
    }

    public final void setTouchDelegate(boolean z) {
        this.isTouchDelegate = z;
    }

    public final void setTouchDelegateEnd(Function0 function0) {
        this.touchDelegateEnd = function0;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IRadiusModifier
    public void setTransitionRadius(float f) {
        this.transitionRadius = f;
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.setBackgroundRadius(f);
            oplusQsVerticalSeekBar.setPaddingVertical(f);
            oplusQsVerticalSeekBar.updateBaseMixColorDrawableRadius(f);
        }
    }

    public final void showSeekBarBackgroundStroke(boolean z) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.showBackgroundStroke = z;
        }
    }

    public final void stopPhysicsMove() {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.stopPhysicsMove();
        }
    }

    public void updateColor(boolean z, boolean z2, QSSpecialModeUpdateMonitor.ColorType colorType) {
        OplusQsVerticalSeekBar oplusQsVerticalSeekBar = this.slider;
        if (oplusQsVerticalSeekBar != null) {
            oplusQsVerticalSeekBar.updateColor(z, z2);
        }
    }

    public void updateCornerRadius() {
    }

    public OplusQsBaseToggleSliderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public OplusQsBaseToggleSliderLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public /* synthetic */ OplusQsBaseToggleSliderLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    public OplusQsBaseToggleSliderLayout(Context context) {
        this(context, null, 0, 14, 0);
    }
}
