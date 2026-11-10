package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.compose.runtime.OpaqueKey$;
import androidx.core.view.ViewKt$;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.airbnb.lottie.LottieAnimationView;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.coui.appcompat.pressfeedback.COUIPressFeedbackHelper;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.plugins.qs.customize.view.animation.threestage.ThreeStageIconAlphaSpringAnimator;
import com.oplus.systemui.plugins.qs.customize.view.animation.threestage.ThreeStageScaleSpringAnimationHelper;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.res.model.TileViewFlag;
import com.oplus.systemui.qs.base.springtransition.DrawableTransitionAlphaTarget;
import com.oplus.systemui.qs.base.springtransition.IIntTarget;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.outline.OvalOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLayout extends FrameLayout {
    private static final String ASSETS_DIR_PREFIX = "ringermode/";
    private static final String TAG = "OplusQSThreeStageLayout";
    private final TileDrawableWrapper bgDrawable;
    private LinearLayout buttonContainer;
    private FrameLayout container;
    public boolean disableTheme;
    private final List<FrameLayout> frameLayoutViews;
    private final List<ThreeStageIconAlphaSpringAnimator> iconAlphaSpringAnimatorSets;
    private final DrawableTransitionAlphaTarget iconBgTransitionTarget;
    private final List<Integer> iconResIds;
    private final List<COUISpringAnimation> iconSpringAnimatorSets;
    private final Map<Integer, Integer> iconViewToLottieViewMap;
    private final IIntTarget iconViewTransitionTarget;
    private final List<ImageView> iconViews;
    private final TileTransitionDrawable indicatorBackground;
    public float indicatorLastTranslationX;
    public final float indicatorScale;
    private COUISpringAnimation indicatorSpringTranslationX;
    public boolean indicatorTranslationEnd;
    public float indicatorTranslationX;
    private final ImageView indicatorView;
    public boolean isDragging;
    public boolean isEditMode;
    public boolean isLayout;
    public boolean isLongPressTriggered;
    public boolean isTouchingSelectedIndex;
    public int lastTouchIndex;
    public float lastX;
    private Runnable longPressRunnable;
    private List<String> lottieFileList;
    private final List<OplusQSThreeStageLottieView> lottieViews;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    public int overDistance;
    public int preSelectedIndex;
    private final List<COUIPressFeedbackHelper> pressScaleHelper;
    private double[] results;
    private final List<ThreeStageScaleSpringAnimationHelper> scaleSyncAnimatorHelper;
    public int selectedIndex;
    private COUISpringAnimation springAnimationBounce;
    public float startX;
    public float startY;
    public final int switchDistance;
    private final TileDrawableWrapper themeBgDrawable;
    private Integer tileColorfulConfig;
    public int touchIndex;
    public final int touchSlop;
    private Function0 viewClick;
    public static final Companion Companion = new Companion();
    private static final Lazy springDragAnimatorConfig$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSThreeStageLayout$$ExternalSyntheticLambda0(1));
    private static final Lazy springClickAnimatorConfig$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSThreeStageLayout$$ExternalSyntheticLambda0(2));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringClickAnimatorConfig() {
            return (Spring) OplusQSThreeStageLayout.springClickAnimatorConfig$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringDragAnimatorConfig() {
            return (Spring) OplusQSThreeStageLayout.springDragAnimatorConfig$delegate.getValue();
        }
    }

    public OplusQSThreeStageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.iconResIds = CollectionsKt__CollectionsKt.listOf(new Integer[]{2131237194, 2131237580, 2131237193});
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.touchIndex = -1;
        this.lastTouchIndex = -1;
        this.indicatorScale = 1.0f;
        this.selectedIndex = -1;
        this.iconViews = new ArrayList();
        this.lottieViews = new ArrayList();
        this.frameLayoutViews = new ArrayList();
        this.lottieFileList = new ArrayList();
        this.iconSpringAnimatorSets = new ArrayList();
        this.scaleSyncAnimatorHelper = new ArrayList();
        this.pressScaleHelper = new ArrayList();
        this.iconAlphaSpringAnimatorSets = new ArrayList();
        int i2 = 0;
        this.viewClick = new OplusQSThreeStageLayout$$ExternalSyntheticLambda0(i2);
        this.results = new double[0];
        Map<Integer, Integer> mapMapOf = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(2131363704, 2131363705), TuplesKt.to(2131363955, 2131363956), TuplesKt.to(2131364918, 2131364920)});
        this.iconViewToLottieViewMap = mapMapOf;
        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
        this.bgDrawable = tileDrawableWrapper;
        TileDrawableWrapper tileDrawableWrapper2 = new TileDrawableWrapper(null);
        this.themeBgDrawable = tileDrawableWrapper2;
        TileTransitionDrawable tileTransitionDrawable = new TileTransitionDrawable(tileDrawableWrapper, tileDrawableWrapper2);
        tileTransitionDrawable.setPathProvider(OvalOutlineProvider.INSTANCE);
        this.indicatorBackground = tileTransitionDrawable;
        this.iconBgTransitionTarget = new DrawableTransitionAlphaTarget(tileTransitionDrawable);
        int i3 = 1;
        setDuplicateParentStateEnabled(true);
        FrameLayout.inflate(context, 2131559374, this);
        setId(2131364403);
        this.overDistance = getResources().getDimensionPixelOffset(2131171126);
        this.switchDistance = getResources().getDimensionPixelOffset(2131171128);
        this.lottieFileList = ArraysKt___ArraysKt.toMutableList(getResources().getStringArray(2130903236));
        this.container = (FrameLayout) findViewById(2131364402);
        this.buttonContainer = (LinearLayout) findViewById(2131362456);
        setLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
        for (Map.Entry<Integer, Integer> entry : mapMapOf.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            int iIntValue2 = entry.getValue().intValue();
            ImageView imageView = (ImageView) requireViewById(iIntValue);
            this.iconViews.add(imageView);
            LottieAnimationView lottieAnimationView = (OplusQSThreeStageLottieView) requireViewById(iIntValue2);
            this.lottieViews.add((OplusQSThreeStageLottieView) lottieAnimationView);
            List<ThreeStageIconAlphaSpringAnimator> list = this.iconAlphaSpringAnimatorSets;
            int activeIconColor = getActiveIconColor(false);
            int activeIconColor2 = getActiveIconColor(true);
            OplusQSThreeStageLayout$$ExternalSyntheticLambda1 oplusQSThreeStageLayout$$ExternalSyntheticLambda1 = new OplusQSThreeStageLayout$$ExternalSyntheticLambda1();
            oplusQSThreeStageLayout$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            list.add(new ThreeStageIconAlphaSpringAnimator(imageView, lottieAnimationView, activeIconColor, activeIconColor2, oplusQSThreeStageLayout$$ExternalSyntheticLambda1));
            this.scaleSyncAnimatorHelper.add(new ThreeStageScaleSpringAnimationHelper.Builder(imageView, lottieAnimationView).setAnimationType(ThreeStageScaleSpringAnimationHelper.AnimationType.SCALE_X).setStiffness(200.0f).setDamping(1.0f).build());
        }
        Iterator it = CollectionsKt__CollectionsKt.listOf(new Integer[]{2131363700, 2131363954, 2131364914}).iterator();
        while (it.hasNext()) {
            this.frameLayoutViews.add((FrameLayout) requireViewById(((Number) it.next()).intValue()));
        }
        int i4 = 0;
        for (Object obj : this.frameLayoutViews) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            FrameLayout frameLayout = (FrameLayout) obj;
            COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(frameLayout, COUIDynamicAnimation.TRANSLATION_X, 0.0f);
            COUISpringForce cOUISpringForce = new COUISpringForce();
            cOUISpringForce.setBounce(0.0f);
            cOUISpringForce.setResponse(0.35f);
            cOUISpringAnimation.setSpring(cOUISpringForce);
            this.iconSpringAnimatorSets.add(cOUISpringAnimation);
            this.pressScaleHelper.add(new COUIPressFeedbackHelper(frameLayout, 0));
            OplusQSThreeStageLayout$3$1 oplusQSThreeStageLayout$3$1 = new OplusQSThreeStageLayout$3$1();
            oplusQSThreeStageLayout$3$1.$index = i4;
            oplusQSThreeStageLayout$3$1.this$0 = this;
            VarHandle.storeStoreFence();
            frameLayout.setOnClickListener(oplusQSThreeStageLayout$3$1);
            i4 = i5;
        }
        ImageView imageView2 = (ImageView) requireViewById(2131363447);
        imageView2.setBackground(this.indicatorBackground);
        this.indicatorView = imageView2;
        COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(DynamicAnimation.TRANSLATION_X, imageView2);
        COUISpringForce cOUISpringForce2 = new COUISpringForce();
        Companion companion = Companion;
        cOUISpringForce2.setBounce(companion.getSpringClickAnimatorConfig().bounce());
        cOUISpringForce2.setResponse(companion.getSpringClickAnimatorConfig().response());
        cOUISpringAnimation2.setSpring(cOUISpringForce2);
        OplusQSThreeStageLayout$ensureSpringAnimation$1$2 oplusQSThreeStageLayout$ensureSpringAnimation$1$2 = new OplusQSThreeStageLayout$ensureSpringAnimation$1$2(i3);
        oplusQSThreeStageLayout$ensureSpringAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addUpdateListener(oplusQSThreeStageLayout$ensureSpringAnimation$1$2);
        OplusQSThreeStageLayout$indicatorTranslationXAnimation$1$3 oplusQSThreeStageLayout$indicatorTranslationXAnimation$1$3 = new OplusQSThreeStageLayout$indicatorTranslationXAnimation$1$3();
        oplusQSThreeStageLayout$indicatorTranslationXAnimation$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation2.addEndListener(oplusQSThreeStageLayout$indicatorTranslationXAnimation$1$3);
        this.indicatorSpringTranslationX = cOUISpringAnimation2;
        COUISpringAnimation cOUISpringAnimation3 = new COUISpringAnimation(new FloatValueHolder(this.indicatorTranslationX));
        COUISpringForce cOUISpringForce3 = new COUISpringForce();
        cOUISpringForce3.setBounce(companion.getSpringDragAnimatorConfig().bounce());
        cOUISpringForce3.setResponse(companion.getSpringDragAnimatorConfig().response());
        cOUISpringAnimation3.setSpring(cOUISpringForce3);
        OplusQSThreeStageLayout$ensureSpringAnimation$1$2 oplusQSThreeStageLayout$ensureSpringAnimation$1$3 = new OplusQSThreeStageLayout$ensureSpringAnimation$1$2(i2);
        oplusQSThreeStageLayout$ensureSpringAnimation$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation3.addUpdateListener(oplusQSThreeStageLayout$ensureSpringAnimation$1$3);
        this.springAnimationBounce = cOUISpringAnimation3;
        int i6 = 0;
        for (Object obj2 : this.iconViews) {
            int i7 = i6 + 1;
            if (i6 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            ImageView imageView3 = (ImageView) obj2;
            Drawable drawable = imageView3.getContext().getDrawable(this.iconResIds.get(i6).intValue());
            if (drawable != null) {
                imageView3.setImageDrawable(drawable);
                imageView3.setImageTintList(ColorStateList.valueOf(getActiveIconColor(false)));
            }
            i6 = i7;
        }
        OplusQSThreeStageLayout$iconViewTransitionTarget$1 oplusQSThreeStageLayout$iconViewTransitionTarget$1 = new OplusQSThreeStageLayout$iconViewTransitionTarget$1();
        oplusQSThreeStageLayout$iconViewTransitionTarget$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconViewTransitionTarget = oplusQSThreeStageLayout$iconViewTransitionTarget$1;
    }

    public static final void access$updateIndicator(OplusQSThreeStageLayout oplusQSThreeStageLayout) {
        int i = oplusQSThreeStageLayout.selectedIndex;
        if (i < 0 || i >= oplusQSThreeStageLayout.frameLayoutViews.size()) {
            return;
        }
        oplusQSThreeStageLayout.overDistance = (int) (((1.0f - oplusQSThreeStageLayout.indicatorScale) * oplusQSThreeStageLayout.indicatorView.getWidth()) / 2.0f);
        int i2 = oplusQSThreeStageLayout.selectedIndex;
        if (i2 == 0 || i2 == oplusQSThreeStageLayout.frameLayoutViews.size() - 1) {
            oplusQSThreeStageLayout.overDistance = oplusQSThreeStageLayout.getPaddingStart() + oplusQSThreeStageLayout.overDistance;
        } else {
            oplusQSThreeStageLayout.overDistance += oplusQSThreeStageLayout.switchDistance;
        }
        oplusQSThreeStageLayout.indicatorView.setTranslationX(oplusQSThreeStageLayout.indicatorTranslationX);
    }

    private final int getSelectedIndex() {
        return this.selectedIndex;
    }

    public final float calculateIndicatorTargetX(float f) {
        if (this.frameLayoutViews.isEmpty()) {
            return 0.0f;
        }
        ViewGroup.LayoutParams layoutParams = this.buttonContainer.getLayoutParams();
        int i = 0;
        float marginStart = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0;
        ViewGroup.LayoutParams layoutParams2 = this.buttonContainer.getLayoutParams();
        float width = ((this.buttonContainer.getWidth() - marginStart) - (layoutParams2 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginEnd() : 0)) / 3.0f;
        float width2 = this.indicatorView.getWidth();
        float width3 = this.frameLayoutViews.get(0).getWidth();
        float f2 = (width3 - width2) / 2.0f;
        boolean zIsLayoutRtl = isLayoutRtl();
        int i2 = this.selectedIndex;
        if (!zIsLayoutRtl) {
            i = i2;
        } else if (i2 == 0) {
            i = 2;
        } else if (i2 == 1) {
            i = 1;
        }
        float f3 = (i * width3) + marginStart + f2 + f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(width, i, "calculateIndicatorTargetX-->segmentWidth:", ", selected:", " targetX:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, " iconFrameSize:", width3, " offset:");
            Log.d("SystemUi--QuickSettings", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f2, " containerPaddingLeft:", marginStart, "OplusQSThreeStageLayout-->"));
        }
        return f3;
    }

    public final void clearRunnable() {
        Runnable runnable = this.longPressRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.longPressRunnable = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || action == 1 || action == 3) {
            clearRunnable();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void executeScaleAnimatorAt(int i, boolean z) {
        if (i < 0 || i >= this.pressScaleHelper.size()) {
            return;
        }
        this.pressScaleHelper.get(i).executeFeedbackAnimator(z);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x000f  */
    public final int getActiveIconColor(boolean z) {
        TileIconColorState tileIconThemeColorState = SepQSResPool.getTileIconThemeColorState();
        if (tileIconThemeColorState == null) {
            tileIconThemeColorState = SepQSResPool.getThreeStageIconColorState();
        } else {
            if (this.disableTheme) {
                tileIconThemeColorState = null;
            }
            if (tileIconThemeColorState == null) {
                tileIconThemeColorState = SepQSResPool.getThreeStageIconColorState();
            }
        }
        ColorStateList colorStateList = tileIconThemeColorState != null ? tileIconThemeColorState.getColorStateList(this.tileColorfulConfig) : null;
        if (colorStateList == null) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                return -1;
            }
            Log.d("SystemUi--QuickSettings", "OplusQSThreeStageLayout-->getActiveIconColor:null");
            return -1;
        }
        if (z) {
            return colorStateList.getColorForState(new int[]{TileViewFlag.Active.getAttr()}, colorStateList.getDefaultColor());
        }
        if (!z) {
            return colorStateList.getColorForState(new int[]{TileViewFlag.Inactive.getAttr()}, colorStateList.getDefaultColor());
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public final int getCurrentMode() {
        int i = this.selectedIndex;
        if (i == 0) {
            return 2;
        }
        if (i != 1) {
            return i != 2 ? -1 : 0;
        }
        return 1;
    }

    public final DrawableTransitionAlphaTarget getIconBgTransitionTarget() {
        return this.iconBgTransitionTarget;
    }

    public final float getIconTargetTranslationX(int i, int i2) {
        float width = this.frameLayoutViews.get(i).getWidth() * 0.1f;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0.0f;
                }
                if (!isLayoutRtl()) {
                    return -width;
                }
            } else {
                if (i2 == 1) {
                    return 0.0f;
                }
                if (isLayoutRtl()) {
                    if (i2 >= 1) {
                        return -width;
                    }
                } else if (i2 < 1) {
                    return -width;
                }
            }
        } else if (isLayoutRtl()) {
            return -width;
        }
        return width;
    }

    public final IIntTarget getIconViewTransitionTarget() {
        return this.iconViewTransitionTarget;
    }

    public final int getSelectedIndexFromX(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        int size = this.frameLayoutViews.size();
        for (int i = 0; i < size; i++) {
            FrameLayout frameLayout = this.frameLayoutViews.get(i);
            float rawX = motionEvent.getRawX();
            if (frameLayout != null && frameLayout.getVisibility() != 8) {
                frameLayout.getLocationOnScreen(iArr);
                float f = iArr[0];
                float width = frameLayout.getWidth() + f;
                float f2 = this.isDragging ? this.switchDistance : 0.0f;
                if (rawX >= f + f2 && rawX <= width - f2) {
                    return i;
                }
            }
        }
        return -1;
    }

    public final void handleTouchEnd(MotionEvent motionEvent) {
        int i;
        this.isTouchingSelectedIndex = false;
        if (this.isLongPressTriggered) {
            clearRunnable();
            this.isLongPressTriggered = false;
            return;
        }
        int i2 = this.touchIndex;
        if (i2 != this.selectedIndex && i2 >= 0) {
            selectSegmentAt(i2);
        }
        int i3 = this.lastTouchIndex;
        if (i3 < 0) {
            i3 = this.selectedIndex;
        }
        executeScaleAnimatorAt(i3, false);
        if (this.isDragging && (i = this.selectedIndex) >= 0 && i < this.frameLayoutViews.size()) {
            float fCalculateIndicatorTargetX = calculateIndicatorTargetX(getIconTargetTranslationX(i, i));
            float f = this.indicatorTranslationX;
            if (fCalculateIndicatorTargetX != f) {
                this.springAnimationBounce.setStartValue(f);
                this.springAnimationBounce.animateToFinalPosition(fCalculateIndicatorTargetX);
            }
        }
        this.isDragging = false;
        float x = motionEvent.getX();
        float width = this.container.getWidth() / 3.0f;
        if (x < width) {
            setTag(Integer.valueOf(isLayoutRtl() ? 0 : 2));
        } else if (x < 2.0f * width) {
            setTag(1);
        } else {
            setTag(Integer.valueOf(isLayoutRtl() ? 2 : 0));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onButtonClick: tag:", getTag(), "OplusQSThreeStageLayout-->", "SystemUi--QuickSettings");
        }
        this.viewClick.invoke();
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
        clearRunnable();
        this.isLongPressTriggered = false;
    }

    public final void onIconDrawableUpdate(boolean z) {
        if (z) {
            this.themeBgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighLightIconThemeBg().getValue(), this.indicatorView, false, 4));
        } else {
            this.bgDrawable.setDrawable(QSDrawableBuilder.build$default((QSDrawableBuilder) SepQSResPool.INSTANCE.getHighLightIconBg().getValue(), this.indicatorView, false, 6));
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isEditMode) {
            return true;
        }
        this.lastTouchIndex = this.touchIndex;
        this.touchIndex = getSelectedIndexFromX(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 2 && ((float) Math.hypot(motionEvent.getX() - this.startX, motionEvent.getY() - this.startY)) > this.touchSlop) {
                clearRunnable();
                if (!this.isDragging && this.isTouchingSelectedIndex && !this.isLongPressTriggered) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    this.isDragging = true;
                    this.lastX = motionEvent.getX();
                    this.indicatorLastTranslationX = this.indicatorTranslationX;
                    return true;
                }
            }
            return false;
        }
        this.startX = motionEvent.getX();
        this.startY = motionEvent.getY();
        this.isLongPressTriggered = false;
        clearRunnable();
        OplusQSThreeStageLayout$checkLongPress$1 oplusQSThreeStageLayout$checkLongPress$1 = new OplusQSThreeStageLayout$checkLongPress$1();
        oplusQSThreeStageLayout$checkLongPress$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.longPressRunnable = oplusQSThreeStageLayout$checkLongPress$1;
        postDelayed(oplusQSThreeStageLayout$checkLongPress$1, ViewConfiguration.getLongPressTimeout());
        motionEvent.getX();
        this.isDragging = false;
        this.isTouchingSelectedIndex = this.selectedIndex == this.touchIndex;
        this.indicatorTranslationX = this.indicatorView.getTranslationX();
        getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.isLayout) {
            return;
        }
        setTranslationXAndScale();
        this.isLayout = true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isEditMode) {
            return false;
        }
        this.lastTouchIndex = this.touchIndex;
        int selectedIndexFromX = getSelectedIndexFromX(motionEvent);
        this.touchIndex = selectedIndexFromX;
        executeScaleAnimatorAt(selectedIndexFromX, true);
        if (motionEvent.getAction() == 1) {
            executeScaleAnimatorAt(this.selectedIndex, false);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            motionEvent.getX();
            this.indicatorTranslationX = this.indicatorView.getTranslationX();
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
        if (actionMasked == 1) {
            handleTouchEnd(motionEvent);
            return true;
        }
        if (actionMasked == 2) {
            boolean z = this.isDragging;
            int i = this.touchIndex;
            if (!z) {
                int i2 = this.lastTouchIndex;
                if (i != i2) {
                    if (i2 < 0) {
                        i2 = this.preSelectedIndex;
                    }
                    executeScaleAnimatorAt(i2, false);
                }
            } else {
                if (i != this.selectedIndex && i >= 0) {
                    selectSegmentAt(i);
                    return true;
                }
                if (!((COUIDynamicAnimation) this.indicatorSpringTranslationX).mRunning) {
                    float x = motionEvent.getX();
                    if (this.indicatorTranslationEnd) {
                        this.lastX = x;
                        this.indicatorTranslationEnd = false;
                    }
                    float f = x - this.lastX;
                    if (f != 0.0f) {
                        float f2 = this.overDistance;
                        double[] dArr = {f};
                        if (this.results.length < 1) {
                            this.results = new double[1];
                        }
                        double d = f2;
                        if (d == 0.0d) {
                            this.results[0] = 0.0d;
                        } else {
                            this.results[0] = Math.signum(dArr[0]) * (1.0d - (1.0d / (((Math.abs(dArr[0]) * 0.11999999731779099d) / d) + 1.0d))) * d;
                        }
                        double[] dArr2 = this.results;
                        this.springAnimationBounce.setStartValue(this.indicatorTranslationX);
                        updateSpringConfig(this.springAnimationBounce);
                        this.springAnimationBounce.animateToFinalPosition(this.indicatorLastTranslationX + ((float) dArr2[0]));
                        return true;
                    }
                }
            }
        } else if (actionMasked == 3) {
            handleTouchEnd(motionEvent);
            return true;
        }
        return true;
    }

    public final void selectSegmentAt(int i) {
        int i2 = this.selectedIndex;
        if (i == i2) {
            return;
        }
        this.preSelectedIndex = i2;
        Iterator<T> it = this.frameLayoutViews.iterator();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                if ((this.selectedIndex < 0 && this.isLayout) || this.isEditMode) {
                    this.selectedIndex = i;
                    setTranslationXAndScale();
                    return;
                }
                this.selectedIndex = i;
                if (this.isLayout) {
                    int i5 = this.preSelectedIndex;
                    if (i5 >= 0 && i5 <= this.frameLayoutViews.size()) {
                        if (!QsColorUtil.isGlobalThemeApplied(getContext())) {
                            this.lottieViews.get(this.preSelectedIndex).cancelAnimation();
                        }
                        this.iconAlphaSpringAnimatorSets.get(this.preSelectedIndex).setIconSelected(getActiveIconColor(true), getActiveIconColor(false), false);
                    }
                    if (QsColorUtil.isGlobalThemeApplied(getContext())) {
                        tintIconColor(i, true);
                    } else {
                        int activeIconColor = getActiveIconColor(true);
                        int activeIconColor2 = getActiveIconColor(false);
                        if (!this.isEditMode) {
                            this.lottieViews.get(i).playLottieAnimation(this.iconViews.get(i), OpaqueKey$.ExternalSyntheticOutline0.m(this.lottieFileList.get(i), ASSETS_DIR_PREFIX), this.iconAlphaSpringAnimatorSets.get(i), activeIconColor, activeIconColor2);
                        }
                    }
                    for (Object obj : this.frameLayoutViews) {
                        int i6 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                            throw null;
                        }
                        FrameLayout frameLayout = (FrameLayout) obj;
                        float iconTargetTranslationX = getIconTargetTranslationX(i, i3);
                        if (this.isEditMode) {
                            frameLayout.setTranslationX(getTranslationX());
                        } else if (frameLayout.getTranslationX() != iconTargetTranslationX || ((COUIDynamicAnimation) this.iconSpringAnimatorSets.get(i3)).mRunning) {
                            this.iconSpringAnimatorSets.get(i3).animateToFinalPosition(iconTargetTranslationX);
                        }
                        if (i == i3) {
                            COUISpringAnimation cOUISpringAnimation = this.springAnimationBounce;
                            if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
                                cOUISpringAnimation.cancel();
                            }
                            float fCalculateIndicatorTargetX = calculateIndicatorTargetX(iconTargetTranslationX);
                            updateSpringConfig(this.indicatorSpringTranslationX);
                            if (fCalculateIndicatorTargetX != this.indicatorView.getTranslationX() || ((COUIDynamicAnimation) this.indicatorSpringTranslationX).mRunning) {
                                this.indicatorSpringTranslationX.animateToFinalPosition(fCalculateIndicatorTargetX);
                            }
                            this.indicatorLastTranslationX = fCalculateIndicatorTargetX;
                        }
                        float f = i3 == i ? 1.2f : 1.0f;
                        boolean z2 = this.isEditMode;
                        List<ThreeStageScaleSpringAnimationHelper> list = this.scaleSyncAnimatorHelper;
                        if (z2) {
                            list.get(i3).setScale(f);
                        } else {
                            list.get(i3).animateTo(f);
                        }
                        i3 = i6;
                    }
                    return;
                }
                return;
            }
            Object next = it.next();
            int i7 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            FrameLayout frameLayout2 = (FrameLayout) next;
            if (i4 != i) {
                z = false;
            }
            frameLayout2.setTag(2131364404, Boolean.valueOf(z));
            i4 = i7;
        }
    }

    public final void setEditMode(boolean z) {
        this.isEditMode = z;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.onClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
        this.onLongClickListener = onLongClickListener;
    }

    public final void setSelectedIndex(int i, CharSequence charSequence) {
        int i2 = 2;
        if (i != 0) {
            if (i != 1) {
                i2 = i != 2 ? -1 : 0;
            } else {
                i2 = 1;
            }
        }
        if (i2 >= 0 && i2 <= this.frameLayoutViews.size()) {
            this.frameLayoutViews.get(i2).setContentDescription(charSequence);
        }
        if (i2 != this.selectedIndex) {
            this.frameLayoutViews.get(i2).setContentDescription(charSequence);
            selectSegmentAt(i2);
        }
    }

    public final void setTranslationXAndScale() {
        if (this.selectedIndex >= 0) {
            int i = 0;
            for (Object obj : this.frameLayoutViews) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                float iconTargetTranslationX = getIconTargetTranslationX(this.selectedIndex, i);
                ((FrameLayout) obj).setTranslationX(iconTargetTranslationX);
                if (this.selectedIndex == i) {
                    this.indicatorView.setTranslationX(calculateIndicatorTargetX(iconTargetTranslationX));
                }
                float f = i == this.selectedIndex ? 1.2f : 1.0f;
                ImageView imageView = this.iconViews.get(i);
                imageView.setScaleX(f);
                imageView.setScaleY(f);
                tintIconColor(i, i == this.selectedIndex);
                i = i2;
            }
        }
    }

    public final void setViewClick(Function0 function0) {
        this.viewClick = function0;
    }

    public final void tintColor(Integer num) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSThreeStageLayout-->[tintColor]");
        }
        this.tileColorfulConfig = num;
        int i = 0;
        for (Object obj : this.frameLayoutViews) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            tintIconColor(i, i == this.selectedIndex);
            i = i2;
        }
    }

    public final void tintIconColor(int i, boolean z) {
        int activeIconColor = getActiveIconColor(z);
        ColorStateList imageTintList = this.iconViews.get(i).getImageTintList();
        if (imageTintList == null || imageTintList.getDefaultColor() != activeIconColor) {
            this.iconViews.get(i).setImageTintList(ColorStateList.valueOf(activeIconColor));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQSThreeStageLayout-->".concat("tintIconColor COLOR = " + Integer.toHexString(activeIconColor) + " index:" + i));
            }
        }
    }

    public final void updateContainerLayoutParams(int i, int i2) {
        LinearLayout linearLayout = this.buttonContainer;
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (layoutParams == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i2);
        }
        linearLayout.setLayoutParams(layoutParams);
    }

    public final void updateIconBgDrawable() {
        if (!BaseQSResPool.globalThemeApplied() || this.disableTheme) {
            this.indicatorBackground.setTransitionAlpha(255);
        } else {
            this.indicatorBackground.setTransitionAlpha(0);
        }
    }

    public final void updateIconViewSize(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            ViewGroup.LayoutParams layoutParams = this.iconViews.get(i2).getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = i;
                layoutParams.height = i;
            }
        }
    }

    public final void updateSpringConfig(COUISpringAnimation cOUISpringAnimation) {
        Spring springClickAnimatorConfig = Intrinsics.areEqual(cOUISpringAnimation, this.indicatorSpringTranslationX) ? Companion.getSpringClickAnimatorConfig() : Companion.getSpringDragAnimatorConfig();
        COUISpringForce spring = cOUISpringAnimation.getSpring();
        spring.setBounce(springClickAnimatorConfig.bounce());
        spring.setResponse(springClickAnimatorConfig.response());
        cOUISpringAnimation.setStartVelocity(springClickAnimatorConfig.velocity());
    }

    public OplusQSThreeStageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 4, 0);
    }

    public /* synthetic */ OplusQSThreeStageLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, (i & 2) != 0 ? null : attributeSet, 0);
    }

    public OplusQSThreeStageLayout(Context context) {
        this(context, null, 6, 0);
    }
}
