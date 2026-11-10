package com.oplus.systemui.shade;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Region;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.keyguard.IKeyguardTipsViewController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.panelanimation.qs.QSPanelOverScrollAnimationEx;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.qs.ISysUiCustom;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.NotificationsQuickSettingsContainer;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorKt;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.PulseExpansionHandlerExt;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.keyguard.KeyguardVFXController;
import com.oplus.keyguard.OplusLockIconViewControllerExImpl;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.keyguard.OplusKgdImmersiveThemeController;
import com.oplus.systemui.keyguard.anim.OplusKgdAnimParams;
import com.oplus.systemui.keyguard.anim.OplusWallpaperAnimControllerImpl;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleClockControllerImpl;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleClockStateController;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleWidgetUIControllerImpl;
import com.oplus.systemui.keyguard.oneshot.OneShotAnimController;
import com.oplus.systemui.keyguard.ui.view.OplusKeyguardBottomAreaView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout;
import com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.clearall.ClearAllController;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.OplusQSBottomImpl;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.OplusQSPanelContainer;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.mydevice.MyDevicePanel;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.utils.PhysicalAnimationUtil;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.separate.ColumnWidthUtil;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil$$ExternalSyntheticLambda4;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil$$ExternalSyntheticLambda5;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.systemui.util.OplusKgdSwipeUpTouchProgressTracker;
import com.oplus.systemui.volume.utils.ScreenUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.receiver.ThemeReceiver;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotificationShadeRegionHelper;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.function.BiConsumer;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp extends NotificationPanelViewControllerEx implements OplusNotificationEx, NotificationShadeRegionHelper.SetTouchExclusionRegionCallBack, ConfigurationController.ConfigurationListener {
    private static final String FRONT_APP_PACKAGE = "com.oplus.camera";
    private static final String REASON_TOUCH_AND_ANIMATION_DISABLED = "setTouchAndAnimationDisabled";
    private static final String TAG = "NotificationPanelViewControllerExImp";
    private final ActiveNotificationsInteractor activeNotificationsInteractor;
    private final AmbientState ambientState;
    private final IObserverListener childModeListener;
    private final ClearAllController clearAllController;
    private final OplusCloseAllController closeController;
    private final ConfigurationController configurationController;
    private final Context context;
    public int currentBarState;
    private ExpandableNotificationRow dragExpandableNotificationRow;
    private final NotificationPanelViewControllerExImp$elementShowStateChangeListener$1 elementShowStateChangeListener;
    private final NotificationPanelViewControllerExImp$fpVisibilityListener$1 fpVisibilityListener;
    private final Runnable fpVisibilityRefreshRunnable;
    private final FullScreenBannerHelper fullScreenBannerHelper;
    private ValueAnimator headUpSpringBackAnimator;
    private final HeadsUpManager headsUpManager;
    public int horizontalMarginStackLayout;
    public boolean isNeedInterceptZoom;
    public boolean isOneshotHandleTracking;
    public boolean isScaleFadePanelAllowed;
    public boolean isStartedZoomAnimation;
    public boolean isTrackingStartedZoomAnimation;
    private final Lazy keyguardBottomAreaControllerEx;
    private final NotificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1 keyguardBottomAreaHeightListener;
    private final OplusKeyguardBottomAreaView keyguardBottomAreaView;
    private final Lazy keyguardMediaController;
    public int keyguardNotificationBottomPadding;
    public float keyguardNotificationMinBottomPadding;
    private final KeyguardSliderLayout keyguardSliderLayout;
    private final NotificationPanelViewControllerExImp$keyguardStateCallback$1 keyguardStateCallback;
    private final Lazy keyguardStateController;
    private final Lazy keyguardStyleClockController;
    private final Lazy keyguardStyleWidgetUIController;
    private final Lazy keyguardUpdateMonitor;
    private final Lazy keyguardViewMediator;
    public float lastY;
    public boolean mHeadsUpSwipeUp;
    public final Uri mIsInGameModeUri;
    private String mLastPkg;
    public boolean mLowFrameRate;
    private String mLowFrameRatePkg;
    public float mPreY;
    public IBinder mSurfaceFlinger;
    private Runnable mTryToFastUnlockForSlideUpRunnable;
    private final Handler mainHandler;
    public int marginBottom;
    public final int minScaleHeight;
    public final int minZoomHeight;
    public float multiplePointerInitialY;
    private final Lazy notificationPopupMenuController;
    public int notificationScrimMarginEndWithQuickSettings;
    private final Lazy notificationShadeWindowController;
    private final NotificationStackScrollLayoutController notificationStackScrollLayoutController;
    private final Lazy notificationStackScrollLayoutControllerExtImpl;
    private final Lazy notificationsQSContainerController;
    private final Lazy oneShotAnimController;
    public final int oneshotHandleScaleRatio;
    private final Lazy oplusBiometricAuthControllerLazy;
    private final Lazy oplusKeyguardTipsViewController;
    private final Lazy oplusKgdImmersiveController;
    private final Lazy oplusKgdImmersiveThemeController;
    private final OplusKgdSwipeUpTouchProgressTracker oplusKgdSwipeUpTouchProcessTracker;
    private final Lazy oplusLockIconViewControllerExImpl;
    private final Lazy oplusPanelKeyguardAnimControllerImpl;
    private final Lazy oplusPanoramicAodRepository;
    private final Lazy oplusWallpaperAnimControllerImpl;
    private final QSPanelOverScrollAnimationEx overScrollAnimationEx;
    public int paddingBetweenNotifAndScrim;
    private final PanelAnimationEx panelAnimationEx;
    public long panelStartExpandTime;
    private NotificationPanelViewController panelViewController;
    public int panelWidth;
    private final PictorialMediator pictorialMediator;
    private final Lazy platformBlurManager;
    private final Lazy primaryBouncerInteractor;
    private final PulseExpansionHandlerExt pulseExpansionHandlerExt;
    public int quickSettingsFrameMarginStart;
    private final Runnable requestLayoutRunnable;
    private Runnable requestPanelHeightUpdateAction;
    private final CoroutineScope scope;
    private Lazy seedlingPluginManager;
    private final Lazy separateNotificationAndQSEx;
    private final ShadeInteractor shadeInteractor;
    private final Lazy sharedNotificationContainer;
    private final SharedNotificationContainerViewModel sharedNotificationContainerViewModel;
    private Long statusBarEventDownTime;
    private final SysuiStatusBarStateController statusBarStateController;
    private final NotificationPanelViewControllerExImp$statusBarStateListener$1 statusBarStateListener;
    private final SysUiStateControllerImpl sysUiStateControllerImpl;
    private final NotificationPanelViewControllerExImp$themeReceiverListener$1 themeReceiverListener;
    public float thresholdForElementAppearance;
    public static final Companion Companion = new Companion();
    private static final Interpolator SPRING_BACK_PATH_INTERPOLATOR = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    private static final Interpolator EASE_OUT_SINE = new PathInterpolator(0.66f, 1.33f, 0.72f, 1.06f);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelViewControllerExImp$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationPanelView $view;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelViewControllerExImp$init$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass2 implements FlowCollector {
            public /* synthetic */ NotificationPanelViewControllerExImp this$0;

            public final Object emit(Object obj, Continuation continuation) {
                NotificationPanelViewControllerExImp.access$shouldReSetFrameRateWhenPanelExpanded(this.this$0, ((Boolean) obj).booleanValue());
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationPanelView notificationPanelView, Continuation continuation) {
            super(2, continuation);
            this.$view = notificationPanelView;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationPanelViewControllerExImp.this.new AnonymousClass1(this.$view, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            ComponentName componentName;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = NotificationPanelViewControllerExImp.this;
                NotificationPanelView notificationPanelView = this.$view;
                Companion companion = NotificationPanelViewControllerExImp.Companion;
                notificationPanelViewControllerExImp.getClass();
                ActivityManager.RunningTaskInfo topPackageTaskInfo = KeyguardUtils.Companion.getTopPackageTaskInfo(notificationPanelView.getContext());
                String packageName = (topPackageTaskInfo == null || (componentName = topPackageTaskInfo.topActivity) == null) ? null : componentName.getPackageName();
                if (packageName != null) {
                    notificationPanelViewControllerExImp.setFps(0, Util.getPid(notificationPanelView.getContext(), packageName), packageName);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("init restore framerate . restore package:", packageName, "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
                    }
                }
                StateFlow stateFlowCreateAnyExpansionFlow = ShadeInteractorKt.createAnyExpansionFlow(NotificationPanelViewControllerExImp.this.scope, NotificationPanelViewControllerExImp.this.shadeInteractor.getShadeExpansion(), NotificationPanelViewControllerExImp.this.shadeInteractor.getSeparateQsExpansion());
                NotificationPanelViewControllerExImp$init$1$invokeSuspend$$inlined$map$1 notificationPanelViewControllerExImp$init$1$invokeSuspend$$inlined$map$1 = new NotificationPanelViewControllerExImp$init$1$invokeSuspend$$inlined$map$1();
                notificationPanelViewControllerExImp$init$1$invokeSuspend$$inlined$map$1.$this_unsafeTransform$inlined = stateFlowCreateAnyExpansionFlow;
                VarHandle.storeStoreFence();
                StateFlow stateFlowStateIn = FlowKt.stateIn(notificationPanelViewControllerExImp$init$1$invokeSuspend$$inlined$map$1, NotificationPanelViewControllerExImp.this.scope, SharingStarted.Companion.getEagerly(), Boxing.boxBoolean(false));
                NotificationPanelViewControllerExImp notificationPanelViewControllerExImp2 = NotificationPanelViewControllerExImp.this;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                anonymousClass2.this$0 = notificationPanelViewControllerExImp2;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowStateIn.collect(anonymousClass2, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelViewControllerExImp$onExpandingFinished$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ QuickSettingsController $qs;
        public /* synthetic */ NotificationPanelViewControllerExImp this$0;

        @Override // java.lang.Runnable
        public final void run() {
            NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
            QuickSettingsController quickSettingsController = this.$qs;
            Companion companion = NotificationPanelViewControllerExImp.Companion;
            NotificationPanelViewController base = notificationPanelViewControllerExImp.getBase();
            if (base != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(",PANEL_EXPANDED=", "NotificationPanelViewControllerExImp-->", KeyguardLogger$.ExternalSyntheticOutline0.m("sendPullMessageToOthers::statusBarState=", base.getBarState(), ",QS_EXPANDED=", ",QS_FULLY_EXPANDED=", quickSettingsController.getExpanded()), quickSettingsController.getFullyExpanded(), base.isPanelExpanded()));
                }
                Trace.traceBegin(8L, "sendPullMessageToOthers");
                Intent intent = new Intent();
                intent.setAction(OplusNotificationCommercializeControl.PANEL_STATE_ACTION);
                intent.putExtra(OplusNotificationCommercializeControl.KEY_KEYGUARD_SHOWING, base.getBarState() == 1);
                intent.putExtra(OplusNotificationCommercializeControl.KEY_QS_EXPANDED, quickSettingsController.getExpanded());
                intent.putExtra(OplusNotificationCommercializeControl.KEY_QS_FULLY_EXPANDED, quickSettingsController.getFullyExpanded());
                intent.putExtra(OplusNotificationCommercializeControl.KEY_PANEL_EXPANDED, base.isPanelExpanded());
                intent.putExtra(OplusNotificationCommercializeControl.KEY_STATUS_BAR_STATE, base.getBarState());
                base.getView().getContext().sendBroadcastAsUser(intent, UserHandle.CURRENT, OplusNotificationCommercializeControl.PANEL_STATE_PERMISSION);
                Trace.traceEnd(8L);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelViewControllerExImp$onSetTouchExclusionRegionTrue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02841 extends SuspendLambda implements Function2 {
        int label;

        public C02841(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationPanelViewControllerExImp.this.new C02841(continuation);
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
            NotificationPanelViewControllerExImp.access$shouldReSetFrameRateWhenPanelExpanded(NotificationPanelViewControllerExImp.this, false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Hg993BJJjehTuCMyZ__EBC-jOR8, reason: not valid java name */
    public static Unit m365$r8$lambda$Hg993BJJjehTuCMyZ__EBCjOR8(NotificationPanelViewControllerExImp notificationPanelViewControllerExImp, float f) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) notificationPanelViewControllerExImp.keyguardStateController.get();
        KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl = (KeyguardStyleClockControllerImpl) notificationPanelViewControllerExImp.keyguardStyleClockController.get();
        OneShotAnimController oneShotAnimController = (OneShotAnimController) notificationPanelViewControllerExImp.oneShotAnimController.get();
        OplusWallpaperAnimControllerImpl oplusWallpaperAnimControllerImpl = (OplusWallpaperAnimControllerImpl) notificationPanelViewControllerExImp.oplusWallpaperAnimControllerImpl.get();
        if (!keyguardStateControllerImpl.mShowing || !keyguardStateControllerImpl.mCanDismissLockScreen) {
            return Unit.INSTANCE;
        }
        if (oneShotAnimController.willSupportOneShotForUnlock()) {
            keyguardStyleClockControllerImpl.setPanelExpandFraction(f);
            oplusWallpaperAnimControllerImpl.onSwipeUpUnlockProgressChanged(f);
        }
        keyguardStyleClockControllerImpl.getClass();
        if (KeyguardStyleClockControllerImpl.isOrbuculumClockEnabled()) {
            KeyguardStyleClockStateController.Companion.getInstance().notifyLiveClockUnlockProgress(f);
        }
        return Unit.INSTANCE;
    }

    public NotificationPanelViewControllerExImp(Context context, ClearAllController clearAllController, OplusCloseAllController oplusCloseAllController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Lazy lazy, PulseExpansionHandlerExt pulseExpansionHandlerExt, AmbientState ambientState, HeadsUpManager headsUpManager, KeyguardSliderLayout keyguardSliderLayout, Handler handler, PictorialMediator pictorialMediator, PanelAnimationEx panelAnimationEx, QSPanelOverScrollAnimationEx qSPanelOverScrollAnimationEx, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, Lazy lazy13, Lazy lazy14, FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy15, Lazy lazy16, OplusKeyguardBottomAreaView oplusKeyguardBottomAreaView, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, CoroutineScope coroutineScope, ShadeInteractor shadeInteractor, SysUiStateControllerImpl sysUiStateControllerImpl, ConfigurationController configurationController, ActiveNotificationsInteractor activeNotificationsInteractor, Lazy lazy17, Lazy lazy18, Lazy lazy19, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy20, Lazy lazy21, Lazy lazy22, Lazy lazy23, Lazy lazy24) {
        super(context);
        this.context = context;
        this.clearAllController = clearAllController;
        this.closeController = oplusCloseAllController;
        this.notificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.oplusBiometricAuthControllerLazy = lazy;
        this.pulseExpansionHandlerExt = pulseExpansionHandlerExt;
        this.ambientState = ambientState;
        this.headsUpManager = headsUpManager;
        this.keyguardSliderLayout = keyguardSliderLayout;
        this.mainHandler = handler;
        this.pictorialMediator = pictorialMediator;
        this.panelAnimationEx = panelAnimationEx;
        this.overScrollAnimationEx = qSPanelOverScrollAnimationEx;
        this.separateNotificationAndQSEx = lazy2;
        this.keyguardStyleClockController = lazy3;
        this.keyguardStyleWidgetUIController = lazy4;
        this.notificationShadeWindowController = lazy5;
        this.keyguardViewMediator = lazy6;
        this.oplusPanelKeyguardAnimControllerImpl = lazy7;
        this.oplusWallpaperAnimControllerImpl = lazy8;
        this.primaryBouncerInteractor = lazy9;
        this.keyguardStateController = lazy10;
        this.oplusLockIconViewControllerExImpl = lazy11;
        this.keyguardBottomAreaControllerEx = lazy12;
        this.keyguardUpdateMonitor = lazy13;
        this.platformBlurManager = lazy14;
        this.fullScreenBannerHelper = fullScreenBannerHelper;
        this.sharedNotificationContainer = lazy15;
        this.notificationStackScrollLayoutControllerExtImpl = lazy16;
        this.keyguardBottomAreaView = oplusKeyguardBottomAreaView;
        this.sharedNotificationContainerViewModel = sharedNotificationContainerViewModel;
        this.scope = coroutineScope;
        this.shadeInteractor = shadeInteractor;
        this.sysUiStateControllerImpl = sysUiStateControllerImpl;
        this.configurationController = configurationController;
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        this.oplusKeyguardTipsViewController = lazy17;
        this.oplusKgdImmersiveController = lazy18;
        this.oplusKgdImmersiveThemeController = lazy19;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.notificationsQSContainerController = lazy20;
        this.notificationPopupMenuController = lazy21;
        this.oneShotAnimController = lazy22;
        this.oplusPanoramicAodRepository = lazy23;
        this.keyguardMediaController = lazy24;
        this.minScaleHeight = context.getResources().getDimensionPixelSize(2131170810);
        this.minZoomHeight = context.getResources().getDimensionPixelSize(2131170674);
        this.thresholdForElementAppearance = context.getResources().getDimension(2131173668);
        this.mIsInGameModeUri = Settings.Global.getUriFor(NavBarSettingsValueProxy.DEBUG_GAME_MODE_VALUE);
        this.oneshotHandleScaleRatio = SystemProperties.getInt("debug.oneshot.handle.scale.ratio", 300);
        NotificationPanelViewControllerExImp$onTrackingStarted$2$1 notificationPanelViewControllerExImp$onTrackingStarted$2$1 = new NotificationPanelViewControllerExImp$onTrackingStarted$2$1(4);
        notificationPanelViewControllerExImp$onTrackingStarted$2$1.$this_run = this;
        VarHandle.storeStoreFence();
        this.requestLayoutRunnable = notificationPanelViewControllerExImp$onTrackingStarted$2$1;
        NotificationPanelViewControllerExImp$$ExternalSyntheticLambda0 notificationPanelViewControllerExImp$$ExternalSyntheticLambda0 = new NotificationPanelViewControllerExImp$$ExternalSyntheticLambda0();
        notificationPanelViewControllerExImp$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusKgdSwipeUpTouchProcessTracker = new OplusKgdSwipeUpTouchProgressTracker(context, notificationPanelViewControllerExImp$$ExternalSyntheticLambda0);
        NotificationPanelViewControllerExImp$themeReceiverListener$1 notificationPanelViewControllerExImp$themeReceiverListener$1 = new NotificationPanelViewControllerExImp$themeReceiverListener$1();
        notificationPanelViewControllerExImp$themeReceiverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.themeReceiverListener = notificationPanelViewControllerExImp$themeReceiverListener$1;
        NotificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1 notificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1 = new NotificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1();
        notificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardBottomAreaHeightListener = notificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1;
        NotificationPanelViewControllerExImp$onTrackingStarted$2$1 notificationPanelViewControllerExImp$onTrackingStarted$2$2 = new NotificationPanelViewControllerExImp$onTrackingStarted$2$1(1);
        notificationPanelViewControllerExImp$onTrackingStarted$2$2.$this_run = this;
        VarHandle.storeStoreFence();
        this.fpVisibilityRefreshRunnable = notificationPanelViewControllerExImp$onTrackingStarted$2$2;
        NotificationPanelViewControllerExImp$fpVisibilityListener$1 notificationPanelViewControllerExImp$fpVisibilityListener$1 = new NotificationPanelViewControllerExImp$fpVisibilityListener$1();
        notificationPanelViewControllerExImp$fpVisibilityListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fpVisibilityListener = notificationPanelViewControllerExImp$fpVisibilityListener$1;
        NotificationPanelViewControllerExImp$childModeListener$1 notificationPanelViewControllerExImp$childModeListener$1 = new NotificationPanelViewControllerExImp$childModeListener$1();
        notificationPanelViewControllerExImp$childModeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childModeListener = notificationPanelViewControllerExImp$childModeListener$1;
        NotificationPanelViewControllerExImp$elementShowStateChangeListener$1 notificationPanelViewControllerExImp$elementShowStateChangeListener$1 = new NotificationPanelViewControllerExImp$elementShowStateChangeListener$1();
        notificationPanelViewControllerExImp$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = notificationPanelViewControllerExImp$elementShowStateChangeListener$1;
        NotificationPanelViewControllerExImp$statusBarStateListener$1 notificationPanelViewControllerExImp$statusBarStateListener$1 = new NotificationPanelViewControllerExImp$statusBarStateListener$1();
        notificationPanelViewControllerExImp$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = notificationPanelViewControllerExImp$statusBarStateListener$1;
        NotificationPanelViewControllerExImp$keyguardStateCallback$1 notificationPanelViewControllerExImp$keyguardStateCallback$1 = new NotificationPanelViewControllerExImp$keyguardStateCallback$1();
        notificationPanelViewControllerExImp$keyguardStateCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStateCallback = notificationPanelViewControllerExImp$keyguardStateCallback$1;
    }

    public static final void access$removeFingerprintVisibilityListener(NotificationPanelViewControllerExImp notificationPanelViewControllerExImp) {
        notificationPanelViewControllerExImp.mainHandler.removeCallbacks(notificationPanelViewControllerExImp.fpVisibilityRefreshRunnable);
        ((OplusBiometricAuthController) notificationPanelViewControllerExImp.oplusBiometricAuthControllerLazy.get()).removeVisibilityListener(notificationPanelViewControllerExImp.fpVisibilityListener);
    }

    public static final void access$shouldReSetFrameRateWhenPanelExpanded(NotificationPanelViewControllerExImp notificationPanelViewControllerExImp, boolean z) {
        NotificationPanelViewController notificationPanelViewController;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        Object next;
        ComponentName componentName;
        ViewGroup windowRootView = ((NotificationShadeWindowController) notificationPanelViewControllerExImp.notificationShadeWindowController.get()).getWindowRootView();
        if (windowRootView == null || (notificationPanelViewController = notificationPanelViewControllerExImp.panelViewController) == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("shouldReSetFrameRateWhenPanelExpanded isExpanded: ", " isKeyguardShowing:", "NotificationPanelViewControllerExImp-->", z, notificationPanelViewController.isKeyguardShowing()));
        }
        if (!FlavorOneFeatureOption.INSTANCE.isSupportReSetFrameRate()) {
            NotificationFeatureOption.INSTANCE.getClass();
            if (!NotificationFeatureOption.isNotificationLightOs) {
                return;
            }
        }
        if (!z || notificationPanelViewControllerExImp.mLowFrameRate || notificationPanelViewController.isKeyguardShowing()) {
            if (!notificationPanelViewControllerExImp.mLowFrameRate || z) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("restore framerate when panel is not fully expanded. restore package:", notificationPanelViewControllerExImp.mLowFrameRatePkg, "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
            }
            notificationPanelViewControllerExImp.setFps(0, Util.getPid(windowRootView.getContext(), notificationPanelViewControllerExImp.mLowFrameRatePkg), notificationPanelViewControllerExImp.mLowFrameRatePkg);
            notificationPanelViewControllerExImp.mLowFrameRate = false;
            notificationPanelViewControllerExImp.mLastPkg = null;
            notificationPanelViewControllerExImp.mLowFrameRatePkg = null;
            return;
        }
        ActivityManager.RunningTaskInfo topPackageTaskInfo = KeyguardUtils.Companion.getTopPackageTaskInfo(windowRootView.getContext());
        String packageName = (topPackageTaskInfo == null || (componentName = topPackageTaskInfo.topActivity) == null) ? null : componentName.getPackageName();
        notificationPanelViewControllerExImp.mLowFrameRatePkg = packageName;
        if (packageName == null || packageName.equals(notificationPanelViewControllerExImp.mLastPkg) || windowRootView.getVisibility() != 0 || StringsKt__StringsJVMKt.equals(notificationPanelViewControllerExImp.mLowFrameRatePkg, "com.android.systemui", false)) {
            return;
        }
        String str = notificationPanelViewControllerExImp.mLowFrameRatePkg;
        ActivityManager activityManager = (ActivityManager) notificationPanelViewControllerExImp.context.getSystemService(ActivityManager.class);
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            Iterator<T> it = runningAppProcesses.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((ActivityManager.RunningAppProcessInfo) next).processName, str));
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) next;
            if (runningAppProcessInfo != null) {
                int i = runningAppProcessInfo.pid;
                AudioManager audioManager = (AudioManager) notificationPanelViewControllerExImp.context.getSystemService(AudioManager.class);
                List<AudioPlaybackConfiguration> activePlaybackConfigurations = audioManager != null ? audioManager.getActivePlaybackConfigurations() : null;
                if (activePlaybackConfigurations != null) {
                    for (AudioPlaybackConfiguration audioPlaybackConfiguration : activePlaybackConfigurations) {
                        if (audioPlaybackConfiguration != null && audioPlaybackConfiguration.getClientPid() == i && audioPlaybackConfiguration.getPlayerState() == 2) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp-->".concat(ConstraintSet$.ExternalSyntheticOutline0.m(i, "checkIsActivePlayback pkg=", str, " pid=", " is STARTED")));
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }
        notificationPanelViewControllerExImp.mLastPkg = notificationPanelViewControllerExImp.mLowFrameRatePkg;
        int i2 = notificationPanelViewControllerExImp.mIsInGameModeUri != null ? Settings.Global.getInt(windowRootView.getContext().getContentResolver(), NavBarSettingsValueProxy.DEBUG_GAME_MODE_VALUE, -1) : -1;
        if (i2 == -1 || i2 == 1) {
            return;
        }
        if (StringsKt__StringsJVMKt.equals(notificationPanelViewControllerExImp.mLowFrameRatePkg, FRONT_APP_PACKAGE, false)) {
            BrandUtils.INSTANCE.getClass();
            if (BrandUtils.isRealme) {
                notificationPanelViewControllerExImp.mLowFrameRate = notificationPanelViewControllerExImp.setFps(10, Util.getPid(windowRootView.getContext(), notificationPanelViewControllerExImp.mLowFrameRatePkg), notificationPanelViewControllerExImp.mLowFrameRatePkg);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("set 10 fps when panel is fully expanded. low frame rate package:", notificationPanelViewControllerExImp.mLowFrameRatePkg, "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
        }
        notificationPanelViewControllerExImp.mLowFrameRate = notificationPanelViewControllerExImp.setFps(30, Util.getPid(windowRootView.getContext(), notificationPanelViewControllerExImp.mLowFrameRatePkg), notificationPanelViewControllerExImp.mLowFrameRatePkg);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("set 30 fps when panel is fully expanded. low frame rate package:", notificationPanelViewControllerExImp.mLowFrameRatePkg, "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
        }
    }

    private final int getKeyguardScreenHeight() {
        ViewGroup windowRootView = ((NotificationShadeWindowController) this.notificationShadeWindowController.get()).getWindowRootView();
        if (windowRootView != null) {
            return windowRootView.getHeight();
        }
        OplusKeyguardBottomAreaView oplusKeyguardBottomAreaView = this.keyguardBottomAreaView;
        return oplusKeyguardBottomAreaView != null ? oplusKeyguardBottomAreaView.getHeight() : this.context.getResources().getDisplayMetrics().heightPixels;
    }

    private final float getKgdNotificationCapsuleBottomPadding() {
        ((OplusKeyguardBottomAreaControllerEx) this.keyguardBottomAreaControllerEx.get()).getClass();
        return 0.0f;
    }

    private final double getScaleFadeDamping() {
        return 0.0d;
    }

    private final IKeyguardTipsViewController getTipsViewController() {
        return (IKeyguardTipsViewController) this.oplusKeyguardTipsViewController.get();
    }

    private final float getUdfpsIconPadding() {
        if (((OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get()).isNeedNotificationOverlayFingerPrint() || (((((KeyguardStateController) this.keyguardStateController.get()).mCanDismissLockScreen && !((OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get()).isFingerprintIconReallyVisible()) || ((KeyguardStateController) this.keyguardStateController.get()).getEx().isKeyguardFpSuppressed()) && ((OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get()).getFpType() != 2)) {
            return 0.0f;
        }
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, this.context, false, 6);
        if (contextUpdateDisplayConfiguration$default == null) {
            contextUpdateDisplayConfiguration$default = this.context;
        }
        int dimensionPixelSize = contextUpdateDisplayConfiguration$default.getResources().getDimensionPixelSize(2131168637);
        float udfpsIconPadding = ((OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get()).getUdfpsIconPadding();
        return udfpsIconPadding > 0.0f ? udfpsIconPadding + dimensionPixelSize : udfpsIconPadding;
    }

    private final void setStartedZoomAnimation(boolean z) {
        this.isStartedZoomAnimation = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            NotificationPanelViewController base = getBase();
            Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp-->".concat("isStartedZoomAnimation=" + z + " isTracking=" + (base != null ? Boolean.valueOf(base.isTracking()) : null)));
        }
        NotificationPanelViewController base2 = getBase();
        if (base2 != null && base2.isTracking() && z) {
            setTrackingStartedZoomAnimation(true);
        }
    }

    public final float adjustExpandedMovingHeight(float f) {
        NotificationPanelViewController base = getBase();
        if (base != null) {
            if (this.isScaleFadePanelAllowed) {
                ViewGroup view = base.getView();
                float height = view != null ? view.getHeight() : 0.0f;
                if (height > 0.0f) {
                    float maxPanelTransitionDistance = base.getMaxPanelTransitionDistance();
                    if (maxPanelTransitionDistance > 0.0f) {
                        if (height < maxPanelTransitionDistance) {
                            height = maxPanelTransitionDistance;
                        }
                        float fAbs = Math.abs(f);
                        if (fAbs > height) {
                            fAbs = height;
                        }
                        float fCoerceIn = fAbs / height;
                        if (((KeyguardStateController) this.keyguardStateController.get()).mShowing && ((KeyguardStateController) this.keyguardStateController.get()).mCanDismissLockScreen && ((OneShotAnimController) this.oneShotAnimController.get()).willSupportOneShotForUnlock()) {
                            fCoerceIn = RangesKt___RangesKt.coerceIn((fCoerceIn * this.oneshotHandleScaleRatio) / 100.0f, 0.0f, 1.0f);
                        }
                        double d = fCoerceIn;
                        return Math.signum(f) * ((float) (Math.sin(d * 1.5707963267948966d) * ExternalSyntheticOutline0.m(-getScaleFadeDamping(), d, maxPanelTransitionDistance)));
                    }
                }
            } else if (base.getBarState() == 0) {
                return this.panelAnimationEx.getAdjustExpandedHeight(f, base.getMaxPanelTransitionDistance());
            }
        }
        return f;
    }

    public final float adjustKeyguardBottomAreaExpansionAlpha(float f) {
        NotificationPanelViewController base = getBase();
        if (base == null || !canScaleFadePanelAtExpandFraction(base.getExpandedFraction())) {
            return MathUtils.saturate(f);
        }
        return OplusKgdAnimParams.INSTANCE.getINTERPOLATOR_SCALE_FADE().getInterpolation(((PrimaryBouncerInteractor) this.primaryBouncerInteractor.get()).isBouncerShowing() ? (float) Math.pow(MathUtils.saturate(base.getExpandedFraction()), 3.0d) : MathUtils.saturate(base.getExpandedFraction()));
    }

    public boolean applyCustomFlingCollapseAnimation(ValueAnimator valueAnimator, float f) {
        NotificationPanelViewController base = getBase();
        if (base == null || !canScaleFadePanelAtExpandedHeight(base.getExpandedHeight())) {
            return false;
        }
        valueAnimator.setInterpolator(OplusKgdAnimParams.INSTANCE.getOSpringAnimInterpolator());
        ViewGroup view = base.getView();
        float height = view != null ? view.getHeight() : base.getExpandedHeight();
        if (height <= 0.0f) {
            valueAnimator.setDuration(0L);
            return true;
        }
        float fConstrain = MathUtils.constrain(base.getExpandedHeight() / height, 0.0f, 1.0f);
        if (f == 0.0f) {
            valueAnimator.setDuration((long) ((fConstrain * 100.0f) + 200.0f));
            return true;
        }
        if (ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
            valueAnimator.setDuration((long) ((fConstrain * 100.0f) + 400.0f));
            return true;
        }
        valueAnimator.setDuration((long) ((fConstrain * 100.0f) + 100.0f));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: bind, reason: merged with bridge method [inline-methods] */
    public void bind$1(NotificationPanelViewController notificationPanelViewController) {
        setBase(notificationPanelViewController);
        this.pictorialMediator.setPanelViewController(notificationPanelViewController);
        this.panelViewController = notificationPanelViewController;
        this.configurationController.addCallback(this);
    }

    public final int calculateStackScrollBottomMargin(int i, boolean z) {
        if (this.currentBarState == 1) {
            return 0;
        }
        if (z) {
            Context context = this.context;
            if (!DeviceStateUtils.isLandInSmallScreen(context, DeviceUtil.isLandscape(context))) {
                return NavBarUtils.getNavigationBarHeight(this.context) + i;
            }
        }
        return i;
    }

    public final boolean canScaleFadePanelAtExpandFraction(float f) {
        NotificationPanelViewController base = getBase();
        if (base != null) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isMotionOptimizationEnable && base.getBarState() == 1 && this.isScaleFadePanelAllowed && f >= 0.0f && f < 1.0f && ((KeyguardViewMediator) this.keyguardViewMediator.get()).mShowing && !base.isFullyExpanded()) {
                if (base.isTracking()) {
                    return true;
                }
                return (base.mHeightAnimator == null || base.mIsSpringBackAnimation) ? false : true;
            }
        }
        return false;
    }

    public final boolean canScaleFadePanelAtExpandedHeight(float f) {
        NotificationPanelViewController base = getBase();
        return base != null && this.isScaleFadePanelAllowed && f >= 0.0f && f < ((float) base.getMaxPanelTransitionDistance());
    }

    public final void cancelHeadUpSpringBackAnimator(boolean z) {
        ValueAnimator valueAnimator = this.headUpSpringBackAnimator;
        if (valueAnimator != null) {
            if (!valueAnimator.isRunning()) {
                valueAnimator = null;
            }
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }
        if (z) {
            resetZoomScalex(1.0f);
            this.dragExpandableNotificationRow = null;
        }
        this.headUpSpringBackAnimator = null;
    }

    public final void cancelRequestPanelHeightUpdateAction() {
        NotificationPanelViewController base;
        ViewGroup view;
        Handler handler;
        Runnable runnable = this.requestPanelHeightUpdateAction;
        if (runnable == null || (base = getBase()) == null || (view = base.getView()) == null || (handler = view.getHandler()) == null) {
            return;
        }
        if (!handler.hasCallbacks(runnable)) {
            handler = null;
        }
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public final void checkUpdateScaleFadePanelAllowedState() {
        View view;
        QuickSettingsController qsController;
        KeyguardFeatureOption.INSTANCE.getClass();
        boolean z = false;
        if (!KeyguardFeatureOption.isMotionOptimizationEnable) {
            this.isScaleFadePanelAllowed = false;
            return;
        }
        NotificationPanelViewController base = getBase();
        if (base != null && base.getBarState() == 1 && ((qsController = base.getQsController()) == null || !qsController.getExpanded())) {
            z = true;
        }
        if (this.isScaleFadePanelAllowed != z) {
            this.isScaleFadePanelAllowed = z;
            NotificationPanelViewController base2 = getBase();
            if (base2 != null) {
                base2.requestScrollerTopPaddingUpdate();
                base2.setExpandedHeight(base2.getExpandedHeight());
                if (base2.mNotificationContainerParent == null) {
                    return;
                }
                float fLerp = MathUtils.lerp(1.0f, 0.9f, 0.0f);
                NotificationsQuickSettingsContainer notificationsQuickSettingsContainer = base2.mNotificationContainerParent;
                boolean z2 = base2.mSplitShadeEnabled;
                if (notificationsQuickSettingsContainer.mStackScroller != null && (view = notificationsQuickSettingsContainer.mQSContainer) != null) {
                    view.getBoundsOnScreen(notificationsQuickSettingsContainer.mUpperRect);
                    notificationsQuickSettingsContainer.mStackScroller.getBoundsOnScreen(notificationsQuickSettingsContainer.mBoundingBoxRect);
                    notificationsQuickSettingsContainer.mBoundingBoxRect.union(notificationsQuickSettingsContainer.mUpperRect);
                    float fCenterX = notificationsQuickSettingsContainer.mBoundingBoxRect.centerX();
                    float fCenterY = notificationsQuickSettingsContainer.mBoundingBoxRect.centerY();
                    notificationsQuickSettingsContainer.mQSContainer.setPivotX(fCenterX);
                    notificationsQuickSettingsContainer.mQSContainer.setPivotY(fCenterY);
                    notificationsQuickSettingsContainer.mQSContainer.setScaleX(fLerp);
                    notificationsQuickSettingsContainer.mQSContainer.setScaleY(fLerp);
                    notificationsQuickSettingsContainer.mStackScroller.setPivotX(z2 ? 0.0f : fCenterX);
                    notificationsQuickSettingsContainer.mStackScroller.setPivotY(fCenterY);
                    notificationsQuickSettingsContainer.mStackScroller.setScaleX(fLerp);
                    notificationsQuickSettingsContainer.mStackScroller.setScaleY(fLerp);
                }
                ScrimController scrimController = base2.mScrimController;
                scrimController.mNotificationsScrim.setScaleX(fLerp);
                scrimController.mNotificationsScrim.setScaleY(fLerp);
            }
        }
    }

    public final void collectNotificationPanelChangeTime(boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (z) {
            String date = NotificationStatisticUtil.formatDate(jCurrentTimeMillis);
            if ("slide_down".equals("slide_down")) {
                NotificationStatisticUtil.sDownExpansionTime = date;
                return;
            } else {
                if ("slide_up".equals("slide_down")) {
                    NotificationStatisticUtil.sUpCollapseTime = date;
                    return;
                }
                return;
            }
        }
        String date2 = NotificationStatisticUtil.formatDate(jCurrentTimeMillis);
        if ("slide_down".equals("slide_up")) {
            NotificationStatisticUtil.sDownExpansionTime = date2;
        } else if ("slide_up".equals("slide_up")) {
            NotificationStatisticUtil.sUpCollapseTime = date2;
        }
    }

    public final float computeNotificationScaleFraction() {
        NotificationPanelViewController base = getBase();
        if (base != null) {
            return base.getBarState() == 1 ? ((PrimaryBouncerInteractor) this.primaryBouncerInteractor.get()).isBouncerShowing() : false ? (float) Math.pow(MathUtils.saturate(base.getExpandedFraction()), 2.0d) : MathUtils.saturate(base.getExpandedFraction());
        }
        return 1.0f;
    }

    public final void customizerShowChange(boolean z) {
        this.clearAllController.setQsCustomizerShowing(z);
    }

    public void delayExpandedRunnable(Runnable runnable) {
        runnable.run();
    }

    public void doOnPerDraw(View view, float f, BiConsumer<View, Float> biConsumer) {
        NotificationPanelViewControllerExImp$doOnPerDraw$.inlined.doOnPreDraw.1 r0 = new NotificationPanelViewControllerExImp$doOnPerDraw$.inlined.doOnPreDraw.1();
        r0.$action$inlined = biConsumer;
        r0.$view$inlined = view;
        r0.$newHeight$inlined = f;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(view, r0);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e9  */
    public void endHeadsUpTrackingEvent(MotionEvent motionEvent) {
        NotificationPanelViewController base;
        ValueAnimator heightAnimator;
        ExpandableNotificationRow row;
        ValueAnimator valueAnimator;
        ValueAnimator heightAnimator2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        cancelHeadUpSpringBackAnimator(false);
        boolean z = true;
        if (System.currentTimeMillis() - this.ambientState.getLastExpandingVelocitySetTime() < 50 && (base = getBase()) != null && base.mHeadsUpManager.hasPinnedHeadsUp()) {
            float headTrackingHeight = this.ambientState.getExt().getHeadTrackingHeight();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("NotificationPanelViewControllerExImp-->", "SystemUi--Notification", NormalizedTouchData$.ExternalSyntheticOutline0.m(headTrackingHeight, motionEvent.getActionMasked(), "endHeadsUpTrackingEvent event: ", ", headTrackingHeight: ", " isStartedZoomAnimation="), this.isStartedZoomAnimation);
            }
            if (this.isStartedZoomAnimation) {
                setHeadsUpSwipeUp(false);
                return;
            }
            if (headTrackingHeight > 0.0f) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(headTrackingHeight, 0.0f);
                createHeadUpSpringBackAnimation.1 r1 = new createHeadUpSpringBackAnimation.1();
                r1.this$0 = this;
                VarHandle.storeStoreFence();
                valueAnimatorOfFloat.addUpdateListener(r1);
                valueAnimatorOfFloat.addListener(new createHeadUpSpringBackAnimation.2(this.headsUpManager.getTopEntry(), this));
                valueAnimatorOfFloat.setDuration(350L);
                valueAnimatorOfFloat.setInterpolator(SPRING_BACK_PATH_INTERPOLATOR);
                valueAnimatorOfFloat.addListener(new endHeadsUpTrackingEvent.2(this, 0));
                if (this.ambientState.getTrackedHeadsUpRow() != null) {
                    row = this.ambientState.getTrackedHeadsUpRow();
                } else {
                    NotificationEntry topEntry = this.headsUpManager.getTopEntry();
                    row = topEntry != null ? topEntry.getRow() : null;
                }
                if (row != null && (oplusExpandableNotificationRowEx = row.mRowEx) != null) {
                    oplusExpandableNotificationRowEx.boostFrameRate(valueAnimatorOfFloat);
                }
                this.headUpSpringBackAnimator = valueAnimatorOfFloat;
                NotificationPanelViewController base2 = getBase();
                if (base2 == null || (heightAnimator2 = base2.getHeightAnimator()) == null) {
                    valueAnimator = this.headUpSpringBackAnimator;
                    if (valueAnimator != null) {
                        valueAnimator.start();
                    }
                } else {
                    if (!heightAnimator2.isRunning()) {
                        heightAnimator2 = null;
                    }
                    if (heightAnimator2 != null) {
                        heightAnimator2.addListener(new endHeadsUpTrackingEvent.2(this, 1));
                        heightAnimator2.setCurrentFraction(1.0f);
                    } else {
                        valueAnimator = this.headUpSpringBackAnimator;
                        if (valueAnimator != null) {
                            valueAnimator.start();
                        }
                    }
                }
                z = false;
            } else if (isTrackingChildBannerWithSlidingUpBlocking()) {
                NotificationPanelViewController base3 = getBase();
                if (base3 != null && (heightAnimator = base3.getHeightAnimator()) != null) {
                    if (!base3.isCollapsing() || !heightAnimator.isRunning()) {
                        heightAnimator = null;
                    }
                    if (heightAnimator != null) {
                        heightAnimator.addListener(new endHeadsUpTrackingEvent.2(base3, 2));
                        heightAnimator.setCurrentFraction(1.0f);
                    }
                }
            } else {
                ExpandableNotificationRow trackedHeadsUpRow = this.ambientState.getTrackedHeadsUpRow();
                if (trackedHeadsUpRow != null) {
                    NotificationStatisticUtil.collectNotificationMenuEvent(this.context, trackedHeadsUpRow.getEntry(), "", "swipe_up", 1000);
                }
                setHeadsUpSwipeUp(true);
                this.headsUpManager.unpinAll(true, "NPVCEI.endHeadsUpTrackingEvent");
            }
        }
        setStartedZoomAnimation(false);
        if (z) {
            this.dragExpandableNotificationRow = null;
        }
    }

    public final int ensurePanelMaxHeight(int i, boolean z) {
        NotificationPanelViewController base;
        ViewGroup view;
        Resources resources;
        int iEnsureStackMaxHeight = ensureStackMaxHeight(i);
        return (!z || (base = getBase()) == null || (view = base.getView()) == null || (resources = view.getResources()) == null) ? iEnsureStackMaxHeight : getSplitStatusBarHeight(true, resources) + iEnsureStackMaxHeight;
    }

    public final int ensureStackMaxHeight(int i) {
        int i2;
        NotificationPanelViewController base = getBase();
        return (base == null || base.getBarState() != 0 || !base.mExpandingFromHeadsUp || (i2 = base.mHeadsUpStartHeight) < i) ? i : this.ambientState.getExt().getMaxHeadsUpPullHeight() + this.notificationStackScrollLayoutController.getMinExpansionHeight() + i2;
    }

    public final boolean forbidQsExpansion() {
        return this.panelAnimationEx.getUseLayeredAnimation() ? this.overScrollAnimationEx.forbidQsExpansion() : QSFragmentHelper.getInstance().forbidQsExpansion();
    }

    public final float getInitialExpandY(float f, float f2) {
        this.lastY = f2;
        float f3 = this.multiplePointerInitialY;
        return f3 == 0.0f ? f : f3;
    }

    public boolean getIsNeedInterceptZoom() {
        return this.isNeedInterceptZoom;
    }

    public int getKeyguardNotificationBottomPadding() {
        return this.keyguardNotificationBottomPadding;
    }

    public int getKeyguardStyleClockHeight() {
        return ((KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get()).getKeyguardStyleClockHeight();
    }

    public float getMaxVerticalSpaceForLockScreenNotification() {
        int keyguardScreenHeight = getKeyguardScreenHeight();
        float smallClockBottomPositionForNotification = ((KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get()).getSmallClockBottomPositionForNotification();
        float keyguardNotificationBottomPadding = (keyguardScreenHeight - smallClockBottomPositionForNotification) - getKeyguardNotificationBottomPadding();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int keyguardNotificationBottomPadding2 = getKeyguardNotificationBottomPadding();
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(keyguardNotificationBottomPadding, keyguardScreenHeight, "getMaxVerticalSpaceForNotification: maxVerticalSpace=", " screenHeight=", " clockBottomPosition=");
            sbM.append(smallClockBottomPositionForNotification);
            sbM.append(" bottomPadding=");
            sbM.append(keyguardNotificationBottomPadding2);
            Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp-->".concat(sbM.toString()));
        }
        if (keyguardNotificationBottomPadding < 0.0f) {
            return 0.0f;
        }
        return keyguardNotificationBottomPadding;
    }

    public int getPanelWidth(Context context) {
        return ColumnWidthUtil.getPanelWidth(context);
    }

    public float getPreY() {
        return this.mPreY;
    }

    public int getQQSBottom() {
        if (((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()) {
            return 0;
        }
        int[] iArr = new int[2];
        OplusQSBottomImpl qSBottom = QSFragmentHelper.getInstance().getQSBottom();
        if (qSBottom == null) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "qsBottom is null");
            return 0;
        }
        qSBottom.getLocationOnScreen(iArr);
        return qSBottom.getHeight() + iArr[1];
    }

    public Region getQSInterceptRegion(int i, int i2, int i3, View view, View view2, QS qs, boolean z, boolean z2) {
        if ((z && !z2) || qs == null) {
            return new Region((int) view2.getX(), i2 + i, view2.getWidth() + ((int) view2.getX()), i3 + i);
        }
        if (z2) {
            return new Region((int) view.getX(), i2 + i, view.getWidth() + ((int) view.getX()), qs.getDesiredHeight() + i);
        }
        return new Region((int) view.getX(), i2 + i, view.getWidth() + ((int) view.getX()), qs.getQsMinExpansionHeight() + i);
    }

    public final float getQsKeyguardExpandFraction(float f, int i) {
        return Math.min(1.0f, (f - i) / this.thresholdForElementAppearance);
    }

    public int getQuickQsOffsetHeight(Context context, Resources resources) {
        return resources.getDimensionPixelSize(2131170763);
    }

    public final Lazy getSeedlingPluginManager() {
        return this.seedlingPluginManager;
    }

    public int getSplitStatusBarHeight(boolean z, Resources resources) {
        float initialDisplayDensity = 1.0f;
        if (z) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                initialDisplayDensity = (KeyguardUtils.Companion.getInitialDisplayDensity() * 1.0f) / resources.getDisplayMetrics().densityDpi;
            }
        }
        return (int) (initialDisplayDensity * resources.getDimensionPixelSize(2131172947));
    }

    public Long getStatusBarEventDownTime() {
        return this.statusBarEventDownTime;
    }

    public final float getSuitableSpeed(float f, boolean z, boolean z2, float f2) {
        if (z) {
            if (f > 3000.0f) {
                return 3000.0f;
            }
        } else if (z2) {
            float layoutMaxHeight = f2 / this.ambientState.getLayoutMaxHeight();
            if (layoutMaxHeight > 1.0f) {
                layoutMaxHeight = 1.0f;
            }
            if (0.0f >= layoutMaxHeight) {
                layoutMaxHeight = 0.0f;
            }
            float f3 = layoutMaxHeight * 9000.0f;
            float f4 = 3000.0f < f3 ? f3 : 3000.0f;
            if (f > f4) {
                return f4;
            }
        } else if (f > 9000.0f) {
            return 9000.0f;
        }
        return f;
    }

    public Pair<Float, Float> getTargetScaleFadePivot() {
        QuickSettingsController qsController;
        QS qs;
        View view;
        NotificationShadeWindowView notificationShadeWindowView;
        NotificationShadeWindowView notificationShadeWindowView2;
        Float fValueOf = Float.valueOf(0.0f);
        NotificationPanelViewController base = getBase();
        if (base == null) {
            return new Pair<>(fValueOf, fValueOf);
        }
        if (base.mSplitShadeEnabled) {
            CentralSurfaces centralSurfaces = base.mCentralSurfaces;
            if (centralSurfaces != null && (notificationShadeWindowView2 = centralSurfaces.getNotificationShadeWindowView()) != null) {
                return new Pair<>(Float.valueOf(notificationShadeWindowView2.getWidth() / 2.0f), Float.valueOf(notificationShadeWindowView2.getHeight() / 2.0f));
            }
        } else if (base.getBarState() == 1) {
            CentralSurfaces centralSurfaces2 = base.mCentralSurfaces;
            if (centralSurfaces2 != null && (notificationShadeWindowView = centralSurfaces2.getNotificationShadeWindowView()) != null) {
                return new Pair<>(Float.valueOf(notificationShadeWindowView.getWidth() / 2.0f), Float.valueOf(notificationShadeWindowView.getHeight() / 2.0f));
            }
        } else {
            NotificationStackScrollLayout view2 = this.notificationStackScrollLayoutController.getView();
            if (view2 != null && (qsController = base.getQsController()) != null && (qs = qsController.getQs()) != null && (view = qs.getView()) != null) {
                return new Pair<>(Float.valueOf(base.getView().getWidth() / 2.0f), Float.valueOf(((view2.getIntrinsicContentHeight() + this.paddingBetweenNotifAndScrim) + view.getHeight()) / 2.0f));
            }
        }
        return new Pair<>(fValueOf, fValueOf);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0221  */
    /* JADX WARN: Code duplicated, block: B:105:0x022b  */
    /* JADX WARN: Code duplicated, block: B:109:0x023b  */
    /* JADX WARN: Code duplicated, block: B:62:0x0123  */
    /* JADX WARN: Code duplicated, block: B:66:0x012b  */
    /* JADX WARN: Code duplicated, block: B:68:0x0148  */
    /* JADX WARN: Code duplicated, block: B:75:0x0175  */
    /* JADX WARN: Code duplicated, block: B:82:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:89:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:96:0x01fc  */
    /* JADX WARN: Instruction removed from duplicated block: B:66:0x012b, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v22, types: [android.view.View, com.oplus.systemui.qs.OplusQSTileMediaContainer] */
    /* JADX WARN: Type inference failed for: r5v27, types: [android.view.View, com.oplus.systemui.qs.OplusQuickStatusBarHeader] */
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
    public boolean handleQsTouch(MotionEvent motionEvent) {
        int iFindPointerIndex;
        float rawY;
        float f;
        int scrollY;
        int scrollY2;
        float f2;
        int scrollY3;
        int scrollY4;
        int scrollY5;
        int scrollY6;
        MyDevicePanel myDevicePanel;
        int scrollY7;
        int iCalcRealOverScrollDist;
        int iCalcRealOverScrollDist2;
        int iCalcRealOverScrollDist3;
        int iCalcRealOverScrollDist4;
        int iCalcRealOverScrollDist5;
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            return this.overScrollAnimationEx.handleQSTouch(motionEvent);
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        if (qSFragmentHelper.mContainer != null) {
            int actionMasked = motionEvent.getActionMasked() & 255;
            if (qSFragmentHelper.isFullExpanded()) {
                SuperPowerSaveRepositoryImpl.Companion.getClass();
                if (!SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                    if (qSFragmentHelper.mVelocityTracker == null) {
                        qSFragmentHelper.mVelocityTracker = VelocityTracker.obtain();
                    }
                    qSFragmentHelper.mVelocityTracker.addMovement(motionEvent);
                    if (actionMasked == 0) {
                        qSFragmentHelper.mLastMotionX = motionEvent.getX();
                        float rawY2 = motionEvent.getRawY();
                        qSFragmentHelper.mLastMotionY = rawY2;
                        qSFragmentHelper.mInitialMotionY = rawY2;
                        qSFragmentHelper.mActivePointerId = motionEvent.getPointerId(0);
                        qSFragmentHelper.abortAnimation();
                        qSFragmentHelper.mScrollMode = -1;
                        qSFragmentHelper.mDeltaY = 0.0f;
                    } else if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked != 3) {
                                if (actionMasked == 5) {
                                    int actionIndex = motionEvent.getActionIndex();
                                    qSFragmentHelper.mLastMotionY = motionEvent.getRawY(actionIndex);
                                    qSFragmentHelper.mActivePointerId = motionEvent.getPointerId(actionIndex);
                                } else if (actionMasked == 6) {
                                    int actionIndex2 = motionEvent.getActionIndex();
                                    if (motionEvent.getPointerId(actionIndex2) == qSFragmentHelper.mActivePointerId) {
                                        int i = actionIndex2 == 0 ? 1 : 0;
                                        qSFragmentHelper.mLastMotionY = motionEvent.getY(i);
                                        qSFragmentHelper.mActivePointerId = motionEvent.getPointerId(i);
                                        VelocityTracker velocityTracker = qSFragmentHelper.mVelocityTracker;
                                        if (velocityTracker != null) {
                                            velocityTracker.clear();
                                        }
                                    }
                                    int iFindPointerIndex2 = motionEvent.findPointerIndex(qSFragmentHelper.mActivePointerId);
                                    if (iFindPointerIndex2 != -1) {
                                        qSFragmentHelper.mLastMotionY = motionEvent.getRawY(iFindPointerIndex2);
                                    }
                                }
                            } else if (qSFragmentHelper.mIsBeingDragged) {
                                qSFragmentHelper.resetTouch();
                            }
                        } else if (!qSFragmentHelper.mIsBeingDragged) {
                            int iFindPointerIndex3 = motionEvent.findPointerIndex(qSFragmentHelper.mActivePointerId);
                            if (iFindPointerIndex3 == -1) {
                                qSFragmentHelper.resetTouch();
                            } else {
                                float x = motionEvent.getX(iFindPointerIndex3);
                                float fAbs = Math.abs(x - qSFragmentHelper.mLastMotionX);
                                float rawY3 = motionEvent.getRawY(iFindPointerIndex3);
                                float fAbs2 = Math.abs(rawY3 - qSFragmentHelper.mLastMotionY);
                                qSFragmentHelper.mLastTurnMotionY = 0.0f;
                                if (fAbs2 > qSFragmentHelper.mTouchSlop && fAbs2 > fAbs) {
                                    qSFragmentHelper.mIsBeingDragged = true;
                                    qSFragmentHelper.mLastMotionX = x;
                                    qSFragmentHelper.mLastMotionY = rawY3;
                                }
                                if (qSFragmentHelper.mIsBeingDragged) {
                                    rawY = motionEvent.getRawY(iFindPointerIndex);
                                    f = qSFragmentHelper.mLastMotionY - rawY;
                                    scrollY = qSFragmentHelper.mQSPanelScrollView.getScrollY();
                                    qSFragmentHelper.mDeltaY = f;
                                    StringBuilder sb = new StringBuilder("performDrag mLastMotionY=");
                                    VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, qSFragmentHelper.mLastMotionY, " deltaY=", f, " panelScrollY=");
                                    ClipboardOverlayView$.ExternalSyntheticOutline0.m(scrollY, LogUtil.TAG_QS, "QSFragmentHelper", sb);
                                    qSFragmentHelper.mLastMotionY = rawY;
                                    if (f < (-qSFragmentHelper.mTouchSlop)) {
                                        qSFragmentHelper.mLastTurnMotionY = rawY;
                                    }
                                    if (scrollY >= -120) {
                                        scrollY2 = qSFragmentHelper.mHeader.getScrollY();
                                        f2 = scrollY2 + f;
                                        LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", "deltaY=" + f);
                                        if (f2 <= 0.0f) {
                                            iCalcRealOverScrollDist5 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY2, qSFragmentHelper.mOverscrollDistance, 27);
                                            if (f > 0.0f) {
                                                iCalcRealOverScrollDist5 = 2;
                                            }
                                            qSFragmentHelper.mHeader.scrollTo(0, Math.min(scrollY2 + iCalcRealOverScrollDist5, 0));
                                            BaseQuickStatusBarHeader baseQuickStatusBarHeader = qSFragmentHelper.mHeader;
                                            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                            baseQuickStatusBarHeader.postInvalidateOnAnimation();
                                        }
                                        scrollY3 = qSFragmentHelper.mFooter.getScrollY();
                                        if (scrollY3 + f <= 0.0f) {
                                            iCalcRealOverScrollDist4 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY3, qSFragmentHelper.mOverscrollDistance, 24);
                                            if (f > 0.0f) {
                                                iCalcRealOverScrollDist4 = 2;
                                            }
                                            qSFragmentHelper.mFooter.scrollTo(0, Math.min(scrollY3 + iCalcRealOverScrollDist4, 0));
                                            OplusQSFooterImpl oplusQSFooterImpl = qSFragmentHelper.mFooter;
                                            WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                                            oplusQSFooterImpl.postInvalidateOnAnimation();
                                        }
                                        scrollY4 = qSFragmentHelper.mTileMediaContainer.getScrollY();
                                        if (scrollY4 + f <= 0.0f) {
                                            iCalcRealOverScrollDist3 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY4, qSFragmentHelper.mOverscrollDistance, 21);
                                            if (f > 0.0f) {
                                                iCalcRealOverScrollDist3 = 2;
                                            }
                                            qSFragmentHelper.mTileMediaContainer.scrollTo(0, Math.min(scrollY4 + iCalcRealOverScrollDist3, 0));
                                            ConstraintLayout constraintLayout = qSFragmentHelper.mTileMediaContainer;
                                            WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                                            constraintLayout.postInvalidateOnAnimation();
                                        }
                                        scrollY5 = qSFragmentHelper.mQSPanelScrollView.getScrollY();
                                        if (scrollY5 + f <= 0.0f) {
                                            iCalcRealOverScrollDist2 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY5, qSFragmentHelper.mOverscrollDistance, 18);
                                            if (f > 0.0f) {
                                                iCalcRealOverScrollDist2 = 2;
                                            }
                                            qSFragmentHelper.mQSPanelScrollView.scrollTo(0, Math.min(scrollY5 + iCalcRealOverScrollDist2, 0));
                                            OplusQSPanelContainer oplusQSPanelContainer = qSFragmentHelper.mQSPanelScrollView;
                                            WeakHashMap weakHashMap4 = ViewCompat.sViewPropertyAnimatorMap;
                                            oplusQSPanelContainer.postInvalidateOnAnimation();
                                        }
                                        scrollY6 = qSFragmentHelper.mQSBottom.getScrollY();
                                        if (scrollY6 + f <= 0.0f) {
                                            iCalcRealOverScrollDist = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY6, qSFragmentHelper.mOverscrollDistance, 16);
                                            if (f > 0.0f) {
                                                iCalcRealOverScrollDist = 2;
                                            }
                                            qSFragmentHelper.mQSBottom.scrollTo(0, Math.min(scrollY6 + iCalcRealOverScrollDist, 0));
                                            OplusQSBottomImpl oplusQSBottomImpl = qSFragmentHelper.mQSBottom;
                                            WeakHashMap weakHashMap5 = ViewCompat.sViewPropertyAnimatorMap;
                                            oplusQSBottomImpl.postInvalidateOnAnimation();
                                        }
                                        myDevicePanel = qSFragmentHelper.mMyDevicePanel;
                                        if (myDevicePanel != null) {
                                            scrollY7 = myDevicePanel.getScrollY();
                                            if (scrollY7 + f <= 0.0f) {
                                                int iCalcRealOverScrollDist6 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY7, qSFragmentHelper.mOverscrollDistance, 14);
                                                qSFragmentHelper.mMyDevicePanel.scrollTo(0, Math.min(scrollY7 + ((f > 0.0f || iCalcRealOverScrollDist6 != 0) ? iCalcRealOverScrollDist6 : 2), 0));
                                                MyDevicePanel myDevicePanel2 = qSFragmentHelper.mMyDevicePanel;
                                                WeakHashMap weakHashMap6 = ViewCompat.sViewPropertyAnimatorMap;
                                                myDevicePanel2.postInvalidateOnAnimation();
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (qSFragmentHelper.mIsBeingDragged && (iFindPointerIndex = motionEvent.findPointerIndex(qSFragmentHelper.mActivePointerId)) != -1) {
                            rawY = motionEvent.getRawY(iFindPointerIndex);
                            f = qSFragmentHelper.mLastMotionY - rawY;
                            scrollY = qSFragmentHelper.mQSPanelScrollView.getScrollY();
                            qSFragmentHelper.mDeltaY = f;
                            StringBuilder sb2 = new StringBuilder("performDrag mLastMotionY=");
                            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb2, qSFragmentHelper.mLastMotionY, " deltaY=", f, " panelScrollY=");
                            ClipboardOverlayView$.ExternalSyntheticOutline0.m(scrollY, LogUtil.TAG_QS, "QSFragmentHelper", sb2);
                            qSFragmentHelper.mLastMotionY = rawY;
                            if (f < (-qSFragmentHelper.mTouchSlop)) {
                                qSFragmentHelper.mLastTurnMotionY = rawY;
                            }
                            if (scrollY >= -120) {
                                scrollY2 = qSFragmentHelper.mHeader.getScrollY();
                                f2 = scrollY2 + f;
                                LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", "deltaY=" + f);
                                if (f2 <= 0.0f) {
                                    iCalcRealOverScrollDist5 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY2, qSFragmentHelper.mOverscrollDistance, 27);
                                    if (f > 0.0f && iCalcRealOverScrollDist5 == 0) {
                                        iCalcRealOverScrollDist5 = 2;
                                    }
                                    qSFragmentHelper.mHeader.scrollTo(0, Math.min(scrollY2 + iCalcRealOverScrollDist5, 0));
                                    BaseQuickStatusBarHeader baseQuickStatusBarHeader2 = qSFragmentHelper.mHeader;
                                    WeakHashMap weakHashMap7 = ViewCompat.sViewPropertyAnimatorMap;
                                    baseQuickStatusBarHeader2.postInvalidateOnAnimation();
                                }
                                scrollY3 = qSFragmentHelper.mFooter.getScrollY();
                                if (scrollY3 + f <= 0.0f) {
                                    iCalcRealOverScrollDist4 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY3, qSFragmentHelper.mOverscrollDistance, 24);
                                    if (f > 0.0f && iCalcRealOverScrollDist4 == 0) {
                                        iCalcRealOverScrollDist4 = 2;
                                    }
                                    qSFragmentHelper.mFooter.scrollTo(0, Math.min(scrollY3 + iCalcRealOverScrollDist4, 0));
                                    OplusQSFooterImpl oplusQSFooterImpl2 = qSFragmentHelper.mFooter;
                                    WeakHashMap weakHashMap8 = ViewCompat.sViewPropertyAnimatorMap;
                                    oplusQSFooterImpl2.postInvalidateOnAnimation();
                                }
                                scrollY4 = qSFragmentHelper.mTileMediaContainer.getScrollY();
                                if (scrollY4 + f <= 0.0f) {
                                    iCalcRealOverScrollDist3 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY4, qSFragmentHelper.mOverscrollDistance, 21);
                                    if (f > 0.0f && iCalcRealOverScrollDist3 == 0) {
                                        iCalcRealOverScrollDist3 = 2;
                                    }
                                    qSFragmentHelper.mTileMediaContainer.scrollTo(0, Math.min(scrollY4 + iCalcRealOverScrollDist3, 0));
                                    ConstraintLayout constraintLayout2 = qSFragmentHelper.mTileMediaContainer;
                                    WeakHashMap weakHashMap9 = ViewCompat.sViewPropertyAnimatorMap;
                                    constraintLayout2.postInvalidateOnAnimation();
                                }
                                scrollY5 = qSFragmentHelper.mQSPanelScrollView.getScrollY();
                                if (scrollY5 + f <= 0.0f) {
                                    iCalcRealOverScrollDist2 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY5, qSFragmentHelper.mOverscrollDistance, 18);
                                    if (f > 0.0f && iCalcRealOverScrollDist2 == 0) {
                                        iCalcRealOverScrollDist2 = 2;
                                    }
                                    qSFragmentHelper.mQSPanelScrollView.scrollTo(0, Math.min(scrollY5 + iCalcRealOverScrollDist2, 0));
                                    OplusQSPanelContainer oplusQSPanelContainer2 = qSFragmentHelper.mQSPanelScrollView;
                                    WeakHashMap weakHashMap10 = ViewCompat.sViewPropertyAnimatorMap;
                                    oplusQSPanelContainer2.postInvalidateOnAnimation();
                                }
                                scrollY6 = qSFragmentHelper.mQSBottom.getScrollY();
                                if (scrollY6 + f <= 0.0f) {
                                    iCalcRealOverScrollDist = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY6, qSFragmentHelper.mOverscrollDistance, 16);
                                    if (f > 0.0f && iCalcRealOverScrollDist == 0) {
                                        iCalcRealOverScrollDist = 2;
                                    }
                                    qSFragmentHelper.mQSBottom.scrollTo(0, Math.min(scrollY6 + iCalcRealOverScrollDist, 0));
                                    OplusQSBottomImpl oplusQSBottomImpl2 = qSFragmentHelper.mQSBottom;
                                    WeakHashMap weakHashMap11 = ViewCompat.sViewPropertyAnimatorMap;
                                    oplusQSBottomImpl2.postInvalidateOnAnimation();
                                }
                                myDevicePanel = qSFragmentHelper.mMyDevicePanel;
                                if (myDevicePanel != null) {
                                    scrollY7 = myDevicePanel.getScrollY();
                                    if (scrollY7 + f <= 0.0f) {
                                        int iCalcRealOverScrollDist7 = PhysicalAnimationUtil.calcRealOverScrollDist((int) f, scrollY7, qSFragmentHelper.mOverscrollDistance, 14);
                                        qSFragmentHelper.mMyDevicePanel.scrollTo(0, Math.min(scrollY7 + ((f > 0.0f || iCalcRealOverScrollDist7 != 0) ? iCalcRealOverScrollDist7 : 2), 0));
                                        MyDevicePanel myDevicePanel3 = qSFragmentHelper.mMyDevicePanel;
                                        WeakHashMap weakHashMap12 = ViewCompat.sViewPropertyAnimatorMap;
                                        myDevicePanel3.postInvalidateOnAnimation();
                                    }
                                }
                            }
                        }
                    } else if (qSFragmentHelper.mIsBeingDragged) {
                        VelocityTracker velocityTracker2 = qSFragmentHelper.mVelocityTracker;
                        velocityTracker2.computeCurrentVelocity(1000, qSFragmentHelper.mMaximumVelocity);
                        int yVelocity = (int) velocityTracker2.getYVelocity(qSFragmentHelper.mActivePointerId);
                        BaseQuickStatusBarHeader baseQuickStatusBarHeader3 = qSFragmentHelper.mHeader;
                        if (baseQuickStatusBarHeader3 != null && baseQuickStatusBarHeader3.getVisibility() == 0 && QSFragmentHelper.isInOverScroll(qSFragmentHelper.mHeader)) {
                            ?? r5 = qSFragmentHelper.mHeader;
                            qSFragmentHelper.springBack(r5, r5.getSpringOverScroller(), yVelocity);
                        }
                        if (QSFragmentHelper.isInOverScroll(qSFragmentHelper.mFooter)) {
                            OplusQSFooterImpl oplusQSFooterImpl3 = qSFragmentHelper.mFooter;
                            qSFragmentHelper.springBack(oplusQSFooterImpl3, oplusQSFooterImpl3.getSpringOverScroller(), yVelocity);
                        }
                        if (QSFragmentHelper.isInOverScroll(qSFragmentHelper.mTileMediaContainer)) {
                            ?? r6 = qSFragmentHelper.mTileMediaContainer;
                            qSFragmentHelper.springBack(r6, r6.getSpringOverScroller(), yVelocity);
                        }
                        if (QSFragmentHelper.isInOverScroll(qSFragmentHelper.mQSPanelScrollView)) {
                            OplusQSPanelContainer oplusQSPanelContainer3 = qSFragmentHelper.mQSPanelScrollView;
                            qSFragmentHelper.springBack(oplusQSPanelContainer3, oplusQSPanelContainer3.getSpringOverScroller(), yVelocity);
                        }
                        if (QSFragmentHelper.isInOverScroll(qSFragmentHelper.mQSBottom)) {
                            OplusQSBottomImpl oplusQSBottomImpl3 = qSFragmentHelper.mQSBottom;
                            qSFragmentHelper.springBack(oplusQSBottomImpl3, oplusQSBottomImpl3.getSpringOverScroller(), yVelocity);
                        }
                        MyDevicePanel myDevicePanel4 = qSFragmentHelper.mMyDevicePanel;
                        if (myDevicePanel4 != null && myDevicePanel4.getVisibility() == 0 && QSFragmentHelper.isInOverScroll(qSFragmentHelper.mMyDevicePanel)) {
                            MyDevicePanel myDevicePanel5 = qSFragmentHelper.mMyDevicePanel;
                            qSFragmentHelper.springBack(myDevicePanel5, myDevicePanel5.getSpringOverScroller(), yVelocity);
                        }
                        qSFragmentHelper.resetTouch();
                    }
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(actionMasked, "handleQsTouch: action=", " mIsBeingDragged=");
                    sbM.append(qSFragmentHelper.mIsBeingDragged);
                    sbM.append(" diffY=");
                    sbM.append(qSFragmentHelper.mInitialMotionY - qSFragmentHelper.mLastTurnMotionY);
                    sbM.append(" mInitialMotionY=");
                    sbM.append(qSFragmentHelper.mInitialMotionY);
                    sbM.append(" mLastTurnMotionY=");
                    sbM.append(qSFragmentHelper.mLastTurnMotionY);
                    LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", sbM.toString());
                    if (qSFragmentHelper.mIsBeingDragged && qSFragmentHelper.mInitialMotionY - qSFragmentHelper.mLastTurnMotionY < 0.0f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void hideQsPopup(boolean z) {
        if (z) {
            QSFragmentHelper.getInstance().hideCouiPopup();
        }
    }

    /* JADX WARN: Code duplicated, block: B:6:0x0065  */
    public void init(NotificationPanelView notificationPanelView) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        NotificationPanelViewControllerExImp$keyguardStateCallback$1 notificationPanelViewControllerExImp$keyguardStateCallback$1 = this.keyguardStateCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(notificationPanelViewControllerExImp$keyguardStateCallback$1);
        ((KeyguardStateController) this.keyguardStateController.get()).getEx().addCallback(this.keyguardStateCallback);
        getTipsViewController().init();
        ((OplusKgdImmersiveController) this.oplusKgdImmersiveController.get()).init();
        ((OplusKgdImmersiveThemeController) this.oplusKgdImmersiveThemeController.get()).init();
        this.statusBarStateController.addCallback(this.statusBarStateListener);
        this.panelAnimationEx.getElementShowState().addCallback(this.elementShowStateChangeListener);
        if (FlavorOneFeatureOption.INSTANCE.isSupportReSetFrameRate()) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(notificationPanelView, null), 3);
        } else {
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isNotificationLightOs) {
                BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(notificationPanelView, null), 3);
            }
        }
        NotificationShadeRegionHelper.registerCallback(this);
        NotificationSpotLightEffectManager.init(this.context, this.oplusPanoramicAodRepository);
        ChildrenModeObserver.getInstance().addListener(this.childModeListener);
    }

    public final boolean isBlockTouchUpdateExpandedHeight(float f) {
        NotificationPanelViewController base = getBase();
        if (base == null) {
            return false;
        }
        if (isTrackingChildBannerWithSlidingUpBlocking() && f <= base.mInitialOffsetOnTouch) {
            return true;
        }
        if (!base.mExpandingFromHeadsUp || f <= base.mInitialOffsetOnTouch) {
            return (isInHeadUpSpringBack() && f >= base.mInitialOffsetOnTouch) || this.isTrackingStartedZoomAnimation;
        }
        return true;
    }

    public final boolean isDisableDragSbWhenInKg() {
        return this.pulseExpansionHandlerExt.isDisableDragSbWhenInKg();
    }

    public boolean isExpandIntercept(MotionEvent motionEvent, boolean z) {
        return isTwoFingerSwipeTrackpadEvent(motionEvent) && z;
    }

    public final boolean isInBrickMode() {
        return OplusSceneModeObserver.Companion.getInstance(this.context).isInBrickMode;
    }

    public final boolean isInHeadUpSpringBack() {
        return this.headUpSpringBackAnimator != null;
    }

    public final boolean isKeepNpvWhenUnlockForOneshot() {
        boolean z = ((KeyguardStateController) this.keyguardStateController.get()).mShowing && ((KeyguardStateController) this.keyguardStateController.get()).mCanDismissLockScreen && ((OneShotAnimController) this.oneShotAnimController.get()).willSupportOneShotForUnlock();
        OplusLockIconView$.ExternalSyntheticOutline0.m("isFlingEndKeepNpvForOneshot=", TAG, z);
        return z;
    }

    public final boolean isKeyguardSliderLayoutInMainPager() {
        KeyguardSliderLayout keyguardSliderLayout = this.keyguardSliderLayout;
        if (keyguardSliderLayout != null) {
            return keyguardSliderLayout.mMainPagerSelected;
        }
        return true;
    }

    public boolean isMoveNeedStartExpand(boolean z, MotionEvent motionEvent) {
        NotificationPanelViewController base = getBase();
        if (base == null) {
            return super.isMoveNeedStartExpand(z, motionEvent);
        }
        if (base.isTracking() || !z || base.isKeyguardShowing()) {
            return false;
        }
        if (!NotificationPanelViewController.isTwoFingerSwipeTrackpadEvent(motionEvent)) {
            return true;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            return base.isOnKeyguard() || base.getBarState() == 0;
        }
        return false;
    }

    public boolean isQSStatus(QuickSettingsController quickSettingsController) {
        return quickSettingsController.getExpanded();
    }

    public boolean isSlideBackToKgOnNotifiCenter(boolean z, float f, float f2, boolean z2, QuickSettingsControllerImpl quickSettingsControllerImpl, SysuiStatusBarStateController sysuiStatusBarStateController) {
        NotificationPanelViewController base;
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx;
        NotificationPanelViewController base2;
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx2;
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx3;
        boolean z3 = !z && f == 0.0f;
        boolean z4 = z && f > 0.0f;
        if (((KeyguardStateController) this.keyguardStateController.get()).mShowing && (base = getBase()) != null && (oplusLockscreenShadeTransitionControllerEx = base.mLockscreenShadeTransitionControllerEx) != null && oplusLockscreenShadeTransitionControllerEx.isOnLockscreenNtfCenter() && ((z3 || z4) && ((base2 = getBase()) == null || (oplusLockscreenShadeTransitionControllerEx3 = base2.mLockscreenShadeTransitionControllerEx) == null || !oplusLockscreenShadeTransitionControllerEx3.isAnimRunning()))) {
            boolean z5 = f2 < 0.0f || (z2 && f2 == 0.0f && (quickSettingsControllerImpl == null || !quickSettingsControllerImpl.getExpanded()));
            boolean z6 = f2 > 0.0f || (z2 && f2 == 0.0f && (quickSettingsControllerImpl == null || !quickSettingsControllerImpl.getExpanded()));
            NotificationPanelViewController base3 = getBase();
            boolean z7 = (base3 == null || (oplusLockscreenShadeTransitionControllerEx2 = base3.mLockscreenShadeTransitionControllerEx) == null || !oplusLockscreenShadeTransitionControllerEx2.backToKgFirstWhenTouchOnKgNtfCenter()) ? false : true;
            if (z5 || (z6 && z7)) {
                String str = z5 ? "up" : "down";
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Bundler$.ExternalSyntheticOutline0.m("When sliding ", str, " quickly in shadeLocked state, switch to KEYGUARD state"), null, 8);
                NotificationPanelViewController base4 = getBase();
                if (base4 != null) {
                    base4.positionClockAndNotifications(false);
                }
                ILockScreenExtStateController.gotoKeyguardState$default((ILockScreenExtStateController) DependencyEx.sDependency.getDependency(ILockScreenExtStateController.class), "NPVC-slide-".concat(str), 0.0f, 8);
                return true;
            }
        }
        return false;
    }

    public final boolean isTrackingChildBannerWithSlidingUpBlocking() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion() && ((NotificationPanelViewControllerEx) this).isTrackingHeadsUpGroupBehaviorChild;
    }

    public final boolean isTrackingStartedZoomAnimation() {
        return this.isTrackingStartedZoomAnimation;
    }

    public boolean isTwoFingerSwipeTrackpadEvent(MotionEvent motionEvent) {
        return motionEvent.getSource() == 8194 && motionEvent.getToolType(0) == 1 && motionEvent.getClassification() == 3;
    }

    public final boolean isUpdateBottomSpacing(boolean z, boolean z2, boolean z3, boolean z4) {
        return (z || z2 || z3 || z4) ? false : true;
    }

    public final void loadDimens() {
        Resources resources = this.context.getResources();
        resources.getDimension(2131168601);
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, this.context, false, 6);
        if (contextUpdateDisplayConfiguration$default == null) {
            contextUpdateDisplayConfiguration$default = this.context;
        }
        this.keyguardNotificationMinBottomPadding = contextUpdateDisplayConfiguration$default.getResources().getDimension(2131168638);
        this.clearAllController.updateResource(resources);
    }

    public void notifyClosingChanged(NotificationStackScrollLayoutController notificationStackScrollLayoutController, boolean z) {
        if (z) {
            NotificationPopupMenuController notificationPopupMenuController = (NotificationPopupMenuController) this.notificationPopupMenuController.get();
            if (notificationPopupMenuController.isPopupMenuShowing()) {
                NotificationPopupMenuController.dismissPopupMenu$default(notificationPopupMenuController, "notifyClosingChanged", false, 4);
            }
        }
        if (z || !this.panelAnimationEx.getUseLayeredAnimation()) {
            return;
        }
        notificationStackScrollLayoutController.mView.requestLayout();
    }

    public final void onActionPointerUpdate(float f, float f2) {
        float f3 = this.multiplePointerInitialY;
        if (f3 != 0.0f) {
            f2 = f3;
        }
        this.multiplePointerInitialY = (f - this.lastY) + f2;
        this.lastY = f;
    }

    public final void onClockBottomChanged() {
        NotificationStackScrollLayout view = this.notificationStackScrollLayoutController.getView();
        if (view != null) {
            view.mAnimateNextTopPaddingChange = true;
        }
        NotificationPanelViewController base = getBase();
        if (base != null) {
            base.requestScrollerTopPaddingUpdate();
        }
    }

    public void onConfigChanged(Configuration configuration) {
        this.thresholdForElementAppearance = this.context.getResources().getDimension(2131173668);
    }

    public final void onDistanceClear() {
        this.lastY = 0.0f;
        this.multiplePointerInitialY = 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:105:0x0178  */
    /* JADX WARN: Code duplicated, block: B:110:0x0185  */
    /* JADX WARN: Code duplicated, block: B:112:0x018b  */
    /* JADX WARN: Code duplicated, block: B:42:0x008b  */
    /* JADX WARN: Code duplicated, block: B:83:0x012d  */
    /* JADX WARN: Code duplicated, block: B:90:0x0144  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
    
        if (r2.onEmptySpaceClick(r19, r20) == true) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
    
        if (r2.onEmptySpaceClick(r19, r20) == true) goto L118;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onEmptySpaceClick(float r19, float r20, int r21, com.android.systemui.shade.QuickSettingsController r22, float r23) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.shade.NotificationPanelViewControllerExImp.onEmptySpaceClick(float, float, int, com.android.systemui.shade.QuickSettingsController, float):boolean");
    }

    public final void onExpandFractionNoAnimAfter() {
        this.sysUiStateControllerImpl.onExpandFractionNoAnimAfter();
    }

    public final void onExpandFractionNoAnimBefore() {
        this.sysUiStateControllerImpl.onExpandFractionNoAnimBefore();
    }

    public void onExpandingFinished(float f, QuickSettingsController quickSettingsController, boolean z, String str) {
        onHeightUpdatedExt(f, quickSettingsController, z);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onExpandingFinished cause: ", str, "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
        }
        if (!Intrinsics.areEqual(str, "endMotionEvent 3") && !Intrinsics.areEqual(str, REASON_TOUCH_AND_ANIMATION_DISABLED)) {
            Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.DISPATCHER_BACKGROUND_EXECUTOR);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            anonymousClass2.$qs = quickSettingsController;
            VarHandle.storeStoreFence();
            executor.execute(anonymousClass2);
        }
        setTrackingHeadsUpGroupBehaviorChild(false);
        if (this.mHeadsUpSwipeUp) {
            setHeadsUpSwipeUp(false);
            this.ambientState.getExt().setHeadTrackingHeight(0.0f);
            return;
        }
        NotificationPanelViewController base = getBase();
        if (base == null || !base.mHeadsUpManager.hasPinnedHeadsUp()) {
            this.ambientState.getExt().setHeadTrackingHeight(0.0f);
        }
    }

    public final void onExpandingFinishedStatic(int i, boolean z, boolean z2) {
        String screenOrientation;
        String screenOrientation2 = SeparateQSStatisicUtil.VALUE_KEYGUARD;
        if (z) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Context context = this.context;
            if (i == 1) {
                Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
                screenOrientation = SeparateQSStatisicUtil.VALUE_KEYGUARD;
            } else {
                screenOrientation = NotificationStatisticUtil.getScreenOrientation(context);
            }
            ThreadManager threadManager = ThreadManager.getInstance();
            NotificationStatisticUtil$$ExternalSyntheticLambda4 notificationStatisticUtil$$ExternalSyntheticLambda4 = new NotificationStatisticUtil$$ExternalSyntheticLambda4();
            notificationStatisticUtil$$ExternalSyntheticLambda4.f$0 = "slide_down";
            notificationStatisticUtil$$ExternalSyntheticLambda4.f$1 = screenOrientation;
            notificationStatisticUtil$$ExternalSyntheticLambda4.f$2 = jCurrentTimeMillis;
            notificationStatisticUtil$$ExternalSyntheticLambda4.f$3 = context;
            VarHandle.storeStoreFence();
            threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda4);
            if (i != 1) {
                this.panelStartExpandTime = jCurrentTimeMillis;
            }
        }
        if (z2) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Context context2 = this.context;
            if (i == 1) {
                Lock lock2 = NotificationStatisticUtil.NOTIFICATION_LOCK;
            } else {
                screenOrientation2 = NotificationStatisticUtil.getScreenOrientation(context2);
            }
            ThreadManager threadManager2 = ThreadManager.getInstance();
            NotificationStatisticUtil$$ExternalSyntheticLambda4 notificationStatisticUtil$$ExternalSyntheticLambda5 = new NotificationStatisticUtil$$ExternalSyntheticLambda4();
            notificationStatisticUtil$$ExternalSyntheticLambda5.f$0 = "slide_up";
            notificationStatisticUtil$$ExternalSyntheticLambda5.f$1 = screenOrientation2;
            notificationStatisticUtil$$ExternalSyntheticLambda5.f$2 = jCurrentTimeMillis2;
            notificationStatisticUtil$$ExternalSyntheticLambda5.f$3 = context2;
            VarHandle.storeStoreFence();
            threadManager2.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda5);
            if (i != 1) {
                long j = this.panelStartExpandTime;
                if (j != 0) {
                    long j2 = jCurrentTimeMillis2 - j;
                    this.panelStartExpandTime = 0L;
                    int allNotificationsCountValue = this.activeNotificationsInteractor.getAllNotificationsCountValue();
                    String str = DeviceStateController.getInstance().isFoldingState$1() ? "1" : "0";
                    Context context3 = this.context;
                    ThreadManager threadManager3 = ThreadManager.getInstance();
                    NotificationStatisticUtil$$ExternalSyntheticLambda5 notificationStatisticUtil$$ExternalSyntheticLambda6 = new NotificationStatisticUtil$$ExternalSyntheticLambda5();
                    notificationStatisticUtil$$ExternalSyntheticLambda6.f$0 = allNotificationsCountValue;
                    notificationStatisticUtil$$ExternalSyntheticLambda6.f$1 = str;
                    notificationStatisticUtil$$ExternalSyntheticLambda6.f$2 = j2;
                    notificationStatisticUtil$$ExternalSyntheticLambda6.f$3 = context3;
                    VarHandle.storeStoreFence();
                    threadManager3.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda6);
                }
            }
        }
    }

    public DisposableHandles onFinishInflateExt(ViewGroup viewGroup) {
        DisposableHandles disposableHandles = new DisposableHandles();
        ThemeReceiver.Companion companion = ThemeReceiver.Companion;
        companion.getInstance().removeListener(this.themeReceiverListener);
        companion.getInstance().addListener(this.themeReceiverListener);
        disposableHandles.plusAssign(this.clearAllController.bindViews(viewGroup, this.notificationStackScrollLayoutController.getView()));
        disposableHandles.plusAssign(this.closeController.bindView(viewGroup));
        return disposableHandles;
    }

    public void onHeightUpdatedExt(float f, QuickSettingsController quickSettingsController, boolean z) {
        NotificationPanelViewController base = getBase();
        if (base != null) {
            if (quickSettingsController != null) {
                float minExpansionHeight = z ? quickSettingsController.getMinExpansionHeight() : quickSettingsController.getMinExpansionHeight();
                float maxPanelTransitionDistance = base.getMaxPanelTransitionDistance();
                this.clearAllController.onHeightUpdated(this.panelAnimationEx.getPanelExpandFraction().getRawFraction() * maxPanelTransitionDistance, maxPanelTransitionDistance, minExpansionHeight, base.mExpandingFromHeadsUp, z);
            }
            if (((KeyguardStateController) this.keyguardStateController.get()).mShowing && ((OneShotAnimController) this.oneShotAnimController.get()).isOneShotEnabled()) {
                this.oplusKgdSwipeUpTouchProcessTracker.setMaxDistance(ScreenUtils.getScreenHeight(this.context));
            }
        }
    }

    public final void onNotificationExpectedHeightChanged(int i) {
        ((KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get()).onClockExpectedHeightChange(i, getKeyguardNotificationBottomPadding());
    }

    public void onOneshotTouchEvent(MotionEvent motionEvent) {
        if (((OneShotAnimController) this.oneShotAnimController.get()).isOneShotEnabled()) {
            if (motionEvent.getActionMasked() != 0) {
                if (motionEvent.getActionMasked() == 2) {
                    this.oplusKgdSwipeUpTouchProcessTracker.onTouchEvent(motionEvent, false);
                }
            } else if (((KeyguardStateController) this.keyguardStateController.get()).mShowing) {
                this.oplusKgdSwipeUpTouchProcessTracker.onTouchEvent(motionEvent, false);
                this.isOneshotHandleTracking = true;
                KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl = (KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get();
                keyguardStyleClockControllerImpl.getClass();
                if (KeyguardStyleClockControllerImpl.isOrbuculumClockEnabled()) {
                    KeyguardStyleClockStateController.Companion.getInstance().notifyLiveClockUnlockProgress(0.0f);
                }
                keyguardStyleClockControllerImpl.setOneshotHandleTouchDown(motionEvent);
            }
        }
    }

    public final void onPanelStateChanged(int i) {
        NotificationPanelViewController base;
        NotificationPanelViewController base2;
        ViewGroup view;
        if (((KeyguardStateController) this.keyguardStateController.get()).mShowing || (base = getBase()) == null || base.getBarState() != 0) {
            return;
        }
        if ((i != 1 && i != 2) || KeyguardRapidUnlockUtils.Companion.getKeyguardComposed() || (base2 = getBase()) == null || (view = base2.getView()) == null) {
            return;
        }
        view.postOnAnimation(onPanelStateChanged.1.INSTANCE);
    }

    public final void onQsClosingBegin() {
        QSFragmentHelper.getInstance().onQsClosingBegin();
    }

    public final void onSetTouchExclusionRegionTrue() {
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new C02841(null), 3);
    }

    public final void onTrackingStarted() {
        NotificationPanelViewController base;
        ViewGroup view;
        ViewGroup view2;
        View rootView;
        this.isNeedInterceptZoom = true;
        int i = 0;
        setTrackingStartedZoomAnimation(false);
        NotificationPanelViewController base2 = getBase();
        if (base2 != null && (view2 = base2.getView()) != null && (rootView = view2.getRootView()) != null) {
            NotificationShadeRegionHelper.setTouchExclusionRegion(false, rootView, "onTrackingStarted");
        }
        if (KeyguardRapidUnlockUtils.Companion.getKeyguardComposed() || (base = getBase()) == null || (view = base.getView()) == null) {
            return;
        }
        NotificationPanelViewControllerExImp$onTrackingStarted$2$1 notificationPanelViewControllerExImp$onTrackingStarted$2$1 = new NotificationPanelViewControllerExImp$onTrackingStarted$2$1(i);
        notificationPanelViewControllerExImp$onTrackingStarted$2$1.$this_run = view;
        VarHandle.storeStoreFence();
        view.post(notificationPanelViewControllerExImp$onTrackingStarted$2$1);
    }

    public final void onTrackingStopped() {
        setTrackingStartedZoomAnimation(false);
    }

    public final void onUpdateResourcesEnd() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isLargeScreenFolding() && ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mKeyguardShowing && SmoothTransitionController.Companion.isPanoramicAodEnable(this.context) && !((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).mDeviceInteractive) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "NotificationPanelViewControllerExImp-->is screened off, and refresh anim state");
            }
            Choreographer choreographer = Choreographer.getInstance();
            onUpdateResourcesEnd.2 r1 = new onUpdateResourcesEnd.2();
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            choreographer.postFrameCallback(r1);
        }
    }

    public void oneshotEndMotionEvent(MotionEvent motionEvent, boolean z, float f, String str) {
        KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl = (KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get();
        keyguardStyleClockControllerImpl.getClass();
        if (KeyguardStyleClockControllerImpl.isOrbuculumClockEnabled() && ((KeyguardStateController) this.keyguardStateController.get()).mCanDismissLockScreen && !z) {
            KeyguardStyleClockStateController.Companion.getInstance().notifyLiveClockUnlockTouchUp(f);
        }
        if (((OneShotAnimController) this.oneShotAnimController.get()).isOneShotEnabled()) {
            this.isOneshotHandleTracking = false;
            this.oplusKgdSwipeUpTouchProcessTracker.onTouchEvent(motionEvent, z);
            keyguardStyleClockControllerImpl.setOneshotHandleTouchUp(z, f, str);
        }
    }

    public final void postRequestLayoutRunnable() {
        if (this.mainHandler.hasCallbacks(this.requestLayoutRunnable)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "NotificationPanelViewControllerExImp-->run requestLayout");
        }
        this.mainHandler.post(this.requestLayoutRunnable);
    }

    public final void postRequestPanelHeightUpdateAction() {
        ViewGroup view;
        Handler handler;
        Handler handler2;
        Runnable runnable = this.requestPanelHeightUpdateAction;
        Runnable runnable2 = runnable;
        if (runnable == null) {
            NotificationPanelViewControllerExImp$onTrackingStarted$2$1 notificationPanelViewControllerExImp$onTrackingStarted$2$1 = new NotificationPanelViewControllerExImp$onTrackingStarted$2$1(3);
            notificationPanelViewControllerExImp$onTrackingStarted$2$1.$this_run = this;
            VarHandle.storeStoreFence();
            this.requestPanelHeightUpdateAction = notificationPanelViewControllerExImp$onTrackingStarted$2$1;
            runnable2 = notificationPanelViewControllerExImp$onTrackingStarted$2$1;
        }
        NotificationPanelViewController base = getBase();
        if (base == null || (view = base.getView()) == null || (handler = view.getHandler()) == null) {
            return;
        }
        if (handler.hasCallbacks(runnable2)) {
            handler2 = handler;
            handler2 = null;
        }
        if (handler2 != null) {
            handler2.post(runnable2);
        }
    }

    public final void refreshMediaPosition() {
        ((KeyguardMediaController) this.keyguardMediaController.get()).refreshMediaPosition("npvc StatusBarState.onStateChanged");
    }

    public final void removeRequestLayoutRunnable() {
        this.mainHandler.removeCallbacks(this.requestLayoutRunnable);
    }

    public final void requestPositionClockAndNotifications() {
        NotificationPanelViewController base = getBase();
        if (base != null) {
            base.positionClockAndNotifications(false);
        }
    }

    public final void resetHeadsUpAnimationStatus() {
        NotificationStackScrollLayout view;
        if (NotificationExtFlag.OPLUS_HEADSUP || (view = this.notificationStackScrollLayoutController.getView()) == null) {
            return;
        }
        view.clearHeadsUpDisappearRunning();
    }

    public boolean resetNoticesClick(NotificationStackScrollLayoutController notificationStackScrollLayoutController, MotionEvent motionEvent, int i, boolean z) {
        if (motionEvent.getAction() != 0) {
            return z;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i);
        if (iFindPointerIndex < 0) {
            iFindPointerIndex = 0;
        }
        ExpandableView childAtRawPosition = notificationStackScrollLayoutController.mView.getChildAtRawPosition(motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex));
        return childAtRawPosition != null && ((childAtRawPosition instanceof ExpandableNotificationRow) || (childAtRawPosition instanceof OplusCustomRow) || (childAtRawPosition instanceof MediaContainerView));
    }

    public void resetOneshotHandleState(String str) {
        if (this.isOneshotHandleTracking) {
            this.isOneshotHandleTracking = false;
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("resetOneshotHandleState, reason=", str, TAG);
            this.oplusKgdSwipeUpTouchProcessTracker.reset();
            KeyguardStyleClockControllerImpl keyguardStyleClockControllerImpl = (KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get();
            keyguardStyleClockControllerImpl.setPanelExpandFraction(0.0f);
            keyguardStyleClockControllerImpl.setOneshotHandleTouchUp(true, 0.0f, str);
            if (KeyguardStyleClockControllerImpl.isOrbuculumClockEnabled()) {
                KeyguardStyleClockStateController.Companion.getInstance().notifyLiveClockUnlockProgress(0.0f);
            }
        }
    }

    public final void resetTouchState() {
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.overScrollAnimationEx.resetTouchState();
            return;
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        if (qSFragmentHelper.mIsBeingDragged) {
            qSFragmentHelper.resetSpringBack();
            qSFragmentHelper.resetTouch();
        }
    }

    public boolean resetWidgetClick(MotionEvent motionEvent) {
        return ((KeyguardStyleWidgetUIControllerImpl) this.keyguardStyleWidgetUIController.get()).isTouchInAnyWidget(motionEvent);
    }

    public final void resetZoomAnimationParams() {
        setHeadsUpSwipeUp(false);
        setStartedZoomAnimation(false);
        this.isNeedInterceptZoom = false;
        this.dragExpandableNotificationRow = null;
    }

    public final void resetZoomScalex(float f) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2;
        RowAnimationStateEx rowAnimationStateEx;
        ExpandableNotificationRow expandableNotificationRow = this.dragExpandableNotificationRow;
        if (expandableNotificationRow != null && (rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableNotificationRow)) != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.invoke(f));
        }
        ExpandableNotificationRow expandableNotificationRow2 = this.dragExpandableNotificationRow;
        if (expandableNotificationRow2 != null) {
            expandableNotificationRow2.setScaleX(f);
        }
        ExpandableNotificationRow expandableNotificationRow3 = this.dragExpandableNotificationRow;
        if (f == 1.0f) {
            if (expandableNotificationRow3 == null || (oplusExpandableNotificationRowEx2 = expandableNotificationRow3.mRowEx) == null) {
                return;
            }
            oplusExpandableNotificationRowEx2.setIsZoomBackUpHeadsUp(false);
            return;
        }
        if (expandableNotificationRow3 == null || (oplusExpandableNotificationRowEx = expandableNotificationRow3.mRowEx) == null) {
            return;
        }
        oplusExpandableNotificationRowEx.setIsZoomBackUpHeadsUp(true);
    }

    public void revertRowClickAnim(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        notificationStackScrollLayoutController.getExt().revertRowClickAnim(notificationStackScrollLayoutController);
    }

    public void setFlingToHeightInterpolatorIfNeeded(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            valueAnimator.setInterpolator(EASE_OUT_SINE);
        }
    }

    public final boolean setFps(int i, int i2, String str) {
        try {
            if (this.mSurfaceFlinger == null) {
                this.mSurfaceFlinger = ServiceManager.getService("SurfaceFlinger");
            }
            Trace.beginSection("setFps fps=" + i + " pkg=" + str);
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
            parcelObtain.writeInt(1);
            parcelObtain.writeInt(i);
            parcelObtain.writeString(str);
            parcelObtain.writeInt(0);
            parcelObtain.writeString("panel_expened");
            parcelObtain.writeInt(0);
            parcelObtain.writeInt(i2);
            parcelObtain.writeInt(0);
            IBinder iBinder = this.mSurfaceFlinger;
            if (iBinder == null) {
                iBinder = null;
            }
            boolean zTransact = iBinder.transact(22017, parcelObtain, null, 0);
            parcelObtain.recycle();
            Trace.endSection();
            return zTransact;
        } catch (RemoteException e) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "setFps test error: " + e);
            return false;
        }
    }

    public void setHeadsUpSwipeUp(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setHeadsUpSwipeUp swipeUp: ", "NotificationPanelViewControllerExImp-->", "SystemUi--Notification", z);
        }
        this.mHeadsUpSwipeUp = z;
    }

    public void setKeyguardNotificationBottomPadding(int i) {
        if (this.keyguardNotificationBottomPadding != i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("oldBottomPadding = ", this.keyguardNotificationBottomPadding, i, ", newValue = ", "NotificationPanelViewControllerExImp-->"));
            }
            this.keyguardNotificationBottomPadding = i;
            NotificationStackScrollLayout notificationStackScrollLayout = this.notificationStackScrollLayoutController.mView;
            if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
                notificationStackScrollLayout.mEverythingNeedsAnimation = true;
                notificationStackScrollLayout.mNeedsAnimation = true;
                notificationStackScrollLayout.requestChildrenUpdate$1();
            }
        }
    }

    public void setKeyguardStyleClockVisibility(int i, boolean z, String str) {
        KeyguardStyleClockControllerImpl.setKeyguardStyleClockVisibility$default((KeyguardStyleClockControllerImpl) this.keyguardStyleClockController.get(), i, z, str, 8);
    }

    public void setNotificationsConstraints(ConstraintSet constraintSet, boolean z, int i, int i2, boolean z2) {
        int i3 = z ? this.notificationScrimMarginEndWithQuickSettings + this.horizontalMarginStackLayout : this.horizontalMarginStackLayout;
        constraintSet.connect(2131365190, 6, z ? 2131364742 : 0, 6);
        constraintSet.getConstraint(2131365190).layout.mWidth = z ? 0 : this.panelWidth;
        constraintSet.setMargin(2131365190, 6, this.horizontalMarginStackLayout);
        constraintSet.setMargin(2131365190, 7, i3);
        constraintSet.setMargin(2131365190, 3, 0);
        constraintSet.setMargin(2131365190, 4, 0);
        ((SharedNotificationContainer) this.sharedNotificationContainer.get()).updateConstraints(SharedNotificationContainerViewModel.HorizontalPosition.EdgeToEdge.INSTANCE, 0, i2, 0, calculateStackScrollBottomMargin(this.marginBottom, z2), this.notificationStackScrollLayoutController.getAlpha(), true);
        this.notificationStackScrollLayoutController.setOverExpansion(0.0f);
        this.notificationStackScrollLayoutController.setOverScrollAmount(0);
        if (z) {
            constraintSet.setMargin(2131365320, 7, (i3 - this.context.getResources().getDimensionPixelSize(2131172051)) - this.context.getResources().getDimensionPixelSize(2131172239));
        }
    }

    public void setOnEmptySpaceClickListener(NotificationStackScrollLayout.OnEmptySpaceClickListener onEmptySpaceClickListener) {
        KeyguardSliderLayout keyguardSliderLayout = this.keyguardSliderLayout;
        if (keyguardSliderLayout != null) {
            keyguardSliderLayout.setOnEmptySpaceClickListener(onEmptySpaceClickListener);
        }
    }

    public void setPreY(float f) {
        this.mPreY = f;
    }

    public void setQsMargin(ConstraintSet constraintSet, int i, boolean z, int i2) {
        Resources resources = this.context.getResources();
        this.panelWidth = resources.getDimensionPixelSize(2131170750);
        this.paddingBetweenNotifAndScrim = resources.getDimensionPixelSize(2131170783);
        this.horizontalMarginStackLayout = resources.getDimensionPixelSize(2131170615);
        this.quickSettingsFrameMarginStart = resources.getDimensionPixelSize(2131172247);
        this.notificationScrimMarginEndWithQuickSettings = resources.getDimensionPixelSize(2131170773);
        this.marginBottom = resources.getDimensionPixelSize(2131170745);
        resources.getDimensionPixelSize(2131170774);
        if (z) {
            i2 = this.quickSettingsFrameMarginStart;
        }
        constraintSet.setMargin(i, 6, i2);
    }

    public final void setScaleForZoom(ExpandableNotificationRow expandableNotificationRow, float f, float f2) {
        RowAnimationStateEx rowAnimationStateEx;
        ExpandableNotificationRow expandableNotificationRow2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ImageView dragView;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2;
        if (this.dragExpandableNotificationRow == null) {
            if (expandableNotificationRow == null) {
                NotificationEntry topEntry = this.headsUpManager.getTopEntry();
                expandableNotificationRow = topEntry != null ? topEntry.getRow() : null;
            }
            this.dragExpandableNotificationRow = expandableNotificationRow;
        }
        ExpandableNotificationRow expandableNotificationRow3 = this.dragExpandableNotificationRow;
        if (((expandableNotificationRow3 == null || (oplusExpandableNotificationRowEx2 = expandableNotificationRow3.mRowEx) == null) ? null : oplusExpandableNotificationRowEx2.getDragView()) == null || (expandableNotificationRow2 = this.dragExpandableNotificationRow) == null || (oplusExpandableNotificationRowEx = expandableNotificationRow2.mRowEx) == null || (dragView = oplusExpandableNotificationRowEx.getDragView()) == null || dragView.getVisibility() != 0) {
            ExpandableNotificationRow expandableNotificationRow4 = this.dragExpandableNotificationRow;
            if (expandableNotificationRow4 == null) {
                return;
            }
            if (Intrinsics.areEqual(expandableNotificationRow4 != null ? Float.valueOf(expandableNotificationRow4.getScaleX()) : null, 1.0f)) {
                return;
            }
        }
        int i = this.minScaleHeight;
        if (f > i) {
            float f3 = f2 - i;
            if (f3 > 0.0f) {
                float f4 = ((f - i) * 0.050000012f) / f3;
                resetZoomScalex(1.0f - (0.050000012f > f4 ? f4 : 0.050000012f));
                return;
            }
            return;
        }
        ExpandableNotificationRow trackedHeadsUpRow = this.ambientState.getTrackedHeadsUpRow();
        if (trackedHeadsUpRow != null) {
            trackedHeadsUpRow.setScaleX(1.0f);
        }
        ExpandableNotificationRow trackedHeadsUpRow2 = this.ambientState.getTrackedHeadsUpRow();
        if (trackedHeadsUpRow2 != null && (rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(trackedHeadsUpRow2)) != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.getINVALID());
        }
        resetZoomScalex(1.0f);
    }

    public final void setSeedlingPluginManager(Lazy lazy) {
        this.seedlingPluginManager = lazy;
    }

    public void setStatusBarEventDownTime(Long l) {
        if (Intrinsics.areEqual(this.statusBarEventDownTime, l)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "set statusBarEventDownTime to: " + l, null, 8);
        this.statusBarEventDownTime = l;
    }

    public void setTouchExclusionRegion(boolean z, View view, String str) {
        NotificationShadeRegionHelper.setTouchExclusionRegion(z && !((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue(), view, str);
    }

    public void setTrackingStartedZoomAnimation(boolean z) {
        this.isTrackingStartedZoomAnimation = z;
    }

    public void setTryToFastUnlockForSlideUpRunnable(Runnable runnable) {
        this.mTryToFastUnlockForSlideUpRunnable = runnable;
    }

    public boolean shouldSendCancelEvent(ViewGroup viewGroup, MotionEvent motionEvent) {
        boolean z = false;
        if (((KeyguardStateController) this.keyguardStateController.get()).mShowing && motionEvent.getAction() != 3 && motionEvent.getAction() != 1 && GlobalUtils.getAndResetInterceptShadeTouchEvent() && GlobalUtils.hasDialogShowing() && !GlobalUtils.sIsMediaOutputFromOutside) {
            z = true;
        }
        if (z) {
            GlobalUtils.sendCancelMotionEvent(viewGroup, "onInterceptTouchEvent");
        }
        return z;
    }

    public final boolean shouldShowIconsWhenExpanded() {
        NotificationPanelViewController base = getBase();
        return base != null && base.getBarState() == 0;
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
    public final void showDeviceMonitoringDialog() {
        OplusQSBottomImpl qSBottom = QSFragmentHelper.getInstance().getQSBottom();
        if (qSBottom != null) {
            AppCompatTextView appCompatTextView = qSBottom.mOplusQSSecurityView;
            appCompatTextView.onClick(appCompatTextView);
        }
    }

    public final void tryToFastUnlockForSlideUp(float f, boolean z) {
        Runnable runnable;
        if (this.mTryToFastUnlockForSlideUpRunnable != null) {
            boolean z2 = !z && f == 0.0f;
            KeyguardViewMediator keyguardViewMediator = (KeyguardViewMediator) this.keyguardViewMediator.get();
            if (z2 && keyguardViewMediator.isShowingAndNotOccluded() && (runnable = this.mTryToFastUnlockForSlideUpRunnable) != null) {
                runnable.run();
            }
        }
    }

    public void unPinEntryWhenTrackingStart(HeadsUpManager headsUpManager, boolean z, boolean z2, HeadsUpTouchHelper headsUpTouchHelper) {
        if (headsUpManager.hasPinnedHeadsUp() && z && !z2 && !headsUpTouchHelper.mTouchingHeadsUpView) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp--> unpinAll because panel is expand");
            }
            headsUpManager.setHeadsUpGoingAwayAnimationsAllowed(false);
            headsUpManager.unpinAll(true, "NPVCEI.unPinEntryWhenTrackingStart");
            headsUpManager.setHeadsUpGoingAwayAnimationsAllowed(true);
        }
        FullScreenBannerHelper fullScreenBannerHelper = this.fullScreenBannerHelper;
        if (!fullScreenBannerHelper.mIsBannerShowing || fullScreenBannerHelper.mFullScreenBanner == null) {
            return;
        }
        fullScreenBannerHelper.executeBannerCollpseAnim(false, false, new Runnable[0]);
    }

    public final void unlockAnimationFinished() {
        KeyguardVFXController companion = KeyguardVFXController.Companion.getInstance(this.context);
        if (companion.mSOAVFXEnable) {
            return;
        }
        companion.mSOAVFXEnable = true;
    }

    public void unpinAllHeadsUp(HeadsUpManager headsUpManager) {
        if (headsUpManager.hasPinnedHeadsUp()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp-->expandToNotifications -> unpinAllHeadsUp");
            }
            headsUpManager.setHeadsUpGoingAwayAnimationsAllowed(false);
            headsUpManager.unpinAll(false, "NPVCEI.unpinAllHeadsUp");
            headsUpManager.setHeadsUpGoingAwayAnimationsAllowed(true);
        }
    }

    public final void updateClearAllButtonLayoutParams(int i) {
        this.clearAllController.updateLayoutParams(i);
    }

    public final void updateDirection() {
        ViewGroup view;
        ViewGroup view2;
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        NotificationPanelViewController base = getBase();
        if (base == null || (view2 = base.getView()) == null || view2.getLayoutDirection() != layoutDirectionFromLocale) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(layoutDirectionFromLocale, "localDirection=", "NotificationPanelViewControllerExImp-->", "SystemUi--Notification");
            }
            NotificationPanelViewController base2 = getBase();
            if (base2 == null || (view = base2.getView()) == null) {
                return;
            }
            view.setLayoutDirection(layoutDirectionFromLocale);
        }
    }

    public boolean updateIsBeingDraggedOnTouchDown(MotionEvent motionEvent) {
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            return this.overScrollAnimationEx.updateIsBeingDraggedOnTouchDown(motionEvent);
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        qSFragmentHelper.getClass();
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            qSFragmentHelper.resetTouch();
            return false;
        }
        if (action == 0) {
            qSFragmentHelper.mLastMotionX = motionEvent.getX();
            float rawY = motionEvent.getRawY();
            qSFragmentHelper.mLastMotionY = rawY;
            qSFragmentHelper.mInitialMotionY = rawY;
            qSFragmentHelper.mActivePointerId = motionEvent.getPointerId(0);
            qSFragmentHelper.mLastTurnMotionY = 0.0f;
            if (qSFragmentHelper.mScrollMode == 1 && qSFragmentHelper.isOverTouchSlop()) {
                LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", "handleQsTouch: springOverScroller abort");
                qSFragmentHelper.abortAnimation();
                qSFragmentHelper.mScrollMode = -1;
                qSFragmentHelper.mIsBeingDragged = true;
            } else {
                qSFragmentHelper.mIsBeingDragged = false;
            }
        }
        return qSFragmentHelper.mIsBeingDragged;
    }

    public final void updateLockIconAlphaAndScale() {
        ILockScreenExtStateController iLockScreenExtStateController;
        NotificationPanelViewController base = getBase();
        if (base != null) {
            boolean z = ((KeyguardStateController) this.keyguardStateController.get()).mCanDismissLockScreen;
            boolean z2 = ((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway;
            boolean zIsBouncerShowing = ((PrimaryBouncerInteractor) this.primaryBouncerInteractor.get()).isBouncerShowing();
            NotificationPanelViewController base2 = getBase();
            boolean z3 = false;
            if (base2 != null && (iLockScreenExtStateController = base2.mLockScreenExtStateController) != null && iLockScreenExtStateController.isLockScreenNtfCenterState()) {
                z3 = true;
            }
            if ((!z && !z2) || zIsBouncerShowing || z3) {
                ((OplusLockIconViewControllerExImpl) this.oplusLockIconViewControllerExImpl.get()).setLockIconViewAnimFraction(1.0f, 1.0f);
            } else {
                ((OplusLockIconViewControllerExImpl) this.oplusLockIconViewControllerExImpl.get()).setLockIconViewAnimFraction(MathUtils.saturate(base.getExpandedFraction()), MathUtils.lerp(0.9f, 1.0f, MathUtils.saturate(base.getExpandedFraction())));
            }
        }
    }

    public final void updateLockscreenNotificationBottomPadding() {
        float udfpsIconPadding = getUdfpsIconPadding();
        float kgdNotificationCapsuleBottomPadding = getKgdNotificationCapsuleBottomPadding();
        NotificationPanelViewController base = getBase();
        float fMax = Math.max(udfpsIconPadding, Math.max(kgdNotificationCapsuleBottomPadding, base != null ? base.mAmbientIndicationBottomPadding : 0.0f));
        float keyguardNotificationMinBottomPaddingForOverlay = udfpsIconPadding == 0.0f ? getKeyguardNotificationMinBottomPaddingForOverlay() : this.keyguardNotificationMinBottomPadding;
        if (fMax < keyguardNotificationMinBottomPaddingForOverlay) {
            fMax = keyguardNotificationMinBottomPaddingForOverlay;
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            fMax = Math.max(fMax, getTipsViewController().getKgdTipsBottomInset());
        }
        setKeyguardNotificationBottomPadding((int) fMax);
    }

    public final void updateNotificationScaleLevel() {
        NotificationStackScrollLayout view;
        NotificationPanelViewController base = getBase();
        if (base == null || (view = this.notificationStackScrollLayoutController.getView()) == null) {
            return;
        }
        if (((NotificationStackScrollLayoutExtImpl) view.mExt).isViewScaleAnimationRunning()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPanelViewControllerExImp-->updateNotificationScaleLevel skip, NsslScaleAnimation is running");
                return;
            }
            return;
        }
        view.getScaleX();
        float fLerp = canScaleFadePanelAtExpandFraction(base.getExpandedFraction()) ? MathUtils.lerp(0.9f, 1.0f, computeNotificationScaleFraction()) : 1.0f;
        NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = (NotificationStackScrollLayoutControllerExtImpl) this.notificationStackScrollLayoutControllerExtImpl.get();
        float viewScaleFromPanel = notificationStackScrollLayoutControllerExtImpl.getViewScaleFromPanel();
        BrandUtils.INSTANCE.getClass();
        boolean z = false;
        if (BrandUtils.isRealme && (viewScaleFromPanel != view.getScaleX() || viewScaleFromPanel != view.getScaleY())) {
            z = true;
        }
        if (fLerp != viewScaleFromPanel || z) {
            notificationStackScrollLayoutControllerExtImpl.updateViewScaleFromPanel(fLerp);
        }
    }

    public void updatePanelCloseOrGoingDownIntercept(MotionEvent motionEvent) {
        NotificationPanelViewController base;
        if (!isTwoFingerSwipeTrackpadEvent(motionEvent) || (base = getBase()) == null) {
            return;
        }
        base.mPanelClosedOrGoingCloseOnInterceptDown = true;
    }

    public void updateQsExpansion(QS qs, float f) {
        this.clearAllController.updateQsExpansion(qs, f);
    }

    public final void updateTrackedHeadsUpState(float f, float f2, float f3) {
        CentralSurfaces centralSurfaces;
        NotificationShadeWindowView notificationShadeWindowView;
        float preY = f - getPreY();
        setPreY(f);
        ExpandableNotificationRow trackedHeadsUpRow = this.ambientState.getTrackedHeadsUpRow();
        this.ambientState.setExpandingVelocity(f3);
        if (trackedHeadsUpRow == null || trackedHeadsUpRow.getViewState() == null) {
            return;
        }
        cancelHeadUpSpringBackAnimator(false);
        NotificationPanelViewController base = getBase();
        float f4 = 0.0f;
        float f5 = base != null ? base.mInitialOffsetOnTouch : 0.0f;
        float maxHeadsUpPullHeight = this.ambientState.getExt().getMaxHeadsUpPullHeight();
        float headTrackingHeight = this.ambientState.getExt().getHeadTrackingHeight();
        ImageView dragView = trackedHeadsUpRow.mRowEx.getDragView();
        boolean z = dragView != null && dragView.getVisibility() == 0;
        if (z && this.isStartedZoomAnimation) {
            return;
        }
        boolean zIsTrackingChildBannerWithSlidingUpBlocking = isTrackingChildBannerWithSlidingUpBlocking();
        if (!zIsTrackingChildBannerWithSlidingUpBlocking || f2 > f5) {
            float f6 = maxHeadsUpPullHeight - headTrackingHeight;
            if (preY > 10.0f && !z) {
                preY = 10.0f;
            }
            if (headTrackingHeight >= 0.0f && !z) {
                preY *= f6 / maxHeadsUpPullHeight;
            }
            float f7 = preY + headTrackingHeight;
            f4 = (!zIsTrackingChildBannerWithSlidingUpBlocking || f7 >= 0.0f) ? f7 : 0.0f;
            if (f4 > maxHeadsUpPullHeight) {
                f4 = maxHeadsUpPullHeight;
            }
        }
        if (f4 != headTrackingHeight) {
            if (z) {
                this.dragExpandableNotificationRow = trackedHeadsUpRow;
                setScaleForZoom(trackedHeadsUpRow, f4, maxHeadsUpPullHeight);
            } else {
                this.dragExpandableNotificationRow = null;
            }
            this.ambientState.getExt().setHeadTrackingHeight(f4);
            this.notificationStackScrollLayoutController.getView().requestChildrenUpdate$1();
        }
        if (z) {
            float headTrackingHeight2 = this.ambientState.getExt().getHeadTrackingHeight();
            float f8 = this.minZoomHeight;
            if (headTrackingHeight2 < f8) {
                setStartedZoomAnimation(false);
                return;
            }
            if (this.isStartedZoomAnimation) {
                return;
            }
            if ((f3 <= 6000.0f || headTrackingHeight2 < f8) && headTrackingHeight2 < maxHeadsUpPullHeight) {
                return;
            }
            this.isNeedInterceptZoom = false;
            boolean zOnStartFloatWindow = this.notificationStackScrollLayoutController.getExt().onStartFloatWindow(trackedHeadsUpRow, f3);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("NotificationPanelViewControllerExImp-->", "SystemUi--Notification", MonoSpline$.ExternalSyntheticOutline0.m("startZoomAnimator,velocityTrackerY:", f3, ",curPullHeight:", headTrackingHeight2, " "), zOnStartFloatWindow);
            }
            if (!zOnStartFloatWindow) {
                setStartedZoomAnimation(false);
                return;
            }
            cancelHeadUpSpringBackAnimator(false);
            OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = trackedHeadsUpRow.mRowEx;
            if (oplusExpandableNotificationRowEx != null) {
                oplusExpandableNotificationRowEx.setIsZoomBackUpHeadsUp(false);
            }
            setStartedZoomAnimation(true);
            this.isNeedInterceptZoom = false;
            setHeadsUpSwipeUp(false);
            NotificationPanelViewController base2 = getBase();
            if (base2 == null || (centralSurfaces = base2.mCentralSurfaces) == null || (notificationShadeWindowView = centralSurfaces.getNotificationShadeWindowView()) == null) {
                return;
            }
            NotificationUtil.sendCancelMotionEvent(notificationShadeWindowView, "startZoomAnimator");
        }
    }

    public final boolean usingSysUiCustom() {
        ISysUiCustom iSysUiCustom = (ISysUiCustom) DependencyEx.sDependency.getDependency(ISysUiCustom.class);
        return iSysUiCustom != null && iSysUiCustom.usingSysUiCustom();
    }
}
