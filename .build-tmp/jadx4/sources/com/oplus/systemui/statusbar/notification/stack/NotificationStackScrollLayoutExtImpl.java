package com.oplus.systemui.statusbar.notification.stack;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.OplusSpringOverScroller;
import androidx.collection.FloatList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.LogWindowBase;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.TransCalculator;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotifPipelineChoreographerImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.data.model.NotifStats;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import com.android.systemui.statusbar.notification.stack.NotificationSection;
import com.android.systemui.statusbar.notification.stack.NotificationSectionsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.SeparateModeHelper;
import com.android.systemui.util.kotlin.UtilsKt;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.coui.appcompat.springchain.COUIChainItem;
import com.coui.appcompat.springchain.COUIGridSpringChain;
import com.coui.appcompat.springchain.api.IChainItem;
import com.coui.appcompat.springchain.api.ISpringUpdateListener;
import com.facebook.rebound.Spring;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController;
import com.oplus.systemui.notification.clearall.ClearAllController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.statistic.CustomRowTrackLinkHelper;
import com.oplus.systemui.panelanimation.SpringInterpolator;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.shade.NotificationPanelNsslScaleAnimationManager;
import com.oplus.systemui.shade.NotificationPanelViewControllerExImp;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.helper.DeleteSoundHelper;
import com.oplus.systemui.statusbar.notification.helper.DeleteSoundHelper$$ExternalSyntheticLambda2;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.rendercache.RenderBoosterStagger;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationStackScrollLayoutExtImpl implements NotificationStackScrollLayoutExt {
    private static final String DEFAULT_SCROLLER_SPRING_CONFIG = "spring_config(1000,4000,10000,0.6,5,2.5)";
    private static final String TAG = "NotificationStackScrollLayoutExtImpl";
    private final Lazy ambientState;
    private final Lazy aodTransition;
    private final NotificationPanelNsslScaleAnimationManager appearAnimationManager;
    private final Lazy capsuleCardControl;
    private final NotifPipelineChoreographerImpl choreographer;
    private final kotlin.Lazy clearAllController$delegate;
    private final Context context;
    public float currentTranslationY;
    private final kotlin.Lazy customRowManager$delegate;
    private final kotlin.Lazy deleteSoundHelper$delegate;
    private final NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1 elementShowStateChangeListener;
    private EmptyShadeView emptyShadeView;
    private final kotlin.Lazy extraAnimationEventGenAction$delegate;
    private final kotlin.Lazy extraAnimationEvents$delegate;
    private FlingAnimationUtils flingAnimationUtils;
    public boolean hasBoosted;
    public boolean hasMediaPanelOnKg;
    public boolean hasOverFling;
    public boolean hasVisibleNotificationRow;
    public boolean hasVisibleUbiquitousRow;
    public boolean isClearAll;
    public boolean isContentHeightChanged;
    public boolean isHorizontalSwitch;
    public final boolean isParallax;
    public boolean isScrollToDefaultOrTopPosition;
    public boolean isScrollerFling;
    public boolean keyguardNsslVisible;
    public int lastFirstChildTranslationY;
    public long lastLogDistanceTimeMs;
    public int mMinimumPaddingsScrimTop;
    private final HashMap<ExpandableNotificationRow, Runnable> mPendingExpansionChangeRunnables;
    public int mQuickQsOffsetHeight;
    public boolean mStackScrollerOverScrolling;
    public boolean needDeferredRenderBooster;
    private final NotifPipeline notifPipeline;
    private final Lazy npvc;
    private final Lazy oplusCustomRowLockScreenInteractor;
    private final OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    public float overDistance;
    private ValueAnimator overScrollAnimator;
    public final ValueAnimator overScrollerAnimation;
    private final Interpolator overScrollerInterpolator;
    private TimeInterpolator overScrollerInterpolatorOverride;
    private final kotlin.Lazy panelAnimationEx$delegate;
    private final ActiveNotificationListRepository repository;
    private final ArrayList<RenderBooster> rowBoosterBuffer;
    private final COUIGridSpringChain scrollGridChain;
    public int scrollGridCount;
    public int scrollYWhenDown;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final Lazy sharedNotificationContainerInteractor;
    private final NotificationStackScrollLayoutExtImpl$stateListener$1 stateListener;
    public final SysuiStatusBarStateController statusBarStateController;
    private final COUIGridSpringChain.TransCalculator transCalculator;
    private NotificationStackScrollLayout view;
    private Function0 windowInsetChangedListener;
    private final WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor;
    public static final Companion Companion = new Companion();
    private static final SpringInterpolator REBOUND_INTERPOLATOR = new SpringInterpolator(0.6d, 0.0d, 0.0d, 0.0f);

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ISpringUpdateListener {
        public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

        public final void onUpdate(int i, float f, int i2, IChainItem iChainItem) {
            NotificationStackScrollLayout view = this.this$0.getView();
            if (view != null) {
                view.requestChildrenUpdate$1();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private static /* synthetic */ void getINVALIDATE_SHADE_NOTIFICATION$annotations() {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$isRenderExpensiveView$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class C02941 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            return Boolean.valueOf(((OplusExpandableNotificationRowExImpl.Companion) ((CallableReference) this).receiver).isRenderExpensiveView((View) obj));
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$T3GaZ-m8IoTxmjcCJL_0g6bRqBM, reason: not valid java name */
    public static Unit m461$r8$lambda$T3GaZm8IoTxmjcCJL_0g6bRqBM(NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl) {
        notificationStackScrollLayoutExtImpl.rowBoosterBuffer.clear();
        return Unit.INSTANCE;
    }

    public NotificationStackScrollLayoutExtImpl(Context context, ActiveNotificationListRepository activeNotificationListRepository, OplusPanoramicAodRepository oplusPanoramicAodRepository, Lazy lazy, Lazy lazy2, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, Lazy lazy3, Lazy lazy4, OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController, NotifPipelineChoreographerImpl notifPipelineChoreographerImpl, NotifPipeline notifPipeline, Lazy lazy5, Lazy lazy6, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx) {
        this.context = context;
        this.repository = activeNotificationListRepository;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.aodTransition = lazy;
        this.sharedNotificationContainerInteractor = lazy2;
        this.windowRootViewVisibilityInteractor = windowRootViewVisibilityInteractor;
        this.capsuleCardControl = lazy3;
        this.oplusCustomRowLockScreenInteractor = lazy4;
        this.oplusNotificationTiltShiftBlurController = oplusNotificationTiltShiftBlurController;
        this.choreographer = notifPipelineChoreographerImpl;
        this.notifPipeline = notifPipeline;
        this.npvc = lazy5;
        this.ambientState = lazy6;
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        final int i = 0;
        this.extraAnimationEvents$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        final int i2 = 1;
        this.extraAnimationEventGenAction$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        NotificationStackScrollLayoutExtImpl$overScrollerInterpolator$1 notificationStackScrollLayoutExtImpl$overScrollerInterpolator$1 = new NotificationStackScrollLayoutExtImpl$overScrollerInterpolator$1();
        notificationStackScrollLayoutExtImpl$overScrollerInterpolator$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.overScrollerInterpolator = notificationStackScrollLayoutExtImpl$overScrollerInterpolator$1;
        final int i3 = 2;
        this.clearAllController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        final int i4 = 3;
        this.deleteSoundHelper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        this.isParallax = true;
        this.rowBoosterBuffer = new ArrayList<>();
        this.mPendingExpansionChangeRunnables = new HashMap<>();
        final int i5 = 4;
        this.panelAnimationEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        final int i6 = 5;
        this.customRowManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 1:
                        NotificationStackScrollLayoutExtImpl.Companion companion2 = NotificationStackScrollLayoutExtImpl.Companion;
                        return new LinkedHashSet();
                    case 2:
                        NotificationStackScrollLayoutExtImpl.Companion companion3 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (ClearAllController) DependencyEx.sDependency.getDependency(ClearAllController.class);
                    case 3:
                        NotificationStackScrollLayoutExtImpl.Companion companion4 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (DeleteSoundHelper) DependencyEx.sDependency.getDependency(DeleteSoundHelper.class);
                    case 4:
                        NotificationStackScrollLayoutExtImpl.Companion companion5 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                    default:
                        NotificationStackScrollLayoutExtImpl.Companion companion6 = NotificationStackScrollLayoutExtImpl.Companion;
                        return (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
                }
            }
        });
        COUIGridSpringChain cOUIGridSpringChain = new COUIGridSpringChain(110, 28, 500, 22, 110, 28, 500, 22);
        this.scrollGridChain = cOUIGridSpringChain;
        this.statusBarStateController = (SysuiStatusBarStateController) DependencyEx.sDependency.getDependency(SysuiStatusBarStateController.class);
        NotificationStackScrollLayoutExtImpl$stateListener$1 notificationStackScrollLayoutExtImpl$stateListener$1 = new NotificationStackScrollLayoutExtImpl$stateListener$1();
        notificationStackScrollLayoutExtImpl$stateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.stateListener = notificationStackScrollLayoutExtImpl$stateListener$1;
        this.overScrollerAnimation = ValueAnimator.ofFloat(0.0f, 1.0f);
        NotificationStackScrollLayoutExtImpl$transCalculator$1 notificationStackScrollLayoutExtImpl$transCalculator$1 = new NotificationStackScrollLayoutExtImpl$transCalculator$1();
        this.transCalculator = notificationStackScrollLayoutExtImpl$transCalculator$1;
        this.flingAnimationUtils = new FlingAnimationUtils(context.getResources().getDisplayMetrics(), 0.5f);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUIGridSpringChain.setSpringUpdateListener(anonymousClass1);
        cOUIGridSpringChain.setTranCalculator(notificationStackScrollLayoutExtImpl$transCalculator$1, 1);
        cOUIGridSpringChain.setTranCalculator(notificationStackScrollLayoutExtImpl$transCalculator$1, 2);
        NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1 notificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1 = new NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1();
        notificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1.this$0 = this;
        NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1.AnonymousClass1 anonymousClass2 = new NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1.AnonymousClass1();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        notifPipeline.addOnAfterRenderListListener(anonymousClass2);
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = notificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1;
        this.appearAnimationManager = NotificationPanelNsslScaleAnimationManager.Companion.create((ScrimUtil.isBAnimLevel() || ScrimUtil.isBPAnimLevel()) ? NotificationPanelNsslScaleAnimationManager.Config.BB_PLUS_ALPHA_SCALE : NotificationPanelNsslScaleAnimationManager.Config.LEGACY_SCALE_ONLY);
    }

    public static final void access$cancelScrollScaleAnimator(NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl) {
        View view = notificationStackScrollLayoutExtImpl.getView();
        if (view != null) {
            notificationStackScrollLayoutExtImpl.appearAnimationManager.cancelViewAnimation(view, "onStateChanged = KEYGUARD, cancelViewAnimation");
            notificationStackScrollLayoutExtImpl.updatePivot();
            notificationStackScrollLayoutExtImpl.ensureNsslHiddenWhenElementNotShowing();
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
    public static final void access$updateViewScale(NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl) {
        NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9;
        View view = notificationStackScrollLayoutExtImpl.getView();
        if (view != null) {
            int i = 1;
            boolean z = !notificationStackScrollLayoutExtImpl.isHorizontalSwitch;
            if (notificationStackScrollLayoutExtImpl.getShouldNsslAlphaAnimation()) {
                notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9(2);
                notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9.f$0 = view;
                VarHandle.storeStoreFence();
            } else {
                notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 = null;
            }
            NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda10 = notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9;
            NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8();
            notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8.f$0 = notificationStackScrollLayoutExtImpl;
            notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8.f$1 = view;
            VarHandle.storeStoreFence();
            boolean zIsElementShowing = notificationStackScrollLayoutExtImpl.getPanelAnimationEx().isElementShowing();
            NotificationPanelNsslScaleAnimationManager notificationPanelNsslScaleAnimationManager = notificationStackScrollLayoutExtImpl.appearAnimationManager;
            if (zIsElementShowing) {
                NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda11 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9(0);
                notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda11.f$0 = notificationStackScrollLayoutExtImpl;
                VarHandle.storeStoreFence();
                notificationPanelNsslScaleAnimationManager.show(view, z, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda11, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda10, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8);
                return;
            }
            boolean z2 = !notificationStackScrollLayoutExtImpl.getPanelAnimationEx().shouldSnapHideAfterCollapse();
            NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda12 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9(i);
            notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda12.f$0 = notificationStackScrollLayoutExtImpl;
            VarHandle.storeStoreFence();
            notificationPanelNsslScaleAnimationManager.hide(view, z2, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda12, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda10, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8);
        }
    }

    private final ClearAllController getClearAllController() {
        return (ClearAllController) this.clearAllController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Float, Float> getCurrentAlphaAndScale() {
        float scaleX;
        boolean zIsAnimationRunning = this.appearAnimationManager.isAnimationRunning(getView());
        if (zIsAnimationRunning) {
            NotificationStackScrollLayout view = getView();
            scaleX = view != null ? view.getScaleX() : ScaleState.Companion.getINVALID().getScaleX();
        } else if (getPanelAnimationEx().isElementShowing()) {
            scaleX = getShouldNsslAlphaAnimation() ? 0.8f : 0.9f;
        } else {
            NotificationStackScrollLayout view2 = getView();
            scaleX = view2 != null ? view2.getScaleX() : ScaleState.Companion.getINVALID().getScaleX();
        }
        float alpha = 1.0f;
        if (zIsAnimationRunning) {
            NotificationStackScrollLayout view3 = getView();
            if (view3 != null) {
                alpha = view3.getAlpha();
            }
        } else if (getPanelAnimationEx().isElementShowing()) {
            alpha = 0.0f;
        } else {
            NotificationStackScrollLayout view4 = getView();
            if (view4 != null) {
                alpha = view4.getAlpha();
            }
        }
        return new Pair<>(Float.valueOf(alpha), Float.valueOf(scaleX));
    }

    private final OplusCustomRowControllerManager getCustomRowManager() {
        return (OplusCustomRowControllerManager) this.customRowManager$delegate.getValue();
    }

    private final DeleteSoundHelper getDeleteSoundHelper() {
        return (DeleteSoundHelper) this.deleteSoundHelper$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PanelAnimationEx getPanelAnimationEx() {
        return (PanelAnimationEx) this.panelAnimationEx$delegate.getValue();
    }

    private final boolean getShouldNsslAlphaAnimation() {
        return ScrimUtil.isBAnimLevel() || ScrimUtil.isBPAnimLevel();
    }

    public static /* synthetic */ void getTransCalculator$annotations() {
    }

    public static Function1 isRenderExpensiveView(ExpandableView expandableView) {
        if (expandableView instanceof ExpandableNotificationRow) {
            return new C02941(1, OplusExpandableNotificationRowExImpl.Companion, OplusExpandableNotificationRowExImpl.Companion.class, "isRenderExpensiveView", "isRenderExpensiveView(Landroid/view/View;)Z", 0);
        }
        return null;
    }

    public final void applyRenderBoosterForVisibleChildren(String str, boolean z) {
        Sequence<ExpandableView> children;
        Sequence<ExpandableView> children2;
        if (this.hasBoosted) {
            return;
        }
        int i = 0;
        if (z) {
            NotificationStackScrollLayout view = getView();
            if (view != null && (children2 = ConvenienceExtensionsKt.getChildren(view)) != null) {
                for (ExpandableView expandableView : children2) {
                    if (expandableView.getVisibility() != 8 && ((expandableView instanceof ExpandableNotificationRow) || (expandableView instanceof OplusCustomRow))) {
                        this.rowBoosterBuffer.add(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, expandableView, isRenderExpensiveView(expandableView), 1));
                    }
                }
            }
            ArrayList<RenderBooster> arrayList = this.rowBoosterBuffer;
            if (arrayList.isEmpty()) {
                return;
            }
            ViewGroup view2 = getView();
            if (view2 != null) {
                RenderBoosterStagger renderBoosterStagger = RenderBoosterStagger.INSTANCE;
                NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6(i);
                notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6.f$0 = this;
                VarHandle.storeStoreFence();
                renderBoosterStagger.schedule(view2, arrayList, 11, true, null, notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6);
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[applyRenderBoosterForVisibleChildren] reason=", str, ", visibleChildren=", arrayList.size()), null, 8);
        } else {
            NotificationStackScrollLayout view3 = getView();
            if (view3 != null && (children = ConvenienceExtensionsKt.getChildren(view3)) != null) {
                int i2 = 0;
                for (ExpandableView expandableView2 : children) {
                    if (expandableView2.getVisibility() != 8 && ((expandableView2 instanceof ExpandableNotificationRow) || (expandableView2 instanceof OplusCustomRow))) {
                        RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, expandableView2, isRenderExpensiveView(expandableView2), 1), 11, false, 6);
                        i2++;
                    }
                }
                i = i2;
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[applyRenderBoosterForVisibleChildren] reason=", str, ", visibleChildren=", i), null, 8);
        }
        this.hasBoosted = true;
    }

    public void bind(NotificationStackScrollLayout notificationStackScrollLayout) {
        super.bind(notificationStackScrollLayout);
        NotificationStackScrollLayout view = getView();
        this.keyguardNsslVisible = view != null && view.getVisibility() == 0;
        if (shouldApplyRenderBooster()) {
            applyRenderBoosterForVisibleChildren("bind", false);
        }
        updateScrollGridCount();
    }

    public final int calcNsslContentHeight(int i, int i2, float f, int i3) {
        float fMax;
        float px;
        if (isOnLockscreenNtfCenter()) {
            fMax = f + i;
            px = (UtilsKt.toPx(this.context.getResources().getConfiguration().screenHeightDp, this.context) - this.context.getResources().getDimensionPixelSize(2131170745)) - ((OplusCloseAllController) DependencyEx.sDependency.getDependency(OplusCloseAllController.class)).viewTop();
        } else {
            fMax = f + Math.max(i2, i);
            px = i3;
        }
        return (int) (fMax + px);
    }

    public boolean canChildBeExpandedByDragGesture(View view) {
        return this.statusBarStateController.getState() == 0;
    }

    public boolean canViewBeDismissed(NotificationEntry notificationEntry) {
        return notificationEntry.getRow().canViewBeDismissed();
    }

    public void cancelLongPress(NotificationStackScrollLayoutController notificationStackScrollLayoutController, boolean z, boolean z2) {
        if (z && z2 && notificationStackScrollLayoutController != null) {
            notificationStackScrollLayoutController.mView.cancelLongPress();
        }
    }

    public final void cancelScrollAnimator() {
        if (this.isParallax) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "cancelScrollAnimator releaseSpringComplete", null, 8);
            this.scrollGridChain.releaseSpringComplete();
        } else {
            ValueAnimator valueAnimator = this.overScrollAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }
    }

    public Animator computeFlingOverScrollerProperties(float f, float f2, float f3, float f4) {
        FlingAnimationUtils flingAnimationUtils = this.flingAnimationUtils;
        if (flingAnimationUtils != null) {
            flingAnimationUtils.apply(this.overScrollerAnimation, f, f2, f3, f4);
        }
        return this.overScrollerAnimation;
    }

    public OplusSpringOverScroller createOplusSpringOverScroller(Context context) {
        OplusSpringOverScroller oplusSpringOverScroller = new OplusSpringOverScroller(context, getOverScrollerInterpolator());
        oplusSpringOverScroller.setSpringConfigFromRus(DEFAULT_SCROLLER_SPRING_CONFIG);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->OplusSpringOverScroller initScrollerSpringConfig spring_config(1000,4000,10000,0.6,5,2.5)");
        }
        return oplusSpringOverScroller;
    }

    public boolean dispatchTouchEvent(NotificationStackScrollLayoutController notificationStackScrollLayoutController, boolean z, MotionEvent motionEvent, int i) {
        NotificationStackScrollLayoutControllerExt ext;
        if (motionEvent.getActionMasked() == 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(i, "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification", NormalizedTouchData$.ExternalSyntheticOutline0.m(this.overDistance, this.scrollYWhenDown, "dispatchTouchEvent ACTION_DOWN scrollYWhenDown ", " overDistance ", " ownScrollY "));
            }
            this.scrollYWhenDown = i;
        }
        if (motionEvent.getActionMasked() == 0 && z && (ext = notificationStackScrollLayoutController.getExt()) != null && ext.isTouchInSideEmptyArea(notificationStackScrollLayoutController.getView(), motionEvent)) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "down side empty event on keyguard", null, 8);
            return true;
        }
        if (motionEvent.getActionMasked() == 0 || !z || !notificationStackScrollLayoutController.mIsDownInSideEmptyArea.booleanValue()) {
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "not down side empty event on keyguard", null, 8);
        return true;
    }

    public int doLayeredOverScroll(NotificationStackScrollLayout notificationStackScrollLayout, int i, OplusSpringOverScroller oplusSpringOverScroller) {
        if (notificationStackScrollLayout != null && oplusSpringOverScroller != null && this.isScrollerFling) {
            int topPadding = 0;
            if (isAttachToTop(i)) {
                if (!SeparateModeHelper.INSTANCE.getSeparateMode() && !isOnLockscreenNtfCenter()) {
                    topPadding = notificationStackScrollLayout.getTopPadding() - getQuickQsOffsetHeight();
                }
                updateOverDistance(Math.abs(i - topPadding), "doLayeredOverScroll:isAttachToTop");
                updateMoveTranslation(this.overDistance);
                flingOver(true, notificationStackScrollLayout, oplusSpringOverScroller, i);
                notificationStackScrollLayout.requestChildrenUpdate$1();
                return topPadding;
            }
            NotificationStackScrollLayout view = getView();
            if (view != null && i >= view.getScrollRange() && this.scrollYWhenDown < view.getScrollRange()) {
                updateOverDistance(notificationStackScrollLayout.getScrollRange() - i, "doLayeredOverScroll:isAttachToBottom");
                updateMoveTranslation(this.overDistance);
                flingOver(false, notificationStackScrollLayout, oplusSpringOverScroller, i);
                notificationStackScrollLayout.requestChildrenUpdate$1();
                return notificationStackScrollLayout.getScrollRange();
            }
        }
        return i;
    }

    public void drawBoundaryIfDebug(LogWindowBase.TestPaintBoundary testPaintBoundary, Canvas canvas, Path path) {
        LogWindowBase.Companion.printViewBoundary(testPaintBoundary, canvas, path);
    }

    public final void ensureNsslHiddenWhenElementNotShowing() {
        NotificationStackScrollLayout view;
        if (!getShouldNsslAlphaAnimation() || getPanelAnimationEx().isElementShowing() || this.statusBarStateController.getState() != 0 || (view = getView()) == null || view.getAlpha() == 0.0f || this.appearAnimationManager.isAnimationRunning(view)) {
            return;
        }
        view.setAlpha(0.0f);
    }

    public void flingDown(OplusSpringOverScroller oplusSpringOverScroller, int i, int i2, int i3, float f, int i4) {
        int i5;
        int i6;
        int i7;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            i5 = i2;
            i6 = i3;
            i7 = i4;
            MobileInputLogger$.ExternalSyntheticOutline0.m(i7, "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("flingDown scrollY ", i5, i6, "  velocityY ", " minScrollY "));
        } else {
            i5 = i2;
            i6 = i3;
            i7 = i4;
        }
        oplusSpringOverScroller.fling(i, i5, 1, i6, 0, 0, 0, i7, 0, 0);
    }

    public final void flingOver(boolean z, NotificationStackScrollLayout notificationStackScrollLayout, OplusSpringOverScroller oplusSpringOverScroller, int i) {
        int currVelocity;
        if (!this.hasOverFling) {
            this.hasOverFling = true;
            int topPadding = (SeparateModeHelper.INSTANCE.getSeparateMode() || isOnLockscreenNtfCenter()) ? 0 : notificationStackScrollLayout.getTopPadding() - getQuickQsOffsetHeight();
            int scrollRange = notificationStackScrollLayout.getScrollRange();
            int iAbs = (int) (Math.abs(oplusSpringOverScroller.getCurrVelocity()) / 12.0f);
            if (z) {
                topPadding -= iAbs;
                currVelocity = -((int) oplusSpringOverScroller.getCurrVelocity());
            } else {
                scrollRange += iAbs;
                currVelocity = (int) oplusSpringOverScroller.getCurrVelocity();
            }
            oplusSpringOverScroller.fling(notificationStackScrollLayout.getScrollX(), i, 1, currVelocity, 0, 0, topPadding, scrollRange, 0, 0);
            cancelScrollAnimator();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iAbs, "flingOver overScrollY ", "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification");
            }
        }
        if (oplusSpringOverScroller.getCurrY() == oplusSpringOverScroller.getFinalY()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->flingOver to final");
            }
            oplusSpringOverScroller.forceFinished(true);
            startRebound();
        }
    }

    public void flingUp(OplusSpringOverScroller oplusSpringOverScroller, int i, int i2, int i3, float f, int i4) {
        int i5;
        int i6;
        int i7;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            i5 = i2;
            i6 = i3;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("flingUp scrollY ", i5, i6, " velocityY ", " scrollOffset ");
            sbM.append(f);
            sbM.append(" minScrollY ");
            i7 = i4;
            sbM.append(i7);
            Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->".concat(sbM.toString()));
        } else {
            i5 = i2;
            i6 = i3;
            i7 = i4;
        }
        oplusSpringOverScroller.fling(i, i5, 1, i6, 0, 0, (int) f, i7, 0, 0);
    }

    public int getAppearPositionWhenHasNoVisibleNotification(StackScrollerDecorView stackScrollerDecorView, boolean z, int i, boolean z2) {
        if (z) {
            return 0;
        }
        if (i != 0 || z2) {
            return stackScrollerDecorView.getHeight();
        }
        return 0;
    }

    public final Context getContext() {
        return this.context;
    }

    public int getCurrentStackHeight(NotificationStackScrollLayout notificationStackScrollLayout) {
        NotificationShelf notificationShelf;
        if (notificationStackScrollLayout != null && (notificationShelf = notificationStackScrollLayout.mShelf) != null && notificationShelf.getVisibility() == 0) {
            return (int) notificationStackScrollLayout.mShelf.getY();
        }
        if (notificationStackScrollLayout != null) {
            return notificationStackScrollLayout.mCurrentStackHeight;
        }
        return 0;
    }

    public Set<Function0> getExtraAnimationEventGenAction() {
        return (Set) this.extraAnimationEventGenAction$delegate.getValue();
    }

    public Set<NotificationStackScrollLayout.AnimationEvent> getExtraAnimationEvents() {
        return (Set) this.extraAnimationEvents$delegate.getValue();
    }

    public final boolean getHasMediaPanelOnKg() {
        return this.hasMediaPanelOnKg;
    }

    public final boolean getHasVisibleNotificationRow() {
        return this.hasVisibleNotificationRow;
    }

    public final boolean getHasVisibleUbiquitousRow() {
        return this.hasVisibleUbiquitousRow;
    }

    public final float getLayeringReboundTrans(int i, int i2) {
        float f = this.overDistance;
        float space = 0.0f;
        int i3 = f > 0.0f ? i : (i2 - i) - 1;
        if (this.isParallax) {
            float currentSpringY = this.scrollGridChain.getCurrentSpringY(i);
            if (!Float.isNaN(currentSpringY)) {
                return currentSpringY;
            }
            StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(currentSpringY, i, "getLayeringReboundTrans error index ", " value ", " count ");
            sbM.append(i2);
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, sbM.toString());
            return 0.0f;
        }
        if (f == 0.0f) {
            return 0.0f;
        }
        TransCalculator transCalculator = TransCalculator.INSTANCE;
        if (i3 < 0) {
            transCalculator.getClass();
            return 0.0f;
        }
        int i4 = 0;
        while (true) {
            transCalculator.getClass();
            space += TransCalculator.getSpace(f, i4);
            if (i4 == i3) {
                return space;
            }
            i4++;
        }
    }

    public final float getLockscreenNotificationDisplayHeight(boolean z) {
        if (z) {
            ((NotificationPanelViewController) this.npvc.get()).getExt().updateLockscreenNotificationBottomPadding();
        }
        return ((this.context.getResources().getConfiguration().windowConfiguration.getBounds().height() - ((NotificationPanelViewController) this.npvc.get()).getExt().getKeyguardNotificationBottomPadding()) - ((AmbientState) this.ambientState.get()).getTopPadding()) + ((int) this.shadeTransitionControllerEx.getNotificationDragAmount());
    }

    public int getMinimumPaddingsScrimTop() {
        return this.mMinimumPaddingsScrimTop;
    }

    public Interpolator getOverScrollerInterpolator() {
        return this.overScrollerInterpolator;
    }

    public int getOverY() {
        return 50;
    }

    public int getQuickQsOffsetHeight() {
        return this.mQuickQsOffsetHeight;
    }

    public TimeInterpolator getScrollInterpolator(int i, int i2) {
        int iAbs = Math.abs(i);
        if (iAbs > 20000) {
            iAbs = 20000;
        }
        return new SpringInterpolator(Math.abs(i2) > 400 ? 0.5d : 0.4d, i < -1000 ? 0.2d : 0.0d, iAbs, 6666.0f);
    }

    public int getScrollYWhenDown() {
        return this.scrollYWhenDown;
    }

    public float getStackedBottomBorder() {
        return (this.context.getResources().getConfiguration().windowConfiguration.getBounds().height() - ((NotificationPanelViewController) this.npvc.get()).getExt().getKeyguardNotificationBottomPadding()) + ((int) this.shadeTransitionControllerEx.getNotificationDragAmount());
    }

    public final COUIGridSpringChain.TransCalculator getTransCalculator() {
        return this.transCalculator;
    }

    public /* bridge */ /* synthetic */ float getViewNegativeExpandOffset() {
        return super.getViewNegativeExpandOffset();
    }

    public final boolean hasNonClearAbleSilentNotifs() {
        return ((NotifStats) this.repository.getNotifStats().getValue()).getHasNonClearableSilentNotifs();
    }

    public boolean hookStackScrollLayoutAmbientVisible(AmbientState ambientState) {
        if (!ambientState.isFullyHidden()) {
            return true;
        }
        if (!ambientState.mDozing) {
            return false;
        }
        SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
        if (companion.getInstance(this.context).isPanoramicAod()) {
            return true;
        }
        return ScrimUtil.isLowGaussianLevel(this.context) && !companion.getInstance(this.context).isWorkShopAod();
    }

    public void initView(Context context) {
        Resources resources = context.getResources();
        if (resources != null) {
            this.mMinimumPaddingsScrimTop = resources.getDimensionPixelSize(2131170783);
            this.mQuickQsOffsetHeight = resources.getDimensionPixelSize(2131170763);
        }
        this.statusBarStateController.addCallback(this.stateListener);
        getPanelAnimationEx().getElementShowState().addCallback(this.elementShowStateChangeListener);
        updatePivot();
    }

    public final boolean isAttachToTop(int i) {
        NotificationStackScrollLayout view = getView();
        if (view == null) {
            return false;
        }
        if (SeparateModeHelper.INSTANCE.getSeparateMode() || isOnLockscreenNtfCenter()) {
            return i <= 0;
        }
        int topPadding = view.getTopPadding() - getQuickQsOffsetHeight();
        return i <= topPadding && this.scrollYWhenDown > topPadding;
    }

    public boolean isEmptyShadeViewOnKeyguard(int i, ExpandableView expandableView) {
        return (i == 2 || i == 1) && (expandableView instanceof EmptyShadeView);
    }

    public boolean isInsideKgNtfCenterHeader(MotionEvent motionEvent) {
        OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx = (OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class);
        return oplusLockscreenShadeTransitionControllerEx.isOnLockscreenNtfCenter() && motionEvent.getY() < ((float) oplusLockscreenShadeTransitionControllerEx.getNtfTopPaddingInLockscreenNtfCenter());
    }

    public final boolean isInterceptOnScrollTouchDown() {
        NotificationPanelViewController notificationPanelController;
        EmptyShadeView emptyShadeView;
        NotificationStackScrollLayout view = getView();
        return (view == null || (notificationPanelController = view.getNotificationPanelController()) == null || !notificationPanelController.isFullyExpanded() || (emptyShadeView = this.emptyShadeView) == null || !((StackScrollerDecorView) emptyShadeView).mIsVisible) ? false : true;
    }

    public final boolean isOnLockscreenNtfCenter() {
        return ((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnLockscreenNtfCenter();
    }

    public boolean isOplusCustomRow(View view) {
        boolean z = view instanceof OplusCustomRow;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isOplusCustomRow ", z), null, 8);
        return z;
    }

    public final boolean isOverBorder(int i) {
        NotificationStackScrollLayout view = getView();
        if (view != null) {
            if ((SeparateModeHelper.INSTANCE.getSeparateMode() || isOnLockscreenNtfCenter()) && view.getScrollRange() == 0) {
                return !(this.overDistance == 0.0f);
            }
            if ((this.overDistance < 0.0f && i >= view.getScrollRange()) || (this.overDistance > 0.0f && i < view.getScrollRange())) {
                return true;
            }
        }
        resetOverDistance("isOverBorder");
        return false;
    }

    public final boolean isPanoramicAodShow() {
        OplusPanoramicAodRepository oplusPanoramicAodRepository = this.oplusPanoramicAodRepository;
        return ((OplusPanoramicAodCoreStartable) oplusPanoramicAodRepository).isPanoramicAod && oplusPanoramicAodRepository.getShouldUpdateUi();
    }

    public final boolean isQuickFoldGroup() {
        return !getPanelAnimationEx().isElementShowing() && getPanelAnimationEx().getPanelExpandFraction().getRawFraction() <= 0.05f;
    }

    public final boolean isRunningReboundAnimator() {
        if (!this.isParallax) {
            ValueAnimator valueAnimator = this.overScrollAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return false;
            }
        } else if (this.scrollGridChain.isSpringSystemRunning() <= 0) {
            return false;
        }
        return true;
    }

    public boolean isTextNeedUseLightColorWhenLight(Context context) {
        return QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState());
    }

    public final boolean isViewScaleAnimationRunning() {
        return this.appearAnimationManager.isAnimationRunning(getView());
    }

    public final void notifyUpdateAodState() {
        ((NotificationAodTransition) this.aodTransition.get()).updateStateForSensitivityChanged();
    }

    public final void onCapsuleStateAnimFinished() {
        ((CapsuleCardControl) this.capsuleCardControl.get()).onCapsuleStateAnimFinished();
    }

    public void onGroupExpandChanged(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
        if (childrenContainer == null || (notificationChildrenContainerOverlayEx = childrenContainer.mOverlayEx) == null) {
            return;
        }
        notificationChildrenContainerOverlayEx.onGroupExpandChanged(z);
    }

    public final void onKeyguardNsslVisibilityChanged(boolean z) {
        int i;
        Sequence<View> children;
        if (this.keyguardNsslVisible == z) {
            return;
        }
        this.keyguardNsslVisible = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onKeyguardNsslVisibilityChanged] visible=", "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification", z);
        }
        if (this.statusBarStateController.getState() != 1) {
            return;
        }
        if (z) {
            tryRequestRenderBoosterAfterRenderList("onKeyguardNsslVisibilityChanged");
            return;
        }
        this.needDeferredRenderBooster = false;
        ViewGroup view = getView();
        if (view != null) {
            RenderBoosterStagger.INSTANCE.cancel(view);
        }
        this.rowBoosterBuffer.clear();
        if (this.hasBoosted) {
            NotificationStackScrollLayout view2 = getView();
            if (view2 == null || (children = ConvenienceExtensionsKt.getChildren(view2)) == null) {
                i = 0;
            } else {
                i = 0;
                for (View view3 : children) {
                    if ((view3 instanceof ExpandableNotificationRow) || (view3 instanceof OplusCustomRow)) {
                        RenderBooster renderBooster = RenderBooster.Companion.renderBooster(view3);
                        if (renderBooster != null) {
                            renderBooster.requestStop(0);
                        }
                        i++;
                    }
                }
            }
            this.hasBoosted = false;
            if (i > 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "[stopKeyguardRenderBoosterForAllChildren] reason=onKeyguardNsslVisibilityChanged, count=", "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification");
            }
        }
    }

    public void onRemoveTransientView(View view) {
        ((NotificationAodTransition) this.aodTransition.get()).onRemoveView(view);
    }

    public /* bridge */ /* synthetic */ boolean onSwipeBeginOverride(NotificationStackScrollLayout notificationStackScrollLayout, View view, NotificationSectionsManager notificationSectionsManager, NotificationSection[] notificationSectionArr) {
        return super.onSwipeBeginOverride(notificationStackScrollLayout, view, notificationSectionsManager, notificationSectionArr);
    }

    public void onViewAdded(ExpandableView expandableView) {
        String str = expandableView.mChangingPosition ? "changing position" : "newComing notification";
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", keyguardNsslVisible=", "NotificationStackScrollLayoutExtImpl-->", ConstraintSet$.ExternalSyntheticOutline0.m(expandableView.getVisibility(), "[onViewAdded] ", str, " visibility=", ",isShadeShowing="), getPanelAnimationEx().isShadeShowing(), this.keyguardNsslVisible));
        }
        if (expandableView.mChangingPosition || expandableView.getVisibility() == 8 || !shouldApplyRenderBooster()) {
            return;
        }
        RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, expandableView, isRenderExpensiveView(expandableView), 1), 11, false, 6);
    }

    public void onViewRemoved(ExpandableView expandableView, ViewGroup viewGroup) {
        RenderBooster renderBooster;
        String str = expandableView.mChangingPosition ? "changing position" : "removing notification";
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", keyguardNsslVisible=", "NotificationStackScrollLayoutExtImpl-->", ConstraintSet$.ExternalSyntheticOutline0.m(expandableView.getVisibility(), "[onViewRemoved] ", str, " visibility=", ",isShadeShowing="), getPanelAnimationEx().isShadeShowing(), this.keyguardNsslVisible));
        }
        if (expandableView.mChangingPosition || !shouldApplyRenderBooster() || (renderBooster = RenderBooster.Companion.renderBooster(expandableView)) == null) {
            return;
        }
        renderBooster.requestStop(0);
    }

    public final void playClearAllSound() {
        DeleteSoundHelper deleteSoundHelper = getDeleteSoundHelper();
        ExecutorService executorService = deleteSoundHelper.mSoundEexecutor;
        DeleteSoundHelper$$ExternalSyntheticLambda2 deleteSoundHelper$$ExternalSyntheticLambda2 = new DeleteSoundHelper$$ExternalSyntheticLambda2(0);
        deleteSoundHelper$$ExternalSyntheticLambda2.f$0 = deleteSoundHelper;
        VarHandle.storeStoreFence();
        executorService.execute(deleteSoundHelper$$ExternalSyntheticLambda2);
    }

    public final void requestAnimateKgNotificationStateTransform(int i, AnimationFilter animationFilter) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[requestAnimateKgNotificationStateTransform] type = " + i + ",filter = " + animationFilter, null, 8);
        NotificationStackScrollLayout view = getView();
        if (view != null) {
            StringBuilder sb = new StringBuilder("[requestCustomAnimate]: mIsExpanded=");
            sb.append(view.mIsExpanded);
            sb.append(", mAnimationsEnabled=");
            ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_NOTIFICATION, "StackScroller", sb, view.mAnimationsEnabled);
            if (view.mIsExpanded && view.mAnimationsEnabled) {
                getExtraAnimationEvents().add(new NotificationStackScrollLayout.AnimationEvent((ExpandableNotificationRow) null, i, animationFilter));
                view.mNeedsAnimation = true;
            }
            view.requestChildrenUpdate$1();
        }
    }

    public void requestScreenState(View view, int i) {
        if (view.getVisibility() == 0 && AODDisplayUtil.getInstance(this.context).isPanoramicProcessType()) {
            SmoothTransitionController.Companion.getInstance(this.context).requestScreenStateForSysUiComponent(TAG, 500);
        }
    }

    public void resetAllClearButtonVisibility(int i, NotificationPanelViewController notificationPanelViewController, boolean z) {
        NotificationPanelViewControllerEx ext;
        if (i != 2 || notificationPanelViewController == null || (ext = notificationPanelViewController.getExt()) == null) {
            return;
        }
        ext.onHeightUpdatedExt(notificationPanelViewController.getExpandedHeight(), notificationPanelViewController.getQsController(), z);
    }

    public final void resetOverDistance(String str) {
        float f = this.overDistance;
        if (f == 0.0f) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "resetOverDistance reason = " + str + ",curr=" + f, null, 8);
        this.overDistance = 0.0f;
    }

    public final void resetScrollingEnabled() {
        NotificationPanelViewController notificationPanelController;
        QuickSettingsController qsController;
        setScrollingEnabled(this.statusBarStateController.getState() != 1);
        NotificationStackScrollLayout view = getView();
        if (view == null || (notificationPanelController = view.getNotificationPanelController()) == null || (qsController = notificationPanelController.getQsController()) == null) {
            return;
        }
        qsController.updateQsState();
    }

    public final void resetSpringChain() {
        if (this.isParallax) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "resetSpringChain", null, 8);
            this.scrollGridChain.releaseSpringAndLoc();
            resetOverDistance("onPanelCollapsed");
        }
    }

    public void runAfterExpansionAnimationFinished(ExpandableNotificationRow expandableNotificationRow) {
        NotificationStackScrollLayout view = getView();
        if (view == null) {
            return;
        }
        Runnable runnableRemove = this.mPendingExpansionChangeRunnables.remove(expandableNotificationRow);
        if (runnableRemove != null) {
            view.mAnimationFinishedRunnables.remove(runnableRemove);
        }
        NotificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1 notificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1 = new NotificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1();
        notificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1.this$0 = this;
        notificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1.$changedRow = expandableNotificationRow;
        VarHandle.storeStoreFence();
        this.mPendingExpansionChangeRunnables.put(expandableNotificationRow, notificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1);
        view.runAfterAnimationFinished$1(notificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1);
    }

    public void setChildDismissUsingRowTranslationX(View view, boolean z, NotificationSectionsManager notificationSectionsManager) {
        OplusNotificationSectionsManagerEx notificationSectionsManagerEx;
        if (notificationSectionsManager == null || (notificationSectionsManagerEx = notificationSectionsManager.getNotificationSectionsManagerEx()) == null) {
            return;
        }
        notificationSectionsManagerEx.setDismissUsingRowTranslationX(z);
    }

    public void setClearAll(boolean z) {
        this.isClearAll = z;
    }

    public final void setContentHeightChanged(boolean z) {
        this.isContentHeightChanged = z;
    }

    public void setEmptyShadeView(EmptyShadeView emptyShadeView) {
        this.emptyShadeView = emptyShadeView;
    }

    public void setEmptyShadeViewTextColor(Context context, EmptyShadeView emptyShadeView, int i, int i2) {
        int color = context.getColor(2131102832);
        emptyShadeView.setTextColors(color, color);
    }

    public void setExpandingEnabled(boolean z) {
        NotificationStackScrollLayout view;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion() || (view = getView()) == null) {
            return;
        }
        view.setExpandingEnabled(z);
    }

    public void setFirstChildTranslationY(ExpandableView expandableView, int i, boolean z) {
        int translationY;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion() || this.statusBarStateController.getState() == 1 || expandableView == null || (translationY = (int) expandableView.getTranslationY()) == this.lastFirstChildTranslationY) {
            return;
        }
        this.lastFirstChildTranslationY = translationY;
        if (i == 0 && z) {
            this.oplusNotificationTiltShiftBlurController.setFirstChildTranslationY(translationY);
        }
    }

    public void setFlingState(boolean z) {
        this.hasOverFling = false;
        this.isScrollerFling = z;
    }

    public final void setHasMediaPanelOnKg(boolean z) {
        this.hasMediaPanelOnKg = z;
    }

    public final void setHasVisibleNotificationRow(boolean z) {
        this.hasVisibleNotificationRow = z;
    }

    public final void setHasVisibleUbiquitousRow(boolean z) {
        this.hasVisibleUbiquitousRow = z;
    }

    public void setHorizontalSwitchState(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setHorizontalSwitchState: ", "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification", z);
        }
        this.isHorizontalSwitch = z;
    }

    public void setOverDistanceAndRefresh(float f) {
        updateOverDistance(this.overDistance + f, "setOverDistanceAndRefresh");
        NotificationStackScrollLayout view = getView();
        if (view != null) {
            view.requestChildrenUpdate$1();
        }
        updateMoveTranslation(this.overDistance);
    }

    public void setOverScrollerInterpolatorOverride(TimeInterpolator timeInterpolator) {
        this.overScrollerInterpolatorOverride = timeInterpolator;
    }

    public void setOwnScrollY(int i) {
        this.oplusNotificationTiltShiftBlurController.setOwnScrollY(i);
    }

    public void setScrollToDefaultOrTopPositionState(boolean z) {
        this.isScrollToDefaultOrTopPosition = z;
    }

    public void setScrollingEnabled(boolean z) {
        NotificationStackScrollLayout view = getView();
        if (view != null) {
            view.setScrollingEnabled(z);
        }
    }

    public void setShowDismissExt(boolean z) {
        getClearAllController().setShowDismiss(z);
    }

    public void setStackScrollerOverScrolling(boolean z) {
        this.mStackScrollerOverScrolling = z;
    }

    public void setStatusBarState(int i) {
        EmptyShadeView emptyShadeView;
        if (i != 1 || (emptyShadeView = this.emptyShadeView) == null) {
            return;
        }
        emptyShadeView.setVisible(false, false, (NotificationListViewBinder.bindEmptyShade.2.2.1.1) null);
    }

    public void setWindowInsetChangedListener(Function0 function0) {
        this.windowInsetChangedListener = function0;
    }

    public final boolean shouldApplyRenderBooster() {
        if (getPanelAnimationEx().isShadeShowing()) {
            return this.keyguardNsslVisible || this.statusBarStateController.getState() != 1;
        }
        return false;
    }

    public final void shouldExposeCustomRow(boolean z) {
        ExpandableViewState viewState;
        CustomRowEntry entry;
        if (((Boolean) this.windowRootViewVisibilityInteractor.isLockscreenOrShadeVisibleAndInteractive().getValue()).booleanValue()) {
            for (OplusCustomRowController oplusCustomRowController : getCustomRowManager().getOplusCustomRowControllerList()) {
                LaunchableView oplusCustomRow = oplusCustomRowController.getOplusCustomRow();
                if (oplusCustomRow != null && (viewState = oplusCustomRow.getViewState()) != null && (viewState.location & 5) != 0 && oplusCustomRow.getVisibility() == 0 && (entry = oplusCustomRowController.getEntry()) != null) {
                    CustomRowTrackLinkHelper.Companion.getInstance().onExpose(entry, z);
                }
            }
        }
    }

    public boolean shouldSkipHeightUpdate(AmbientState ambientState) {
        NotificationPanelViewController notificationPanelController;
        NotificationPanelViewControllerEx ext;
        if (ambientState != null && ambientState.isOnKeyguard() && ambientState.mIsSwipingUp) {
            return true;
        }
        NotificationStackScrollLayout view = getView();
        if (view == null || (notificationPanelController = view.getNotificationPanelController()) == null || (ext = notificationPanelController.getExt()) == null) {
            return false;
        }
        return ext.canScaleFadePanelAtExpandedHeight(notificationPanelController.getExpandedHeight());
    }

    public boolean shouldSuppressPromotionAnimations(ExpandableView expandableView, String str) {
        if (expandableView instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (expandableNotificationRow.getEntry().getAttachState().getSuppressPromotionAnimations()) {
                expandableNotificationRow.getEntry().getAttachState().setSuppressPromotionAnimations(false);
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    return true;
                }
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("should suppress promotion animations, from: ", str, "  key: ", expandableNotificationRow.getKey(), "NotificationStackScrollLayoutExtImpl-->"));
                return true;
            }
        }
        return false;
    }

    public final void startRebound() {
        float f = this.overDistance;
        int i = f > 0.0f ? 1 : 2;
        int i2 = this.scrollGridCount;
        StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, i, "startRebound overDistance ", " direction() ", " scrollGridCount ");
        sbM.append(i2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (this.isParallax) {
            if (this.scrollGridCount > 0) {
                this.scrollGridChain.startRebound(this.overDistance > 0.0f ? 1 : 2);
                Iterator it = this.scrollGridChain.getSpringChainY().getAllSprings().iterator();
                while (it.hasNext()) {
                    ((Spring) it.next()).setRestSpeedThreshold(3.0d);
                }
            }
            resetOverDistance("startRebound:isParallax");
            return;
        }
        ValueAnimator valueAnimator = this.overScrollAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        float f2 = this.overDistance;
        startRebound.2 r7 = new startRebound.2(1, this, NotificationStackScrollLayoutExtImpl.class, "animatorUpdate", "animatorUpdate(F)V", 0);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, 0.0f);
        valueAnimatorOfFloat.setDuration(850L);
        valueAnimatorOfFloat.setInterpolator(REBOUND_INTERPOLATOR);
        getValueAnimator.1.1 r1 = new getValueAnimator.1.1();
        r1.$startTime = jCurrentAnimationTimeMillis;
        r1.$animator = valueAnimatorOfFloat;
        r1.$startValue = f2;
        r1.$update = r7;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r1);
        this.overScrollAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.addListener(new startRebound.3.1(this));
        valueAnimatorOfFloat.start();
    }

    public int targetScrollForView(NotificationStackScrollLayout notificationStackScrollLayout, ExpandableView expandableView, int i) {
        NotificationContentView privateLayout;
        RemoteInputView expandedRemoteInput;
        View viewFindViewById;
        int imeInset = (notificationStackScrollLayout.getImeInset() + (expandableView.getIntrinsicHeight() + i)) - notificationStackScrollLayout.getHeight();
        if (!notificationStackScrollLayout.mIsExpanded && NotificationStackScrollLayout.isPinnedHeadsUp(expandableView)) {
            NotificationPanelViewControllerEx ext = notificationStackScrollLayout.getNotificationPanelController().getExt();
            ext.getClass();
            if ((ext instanceof NotificationPanelViewControllerExImp) && !notificationStackScrollLayout.isHeadsUpTransition()) {
                imeInset += Math.max(0, (int) notificationStackScrollLayout.mAmbientState.getExt().getHeadTrackingHeight());
            }
            return imeInset + notificationStackScrollLayout.mHeadsUpInset;
        }
        FeatureOption.INSTANCE.getClass();
        Integer numValueOf = null;
        if (!FeatureOption.isTablet() && this.context.getResources().getConfiguration().orientation == 2) {
            ExpandableNotificationRow expandableNotificationRow = expandableView instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) expandableView : null;
            if (expandableNotificationRow != null && (privateLayout = expandableNotificationRow.getPrivateLayout()) != null && (expandedRemoteInput = privateLayout.getExpandedRemoteInput()) != null && expandedRemoteInput.isActive() && expandedRemoteInput.isAttachedToWindow() && expandedRemoteInput.getVisibility() == 0 && (viewFindViewById = expandedRemoteInput.findViewById(2131364883)) != null && viewFindViewById.getHeight() > 0) {
                int[] iArr = new int[2];
                expandableView.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewFindViewById.getLocationOnScreen(iArr2);
                int height = (viewFindViewById.getHeight() + iArr2[1]) - iArr[1];
                if (height > 0) {
                    numValueOf = Integer.valueOf(height);
                }
            }
        }
        if (numValueOf != null) {
            imeInset = (notificationStackScrollLayout.getImeInset() + numValueOf.intValue()) - notificationStackScrollLayout.getHeight();
        }
        return notificationStackScrollLayout.getTopPadding() + imeInset;
    }

    public final void tryRequestRenderBoosterAfterRenderList(String str) {
        if (this.choreographer.isScheduled) {
            this.needDeferredRenderBooster = true;
            return;
        }
        this.needDeferredRenderBooster = false;
        if (shouldApplyRenderBooster()) {
            applyRenderBoosterForVisibleChildren(str, this.isHorizontalSwitch);
        }
    }

    public final void updateClearAllPosition(int i, boolean z) {
        getClearAllController().updateClearAllPosition(i, z);
    }

    public /* bridge */ /* synthetic */ void updateColors(FooterView footerView) {
        super.updateColors(footerView);
    }

    public void updateHasVisibleKeyguardElement(NotificationStackScrollLayout notificationStackScrollLayout) {
        int childCount = notificationStackScrollLayout.getChildCount();
        this.hasVisibleUbiquitousRow = false;
        this.hasMediaPanelOnKg = false;
        this.hasVisibleNotificationRow = false;
        for (int i = 0; i < childCount; i++) {
            LaunchableView childAt = notificationStackScrollLayout.getChildAt(i);
            boolean z = true;
            if (childAt instanceof MediaContainerView) {
                MediaContainerView mediaContainerView = (MediaContainerView) childAt;
                this.hasMediaPanelOnKg = mediaContainerView.isAttachedToWindow() && mediaContainerView.getVisibility() == 0;
            } else if (childAt instanceof OplusCustomRow) {
                if (!this.hasVisibleUbiquitousRow) {
                    LaunchableView launchableView = (OplusCustomRow) childAt;
                    ExpandableViewState viewState = launchableView.getViewState();
                    if ((launchableView.getVisibility() != 0 || ((ExpandableView) launchableView).mWillBeGone) && (((ViewState) viewState).hidden || ((ViewState) viewState).gone)) {
                        z = false;
                    }
                    this.hasVisibleUbiquitousRow = z;
                }
            } else if (childAt instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) childAt;
                ExpandableViewState viewState2 = expandableNotificationRow.getViewState();
                if ((expandableNotificationRow.getVisibility() != 0 || ((ExpandableView) expandableNotificationRow).mWillBeGone) && (((ViewState) viewState2).hidden || ((ViewState) viewState2).gone)) {
                    z = false;
                }
                this.hasVisibleNotificationRow = z;
                if (z) {
                    return;
                }
            } else {
                continue;
            }
        }
    }

    public final void updateMoveTranslation(float f) {
        if (this.isParallax) {
            int iIsSpringSystemRunning = this.scrollGridChain.isSpringSystemRunning();
            if (iIsSpringSystemRunning > 0) {
                this.scrollGridChain.releaseSpringComplete();
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updateMoveTranslation releaseSpringComplete hasSpring " + iIsSpringSystemRunning + " distance " + f, null, 8);
            }
            this.scrollGridChain.updateMoveTranslation(f, this.overDistance > 0.0f ? 1 : 2);
        }
    }

    public final void updateOverDistance(float f, String str) {
        if (f == this.overDistance) {
            return;
        }
        if (SystemClock.elapsedRealtime() - this.lastLogDistanceTimeMs > 100 || Math.abs(this.overDistance - f) > 50.0f) {
            this.lastLogDistanceTimeMs = SystemClock.elapsedRealtime();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m("updateOverDistance reason = ", str, ",curr=", this.overDistance, ",new="), f, "NotificationStackScrollLayoutExtImpl-->", "SystemUi--Notification");
            }
        }
        this.overDistance = f;
    }

    public void updatePanelHeight(NotificationPanelViewController notificationPanelViewController) {
        NotificationPanelViewControllerEx ext;
        if (this.isContentHeightChanged && notificationPanelViewController != null && (ext = notificationPanelViewController.getExt()) != null) {
            ext.postRequestPanelHeightUpdateAction();
        }
        this.isContentHeightChanged = false;
    }

    public final void updatePivot() {
        NotificationStackScrollLayout view = getView();
        if (view != null) {
            if (isViewScaleAnimationRunning() && SeparateModeHelper.INSTANCE.getSeparateMode()) {
                view.setPivotX(view.getWidth() / 2.0f);
                view.setPivotY(getShouldNsslAlphaAnimation() ? -300.0f : -600.0f);
            } else {
                NotificationStackScrollLayoutExtImpl$updatePivot$1$1 notificationStackScrollLayoutExtImpl$updatePivot$1$1 = new NotificationStackScrollLayoutExtImpl$updatePivot$1$1();
                notificationStackScrollLayoutExtImpl$updatePivot$1$1.$it = view;
                VarHandle.storeStoreFence();
                view.post(notificationStackScrollLayoutExtImpl$updatePivot$1$1);
            }
        }
    }

    public final void updateScrollGridCount() {
        NotificationStackScrollLayout view;
        if (!this.isParallax || (view = getView()) == null || this.scrollGridCount == view.getVisibleChildrenCount()) {
            return;
        }
        int i = this.scrollGridCount;
        int visibleChildrenCount = view.getVisibleChildrenCount();
        int childCount = view.getChildCount();
        float f = this.overDistance;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("bind scrollGridCount ", i, visibleChildrenCount, " o.visibleChildrenCount ", " o.childCount ");
        sbM.append(childCount);
        sbM.append(" overDistance:");
        sbM.append(f);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        this.scrollGridChain.clearAllItems();
        int visibleChildrenCount2 = view.getVisibleChildrenCount();
        this.scrollGridCount = visibleChildrenCount2;
        for (int i2 = 0; i2 < visibleChildrenCount2; i2++) {
            this.scrollGridChain.addItem(new COUIChainItem((View) null, 0, i2, 1, 1, false));
        }
        float f2 = this.overDistance;
        if (f2 == 0.0f) {
            return;
        }
        this.scrollGridChain.updateMoveTranslation(f2, f2 > 0.0f ? 1 : 2);
    }

    public final boolean useLayeredAnimation(boolean z) {
        return !z && FeatureOption.getUseLayeredPanelAnimation();
    }

    public NotificationStackScrollLayout getView() {
        return this.view;
    }

    public void setView(NotificationStackScrollLayout notificationStackScrollLayout) {
        this.view = notificationStackScrollLayout;
    }
}
