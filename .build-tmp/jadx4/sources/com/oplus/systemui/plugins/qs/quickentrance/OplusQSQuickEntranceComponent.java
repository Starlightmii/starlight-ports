package com.oplus.systemui.plugins.qs.quickentrance;

import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewKt$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.proxy.EmptyAreaGestureProxy;
import com.oplus.systemui.plugins.qs.quickentrance.popupwindow.OplusQSMoreButtonPopupWindowController;
import com.oplus.systemui.plugins.qs.quickentrance.ui.view.OplusQSQuickEntranceContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.viewmodel.OplusQSQuickEntranceViewModel;
import com.oplus.systemui.plugins.qs.util.QSThreadUtil;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceComponent extends ViewController implements IQsComponent, QSFgs.CallBack, QSQuickEntrance.CallBack {
    public static final Companion Companion = new Companion();
    private static final String QS_EDIT_HAS_OPEN = "qs_editButton_has_open";
    private static final String TAG = "OplusQSQuickEntranceComponent";
    private final CoroutineScope bgScope;
    public boolean canCancelShowPullDownTextView;
    private final CellAreaProvider cellAreaProvider;
    private final TextView continuePullDownTextView;
    private final View editBtn;
    private final Lazy editBtnBackgroundProxy$delegate;
    private final View editBtnRedDot;
    private final OplusQSQuickEntranceComponent$editModeOpenStateObserver$1 editModeOpenStateObserver;
    private Handler handler;
    public boolean hasEditRegister;
    public boolean isEditMode;
    public boolean isEditModeOpened;
    private final View leftPlaceHolder;
    public boolean mIsExpanded;
    private final Lazy multiUserBackgroundProxy$delegate;
    private final View multiUserBtn;
    private final QSFgs qsFgs;
    private final OplusQSMoreButtonPopupWindowController qsMoreButtonPopupWindowController;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final OplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1 qsPanelExpandFractionListener;
    private final QSPrivary qsPrivary;
    private final QSQuickEntrance qsQSQuickEntrance;
    private final QSQuickEntrance quickEntrance;
    private final Runnable rehideRealStatusRunnable;
    private final Runnable reshowRealStatusRunnable;
    private final ScenarioPreviewShowState scenarioPreviewShowState;
    private final Lazy settingsBtnIconBackgroundProxy$delegate;
    private final FrameLayout settingsButton;
    private final ImageView settingsButtonIcon;
    private final FrameLayout settingsButtonRemote;
    private Function1 statusBarAlphaUpdater;
    private final QSElement statusBarHeader;
    private final OplusSeparateQSPlugin.IStub stub;
    public List topButtonPressFeedbacks;
    private final Lazy transitionProperties$delegate;
    private String userDescription;
    private Drawable userIcon;
    public int userId;
    private final ImageView userImage;
    private final OplusQSQuickEntranceContainerView view;
    private final OplusQSQuickEntranceViewModel viewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQSQuickEntranceComponent this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            switch (this.$r8$classId) {
                case 0:
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "OplusQSQuickEntranceComponent-->settings onClick");
                    }
                    this.this$0.quickEntrance.handleSettingsOnClick(this.this$0.settingsButtonRemote);
                    break;
                case 1:
                    Context currentUserContext = this.this$0.stub.getCurrentUserContext(this.this$0.getContext());
                    if (currentUserContext == null) {
                        currentUserContext = this.this$0.getContext();
                    }
                    KeyguardManager keyguardManager = (KeyguardManager) currentUserContext.getSystemService(KeyguardManager.class);
                    boolean zIsDeviceLocked = keyguardManager != null ? keyguardManager.isDeviceLocked() : false;
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQSQuickEntranceComponent.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("editBtn click, isDeviceLocked:", zIsDeviceLocked), null, 8);
                    OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                    if (!zIsDeviceLocked) {
                        oplusQSQuickEntranceComponent.getQsMoreButtonPopupWindowController().showEdit$1();
                    } else {
                        String string = oplusQSQuickEntranceComponent.getContext().getString(2131955203);
                        String strValueOf = String.valueOf(2131955203);
                        TemporarilyInfoManager.InfoShowStyle infoShowStyle = new TemporarilyInfoManager.InfoShowStyle(QsColorUtil.isTextNeedUseLightColorWhenLight(oplusQSQuickEntranceComponent.getContext(), QsColorUtil.getQsColorState()) ? -1 : oplusQSQuickEntranceComponent.getContext().getColor(2131103752), 11);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("show temporarily info text = ", string, "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings");
                        }
                        TemporarilyInfoManager.Companion.getInstance().show(string, null, strValueOf, infoShowStyle);
                    }
                    break;
                default:
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "OplusQSQuickEntranceComponent-->userButton OnClick");
                    }
                    this.this$0.qsQSQuickEntrance.handleUserSwitchClick(this.this$0.multiUserBtn);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onNumberOfPackagesChanged$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ boolean $isfgsNew;
        public /* synthetic */ int $numPackages;
        public /* synthetic */ OplusQSQuickEntranceComponent this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusQSMoreButtonPopupWindowController qsMoreButtonPopupWindowController = this.this$0.getQsMoreButtonPopupWindowController();
            int i = this.$numPackages;
            boolean z = this.$isfgsNew;
            qsMoreButtonPopupWindowController.getClass();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onNumberOfPackagesChanged numPackages ", "OplusQSMoreButtonPopupWindowController-->", "SystemUi--QuickSettings");
            }
            qsMoreButtonPopupWindowController.fgsNumPkg = i;
            qsMoreButtonPopupWindowController.isFgsNew = z;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onShowUserSwitcherRefreshRequested$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02431 extends SuspendLambda implements Function2 {
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onShowUserSwitcherRefreshRequested$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00601 extends SuspendLambda implements Function2 {
            final /* synthetic */ boolean $show;
            int label;
            final /* synthetic */ OplusQSQuickEntranceComponent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00601(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, boolean z, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSQuickEntranceComponent;
                this.$show = z;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00601(this.this$0, this.$show, continuation);
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
                boolean z = this.$show;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("isShowUserSwitcher: ", "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings", z);
                }
                this.this$0.multiUserBtn.setVisibility(this.$show ? 0 : 8);
                this.this$0.leftPlaceHolder.setVisibility(this.this$0.multiUserBtn.getVisibility());
                this.this$0.refreshUserInfo();
                if (this.this$0.multiUserBtn.getVisibility() == 0) {
                    this.this$0.getMultiUserBackgroundProxy().refreshViewBackground();
                }
                return Unit.INSTANCE;
            }
        }

        public C02431(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusQSQuickEntranceComponent.this.new C02431(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean zIsShowUserSwitcher = OplusQSQuickEntranceComponent.this.qsQSQuickEntrance.isShowUserSwitcher();
                MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
                C00601 c00601 = new C00601(OplusQSQuickEntranceComponent.this, zIsShowUserSwitcher, null);
                this.Z$0 = zIsShowUserSwitcher;
                this.label = 1;
                if (BuildersKt.withContext(immediate, c00601, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02442 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQSQuickEntranceComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00611 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSQuickEntranceComponent this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00621 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ OplusQSQuickEntranceComponent this$0;

                    public /* synthetic */ C00621(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                int iIntValue = ((Number) obj).intValue();
                                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                                oplusQSQuickEntranceComponent.userId = iIntValue;
                                oplusQSQuickEntranceComponent.updateEditRedView();
                                break;
                            case 1:
                                ((Boolean) obj).booleanValue();
                                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent2 = this.this$0;
                                Companion companion = OplusQSQuickEntranceComponent.Companion;
                                oplusQSQuickEntranceComponent2.updateVisibility$6();
                                break;
                            case 2:
                                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent3 = this.this$0;
                                oplusQSQuickEntranceComponent3.settingsButton.setVisibility(!zBooleanValue ? 0 : 8);
                                if (oplusQSQuickEntranceComponent3.settingsButton.getVisibility() == 0) {
                                    oplusQSQuickEntranceComponent3.getSettingsBtnIconBackgroundProxy().refreshViewBackground();
                                }
                                break;
                            default:
                                String str = (String) obj;
                                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent4 = this.this$0;
                                oplusQSQuickEntranceComponent4.scenarioPreviewShowState.setCurrentScenarioName(str);
                                if (str == null || str.length() == 0) {
                                    OplusQSQuickEntranceComponent.access$cancelShowOrHideScenarioPreview(oplusQSQuickEntranceComponent4);
                                } else {
                                    if (oplusQSQuickEntranceComponent4.scenarioPreviewShowState.getValue(str) == null) {
                                        OplusQSQuickEntranceComponent.access$registerScenarioPreviewIfAbsent(oplusQSQuickEntranceComponent4, str);
                                    }
                                    if (oplusQSQuickEntranceComponent4.mIsExpanded) {
                                        OplusQSQuickEntranceComponent.access$handleScenarioPreviewFirstShow(oplusQSQuickEntranceComponent4);
                                    }
                                }
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getScenarioPreviewInfo currentScenarioName: ", str, "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings");
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00611(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSQuickEntranceComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00611(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow userId = OplusQSSpecialModeProvider.Companion.getInstance().getUserId();
                        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                        C00621 c00621 = new C00621(0);
                        c00621.this$0 = oplusQSQuickEntranceComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (FlowExtKt.collectTraced(userId, "OplusQSQuickEntranceComponent-collect-userId", c00621, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00632 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSQuickEntranceComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00632(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSQuickEntranceComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00632(this.this$0, continuation);
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
                        StateFlow superPowerSaveMode = OplusQSSpecialModeProvider.Companion.getInstance().getSuperPowerSaveMode();
                        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                        C00611.C00621 c00621 = new C00611.C00621(i2);
                        c00621.this$0 = oplusQSQuickEntranceComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (FlowExtKt.collectTraced(superPowerSaveMode, "OplusQSQuickEntranceComponent-collect-superPowerSaveMode", c00621, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSQuickEntranceComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSQuickEntranceComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow hideSettingsEntrance = this.this$0.viewModel.getHideSettingsEntrance();
                        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                        C00611.C00621 c00621 = new C00611.C00621(2);
                        c00621.this$0 = oplusQSQuickEntranceComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (hideSettingsEntrance.collect(c00621, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$onViewAttached$2$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSQuickEntranceComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSQuickEntranceComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow scenarioPreviewInfo = this.this$0.qsPrivary.getScenarioPreviewInfo();
                        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                        C00611.C00621 c00621 = new C00611.C00621(3);
                        c00621.this$0 = oplusQSQuickEntranceComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (scenarioPreviewInfo.collect(c00621, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSQuickEntranceComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
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
                CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSQuickEntranceComponent-launch-userId", (CoroutineContext) null, (CoroutineStart) null, new C00611(this.this$0, null), 6);
                CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSQuickEntranceComponent-launch-superPowerSaveMode", (CoroutineContext) null, (CoroutineStart) null, new C00632(this.this$0, null), 6);
                CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSQuickEntranceComponent-launch-hideSettingsEntrance", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 6);
                CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSQuickEntranceComponent-launch-getScenarioPreviewInfo", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.this$0, null), 6);
                return Unit.INSTANCE;
            }
        }

        public C02442(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            C02442 c02442 = OplusQSQuickEntranceComponent.this.new C02442((Continuation) obj3);
            c02442.L$0 = (LifecycleOwner) obj;
            return c02442.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusQSQuickEntranceComponent.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$setEmptyTouchListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnTouchListenerC02451 implements View.OnTouchListener {
        public /* synthetic */ EmptyAreaGestureProxy $emptyAreaGestureProxy;

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return this.$emptyAreaGestureProxy.onTouchEvent(motionEvent);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$updateEditRedView$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02461 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$updateEditRedView$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00641 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusQSQuickEntranceComponent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00641(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSQuickEntranceComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00641(this.this$0, continuation);
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
                this.this$0.editBtnRedDot.setVisibility((this.this$0.isEditModeOpened || ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) ? 8 : 0);
                return Unit.INSTANCE;
            }
        }

        public C02461(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusQSQuickEntranceComponent.this.new C02461(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = OplusQSQuickEntranceComponent.this;
                oplusQSQuickEntranceComponent.isEditModeOpened = oplusQSQuickEntranceComponent.getDialogDisplayedBySettings();
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C00641 c00641 = new C00641(OplusQSQuickEntranceComponent.this, null);
                this.label = 1;
                if (BuildersKt.withContext(main, c00641, this) == coroutine_suspended) {
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

    public static QuickEntranceItemViewBackgroundProxy $r8$lambda$9B9JwLzIGEss9OgHPx2i988exgU(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        ImageView imageView = oplusQSQuickEntranceComponent.userImage;
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(1);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0.f$0 = oplusQSQuickEntranceComponent;
        VarHandle.storeStoreFence();
        return new QuickEntranceItemViewBackgroundProxy(imageView, null, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0);
    }

    public static QuickEntranceItemViewBackgroundProxy $r8$lambda$A_AOmA0q8p1ODfHT_xQ1zD3xxL8(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        View view = oplusQSQuickEntranceComponent.editBtn;
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(5);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0.f$0 = oplusQSQuickEntranceComponent;
        VarHandle.storeStoreFence();
        return new QuickEntranceItemViewBackgroundProxy(view, null, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: renamed from: $r8$lambda$OJ9G9b-vcfFjibdhKSq6GhuWOrI, reason: not valid java name */
    public static Unit m230$r8$lambda$OJ9G9bvcfFjibdhKSq6GhuWOrI(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, float f) {
        oplusQSQuickEntranceComponent.setQuickEntranceContainerViewAlpha(f);
        Function1 function1 = oplusQSQuickEntranceComponent.statusBarAlphaUpdater;
        if (function1 != null) {
            function1.invoke(Float.valueOf(f));
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$R2FPnLabzcYYtvG9lIBLx_CsDQg(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, boolean z) {
        if (z) {
            oplusQSQuickEntranceComponent.continuePullDownTextView.setVisibility(8);
            oplusQSQuickEntranceComponent.canCancelShowPullDownTextView = false;
        }
        return Unit.INSTANCE;
    }

    public static float $r8$lambda$Ya73x_kjNAd3pnLuvMyz82zFahc(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        return oplusQSQuickEntranceComponent.getViewSize() / 2.0f;
    }

    public static float $r8$lambda$eIiMeCu88bGT93865BcepAHQgXs(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        return oplusQSQuickEntranceComponent.getViewSize() / 2.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v23, types: [com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$editModeOpenStateObserver$1] */
    public OplusQSQuickEntranceComponent(OplusQSQuickEntranceContainerView oplusQSQuickEntranceContainerView, OplusQSMoreButtonPopupWindowController oplusQSMoreButtonPopupWindowController, QSPanelAnimatorManager qSPanelAnimatorManager, QSPrivary qSPrivary, OplusQSQuickEntranceViewModel oplusQSQuickEntranceViewModel, QSQuickEntrance qSQuickEntrance, QSFgs qSFgs, QSElement qSElement, CellAreaProvider cellAreaProvider, CoroutineScope coroutineScope, OplusSeparateQSPlugin.IStub iStub, QSQuickEntrance qSQuickEntrance2) {
        super(oplusQSQuickEntranceContainerView);
        this.view = oplusQSQuickEntranceContainerView;
        this.qsMoreButtonPopupWindowController = oplusQSMoreButtonPopupWindowController;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        this.qsPrivary = qSPrivary;
        this.viewModel = oplusQSQuickEntranceViewModel;
        this.quickEntrance = qSQuickEntrance;
        this.qsFgs = qSFgs;
        this.statusBarHeader = qSElement;
        this.cellAreaProvider = cellAreaProvider;
        this.bgScope = coroutineScope;
        this.stub = iStub;
        this.qsQSQuickEntrance = qSQuickEntrance2;
        this.settingsButtonIcon = (ImageView) oplusQSQuickEntranceContainerView.requireViewById(2131364823);
        this.settingsButton = (FrameLayout) oplusQSQuickEntranceContainerView.requireViewById(2131364822);
        this.settingsButtonRemote = (FrameLayout) oplusQSQuickEntranceContainerView.requireViewById(2131364824);
        this.editBtn = oplusQSQuickEntranceContainerView.requireViewById(2131364743);
        this.multiUserBtn = oplusQSQuickEntranceContainerView.requireViewById(2131364108);
        this.leftPlaceHolder = oplusQSQuickEntranceContainerView.requireViewById(2131364780);
        this.userImage = (ImageView) oplusQSQuickEntranceContainerView.requireViewById(2131363503);
        this.editBtnRedDot = oplusQSQuickEntranceContainerView.requireViewById(2131363006);
        this.continuePullDownTextView = (TextView) oplusQSQuickEntranceContainerView.requireViewById(2131364723);
        this.handler = new Handler(Looper.getMainLooper());
        this.scenarioPreviewShowState = ScenarioPreviewShowState.Companion.getInstance();
        this.userDescription = "";
        final Handler handler = this.handler;
        this.editModeOpenStateObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$editModeOpenStateObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
                OplusQSQuickEntranceComponent.Companion companion = OplusQSQuickEntranceComponent.Companion;
                oplusQSQuickEntranceComponent.updateEditRedView();
            }
        };
        OplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1 oplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1 = new OplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1();
        oplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelExpandFractionListener = oplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1;
        OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 = new OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1(0);
        oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.rehideRealStatusRunnable = oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1;
        OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 oplusQSQuickEntranceComponent$rehideRealStatusRunnable$2 = new OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1(1);
        oplusQSQuickEntranceComponent$rehideRealStatusRunnable$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.reshowRealStatusRunnable = oplusQSQuickEntranceComponent$rehideRealStatusRunnable$2;
        this.transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda2());
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(2);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.editBtnBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0);
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(3);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.settingsBtnIconBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1);
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda2 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(4);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.multiUserBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(oplusQSQuickEntranceComponent$$ExternalSyntheticLambda2);
    }

    public static final void access$cancelShowOrHideScenarioPreview(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        if (oplusQSQuickEntranceComponent.continuePullDownTextView.getVisibility() == 0) {
            FrameLayout frameLayout = oplusQSQuickEntranceComponent.settingsButton;
            if (oplusQSQuickEntranceComponent.qsPanelAnimatorManager.isAnimating(frameLayout)) {
                oplusQSQuickEntranceComponent.qsPanelAnimatorManager.cancelElementAnimators(frameLayout);
            }
            oplusQSQuickEntranceComponent.setQuickEntranceContainerViewAlpha(1.0f);
            Function1 function1 = oplusQSQuickEntranceComponent.statusBarAlphaUpdater;
            if (function1 != null) {
                function1.invoke(Float.valueOf(1.0f));
            }
            oplusQSQuickEntranceComponent.continuePullDownTextView.setVisibility(8);
        }
        oplusQSQuickEntranceComponent.cancelScenarioPreviewStatusRunnables();
        oplusQSQuickEntranceComponent.canCancelShowPullDownTextView = false;
    }

    public static final void access$handleScenarioPreviewFirstShow(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        oplusQSQuickEntranceComponent.getClass();
        if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
            return;
        }
        String currentScenarioName = oplusQSQuickEntranceComponent.scenarioPreviewShowState.getCurrentScenarioName();
        boolean zIsFirstShow = oplusQSQuickEntranceComponent.scenarioPreviewShowState.isFirstShow(currentScenarioName);
        if (zIsFirstShow) {
            long lastShowTime = oplusQSQuickEntranceComponent.scenarioPreviewShowState.getLastShowTime();
            if (lastShowTime == 0 || System.currentTimeMillis() - lastShowTime >= 7200000) {
                oplusQSQuickEntranceComponent.canCancelShowPullDownTextView = true;
                oplusQSQuickEntranceComponent.view.removeCallbacks(oplusQSQuickEntranceComponent.rehideRealStatusRunnable);
                oplusQSQuickEntranceComponent.view.postDelayed(oplusQSQuickEntranceComponent.rehideRealStatusRunnable, 500L);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("handleScenarioPreviewFirstShow scenario[", currentScenarioName, "] isFirstShow = ", zIsFirstShow, "OplusQSQuickEntranceComponent-->"));
        }
    }

    public static final void access$registerScenarioPreviewIfAbsent(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, String str) {
        Boolean scenarioPreviewState = oplusQSQuickEntranceComponent.stub.getScenarioPreviewState(str);
        if (scenarioPreviewState != null) {
            oplusQSQuickEntranceComponent.scenarioPreviewShowState.setValue(str, scenarioPreviewState.booleanValue());
        } else {
            oplusQSQuickEntranceComponent.stub.setScenarioPreviewState(str, false);
            oplusQSQuickEntranceComponent.scenarioPreviewShowState.setValue(str, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getDialogDisplayedBySettings() {
        return Settings.System.getIntForUser(getContext().getContentResolver(), QS_EDIT_HAS_OPEN, 0, this.userId) == 1;
    }

    private final QuickEntranceItemViewBackgroundProxy getEditBtnBackgroundProxy() {
        return (QuickEntranceItemViewBackgroundProxy) this.editBtnBackgroundProxy$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QuickEntranceItemViewBackgroundProxy getMultiUserBackgroundProxy() {
        return (QuickEntranceItemViewBackgroundProxy) this.multiUserBackgroundProxy$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QuickEntranceItemViewBackgroundProxy getSettingsBtnIconBackgroundProxy() {
        return (QuickEntranceItemViewBackgroundProxy) this.settingsBtnIconBackgroundProxy$delegate.getValue();
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    private final int getViewSize() {
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        CellAreaProvider cellAreaProvider = this.cellAreaProvider;
        return zBooleanValue ? cellAreaProvider.getCellAreaConfig().getBottonZoneWidthLand() : cellAreaProvider.getCellAreaConfig().getButtonZoneHeight();
    }

    private final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            updateVisibility$6();
            updateClickAbilities();
            updateEditRedView();
            this.viewModel.setEditMode(this.isEditMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final QuickEntranceItemViewBackgroundProxy settingsBtnIconBackgroundProxy_delegate$lambda$11(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        ImageView imageView = oplusQSQuickEntranceComponent.settingsButtonIcon;
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(0);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0.f$0 = oplusQSQuickEntranceComponent;
        VarHandle.storeStoreFence();
        return new QuickEntranceItemViewBackgroundProxy(imageView, null, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float settingsBtnIconBackgroundProxy_delegate$lambda$11$lambda$10(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent) {
        return oplusQSQuickEntranceComponent.getViewSize() / 2.0f;
    }

    public static void showOrHideForScenarioPreview$default(OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, boolean z) {
        oplusQSQuickEntranceComponent.continuePullDownTextView.setVisibility(0);
        if (!z) {
            TextView textView = oplusQSQuickEntranceComponent.continuePullDownTextView;
            OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 = new OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1(3);
            oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1.this$0 = textView;
            VarHandle.storeStoreFence();
            textView.post(oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1);
        }
        FrameLayout frameLayout = oplusQSQuickEntranceComponent.settingsButton;
        QSPanelAnimatorManager qSPanelAnimatorManager = oplusQSQuickEntranceComponent.qsPanelAnimatorManager;
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1(1);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1.f$0 = oplusQSQuickEntranceComponent;
        VarHandle.storeStoreFence();
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda0(6);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0.f$0 = frameLayout;
        VarHandle.storeStoreFence();
        OplusQSQuickEntranceComponent$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new OplusQSQuickEntranceComponent$.ExternalSyntheticLambda9();
        externalSyntheticLambda9.f$0 = z;
        externalSyntheticLambda9.f$1 = oplusQSQuickEntranceComponent;
        VarHandle.storeStoreFence();
        QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager, frameLayout, z, true, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda0, 0.0f, 0.5f, externalSyntheticLambda9, 160);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object obj;
        Object next;
        Set<? extends SpringTransition> set2 = set;
        Iterator<T> it = set2.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "parent_translation_transition"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.view, BoundsTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.editBtn, BoundsTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.settingsButton, BoundsTransition.class));
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.multiUserBtn, BoundsTransition.class));
        }
        for (Object obj2 : set2) {
            if (Intrinsics.areEqual(((SpringTransition) obj2).getTransitionName(), "status_bar_alpha_transition")) {
                obj = obj2;
                break;
            }
        }
        SpringTransition springTransition2 = (SpringTransition) obj;
        if (springTransition2 != null) {
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.view, FadeTransition.class));
        }
    }

    public final void cancelScenarioPreviewStatusRunnables() {
        Handler handler = this.view.getHandler();
        if (handler != null && handler.hasCallbacks(this.rehideRealStatusRunnable)) {
            this.view.removeCallbacks(this.rehideRealStatusRunnable);
        }
        Handler handler2 = this.view.getHandler();
        if (handler2 == null || !handler2.hasCallbacks(this.reshowRealStatusRunnable)) {
            return;
        }
        this.view.removeCallbacks(this.reshowRealStatusRunnable);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return Intrinsics.areEqual(str, EditModeSwitchAnimationManager.SCENE_EDIT) ? (SpringTransition[]) CollectionsKt__CollectionsKt.listOfNotNull(IEditModeSwitcher.Companion.createTransition("status_bar_alpha_transition")).toArray(new SpringTransition[0]) : new SpringTransition[0];
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
        this.mView.removeOnAttachStateChangeListener(this.mOnAttachStateListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return this.view;
    }

    public final ViewGroup getCarrierTextContainer() {
        return this.statusBarHeader.getClockContainer();
    }

    public final OplusQSMoreButtonPopupWindowController getQsMoreButtonPopupWindowController() {
        return this.qsMoreButtonPopupWindowController;
    }

    public final Function1 getStatusBarAlphaUpdater() {
        return this.statusBarAlphaUpdater;
    }

    public final QSElement getStatusBarHeader() {
        return this.statusBarHeader;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return super.getSubSize();
    }

    public final OplusQSQuickEntranceContainerView getView() {
        return this.view;
    }

    public final boolean hideCouiPopup() {
        return this.qsMoreButtonPopupWindowController.hideCouiPopup();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        super.onEnterAddingMode(z, z2, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        super.onEnterDragMode(z, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 = new OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1(2);
        oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        workHandler.post(oplusQSQuickEntranceComponent$rehideRealStatusRunnable$1);
        setEditMode(true);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        setEditMode(false);
        List list = this.topButtonPressFeedbacks;
        if (list == null) {
            list = null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((PressFeedbackHelper.QsTilePressFeedback) it.next()).reset();
        }
        Iterator it2 = CollectionsKt__CollectionsKt.listOf(new View[]{this.settingsButtonRemote, this.multiUserBtn, this.editBtn}).iterator();
        while (it2.hasNext()) {
            SharedSpotLightEffect.detach((View) it2.next(), "detach");
        }
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onInit() {
        FrameLayout frameLayout = this.settingsButtonRemote;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        frameLayout.setOnClickListener(anonymousClass1);
        View view = this.multiUserBtn;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(2);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        view.setOnClickListener(anonymousClass2);
        updateColor(QSSpecialModeUpdateMonitor.ColorType.COLOR_STATE);
        updateClickAbilities();
        int i = 1;
        List<View> listListOf = CollectionsKt__CollectionsKt.listOf(new View[]{this.settingsButtonRemote, this.multiUserBtn, this.editBtn});
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listListOf, 10));
        for (View view2 : listListOf) {
            arrayList.add(PressFeedbackHelper.attachPressFeedback(view2, view2));
        }
        this.topButtonPressFeedbacks = arrayList;
        updateEditRedView();
        View view3 = this.editBtn;
        Object parent = view3.getParent();
        View view4 = parent instanceof View ? (View) parent : null;
        if (view4 != null) {
            if (!view4.isLaidOut() || view4.isLayoutRequested()) {
                OplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1 oplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1 = new OplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1();
                oplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1.this$0 = this;
                oplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1.$targetView$inlined = view3;
                oplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1.$parentView$inlined = view4;
                VarHandle.storeStoreFence();
                view4.addOnLayoutChangeListener(oplusQSQuickEntranceComponent$expandTouchArea$$inlined$doOnLayout$1);
            } else {
                int dimension = (int) getContext().getResources().getDimension(2131171789);
                Rect rect = new Rect();
                view3.getHitRect(rect);
                rect.left -= dimension;
                rect.right += dimension;
                rect.top -= dimension;
                rect.bottom += dimension;
                view4.setTouchDelegate(new TouchDelegate(rect, view3));
            }
        }
        View view5 = this.editBtn;
        AnonymousClass1 anonymousClass3 = new AnonymousClass1(i);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        view5.setOnClickListener(anonymousClass3);
    }

    public final void onNumberOfPackagesChanged(int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onNumberOfPackagesChanged numPackages ", "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings");
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        anonymousClass2.$numPackages = i;
        anonymousClass2.$isfgsNew = z;
        VarHandle.storeStoreFence();
        QSThreadUtil.runOnUiThread(anonymousClass2);
    }

    public void onRunningAppsChanged(List<QSFgs.QSRunningApp> list) {
        this.qsMoreButtonPopupWindowController.updateAppItemsLocked(list);
    }

    public final void onShowUserSwitcherRefreshRequested() {
        CoroutineTracingKt.launchTraced$default(this.bgScope, "OplusQSQuickEntranceComponent-launch-refreshShowUserSwitcher", (CoroutineContext) null, (CoroutineStart) null, new C02431(null), 6);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    public void onUserInfoChanged(String str, Drawable drawable) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSQuickEntranceComponent-->".concat("icon: " + drawable));
        }
        this.userDescription = str;
        this.userIcon = drawable;
        refreshUserInfo();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        ContentResolver contentResolver;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSQuickEntranceComponent-->onViewAttached");
        }
        setQuickEntranceContainerViewAlpha(1.0f);
        Function1 function1 = this.statusBarAlphaUpdater;
        if (function1 != null) {
            function1.invoke(Float.valueOf(1.0f));
        }
        this.continuePullDownTextView.setVisibility(8);
        this.qsFgs.addCallBack(this);
        this.viewModel.onBind();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("registerEditModeOpenStateObserver hasRegister: ", "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings", this.hasEditRegister);
        }
        if (!this.hasEditRegister) {
            Context context = getContext();
            if (context != null && (contentResolver = context.getContentResolver()) != null) {
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(QS_EDIT_HAS_OPEN), false, this.editModeOpenStateObserver, this.userId);
            }
            this.hasEditRegister = true;
        }
        this.qsQSQuickEntrance.addCallBack(this);
        this.qsPanelAnimatorManager.addExpandFractionCallback(this.qsPanelExpandFractionListener);
        RepeatWhenAttachedKt.repeatWhenAttached(this.view, Dispatchers.getMain().getImmediate(), new C02442(null));
        refreshItemViewBackground$1();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        ContentResolver contentResolver;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSQuickEntranceComponent-->onViewDetached");
        }
        cancelScenarioPreviewStatusRunnables();
        this.qsFgs.removeCallBack(this);
        this.viewModel.onDestroy();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("unregisterEditModeOpenStateObserve hasRegister: ", "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings", this.hasEditRegister);
        }
        if (this.hasEditRegister) {
            Context context = getContext();
            if (context != null && (contentResolver = context.getContentResolver()) != null) {
                contentResolver.unregisterContentObserver(this.editModeOpenStateObserver);
            }
            this.hasEditRegister = false;
        }
        this.qsQSQuickEntrance.removeCallBack(this);
        this.qsPanelAnimatorManager.removeExpandFractionCallback(this.qsPanelExpandFractionListener);
    }

    public final void refreshItemViewBackground$1() {
        if (this.view.getVisibility() == 0) {
            QsColorUtil.INSTANCE.getClass();
            if (QsColorUtil.isSuperPowerSaveState) {
                return;
            }
            if (this.editBtn.getVisibility() == 0) {
                getEditBtnBackgroundProxy().refreshViewBackground();
            }
            if (this.settingsButton.getVisibility() == 0) {
                getSettingsBtnIconBackgroundProxy().refreshViewBackground();
            }
            if (this.multiUserBtn.getVisibility() == 0) {
                getMultiUserBackgroundProxy().refreshViewBackground();
            }
        }
    }

    public final void refreshUserInfo() {
        ImageView imageView = this.userImage;
        imageView.setImageDrawable(this.userIcon);
        imageView.setContentDescription(this.userDescription);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
    }

    public final void resetTranslationYExpand() {
        float f = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue() ? -60.0f : (-this.cellAreaProvider.getCellAreaConfig().getC5Height()) - 20.0f;
        float fCoerceIn = RangesKt___RangesKt.coerceIn(0.0f * f, f, 0.0f);
        this.settingsButton.setTranslationY(fCoerceIn);
        this.multiUserBtn.setTranslationY(fCoerceIn);
        this.editBtn.setTranslationY(fCoerceIn);
        this.editBtnRedDot.setTranslationY(fCoerceIn);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        super.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
        ((OplusQSQuickEntranceContainerView) this.mView).setTranslationY(f);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        ViewGroup viewGroup = (ViewGroup) this.mView;
        OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 = new OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1(0);
        oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1.f$0 = function1;
        VarHandle.storeStoreFence();
        EmptyAreaGestureProxy emptyAreaGestureProxy = new EmptyAreaGestureProxy(viewGroup, oplusQSQuickEntranceComponent$$ExternalSyntheticLambda1);
        ConstraintLayout constraintLayout = (OplusQSQuickEntranceContainerView) this.mView;
        ViewOnTouchListenerC02451 viewOnTouchListenerC02451 = new ViewOnTouchListenerC02451();
        viewOnTouchListenerC02451.$emptyAreaGestureProxy = emptyAreaGestureProxy;
        VarHandle.storeStoreFence();
        constraintLayout.setOnTouchListener(viewOnTouchListenerC02451);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        super.setLayerType(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("listening: ", "OplusQSQuickEntranceComponent-->", "SystemUi--QuickSettings", z);
        }
        this.viewModel.setListening(z);
        if (z) {
            updateClickAbilities();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    public final void setQuickEntranceContainerViewAlpha(float f) {
        this.editBtn.setTransitionAlpha(f);
        this.settingsButton.setTransitionAlpha(f);
        this.multiUserBtn.setTransitionAlpha(f);
        this.editBtnRedDot.setTransitionAlpha(f);
        this.continuePullDownTextView.setTransitionAlpha(1.0f - f);
    }

    public final void setStatusBarAlphaUpdater(Function1 function1) {
        this.statusBarAlphaUpdater = function1;
    }

    public final void updateClickAbilities() {
        boolean z = this.quickEntrance.getClickEnable() && !this.isEditMode;
        this.settingsButton.setClickable(z);
        this.settingsButtonRemote.setClickable(z);
        this.multiUserBtn.setClickable(z);
        this.editBtn.setClickable(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateColor " + colorType, null, 8);
        if (colorType == QSSpecialModeUpdateMonitor.ColorType.UI_MODE) {
            refreshItemViewBackground$1();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        super.updateDrawable(list, z);
    }

    public final void updateEditRedView() {
        CoroutineTracingKt.launchTraced$default(this.bgScope, "OplusQSQuickEntranceComponent-launch-updateEditRedView", (CoroutineContext) null, (CoroutineStart) null, new C02461(null), 6);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        super.updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
        refreshItemViewBackground$1();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        if (z) {
            View view = this.editBtn;
            OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
            view.setVisibility(((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue() ? 8 : 0);
            this.editBtnRedDot.setVisibility((this.isEditModeOpened || ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue()) ? 8 : 0);
        }
        int viewSize = getViewSize();
        View view2 = this.editBtn;
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        if (layoutParams == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        layoutParams.width = viewSize;
        layoutParams.height = viewSize;
        view2.setLayoutParams(layoutParams);
        FrameLayout frameLayout = this.settingsButton;
        ViewGroup.LayoutParams layoutParams2 = frameLayout.getLayoutParams();
        if (layoutParams2 == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        layoutParams2.width = viewSize;
        layoutParams2.height = viewSize;
        frameLayout.setLayoutParams(layoutParams2);
        View view3 = this.multiUserBtn;
        ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
        if (layoutParams3 == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        layoutParams3.width = viewSize;
        layoutParams3.height = viewSize;
        view3.setLayoutParams(layoutParams3);
        View view4 = this.leftPlaceHolder;
        ViewGroup.LayoutParams layoutParams4 = view4.getLayoutParams();
        if (layoutParams4 == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        layoutParams4.width = viewSize;
        layoutParams4.height = viewSize;
        view4.setLayoutParams(layoutParams4);
        this.view.applyViewArrangement(((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue());
        refreshItemViewBackground$1();
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
    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
        this.view.setTransitionAlpha(f);
    }

    public final void updateVisibility$6() {
        if (((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue()) {
            this.view.setVisibility(8);
        } else {
            boolean z = this.isEditMode;
            ConstraintLayout constraintLayout = this.view;
            if (z) {
                constraintLayout.setVisibility(4);
            } else {
                constraintLayout.setVisibility(0);
            }
        }
        refreshItemViewBackground$1();
    }
}
