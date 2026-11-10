package com.oplus.systemui.notification.lockscreen.statemanager;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.SystemProperties;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.util.settings.GlobalSettings;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationCardView;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationData;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenDataCollectHelper;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationViewBinder {
    private static final String BUBBLE_KEY_CARD_SUPPORT_IMMERSIVE = "enableLockImmersive";
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockScreenNotificationViewBinder";
    private final Lazy capsuleContainer;
    private final NotificationCapsuleContainerControllerImpl capsuleContainerController;
    private final Lazy capsuleLightOSManager;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final LockScreenDataCollectHelper lockScreenDataCollectHelper;
    private final ILockScreenExtStateController lockScreenExtStateController;
    private final Lazy notificationKeyguardHelper;
    private final kotlin.Lazy oplusCustomContainerManager$delegate = LazyKt__LazyJVMKt.lazy(new LockScreenNotificationViewBinder$$ExternalSyntheticLambda0());
    private final OplusImmersiveViewManager oplusImmersiveViewManager;
    private final SeedlingViewModel seedlingViewModel;
    private final GlobalSettings settings;
    private final Lazy userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ClickHelper {
        public static final ClickHelper INSTANCE = new ClickHelper();
        private static ClickedItem lastClickedItem = new ClickedItem(null, 0);

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class ClickedItem {
            private final CapsuleItemData data;
            public final long lastClickTime;

            public ClickedItem(CapsuleItemData capsuleItemData, long j) {
                this.data = capsuleItemData;
                this.lastClickTime = j;
            }

            public final CapsuleItemData component1() {
                return this.data;
            }

            public final ClickedItem copy(CapsuleItemData capsuleItemData, long j) {
                return new ClickedItem(capsuleItemData, j);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ClickedItem)) {
                    return false;
                }
                ClickedItem clickedItem = (ClickedItem) obj;
                return Intrinsics.areEqual(this.data, clickedItem.data) && this.lastClickTime == clickedItem.lastClickTime;
            }

            public final CapsuleItemData getData() {
                return this.data;
            }

            public final long getLastClickTime() {
                return this.lastClickTime;
            }

            public final int hashCode() {
                CapsuleItemData capsuleItemData = this.data;
                return Long.hashCode(this.lastClickTime) + ((capsuleItemData == null ? 0 : capsuleItemData.hashCode()) * 31);
            }

            public String toString() {
                return "ClickedItem(data=" + this.data + ", lastClickTime=" + this.lastClickTime + ")";
            }
        }

        public final boolean fastClicked(CapsuleItemData capsuleItemData) {
            boolean z = false;
            if (Intrinsics.areEqual(capsuleItemData, lastClickedItem.getData()) && Math.abs(SystemClock.uptimeMillis() - lastClickedItem.getLastClickTime()) < 500) {
                z = true;
            }
            if (!z) {
                lastClickedItem = new ClickedItem(capsuleItemData, SystemClock.uptimeMillis());
            }
            return z;
        }

        public final boolean isRemoved(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleItemData capsuleItemData) {
            List<CapsuleItemView> capsuleChildren = CapsuleViewExpandKtKt.getCapsuleChildren(notificationCapsuleContainer);
            if ((capsuleChildren instanceof Collection) && capsuleChildren.isEmpty()) {
                return true;
            }
            Iterator<T> it = capsuleChildren.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((CapsuleItemView) it.next()).getEntry(), capsuleItemData)) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isRemoving(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleItemData capsuleItemData) {
            int transientViewCount = notificationCapsuleContainer.getTransientViewCount();
            if (transientViewCount >= 0) {
                int i = 0;
                while (true) {
                    Object transientView = notificationCapsuleContainer.getTransientView(i);
                    CapsuleItemView capsuleItemView = transientView instanceof CapsuleItemView ? (CapsuleItemView) transientView : null;
                    if (Intrinsics.areEqual(capsuleItemView != null ? capsuleItemView.getEntry() : null, capsuleItemData)) {
                        return true;
                    }
                    if (i != transientViewCount) {
                        i++;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$bindWhileAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$bindWhileAttached$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00281 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ LockScreenNotificationViewBinder this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$bindWhileAttached$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00291 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ LockScreenNotificationViewBinder this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$bindWhileAttached$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00301 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ LockScreenNotificationViewBinder this$0;

                    public /* synthetic */ C00301(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) throws JSONException {
                        switch (this.$r8$classId) {
                            case 0:
                                ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) obj;
                                LockScreenNotificationViewBinder lockScreenNotificationViewBinder = this.this$0;
                                int newState = extStateEvent.getNewState();
                                Bundle extra = extStateEvent.getExtra();
                                LockScreenNotificationViewBinder.access$saveExtToSetting(lockScreenNotificationViewBinder, newState, extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null);
                                lockScreenNotificationViewBinder.lockScreenDataCollectHelper.collectExtStateAndReport(extStateEvent.getOldState(), extStateEvent.getNewState(), extStateEvent.getReason());
                                break;
                            default:
                                int iIntValue = ((Number) obj).intValue();
                                LockScreenNotificationViewBinder lockScreenNotificationViewBinder2 = this.this$0;
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iIntValue, "childCountFlow: ", "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
                                }
                                if (iIntValue == 0) {
                                    ((CapsuleLightOSController) lockScreenNotificationViewBinder2.capsuleLightOSManager.get()).resetLightOSViewState();
                                    lockScreenNotificationViewBinder2.lockScreenExtStateController.goToExtState(false, lockScreenNotificationViewBinder2.lockScreenExtStateController.getDefaultExtState(), "all child removed", 304, null);
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00291(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = lockScreenNotificationViewBinder;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00291(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow extStateEvent = this.this$0.lockScreenExtStateController.getExtStateEvent();
                        LockScreenNotificationViewBinder lockScreenNotificationViewBinder = this.this$0;
                        C00301 c00301 = new C00301(0);
                        c00301.this$0 = lockScreenNotificationViewBinder;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (extStateEvent.collect(c00301, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    Regex$.ExternalSyntheticBUOutline0.m$1();
                    return null;
                }
            }

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$bindWhileAttached$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ LockScreenNotificationViewBinder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = lockScreenNotificationViewBinder;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    int i2 = 1;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow childCountFlow = ((NotificationCapsuleContainer) this.this$0.capsuleContainer.get()).getChildCountFlow();
                        LockScreenNotificationViewBinder lockScreenNotificationViewBinder = this.this$0;
                        C00291.C00301 c00301 = new C00291.C00301(i2);
                        c00301.this$0 = lockScreenNotificationViewBinder;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (childCountFlow.collect(c00301, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    Regex$.ExternalSyntheticBUOutline0.m$1();
                    return null;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00281(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, Continuation continuation) {
                super(2, continuation);
                this.this$0 = lockScreenNotificationViewBinder;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00281 c00281 = new C00281(this.this$0, continuation);
                c00281.L$0 = obj;
                return c00281;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00291(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = LockScreenNotificationViewBinder.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new C00281(LockScreenNotificationViewBinder.this, null), 3);
            return Unit.INSTANCE;
        }
    }

    public LockScreenNotificationViewBinder(Lazy lazy, NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, SeedlingViewModel seedlingViewModel, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController, GlobalSettings globalSettings, Lazy lazy2, Lazy lazy3, LockScreenDataCollectHelper lockScreenDataCollectHelper, Lazy lazy4, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.capsuleContainer = lazy;
        this.capsuleContainerController = notificationCapsuleContainerControllerImpl;
        this.seedlingViewModel = seedlingViewModel;
        this.oplusImmersiveViewManager = oplusImmersiveViewManager;
        this.lockScreenExtStateController = iLockScreenExtStateController;
        this.settings = globalSettings;
        this.userTracker = lazy2;
        this.notificationKeyguardHelper = lazy3;
        this.lockScreenDataCollectHelper = lockScreenDataCollectHelper;
        this.capsuleLightOSManager = lazy4;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
    }

    public static final void access$immerToImmer(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, String str) {
        ILockScreenExtStateController.ExtStateEvent.Companion companion;
        String lastImmersiveKey;
        if (lockScreenNotificationViewBinder.lockScreenExtStateController.canAcceptImmersiveToImmersive(str)) {
            Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) lockScreenNotificationViewBinder.lockScreenExtStateController.getExtStateEvent().getValue()).getExtra();
            if (extra != null && (lastImmersiveKey = (companion = ILockScreenExtStateController.ExtStateEvent.Companion).getLastImmersiveKey(extra)) != null) {
                lockScreenNotificationViewBinder.lockScreenExtStateController.goToExtState(true, 2, "ImmersiveToImmersive:".concat(str), ZoomAnimationUtils.TRANSIT_STATE_SWITCH, companion.withImmersiveKey(new Bundle(), lastImmersiveKey));
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("onImmerToImmerCapsuleClickGesture:", str, ":no lastFocusCard", "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
            }
        }
    }

    public static final void access$onCapsuleItemClick(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, CapsuleItemData capsuleItemData) {
        ClickHelper clickHelper = ClickHelper.INSTANCE;
        if (clickHelper.fastClicked(capsuleItemData)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onCapsuleItemViewClick fastClicked: ", capsuleItemData.getKey(), "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        if (clickHelper.isRemoved((NotificationCapsuleContainer) lockScreenNotificationViewBinder.capsuleContainer.get(), capsuleItemData) || clickHelper.isRemoving((NotificationCapsuleContainer) lockScreenNotificationViewBinder.capsuleContainer.get(), capsuleItemData)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onCapsuleItemViewClick isRemoved or isRemoving: ", capsuleItemData.getKey(), "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onCapsuleItemViewClick: ", capsuleItemData.getKey(), "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
        }
        if (capsuleItemData instanceof CapsuleNotificationData) {
            CapsuleNotificationCardView currentView = ((CapsuleNotificationData) capsuleItemData).getCapsuleNotificationContainerView().getCurrentView();
            if (currentView != null && currentView.isClickableCard()) {
                currentView.onSingleCardClick();
                return;
            }
            ILockScreenExtStateController iLockScreenExtStateController = lockScreenNotificationViewBinder.lockScreenExtStateController;
            ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
            iLockScreenExtStateController.goToExtState(true, 0, "CapsuleContainerClick", 303, null);
            return;
        }
        if (capsuleItemData instanceof CapsuleSeedingData) {
            if (SystemProperties.getBoolean("debug.capsule.force.immersion", false)) {
                CustomRowEntry customRowEntry = ((CapsuleSeedingData) capsuleItemData).getCustomRowEntry();
                if (lockScreenNotificationViewBinder.isSameImmersionItem(customRowEntry)) {
                    LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "onRequestImmersiveState:fail, because of not in immersive state");
                    return;
                } else {
                    lockScreenNotificationViewBinder.lockScreenExtStateController.goToExtState(true, 2, "onItemClick:test", 303, ILockScreenExtStateController.ExtStateEvent.Companion.withImmersiveKey(new Bundle(), customRowEntry.getKey()));
                    return;
                }
            }
            Bundle bundleCallPluginStartActivity = lockScreenNotificationViewBinder.getOplusCustomContainerManager().callPluginStartActivity(((CapsuleSeedingData) capsuleItemData).getCustomRowEntry().getKey(), 1);
            if (bundleCallPluginStartActivity == null || bundleCallPluginStartActivity.getBoolean(BUBBLE_KEY_CARD_SUPPORT_IMMERSIVE, false)) {
                return;
            }
            ILockScreenExtStateController iLockScreenExtStateController2 = lockScreenNotificationViewBinder.lockScreenExtStateController;
            ILockScreenExtStateController.Companion companion2 = ILockScreenExtStateController.Companion;
            iLockScreenExtStateController2.goToExtState(true, 0, "NoImmersiveCapsuleClick", 303, null);
        }
    }

    public static final void access$saveExtToSetting(LockScreenNotificationViewBinder lockScreenNotificationViewBinder, int i, String str) throws JSONException {
        lockScreenNotificationViewBinder.getClass();
        ILockScreenExtStateController.Companion.getClass();
        String strValueOf = String.valueOf(i == 3 ? 0 : i);
        String strValueOf2 = String.valueOf(((UserTracker) lockScreenNotificationViewBinder.userTracker.get()).getUserId());
        if (i != 2) {
            str = null;
        } else if (str == null) {
            str = LockScreenStatisticUtil.REPORT_ERROR;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mode", strValueOf);
        jSONObject.put("userId", strValueOf2);
        jSONObject.put(OplusCustomRowControllerManager.KEY_LOCK_IMMERSIVE_ENABLE, "1");
        if (str != null) {
            jSONObject.put("key", str);
        }
        String string = jSONObject.toString();
        lockScreenNotificationViewBinder.settings.putString("live_alert_lock_state", string);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("saveExtToSetting: ", string, "LockScreenNotificationViewBinder-->", "SystemUi--Notification");
        }
    }

    private final OplusCustomRowControllerManager getOplusCustomContainerManager() {
        return (OplusCustomRowControllerManager) this.oplusCustomContainerManager$delegate.getValue();
    }

    public final void bindWhileAttached(NotificationPanelView notificationPanelView) {
        this.capsuleContainerController.init();
        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.capsuleContainerController;
        LockScreenNotificationViewBinder$initCapsule$1 lockScreenNotificationViewBinder$initCapsule$1 = new LockScreenNotificationViewBinder$initCapsule$1();
        lockScreenNotificationViewBinder$initCapsule$1.this$0 = this;
        VarHandle.storeStoreFence();
        notificationCapsuleContainerControllerImpl.setupOutActionListener(lockScreenNotificationViewBinder$initCapsule$1);
        SeedlingViewModel seedlingViewModel = this.seedlingViewModel;
        LockScreenNotificationViewBinder$initCapsule$1 lockScreenNotificationViewBinder$initCapsule$2 = new LockScreenNotificationViewBinder$initCapsule$1();
        lockScreenNotificationViewBinder$initCapsule$2.this$0 = this;
        VarHandle.storeStoreFence();
        seedlingViewModel.setSeedingStateCallback(lockScreenNotificationViewBinder$initCapsule$2);
        OplusImmersiveViewManager oplusImmersiveViewManager = this.oplusImmersiveViewManager;
        LockScreenNotificationViewBinder$initCapsule$1 lockScreenNotificationViewBinder$initCapsule$3 = new LockScreenNotificationViewBinder$initCapsule$1();
        lockScreenNotificationViewBinder$initCapsule$3.this$0 = this;
        VarHandle.storeStoreFence();
        oplusImmersiveViewManager.setImmersiveTouchCallback(lockScreenNotificationViewBinder$initCapsule$3);
        RepeatWhenAttachedKt.repeatWhenAttached$default(notificationPanelView, (CoroutineContext) null, new AnonymousClass1(null), 3);
    }

    public final boolean isSameImmersionItem(CustomRowEntry customRowEntry) {
        if (customRowEntry != null && this.lockScreenExtStateController.isImmersiveState()) {
            Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) this.lockScreenExtStateController.getExtStateEvent().getValue()).getExtra();
            if (Intrinsics.areEqual(extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null, customRowEntry.getKey())) {
                return true;
            }
        }
        return false;
    }
}
