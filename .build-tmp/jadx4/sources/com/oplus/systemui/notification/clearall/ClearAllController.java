package com.oplus.systemui.notification.clearall;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.insets.Protection$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.SeparateModeHelper;
import com.android.systemui.util.animation.ScreenOffAnimationGuardKt;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.notification.base.OplusNotificationQsInteractor;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.shade.AnimationType;
import com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager;
import com.oplus.systemui.shade.NotificationPanelSeparateAnimationManagerImpl;
import com.oplus.systemui.statusbar.notification.util.ViewStatisticUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.MaskBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.HapticSettingObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClearAllController implements StatusBarStateController.StateListener, ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ClearAllController";
    private final ActiveNotificationsInteractor activeNotificationsInteractor;
    public Handler bgHandler;
    private OplusClearAllButton clearAll;
    public int clearButtonMarginEnd;
    public ConfigurationController configurationController;
    private final Context context;
    public int defaultMarginBottom;
    private DisposableHandle disposableHandle;
    public int distanceToStackScroller;
    private final ClearAllController$elementShowStateChangeListener$1 elementShowStateChangeListener;
    public HeadsUpManager headsUpManagerPhone;
    public int horizontalMarginStackLayout;
    public int iconHeight;
    public int iconWidth;
    public boolean isLandInSeparateMode;
    public boolean isLayoutRtl;
    private Integer lastScreenState;
    private LinearmotorVibrator linearmotorVibrator;
    public boolean mDialogShowing;
    private final Lazy materialColorChangeCallback$delegate;
    private final MaterialColorManager materialColorManager;
    private final dagger.Lazy notificationEffectRepository;
    private final dagger.Lazy notificationMaterialColorEx;
    private final OplusNotificationQsInteractor notificationQsInteractor;
    private final PanelAnimationEx panelAnimationEx;
    private ViewGroup panelView;
    public boolean playingAnimation;
    private Boolean qsFullyExpanded;
    public int qsNotificationPadding;
    public int scrollLayoutWidth;
    public boolean showDismiss;
    public boolean showingCustomizer;
    private OplusClearAllButton showingView;
    public boolean splitShadeEnabled;
    private NotificationStackScrollLayout stackScroller;
    public int stackScrollerSidePadding;
    private Integer statusBarState;
    private final SysuiStatusBarStateController statusBarStateController;
    private final SuperPowerSaveRepository superPowerSaveRepository;
    public float targetAlpha;
    private Vibrator vibrator;
    private final ViewBlurManager viewBlurManager;
    public boolean portrait = true;
    private final Interpolator expansionInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    public float lastExpansion = 1.0f;
    public float lastQsExpansion = 1.0f;
    private final long[] clickPattern = {0, 65};
    public int mLastVisibility = 8;
    public int mCurrentOrientation = 1;
    private final NotificationPanelSeparateAnimationManager clearAllAnimationManager = NotificationPanelSeparateAnimationManager.Companion.create(true);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ ClearAllController this$0;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ClearAllController clearAllController = this.this$0;
            if (HapticSettingObserver.getInstance().mSystemHapticEnabled) {
                Handler bgHandler = clearAllController.getBgHandler();
                ClearAllController$performHapticFeedback$1 clearAllController$performHapticFeedback$1 = new ClearAllController$performHapticFeedback$1();
                clearAllController$performHapticFeedback$1.this$0 = clearAllController;
                VarHandle.storeStoreFence();
                bgHandler.post(clearAllController$performHapticFeedback$1);
            }
            this.this$0.getHeadsUpManagerPhone().releaseAllImmediately("ClearAllController.bindViews");
            NotificationStackScrollLayout notificationStackScrollLayout = this.this$0.stackScroller;
            if (notificationStackScrollLayout != null) {
                notificationStackScrollLayout.clearNotifications(true);
            }
            ClearAllController.access$executeOnClickAnimator(this.this$0, (OplusClearAllButton) view);
            ViewStatisticUtil.clearAllButtonClicked(this.this$0.context, String.valueOf(this.this$0.activeNotificationsInteractor.getAllNotificationsCountValue()));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ClearAllController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00071 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ ClearAllController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00081 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ ClearAllController this$0;

                    public /* synthetic */ C00081(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                this.this$0.setShowingDialog(((Boolean) obj).booleanValue());
                                break;
                            default:
                                ((Boolean) obj).booleanValue();
                                this.this$0.updateClearAllBackgroundTint();
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00071(ClearAllController clearAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = clearAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00071(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow istDetailShowingDialog = this.this$0.notificationQsInteractor.getIstDetailShowingDialog();
                        ClearAllController clearAllController = this.this$0;
                        C00081 c00081 = new C00081(0);
                        c00081.this$0 = clearAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (istDetailShowingDialog.collect(c00081, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00092 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ ClearAllController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C00101 extends SuspendLambda implements Function5 {
                    int label;

                    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                        ((Boolean) obj).booleanValue();
                        ((Boolean) obj2).booleanValue();
                        ((Boolean) obj3).booleanValue();
                        return new C00101(5, (Continuation) obj5).invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                }

                /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$2$2, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C00112 extends SuspendLambda implements Function2 {
                    int label;
                    final /* synthetic */ ClearAllController this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00112(ClearAllController clearAllController, Continuation continuation) {
                        super(2, continuation);
                        this.this$0 = clearAllController;
                    }

                    public final Continuation create(Object obj, Continuation continuation) {
                        return new C00112(this.this$0, continuation);
                    }

                    public final Object invoke(Object obj, Object obj2) {
                        return create((Unit) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        ClearAllController.updatePlatformBlurDrawable$default(this.this$0);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00092(ClearAllController clearAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = clearAllController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00092(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowCombine = FlowKt.combine(((NotificationEffectRepository) this.this$0.notificationEffectRepository.get()).isStrokeLineEnable(), this.this$0.superPowerSaveRepository.getSuperPowerSaveMode(), ScrimControllerEx.Companion.isThirdThemeMode(), ((NotificationMaterialColorEx) this.this$0.notificationMaterialColorEx.get()).getBlurMode(), new C00101(5, null));
                        C00112 c00112 = new C00112(this.this$0, null);
                        this.label = 1;
                        if (FlowKt.collectLatest(flowCombine, c00112, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.notification.clearall.ClearAllController$bindViews$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ ClearAllController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(ClearAllController clearAllController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = clearAllController;
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
                    int i2 = 1;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsMaterialColor = ((NotificationMaterialColorEx) this.this$0.notificationMaterialColorEx.get()).isMaterialColor();
                        ClearAllController clearAllController = this.this$0;
                        C00071.C00081 c00081 = new C00071.C00081(i2);
                        c00081.this$0 = clearAllController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsMaterialColor.collect(c00081, this) == coroutine_suspended) {
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
            public AnonymousClass1(ClearAllController clearAllController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = clearAllController;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00071(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00092(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = ClearAllController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(ClearAllController.this, null);
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

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit $r8$lambda$iA31iO7oOzwElCOMnAwFEe7ks6w(OplusClearAllButton oplusClearAllButton, ClearAllController clearAllController, AnimationType animationType, float f) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "clearAllhide onAnimationEnd: " + animationType.name() + " " + f, null, 8);
        if (animationType == AnimationType.ALPHA || animationType == AnimationType.BOTH) {
            if (f == 1.0f) {
                oplusClearAllButton.setVisibility(8);
                clearAllController.setMLastVisibility(8);
            }
            clearAllController.playingAnimation = false;
        }
        return Unit.INSTANCE;
    }

    public ClearAllController(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, PanelAnimationEx panelAnimationEx, OplusNotificationQsInteractor oplusNotificationQsInteractor, ViewBlurManager viewBlurManager, MaterialColorManager materialColorManager, ActiveNotificationsInteractor activeNotificationsInteractor, dagger.Lazy lazy, SuperPowerSaveRepository superPowerSaveRepository, dagger.Lazy lazy2) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.panelAnimationEx = panelAnimationEx;
        this.notificationQsInteractor = oplusNotificationQsInteractor;
        this.viewBlurManager = viewBlurManager;
        this.materialColorManager = materialColorManager;
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        this.notificationEffectRepository = lazy;
        this.superPowerSaveRepository = superPowerSaveRepository;
        this.notificationMaterialColorEx = lazy2;
        ClearAllController$$ExternalSyntheticLambda0 clearAllController$$ExternalSyntheticLambda0 = new ClearAllController$$ExternalSyntheticLambda0();
        clearAllController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.materialColorChangeCallback$delegate = LazyKt__LazyJVMKt.lazy(clearAllController$$ExternalSyntheticLambda0);
        ClearAllController$elementShowStateChangeListener$1 clearAllController$elementShowStateChangeListener$1 = new ClearAllController$elementShowStateChangeListener$1();
        clearAllController$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = clearAllController$elementShowStateChangeListener$1;
    }

    public static final void access$executeOnClickAnimator(final ClearAllController clearAllController, final OplusClearAllButton oplusClearAllButton) {
        clearAllController.playingAnimation = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.getClass();
        ScreenOffAnimationGuardKt.enableScreenOffAnimationGuard(valueAnimatorOfFloat, clearAllController.context);
        valueAnimatorOfFloat.setInterpolator(new PathInterpolator(0.17f, 0.0f, 0.83f, 1.0f));
        ClearAllController$executeOnClickAnimator$1 clearAllController$executeOnClickAnimator$1 = new ClearAllController$executeOnClickAnimator$1();
        clearAllController$executeOnClickAnimator$1.$view = oplusClearAllButton;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(clearAllController$executeOnClickAnimator$1);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.clearall.ClearAllController$executeOnClickAnimator$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.this$0.setVisible(oplusClearAllButton, false, false);
                this.this$0.playingAnimation = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                this.this$0.setVisible(oplusClearAllButton, false, false);
                this.this$0.playingAnimation = false;
            }
        });
        valueAnimatorOfFloat.setDuration(417L);
        valueAnimatorOfFloat.start();
    }

    public static /* synthetic */ void getBgHandler$annotations() {
    }

    private final int getClearAllMaskOverlayColor() {
        if (ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
            return 0;
        }
        return this.context.getColor(2131103146);
    }

    private final ClearAllController$materialColorChangeCallback$2$1 getMaterialColorChangeCallback() {
        return (ClearAllController$materialColorChangeCallback$2$1) this.materialColorChangeCallback$delegate.getValue();
    }

    private final int getScreenState() {
        if (Intrinsics.areEqual(this.qsFullyExpanded, Boolean.TRUE) && !this.splitShadeEnabled) {
            return 1;
        }
        if (this.showingCustomizer) {
            return 4;
        }
        Integer num = this.statusBarState;
        if (num != null && num.intValue() == 2) {
            return 2;
        }
        Integer num2 = this.statusBarState;
        return ((num2 != null && num2.intValue() == 1) || KeyguardUtils.Companion.isSystemFbeMode(this.context)) ? 0 : 3;
    }

    private final void setLastScreenState(Integer num) {
        if (Intrinsics.areEqual(this.lastScreenState, num)) {
            return;
        }
        this.lastScreenState = num;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "lastScreenState: " + num, null, 8);
    }

    private final void setMLastVisibility(int i) {
        if (this.mLastVisibility == i) {
            return;
        }
        this.mLastVisibility = i;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mLastVisibility, "mLastVisibility changed: ", "ClearAllController-->", "SystemUi--Notification");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
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
    public final void setShowingDialog(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("setShowingDialog: ", this.mLastVisibility, " mLastVisibility: ", "ClearAllController-->", z));
        }
        this.mDialogShowing = z;
        AppCompatImageView appCompatImageView = this.clearAll;
        if (z) {
            if (appCompatImageView != null) {
                appCompatImageView.setVisibility(8);
            }
        } else if (appCompatImageView != null) {
            appCompatImageView.setVisibility(this.mLastVisibility);
        }
    }

    private final void setTargetAlpha(float f) {
        if (this.targetAlpha == f) {
            return;
        }
        this.targetAlpha = f;
        if (f == 0.0f || f == 1.0f) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(f, "targetAlpha: "), null, 8);
        }
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
    public static void updatePlatformBlurDrawable$default(ClearAllController clearAllController) {
        Drawable background;
        ViewBlurProxy.BlurType blurType;
        AppCompatImageView appCompatImageView;
        Drawable drawable = clearAllController.context.getDrawable(2131235920);
        clearAllController.updateClearAllBackgroundTint();
        AppCompatImageView appCompatImageView2 = clearAllController.clearAll;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setClipToOutline(true);
        }
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                if (ScrimUtil.isBAnimLevel()) {
                    int currentButtonMaterialColor = clearAllController.materialColorManager.getCurrentButtonMaterialColor();
                    if (currentButtonMaterialColor != 0) {
                        if (drawable != null) {
                            drawable.setTint(currentButtonMaterialColor);
                        }
                    } else if (drawable != null) {
                        drawable.setTintList(null);
                    }
                    AppCompatImageView appCompatImageView3 = clearAllController.clearAll;
                    if (appCompatImageView3 != null) {
                        appCompatImageView3.setBackground(drawable);
                        return;
                    }
                    return;
                }
                AppCompatImageView appCompatImageView4 = clearAllController.clearAll;
                if (appCompatImageView4 == null) {
                    return;
                }
                ViewBlurProxy viewBlurProxyRequireBlurProxyForView = clearAllController.viewBlurManager.requireBlurProxyForView(appCompatImageView4, ViewBlurManager.CardType.CLEAR_ALL);
                if (ScrimUtil.isBPAnimLevel()) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
                } else {
                    blurType = GaussBlurUtils.isGaussBlurDisabled() ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
                }
                viewBlurProxyRequireBlurProxyForView.setBlurType(blurType);
                if (Intrinsics.areEqual(viewBlurProxyRequireBlurProxyForView.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && (appCompatImageView = clearAllController.clearAll) != null) {
                    appCompatImageView.setClipToOutline(false);
                }
                int dimensionPixelSize = clearAllController.context.getResources().getDimensionPixelSize(2131170742);
                BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
                blurConfig.setCornerRadius(dimensionPixelSize >> 1);
                blurConfig.setRadiusWeight(null);
                blurConfig.setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createDecoratorCornerParams(blurConfig.getCornerRadius()));
                viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
                int clearAllMaskOverlayColor = clearAllController.getClearAllMaskOverlayColor();
                Drawable background2 = appCompatImageView4.getBackground();
                MaskBlurDrawable maskBlurDrawable = background2 instanceof MaskBlurDrawable ? (MaskBlurDrawable) background2 : null;
                if (maskBlurDrawable == null) {
                    appCompatImageView4.setBackground(new MaskBlurDrawable(viewBlurProxyRequireBlurProxyForView, drawable, clearAllMaskOverlayColor));
                    return;
                }
                maskBlurDrawable.setDefaultDrawable(drawable);
                maskBlurDrawable.setViewBlurProxy(viewBlurProxyRequireBlurProxyForView);
                maskBlurDrawable.setMaskColor(clearAllMaskOverlayColor);
                return;
            }
        }
        if (drawable != null) {
            drawable.setTintList(null);
        }
        AppCompatImageView appCompatImageView5 = clearAllController.clearAll;
        if (appCompatImageView5 != null) {
            appCompatImageView5.setBackground(drawable);
        }
        AppCompatImageView appCompatImageView6 = clearAllController.clearAll;
        if (appCompatImageView6 == null || (background = appCompatImageView6.getBackground()) == null) {
            return;
        }
        background.invalidateSelf();
    }

    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.widget.ImageView, com.oplus.systemui.notification.clearall.OplusClearAllButton] */
    public final DisposableHandle bindViews(ViewGroup viewGroup, NotificationStackScrollLayout notificationStackScrollLayout) {
        this.panelView = viewGroup;
        Object objFindViewById = viewGroup.findViewById(2131362545);
        ?? r0 = objFindViewById instanceof OplusClearAllButton ? (OplusClearAllButton) objFindViewById : 0;
        this.clearAll = r0;
        this.stackScroller = notificationStackScrollLayout;
        if (r0 != 0) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            r0.setOnClickListener(anonymousClass1);
        }
        this.statusBarStateController.addCallback(this, 3);
        this.mCurrentOrientation = this.context.getResources().getConfiguration().orientation;
        updateResource(notificationStackScrollLayout.getResources());
        getConfigurationController().addCallback(this);
        if (this.panelAnimationEx.getUseLayeredAnimation()) {
            this.panelAnimationEx.getElementShowState().addCallback(this.elementShowStateChangeListener);
        }
        if (ScrimUtil.isBAnimLevel()) {
            this.materialColorManager.removeMaterialColorCallback(getMaterialColorChangeCallback());
            this.materialColorManager.addMaterialColorCallback(getMaterialColorChangeCallback());
        }
        DisposableHandle disposableHandle = this.disposableHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        DisposableHandle disposableHandleRepeatWhenAttached$default = RepeatWhenAttachedKt.repeatWhenAttached$default(viewGroup, (CoroutineContext) null, new AnonymousClass2(null), 3);
        this.disposableHandle = disposableHandleRepeatWhenAttached$default;
        return disposableHandleRepeatWhenAttached$default == null ? new DisposableHandles() : disposableHandleRepeatWhenAttached$default;
    }

    public final Handler getBgHandler() {
        Handler handler = this.bgHandler;
        if (handler != null) {
            return handler;
        }
        return null;
    }

    public final ConfigurationController getConfigurationController() {
        ConfigurationController configurationController = this.configurationController;
        if (configurationController != null) {
            return configurationController;
        }
        return null;
    }

    public final HeadsUpManager getHeadsUpManagerPhone() {
        HeadsUpManager headsUpManager = this.headsUpManagerPhone;
        if (headsUpManager != null) {
            return headsUpManager;
        }
        return null;
    }

    public void onConfigChanged(Configuration configuration) {
        AppCompatImageView appCompatImageView;
        int i = configuration.orientation;
        if (i != this.mCurrentOrientation) {
            this.mCurrentOrientation = i;
            boolean zShouldUseSplitNotificationShade = LargeScreenUtils.shouldUseSplitNotificationShade(this.context.getResources());
            int i2 = configuration.orientation;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i2, "updateOrientation orientation: ", "ClearAllController-->", "SystemUi--Notification");
            }
            this.portrait = i2 == 1;
            updateLayoutParams("updateOrientation");
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet() && !this.portrait && zShouldUseSplitNotificationShade && (appCompatImageView = this.showingView) != null) {
                appCompatImageView.setAlpha(1.0f);
            }
        }
        updatePlatformBlurDrawable$default(this);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0062  */
    /* JADX WARN: Code duplicated, block: B:33:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x0082  */
    /* JADX WARN: Code duplicated, block: B:54:0x009d  */
    public final void onHeightUpdated(float f, float f2, float f3, boolean z, boolean z2) {
        boolean z3;
        Integer num;
        float f4;
        Integer num2;
        String str;
        ClearAllController clearAllController;
        Integer num3;
        float f5 = f3 - this.qsNotificationPadding;
        if (z2) {
            if (((Boolean) getHeadsUpManagerPhone().isTrackingHeadsUp().getValue()).booleanValue()) {
                z3 = true;
            } else {
                z3 = true;
            }
            if (f2 == 0.0f) {
                f4 = 0.0f;
            } else {
                f4 = f / f2;
                if (1.0f <= f4) {
                    f4 = 1.0f;
                }
                if (0.0f >= f4) {
                    f4 = 0.0f;
                }
            }
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet() && this.portrait) {
                if (((Boolean) getHeadsUpManagerPhone().isTrackingHeadsUp().getValue()).booleanValue() || (z && ((int) (f - f2)) != 0)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (f2 == 0.0f && (((num = this.lastScreenState) == null || num.intValue() != 0) && !z3)) {
                    f4 = f / f2;
                    if (1.0f <= f4) {
                        f4 = 1.0f;
                    }
                    if (0.0f >= f4) {
                        f4 = 0.0f;
                    }
                } else {
                    f4 = 0.0f;
                }
            } else if (f2 != 0.0f && f2 != f5 && f >= f5 && (((num3 = this.lastScreenState) == null || num3.intValue() != 0) && !((Boolean) getHeadsUpManagerPhone().isTrackingHeadsUp().getValue()).booleanValue())) {
                f4 = (f - f5) / (f2 - f5);
                if (1.0f <= f4) {
                    f4 = 1.0f;
                }
                if (0.0f >= f4) {
                    f4 = 0.0f;
                }
            } else {
                f4 = 0.0f;
            }
        }
        if (f4 == 0.0f) {
            this.playingAnimation = false;
        } else if (this.lastExpansion == 0.0f && (num2 = this.lastScreenState) != null && num2.intValue() == 3) {
            updateVisibility(false);
        } else if (this.panelAnimationEx.getUseLayeredAnimation()) {
            updateVisibility(true);
        }
        if (this.lastQsExpansion == 0.0f || z2) {
            setTargetAlpha(this.expansionInterpolator.getInterpolation(f4));
            if (f4 != this.lastExpansion && !this.playingAnimation) {
                updateVisibility(true);
            }
        }
        if ((f4 == 0.0f || f4 == 1.0f) && f4 != this.lastExpansion && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            float f6 = this.targetAlpha;
            float f7 = this.lastExpansion;
            float f8 = this.lastQsExpansion;
            Boolean bool = this.qsFullyExpanded;
            int screenState = getScreenState();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("onHeightUpdated targetAlpha  ", f6, ", expandHeight = ", f, ", maxExpandHeight = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, ", qsMinHeight = ", f3, ", qsHeight = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f5, ", expansion = ", f4, ", lastExpansion = ");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f7, ", lastQsExpansion = ", f8, ", qsFullyExpanded = ");
            sbM.append(bool);
            sbM.append(", screenState = ");
            sbM.append(screenState);
            str = "SystemUi--Notification";
            Log.d(str, "ClearAllController-->".concat(sbM.toString()));
        } else {
            str = "SystemUi--Notification";
        }
        if (f == 0) {
            clearAllController = this;
            if (Intrinsics.areEqual(clearAllController.qsFullyExpanded, Boolean.TRUE) && !clearAllController.playingAnimation) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    boolean z4 = clearAllController.portrait;
                    Integer num4 = clearAllController.lastScreenState;
                    Boolean bool2 = clearAllController.qsFullyExpanded;
                    boolean z5 = clearAllController.splitShadeEnabled;
                    int screenState2 = clearAllController.getScreenState();
                    StringBuilder sb = new StringBuilder("notification panel is fully close:  portrait = ");
                    sb.append(z4);
                    sb.append(", lastScreenState = ");
                    sb.append(num4);
                    sb.append(", qsFullyExpanded = ");
                    sb.append(bool2);
                    sb.append(", splitShadeEnabled=");
                    sb.append(z5);
                    sb.append(", screenState = ");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(screenState2, "ClearAllController-->", str, sb);
                }
                clearAllController.qsFullyExpanded = Boolean.FALSE;
                int screenState3 = clearAllController.getScreenState();
                Integer num5 = clearAllController.lastScreenState;
                if (num5 == null || screenState3 != num5.intValue()) {
                    clearAllController.setLastScreenState(Integer.valueOf(screenState3));
                }
            }
        } else {
            clearAllController = this;
        }
        clearAllController.lastExpansion = f4;
    }

    public final void onLayoutDirectionChanged(boolean z) {
        this.isLayoutRtl = z;
        updateClearAllPosition(this.splitShadeEnabled);
    }

    public final void onStateChanged(int i) {
        this.statusBarState = Integer.valueOf(i);
        int screenState = getScreenState();
        if (screenState == 0) {
            this.playingAnimation = false;
        }
        Integer num = this.lastScreenState;
        if (num != null && screenState == num.intValue()) {
            return;
        }
        setLastScreenState(Integer.valueOf(screenState));
        updateVisibility(false);
    }

    public final void setBgHandler(Handler handler) {
        this.bgHandler = handler;
    }

    public final void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    public final void setHeadsUpManagerPhone(HeadsUpManager headsUpManager) {
        this.headsUpManagerPhone = headsUpManager;
    }

    public final void setQsCustomizerShowing(boolean z) {
        this.showingCustomizer = z;
        int screenState = getScreenState();
        Integer num = this.lastScreenState;
        if (num != null && screenState == num.intValue()) {
            return;
        }
        setLastScreenState(Integer.valueOf(screenState));
        updateVisibility(true);
    }

    public final void setShowDismiss(boolean z) {
        if (z != this.showDismiss) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setShowDismiss ", z), null, 8);
            this.showDismiss = z;
            updateVisibility(this.lastExpansion == 1.0f && !this.playingAnimation);
            if (this.showDismiss) {
                return;
            }
            this.playingAnimation = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setVisible(OplusClearAllButton oplusClearAllButton, boolean z, boolean z2) {
        int i = 0;
        if (oplusClearAllButton != 0 ? Intrinsics.areEqual(oplusClearAllButton.getTargetVisible(), Boolean.valueOf(z)) : false) {
            return;
        }
        float f = this.targetAlpha;
        boolean z3 = this.mDialogShowing;
        Integer num = this.lastScreenState;
        boolean z4 = this.showDismiss;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("setVisible  visible ", z, "  targetAlpha ", f, " mDialogShowing ");
        sbM.append(z3);
        sbM.append(" lastScreenState ");
        sbM.append(num);
        sbM.append(" showDismiss ");
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, DynamicRouteAttributes$.ExternalSyntheticOutline0.m(" animated ", " ", sbM, z4, z2), null, 8);
        if (oplusClearAllButton != 0) {
            oplusClearAllButton.setTargetVisible(Boolean.valueOf(z));
        }
        if (!z2) {
            AppCompatImageView appCompatImageView = this.clearAll;
            if (appCompatImageView != null) {
                ((NotificationPanelSeparateAnimationManagerImpl) this.clearAllAnimationManager).cancelViewAnimation(appCompatImageView, "clearAllAnimation cancel");
            }
            if (oplusClearAllButton != 0) {
                oplusClearAllButton.setAlpha(this.targetAlpha);
            }
            setMLastVisibility(z ? 0 : 8);
            if (oplusClearAllButton != 0) {
                oplusClearAllButton.setVisibility(this.mDialogShowing ? 8 : this.mLastVisibility);
                return;
            }
            return;
        }
        if (oplusClearAllButton == 0) {
            return;
        }
        int i2 = 1;
        this.playingAnimation = true;
        if (!z) {
            boolean z5 = (oplusClearAllButton.getAlpha() == 0.0f || this.panelAnimationEx.shouldSnapHideAfterCollapse()) ? false : true;
            NotificationPanelSeparateAnimationManager notificationPanelSeparateAnimationManager = this.clearAllAnimationManager;
            ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda1 = new ClearAllController$$ExternalSyntheticLambda1(3);
            clearAllController$$ExternalSyntheticLambda1.f$0 = oplusClearAllButton;
            VarHandle.storeStoreFence();
            ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda2 = new ClearAllController$$ExternalSyntheticLambda1(4);
            clearAllController$$ExternalSyntheticLambda2.f$0 = oplusClearAllButton;
            VarHandle.storeStoreFence();
            ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda3 = new ClearAllController$$ExternalSyntheticLambda1(5);
            clearAllController$$ExternalSyntheticLambda3.f$0 = oplusClearAllButton;
            VarHandle.storeStoreFence();
            ClearAllController$$ExternalSyntheticLambda4 clearAllController$$ExternalSyntheticLambda4 = new ClearAllController$$ExternalSyntheticLambda4(i2);
            clearAllController$$ExternalSyntheticLambda4.f$1 = oplusClearAllButton;
            clearAllController$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            ((NotificationPanelSeparateAnimationManagerImpl) notificationPanelSeparateAnimationManager).clearAllhide(oplusClearAllButton, z5, clearAllController$$ExternalSyntheticLambda1, clearAllController$$ExternalSyntheticLambda2, clearAllController$$ExternalSyntheticLambda3, clearAllController$$ExternalSyntheticLambda4);
            return;
        }
        setMLastVisibility(0);
        oplusClearAllButton.setVisibility(this.mDialogShowing ? 8 : 0);
        NotificationPanelSeparateAnimationManager notificationPanelSeparateAnimationManager2 = this.clearAllAnimationManager;
        ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda5 = new ClearAllController$$ExternalSyntheticLambda1(0);
        clearAllController$$ExternalSyntheticLambda5.f$0 = oplusClearAllButton;
        VarHandle.storeStoreFence();
        ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda6 = new ClearAllController$$ExternalSyntheticLambda1(1);
        clearAllController$$ExternalSyntheticLambda6.f$0 = oplusClearAllButton;
        VarHandle.storeStoreFence();
        ClearAllController$$ExternalSyntheticLambda1 clearAllController$$ExternalSyntheticLambda7 = new ClearAllController$$ExternalSyntheticLambda1(2);
        clearAllController$$ExternalSyntheticLambda7.f$0 = oplusClearAllButton;
        VarHandle.storeStoreFence();
        ClearAllController$$ExternalSyntheticLambda4 clearAllController$$ExternalSyntheticLambda8 = new ClearAllController$$ExternalSyntheticLambda4(i);
        clearAllController$$ExternalSyntheticLambda8.f$0 = this;
        clearAllController$$ExternalSyntheticLambda8.f$1 = oplusClearAllButton;
        VarHandle.storeStoreFence();
        ((NotificationPanelSeparateAnimationManagerImpl) notificationPanelSeparateAnimationManager2).clearAllshow(oplusClearAllButton, true, clearAllController$$ExternalSyntheticLambda5, clearAllController$$ExternalSyntheticLambda6, clearAllController$$ExternalSyntheticLambda7, clearAllController$$ExternalSyntheticLambda8);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0050  */
    public final void updateClearAllBackgroundTint() {
        int color;
        Drawable drawable = this.context.getResources().getDrawable(2131235921);
        if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            color = this.context.getColor(2131102885);
        } else {
            ScrimControllerEx.Companion.getClass();
            if (ScrimControllerEx.isThirdTheme) {
                color = this.context.getColor(2131102885);
            } else if (ScrimUtil.isLowGaussianLevel(this.context)) {
                color = this.context.getColor(2131102937);
            } else {
                color = NotificationColorPalette.Companion.from((MaterialColorMode) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).getCurrentMaterialColor().getValue()).getPrimaryColor();
            }
        }
        drawable.setTint(color);
        OplusClearAllButton oplusClearAllButton = this.clearAll;
        if (oplusClearAllButton != null) {
            oplusClearAllButton.setImageDrawable(drawable);
        }
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
    public final void updateClearAllPosition(boolean z) {
        AppCompatImageView appCompatImageView;
        int i = this.scrollLayoutWidth;
        if (i == 0) {
            return;
        }
        float f = i - (this.stackScrollerSidePadding * 2.0f);
        float f2 = 0.0f;
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(this.horizontalMarginStackLayout, "ClearAllController-->", "SystemUi--Notification", KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, this.distanceToStackScroller, "clearAll land translation: 0.0, width: ", "  distanceToStackScroller: ", " horizontalMarginStackLayout: "));
            }
            boolean z2 = this.isLayoutRtl;
            int i2 = this.horizontalMarginStackLayout;
            f2 = z2 ? (0.0f - (f / 2.0f)) - i2 : (f / 2.0f) + i2;
        }
        AppCompatImageView appCompatImageView2 = this.clearAll;
        if (Intrinsics.areEqual(f2, appCompatImageView2 != null ? Float.valueOf(appCompatImageView2.getTranslationX()) : null) || (appCompatImageView = this.clearAll) == null) {
            return;
        }
        appCompatImageView.setTranslationX(f2);
    }

    public final void updateLayoutParams(String str) {
        boolean z = SeparateModeHelper.INSTANCE.getSeparateMode() && DeviceStateUtils.isLandInSmallScreen(this.context, this.portrait ^ true);
        if (this.isLandInSeparateMode == z) {
            AppCompatImageView appCompatImageView = this.clearAll;
            if (appCompatImageView != null) {
                ViewGroup.LayoutParams layoutParams = appCompatImageView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = this.iconWidth;
                    layoutParams.height = this.iconHeight;
                } else {
                    layoutParams = null;
                }
                appCompatImageView.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        this.isLandInSeparateMode = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("updateLayoutParams, reason: ", str, ", isLandInSeparateMode: ", this.isLandInSeparateMode, "ClearAllController-->"));
        }
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(-2, -2);
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = this.iconHeight;
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = this.iconWidth;
        if (this.isLandInSeparateMode) {
            layoutParams2.topToTop = 0;
            layoutParams2.bottomToBottom = 0;
            layoutParams2.endToEnd = 0;
            layoutParams2.setMarginEnd(this.clearButtonMarginEnd);
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin, "clearAllButton isLandInSeparateMode lp.bottomMargin: ", "ClearAllController-->", "SystemUi--Notification");
            }
        } else {
            layoutParams2.startToStart = 0;
            layoutParams2.endToEnd = 0;
            layoutParams2.bottomToBottom = 0;
            boolean zIsLandInSmallScreen = DeviceStateUtils.isLandInSmallScreen(this.context, !this.portrait);
            int navigationBarHeight = this.defaultMarginBottom;
            if (!zIsLandInSmallScreen) {
                navigationBarHeight += NavBarUtils.getNavigationBarHeight(this.context);
            }
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = navigationBarHeight;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("clearAllButton defaultMarginBottom= ", this.defaultMarginBottom, ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin, " lp.bottomMargin: ", "ClearAllController-->"));
            }
        }
        AppCompatImageView appCompatImageView2 = this.clearAll;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setLayoutParams(layoutParams2);
        }
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
    public final void updateQsExpansion(QS qs, float f) {
        float f2 = this.lastExpansion;
        if (f2 == 1.0f) {
            if ((f == 1.0f || f == 0.0f) && f != this.lastQsExpansion && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                boolean z = this.portrait;
                float f3 = this.lastExpansion;
                float f4 = this.lastQsExpansion;
                Boolean bool = this.qsFullyExpanded;
                boolean z2 = this.splitShadeEnabled;
                int screenState = getScreenState();
                boolean z3 = this.playingAnimation;
                StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("updateQsExpansion portrait = ", z, ", qsExpansion = ", f, ", tmpExpansion = ");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", lastExpansion = ", f3, ", lastQsExpansion = ");
                sbM.append(f4);
                sbM.append(", qsFullyExpanded = ");
                sbM.append(bool);
                sbM.append(", splitShadeEnabled= ");
                PromptIconState$.ExternalSyntheticOutline0.m(screenState, ", screenState = ", ", playingAnimation = ", sbM, z2);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("ClearAllController-->", "SystemUi--Notification", sbM, z3);
            }
            setTargetAlpha(this.splitShadeEnabled ? 1.0f : this.expansionInterpolator.getInterpolation(1.0f - f));
            if (!this.playingAnimation) {
                if (!Intrinsics.areEqual(this.qsFullyExpanded, Boolean.valueOf(f == 1.0f))) {
                    this.qsFullyExpanded = Boolean.valueOf(f == 1.0f);
                    int screenState2 = getScreenState();
                    Integer num = this.lastScreenState;
                    if (num == null || screenState2 != num.intValue()) {
                        setLastScreenState(Integer.valueOf(screenState2));
                        updateVisibility(false);
                    }
                }
            }
            AppCompatImageView appCompatImageView = this.showingView;
            if (appCompatImageView != null && appCompatImageView.getVisibility() == 0) {
                float f5 = this.targetAlpha;
                AppCompatImageView appCompatImageView2 = this.showingView;
                if (appCompatImageView2 != null && appCompatImageView2.getAlpha() != f5) {
                    if (this.playingAnimation) {
                        ((NotificationPanelSeparateAnimationManagerImpl) this.clearAllAnimationManager).clearAllShowAlphaAnimateToPosition(appCompatImageView2, f5);
                    } else {
                        appCompatImageView2.setAlpha(f5);
                    }
                }
            }
        } else if (f2 == 0.0f && this.expansionInterpolator.getInterpolation(f) == 0.0f && !this.playingAnimation) {
            setTargetAlpha(this.expansionInterpolator.getInterpolation(f));
            AppCompatImageView appCompatImageView3 = this.showingView;
            if (appCompatImageView3 != null) {
                appCompatImageView3.setAlpha(this.targetAlpha);
            }
        }
        this.lastQsExpansion = f;
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
    public final void updateResource(Resources resources) {
        resources.getDimensionPixelSize(2131165988);
        this.qsNotificationPadding = resources.getDimensionPixelSize(2131172016);
        this.iconWidth = resources.getDimensionPixelSize(2131170743);
        this.iconHeight = resources.getDimensionPixelSize(2131170742);
        this.distanceToStackScroller = resources.getDimensionPixelSize(2131167996);
        this.stackScrollerSidePadding = resources.getDimensionPixelSize(2131170784);
        this.horizontalMarginStackLayout = resources.getDimensionPixelSize(2131172246);
        this.clearButtonMarginEnd = resources.getDimensionPixelSize(2131165990);
        this.defaultMarginBottom = this.context.getResources().getDimensionPixelSize(2131165989);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mCurrentOrientation, "updateResource mCurrentOrientation: ", "ClearAllController-->", "SystemUi--Notification");
        }
        this.portrait = this.mCurrentOrientation == 1;
        int dimensionPixelSize = resources.getDimensionPixelSize(2131165991);
        AppCompatImageView appCompatImageView = this.clearAll;
        if (appCompatImageView != null) {
            appCompatImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        }
        updateLayoutParams("updateResource");
    }

    public final void updateVisibility(boolean z) {
        setLastScreenState(Integer.valueOf(getScreenState()));
        boolean z2 = this.showDismiss && (!this.panelAnimationEx.getUseLayeredAnimation() || this.panelAnimationEx.isElementShowing());
        Integer num = this.lastScreenState;
        if ((num != null && num.intValue() == 0) || ((num != null && num.intValue() == 1) || (num != null && num.intValue() == 2))) {
            setVisible(false, z);
            return;
        }
        if (num != null && num.intValue() == 3) {
            setVisible(z2, z);
            return;
        }
        if (num != null && num.intValue() == 4) {
            setVisible(!this.showingCustomizer, z);
        } else if (num != null && num.intValue() == 5) {
            setVisible(true, z);
        }
    }

    public final void updateClearAllPosition(int i, boolean z) {
        if (this.splitShadeEnabled == z && this.scrollLayoutWidth == i) {
            return;
        }
        this.splitShadeEnabled = z;
        int screenState = getScreenState();
        Integer num = this.lastScreenState;
        if (num == null || screenState != num.intValue()) {
            setLastScreenState(Integer.valueOf(screenState));
            updateVisibility(false);
        }
        this.scrollLayoutWidth = i;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() && !z) {
            updateClearAllPosition(false);
        } else {
            updateClearAllPosition(z);
        }
    }

    public final void updateLayoutParams(int i) {
        AppCompatImageView appCompatImageView = this.clearAll;
        ViewGroup.LayoutParams layoutParams = appCompatImageView != null ? appCompatImageView.getLayoutParams() : null;
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            if (DeviceStateUtils.isLandInSmallScreen(this.context, !this.portrait) && SeparateModeHelper.INSTANCE.getSeparateMode()) {
                i = 0;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = i;
        }
        AppCompatImageView appCompatImageView2 = this.clearAll;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setLayoutParams(layoutParams2);
        }
    }

    public final void setVisible(boolean z, boolean z2) {
        OplusClearAllButton oplusClearAllButton;
        OplusClearAllButton oplusClearAllButton2 = this.clearAll;
        if (z) {
            setVisible(oplusClearAllButton2, true, z2);
            oplusClearAllButton = this.clearAll;
        } else {
            setVisible(oplusClearAllButton2, false, z2);
            oplusClearAllButton = null;
        }
        this.showingView = oplusClearAllButton;
    }
}
