package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.AnimAfsManager;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.base.OplusCapsuleLog;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleViewModel;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.FocusCard;
import com.oplus.systemui.notification.lockscreen.statemanager.ICardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.SeedlingFocusCard;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleCardControl implements ICardControl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleCardControl";
    private final Lazy capsuleContainer;
    private final Lazy capsuleContainerController;
    private final OplusCapsuleLog capsuleLog;
    private final Lazy capsuleViewModel;
    private final Context context;
    public boolean eventChangeToAnimation;
    private final Lazy immersiveCardControl;
    public boolean isAllowToShowNotificationCapsule = true;
    private final Lazy lockScreenSeedingInteractor;
    public boolean needShow;
    private final Lazy nssl;
    private final Lazy stackCardControl;
    public boolean waitingAnimationNotifyState;
    private Integer waitingAnimationState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl$show$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements Runnable {
        public /* synthetic */ boolean $animate;
        public /* synthetic */ int $newState;
        public /* synthetic */ CapsuleCardControl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.$newState != 2) {
                ((ImmersiveCardControl) this.this$0.immersiveCardControl.get()).clearFocusCard("capsule show from stack");
            }
            ((NotificationCapsuleContainerControllerImpl) this.this$0.capsuleContainerController.get()).generateStackAnimation(((NotificationCapsuleContainer) this.this$0.capsuleContainer.get()).getBaseCard(), ((StackCardControl) this.this$0.stackCardControl.get()).getFirstCardForCapsule(), this.$animate, false);
        }
    }

    public CapsuleCardControl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, OplusCapsuleLog oplusCapsuleLog) {
        this.context = context;
        this.capsuleContainer = lazy;
        this.capsuleContainerController = lazy2;
        this.nssl = lazy3;
        this.stackCardControl = lazy4;
        this.immersiveCardControl = lazy5;
        this.capsuleViewModel = lazy6;
        this.lockScreenSeedingInteractor = lazy7;
        this.capsuleLog = oplusCapsuleLog;
    }

    private final FocusCard getFocusCard() {
        return ((ImmersiveCardControl) this.immersiveCardControl.get()).getFocusCard();
    }

    private final FocusCard getLastFocusCard() {
        return ((ImmersiveCardControl) this.immersiveCardControl.get()).getLastFocusCard();
    }

    private final void setWaitingAnimationState(Integer num) {
        if (Intrinsics.areEqual(this.waitingAnimationState, num)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Integer num2 = this.waitingAnimationState;
            boolean z = this.waitingAnimationNotifyState;
            StringBuilder sb = new StringBuilder("waitingAnimationState:");
            sb.append(num2);
            sb.append(" ->");
            sb.append(num);
            sb.append(",current=");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("CapsuleCardControl-->", "SystemUi--Notification", sb, z);
        }
        if (num == null) {
            if (this.waitingAnimationNotifyState && !((ImmersiveCardControl) this.immersiveCardControl.get()).isCurrentlyAnimating()) {
                ((LockScreenSeedingInteractor) this.lockScreenSeedingInteractor.get()).notifyExtAnimationStateChanged(false);
                this.waitingAnimationNotifyState = false;
            }
        } else if (this.waitingAnimationState == null && !this.waitingAnimationNotifyState) {
            this.waitingAnimationNotifyState = true;
            ((LockScreenSeedingInteractor) this.lockScreenSeedingInteractor.get()).notifyExtAnimationStateChanged(true);
        }
        this.eventChangeToAnimation = this.waitingAnimationState == null && num != null;
        this.waitingAnimationState = num;
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public ExpandableView findSeedingView(String str) {
        return ((NotificationCapsuleContainer) this.capsuleContainer.get()).findCapsuleSeedingViewByServiceId(str);
    }

    public final CapsuleItemView getBaseCard() {
        return ((NotificationCapsuleContainer) this.capsuleContainer.get()).getBaseCard();
    }

    public final Rect getCapsulePosition() {
        int itemWidth = getItemWidth();
        int width = (((NotificationCapsuleContainer) this.capsuleContainer.get()).getWidth() - itemWidth) / 2;
        int capsuleTop = getCapsuleTop();
        return new Rect(width, capsuleTop, itemWidth + width, getItemHeight() + capsuleTop);
    }

    public final int getCapsuleTop() {
        Context context = this.context;
        return (context.getResources().getConfiguration().windowConfiguration.getBounds().height() - ((NotificationCapsuleContainer) this.capsuleContainer.get()).getMarginBottom()) - CapsuleConfig.INSTANCE.getItemHeight();
    }

    public final int getCapsuleTopFromBottom() {
        return CapsuleConfig.INSTANCE.getItemHeight() + ((NotificationCapsuleContainer) this.capsuleContainer.get()).getMarginBottom();
    }

    public final CapsuleItemView getCurrentShowingCard() {
        return ((NotificationCapsuleContainer) this.capsuleContainer.get()).getCurrentShowingCard();
    }

    public final boolean getEventChangeToAnimation() {
        return this.eventChangeToAnimation;
    }

    public final int getItemHeight() {
        return CapsuleConfig.INSTANCE.getItemHeight();
    }

    public final int getItemWidth() {
        return CapsuleViewExpandKtKt.getCurrentCardShowWidth((NotificationCapsuleContainer) this.capsuleContainer.get());
    }

    public final float getStackY() {
        return ((NotificationCapsuleContainer) this.capsuleContainer.get()).getStackY();
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public int getViewTypeFlag() {
        return 2;
    }

    public final boolean hasSeedingData() {
        return ((CapsuleViewModel) this.capsuleViewModel.get()).hasSeedingData();
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void hide(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "CapsuleCardControl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("hide:", getViewTypeFlag(), " animate:", " from:", z));
        }
        setWaitingAnimationState(Integer.valueOf(i2));
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).checkIfNeedCancelHScroll("CapsuleCardControl:hide:" + getViewTypeFlag());
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).requestCancelTouchFeedback(false);
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).resetScrollByY();
        if (i2 == 0 || i2 == 3) {
            if (i != 2) {
                ((ImmersiveCardControl) this.immersiveCardControl.get()).clearFocusCard("capsule hide to stack");
            }
            updateCapsuleShowing(false);
            ((NotificationCapsuleContainerControllerImpl) this.capsuleContainerController.get()).generateStackAnimation(((NotificationCapsuleContainer) this.capsuleContainer.get()).getBaseCard(), ((StackCardControl) this.stackCardControl.get()).getFirstCardForCapsule(), z, true);
            ((NotificationCapsuleContainer) this.capsuleContainer.get()).resetDate("hide and not keep self");
        }
    }

    public final boolean isInCapsuleArea(float f, float f2) {
        int itemWidth = getItemWidth();
        int width = (((NotificationCapsuleContainer) this.capsuleContainer.get()).getWidth() - itemWidth) / 2;
        if (f > itemWidth + width || f < width) {
            return false;
        }
        int height = ((NotificationCapsuleContainer) this.capsuleContainer.get()).getHeight() - getCapsuleTopFromBottom();
        return f2 <= ((float) (getItemHeight() + height)) && f2 >= ((float) height);
    }

    public final boolean isLockScreenExtStateAnimRunning() {
        return this.waitingAnimationState != null;
    }

    public final boolean isStaticShowIng() {
        OplusExpandableViewEx oplusExpandableViewEx;
        if (((NotificationCapsuleContainer) this.capsuleContainer.get()).getAnimatorHelper().getAmbientState().isStackState) {
            return false;
        }
        CapsuleItemView baseCard = ((NotificationCapsuleContainer) this.capsuleContainer.get()).getBaseCard();
        return ((baseCard == null || (oplusExpandableViewEx = ((ExpandableView) baseCard).mExpandableViewEx) == null) ? 1.0f : oplusExpandableViewEx.getCapsuleProgress()) == 1.0f;
    }

    public final void onCapsuleStateAnimFinished() {
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).onNsslAnimationEnd();
        setWaitingAnimationState(null);
        if (this.capsuleLog.isTransitionAnimating) {
            this.capsuleLog.onStackAnimationEnd();
            AnimAfsManager.INSTANCE.scheduleAnimationCtrl(600, false);
        }
    }

    public final void onImmerStateAnimFinished() {
        if (this.waitingAnimationState == null && this.waitingAnimationNotifyState) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleCardControl-->onImmerStateAnimFinished:needReset");
            }
            ((LockScreenSeedingInteractor) this.lockScreenSeedingInteractor.get()).notifyExtAnimationStateChanged(false);
            this.waitingAnimationNotifyState = false;
        }
    }

    public final void requestUpdateChild(String str) {
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).requestChildrenUpdate(str);
    }

    public final void setAllowToShowNotificationCapsule(boolean z) {
        this.isAllowToShowNotificationCapsule = z;
        updateCapsuleShowing(this.needShow);
    }

    /* JADX WARN: Code duplicated, block: B:49:0x011d  */
    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void show(int i, int i2, boolean z, String str) {
        setWaitingAnimationState(Integer.valueOf(i2));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "CapsuleCardControl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("showFrom:", getViewTypeFlag(), " animate:", " from:", z));
        }
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).resetScrollByY();
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).checkIfNeedCancelHScroll("CapsuleCardControl:showFrom:" + getViewTypeFlag());
        Iterator<T> it = CapsuleViewExpandKtKt.getCapsuleChildren((NotificationCapsuleContainer) this.capsuleContainer.get()).iterator();
        while (it.hasNext()) {
            ((CapsuleItemView) it.next()).cancelStrokeAnimation();
        }
        if (i == 0) {
            NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) this.nssl.get();
            AnonymousClass5 anonymousClass5 = new AnonymousClass5();
            anonymousClass5.$newState = i2;
            anonymousClass5.this$0 = this;
            anonymousClass5.$animate = z;
            VarHandle.storeStoreFence();
            notificationStackScrollLayout.tryAddPreDrawBeforeNssl(anonymousClass5);
        } else if (i == 2) {
            FocusCard lastFocusCard = i2 == 2 ? getLastFocusCard() : getFocusCard();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sb = new StringBuilder("CapsuleCardControl-->");
                FocusCard focusCard = getFocusCard();
                String key = focusCard != null ? focusCard.getKey() : null;
                FocusCard lastFocusCard2 = getLastFocusCard();
                String key2 = lastFocusCard2 != null ? lastFocusCard2.getKey() : null;
                String key3 = lastFocusCard != null ? lastFocusCard.getKey() : null;
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("showFrom:immersive, focus ", key, ",last ", key2, ",card =");
                sbM.append(key3);
                sbM.append(",isEntre =false");
                sb.append(sbM.toString());
                Log.d("SystemUi--Notification", sb.toString());
            }
            CapsuleItemView capsuleItemViewFindCapsuleSeedingView = ((NotificationCapsuleContainer) this.capsuleContainer.get()).findCapsuleSeedingView(lastFocusCard != null ? lastFocusCard.getKey() : null);
            if (capsuleItemViewFindCapsuleSeedingView != null) {
                SeedlingFocusCard seedlingFocusCard = lastFocusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) lastFocusCard : null;
                OplusCustomRow stackRow = seedlingFocusCard != null ? seedlingFocusCard.getStackRow() : null;
                NotificationStackScrollLayout notificationStackScrollLayout2 = (NotificationStackScrollLayout) this.nssl.get();
                CapsuleCardControl$show$4$1 capsuleCardControl$show$4$1 = new CapsuleCardControl$show$4$1();
                capsuleCardControl$show$4$1.this$0 = this;
                capsuleCardControl$show$4$1.$view = capsuleItemViewFindCapsuleSeedingView;
                capsuleCardControl$show$4$1.$stackView = stackRow;
                capsuleCardControl$show$4$1.$animate = z;
                VarHandle.storeStoreFence();
                notificationStackScrollLayout2.tryAddPreDrawBeforeNssl(capsuleCardControl$show$4$1);
            }
        } else if (i == 3) {
            NotificationStackScrollLayout notificationStackScrollLayout3 = (NotificationStackScrollLayout) this.nssl.get();
            AnonymousClass5 anonymousClass6 = new AnonymousClass5();
            anonymousClass6.$newState = i2;
            anonymousClass6.this$0 = this;
            anonymousClass6.$animate = z;
            VarHandle.storeStoreFence();
            notificationStackScrollLayout3.tryAddPreDrawBeforeNssl(anonymousClass6);
        }
        updateCapsuleShowing(true);
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    /* JADX INFO: renamed from: switch, reason: not valid java name */
    public void mo73switch(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "CapsuleCardControl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("switch:", getViewTypeFlag(), " animate:", " from:", z));
        }
        setWaitingAnimationState(Integer.valueOf(i2));
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).checkIfNeedCancelHScroll("CapsuleCardControl:switch:" + getViewTypeFlag());
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).requestCancelTouchFeedback(false);
        if (i2 == 2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                FocusCard focusCard = getFocusCard();
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("switch: EXT_STATE_IMMERSIVE , need ", focusCard != null ? focusCard.getKey() : null, ",isEntre =true", "CapsuleCardControl-->", "SystemUi--Notification");
            }
            NotificationCapsuleContainer notificationCapsuleContainer = (NotificationCapsuleContainer) this.capsuleContainer.get();
            FocusCard focusCard2 = getFocusCard();
            CapsuleItemView capsuleItemViewFindCapsuleSeedingView = notificationCapsuleContainer.findCapsuleSeedingView(focusCard2 != null ? focusCard2.getKey() : null);
            if (capsuleItemViewFindCapsuleSeedingView != null) {
                FocusCard focusCard3 = getFocusCard();
                SeedlingFocusCard seedlingFocusCard = focusCard3 instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard3 : null;
                ((NotificationCapsuleContainerControllerImpl) this.capsuleContainerController.get()).generateImmersiveAnimation(capsuleItemViewFindCapsuleSeedingView, seedlingFocusCard != null ? seedlingFocusCard.getStackRow() : null, z, true);
            }
        }
        show(i, i2, z, str);
    }

    public final void updateCapsuleShowing(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("updateCapsuleShowing: isShow=", " ", "CapsuleCardControl-->", z, this.isAllowToShowNotificationCapsule));
        }
        this.needShow = z;
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).setShowing(z && this.isAllowToShowNotificationCapsule);
    }

    public final void updateImmersiveCard(String str, String str2) {
        ((NotificationCapsuleContainer) this.capsuleContainer.get()).updateImmersiveCard(((NotificationCapsuleContainer) this.capsuleContainer.get()).findCapsuleSeedingView(str), str2);
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public /* bridge */ /* synthetic */ void updateViewStateToExtState(int i, List list) {
        super.updateViewStateToExtState(i, list);
    }
}
