package com.oplus.systemui.statusbar.notification.interruption;

import android.service.notification.NotificationListenerService;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import com.android.systemui.statusbar.notification.interruption.OplusKeyguardNotificationVisibilityProviderEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.notification.debug.OplusNotificationDebug;
import com.oplus.systemui.security.SecurityLockModeManager;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.collection.coordinator.AodNotificationCoordinator;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardNotificationVisibilityProviderExImpl extends OplusKeyguardNotificationVisibilityProviderEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusKeyguardNotificati";
    private final AodNotificationCoordinator aodNotificationCoordinator;
    private final CoroutineScope coroutineScope;
    private final KeyguardStateController keyguardStateController;
    private final Lazy notificationKeyguardHelper;
    private final Lazy notificationMediaManager;
    private final OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx;
    private final Lazy oplusNotificationDebug;
    private final SecurityLockModeManager securityLockModeManager;
    private final Lazy statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.interruption.OplusKeyguardNotificationVisibilityProviderExImpl$onStartEx$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $notifyStateChanged;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.interruption.OplusKeyguardNotificationVisibilityProviderExImpl$onStartEx$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01231 implements FlowCollector {
            public /* synthetic */ Function1 $notifyStateChanged;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.interruption.OplusKeyguardNotificationVisibilityProviderExImpl$onStartEx$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01241 extends SuspendLambda implements Function2 {
                final /* synthetic */ boolean $it;
                final /* synthetic */ Function1 $notifyStateChanged;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01241(Continuation continuation, Function1 function1, boolean z) {
                    super(2, continuation);
                    this.$notifyStateChanged = function1;
                    this.$it = z;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01241(continuation, this.$notifyStateChanged, this.$it);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isExpRegion()) {
                        this.$notifyStateChanged.invoke("isKgNtfCenterExpanded(" + this.$it + ")");
                    }
                    return Unit.INSTANCE;
                }
            }

            public final Object emit(Object obj, Continuation continuation) {
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C01241(null, this.$notifyStateChanged, ((Boolean) obj).booleanValue()), continuation);
                return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$notifyStateChanged = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusKeyguardNotificationVisibilityProviderExImpl.this.new AnonymousClass1(this.$notifyStateChanged, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsKgNtfCenterExpanded = OplusKeyguardNotificationVisibilityProviderExImpl.this.oplusLockscreenShadeTransitionControllerEx.isKgNtfCenterExpanded();
                Function1 function1 = this.$notifyStateChanged;
                C01231 c01231 = new C01231();
                c01231.$notifyStateChanged = function1;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsKgNtfCenterExpanded.collect(c01231, this) == coroutine_suspended) {
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

    public OplusKeyguardNotificationVisibilityProviderExImpl(Lazy lazy, Lazy lazy2, Lazy lazy3, KeyguardStateController keyguardStateController, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, SecurityLockModeManager securityLockModeManager, AodNotificationCoordinator aodNotificationCoordinator, CoroutineScope coroutineScope, Lazy lazy4) {
        this.notificationKeyguardHelper = lazy;
        this.notificationMediaManager = lazy2;
        this.statusBarStateController = lazy3;
        this.keyguardStateController = keyguardStateController;
        this.oplusLockscreenShadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.securityLockModeManager = securityLockModeManager;
        this.aodNotificationCoordinator = aodNotificationCoordinator;
        this.coroutineScope = coroutineScope;
        this.oplusNotificationDebug = lazy4;
    }

    public boolean isRankingVisibilityOverrideSecret(NotificationEntry notificationEntry) {
        NotificationListenerService.Ranking ranking = notificationEntry.getRanking();
        return ranking != null && ranking.getLockscreenVisibilityOverride() == -1;
    }

    public void onStartEx(Function1 function1) {
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getDefault(), (CoroutineStart) null, new AnonymousClass1(function1, null), 2);
    }

    public boolean shouldForceHideNotification(NotificationEntry notificationEntry) {
        if (this.securityLockModeManager.securityLockMode != 1) {
            int currentOrUpcomingState = ((SysuiStatusBarStateController) this.statusBarStateController.get()).getCurrentOrUpcomingState();
            if (currentOrUpcomingState != 1 && currentOrUpcomingState != 2) {
                return false;
            }
            if (!((OplusNotificationEntryExImpl) notificationEntry.getEx()).isClose && !((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mHideForCharge && !this.aodNotificationCoordinator.isHidePanoramicAodCustomNotification(notificationEntry)) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldHideNotification(NotificationEntry notificationEntry) {
        int currentOrUpcomingState = ((SysuiStatusBarStateController) this.statusBarStateController.get()).getCurrentOrUpcomingState();
        if (currentOrUpcomingState != 1 && currentOrUpcomingState != 2 && !this.keyguardStateController.mShowing) {
            return false;
        }
        if (((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).shouldShowOnKeyguard(notificationEntry.getSbn())) {
            LockDeadUtil.INSTANCE.getClass();
            if (!LockDeadUtil.isLockDeadState) {
                int i = ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mLockScreenNotificationState;
                if (i == 2 || i == 1) {
                    if (((NotificationMediaManager) this.notificationMediaManager.get()).getOplusNotificationMediaManagerExt().isMediaNotification(notificationEntry)) {
                        return false;
                    }
                    FlavorTwoFeatureOption.INSTANCE.getClass();
                    if (FlavorTwoFeatureOption.isFlavorTwoDevice()) {
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isLightOs() && FeatureOption.isExpRegion()) {
                            return false;
                        }
                    }
                    if (((OplusNotificationDebug) this.oplusNotificationDebug.get()).getSkipCheckPostedBeforeLock() || notificationEntry.getEx().getPostTime() >= NotificationCommHelper.Companion.getInstance().getLastGoKeyguardTime() - 10000) {
                        return false;
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusKeyguardNotificati-->shouldHideNotificationInLockState--Lock screen switch is not available");
                }
            }
        }
        return true;
    }

    public boolean shouldHideSilentNotification(PipelineEntry pipelineEntry) {
        return true;
    }

    public final boolean shouldShowSilentNotification() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion();
    }

    public boolean shouldTryForceShowNotification(NotificationEntry notificationEntry) {
        int identifier = notificationEntry.getSbn().getUser().getIdentifier();
        if (identifier != -1 && identifier != KeyguardUpdateMonitor.getCurrentUser()) {
            return false;
        }
        if (((OplusNotificationEntryExImpl) notificationEntry.getEx()).isSysUIHighPriority) {
            return true;
        }
        GroupEntry parent = notificationEntry.getParent();
        GroupEntry groupEntry = parent instanceof GroupEntry ? parent : null;
        return (groupEntry != null ? groupEntry.getSummary() : null) == notificationEntry;
    }
}
