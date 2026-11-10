package com.oplus.systemui.plugins.qs;

import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.runtime.OpaqueKey$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.insets.Protection$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSSecurity;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.util.DimensionKt;
import com.android.systemui.util.JankUtils;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.addtile.AddTileComponent;
import com.oplus.systemui.plugins.qs.animator.AnimatorUtils;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.bottom.OplusQSBottomComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSDragComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSLeftBTilesComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent;
import com.oplus.systemui.plugins.qs.customize.component.QSEmptyListTipComponent;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.DragAnimationState;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.tile.TextSizeConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.plugins.qs.edittop.EditTopComponent;
import com.oplus.systemui.plugins.qs.privacy.OplusQSPrivacyComponent;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent;
import com.oplus.systemui.plugins.qs.quickentrance.popupwindow.OplusQSMoreButtonPopupWindowController;
import com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController;
import com.oplus.systemui.plugins.qs.util.FlingAnimationUtils;
import com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.springtransition.BoundsTransition;
import com.oplus.systemui.qs.base.springtransition.FadeTransition;
import com.oplus.systemui.qs.base.springtransition.ScaleXTransition;
import com.oplus.systemui.qs.base.springtransition.ScaleYTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.TranslationXTransition;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.Gefingerpoken;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.SpecialLanguageTextScaleHelper;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplus.systemui.qs.base.widget.recyclerview.ParallelCOUIRecyclerView;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotificationShadeRegionHelper;
import com.oplusos.systemui.common.util.PerformanceUtils;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent extends ViewController implements IEditModeSwitcher, IComponentHostAbility {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSRootViewComponent";
    public int activePointerId;
    public final ViewGroup bottomArea;
    public boolean canSlideHToQSOnStatusBar;
    private final CellAreaProvider cellAreaProvider;
    private final OplusQSRootViewComponent$cellCalculatorListener$1 cellCalculatorListener;
    private final OplusQSRootViewComponent$cellConfigUpdateListener$1 cellConfigUpdateListener;
    private final Runnable collapseQSPanelRunnable;
    private Function0 collapsedEndBlock;
    public float curRawFraction;
    private final RecyclerViewDragHelper dragHelper;
    private final EditModeSwitchAnimationManager editModeSwitchAnimationManager;
    private final EditTopComponent editTopController;
    private Runnable exitEditRunnableAfterDragAnim;
    private ValueAnimator expandAnimator;
    public float expandedFraction;
    public float expandedHeight;
    public boolean expectingSynthesizedDown;
    private final SeparateQSFakeStatusController fakeStatusContainerController;
    private final Lazy flingAnimationUtils$delegate;
    private final CopyOnWriteArraySet<PanelFractionChangeListener> fractionChangeListeners;
    public boolean gestureWaitForTouchSlop;
    public boolean hasSceneQSCloseEnd;
    public boolean hasSetLayerType;
    private View headerView;
    public float initialHeightOnTouch;
    public boolean isAddingMode;
    public boolean isClosing;
    public boolean isEditMode;
    public boolean isExpanded;
    public boolean isGfxStart;
    public boolean isInPageIndicatorArea;
    public boolean isIntentCloseQs;
    public final boolean isLowGaussianLevel;
    public boolean isQsPullTraceStart;
    public boolean isScenarioTouch;
    public boolean isTouchDisabled;
    public boolean isTracking;
    public boolean isYTouchStart;
    public float lastExpandedHeight;
    private final PointF lastPos;
    public boolean launcherIntentExpand;
    private final LogBuffer logBuffer;
    private final Runnable longClickRunnable;
    public boolean mLastEventSynthesizedDown;
    private final CopyOnWriteArrayList<IQsComponent> nodes;
    private final IOplusGcSupressionExt oplusGcSupressionExt;
    public final COUIRecyclerView otherTilesContainerView;
    public final COUIRecyclerView otherTilesLandLeftContainerView;
    public final ViewGroup pageIndicatorParent;
    public boolean panelClosedOnDown;
    private final ConstraintLayout panelContainer;
    public float panelNegY;
    private Rect personalContainerRect;
    public final COUIRecyclerView personalContainerView;
    private final Lazy privacyBottomMargin$delegate;
    private final OplusQSPrivacyContainerView privacyView;
    private final OplusQSBottomComponent qsBottomComponent;
    public boolean qsDisabled;
    private final OplusQSRootViewComponent$qsElementEndListener$1 qsElementEndListener;
    private final OplusQSRootViewComponent$qsElementShowingChangeListener$1 qsElementShowingChangeListener;
    private final OplusQSMoreButtonPopupWindowController qsMoreButtonPopupWindowController;
    private final OplusQSTilesComponent qsOtherTilesComponent;
    private final QSPanelAnimatorManager qsPanelAnimatorManager;
    private final OplusQSRootViewComponent$qsPanelExpandFractionListener$1 qsPanelExpandFractionListener;
    private final OplusQSPersonalComponent qsPersonalComponent;
    private final OplusQSPrivacyComponent qsPrivacyComponent;
    private final OplusQSQuickEntranceComponent qsQuickEntranceComponent;
    private final OplusQSRootViewComponent$qsScenarioAnimListener$1 qsScenarioAnimListener;
    private final QSSecurity qsSecurity;
    public final View qsStatusBar;
    public final ConstraintLayout quickEntrance;
    public int smallestScreenWidth;
    private final ViewGroup statusBarContainer;
    private final QSElement statusBarHeader;
    private final OplusSeparateQSPlugin.IStub stub;
    private final ConstraintSet tmpConstraintSet;
    private final PointF touchDownPoint;
    public final int touchSlop;
    private final Lazy transitionProperties$delegate;
    private final Lazy velocityTracker$delegate;
    private Rect viewpagerRect;
    public float yVelocity;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0 {
        public final Object invoke() {
            OplusQSRootViewComponent oplusQSRootViewComponent = (OplusQSRootViewComponent) ((CallableReference) this).receiver;
            Companion companion = OplusQSRootViewComponent.Companion;
            oplusQSRootViewComponent.tryStopGfx();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function0 {
        public final Object invoke() {
            OplusQSRootViewComponent oplusQSRootViewComponent = (OplusQSRootViewComponent) ((CallableReference) this).receiver;
            Companion companion = OplusQSRootViewComponent.Companion;
            if (oplusQSRootViewComponent.isTracking && oplusQSRootViewComponent.isYTouchStart && !GlobalUtils.hasDialogShowing()) {
                GlobalUtils.sendCancelMotionEvent((ViewGroup) oplusQSRootViewComponent.mView, "notifyNodeNumChanged");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PanelFractionChangeListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TouchHandler implements Gefingerpoken {
        public /* synthetic */ OplusQSRootViewComponent this$0;

        public final boolean degreeMatched(MotionEvent motionEvent, int i) {
            float rawY = motionEvent.getRawY(i);
            OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
            return Math.atan(((double) Math.abs(rawY - oplusQSRootViewComponent.touchDownPoint.y)) / ((double) Math.abs(motionEvent.getRawX(i) - oplusQSRootViewComponent.touchDownPoint.x))) > 1.0471975803375244d;
        }

        @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }

        public final boolean isInBottomSlidingBackArea(float f) {
            OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
            Companion companion = OplusQSRootViewComponent.Companion;
            return f > ((float) ((OplusQSRootView) oplusQSRootViewComponent.mView).getHeight()) - DimensionKt.dpToPx(Float.valueOf(22.0f), oplusQSRootViewComponent.getContext());
        }

        /* JADX WARN: Code duplicated, block: B:103:0x0183 A[RETURN] */
        @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int actionIndex;
            int pointerId;
            int i;
            OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
            int i2 = oplusQSRootViewComponent.touchSlop;
            if (oplusQSRootViewComponent.isEditMode) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    oplusQSRootViewComponent.touchDownPoint.set(motionEvent.getRawX(), motionEvent.getRawY());
                } else if (actionMasked == 2) {
                    if (!oplusQSRootViewComponent.isAddingMode ? !(Math.abs(motionEvent.getRawY(motionEvent.getActionIndex()) - oplusQSRootViewComponent.touchDownPoint.y) <= i2 || !degreeMatched(motionEvent, motionEvent.getActionIndex())) : isInBottomSlidingBackArea(oplusQSRootViewComponent.touchDownPoint.y) || super.onInterceptTouchEvent(motionEvent)) {
                        return false;
                    }
                    return true;
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
            if (!oplusQSRootViewComponent.isTouchDisabled) {
                if (oplusQSRootViewComponent.isTracking && ((motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5) && oplusQSRootViewComponent.activePointerId != -1 && (pointerId = motionEvent.getPointerId((actionIndex = motionEvent.getActionIndex()))) >= 0 && pointerId != (i = oplusQSRootViewComponent.activePointerId))) {
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("isSecondPointDown ptrId=", pointerId, i, " activePointerId=", " ptrIdx=");
                    sbM.append(actionIndex);
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, sbM.toString(), null, 8);
                    oplusQSRootViewComponent.activePointerId = pointerId;
                    int actionIndex2 = motionEvent.getActionIndex();
                    oplusQSRootViewComponent.touchDownPoint.set(motionEvent.getRawX(actionIndex2) - (oplusQSRootViewComponent.lastPos.x - oplusQSRootViewComponent.touchDownPoint.x), motionEvent.getRawY(actionIndex2) - (oplusQSRootViewComponent.lastPos.y - oplusQSRootViewComponent.touchDownPoint.y));
                    oplusQSRootViewComponent.lastPos.set(oplusQSRootViewComponent.touchDownPoint.x, oplusQSRootViewComponent.touchDownPoint.y);
                    return true;
                }
                int iFindPointerIndex = motionEvent.findPointerIndex(oplusQSRootViewComponent.activePointerId);
                if (iFindPointerIndex < 0) {
                    oplusQSRootViewComponent.activePointerId = motionEvent.getPointerId(0);
                    iFindPointerIndex = 0;
                }
                OplusQSRootViewComponent.access$addMovement(oplusQSRootViewComponent, motionEvent, iFindPointerIndex);
                OplusQSRootViewComponent.access$initDownEvent(oplusQSRootViewComponent, motionEvent);
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 != 0) {
                    if (actionMasked2 != 1) {
                        if (actionMasked2 == 2) {
                            OplusQSRootViewComponent.access$resetScrollIfNoDownEvent(oplusQSRootViewComponent, motionEvent, iFindPointerIndex);
                            if (OplusQSRootViewComponent.access$canScrollX(oplusQSRootViewComponent, motionEvent)) {
                                float rawY = motionEvent.getRawY(iFindPointerIndex) - oplusQSRootViewComponent.touchDownPoint.y;
                                if (Math.abs(rawY) > i2) {
                                    if (Math.abs(rawY) > i2 && degreeMatched(motionEvent, iFindPointerIndex) && rawY < 0.0f) {
                                        oplusQSRootViewComponent.touchDownPoint.set(motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                                        return true;
                                    }
                                    if (!oplusQSRootViewComponent.panelClosedOnDown && Math.abs(rawY) > i2 && degreeMatched(motionEvent, iFindPointerIndex) && rawY > 0.0f) {
                                        oplusQSRootViewComponent.touchDownPoint.set(motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                                        return true;
                                    }
                                }
                            }
                            return true;
                        }
                        if (actionMasked2 == 3) {
                        }
                    }
                    oplusQSRootViewComponent.resetTouch();
                } else {
                    oplusQSRootViewComponent.setPanelClosedOnDown(oplusQSRootViewComponent.isFullyCollapsed());
                    if (oplusQSRootViewComponent.isFullyCollapsed()) {
                        oplusQSRootViewComponent.reset("onInterceptTouchEvent ACTION_DOWN");
                    }
                    oplusQSRootViewComponent.startExpandMotion(motionEvent, iFindPointerIndex, false);
                    if (!oplusQSRootViewComponent.isInViewpagerArea(oplusQSRootViewComponent.touchDownPoint) && ((OplusQSRootView) oplusQSRootViewComponent.mView).getDisallowChildIntercept()) {
                        ((OplusQSRootView) oplusQSRootViewComponent.mView).setDisallowChildIntercept(false);
                    }
                    ValueAnimator valueAnimator = oplusQSRootViewComponent.expandAnimator;
                    if (valueAnimator != null) {
                        valueAnimator.cancel();
                    }
                    ViewGroup viewGroup = oplusQSRootViewComponent.pageIndicatorParent;
                    oplusQSRootViewComponent.isInPageIndicatorArea = GlobalUtils.isTouchInView(motionEvent, viewGroup != null ? viewGroup : null);
                }
                if (oplusQSRootViewComponent.isFullyCollapsed() || oplusQSRootViewComponent.expectingSynthesizedDown || oplusQSRootViewComponent.isClosing || super.onInterceptTouchEvent(motionEvent)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Code duplicated, block: B:13:0x0033  */
        /* JADX WARN: Code duplicated, block: B:143:0x02a2  */
        /* JADX WARN: Code duplicated, block: B:15:0x005f  */
        /* JADX WARN: Code duplicated, block: B:22:0x0092  */
        /* JADX WARN: Code duplicated, block: B:23:0x0094  */
        /* JADX WARN: Code duplicated, block: B:26:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:34:0x00bf  */
        /* JADX WARN: Code duplicated, block: B:35:0x00c3  */
        @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean zIsFullyCollapsed;
            boolean z;
            boolean z2;
            float yVelocity;
            float rawY;
            boolean zBooleanValue;
            boolean z3;
            OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
            int i = oplusQSRootViewComponent.touchSlop;
            if (oplusQSRootViewComponent.isFullyExpanded() && oplusQSRootViewComponent.isEditMode) {
                int actionIndex = motionEvent.getActionIndex();
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    oplusQSRootViewComponent.getVelocityTracker().clear();
                    oplusQSRootViewComponent.getVelocityTracker().addMovement(motionEvent);
                    if (((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isLargeScreen().getValue()).booleanValue() && isInBottomSlidingBackArea(motionEvent.getRawY())) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->[onTouchEventWhenEditMode] isLargeScreen and InBottomSlidingBackArea, return true");
                            return true;
                        }
                    }
                } else if (actionMasked == 1) {
                    oplusQSRootViewComponent.getVelocityTracker().addMovement(motionEvent);
                    oplusQSRootViewComponent.getVelocityTracker().computeCurrentVelocity(1000);
                    yVelocity = oplusQSRootViewComponent.getVelocityTracker().getYVelocity(actionIndex);
                    rawY = motionEvent.getRawY(actionIndex) - oplusQSRootViewComponent.touchDownPoint.y;
                    oplusQSRootViewComponent.onTrackingStopped$1(false);
                    oplusQSRootViewComponent.isYTouchStart = false;
                    if (degreeMatched(motionEvent, actionIndex)) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("[onTouchEventWhenEditMode]ACTION_UP, degreeMatched: deltaY=", rawY, ",velocityY=", yVelocity, "OplusQSRootViewComponent-->"));
                        }
                        zBooleanValue = ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isGestureNavigation().getValue()).booleanValue();
                        if (rawY < -102.0f) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolean z4 = yVelocity < (-DimensionKt.dpToPx(Float.valueOf(250.0f), oplusQSRootViewComponent.getContext()));
                        boolean zIsInBottomSlidingBackArea = isInBottomSlidingBackArea(oplusQSRootViewComponent.touchDownPoint.y);
                        if (zBooleanValue && ((z3 || z4) && zIsInBottomSlidingBackArea)) {
                            if (oplusQSRootViewComponent.isAddingMode) {
                                oplusQSRootViewComponent.exitAddingMode(true);
                            } else {
                                oplusQSRootViewComponent.exitEditMode(true);
                            }
                        }
                        oplusQSRootViewComponent.getQsPanelAnimatorManager().startRebound();
                        return true;
                    }
                } else if (actionMasked == 2) {
                    oplusQSRootViewComponent.getVelocityTracker().addMovement(motionEvent);
                } else if (actionMasked == 3) {
                    oplusQSRootViewComponent.getVelocityTracker().addMovement(motionEvent);
                    oplusQSRootViewComponent.getVelocityTracker().computeCurrentVelocity(1000);
                    yVelocity = oplusQSRootViewComponent.getVelocityTracker().getYVelocity(actionIndex);
                    rawY = motionEvent.getRawY(actionIndex) - oplusQSRootViewComponent.touchDownPoint.y;
                    oplusQSRootViewComponent.onTrackingStopped$1(false);
                    oplusQSRootViewComponent.isYTouchStart = false;
                    if (degreeMatched(motionEvent, actionIndex)) {
                        if (LogUtil.getNormalDebug()) {
                            Log.d("SystemUi--QuickSettings", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("[onTouchEventWhenEditMode]ACTION_UP, degreeMatched: deltaY=", rawY, ",velocityY=", yVelocity, "OplusQSRootViewComponent-->"));
                        }
                        zBooleanValue = ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isGestureNavigation().getValue()).booleanValue();
                        if (rawY < -102.0f) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (yVelocity < (-DimensionKt.dpToPx(Float.valueOf(250.0f), oplusQSRootViewComponent.getContext()))) {
                        }
                        boolean zIsInBottomSlidingBackArea2 = isInBottomSlidingBackArea(oplusQSRootViewComponent.touchDownPoint.y);
                        if (zBooleanValue) {
                            if (oplusQSRootViewComponent.isAddingMode) {
                                oplusQSRootViewComponent.exitAddingMode(true);
                            } else {
                                oplusQSRootViewComponent.exitEditMode(true);
                            }
                        }
                        oplusQSRootViewComponent.getQsPanelAnimatorManager().startRebound();
                        return true;
                    }
                }
                return super.onTouchEvent(motionEvent);
            }
            if (!oplusQSRootViewComponent.isTouchDisabled) {
                int iFindPointerIndex = motionEvent.findPointerIndex(oplusQSRootViewComponent.activePointerId);
                if (iFindPointerIndex < 0) {
                    oplusQSRootViewComponent.activePointerId = motionEvent.getPointerId(0);
                    iFindPointerIndex = 0;
                }
                OplusQSRootViewComponent.access$addMovement(oplusQSRootViewComponent, motionEvent, iFindPointerIndex);
                if (oplusQSRootViewComponent.mLastEventSynthesizedDown && motionEvent.getAction() == 1) {
                    oplusQSRootViewComponent.launcherIntentExpand = true;
                    oplusQSRootViewComponent.flingQS(0.0f, true);
                }
                OplusQSRootViewComponent.access$initDownEvent(oplusQSRootViewComponent, motionEvent);
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 == 0) {
                    ((OplusQSRootView) oplusQSRootViewComponent.mView).postDelayed(oplusQSRootViewComponent.longClickRunnable, 400L);
                    oplusQSRootViewComponent.canSlideHToQSOnStatusBar = oplusQSRootViewComponent.stub.canSlideHToQSOnStatusBar(new PointF(motionEvent.getX(), motionEvent.getY()));
                    if (oplusQSRootViewComponent.expectingSynthesizedDown) {
                        oplusQSRootViewComponent.expectingSynthesizedDown = false;
                        zIsFullyCollapsed = false;
                    } else {
                        zIsFullyCollapsed = oplusQSRootViewComponent.isFullyCollapsed();
                    }
                    oplusQSRootViewComponent.gestureWaitForTouchSlop = zIsFullyCollapsed;
                    oplusQSRootViewComponent.setPanelClosedOnDown(oplusQSRootViewComponent.isFullyCollapsed());
                    oplusQSRootViewComponent.startExpandMotion(motionEvent, iFindPointerIndex, false);
                    oplusQSRootViewComponent.lastPos.set(motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                    if (oplusQSRootViewComponent.panelClosedOnDown) {
                        oplusQSRootViewComponent.stub.gpuBoomForTouchEvent(motionEvent);
                    }
                    if (!oplusQSRootViewComponent.gestureWaitForTouchSlop) {
                        oplusQSRootViewComponent.onTrackingStarted$1();
                    }
                    oplusQSRootViewComponent.yVelocity = 0.0f;
                    boolean z5 = oplusQSRootViewComponent.gestureWaitForTouchSlop;
                    boolean z6 = oplusQSRootViewComponent.isTracking;
                    boolean z7 = oplusQSRootViewComponent.panelClosedOnDown;
                    float f = oplusQSRootViewComponent.expandedFraction;
                    boolean z8 = oplusQSRootViewComponent.expectingSynthesizedDown;
                    StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("gestureWaitForTouchSlop:", ", isTracking:", ", panelClosedOnDown:", z5, z6);
                    sbM.append(z7);
                    sbM.append(" expandedFraction=");
                    sbM.append(f);
                    sbM.append(" expectingSynthesizedDown=");
                    sbM.append(z8);
                    LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, sbM.toString(), null, 8);
                } else if (actionMasked2 == 1) {
                    ((OplusQSRootView) oplusQSRootViewComponent.mView).removeCallbacks(oplusQSRootViewComponent.longClickRunnable);
                    OplusQSRootViewComponent.access$endMotionEvent(oplusQSRootViewComponent, motionEvent, motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                    oplusQSRootViewComponent.launcherIntentExpand = false;
                    oplusQSRootViewComponent.isYTouchStart = false;
                    oplusQSRootViewComponent.yVelocity = 0.0f;
                } else if (actionMasked2 == 2) {
                    OplusQSRootViewComponent.access$resetScrollIfNoDownEvent(oplusQSRootViewComponent, motionEvent, iFindPointerIndex);
                    if (OplusQSRootViewComponent.access$canScrollX(oplusQSRootViewComponent, motionEvent)) {
                        float rawY2 = motionEvent.getRawY(iFindPointerIndex) - oplusQSRootViewComponent.touchDownPoint.y;
                        if (Math.abs(motionEvent.getRawX(iFindPointerIndex) - oplusQSRootViewComponent.touchDownPoint.x) > i || Math.abs(rawY2) > i) {
                            ((OplusQSRootView) oplusQSRootViewComponent.mView).removeCallbacks(oplusQSRootViewComponent.longClickRunnable);
                        }
                        boolean z9 = Math.abs(rawY2) > ((float) i) && Math.abs(rawY2) > Math.abs(motionEvent.getRawX(iFindPointerIndex) - oplusQSRootViewComponent.touchDownPoint.x);
                        if (z9 && oplusQSRootViewComponent.gestureWaitForTouchSlop && !oplusQSRootViewComponent.isTracking) {
                            oplusQSRootViewComponent.getVelocityTracker().computeCurrentVelocity(1000);
                            oplusQSRootViewComponent.yVelocity = oplusQSRootViewComponent.getVelocityTracker().getYVelocity();
                            oplusQSRootViewComponent.cancelAnimator();
                            oplusQSRootViewComponent.startExpandMotion(motionEvent, iFindPointerIndex, true);
                            if (oplusQSRootViewComponent.curRawFraction == 0.0f && Math.abs(oplusQSRootViewComponent.yVelocity) > 1000.0f) {
                                oplusQSRootViewComponent.getQsPanelAnimatorManager().setAnimationStartValue(1.0E-4f);
                            }
                        }
                        if (!oplusQSRootViewComponent.isYTouchStart && z9) {
                            oplusQSRootViewComponent.isYTouchStart = true;
                        }
                        float defaultSettingEngine = oplusQSRootViewComponent.getQsPanelAnimatorManager().getDefaultSettingEngine();
                        float maxPanelHeight = oplusQSRootViewComponent.getMaxPanelHeight() == 0 ? 0.0f : (oplusQSRootViewComponent.initialHeightOnTouch * defaultSettingEngine) / oplusQSRootViewComponent.getMaxPanelHeight();
                        if ((!oplusQSRootViewComponent.gestureWaitForTouchSlop || oplusQSRootViewComponent.isTracking) && ((((z = oplusQSRootViewComponent.isClosing) && rawY2 > 0.0f) || !z) && !oplusQSRootViewComponent.isIntentCloseQs && ((z2 = oplusQSRootViewComponent.canSlideHToQSOnStatusBar) || (!z2 && oplusQSRootViewComponent.shouldExpand(oplusQSRootViewComponent.getVelocityTracker().getYVelocity(), motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex), false))))) {
                            float f2 = defaultSettingEngine == 0.0f ? 1.0f : defaultSettingEngine / 300.0f;
                            float f3 = (rawY2 * f2) + maxPanelHeight;
                            float f4 = defaultSettingEngine * 0.86f;
                            if (maxPanelHeight < f4) {
                                rawY2 = (f3 - f4) / f2;
                            }
                            oplusQSRootViewComponent.getQsPanelAnimatorManager().onTouchMove(f3, oplusQSRootViewComponent.yVelocity, oplusQSRootViewComponent.panelClosedOnDown || oplusQSRootViewComponent.isClosing);
                            if (rawY2 >= 0.0f) {
                                oplusQSRootViewComponent.getQsPanelAnimatorManager().onMoveOver(rawY2, oplusQSRootViewComponent.panelClosedOnDown || oplusQSRootViewComponent.isClosing, oplusQSRootViewComponent.resetExtraFakeTranslationY(true), oplusQSRootViewComponent.yVelocity);
                            }
                        }
                        oplusQSRootViewComponent.lastPos.set(motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                    }
                } else if (actionMasked2 == 3) {
                    ((OplusQSRootView) oplusQSRootViewComponent.mView).removeCallbacks(oplusQSRootViewComponent.longClickRunnable);
                    OplusQSRootViewComponent.access$endMotionEvent(oplusQSRootViewComponent, motionEvent, motionEvent.getRawX(iFindPointerIndex), motionEvent.getRawY(iFindPointerIndex));
                    oplusQSRootViewComponent.launcherIntentExpand = false;
                    oplusQSRootViewComponent.isYTouchStart = false;
                    oplusQSRootViewComponent.yVelocity = 0.0f;
                } else if (actionMasked2 == 6) {
                    OplusQSRootViewComponent.access$onSecondaryPointerUp(oplusQSRootViewComponent, motionEvent);
                }
                if (!oplusQSRootViewComponent.gestureWaitForTouchSlop || oplusQSRootViewComponent.isTracking || (motionEvent.getActionMasked() == 0 && oplusQSRootViewComponent.panelClosedOnDown)) {
                }
            }
            return false;
            return true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$exitEditMode$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ boolean $withAnimation;
        public /* synthetic */ OplusQSRootViewComponent this$0;

        @Override // java.lang.Runnable
        public final void run() {
            LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "exitEditMode exitEditRunnableAfterDragAnim start", null, 8);
            boolean z = this.$withAnimation;
            OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
            if (z) {
                oplusQSRootViewComponent.editModeSwitchAnimationManager.exitEditMode();
            } else {
                oplusQSRootViewComponent.editModeSwitchAnimationManager.skipToEnd();
            }
            this.this$0.getCellAreaProvider().setEditMode(this.this$0.isEditMode);
            CopyOnWriteArrayList copyOnWriteArrayList = this.this$0.nodes;
            boolean z2 = this.$withAnimation;
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((IQsComponent) it.next()).onExitEditMode(z2);
            }
            this.this$0.getStatusBarContainer().setLayerType(0, null);
            this.this$0.getStatusBarContainer().setVisibility(0);
            this.this$0.privacyView.setVisibility(0);
            this.this$0.updateResource("exitEditMode", false);
            this.this$0.updateStatusBarTopMargin();
            TemporarilyInfoManager.Companion.getInstance().setStarted(1.0f == this.this$0.expandedFraction);
            this.this$0.stub.updateEditMode(this.this$0.isEditMode);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01951 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00411 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQSRootViewComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00421 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00431 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ OplusQSRootViewComponent this$0;

                    public /* synthetic */ C00431(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                ((Number) obj).intValue();
                                OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                                int i = oplusQSRootViewComponent.smallestScreenWidth;
                                OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
                                if (i != ((Number) companion.getInstance().getSmallestScreenWidth().getValue()).intValue()) {
                                    oplusQSRootViewComponent.smallestScreenWidth = ((Number) companion.getInstance().getSmallestScreenWidth().getValue()).intValue();
                                }
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-smallestScreenWidthChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(oplusQSRootViewComponent.smallestScreenWidth, "foldOpen change updateResource: "), null, 8);
                                oplusQSRootViewComponent.getCellAreaProvider().updateCellAreaConfig();
                                oplusQSRootViewComponent.updateResource("smallestScreenWidthChanged", false);
                                Trace.traceEnd(8L);
                                break;
                            case 1:
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("superPowerSaveMode change updateResource:  ", ((Boolean) obj).booleanValue()), null, 8);
                                OplusQSRootViewComponent oplusQSRootViewComponent2 = this.this$0;
                                oplusQSRootViewComponent2.getCellAreaProvider().updateCellAreaConfig();
                                oplusQSRootViewComponent2.updateResource("superPowerSaveModeChanged", false);
                                break;
                            case 2:
                                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-enableStrokeConfigChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "isStrokeSupport: " + zBooleanValue, null, 8);
                                OplusQSRootViewComponent.access$updateColor(this.this$0, QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
                                Trace.traceEnd(8L);
                                break;
                            case 3:
                                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-isDefaultFontChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "isDefaultFont change updateResource: " + zBooleanValue2, null, 8);
                                OplusQSRootViewComponent.access$updateTextSize(this.this$0, zBooleanValue2);
                                Trace.traceEnd(8L);
                                break;
                            case 4:
                                this.this$0.stub.notifyScenarioGuideState((String) obj);
                                break;
                            case 5:
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "overlayChangedEvent change ", null, 8);
                                OplusQSRootViewComponent.access$updateColor(this.this$0, QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
                                break;
                            case 6:
                                ((Boolean) obj).getClass();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-isNightModeChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion.getInstance().isNightMode().getValue(), "isNightMode change updateResource: "), null, 8);
                                OplusQSRootViewComponent.access$updateColor(this.this$0, QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
                                Trace.traceEnd(8L);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                ((Number) obj).intValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-colorStateChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion.getInstance().getColorState().getValue(), "colorState change updateResource: "), null, 8);
                                OplusQSRootViewComponent.access$updateColor(this.this$0, QSSpecialModeUpdateMonitor.ColorType.COLOR_STATE);
                                Trace.traceEnd(8L);
                                break;
                            case 8:
                                boolean zBooleanValue3 = ((Boolean) obj).booleanValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-enableShowTileNameChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName().getValue(), "enable show tile name: "), null, 8);
                                OplusQSRootViewComponent oplusQSRootViewComponent3 = this.this$0;
                                oplusQSRootViewComponent3.getCellAreaProvider().updateCellAreaConfig();
                                oplusQSRootViewComponent3.updateResource("enableShowTileNameChanged", false);
                                oplusQSRootViewComponent3.stub.notifyEnableShowTileNameChange(zBooleanValue3);
                                Trace.traceEnd(8L);
                                break;
                            case 9:
                                boolean zBooleanValue4 = ((Boolean) obj).booleanValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-enableColorfulConfigChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "isTileColorfulEnable: " + zBooleanValue4, null, 8);
                                OplusQSRootViewComponent.access$updateColor(this.this$0, QSSpecialModeUpdateMonitor.ColorType.UI_MODE);
                                Trace.traceEnd(8L);
                                break;
                            case 10:
                                boolean zBooleanValue5 = ((Boolean) obj).booleanValue();
                                OplusQSRootViewComponent oplusQSRootViewComponent4 = this.this$0;
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion), "landSplitMode change updateResource: "), null, 8);
                                if (zBooleanValue5) {
                                    oplusQSRootViewComponent4.exitEditMode(false);
                                }
                                oplusQSRootViewComponent4.getCellAreaProvider().updateCellAreaConfig();
                                oplusQSRootViewComponent4.updateResource("landSplitModeChanged", true);
                                oplusQSRootViewComponent4.qsOtherTilesComponent.enableRecyclerViewHeightAnimation();
                                TemporarilyInfoManager.Companion.getInstance().setLandSplitMode(zBooleanValue5);
                                oplusQSRootViewComponent4.hideCouiPopup();
                                break;
                            case 11:
                                ((Number) obj).intValue();
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-rotationChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion.getInstance().getRotation().getValue(), "rotation change updateResource: "), null, 8);
                                OplusQSRootViewComponent.access$handleLargeScreenRotation(this.this$0);
                                Trace.traceEnd(8L);
                                break;
                            case 12:
                                DragAnimationState dragAnimationState = (DragAnimationState) obj;
                                OplusQSRootViewComponent oplusQSRootViewComponent5 = this.this$0;
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, "[dragAnimationStateFlow] DragAnimationState is " + dragAnimationState, null, 8);
                                if (dragAnimationState instanceof DragAnimationState.Idle) {
                                    Runnable runnable = oplusQSRootViewComponent5.exitEditRunnableAfterDragAnim;
                                    if (runnable != null) {
                                        runnable.run();
                                    }
                                    oplusQSRootViewComponent5.exitEditRunnableAfterDragAnim = null;
                                }
                                break;
                            default:
                                Trace.traceBegin(8L, "OplusQSRootViewComponent-languageCountryChanged");
                                LogUtil.i$default(LogUtil.TAG_QS, OplusQSRootViewComponent.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion.getInstance().getLanguageCountry().getValue(), "languageCountry change updateResource: "), null, 8);
                                this.this$0.getCellAreaProvider().updateCellAreaConfig();
                                Trace.traceEnd(8L);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00421(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00421(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow smallestScreenWidth = OplusQSSpecialModeProvider.Companion.getInstance().getSmallestScreenWidth();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00431 c00431 = new C00431(0);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (smallestScreenWidth.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$10, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass10 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass10(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass10(this.this$0, continuation);
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
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(i2);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (superPowerSaveMode.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$11, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass11 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass11(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass11(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow supportStrokeCorner = SepQSResPool.INSTANCE.getSupportStrokeCorner();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(2);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (supportStrokeCorner.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$12, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass12 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass12(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass12(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsDefaultFont = OplusQSSpecialModeProvider.Companion.getInstance().isDefaultFont();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(3);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsDefaultFont.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$13, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass13 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass13(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass13(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow scenarioPreviewInfo = this.this$0.stub.bindPrivary().getScenarioPreviewInfo();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(4);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (scenarioPreviewInfo.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$14, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass14 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass14(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass14(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SharedFlow overlayChangedEvent = OplusQSSpecialModeProvider.Companion.getInstance().getOverlayChangedEvent();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(5);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (overlayChangedEvent.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
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
                        StateFlow stateFlowIsNightMode = OplusQSSpecialModeProvider.Companion.getInstance().isNightMode();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(6);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsNightMode.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
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
                        StateFlow colorState = OplusQSSpecialModeProvider.Companion.getInstance().getColorState();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(7);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (colorState.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
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
                        StateFlow enableShowTileName = OplusSeparateTileNameState.Companion.getInstance().getEnableShowTileName();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(8);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (enableShowTileName.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsTileColorfulEnable = OplusQSSpecialModeProvider.Companion.getInstance().isTileColorfulEnable();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(9);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsTileColorfulEnable.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$6, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow stateFlowIsLandSplitMode = OplusQSSpecialModeProvider.Companion.getInstance().isLandSplitMode();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(10);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (stateFlowIsLandSplitMode.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$7, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass7 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass7(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow rotation = OplusQSSpecialModeProvider.Companion.getInstance().getRotation();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(11);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (rotation.collect(c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$8, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass8 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass8(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass8(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow dragAnimationStateFlow = this.this$0.dragHelper.getDragAnimationStateFlow();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(12);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (FlowExtKt.collectTraced(dragAnimationStateFlow, "OplusQSRootViewComponent-collect-allSelectable", c00431, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$onViewAttached$1$1$9, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass9 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQSRootViewComponent this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass9(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQSRootViewComponent;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass9(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow languageCountry = OplusQSSpecialModeProvider.Companion.getInstance().getLanguageCountry();
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        C00421.C00431 c00431 = new C00421.C00431(13);
                        c00431.this$0 = oplusQSRootViewComponent;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (languageCountry.collect(c00431, this) == coroutine_suspended) {
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
            public C00411(OplusQSRootViewComponent oplusQSRootViewComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSRootViewComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00411 c00411 = new C00411(this.this$0, continuation);
                c00411.L$0 = obj;
                return c00411;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00421(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass7(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass8(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass9(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass10(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass11(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass12(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass13(this.this$0, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass14(this.this$0, null), 3);
                SepQSTileViewBinder.collectTileViewRes(coroutineScope, this.this$0.nodes);
                return Unit.INSTANCE;
            }
        }

        public C01951(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            C01951 c01951 = OplusQSRootViewComponent.this.new C01951((Continuation) obj3);
            c01951.L$0 = (LifecycleOwner) obj;
            return c01951.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00411 c00411 = new C00411(OplusQSRootViewComponent.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00411, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$updateResource$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01961 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQSRootViewComponent this$0;

        public /* synthetic */ RunnableC01961(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    if (this.this$0.isFullyExpanded()) {
                        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
                        oplusQSRootViewComponent.expandedHeight = oplusQSRootViewComponent.getMaxPanelHeight();
                    }
                    break;
                case 1:
                    OplusQSRootViewComponent oplusQSRootViewComponent2 = this.this$0;
                    Companion companion = OplusQSRootViewComponent.Companion;
                    oplusQSRootViewComponent2.collapseQSPanelInner();
                    break;
                default:
                    OplusQSRootViewComponent oplusQSRootViewComponent3 = this.this$0;
                    Companion companion2 = OplusQSRootViewComponent.Companion;
                    oplusQSRootViewComponent3.longClickBlock();
                    break;
            }
        }
    }

    public static Unit $r8$lambda$CZFCS0XhN003hG2l4EpGkKXImho(OplusQSRootViewComponent oplusQSRootViewComponent) {
        oplusQSRootViewComponent.qsQuickEntranceComponent.resetTranslationYExpand();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$eHalvMl-PSiwpuEVXkzjvKihcEQ, reason: not valid java name */
    public static Unit m116$r8$lambda$eHalvMlPSiwpuEVXkzjvKihcEQ(OplusQSRootViewComponent oplusQSRootViewComponent, float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.9f, 1.0f);
        oplusQSRootViewComponent.setTileZoneScaleInfo(oplusQSRootViewComponent.qsPersonalComponent.$$delegate_0.isTileDataEmpty() ? (f - 0.9f) / 0.100000024f : 1.0f, fCoerceIn, 1.0f - ((fCoerceIn - 0.9f) / 0.100000024f), false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSRootViewComponent(OplusQSRootView oplusQSRootView, OplusQSPersonalComponent oplusQSPersonalComponent, OplusQSPrivacyContainerView oplusQSPrivacyContainerView, OplusQSLeftBTilesComponent oplusQSLeftBTilesComponent, OplusQSTilesComponent oplusQSTilesComponent, QSEmptyListTipComponent qSEmptyListTipComponent, ViewGroup viewGroup, ConstraintLayout constraintLayout, OplusSeparateQSPlugin.IStub iStub, QSElement qSElement, SeparateQSFakeStatusController separateQSFakeStatusController, OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent, OplusQSPrivacyComponent oplusQSPrivacyComponent, OplusQSBottomComponent oplusQSBottomComponent, EditTopComponent editTopComponent, AddTileComponent addTileComponent, QSPanelAnimatorManager qSPanelAnimatorManager, QSSecurity qSSecurity, EditModeSwitchAnimationManager editModeSwitchAnimationManager, CellAreaProvider cellAreaProvider, OplusQSDragComponent oplusQSDragComponent, OplusQSMoreButtonPopupWindowController oplusQSMoreButtonPopupWindowController, RecyclerViewDragHelper recyclerViewDragHelper, LogBuffer logBuffer) {
        super(oplusQSRootView);
        this.qsPersonalComponent = oplusQSPersonalComponent;
        this.privacyView = oplusQSPrivacyContainerView;
        this.qsOtherTilesComponent = oplusQSTilesComponent;
        this.statusBarContainer = viewGroup;
        this.panelContainer = constraintLayout;
        this.stub = iStub;
        this.statusBarHeader = qSElement;
        this.fakeStatusContainerController = separateQSFakeStatusController;
        this.qsQuickEntranceComponent = oplusQSQuickEntranceComponent;
        this.qsPrivacyComponent = oplusQSPrivacyComponent;
        this.qsBottomComponent = oplusQSBottomComponent;
        this.editTopController = editTopComponent;
        this.qsPanelAnimatorManager = qSPanelAnimatorManager;
        this.qsSecurity = qSSecurity;
        this.editModeSwitchAnimationManager = editModeSwitchAnimationManager;
        this.cellAreaProvider = cellAreaProvider;
        this.qsMoreButtonPopupWindowController = oplusQSMoreButtonPopupWindowController;
        this.dragHelper = recyclerViewDragHelper;
        this.logBuffer = logBuffer;
        CopyOnWriteArrayList<IQsComponent> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.nodes = copyOnWriteArrayList;
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.expandedFraction = 1.0f;
        OplusQSRootViewComponent$$ExternalSyntheticLambda0 oplusQSRootViewComponent$$ExternalSyntheticLambda0 = new OplusQSRootViewComponent$$ExternalSyntheticLambda0(0);
        oplusQSRootViewComponent$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.privacyBottomMargin$delegate = LazyKt__LazyJVMKt.lazy(oplusQSRootViewComponent$$ExternalSyntheticLambda0);
        this.panelClosedOnDown = true;
        this.initialHeightOnTouch = this.expandedHeight;
        OplusQSRootViewComponent$$ExternalSyntheticLambda0 oplusQSRootViewComponent$$ExternalSyntheticLambda1 = new OplusQSRootViewComponent$$ExternalSyntheticLambda0(1);
        oplusQSRootViewComponent$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.flingAnimationUtils$delegate = LazyKt__LazyJVMKt.lazy(oplusQSRootViewComponent$$ExternalSyntheticLambda1);
        this.activePointerId = -1;
        ConstraintSet constraintSet = new ConstraintSet();
        this.tmpConstraintSet = constraintSet;
        this.lastPos = new PointF();
        this.touchDownPoint = new PointF(-1.0f, -1.0f);
        final int i = 0;
        this.velocityTracker$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i) {
                    case 0:
                        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
                        return VelocityTracker.obtain(0);
                    default:
                        OplusQSRootViewComponent.Companion companion2 = OplusQSRootViewComponent.Companion;
                        return new TransitionPropertiesCache();
                }
            }
        });
        this.viewpagerRect = new Rect();
        this.personalContainerRect = new Rect();
        this.oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
        this.isLowGaussianLevel = ScrimUtil.isLowGaussianLevel(getContext());
        OplusQSRootViewComponent$qsPanelExpandFractionListener$1 oplusQSRootViewComponent$qsPanelExpandFractionListener$1 = new OplusQSRootViewComponent$qsPanelExpandFractionListener$1();
        oplusQSRootViewComponent$qsPanelExpandFractionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelExpandFractionListener = oplusQSRootViewComponent$qsPanelExpandFractionListener$1;
        OplusQSRootViewComponent$qsScenarioAnimListener$1 oplusQSRootViewComponent$qsScenarioAnimListener$1 = new OplusQSRootViewComponent$qsScenarioAnimListener$1();
        oplusQSRootViewComponent$qsScenarioAnimListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsScenarioAnimListener = oplusQSRootViewComponent$qsScenarioAnimListener$1;
        OplusQSRootViewComponent$qsElementShowingChangeListener$1 oplusQSRootViewComponent$qsElementShowingChangeListener$1 = new OplusQSRootViewComponent$qsElementShowingChangeListener$1();
        oplusQSRootViewComponent$qsElementShowingChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsElementShowingChangeListener = oplusQSRootViewComponent$qsElementShowingChangeListener$1;
        OplusQSRootViewComponent$qsElementEndListener$1 oplusQSRootViewComponent$qsElementEndListener$1 = new OplusQSRootViewComponent$qsElementEndListener$1();
        oplusQSRootViewComponent$qsElementEndListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsElementEndListener = oplusQSRootViewComponent$qsElementEndListener$1;
        this.fractionChangeListeners = new CopyOnWriteArraySet<>();
        RunnableC01961 runnableC01961 = new RunnableC01961(2);
        runnableC01961.this$0 = this;
        VarHandle.storeStoreFence();
        this.longClickRunnable = runnableC01961;
        RunnableC01961 runnableC01962 = new RunnableC01961(1);
        runnableC01962.this$0 = this;
        VarHandle.storeStoreFence();
        this.collapseQSPanelRunnable = runnableC01962;
        OplusQSRootViewComponent$cellCalculatorListener$1 oplusQSRootViewComponent$cellCalculatorListener$1 = new OplusQSRootViewComponent$cellCalculatorListener$1();
        oplusQSRootViewComponent$cellCalculatorListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.cellCalculatorListener = oplusQSRootViewComponent$cellCalculatorListener$1;
        OplusQSRootViewComponent$cellConfigUpdateListener$1 oplusQSRootViewComponent$cellConfigUpdateListener$1 = new OplusQSRootViewComponent$cellConfigUpdateListener$1();
        oplusQSRootViewComponent$cellConfigUpdateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.cellConfigUpdateListener = oplusQSRootViewComponent$cellConfigUpdateListener$1;
        final int i2 = 1;
        this.transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
                        return VelocityTracker.obtain(0);
                    default:
                        OplusQSRootViewComponent.Companion companion2 = OplusQSRootViewComponent.Companion;
                        return new TransitionPropertiesCache();
                }
            }
        });
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "init", null, 8);
        TouchHandler touchHandler = new TouchHandler();
        touchHandler.this$0 = this;
        VarHandle.storeStoreFence();
        oplusQSRootView.setOnTouchListener(touchHandler);
        this.personalContainerView = oplusQSRootView.requireViewById(2131364532);
        this.otherTilesContainerView = oplusQSRootView.requireViewById(2131364435);
        this.otherTilesLandLeftContainerView = oplusQSRootView.requireViewById(2131364436);
        oplusQSRootView.requireViewById(2131364787);
        this.pageIndicatorParent = (ViewGroup) oplusQSRootView.requireViewById(2131364788);
        this.quickEntrance = oplusQSRootView.requireViewById(2131364792);
        this.bottomArea = (ViewGroup) oplusQSRootView.requireViewById(2131364712);
        oplusQSRootView.requireViewById(2131364743);
        final int i3 = 0;
        this.headerView = QSElement.createView$default(qSElement, (SpanSizeInPlugin) null, false, false, 7, (Object) null);
        this.qsStatusBar = oplusQSRootView.requireViewById(2131364804);
        View viewCreateView$default = QSElement.createView$default(qSElement, (SpanSizeInPlugin) null, false, false, 7, (Object) null);
        ViewParent parent = viewCreateView$default.getParent();
        ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup2 != null) {
            viewGroup2.removeAllViews();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        viewCreateView$default.setLayoutParams(layoutParams);
        viewGroup.addView(viewCreateView$default);
        int paddingRight = viewGroup.getPaddingRight();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(oplusQSPrivacyContainerView.getId(), 7, 0, 7, paddingRight);
        constraintSet.connect(oplusQSPrivacyContainerView.getId(), 4, viewGroup.getId(), 3, getPrivacyBottomMargin());
        constraintSet.applyTo(constraintLayout);
        separateQSFakeStatusController.init();
        copyOnWriteArrayList.add(oplusQSPrivacyComponent);
        copyOnWriteArrayList.add(oplusQSQuickEntranceComponent);
        copyOnWriteArrayList.add(oplusQSPersonalComponent);
        copyOnWriteArrayList.add(oplusQSLeftBTilesComponent);
        copyOnWriteArrayList.add(oplusQSTilesComponent);
        copyOnWriteArrayList.add(qSEmptyListTipComponent);
        copyOnWriteArrayList.add(oplusQSBottomComponent);
        copyOnWriteArrayList.add(editTopComponent);
        copyOnWriteArrayList.add(addTileComponent);
        copyOnWriteArrayList.add(oplusQSDragComponent);
        for (IQsComponent iQsComponent : copyOnWriteArrayList) {
            iQsComponent.injectHostAbility(this);
            iQsComponent.startComponent();
        }
        this.qsPanelAnimatorManager.initNodes(this.nodes);
        OplusQSRootViewComponent$$ExternalSyntheticLambda8 oplusQSRootViewComponent$$ExternalSyntheticLambda8 = new OplusQSRootViewComponent$$ExternalSyntheticLambda8();
        oplusQSRootViewComponent$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).setEmptyTouchListener(oplusQSRootViewComponent$$ExternalSyntheticLambda8);
        }
        TemporarilyInfoManager companion = TemporarilyInfoManager.Companion.getInstance();
        companion.setQsRootView(this.panelContainer);
        companion.setStatusBarContainerView(this.statusBarContainer);
        companion.setQsQuickEntranceContainerView(this.qsQuickEntranceComponent.getView());
        companion.setPrivacyContainerView(this.privacyView);
        OplusQSRootViewComponent$$ExternalSyntheticLambda0 oplusQSRootViewComponent$$ExternalSyntheticLambda2 = new OplusQSRootViewComponent$$ExternalSyntheticLambda0(3);
        oplusQSRootViewComponent$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        companion.setQuickEntranceTranslationYReset(oplusQSRootViewComponent$$ExternalSyntheticLambda2);
        companion.setStarted(false);
        companion.setStarted(1.0f == this.expandedFraction);
        OplusQSRootViewComponent$initTemporarilyInfoManager$1$2 oplusQSRootViewComponent$initTemporarilyInfoManager$1$2 = new OplusQSRootViewComponent$initTemporarilyInfoManager$1$2();
        oplusQSRootViewComponent$initTemporarilyInfoManager$1$2.$this_apply = companion;
        oplusQSRootViewComponent$initTemporarilyInfoManager$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        addPanelFractionChangeListener(oplusQSRootViewComponent$initTemporarilyInfoManager$1$2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initCellCalculatorManager", null, 8);
        this.cellAreaProvider.addConfigUpdateListener(this.cellConfigUpdateListener);
        this.cellAreaProvider.updateCellAreaConfig();
        CellCalculatorManager companion2 = CellCalculatorManager.Companion.getInstance(getContext());
        companion2.setCellAreaProvider(this.cellAreaProvider);
        companion2.addCellCalculatorListener(this.cellCalculatorListener);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initTileColorfulConfig", null, 8);
        OplusQSSpecialModeProvider.Companion.getInstance().updateColorfulConfigEnable();
        this.editModeSwitchAnimationManager.init(CollectionsKt__CollectionsKt.listOf(new IEditModeSwitcher[]{this.qsPrivacyComponent, this.qsQuickEntranceComponent, qSEmptyListTipComponent, this.qsPersonalComponent, this.qsOtherTilesComponent, this.qsBottomComponent, this.editTopController, addTileComponent, this, oplusQSDragComponent}));
        this.qsQuickEntranceComponent.getQsMoreButtonPopupWindowController().setHostAbility(this);
        this.qsPanelAnimatorManager.setSpringEndAction(new AnonymousClass2(0, this, OplusQSRootViewComponent.class, "tryStopGfx", "tryStopGfx()V", 0));
        this.qsPanelAnimatorManager.setNumChangeCancelAction(new AnonymousClass3(0, this, OplusQSRootViewComponent.class, "cancelAnimationOnNumChange", "cancelAnimationOnNumChange()V", 0));
        OplusQSBottomComponent oplusQSBottomComponent2 = this.qsBottomComponent;
        OplusQSRootViewComponent$$ExternalSyntheticLambda0 oplusQSRootViewComponent$$ExternalSyntheticLambda3 = new OplusQSRootViewComponent$$ExternalSyntheticLambda0(2);
        oplusQSRootViewComponent$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQSBottomComponent2.setPanelNegYGetter(oplusQSRootViewComponent$$ExternalSyntheticLambda3);
        this.qsPanelAnimatorManager.addCallback(this.qsScenarioAnimListener);
        ViewGroup viewGroup3 = this.personalContainerView;
        (viewGroup3 == null ? null : viewGroup3).setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent.5
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                switch (i3) {
                    case 0:
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, 1, false));
                        break;
                    default:
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, 1, false));
                        break;
                }
            }
        });
        ViewGroup viewGroup4 = this.otherTilesContainerView;
        final int i4 = 1;
        (viewGroup4 == null ? null : viewGroup4).setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.oplus.systemui.plugins.qs.OplusQSRootViewComponent.5
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                switch (i4) {
                    case 0:
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, 1, false));
                        break;
                    default:
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, 1, false));
                        break;
                }
            }
        });
    }

    public static final void access$addMovement(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX(i) - motionEvent.getX();
        float rawY = motionEvent.getRawY(i) - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        oplusQSRootViewComponent.getVelocityTracker().addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    public static final boolean access$canScrollX(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent) {
        return !oplusQSRootViewComponent.isClosing || Math.abs(oplusQSRootViewComponent.touchDownPoint.x - motionEvent.getRawX()) <= Math.abs(oplusQSRootViewComponent.touchDownPoint.y - motionEvent.getRawY());
    }

    public static final void access$endMotionEvent(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent, float f, float f2) {
        boolean z;
        boolean zShouldExpand;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(motionEvent.getActionMasked(), "endMotionEvent actionMasked:"), null, 8);
        oplusQSRootViewComponent.activePointerId = -1;
        float fAbs = Math.abs(f - oplusQSRootViewComponent.touchDownPoint.x);
        float f3 = oplusQSRootViewComponent.touchSlop;
        boolean z2 = fAbs > f3 || Math.abs(f2 - oplusQSRootViewComponent.touchDownPoint.y) > f3;
        boolean z3 = (oplusQSRootViewComponent.isFullyCollapsed() || oplusQSRootViewComponent.isFullyExpanded()) ? false : true;
        boolean z4 = oplusQSRootViewComponent.isTracking && oplusQSRootViewComponent.isYTouchStart;
        int actionMasked = motionEvent.getActionMasked();
        boolean z5 = oplusQSRootViewComponent.canSlideHToQSOnStatusBar;
        StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(f, actionMasked, "endMotionEvent, event:", ", x:", ", y:");
        sbM.append(f2);
        sbM.append(", isValidTouch:");
        sbM.append(z2);
        sbM.append(", canTracking:");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isTrackingYTouch:", ", canSlideHToQSOnStatusBar:", sbM, z3, z4);
        sbM.append(z5);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (z4 || z2 || z3 || motionEvent.getActionMasked() == 3) {
            oplusQSRootViewComponent.getVelocityTracker().computeCurrentVelocity(1000);
            float yVelocity = oplusQSRootViewComponent.getVelocityTracker().getYVelocity();
            float fHypot = (float) Math.hypot(oplusQSRootViewComponent.getVelocityTracker().getXVelocity(), oplusQSRootViewComponent.getVelocityTracker().getYVelocity());
            boolean zShouldExpand2 = oplusQSRootViewComponent.shouldExpand(yVelocity, fHypot);
            if (oplusQSRootViewComponent.canSlideHToQSOnStatusBar || oplusQSRootViewComponent.stub.isInXOverscroll() || !oplusQSRootViewComponent.isClosing) {
                z = true;
                zShouldExpand = zShouldExpand2;
            } else {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("endMotionEvent, first call shouldExpand:", zShouldExpand2), null, 8);
                z = true;
                zShouldExpand = oplusQSRootViewComponent.shouldExpand(yVelocity, f, f2, true);
            }
            if (motionEvent.getActionMasked() == 3 && oplusQSRootViewComponent.stub.isDraggingX()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "closing is intercept by DraggingX, cancel expand", null, 8);
                zShouldExpand = z;
            }
            boolean z6 = oplusQSRootViewComponent.isClosing;
            boolean z7 = oplusQSRootViewComponent.panelClosedOnDown;
            boolean z8 = oplusQSRootViewComponent.canSlideHToQSOnStatusBar;
            float f4 = oplusQSRootViewComponent.expandedFraction;
            boolean z9 = oplusQSRootViewComponent.launcherIntentExpand;
            StringBuilder sbM2 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("endMotionEvent, expand: ", zShouldExpand, ", vel: ", yVelocity, ", vectorVel: ");
            sbM2.append(fHypot);
            sbM2.append("  isClosing=");
            sbM2.append(z6);
            sbM2.append(" panelClosedOnDown=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(" canSlideHToQSOnStatusBar=", " expandedFraction=", sbM2, z7, z8);
            sbM2.append(f4);
            sbM2.append(" launcherIntentExpand=");
            sbM2.append(z9);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM2.toString(), null, 8);
            if (oplusQSRootViewComponent.isTracking && zShouldExpand) {
                oplusQSRootViewComponent.stub.gpuBoomForTouchEvent(motionEvent);
            }
            oplusQSRootViewComponent.onTrackingStopped$1(false);
            boolean z10 = oplusQSRootViewComponent.launcherIntentExpand ? true : zShouldExpand;
            if (oplusQSRootViewComponent.isScenarioTouch && !z10) {
                oplusQSRootViewComponent.qsPanelAnimatorManager.flingToScenario(Boolean.FALSE);
            }
            oplusQSRootViewComponent.flingQS(fHypot, z10);
            oplusQSRootViewComponent.resetExtraFakeTranslationY(z10);
            oplusQSRootViewComponent.qsPanelAnimatorManager.startRebound();
            oplusQSRootViewComponent.stub.notifySeparateQSEndMotionEvent(oplusQSRootViewComponent.expandedFraction, z10);
        } else {
            oplusQSRootViewComponent.onTrackingStopped$1(false);
            if (oplusQSRootViewComponent.isAddingMode) {
                oplusQSRootViewComponent.exitAddingMode(true);
            } else if (!oplusQSRootViewComponent.isEditMode) {
                oplusQSRootViewComponent.collapseQSPanel(false, "onEmptySpaceClick");
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "endMotionEvent, onEmptySpaceClick", null, 8);
        }
        oplusQSRootViewComponent.resetTouch();
        oplusQSRootViewComponent.getVelocityTracker().clear();
    }

    public static final void access$handleLargeScreenRotation(OplusQSRootViewComponent oplusQSRootViewComponent) {
        oplusQSRootViewComponent.getClass();
        if (((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isLargeScreen().getValue()).booleanValue()) {
            oplusQSRootViewComponent.exitEditMode(false);
            oplusQSRootViewComponent.cellAreaProvider.updateCellAreaConfig();
            oplusQSRootViewComponent.updateResource("rotationChanged", false);
            oplusQSRootViewComponent.qsOtherTilesComponent.enableRecyclerViewHeightAnimation();
            oplusQSRootViewComponent.hideCouiPopup();
            oplusQSRootViewComponent.reset("rotationChanged");
        }
    }

    public static final void access$initDownEvent(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getActionMasked() == 0) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("initDownEvent expectingSynthesizedDown=", oplusQSRootViewComponent.expectingSynthesizedDown), null, 8);
            z = oplusQSRootViewComponent.expectingSynthesizedDown;
        } else {
            z = false;
        }
        oplusQSRootViewComponent.mLastEventSynthesizedDown = z;
    }

    public static final boolean access$isNtfVisibleOnUnlock(OplusQSRootViewComponent oplusQSRootViewComponent) {
        if (oplusQSRootViewComponent.stub.getNtIsFullCollapsed()) {
            return false;
        }
        return !oplusQSRootViewComponent.stub.isKeyguardShowing() || oplusQSRootViewComponent.stub.isKeyguardOccluded();
    }

    public static final void access$onSecondaryPointerUp(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == oplusQSRootViewComponent.activePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            oplusQSRootViewComponent.activePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = oplusQSRootViewComponent.getVelocityTracker();
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
            oplusQSRootViewComponent.touchDownPoint.set(motionEvent.getRawX(i) - (oplusQSRootViewComponent.lastPos.x - oplusQSRootViewComponent.touchDownPoint.x), motionEvent.getRawY(i) - (oplusQSRootViewComponent.lastPos.y - oplusQSRootViewComponent.touchDownPoint.y));
            PointF pointF = oplusQSRootViewComponent.lastPos;
            PointF pointF2 = oplusQSRootViewComponent.touchDownPoint;
            pointF.set(pointF2.x, pointF2.y);
            oplusQSRootViewComponent.initialHeightOnTouch = oplusQSRootViewComponent.expandedHeight;
        }
    }

    public static final void access$resetScrollIfNoDownEvent(OplusQSRootViewComponent oplusQSRootViewComponent, MotionEvent motionEvent, int i) {
        PointF pointF = oplusQSRootViewComponent.touchDownPoint;
        if (pointF.x == -1.0f && pointF.y == -1.0f) {
            oplusQSRootViewComponent.startExpandMotion(motionEvent, i, false);
            if (oplusQSRootViewComponent.isFullyCollapsed()) {
                oplusQSRootViewComponent.reset("resetScrollIfNoDownEvent");
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "resetScrollIfNoDownEvent no down event, call reset!", null, 8);
            }
        }
    }

    public static final void access$updateColor(OplusQSRootViewComponent oplusQSRootViewComponent, QSSpecialModeUpdateMonitor.ColorType colorType) {
        oplusQSRootViewComponent.statusBarHeader.updateColor(colorType);
        oplusQSRootViewComponent.qsPrivacyComponent.updateColor(colorType);
        oplusQSRootViewComponent.qsBottomComponent.updateColor(colorType);
        Iterator<T> it = oplusQSRootViewComponent.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).updateColor(colorType);
        }
    }

    public static final void access$updateTextSize(OplusQSRootViewComponent oplusQSRootViewComponent, boolean z) {
        TextSizeConfig.Companion companion = TextSizeConfig.Companion;
        SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = companion.getInstance(oplusQSRootViewComponent.getContext()).getSpecialLanguageTextScaleHelper();
        if (specialLanguageTextScaleHelper.isNeedUpdateScale(oplusQSRootViewComponent.getContext(), z)) {
            OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
            int initialDisplayDensity = proxy != null ? proxy.getInitialDisplayDensity() : oplusQSRootViewComponent.getContext().getResources().getDisplayMetrics().densityDpi;
            float f = initialDisplayDensity / 160.0f;
            float fCalculatorSystemScale = specialLanguageTextScaleHelper.calculatorSystemScale(f, initialDisplayDensity);
            companion.getInstance(oplusQSRootViewComponent.getContext()).updateScale(fCalculatorSystemScale);
            TemporarilyInfoManager.Companion.getInstance().updateTemporarilyViewTextSize(fCalculatorSystemScale, specialLanguageTextScaleHelper, f);
        }
    }

    private final FlingAnimationUtils getFlingAnimationUtils() {
        return (FlingAnimationUtils) this.flingAnimationUtils$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMaxPanelHeight() {
        return ((OplusQSRootView) this.mView).getMeasuredHeight();
    }

    private final int getPrivacyBottomMargin() {
        return ((Number) this.privacyBottomMargin$delegate.getValue()).intValue();
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VelocityTracker getVelocityTracker() {
        return (VelocityTracker) this.velocityTracker$delegate.getValue();
    }

    private final void setClosing(boolean z) {
        if (this.isClosing != z) {
            this.isClosing = z;
            this.qsOtherTilesComponent.setShouldInterceptScroll(z);
            NotificationShadeRegionHelper.setTouchExclusionRegion(this.isClosing && !this.stub.getUseMotionBlur() && (!this.stub.isKeyguardShowing() || this.stub.isKeyguardOccluded()), ((OplusQSRootView) this.mView).getRootView(), "isClosing");
        }
    }

    private final void setExpandedFraction(float f) {
        if (this.expandedFraction == f) {
            return;
        }
        this.expandedFraction = f;
        Iterator it = CollectionsKt___CollectionsKt.toList(this.fractionChangeListeners).iterator();
        while (it.hasNext()) {
            OplusQSRootViewComponent$initTemporarilyInfoManager$1$2 oplusQSRootViewComponent$initTemporarilyInfoManager$1$2 = (OplusQSRootViewComponent$initTemporarilyInfoManager$1$2) ((PanelFractionChangeListener) it.next());
            TemporarilyInfoManager temporarilyInfoManager = oplusQSRootViewComponent$initTemporarilyInfoManager$1$2.$this_apply;
            if (Intrinsics.areEqual(temporarilyInfoManager.getQsRootView(), oplusQSRootViewComponent$initTemporarilyInfoManager$1$2.this$0.panelContainer)) {
                temporarilyInfoManager.setStarted(1.0f == f);
            }
        }
    }

    private final void setLayerType(boolean z) {
        if (this.hasSetLayerType != z) {
            this.hasSetLayerType = z;
            Iterator<IQsComponent> it = this.nodes.iterator();
            while (it.hasNext()) {
                it.next().setLayerType(z);
            }
        }
    }

    private final void setListening(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setListening: ", z), null, 8);
        OplusSeparateClickTileManager.Companion.getInstance().setListening(z);
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).setListening(z);
        }
        this.statusBarHeader.setListening(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPanelClosedOnDown(boolean z) {
        GlobalUtils.panelClosedOnDown = z;
        if (this.panelClosedOnDown != z) {
            this.panelClosedOnDown = z;
        }
    }

    private final void setPanelNegY(float f) {
        if (this.panelNegY == f) {
            return;
        }
        this.panelNegY = f;
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).updateNodeTranslationY();
        }
    }

    private final void setQsVisible(boolean z) {
        CopyOnWriteArrayList<IQsComponent> copyOnWriteArrayList;
        if (z && ((OplusQSRootView) this.mView).getVisibility() != 0) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(this.qsPanelAnimatorManager.getRawFraction(), "setQsVisible: true rawFraction:"), null, 8);
            this.fakeStatusContainerController.updateElementLoc$1();
            if (!this.qsPanelAnimatorManager.isSecondElementVisible()) {
                doTileZoneScale(false, false);
            }
            this.qsPanelAnimatorManager.initStatusWhenExpandStarted();
            ((OplusQSRootView) this.mView).setVisibility(0);
            setLayerType(true);
            ((OplusQSRootView) this.mView).setTransitionAlpha(1.0f);
            ((OplusQSRootView) this.mView).setAlpha(1.0f);
            ((OplusQSRootView) this.mView).setScaleX(1.0f);
            ((OplusQSRootView) this.mView).setScaleY(1.0f);
            setListening(true);
            this.isGfxStart = true;
            LogUtil.setLogBufferDisabled(true);
            this.isQsPullTraceStart = true;
            Trace.asyncTraceBegin(8L, "QS PULL", 7);
            PerformanceUtils.notifySFAnimating(400);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "PerformanceUtils-->QSPanelAnimator QS Pull notifySFAnimating done");
            }
            this.stub.restrainNavigationBarInvertColor(true);
            this.stub.startWaitAction();
            this.oplusGcSupressionExt.callGcSupression(3, 3000);
            OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.CONTROL_PANEL_SWITCH);
            this.stub.updateSeparateQSExpansion(this.expandedFraction, true, this.isTracking, this.expandedHeight);
            this.stub.updateSeparateQSExpandState(true);
            return;
        }
        if (z || ((OplusQSRootView) this.mView).getVisibility() != 0 || this.isTracking) {
            return;
        }
        setClosing(false);
        this.isIntentCloseQs = false;
        ((OplusQSRootView) this.mView).setVisibility(4);
        ((OplusQSRootView) this.mView).setTranslationX(0.0f);
        if (!this.isEditMode && ((copyOnWriteArrayList = this.nodes) == null || !copyOnWriteArrayList.isEmpty())) {
            Iterator<T> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (((IQsComponent) it.next()).checkEditModeAbnormal()) {
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, "checkEditModeAbnormal: ", null, 8);
                    enterEditMode(false);
                    break;
                }
            }
        }
        exitEditMode(false);
        setExpandedFraction(0.0f);
        setListening(false);
        this.qsPanelAnimatorManager.getQsScenarioPullAnimator().resetScenarioAnimator();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(this.qsPanelAnimatorManager.getRawFraction(), "setQsVisible: false rawFraction:"), null, 8);
        this.stub.updateSeparateQSExpansion(this.qsPanelAnimatorManager.getRawFraction(), false, this.isTracking, this.expandedHeight);
        this.stub.updateSeparateQSExpandState(false);
        setLayerType(false);
        tryStopGfx();
        this.stub.onQsClosingEnd();
    }

    public final void addPanelFractionChangeListener(PanelFractionChangeListener panelFractionChangeListener) {
        this.fractionChangeListeners.add(panelFractionChangeListener);
    }

    public final void addScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        this.qsPanelAnimatorManager.addCallback(qSScenarioAnimListener);
    }

    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        Object obj;
        Object next;
        Object next2;
        Set<? extends SpringTransition> set2 = set;
        Iterator<T> it = set2.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next).getTransitionName(), "status_bar_alpha_transition"));
        SpringTransition springTransition = (SpringTransition) next;
        if (springTransition != null) {
            springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.statusBarContainer, FadeTransition.class));
        }
        Iterator<T> it2 = set2.iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!Intrinsics.areEqual(((SpringTransition) next2).getTransitionName(), "parent_translation_transition"));
        SpringTransition springTransition2 = (SpringTransition) next2;
        if (springTransition2 != null) {
            springTransition2.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.statusBarContainer, BoundsTransition.class));
        }
        if (OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            for (Object obj2 : set2) {
                if (Intrinsics.areEqual(((SpringTransition) obj2).getTransitionName(), "adding_transition_position")) {
                    obj = obj2;
                    break;
                }
            }
            SpringTransition springTransition3 = (SpringTransition) obj;
            if (springTransition3 != null) {
                springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.panelContainer, TranslationXTransition.class));
                springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.panelContainer, ScaleXTransition.class));
                springTransition3.addTargetProperty(getTransitionProperties().fetchTransitionProperty(this.panelContainer, ScaleYTransition.class));
            }
        }
    }

    public final void bringToFront$1() {
        ViewParent parent = ((OplusQSRootView) this.mView).getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null || viewGroup.indexOfChild(this.mView) == viewGroup.getChildCount() - 1) {
            return;
        }
        viewGroup.bringChildToFront(this.mView);
    }

    public final void cancelAnimator() {
        ValueAnimator valueAnimator = this.expandAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ((OplusQSRootView) this.mView).removeCallbacks(this.collapseQSPanelRunnable);
    }

    public final void clickTile(ComponentName componentName) {
        this.qsOtherTilesComponent.clickTile(componentName);
    }

    public final void collapseQSPanel(boolean z, String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("collapseQSPanel call by callFun=", str), null, 8);
        }
        if (Intrinsics.areEqual(str, "onEmptySpaceClick")) {
            this.stub.stopHorizontalSlideSwitchOnCollapse();
        }
        if ((!isFullyCollapsed() || this.qsPanelAnimatorManager.isFractionAnimationRunning()) && !this.isClosing && !this.isTracking) {
            if (!z) {
                collapseQSPanelInner();
                return;
            } else {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "collapseQSPanel delayed", null, 8);
                ((OplusQSRootView) this.mView).postDelayed(this.collapseQSPanelRunnable, 120L);
                return;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z2 = this.isTracking;
            float f = this.expandedFraction;
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(",isFractionAnimationRunning:", "OplusQSRootViewComponent-->", NotificationPanelViewController$.ExternalSyntheticOutline0.m("collapseQSPanel fail!isTracking=", z2, ",expandedFraction=", f, " isClosing="), this.isClosing, this.qsPanelAnimatorManager.isFractionAnimationRunning()));
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x004b  */
    public final void collapseQSPanelInner() {
        boolean z;
        if (isFullyCollapsed()) {
            z = false;
        } else {
            PointF pointF = this.touchDownPoint;
            Rect rect = this.personalContainerRect;
            ViewGroup viewGroup = this.personalContainerView;
            int left = (viewGroup == null ? null : viewGroup).getLeft();
            int top = (viewGroup == null ? null : viewGroup).getTop();
            int right = (viewGroup == null ? null : viewGroup).getRight();
            if (viewGroup == null) {
                viewGroup = null;
            }
            rect.set(left, top, right, viewGroup.getBottom());
            if (this.personalContainerRect.contains((int) pointF.x, (int) pointF.y) || isInViewpagerArea(this.touchDownPoint)) {
                z = true;
            } else {
                z = false;
            }
        }
        this.isIntentCloseQs = z;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[resetCurrentPage]", null, 8);
        this.qsOtherTilesComponent.resetCurrentPage();
        hideCouiPopup();
        flingQS(0.0f, false);
    }

    public final void collapseQSPanelWithoutAnimate(boolean z, Function0 function0) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "collapseQSPanelWithoutAnimate", null, 8);
        if (function0 != null) {
            this.collapsedEndBlock = function0;
            if (this.qsPanelAnimatorManager.getRawFraction() == 0.0f && !this.qsPanelAnimatorManager.isFractionAnimationRunning()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "collapseQSPanelWithoutAnimate block invoke", null, 8);
                Function0 function1 = this.collapsedEndBlock;
                if (function1 != null) {
                    function1.invoke();
                }
                this.collapsedEndBlock = null;
            }
        }
        if (this.isTracking) {
            onTrackingStopped$1(false);
        }
        if (!((isFullyCollapsed() && !this.qsPanelAnimatorManager.isFractionAnimationRunning()) || this.isClosing || this.isTracking) || z) {
            hideCouiPopup();
            onCollapseQSPanelStart$1(false);
            cancelAnimator();
            updateMoveExpandHeight(0.0f, 0.0f);
            this.qsPanelAnimatorManager.skipToEnd(false);
            if (z) {
                onTrackingStopped$1(false);
            }
            this.stub.onQsClosingBegin();
            return;
        }
        Function0 function2 = this.collapsedEndBlock;
        if (function2 != null) {
            function2.invoke();
        }
        this.collapsedEndBlock = null;
        boolean z2 = this.isTracking;
        float f = this.expandedFraction;
        boolean z3 = this.isClosing;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("collapseQSPanelWithoutAnimate fail!isTracking=", z2, " expandedFraction=", f, " isClosing=");
        sbM.append(z3);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
    }

    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        int iHashCode = str.hashCode();
        if (iHashCode == -1895625004 ? !str.equals(EditModeSwitchAnimationManager.SCENE_ADDING) : !(iHashCode == -1700191961 ? str.equals(EditModeSwitchAnimationManager.SCENE_DRAG) : iHashCode == -1700175363 && str.equals(EditModeSwitchAnimationManager.SCENE_EDIT))) {
            return new SpringTransition[0];
        }
        IEditModeSwitcher.Companion companion = IEditModeSwitcher.Companion;
        return (SpringTransition[]) CollectionsKt__CollectionsKt.listOfNotNull(new SpringTransition[]{companion.createTransition("remove_icon_scale_transition"), companion.createTransition("remove_icon_alpha_transition")}).toArray(new SpringTransition[0]);
    }

    public final void disable(int i, int i2, int i3, boolean z) {
        if (i != getContext().getDisplayId()) {
            return;
        }
        boolean z2 = (i3 & 1) != 0;
        if (z2 != this.qsDisabled) {
            this.qsDisabled = z2;
            this.panelContainer.setVisibility(z2 ? 4 : 0);
            ViewGroup viewGroup = this.bottomArea;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.setVisibility(this.qsDisabled ? 4 : 0);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "disable qsDisabled=" + z2, null, 8);
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
    public final void doTileZoneScale(boolean z, boolean z2) {
        ViewGroup viewGroup;
        float width;
        float width2;
        float width3;
        float width4;
        float width5;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("doTileZoneScale toShow: ", ", withAnimation: ", " isLowGaussianLevel=", z, z2);
        boolean z3 = this.isLowGaussianLevel;
        sbM.append(z3);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        boolean zIsLayoutRtl = ((OplusQSRootView) this.mView).isLayoutRtl();
        ViewGroup viewGroup2 = this.quickEntrance;
        ViewGroup viewGroup3 = this.otherTilesContainerView;
        ViewGroup viewGroup4 = this.personalContainerView;
        if (zBooleanValue) {
            ViewGroup viewGroup5 = viewGroup4 == null ? null : viewGroup4;
            if (zIsLayoutRtl) {
                width = 0.0f;
            } else {
                width = (viewGroup4 == null ? null : viewGroup4).getWidth();
            }
            viewGroup5.setPivotX(width);
            (viewGroup4 == null ? null : viewGroup4).setPivotY(0.0f);
            ViewGroup viewGroup6 = viewGroup3 == null ? null : viewGroup3;
            if (zIsLayoutRtl) {
                width2 = (viewGroup3 == null ? null : viewGroup3).getWidth();
            } else {
                width2 = 0.0f;
            }
            viewGroup6.setPivotX(width2);
            (viewGroup3 == null ? null : viewGroup3).setPivotY(0.0f);
            ViewGroup viewGroup7 = this.otherTilesLandLeftContainerView;
            ViewGroup viewGroup8 = viewGroup7 == null ? null : viewGroup7;
            if (zIsLayoutRtl) {
                width3 = 0.0f;
            } else {
                width3 = (viewGroup7 == null ? null : viewGroup7).getWidth();
            }
            viewGroup8.setPivotX(width3);
            if (viewGroup7 == null) {
                viewGroup7 = null;
            }
            viewGroup7.setPivotY(-(viewGroup4 == null ? null : viewGroup4).getHeight());
            ViewGroup viewGroup9 = this.bottomArea;
            ViewGroup viewGroup10 = viewGroup9 == null ? null : viewGroup9;
            if (zIsLayoutRtl) {
                if (viewGroup9 == null) {
                    viewGroup9 = null;
                }
                width4 = viewGroup9.getWidth();
            } else {
                width4 = 0.0f;
            }
            viewGroup10.setPivotX(width4);
            ViewGroup viewGroup11 = this.bottomArea;
            if (viewGroup11 == null) {
                viewGroup11 = null;
            }
            viewGroup11.setPivotY(-(viewGroup3 == null ? null : viewGroup3).getHeight());
            ViewGroup viewGroup12 = viewGroup2 == null ? null : viewGroup2;
            if (zIsLayoutRtl) {
                if (viewGroup4 == null) {
                    viewGroup4 = null;
                }
                width5 = -viewGroup4.getWidth();
            } else {
                float width6 = (viewGroup2 == null ? null : viewGroup2).getWidth();
                if (viewGroup4 == null) {
                    viewGroup4 = null;
                }
                width5 = width6 + viewGroup4.getWidth();
            }
            viewGroup12.setPivotX(width5);
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setPivotY(0.0f);
        } else {
            (viewGroup4 == null ? null : viewGroup4).setPivotX((viewGroup4 == null ? null : viewGroup4).getWidth() / 2);
            (viewGroup4 == null ? null : viewGroup4).setPivotY(0.0f);
            (viewGroup3 == null ? null : viewGroup3).setPivotX((viewGroup3 == null ? null : viewGroup3).getWidth() / 2);
            (viewGroup3 == null ? null : viewGroup3).setPivotY((-this.cellAreaProvider.getCellAreaConfig().getC4Height()) - (viewGroup4 == null ? null : viewGroup4).getHeight());
            ViewGroup viewGroup13 = this.bottomArea;
            ViewGroup viewGroup14 = viewGroup13 == null ? null : viewGroup13;
            if (viewGroup13 == null) {
                viewGroup13 = null;
            }
            viewGroup14.setPivotX(viewGroup13.getWidth() / 2);
            ViewGroup viewGroup15 = this.bottomArea;
            if (viewGroup15 == null) {
                viewGroup15 = null;
            }
            int i = -this.cellAreaProvider.getCellAreaConfig().getC4Height();
            if (viewGroup4 == null) {
                viewGroup4 = null;
            }
            viewGroup15.setPivotY((i - viewGroup4.getHeight()) - (viewGroup3 == null ? null : viewGroup3).getHeight());
            (viewGroup2 == null ? null : viewGroup2).setPivotX((viewGroup2 == null ? null : viewGroup2).getWidth() / 2);
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setPivotY(0.0f);
        }
        QSPanelAnimatorManager qSPanelAnimatorManager = this.qsPanelAnimatorManager;
        if (z3) {
            viewGroup = viewGroup3 != null ? viewGroup3 : null;
            OplusQSRootViewComponent$$ExternalSyntheticLambda5 oplusQSRootViewComponent$$ExternalSyntheticLambda5 = new OplusQSRootViewComponent$$ExternalSyntheticLambda5();
            oplusQSRootViewComponent$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            qSPanelAnimatorManager.animateToWithProxyLogicForLow(viewGroup, z, z2, oplusQSRootViewComponent$$ExternalSyntheticLambda5);
            return;
        }
        viewGroup = viewGroup3 != null ? viewGroup3 : null;
        OplusQSRootViewComponent$$ExternalSyntheticLambda6 oplusQSRootViewComponent$$ExternalSyntheticLambda6 = new OplusQSRootViewComponent$$ExternalSyntheticLambda6();
        oplusQSRootViewComponent$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        OplusQSRootViewComponent$$ExternalSyntheticLambda7 oplusQSRootViewComponent$$ExternalSyntheticLambda7 = new OplusQSRootViewComponent$$ExternalSyntheticLambda7();
        oplusQSRootViewComponent$$ExternalSyntheticLambda7.f$0 = z;
        oplusQSRootViewComponent$$ExternalSyntheticLambda7.f$1 = 0.9f;
        VarHandle.storeStoreFence();
        QSPanelAnimatorManager.animateToWithProxyLogic$default(qSPanelAnimatorManager, viewGroup, z, z2, oplusQSRootViewComponent$$ExternalSyntheticLambda6, oplusQSRootViewComponent$$ExternalSyntheticLambda7, 0.9f, 0.3f, null, 256);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        float alpha = ((OplusQSRootView) this.mView).getAlpha();
        float transitionAlpha = ((OplusQSRootView) this.mView).getTransitionAlpha();
        float scaleX = ((OplusQSRootView) this.mView).getScaleX();
        float translationX = ((OplusQSRootView) this.mView).getTranslationX();
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("OplusQSRootViewComponent panelView alpha: ", alpha, ", transitionAlpha: ", transitionAlpha, " scale: ");
        sbM.append(scaleX);
        sbM.append(" translationX: ");
        sbM.append(translationX);
        printWriter.println(sbM.toString());
        Object tag = ((OplusQSRootView) this.mView).getRootView().getTag(((OplusQSRootView) this.mView).getRootView().getId());
        Region region = tag instanceof Region ? (Region) tag : null;
        printWriter.println("OplusQSRootViewComponent panelView exclusionRegion: " + (region != null ? region.getBounds() : null));
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "OplusQSRootViewComponent panelView isGfxStart: ", this.isGfxStart);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "OplusQSRootViewComponent hasExclusionDone: ", this.qsPanelExpandFractionListener.getHasExclusionDone());
        ViewGroup viewGroup = this.bottomArea;
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("OplusQSRootViewComponent bottomArea translationX: ", (viewGroup != null ? viewGroup : null).getTranslationX(), printWriter);
        this.cellAreaProvider.dump(printWriter, strArr);
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).dump(printWriter, strArr);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IComponentHostAbility
    public boolean enterAddingMode(boolean z, boolean z2, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z3 = this.isEditMode;
            boolean z4 = this.isAddingMode;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("[enterAddingMode](", ", ", ", ", z, z2);
            sbM.append(bundle);
            sbM.append("), isEditMode=");
            sbM.append(z3);
            sbM.append(", isAddingMode=");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusQSRootViewComponent-->", "SystemUi--QuickSettings", sbM, z4);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "exitDropMode", null, 8);
        this.qsPersonalComponent.stopDrag();
        this.qsOtherTilesComponent.stopDrag();
        if (!this.isEditMode || this.isAddingMode) {
            return false;
        }
        this.isAddingMode = true;
        EditModeSwitchAnimationManager editModeSwitchAnimationManager = this.editModeSwitchAnimationManager;
        if (z2) {
            editModeSwitchAnimationManager.enterAddingMode();
        } else {
            editModeSwitchAnimationManager.skipToEnd();
        }
        updatePanelWhenAddingModeChange();
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onEnterAddingMode(z, z2, bundle);
        }
        return true;
    }

    public final void enterDragDisableMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->".concat("[enterDragDisableMode] true"));
        }
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onEnterDragDisableMode();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IComponentHostAbility
    public boolean enterDragMode(boolean z, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->".concat("[enterDragMode] " + z + ", " + bundle));
        }
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onEnterDragMode(z, bundle);
        }
        return true;
    }

    public final void enterEditMode(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("enterEditMode withAnimation=", ", oldEditMode=", z, this.isEditMode), null, 8);
        if (this.isEditMode) {
            return;
        }
        this.isEditMode = true;
        if (this.exitEditRunnableAfterDragAnim != null) {
            this.exitEditRunnableAfterDragAnim = null;
        }
        EditModeSwitchAnimationManager editModeSwitchAnimationManager = this.editModeSwitchAnimationManager;
        if (z) {
            editModeSwitchAnimationManager.enterEditMode();
        } else {
            editModeSwitchAnimationManager.skipToEnd();
        }
        this.cellAreaProvider.setEditMode(this.isEditMode);
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onEnterEditMode(z);
        }
        this.statusBarContainer.setLayerType(2, null);
        this.statusBarContainer.setVisibility(4);
        this.privacyView.setVisibility(4);
        updateResource("enterEditMode", false);
        updateStatusBarTopMargin();
        TemporarilyInfoManager.Companion.getInstance().setStarted(false);
        this.stub.updateEditMode(this.isEditMode);
        this.qsPanelAnimatorManager.startRebound();
        this.qsPanelAnimatorManager.updatePivotXY(true);
    }

    public final boolean exitAddingMode(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z2 = this.isEditMode;
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusQSRootViewComponent-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m("[exitAddingMode] (", "), isEditMode=", ", isAddingMode=", z, z2), this.isAddingMode);
        }
        if (!this.isEditMode || !this.isAddingMode) {
            return false;
        }
        this.isAddingMode = false;
        EditModeSwitchAnimationManager editModeSwitchAnimationManager = this.editModeSwitchAnimationManager;
        if (z) {
            editModeSwitchAnimationManager.exitAddingMode();
        } else {
            editModeSwitchAnimationManager.skipToEnd();
        }
        updatePanelWhenAddingModeChange();
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onExitAddingMode(z);
        }
        return true;
    }

    public final void exitDragDisableMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->".concat("[exitDragDisableMode] true"));
        }
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onExitDragDisableMode();
        }
    }

    public final void exitDragMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->".concat("[exitDragMode] true"));
        }
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onExitDragMode();
        }
    }

    public final boolean exitEditMode(boolean z) {
        boolean z2 = this.isEditMode;
        Object value = this.dragHelper.getDragAnimationStateFlow().getValue();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("exitEditMode withAnimation=", ", oldEditMode=", ", dragHelper.dragAnimationStateFlow.value=", z, z2);
        sbM.append(value);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "exitDropMode", null, 8);
        this.qsPersonalComponent.stopDrag();
        this.qsOtherTilesComponent.stopDrag();
        exitAddingMode(z);
        if (!this.isEditMode) {
            return false;
        }
        this.isEditMode = false;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$withAnimation = z;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.exitEditRunnableAfterDragAnim = anonymousClass1;
        if (Intrinsics.areEqual(this.dragHelper.getDragAnimationStateFlow().getValue(), DragAnimationState.Idle.INSTANCE) || !z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, OplusLockIconView$.ExternalSyntheticOutline0.m("exitEditMode DragAnimationState = Idle, withAnimation=", ", run dragAnimationRunnable directly.", z), null, 8);
            Runnable runnable = this.exitEditRunnableAfterDragAnim;
            if (runnable != null) {
                runnable.run();
            }
            this.exitEditRunnableAfterDragAnim = null;
        }
        this.qsPanelAnimatorManager.updatePivotXY(false);
        return true;
    }

    public final void expandQSPanel(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[expandQSPanel]", null, 8);
        if (!z) {
            reset("expandQSPanel");
        }
        cancelAnimator();
        bringToFront$1();
        flingQS(0.0f, true);
    }

    public final void expandQSPanelWithoutAnimate(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[expandQSPanelWithoutAnimate]", null, 8);
        if (!z) {
            reset("expandQSPanelWithoutAnimate");
        }
        cancelAnimator();
        bringToFront$1();
        updateMoveExpandHeight(getMaxPanelHeight(), 1.0f);
        this.qsPanelAnimatorManager.skipToEnd(true);
    }

    public final void flingQS(float f, boolean z) {
        float f2 = this.yVelocity;
        float f3 = this.curRawFraction;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("fling qs, expand: ", z, ", vel: ", f, " yVelocity=");
        sbM.append(f2);
        sbM.append(" curRawFraction=");
        sbM.append(f3);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (this.yVelocity == 0.0f && this.curRawFraction == 0.0f) {
            this.yVelocity = f;
        }
        if (z) {
            getMaxPanelHeight();
        }
        if (z) {
            updatePanelExpanded(true);
        }
        boolean z2 = false;
        if (!z && f > 0.0f && this.qsPanelAnimatorManager.getRawFraction() > 0.0f) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "gfxSceneBegin(), flingQS SCENE_QS_CLOSE", null, 8);
            JankUtils.gfxSceneBegin(getContext(), 9, "QS_CLOSE", 0L, 4L, true);
            this.hasSceneQSCloseEnd = false;
        }
        this.qsPanelAnimatorManager.onFling(this.yVelocity, z);
        if (z && this.isClosing && !this.hasSceneQSCloseEnd) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "gfxSceneEnd(), flingQS blocking SCENE_QS_CLOSE", null, 8);
            JankUtils.gfxSceneEnd(getContext(), new int[]{9});
            this.hasSceneQSCloseEnd = true;
        }
        if (!z && !isFullyCollapsed()) {
            z2 = true;
        }
        setClosing(z2);
        if (z) {
            return;
        }
        onCollapseQSPanelStart$1(true);
        this.stub.onQsClosingBegin();
    }

    public final void flingToScenario(boolean z) {
        this.qsPanelAnimatorManager.flingToScenario(Boolean.valueOf(z));
    }

    public final int getAreaTop() {
        return this.cellAreaProvider.getCellAreaConfig().getAHeight();
    }

    public final float getBlurFraction() {
        if (((OplusQSRootView) this.mView).getVisibility() == 4) {
            return 0.0f;
        }
        return this.qsPanelAnimatorManager.getBlurFraction();
    }

    public final CellAreaProvider getCellAreaProvider() {
        return this.cellAreaProvider;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0064  */
    public final Triple<List<View>, List<View>, List<View>> getExtraAnimationViews() {
        Triple<List<View>, List<View>, List<View>> tripleFindFirstAndLastVisibleChildren;
        List listEmptyList;
        List listEmptyList2;
        List list;
        List list2;
        List listEmptyList3;
        List listEmptyList4;
        List listEmptyList5;
        List listEmptyList6;
        List list3;
        List list4;
        List list5;
        List list6;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        RecyclerView recyclerView = this.personalContainerView;
        if (recyclerView == null) {
            recyclerView = null;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        Triple<List<View>, List<View>, List<View>> tripleFindFirstAndLastVisibleChildren2 = staggeredPagerLayoutManager != null ? staggeredPagerLayoutManager.findFirstAndLastVisibleChildren() : null;
        RecyclerView recyclerView2 = this.otherTilesContainerView;
        if (zBooleanValue || zBooleanValue2) {
            if (recyclerView2 == null) {
                recyclerView2 = null;
            }
            RecyclerView.LayoutManager layoutManager2 = recyclerView2.getLayoutManager();
            StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = layoutManager2 instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager2 : null;
            if (staggeredPagerLayoutManager2 != null) {
                tripleFindFirstAndLastVisibleChildren = staggeredPagerLayoutManager2.findFirstAndLastVisibleChildren();
            } else {
                tripleFindFirstAndLastVisibleChildren = null;
            }
        } else {
            if (recyclerView2 == null) {
                recyclerView2 = null;
            }
            RecyclerView.LayoutManager layoutManager3 = recyclerView2.getLayoutManager();
            StaggeredPagerLayoutManager staggeredPagerLayoutManager3 = layoutManager3 instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager3 : null;
            if (staggeredPagerLayoutManager3 != null) {
                tripleFindFirstAndLastVisibleChildren = staggeredPagerLayoutManager3.findVisibleAndCurrentPageChildren();
            } else {
                tripleFindFirstAndLastVisibleChildren = null;
            }
        }
        ConstraintLayout constraintLayout = this.quickEntrance;
        if (zBooleanValue) {
            if (tripleFindFirstAndLastVisibleChildren == null || (list6 = (List) tripleFindFirstAndLastVisibleChildren.getThird()) == null || (listEmptyList3 = CollectionsKt___CollectionsKt.filterNotNull(list6)) == null) {
                listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList.addAll(listEmptyList3);
            if (tripleFindFirstAndLastVisibleChildren == null || (list5 = (List) tripleFindFirstAndLastVisibleChildren.getSecond()) == null || (listEmptyList4 = CollectionsKt___CollectionsKt.filterNotNull(list5)) == null) {
                listEmptyList4 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList3.addAll(listEmptyList4);
            ViewGroup viewGroup = this.bottomArea;
            if (viewGroup == null) {
                viewGroup = null;
            }
            arrayList3.add(viewGroup);
            if (tripleFindFirstAndLastVisibleChildren2 == null || (list4 = (List) tripleFindFirstAndLastVisibleChildren2.getFirst()) == null || (listEmptyList5 = CollectionsKt___CollectionsKt.filterNotNull(list4)) == null) {
                listEmptyList5 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList2.addAll(listEmptyList5);
            if (tripleFindFirstAndLastVisibleChildren == null || (list3 = (List) tripleFindFirstAndLastVisibleChildren.getFirst()) == null || (listEmptyList6 = CollectionsKt___CollectionsKt.filterNotNull(list3)) == null) {
                listEmptyList6 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList2.addAll(listEmptyList6);
            arrayList2.add(constraintLayout == null ? null : constraintLayout);
        } else {
            if (tripleFindFirstAndLastVisibleChildren == null || (list2 = (List) tripleFindFirstAndLastVisibleChildren.getThird()) == null || (listEmptyList = CollectionsKt___CollectionsKt.filterNotNull(list2)) == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList.addAll(listEmptyList);
            if (tripleFindFirstAndLastVisibleChildren == null || (list = (List) tripleFindFirstAndLastVisibleChildren.getSecond()) == null || (listEmptyList2 = CollectionsKt___CollectionsKt.filterNotNull(list)) == null) {
                listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList3.addAll(listEmptyList2);
            ViewGroup viewGroup2 = this.bottomArea;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            arrayList3.add(viewGroup2);
            arrayList2.add(constraintLayout == null ? null : constraintLayout);
            View view = this.qsStatusBar;
            if (view == null) {
                view = null;
            }
            arrayList2.add(view);
        }
        View[] viewArr = new View[3];
        if (constraintLayout == null) {
            constraintLayout = null;
        }
        viewArr[0] = constraintLayout;
        ViewGroup viewGroup3 = this.bottomArea;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        viewArr[1] = viewGroup3;
        View view2 = this.qsStatusBar;
        viewArr[2] = view2 != null ? view2 : null;
        arrayList.addAll(CollectionsKt__CollectionsKt.listOfNotNull(viewArr));
        return new Triple<>(arrayList, arrayList2, arrayList3);
    }

    public final View getFakeLayout() {
        return this.fakeStatusContainerController.getFakeLayout();
    }

    public final List<ViewGroup> getQSContainers() {
        ViewGroup[] viewGroupArr = new ViewGroup[2];
        COUIRecyclerView cOUIRecyclerView = this.personalContainerView;
        if (cOUIRecyclerView == null) {
            cOUIRecyclerView = null;
        }
        if (cOUIRecyclerView == null) {
            cOUIRecyclerView = null;
        }
        viewGroupArr[0] = cOUIRecyclerView;
        COUIRecyclerView cOUIRecyclerView2 = this.otherTilesLandLeftContainerView;
        if (cOUIRecyclerView2 == null) {
            cOUIRecyclerView2 = null;
        }
        viewGroupArr[1] = cOUIRecyclerView2 != null ? cOUIRecyclerView2 : null;
        return CollectionsKt__CollectionsKt.listOfNotNull(viewGroupArr);
    }

    public final Rect getQSPageViewArea() {
        ViewGroup viewGroup = this.otherTilesContainerView;
        if ((viewGroup == null ? null : viewGroup).getVisibility() == 0) {
            return new Rect((viewGroup == null ? null : viewGroup).getLeft(), (viewGroup == null ? null : viewGroup).getTop(), (viewGroup != null ? viewGroup : null).getRight(), ((OplusQSRootView) this.mView).getHeight());
        }
        return null;
    }

    public final Pair<List<Rect>, List<Rect>> getQSScrollXHotZone() {
        Rect rect = new Rect(0, 0, ((OplusQSRootView) this.mView).getWidth(), ((OplusQSRootView) this.mView).getHeight());
        int currentPage = this.qsOtherTilesComponent.getCurrentPage();
        ArrayList arrayList = new ArrayList();
        ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).getClass();
        if (currentPage != 0) {
            View view = this.otherTilesContainerView;
            if (view == null) {
                view = null;
            }
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int i = iArr[0];
            arrayList.add(new Rect(i, iArr[1], view.getWidth() + i, view.getHeight() + iArr[1]));
        }
        return TuplesKt.to(CollectionsKt__CollectionsJVMKt.listOf(rect), arrayList);
    }

    public final QSPanelAnimatorManager getQsPanelAnimatorManager() {
        return this.qsPanelAnimatorManager;
    }

    public final ViewGroup getStatusBarContainer() {
        return this.statusBarContainer;
    }

    public final boolean hideCouiPopup() {
        return this.qsQuickEntranceComponent.hideCouiPopup();
    }

    public final boolean isBlurFractionAnimationRunning() {
        return this.qsPanelAnimatorManager.isBlurFractionAnimationRunning();
    }

    public final boolean isFractionAnimationRunning() {
        return this.qsPanelAnimatorManager.isFractionAnimationRunning();
    }

    public final boolean isFullyCollapsed() {
        return this.expandedFraction == 0.0f;
    }

    public final boolean isFullyExpanded() {
        return this.expandedFraction == 1.0f;
    }

    public final boolean isInViewpagerArea(PointF pointF) {
        Rect rect = this.viewpagerRect;
        ViewGroup viewGroup = this.otherTilesContainerView;
        rect.set((viewGroup == null ? null : viewGroup).getLeft(), (viewGroup == null ? null : viewGroup).getTop(), (viewGroup == null ? null : viewGroup).getRight(), (viewGroup != null ? viewGroup : null).getBottom());
        return this.viewpagerRect.contains((int) pointF.x, (int) pointF.y);
    }

    public final boolean isQsAnimationRunning() {
        return this.qsPanelAnimatorManager.isFractionAnimationRunning();
    }

    public final boolean longClickBlock() {
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue() || ((Boolean) companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue() || this.stub.isKeyguardShowing() || !isFullyExpanded()) {
            return false;
        }
        this.qsMoreButtonPopupWindowController.showEdit$1();
        return true;
    }

    public final void onCollapseQSPanelStart$1(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[onCollapseQSPanel] ", "OplusQSRootViewComponent-->", "SystemUi--QuickSettings", z);
        }
        exitAddingMode(z);
        if (!z) {
            exitEditMode(false);
        } else if (this.editModeSwitchAnimationManager.isAnyRunning() && !this.isEditMode) {
            this.editModeSwitchAnimationManager.skipToEnd();
        }
        GlobalUtils.panelClosedOnDown = false;
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onCollapseQSPanelStart(z);
        }
    }

    public final void onDestroy() {
        OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.CONTROL_PANEL_SWITCH);
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).destroyComponent();
        }
    }

    public final void onSystemIconScaleChanged(float f) {
        this.fakeStatusContainerController.onSystemIconScaleChanged(f);
    }

    public final void onTrackingStarted$1() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onTrackingStarted", null, 8);
        this.isTracking = true;
        updatePanelExpanded(false);
        this.stub.trackingStart(this.mView);
    }

    public final void onTrackingStopped$1(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onTrackingStopped", null, 8);
        this.isTracking = false;
        updatePanelExpanded(z);
        this.stub.updateSeparateQSExpansion(this.expandedFraction, this.isExpanded, this.isTracking, this.expandedHeight);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onViewAttached", null, 8);
        this.qsPanelAnimatorManager.addExpandFractionCallback(this.qsPanelExpandFractionListener);
        this.qsPanelAnimatorManager.addElementVisibleChangeListener(this.qsElementShowingChangeListener);
        this.qsPanelAnimatorManager.addElementEndListener(this.qsElementEndListener);
        RepeatWhenAttachedKt.repeatWhenAttached(this.mView, Dispatchers.getMain().getImmediate(), new C01951(null));
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onViewDetached", null, 8);
        onTrackingStopped$1(false);
        this.qsPanelAnimatorManager.removeExpandFractionCallback(this.qsPanelExpandFractionListener);
        this.qsPanelAnimatorManager.skipToEnd(false);
        this.qsPanelAnimatorManager.removeElementVisibleChangeListener(this.qsElementShowingChangeListener);
        this.qsPanelAnimatorManager.removeElementEndListener(this.qsElementEndListener);
        updateMoveExpandHeight(0.0f, 0.0f);
        cancelAnimator();
        setListening(false);
        hideCouiPopup();
        this.cellAreaProvider.removeConfigUpdateListener(this.cellConfigUpdateListener);
        CellCalculatorManager.Companion.getInstance(getContext()).removeCellCalculatorListener(this.cellCalculatorListener);
    }

    public final void removePanelFractionChangeListener(PanelFractionChangeListener panelFractionChangeListener) {
        this.fractionChangeListeners.remove(panelFractionChangeListener);
    }

    public final void removeScenarioAnimatorCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        this.qsPanelAnimatorManager.removeCallback(qSScenarioAnimListener);
    }

    public final void reset(String str) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[reset] reason=".concat(str), null, 8);
        this.qsOtherTilesComponent.reset();
    }

    public final boolean resetExtraFakeTranslationY(boolean z) {
        if (!z) {
            return false;
        }
        AnimatorUtils animatorUtils = AnimatorUtils.INSTANCE;
        if (animatorUtils.getExtraFakeTranslationY() <= 0.0f) {
            return false;
        }
        animatorUtils.setExtraFakeTranslationY(0.0f);
        this.fakeStatusContainerController.setTempRawFraction(0.0f);
        return true;
    }

    public final void resetTouch() {
        this.activePointerId = -1;
        this.touchDownPoint.set(-1.0f, -1.0f);
        this.lastPos.set(-1.0f, -1.0f);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "resetTouch", null, 8);
    }

    public final void setClipToPaddingForScroll(boolean z) {
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        RecyclerView recyclerView = this.otherTilesContainerView;
        if (recyclerView == null) {
            recyclerView = null;
        }
        recyclerView.setClipToPadding(z || zBooleanValue2 || zBooleanValue);
    }

    public final void setScenarioTouch(boolean z) {
        this.isScenarioTouch = z;
    }

    public final void setTileZoneScaleInfo(float f, float f2, float f3, boolean z) {
        float f4;
        ViewGroup viewGroup = this.otherTilesLandLeftContainerView;
        (viewGroup == null ? null : viewGroup).setScaleX(f2);
        (viewGroup == null ? null : viewGroup).setScaleY(f2);
        ViewGroup viewGroup2 = this.otherTilesContainerView;
        (viewGroup2 == null ? null : viewGroup2).setScaleX(f2);
        (viewGroup2 == null ? null : viewGroup2).setScaleY(f2);
        ViewGroup viewGroup3 = this.personalContainerView;
        (viewGroup3 == null ? null : viewGroup3).setScaleX(f2);
        (viewGroup3 == null ? null : viewGroup3).setScaleY(f2);
        ViewGroup viewGroup4 = this.bottomArea;
        if (viewGroup4 == null) {
            viewGroup4 = null;
        }
        viewGroup4.setScaleX(f2);
        ViewGroup viewGroup5 = this.bottomArea;
        if (viewGroup5 == null) {
            viewGroup5 = null;
        }
        viewGroup5.setScaleY(f2);
        if (z) {
            (viewGroup == null ? null : viewGroup).setTransitionAlpha(f);
            (viewGroup2 == null ? null : viewGroup2).setTransitionAlpha(f);
            (viewGroup3 == null ? null : viewGroup3).setTransitionAlpha(f);
            ViewGroup viewGroup6 = this.bottomArea;
            if (viewGroup6 == null) {
                viewGroup6 = null;
            }
            viewGroup6.setTransitionAlpha(f);
            ViewGroup viewGroup7 = this.quickEntrance;
            if (viewGroup7 == null) {
                viewGroup7 = null;
            }
            viewGroup7.setTransitionAlpha(f);
            this.editTopController.updateTopViewAlpha(f);
        } else {
            (viewGroup3 == null ? null : viewGroup3).setTransitionAlpha(f);
        }
        if (f3 > -1.0f) {
            CellAreaProvider cellAreaProvider = this.cellAreaProvider;
            if (z) {
                f4 = ((-cellAreaProvider.getCellAreaConfig().getC2Height()) - 60.0f) * (1.0f - f3);
            } else {
                f4 = ((-cellAreaProvider.getCellAreaConfig().getC2Height()) - 60.0f) * f3;
            }
            setPanelNegY(f4);
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.setTranslationY(this.panelNegY);
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setTranslationY(this.panelNegY);
            (viewGroup3 != null ? viewGroup3 : null).setTranslationY(this.panelNegY);
        }
    }

    public final void setTouchAndAnimationDisabled(boolean z) {
        this.isTouchDisabled = z;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setTouchAndAnimationDisabled ", z), null, 8);
        if (this.isTouchDisabled) {
            cancelAnimator();
            if (this.isTracking) {
                onTrackingStopped$1(false);
            }
            if (this.isClosing) {
                setClosing(false);
            }
        }
    }

    public final boolean shouldExpand(float f, float f2, float f3, boolean z) {
        PointF pointF = this.touchDownPoint;
        float f4 = f2 - pointF.x;
        float f5 = f3 - pointF.y;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("shouldExpand vel:", f, ", xDisplacement:", f4, ", yDisplacement:");
        sbM.append(f5);
        sbM.append(", isUp=");
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (z) {
            return shouldExpand(f, f);
        }
        return (f4 == 0.0f && f5 == 0.0f) ? false : true;
    }

    public final void showDeviceMonitoringDialog() {
        this.qsSecurity.showDeviceMonitoringDialog();
    }

    public final void startExpandMotion(MotionEvent motionEvent, int i, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(KeyguardLogger$.ExternalSyntheticOutline0.m("startExpandMotion event: ", motionEvent.getActionMasked(), ", startTracking: ", " isClosing: ", z), this.isClosing, " panelClosedOnDown: ", this.panelClosedOnDown), null, 8);
        if (z) {
            this.stub.gpuBoomForTouchEvent(motionEvent);
        }
        PointF pointF = this.touchDownPoint;
        boolean z2 = (pointF.y == -1.0f && pointF.x == -1.0f) || motionEvent.getActionMasked() == 0;
        if ((!this.isTracking || isFullyCollapsed()) && z2) {
            this.initialHeightOnTouch = this.expandedHeight;
            setPanelClosedOnDown(isFullyCollapsed());
            if (motionEvent.getActionMasked() == 2 && motionEvent.getHistorySize() > 0 && motionEvent.getPointerCount() == 1) {
                this.touchDownPoint.set(motionEvent.getHistoricalX(0), motionEvent.getHistoricalY(0));
            } else {
                this.touchDownPoint.set(motionEvent.getRawX(i), motionEvent.getRawY(i));
            }
            PointF pointF2 = this.lastPos;
            PointF pointF3 = this.touchDownPoint;
            pointF2.set(pointF3.x, pointF3.y);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "initialOffsetOnTouch: " + this.initialHeightOnTouch + ", panelClosedOnDown: " + this.panelClosedOnDown, null, 8);
        }
        if (z) {
            onTrackingStarted$1();
        }
    }

    public final void startWaitingForExpandGesture() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(this.expandedFraction, "startWaitingForExpandGesture return expandedFraction="), null, 8);
        boolean useMotionBlur = this.stub.getUseMotionBlur();
        float f = this.expandedFraction;
        if (useMotionBlur) {
            if (f > 0.2f) {
                return;
            }
        } else if (f >= 1.0f) {
            return;
        }
        this.expectingSynthesizedDown = true;
        onTrackingStarted$1();
    }

    public final boolean syncBlurFractionFromExternal(float f) {
        return this.qsPanelAnimatorManager.syncBlurFractionFromExternal(f);
    }

    public final void tryStopGfx() {
        if (this.isGfxStart) {
            this.stub.restrainNavigationBarInvertColor(false);
            this.stub.stopWaitAction();
            if (this.isQsPullTraceStart) {
                JankUtils.gfxSceneEnd(getContext(), new int[]{7});
                Trace.asyncTraceEnd(8L, "QS PULL", 7);
                this.isQsPullTraceStart = false;
            }
            this.oplusGcSupressionExt.callGcDesupression(3);
            this.isGfxStart = false;
            LogUtil.setLogBufferDisabled(false);
        }
    }

    public final void updateMoveExpandHeight(float f, float f2) {
        this.expandedHeight = f;
        setExpandedFraction(f2);
        float f3 = this.lastExpandedHeight;
        float f4 = this.expandedHeight;
        OplusSeparateQSPlugin.IStub iStub = this.stub;
        float f5 = this.expandedFraction;
        if (f3 > f4) {
            iStub.notifyStatusbarState(f5 >= 0.001f);
        } else {
            iStub.notifyStatusbarState(f5 > 0.0f);
        }
        updatePanelExpanded(false);
        this.stub.updateSeparateQSExpansion(this.expandedFraction, this.isExpanded, this.isTracking, this.expandedHeight);
        this.lastExpandedHeight = this.expandedHeight;
    }

    public final void updatePanelExpanded(boolean z) {
        boolean z2 = !isFullyCollapsed() || this.expectingSynthesizedDown || z;
        this.isExpanded = z2;
        setQsVisible(z2);
        if ((isFullyExpanded() || isFullyCollapsed()) && this.isClosing) {
            setClosing(false);
        }
    }

    public final void updatePanelWhenAddingModeChange() {
        if (!this.isAddingMode || !OplusQSSpecialModeProvider.Companion.getInstance().isSplitAdd()) {
            this.panelContainer.setTranslationX(0.0f);
            this.panelContainer.setScaleX(1.0f);
            this.panelContainer.setScaleY(1.0f);
            return;
        }
        ConstraintLayout constraintLayout = this.panelContainer;
        int minMargin = this.cellAreaProvider.getCellAreaConfig().getMinMargin();
        AddTileCalculator addTileCalculator = AddTileCalculator.INSTANCE;
        constraintLayout.setTranslationX(minMargin - addTileCalculator.getAddSplitPanelMargin());
        int layoutDirection = getContext().getResources().getConfiguration().getLayoutDirection();
        ConstraintLayout constraintLayout2 = this.panelContainer;
        if (layoutDirection == 1) {
            constraintLayout2.setTranslationX(-constraintLayout2.getTranslationX());
            this.panelContainer.setPivotX(addTileCalculator.getAddSplitPanelMargin());
        } else {
            constraintLayout2.setPivotX(constraintLayout2.getWidth() - addTileCalculator.getAddSplitPanelMargin());
        }
        this.panelContainer.setScaleX(addTileCalculator.getAddSplitPanelScale());
        this.panelContainer.setScaleY(addTileCalculator.getAddSplitPanelScale());
        this.panelContainer.setPivotY(0.0f);
    }

    public final void updateResource(String str, boolean z) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        int i;
        int c2Height;
        int i2;
        StaggeredPagerLayoutManager.LayoutConfig layoutConfig;
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateResource: updateLandSplitMode=", " isLandSplitMode=", " reason=", z, zBooleanValue);
        sbM.append(str);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        boolean zBooleanValue2 = ((Boolean) companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue();
        CellAreaProvider cellAreaProvider = this.cellAreaProvider;
        int aHeight = zBooleanValue2 ? cellAreaProvider.getCellAreaConfig().getAHeight() : cellAreaProvider.getCellAreaConfig().getC5Height();
        ViewGroup viewGroup3 = this.statusBarContainer;
        ViewGroup.LayoutParams layoutParams = viewGroup3.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = aHeight;
        }
        viewGroup3.setLayoutParams(viewGroup3.getLayoutParams());
        ViewGroup viewGroup4 = this.quickEntrance;
        ViewGroup viewGroup5 = viewGroup4 == null ? null : viewGroup4;
        ViewGroup.LayoutParams layoutParams2 = viewGroup5.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams2 != null) {
            marginLayoutParams2.topMargin = this.cellAreaProvider.getCellAreaConfig().getAHeight();
        }
        viewGroup5.setLayoutParams(viewGroup5.getLayoutParams());
        this.fakeStatusContainerController.updateTopSpace(aHeight, this.cellAreaProvider.getCellAreaConfig().getStatusBarRealHeight());
        if (isFullyCollapsed()) {
            this.qsPanelAnimatorManager.initStatusWhenExpandStarted();
        }
        this.qsBottomComponent.updateBottomSpacing();
        Rect rect = this.viewpagerRect;
        RecyclerView recyclerView = this.otherTilesContainerView;
        rect.set((recyclerView == null ? null : recyclerView).getLeft(), (recyclerView == null ? null : recyclerView).getTop(), (recyclerView == null ? null : recyclerView).getRight(), (recyclerView == null ? null : recyclerView).getBottom());
        Rect rect2 = this.personalContainerRect;
        ViewGroup viewGroup6 = this.personalContainerView;
        rect2.set((viewGroup6 == null ? null : viewGroup6).getLeft(), (viewGroup6 == null ? null : viewGroup6).getTop(), (viewGroup6 == null ? null : viewGroup6).getRight(), (viewGroup6 == null ? null : viewGroup6).getBottom());
        if (z) {
            TemporarilyInfoManager.Companion.getInstance().setLandSplitLayout(zBooleanValue);
            boolean zBooleanValue3 = ((Boolean) companion.getInstance().isLandSplitMode().getValue()).booleanValue();
            if (zBooleanValue3) {
                int dimensionPixelOffset = (getContext().getResources().getDisplayMetrics().widthPixels / 2) - getContext().getResources().getDimensionPixelOffset(2131172045);
                CellCalculatorManager.Companion companion2 = CellCalculatorManager.Companion;
                int iCellZoneWidth = dimensionPixelOffset - companion2.getInstance(getContext()).cellZoneWidth();
                this.tmpConstraintSet.clone(this.panelContainer);
                this.tmpConstraintSet.connect((viewGroup4 == null ? null : viewGroup4).getId(), 6, 0, 6);
                this.tmpConstraintSet.clear((viewGroup4 == null ? null : viewGroup4).getId(), 7);
                this.tmpConstraintSet.connect((viewGroup4 == null ? null : viewGroup4).getId(), 3, this.statusBarContainer.getId(), 4);
                this.tmpConstraintSet.connect((viewGroup4 == null ? null : viewGroup4).getId(), 4, 0, 4);
                this.tmpConstraintSet.connect(this.statusBarContainer.getId(), 3, 0, 3);
                this.tmpConstraintSet.connect(this.statusBarContainer.getId(), 6, 0, 6, iCellZoneWidth);
                this.tmpConstraintSet.connect(this.statusBarContainer.getId(), 7, 2131364778, 7);
                this.tmpConstraintSet.connect(getFakeLayout().getId(), 3, 0, 3);
                this.tmpConstraintSet.connect(getFakeLayout().getId(), 6, 0, 6);
                this.tmpConstraintSet.connect(getFakeLayout().getId(), 7, 2131364778, 7);
                this.tmpConstraintSet.clear((viewGroup6 == null ? null : viewGroup6).getId(), 6);
                this.tmpConstraintSet.connect((viewGroup6 == null ? null : viewGroup6).getId(), 7, 2131364778, 6);
                this.tmpConstraintSet.connect((viewGroup6 == null ? null : viewGroup6).getId(), 3, this.statusBarContainer.getId(), 4);
                this.tmpConstraintSet.clear(2131364436, 6);
                this.tmpConstraintSet.connect(2131364436, 7, 2131364778, 6);
                this.tmpConstraintSet.connect(2131364436, 3, this.statusBarContainer.getId(), 4);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 6, 2131364778, 7);
                this.tmpConstraintSet.clear((recyclerView == null ? null : recyclerView).getId(), 7);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 3, this.statusBarContainer.getId(), 4);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 4, 0, 4);
                this.tmpConstraintSet.connect(2131364712, 6, (recyclerView == null ? null : recyclerView).getId(), 6);
                this.tmpConstraintSet.connect(2131364712, 7, (recyclerView == null ? null : recyclerView).getId(), 7);
                this.tmpConstraintSet.connect(2131364712, 3, (recyclerView == null ? null : recyclerView).getId(), 3, companion2.getInstance(getContext()).cellZoneHeight(true, false, false));
            } else {
                this.tmpConstraintSet.clone(this.panelContainer);
                this.tmpConstraintSet.clear((viewGroup4 == null ? null : viewGroup4).getId(), 6);
                this.tmpConstraintSet.connect((viewGroup4 == null ? null : viewGroup4).getId(), 7, 0, 7);
                this.tmpConstraintSet.connect((viewGroup4 == null ? null : viewGroup4).getId(), 3, 0, 3);
                this.tmpConstraintSet.clear((viewGroup4 == null ? null : viewGroup4).getId(), 4);
                this.tmpConstraintSet.connect(this.statusBarContainer.getId(), 3, (viewGroup4 == null ? null : viewGroup4).getId(), 4);
                this.tmpConstraintSet.clear(this.statusBarContainer.getId(), 6);
                this.tmpConstraintSet.connect(this.statusBarContainer.getId(), 7, 0, 7);
                this.tmpConstraintSet.connect(getFakeLayout().getId(), 3, (viewGroup4 == null ? null : viewGroup4).getId(), 4);
                this.tmpConstraintSet.clear(getFakeLayout().getId(), 6);
                this.tmpConstraintSet.connect(getFakeLayout().getId(), 7, 0, 7);
                this.tmpConstraintSet.clear((viewGroup6 == null ? null : viewGroup6).getId(), 6);
                this.tmpConstraintSet.connect((viewGroup6 == null ? null : viewGroup6).getId(), 7, 0, 7);
                this.tmpConstraintSet.connect((viewGroup6 == null ? null : viewGroup6).getId(), 3, this.statusBarContainer.getId(), 4);
                this.tmpConstraintSet.clear((viewGroup6 == null ? null : viewGroup6).getId(), 4);
                this.tmpConstraintSet.clear((recyclerView == null ? null : recyclerView).getId(), 6);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 7, 0, 7);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 3, (viewGroup6 == null ? null : viewGroup6).getId(), 4);
                this.tmpConstraintSet.connect((recyclerView == null ? null : recyclerView).getId(), 4, 0, 4);
                this.tmpConstraintSet.clear(2131364712, 6);
                this.tmpConstraintSet.connect(2131364712, 7, (recyclerView == null ? null : recyclerView).getId(), 7);
                this.tmpConstraintSet.connect(2131364712, 3, (recyclerView == null ? null : recyclerView).getId(), 3);
            }
            this.tmpConstraintSet.applyTo(this.panelContainer);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateLandSplitMode: isLandSplit=" + zBooleanValue3, null, 8);
            reset("updateLandSplitMode");
            OplusQSRootView oplusQSRootView = (OplusQSRootView) this.mView;
            RunnableC01961 runnableC01961 = new RunnableC01961(0);
            runnableC01961.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSRootView.post(runnableC01961);
        }
        this.tmpConstraintSet.clone(this.panelContainer);
        boolean zBooleanValue4 = ((Boolean) companion.getInstance().isLandSplitMode().getValue()).booleanValue();
        boolean zBooleanValue5 = ((Boolean) companion.getInstance().isLargeScreen().getValue()).booleanValue();
        CellCalculatorManager.Companion companion3 = CellCalculatorManager.Companion;
        int colSpan = companion3.getInstance(getContext()).getGridInfo(zBooleanValue4, true).get(0).getColSpan() * companion3.getInstance(getContext()).getCellSize(true).getCellWidth();
        CellAreaProvider cellAreaProvider2 = this.cellAreaProvider;
        int minMargin = zBooleanValue5 ? (cellAreaProvider2.getCellAreaConfig().getMinMargin() * 2) + colSpan : cellAreaProvider2.getCellAreaConfig().getWidth();
        int colSpan2 = (minMargin - (companion3.getInstance(getContext()).getGridInfo(zBooleanValue4, true).get(0).getColSpan() * companion3.getInstance(getContext()).getCellSize(true).getCellWidth())) / 2;
        int dimension = (int) getContext().getResources().getDimension(2131172156);
        int i3 = dimension + colSpan2;
        ViewGroup viewGroup7 = this.statusBarContainer;
        int i4 = zBooleanValue4 ? dimension : i3;
        int paddingTop = viewGroup7.getPaddingTop();
        if (!zBooleanValue4) {
            dimension = i3;
        }
        viewGroup7.setPaddingRelative(i4, paddingTop, dimension, viewGroup7.getPaddingBottom());
        ViewGroup viewGroup8 = viewGroup4 == null ? null : viewGroup4;
        viewGroup8.setPaddingRelative(zBooleanValue4 ? 0 : colSpan2, viewGroup8.getPaddingTop(), zBooleanValue4 ? 0 : colSpan2, viewGroup8.getPaddingBottom());
        ViewGroup viewGroup9 = this.bottomArea;
        if (viewGroup9 == null) {
            viewGroup9 = null;
        }
        viewGroup9.setPaddingRelative(colSpan2, viewGroup9.getPaddingTop(), colSpan2, viewGroup9.getPaddingBottom());
        (recyclerView == null ? null : recyclerView).setClipToPadding(zBooleanValue5 || zBooleanValue4);
        ParallelCOUIRecyclerView.Companion companion4 = ParallelCOUIRecyclerView.Companion;
        companion4.handleIgnoreParentBounds(recyclerView == null ? null : recyclerView, zBooleanValue5);
        companion4.handleClipSplitParentBounds(recyclerView == null ? null : recyclerView, zBooleanValue5 || zBooleanValue4);
        RecyclerView recyclerView2 = recyclerView == null ? null : recyclerView;
        ParallelCOUIRecyclerView parallelCOUIRecyclerView = recyclerView2 instanceof ParallelCOUIRecyclerView ? (ParallelCOUIRecyclerView) recyclerView2 : null;
        if (parallelCOUIRecyclerView != null) {
            RecyclerView.LayoutManager layoutManager = parallelCOUIRecyclerView.getLayoutManager();
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
            if (staggeredPagerLayoutManager != null && (layoutConfig = staggeredPagerLayoutManager.getLayoutConfig()) != null) {
                layoutConfig.setNotSupportDragXAnima(zBooleanValue5 || zBooleanValue4);
            }
        }
        ConstraintSet constraintSet = this.tmpConstraintSet;
        if (zBooleanValue4) {
            constraintSet.constrainHeight((viewGroup4 == null ? null : viewGroup4).getId(), 0);
            this.tmpConstraintSet.constrainWidth((viewGroup4 == null ? null : viewGroup4).getId(), this.cellAreaProvider.getCellAreaConfig().getBottonZoneWidthLand());
            this.tmpConstraintSet.constrainWidth(this.statusBarContainer.getId(), 0);
            this.tmpConstraintSet.constrainWidth(2131364712, 0);
        } else {
            constraintSet.constrainHeight((viewGroup4 == null ? null : viewGroup4).getId(), this.cellAreaProvider.getCellAreaConfig().getButtonZoneHeight());
            this.tmpConstraintSet.constrainWidth((viewGroup4 == null ? null : viewGroup4).getId(), minMargin);
            this.tmpConstraintSet.constrainWidth(this.statusBarContainer.getId(), minMargin);
            this.tmpConstraintSet.constrainWidth(2131364712, minMargin);
            this.tmpConstraintSet.constrainWidth((viewGroup6 == null ? null : viewGroup6).getId(), minMargin);
            this.tmpConstraintSet.constrainWidth((recyclerView == null ? null : recyclerView).getId(), minMargin);
        }
        this.tmpConstraintSet.constrainHeight(this.statusBarContainer.getId(), this.cellAreaProvider.getCellAreaConfig().getStatusBarRealHeight());
        this.tmpConstraintSet.applyTo(this.panelContainer);
        CellAreaConfig cellAreaConfig = this.cellAreaProvider.getCellAreaConfig();
        cellAreaConfig.getClass();
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() && cellAreaConfig.isCenterHole) {
            boolean z2 = getContext().getResources().getConfiguration().getLayoutDirection() == 1;
            int rotation = getContext().getDisplay().getRotation();
            int holeBottom = this.cellAreaProvider.getCellAreaConfig().getHoleBottom();
            if (((!z2 || rotation != 1) && (z2 || rotation != 3)) || (i2 = holeBottom - colSpan2) < 0) {
                i2 = 0;
            }
            ConstraintLayout constraintLayoutRequireViewById = ((OplusQSRootView) this.mView).requireViewById(2131364722);
            ViewGroup.LayoutParams layoutParams3 = constraintLayoutRequireViewById.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            if (marginLayoutParams3 != null) {
                marginLayoutParams3.setMarginEnd(i2);
                constraintLayoutRequireViewById.setLayoutParams(marginLayoutParams3);
            }
        }
        boolean zIsPrivacyIconInQuickEntrance = companion.getInstance().isPrivacyIconInQuickEntrance();
        if (zIsPrivacyIconInQuickEntrance) {
            viewGroup = viewGroup4 == null ? null : viewGroup4;
        } else {
            viewGroup = this.statusBarContainer;
        }
        int paddingRight = this.statusBarContainer.getPaddingRight();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("updateHeaderAndPrivacy shouldChangePrivacyPlace:", paddingRight, ", padding: ", "OplusQSRootViewComponent-->", zIsPrivacyIconInQuickEntrance));
        }
        if (Intrinsics.areEqual(this.privacyView.getParent(), this.statusBarContainer)) {
            viewGroup2 = this.statusBarContainer;
        } else {
            viewGroup2 = (!Intrinsics.areEqual(this.privacyView.getParent(), viewGroup4 == null ? null : viewGroup4) || viewGroup4 == null) ? null : viewGroup4;
        }
        if (!Intrinsics.areEqual(viewGroup2, viewGroup)) {
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.privacyView);
            }
            if (viewGroup.indexOfChild(this.privacyView) == -1) {
                boolean zEquals = viewGroup.equals(viewGroup4 == null ? null : viewGroup4);
                LaunchableFrameLayout launchableFrameLayout = this.privacyView;
                if (zEquals) {
                    launchableFrameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(0, -2));
                    (viewGroup4 == null ? null : viewGroup4).addView(this.privacyView);
                    this.tmpConstraintSet.clone(viewGroup4 == null ? null : viewGroup4);
                    int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131172072);
                    this.tmpConstraintSet.connect(this.privacyView.getId(), 6, 2131364779, 7, dimensionPixelSize);
                    this.tmpConstraintSet.connect(this.privacyView.getId(), 7, 2131364796, 6, dimensionPixelSize);
                    this.tmpConstraintSet.connect(this.privacyView.getId(), 4, 0, 4, 0);
                    this.tmpConstraintSet.connect(this.privacyView.getId(), 3, 0, 3, 0);
                    this.tmpConstraintSet.constrainWidth(this.privacyView.getId(), 0);
                    this.tmpConstraintSet.applyTo(viewGroup4 == null ? null : viewGroup4);
                } else {
                    launchableFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    viewGroup.addView(this.privacyView);
                }
            }
        }
        View view = this.headerView;
        if (view != null) {
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2);
            layoutParams4.weight = 1.0f;
            layoutParams4.setMarginEnd(zIsPrivacyIconInQuickEntrance ? 0 : getContext().getResources().getDimensionPixelSize(2131172124));
            view.setLayoutParams(layoutParams4);
        }
        boolean zBooleanValue6 = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue();
        int dimensionPixelSize2 = zBooleanValue6 ? getContext().getResources().getDimensionPixelSize(2131172045) : 0;
        this.tmpConstraintSet.clone(this.panelContainer);
        ConstraintSet constraintSet2 = this.tmpConstraintSet;
        if (zBooleanValue6) {
            constraintSet2.setMargin(2131364436, 3, companion3.getInstance(getContext()).cellZoneHeight(true, true, false));
            i = 7;
            this.tmpConstraintSet.setMargin(this.statusBarContainer.getId(), 7, -((companion3.getInstance(getContext()).getCellSize(false).getCellWidth() * 4) + dimensionPixelSize2));
        } else {
            i = 7;
            constraintSet2.setMargin(this.statusBarContainer.getId(), 7, 0);
        }
        ConstraintSet constraintSet3 = this.tmpConstraintSet;
        if (viewGroup6 == null) {
            viewGroup6 = null;
        }
        constraintSet3.setMargin(viewGroup6.getId(), i, dimensionPixelSize2);
        this.tmpConstraintSet.setMargin((recyclerView == null ? null : recyclerView).getId(), 6, dimensionPixelSize2);
        this.tmpConstraintSet.setMargin(2131364436, i, dimensionPixelSize2);
        int aWidthLand = zBooleanValue6 ? this.cellAreaProvider.getCellAreaConfig().getAWidthLand() : 0;
        ConstraintSet constraintSet4 = this.tmpConstraintSet;
        if (viewGroup4 == null) {
            viewGroup4 = null;
        }
        constraintSet4.setMargin(viewGroup4.getId(), 6, aWidthLand);
        if (zBooleanValue6) {
            QsColorUtil.INSTANCE.getClass();
            c2Height = QsColorUtil.isSuperPowerSaveState ? this.cellAreaProvider.getCellAreaConfig().getC2Height() : 0;
        } else {
            int personalRowCount = companion3.getInstance(getContext()).getPersonalRowCount();
            CellAreaProvider cellAreaProvider3 = this.cellAreaProvider;
            c2Height = personalRowCount == 0 ? cellAreaProvider3.getCellAreaConfig().getC2Height() : cellAreaProvider3.getCellAreaConfig().getC4Height() - this.cellAreaProvider.getCellAreaConfig().getEditABottomBorderMargin();
        }
        ConstraintSet constraintSet5 = this.tmpConstraintSet;
        if (recyclerView == null) {
            recyclerView = null;
        }
        constraintSet5.setMargin(recyclerView.getId(), 3, c2Height);
        int iCellZoneHeight = zBooleanValue6 ? companion3.getInstance(getContext()).cellZoneHeight(true, false, false) : companion3.getInstance(getContext()).cellZoneHeight(false, false, false);
        ConstraintSet constraintSet6 = this.tmpConstraintSet;
        ViewGroup viewGroup10 = this.bottomArea;
        constraintSet6.setMargin((viewGroup10 == null ? null : viewGroup10).getId(), 3, iCellZoneHeight);
        this.tmpConstraintSet.applyTo(this.panelContainer);
        Iterator<T> it = this.nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).updateResource(z, getContext());
        }
    }

    public final void updateStatusBarTopMargin() {
        ViewGroup viewGroup = this.statusBarContainer;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
        boolean z = this.isEditMode;
        CellAreaProvider cellAreaProvider = this.cellAreaProvider;
        marginLayoutParams.topMargin = z ? cellAreaProvider.getCellAreaConfig().getC5Height() - this.cellAreaProvider.getCellAreaConfig().getStatusBarRealHeight() : cellAreaProvider.getCellAreaConfig().getC5Height();
        viewGroup.setLayoutParams(marginLayoutParams);
    }

    public final boolean shouldExpand(float f, float f2) {
        if (Math.abs(f2) < getFlingAnimationUtils().getMinVelocityPxPerSecond()) {
            return this.expandedFraction > 0.5f;
        }
        return f > 0.0f;
    }
}
