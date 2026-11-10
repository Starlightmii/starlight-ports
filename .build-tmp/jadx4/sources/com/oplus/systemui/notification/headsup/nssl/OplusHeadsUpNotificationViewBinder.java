package com.oplus.systemui.notification.headsup.nssl;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.shade.domain.interactor.ShadeStatusBarComponentsInteractor;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.statusbar.notification.ui.viewbinder.HeadsUpNotificationViewBinder;
import com.android.systemui.util.kotlin.Utils;
import com.oplus.systemui.notification.aonaccess.AonAuthStarter;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import com.oplus.systemui.notification.headsup.OplusHeadsUpViewModel;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayoutController;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController;
import com.oplus.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpNotificationViewBinder extends HeadsUpNotificationViewBinder implements OplusHeadsUpNotificationInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusHeadsUpNotificationViewBinder";
    private final Lazy aonAuthStarter;
    private final CoroutineScope applicationScope;
    private final Context context;
    private Job darkChangeJob;
    private final Lazy fullScreenBannerHelper;
    private final HeadsUpAnimator headsUpAnimator;
    private NotificationEntry headsUpEntry;
    private final Lazy headsUpLayoutController;
    private final HeadsUpManager headsUpManager;
    private final HeadsUpNsslStateManager headsUpNsslStateManager;
    private final OplusHeadsUpViewModel headsUpViewModelModel;
    private final Lazy notificationMaterialColor;
    private final NotificationPopupMenuController notificationMenuPanelController;
    private final Lazy notificationSectionsManagerEx;
    private final OplusPanelViewInteractor oplusPanelViewInteractor;
    private final NotificationRemoteInputManager remoteInputManager;
    private final Lazy rowChanger;
    private final ShadeStatusBarComponentsInteractor shadeStatusBarComponentsInteractor;
    private final Lazy sysuiDarkIconDispatcher;
    private final NotificationListViewModel viewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpNotificationViewBinder$bindHeadsUpNotifications$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpNotificationViewBinder$bindHeadsUpNotifications$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusHeadsUpNotificationViewBinder this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpNotificationViewBinder$bindHeadsUpNotifications$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C00171 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public /* synthetic */ OplusHeadsUpNotificationViewBinder this$0;

                public /* synthetic */ C00171(int i) {
                    this.$r8$classId = i;
                }

                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder = this.this$0;
                            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusHeadsUpNotificationViewBinder.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isVisibility: ", zBooleanValue), null, 8);
                            if (zBooleanValue && (oplusHeadsUpNotificationViewBinder.headsUpEntry != null || oplusHeadsUpNotificationViewBinder.headsUpManager.hasPinnedHeadsUp() || oplusHeadsUpNotificationViewBinder.headsUpManager.hasNotifications() || ((FullScreenBannerHelper) oplusHeadsUpNotificationViewBinder.fullScreenBannerHelper.get()).mIsBannerShowing)) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    Log.d("SystemUi--Notification", "OplusHeadsUpNotificationViewBinder-->hide HeadsUp");
                                }
                                ((OplusHeadsUpRowChanger) oplusHeadsUpNotificationViewBinder.rowChanger.get()).setTempHeadsUpViewVisibility(8);
                                oplusHeadsUpNotificationViewBinder.headsUpManager.unpinAll(true, "OplusHeadsUpNotificationViewBinder.bindHeadsUpNotifications");
                                ((OplusNotificationSectionsManagerExImpl) oplusHeadsUpNotificationViewBinder.notificationSectionsManagerEx.get()).hideHeadsUpNotification(false, true);
                            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "OplusHeadsUpNotificationViewBinder-->no HeadsUp to hide");
                            }
                            break;
                        case 1:
                            Triple triple = (Triple) obj;
                            Pair pair = (Pair) triple.component1();
                            boolean zBooleanValue2 = ((Boolean) triple.component2()).booleanValue();
                            boolean zBooleanValue3 = ((Boolean) triple.component3()).booleanValue();
                            NotificationEntry notificationEntry = (NotificationEntry) pair.getFirst();
                            if (notificationEntry != null) {
                                OplusHeadsUpNotificationViewBinder.access$onHeadsUpPinnedStateChanged(this.this$0, notificationEntry, ((Boolean) pair.getSecond()).booleanValue(), zBooleanValue2, ((Boolean) this.this$0.oplusPanelViewInteractor.isVisibility().getValue()).booleanValue(), zBooleanValue3);
                            }
                            break;
                        default:
                            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusHeadsUpNotificationViewBinder.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleStatusBarColorChange: isDark=", ((Boolean) obj).booleanValue()), null, 8);
                            ((HeadsUpLayoutController) this.this$0.headsUpLayoutController.get()).notifyMaterialColorChanged();
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusHeadsUpNotificationViewBinder;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlowIsVisibility = this.this$0.oplusPanelViewInteractor.isVisibility();
                    OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder = this.this$0;
                    C00171 c00171 = new C00171(0);
                    c00171.this$0 = oplusHeadsUpNotificationViewBinder;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (stateFlowIsVisibility.collect(c00171, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpNotificationViewBinder$bindHeadsUpNotifications$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00182 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusHeadsUpNotificationViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00182(OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusHeadsUpNotificationViewBinder;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00182(this.this$0, continuation);
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
                    Flow flowSample = Utils.Companion.sample(this.this$0.headsUpViewModelModel.getHeadsUpViewState(), this.this$0.headsUpViewModelModel.getHeadsUpAnimationsEnabled(), this.this$0.headsUpViewModelModel.getHeadsUpGoingAwayAnimationsAllowed());
                    OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder = this.this$0;
                    AnonymousClass1.C00171 c00171 = new AnonymousClass1.C00171(i2);
                    c00171.this$0 = oplusHeadsUpNotificationViewBinder;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (flowSample.collect(c00171, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpNotificationViewBinder$bindHeadsUpNotifications$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            int label;

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(2, continuation);
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
                SetsKt__SetsKt.emptySet();
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = OplusHeadsUpNotificationViewBinder.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusHeadsUpNotificationViewBinder.TAG, "bindHeadsUpNotifications", null, 8);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(OplusHeadsUpNotificationViewBinder.this, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00182(OplusHeadsUpNotificationViewBinder.this, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(2, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$7j-QaJNzgQKC1suBtriR1HW4W2s, reason: not valid java name */
    public static Unit m56$r8$lambda$7jQaJNzgQKC1suBtriR1HW4W2s(OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, NotificationEntry notificationEntry) {
        oplusHeadsUpNotificationViewBinder.headsUpNsslStateManager.clearTempState(notificationEntry.getRow());
        oplusHeadsUpNotificationViewBinder.headsUpAnimator.show(notificationEntry.getRow());
        oplusHeadsUpNotificationViewBinder.handleStatusBarColorChange(true);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$ecEEdHcwrwU1vCbzJVAiFMt6fLc(OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, NotificationEntry notificationEntry) {
        ((AonAuthStarter) oplusHeadsUpNotificationViewBinder.aonAuthStarter.get()).tryCloseAonCamera("onHeadsUpUnPinned");
        oplusHeadsUpNotificationViewBinder.headsUpViewModelModel.releaseHeadsUpViewState(notificationEntry);
        oplusHeadsUpNotificationViewBinder.handleStatusBarColorChange(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$wTaj-S2JHhF0V5QpuCFf4oWVAUE, reason: not valid java name */
    public static Unit m57$r8$lambda$wTajS2JHhF0V5QpuCFf4oWVAUE(NotificationEntry notificationEntry, OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, Function1 function1, View view) {
        NotificationContentView privateLayout;
        ExpandableNotificationRow row = notificationEntry.getRow();
        if (row != null && row.mHeadsupDisappearRunning) {
            notificationEntry.setHeadsUpAnimatingAway(false);
        }
        ExpandableNotificationRow row2 = notificationEntry.getRow();
        if (row2 != null && (privateLayout = row2.getPrivateLayout()) != null && privateLayout.mPreviousHeadsUpRemoteInputIntent != null) {
            Log.d("NotificationContentView", "headsUpAnimator dismiss resetHeadsUpCached mPreviousHeadsUpRemoteInputIntent = null");
            privateLayout.mPreviousHeadsUpRemoteInputIntent = null;
        }
        if (Intrinsics.areEqual(oplusHeadsUpNotificationViewBinder.headsUpEntry, notificationEntry)) {
            oplusHeadsUpNotificationViewBinder.headsUpEntry = null;
        } else {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "dismiss: new entry not equal to headsUpEntery");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusHeadsUpNotificationViewBinder-->setHeadsUpDismissed");
        }
        NotificationRemoteInputManager notificationRemoteInputManager = oplusHeadsUpNotificationViewBinder.remoteInputManager;
        RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
        if (remoteInputController != null && remoteInputController.pruneWeakThenRemoveAndContains(notificationEntry, (NotificationEntry) null, (Object) null)) {
            notificationEntry.mRemoteEditImeVisible = false;
            notificationRemoteInputManager.mRemoteInputController.removeRemoteInput(notificationEntry, (Object) null, "RemoteInputManager#cleanUpRemoteInputForUserRemoval");
        }
        ((OplusHeadsUpRowChanger) oplusHeadsUpNotificationViewBinder.rowChanger.get()).setHeadsUpDismissed(notificationEntry);
        oplusHeadsUpNotificationViewBinder.headsUpNsslStateManager.resumeNsslState(notificationEntry.getRow());
        if (function1 != null) {
            function1.invoke(view);
        }
        return Unit.INSTANCE;
    }

    public OplusHeadsUpNotificationViewBinder(NotificationListViewModel notificationListViewModel, ShadeStatusBarComponentsInteractor shadeStatusBarComponentsInteractor, OplusPanelViewInteractor oplusPanelViewInteractor, HeadsUpManager headsUpManager, Lazy lazy, HeadsUpAnimator headsUpAnimator, OplusHeadsUpViewModel oplusHeadsUpViewModel, HeadsUpNsslStateManager headsUpNsslStateManager, NotificationPopupMenuController notificationPopupMenuController, Lazy lazy2, Lazy lazy3, Lazy lazy4, NotificationRemoteInputManager notificationRemoteInputManager, Lazy lazy5, Lazy lazy6, Lazy lazy7, Context context, CoroutineScope coroutineScope) {
        super(notificationListViewModel, shadeStatusBarComponentsInteractor);
        this.viewModel = notificationListViewModel;
        this.shadeStatusBarComponentsInteractor = shadeStatusBarComponentsInteractor;
        this.oplusPanelViewInteractor = oplusPanelViewInteractor;
        this.headsUpManager = headsUpManager;
        this.rowChanger = lazy;
        this.headsUpAnimator = headsUpAnimator;
        this.headsUpViewModelModel = oplusHeadsUpViewModel;
        this.headsUpNsslStateManager = headsUpNsslStateManager;
        this.notificationMenuPanelController = notificationPopupMenuController;
        this.aonAuthStarter = lazy2;
        this.notificationSectionsManagerEx = lazy3;
        this.fullScreenBannerHelper = lazy4;
        this.remoteInputManager = notificationRemoteInputManager;
        this.sysuiDarkIconDispatcher = lazy5;
        this.headsUpLayoutController = lazy6;
        this.notificationMaterialColor = lazy7;
        this.context = context;
        this.applicationScope = coroutineScope;
    }

    public static final void access$onHeadsUpPinnedStateChanged(OplusHeadsUpNotificationViewBinder oplusHeadsUpNotificationViewBinder, NotificationEntry notificationEntry, boolean z, boolean z2, boolean z3, boolean z4) {
        oplusHeadsUpNotificationViewBinder.getClass();
        boolean z5 = false;
        if (!z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusHeadsUpNotificationViewBinder-->", "SystemUi--Notification", TextFieldImplKt$.ExternalSyntheticOutline0.m("onHeadsUpUnPinned:", ",panel:", ",goingAway:", z2, z3), z4);
            }
            if (z2 && !z3 && z4 && !oplusHeadsUpNotificationViewBinder.notificationMenuPanelController.isPopupMenuShowing()) {
                z5 = true;
            }
            OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1 oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1 = new OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1();
            oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1.f$0 = oplusHeadsUpNotificationViewBinder;
            oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1.f$1 = notificationEntry;
            VarHandle.storeStoreFence();
            oplusHeadsUpNotificationViewBinder.dismiss(notificationEntry, z5, "unPinned", oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda1);
            return;
        }
        if (!z2 || z3) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onHeadsUpPinned:no show :", ",panel:", z2, z3));
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusHeadsUpNotificationViewBinder-->onHeadsUpPinned:");
        }
        NotificationEntry notificationEntry2 = oplusHeadsUpNotificationViewBinder.headsUpEntry;
        if (notificationEntry2 != null) {
            oplusHeadsUpNotificationViewBinder.dismiss(notificationEntry2, false, "new Pinned", null);
        }
        oplusHeadsUpNotificationViewBinder.headsUpEntry = notificationEntry;
        OplusHeadsUpRowChanger oplusHeadsUpRowChanger = (OplusHeadsUpRowChanger) oplusHeadsUpNotificationViewBinder.rowChanger.get();
        OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2 oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2 = new OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2();
        oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2.f$0 = oplusHeadsUpNotificationViewBinder;
        oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2.f$1 = notificationEntry;
        VarHandle.storeStoreFence();
        oplusHeadsUpRowChanger.setHeadsUpShowing(notificationEntry, oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda2);
    }

    public Object bindHeadsUpNotifications(NotificationStackScrollLayout notificationStackScrollLayout, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(null), continuation);
        return objCoroutineScope == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void dismiss(NotificationEntry notificationEntry, boolean z, String str, Function1 function1) {
        if (!Intrinsics.areEqual(notificationEntry, this.headsUpEntry)) {
            if (function1 != null) {
                function1.invoke((Object) null);
            }
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "[dismiss] block by entry not equal to headsUpEntery");
            return;
        }
        if (z) {
            notificationEntry.setHeadsUpAnimatingAway(true);
        }
        HeadsUpAnimator headsUpAnimator = this.headsUpAnimator;
        OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0 oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0 = new OplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0();
        oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0.f$0 = notificationEntry;
        oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0.f$1 = this;
        oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0.f$2 = function1;
        VarHandle.storeStoreFence();
        headsUpAnimator.dismiss(z, str, oplusHeadsUpNotificationViewBinder$$ExternalSyntheticLambda0);
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.markHeadsUpSeen();
        }
    }

    public final void handleStatusBarColorChange(boolean z) {
        if (ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
            Job job = this.darkChangeJob;
            if (z) {
                if (job != null) {
                    Job.Key key = Job.Key;
                    job.cancel((CancellationException) null);
                }
                this.darkChangeJob = BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new handleStatusBarColorChange.1(this, (Continuation) null), 2);
                return;
            }
            if (job != null) {
                Job.Key key2 = Job.Key;
                job.cancel((CancellationException) null);
            }
            this.darkChangeJob = null;
        }
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor
    public NotificationEntry headsUpEntry() {
        return this.headsUpEntry;
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor
    public void dismiss(boolean z, String str, Function1 function1) {
        NotificationEntry notificationEntry = this.headsUpEntry;
        if (notificationEntry != null) {
            dismiss(notificationEntry, z, str, function1);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusHeadsUpNotificationViewBinder-->setHeadsUpDismissed skip no headsUp");
        }
    }
}
