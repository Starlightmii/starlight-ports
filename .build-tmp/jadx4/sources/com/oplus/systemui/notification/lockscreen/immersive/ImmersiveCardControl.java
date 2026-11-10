package com.oplus.systemui.notification.lockscreen.immersive;

import android.os.Bundle;
import android.util.Log;
import androidx.collection.FloatList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.notification.lockscreen.statemanager.FocusCard;
import com.oplus.systemui.notification.lockscreen.statemanager.ICardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.SeedlingFocusCard;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImmersiveCardControl implements ICardControl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ImmersiveCardControl";
    private final Lazy capsuleCardControl;
    private FocusCard focusCard;
    private final Lazy immersiveViewManager;
    private final Lazy keyguardStateController;
    private FocusCard lastFocusCard;
    private final Lazy lockScreenExtStateController;
    private final Lazy nsslc;
    private final Lazy seedlingViewModel;
    private final SysuiStatusBarStateController statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl$hide$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements Runnable {
        public /* synthetic */ ImmersiveCardControl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.clearFocusCard("OnHideImmerFinished");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl$show$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ SeedlingFocusCard $seedlingCard;
        public /* synthetic */ ImmersiveCardControl this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ((OplusImmersiveViewManager) this.this$0.immersiveViewManager.get()).unBindForStack(this.$seedlingCard.getStackRow());
                    break;
                case 1:
                    ((OplusImmersiveViewManager) this.this$0.immersiveViewManager.get()).unBindForStack(this.$seedlingCard.getStackRow());
                    break;
                default:
                    ((OplusImmersiveViewManager) this.this$0.immersiveViewManager.get()).unBindForStack(this.$seedlingCard.getStackRow());
                    break;
            }
        }
    }

    public ImmersiveCardControl(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy5, Lazy lazy6) {
        this.immersiveViewManager = lazy;
        this.capsuleCardControl = lazy2;
        this.lockScreenExtStateController = lazy3;
        this.nsslc = lazy4;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardStateController = lazy5;
        this.seedlingViewModel = lazy6;
    }

    private final void setFocusCard(FocusCard focusCard) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[focusCard]:", focusCard != null ? focusCard.getKey() : null, "ImmersiveCardControl-->", "SystemUi--Notification");
        }
        FocusCard focusCard2 = this.focusCard;
        if (!Intrinsics.areEqual(focusCard2 != null ? focusCard2.getKey() : null, focusCard != null ? focusCard.getKey() : null)) {
            this.lastFocusCard = this.focusCard;
        }
        this.focusCard = focusCard;
        ((CapsuleCardControl) this.capsuleCardControl.get()).updateImmersiveCard(focusCard != null ? focusCard.getKey() : null, "setFocusCard");
    }

    public final void clearFocusCard(String str) {
        if (this.focusCard == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("clearFocusCard:", str, "ImmersiveCardControl-->", "SystemUi--Notification");
        }
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState()) {
            return;
        }
        setFocusCard(null);
    }

    public final Integer currImmersiveBgType(ILockScreenExtStateController.ExtStateEvent extStateEvent) {
        String immersiveKey;
        OplusCustomRow oplusCustomRowFindStackView;
        CustomRowEntry entry;
        Bundle extra = extStateEvent.getExtra();
        if (extra == null || (immersiveKey = ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra)) == null || (oplusCustomRowFindStackView = ((SeedlingViewModel) this.seedlingViewModel.get()).findStackView(immersiveKey)) == null || (entry = oplusCustomRowFindStackView.getEntry()) == null) {
            return null;
        }
        return Integer.valueOf(LockScreenSeedingInteractor.Companion.getBgType(entry));
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public ExpandableView findSeedingView(String str) {
        FocusCard focusCard = this.focusCard;
        if (Intrinsics.areEqual(str, focusCard != null ? focusCard.getKey() : null)) {
            FocusCard focusCard2 = this.focusCard;
            SeedlingFocusCard seedlingFocusCard = focusCard2 instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard2 : null;
            if (seedlingFocusCard != null) {
                return seedlingFocusCard.getStackRow();
            }
        }
        return null;
    }

    public final FocusCard getFocusCard() {
        return this.focusCard;
    }

    public final int getImmersiveCardBottomMargin() {
        return CapsuleConfig.INSTANCE.getCardMarginBottom() + ((CapsuleCardControl) this.capsuleCardControl.get()).getCapsuleTopFromBottom();
    }

    public final FocusCard getLastFocusCard() {
        return this.lastFocusCard;
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public int getViewTypeFlag() {
        return 4;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void hide(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "ImmersiveCardControl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("hideTo:", getViewTypeFlag(), " animate:", " from:", z));
        }
        if (z) {
            FocusCard focusCard = this.focusCard;
            SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
            if (seedlingFocusCard != null) {
                ((OplusImmersiveViewManager) this.immersiveViewManager.get()).bindForStack(seedlingFocusCard.getStackRow(), false);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) this.nsslc.get();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
                anonymousClass2.this$0 = this;
                anonymousClass2.$seedlingCard = seedlingFocusCard;
                VarHandle.storeStoreFence();
                notificationStackScrollLayoutController.runAfterAnimationFinished$1(anonymousClass2);
            }
        }
        FocusCard focusCard2 = this.focusCard;
        SeedlingFocusCard seedlingFocusCard2 = focusCard2 instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard2 : null;
        if (seedlingFocusCard2 != null) {
            NotificationSpotLightDelegate.Companion.markLazyParamsKind(seedlingFocusCard2.getStackRow(), NotificationSpotLightParamsKind.NOTIFICATION_SEEDLING_CARD);
        }
        if (z) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = (NotificationStackScrollLayoutController) this.nsslc.get();
            AnonymousClass4 anonymousClass4 = new AnonymousClass4();
            anonymousClass4.this$0 = this;
            VarHandle.storeStoreFence();
            notificationStackScrollLayoutController2.runAfterAnimationFinished$1(anonymousClass4);
        } else {
            setFocusCard(null);
        }
        ((CapsuleCardControl) this.capsuleCardControl.get()).updateImmersiveCard(null, "hideTo first");
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).setSeedingDataForBg(null, null, z);
    }

    public final boolean isCurrentlyAnimating() {
        return ((OplusImmersiveViewManager) this.immersiveViewManager.get()).isCurrentlyAnimating();
    }

    public final boolean isRemoved(String str) {
        return ((SeedlingViewModel) this.seedlingViewModel.get()).isRemoved(str);
    }

    public final boolean isTouchWithinFocusCard(float f, float f2) {
        LaunchableView stackRow;
        FocusCard focusCard = this.focusCard;
        SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
        if (seedlingFocusCard != null && (stackRow = seedlingFocusCard.getStackRow()) != null) {
            int[] iArr = new int[2];
            stackRow.getLocationOnScreen(iArr);
            int i = iArr[0];
            if (f >= i && f <= stackRow.getWidth() + i) {
                int i2 = iArr[1];
                if (f2 >= i2 && f2 <= stackRow.getHeight() + i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean needRemoveSurfaceViewBg() {
        return this.statusBarStateController.getState() == 0 && !((KeyguardStateController) this.keyguardStateController.get()).mShowing;
    }

    public final void onShadeState() {
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).onShadeState();
    }

    public final void setImmersiveCardKey(String str) {
        OplusCustomRow oplusCustomRowFindStackView = ((SeedlingViewModel) this.seedlingViewModel.get()).findStackView(str);
        if (oplusCustomRowFindStackView == null) {
            setFocusCard(null);
            return;
        }
        CustomRowEntry entry = oplusCustomRowFindStackView.getEntry();
        if (entry == null) {
            setFocusCard(null);
        } else {
            setFocusCard(new SeedlingFocusCard(entry, oplusCustomRowFindStackView));
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void show(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            FocusCard focusCard = this.focusCard;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[show]:", i, i2, " -> ", " animate:");
            DeviceItem$.ExternalSyntheticOutline0.m(" from:", str, " focusCard:", sbM, z);
            sbM.append(focusCard);
            Log.d("SystemUi--Notification", "ImmersiveCardControl-->".concat(sbM.toString()));
        }
        FocusCard focusCard2 = this.focusCard;
        SeedlingFocusCard seedlingFocusCard = focusCard2 instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard2 : null;
        if (seedlingFocusCard == null) {
            return;
        }
        if (z) {
            ((OplusImmersiveViewManager) this.immersiveViewManager.get()).bindForStack(seedlingFocusCard.getStackRow(), true);
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) this.nsslc.get();
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
            anonymousClass2.this$0 = this;
            anonymousClass2.$seedlingCard = seedlingFocusCard;
            VarHandle.storeStoreFence();
            notificationStackScrollLayoutController.runAfterAnimationFinished$1(anonymousClass2);
        }
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(seedlingFocusCard.getStackRow(), NotificationSpotLightParamsKind.NOTIFICATION_SEEDLING_IMMERSIVE_CARD);
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).setSeedingDataForBg(seedlingFocusCard.getData(), seedlingFocusCard.getStackRow(), z);
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    /* JADX INFO: renamed from: switch */
    public void mo73switch(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "ImmersiveCardControl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("switch:", getViewTypeFlag(), " animate:", " from:", z));
        }
        if (z) {
            FocusCard focusCard = this.focusCard;
            SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
            if (seedlingFocusCard != null) {
                ((OplusImmersiveViewManager) this.immersiveViewManager.get()).bindForStack(seedlingFocusCard.getStackRow(), false);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) this.nsslc.get();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(2);
                anonymousClass2.this$0 = this;
                anonymousClass2.$seedlingCard = seedlingFocusCard;
                VarHandle.storeStoreFence();
                notificationStackScrollLayoutController.runAfterAnimationFinished$1(anonymousClass2);
            }
        }
        ((OplusImmersiveViewManager) this.immersiveViewManager.get()).setSeedingDataForBg(null, null, z);
        show(i, i2, z, str);
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public /* bridge */ /* synthetic */ void updateViewStateToExtState(int i, List list) {
        super.updateViewStateToExtState(i, list);
    }
}
