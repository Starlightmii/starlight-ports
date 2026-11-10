package com.oplus.systemui.notification.lockscreen.statemanager;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.AnimAfsManager;
import com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.LockScreenExpandOriginController;
import com.oplus.systemui.notification.lockscreen.base.OplusCapsuleLog;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataChangeInterceptor;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationExtStateManager implements ILockScreenExtStateController {
    public static final Companion Companion = new Companion();
    private static final Map<Integer, Integer> STATE_MAPPING = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(0, 1), TuplesKt.to(3, 1), TuplesKt.to(1, 2), TuplesKt.to(2, 7)});
    private static final String TAG = "LockScreenExtStateController";
    private final MutableStateFlow _extStateEvent;
    private final Lazy capsuleCardControl;
    private final Lazy capsuleContainerController;
    private final OplusCapsuleLog eventLog;
    private final StateFlow extStateEvent;
    private String focusCardKey;
    private final Lazy immersiveCardControl;
    public boolean isKeyguardState;
    private final LockScreenNotificationExtStateManager$keyguardCallback$1 keyguardCallback;
    private final Lazy keyguardStateController;
    private final Lazy keyguardUnlockAnimationController;
    private final Lazy keyguardUpdateMonitor;
    public int lastExtState;
    public long lastImmersiveSwitchTime;
    public boolean lastKeyguardOccluded;
    public int lastLockScreenNotificationStyle = -1;
    private final Lazy lockScreenSeedingInteractor;
    private final kotlin.Lazy notificationDisplayRectCallbackWrapper$delegate;
    private final Lazy notificationKeyguardHelper;
    private final Lazy notificationsQSContainerController;
    private final Lazy nsslController;
    private Function1 onExtStateChanged;
    private final IOplusGcSupressionExt oplusGcSupressionExt;
    private final Lazy oplusImmersiveController;
    private final Lazy oplusLockscreenShadeTransitionController;
    private final Lazy oplusPanoramicAodRepository;
    private final Lazy seedlingViewModel;
    private final Lazy stackAlgorithmManager;
    private final Lazy stackCardControl;
    private final SysuiStatusBarStateController statusBarStateController;
    private final WakefulnessLifecycle wakefulnessLifecycle;
    private final WakefulnessLifecycle.Observer wakefulnessObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ LockScreenNotificationExtStateManager this$0;

        public final void onStateChanged(int i) {
            LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
            if (i != 0 || ((KeyguardStateController) lockScreenNotificationExtStateManager.keyguardStateController.get()).mShowing) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockScreenExtStateController-->[onStateChanged]: handleExitKeyguard");
            }
            LockScreenNotificationExtStateManager.access$handleExitKeyguard(lockScreenNotificationExtStateManager);
        }

        public final void onStatePreChange(int i, int i2) {
            super.onStatePreChange(i, i2);
            if (i == 1) {
                if (i2 == 0 || i2 == 2) {
                    ((NotificationCapsuleContainerControllerImpl) this.this$0.capsuleContainerController.get()).getView().unBindToStackView(null, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "onStatePreChange:", LogUtil.SEPARATOR));
                }
            }
        }
    }

    public LockScreenNotificationExtStateManager(SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, WakefulnessLifecycle wakefulnessLifecycle, Lazy lazy13, OplusCapsuleLog oplusCapsuleLog, Lazy lazy14, Lazy lazy15, Lazy lazy16) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.nsslController = lazy;
        this.oplusLockscreenShadeTransitionController = lazy2;
        this.notificationKeyguardHelper = lazy3;
        this.stackCardControl = lazy4;
        this.immersiveCardControl = lazy5;
        this.capsuleCardControl = lazy6;
        this.lockScreenSeedingInteractor = lazy7;
        this.notificationsQSContainerController = lazy8;
        this.keyguardStateController = lazy9;
        this.oplusImmersiveController = lazy10;
        this.capsuleContainerController = lazy11;
        this.keyguardUpdateMonitor = lazy12;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.seedlingViewModel = lazy13;
        this.eventLog = oplusCapsuleLog;
        this.stackAlgorithmManager = lazy14;
        this.oplusPanoramicAodRepository = lazy15;
        this.keyguardUnlockAnimationController = lazy16;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(new ILockScreenExtStateController.ExtStateEvent(0, 0, -1, (Bundle) null, false, 56));
        this._extStateEvent = MutableStateFlow;
        this.extStateEvent = MutableStateFlow;
        this.oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
        LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0 lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0 = new LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0(0);
        lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.notificationDisplayRectCallbackWrapper$delegate = LazyKt__LazyJVMKt.lazy(lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0);
        LockScreenNotificationExtStateManager$wakefulnessObserver$1 lockScreenNotificationExtStateManager$wakefulnessObserver$1 = new LockScreenNotificationExtStateManager$wakefulnessObserver$1();
        lockScreenNotificationExtStateManager$wakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wakefulnessObserver = lockScreenNotificationExtStateManager$wakefulnessObserver$1;
        LockScreenNotificationExtStateManager$keyguardCallback$1 lockScreenNotificationExtStateManager$keyguardCallback$1 = new LockScreenNotificationExtStateManager$keyguardCallback$1();
        lockScreenNotificationExtStateManager$keyguardCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardCallback = lockScreenNotificationExtStateManager$keyguardCallback$1;
    }

    public static final void access$handleExitKeyguard(LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager) {
        if (lockScreenNotificationExtStateManager.isKeyguardState) {
            lockScreenNotificationExtStateManager.isKeyguardState = false;
            ((ImmersiveCardControl) lockScreenNotificationExtStateManager.immersiveCardControl.get()).onShadeState();
            ((NotificationCapsuleContainerControllerImpl) lockScreenNotificationExtStateManager.capsuleContainerController.get()).getClass();
            CapsuleDataChangeInterceptor.Companion.getClass();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(CapsuleDataChangeInterceptor.interceptorList.size(), "forceClearAllInterceptors: interceptorList.size=", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
            }
            Iterator it = CollectionsKt___CollectionsKt.toList(CapsuleDataChangeInterceptor.interceptorList).iterator();
            while (it.hasNext()) {
                ((CapsuleDataChangeInterceptor) it.next()).unIntercept();
            }
            if (!((CapsuleCardControl) lockScreenNotificationExtStateManager.capsuleCardControl.get()).hasSeedingData()) {
                lockScreenNotificationExtStateManager.gotoDefaultState("handleExitKeyguard");
                lockScreenNotificationExtStateManager.lastLockScreenNotificationStyle = ((NotificationKeyguardHelper) lockScreenNotificationExtStateManager.notificationKeyguardHelper.get()).getLockScreenNotificationStyle();
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockScreenExtStateController-->[handleExitKeyguard]:SHADE, but has seeding data, return.");
            }
            if (lockScreenNotificationExtStateManager.isImmersiveState()) {
                FocusCard focusCard = ((ImmersiveCardControl) lockScreenNotificationExtStateManager.immersiveCardControl.get()).getFocusCard();
                lockScreenNotificationExtStateManager.focusCardKey = focusCard != null ? focusCard.getKey() : null;
                lockScreenNotificationExtStateManager.goToExtState(false, lockScreenNotificationExtStateManager.getLastExtState(), "stateChanged:SHADE:immersive:pause", 305, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getExtState() {
        return ((ILockScreenExtStateController.ExtStateEvent) getExtStateEvent().getValue()).getNewState();
    }

    private static /* synthetic */ void getExtState$annotations() {
    }

    public static /* synthetic */ void getLastExtState$annotations() {
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public final boolean backToKgFirstWhenTouchOnKgNtfCenter() {
        NotificationStackScrollLayout view;
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt;
        return isLockScreenNtfCenterState() && (view = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView()) != null && (notificationStackScrollLayoutExt = view.mExt) != null && notificationStackScrollLayoutExt.getScrollYWhenDown() == 0;
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public boolean canAcceptImmersiveToImmersive(String str) {
        if (!isImmersiveState()) {
            return true;
        }
        long jUptimeMillis = SystemClock.uptimeMillis() - this.lastImmersiveSwitchTime;
        if (jUptimeMillis >= 100) {
            return true;
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            return false;
        }
        Log.d("SystemUi--Notification", "LockScreenExtStateController-->".concat("canAcceptImmersiveToImmersive: rejected, too fast " + jUptimeMillis + "ms < 100ms, from=" + str));
        return false;
    }

    public final void collapseExpandedNotifications(String str) {
        for (ExpandableNotificationRow expandableNotificationRow : ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren()) {
            ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
            if (expandableNotificationRow2 != null) {
                boolean z = expandableNotificationRow2.mIsSummaryWithChildren && expandableNotificationRow2.isGroupExpanded();
                boolean z2 = !expandableNotificationRow2.mIsSummaryWithChildren && expandableNotificationRow2.mHasUserChangedExpansion && expandableNotificationRow2.isExpanded(false);
                if (z || z2) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "collapseExpandedNotifications for ".concat(str), null, 8);
                    expandableNotificationRow2.setUserExpanded(false, expandableNotificationRow2.mIsSummaryWithChildren);
                }
            }
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public ExpandableView findSeedingCurrentView(String str) {
        CustomRowEntry data;
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        int extState = getExtState();
        companion.getClass();
        if (extState == 0 || extState == 3 || this.statusBarStateController.getState() == 0) {
            return ((StackCardControl) this.stackCardControl.get()).findSeedingView(str);
        }
        if (getExtState() == 2) {
            FocusCard focusCard = ((ImmersiveCardControl) this.immersiveCardControl.get()).getFocusCard();
            SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
            if (Intrinsics.areEqual((seedlingFocusCard == null || (data = seedlingFocusCard.getData()) == null) ? null : data.getServiceId(), str)) {
                FocusCard focusCard2 = ((ImmersiveCardControl) this.immersiveCardControl.get()).getFocusCard();
                SeedlingFocusCard seedlingFocusCard2 = focusCard2 instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard2 : null;
                if (seedlingFocusCard2 != null) {
                    return seedlingFocusCard2.getStackRow();
                }
                return null;
            }
        }
        return ((CapsuleCardControl) this.capsuleCardControl.get()).findSeedingView(str);
    }

    public final List getControl(int i) {
        Integer num = STATE_MAPPING.get(Integer.valueOf(i));
        if (num == null) {
            Path$.ExternalSyntheticBUOutline0.m("illegal state");
            return null;
        }
        int iIntValue = num.intValue();
        ArrayList arrayList = new ArrayList();
        Companion.getClass();
        if ((iIntValue & 4) != 0) {
            arrayList.add(this.immersiveCardControl.get());
        }
        if ((iIntValue & 2) != 0) {
            arrayList.add(this.capsuleCardControl.get());
        }
        if ((iIntValue & 1) != 0) {
            arrayList.add(this.stackCardControl.get());
        }
        return arrayList;
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public int getDefaultExtState() {
        return ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).getLockScreenNotificationStyle();
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public StateFlow getExtStateEvent() {
        return this.extStateEvent;
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public boolean getHasNotificationStacked() {
        return ((StackCardControl) this.stackCardControl.get()).getHasNotificationStacked();
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public int getLastExtState() {
        return this.lastExtState;
    }

    public final NotificationDisplayRectCallbackWrapper getNotificationDisplayRectCallbackWrapper() {
        return (NotificationDisplayRectCallbackWrapper) this.notificationDisplayRectCallbackWrapper$delegate.getValue();
    }

    public final Function1 getOnExtStateChanged() {
        return this.onExtStateChanged;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:49:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:62:0x0170  */
    /* JADX WARN: Code duplicated, block: B:69:0x01ad A[LOOP:1: B:67:0x01a7->B:69:0x01ad, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x021c  */
    /* JADX WARN: Code duplicated, block: B:86:0x0199 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x0183 A[SYNTHETIC] */
    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public void goToExtState(boolean z, int i, String str, int i2, Bundle bundle) {
        Integer num;
        int iIntValue;
        int extState;
        List control;
        Iterator it;
        int viewTypeFlag;
        if (i == 2) {
            String immersiveKey = bundle != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(bundle) : null;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                int extState2 = getExtState();
                boolean z2 = this.isKeyguardState;
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[goToExtState]:enter immersive ", extState2, i, " ->", ", from: ");
                ConstraintWidget$.ExternalSyntheticOutline0.m(i2, str, ", reason: ", ", immersiveKey=", sbM);
                sbM.append(immersiveKey);
                sbM.append(", isKeyguardState=");
                sbM.append(z2);
                Log.d("SystemUi--Notification", "LockScreenExtStateController-->".concat(sbM.toString()));
            }
            if (immersiveKey == null || !this.isKeyguardState) {
                if (immersiveKey != null) {
                    this.focusCardKey = immersiveKey;
                    return;
                }
                return;
            }
            if (getExtState() == 2 && z) {
                if (!canAcceptImmersiveToImmersive(str)) {
                    return;
                } else {
                    this.lastImmersiveSwitchTime = SystemClock.uptimeMillis();
                }
            }
            ((ImmersiveCardControl) this.immersiveCardControl.get()).setImmersiveCardKey(immersiveKey);
            ILockScreenExtStateController.ExtStateEvent.Companion companion = ILockScreenExtStateController.ExtStateEvent.Companion;
            FocusCard lastFocusCard = ((ImmersiveCardControl) this.immersiveCardControl.get()).getLastFocusCard();
            companion.withLastImmersiveKey(bundle, lastFocusCard != null ? lastFocusCard.getKey() : null);
        }
        if (z != ((ILockScreenExtStateController.ExtStateEvent) getExtStateEvent().getValue()).getAnimate() || i != getExtState()) {
            num = STATE_MAPPING.get(Integer.valueOf(i));
            if (num != null) {
                Path$.ExternalSyntheticBUOutline0.m("illegal state");
                return;
            }
            iIntValue = num.intValue();
            if (i != getExtState()) {
                setLastExtState(getExtState());
            }
            extState = getExtState();
            List<ICardControl> control2 = getControl(extState);
            control = getControl(i);
            if (i != 0 && extState == 0) {
                collapseExpandedNotifications("updateExtState");
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, 0.0f, 0);
            OsenseHelper osenseHelper = (OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class);
            motionEventObtain.getClass();
            osenseHelper.gpuBoomForTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.oplusGcSupressionExt.callGcDesupression(3);
            this.oplusGcSupressionExt.callGcSupression(3, 800);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("[goToExtState]: ", extState, i, " ->", ", from: ");
                sbM2.append(str);
                sbM2.append(", reason: ");
                sbM2.append(i2);
                Log.d("SystemUi--Notification", "LockScreenExtStateController-->".concat(sbM2.toString()));
            }
            for (ICardControl iCardControl : control2) {
                Companion companion2 = Companion;
                viewTypeFlag = iCardControl.getViewTypeFlag();
                companion2.getClass();
                if ((iIntValue & viewTypeFlag) != 0) {
                    control.remove(iCardControl);
                    iCardControl.mo73switch(extState, i, z, "switch:" + str);
                } else {
                    iCardControl.hide(extState, i, z, FileProvider$.ExternalSyntheticOutline0.m("hide:", str));
                }
            }
            it = control.iterator();
            while (it.hasNext()) {
                ((ICardControl) it.next()).show(extState, i, z, FileProvider$.ExternalSyntheticOutline0.m("show:", str));
            }
        } else if (i == 2) {
            if (!Intrinsics.areEqual(bundle != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getLastImmersiveKey(bundle) : null, bundle != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(bundle) : null)) {
                num = STATE_MAPPING.get(Integer.valueOf(i));
                if (num != null) {
                    Path$.ExternalSyntheticBUOutline0.m("illegal state");
                    return;
                }
                iIntValue = num.intValue();
                if (i != getExtState()) {
                    setLastExtState(getExtState());
                }
                extState = getExtState();
                List<ICardControl> control3 = getControl(extState);
                control = getControl(i);
                if (i != 0) {
                    collapseExpandedNotifications("updateExtState");
                }
                MotionEvent motionEventObtain2 = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, 0.0f, 0);
                OsenseHelper osenseHelper2 = (OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class);
                motionEventObtain2.getClass();
                osenseHelper2.gpuBoomForTouchEvent(motionEventObtain2);
                motionEventObtain2.recycle();
                this.oplusGcSupressionExt.callGcDesupression(3);
                this.oplusGcSupressionExt.callGcSupression(3, 800);
                if (LogUtil.getNormalDebug()) {
                    StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("[goToExtState]: ", extState, i, " ->", ", from: ");
                    sbM3.append(str);
                    sbM3.append(", reason: ");
                    sbM3.append(i2);
                    Log.d("SystemUi--Notification", "LockScreenExtStateController-->".concat(sbM3.toString()));
                }
                while (r1.hasNext()) {
                    Companion companion3 = Companion;
                    viewTypeFlag = iCardControl.getViewTypeFlag();
                    companion3.getClass();
                    if ((iIntValue & viewTypeFlag) != 0) {
                        control.remove(iCardControl);
                        iCardControl.mo73switch(extState, i, z, "switch:" + str);
                    } else {
                        iCardControl.hide(extState, i, z, FileProvider$.ExternalSyntheticOutline0.m("hide:", str));
                    }
                }
                it = control.iterator();
                while (it.hasNext()) {
                    ((ICardControl) it.next()).show(extState, i, z, FileProvider$.ExternalSyntheticOutline0.m("show:", str));
                }
            }
        }
        ILockScreenExtStateController.ExtStateEvent extStateEvent = new ILockScreenExtStateController.ExtStateEvent(getExtState(), i, i2, bundle, z, 32);
        if (extStateEvent.equals(this._extStateEvent.getValue())) {
            return;
        }
        this._extStateEvent.setValue(extStateEvent);
        Function1 function1 = this.onExtStateChanged;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
        updateKeyguardFpSuppressionState();
        ((LockScreenSeedingInteractor) this.lockScreenSeedingInteractor.get()).notifyExtStateChanged(getExtState(), str, i2, bundle != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(bundle) : null);
        this.eventLog.logExtStateEvent(extStateEvent);
        if (extStateEvent.getAnimate()) {
            NotificationSpotLightDelegate.Companion.suppressUntilPointerUp();
            AnimAfsManager.INSTANCE.scheduleAnimationCtrl(600, true);
        }
    }

    public final void gotoDefaultState(String str) {
        goToExtState(false, getDefaultExtState(), "gotoDefaultState-".concat(str), 304, null);
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public void gotoKeyguardState(boolean z, String str, float f, Runnable runnable) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[gotoKeyguardState]from:", str, "LockScreenExtStateController-->", "SystemUi--Notification");
        }
        this.oplusGcSupressionExt.callGcDesupression(3);
        this.oplusGcSupressionExt.callGcSupression(3, 800);
        ((OplusLockscreenShadeTransitionControllerEx) this.oplusLockscreenShadeTransitionController.get()).gotoKeyguardState(z, str, f);
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx = (OplusLockscreenShadeTransitionControllerEx) this.oplusLockscreenShadeTransitionController.get();
        gotoKeyguardState.2 r5 = new gotoKeyguardState.2(0);
        r5.$onFinish = runnable;
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        oplusLockscreenShadeTransitionControllerEx.executeRunnableAnimationFinish(r5);
    }

    public final void init() {
        gotoDefaultState("init");
        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) this.keyguardUnlockAnimationController.get();
        LockScreenNotificationExtStateManager$listenUnlockAnimation$1 lockScreenNotificationExtStateManager$listenUnlockAnimation$1 = new LockScreenNotificationExtStateManager$listenUnlockAnimation$1();
        lockScreenNotificationExtStateManager$listenUnlockAnimation$1.this$0 = this;
        VarHandle.storeStoreFence();
        keyguardUnlockAnimationController.addKeyguardUnlockAnimationListener(lockScreenNotificationExtStateManager$listenUnlockAnimation$1);
        this.wakefulnessLifecycle.addObserver(this.wakefulnessObserver);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        LockScreenNotificationExtStateManager$keyguardCallback$1 lockScreenNotificationExtStateManager$keyguardCallback$1 = this.keyguardCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(lockScreenNotificationExtStateManager$keyguardCallback$1);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass1);
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public final boolean isLockScreenNtfCenterState() {
        return this.statusBarStateController.getState() == 2;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [android.widget.FrameLayout, com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow] */
    public final void onEnterKeyguard() {
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager;
        if (this.lastLockScreenNotificationStyle != ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).getLockScreenNotificationStyle()) {
            gotoDefaultState("setting change");
            this.lastLockScreenNotificationStyle = ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).getLockScreenNotificationStyle();
        }
        this.isKeyguardState = true;
        ?? FindStackView = ((SeedlingViewModel) this.seedlingViewModel.get()).findStackView(this.focusCardKey);
        if (FindStackView != 0) {
            CustomRowEntry entry = FindStackView.getEntry();
            if ((entry != null ? entry.getImmersiveCardView() : null) == null) {
                this.focusCardKey = null;
                return;
            }
            if (FindStackView.getParent() == null) {
                KgdLogUtil.i(TAG, "[onEnterKeyguard]: exitImmersiveWallpaperIfNeed parent null");
                if (getExtState() != 2) {
                    OplusKgdImmersiveController.updateImmersiveLockScreenWallpaper$default((OplusKgdImmersiveController) this.oplusImmersiveController.get(), null, null, 4);
                    return;
                }
                return;
            }
            lockScreenNotificationExtStateManager = this;
            lockScreenNotificationExtStateManager.goToExtState(false, 2, "onEnterKeyguard:immersive:resume", 305, ILockScreenExtStateController.ExtStateEvent.Companion.withImmersiveKey(new Bundle(), this.focusCardKey));
            ((OplusKgdImmersiveController) lockScreenNotificationExtStateManager.oplusImmersiveController.get()).restoreImmersiveForKeyguardShow();
        } else {
            lockScreenNotificationExtStateManager = this;
            if (lockScreenNotificationExtStateManager.getExtState() == 2) {
                ((OplusKgdImmersiveController) lockScreenNotificationExtStateManager.oplusImmersiveController.get()).restoreImmersiveForKeyguardShow();
            } else {
                KgdLogUtil.i(TAG, "[onEnterKeyguard]: exitImmersiveWallpaperIfNeed focusCard null");
                if (lockScreenNotificationExtStateManager.getExtState() != 2) {
                    OplusKgdImmersiveController.updateImmersiveLockScreenWallpaper$default((OplusKgdImmersiveController) lockScreenNotificationExtStateManager.oplusImmersiveController.get(), null, null, 4);
                }
            }
        }
        lockScreenNotificationExtStateManager.focusCardKey = null;
    }

    public void setLastExtState(int i) {
        this.lastExtState = i;
    }

    public final void setOnExtStateChanged(Function1 function1) {
        this.onExtStateChanged = function1;
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public void tryGotoShadeLockState(boolean z, String str, float f, Runnable runnable, ExpandableNotificationRow expandableNotificationRow) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[gotoShadeLockState] from:", str, "LockScreenExtStateController-->", "SystemUi--Notification");
        }
        if (expandableNotificationRow != null && ((StackAlgorithmManager) this.stackAlgorithmManager.get()).canStayInPlaceOnExpand(expandableNotificationRow) && "onExpandClicked".equals(str)) {
            if (getExtState() == 3) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[gotoShadeLockState] onExpandClicked at sinking, switch to stack", null, 8);
                LockScreenExpandOriginController.Companion companion = LockScreenExpandOriginController.Companion;
                goToExtState(true, 0, "ExpandClicked:sinking_to_stack", 125, companion.withExpandToStackKey(new Bundle(), companion.resolveNotificationKey(expandableNotificationRow)));
                LockScreenStatisticUtil.incrementOneCounter(LockScreenStatisticUtil.KEY_STACK_EXPAND_TOTAL_COUNT);
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(getExtState(), "[gotoShadeLockState] from onExpandClicked, return for extState: ", "LockScreenExtStateController-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        this.oplusGcSupressionExt.callGcDesupression(3);
        this.oplusGcSupressionExt.callGcSupression(3, 800);
        ((OplusLockscreenShadeTransitionControllerEx) this.oplusLockscreenShadeTransitionController.get()).gotoShadeLockState(z, str, f);
        ((OplusLockscreenShadeTransitionControllerEx) this.oplusLockscreenShadeTransitionController.get()).clearConflictAnimationRunnable();
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx = (OplusLockscreenShadeTransitionControllerEx) this.oplusLockscreenShadeTransitionController.get();
        gotoKeyguardState.2 r8 = new gotoKeyguardState.2(1);
        r8.this$0 = this;
        r8.$onFinish = runnable;
        VarHandle.storeStoreFence();
        oplusLockscreenShadeTransitionControllerEx.executeRunnableAnimationFinish(r8);
    }

    public final void updateKeyguardFpSuppressionState() {
        if (getExtState() == 1) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportUdfps() && ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).getEx().isKeyguardLowFpHidden() && ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).getEx().canSkipBouncer()) {
                ((KeyguardStateController) this.keyguardStateController.get()).getEx().setKeyguardFpSuppressionFlags(1);
                return;
            }
        }
        ((KeyguardStateController) this.keyguardStateController.get()).getEx().clearKeyguardFpSuppressionFlags(1);
    }

    @Override // com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController
    public <V extends View> void updateViewStateToCurrentExtState(int i, List<? extends V> list) {
        if (i == 1) {
            ((StackCardControl) this.stackCardControl.get()).updateViewStateToExtState(getExtState(), list);
        } else if (i == 2) {
            ((CapsuleCardControl) this.capsuleCardControl.get()).updateViewStateToExtState(getExtState(), list);
        } else {
            if (i != 4) {
                return;
            }
            ((ImmersiveCardControl) this.immersiveCardControl.get()).updateViewStateToExtState(getExtState(), list);
        }
    }
}
