package com.oplus.systemui.separate;

import android.content.res.Configuration;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.compose.animation.core.MonoSpline$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.DependencyEx;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.notification.NotificationRowShowAnimationEx;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.PanelType;
import com.android.systemui.shade.QuickSettingsControllerEx;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.base.util.SeparateStaticInfo;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.separate.animation.OplusSlidSwitchAnimation;
import com.oplus.systemui.separate.data.repository.OplusPanelModeRepository;
import com.oplus.systemui.separate.event.OplusAxisSwipeDetector;
import com.oplus.systemui.separate.event.OplusSwipeDetector;
import com.oplus.systemui.separate.event.QueueEventProcesser;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.helper.PanelPullHelper;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPagerController extends ViewController implements OplusAxisSwipeDetector.ScrollListener {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusPanelViewPagerController";
    private CentralSurfaces centralSurfaces;
    public float currentNotifyFraction;
    public float currentQsFraction;
    private OplusAxisSwipeDetector detector;
    private SeparateQSStatisicUtil.EXPANDED downExpanded;
    public boolean downOnRight;
    public boolean downOnYHotArea;
    public boolean forceIntercept;
    private final Lazy fractionAnimation$delegate;
    private final Set<OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1> fractionListeners;
    public boolean hasCalledDownEvent;
    public boolean hasDispatchedEventToChild;
    public float initTranslationX;
    public boolean isLastUpToExpand;
    public boolean isOverScroll;
    public boolean isTargetPageUpdate;
    private final KeyguardBypassController keyguardBypassController;
    private final KeyguardStateController keyguardStateController;
    private SeparateQSStatisicUtil.EXPANDED lastDownExpanded;
    private final dagger.Lazy levelPositionAlgorithm;
    private final QsCenterModeSelectDialogController modeSelectGuideDialog;
    public float moveFraction;
    private NotificationHotAreaHelper notificationHotAreaHelper;
    private NotificationPanelView notificationPanelView;
    private final NotificationPanelViewController notificationPanelViewController;
    private final NotificationStackScrollLayoutController notificationStackScrollLayoutController;
    private final IOplusGcSupressionExt oplusGcSupressionExt;
    private final OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionController;
    private final PanelAnimationEx panelAnimationEx;
    private final int[] panelHitTestLoc;
    private final OplusPanelModeRepository panelModeRepository;
    private final dagger.Lazy pullHelper;
    private View qsPanelView;
    private QueueEventProcesser.OplusQueueMotionEvent queueDownEvent;
    private final QuickSettingsControllerEx quickSettingsControllerEx;
    private final QuickSettingsControllerImpl quickSettingsControllerImpl;
    public float screenHeight;
    public float screenWidth;
    private final OplusSeparateQSManager separateQSManager;
    private final OplusPanelViewPagerController$separateQSPluginListener$1 separateQSPluginListener;
    private final OplusSlidSwitchAnimation slidSwitchAnimation;
    private SeparateStaticInfo staticInfo;
    public int statusBarHeight;
    private final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public int statusBarLocationOnScreenY;
    private final StatusBarStateController statusBarStateController;
    private final OplusPanelViewPagerController$statusBarStateListener$1 statusBarStateListener;
    private final OplusPanelViewPagerController$sysStateControllerListener$1 sysStateControllerListener;
    private final SysUiStateControllerImpl sysUiStateController;
    private PageType targetPage;
    private final Function2 translationChangedCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PageType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ PageType[] $VALUES;
        public static final PageType NOTIFICATION;
        public static final PageType QS;

        static {
            PageType pageType = new PageType("NOTIFICATION", 0);
            NOTIFICATION = pageType;
            PageType pageType2 = new PageType(OplusFakeViewProvider.QS, 1);
            QS = pageType2;
            PageType[] pageTypeArr = {pageType, pageType2};
            $VALUES = pageTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(pageTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static PageType valueOf(String str) {
            return (PageType) Enum.valueOf(PageType.class, str);
        }

        public static PageType[] values() {
            return (PageType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TouchHandler implements Gefingerpoken {
        public boolean ignoringOccludeGesture;
        public long lastTouchDownTime;
        public /* synthetic */ OplusPanelViewPagerController this$0;

        public final boolean ignoreTouchEventInOcclude(MotionEvent motionEvent) {
            boolean zIsFullyCollapsed;
            if (this.ignoringOccludeGesture) {
                if (motionEvent.getActionMasked() != 1 && motionEvent.getActionMasked() != 3) {
                    return true;
                }
                this.ignoringOccludeGesture = false;
                return true;
            }
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            if (motionEvent.getActionMasked() == 0) {
                if (((Boolean) oplusPanelViewPagerController.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                    zIsFullyCollapsed = oplusPanelViewPagerController.notificationPanelViewController.isFullyCollapsed() && oplusPanelViewPagerController.separateQSManager.isFullyCollapsed();
                } else {
                    zIsFullyCollapsed = oplusPanelViewPagerController.notificationPanelViewController.isFullyCollapsed();
                }
                if (oplusPanelViewPagerController.keyguardStateController.mOccluded && motionEvent.getRawY() > oplusPanelViewPagerController.statusBarHeight && (oplusPanelViewPagerController.notificationPanelViewController.isOnKeyguard() || zIsFullyCollapsed)) {
                    this.ignoringOccludeGesture = true;
                    return true;
                }
            }
            return false;
        }

        public final boolean isDisablePullQS() {
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            CentralSurfacesImpl centralSurfacesImpl = oplusPanelViewPagerController.centralSurfaces;
            return centralSurfacesImpl != null && centralSurfacesImpl.isKeyguardShowing() && ((PanelPullHelper) oplusPanelViewPagerController.pullHelper.get()).getDisablePullStatusBar(Boolean.FALSE);
        }

        /* JADX WARN: Code duplicated, block: B:147:0x028d  */
        /* JADX WARN: Code duplicated, block: B:149:0x0298  */
        /* JADX WARN: Code duplicated, block: B:151:0x029e  */
        /* JADX WARN: Code duplicated, block: B:153:0x02a9  */
        /* JADX WARN: Code duplicated, block: B:167:0x02d9  */
        /* JADX WARN: Code duplicated, block: B:168:0x02dc  */
        /* JADX WARN: Code duplicated, block: B:82:0x01a3  */
        /* JADX WARN: Code duplicated, block: B:84:0x01a9  */
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            PageType pageType;
            float f;
            boolean z;
            OplusAxisSwipeDetector.ScrollCondition yScroll;
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            if (motionEvent.getActionMasked() == 0 && OplusPanelViewPagerController.access$isMultipleFingersEventFromShade(oplusPanelViewPagerController, motionEvent)) {
                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "intercept multiple fingers event: " + motionEvent, null, 8);
                return true;
            }
            if (ignoreTouchEventInOcclude(motionEvent)) {
                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "ignoreTouchEventInOcclude", null, 8);
                return true;
            }
            if (oplusPanelViewPagerController.panelModeRepository.resolveSeparateMode() && !oplusPanelViewPagerController.oplusLockscreenShadeTransitionController.isOnLockscreenNtfCenter() && !oplusPanelViewPagerController.oplusLockscreenShadeTransitionController.isAnimatingToCollapse() && !oplusPanelViewPagerController.oplusLockscreenShadeTransitionController.isAnimatingToExpand()) {
                if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
                    reset$6();
                }
                if (motionEvent.getActionMasked() == 0) {
                    OplusPanelViewPagerController.access$onActionDown(oplusPanelViewPagerController, motionEvent);
                    oplusPanelViewPagerController.hasCalledDownEvent = true;
                    OplusSlidSwitchAnimation.OverScrollTranslationValue overScrollTranslationValue = oplusPanelViewPagerController.slidSwitchAnimation.getOverScrollTranslationValue();
                    if (overScrollTranslationValue.getProgressiveController().isRubberbandRunning()) {
                        overScrollTranslationValue.getProgressiveController().skipRubberbandToEnd();
                    }
                    oplusPanelViewPagerController.downOnRight = oplusPanelViewPagerController.isRightArea(motionEvent.getRawX());
                    oplusPanelViewPagerController.notificationPanelViewController.setInitialTouchFromKeyguard(oplusPanelViewPagerController.keyguardStateController.mShowing);
                    OplusAxisSwipeDetector oplusAxisSwipeDetector = oplusPanelViewPagerController.detector;
                    oplusPanelViewPagerController.downOnYHotArea = (oplusAxisSwipeDetector == null || (yScroll = oplusAxisSwipeDetector.getYScroll()) == null) ? false : yScroll.isHotArea(new PointF(motionEvent.getX(), motionEvent.getY()));
                    if (oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().isLogicRunning() && oplusPanelViewPagerController.downOnYHotArea) {
                        oplusPanelViewPagerController.forceIntercept = true;
                        QueueEventProcesser.Companion.getClass();
                        QueueEventProcesser.queueGuideList.clear();
                        oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().setBlocking(true);
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onInterceptTouchEvent return true for animation block", null, 8);
                    } else if (oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().isLogicRunning()) {
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onInterceptTouchEvent finishTranslation", null, 8);
                        oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().setBlocking(false);
                    }
                    if (oplusPanelViewPagerController.fractionListeners.size() > 0) {
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onInterceptTouchEvent fractionListeners.size > 0", null, 8);
                        oplusPanelViewPagerController.updateNotifyExpandFraction(oplusPanelViewPagerController.currentNotifyFraction > 0.5f ? 1.0f : 0.0f, true);
                        float f2 = oplusPanelViewPagerController.currentQsFraction;
                        oplusPanelViewPagerController.updateQSExpandFraction(f2 > 0.5f ? 1.0f : 0.0f, f2 > 0.5f, true, f2 > 0.5f ? oplusPanelViewPagerController.getScreenHeight() : 0.0f);
                    }
                    oplusPanelViewPagerController.initExpanded(motionEvent);
                    if (oplusPanelViewPagerController.isKeyguardVisible$1() || !oplusPanelViewPagerController.downOnYHotArea) {
                        float f3 = oplusPanelViewPagerController.isKeyguardVisible$1() ? 0.0f : 0.5f;
                        if (oplusPanelViewPagerController.getDownOnQsArea()) {
                            if (oplusPanelViewPagerController.getDownOnQsArea()) {
                                f = oplusPanelViewPagerController.currentNotifyFraction;
                                if (f <= f3) {
                                }
                            }
                            z = false;
                        } else {
                            float f4 = oplusPanelViewPagerController.currentQsFraction;
                            if (f4 <= f3 || f4 >= 1.0f || oplusPanelViewPagerController.isLastUpToExpand) {
                                if (oplusPanelViewPagerController.getDownOnQsArea()) {
                                    f = oplusPanelViewPagerController.currentNotifyFraction;
                                    if (f <= f3 && f < 1.0f && !oplusPanelViewPagerController.isLastUpToExpand) {
                                        oplusPanelViewPagerController.queueDownEvent.setValid(true);
                                        oplusPanelViewPagerController.queueDownEvent.setDownEvent(MotionEvent.obtain(motionEvent));
                                        oplusPanelViewPagerController.queueDownEvent.setPointerIndex(motionEvent.getPointerId(0));
                                    }
                                }
                                z = false;
                            } else {
                                oplusPanelViewPagerController.queueDownEvent.setValid(true);
                                oplusPanelViewPagerController.queueDownEvent.setDownEvent(MotionEvent.obtain(motionEvent));
                                oplusPanelViewPagerController.queueDownEvent.setPointerIndex(motionEvent.getPointerId(0));
                            }
                            z = true;
                        }
                    } else {
                        z = false;
                    }
                    oplusPanelViewPagerController.forceIntercept = z;
                    if (z) {
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "forceIntercept", null, 8);
                        return true;
                    }
                    if (OplusPanelViewPagerController.access$shouldInterceptWhileQSExpandedOnKeyguard(oplusPanelViewPagerController) || OplusPanelViewPagerController.access$canScrollToQSOnKeyguard(oplusPanelViewPagerController)) {
                        oplusPanelViewPagerController.targetPage = PageType.QS;
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "QSInterceptOnKeyguard", null, 8);
                        CentralSurfacesImpl centralSurfacesImpl = oplusPanelViewPagerController.centralSurfaces;
                        if (centralSurfacesImpl != null) {
                            centralSurfacesImpl.userActivity();
                        }
                        ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                }
                if (oplusPanelViewPagerController.isTargetPageUpdate && motionEvent.getActionMasked() != 0 && oplusPanelViewPagerController.downExpanded == SeparateQSStatisicUtil.EXPANDED.NONE) {
                    if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                        oplusPanelViewPagerController.isTargetPageUpdate = false;
                        return false;
                    }
                } else if (oplusPanelViewPagerController.statusBarStateController.getState() != 0 || this.ignoringOccludeGesture || oplusPanelViewPagerController.shouldBlockOccludedHorizontalQsExpand() || ((oplusPanelViewPagerController.isKeyguardVisible$1() && !oplusPanelViewPagerController.notificationPanelViewController.isOnKeyguard()) || oplusPanelViewPagerController.downExpanded == SeparateQSStatisicUtil.EXPANDED.NONE || oplusPanelViewPagerController.separateQSManager.isCustomizing())) {
                    oplusPanelViewPagerController.isTargetPageUpdate = true;
                    if (OplusPanelViewPagerController.access$shouldOpenNotification(oplusPanelViewPagerController, motionEvent)) {
                        oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "Intercept because shouldOpenNotification", null, 8);
                        return true;
                    }
                    if (OplusPanelViewPagerController.access$shouldOpenQS(oplusPanelViewPagerController, motionEvent)) {
                        oplusPanelViewPagerController.targetPage = PageType.QS;
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "Intercept because shouldOpenQS", null, 8);
                        return true;
                    }
                    if (oplusPanelViewPagerController.isKeyguardVisible$1()) {
                        oplusPanelViewPagerController.hasCalledDownEvent = true;
                        if (oplusPanelViewPagerController.currentNotifyFraction == 0.0f) {
                            oplusPanelViewPagerController.initExpanded(motionEvent);
                            LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "tounchOnKeyguardWithoutDownEvent", null, 8);
                            if (oplusPanelViewPagerController.getDownOnQsArea()) {
                                pageType = PageType.QS;
                            } else {
                                pageType = PageType.NOTIFICATION;
                            }
                            oplusPanelViewPagerController.targetPage = pageType;
                        }
                    }
                } else if (!isDisablePullQS()) {
                    OplusAxisSwipeDetector oplusAxisSwipeDetector2 = oplusPanelViewPagerController.detector;
                    if (oplusAxisSwipeDetector2 != null) {
                        oplusAxisSwipeDetector2.onTouchEvent(motionEvent);
                    }
                    OplusAxisSwipeDetector oplusAxisSwipeDetector3 = oplusPanelViewPagerController.detector;
                    if (oplusAxisSwipeDetector3 != null && oplusAxisSwipeDetector3.isDraggingOrSettlingOrBlocking()) {
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onInterceptTouchEvent return true for isDraggingOrSettling", null, 8);
                        return true;
                    }
                    oplusPanelViewPagerController.isTargetPageUpdate = true;
                    if (OplusPanelViewPagerController.access$shouldOpenNotification(oplusPanelViewPagerController, motionEvent)) {
                        oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "Intercept because shouldOpenNotification", null, 8);
                        return true;
                    }
                    if (OplusPanelViewPagerController.access$shouldOpenQS(oplusPanelViewPagerController, motionEvent)) {
                        oplusPanelViewPagerController.targetPage = PageType.QS;
                        LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "Intercept because shouldOpenQS", null, 8);
                        return true;
                    }
                    if (oplusPanelViewPagerController.isKeyguardVisible$1() && !oplusPanelViewPagerController.hasCalledDownEvent && motionEvent.getActionMasked() == 2) {
                        oplusPanelViewPagerController.hasCalledDownEvent = true;
                        if (oplusPanelViewPagerController.currentNotifyFraction == 0.0f && oplusPanelViewPagerController.currentQsFraction == 0.0f) {
                            oplusPanelViewPagerController.initExpanded(motionEvent);
                            LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "tounchOnKeyguardWithoutDownEvent", null, 8);
                            if (oplusPanelViewPagerController.getDownOnQsArea()) {
                                pageType = PageType.QS;
                            } else {
                                pageType = PageType.NOTIFICATION;
                            }
                            oplusPanelViewPagerController.targetPage = pageType;
                        }
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Code duplicated, block: B:107:0x01bc  */
        /* JADX WARN: Code duplicated, block: B:109:0x01c4  */
        /* JADX WARN: Code duplicated, block: B:111:0x01ce  */
        /* JADX WARN: Code duplicated, block: B:114:0x01f6  */
        /* JADX WARN: Code duplicated, block: B:117:0x0214  */
        /* JADX WARN: Code duplicated, block: B:119:0x021c  */
        /* JADX WARN: Code duplicated, block: B:121:0x0226  */
        /* JADX WARN: Code duplicated, block: B:125:0x024b  */
        /* JADX WARN: Code duplicated, block: B:128:0x0257  */
        /* JADX WARN: Instruction removed from duplicated block: B:114:0x01f6, please report this as an issue */
        public boolean onTouchEvent(MotionEvent motionEvent) {
            OplusAxisSwipeDetector oplusAxisSwipeDetector;
            OplusAxisSwipeDetector oplusAxisSwipeDetector2;
            boolean z;
            int iFindPointerIndex;
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            if (OplusPanelViewPagerController.access$isMultipleFingersEventFromShade(oplusPanelViewPagerController, motionEvent)) {
                if (motionEvent.getActionMasked() != 2) {
                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onTouchEvent, is multiple event: " + motionEvent, null, 8);
                    return true;
                }
            } else if (!ignoreTouchEventInOcclude(motionEvent)) {
                if (((Boolean) oplusPanelViewPagerController.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                    CentralSurfacesImpl centralSurfacesImpl = oplusPanelViewPagerController.centralSurfaces;
                    if ((centralSurfacesImpl == null || centralSurfacesImpl.isKeyguardShowing()) && motionEvent.getAction() != 3 && motionEvent.getAction() != 1 && GlobalUtils.getAndResetInterceptShadeTouchEvent() && GlobalUtils.hasDialogShowing() && !GlobalUtils.sIsMediaOutputFromOutside) {
                        View rootView = ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).getRootView();
                        GlobalUtils.sendCancelMotionEvent(rootView instanceof ViewGroup ? (ViewGroup) rootView : null, "onTouchEvent");
                        return false;
                    }
                    if (motionEvent.getActionMasked() == 0) {
                        if (motionEvent.getDownTime() == this.lastTouchDownTime) {
                            LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onTouch: duplicate down event detected... ignoring", null, 8);
                            return true;
                        }
                        this.lastTouchDownTime = motionEvent.getDownTime();
                    }
                    OplusAxisSwipeDetector oplusAxisSwipeDetector3 = oplusPanelViewPagerController.detector;
                    if (oplusAxisSwipeDetector3 != null && oplusAxisSwipeDetector3.isDraggingOrSettling()) {
                        OplusAxisSwipeDetector oplusAxisSwipeDetector4 = oplusPanelViewPagerController.detector;
                        if (oplusAxisSwipeDetector4 != null) {
                            oplusAxisSwipeDetector4.onTouchEvent(motionEvent);
                            return true;
                        }
                    } else if (oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().getBlocking()) {
                        oplusAxisSwipeDetector = oplusPanelViewPagerController.detector;
                        if (oplusAxisSwipeDetector != null && oplusAxisSwipeDetector.isLastDragX() && (oplusAxisSwipeDetector2 = oplusPanelViewPagerController.detector) != null) {
                            oplusAxisSwipeDetector2.onTouchEvent(motionEvent);
                        }
                    } else {
                        if (motionEvent.getActionMasked() != 3 && motionEvent.getActionMasked() != 1) {
                            if (oplusPanelViewPagerController.forceIntercept && motionEvent.getActionMasked() == 2 && oplusPanelViewPagerController.queueDownEvent.isValid && (iFindPointerIndex = motionEvent.findPointerIndex(oplusPanelViewPagerController.queueDownEvent.getPointerIndex())) != -1 && motionEvent.getY(iFindPointerIndex) - oplusPanelViewPagerController.queueDownEvent.getDownEvent().getY() < 0.0f) {
                                oplusPanelViewPagerController.queueDownEvent.setDispatchDownEvent(true);
                                oplusPanelViewPagerController.forceIntercept = false;
                            }
                            z = oplusPanelViewPagerController.forceIntercept;
                        } else if (oplusPanelViewPagerController.forceIntercept) {
                            reset$6();
                            z = true;
                        } else {
                            reset$6();
                            z = false;
                        }
                        if (z) {
                            oplusAxisSwipeDetector = oplusPanelViewPagerController.detector;
                            if (oplusAxisSwipeDetector != null) {
                                oplusAxisSwipeDetector2.onTouchEvent(motionEvent);
                            }
                        } else {
                            boolean zOnInterceptTouchEvent = onInterceptTouchEvent(motionEvent);
                            OplusAxisSwipeDetector oplusAxisSwipeDetector5 = oplusPanelViewPagerController.detector;
                            if (oplusAxisSwipeDetector5 == null || !oplusAxisSwipeDetector5.isDraggingOrSettling()) {
                                if (zOnInterceptTouchEvent) {
                                    OplusPanelViewPagerController.access$showTargetPage(oplusPanelViewPagerController);
                                }
                                if (!OplusSceneModeObserver.Companion.getInstance(oplusPanelViewPagerController.getContext()).isInBrickMode || oplusPanelViewPagerController.isKeyGuardShowing()) {
                                    CentralSurfacesImpl centralSurfacesImpl2 = oplusPanelViewPagerController.centralSurfaces;
                                    if (centralSurfacesImpl2 != null) {
                                        CentralSurfacesImpl centralSurfacesImpl3 = centralSurfacesImpl2;
                                        if (centralSurfacesImpl3.mBouncerShowing && centralSurfacesImpl3.mStatusBarKeyguardViewManager.primaryBouncerNeedsScrimming()) {
                                            if (motionEvent.getActionMasked() == 0) {
                                                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "isBouncerShowingScrimmed, return", null, 8);
                                            }
                                            if (oplusPanelViewPagerController.hasDispatchedEventToChild) {
                                                oplusPanelViewPagerController.hasDispatchedEventToChild = false;
                                                MotionEvent motionEventObtain = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 3, 0.0f, 0.0f, 0);
                                                if (oplusPanelViewPagerController.targetPage == PageType.QS) {
                                                    OplusSeparateQSManager oplusSeparateQSManager = oplusPanelViewPagerController.separateQSManager;
                                                    motionEventObtain.getClass();
                                                    oplusSeparateQSManager.dispatchTouchEvent(motionEventObtain);
                                                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "dispatchCancelToTargetChild to QS", null, 8);
                                                }
                                                motionEventObtain.recycle();
                                                return false;
                                            }
                                        } else {
                                            if (oplusPanelViewPagerController.targetPage == PageType.NOTIFICATION) {
                                                if (oplusPanelViewPagerController.queueDownEvent.getDispatchDownEvent()) {
                                                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "dispatchTouchEvent NOTIFICATION down with queueDownEvent.dispatchDownEvent is true!", null, 8);
                                                    oplusPanelViewPagerController.notificationPanelViewController.getView().dispatchTouchEvent(oplusPanelViewPagerController.queueDownEvent.getDownEvent());
                                                    QueueEventProcesser.OplusQueueMotionEvent oplusQueueMotionEvent = oplusPanelViewPagerController.queueDownEvent;
                                                    oplusQueueMotionEvent.isValid = false;
                                                    oplusQueueMotionEvent.dispatchDownEvent = false;
                                                    oplusQueueMotionEvent.pointerIndex = -1;
                                                }
                                                if (motionEvent.getActionMasked() != 2) {
                                                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onTouchEvent notificationPanelView event " + motionEvent, null, 8);
                                                }
                                                return oplusPanelViewPagerController.notificationPanelViewController.getView().dispatchTouchEvent(motionEvent);
                                            }
                                            if (oplusPanelViewPagerController.targetPage == PageType.QS) {
                                                if (oplusPanelViewPagerController.queueDownEvent.getDispatchDownEvent()) {
                                                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "dispatchTouchEvent QS down with queueDownEvent.dispatchDownEvent is true!", null, 8);
                                                    oplusPanelViewPagerController.separateQSManager.dispatchTouchEvent(oplusPanelViewPagerController.queueDownEvent.getDownEvent());
                                                    QueueEventProcesser.OplusQueueMotionEvent oplusQueueMotionEvent2 = oplusPanelViewPagerController.queueDownEvent;
                                                    oplusQueueMotionEvent2.isValid = false;
                                                    oplusQueueMotionEvent2.dispatchDownEvent = false;
                                                    oplusQueueMotionEvent2.pointerIndex = -1;
                                                }
                                                if (!isDisablePullQS()) {
                                                    boolean zDispatchTouchEvent = oplusPanelViewPagerController.separateQSManager.dispatchTouchEvent(motionEvent);
                                                    oplusPanelViewPagerController.hasDispatchedEventToChild = zDispatchTouchEvent;
                                                    return zDispatchTouchEvent;
                                                }
                                            }
                                        }
                                    } else {
                                        if (oplusPanelViewPagerController.targetPage == PageType.NOTIFICATION) {
                                            if (oplusPanelViewPagerController.queueDownEvent.getDispatchDownEvent()) {
                                                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "dispatchTouchEvent NOTIFICATION down with queueDownEvent.dispatchDownEvent is true!", null, 8);
                                                oplusPanelViewPagerController.notificationPanelViewController.getView().dispatchTouchEvent(oplusPanelViewPagerController.queueDownEvent.getDownEvent());
                                                QueueEventProcesser.OplusQueueMotionEvent oplusQueueMotionEvent3 = oplusPanelViewPagerController.queueDownEvent;
                                                oplusQueueMotionEvent3.isValid = false;
                                                oplusQueueMotionEvent3.dispatchDownEvent = false;
                                                oplusQueueMotionEvent3.pointerIndex = -1;
                                            }
                                            if (motionEvent.getActionMasked() != 2) {
                                                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "onTouchEvent notificationPanelView event " + motionEvent, null, 8);
                                            }
                                            return oplusPanelViewPagerController.notificationPanelViewController.getView().dispatchTouchEvent(motionEvent);
                                        }
                                        if (oplusPanelViewPagerController.targetPage == PageType.QS) {
                                            if (oplusPanelViewPagerController.queueDownEvent.getDispatchDownEvent()) {
                                                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "dispatchTouchEvent QS down with queueDownEvent.dispatchDownEvent is true!", null, 8);
                                                oplusPanelViewPagerController.separateQSManager.dispatchTouchEvent(oplusPanelViewPagerController.queueDownEvent.getDownEvent());
                                                QueueEventProcesser.OplusQueueMotionEvent oplusQueueMotionEvent4 = oplusPanelViewPagerController.queueDownEvent;
                                                oplusQueueMotionEvent4.isValid = false;
                                                oplusQueueMotionEvent4.dispatchDownEvent = false;
                                                oplusQueueMotionEvent4.pointerIndex = -1;
                                            }
                                            if (!isDisablePullQS()) {
                                                boolean zDispatchTouchEvent2 = oplusPanelViewPagerController.separateQSManager.dispatchTouchEvent(motionEvent);
                                                oplusPanelViewPagerController.hasDispatchedEventToChild = zDispatchTouchEvent2;
                                                return zDispatchTouchEvent2;
                                            }
                                        }
                                    }
                                } else if (motionEvent.getActionMasked() == 0) {
                                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "isInBrickModeAndKeyGuardNotShow, return", null, 8);
                                    return false;
                                }
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final void reset$6() {
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            oplusPanelViewPagerController.isLastUpToExpand = false;
            oplusPanelViewPagerController.forceIntercept = false;
            oplusPanelViewPagerController.hasCalledDownEvent = false;
            oplusPanelViewPagerController.hasDispatchedEventToChild = false;
            this.ignoringOccludeGesture = false;
            QueueEventProcesser.OplusQueueMotionEvent oplusQueueMotionEvent = oplusPanelViewPagerController.queueDownEvent;
            oplusQueueMotionEvent.isValid = false;
            oplusQueueMotionEvent.dispatchDownEvent = false;
            oplusQueueMotionEvent.pointerIndex = -1;
            oplusPanelViewPagerController.clearProgressiveDownEvent();
            LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, "reset", null, 8);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelViewPagerController$onInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ViewController.OnConfigurationChangedListener {
        public /* synthetic */ OplusPanelViewPagerController this$0;

        public final void onConfigurationChange(Configuration configuration) {
            OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
            oplusPanelViewPagerController.finishAndResetHorizontalScroll("onConfigurationChange", true);
            oplusPanelViewPagerController.releaseProgressiveHelper();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->onConfigurationChange");
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelViewPagerController$onViewAttached$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02811 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelViewPagerController$onViewAttached$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01041 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusPanelViewPagerController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelViewPagerController$onViewAttached$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C01051 implements FlowCollector {
                public /* synthetic */ OplusPanelViewPagerController this$0;

                public final Object emit(Object obj, Continuation continuation) {
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    LogUtil.i$default(LogUtil.TAG_QS, OplusPanelViewPagerController.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("separate mode change: ", zBooleanValue), null, 8);
                    OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
                    oplusPanelViewPagerController.loadQSPanelView(zBooleanValue);
                    CentralSurfacesImpl centralSurfacesImpl = oplusPanelViewPagerController.centralSurfaces;
                    if (centralSurfacesImpl != null) {
                        centralSurfacesImpl.reloadQSFragment(false);
                    }
                    if (zBooleanValue) {
                        oplusPanelViewPagerController.quickSettingsControllerImpl.setExpansionEnabledPolicy(false);
                    }
                    oplusPanelViewPagerController.separateQSManager.onSeparateModeChange(zBooleanValue);
                    SeparateQSStatisicUtil.qsEnabled = zBooleanValue;
                    QsStatisticUtil.qsEnabled = zBooleanValue;
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01041(OplusPanelViewPagerController oplusPanelViewPagerController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusPanelViewPagerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01041(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow enableSeparateNotificationAndQS = this.this$0.panelModeRepository.getEnableSeparateNotificationAndQS();
                    OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
                    C01051 c01051 = new C01051();
                    c01051.this$0 = oplusPanelViewPagerController;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (enableSeparateNotificationAndQS.collect(c01051, this) == coroutine_suspended) {
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

        public C02811(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            C02811 c02811 = OplusPanelViewPagerController.this.new C02811((Continuation) obj3);
            c02811.L$0 = (LifecycleOwner) obj;
            return c02811.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C01041 c01041 = new C01041(OplusPanelViewPagerController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01041, this) == coroutine_suspended) {
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

    public static Unit $r8$lambda$AwoXKvl40h4fYsMvvtf4l4Q42nQ(OplusPanelViewPagerController oplusPanelViewPagerController, float f, boolean z, SeparateQSStatisicUtil.EXPANDED expanded, boolean z2, boolean z3) {
        if (!z3) {
            oplusPanelViewPagerController.notificationStackScrollLayoutController.getView().mExt.setHorizontalSwitchState(false);
            SeparateQSStatisicUtil.EXPANDED expanded2 = oplusPanelViewPagerController.downExpanded;
            SeparateQSStatisicUtil.EXPANDED expanded3 = oplusPanelViewPagerController.lastDownExpanded;
            String qSAndNPVCTranslationX = oplusPanelViewPagerController.getQSAndNPVCTranslationX();
            StringBuilder sb = new StringBuilder("handleHorizontalTranslationFinished downExpanded=");
            sb.append(expanded2);
            sb.append(" ");
            sb.append(expanded3);
            sb.append(" isPanelExpanded=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" scrollToNext=", " displacement=", sb, z2, z);
            sb.append(f);
            sb.append(" ");
            sb.append(qSAndNPVCTranslationX);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sb.toString(), null, 8);
            oplusPanelViewPagerController.oplusGcSupressionExt.callGcDesupression(3);
            int iOrdinal = expanded.ordinal();
            int i = 1;
            if (iOrdinal != 1) {
                int i2 = 2;
                if (iOrdinal == 2) {
                    JankUtils.gfxSceneEnd(oplusPanelViewPagerController.getContext(), new int[]{23});
                    if (z || oplusPanelViewPagerController.isKeyguardVisible$1()) {
                        oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                        if (z2) {
                            OplusSeparateQSManager oplusSeparateQSManager = oplusPanelViewPagerController.separateQSManager;
                            OplusPanelViewPagerController$$ExternalSyntheticLambda1 oplusPanelViewPagerController$$ExternalSyntheticLambda1 = new OplusPanelViewPagerController$$ExternalSyntheticLambda1(i2);
                            oplusPanelViewPagerController$$ExternalSyntheticLambda1.f$0 = oplusPanelViewPagerController;
                            VarHandle.storeStoreFence();
                            OplusSeparateQSManager.collapseQSPanel$default(oplusSeparateQSManager, false, true, false, oplusPanelViewPagerController$$ExternalSyntheticLambda1, 4);
                        } else {
                            oplusPanelViewPagerController.resetHorizontalTranslationView();
                        }
                        SeparateQSStatisicUtil.EXPANDED expanded4 = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
                        oplusPanelViewPagerController.downExpanded = expanded4;
                        if (!oplusPanelViewPagerController.staticInfo.getDisuse() && oplusPanelViewPagerController.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.QS) {
                            oplusPanelViewPagerController.staticInfo.setOpType(SeparateQSStatisicUtil.OPTYPE_SCROLL_LEFTTORIGHT);
                            oplusPanelViewPagerController.onTrackComm(expanded4);
                        }
                    } else {
                        oplusPanelViewPagerController.targetPage = PageType.QS;
                        oplusPanelViewPagerController.downExpanded = SeparateQSStatisicUtil.EXPANDED.QS;
                        if (z2) {
                            oplusPanelViewPagerController.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                        }
                        oplusPanelViewPagerController.resetHorizontalTranslationView();
                        TemporarilyInfoManager.Companion.getInstance().setStarted(true);
                    }
                    OplusAxisSwipeDetector oplusAxisSwipeDetector = oplusPanelViewPagerController.detector;
                    if (oplusAxisSwipeDetector != null) {
                        oplusAxisSwipeDetector.updateInXOverScrollHotAreaIfNeeded();
                    }
                }
            } else {
                JankUtils.gfxSceneEnd(oplusPanelViewPagerController.getContext(), new int[]{22});
                if (!z || oplusPanelViewPagerController.isKeyguardVisible$1()) {
                    oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                    oplusPanelViewPagerController.downExpanded = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
                    if (z2) {
                        OplusSeparateQSManager oplusSeparateQSManager2 = oplusPanelViewPagerController.separateQSManager;
                        OplusPanelViewPagerController$$ExternalSyntheticLambda1 oplusPanelViewPagerController$$ExternalSyntheticLambda2 = new OplusPanelViewPagerController$$ExternalSyntheticLambda1(i);
                        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$0 = oplusPanelViewPagerController;
                        VarHandle.storeStoreFence();
                        OplusSeparateQSManager.collapseQSPanel$default(oplusSeparateQSManager2, false, true, false, oplusPanelViewPagerController$$ExternalSyntheticLambda2, 4);
                    } else {
                        oplusPanelViewPagerController.resetHorizontalTranslationView();
                    }
                    if (!z) {
                        ((LevelPositionAlgorithm) oplusPanelViewPagerController.levelPositionAlgorithm.get()).releaseAlgorithm(0.0f);
                    }
                } else {
                    oplusPanelViewPagerController.targetPage = PageType.QS;
                    if (z2) {
                        oplusPanelViewPagerController.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                    }
                    oplusPanelViewPagerController.resetHorizontalTranslationView();
                    SeparateQSStatisicUtil.EXPANDED expanded5 = SeparateQSStatisicUtil.EXPANDED.QS;
                    oplusPanelViewPagerController.downExpanded = expanded5;
                    if (!oplusPanelViewPagerController.staticInfo.getDisuse() && oplusPanelViewPagerController.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
                        oplusPanelViewPagerController.staticInfo.setOpType(SeparateQSStatisicUtil.OPTYPE_SCROLL_RIGHTTOLEFT);
                        oplusPanelViewPagerController.onTrackComm(expanded5);
                    }
                    TemporarilyInfoManager.Companion.getInstance().setStarted(true);
                }
                OplusAxisSwipeDetector oplusAxisSwipeDetector2 = oplusPanelViewPagerController.detector;
                if (oplusAxisSwipeDetector2 != null) {
                    oplusAxisSwipeDetector2.updateInXOverScrollHotAreaIfNeeded();
                }
            }
            OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue();
            horizontalTranslationValue.getClass();
            LogUtil.i$default(LogUtil.TAG_QS, "ScrollTranslationValue", "reset", null, 8);
            horizontalTranslationValue.waitForBlocking = false;
            horizontalTranslationValue.scrollingToMax = false;
            horizontalTranslationValue.scrollingToMin = false;
            boolean zIsRunning = oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().isRunning();
            if (!zIsRunning) {
                oplusPanelViewPagerController.lastDownExpanded = SeparateQSStatisicUtil.EXPANDED.NONE;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("flingHorizontalTranslationToState onEnd isRunning=", "OplusPanelViewPagerController-->", "SystemUi--QuickSettings", zIsRunning);
                }
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->flingHorizontalTranslationToState onEnd as cancel");
        }
        return Unit.INSTANCE;
    }

    public OplusPanelViewPagerController(OplusPanelViewPager oplusPanelViewPager, OplusSeparateQSManager oplusSeparateQSManager, NotificationPanelViewController notificationPanelViewController, OplusPanelModeRepository oplusPanelModeRepository, QuickSettingsControllerEx quickSettingsControllerEx, QuickSettingsControllerImpl quickSettingsControllerImpl, OplusSlidSwitchAnimation oplusSlidSwitchAnimation, KeyguardBypassController keyguardBypassController, dagger.Lazy lazy, StatusBarKeyguardViewManager statusBarKeyguardViewManager, StatusBarStateController statusBarStateController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, QsCenterModeSelectDialogController qsCenterModeSelectDialogController, KeyguardStateController keyguardStateController, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, PanelAnimationEx panelAnimationEx, SysUiStateControllerImpl sysUiStateControllerImpl, dagger.Lazy lazy2) {
        super(oplusPanelViewPager);
        this.separateQSManager = oplusSeparateQSManager;
        this.notificationPanelViewController = notificationPanelViewController;
        this.panelModeRepository = oplusPanelModeRepository;
        this.quickSettingsControllerEx = quickSettingsControllerEx;
        this.quickSettingsControllerImpl = quickSettingsControllerImpl;
        this.slidSwitchAnimation = oplusSlidSwitchAnimation;
        this.keyguardBypassController = keyguardBypassController;
        this.pullHelper = lazy;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.statusBarStateController = statusBarStateController;
        this.notificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.modeSelectGuideDialog = qsCenterModeSelectDialogController;
        this.keyguardStateController = keyguardStateController;
        this.oplusLockscreenShadeTransitionController = oplusLockscreenShadeTransitionControllerEx;
        this.panelAnimationEx = panelAnimationEx;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.levelPositionAlgorithm = lazy2;
        qsCenterModeSelectDialogController.init();
        this.targetPage = PageType.NOTIFICATION;
        SeparateQSStatisicUtil.EXPANDED expanded = SeparateQSStatisicUtil.EXPANDED.NONE;
        this.downExpanded = expanded;
        this.lastDownExpanded = expanded;
        this.screenWidth = 1.0f;
        this.screenHeight = 1.0f;
        this.staticInfo = new SeparateStaticInfo();
        this.fractionListeners = new LinkedHashSet();
        this.queueDownEvent = new QueueEventProcesser.OplusQueueMotionEvent();
        this.panelHitTestLoc = new int[2];
        this.oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
        this.translationChangedCallback = new OplusPanelViewPagerController$$ExternalSyntheticLambda0();
        OplusPanelViewPagerController$separateQSPluginListener$1 oplusPanelViewPagerController$separateQSPluginListener$1 = new OplusPanelViewPagerController$separateQSPluginListener$1();
        oplusPanelViewPagerController$separateQSPluginListener$1.this$0 = this;
        oplusPanelViewPagerController$separateQSPluginListener$1.$panelViewPager = oplusPanelViewPager;
        VarHandle.storeStoreFence();
        this.separateQSPluginListener = oplusPanelViewPagerController$separateQSPluginListener$1;
        OplusPanelViewPagerController$statusBarStateListener$1 oplusPanelViewPagerController$statusBarStateListener$1 = new OplusPanelViewPagerController$statusBarStateListener$1();
        oplusPanelViewPagerController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusPanelViewPagerController$statusBarStateListener$1;
        OplusPanelViewPagerController$sysStateControllerListener$1 oplusPanelViewPagerController$sysStateControllerListener$1 = new OplusPanelViewPagerController$sysStateControllerListener$1();
        oplusPanelViewPagerController$sysStateControllerListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sysStateControllerListener = oplusPanelViewPagerController$sysStateControllerListener$1;
        OplusPanelViewPagerController$$ExternalSyntheticLambda1 oplusPanelViewPagerController$$ExternalSyntheticLambda1 = new OplusPanelViewPagerController$$ExternalSyntheticLambda1(0);
        oplusPanelViewPagerController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.fractionAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusPanelViewPagerController$$ExternalSyntheticLambda1);
    }

    public static final boolean access$canScrollToQSOnKeyguard(OplusPanelViewPagerController oplusPanelViewPagerController) {
        if (oplusPanelViewPagerController.isKeyGuardVisibleAndNotPassEnabled()) {
            return (!oplusPanelViewPagerController.isKeyguardVisible$1() || oplusPanelViewPagerController.notificationPanelViewController.isOnKeyguard()) && oplusPanelViewPagerController.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION && !oplusPanelViewPagerController.separateQSManager.isFullyExpanded() && !((PanelPullHelper) oplusPanelViewPagerController.pullHelper.get()).getDisablePullStatusBar(Boolean.FALSE) && oplusPanelViewPagerController.getDownOnQsArea() && oplusPanelViewPagerController.downOnYHotArea;
        }
        return false;
    }

    public static final boolean access$isMultipleFingersEventFromShade(OplusPanelViewPagerController oplusPanelViewPagerController, MotionEvent motionEvent) {
        Long statusBarEventDownTime = oplusPanelViewPagerController.notificationPanelViewController.getExt().getStatusBarEventDownTime();
        if (statusBarEventDownTime != null) {
            return motionEvent.getPointerId(motionEvent.getActionIndex()) > 0 && statusBarEventDownTime.longValue() != motionEvent.getDownTime();
        }
        return false;
    }

    public static final void access$onActionDown(OplusPanelViewPagerController oplusPanelViewPagerController, MotionEvent motionEvent) {
        oplusPanelViewPagerController.slidSwitchAnimation.getHorizontalTranslationValue().onDown(motionEvent);
    }

    public static final void access$resetStateForKg(OplusPanelViewPagerController oplusPanelViewPagerController) {
        if (oplusPanelViewPagerController.separateQSManager.isFullyCollapsed()) {
            NotificationPanelView notificationPanelView = oplusPanelViewPagerController.notificationPanelView;
            if (notificationPanelView != null && notificationPanelView.getTransitionAlpha() == 0.0f) {
                notificationPanelView.setTransitionAlpha(1.0f);
            }
            oplusPanelViewPagerController.statusBarKeyguardViewManager.setQsExpansion(0.0f);
            oplusPanelViewPagerController.getFractionAnimation().cancelComplete();
            NotificationPanelView notificationPanelView2 = oplusPanelViewPagerController.notificationPanelView;
            if (notificationPanelView2 != null) {
                notificationPanelView2.setScaleX(1.0f);
                notificationPanelView2.setScaleY(1.0f);
            }
        }
    }

    public static final void access$resetStateForShade(OplusPanelViewPagerController oplusPanelViewPagerController) {
        NotificationPanelView notificationPanelView = oplusPanelViewPagerController.notificationPanelView;
        if (notificationPanelView != null && notificationPanelView.getTransitionAlpha() == 0.0f) {
            notificationPanelView.setTransitionAlpha(1.0f);
        }
        oplusPanelViewPagerController.statusBarKeyguardViewManager.setQsExpansion(0.0f);
        OplusAxisSwipeDetector oplusAxisSwipeDetector = oplusPanelViewPagerController.detector;
        if (oplusAxisSwipeDetector != null) {
            oplusAxisSwipeDetector.reset();
        }
    }

    public static final boolean access$shouldInterceptWhileQSExpandedOnKeyguard(OplusPanelViewPagerController oplusPanelViewPagerController) {
        return oplusPanelViewPagerController.isKeyGuardVisibleAndNotPassEnabled() && !oplusPanelViewPagerController.separateQSManager.isFullyCollapsed();
    }

    public static final boolean access$shouldOpenNotification(OplusPanelViewPagerController oplusPanelViewPagerController, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && oplusPanelViewPagerController.notificationPanelViewController.isFullyCollapsed() && !oplusPanelViewPagerController.downOnYHotArea && !oplusPanelViewPagerController.getDownOnQsArea() && !oplusPanelViewPagerController.isKeyguardVisible$1()) {
            float f = oplusPanelViewPagerController.currentQsFraction;
            if (f > 0.0f && f < 0.2f) {
                OplusSeparateQSManager.collapseQSPanel$default(oplusPanelViewPagerController.separateQSManager, false, true, false, null, 12);
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "qs is not full collapsed,scroll top to bottom on left,force collapseQSPanel!", null, 8);
                oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                return true;
            }
        }
        if (!oplusPanelViewPagerController.hasCalledDownEvent && motionEvent.getActionMasked() == 2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldOpenNotification  hasCalledDownEvent is false,call initExpanded", null, 8);
            oplusPanelViewPagerController.hasCalledDownEvent = true;
            oplusPanelViewPagerController.initExpanded(motionEvent);
        }
        if (oplusPanelViewPagerController.downExpanded == SeparateQSStatisicUtil.EXPANDED.NONE) {
            boolean z = oplusPanelViewPagerController.separateQSManager.isFullyCollapsed() && (!oplusPanelViewPagerController.isKeyguardVisible$1() || (oplusPanelViewPagerController.isKeyguardVisible$1() && oplusPanelViewPagerController.separateQSManager.isFullyCollapsed() && (motionEvent.getRawY() > ((float) oplusPanelViewPagerController.statusBarHeight) ? 1 : (motionEvent.getRawY() == ((float) oplusPanelViewPagerController.statusBarHeight) ? 0 : -1)) <= 0));
            if (oplusPanelViewPagerController.notificationPanelViewController.isFullyCollapsed() && z && !oplusPanelViewPagerController.getDownOnQsArea()) {
                oplusPanelViewPagerController.targetPage = PageType.NOTIFICATION;
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldOpenNotification  set targetPage=NOTIFICATION", null, 8);
                return true;
            }
        }
        return false;
    }

    public static final boolean access$shouldOpenQS(OplusPanelViewPagerController oplusPanelViewPagerController, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && oplusPanelViewPagerController.separateQSManager.isFullyCollapsed() && !oplusPanelViewPagerController.downOnYHotArea && oplusPanelViewPagerController.getDownOnQsArea() && !oplusPanelViewPagerController.isKeyguardVisible$1()) {
            float f = oplusPanelViewPagerController.currentNotifyFraction;
            if (f > 0.0f && f < 0.2f) {
                oplusPanelViewPagerController.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "notify is not full collapsed,scroll top to bottom on right,force collapseNotifPanelWithoutAnimate!", null, 8);
                oplusPanelViewPagerController.targetPage = PageType.QS;
                return true;
            }
        }
        if (!oplusPanelViewPagerController.hasCalledDownEvent && motionEvent.getActionMasked() == 2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldOpenQS  hasCalledDownEvent is false,call initExpanded", null, 8);
            oplusPanelViewPagerController.hasCalledDownEvent = true;
            oplusPanelViewPagerController.initExpanded(motionEvent);
        }
        boolean z = oplusPanelViewPagerController.notificationPanelViewController.isFullyCollapsed() && (!oplusPanelViewPagerController.isKeyguardVisible$1() || (oplusPanelViewPagerController.isKeyguardVisible$1() && oplusPanelViewPagerController.downExpanded == SeparateQSStatisicUtil.EXPANDED.NONE && !oplusPanelViewPagerController.notificationPanelViewController.isOnKeyguard() && (motionEvent.getRawY() > ((float) oplusPanelViewPagerController.statusBarHeight) ? 1 : (motionEvent.getRawY() == ((float) oplusPanelViewPagerController.statusBarHeight) ? 0 : -1)) <= 0));
        if (!oplusPanelViewPagerController.separateQSManager.isFullyCollapsed() || !z || !oplusPanelViewPagerController.getDownOnQsArea()) {
            return false;
        }
        oplusPanelViewPagerController.targetPage = PageType.QS;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldOpenQS  set targetPage=QS", null, 8);
        return true;
    }

    public static final void access$showTargetPage(OplusPanelViewPagerController oplusPanelViewPagerController) {
        if (oplusPanelViewPagerController.targetPage == PageType.QS && ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).indexOfChild(oplusPanelViewPagerController.qsPanelView) != ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).getChildCount() - 1) {
            View view = oplusPanelViewPagerController.qsPanelView;
            if (view != null) {
                view.bringToFront();
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("showTargetPage " + oplusPanelViewPagerController.targetPage));
                return;
            }
            return;
        }
        if (oplusPanelViewPagerController.targetPage != PageType.NOTIFICATION || ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).indexOfChild(oplusPanelViewPagerController.notificationPanelView) == ((OplusPanelViewPager) ((ViewController) oplusPanelViewPagerController).mView).getChildCount() - 1) {
            return;
        }
        NotificationPanelView notificationPanelView = oplusPanelViewPagerController.notificationPanelView;
        if (notificationPanelView != null) {
            notificationPanelView.bringToFront();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("showTargetPage " + oplusPanelViewPagerController.targetPage));
        }
    }

    public static final boolean computeDownExpandedFromPanelLayoutHit$isScreenPointInsideLayout(View view, OplusPanelViewPagerController oplusPanelViewPagerController, float f, float f2) {
        if (view.getVisibility() == 0 && view.getWidth() > 0 && view.getHeight() > 0 && view.isAttachedToWindow()) {
            view.getLocationOnScreen(oplusPanelViewPagerController.panelHitTestLoc);
            int[] iArr = oplusPanelViewPagerController.panelHitTestLoc;
            int i = iArr[0];
            int i2 = iArr[1];
            if (f >= i && f < view.getWidth() + i && f2 >= i2 && f2 < view.getHeight() + i2) {
                return true;
            }
        }
        return false;
    }

    private final String getClearButtonTranslationX() {
        View viewFindViewById;
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        if (notificationPanelView == null || (viewFindViewById = notificationPanelView.findViewById(2131362545)) == null) {
            return "clearButton tx=0f";
        }
        return "clearButton tx=" + Float.valueOf(viewFindViewById.getTranslationX());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getDownOnQsArea() {
        boolean zIsQsOnLeft = isQsOnLeft();
        boolean z = this.downOnRight;
        if (zIsQsOnLeft) {
            return !z;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getFractionAnimation() {
        return (COUISpringAnimation) this.fractionAnimation$delegate.getValue();
    }

    private final Float getLastDownExpandedTranslationX() {
        int iOrdinal = resolveReadablePanelForTranslationX().ordinal();
        if (iOrdinal == 1) {
            NotificationPanelView notificationPanelView = this.notificationPanelView;
            return Float.valueOf(-(notificationPanelView != null ? notificationPanelView.getX() : 0.0f));
        }
        if (iOrdinal != 2) {
            return null;
        }
        boolean zIsQsOnLeft = isQsOnLeft();
        float f = this.screenWidth;
        if (zIsQsOnLeft) {
            f = -f;
        }
        View view = this.qsPanelView;
        return Float.valueOf(f - (view != null ? view.getX() : 0.0f));
    }

    private final boolean getLastScrollToNext() {
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        boolean zAreEqual = Intrinsics.areEqual(notificationPanelView != null ? Float.valueOf(notificationPanelView.getTranslationX()) : null, 0.0f);
        View view = this.qsPanelView;
        boolean zAreEqual2 = Intrinsics.areEqual(view != null ? Float.valueOf(view.getTranslationX()) : null, 0.0f);
        if (zAreEqual && zAreEqual2) {
            return false;
        }
        SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
        if (expanded == SeparateQSStatisicUtil.EXPANDED.QS) {
            return zAreEqual;
        }
        if (expanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            return zAreEqual2;
        }
        return false;
    }

    private final Rect getNotificationXScrollHotArea() {
        ViewGroup qSContainer = ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getQSContainer();
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        Number numberValueOf = centralSurfaces != null ? Float.valueOf(centralSurfaces.getDisplayWidth()) : Integer.valueOf(getScreenWidth());
        int[] iArr = new int[2];
        if (qSContainer == null) {
            return new Rect(0, 0, numberValueOf.intValue(), this.quickSettingsControllerEx.getNotificationSettingAreaHeight() + this.statusBarLocationOnScreenY);
        }
        qSContainer.getLocationInWindow(iArr);
        this.statusBarLocationOnScreenY = iArr[1];
        return new Rect(0, 0, numberValueOf.intValue(), qSContainer.getHeight() + iArr[1]);
    }

    private final Rect getNotificationYScrollHotArea() {
        if (!isKeyguardVisible$1()) {
            return getYScrollHotArea(((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getStatusBarContainer());
        }
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        return new Rect(0, 0, (centralSurfaces != null ? Float.valueOf(centralSurfaces.getDisplayWidth()) : Integer.valueOf(getScreenWidth())).intValue(), this.statusBarHeight);
    }

    private final String getQSAndNPVCTranslationX() {
        View view = this.qsPanelView;
        Float fValueOf = view != null ? Float.valueOf(view.getTranslationX()) : null;
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        return "qTx=" + fValueOf + " nTx=" + (notificationPanelView != null ? Float.valueOf(notificationPanelView.getTranslationX()) : null);
    }

    private final List<Rect> getQsXScrollHotArea() {
        return CollectionsKt__CollectionsJVMKt.listOf(getNotificationXScrollHotArea());
    }

    private final Rect getQsYScrollHotArea() {
        if (!isKeyguardVisible$1()) {
            return getYScrollHotArea(this.separateQSManager.getStatusBarContainer());
        }
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        return new Rect(0, 0, (centralSurfaces != null ? Float.valueOf(centralSurfaces.getDisplayWidth()) : Integer.valueOf(getScreenWidth())).intValue(), this.statusBarHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getScreenHeight() {
        Rect bounds = getContext().getResources().getConfiguration().windowConfiguration.getBounds();
        return bounds.bottom - bounds.top;
    }

    private final int getScreenWidth() {
        Rect bounds = getContext().getResources().getConfiguration().windowConfiguration.getBounds();
        return bounds.right - bounds.left;
    }

    private final Pair<List<Rect>, List<Rect>> getScrollXHotZone() {
        Pair<List<Rect>, List<Rect>> notificationScrollXHotZone;
        Pair<List<Rect>, List<Rect>> pair = TuplesKt.to(getQsXScrollHotArea(), CollectionsKt__CollectionsKt.emptyList());
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            NotificationHotAreaHelper notificationHotAreaHelper = this.notificationHotAreaHelper;
            if (notificationHotAreaHelper != null && (notificationScrollXHotZone = notificationHotAreaHelper.getNotificationScrollXHotZone()) != null) {
                return notificationScrollXHotZone;
            }
        } else {
            Pair<List<Rect>, List<Rect>> qSScrollXHotZone = this.separateQSManager.getQSScrollXHotZone();
            if (qSScrollXHotZone != null) {
                return qSScrollXHotZone;
            }
        }
        return pair;
    }

    private final Rect getScrollYHotZone() {
        return this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION ? getNotificationYScrollHotArea() : getQsYScrollHotArea();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMoveFraction(float f) {
        View view;
        float f2;
        if (this.moveFraction == f) {
            return;
        }
        this.moveFraction = f;
        int iOrdinal = this.downExpanded.ordinal();
        if (iOrdinal == 0) {
            view = null;
            f2 = 0.0f;
        } else if (iOrdinal == 1) {
            view = this.notificationPanelView;
            f2 = this.currentQsFraction;
        } else if (iOrdinal != 2) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return;
        } else {
            view = this.qsPanelView;
            f2 = this.currentNotifyFraction;
        }
        if (view == null) {
            return;
        }
        float yScrollAppearDisplacement = 150.0f;
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            boolean zIsLandscape = DeviceUtil.isLandscape(view.getContext());
            OplusSeparateQSManager oplusSeparateQSManager = this.separateQSManager;
            if (zIsLandscape) {
                Float firstElementShowDistance = oplusSeparateQSManager.getFirstElementShowDistance();
                yScrollAppearDisplacement = ((firstElementShowDistance != null ? firstElementShowDistance.floatValue() : 102.0f) / 0.85f) * 0.5f;
            } else {
                Float firstElementShowDistance2 = oplusSeparateQSManager.getFirstElementShowDistance();
                if (firstElementShowDistance2 != null) {
                    yScrollAppearDisplacement = firstElementShowDistance2.floatValue();
                }
            }
        } else {
            OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
            if (oplusAxisSwipeDetector != null) {
                yScrollAppearDisplacement = oplusAxisSwipeDetector.getYScrollAppearDisplacement();
            }
        }
        view.setAlpha(f2 <= 0.5f ? RangesKt___RangesKt.coerceIn(1.0f - (Math.abs(f) / yScrollAppearDisplacement), 0.0f, 1.0f) : 0.0f);
        float fCoerceIn = RangesKt___RangesKt.coerceIn(1.0f - ((Math.abs(f) / yScrollAppearDisplacement) * 0.15f), 0.85f, 1.0f);
        view.setScaleX(fCoerceIn);
        view.setScaleY(fCoerceIn);
    }

    private final void setTranslationXForScrollX(float f) {
        NotificationPanelView notificationPanelView;
        View view;
        View view2;
        NotificationPanelView notificationPanelView2;
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.QS && (view2 = this.qsPanelView) != null && (notificationPanelView2 = this.notificationPanelView) != null) {
            notificationPanelView2.setTranslationX(this.initTranslationX + f);
            view2.setTranslationX(f);
        }
        if (this.downExpanded != SeparateQSStatisicUtil.EXPANDED.NOTIFICATION || (notificationPanelView = this.notificationPanelView) == null || (view = this.qsPanelView) == null) {
            return;
        }
        view.setTranslationX(this.initTranslationX + f);
        notificationPanelView.setTranslationX(f);
    }

    public static void updateNotificationPanelAlpha(View view, float f, String str) {
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusPanelViewPagerController-->".concat("updateNotificationPanelAlpha, alpha: " + f + ", reason: " + str));
        }
        view.setTransitionAlpha(f);
    }

    public final void addAnimationGroups(List list, List list2) {
        if (!list.isEmpty()) {
            this.slidSwitchAnimation.getHorizontalTranslationValue().addGroup((View) list.get(0), list);
        }
        if (list2.isEmpty()) {
            return;
        }
        this.slidSwitchAnimation.getHorizontalTranslationValue().addGroup((View) list2.get(0), list2);
    }

    public final void adjustLastDownExpanded() {
        if (this.lastDownExpanded == SeparateQSStatisicUtil.EXPANDED.NONE) {
            this.lastDownExpanded = resolveReadablePanelForTranslationX();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("lastDownExpanded=NONE adjusted to=" + this.lastDownExpanded));
            }
        }
    }

    public final boolean canSlideHToQSOnStatusBar(PointF pointF) {
        if (this.downExpanded != SeparateQSStatisicUtil.EXPANDED.NONE) {
            return false;
        }
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        return new Rect(0, 0, (centralSurfaces != null ? Float.valueOf(centralSurfaces.getDisplayWidth()) : Integer.valueOf(getScreenWidth())).intValue(), this.statusBarHeight).contains((int) pointF.x, (int) pointF.y);
    }

    public final void clearProgressiveDownEvent() {
        this.slidSwitchAnimation.getHorizontalTranslationValue().clearDownEvent();
    }

    public final void ensureProgressiveHelperReady() {
        NotificationPanelView notificationPanelView;
        if (!((Boolean) this.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue() || (notificationPanelView = this.notificationPanelView) == null || this.qsPanelView == null) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "ensureProgressiveHelperReady progressive ", null, 8);
        Triple<List<View>, List<View>, List<View>> extraAnimationViews = this.separateQSManager.getExtraAnimationViews();
        if (extraAnimationViews == null) {
            extraAnimationViews = new Triple<>(CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList());
        }
        List<? extends View> list = (List) extraAnimationViews.component1();
        List list2 = (List) extraAnimationViews.component2();
        List list3 = (List) extraAnimationViews.component3();
        OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
        ViewGroup qSContainer = ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getQSContainer();
        if (horizontalTranslationValue.hasProgressiveHelper()) {
            horizontalTranslationValue.register(qSContainer);
            if (horizontalTranslationValue.isDynamicChildViewsEqual(list)) {
                return;
            }
            horizontalTranslationValue.removeGroup();
            horizontalTranslationValue.unRegisterDynamicView();
            horizontalTranslationValue.registerDynamicView(list);
            addAnimationGroups(list2, list3);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "ensureProgressiveHelperReady progressive refresh", null, 8);
            return;
        }
        List<ViewGroup> qSContainers = this.separateQSManager.getQSContainers();
        if (qSContainers == null) {
            qSContainers = CollectionsKt__CollectionsKt.emptyList();
        }
        if (qSContainers.size() < 2) {
            return;
        }
        ViewGroup viewGroup = qSContainers.get(0);
        ViewGroup viewGroup2 = qSContainers.get(1);
        ViewGroup view = this.notificationStackScrollLayoutController.getView();
        if (view == null) {
            view = null;
        }
        View viewFindViewById = notificationPanelView.findViewById(2131362545);
        View viewFindViewById2 = notificationPanelView.findViewById(2131365668);
        if (view == null || viewGroup == null || viewGroup2 == null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "ensureProgressiveHelperReady progressive helper disabled: qs containers not ready");
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "ensureProgressiveHelperReady progressive renew", null, 8);
        horizontalTranslationValue.resetHelper();
        horizontalTranslationValue.ensureReady(CollectionsKt__CollectionsKt.listOf(new ViewGroup[]{view, viewGroup, viewGroup2}), getContext());
        horizontalTranslationValue.registerDynamicView(list);
        addAnimationGroups(list2, list3);
        horizontalTranslationValue.register(viewFindViewById);
        horizontalTranslationValue.register(qSContainer);
        horizontalTranslationValue.register(viewFindViewById2);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0020  */
    public final void finishAndResetHorizontalScroll(String str, boolean z) {
        boolean z2;
        OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
        boolean zIsRunning = horizontalTranslationValue.isRunning();
        boolean lastScrollToNext = getLastScrollToNext();
        if (lastScrollToNext) {
            z2 = true;
        } else {
            OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
            if (oplusAxisSwipeDetector != null ? oplusAxisSwipeDetector.shouldScrollXToNext() : false) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        boolean zHasProgressiveHelper = horizontalTranslationValue.hasProgressiveHelper();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat(str + " isExpanded=false isRunning=" + zIsRunning + " shouldScrollToNext=" + z2 + " lastScrollToNext=" + lastScrollToNext + " downExpanded=" + this.downExpanded + " " + getQSAndNPVCTranslationX() + " isOverScroll=" + this.isOverScroll + " hasProgressiveHelper=" + zHasProgressiveHelper));
        }
        flingHorizontalTranslationToState(0.0f, z2, z);
        clearProgressiveDownEvent();
        OplusAxisSwipeDetector oplusAxisSwipeDetector2 = this.detector;
        if (oplusAxisSwipeDetector2 != null) {
            oplusAxisSwipeDetector2.finishedScrolling();
        }
        horizontalTranslationValue.getProgressiveController().skipToEnd();
        LogUtil.i$default(LogUtil.TAG_QS, "ScrollTranslationValue", "reset", null, 8);
        horizontalTranslationValue.waitForBlocking = false;
        horizontalTranslationValue.scrollingToMax = false;
        horizontalTranslationValue.scrollingToMin = false;
        resetHorizontalTranslationView();
    }

    public final void flingHorizontalTranslationToState(float f, boolean z, boolean z2) {
        SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
        float f2 = z ? -this.initTranslationX : 0.0f;
        this.separateQSManager.endPagerFlingAnimation();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("flingHorizontalTranslationToState start " + expanded));
        }
        OplusPanelViewPagerController$$ExternalSyntheticLambda2 oplusPanelViewPagerController$$ExternalSyntheticLambda2 = new OplusPanelViewPagerController$$ExternalSyntheticLambda2();
        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$0 = this;
        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$1 = f;
        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$2 = z;
        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$3 = expanded;
        oplusPanelViewPagerController$$ExternalSyntheticLambda2.f$4 = z2;
        VarHandle.storeStoreFence();
        if (this.isOverScroll) {
            this.isOverScroll = false;
            this.slidSwitchAnimation.getOverScrollTranslationValue().flingToRubberband(oplusPanelViewPagerController$$ExternalSyntheticLambda2);
        } else {
            setTranslationXForScrollX(f2);
            Float lastDownExpandedTranslationX = getLastDownExpandedTranslationX();
            this.slidSwitchAnimation.getHorizontalTranslationValue().flingTo(lastDownExpandedTranslationX != null ? lastDownExpandedTranslationX.floatValue() : 0.0f, oplusPanelViewPagerController$$ExternalSyntheticLambda2);
        }
    }

    public final View getQSPanelView() {
        return this.qsPanelView;
    }

    public final Rect getYScrollHotArea(View view) {
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        Number numberValueOf = centralSurfaces != null ? Float.valueOf(centralSurfaces.getDisplayWidth()) : Integer.valueOf(getScreenWidth());
        int[] iArr = new int[2];
        if (view == null) {
            return new Rect(0, 0, numberValueOf.intValue(), this.statusBarLocationOnScreenY + this.statusBarHeight);
        }
        view.getLocationInWindow(iArr);
        this.statusBarLocationOnScreenY = iArr[1];
        return new Rect(0, 0, numberValueOf.intValue(), view.getHeight() + iArr[1]);
    }

    public final void initExpanded(MotionEvent motionEvent) {
        OplusAxisSwipeDetector.ScrollCondition yScroll;
        this.downOnRight = isRightArea(motionEvent.getRawX());
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        this.downOnYHotArea = (oplusAxisSwipeDetector == null || (yScroll = oplusAxisSwipeDetector.getYScroll()) == null) ? false : yScroll.isHotArea(new PointF(motionEvent.getX(), motionEvent.getY()));
        this.screenWidth = getScreenWidth();
        this.screenHeight = getScreenHeight();
        SeparateQSStatisicUtil.EXPANDED expanded = SeparateQSStatisicUtil.EXPANDED.NONE;
        this.downExpanded = expanded;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        View view = this.notificationPanelView;
        View view2 = this.qsPanelView;
        boolean z = view != null && computeDownExpandedFromPanelLayoutHit$isScreenPointInsideLayout(view, this, rawX, rawY);
        boolean z2 = view2 != null && computeDownExpandedFromPanelLayoutHit$isScreenPointInsideLayout(view2, this, rawX, rawY);
        if (z || z2) {
            if (z && !z2) {
                expanded = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
            } else if (z || !z2) {
                int iIndexOfChild = view != null ? ((OplusPanelViewPager) ((ViewController) this).mView).indexOfChild(view) : -1;
                int iIndexOfChild2 = view2 != null ? ((OplusPanelViewPager) ((ViewController) this).mView).indexOfChild(view2) : -1;
                if (iIndexOfChild > iIndexOfChild2) {
                    expanded = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
                } else {
                    expanded = (iIndexOfChild2 <= iIndexOfChild && z) ? SeparateQSStatisicUtil.EXPANDED.NOTIFICATION : SeparateQSStatisicUtil.EXPANDED.QS;
                }
            } else {
                expanded = SeparateQSStatisicUtil.EXPANDED.QS;
            }
        }
        this.downExpanded = expanded;
        boolean zIsRunning = this.slidSwitchAnimation.getHorizontalTranslationValue().isRunning();
        if (!zIsRunning) {
            this.lastDownExpanded = this.downExpanded;
        }
        this.staticInfo.reset();
        this.staticInfo.setDownEvent(MotionEvent.obtain(motionEvent));
        this.staticInfo.setKeyGuardShow(isKeyguardVisible$1());
        this.staticInfo.setSeparateQSEnabled(((Boolean) this.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue());
        this.staticInfo.setFrom(this.downExpanded);
        this.staticInfo.setDownOnHotArea(this.downOnYHotArea);
        this.staticInfo.setDisuse(false);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SeparateQSStatisicUtil.EXPANDED expanded2 = this.downExpanded;
            boolean z3 = this.downOnRight;
            PageType pageType = this.targetPage;
            boolean z4 = this.downOnYHotArea;
            boolean zIsKeyGuardShowing = isKeyGuardShowing();
            boolean zIsOnKeyguard = this.notificationPanelViewController.isOnKeyguard();
            int size = this.fractionListeners.size();
            float f = this.currentQsFraction;
            float f2 = this.currentNotifyFraction;
            boolean z5 = this.hasCalledDownEvent;
            Pair<List<Rect>, List<Rect>> scrollXHotZone = getScrollXHotZone();
            Rect scrollYHotZone = getScrollYHotZone();
            float expandedFraction = this.notificationPanelViewController.getExpandedFraction();
            boolean z6 = this.isLastUpToExpand;
            SeparateQSStatisicUtil.EXPANDED expanded3 = this.lastDownExpanded;
            String qSAndNPVCTranslationX = getQSAndNPVCTranslationX();
            String clearButtonTranslationX = getClearButtonTranslationX();
            StringBuilder sb = new StringBuilder("initExpanded downExpanded=");
            sb.append(expanded2);
            sb.append(" downOnRight=");
            sb.append(z3);
            sb.append(" targetPage=");
            sb.append(pageType);
            sb.append(" downOnYHotArea=");
            sb.append(z4);
            sb.append(" isKeyguardShowing=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" isOnKeyguard=", " fractionListeners.size=", sb, zIsKeyGuardShowing, zIsOnKeyguard);
            IconProvider$.ExternalSyntheticOutline0.m(f, size, " currentQsFraction=", " currentNotifyFraction=", sb);
            sb.append(f2);
            sb.append(" hasCalledDownEvent=");
            sb.append(z5);
            sb.append(" getScrollXHotZone=");
            sb.append(scrollXHotZone);
            sb.append(" getScrollYHotZone=");
            sb.append(scrollYHotZone);
            sb.append(" notifyExpandedFraction=");
            sb.append(expandedFraction);
            sb.append(" isLastUpToExpand=");
            sb.append(z6);
            sb.append(" isHTRunning=");
            sb.append(zIsRunning);
            sb.append(" lastDownExpanded=");
            sb.append(expanded3);
            sb.append(" ");
            sb.append(qSAndNPVCTranslationX);
            sb.append(" ");
            sb.append(clearButtonTranslationX);
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat(sb.toString()));
        }
    }

    public final boolean isAnimationBlocking() {
        return this.slidSwitchAnimation.getHorizontalTranslationValue().getBlocking();
    }

    public final boolean isAnimationRunningInSeqEvent() {
        return this.separateQSManager.isAnimationRunningInSeqEvent();
    }

    public final boolean isDownNotificationArea() {
        return (getDownOnQsArea() && this.downOnYHotArea) ? false : true;
    }

    public final boolean isDraggingX() {
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector != null) {
            return oplusAxisSwipeDetector.isDraggingX();
        }
        return false;
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public boolean isInScrollXHotZone(PointF pointF) {
        List list = (List) getScrollXHotZone().getFirst();
        List list2 = (List) getScrollXHotZone().getSecond();
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((Rect) it.next()).contains((int) pointF.x, (int) pointF.y)) {
                z = true;
            }
        }
        if (z) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                if (((Rect) it2.next()).contains((int) pointF.x, (int) pointF.y)) {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public boolean isInScrollXOverHotArea(PointF pointF) {
        boolean zIsInScrollXHotZone = isInScrollXHotZone(pointF);
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            return zIsInScrollXHotZone;
        }
        Rect qSPageViewArea = this.separateQSManager.getQSPageViewArea();
        if (qSPageViewArea == null) {
            qSPageViewArea = new Rect(-1, -1, -1, -1);
        }
        return zIsInScrollXHotZone && !qSPageViewArea.contains((int) pointF.x, (int) pointF.y);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public boolean isInScrollYHotZone(PointF pointF) {
        Rect scrollYHotZone = getScrollYHotZone();
        int i = scrollYHotZone.top;
        int i2 = scrollYHotZone.bottom;
        int i3 = (int) pointF.y;
        return i <= i3 && i3 < i2;
    }

    public final boolean isInXOverScroll() {
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        return oplusAxisSwipeDetector != null && oplusAxisSwipeDetector.isXOverScroll();
    }

    public final boolean isKeyGuardShowing() {
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        return centralSurfacesImpl != null && centralSurfacesImpl.isKeyguardShowing();
    }

    public final boolean isKeyGuardVisibleAndNotPassEnabled() {
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        if (centralSurfacesImpl == null || !centralSurfacesImpl.isKeyguardShowing() || this.keyguardBypassController.getBypassEnabled()) {
            return false;
        }
        return !this.keyguardStateController.mOccluded;
    }

    public final boolean isKeyguardVisible$1() {
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        return (centralSurfacesImpl == null || !centralSurfacesImpl.isKeyguardShowing() || this.keyguardStateController.mOccluded) ? false : true;
    }

    public final boolean isNotificationListBeDrag() {
        return this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION && this.notificationPanelViewController.mTouchSlopExceeded;
    }

    public final boolean isQsOnLeft() {
        return getContext().getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public final boolean isQsToNotificationScroll() {
        return this.downExpanded == SeparateQSStatisicUtil.EXPANDED.QS && this.slidSwitchAnimation.getHorizontalTranslationValue().isLogicRunning();
    }

    public final boolean isRightArea(float f) {
        CentralSurfaces centralSurfaces = this.centralSurfaces;
        return f >= (centralSurfaces != null ? centralSurfaces.getDisplayWidth() : 1080.0f) * 0.5f;
    }

    public final boolean isRightDownExpanded() {
        return this.downExpanded == (isQsOnLeft() ? SeparateQSStatisicUtil.EXPANDED.NOTIFICATION : SeparateQSStatisicUtil.EXPANDED.QS);
    }

    public final void loadQSPanelView(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("loadQSPanelView enableSeparateQS: ", z), null, 8);
        View view = this.qsPanelView;
        if (view != null) {
            OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, false, true, false, null, 12);
            ((OplusPanelViewPager) ((ViewController) this).mView).removeView(view);
        }
        releaseProgressiveHelper();
        this.qsPanelView = null;
        if (z) {
            View viewOnCreateQSPanelView = this.separateQSManager.onCreateQSPanelView((ViewGroup) ((ViewController) this).mView);
            this.qsPanelView = viewOnCreateQSPanelView;
            if (viewOnCreateQSPanelView != null) {
                ensureProgressiveHelperReady();
                CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
                if (centralSurfacesImpl != null) {
                    centralSurfacesImpl.initBrightnessMirrorController();
                }
                ((OplusPanelViewPager) ((ViewController) this).mView).addView(this.qsPanelView);
            }
        }
    }

    public final void notifyNTEndMotionEvent(float f, boolean z) {
        this.isLastUpToExpand = z;
        if (isKeyguardVisible$1()) {
            return;
        }
        float f2 = this.currentQsFraction;
        PageType pageType = this.targetPage;
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        Boolean boolValueOf = oplusAxisSwipeDetector != null ? Boolean.valueOf(oplusAxisSwipeDetector.isDraggingAndNotSettingState()) : null;
        boolean zIsQsVisible = this.separateQSManager.isQsVisible();
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        Float fValueOf = notificationPanelView != null ? Float.valueOf(notificationPanelView.getAlpha()) : null;
        String qSAndNPVCTranslationX = getQSAndNPVCTranslationX();
        NotificationPanelView notificationPanelView2 = this.notificationPanelView;
        Integer numValueOf = notificationPanelView2 != null ? Integer.valueOf(notificationPanelView2.getVisibility()) : null;
        boolean zIsFullyExpanded = this.separateQSManager.isFullyExpanded();
        float expandedFraction = this.notificationPanelViewController.getExpandedFraction();
        int size = this.fractionListeners.size();
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("notifyNTEndMotionEvent expanded=", z, " fraction=", f, " currentQsFraction=");
        sbM.append(f2);
        sbM.append(" targetPage=");
        sbM.append(pageType);
        sbM.append(" dState=");
        sbM.append(boolValueOf);
        sbM.append(" isQsVisible=");
        sbM.append(zIsQsVisible);
        sbM.append(" ntfalpha=");
        sbM.append(fValueOf);
        sbM.append(" ");
        sbM.append(qSAndNPVCTranslationX);
        sbM.append(" ntfisVisible=");
        sbM.append(numValueOf);
        sbM.append(" qsFull=");
        sbM.append(zIsFullyExpanded);
        sbM.append(" notifyExpandedFraction=");
        sbM.append(expandedFraction);
        sbM.append(" fractionListeners.size=");
        sbM.append(size);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        OplusAxisSwipeDetector oplusAxisSwipeDetector2 = this.detector;
        if ((oplusAxisSwipeDetector2 == null || !oplusAxisSwipeDetector2.isDraggingAndNotSettingState()) && z && this.fractionListeners.size() <= 0 && !this.slidSwitchAnimation.getHorizontalTranslationValue().isRunning()) {
            OplusAxisSwipeDetector oplusAxisSwipeDetector3 = this.detector;
            if (oplusAxisSwipeDetector3 == null || !oplusAxisSwipeDetector3.isBlocking()) {
                if (this.currentQsFraction > 0.5f || this.separateQSManager.isQsVisible()) {
                    this.targetPage = PageType.NOTIFICATION;
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "notifyNTEndMotionEvent notify expand to true with endMotionEvent,should collapse QS", null, 8);
                    OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, false, true, false, null, 12);
                }
            }
        }
    }

    public final void notifySeparateQSEndMotionEvent(float f, boolean z) {
        NotificationPanelView notificationPanelView;
        NotificationPanelView notificationPanelView2;
        this.isLastUpToExpand = z;
        if (isKeyguardVisible$1()) {
            return;
        }
        float f2 = this.currentNotifyFraction;
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        Boolean boolValueOf = oplusAxisSwipeDetector != null ? Boolean.valueOf(oplusAxisSwipeDetector.isDraggingAndNotSettingState()) : null;
        PageType pageType = this.targetPage;
        NotificationPanelView notificationPanelView3 = this.notificationPanelView;
        Float fValueOf = notificationPanelView3 != null ? Float.valueOf(notificationPanelView3.getAlpha()) : null;
        NotificationPanelView notificationPanelView4 = this.notificationPanelView;
        Integer numValueOf = notificationPanelView4 != null ? Integer.valueOf(notificationPanelView4.getVisibility()) : null;
        boolean zIsFullyExpanded = this.separateQSManager.isFullyExpanded();
        String qSAndNPVCTranslationX = getQSAndNPVCTranslationX();
        float expandedFraction = this.notificationPanelViewController.getExpandedFraction();
        int size = this.fractionListeners.size();
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("notifySeparateQSEndMotionEvent currentNotifyFraction=", f2, " fraction=", f, " expanded=");
        sbM.append(z);
        sbM.append(" dState=");
        sbM.append(boolValueOf);
        sbM.append(" targetPage=");
        sbM.append(pageType);
        sbM.append(" ntfalpha=");
        sbM.append(fValueOf);
        sbM.append(" ntfisVisible=");
        sbM.append(numValueOf);
        sbM.append(" qsFull=");
        sbM.append(zIsFullyExpanded);
        sbM.append(" ");
        sbM.append(qSAndNPVCTranslationX);
        sbM.append(" notifyExpandedFraction=");
        sbM.append(expandedFraction);
        sbM.append(" fractionListeners.size=");
        sbM.append(size);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (this.currentNotifyFraction == 0.0f && f == 0.0f && this.notificationPanelViewController.getExpandedFraction() == 0.0f && ((notificationPanelView2 = this.notificationPanelView) == null || notificationPanelView2.getVisibility() != 0)) {
            this.isTargetPageUpdate = false;
            this.downExpanded = SeparateQSStatisicUtil.EXPANDED.NONE;
        }
        OplusAxisSwipeDetector oplusAxisSwipeDetector2 = this.detector;
        if ((oplusAxisSwipeDetector2 == null || !oplusAxisSwipeDetector2.isDraggingAndNotSettingState()) && z && this.fractionListeners.size() <= 0 && !this.slidSwitchAnimation.getHorizontalTranslationValue().isRunning()) {
            OplusAxisSwipeDetector oplusAxisSwipeDetector3 = this.detector;
            if (oplusAxisSwipeDetector3 == null || !oplusAxisSwipeDetector3.isBlocking()) {
                if (this.currentNotifyFraction == 1.0f || this.notificationPanelViewController.getExpandedFraction() == 1.0f || ((notificationPanelView = this.notificationPanelView) != null && notificationPanelView.getVisibility() == 0)) {
                    this.targetPage = PageType.QS;
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "notifySeparateQSEndMotionEvent qs expand to true with endMotionEvent,should collapse notify", null, 8);
                    this.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
                }
            }
        }
    }

    public final void onInit() {
        OplusPanelViewPager oplusPanelViewPager = (OplusPanelViewPager) ((ViewController) this).mView;
        TouchHandler touchHandler = new TouchHandler();
        touchHandler.this$0 = this;
        touchHandler.lastTouchDownTime = -1L;
        VarHandle.storeStoreFence();
        oplusPanelViewPager.setOnTouchListener(touchHandler);
        OplusPanelViewPager oplusPanelViewPager2 = (OplusPanelViewPager) ((ViewController) this).mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        oplusPanelViewPager2.setConfigurationChangeListener(anonymousClass1);
        this.centralSurfaces = (CentralSurfaces) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
        if (this.notificationPanelViewController.getView() instanceof NotificationPanelView) {
            NotificationPanelView view = this.notificationPanelViewController.getView();
            this.notificationPanelView = view instanceof NotificationPanelView ? view : null;
        }
        this.detector = new OplusAxisSwipeDetector(getContext(), this);
        this.notificationHotAreaHelper = new NotificationHotAreaHelper(this.notificationPanelViewController, this.notificationStackScrollLayoutController);
        this.sysUiStateController.addListener(this.sysStateControllerListener);
        this.slidSwitchAnimation.getHorizontalTranslationValue().setOnTranslationChangedCallback(this.translationChangedCallback);
    }

    public final void onScreenTurnedOff() {
        OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, false, true, false, null, 12);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollBlockEnd(PointF pointF, PointF pointF2, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
            boolean z2 = this.downOnRight;
            View view = this.qsPanelView;
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("onScrollBlockEnd velocity=" + pointF + " displacement=" + pointF2 + " downExpanded=" + expanded + " downOnRight=" + z2 + " qsPanelView.translationX=" + (view != null ? Float.valueOf(view.getTranslationX()) : null)));
        }
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector == null) {
            return;
        }
        int iOrdinal = oplusAxisSwipeDetector.getBlockScrollMode().ordinal();
        if (iOrdinal == 1) {
            flingHorizontalTranslationToState(pointF2.x, z, true);
            clearProgressiveDownEvent();
            OplusAxisSwipeDetector oplusAxisSwipeDetector2 = this.detector;
            if (oplusAxisSwipeDetector2 != null) {
                oplusAxisSwipeDetector2.finishedScrolling();
            }
            oplusAxisSwipeDetector.setBlockScrollMode(OplusSwipeDetector.BlockScrollMode.UNDECIDED);
            return;
        }
        if (iOrdinal != 2) {
            return;
        }
        oplusAxisSwipeDetector.setBlockScrollMode(OplusSwipeDetector.BlockScrollMode.UNDECIDED);
        clearProgressiveDownEvent();
        OplusAxisSwipeDetector oplusAxisSwipeDetector3 = this.detector;
        if (oplusAxisSwipeDetector3 != null) {
            oplusAxisSwipeDetector3.finishedScrolling();
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollBlockStart(MotionEvent motionEvent, PointF pointF) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollBlockStart", null, 8);
        prepareHorizontalTranslationStart(true);
        ensureProgressiveHelperReady();
        syncHorizontalTranslationDown();
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollBlocking(PointF pointF) {
        OplusAxisSwipeDetector oplusAxisSwipeDetector;
        OplusAxisSwipeDetector oplusAxisSwipeDetector2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
            boolean z = this.downOnRight;
            View view = this.qsPanelView;
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("onScrollBlocking displacement=" + pointF + " downExpanded=" + expanded + " downOnRight=" + z + " qsPanelView.translationX=" + (view != null ? Float.valueOf(view.getTranslationX()) : null)));
        }
        boolean z2 = this.downOnRight;
        if ((!this.slidSwitchAnimation.getHorizontalTranslationValue().getBlocking() && ((oplusAxisSwipeDetector2 = this.detector) == null || !oplusAxisSwipeDetector2.isBlocking())) || ((oplusAxisSwipeDetector = this.detector) != null && oplusAxisSwipeDetector.isLastDragXOver())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                boolean blocking = this.slidSwitchAnimation.getHorizontalTranslationValue().getBlocking();
                OplusAxisSwipeDetector oplusAxisSwipeDetector3 = this.detector;
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("doBlockSpringAnimation return; getBlocking=" + blocking + " isLastDragXOver=" + (oplusAxisSwipeDetector3 != null ? Boolean.valueOf(oplusAxisSwipeDetector3.isLastDragXOver()) : null)));
                return;
            }
            return;
        }
        if (!isRightDownExpanded()) {
            if (this.downExpanded != (isQsOnLeft() ? SeparateQSStatisicUtil.EXPANDED.QS : SeparateQSStatisicUtil.EXPANDED.NOTIFICATION)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->".concat("doBlockSpringAnimation return for downExpanded=" + this.downExpanded));
                    return;
                }
                return;
            }
        }
        OplusAxisSwipeDetector oplusAxisSwipeDetector4 = this.detector;
        if (oplusAxisSwipeDetector4 == null) {
            return;
        }
        int iOrdinal = oplusAxisSwipeDetector4.getBlockScrollMode().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                syncHorizontalTranslationMove(pointF.x);
                return;
            } else {
                if (iOrdinal == 2) {
                    return;
                }
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->UNDECIDED ");
        }
        if (Math.abs(pointF.x) > Math.abs(pointF.y)) {
            oplusAxisSwipeDetector4.setBlockScrollMode(OplusSwipeDetector.BlockScrollMode.HORIZONTAL);
            syncHorizontalTranslationMove(pointF.x);
        } else if (pointF.y >= 0.0f) {
            oplusAxisSwipeDetector4.setBlockScrollMode(OplusSwipeDetector.BlockScrollMode.VERTICAL);
            flingHorizontalTranslationToState(pointF.x, z2 != isRightDownExpanded(), true);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->doBlockSpringAnimation return for displacement.y < 0F");
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollX(float f, MotionEvent motionEvent) {
        syncHorizontalTranslationMove(f);
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollXEnd(PointF pointF, float f, MotionEvent motionEvent, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollXEnd shouldScrollToNext=" + z + " downExpanded=" + this.downExpanded + " " + this.lastDownExpanded + " velocity=" + pointF + " displacement=" + f + " " + getQSAndNPVCTranslationX(), null, 8);
        flingHorizontalTranslationToState(f, z, true);
        clearProgressiveDownEvent();
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector != null) {
            oplusAxisSwipeDetector.finishedScrolling();
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollXEndOverScroll(PointF pointF, float f, MotionEvent motionEvent, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollXEndOverScroll displacement=" + f + " downExpanded=" + this.downExpanded + " " + getQSAndNPVCTranslationX(), null, 8);
        flingHorizontalTranslationToState(0.0f, false, true);
        clearProgressiveDownEvent();
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector != null) {
            oplusAxisSwipeDetector.finishedScrolling();
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollXOverScroll(float f, MotionEvent motionEvent) {
        this.isOverScroll = true;
        if (f >= 0.0f) {
            f = -f;
        }
        float fCalculateRubberband = this.slidSwitchAnimation.getOverScrollTranslationValue().getProgressiveController().calculateRubberband(f, this.screenWidth);
        Float lastDownExpandedTranslationX = getLastDownExpandedTranslationX();
        int iFloatValue = lastDownExpandedTranslationX != null ? (int) lastDownExpandedTranslationX.floatValue() : 0;
        OplusSlidSwitchAnimation oplusSlidSwitchAnimation = this.slidSwitchAnimation;
        if (f < 0.0f) {
            oplusSlidSwitchAnimation.getOverScrollTranslationValue().getProgressiveController().moveToRubberband(-fCalculateRubberband, iFloatValue);
        } else {
            oplusSlidSwitchAnimation.getOverScrollTranslationValue().getProgressiveController().moveToRubberband(fCalculateRubberband, iFloatValue);
        }
    }

    public final void onScrollXStart(float f) {
        if (shouldBlockOccludedHorizontalQsExpand()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollXStart ignored: occluded and panels collapsed", null, 8);
            return;
        }
        this.separateQSManager.setClipToPaddingForScroll(true);
        this.separateQSManager.handleIgnoreParentBoundsForScroll(true);
        boolean zIsRunning = this.slidSwitchAnimation.getHorizontalTranslationValue().isRunning();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollXStart displacement=" + f + " downExpanded=" + this.downExpanded + " " + this.lastDownExpanded + " initTranslationX=" + this.initTranslationX + " " + getQSAndNPVCTranslationX() + " isHTRunning=" + zIsRunning, null, 8);
        adjustLastDownExpanded();
        prepareHorizontalTranslationStart(zIsRunning);
        ensureProgressiveHelperReady();
        syncHorizontalTranslationDown();
        this.slidSwitchAnimation.getHorizontalTranslationValue().updateSDKLogEnabled$1();
    }

    public final void onScrollXStartOverScroll(float f) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollXStartOverScroll displacement=" + f + " downExpanded=" + this.downExpanded + " " + this.lastDownExpanded + " " + getQSAndNPVCTranslationX(), null, 8);
        this.notificationStackScrollLayoutController.getView().mExt.setHorizontalSwitchState(true);
        this.separateQSManager.setClipToPaddingForScroll(true);
        this.separateQSManager.handleIgnoreParentBoundsForScroll(true);
        adjustLastDownExpanded();
        ensureProgressiveHelperReady();
        syncHorizontalTranslationDown();
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollY(float f, MotionEvent motionEvent) {
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.QS) {
            View view = this.qsPanelView;
            if (view != null) {
                setAlphaForScrollY(f, this.currentNotifyFraction, view);
            }
            this.notificationPanelViewController.getView().dispatchTouchEvent(motionEvent);
        }
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            NotificationPanelView notificationPanelView = this.notificationPanelView;
            if (notificationPanelView != null) {
                if (this.statusBarStateController.getState() == 1) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollY but status is KEYGUARD", null, 8);
                    return;
                }
                setAlphaForScrollY(f, this.currentQsFraction, notificationPanelView);
            }
            this.separateQSManager.dispatchTouchEvent(motionEvent);
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollYEnd(PointF pointF, float f, MotionEvent motionEvent, boolean z) {
        float f2 = this.currentQsFraction;
        float f3 = this.currentNotifyFraction;
        boolean z2 = this.isLastUpToExpand;
        SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
        int size = this.fractionListeners.size();
        StringBuilder sb = new StringBuilder("onScrollYEnd velocity=");
        sb.append(pointF);
        sb.append(" displacement=");
        sb.append(f);
        sb.append(" currentQsFraction=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f2, " currentNotifyFraction=", f3, " shouldScrollToNext=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(" isLastUpToExpand=", " downExpanded=", sb, z, z2);
        sb.append(expanded);
        sb.append(" fractionListeners.size=");
        sb.append(size);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sb.toString(), null, 8);
        this.fractionListeners.clear();
        SeparateQSStatisicUtil.EXPANDED expanded2 = this.downExpanded;
        boolean z3 = false;
        int i = 1;
        if (expanded2 == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 = new OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1(z3 ? 1 : 0);
            oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.fractionListeners.add(oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1);
            if ((z && this.currentQsFraction == 1.0f) || (!z && this.currentQsFraction < 0.5f)) {
                oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.onFractionChangeFinished(this.currentQsFraction == 1.0f);
            }
            this.separateQSManager.dispatchTouchEvent(motionEvent);
        } else if (expanded2 == SeparateQSStatisicUtil.EXPANDED.QS) {
            OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$2 = new OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1(i);
            oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$2.this$0 = this;
            VarHandle.storeStoreFence();
            this.fractionListeners.add(oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$2);
            if ((z && this.currentNotifyFraction == 1.0f) || (!z && this.currentNotifyFraction < 0.5f)) {
                oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$2.onFractionChangeFinished(this.currentNotifyFraction == 1.0f);
            }
            this.notificationPanelViewController.getView().dispatchTouchEvent(motionEvent);
        }
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector != null) {
            oplusAxisSwipeDetector.finishedScrolling();
        }
    }

    @Override // com.oplus.systemui.separate.event.OplusAxisSwipeDetector.ScrollListener
    public void onScrollYStart(float f, MotionEvent motionEvent) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onScrollYStart", null, 8);
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.QS) {
            TemporarilyInfoManager.Companion.getInstance().setStarted(false);
            this.notificationPanelViewController.getView().dispatchTouchEvent(motionEvent);
            NotificationPanelView notificationPanelView = this.notificationPanelView;
            if (notificationPanelView != null) {
                notificationPanelView.bringToFront();
            }
        }
        if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            this.separateQSManager.dispatchTouchEvent(motionEvent);
            View view = this.qsPanelView;
            if (view != null) {
                view.bringToFront();
            }
        }
        getFractionAnimation().setStartValue(0.0f);
        getFractionAnimation().cancelComplete();
        OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
        if (horizontalTranslationValue.isRunning()) {
            horizontalTranslationValue.getProgressiveController().skipToEnd();
        }
    }

    public final void onSeparateNotifyExpandChange(boolean z) {
        if (!z || this.staticInfo.getDisuse()) {
            return;
        }
        if (this.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.NONE || isKeyguardVisible$1()) {
            onTrackComm(SeparateQSStatisicUtil.EXPANDED.NOTIFICATION);
        }
    }

    public final void onTrackComm(SeparateQSStatisicUtil.EXPANDED expanded) {
        this.staticInfo.setTo(expanded);
        SeparateQSStatisicUtil.collectSeparateQsAndNotiExpandEvent(getContext(), this.staticInfo);
        this.staticInfo.reset();
    }

    public final void onViewAttached() {
        this.separateQSManager.addCallback(this.separateQSPluginListener);
        RepeatWhenAttachedKt.repeatWhenAttached$default(((ViewController) this).mView, Dispatchers.getMain().getImmediate(), new C02811(null), 2);
        ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.statusBarHeight = SystemBarUtils.getStatusBarHeight(getContext());
        this.statusBarStateController.addCallback(this.statusBarStateListener);
    }

    public final void onViewDetached() {
        this.separateQSManager.removeCallback(this.separateQSPluginListener);
        this.statusBarStateController.removeCallback(this.statusBarStateListener);
        releaseProgressiveHelper();
    }

    public final void prepareHorizontalTranslationStart(boolean z) {
        if (shouldBlockOccludedHorizontalQsExpand()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "prepareHorizontalTranslationStart ignored: occluded and panels collapsed", null, 8);
            return;
        }
        this.notificationStackScrollLayoutController.getView().mExt.setHorizontalSwitchState(true);
        int iOrdinal = this.downExpanded.ordinal();
        if (iOrdinal == 1) {
            boolean zIsQsOnLeft = isQsOnLeft();
            float f = this.screenWidth;
            if (zIsQsOnLeft) {
                f = -f;
            }
            this.initTranslationX = f;
            View view = this.qsPanelView;
            if (view != null) {
                view.setTranslationX(f);
            }
            View view2 = this.qsPanelView;
            if (view2 != null) {
                view2.setAlpha(1.0f);
            }
            this.separateQSManager.expandQSPanel(false, z);
            JankUtils.gfxSceneBegin$default(getContext(), 22, "NC_TO_QS", 4L);
        } else if (iOrdinal == 2) {
            boolean zIsQsOnLeft2 = isQsOnLeft();
            float f2 = this.screenWidth;
            if (!zIsQsOnLeft2) {
                f2 = -f2;
            }
            this.initTranslationX = f2;
            NotificationPanelView notificationPanelView = this.notificationPanelView;
            if (notificationPanelView != null) {
                notificationPanelView.setTranslationX(f2);
            }
            NotificationPanelView notificationPanelView2 = this.notificationPanelView;
            if (notificationPanelView2 != null) {
                notificationPanelView2.setAlpha(1.0f);
            }
            NotificationPanelViewController notificationPanelViewController = this.notificationPanelViewController;
            notificationPanelViewController.getClass();
            LogUtil.i(LogUtil.TAG_QS, "NotificationPanelView", "expandNotifPanelWithoutAnimate");
            notificationPanelViewController.mFixedDuration = 0;
            ((NotificationRowShowAnimationEx) DependencyEx.sDependency.getDependency(NotificationRowShowAnimationEx.class)).expandedNoAnimation();
            notificationPanelViewController.notifyExpandingStarted();
            notificationPanelViewController.fling(0.0f, "expandNotifPanelWithoutAnimate", 1.0f, true, false);
            notificationPanelViewController.mFixedDuration = -1;
            TemporarilyInfoManager.Companion.getInstance().setStarted(false);
            JankUtils.gfxSceneBegin$default(getContext(), 23, "QS_TO_NC", 4L);
        }
        if (z) {
            return;
        }
        this.oplusGcSupressionExt.callGcSupression(3, 3000);
    }

    public final void releaseProgressiveHelper() {
        this.slidSwitchAnimation.getHorizontalTranslationValue().release();
    }

    public final void resetHorizontalTranslationView() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "resetHorizontalTranslationView", null, 8);
        View view = this.qsPanelView;
        if (view != null) {
            view.setTranslationX(0.0f);
        }
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        if (notificationPanelView != null) {
            notificationPanelView.setTranslationX(0.0f);
        }
        this.separateQSManager.setClipToPaddingForScroll(false);
        this.separateQSManager.handleIgnoreParentBoundsForScroll(false);
    }

    public final SeparateQSStatisicUtil.EXPANDED resolveReadablePanelForTranslationX() {
        View view = this.qsPanelView;
        boolean z = false;
        boolean z2 = view != null && view.getVisibility() == 0 && view.isShown() && !this.separateQSManager.isFullyCollapsed();
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        if (notificationPanelView != null && notificationPanelView.getVisibility() == 0 && notificationPanelView.isShown() && !this.notificationPanelViewController.isFullyCollapsed()) {
            z = true;
        }
        if (z2 && !z) {
            return SeparateQSStatisicUtil.EXPANDED.QS;
        }
        if (z && !z2) {
            return SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
        }
        SeparateQSStatisicUtil.EXPANDED expanded = this.lastDownExpanded;
        return (expanded == SeparateQSStatisicUtil.EXPANDED.QS || expanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) ? expanded : this.downExpanded;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x004b  */
    public final void setAlphaForScrollY(float f, float f2, View view) {
        float yScrollAppearDisplacement;
        if (f < 0.0f) {
            view.setAlpha(f2 <= 0.5f ? 1.0f : 0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
        SeparateQSStatisicUtil.EXPANDED expanded2 = SeparateQSStatisicUtil.EXPANDED.NOTIFICATION;
        if (expanded == expanded2) {
            boolean zIsLandscape = DeviceUtil.isLandscape(view.getContext());
            OplusSeparateQSManager oplusSeparateQSManager = this.separateQSManager;
            if (zIsLandscape) {
                Float firstElementShowDistance = oplusSeparateQSManager.getFirstElementShowDistance();
                yScrollAppearDisplacement = ((firstElementShowDistance != null ? firstElementShowDistance.floatValue() : 102.0f) / 0.85f) * 0.5f;
            } else {
                Float firstElementShowDistance2 = oplusSeparateQSManager.getFirstElementShowDistance();
                if (firstElementShowDistance2 != null) {
                    yScrollAppearDisplacement = firstElementShowDistance2.floatValue();
                } else {
                    yScrollAppearDisplacement = 150.0f;
                }
            }
        } else {
            OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
            if (oplusAxisSwipeDetector != null) {
                yScrollAppearDisplacement = oplusAxisSwipeDetector.getYScrollAppearDisplacement();
            } else {
                yScrollAppearDisplacement = 150.0f;
            }
        }
        if (this.downExpanded == expanded2) {
            f = (f * yScrollAppearDisplacement) / 150.0f;
        }
        getFractionAnimation().animateToFinalPosition(f);
    }

    public final boolean shouldBlockOccludedHorizontalQsExpand() {
        return this.keyguardStateController.mOccluded && this.separateQSManager.isFullyCollapsed() && this.notificationPanelViewController.isFullyCollapsed();
    }

    public final void stopHorizontalSlideSwitchOnCollapse(PanelType panelType) {
        boolean zIsRunning = this.slidSwitchAnimation.getHorizontalTranslationValue().isRunning();
        boolean zIsRunningAnimation = this.notificationPanelViewController.mPanelAnimationEx.isRunningAnimation();
        boolean zIsQsAnimationRunning = this.separateQSManager.isQsAnimationRunning();
        boolean zIsClosing = this.notificationPanelViewController.isClosing();
        boolean zIsClosing2 = this.separateQSManager.isClosing();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("stopHorizontalSlideSwitchOnCollapse callerPanel=");
            sb.append(panelType);
            sb.append(" isHRunning=");
            sb.append(zIsRunning);
            sb.append(" ntfRunning=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" qsRunning=", " isNtfClosing=", sb, zIsRunningAnimation, zIsQsAnimationRunning);
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" isQsClosing=", "OplusPanelViewPagerController-->", sb, zIsClosing, zIsClosing2));
        }
        if (panelType == null) {
            OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
            if (horizontalTranslationValue.isRunning()) {
                horizontalTranslationValue.getProgressiveController().skipToEnd();
                return;
            }
            return;
        }
        if (zIsRunning) {
            int iOrdinal = panelType.ordinal();
            if (iOrdinal == 0) {
                if (this.downExpanded == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
                    OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, false, true, false, null, 12);
                }
            } else if (iOrdinal == 1 && this.downExpanded == SeparateQSStatisicUtil.EXPANDED.QS) {
                this.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
            }
        }
    }

    public final void syncHorizontalTranslationDown() {
        OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
        Float lastDownExpandedTranslationX = getLastDownExpandedTranslationX();
        if (lastDownExpandedTranslationX != null) {
            horizontalTranslationValue.getProgressiveController().downTo(lastDownExpandedTranslationX.floatValue());
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    public final void syncHorizontalTranslationMove(float f) {
        float fCoerceIn;
        setTranslationXForScrollX(f);
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        float fCoerceIn2 = 0.0f;
        float translationX = notificationPanelView != null ? notificationPanelView.getTranslationX() : 0.0f;
        View view = this.qsPanelView;
        float translationX2 = view != null ? view.getTranslationX() : 0.0f;
        this.isOverScroll = !isQsOnLeft() ? !(translationX > 0.0f || translationX2 < 0.0f) : !(translationX < 0.0f || translationX2 > 0.0f);
        boolean zIsQsOnLeft = isQsOnLeft();
        NotificationPanelView notificationPanelView2 = this.notificationPanelView;
        if (zIsQsOnLeft) {
            if (notificationPanelView2 != null) {
                fCoerceIn = RangesKt___RangesKt.coerceIn(notificationPanelView2.getTranslationX(), 0.0f, this.screenWidth);
            } else {
                fCoerceIn = 0.0f;
            }
        } else if (notificationPanelView2 != null) {
            fCoerceIn = RangesKt___RangesKt.coerceIn(notificationPanelView2.getTranslationX(), -this.screenWidth, 0.0f);
        } else {
            fCoerceIn = 0.0f;
        }
        boolean zIsQsOnLeft2 = isQsOnLeft();
        View view2 = this.qsPanelView;
        if (zIsQsOnLeft2) {
            if (view2 != null) {
                fCoerceIn2 = RangesKt___RangesKt.coerceIn(view2.getTranslationX(), -this.screenWidth, 0.0f);
            }
        } else if (view2 != null) {
            fCoerceIn2 = RangesKt___RangesKt.coerceIn(view2.getTranslationX(), 0.0f, this.screenWidth);
        }
        NotificationPanelView notificationPanelView3 = this.notificationPanelView;
        if (notificationPanelView3 != null) {
            notificationPanelView3.setTranslationX(fCoerceIn);
        }
        View view3 = this.qsPanelView;
        if (view3 != null) {
            view3.setTranslationX(fCoerceIn2);
        }
        if (!this.isOverScroll) {
            OplusSlidSwitchAnimation.HorizontalTranslationValue horizontalTranslationValue = this.slidSwitchAnimation.getHorizontalTranslationValue();
            Float lastDownExpandedTranslationX = getLastDownExpandedTranslationX();
            if (lastDownExpandedTranslationX != null) {
                horizontalTranslationValue.getProgressiveController().moveTo(lastDownExpandedTranslationX.floatValue());
                return;
            }
            return;
        }
        int iOrdinal = this.downExpanded.ordinal();
        if (iOrdinal == 1 ? !isQsOnLeft() : iOrdinal == 2 && isQsOnLeft()) {
            f = -f;
        }
        float fCalculateRubberband = this.slidSwitchAnimation.getOverScrollTranslationValue().getProgressiveController().calculateRubberband(f, this.screenWidth);
        int iOrdinal2 = this.downExpanded.ordinal();
        if (iOrdinal2 == 1 ? isQsOnLeft() : !(iOrdinal2 != 2 || isQsOnLeft())) {
            fCalculateRubberband = -fCalculateRubberband;
        }
        Float lastDownExpandedTranslationX2 = getLastDownExpandedTranslationX();
        this.slidSwitchAnimation.getOverScrollTranslationValue().getProgressiveController().moveToRubberband(fCalculateRubberband, lastDownExpandedTranslationX2 != null ? (int) lastDownExpandedTranslationX2.floatValue() : 0);
    }

    public final void transferQNSwitchForKeyguard(float f, float f2, boolean z) {
        NotificationPanelView notificationPanelView;
        this.currentQsFraction = f;
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        if (centralSurfacesImpl == null || !centralSurfacesImpl.isKeyguardShowing()) {
            return;
        }
        OplusAxisSwipeDetector oplusAxisSwipeDetector = this.detector;
        if (oplusAxisSwipeDetector == null || !oplusAxisSwipeDetector.isDraggingOrSettling()) {
            float fCoerceIn = RangesKt___RangesKt.coerceIn(1.0f - (f / 0.45f), 0.0f, 1.0f);
            if ((fCoerceIn == 0.0f || fCoerceIn == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(NotificationPanelViewController$.ExternalSyntheticOutline0.m("transferQNSwitchForKeyguard expanded=", z, " expansion=", f2, " alpha="), fCoerceIn, "OplusPanelViewPagerController-->", "SystemUi--QuickSettings");
            }
            this.statusBarKeyguardViewManager.setQsExpansion(f2);
            if (z) {
                this.targetPage = PageType.QS;
                if (f2 == 1.0f && !this.staticInfo.getDisuse() && this.downOnYHotArea) {
                    onTrackComm(SeparateQSStatisicUtil.EXPANDED.QS);
                }
            } else if (f2 == 0.0f) {
                NotificationPanelView notificationPanelView2 = this.notificationPanelView;
                if (Intrinsics.areEqual(notificationPanelView2 != null ? Float.valueOf(notificationPanelView2.getTransitionAlpha()) : null, 0.0f) && (notificationPanelView = this.notificationPanelView) != null) {
                    notificationPanelView.setTransitionAlpha(1.0f);
                }
            }
            NotificationPanelView notificationPanelView3 = this.notificationPanelView;
            if (notificationPanelView3 != null) {
                updateNotificationPanelAlpha(notificationPanelView3, fCoerceIn, "transfer");
            }
        }
    }

    public final void updateNotifyExpandFraction(float f, boolean z) {
        SeparateQSStatisicUtil.EXPANDED expanded;
        this.currentNotifyFraction = f;
        if (isKeyguardVisible$1() || (expanded = this.downExpanded) == SeparateQSStatisicUtil.EXPANDED.NOTIFICATION) {
            return;
        }
        if (expanded == SeparateQSStatisicUtil.EXPANDED.NONE && this.currentNotifyFraction == 1.0f && this.currentQsFraction != 0.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->avoid overlap, collapseQSPanel");
            }
            OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, false, true, false, null, 12);
        }
        if (!z && f == 0.0f) {
            this.hasCalledDownEvent = false;
        }
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        Number numberValueOf = notificationPanelView != null ? Integer.valueOf(notificationPanelView.getHeight()) : Float.valueOf(this.screenHeight);
        for (OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 : this.fractionListeners) {
            oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.onMoveDisplacementChanged(numberValueOf.floatValue() * f);
            if (f == 0.0f || f == 1.0f) {
                oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.onFractionChangeFinished(f == 1.0f);
            }
        }
    }

    public final void updateQSExpandFraction(float f, boolean z, boolean z2, float f2) {
        this.currentQsFraction = f;
        if (isKeyguardVisible$1()) {
            return;
        }
        if (f == 0.0f) {
            NotificationPanelView notificationPanelView = this.notificationPanelView;
            if (!Intrinsics.areEqual(notificationPanelView != null ? Float.valueOf(notificationPanelView.getTransitionAlpha()) : null, 1.0f)) {
                NotificationPanelView notificationPanelView2 = this.notificationPanelView;
                if (notificationPanelView2 != null) {
                    updateNotificationPanelAlpha(notificationPanelView2, 1.0f, "updateQSExpandFraction");
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->updateQSExpandFraction force set transitionAlpha = 1 for notificationPanelView");
                }
            }
        }
        if (z && !this.staticInfo.getDisuse() && this.staticInfo.getFrom() == SeparateQSStatisicUtil.EXPANDED.NONE) {
            onTrackComm(SeparateQSStatisicUtil.EXPANDED.QS);
            NotificationPanelViewControllerEx ext = this.notificationPanelViewController.getExt();
            if (ext != null) {
                ext.resetHeadsUpAnimationStatus();
            }
        }
        SeparateQSStatisicUtil.EXPANDED expanded = this.downExpanded;
        if (expanded == SeparateQSStatisicUtil.EXPANDED.QS) {
            return;
        }
        if (expanded == SeparateQSStatisicUtil.EXPANDED.NONE && this.currentQsFraction == 1.0f && this.currentNotifyFraction != 0.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPanelViewPagerController-->avoid overlap, collapseNotifPanelWithoutAnimate");
            }
            this.notificationPanelViewController.collapseNotifPanelWithoutAnimate();
        }
        if (!z2 && f == 0.0f) {
            this.hasCalledDownEvent = false;
        }
        for (OplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1 : this.fractionListeners) {
            oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.onMoveDisplacementChanged(f2);
            if (f == 0.0f || f == 1.0f) {
                oplusPanelViewPagerController$onScrollYEnd$scrollFractionListener$1.onFractionChangeFinished(f == 1.0f);
            }
        }
    }
}
