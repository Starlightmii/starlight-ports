package com.oplus.systemui.panelanimation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.core.view.insets.Protection$;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.separate.SeparatePanelAnimation;
import com.oplus.systemui.statusbar.notification.stack.NotificationSeedingContainer;
import com.oplus.systemui.statusbar.notification.stack.NotificationSeedingContainerController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LevelPositionAlgorithmImpl extends LevelPositionAlgorithm {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LevelPositionAlgorithmImpl";
    private final Context context;
    public float currentFraction;
    public float fluidDelta;
    public int fluidViewsSize;
    private final SpringAnimation fractionAnimation;
    public boolean inTracking;
    private final Handler mainHandler;
    private final Lazy notificationPanelViewController;
    private final Lazy panelAnimationEx;
    public int qsViewsSize;
    private final SeparatePanelAnimation separatePanelAnimation;
    private final SysuiStatusBarStateController statusBarStateController;
    public int screenHeight = 2440;
    public float maxPositionForFirstItem = 400.0f;
    private final ReboundAnimation reboundAnimation = new ReboundAnimation();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.LevelPositionAlgorithmImpl$initAlgorithm$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ int $height;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NotificationStackScrollLayout $stackScrollLayout;
        public /* synthetic */ LevelPositionAlgorithmImpl this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.initSeparateTracing(this.$stackScrollLayout, this.$height);
                    break;
                default:
                    this.this$0.initAnimationAndNSSL(this.$height, this.$stackScrollLayout, false);
                    break;
            }
        }
    }

    public LevelPositionAlgorithmImpl(Context context, Handler handler, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy, Lazy lazy2) {
        this.context = context;
        this.mainHandler = handler;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.notificationPanelViewController = lazy;
        this.panelAnimationEx = lazy2;
        this.separatePanelAnimation = new SeparatePanelAnimation(context, lazy);
        SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
        springAnimation.setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(2000.0f));
        springAnimation.setStartValue(0.0f);
        springAnimation.setMinimumVisibleChange(0.001f);
        LevelPositionAlgorithmImpl$fractionAnimation$1$1 levelPositionAlgorithmImpl$fractionAnimation$1$1 = new LevelPositionAlgorithmImpl$fractionAnimation$1$1();
        levelPositionAlgorithmImpl$fractionAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addUpdateListener(levelPositionAlgorithmImpl$fractionAnimation$1$1);
        this.fractionAnimation = springAnimation;
    }

    private final int getCurrentSeedingSize() {
        NotificationSeedingContainer containerView = ((NotificationSeedingContainerController) DependencyEx.sDependency.getDependency(NotificationSeedingContainerController.class)).getContainerView();
        if (containerView == null) {
            return 0;
        }
        Sequence seedingCards = NotificationSeedingContainer.Companion.getSeedingCards(containerView);
        Integer numValueOf = seedingCards != null ? Integer.valueOf(SequencesKt___SequencesKt.count(seedingCards)) : null;
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    private final boolean getUseLayeredAnimation() {
        return FeatureOption.getUseLayeredPanelAnimation();
    }

    public final float getFirstViewMoveDistance() {
        if (!getUseLayeredAnimation() || !isInPanelAnimation()) {
            return 0.0f;
        }
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return getSeparatePanelAnimation().getOverScrollHeight();
        }
        float gapWithIndex = this.currentFraction * this.maxPositionForFirstItem;
        int index = getIndex(0, 2);
        int i = 1;
        if (1 <= index) {
            while (true) {
                gapWithIndex += getGapWithIndex(i);
                if (i == index) {
                    break;
                }
                i++;
            }
        }
        return gapWithIndex;
    }

    public final float getGapWithIndex(int i) {
        float f = this.maxPositionForFirstItem * this.currentFraction;
        if (i == 0) {
            return f;
        }
        if (i == 1) {
            return f * 0.2f;
        }
        if (i == 2) {
            return f * 0.2f * 0.9f;
        }
        if (3 > i || i >= 13) {
            return 0.0f;
        }
        return (float) (Math.pow(0.9d, i - 1) * ((double) (f * 0.2f)));
    }

    public final int getIndex(int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = this.qsViewsSize;
        } else {
            if (i2 != 2) {
                return i;
            }
            i += this.qsViewsSize;
            i3 = this.fluidViewsSize;
        }
        return i + i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float getPaddingWithIndex(int i, int i2, View view) {
        LevelPositionAlgorithmImpl levelPositionAlgorithmImpl;
        if (!getUseLayeredAnimation()) {
            return 0.0f;
        }
        int index = getIndex(i, i2);
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return getSeparatePanelAnimation().getPaddingWithIndex(index);
        }
        this.fluidDelta = 0.0f;
        if (i2 == 2 && (view instanceof NotificationSeedingContainer) && getCurrentSeedingSize() > 1) {
            levelPositionAlgorithmImpl = this;
            ((NotificationSeedingContainer) view).updateSeedlingCardLayeredPosition(new LevelPositionAlgorithmImpl$checkNeedUpdateFluidView$1(1, levelPositionAlgorithmImpl, LevelPositionAlgorithmImpl.class, "getFluidGapWithIndex", "getFluidGapWithIndex(I)F", 0));
        } else {
            levelPositionAlgorithmImpl = this;
        }
        if (i2 == 0) {
            if (levelPositionAlgorithmImpl.isInPanelAnimation()) {
                return levelPositionAlgorithmImpl.getGapWithIndex(index);
            }
            return 0.0f;
        }
        float gapWithIndex = levelPositionAlgorithmImpl.getGapWithIndex(index);
        if (i >= 1 && i2 == 2) {
            float f = levelPositionAlgorithmImpl.fluidDelta;
            if (f > 0.0f) {
                gapWithIndex += f;
            }
        }
        if (levelPositionAlgorithmImpl.isInPanelAnimation()) {
            return gapWithIndex;
        }
        return 0.0f;
    }

    public float getScrollHeight() {
        return this.currentFraction * this.maxPositionForFirstItem;
    }

    public void initAlgorithm(float f, int i, NotificationStackScrollLayout notificationStackScrollLayout) {
        int i2 = 0;
        int i3 = 1;
        boolean z = this.statusBarStateController.getState() == 1;
        if (!getUseLayeredAnimation() || z) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("initAlgorithm return, isOnKeyguard: ", z), null, 8);
            return;
        }
        if (((ILockScreenExtStateController) DependencyEx.sDependency.getDependency(ILockScreenExtStateController.class)).backToKgFirstWhenTouchOnKgNtfCenter()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "initAlgorithm return, stackScrollLayout ownScrollY is 0", null, 8);
            return;
        }
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            this.maxPositionForFirstItem = i * 1.0f;
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                initSeparateTracing(notificationStackScrollLayout, i);
                return;
            }
            Handler handler = this.mainHandler;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i2);
            anonymousClass1.this$0 = this;
            anonymousClass1.$height = i;
            anonymousClass1.$stackScrollLayout = notificationStackScrollLayout;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
            return;
        }
        this.maxPositionForFirstItem = i * 0.2f;
        if (f > 0.1f) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, Protection$.ExternalSyntheticOutline0.m(f, "initAlgorithm, expandedFraction: "), null, 8);
            return;
        }
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            initAnimationAndNSSL(i, notificationStackScrollLayout, true);
            return;
        }
        Handler handler2 = this.mainHandler;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(i3);
        anonymousClass2.this$0 = this;
        anonymousClass2.$height = i;
        anonymousClass2.$stackScrollLayout = notificationStackScrollLayout;
        VarHandle.storeStoreFence();
        handler2.post(anonymousClass2);
    }

    public final void initAnimationAndNSSL(int i, NotificationStackScrollLayout notificationStackScrollLayout, boolean z) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("initAlgorithm, height: ", i, ", isMainLooper: ", z), null, 8);
        this.screenHeight = i;
        if (getReboundAnimation().isInReboundAnimation()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "initAlgorithm, cancel rebound animation", null, 8);
            getReboundAnimation().cancelReboundAnimation();
        }
        this.inTracking = true;
        this.fractionAnimation.animateToFinalPosition(0.0f);
        this.fractionAnimation.skipToEnd();
        getReboundAnimation().initStackScrollLayout(notificationStackScrollLayout);
    }

    public final void initSeparateTracing(NotificationStackScrollLayout notificationStackScrollLayout, int i) {
        getSeparatePanelAnimation().startTracking(this.context, notificationStackScrollLayout, this.maxPositionForFirstItem, i);
        this.qsViewsSize = 1;
    }

    public final boolean isInPanelAnimation() {
        if (!getUseLayeredAnimation()) {
            return false;
        }
        SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
        if (separateModeHelper.getSeparateMode() ? getSeparatePanelAnimation().getInTracking() : this.inTracking) {
            return true;
        }
        return getUseLayeredAnimation() && (separateModeHelper.getSeparateMode() ? getSeparatePanelAnimation().isInReboundAnimation() : getReboundAnimation().isInReboundAnimation());
    }

    public final void releaseAlgorithm(float f) {
        if (getUseLayeredAnimation()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "releaseAlgorithm intracking " + this.inTracking + ", vel: " + f, null, 8);
            if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
                this.fluidViewsSize = 0;
                if (this.inTracking) {
                    this.inTracking = false;
                    if (this.fractionAnimation.isRunning()) {
                        this.fractionAnimation.cancel();
                    }
                    getReboundAnimation().startAnimation(this.currentFraction, f);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                getSeparatePanelAnimation().stopTracking();
                return;
            }
            Handler handler = this.mainHandler;
            releaseAlgorithm.1 r0 = new releaseAlgorithm.1();
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r0);
        }
    }

    public final void updateFraction(float f, boolean z, boolean z2) {
        boolean z3 = this.statusBarStateController.getState() == 1;
        boolean separateMode = SeparateModeHelper.INSTANCE.getSeparateMode();
        boolean z4 = z3 && (separateMode || f != 0.0f);
        if (!getUseLayeredAnimation() || z4) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sb = new StringBuilder("updateFraction return, fraction: ");
                sb.append(f);
                sb.append(",isBreakCollapse: ");
                sb.append(z2);
                sb.append(", isOnKeyguard: ");
                Log.d("SystemUi--Common", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", isSeparateMode: ", "LevelPositionAlgorithmImpl-->", sb, z3, separateMode));
                return;
            }
            return;
        }
        float rawFraction = ((PanelAnimationEx) this.panelAnimationEx.get()).getPanelExpandFraction().getRawFraction();
        if (rawFraction <= 0.0f) {
            return;
        }
        if (separateMode) {
            getSeparatePanelAnimation().setInTracking(true);
            getSeparatePanelAnimation().updateMoveDistance(f > 0.0f ? this.screenHeight * f : 0.0f, rawFraction, rawFraction > 0.86f);
        } else {
            if (!z && !this.inTracking) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "LevelPositionAlgorithmImpl-->need not update fraction for event");
                    return;
                }
                return;
            }
            if (z) {
                this.currentFraction = f;
            } else {
                if (this.currentFraction == f) {
                    return;
                }
                this.fractionAnimation.animateToFinalPosition(f);
            }
        }
    }

    public ReboundAnimation getReboundAnimation() {
        return this.reboundAnimation;
    }

    public SeparatePanelAnimation getSeparatePanelAnimation() {
        return this.separatePanelAnimation;
    }
}
