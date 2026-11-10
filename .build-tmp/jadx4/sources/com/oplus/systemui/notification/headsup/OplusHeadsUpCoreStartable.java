package com.oplus.systemui.notification.headsup;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.internal.statusbar.IStatusBarService;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.user.data.repository.UserRepository;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import com.oplus.systemui.notification.headsup.nssl.OplusNotificationListContainerWraper;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayoutController;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpTouchHelper;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
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
public final class OplusHeadsUpCoreStartable implements CoreStartable, OnHeadsUpChangedListener {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusHeadsUpCoreStart";
    private final CoroutineScope coroutineScope;
    private final HeadsUpAnimator headsUpAnimator;
    private final Lazy headsUpInteractor;
    private final HeadsUpLayoutController headsUpLayoutController;
    private final InitController initController;
    private final Lazy lockscreenUserManager;
    private final OplusNotificationListContainerWraper oplusNotificationListContainerWraper;
    private final OplusPanelViewInteractor panelViewInteractor;
    private final UserRepository userRepository;
    private final OplusHeadsUpViewModel viewModel;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpCoreStartable$start$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Runnable {
        public /* synthetic */ OplusHeadsUpCoreStartable this$0;

        @Override // java.lang.Runnable
        public final void run() {
            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = this.this$0.visualInterruptionDecisionProvider;
            VisualInterruptionType visualInterruptionType = VisualInterruptionType.PEEK;
            Set of = SetsKt__SetsJVMKt.setOf(visualInterruptionType);
            final OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable = this.this$0;
            visualInterruptionDecisionProvider.addCondition(new VisualInterruptionCondition(of) { // from class: com.oplus.systemui.notification.headsup.OplusHeadsUpCoreStartable.start.3.1
                public String reason = "oplus headsUp";

                public String getReason() {
                    return this.reason;
                }

                public final boolean shouldSuppress() {
                    if (!((Boolean) oplusHeadsUpCoreStartable.panelViewInteractor.isVisibility().getValue()).booleanValue()) {
                        return false;
                    }
                    this.reason = "panel view showing";
                    return true;
                }
            });
            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider2 = this.this$0.visualInterruptionDecisionProvider;
            Set of2 = SetsKt__SetsJVMKt.setOf(visualInterruptionType);
            final OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable2 = this.this$0;
            visualInterruptionDecisionProvider2.addFilter(new VisualInterruptionFilter(of2) { // from class: com.oplus.systemui.notification.headsup.OplusHeadsUpCoreStartable.start.3.2
                public String reason = "oplus headsUp";

                public String getReason() {
                    return this.reason;
                }

                public final boolean shouldSuppress(NotificationEntry notificationEntry) {
                    ExpandableNotificationRow row;
                    OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
                    OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable3 = oplusHeadsUpCoreStartable2;
                    NotificationEntry notificationEntryHeadsUpEntry = ((OplusHeadsUpNotificationInteractor) oplusHeadsUpCoreStartable3.headsUpInteractor.get()).headsUpEntry();
                    if (oplusHeadsUpCoreStartable3.headsUpLayoutController.isTrackingHeadsUp() && !Intrinsics.areEqual(notificationEntryHeadsUpEntry, notificationEntry)) {
                        this.reason = "has touch headsUp";
                        return true;
                    }
                    int identifier = notificationEntry.getSbn().getUser().getIdentifier();
                    if (!((NotificationLockscreenUserManager) oplusHeadsUpCoreStartable3.lockscreenUserManager.get()).isCurrentProfile(identifier)) {
                        this.reason = TextFieldBuffer$.ExternalSyntheticOutline0.m(identifier, oplusHeadsUpCoreStartable3.userRepository.getSelectedUserInfo().getUserHandle().getIdentifier(), "fake userid ", " ");
                        return true;
                    }
                    if (oplusHeadsUpCoreStartable3.headsUpLayoutController.hasHeadsUpFocus() && !Intrinsics.areEqual(notificationEntryHeadsUpEntry, notificationEntry)) {
                        this.reason = "is headsUp focus";
                        return true;
                    }
                    if (notificationEntryHeadsUpEntry == null || notificationEntryHeadsUpEntry == notificationEntry || (row = notificationEntryHeadsUpEntry.getRow()) == null || !row.mExpandAnimationRunning) {
                        return false;
                    }
                    ExpandableNotificationRow row2 = notificationEntryHeadsUpEntry.getRow();
                    if (SystemClock.elapsedRealtime() - ((row2 == null || (oplusExpandableNotificationRowEx = row2.mRowEx) == null) ? 0L : oplusExpandableNotificationRowEx.getWillLaunchTimestamp()) >= 300) {
                        return false;
                    }
                    this.reason = "zoom animator is running";
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "OplusHeadsUpCoreStart-->shouldSuppress: zoom animator is running");
                    }
                    return true;
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpCoreStartable$start$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpCoreStartable$start$5$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ OplusHeadsUpCoreStartable this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusHeadsUpCoreStartable.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isVisibility: ", zBooleanValue), null, 8);
                if (zBooleanValue) {
                    ((OplusHeadsUpNotificationInteractor) this.this$0.headsUpInteractor.get()).dismiss(false, "panelView Visibility", null);
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusHeadsUpCoreStartable.this.new AnonymousClass5(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsVisibility = OplusHeadsUpCoreStartable.this.panelViewInteractor.isVisibility();
                OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable = OplusHeadsUpCoreStartable.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = oplusHeadsUpCoreStartable;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsVisibility.collect(anonymousClass1, this) == coroutine_suspended) {
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

    public static Unit $r8$lambda$Gv465Gg8ZL8CnIQkR5RPKSjNF1o(OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable, View view) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view, "start: init success,row = "), null, 8);
        oplusHeadsUpCoreStartable.viewModel.initHeadsUp();
        return Unit.INSTANCE;
    }

    public OplusHeadsUpCoreStartable(InitController initController, HeadsUpAnimator headsUpAnimator, HeadsUpLayoutController headsUpLayoutController, OplusNotificationListContainerWraper oplusNotificationListContainerWraper, OplusPanelViewInteractor oplusPanelViewInteractor, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, OplusHeadsUpViewModel oplusHeadsUpViewModel, Lazy lazy, UserRepository userRepository, Lazy lazy2, CoroutineScope coroutineScope) {
        this.initController = initController;
        this.headsUpAnimator = headsUpAnimator;
        this.headsUpLayoutController = headsUpLayoutController;
        this.oplusNotificationListContainerWraper = oplusNotificationListContainerWraper;
        this.panelViewInteractor = oplusPanelViewInteractor;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.viewModel = oplusHeadsUpViewModel;
        this.headsUpInteractor = lazy;
        this.userRepository = userRepository;
        this.lockscreenUserManager = lazy2;
        this.coroutineScope = coroutineScope;
    }

    public final void initHeadsUpHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, HeadsUpTouchHelper.Callback callback, HeadsUpTouchHelper.HeadsUpNotificationViewController headsUpNotificationViewController) {
        HeadsUpAnimator headsUpAnimator = this.headsUpAnimator;
        HeadsUpLayoutController headsUpLayoutController = this.headsUpLayoutController;
        headsUpAnimator.setHeadsUpHelper(new OplusHeadsUpTouchHelper(headsUpManager, iStatusBarService, headsUpLayoutController, headsUpLayoutController));
    }

    public final void onFinishAnimationCallback() {
        ((OplusHeadsUpNotificationInteractor) this.headsUpInteractor.get()).dismiss(false, "animationCallBack", null);
    }

    public final void reuseSwipeHelper(NotificationSwipeHelper.Builder builder, NotificationSwipeHelper.NotificationCallback notificationCallback, NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener) {
        NotificationSwipeHelper.Builder onMenuEventListener2 = builder.setOnMenuEventListener(onMenuEventListener);
        OplusHeadsUpCoreStartable$reuseSwipeHelper$build$1 oplusHeadsUpCoreStartable$reuseSwipeHelper$build$1 = new OplusHeadsUpCoreStartable$reuseSwipeHelper$build$1();
        oplusHeadsUpCoreStartable$reuseSwipeHelper$build$1.this$0 = this;
        oplusHeadsUpCoreStartable$reuseSwipeHelper$build$1.$callback = notificationCallback;
        VarHandle.storeStoreFence();
        this.headsUpAnimator.reuseSwipeHelper(onMenuEventListener2.setNotificationCallback(oplusHeadsUpCoreStartable$reuseSwipeHelper$build$1).build());
    }

    public final void start() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusHeadsUpCoreStart-->start2");
        }
        if (NotificationExtFlag.OPLUS_HEADSUP) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusHeadsUpCoreStart-->ready");
            }
            InitController initController = this.initController;
            AnonymousClass3 anonymousClass3 = new AnonymousClass3();
            anonymousClass3.this$0 = this;
            VarHandle.storeStoreFence();
            initController.addPostInitTask(anonymousClass3);
            OplusNotificationListContainerWraper oplusNotificationListContainerWraper = this.oplusNotificationListContainerWraper;
            OplusHeadsUpCoreStartable$$ExternalSyntheticLambda0 oplusHeadsUpCoreStartable$$ExternalSyntheticLambda0 = new OplusHeadsUpCoreStartable$$ExternalSyntheticLambda0();
            oplusHeadsUpCoreStartable$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            oplusNotificationListContainerWraper.init(oplusHeadsUpCoreStartable$$ExternalSyntheticLambda0);
            BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass5(null), 2);
        }
    }
}
