package com.oplus.systemui.plugins.qs.guide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.content.Context;
import android.support.v4.content.FileProvider$;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.fragment.app.Fragment$5$;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualView extends ConstraintLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsGuideSpiritualView";
    public static boolean isDebug;
    private final COUIEaseInterpolator alphaInterpolator;
    private AnimationCallBack animationCallBack;
    private final FloatEvaluator floatEvaluator;
    public boolean isNotifAnimationPlaying;
    public boolean isQsAnimationPlaying;
    public boolean isQsScenarioAssistantAnimationPlaying;
    private final COUIMoveEaseInterpolator moveInterpolator;
    private Animator notifAnimator;
    private TextView notificationTextView;
    private OplusQSEffectView notificationView;
    private Animator qsAnimator;
    private View qsScenarioAssistantMask;
    private OplusQSEffectView qsScenarioAssistantView;
    private TextView qsTextView;
    private OplusQSEffectView qsView;
    public final float textOffset;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AnimationCallBack {
        void onAnimationCancel(AnimationType animationType);

        void onAnimationEnd(AnimationType animationType);

        void onAnimationStart(AnimationType animationType);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimationType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimationType[] $VALUES;
        public static final AnimationType NOTIFICATION;
        public static final AnimationType QS;
        public static final AnimationType SCENARIO_ASSISTANT;

        static {
            AnimationType animationType = new AnimationType("NOTIFICATION", 0);
            NOTIFICATION = animationType;
            AnimationType animationType2 = new AnimationType(OplusFakeViewProvider.QS, 1);
            QS = animationType2;
            AnimationType animationType3 = new AnimationType("SCENARIO_ASSISTANT", 2);
            SCENARIO_ASSISTANT = animationType3;
            AnimationType[] animationTypeArr = {animationType, animationType2, animationType3};
            $VALUES = animationTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animationTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimationType valueOf(String str) {
            return (AnimationType) Enum.valueOf(AnimationType.class, str);
        }

        public static AnimationType[] values() {
            return (AnimationType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void setDebug(boolean z) {
            OplusQsGuideSpiritualView.isDebug = z;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQsGuideSpiritualView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.alphaInterpolator = new COUIEaseInterpolator();
        this.moveInterpolator = new COUIMoveEaseInterpolator();
        this.floatEvaluator = new FloatEvaluator();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideSpiritualView--> init");
        }
        LayoutInflater.from(context).inflate(2131559347, (ViewGroup) this);
        setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -1));
        setClickable(false);
        setClipChildren(false);
        setClipToPadding(false);
        this.notificationView = (OplusQSEffectView) findViewById(2131363283);
        this.notificationTextView = (TextView) findViewById(2131363282);
        this.qsTextView = (TextView) findViewById(2131363286);
        this.qsView = (OplusQSEffectView) findViewById(2131363287);
        this.textOffset = DeviceUtil.dp2px(context, 3.0f);
        AnimationType animationType = AnimationType.NOTIFICATION;
        initView(animationType);
        initAnimator(animationType);
        AnimationType animationType2 = AnimationType.QS;
        initView(animationType2);
        initAnimator(animationType2);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportScenarioAssistant()) {
            this.qsScenarioAssistantView = (OplusQSEffectView) findViewById(2131363284);
            this.qsScenarioAssistantMask = findViewById(2131363285);
            AnimationType animationType3 = AnimationType.SCENARIO_ASSISTANT;
            initView(animationType3);
            initAnimator(animationType3);
        }
    }

    public static final void access$setAnimationPlaying(OplusQsGuideSpiritualView oplusQsGuideSpiritualView, AnimationType animationType, boolean z) {
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            oplusQsGuideSpiritualView.isNotifAnimationPlaying = z;
            return;
        }
        if (iOrdinal == 1) {
            oplusQsGuideSpiritualView.isQsAnimationPlaying = z;
        } else if (iOrdinal == 2) {
            oplusQsGuideSpiritualView.isQsScenarioAssistantAnimationPlaying = z;
        } else {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getScreenHeight() {
        return getContext().getResources().getConfiguration().windowConfiguration.getBounds().height();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getScreenWidth() {
        return getContext().getResources().getConfiguration().windowConfiguration.getBounds().width();
    }

    public final void cancelAnimation(OplusQsGuideSpiritualViewController.GuideFlag guideFlag) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "cancelAnimation guideFlag=" + guideFlag, null, 8);
        if (guideFlag == OplusQsGuideSpiritualViewController.GuideFlag.FLAG_SCENARIO_ASSISTANT_CANCEL) {
            cancelAnimation(AnimationType.SCENARIO_ASSISTANT);
            return;
        }
        cancelAnimation(AnimationType.NOTIFICATION);
        cancelAnimation(AnimationType.QS);
        if (guideFlag == OplusQsGuideSpiritualViewController.GuideFlag.FLAG_SCENARIO_ASSISTANT || guideFlag != OplusQsGuideSpiritualViewController.GuideFlag.FLAG_SCENARIO_ASSISTANT_KEEP) {
            cancelAnimation(AnimationType.SCENARIO_ASSISTANT);
        }
    }

    public final OplusQSEffectView effectView(AnimationType animationType) {
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            return this.notificationView;
        }
        if (iOrdinal == 1) {
            return this.qsView;
        }
        if (iOrdinal == 2) {
            return this.qsScenarioAssistantView;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final void initAnimator(final AnimationType animationType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initAnimator: " + animationType, null, 8);
        final OplusQSEffectView oplusQSEffectViewEffectView = effectView(animationType);
        if (oplusQSEffectViewEffectView != null) {
            oplusQSEffectViewEffectView.addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualView$initAnimator$1$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualView", "onAnimationCancel: " + animationType, null, 8);
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView = this;
                    OplusQsGuideSpiritualView.AnimationType animationType2 = animationType;
                    OplusQsGuideSpiritualView.Companion companion = OplusQsGuideSpiritualView.Companion;
                    TextView textView = oplusQsGuideSpiritualView.textView(animationType2);
                    if (textView != null) {
                        textView.setAlpha(0.0f);
                    }
                    oplusQSEffectViewEffectView.setAlpha(0.0f);
                    OplusQsGuideSpiritualView.AnimationCallBack animationCallBack = this.animationCallBack;
                    if (animationCallBack != null) {
                        ((OplusQsGuideSpiritualViewController$animationCallBack$1) animationCallBack).onAnimationCancel(animationType);
                    }
                    OplusQsGuideSpiritualView.access$setAnimationPlaying(this, animationType, false);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    OplusQsGuideSpiritualView.AnimationCallBack animationCallBack;
                    super.onAnimationEnd(animator);
                    LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualView", "onAnimationEnd: " + animationType, null, 8);
                    OplusQsGuideSpiritualView oplusQsGuideSpiritualView = this;
                    OplusQsGuideSpiritualView.AnimationType animationType2 = animationType;
                    OplusQsGuideSpiritualView.Companion companion = OplusQsGuideSpiritualView.Companion;
                    TextView textView = oplusQsGuideSpiritualView.textView(animationType2);
                    if (textView != null) {
                        textView.setAlpha(0.0f);
                    }
                    oplusQSEffectViewEffectView.setAlpha(0.0f);
                    if (this.isAnimationPlaying(animationType) && (animationCallBack = this.animationCallBack) != null) {
                        ((OplusQsGuideSpiritualViewController$animationCallBack$1) animationCallBack).onAnimationEnd(animationType);
                    }
                    OplusQsGuideSpiritualView.access$setAnimationPlaying(this, animationType, false);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualView", "onAnimationStart: " + animationType, null, 8);
                    oplusQSEffectViewEffectView.setAlpha(1.0f);
                    OplusQsGuideSpiritualView.AnimationCallBack animationCallBack = this.animationCallBack;
                    if (animationCallBack != null) {
                        ((OplusQsGuideSpiritualViewController$animationCallBack$1) animationCallBack).onAnimationStart(animationType);
                    }
                    OplusQsGuideSpiritualView.access$setAnimationPlaying(this, animationType, true);
                }
            });
            OplusQsGuideSpiritualView$initAnimator$1$2 oplusQsGuideSpiritualView$initAnimator$1$2 = new OplusQsGuideSpiritualView$initAnimator$1$2();
            oplusQsGuideSpiritualView$initAnimator$1$2.this$0 = this;
            oplusQsGuideSpiritualView$initAnimator$1$2.$effectView = oplusQSEffectViewEffectView;
            oplusQsGuideSpiritualView$initAnimator$1$2.$type = animationType;
            VarHandle.storeStoreFence();
            oplusQSEffectViewEffectView.addPositionListener(oplusQsGuideSpiritualView$initAnimator$1$2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x005b  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void initView(AnimationType animationType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("initView: ", animationType.name()), null, 8);
        int screenWidth = getScreenWidth();
        float f = screenWidth;
        float f2 = 0.25f * f;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172145);
        if (animationType == AnimationType.SCENARIO_ASSISTANT) {
            f2 = ((f * 0.5f) - 120.0f) - dimensionPixelSize;
        }
        boolean z = getContext().getResources().getConfiguration().getLayoutDirection() == 1;
        int iOrdinal = animationType.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                } else if (z) {
                    f2 = -f2;
                }
            } else if (z) {
                f2 = -f2;
            }
        } else if (!z) {
            f2 = -f2;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m("getTranslationX:", animationType.name(), " -- ", f2, " screenWidth="), screenWidth, dimensionPixelSize, " marginToRight="), null, 8);
        int iOrdinal2 = animationType.ordinal();
        if (iOrdinal2 != 0 && iOrdinal2 != 1 && iOrdinal2 != 2) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return;
        }
        float fDp2px = DeviceUtil.dp2px(getContext(), -133.0f);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getTranslationY:" + animationType.name() + " -- " + fDp2px, null, 8);
        OplusQSEffectView oplusQSEffectViewEffectView = effectView(animationType);
        if (oplusQSEffectViewEffectView != null) {
            oplusQSEffectViewEffectView.setAlpha(0.0f);
            oplusQSEffectViewEffectView.setTranslationX(f2);
            oplusQSEffectViewEffectView.setTranslationY(fDp2px);
        }
        TextView textView = textView(animationType);
        if (textView != null) {
            textView.setAlpha(0.0f);
            textView.setTranslationX(f2);
            textView.setTranslationY(fDp2px);
            textView.setMaxWidth(getScreenWidth() / 3);
        }
    }

    public final boolean isAnimationPlaying(AnimationType animationType) {
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            return this.isNotifAnimationPlaying;
        }
        if (iOrdinal == 1) {
            return this.isQsAnimationPlaying;
        }
        if (iOrdinal == 2) {
            return this.isQsScenarioAssistantAnimationPlaying;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return false;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("onSizeChanged ", i, i2, ", ", ", "), i3, i4, ", "), null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void playAnimation(AnimationType animationType) {
        CharSequence text;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "playAnimation: " + animationType, null, 8);
        AnimationType animationType2 = AnimationType.SCENARIO_ASSISTANT;
        OplusQSEffectView oplusQSEffectViewEffectView = effectView(animationType2);
        AnimatorSet animators = oplusQSEffectViewEffectView != null ? oplusQSEffectViewEffectView.getAnimators() : null;
        OplusQSEffectView oplusQSEffectViewEffectView2 = effectView(AnimationType.NOTIFICATION);
        AnimatorSet animators2 = oplusQSEffectViewEffectView2 != null ? oplusQSEffectViewEffectView2.getAnimators() : null;
        OplusQSEffectView oplusQSEffectViewEffectView3 = effectView(AnimationType.QS);
        AnimatorSet animators3 = oplusQSEffectViewEffectView3 != null ? oplusQSEffectViewEffectView3.getAnimators() : null;
        if (animators == null || !animators.isStarted()) {
            if (animators == null || !animators.isRunning()) {
                if (animators2 == null || !animators2.isStarted()) {
                    if (animators2 == null || !animators2.isRunning()) {
                        if (animators3 == null || !animators3.isStarted()) {
                            if ((animators3 != null && animators3.isRunning()) || this.isNotifAnimationPlaying || this.isQsAnimationPlaying || this.isQsScenarioAssistantAnimationPlaying) {
                                return;
                            }
                            View view = this.qsScenarioAssistantMask;
                            if (view != null) {
                                view.setVisibility(animationType == animationType2 ? 0 : 8);
                            }
                            OplusQSEffectView oplusQSEffectViewEffectView4 = effectView(animationType);
                            if (oplusQSEffectViewEffectView4 != null) {
                                oplusQSEffectViewEffectView4.startEffect();
                            }
                            LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m(" announceForAccessibility: ", animationType.name()), null, 8);
                            OplusQSEffectView oplusQSEffectViewEffectView5 = effectView(animationType);
                            if (oplusQSEffectViewEffectView5 != null) {
                                int iOrdinal = animationType.ordinal();
                                if (iOrdinal == 0) {
                                    text = getContext().getText(2131955158);
                                } else {
                                    if (iOrdinal != 1 && iOrdinal != 2) {
                                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                                        return;
                                    }
                                    text = getContext().getText(2131955185);
                                }
                                text.getClass();
                                oplusQSEffectViewEffectView5.sendAccessibilityEvent(8);
                                oplusQSEffectViewEffectView5.announceForAccessibility(text);
                            }
                        }
                    }
                }
            }
        }
    }

    public final void release() {
        View view = this.qsScenarioAssistantMask;
        if (view != null) {
            view.setVisibility(8);
        }
        OplusQSEffectView oplusQSEffectView = this.qsView;
        if (oplusQSEffectView != null) {
            oplusQSEffectView.releaseEffect();
        }
        OplusQSEffectView oplusQSEffectView2 = this.notificationView;
        if (oplusQSEffectView2 != null) {
            oplusQSEffectView2.releaseEffect();
        }
        OplusQSEffectView oplusQSEffectView3 = this.qsScenarioAssistantView;
        if (oplusQSEffectView3 != null) {
            oplusQSEffectView3.releaseEffect();
        }
    }

    public final void setCallBack(AnimationCallBack animationCallBack) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setCallBack:", null, 8);
        this.animationCallBack = animationCallBack;
    }

    public final TextView textView(AnimationType animationType) {
        int iOrdinal = animationType.ordinal();
        if (iOrdinal == 0) {
            return this.notificationTextView;
        }
        if (iOrdinal == 1) {
            return this.qsTextView;
        }
        if (iOrdinal == 2) {
            return null;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final void cancelAnimation(AnimationType animationType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "cancelAnimation: " + animationType, null, 8);
        View view = this.qsScenarioAssistantMask;
        if (view != null) {
            view.setVisibility(8);
        }
        OplusQSEffectView oplusQSEffectViewEffectView = effectView(animationType);
        if (oplusQSEffectViewEffectView != null) {
            oplusQSEffectViewEffectView.cancelEffect();
        }
    }

    public OplusQsGuideSpiritualView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public OplusQsGuideSpiritualView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public /* synthetic */ OplusQsGuideSpiritualView(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    public OplusQsGuideSpiritualView(Context context) {
        this(context, null, 0, 14, 0);
    }
}
