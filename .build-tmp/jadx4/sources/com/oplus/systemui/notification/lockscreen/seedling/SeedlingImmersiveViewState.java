package com.oplus.systemui.notification.lockscreen.seedling;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.exifinterface.media.ExifInterface$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingImmersiveViewState extends ExpandableViewState {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeedlingImmersiveViewState";
    public final int cardType;
    public int fromExtState;
    public int immersiveState;
    public final boolean isBg;
    public int reason;
    public int toExtState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SeedlingImmersiveViewState(int i) {
        this.cardType = i;
        this.isBg = i == 3;
    }

    public static /* synthetic */ void getFromExtState$annotations() {
    }

    public static /* synthetic */ void getToExtState$annotations() {
    }

    public final void animateTo(final ImmersiveViewEntry immersiveViewEntry, AnimationProperties animationProperties, Rect rect, Rect rect2) {
        if (immersiveViewEntry.getImmersiveState() == this.immersiveState) {
            cancelAnimations(immersiveViewEntry);
            return;
        }
        boolean normalDebug = LogUtil.getNormalDebug();
        int i = this.cardType;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(immersiveViewEntry.getImmersiveState(), this.immersiveState, OplusPreventModeData.SEPARATOR, "SeedlingImmersiveViewState-->", KeyguardLogger$.ExternalSyntheticOutline0.m(i, "animateTo(", "): ", immersiveViewEntry.getKey(), OplusPreventModeData.SEPARATOR)));
        }
        ImmersiveViewEntry.AnimationInfo animatorInfo = immersiveViewEntry.getAnimatorInfo();
        String key = immersiveViewEntry.getKey();
        boolean z = animatorInfo != null;
        int i2 = this.fromExtState;
        int i3 = this.toExtState;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "startImmersiveStateAnimation(", "): ", key, OplusPreventModeData.SEPARATOR);
        PromptIconState$.ExternalSyntheticOutline0.m(i2, OplusPreventModeData.SEPARATOR, "->", sbM, z);
        Fragment$5$.ExternalSyntheticOutline0.m(i3, TAG, sbM);
        Integer numValueOf = animatorInfo != null ? Integer.valueOf(animatorInfo.getEndState()) : null;
        int i4 = this.immersiveState;
        if (numValueOf != null && numValueOf.intValue() == i4 && (i == 3 || animatorInfo.getEndExtState() == this.toExtState)) {
            return;
        }
        if (animatorInfo != null) {
            ExifInterface$.ExternalSyntheticOutline1.m("startImmersiveStateAnimation(", i, i4, "):do newEndValue=", TAG);
            animatorInfo.setEndExtState(this.toExtState);
            animatorInfo.setEndState(i4);
            LockScreenSeedingInteractor.Companion companion = LockScreenSeedingInteractor.Companion;
            companion.onPreChange(immersiveViewEntry.getEntry(), this.isBg, this.fromExtState, this.toExtState, this.reason, true);
            companion.onChangeStart(immersiveViewEntry.getEntry(), this.isBg, this.fromExtState, this.toExtState, this.reason, true);
            View customView = immersiveViewEntry.getEntry().getCustomView();
            if (animatorInfo.getDelayRunnable() != null) {
                customView.removeCallbacks(animatorInfo.getDelayRunnable());
                animatorInfo.setDelayRunnable(null);
            } else if (!animatorInfo.getAnimator().isRunning) {
                Path$.ExternalSyntheticBUOutline0.m$1("Animator is running not ");
                return;
            }
            animatorInfo.getAnimator().animateToState(1, this.fromExtState, this.toExtState, this.cardType, rect, rect2);
            return;
        }
        LockScreenSeedingInteractor.Companion companion2 = LockScreenSeedingInteractor.Companion;
        companion2.onPreChange(immersiveViewEntry.getEntry(), this.isBg, this.fromExtState, this.toExtState, this.reason, true);
        companion2.onChangeStart(immersiveViewEntry.getEntry(), this.isBg, this.fromExtState, this.toExtState, this.reason, true);
        ImmersiveViewEntry.AnimationInfo animationInfo = new ImmersiveViewEntry.AnimationInfo(this.immersiveState, this.toExtState, new SeedingImmersiveAnimator(companion2.getImmersiverBgAnimator(immersiveViewEntry.getEntry(), this.cardType, this.fromExtState, this.toExtState, rect, rect2)), null);
        AnimatorListenerAdapter animationFinishListener = animationProperties != null ? animationProperties.getAnimationFinishListener(ImmersiveViewEntry.Companion.getIMMERSIVE_PROPERTY()) : null;
        if (animationFinishListener != null) {
            animationInfo.getAnimator().addListener(animationFinishListener);
        }
        animationInfo.getAnimator().addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState$startImmersiveStateAnimation$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Log.d("SeedlingImmersiveViewState", "onAnimationEnd(" + this.this$0.getCardType() + ") " + immersiveViewEntry.getKey());
                immersiveViewEntry.setImmersiveState(this.this$0.getImmersiveState());
                immersiveViewEntry.setAnimatorInfo(null);
                LockScreenSeedingInteractor.Companion companion3 = LockScreenSeedingInteractor.Companion;
                CustomRowEntry entry = immersiveViewEntry.getEntry();
                SeedlingImmersiveViewState seedlingImmersiveViewState = this.this$0;
                companion3.onChangeEnd(entry, seedlingImmersiveViewState.isBg, seedlingImmersiveViewState.getFromExtState(), this.this$0.getToExtState(), this.this$0.getReason(), true);
            }
        });
        if (animationFinishListener != null) {
            animationFinishListener.onAnimationStart(animationInfo.getAnimator());
        }
        immersiveViewEntry.setImmersiveState(4);
        immersiveViewEntry.setAnimatorInfo(animationInfo);
        SeedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1 seedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1 = new SeedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1();
        seedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1.$info = animationInfo;
        VarHandle.storeStoreFence();
        if ((animationProperties != null ? animationProperties.delay : 0L) <= 0 || animationInfo.getAnimator().isRunning) {
            seedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1.run();
        } else {
            animationInfo.setDelayRunnable(seedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1);
            immersiveViewEntry.getEntry().getCustomView().postDelayed(seedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1, animationProperties != null ? animationProperties.delay : 0L);
        }
    }

    public final void applyTo(ImmersiveViewEntry immersiveViewEntry) {
        ImmersiveViewEntry.AnimationInfo animatorInfo = immersiveViewEntry.getAnimatorInfo();
        int i = this.cardType;
        if (animatorInfo != null) {
            if (animatorInfo.getEndState() != this.immersiveState) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(animatorInfo.getEndState(), this.immersiveState, OplusPreventModeData.SEPARATOR, "SeedlingImmersiveViewState-->", KeyguardLogger$.ExternalSyntheticOutline0.m(i, "applyTo(", "): ", immersiveViewEntry.getKey(), OplusPreventModeData.SEPARATOR)));
                }
                animateTo(immersiveViewEntry, null, null, null);
                return;
            }
            return;
        }
        if (immersiveViewEntry.getImmersiveState() != this.immersiveState) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(this.immersiveState, "SeedlingImmersiveViewState-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m(i, "applyTo(", "),no old: ", immersiveViewEntry.getKey(), OplusPreventModeData.SEPARATOR));
            }
            LockScreenSeedingInteractor.Companion.onPreChange(immersiveViewEntry.getEntry(), this.isBg, this.fromExtState, this.toExtState, this.reason, false);
            immersiveViewEntry.setImmersiveState(this.immersiveState);
        }
    }

    public final void cancelAnimations(ImmersiveViewEntry immersiveViewEntry) {
        SeedingImmersiveAnimator animator;
        ImmersiveViewEntry.AnimationInfo animatorInfo = immersiveViewEntry.getAnimatorInfo();
        if (animatorInfo == null || (animator = animatorInfo.getAnimator()) == null) {
            return;
        }
        animator.cancel();
    }

    public void copyFrom(ViewState viewState) {
        if (viewState instanceof SeedlingImmersiveViewState) {
            this.immersiveState = ((SeedlingImmersiveViewState) viewState).immersiveState;
        }
    }

    public final int getCardType() {
        return this.cardType;
    }

    public final int getFromExtState() {
        return this.fromExtState;
    }

    public final int getImmersiveState() {
        return this.immersiveState;
    }

    public final int getReason() {
        return this.reason;
    }

    public final int getToExtState() {
        return this.toExtState;
    }

    public final void setFromExtState(int i) {
        this.fromExtState = i;
    }

    public final void setImmersiveState(int i) {
        this.immersiveState = i;
    }

    public final void setReason(int i) {
        this.reason = i;
    }

    public final void setToExtState(int i) {
        this.toExtState = i;
    }
}
