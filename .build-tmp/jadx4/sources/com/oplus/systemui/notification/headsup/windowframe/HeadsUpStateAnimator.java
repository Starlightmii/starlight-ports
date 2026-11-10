package com.oplus.systemui.notification.headsup.windowframe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpStateAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpAnimator2";
    private final AnimationProperties animationProperties;
    private final Context context;
    private final HeadsUpLayout headsUpLayout;
    private LogBuffer logger;
    public int mGoToFullShadeAppearingTranslation;
    public float mHeadsUpAppearStartAboveScreen;
    private final ExpandableViewState tmpState = new ExpandableViewState();
    private final AnimationFilter animationFilter = new AnimationFilter();
    private final HashSet<Animator> animatorSet = new HashSet<>();

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.HeadsUpStateAnimator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends AnimationProperties {
        public /* synthetic */ HeadsUpStateAnimator this$0;

        public AnimationFilter getAnimationFilter() {
            return this.this$0.animationFilter;
        }

        public final AnimatorListenerAdapter getAnimationFinishListener(Property property) {
            return this.this$0.getGlobalAnimationFinishedListener();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public HeadsUpStateAnimator(Context context, HeadsUpLayout headsUpLayout, LogBuffer logBuffer) {
        this.context = context;
        this.headsUpLayout = headsUpLayout;
        this.logger = logBuffer;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationProperties = anonymousClass1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnimatorListenerAdapter getGlobalAnimationFinishedListener() {
        return new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.headsup.windowframe.HeadsUpStateAnimator.getGlobalAnimationFinishedListener.1
            public boolean mWasCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mWasCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                HeadsUpStateAnimator.this.animatorSet.remove(animator);
                if (!HeadsUpStateAnimator.this.animatorSet.isEmpty() || this.mWasCancelled) {
                    return;
                }
                HeadsUpStateAnimator.this.headsUpLayout.runAnimationFinishedRunnables();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                this.mWasCancelled = false;
                HeadsUpStateAnimator.this.animatorSet.add(animator);
            }
        };
    }

    public static /* synthetic */ void getMGoToFullShadeAppearingTranslation$annotations() {
    }

    public static /* synthetic */ void getMHeadsUpAppearStartAboveScreen$annotations() {
    }

    public final int getMGoToFullShadeAppearingTranslation() {
        return this.mGoToFullShadeAppearingTranslation;
    }

    public final float getMHeadsUpAppearStartAboveScreen() {
        return this.mHeadsUpAppearStartAboveScreen;
    }

    public final void setMGoToFullShadeAppearingTranslation(int i) {
        this.mGoToFullShadeAppearingTranslation = i;
    }

    public final void setMHeadsUpAppearStartAboveScreen(float f) {
        this.mHeadsUpAppearStartAboveScreen = f;
    }

    public final void startAnimationForEvents(List<HeadsUpLayout.AnimationEvent> list) {
        this.animationFilter.reset();
        AnimationFilter animationFilter = this.animationFilter;
        animationFilter.animateHeight = true;
        animationFilter.animateTopInset = true;
        animationFilter.animateY = true;
        animationFilter.animateZ = true;
        for (HeadsUpLayout.AnimationEvent animationEvent : list) {
            ExpandableView mChangingView = animationEvent.getMChangingView();
            if (mChangingView != null) {
                this.animationProperties.duration = animationEvent.getLength();
                if (animationEvent.getAnimationType() == 11) {
                    this.tmpState.copyFrom(mChangingView.getViewState());
                    boolean headsUpFromBottom = animationEvent.getHeadsUpFromBottom();
                    ExpandableViewState expandableViewState = this.tmpState;
                    if (headsUpFromBottom) {
                        expandableViewState.setYTranslation(0.0f, "HeadsUpAnimator2.processAnimationEvents.1");
                    } else {
                        expandableViewState.setYTranslation(Math.min(-expandableViewState.height, 0.0f), "HeadsUpAnimator2.processAnimationEvents.2");
                    }
                    this.tmpState.applyToView(mChangingView);
                } else if (animationEvent.getAnimationType() == 12 || animationEvent.getAnimationType() == 13) {
                    this.tmpState.copyFrom(mChangingView.getViewState());
                } else if (animationEvent.getAnimationType() == 9) {
                    mChangingView.getViewState().setYTranslation(mChangingView.getTranslationY(), "HeadsUpAnimator2.processAnimationEvents.3");
                    ExifInterface$.ExternalSyntheticOutline1.m(mChangingView.getViewState().height, "processAnimationEvents: ", TAG);
                } else if (animationEvent.getAnimationType() == 10) {
                    AnimationFilter animationFilter2 = this.animationFilter;
                    animationFilter2.animateAlpha = true;
                    animationFilter2.animateTopInset = true;
                    Log.d(TAG, "processAnimationEvents: " + mChangingView.getViewState().height);
                    mChangingView.getViewState().setYTranslation(mChangingView.getTranslationY(), "HeadsUpAnimator2.processAnimationEvents.4");
                }
                mChangingView.getViewState().animateTo(mChangingView, this.animationProperties);
            }
        }
    }
}
