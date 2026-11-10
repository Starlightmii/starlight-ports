package com.oplus.systemui.statusbar.event;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.animation.Animator;
import androidx.core.animation.PathInterpolator;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.statusbar.event.OplusSystemEventChipAnimationControllerEx;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemEventChipAnimationControllerExImpl extends OplusSystemEventChipAnimationControllerEx {
    public static final Companion Companion = new Companion();
    private static final PathInterpolator PATH_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.33f, 1.0f);
    private FrameLayout animationWindowView;
    private final Context context;
    private BackgroundAnimatableView currentAnimatedView;
    public boolean initialized;
    private final StatusBarWindowController statusBarWindowController;
    public ContextThemeWrapper themedContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSystemEventChipAnimationControllerExImpl(Context context, StatusBarWindowController statusBarWindowController) {
        super(context, statusBarWindowController);
        this.context = context;
        this.statusBarWindowController = statusBarWindowController;
    }

    public void announceForAccessibility(String str) {
        View view;
        BackgroundAnimatableView backgroundAnimatableView = this.currentAnimatedView;
        if (backgroundAnimatableView == null || (view = backgroundAnimatableView.getView()) == null) {
            return;
        }
        view.setStateDescription(str);
    }

    public final void init() {
        this.initialized = true;
        this.themedContext = new ContextThemeWrapper(this.context, 2132019272);
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.statusBarWindowController;
        if (statusBarWindowControllerImpl != null) {
            this.animationWindowView = (FrameLayout) statusBarWindowControllerImpl.getStatusBarWindowView().findViewById(2131364645);
        }
    }

    public /* bridge */ /* synthetic */ Animator onHidePersistentDot() {
        return super/*com.android.systemui.statusbar.events.SystemStatusAnimationCallback*/.onHidePersistentDot();
    }

    public Animator onSystemEventAnimationBegin() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        valueAnimatorOfFloat.setDuration(333L);
        valueAnimatorOfFloat.setInterpolator(PATH_INTERPOLATOR);
        OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1 oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1 = new OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1();
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1.this$0 = this;
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1.$this_apply = valueAnimatorOfFloat;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1);
        return valueAnimatorOfFloat;
    }

    public Animator onSystemEventAnimationFinish(boolean z) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        valueAnimatorOfFloat.setDuration(333L);
        valueAnimatorOfFloat.setInterpolator(PATH_INTERPOLATOR);
        BackgroundAnimatableView backgroundAnimatableView = this.currentAnimatedView;
        OngoingPrivacyChip ongoingPrivacyChip = null;
        View view = backgroundAnimatableView != null ? backgroundAnimatableView.getView() : null;
        OngoingPrivacyChip childAt = view instanceof OngoingPrivacyChip ? (OngoingPrivacyChip) view : null;
        if (childAt != null) {
            while (childAt instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childAt;
                if (viewGroup.getChildCount() == 0) {
                    break;
                }
                childAt = viewGroup.getChildAt(0);
                if (childAt instanceof ImageView) {
                    ongoingPrivacyChip = childAt;
                    break;
                }
                childAt.getClass();
            }
        }
        int width = ongoingPrivacyChip != null ? ongoingPrivacyChip.getWidth() : 0;
        int height = ongoingPrivacyChip != null ? ongoingPrivacyChip.getHeight() : 0;
        OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1 oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1 = new OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1();
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1.$this_apply = valueAnimatorOfFloat;
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1.this$0 = this;
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1.$actualAnimatedView = ongoingPrivacyChip;
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1.$startWidth = width;
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1.$startHeight = height;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1);
        OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1 oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1 = new OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1();
        oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addListener(oplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1);
        return valueAnimatorOfFloat;
    }

    public /* bridge */ /* synthetic */ Animator onSystemStatusAnimationTransitionToPersistentDot(String str, List list) {
        return super/*com.android.systemui.statusbar.events.SystemStatusAnimationCallback*/.onSystemStatusAnimationTransitionToPersistentDot(str, list);
    }

    public void prepareChipAnimation(Function1 function1) {
        if (!this.initialized) {
            init();
        }
        ContextThemeWrapper contextThemeWrapper = this.themedContext;
        if (contextThemeWrapper == null) {
            contextThemeWrapper = null;
        }
        BackgroundAnimatableView backgroundAnimatableView = (BackgroundAnimatableView) function1.invoke(contextThemeWrapper);
        FrameLayout frameLayout = this.animationWindowView;
        if (frameLayout != null) {
            View view = backgroundAnimatableView.getView();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 8388629;
            frameLayout.addView(view, layoutParams);
            backgroundAnimatableView.getView().setAlpha(0.0f);
        }
        this.currentAnimatedView = backgroundAnimatableView;
    }
}
