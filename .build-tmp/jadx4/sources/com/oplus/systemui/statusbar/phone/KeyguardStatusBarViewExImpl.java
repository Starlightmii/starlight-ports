package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.CarrierText;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.display.dagger.SystemUIDisplaySubcomponent;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.layout.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewEx;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.StatusIconContainer$;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.anim.OplusPanelKeyguardAnimControllerImpl;
import com.oplus.systemui.seamless.FakeBinder;
import com.oplus.systemui.statusbar.FixedStatusBarControllerExImpl;
import com.oplus.systemui.statusbar.OplusKeyguardStatusBarClockController;
import com.oplus.systemui.statusbar.OplusKeyguardStatusBarColorController;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicContentLayout;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.receiver.FixedStatusBarReceiver;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.anim.StatusBarCoordAnimationController;
import com.oplus.systemui.statusbar.widget.OplusKeyguardStatusBarClock;
import com.oplus.systemui.statusbar.widget.OplusStatCarrierText;
import com.oplus.systemui.statusbar.widget.SysuiLifecycle;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.statusbar.controller.ScreenBurnInProtector;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStatusBarViewExImpl extends KeyguardStatusBarViewEx implements ConfigurationController.ConfigurationListener, IReceiverListener, ScreenBurnInProtector.OnBurnInPreventCallback, SystemTypefaceHelper.SystemUIFontCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardStatusBarViewEx";
    private StatBatteryMeterView batteryView;
    private final Context context;
    public View cutoutSpace;
    private final ArrayList<Rect> emptyTintRect = new ArrayList<>();
    private final Lazy fakeBinder;
    private final FixedStatusBarControllerExImpl fixedStatusBarControllerExt;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private TintedIconManager iconManager;
    public OplusStatCarrierText keyguardCarrierTextView;
    private final KeyguardLogger keyguardLogger;
    private OplusKeyguardStatusBarClock keyguardStatusBarClock;
    public ViewGroup keyguardStatusBarContentView;
    public ViewGroup keyguardStatusBarLeftContView;
    public KeyguardStatusBarView keyguardStatusBarView;
    private final ConfigurationController mConfigurationController;
    private View mSystemIconsContainer;
    private NotificationPanelViewController notificationPanelViewController;
    private final kotlin.Lazy oplusKeyguardStatusBarClockController$delegate;
    private final Lazy oplusKeyguardStatusBarColorController;
    private final Lazy oplusPanelKeyguardAnimControllerImpl;
    public float panelKgdStatusBarAnimAlpha;
    private final PerDisplayRepository perDisplaySubcomponentRepo;
    private final StatusBarCoordAnimationController statusBarCoordAnimationController;
    private final StatusBarPaddingProvider statusBarPaddingProvider;
    public ViewGroup statusIconArea;
    public StatusIconContainer statusIconContainer;
    private final KeyguardStatusBarViewExImpl$statusIconContainerHiddenReceiver$1 statusIconContainerHiddenReceiver;
    private final KeyguardStatusBarViewExImpl$sysStateControllerListener$1 sysStateControllerListener;
    private final SysUiStateControllerImpl sysUiStateControllerImpl;
    public int themeResId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$onAttachedToWindow$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            return Integer.valueOf(((SystemTypefaceHelper) ((CallableReference) this).receiver).mapStatusCommonTextFontWeight(((Number) obj).intValue()));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ KeyguardStatusBarViewController $keyguardStatusBarViewController;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$onViewAttached$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01291 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardStatusBarViewController $keyguardStatusBarViewController;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ KeyguardStatusBarViewExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$onViewAttached$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01301 extends SuspendLambda implements Function2 {
                final /* synthetic */ KeyguardStatusBarViewController $keyguardStatusBarViewController;
                int label;
                final /* synthetic */ KeyguardStatusBarViewExImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01301(KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl, KeyguardStatusBarViewController keyguardStatusBarViewController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = keyguardStatusBarViewExImpl;
                    this.$keyguardStatusBarViewController = keyguardStatusBarViewController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01301(this.this$0, this.$keyguardStatusBarViewController, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow statusbarAnimAlpha = ((OplusPanelKeyguardAnimControllerImpl) this.this$0.oplusPanelKeyguardAnimControllerImpl.get()).getStatusbarAnimAlpha();
                        final KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl = this.this$0;
                        final KeyguardStatusBarViewController keyguardStatusBarViewController = this.$keyguardStatusBarViewController;
                        FlowCollector flowCollector = new FlowCollector() { // from class: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl.onViewAttached.1.1.1.1
                            public final Object emit(Object obj2, Continuation continuation) {
                                keyguardStatusBarViewExImpl.panelKgdStatusBarAnimAlpha = ((Number) obj2).floatValue();
                                keyguardStatusBarViewController.updateViewState$1();
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        if (statusbarAnimAlpha.collect(flowCollector, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$onViewAttached$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ KeyguardStatusBarViewExImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = keyguardStatusBarViewExImpl;
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
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow statusbarSystemIconsAnimAlpha = ((OplusPanelKeyguardAnimControllerImpl) this.this$0.oplusPanelKeyguardAnimControllerImpl.get()).getStatusbarSystemIconsAnimAlpha();
                        final KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl = this.this$0;
                        FlowCollector flowCollector = new FlowCollector() { // from class: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl.onViewAttached.1.1.2.1
                            /* JADX WARN: Multi-variable type inference failed */
                            /* JADX WARN: Type inference failed for: r0v0 */
                            /* JADX WARN: Type inference failed for: r0v1, types: [android.view.View] */
                            /* JADX WARN: Type inference failed for: r0v2 */
                            public final Object emit(Object obj2, Continuation continuation) {
                                float fFloatValue = ((Number) obj2).floatValue();
                                LinearLayout linearLayout = keyguardStatusBarViewExImpl.statusIconContainer;
                                if (linearLayout == null) {
                                    linearLayout = null;
                                }
                                linearLayout.setAlpha(fFloatValue);
                                View view = keyguardStatusBarViewExImpl.statusIconContainer;
                                if (view == null) {
                                    view = null;
                                }
                                view.setVisibility(view.getAlpha() > 0.0f ? 0 : 4);
                                StatBatteryMeterView statBatteryMeterView = keyguardStatusBarViewExImpl.batteryView;
                                if (statBatteryMeterView != null) {
                                    KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl2 = keyguardStatusBarViewExImpl;
                                    ImageView imageView = (ImageView) statBatteryMeterView.findViewById(2131362241);
                                    TextView textView = (TextView) statBatteryMeterView.findViewById(2131362245);
                                    if (imageView.getVisibility() != 0 && textView.getVisibility() != 0) {
                                        CarrierText carrierText = keyguardStatusBarViewExImpl2.keyguardCarrierTextView;
                                        if (carrierText == null) {
                                            carrierText = null;
                                        }
                                        carrierText.setAlpha(fFloatValue);
                                        OplusStatCarrierText oplusStatCarrierText = keyguardStatusBarViewExImpl2.keyguardCarrierTextView;
                                        ?? r0 = oplusStatCarrierText != null ? oplusStatCarrierText : 0;
                                        r0.setVisibility(r0.getAlpha() > 0.0f ? 0 : 4);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        if (statusbarSystemIconsAnimAlpha.collect(flowCollector, this) == coroutine_suspended) {
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
            public C01291(KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl, KeyguardStatusBarViewController keyguardStatusBarViewController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = keyguardStatusBarViewExImpl;
                this.$keyguardStatusBarViewController = keyguardStatusBarViewController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C01291 c01291 = new C01291(this.this$0, this.$keyguardStatusBarViewController, continuation);
                c01291.L$0 = obj;
                return c01291;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01301(this.this$0, this.$keyguardStatusBarViewController, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(KeyguardStatusBarViewController keyguardStatusBarViewController, Continuation continuation) {
            super(3, continuation);
            this.$keyguardStatusBarViewController = keyguardStatusBarViewController;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = KeyguardStatusBarViewExImpl.this.new AnonymousClass1(this.$keyguardStatusBarViewController, (Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.STARTED;
                C01291 c01291 = new C01291(KeyguardStatusBarViewExImpl.this, this.$keyguardStatusBarViewController, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01291, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: $r8$lambda$LrVhP_ldInLG-jaz0pt3a73mPGM, reason: not valid java name */
    public static OplusKeyguardStatusBarClockController m465$r8$lambda$LrVhP_ldInLGjaz0pt3a73mPGM(KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl) {
        return new OplusKeyguardStatusBarClockController(keyguardStatusBarViewExImpl.keyguardLogger);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$statusIconContainerHiddenReceiver$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$sysStateControllerListener$1] */
    public KeyguardStatusBarViewExImpl(Context context, StatusBarPaddingProvider statusBarPaddingProvider, StatusBarCoordAnimationController statusBarCoordAnimationController, HiddenIconDispatcher hiddenIconDispatcher, Lazy lazy, Lazy lazy2, SysUiStateControllerImpl sysUiStateControllerImpl, ConfigurationController configurationController, KeyguardLogger keyguardLogger, Lazy lazy3, PerDisplayRepository perDisplayRepository) {
        this.context = context;
        this.statusBarPaddingProvider = statusBarPaddingProvider;
        this.statusBarCoordAnimationController = statusBarCoordAnimationController;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.oplusPanelKeyguardAnimControllerImpl = lazy;
        this.oplusKeyguardStatusBarColorController = lazy2;
        this.sysUiStateControllerImpl = sysUiStateControllerImpl;
        this.mConfigurationController = configurationController;
        this.keyguardLogger = keyguardLogger;
        this.fakeBinder = lazy3;
        this.perDisplaySubcomponentRepo = perDisplayRepository;
        KeyguardStatusBarViewExImpl$$ExternalSyntheticLambda0 keyguardStatusBarViewExImpl$$ExternalSyntheticLambda0 = new KeyguardStatusBarViewExImpl$$ExternalSyntheticLambda0();
        keyguardStatusBarViewExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusKeyguardStatusBarClockController$delegate = LazyKt__LazyJVMKt.lazy(keyguardStatusBarViewExImpl$$ExternalSyntheticLambda0);
        this.fixedStatusBarControllerExt = new FixedStatusBarControllerExImpl(context, statusBarPaddingProvider);
        this.panelKgdStatusBarAnimAlpha = 1.0f;
        this.statusIconContainerHiddenReceiver = new HiddenIconDispatcher.HiddenReceiver() { // from class: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$statusIconContainerHiddenReceiver$1
            public final void dump(PrintWriter printWriter, String[] strArr) {
            }

            @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
            public final void onHiddenChanged(ArrayList arrayList, boolean z) {
                LinearLayout linearLayout = this.this$0.statusIconContainer;
                if (linearLayout == null) {
                    linearLayout = null;
                }
                if (linearLayout.isLayoutRequested()) {
                    return;
                }
                ((StatusIconContainer) linearLayout).mIsPostUpdateStates = true;
                ((StatusIconContainer) linearLayout).mForceDisappearAnimateOnce = true;
                StatusIconContainer$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new StatusIconContainer$.ExternalSyntheticLambda0();
                externalSyntheticLambda0.f$0 = linearLayout;
                externalSyntheticLambda0.f$1 = false;
                VarHandle.storeStoreFence();
                linearLayout.post(externalSyntheticLambda0);
            }

            @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
            public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
                super.setHiddenIconListChanged(z, arrayList);
            }
        };
        this.sysStateControllerListener = new SysUiStateController.ISysUiStateListener() { // from class: com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl$sysStateControllerListener$1
            public final void onPanelExpandedChanged(boolean z, View view) {
                ViewGroup viewGroup = this.this$0.keyguardStatusBarContentView;
                if (viewGroup != null) {
                    if (z) {
                        viewGroup.setAlpha(0.0f);
                        if (view != null) {
                            view.setVisibility(0);
                        }
                    } else {
                        viewGroup.setAlpha(1.0f);
                        if (view != null) {
                            view.setVisibility(4);
                        }
                    }
                }
                this.this$0.fixedStatusBarControllerExt.onPanelExpandedChanged(z);
            }
        };
    }

    private final OplusKeyguardStatusBarClockController getOplusKeyguardStatusBarClockController() {
        return (OplusKeyguardStatusBarClockController) this.oplusKeyguardStatusBarClockController$delegate.getValue();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(TAG);
        OplusLockIconView$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("themeResId: ", this.themeResId, 2132019268, "  wallpaper light:", " dark:"), 2132019247, printWriter);
        StatBatteryMeterView statBatteryMeterView = this.batteryView;
        if (statBatteryMeterView != null) {
            statBatteryMeterView.dump(printWriter);
        }
    }

    public final View getKeyguardStatusBarView() {
        KeyguardStatusBarView keyguardStatusBarView = this.keyguardStatusBarView;
        if (keyguardStatusBarView == null) {
            return null;
        }
        return keyguardStatusBarView;
    }

    public int getLastRotation() {
        return this.statusBarPaddingProvider.getLastRotation();
    }

    public float getPanelKgdAnimAlpha() {
        return this.panelKgdStatusBarAnimAlpha;
    }

    public final boolean hookDozingState(boolean z) {
        if (!((OplusPanelKeyguardAnimControllerImpl) this.oplusPanelKeyguardAnimControllerImpl.get()).isPanelKydAnimEnable()) {
            return !z;
        }
        if (z) {
            SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
            if (!companion.isPanoramicAodEnable(this.context) && !companion.isWorkShopAodEnable(this.context) && !ScrimUtil.isLowGaussianLevel(this.context)) {
                return false;
            }
        }
        return true;
    }

    public final void onAttachedToWindow() {
        StatusBarContentInsetsProvider statusBarContentInsetsProvider = ((SystemUIDisplaySubcomponent) this.perDisplaySubcomponentRepo.getOrDefault(this.context.getDisplayId())).getStatusBarContentInsetsProvider();
        KeyguardStatusBarView keyguardStatusBarView = this.keyguardStatusBarView;
        KeyguardStatusBarView keyguardStatusBarView2 = keyguardStatusBarView == null ? null : keyguardStatusBarView;
        if (keyguardStatusBarView == null) {
            keyguardStatusBarView = null;
        }
        keyguardStatusBarView2.updateWindowInsets(keyguardStatusBarView.getRootWindowInsets(), statusBarContentInsetsProvider);
        FixedStatusBarReceiver.Companion.getInstance().addListener(this);
        ScreenBurnInProtector.Companion.getInstance().addCallback(this);
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        StatBatteryMeterView statBatteryMeterView = (StatBatteryMeterView) (relativeLayout != null ? relativeLayout : null).findViewById(2131362233);
        this.batteryView = statBatteryMeterView;
        if (statBatteryMeterView != null) {
            this.hiddenIconDispatcher.addHiddenReceiver(StatusBarLocation.KEYGUARD, statBatteryMeterView);
        }
        this.sysUiStateControllerImpl.addListener(this.sysStateControllerListener);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->onAttachedToWindow");
        }
        this.mConfigurationController.addCallback(this);
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(this.context).addCallback(this, new AnonymousClass3(1, companion.getInstance(this.context), SystemTypefaceHelper.class, "mapStatusCommonTextFontWeight", "mapStatusCommonTextFontWeight(I)I", 0));
    }

    public final void onBurnInPreventTrigger(float f) {
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        if (relativeLayout == null) {
            relativeLayout = null;
        }
        relativeLayout.setTranslationX(f);
    }

    public final void onDetachedFromWindow() {
        FixedStatusBarReceiver.Companion.getInstance().removeListener(this);
        ScreenBurnInProtector.Companion.getInstance().removeCallback(this);
        this.statusBarCoordAnimationController.onKeyguardStatusBarViewDestroy();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->onDetachedFromWindow");
        }
        StatBatteryMeterView statBatteryMeterView = this.batteryView;
        if (statBatteryMeterView != null) {
            this.hiddenIconDispatcher.removeHiddenReceiver(StatusBarLocation.KEYGUARD, statBatteryMeterView);
        }
        this.sysUiStateControllerImpl.removeListener(this.sysStateControllerListener);
        this.mConfigurationController.removeCallback(this);
        SystemTypefaceHelper.Companion.getInstance(this.context).removeCallback(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onFinishInflate(KeyguardStatusBarView keyguardStatusBarView) {
        this.keyguardStatusBarView = keyguardStatusBarView;
        if (keyguardStatusBarView == null) {
            keyguardStatusBarView = null;
        }
        this.keyguardStatusBarLeftContView = (ViewGroup) keyguardStatusBarView.requireViewById(2131363606);
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        if (relativeLayout == null) {
            relativeLayout = null;
        }
        this.keyguardStatusBarContentView = (ViewGroup) relativeLayout.requireViewById(2131365362);
        ViewParent viewParent = this.keyguardStatusBarLeftContView;
        if ((viewParent == null ? null : viewParent) instanceof HiddenIconDispatcher.HiddenReceiver) {
            if (viewParent == null) {
                viewParent = null;
            }
            ((IScaledAncestorView) viewParent).setScaleAnimateEnable(false);
            HiddenIconDispatcher hiddenIconDispatcher = this.hiddenIconDispatcher;
            ViewGroup viewGroup = this.keyguardStatusBarLeftContView;
            if (viewGroup == null) {
                viewGroup = null;
            }
            SysuiLifecycle.ViewLifecycle viewLifecycleViewAttachLifecycle = SysuiLifecycle.viewAttachLifecycle(viewGroup);
            StatusBarLocation statusBarLocation = StatusBarLocation.KEYGUARD;
            ViewParent viewParent2 = this.keyguardStatusBarLeftContView;
            if (viewParent2 == null) {
                viewParent2 = null;
            }
            hiddenIconDispatcher.observe(viewLifecycleViewAttachLifecycle, statusBarLocation, (HiddenIconDispatcher.HiddenReceiver) viewParent2);
            ViewParent viewParent3 = this.keyguardStatusBarLeftContView;
            if (viewParent3 == null) {
                viewParent3 = null;
            }
            ((IScaledAncestorView) viewParent3).setScaleAnimateEnable(true);
        }
        RelativeLayout relativeLayout2 = this.keyguardStatusBarView;
        if (relativeLayout2 == null) {
            relativeLayout2 = null;
        }
        this.keyguardCarrierTextView = (OplusStatCarrierText) relativeLayout2.requireViewById(2131363560);
        RelativeLayout relativeLayout3 = this.keyguardStatusBarView;
        if (relativeLayout3 == null) {
            relativeLayout3 = null;
        }
        this.keyguardStatusBarClock = (OplusKeyguardStatusBarClock) relativeLayout3.findViewById(2131363605);
        RelativeLayout relativeLayout4 = this.keyguardStatusBarView;
        if (relativeLayout4 == null) {
            relativeLayout4 = null;
        }
        View viewFindViewById = relativeLayout4.findViewById(2131365534);
        this.mSystemIconsContainer = viewFindViewById;
        if (viewFindViewById instanceof HiddenIconDispatcher.HiddenReceiver) {
            ((IScaledAncestorView) viewFindViewById).setScaleAnimateEnable(false);
            this.hiddenIconDispatcher.observe(SysuiLifecycle.viewAttachLifecycle(this.mSystemIconsContainer), StatusBarLocation.KEYGUARD, (HiddenIconDispatcher.HiddenReceiver) this.mSystemIconsContainer);
            ((IScaledAncestorView) this.mSystemIconsContainer).setScaleAnimateEnable(true);
        }
        RelativeLayout relativeLayout5 = this.keyguardStatusBarView;
        if (relativeLayout5 == null) {
            relativeLayout5 = null;
        }
        this.cutoutSpace = relativeLayout5.requireViewById(2131362777);
        RelativeLayout relativeLayout6 = this.keyguardStatusBarView;
        if (relativeLayout6 == null) {
            relativeLayout6 = null;
        }
        this.statusIconArea = (ViewGroup) relativeLayout6.requireViewById(2131365377);
        RelativeLayout relativeLayout7 = this.keyguardStatusBarView;
        if (relativeLayout7 == null) {
            relativeLayout7 = null;
        }
        this.statusIconContainer = relativeLayout7.requireViewById(2131365358);
        StatusBarCoordAnimationController statusBarCoordAnimationController = this.statusBarCoordAnimationController;
        ViewGroup viewGroup2 = this.keyguardStatusBarView;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        statusBarCoordAnimationController.onKeyguardStatusBarViewCreated(viewGroup2);
        getOplusKeyguardStatusBarClockController().bindView(this.keyguardStatusBarClock);
        OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = (OplusKeyguardStatusBarColorController) this.oplusKeyguardStatusBarColorController.get();
        KeyguardStatusBarView keyguardStatusBarView2 = this.keyguardStatusBarView;
        oplusKeyguardStatusBarColorController.bindView(keyguardStatusBarView2 != null ? keyguardStatusBarView2 : null);
    }

    public final void onLocaleListChanged() {
        OplusKeyguardStatusBarClock oplusKeyguardStatusBarClock = this.keyguardStatusBarClock;
        if (oplusKeyguardStatusBarClock != null) {
            oplusKeyguardStatusBarClock.refreshDateTimePatternGenerator();
        }
    }

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public void onReceive(Context context, Intent intent) {
        if (FixedStatusBarReceiver.ACTION_FIXED_STATUS_BAR.equals(intent.getAction())) {
            boolean booleanExtra = intent.getBooleanExtra(FixedStatusBarReceiver.EXTRA_FIXED_STATUS_BAR, false);
            String stringExtra = intent.getStringExtra(FixedStatusBarReceiver.EXTRA_FIXED_STATUS_BAR_TIME);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->".concat("onReceive(), fixedStatusBar = " + booleanExtra + ", fixedTime = " + stringExtra));
            }
            ViewGroup viewGroup = this.keyguardStatusBarLeftContView;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.setVisibility(booleanExtra ? 8 : 0);
            ViewGroup viewGroup2 = this.statusIconArea;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setVisibility(booleanExtra ? 8 : 0);
            View view = this.cutoutSpace;
            if (view == null) {
                view = null;
            }
            view.setVisibility(booleanExtra ? 8 : 0);
            FixedStatusBarControllerExImpl fixedStatusBarControllerExImpl = this.fixedStatusBarControllerExt;
            KeyguardStatusBarView keyguardStatusBarView = this.keyguardStatusBarView;
            fixedStatusBarControllerExImpl.updateFixedKeyguardStatusBarView(booleanExtra, keyguardStatusBarView != null ? keyguardStatusBarView : null, stringExtra);
        }
    }

    public void onThemeChanged(TintedIconManager tintedIconManager) {
        if (this.themeResId != this.context.getThemeResId()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onThemeChanged themeResId:", this.themeResId, this.context.getThemeResId(), " context.themeResId:", "KeyguardStatusBarViewEx-->"));
            }
            this.themeResId = this.context.getThemeResId();
            KeyguardStatusBarView keyguardStatusBarView = this.keyguardStatusBarView;
            if (keyguardStatusBarView == null) {
                keyguardStatusBarView = null;
            }
            keyguardStatusBarView.getClass();
        }
    }

    public void onViewAttached(KeyguardStatusBarViewController keyguardStatusBarViewController, TintedIconManager tintedIconManager) {
        this.iconManager = tintedIconManager;
        StatusIconContainer statusIconContainer = this.statusIconContainer;
        if (statusIconContainer == null) {
            statusIconContainer = null;
        }
        StatusBarLocation statusBarLocation = StatusBarLocation.KEYGUARD;
        statusIconContainer.setHiddenDispatcherRegisterLocation(statusBarLocation);
        this.hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, this.statusIconContainerHiddenReceiver);
        OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController = getOplusKeyguardStatusBarClockController();
        if (oplusKeyguardStatusBarClockController != null) {
            oplusKeyguardStatusBarClockController.onViewAttached();
        }
        ((OplusKeyguardStatusBarColorController) this.oplusKeyguardStatusBarColorController.get()).bindIconManager(tintedIconManager);
        ((OplusKeyguardStatusBarColorController) this.oplusKeyguardStatusBarColorController.get()).onViewAttached();
        ((FakeBinder) this.fakeBinder.get()).setKgdFakeView();
        View view = this.keyguardStatusBarView;
        if (view == null) {
            view = null;
        }
        RepeatWhenAttachedKt.repeatWhenAttached$default(view, Dispatchers.getMain().getImmediate(), new AnonymousClass1(keyguardStatusBarViewController, null), 2);
    }

    public final void onViewDetached() {
        this.hiddenIconDispatcher.removeHiddenReceiver(StatusBarLocation.KEYGUARD, this.statusIconContainerHiddenReceiver);
        OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController = getOplusKeyguardStatusBarClockController();
        if (oplusKeyguardStatusBarClockController != null) {
            oplusKeyguardStatusBarClockController.onViewDetached();
        }
        ((OplusKeyguardStatusBarColorController) this.oplusKeyguardStatusBarColorController.get()).onViewDetached();
        ((FakeBinder) this.fakeBinder.get()).clearKgdFakeView();
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public void setFontTypeface(Typeface typeface, int i) {
        this.keyguardLogger.log(TAG, LogLevel.DEBUG, "onFontChanged", (Throwable) null);
        OplusKeyguardStatusBarClock oplusKeyguardStatusBarClock = this.keyguardStatusBarClock;
        if (oplusKeyguardStatusBarClock != null) {
            oplusKeyguardStatusBarClock.setTypeface(typeface);
        }
        CarrierText carrierText = this.keyguardCarrierTextView;
        if (carrierText != null) {
            carrierText.setTypeface(typeface);
        }
    }

    public final void setKeyguardStatusBarPadding() {
        StatusBarPaddingProvider statusBarPaddingProvider = this.statusBarPaddingProvider;
        ViewGroup viewGroup = this.keyguardStatusBarContentView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        statusBarPaddingProvider.updateKeyguardStatusBarPadding(viewGroup, new Pair<>(0, 0));
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        if (relativeLayout == null) {
            relativeLayout = null;
        }
        relativeLayout.requestLayout();
        StatusBarPaddingProvider statusBarPaddingProvider2 = this.statusBarPaddingProvider;
        RelativeLayout relativeLayout2 = this.keyguardStatusBarView;
        this.hiddenIconDispatcher.setStatusBarPaddingRect(StatusBarLocation.KEYGUARD, statusBarPaddingProvider2.getHomeStatusBarLeftRightPadding((relativeLayout2 != null ? relativeLayout2 : null).toString(), new Pair<>(0, 0), new Pair<>(0, 0)));
    }

    public void setNotificationPanelController(NotificationPanelViewController notificationPanelViewController) {
        this.notificationPanelViewController = notificationPanelViewController;
    }

    public void updateCustKeyguardStatusBarHeight(ViewGroup.LayoutParams layoutParams, DisplayCutout displayCutout) {
        Insets waterfallInsets;
        layoutParams.height = SystemBarUtils.getStatusBarHeight(this.context) - ((displayCutout == null || (waterfallInsets = displayCutout.getWaterfallInsets()) == null) ? 0 : waterfallInsets.top);
        RelativeLayout relativeLayout = this.keyguardStatusBarView;
        if (relativeLayout == null) {
            relativeLayout = null;
        }
        relativeLayout.setLayoutParams(layoutParams);
    }

    public void updateIconsAndTextColors(ArrayList<Rect> arrayList, float f, int i) {
        this.themeResId = this.context.getThemeResId();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            int themeResId = this.context.getThemeResId();
            int userId = this.context.getUserId();
            String hexString = Integer.toHexString(i);
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateIconsAndTextColors ThemeResId:", themeResId, userId, " UserId: ", " Theme_SystemUI_LightWallpaperResID: ");
            RectList$.ExternalSyntheticOutline0.m(2132019268, 2132019247, " Theme_SystemUIResID: ", " iconColor: ", sbM);
            sbM.append(hexString);
            sbM.append(" intensity: ");
            sbM.append(f);
            Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->".concat(sbM.toString()));
        }
        getOplusKeyguardStatusBarClockController().updateColor(i);
    }

    public final void updateLayoutParamsForCutout() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->updateLayoutParamsForCutout");
        }
        ViewGroup viewGroup = this.keyguardStatusBarLeftContView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        layoutParams.addRule(16, 2131362777);
        layoutParams.width = -1;
        ViewGroup viewGroup2 = this.keyguardStatusBarLeftContView;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        ((DynamicContentLayout) viewGroup2).setNeedRelayout(false);
        ViewGroup viewGroup3 = this.keyguardStatusBarLeftContView;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        ((DynamicContentLayout) viewGroup3).setMaxWidth(0);
        ViewGroup viewGroup4 = this.statusIconArea;
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) (viewGroup4 != null ? viewGroup4 : null).getLayoutParams();
        layoutParams2.addRule(17, 2131362777);
        layoutParams2.width = -1;
    }

    public final void updateLayoutParamsNoCutout() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "KeyguardStatusBarViewEx-->updateLayoutParamsNoCutout");
        }
        ViewGroup viewGroup = this.keyguardStatusBarLeftContView;
        if (viewGroup == null) {
            viewGroup = null;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        layoutParams.removeRule(16);
        layoutParams.width = -2;
        ViewGroup viewGroup2 = this.statusIconArea;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) viewGroup2.getLayoutParams();
        layoutParams2.addRule(17, 2131363606);
        layoutParams2.width = -2;
        ViewGroup viewGroup3 = this.keyguardStatusBarLeftContView;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        ((DynamicContentLayout) viewGroup3).setNeedRelayout(true);
        ViewGroup viewGroup4 = this.keyguardStatusBarLeftContView;
        ((DynamicContentLayout) (viewGroup4 != null ? viewGroup4 : null)).setMaxWidth(this.context.getResources().getDimensionPixelSize(2131168712));
    }
}
