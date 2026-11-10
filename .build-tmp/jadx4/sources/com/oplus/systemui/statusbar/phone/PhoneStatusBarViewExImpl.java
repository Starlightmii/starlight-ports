package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Trace;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.data.repository.StatusBarConfigurationController;
import com.android.systemui.statusbar.data.repository.StatusBarModePerDisplayRepository;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewEx;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.ShouldShowForDisplay;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.keyguard.helper.ScreenResolutionHelper;
import com.oplus.systemui.privacy.PrivacyDotLayoutState;
import com.oplus.systemui.statusbar.FixedStatusBarControllerEx;
import com.oplus.systemui.statusbar.FixedStatusBarControllerExImpl;
import com.oplus.systemui.statusbar.clock.ui.ClockSecondsViewBinder;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.receiver.FixedStatusBarReceiver;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.anim.StatusBarCoordAnimationController;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplus.systemui.statusbar.util.StatusbarClickHelper;
import com.oplus.systemui.statusbar.widget.NotificationBadgeView;
import com.oplus.systemui.statusbar.widget.StatClock;
import com.oplus.systemui.util.ScreenUtils;
import com.oplus.zoom.ui.tips.TipsManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.statusbar.controller.ScreenBurnInProtector;
import com.oplusos.systemui.statusbar.util.AnimationDuringFoldingUtil;
import dagger.Lazy;
import io.grpc.Status$;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PhoneStatusBarViewExImpl extends PhoneStatusBarViewEx implements IReceiverListener, ScreenBurnInProtector.OnBurnInPreventCallback {
    public static final Companion Companion = new Companion();
    private final MutableStateFlow _privacyDotLayoutStateFlow;
    public FrameLayout animationWindowView;
    private StatBatteryMeterView batteryView;
    private final BroadcastSender broadcastSender;
    private View capsulePluginContainer;
    private TextView chipShortTimeView;
    private TextView chipTextView;
    private TextView chipTimeView;
    private final StatusbarClickHelper clickHelper;
    private StatClock clock;
    private final Lazy clockSecondsViewBinder;
    private final StatusBarConfigurationController configurationController;
    private final PhoneStatusBarViewExImpl$configurationListener$1 configurationListener;
    public int cutoutLeftPadding;
    public int cutoutRightPadding;
    private final DarkIconDispatcher darkIconDispatcher;
    private final Flow disableAnimation;
    private final StateFlow disableForPanel;
    public LinearLayout dynamicIconGroup;
    private TextView dynamicText;
    private FixedStatusBarControllerEx fixedStatusBarControllerExt;
    public boolean foldState;
    public boolean foldStateForPadding;
    private DeviceStateController.StateChangedListener foldStateListener;
    private final PhoneStatusBarViewExImpl$handler$1 handler;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    public boolean hideStatusBarWhenSystemNotReady;
    public int holePosition;
    private Rect homePaddingRect;
    public boolean isLayoutRtl;
    public boolean isSystemReady;
    public int lastStableDisplayWidth;
    private final Set<PhoneStatusBarViewEx.StatusBarContentPaddingChangedListener> listeners;
    private final KeyguardUpdateMonitorCallback monitorCallback;
    private ViewGroup notificationIconArea;
    public PhoneStatusBarView phoneStatusBarView;
    private final StateFlow privacyDotLayoutStateFlow;
    private final CoroutineScope scope;
    private final ScreenLifecycle screenLifecycle;
    private final PhoneStatusBarViewExImpl$screenObserver$1 screenObserver;
    public int screenResolution;
    private final ShadeInteractor shade;
    private final Lazy shadeInteractor;
    public boolean shouldRequestLayout;
    private final ShouldShowForDisplay shouldShowForDisplay;
    private View statusBarContents;
    private final StatusBarCoordAnimationController statusBarCoordAnimationController;
    private View statusBarLeft;
    public int statusBarMarginBottom;
    public int statusBarMarginLeft;
    public int statusBarMarginRight;
    public int statusBarMarginTop;
    private final StatusBarModePerDisplayRepository statusBarModePerDisplayRepository;
    private final StatusBarPaddingProvider statusBarPaddingProvider;
    private StatusIconContainer statusIcons;
    public boolean systemFoldingModeChanged;
    private final PhoneStatusBarViewExImpl$typefaceCallback$1 typefaceCallback;
    private final KeyguardUpdateMonitor updateMonitor;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return PhoneStatusBarViewExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = PhoneStatusBarViewExImpl.this.disableAnimation;
                final PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = PhoneStatusBarViewExImpl.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl.1.1
                    public final Object emit(Object obj2, Continuation continuation) {
                        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("disableAnimation=", "PhoneStatusBarViewEx-->", "SystemUi--Statusbar", zBooleanValue);
                        }
                        StatusIconContainer statusIconContainer = phoneStatusBarViewExImpl.statusIcons;
                        if (statusIconContainer != null) {
                            statusIconContainer.setEnableAnimation(!zBooleanValue);
                        }
                        phoneStatusBarViewExImpl.setNotificationDisableAnimation(zBooleanValue);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return PhoneStatusBarViewExImpl.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsAnyExpanded = ((ShadeInteractor) PhoneStatusBarViewExImpl.this.shadeInteractor.get()).isAnyExpanded();
                final PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = PhoneStatusBarViewExImpl.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl.2.1
                    public final Object emit(Object obj2, Continuation continuation) {
                        ((FixedStatusBarControllerExImpl) phoneStatusBarViewExImpl.fixedStatusBarControllerExt).onPanelExpandedChanged(((Boolean) obj2).booleanValue());
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (stateFlowIsAnyExpanded.collect(flowCollector, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$onAttachedToWindow$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            return Integer.valueOf(((SystemTypefaceHelper) ((CallableReference) this).receiver).mapStatusCommonTextFontWeight(((Number) obj).intValue()));
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$screenObserver$1] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$configurationListener$1] */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$typefaceCallback$1] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$handler$1] */
    public PhoneStatusBarViewExImpl(Context context, CoroutineScope coroutineScope, KeyguardUpdateMonitor keyguardUpdateMonitor, ScreenLifecycle screenLifecycle, StatusBarPaddingProvider statusBarPaddingProvider, StatusBarConfigurationController statusBarConfigurationController, DarkIconDispatcher darkIconDispatcher, Lazy lazy, StatusBarModePerDisplayRepository statusBarModePerDisplayRepository, StatusBarCoordAnimationController statusBarCoordAnimationController, HiddenIconDispatcher hiddenIconDispatcher, BroadcastSender broadcastSender, ShouldShowForDisplay shouldShowForDisplay, Lazy lazy2) {
        super(context);
        this.scope = coroutineScope;
        this.updateMonitor = keyguardUpdateMonitor;
        this.screenLifecycle = screenLifecycle;
        this.statusBarPaddingProvider = statusBarPaddingProvider;
        this.configurationController = statusBarConfigurationController;
        this.darkIconDispatcher = darkIconDispatcher;
        this.shadeInteractor = lazy;
        this.statusBarModePerDisplayRepository = statusBarModePerDisplayRepository;
        this.statusBarCoordAnimationController = statusBarCoordAnimationController;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.broadcastSender = broadcastSender;
        this.shouldShowForDisplay = shouldShowForDisplay;
        this.clockSecondsViewBinder = lazy2;
        this.listeners = new LinkedHashSet();
        this.homePaddingRect = new Rect();
        this.statusBarMarginRight = -1;
        this.statusBarMarginLeft = -1;
        this.statusBarMarginTop = -1;
        this.statusBarMarginBottom = -1;
        this.screenResolution = 1080;
        this.clickHelper = new StatusbarClickHelper(broadcastSender);
        this.monitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$monitorCallback$1
            public final void onSystemReady() {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->onSystemReady:");
                }
                PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = this.this$0;
                phoneStatusBarViewExImpl.isSystemReady = true;
                phoneStatusBarViewExImpl.reShowStatusBarAfterSystemNotReady();
            }
        };
        this.configurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$configurationListener$1
            public final void onLocaleListChanged() {
                Resources resources;
                Configuration configuration;
                PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    StringBuilder sb = new StringBuilder("PhoneStatusBarViewEx-->");
                    Clock clock = phoneStatusBarViewExImpl.clock;
                    sb.append("onLocaleListChanged current locale: " + ((clock == null || (resources = clock.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : configuration.getLocales()));
                    Log.d("SystemUi--Statusbar", sb.toString());
                }
                StatClock statClock = this.this$0.clock;
                if (statClock != null) {
                    statClock.refreshDateTimePatternGenerator();
                }
            }
        };
        this.typefaceCallback = new SystemTypefaceHelper.SystemUIFontCallback() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$typefaceCallback$1
            @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
            public final void setFontTypeface(Typeface typeface, int i) {
                StatClock statClock = this.this$0.clock;
                if (statClock != null) {
                    statClock.setTypeface(typeface);
                }
                TextView textView = this.this$0.chipTimeView;
                if (textView != null) {
                    textView.setTypeface(typeface);
                }
                TextView textView2 = this.this$0.chipTextView;
                if (textView2 != null) {
                    textView2.setTypeface(typeface);
                }
                TextView textView3 = this.this$0.chipShortTimeView;
                if (textView3 != null) {
                    textView3.setTypeface(typeface);
                }
                TextView textView4 = this.this$0.dynamicText;
                if (textView4 != null) {
                    textView4.setTypeface(typeface);
                }
                FrameLayout frameLayout = this.this$0.phoneStatusBarView;
                if (frameLayout == null) {
                    frameLayout = null;
                }
                NotificationBadgeView notificationBadgeView = (NotificationBadgeView) frameLayout.findViewById(2131364200);
                if (notificationBadgeView != null) {
                    notificationBadgeView.setFontTypeface(typeface);
                }
            }
        };
        this.foldState = DeviceStateController.getInstance().isOpenOrHalfOpen();
        this.foldStateForPadding = DeviceStateController.getInstance().isOpenOrHalfOpen();
        this.lastStableDisplayWidth = -1;
        boolean zIsHighGaussianLevel = ScrimUtil.isHighGaussianLevel(context);
        ShadeInteractor shadeInteractor = (ShadeInteractor) lazy.get();
        this.shade = shadeInteractor;
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$handler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                ViewPropertyAnimator viewPropertyAnimatorAnimate;
                ViewPropertyAnimator viewPropertyAnimatorAlpha;
                ViewPropertyAnimator duration;
                ViewPropertyAnimator interpolator;
                ViewPropertyAnimator listener;
                ViewPropertyAnimator viewPropertyAnimatorAnimate2;
                int i = message.what;
                if (i == 1000) {
                    if (message.obj instanceof Boolean) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->device state changed");
                        }
                        PhoneStatusBarViewExImpl.access$handleDeviceStateChanged(this.this$0, ((Boolean) message.obj).booleanValue());
                        return;
                    }
                    return;
                }
                if (i != 2000) {
                    return;
                }
                View view = this.this$0.statusBarContents;
                if (view != null && (viewPropertyAnimatorAnimate2 = view.animate()) != null) {
                    viewPropertyAnimatorAnimate2.cancel();
                }
                View view2 = this.this$0.statusBarContents;
                if (view2 == null || (viewPropertyAnimatorAnimate = view2.animate()) == null || (viewPropertyAnimatorAlpha = viewPropertyAnimatorAnimate.alpha(1.0f)) == null || (duration = viewPropertyAnimatorAlpha.setDuration(AnimationDuringFoldingUtil.ALPHA_DURATION.longValue())) == null || (interpolator = duration.setInterpolator(new AccelerateInterpolator())) == null || (listener = interpolator.setListener(new handleMessage.2(this.this$0))) == null) {
                    return;
                }
                listener.start();
            }
        };
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(PrivacyDotLayoutState.Companion.getEMPTY());
        this._privacyDotLayoutStateFlow = MutableStateFlow;
        this.privacyDotLayoutStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        this.fixedStatusBarControllerExt = new FixedStatusBarControllerExImpl(context, statusBarPaddingProvider);
        StateFlow stateFlowIsAnyFullyExpanded = zIsHighGaussianLevel ? shadeInteractor.isAnyFullyExpanded() : shadeInteractor.isAnyExpanded();
        this.disableForPanel = stateFlowIsAnyFullyExpanded;
        this.disableAnimation = FlowKt.distinctUntilChanged(FlowKt.combine(stateFlowIsAnyFullyExpanded, statusBarModePerDisplayRepository.isInFullscreenMode(), new PhoneStatusBarViewExImpl$disableAnimation$1(3, null)));
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(null), 2);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
        this.screenObserver = new ScreenLifecycle.Observer() { // from class: com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl$screenObserver$1
            public final void onScreenTurnedOff() {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->onScreenTurnedOff");
                }
                PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = this.this$0;
                phoneStatusBarViewExImpl.isSystemReady = phoneStatusBarViewExImpl.updateMonitor.mSystemReady;
                PhoneStatusBarViewExImpl phoneStatusBarViewExImpl2 = this.this$0;
                if (phoneStatusBarViewExImpl2.isSystemReady) {
                    phoneStatusBarViewExImpl2.reShowStatusBarAfterSystemNotReady();
                }
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
            }

            public final void onScreenTurningOn() {
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
            }
        };
    }

    public static final void access$handleDeviceStateChanged(PhoneStatusBarViewExImpl phoneStatusBarViewExImpl, boolean z) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        if (phoneStatusBarViewExImpl.foldState != z) {
            phoneStatusBarViewExImpl.foldState = z;
            View view = phoneStatusBarViewExImpl.statusBarContents;
            if (view != null && (viewPropertyAnimatorAnimate = view.animate()) != null) {
                viewPropertyAnimatorAnimate.cancel();
            }
            View view2 = phoneStatusBarViewExImpl.statusBarContents;
            if (view2 != null) {
                view2.setAlpha(0.0f);
            }
            phoneStatusBarViewExImpl.handler.removeMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME);
            PhoneStatusBarViewExImpl$handler$1 phoneStatusBarViewExImpl$handler$1 = phoneStatusBarViewExImpl.handler;
            boolean zSendMessageDelayed = phoneStatusBarViewExImpl$handler$1.sendMessageDelayed(phoneStatusBarViewExImpl$handler$1.obtainMessage(TipsManager.DELAY_SING_TAP_HIDE_TIME), AnimationDuringFoldingUtil.RECOVER_ALPHA_DURATION.longValue());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("handleDeviceStateChanged: sendMessageResult=", " foldState=", "PhoneStatusBarViewEx-->", zSendMessageDelayed, z));
            }
            PhoneStatusBarView phoneStatusBarView = phoneStatusBarViewExImpl.phoneStatusBarView;
            if (phoneStatusBarView != null) {
                phoneStatusBarViewExImpl.systemFoldingModeChanged = true;
                phoneStatusBarView.updateLayout();
                phoneStatusBarViewExImpl.systemFoldingModeChanged = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNotificationDisableAnimation(boolean z) {
        ViewGroup viewGroup = this.notificationIconArea;
        NotificationIconContainer notificationIconContainer = viewGroup != null ? (NotificationIconContainer) viewGroup.findViewById(2131364175) : null;
        if (notificationIconContainer != null) {
            notificationIconContainer.setDisableAnimation(z);
        }
    }

    public final /* synthetic */ void addCallback(Object obj) {
        if (obj == null) {
            addCallback((PhoneStatusBarViewEx.StatusBarContentPaddingChangedListener) null);
        } else {
            Status$.ExternalSyntheticBUOutline0.m();
        }
    }

    public final void cancelRecoverAlphaMsg() {
        Animation animation;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && hasMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME)) {
            removeMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME);
            View view = this.statusBarContents;
            if (view != null && (animation = view.getAnimation()) != null) {
                animation.cancel();
            }
            View view2 = this.statusBarContents;
            if (view2 != null) {
                view2.setAlpha(1.0f);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->remove MSG_RECOVER_ALPHA msg and recover alpha to 1");
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("PhoneStatusBarViewEx: ");
        printWriter.println("displayId: " + getContext().getDisplayId());
        printWriter.println("status bar height:" + SystemBarUtils.getStatusBarHeight(getContext()) + " dimen height:" + ScreenUtils.getStatusBarHeight(getContext()));
        View view = this.statusBarContents;
        if (view != null) {
            int visibility = view.getVisibility();
            float alpha = view.getAlpha();
            int i = this.holePosition;
            StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(alpha, visibility, "statusBarContents visible:", " alpha:", " holePosition:");
            sbM.append(i);
            printWriter.print(sbM.toString());
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(view.getPaddingRight(), view.getPaddingBottom(), " ,", "]", FloatList$.ExternalSyntheticOutline0.m(" padding: [", view.getPaddingLeft(), view.getPaddingTop(), " ,", " ,")));
            printWriter.println(" cutoutLeftPadding:" + this.cutoutLeftPadding + " cutoutRightPadding:" + this.cutoutRightPadding);
            Rect rect = this.homePaddingRect;
            StringBuilder sb = new StringBuilder(" homePaddingRect:");
            sb.append(rect);
            printWriter.println(sb.toString());
            BiometricMessageDeferral$.ExternalSyntheticOutline0.m(" translationX:", view.getTranslationX(), printWriter);
        }
        StatusIconContainer statusIconContainer = this.statusIcons;
        if (statusIconContainer != null) {
            printWriter.print("statusIcons visible:" + statusIconContainer.getVisibility() + " alpha:" + statusIconContainer.getAlpha());
            printWriter.println(" translateX:" + statusIconContainer.getTranslationX() + " width:" + statusIconContainer.getWidth());
            if (strArr != null) {
                for (int i2 = 0; i2 < statusIconContainer.getChildCount(); i2++) {
                    Dumpable childAt = statusIconContainer.getChildAt(i2);
                    if (childAt instanceof Dumpable) {
                        childAt.dump(printWriter, strArr);
                    }
                }
            }
        }
        View view2 = this.statusBarLeft;
        if (view2 != null) {
            printWriter.print("statusBarLeft visible:" + view2.getVisibility() + " alpha:" + view2.getAlpha());
            float translationX = view2.getTranslationX();
            StringBuilder sb2 = new StringBuilder(" translateX:");
            sb2.append(translationX);
            printWriter.print(sb2.toString());
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(view2.getPaddingRight(), view2.getPaddingBottom(), " ,", "]", FloatList$.ExternalSyntheticOutline0.m(" padding: [", view2.getPaddingLeft(), view2.getPaddingTop(), " ,", " ,")));
        }
        Clock clock = this.clock;
        if (clock != null) {
            int visibility2 = clock.getVisibility();
            float alpha2 = clock.getAlpha();
            int width = clock.getWidth();
            StringBuilder sbM2 = NormalizedTouchData$.ExternalSyntheticOutline0.m(alpha2, visibility2, "clock visible:", " alpha:", " width:");
            sbM2.append(width);
            printWriter.print(sbM2.toString());
            printWriter.print(" translateX:" + clock.getTranslationX());
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(clock.getPaddingRight(), clock.getPaddingBottom(), " ,", "]", FloatList$.ExternalSyntheticOutline0.m(" padding: [", clock.getPaddingLeft(), clock.getPaddingTop(), " ,", " ,")));
            clock.dump(printWriter, strArr);
        }
        StatBatteryMeterView statBatteryMeterView = this.batteryView;
        if (statBatteryMeterView != null) {
            statBatteryMeterView.dump(printWriter);
        }
        ViewGroup viewGroup = this.notificationIconArea;
        if (viewGroup != null) {
            int visibility3 = viewGroup.getVisibility();
            float alpha3 = viewGroup.getAlpha();
            int width2 = viewGroup.getWidth();
            StringBuilder sbM3 = NormalizedTouchData$.ExternalSyntheticOutline0.m(alpha3, visibility3, "notificationArea visible:", " alpha:", " width:");
            sbM3.append(width2);
            printWriter.print(sbM3.toString());
            printWriter.print(" translateX:" + viewGroup.getTranslationX());
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(viewGroup.getPaddingRight(), viewGroup.getPaddingBottom(), " ,", "]", FloatList$.ExternalSyntheticOutline0.m(" padding: [", viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), " ,", " ,")));
        }
        View view3 = this.statusBarContents;
        ViewGroup viewGroup2 = view3 instanceof ViewGroup ? (ViewGroup) view3 : null;
        if (viewGroup2 != null) {
            int childCount = viewGroup2.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = viewGroup2.getChildAt(i3);
                printWriter.print(childAt2 + "  alpha:" + childAt2.getAlpha() + " visibility:" + childAt2.getVisibility());
                printWriter.print(" width:" + childAt2.getWidth() + "  height:" + childAt2.getHeight());
                printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(childAt2.getPaddingRight(), childAt2.getPaddingBottom(), ", ", "]", FloatList$.ExternalSyntheticOutline0.m(" padding:[", childAt2.getPaddingLeft(), childAt2.getPaddingTop(), ", ", ", ")));
            }
        }
        LinearLayout linearLayout = this.dynamicIconGroup;
        if (linearLayout != null) {
            LinearLayout linearLayout2 = linearLayout == null ? null : linearLayout;
            printWriter.print("dynamicIconGroup visible:" + linearLayout2.getVisibility() + " alpha:" + linearLayout2.getAlpha());
            float translationX2 = linearLayout2.getTranslationX();
            StringBuilder sb3 = new StringBuilder("translateX:");
            sb3.append(translationX2);
            printWriter.print(sb3.toString());
            printWriter.println(TimerSpan$.ExternalSyntheticOutline0.m(linearLayout2.getPaddingRight(), linearLayout2.getPaddingBottom(), " ,", "]", FloatList$.ExternalSyntheticOutline0.m(" padding: [", linearLayout2.getPaddingLeft(), linearLayout2.getPaddingTop(), " ,", " ,")));
        }
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("ScreenBurnInProtector currentMode: ", ScreenBurnInProtector.Companion.getInstance().getCurrentMove(), printWriter);
        printWriter.println("privacyDotLayoutState: " + this._privacyDotLayoutStateFlow.getValue());
        printWriter.println();
        this.statusBarPaddingProvider.dump(printWriter);
        OplusStatusBarUtils.dump(printWriter);
        this.statusBarCoordAnimationController.dump(printWriter);
    }

    public final StateFlow getPrivacyDotLayoutStateFlow() {
        return this.privacyDotLayoutStateFlow;
    }

    public int getStatusBarPaddingTop(Integer num) {
        View view = this.statusBarContents;
        return view != null ? view.getPaddingTop() : super.getStatusBarPaddingTop(num);
    }

    public void handleClick(MotionEvent motionEvent) {
        super.handleClick(motionEvent);
        this.clickHelper.scrollToTop(motionEvent);
        ((ClockSecondsViewBinder) this.clockSecondsViewBinder.get()).onStatusBarTouch(motionEvent, this.clock);
    }

    public final void onAttachedToWindow() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->onAttachedToWindow:");
        }
        FixedStatusBarReceiver.Companion.getInstance().addListener(this);
        ScreenBurnInProtector.Companion.getInstance().addCallback(this);
        ConfigurationControllerImpl configurationControllerImpl = this.configurationController;
        PhoneStatusBarViewExImpl$configurationListener$1 phoneStatusBarViewExImpl$configurationListener$1 = this.configurationListener;
        ConfigurationControllerImpl configurationControllerImpl2 = configurationControllerImpl;
        configurationControllerImpl2.getClass();
        configurationControllerImpl2.addCallback(phoneStatusBarViewExImpl$configurationListener$1);
        this.updateMonitor.registerCallback(this.monitorCallback);
        this.screenLifecycle.addObserver(this.screenObserver);
        if (this.updateMonitor.mSystemReady) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->onAttachedToWindow onSystemReady");
            }
            this.isSystemReady = true;
            reShowStatusBarAfterSystemNotReady();
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            this.foldStateListener = new onAttachedToWindow.3(this);
            DeviceStateController.getInstance().addCallback(this.foldStateListener);
        }
        this.darkIconDispatcher.addDarkReceiver(this.batteryView);
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(getContext()).addCallback(this.typefaceCallback, new AnonymousClass4(1, companion.getInstance(getContext()), SystemTypefaceHelper.class, "mapStatusCommonTextFontWeight", "mapStatusCommonTextFontWeight(I)I", 0));
        this.clickHelper.checkClickParams(getContext());
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001e  */
    public final void onBurnInPreventTrigger(float f) {
        StatusIconContainer statusIconContainer;
        View view = this.statusBarContents;
        if (view != null) {
            view.setTranslationX(f);
        }
        if (!OplusStatusBarUtils.isAdaptiveStatusBar(getContext()) && (statusIconContainer = this.statusIcons) != null) {
            int i = (int) f;
            if (this.isLayoutRtl) {
                if (i <= 0) {
                    i = 0;
                }
            } else if (i < 0) {
                i = -i;
            } else {
                i = 0;
            }
            if (statusIconContainer.getPaddingStart() != i) {
                statusIconContainer.setPaddingRelative(i, statusIconContainer.getPaddingTop(), statusIconContainer.getPaddingEnd(), statusIconContainer.getPaddingBottom());
            }
        }
        updatePrivacyChipLayout();
        StateFlowImpl stateFlowImpl = this._privacyDotLayoutStateFlow;
        while (true) {
            Object value = stateFlowImpl.getValue();
            float f2 = f;
            if (stateFlowImpl.compareAndSet(value, PrivacyDotLayoutState.copy$default((PrivacyDotLayoutState) value, 0, 0, 0, 0, 0, 0, 0, 0, f2, 511))) {
                return;
            } else {
                f = f2;
            }
        }
    }

    public final void onDetachedFromWindow() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->onDetachedFromWindow:");
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            DeviceStateController.getInstance().removeCallback(this.foldStateListener);
        }
        FixedStatusBarReceiver.Companion.getInstance().removeListener(this);
        ScreenBurnInProtector.Companion.getInstance().removeCallback(this);
        ConfigurationControllerImpl configurationControllerImpl = this.configurationController;
        PhoneStatusBarViewExImpl$configurationListener$1 phoneStatusBarViewExImpl$configurationListener$1 = this.configurationListener;
        ConfigurationControllerImpl configurationControllerImpl2 = configurationControllerImpl;
        configurationControllerImpl2.getClass();
        configurationControllerImpl2.removeCallback(phoneStatusBarViewExImpl$configurationListener$1);
        this.updateMonitor.removeCallback(this.monitorCallback);
        this.screenLifecycle.removeObserver(this.screenObserver);
        this.darkIconDispatcher.removeDarkReceiver(this.batteryView);
        SystemTypefaceHelper.Companion.getInstance(getContext()).removeCallback(this.typefaceCallback);
    }

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public void onReceive(Context context, Intent intent) {
        if (FixedStatusBarReceiver.ACTION_FIXED_STATUS_BAR.equals(intent.getAction())) {
            boolean booleanExtra = intent.getBooleanExtra(FixedStatusBarReceiver.EXTRA_FIXED_STATUS_BAR, false);
            String stringExtra = intent.getStringExtra(FixedStatusBarReceiver.EXTRA_FIXED_STATUS_BAR_TIME);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "PhoneStatusBarViewEx-->".concat("onReceive(), fixedStatusBar = " + booleanExtra + ", fixedTime = " + stringExtra));
            }
            View view = this.statusBarContents;
            if (view != null) {
                view.setVisibility(booleanExtra ? 8 : 0);
            }
            FixedStatusBarControllerEx fixedStatusBarControllerEx = this.fixedStatusBarControllerExt;
            PhoneStatusBarView phoneStatusBarView = this.phoneStatusBarView;
            if (phoneStatusBarView == null) {
                phoneStatusBarView = null;
            }
            fixedStatusBarControllerEx.updateFixedStatusBarView(booleanExtra, phoneStatusBarView, stringExtra);
        }
    }

    public final void reShowStatusBarAfterSystemNotReady() {
        if (this.hideStatusBarWhenSystemNotReady) {
            Log.d("PhoneStatusBarViewEx", "re-show statusBar after system ready");
            this.hideStatusBarWhenSystemNotReady = false;
            View view = this.statusBarContents;
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    public final /* synthetic */ void removeCallback(Object obj) {
        if (obj == null) {
            removeCallback((PhoneStatusBarViewEx.StatusBarContentPaddingChangedListener) null);
        } else {
            Status$.ExternalSyntheticBUOutline0.m();
        }
    }

    public void setContents(View view) {
        this.statusBarContents = view;
        if (view != null) {
            view.setTranslationX(ScreenBurnInProtector.Companion.getInstance().getCurrentMove());
        }
        View view2 = this.statusBarContents;
        this.statusBarLeft = view2 != null ? view2.findViewById(2131365373) : null;
        View view3 = this.statusBarContents;
        this.clock = view3 != null ? (StatClock) view3.findViewById(2131362566) : null;
        boolean z = this.updateMonitor.mSystemReady;
        this.isSystemReady = z;
        if (z) {
            return;
        }
        Log.d("PhoneStatusBarViewEx", "hide statusBar when system not ready");
        this.hideStatusBarWhenSystemNotReady = true;
        View view4 = this.statusBarContents;
        if (view4 != null) {
            view4.setVisibility(4);
        }
    }

    public void setPhoneStatusBarView(PhoneStatusBarView phoneStatusBarView) {
        this.phoneStatusBarView = phoneStatusBarView;
        if (phoneStatusBarView == null) {
            phoneStatusBarView = null;
        }
        this.capsulePluginContainer = phoneStatusBarView.findViewById(2131365117);
        FrameLayout frameLayout = this.phoneStatusBarView;
        if (frameLayout == null) {
            frameLayout = null;
        }
        this.animationWindowView = (FrameLayout) frameLayout.requireViewById(2131364645);
        FrameLayout frameLayout2 = this.phoneStatusBarView;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        this.dynamicIconGroup = (LinearLayout) frameLayout2.requireViewById(2131362994);
        FrameLayout frameLayout3 = this.phoneStatusBarView;
        if (frameLayout3 == null) {
            frameLayout3 = null;
        }
        this.statusIcons = frameLayout3.findViewById(2131365358);
        FrameLayout frameLayout4 = this.phoneStatusBarView;
        if (frameLayout4 == null) {
            frameLayout4 = null;
        }
        this.notificationIconArea = (ViewGroup) frameLayout4.findViewById(2131364192);
        FrameLayout frameLayout5 = this.phoneStatusBarView;
        if (frameLayout5 == null) {
            frameLayout5 = null;
        }
        this.batteryView = (StatBatteryMeterView) frameLayout5.findViewById(2131362233);
        LinearLayout linearLayout = this.dynamicIconGroup;
        if (linearLayout == null) {
            linearLayout = null;
        }
        this.dynamicText = (TextView) linearLayout.findViewById(2131362995);
        FrameLayout frameLayout6 = this.phoneStatusBarView;
        if (frameLayout6 == null) {
            frameLayout6 = null;
        }
        this.chipTimeView = (TextView) frameLayout6.findViewById(2131364256);
        FrameLayout frameLayout7 = this.phoneStatusBarView;
        if (frameLayout7 == null) {
            frameLayout7 = null;
        }
        this.chipTextView = (TextView) frameLayout7.findViewById(2131364255);
        FrameLayout frameLayout8 = this.phoneStatusBarView;
        this.chipShortTimeView = (TextView) (frameLayout8 != null ? frameLayout8 : null).findViewById(2131364254);
    }

    public final boolean shouldRequestLayout() {
        return this.shouldRequestLayout;
    }

    public final void updateContentsPadding() {
        Object value;
        Rect rect;
        LinearLayout linearLayout;
        Trace.beginSection("updateContentsPadding");
        this.foldStateForPadding = DeviceStateController.getInstance().isOpenOrHalfOpen();
        View view = this.statusBarContents;
        if (view != null) {
            boolean z = getContext().getResources().getConfiguration().getLayoutDirection() == 1;
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131173051);
            View viewRequireViewById = view.requireViewById(2131365370);
            View viewRequireViewById2 = view.requireViewById(2131365365);
            this.homePaddingRect = this.statusBarPaddingProvider.getHomeStatusBarLeftRightPadding(view.toString(), new Pair<>(Integer.valueOf(this.statusBarMarginLeft), Integer.valueOf(this.statusBarMarginRight)), new Pair<>(Integer.valueOf(this.cutoutLeftPadding), Integer.valueOf(this.cutoutRightPadding)));
            if (!this.shouldShowForDisplay.isMainDisplay) {
                int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(2131172976);
                int dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(2131172974);
                Rect rect2 = this.homePaddingRect;
                this.homePaddingRect = new Rect(dimensionPixelSize2, rect2.top, dimensionPixelSize3, rect2.bottom);
            }
            Rect rect3 = this.homePaddingRect;
            view.setPadding(0, rect3.top, 0, rect3.bottom);
            FeatureOption.INSTANCE.getClass();
            int dimensionPixelSize4 = (!((FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet()) || this.holePosition == 2) ? 0 : getContext().getResources().getDimensionPixelSize(2131172972);
            Rect rect4 = this.homePaddingRect;
            int i = (z ? rect4.right : rect4.left) - dimensionPixelSize;
            viewRequireViewById.setPaddingRelative(i >= 0 ? i : 0, viewRequireViewById.getPaddingTop(), dimensionPixelSize4, viewRequireViewById.getPaddingBottom());
            int paddingStart = viewRequireViewById2.getPaddingStart();
            int paddingTop = viewRequireViewById2.getPaddingTop();
            Rect rect5 = this.homePaddingRect;
            viewRequireViewById2.setPaddingRelative(paddingStart, paddingTop, z ? rect5.left : rect5.right, viewRequireViewById2.getPaddingBottom());
            int statusBarHeight = (SystemBarUtils.getStatusBarHeight(getContext()) - view.getPaddingTop()) - view.getPaddingBottom();
            StateFlowImpl stateFlowImpl = this._privacyDotLayoutStateFlow;
            do {
                value = stateFlowImpl.getValue();
                rect = this.homePaddingRect;
            } while (!stateFlowImpl.compareAndSet(value, PrivacyDotLayoutState.copy$default((PrivacyDotLayoutState) value, 0, 0, 0, rect.left, rect.right, rect.top, rect.bottom, statusBarHeight, 0.0f, 527)));
            this.hiddenIconDispatcher.setStatusBarPaddingRect(StatusBarLocation.HOME, this.homePaddingRect);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(statusBarHeight, " statusBarContentsHeight:", "PhoneStatusBarViewEx-->", "SystemUi--Statusbar");
            }
            updatePrivacyChipLayout();
            View view2 = this.statusBarContents;
            if (view2 != null && (linearLayout = this.dynamicIconGroup) != null) {
                linearLayout.setPadding(linearLayout.getPaddingLeft(), view2.getPaddingTop(), linearLayout.getPaddingRight(), view2.getPaddingBottom());
            }
        }
        Trace.endSection();
    }

    public final boolean updateDisplayParameters$1() {
        boolean z;
        int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(getContext()).getStableDisplayWidth();
        if (stableDisplayWidth != this.lastStableDisplayWidth) {
            this.lastStableDisplayWidth = stableDisplayWidth;
            z = true;
        } else {
            z = false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            int i = this.cutoutLeftPadding;
            int i2 = this.cutoutRightPadding;
            int i3 = getContext().getResources().getConfiguration().orientation;
            boolean z2 = this.foldState;
            boolean z3 = this.foldStateForPadding;
            int i4 = this.holePosition;
            boolean z4 = this.systemFoldingModeChanged;
            int i5 = this.lastStableDisplayWidth;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateDisplayParameters(), cutoutLeftPadding = ", i, i2, ", cutoutRightPadding = ", ", orientation = ");
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i3, ", foldState:", " foldStateForPadding:", sbM, z2);
            PromptIconState$.ExternalSyntheticOutline0.m(i4, ", holePosition:", ", systemFoldingModeChanged:", sbM, z3);
            PromptIconState$.ExternalSyntheticOutline0.m(stableDisplayWidth, ", stableDisplayWidth:", " lastStableDisplayWidth:", sbM, z4);
            sbM.append(i5);
            sbM.append(", stableDisplayChange:");
            sbM.append(z);
            Log.d("SystemUi--Statusbar", "lipss-->".concat(sbM.toString()));
        }
        return this.systemFoldingModeChanged || z;
    }

    public final void updatePrivacyChipLayout() {
        int i;
        View view = this.statusBarContents;
        if (view != null) {
            boolean z = getContext().getResources().getConfiguration().getLayoutDirection() == 1;
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131171476);
            int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(2131171477);
            int translationX = (int) view.getTranslationX();
            Rect rect = this.homePaddingRect;
            if (z) {
                int i2 = rect.left;
                i = i2 > 0 ? ((i2 - dimensionPixelSize) - dimensionPixelSize2) + translationX : dimensionPixelSize + translationX;
            } else {
                int i3 = rect.right;
                i = i3 > 0 ? ((i3 - dimensionPixelSize) - dimensionPixelSize2) - translationX : dimensionPixelSize;
            }
            FrameLayout frameLayout = this.animationWindowView;
            if (frameLayout != null) {
                if (frameLayout == null) {
                    frameLayout = null;
                }
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
                layoutParams.setMarginStart(dimensionPixelSize);
                frameLayout.setLayoutParams(layoutParams);
            }
            LinearLayout linearLayout = this.dynamicIconGroup;
            if (linearLayout != null) {
                LinearLayout linearLayout2 = linearLayout != null ? linearLayout : null;
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) linearLayout2.getLayoutParams();
                layoutParams2.setMarginEnd(i);
                linearLayout2.setLayoutParams(layoutParams2);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                int i4 = this.statusBarMarginRight;
                Rect rect2 = this.homePaddingRect;
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("dotMargin: ", i, i4, " statusBarMarginRight:", " homePadding: ");
                sbM.append(rect2);
                sbM.append(" privacyDotMargin:");
                sbM.append(dimensionPixelSize);
                sbM.append(" privacyDotSize:");
                Log.d("SystemUi--Statusbar", NotificationPanelViewController$.ExternalSyntheticOutline0.m(dimensionPixelSize2, translationX, "  transitionX:", "PhoneStatusBarViewEx-->", sbM));
            }
        }
    }

    public final boolean updateRTL() {
        if (this.phoneStatusBarView == null) {
            return true;
        }
        boolean z = this.isLayoutRtl;
        this.isLayoutRtl = getContext().getResources().getConfiguration().getLayoutDirection() == 1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" updateRTL: old=", " new=", "PhoneStatusBarViewEx-->", z, this.isLayoutRtl));
        }
        return z != this.isLayoutRtl;
    }

    public void updateSafeInsets(int i, int i2, Rect rect) {
        int i3;
        String str;
        Trace.beginSection("updateSafeInsets");
        boolean z = false;
        this.shouldRequestLayout = false;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            OplusStatusBarUtils.updateHolePosition(rect);
            int iHolePosition = OplusStatusBarUtils.holePosition();
            if (iHolePosition != this.holePosition) {
                this.holePosition = iHolePosition;
                z = true;
            }
        }
        int i4 = getContext().getResources().getDisplayMetrics().widthPixels;
        int i5 = getContext().getResources().getConfiguration().orientation;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            int i6 = this.cutoutLeftPadding;
            int i7 = this.cutoutRightPadding;
            int i8 = this.statusBarMarginLeft;
            int i9 = this.statusBarMarginRight;
            int i10 = getContext().getResources().getConfiguration().orientation;
            boolean z2 = this.foldState;
            boolean z3 = this.foldStateForPadding;
            int i11 = this.holePosition;
            int i12 = this.screenResolution;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateSafeInsets(), orientation = ", i5, i, ", cutoutLeftPadding = ", "  this:");
            RectList$.ExternalSyntheticOutline0.m(i6, i2, ", cutoutRightPadding = ", " this:", sbM);
            RectList$.ExternalSyntheticOutline0.m(i7, i8, ", statusBarMarginLeft = ", "; statusBarMarginRight:", sbM);
            RectList$.ExternalSyntheticOutline0.m(i9, i10, ", orientation = ", ", foldState:", sbM);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" foldStateForPadding:", ", holePosition:", sbM, z2, z3);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i11, " holePositionChanged:", ", screenResolution:", sbM, z);
            i3 = i4;
            str = "SystemUi--Statusbar";
            Log.d(str, NotificationPanelViewController$.ExternalSyntheticOutline0.m(i3, i12, ", this.screenResolution:", "PhoneStatusBarViewEx-->", sbM));
        } else {
            i3 = i4;
            str = "SystemUi--Statusbar";
        }
        if (i5 != 0 || i3 != this.screenResolution || z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r9)) {
                Log.d(str, "PhoneStatusBarViewEx-->padding change,  update contents padding");
            }
            this.cutoutLeftPadding = i;
            this.cutoutRightPadding = i2;
            this.screenResolution = i3;
            updateContentsPadding();
            this.shouldRequestLayout = true;
        } else if (this.foldState != this.foldStateForPadding) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d(str, "PhoneStatusBarViewEx-->fold state change, force update contents padding");
            }
            updateContentsPadding();
            this.shouldRequestLayout = true;
        }
        Trace.endSection();
    }

    public final void updateStableDisplaySize() {
        ScreenResolutionHelper.Companion.getInstance(getContext()).updateDisplaySize();
    }

    public final void updateStatusBarMargin(int i, int i2, int i3) {
        if (i == this.statusBarMarginLeft && i3 == this.statusBarMarginRight && i2 == this.statusBarMarginTop && this.statusBarMarginBottom == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m(" updateStatusBarMargin: left:", i, i3, " right:", "PhoneStatusBarViewEx-->"));
        }
        this.statusBarMarginLeft = i;
        this.statusBarMarginRight = i3;
        this.statusBarMarginTop = i2;
        this.statusBarMarginBottom = 0;
        updatePrivacyChipLayout();
        StateFlowImpl stateFlowImpl = this._privacyDotLayoutStateFlow;
        while (true) {
            Object value = stateFlowImpl.getValue();
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            if (stateFlowImpl.compareAndSet(value, PrivacyDotLayoutState.copy$default((PrivacyDotLayoutState) value, i4, i5, i6, 0, 0, 0, 0, 0, 0.0f, 1008))) {
                return;
            }
            i = i4;
            i2 = i5;
            i3 = i6;
        }
    }

    public void addCallback(PhoneStatusBarViewEx.StatusBarContentPaddingChangedListener statusBarContentPaddingChangedListener) {
        this.listeners.add(statusBarContentPaddingChangedListener);
    }

    public void removeCallback(PhoneStatusBarViewEx.StatusBarContentPaddingChangedListener statusBarContentPaddingChangedListener) {
        this.listeners.remove(statusBarContentPaddingChangedListener);
    }
}
