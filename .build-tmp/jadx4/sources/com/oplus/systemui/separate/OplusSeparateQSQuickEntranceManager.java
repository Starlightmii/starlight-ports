package com.oplus.systemui.separate;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.user.data.repository.UserSwitcherRepository;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.oplus.systemui.keyguard.familymode.SystemFamilyModeObserver;
import com.oplus.systemui.keyguard.underagemode.UnderageModeObserver;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.base.util.QsDateClickUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;
import com.oplus.systemui.qs.widget.OplusQSCarrierText;
import com.oplus.systemui.qs.widget.OplusQSClock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager {
    private static final String ACTION_QS_CENTER_SETTINGS_PAGE = "com.oplus.notificationmanager.action.QS_CENTER_SHOW";
    public static final Companion Companion = new Companion();
    private static final String KEY_SEPARATE_MODE = "key_separate_mode";
    private static final String TAG = "OplusSeparateQSQuickEntranceManager";
    public OplusSeparateQSQuickEntranceManager$handleUserClick$1.2 _userStateChangedListener;
    private final Lazy activityStarter;
    private final CoroutineScope applicationScope;
    private final Executor backgroundExecutor;
    private final Context context;
    private String description;
    private final Lazy deviceProvisionedController;
    private final OplusSeparateQSQuickEntranceManager$familyModeObserver$1 familyModeObserver;
    private Drawable icon;
    private final KeyguardStateController keyguardStateController;
    private final Handler mainHandler;
    private final UserInfoController.OnUserInfoChangedListener onUserInfoChangedListener;
    private final Lazy oplusBiometricAuthController;
    private final Lazy oplusKeyguardDependencyExImpl;
    private final OplusQSActivityStarter oplusQSActivityStarter;
    private final PanelInteractor panelInteractor;
    private final CopyOnWriteArrayList<QSQuickEntrance.CallBack> qsQuickEntranceBacks = new CopyOnWriteArrayList<>();
    private QSQuickEntranceImpl quickEntranceImpl;
    public boolean reloadedUserInfo;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private OplusSeparateQSManager separateQSManager;
    public long showUserSwitchDialogRequestTime;
    private Runnable showUserSwitchDialogRunnable;
    private final OplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1 superPowerSaveObserver;
    private final Executor uiExecutor;
    private final OplusSeparateQSQuickEntranceManager$underageModeObserver$1 underageModeObserver;
    private final UserInfoController userInfoController;
    private final Lazy userInteractor;
    private final UserManager userManager;
    private final UserSwitcherRepository userSwitcherRepository;
    private final UserTracker userTracker;
    private final OplusSeparateQSQuickEntranceManager$userTrackerCallback$1 userTrackerCallback;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01061 implements FlowCollector {
            public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

            public final Object emit(Object obj, Continuation continuation) {
                LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("UserSwitcherRepository isEnabled changed: ", ((Boolean) obj).booleanValue()), null, 8);
                OplusSeparateQSQuickEntranceManager.access$notifyShowUserSwitcherRefreshRequested(this.this$0);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusSeparateQSQuickEntranceManager.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowIsEnabled = OplusSeparateQSQuickEntranceManager.this.userSwitcherRepository.isEnabled();
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = OplusSeparateQSQuickEntranceManager.this;
                C01061 c01061 = new C01061();
                c01061.this$0 = oplusSeparateQSQuickEntranceManager;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowIsEnabled.collect(c01061, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSQuickEntranceImpl implements QSQuickEntrance {
        private final Context context;

        public QSQuickEntranceImpl(Context context) {
            this.context = context;
        }

        public void addCallBack(QSQuickEntrance.CallBack callBack) {
            OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = OplusSeparateQSQuickEntranceManager.this;
            oplusSeparateQSQuickEntranceManager.qsQuickEntranceBacks.add(callBack);
            callBack.onUserInfoChanged(oplusSeparateQSQuickEntranceManager.description, oplusSeparateQSQuickEntranceManager.icon);
            callBack.onShowUserSwitcherRefreshRequested();
        }

        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5, types: [android.widget.TextView, com.oplus.systemui.qs.widget.OplusQSCarrierText] */
        /* JADX WARN: Type inference failed for: r3v7 */
        public TextView getCarrierView() {
            ?? r3;
            OplusQSCarrierText oplusQSCarrierText;
            Object objInflate = LayoutInflater.from(this.context).inflate(2131559332, (ViewGroup) null, false);
            if (objInflate instanceof OplusQSCarrierText) {
                oplusQSCarrierText = (OplusQSCarrierText) objInflate;
            } else {
                r3 = 0;
            }
            if (r3 == 0) {
                r3 = oplusQSCarrierText;
                return null;
            }
            r3 = oplusQSCarrierText;
            r3.setIsAdaptiveSize(false);
            return r3;
        }

        public boolean getClickEnable() {
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                return false;
            }
            WellBeingAssistantRepositoryImpl.Companion.getClass();
            return !WellBeingAssistantRepositoryImpl.isWellBeingAssistantState;
        }

        public TextView getClockView() {
            Clock clockInflate = LayoutInflater.from(this.context).inflate(2131559333, (ViewGroup) null, false);
            if (clockInflate instanceof OplusQSClock) {
                return (OplusQSClock) clockInflate;
            }
            return null;
        }

        public final Context getContext() {
            return this.context;
        }

        public TextView getDateView() {
            View viewInflate = LayoutInflater.from(this.context).inflate(2131559334, (ViewGroup) null, false);
            if (viewInflate instanceof OplusQSDateView) {
                return (OplusQSDateView) viewInflate;
            }
            return null;
        }

        public final void handleClockOnClick() {
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, "handleClockOnClick", null, 8);
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                return;
            }
            WellBeingAssistantRepositoryImpl.Companion.getClass();
            if (WellBeingAssistantRepositoryImpl.isWellBeingAssistantState) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction(this.context.getString(2131952094));
            intent.addFlags(603979776);
            ((ActivityStarter) OplusSeparateQSQuickEntranceManager.this.activityStarter.get()).postStartActivityDismissingKeyguard(intent, 0);
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "clock_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public final void handleDateOnClick() {
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, "handleDateOnClick", null, 8);
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                return;
            }
            WellBeingAssistantRepositoryImpl.Companion.getClass();
            if (WellBeingAssistantRepositoryImpl.isWellBeingAssistantState) {
                return;
            }
            QsDateClickUtil.onDateClick(this.context, (ActivityStarter) OplusSeparateQSQuickEntranceManager.this.activityStarter.get());
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "date_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public void handleEditClick(Function0 function0) {
            function0.invoke();
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "edit_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public final void handleFgsStopOnClick() {
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "stop_fgs_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public final void handleMoreOnClick() {
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "more_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public void handleQSSettingsOnClick(View view) {
            OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = OplusSeparateQSQuickEntranceManager.this;
            boolean zBooleanValue = ((Boolean) oplusSeparateQSQuickEntranceManager.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleQSSettingsOnClick isSeparateNotiAndQsEnable: ", zBooleanValue), null, 8);
            Intent intent = new Intent(OplusSeparateQSQuickEntranceManager.ACTION_QS_CENTER_SETTINGS_PAGE);
            intent.addFlags(603979776);
            intent.putExtra(OplusSeparateQSQuickEntranceManager.KEY_SEPARATE_MODE, zBooleanValue);
            if (view != null) {
                try {
                    oplusSeparateQSQuickEntranceManager.oplusQSActivityStarter.onAnimationButtonClicked(view, intent, OplusQSActivityStarter.QSTransitionAnimator.QUICKENTRANCE);
                } catch (ActivityNotFoundException e) {
                    MotionLayout$.ExternalSyntheticOutline0.m$1("Activity not found: ", e.getMessage(), OplusSeparateQSQuickEntranceManager.TAG);
                    return;
                }
            }
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, "start QsCenterActivity success", null, 8);
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "qs_setting_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        public void handleSettingsOnClick(View view) {
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, "handleSettingsOnClick", null, 8);
            OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = OplusSeparateQSQuickEntranceManager.this;
            if (!((DeviceProvisionedController) oplusSeparateQSQuickEntranceManager.deviceProvisionedController.get()).isCurrentUserSetup()) {
                ((ActivityStarter) oplusSeparateQSQuickEntranceManager.activityStarter.get()).postQSRunnableDismissingKeyguard(handleSettingsOnClick.1.INSTANCE);
                LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSQuickEntranceManager.TAG, "If user isn't setup just unlock the device and dump them back at SUW", null, 8);
            } else {
                if (view != null) {
                    oplusSeparateQSQuickEntranceManager.oplusQSActivityStarter.onAnimationButtonClicked(view, new Intent("android.settings.SETTINGS"), OplusQSActivityStarter.QSTransitionAnimator.QUICKENTRANCE);
                }
                QsStatisticUtil.collectShortcutsCenterEvent(this.context, "settings_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
            }
        }

        public void handleUserSwitchClick(View view) {
            OplusSeparateQSQuickEntranceManager.access$handleUserClick(OplusSeparateQSQuickEntranceManager.this, view);
            QsStatisticUtil.collectShortcutsCenterEvent(this.context, "user_switch_click", ShadeExpansionHelperImpl.VAL_INVALID, SeparateQSStatisicUtil.INSTANCE.getQsEnabled());
        }

        /* JADX WARN: Code duplicated, block: B:26:0x007e  */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        
            if (com.oplus.content.OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.multiuser_entry_disabled") != false) goto L31;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean isShowUserSwitcher() {
            /*
                r10 = this;
                com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager r10 = com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager.this
                r10.getClass()
                java.lang.String r0 = "showUserSwitcher: isInMultiSystem: "
                com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl$Companion r1 = com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl.Companion
                r1.getClass()
                boolean r1 = com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()
                r2 = 0
                r3 = 8
                java.lang.String r4 = "OplusSeparateQSQuickEntranceManager"
                java.lang.String r5 = "QuickSettings"
                r6 = 0
                if (r1 == 0) goto L21
                java.lang.String r10 = "isSuperPowerSaveModel"
                com.oplusos.systemui.common.util.LogUtil.i$default(r5, r4, r10, r2, r3)
                return r6
            L21:
                com.oplus.systemui.keyguard.familymode.SystemFamilyModeObserver$Companion r1 = com.oplus.systemui.keyguard.familymode.SystemFamilyModeObserver.Companion
                com.oplus.systemui.keyguard.familymode.SystemFamilyModeObserver r1 = r1.getInstance()
                int r1 = r1.mFamilyMode
                r7 = 1
                if (r1 != r7) goto L2e
                goto Lb8
            L2e:
                com.oplus.systemui.keyguard.underagemode.UnderageModeObserver$Companion r1 = com.oplus.systemui.keyguard.underagemode.UnderageModeObserver.Companion
                com.oplus.systemui.keyguard.underagemode.UnderageModeObserver r1 = r1.getInstance()
                int r1 = r1.minorsMode
                if (r1 != r7) goto L3e
                java.lang.String r10 = "isKeyguardUnderageOpen"
                com.oplusos.systemui.common.util.LogUtil.i$default(r5, r4, r10, r2, r3)
                return r6
            L3e:
                int r1 = com.android.keyguard.KeyguardUpdateMonitor.getCurrentUser()     // Catch: java.lang.Throwable -> L97
                r8 = 888(0x378, float:1.244E-42)
                if (r1 != r8) goto L47
                goto L99
            L47:
                java.lang.String r8 = "persist.sys.assert.panic.multi.user.entrance"
                boolean r8 = android.os.SystemProperties.getBoolean(r8, r6)     // Catch: java.lang.Throwable -> L97
                if (r8 == 0) goto L51
                goto L5d
            L51:
                com.oplus.content.OplusFeatureConfigManager r8 = com.oplus.content.OplusFeatureConfigManager.getInstance()     // Catch: java.lang.Throwable -> L97
                java.lang.String r9 = "oplus.software.multiuser_entry_disabled"
                boolean r8 = r8.hasFeature(r9)     // Catch: java.lang.Throwable -> L97
                if (r8 != 0) goto L99
            L5d:
                com.oplus.multiuser.OplusMultiUserManager r8 = com.oplus.multiuser.OplusMultiUserManager.getInstance()     // Catch: java.lang.Throwable -> L97
                boolean r1 = r8.isMultiSystemUserId(r1)     // Catch: java.lang.Throwable -> L97
                if (r1 != 0) goto L7e
                com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager$isMultiUserEnabled$1 r8 = new com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager$isMultiUserEnabled$1     // Catch: java.lang.Throwable -> L97
                r8.<init>()     // Catch: java.lang.Throwable -> L97
                r8.this$0 = r10     // Catch: java.lang.Throwable -> L97
                java.lang.invoke.VarHandle.storeStoreFence()     // Catch: java.lang.Throwable -> L97
                java.lang.Object r10 = com.android.systemui.DejankUtils.whitelistIpcs(r8)     // Catch: java.lang.Throwable -> L97
                java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L97
                boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L97
                if (r10 == 0) goto L7e
                goto L7f
            L7e:
                r7 = r6
            L7f:
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
                r10.<init>(r0)     // Catch: java.lang.Throwable -> L97
                r10.append(r1)     // Catch: java.lang.Throwable -> L97
                java.lang.String r0 = ", show: "
                r10.append(r0)     // Catch: java.lang.Throwable -> L97
                r10.append(r7)     // Catch: java.lang.Throwable -> L97
                java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L97
                com.oplusos.systemui.common.util.LogUtil.i$default(r5, r4, r10, r2, r3)     // Catch: java.lang.Throwable -> L97
                return r7
            L97:
                r10 = move-exception
                goto La0
            L99:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L97
                java.lang.Object r10 = kotlin.Result.constructor-impl(r10)     // Catch: java.lang.Throwable -> L97
                goto La8
            La0:
                java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
                java.lang.Object r10 = kotlin.Result.constructor-impl(r10)
            La8:
                java.lang.Throwable r10 = kotlin.Result.exceptionOrNull-impl(r10)
                if (r10 == 0) goto Lb8
                java.lang.String r10 = r10.getMessage()
                java.lang.String r0 = "showUserSwitcher: message : "
                com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(r3, r0, r10, r5, r4)
            Lb8:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.separate.OplusSeparateQSQuickEntranceManager.QSQuickEntranceImpl.isShowUserSwitcher():boolean");
        }

        public void removeCallBack(QSQuickEntrance.CallBack callBack) {
            OplusSeparateQSQuickEntranceManager.this.qsQuickEntranceBacks.remove(callBack);
        }
    }

    public OplusSeparateQSQuickEntranceManager(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, PanelInteractor panelInteractor, Handler handler, Executor executor, Executor executor2, Lazy lazy5, KeyguardStateController keyguardStateController, UserInfoController userInfoController, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, UserTracker userTracker, UserManager userManager, OplusQSActivityStarter oplusQSActivityStarter, UserSwitcherRepository userSwitcherRepository, CoroutineScope coroutineScope) {
        this.context = context;
        this.activityStarter = lazy;
        this.deviceProvisionedController = lazy2;
        this.oplusBiometricAuthController = lazy3;
        this.oplusKeyguardDependencyExImpl = lazy4;
        this.panelInteractor = panelInteractor;
        this.mainHandler = handler;
        this.backgroundExecutor = executor;
        this.uiExecutor = executor2;
        this.userInteractor = lazy5;
        this.keyguardStateController = keyguardStateController;
        this.userInfoController = userInfoController;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.userTracker = userTracker;
        this.userManager = userManager;
        this.oplusQSActivityStarter = oplusQSActivityStarter;
        this.userSwitcherRepository = userSwitcherRepository;
        this.applicationScope = coroutineScope;
        OplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1 oplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1 = new OplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1();
        oplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onUserInfoChangedListener = oplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1;
        OplusSeparateQSQuickEntranceManager$userTrackerCallback$1 oplusSeparateQSQuickEntranceManager$userTrackerCallback$1 = new OplusSeparateQSQuickEntranceManager$userTrackerCallback$1();
        oplusSeparateQSQuickEntranceManager$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = oplusSeparateQSQuickEntranceManager$userTrackerCallback$1;
        OplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1 oplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1 = new OplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1();
        oplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.superPowerSaveObserver = oplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1;
        OplusSeparateQSQuickEntranceManager$familyModeObserver$1 oplusSeparateQSQuickEntranceManager$familyModeObserver$1 = new OplusSeparateQSQuickEntranceManager$familyModeObserver$1();
        oplusSeparateQSQuickEntranceManager$familyModeObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.familyModeObserver = oplusSeparateQSQuickEntranceManager$familyModeObserver$1;
        OplusSeparateQSQuickEntranceManager$underageModeObserver$1 oplusSeparateQSQuickEntranceManager$underageModeObserver$1 = new OplusSeparateQSQuickEntranceManager$underageModeObserver$1();
        oplusSeparateQSQuickEntranceManager$underageModeObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.underageModeObserver = oplusSeparateQSQuickEntranceManager$underageModeObserver$1;
        ((UserInfoControllerImpl) userInfoController).addCallback(oplusSeparateQSQuickEntranceManager$onUserInfoChangedListener$1);
        userTracker.addCallback(oplusSeparateQSQuickEntranceManager$userTrackerCallback$1, executor2);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(oplusSeparateQSQuickEntranceManager$superPowerSaveObserver$1);
        SystemFamilyModeObserver.Companion.getInstance().addListener(oplusSeparateQSQuickEntranceManager$familyModeObserver$1);
        UnderageModeObserver.Companion.getInstance().addListener(oplusSeparateQSQuickEntranceManager$underageModeObserver$1);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    public static final void access$handleUserClick(OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager, View view) {
        if (!oplusSeparateQSQuickEntranceManager.keyguardStateController.mShowing) {
            ((UserSwitcherInteractor) oplusSeparateQSQuickEntranceManager.userInteractor.get()).showUserSwitcher((Expandable) null, view.getContext(), view);
            return;
        }
        ActivityStarter activityStarter = (ActivityStarter) oplusSeparateQSQuickEntranceManager.activityStarter.get();
        OplusSeparateQSQuickEntranceManager$handleUserClick$1 oplusSeparateQSQuickEntranceManager$handleUserClick$1 = new OplusSeparateQSQuickEntranceManager$handleUserClick$1(0);
        oplusSeparateQSQuickEntranceManager$handleUserClick$1.this$0 = oplusSeparateQSQuickEntranceManager;
        oplusSeparateQSQuickEntranceManager$handleUserClick$1.$view = view;
        VarHandle.storeStoreFence();
        activityStarter.postQSRunnableDismissingKeyguard(oplusSeparateQSQuickEntranceManager$handleUserClick$1);
    }

    public static final void access$notifyShowUserSwitcherRefreshRequested(OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager) {
        Executor executor = oplusSeparateQSQuickEntranceManager.uiExecutor;
        OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1 oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1 = new OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1(1);
        oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1.this$0 = oplusSeparateQSQuickEntranceManager;
        VarHandle.storeStoreFence();
        executor.execute(oplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1);
    }

    public static final void access$onUserInfoChanged(OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager, Drawable drawable) {
        Executor executor = oplusSeparateQSQuickEntranceManager.backgroundExecutor;
        OplusSeparateQSQuickEntranceManager$handleUserClick$1 oplusSeparateQSQuickEntranceManager$handleUserClick$1 = new OplusSeparateQSQuickEntranceManager$handleUserClick$1(3);
        oplusSeparateQSQuickEntranceManager$handleUserClick$1.$view = drawable;
        oplusSeparateQSQuickEntranceManager$handleUserClick$1.this$0 = oplusSeparateQSQuickEntranceManager;
        VarHandle.storeStoreFence();
        executor.execute(oplusSeparateQSQuickEntranceManager$handleUserClick$1);
    }

    public final QSQuickEntrance bindQuickEntrance() {
        if (this.quickEntranceImpl == null) {
            this.quickEntranceImpl = new QSQuickEntranceImpl(this.context);
        }
        return this.quickEntranceImpl;
    }

    public final void setOplusSeparateQSManager(OplusSeparateQSManager oplusSeparateQSManager) {
        this.separateQSManager = oplusSeparateQSManager;
    }
}
