package com.oplus.systemui.panelanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.insets.Protection$;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.ExpandHelper;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.panelanimation.qs.QSPanelOverScrollAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.NotificationShadeDepthControllerEx;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManager;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.separate.OplusSimpleQSManagerImpl;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusPhysicsPropertyCache;
import com.oplus.systemui.util.OplusSpringAnimationExtKt;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotificationShadeRegionHelper;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;
import oplus.util.IOplusGcSupressionExt;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl extends PanelAnimationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PanelAnimationEx";
    private final OplusPanelAnimationExImpl$configurationListener$1 configurationListener;
    private final Context context;
    public float distanceRange;
    private final Lazy elementShowState$delegate;
    private final OplusPanelAnimationExImpl$expandedFractionChangeListener$1 expandedFractionChangeListener;
    public boolean hasAddExpandFractionCallback;
    public boolean hasResetNotificationRowsTranslationOnPullDown;
    public boolean isBreakCollapse;
    public boolean isElementShowing;
    public boolean isFirstElementShowing;
    public boolean isFixedElementShowing;
    public boolean isFlingToHeight;
    public boolean isNBInvertColor;
    public boolean isShadeShowing;
    public boolean isStarPullDown;
    private final KeyguardStateController keyguardStateController;
    private final Lazy levelPositionAlgorithm$delegate;
    private final LogBuffer logBuffer;
    private final CoroutineDispatcher mainDispatcher;
    private final dagger.Lazy notificationPanelViewController;
    private final dagger.Lazy notificationShadeDepthControllerEx;
    private final dagger.Lazy nssl;
    private final IOplusGcSupressionExt oplusGcSupressionExt;
    private final dagger.Lazy oplusSimpleQSManagerImpl;
    private OplusSpringAnimation oplusSpringAnimation;
    private final Lazy panelExpandFraction$delegate;
    public boolean panelExpanded;
    private final dagger.Lazy platformBlurManager;
    private final dagger.Lazy qSPanelOverScrollAnimationEx;
    private final dagger.Lazy separateNotificationAndQSEx;
    private final dagger.Lazy separateNotificationAndQSState;
    private final SysuiStatusBarStateController statusBarStateController;
    private final OplusPanelAnimationExImpl$statusBarStateListener$1 statusBarStateListener;
    private final SysUiStateControllerImpl sysUiStateController;
    private final Lazy translationYValue$delegate;

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.OplusPanelAnimationExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Dumpable {
        public /* synthetic */ OplusPanelAnimationExImpl this$0;

        public final void dump(PrintWriter printWriter, String[] strArr) {
            this.this$0.getLogBuffer().dump(printWriter, 0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.OplusPanelAnimationExImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.OplusPanelAnimationExImpl$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ OplusPanelAnimationExImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                OplusPanelAnimationExImpl.access$refreshElementShowingState(this.this$0, ((Boolean) obj).booleanValue());
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusPanelAnimationExImpl.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow enableSeparateNotificationAndQS = ((OplusSeparateNotificationAndQSState) OplusPanelAnimationExImpl.this.separateNotificationAndQSState.get()).getEnableSeparateNotificationAndQS();
                OplusPanelAnimationExImpl oplusPanelAnimationExImpl = OplusPanelAnimationExImpl.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = oplusPanelAnimationExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (enableSeparateNotificationAndQS.collect(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ElementShowState implements PanelAnimationEx.ElementShowState {
        private final Set<PanelAnimationEx.ElementShowingChangeListener> callbacks = new LinkedHashSet();

        public void addCallback(PanelAnimationEx.ElementShowingChangeListener elementShowingChangeListener) {
            synchronized (this.callbacks) {
                this.callbacks.add(elementShowingChangeListener);
            }
        }

        public final void notifyElementShowStateChange() {
            synchronized (this.callbacks) {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((PanelAnimationEx.ElementShowingChangeListener) it.next()).onElementShowingChanged();
                }
            }
        }

        public final void notifyFirstElementShowStateChange() {
            synchronized (this.callbacks) {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((PanelAnimationEx.ElementShowingChangeListener) it.next()).onFirstElementShowingChanged();
                }
            }
        }

        public final void notifyFixedElementShowingChange() {
            synchronized (this.callbacks) {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((PanelAnimationEx.ElementShowingChangeListener) it.next()).onFixedElementShowingChanged();
                }
            }
        }

        public final void notifyShadeShowingChange(int i, boolean z) {
            synchronized (this.callbacks) {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((PanelAnimationEx.ElementShowingChangeListener) it.next()).onShadeShowingChanged(i, z);
                }
            }
        }

        public void removeCallback(PanelAnimationEx.ElementShowingChangeListener elementShowingChangeListener) {
            synchronized (this.callbacks) {
                this.callbacks.remove(elementShowingChangeListener);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PanelExpandedFraction implements PanelAnimationEx.ExpandedFraction {
        public static final Companion Companion = new Companion();
        public float blurFraction;
        private final SpringAnimation blurFractionAnimation;
        private final CopyOnWriteArraySet<PanelAnimationEx.ExpandedFractionChangeListener> callbacks;
        private final Context context;
        private final SpringAnimation fractionAnimation;
        public boolean isQuicklyElementShow;
        public boolean isStartCustomRowActivity;
        private final LogBuffer logBuffer;
        private final dagger.Lazy platformBlurManager;
        private final QSPanelOverScrollAnimationEx qSPanelOverScrollAnimationEx;
        public float rawFraction;
        private final dagger.Lazy separateNotificationAndQSEx;
        private final SysuiStatusBarStateController statusBarStateController;
        private final Lazy fractionAnimationStiffness$delegate = LazyKt__LazyJVMKt.lazy(new OplusPanelAnimationExImpl$$ExternalSyntheticLambda0(2));
        private final Lazy blurFractionAnimationStiffness$delegate = LazyKt__LazyJVMKt.lazy(new OplusPanelAnimationExImpl$$ExternalSyntheticLambda0(3));

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public PanelExpandedFraction(SysuiStatusBarStateController sysuiStatusBarStateController, QSPanelOverScrollAnimationEx qSPanelOverScrollAnimationEx, LogBuffer logBuffer, dagger.Lazy lazy, dagger.Lazy lazy2, Context context) {
            this.statusBarStateController = sysuiStatusBarStateController;
            this.qSPanelOverScrollAnimationEx = qSPanelOverScrollAnimationEx;
            this.logBuffer = logBuffer;
            this.separateNotificationAndQSEx = lazy;
            this.platformBlurManager = lazy2;
            this.context = context;
            SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
            springAnimation.setSpring(new SpringForce().setDampingRatio(1.0f));
            springAnimation.getSpring().setStiffness(getFractionAnimationStiffness());
            springAnimation.setStartValue(0.0f);
            springAnimation.setMinimumVisibleChange(0.001f);
            int i = 0;
            OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1 oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1 = new OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1(i);
            oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation.addUpdateListener(oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1);
            OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2 oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2 = new OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2(i);
            oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation.addEndListener(oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2);
            this.fractionAnimation = springAnimation;
            SpringAnimation springAnimation2 = new SpringAnimation(new FloatValueHolder());
            springAnimation2.setSpring(new SpringForce().setDampingRatio(1.0f));
            springAnimation2.getSpring().setStiffness(getBlurFractionAnimationStiffness());
            springAnimation2.setStartValue(0.0f);
            springAnimation2.setMinimumVisibleChange(0.001f);
            int i2 = 1;
            OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1 oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$3 = new OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$1(i2);
            oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$3.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation2.addUpdateListener(oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$3);
            OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2 oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$4 = new OplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$2(i2);
            oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$4.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation2.addEndListener(oplusPanelAnimationExImpl$PanelExpandedFraction$fractionAnimation$1$4);
            this.blurFractionAnimation = springAnimation2;
            this.callbacks = new CopyOnWriteArraySet<>();
        }

        public static final float access$getBlurFraction(PanelExpandedFraction panelExpandedFraction, float f) {
            if (((Boolean) ((PlatformBlurManager) panelExpandedFraction.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue()) {
                return RangesKt___RangesKt.coerceIn(f / 0.8f, 0.0f, 1.0f);
            }
            if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
                return panelExpandedFraction.getFraction(0.15f, 0.6f, f);
            }
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f / 0.33f, 0.0f, 1.0f);
            if (((Boolean) ((PlatformBlurManager) panelExpandedFraction.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue() || fCoerceIn >= 0.02f) {
                return fCoerceIn;
            }
            return 0.0f;
        }

        public static final void access$notifyFractionEnd(PanelExpandedFraction panelExpandedFraction, float f) {
            Iterator<T> it = panelExpandedFraction.callbacks.iterator();
            while (it.hasNext()) {
                ((PanelAnimationEx.ExpandedFractionChangeListener) it.next()).onFractionEnd(f);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlurFraction(float f) {
            if (this.blurFraction == f) {
                return;
            }
            this.blurFraction = f;
            if (LogUtil.panicEnabled) {
                this.logBuffer.log(OplusPanelAnimationExImpl.TAG, LogLevel.DEBUG, Protection$.ExternalSyntheticOutline0.m(f, "blurFraction: "), (Throwable) null);
            }
            for (PanelAnimationEx.ExpandedFractionChangeListener expandedFractionChangeListener : this.callbacks) {
                getBlurFraction();
                expandedFractionChangeListener.onBlurFractionChange();
            }
        }

        public static boolean shouldUpdateAnimation(float f, float f2) {
            return Math.abs(f - f2) > 0.001f || f >= 1.0f || f <= 0.0f;
        }

        public void addCallback(PanelAnimationEx.ExpandedFractionChangeListener expandedFractionChangeListener) {
            if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "should be called from the main thread.");
            }
            this.callbacks.add(expandedFractionChangeListener);
        }

        public final void cancel$1(boolean z) {
            if (z && getRawFraction() <= 0.05f) {
                GroupExpansionManagerImpl groupExpansionManagerImpl = (GroupExpansionManager) Dependency.sDependency.getDependencyInner(GroupExpansionManager.class);
                if (groupExpansionManagerImpl != null) {
                    groupExpansionManagerImpl.collapseGroups();
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "fractionAnimation cancel, collapseGroups", null, 8);
            }
            this.fractionAnimation.cancel();
            this.blurFractionAnimation.cancel();
        }

        public final void expandedTo(float f) {
            float f2;
            if (this.isStartCustomRowActivity && f == 0.0f && DeviceUtil.isLandscape(this.context)) {
                f2 = 1400.0f;
            } else {
                if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
                    if (f > 0.0f) {
                        this.isQuicklyElementShow = true;
                    } else {
                        this.isQuicklyElementShow = false;
                    }
                } else if (f <= 0.0f) {
                    f2 = ((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue() ? 439.0f : 120.0f;
                }
                f2 = 400.0f;
            }
            boolean z = f > 0.0f;
            float rawFraction = getRawFraction();
            SpringForce spring = this.fractionAnimation.getSpring();
            Float fValueOf = spring != null ? Float.valueOf(spring.getFinalPosition()) : null;
            SpringForce spring2 = this.fractionAnimation.getSpring();
            Float fValueOf2 = spring2 != null ? Float.valueOf(spring2.getStiffness()) : null;
            float rawFraction2 = getRawFraction();
            float blurFraction = getBlurFraction();
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("animateExpanded, isExpanded=", z, ", (", rawFraction, " -> ");
            sbM.append(f);
            sbM.append("), lastFinalPosition=");
            sbM.append(fValueOf);
            sbM.append(", stiffness=($");
            sbM.append(fValueOf2);
            sbM.append(" -> ");
            sbM.append(f2);
            sbM.append(")rawFraction=");
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(sbM, rawFraction2, ", blurFraction=", blurFraction, " "), null, 8);
            setStartCustomRowActivity(false);
            SpringForce spring3 = this.fractionAnimation.getSpring();
            if (spring3 != null) {
                spring3.setStiffness(f2);
            }
            this.fractionAnimation.animateToFinalPosition(f);
            SpringForce spring4 = this.blurFractionAnimation.getSpring();
            if (spring4 != null) {
                spring4.setStiffness(f2);
            }
            this.blurFractionAnimation.animateToFinalPosition(f);
        }

        public float getBlurFraction() {
            if (this.statusBarStateController.getState() == 1) {
                return 0.0f;
            }
            return this.blurFraction;
        }

        public final SpringAnimation getBlurFractionAnimation() {
            return this.blurFractionAnimation;
        }

        public final float getBlurFractionAnimationStiffness() {
            return ((Number) this.blurFractionAnimationStiffness$delegate.getValue()).floatValue();
        }

        public float getElementFraction() {
            if (this.qSPanelOverScrollAnimationEx.isOnKeyguardAndExpanded()) {
                return getFraction(0.2f, 0.5f, getRawFraction());
            }
            return 1.0f;
        }

        public final Float getFinalPosition() {
            SpringForce spring = this.fractionAnimation.getSpring();
            if (spring != null) {
                return Float.valueOf(spring.getFinalPosition());
            }
            return null;
        }

        public final float getFraction(float f, float f2, float f3) {
            float f4 = f2 - f;
            if (LogUtil.panicEnabled) {
                LogBuffer logBuffer = this.logBuffer;
                LogLevel logLevel = LogLevel.DEBUG;
                boolean zIsOnKeyguardAndExpanded = this.qSPanelOverScrollAnimationEx.isOnKeyguardAndExpanded();
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("getFraction, start=", f, ", end=", f2, ", rawFraction=");
                sbM.append(f3);
                sbM.append(", isKeyguardQs=");
                sbM.append(zIsOnKeyguardAndExpanded);
                logBuffer.log(OplusPanelAnimationExImpl.TAG, logLevel, sbM.toString(), (Throwable) null);
            }
            if (f4 == 0.0f) {
                return 1.0f;
            }
            return RangesKt___RangesKt.coerceIn((f3 - f) / f4, 0.0f, 1.0f);
        }

        public final SpringAnimation getFractionAnimation() {
            return this.fractionAnimation;
        }

        public final float getFractionAnimationStiffness() {
            return ((Number) this.fractionAnimationStiffness$delegate.getValue()).floatValue();
        }

        public float getRawFraction() {
            return this.rawFraction;
        }

        public final boolean isRunningAnimation() {
            return this.fractionAnimation.isRunning();
        }

        public final void notifyFractionChange() {
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((PanelAnimationEx.ExpandedFractionChangeListener) it.next()).onFractionChanged(getRawFraction());
            }
        }

        public void removeCallback(PanelAnimationEx.ExpandedFractionChangeListener expandedFractionChangeListener) {
            if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "should be called from the main thread.");
            }
            this.callbacks.remove(expandedFractionChangeListener);
        }

        public final void setQuicklyElementShow(boolean z) {
            this.isQuicklyElementShow = z;
        }

        public void setRawFraction(float f) {
            if (this.rawFraction == f) {
                return;
            }
            this.rawFraction = f;
            OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get();
            if (oplusSeparateNotificationAndQSEx != null) {
                oplusSeparateNotificationAndQSEx.updateNotifyExpandFraction(f, !(f == 0.0f));
            }
            if (LogUtil.panicEnabled) {
                this.logBuffer.log(OplusPanelAnimationExImpl.TAG, LogLevel.DEBUG, Protection$.ExternalSyntheticOutline0.m(f, "rawFraction: "), (Throwable) null);
            }
            notifyFractionChange();
        }

        public void setStartCustomRowActivity(boolean z) {
            this.isStartCustomRowActivity = z;
        }

        public final void skipToEnd() {
            if (this.fractionAnimation.canSkipToEnd()) {
                this.fractionAnimation.skipToEnd();
            }
            if (this.blurFractionAnimation.canSkipToEnd()) {
                this.blurFractionAnimation.skipToEnd();
            }
        }

        public final void updateFraction(float f, boolean z) {
            if (!z) {
                if (f == 1.0f && getRawFraction() == 1.0f && getBlurFraction() == 1.0f) {
                    return;
                }
                OplusSpringAnimationExtKt.resetTo(this.fractionAnimation, f);
                OplusSpringAnimationExtKt.resetTo(this.blurFractionAnimation, f);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "updateFraction, springAnimation skipToEnd, fraction=" + f, null, 8);
                return;
            }
            SpringForce spring = this.fractionAnimation.getSpring();
            if (spring != null && spring.getStiffness() != getFractionAnimationStiffness()) {
                spring.setStiffness(getFractionAnimationStiffness());
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "updateFraction, fractionAnimationStiffness=" + getFractionAnimationStiffness() + ", finalPosition=" + getFinalPosition(), null, 8);
            }
            if (shouldUpdateAnimation(f, getRawFraction())) {
                this.fractionAnimation.animateToFinalPosition(f);
            }
            if (LogUtil.panicEnabled) {
                this.logBuffer.log(OplusPanelAnimationExImpl.TAG, LogLevel.DEBUG, ListPopupWindow$.ExternalSyntheticOutline0.m(f, "updateFraction, fraction=", " "), (Throwable) null);
            }
            SpringForce spring2 = this.blurFractionAnimation.getSpring();
            if (spring2 != null && spring2.getStiffness() != getBlurFractionAnimationStiffness()) {
                spring2.setStiffness(getBlurFractionAnimationStiffness());
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusPanelAnimationExImpl.TAG, "updateFraction, blurFractionAnimationStiffness=" + getBlurFractionAnimationStiffness() + ", finalPosition=" + getFinalPosition(), null, 8);
            }
            if (shouldUpdateAnimation(f, getBlurFraction())) {
                this.blurFractionAnimation.animateToFinalPosition(f);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PanelTranslationYValue implements PanelAnimationEx.TranslationYValue {
        public static final Companion Companion = new Companion();
        private final Set<PanelAnimationEx.PanelTranslationYChangeListener> callbacks = new LinkedHashSet();
        public boolean isExpandAnimationRunning;
        private final SpringAnimation keyguardHAnimation;
        private final LogBuffer logBuffer;
        private Function2 mblock;
        private final PanelExpandedFraction panelExpandedFraction;
        private final dagger.Lazy platformBlurManager;
        private Function0 thresholdForTranslationY;
        private final SpringAnimation translationYAnimation;
        public float translationYValue;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public PanelTranslationYValue(LogBuffer logBuffer, Function0 function0, PanelExpandedFraction panelExpandedFraction, dagger.Lazy lazy) {
            this.logBuffer = logBuffer;
            this.thresholdForTranslationY = function0;
            this.panelExpandedFraction = panelExpandedFraction;
            this.platformBlurManager = lazy;
            this.translationYValue = -((Number) this.thresholdForTranslationY.invoke()).floatValue();
            SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
            SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
            springAnimation.setSpring(new SpringForce().setDampingRatio(separateModeHelper.getSeparateMode() ? 0.6f : 1.0f));
            springAnimation.setStartValue(0.0f);
            springAnimation.getSpring().setStiffness(OplusPanelAnimationExImplKt.responseToStiffness(((Boolean) ((PlatformBlurManager) lazy.get()).getUseMotionBlur().getValue()).booleanValue() ? 0.4f : 0.3f));
            springAnimation.setMinimumVisibleChange(0.1f);
            int i = 1;
            OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1 oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1 = new OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1(i);
            oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation.addUpdateListener(oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1);
            OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2 oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2 = new OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2(i);
            oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation.addEndListener(oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2);
            this.translationYAnimation = springAnimation;
            SpringAnimation springAnimation2 = new SpringAnimation(new FloatValueHolder());
            springAnimation2.setSpring(new SpringForce().setDampingRatio(separateModeHelper.getSeparateMode() ? 0.6f : 1.0f));
            springAnimation2.setStartValue(0.0f);
            springAnimation2.getSpring().setStiffness(986.96f);
            springAnimation2.setMinimumVisibleChange(0.1f);
            int i2 = 0;
            OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1 oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$3 = new OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$1(i2);
            oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$3.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation2.addUpdateListener(oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$3);
            OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2 oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$4 = new OplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$2(i2);
            oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$4.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation2.addEndListener(oplusPanelAnimationExImpl$PanelTranslationYValue$keyguardHAnimation$1$4);
            this.keyguardHAnimation = springAnimation2;
        }

        public static /* synthetic */ void animateToFinalPosition$default(PanelTranslationYValue panelTranslationYValue, float f, boolean z, boolean z2, int i) {
            if ((i & 4) != 0) {
                z2 = false;
            }
            panelTranslationYValue.animateToFinalPosition(f, z, z2, 0.0f);
        }

        public void addCallback(PanelAnimationEx.PanelTranslationYChangeListener panelTranslationYChangeListener) {
            synchronized (this.callbacks) {
                this.callbacks.add(panelTranslationYChangeListener);
            }
        }

        public final void animateExpandTranslationY(float f, float f2, boolean z, float f3, float f4, boolean z2) {
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("animateExpandTranslationY, isExpanded=", z, ", threshold=", f, ", withoutAnimation=");
            sbM.append(z2);
            sbM.append(" maxH=");
            sbM.append(f4);
            sbM.append(" minH=");
            sbM.append(f3);
            LogUtil.i$default(LogUtil.TAG_COMMON, OplusPanelAnimationExImpl.TAG, sbM.toString(), null, 8);
            animateToFinalPosition(z ? 0.0f : -f, z, z2, f2);
            this.keyguardHAnimation.getSpring().setStiffness(z ? 200.0f : 986.96f);
            this.keyguardHAnimation.getSpring().setDampingRatio(z ? 0.6f : 1.0f);
            this.keyguardHAnimation.animateToFinalPosition(z ? f4 : f3);
            if (z2 && this.keyguardHAnimation.canSkipToEnd()) {
                SpringAnimation springAnimation = this.keyguardHAnimation;
                if (z) {
                    f3 = f4;
                }
                OplusSpringAnimationExtKt.resetTo(springAnimation, f3);
            }
        }

        public final void animateToFinalPosition(float f, boolean z, boolean z2, float f2) {
            if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
                this.translationYAnimation.getSpring().setDampingRatio((((RangesKt___RangesKt.coerceIn(f2, 0.0f, 10000.0f) - 0.0f) / 10000.0f) * (-0.39999998f)) + 1.0f);
            } else {
                this.translationYAnimation.getSpring().setDampingRatio(z ? 0.6f : 1.0f);
            }
            if (f == 0.0f && getTranslationYValue() == 0.0f) {
                return;
            }
            if (z2 && this.translationYAnimation.canSkipToEnd()) {
                OplusSpringAnimationExtKt.resetTo(this.translationYAnimation, f);
            } else {
                this.translationYAnimation.animateToFinalPosition(f);
            }
        }

        public final void cancel() {
            this.translationYAnimation.cancel();
        }

        public final void cancelKeyguardHAnimation() {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "PanelAnimationEx-->updateKeyguardQs keyguardHAnimation.cancel()");
            }
            this.keyguardHAnimation.cancel();
        }

        public final void expandedTo(float f, float f2, boolean z) {
            this.isExpandAnimationRunning = true;
            animateToFinalPosition(MathUtils.lerp(-((Number) this.thresholdForTranslationY.invoke()).floatValue(), 0.0f, f), z, false, f2);
        }

        public final SpringAnimation getKeyguardHAnimation() {
            return this.keyguardHAnimation;
        }

        public final Function2 getMblock() {
            return this.mblock;
        }

        public final SpringAnimation getTranslationYAnimation() {
            return this.translationYAnimation;
        }

        public float getTranslationYValue() {
            return this.translationYValue;
        }

        public final void notifyTranslationYChange() {
            synchronized (this.callbacks) {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((PanelAnimationEx.PanelTranslationYChangeListener) it.next()).onTranslationYChanged();
                }
            }
        }

        public void removeCallback(PanelAnimationEx.PanelTranslationYChangeListener panelTranslationYChangeListener) {
            synchronized (this.callbacks) {
                this.callbacks.remove(panelTranslationYChangeListener);
            }
        }

        public final void setKeyguardMiniChange(boolean z) {
            this.translationYAnimation.setMinimumVisibleChange(z ? 0.01f : 0.1f);
        }

        public final void setMblock(Function2 function2) {
            this.mblock = function2;
        }

        public void setTranslationYValue(float f) {
            if (this.translationYValue == f) {
                return;
            }
            this.translationYValue = f;
            notifyTranslationYChange();
        }

        public final void skipToEnd() {
            if (this.translationYAnimation.canSkipToEnd()) {
                this.translationYAnimation.skipToEnd();
            }
        }

        public final void updateKeyguardQs(float f) {
            this.keyguardHAnimation.getSpring().setStiffness(986.96f);
            this.keyguardHAnimation.getSpring().setDampingRatio(1.0f);
            this.keyguardHAnimation.animateToFinalPosition(f);
        }

        public final void updateKeyguardTranslationY(boolean z, boolean z2) {
            if (z) {
                animateToFinalPosition$default(this, z2 ? 0.0f : -((Number) this.thresholdForTranslationY.invoke()).floatValue(), z2, false, 8);
            }
        }

        public final void updatePanelTranslationY(boolean z, boolean z2, float f, boolean z3, boolean z4) {
            float fCoerceIn;
            if (z3) {
                animateToFinalPosition$default(this, z4 ? 0.0f : -((Number) this.thresholdForTranslationY.invoke()).floatValue(), z4, false, 8);
                return;
            }
            if (!z2) {
                animateToFinalPosition$default(this, MathUtils.lerp(-((Number) this.thresholdForTranslationY.invoke()).floatValue(), 0.0f, f), false, true, 8);
                return;
            }
            if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
                fCoerceIn = RangesKt___RangesKt.coerceIn(f / 0.86f, 0.0f, 1.0f);
            } else {
                if (!z) {
                    f -= 0.35f;
                }
                fCoerceIn = RangesKt___RangesKt.coerceIn(f / 0.65f, 0.0f, 1.0f);
            }
            animateToFinalPosition$default(this, MathUtils.lerp(-((Number) this.thresholdForTranslationY.invoke()).floatValue(), 0.0f, fCoerceIn), false, false, 12);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0021  */
        public final void updatePanelTranslationYForKeyguardQs(float f, float f2, boolean z) {
            float f3;
            if (((Number) this.thresholdForTranslationY.invoke()).floatValue() > 0.0f) {
                if (z) {
                    f = ((Number) this.thresholdForTranslationY.invoke()).floatValue();
                    f3 = f2 - f;
                } else {
                    f3 = -f2;
                }
            } else if (z) {
                f3 = -f2;
            } else {
                f3 = f2 - f;
            }
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f3, Math.min(-((Number) this.thresholdForTranslationY.invoke()).floatValue(), 0.0f), Math.max(-((Number) this.thresholdForTranslationY.invoke()).floatValue(), 0.0f));
            animateToFinalPosition$default(this, fCoerceIn, false, false, 12);
            if (LogUtil.panicEnabled) {
                this.logBuffer.log(OplusPanelAnimationExImpl.TAG, LogLevel.DEBUG, ListPopupWindow$.ExternalSyntheticOutline0.m(fCoerceIn, "updatePanelTranslation, finalTranslation=", " "), (Throwable) null);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.OplusPanelAnimationExImpl$updatePanelAnimatorFraction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C01941 implements LevelPositionAlgorithm.LevelPositionAnimationListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusPanelAnimationExImpl this$0;

        public /* synthetic */ C01941(int i) {
            this.$r8$classId = i;
        }

        public final void onAnimationEnd() {
            switch (this.$r8$classId) {
                case 0:
                    OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
                    if (!oplusPanelAnimationExImpl.getPanelExpandFraction().isRunningAnimation()) {
                        oplusPanelAnimationExImpl.pullDownNotificationPanelAnimatorEnd();
                    }
                    break;
                default:
                    OplusPanelAnimationExImpl oplusPanelAnimationExImpl2 = this.this$0;
                    if (!oplusPanelAnimationExImpl2.getPanelExpandFraction().isRunningAnimation()) {
                        oplusPanelAnimationExImpl2.pullDownNotificationPanelAnimatorEnd();
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-7cyol_fO3bzc9vR7-xbZw7O1SI, reason: not valid java name */
    public static PanelTranslationYValue m112$r8$lambda$7cyol_fO3bzc9vR7xbZw7O1SI(OplusPanelAnimationExImpl oplusPanelAnimationExImpl) {
        LogBuffer logBuffer = oplusPanelAnimationExImpl.logBuffer;
        OplusPanelAnimationExImpl$$ExternalSyntheticLambda1 oplusPanelAnimationExImpl$$ExternalSyntheticLambda1 = new OplusPanelAnimationExImpl$$ExternalSyntheticLambda1(2);
        oplusPanelAnimationExImpl$$ExternalSyntheticLambda1.f$0 = oplusPanelAnimationExImpl;
        VarHandle.storeStoreFence();
        return new PanelTranslationYValue(logBuffer, oplusPanelAnimationExImpl$$ExternalSyntheticLambda1, oplusPanelAnimationExImpl.getPanelExpandFraction(), oplusPanelAnimationExImpl.platformBlurManager);
    }

    public static PanelExpandedFraction $r8$lambda$Cg5vUqszVL1AxP797y47CSh3SDw(OplusPanelAnimationExImpl oplusPanelAnimationExImpl) {
        return new PanelExpandedFraction(oplusPanelAnimationExImpl.statusBarStateController, (QSPanelOverScrollAnimationEx) oplusPanelAnimationExImpl.qSPanelOverScrollAnimationEx.get(), oplusPanelAnimationExImpl.logBuffer, oplusPanelAnimationExImpl.separateNotificationAndQSEx, oplusPanelAnimationExImpl.platformBlurManager, oplusPanelAnimationExImpl.context);
    }

    public OplusPanelAnimationExImpl(Context context, ConfigurationController configurationController, SysuiStatusBarStateController sysuiStatusBarStateController, dagger.Lazy lazy, dagger.Lazy lazy2, LogBuffer logBuffer, DumpManager dumpManager, dagger.Lazy lazy3, dagger.Lazy lazy4, KeyguardStateController keyguardStateController, dagger.Lazy lazy5, dagger.Lazy lazy6, dagger.Lazy lazy7, SysUiStateControllerImpl sysUiStateControllerImpl, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, dagger.Lazy lazy8) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.qSPanelOverScrollAnimationEx = lazy;
        this.oplusSimpleQSManagerImpl = lazy2;
        this.logBuffer = logBuffer;
        this.separateNotificationAndQSEx = lazy3;
        this.notificationPanelViewController = lazy4;
        this.keyguardStateController = keyguardStateController;
        this.platformBlurManager = lazy5;
        this.notificationShadeDepthControllerEx = lazy6;
        this.separateNotificationAndQSState = lazy7;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.mainDispatcher = coroutineDispatcher;
        this.nssl = lazy8;
        int i = 0;
        this.levelPositionAlgorithm$delegate = LazyKt__LazyJVMKt.lazy(new OplusPanelAnimationExImpl$$ExternalSyntheticLambda0(i));
        OplusPanelAnimationExImpl$$ExternalSyntheticLambda1 oplusPanelAnimationExImpl$$ExternalSyntheticLambda1 = new OplusPanelAnimationExImpl$$ExternalSyntheticLambda1(i);
        oplusPanelAnimationExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.panelExpandFraction$delegate = LazyKt__LazyJVMKt.lazy(oplusPanelAnimationExImpl$$ExternalSyntheticLambda1);
        int i2 = 1;
        this.elementShowState$delegate = LazyKt__LazyJVMKt.lazy(new OplusPanelAnimationExImpl$$ExternalSyntheticLambda0(i2));
        OplusPanelAnimationExImpl$$ExternalSyntheticLambda1 oplusPanelAnimationExImpl$$ExternalSyntheticLambda2 = new OplusPanelAnimationExImpl$$ExternalSyntheticLambda1(i2);
        oplusPanelAnimationExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.translationYValue$delegate = LazyKt__LazyJVMKt.lazy(oplusPanelAnimationExImpl$$ExternalSyntheticLambda2);
        context.getResources().getDimension(2131173669);
        context.getResources().getDimension(2131173670);
        context.getResources().getDimension(2131173670);
        this.distanceRange = context.getResources().getDimension(2131172462);
        context.getResources().getDimension(2131165733);
        OplusPanelAnimationExImpl$statusBarStateListener$1 oplusPanelAnimationExImpl$statusBarStateListener$1 = new OplusPanelAnimationExImpl$statusBarStateListener$1();
        oplusPanelAnimationExImpl$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusPanelAnimationExImpl$statusBarStateListener$1;
        OplusPanelAnimationExImpl$configurationListener$1 oplusPanelAnimationExImpl$configurationListener$1 = new OplusPanelAnimationExImpl$configurationListener$1();
        oplusPanelAnimationExImpl$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = oplusPanelAnimationExImpl$configurationListener$1;
        OplusPanelAnimationExImpl$expandedFractionChangeListener$1 oplusPanelAnimationExImpl$expandedFractionChangeListener$1 = new OplusPanelAnimationExImpl$expandedFractionChangeListener$1();
        oplusPanelAnimationExImpl$expandedFractionChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.expandedFractionChangeListener = oplusPanelAnimationExImpl$expandedFractionChangeListener$1;
        this.oplusGcSupressionExt = (IOplusGcSupressionExt) ExtLoader.create(IOplusGcSupressionExt.class);
        if (getUseLayeredAnimation()) {
            configurationController.addCallback(oplusPanelAnimationExImpl$configurationListener$1);
            sysuiStatusBarStateController.addCallback(oplusPanelAnimationExImpl$statusBarStateListener$1);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            dumpManager.registerNormalDumpable("PanelAnimationLogBuffer", anonymousClass1);
        }
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, (CoroutineStart) null, new AnonymousClass2(null), 2);
    }

    public static final void access$doAnimationFrameSmooth(OplusPanelAnimationExImpl oplusPanelAnimationExImpl, SpringAnimation springAnimation, long j, long j2) {
        ReflectionUtils.setValue(DynamicAnimation.class, springAnimation, "mLastFrameTime", Long.valueOf(j - j2));
        springAnimation.doAnimationFrame(j);
    }

    public static final void access$expandedFractionEnd(OplusPanelAnimationExImpl oplusPanelAnimationExImpl) {
        LevelPositionAlgorithm levelPositionAlgorithm = oplusPanelAnimationExImpl.getLevelPositionAlgorithm();
        if (Intrinsics.areEqual(levelPositionAlgorithm != null ? Boolean.valueOf(levelPositionAlgorithm.isInPanelAnimation()) : null, Boolean.FALSE)) {
            oplusPanelAnimationExImpl.pullDownNotificationPanelAnimatorEnd();
        }
        if (oplusPanelAnimationExImpl.getPanelExpandFraction().getRawFraction() == 1.0f) {
            NotificationShadeRegionHelper.setTouchExclusionRegion(false, ((NotificationPanelViewController) oplusPanelAnimationExImpl.notificationPanelViewController.get()).getView().getRootView(), "notification expandedFractionEnd");
        }
    }

    public static final void access$refreshElementShowingState(OplusPanelAnimationExImpl oplusPanelAnimationExImpl, boolean z) {
        boolean z2 = oplusPanelAnimationExImpl.panelExpanded;
        boolean z3 = oplusPanelAnimationExImpl.isElementShowing;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("refreshStateForSeparateChange isSeparateMode: ", " panelExpanded: ", ", isElementShowing: ", z, z2);
        sbM.append(z3);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (oplusPanelAnimationExImpl.isElementShowing || z || !oplusPanelAnimationExImpl.panelExpanded) {
            return;
        }
        oplusPanelAnimationExImpl.setElementShowing(true);
    }

    private final int getGfxScene() {
        return SeparateModeHelper.INSTANCE.getSeparateMode() ? 6 : 1;
    }

    private final String getGfxSceneDes() {
        return SeparateModeHelper.INSTANCE.getSeparateMode() ? "pull down separate notification" : "pull down statusbar";
    }

    private final LevelPositionAlgorithm getLevelPositionAlgorithm() {
        return (LevelPositionAlgorithm) this.levelPositionAlgorithm$delegate.getValue();
    }

    private final boolean getPanelClosedOnDown() {
        NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) this.notificationPanelViewController.get();
        return notificationPanelViewController.mPanelClosedOrGoingCloseOnInterceptDown || notificationPanelViewController.mPanelClosedOnDown;
    }

    private final float getThresholdForElementAppearance() {
        return this.context.getResources().getDimension(2131173668);
    }

    public static void restrainNavigationBarInvertColor$default(OplusPanelAnimationExImpl oplusPanelAnimationExImpl, boolean z) {
        oplusPanelAnimationExImpl.isNBInvertColor = z;
        NotificationShadeDepthControllerEx notificationShadeDepthControllerEx = (NotificationShadeDepthControllerEx) oplusPanelAnimationExImpl.notificationShadeDepthControllerEx.get();
        if (notificationShadeDepthControllerEx != null) {
            notificationShadeDepthControllerEx.restrainNavigationBarInvertColor(1, z);
        }
    }

    private void setElementShowing(boolean z) {
        if (this.isElementShowing == z) {
            return;
        }
        this.isElementShowing = z;
        getElementShowState().notifyElementShowStateChange();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isElementShowing: ", this.isElementShowing), null, 8);
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isElementShowing: ", this.isElementShowing), (Throwable) null);
        }
    }

    private void setFirstElementShowing(boolean z) {
        if (this.isFirstElementShowing == z) {
            return;
        }
        this.isFirstElementShowing = z;
        getElementShowState().notifyFirstElementShowStateChange();
        getPanelExpandFraction().notifyFractionChange();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isFirstElementShowing: ", this.isFirstElementShowing), null, 8);
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isFirstElementShowing: ", this.isFirstElementShowing), (Throwable) null);
        }
    }

    private void setFixedElementShowing(boolean z) {
        if (this.isFixedElementShowing == z) {
            return;
        }
        this.isFixedElementShowing = z;
        getElementShowState().notifyFixedElementShowingChange();
        getPanelExpandFraction().notifyFractionChange();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isFixedElementShowing: ", this.isFixedElementShowing), null, 8);
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isFixedElementShowing: ", this.isFixedElementShowing), (Throwable) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFlingToHeight(boolean z) {
        if (this.isFlingToHeight == z) {
            return;
        }
        this.isFlingToHeight = z;
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isFlingToHeight: ", z), (Throwable) null);
        }
    }

    private final void setPanelExpanded(boolean z) {
        if (this.panelExpanded == z) {
            return;
        }
        this.panelExpanded = z;
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("panelExpanded: ", z), (Throwable) null);
        }
    }

    private void setShadeShowing(boolean z) {
        if (this.isShadeShowing == z) {
            return;
        }
        this.isShadeShowing = z;
        getElementShowState().notifyShadeShowingChange(this.statusBarStateController.getState(), z);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isShadeShowing: ", this.isShadeShowing), null, 8);
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isShadeShowing: ", this.isShadeShowing), (Throwable) null);
        }
    }

    public final float calculatePanelHeightWhenTouchMove(float f, float f2, boolean z) {
        return (!getUseLayeredAnimation() || z || this.statusBarStateController.getState() == 1 || f >= f2) ? f : Math.max(f, 1.0f);
    }

    public final void cancelKeyguardHAnimation() {
        getTranslationYValue().cancelKeyguardHAnimation();
    }

    public void expandedForKeyguardQs(boolean z, boolean z2, String str, float f, float f2, float f3) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("expandedForKeyguardQs isExpanded ", z), null, 8);
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return;
        }
        setElementShowing(z);
        getTranslationYValue().animateExpandTranslationY(getThresholdForTranslationY(), f, z, f2, f3, z2);
    }

    public void expandedForKeyguardQsImmediate(float f, boolean z, String str, float f2, float f3, float f4) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(f, "expandedForKeyguardQsImmediate expandHeight "), null, 8);
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return;
        }
        setElementShowing(f >= getThresholdForElementAppearance());
        expandedForKeyguardQs(this.isElementShowing, z, str, f2, f3, f4);
    }

    public final void expandedTo(float f, float f2) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "expandedTo targetValue " + f + " vel " + f2, null, 8);
        setFlingToHeight(true);
        getPanelExpandFraction().getRawFraction();
        getPanelExpandFraction().expandedTo(f);
        getTranslationYValue().expandedTo(f, f2, f > 0.0f);
        OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get();
        if (oplusSeparateNotificationAndQSEx != null) {
            oplusSeparateNotificationAndQSEx.onSeparateNotifyExpandChange(f > 0.0f);
        }
    }

    public final float getAdjustExpandedHeight(float f, int i) {
        return MathUtils.min((f * i) / this.distanceRange, i - 1);
    }

    public ElementShowState getElementShowState() {
        return (ElementShowState) this.elementShowState$delegate.getValue();
    }

    public float getEndMotionVel() {
        return super.getEndMotionVel();
    }

    public ValueAnimator getHeightAnimator(float f, float f2, float f3, NotificationPanelViewController notificationPanelViewController, int i) {
        int maxPanelTransitionDistance = notificationPanelViewController.getMaxPanelTransitionDistance();
        boolean zIsTracking = notificationPanelViewController.isTracking();
        int state = this.statusBarStateController.getState();
        float rawFraction = getPanelExpandFraction().getRawFraction();
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("getHeightAnimator isTracking ", state, " state ", " fixedDuration ", zIsTracking);
        RectList$.ExternalSyntheticOutline0.m(i, maxPanelTransitionDistance, " maxPanelTransitionDistance ", " targetHeight ", sbM);
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, " currentExpandedHeight ", f, " rawFraction ");
        sbM.append(rawFraction);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (this.statusBarStateController.getState() != 0 || i == 0 || maxPanelTransitionDistance == 0) {
            return super.getHeightAnimator(f, f2, f3, notificationPanelViewController, i);
        }
        float f4 = maxPanelTransitionDistance;
        OplusSpringAnimation oplusSpringAnimation = new OplusSpringAnimation(f2 / f4, getPanelExpandFraction().getRawFraction(), f / f4, this, f3, notificationPanelViewController);
        this.oplusSpringAnimation = oplusSpringAnimation;
        return oplusSpringAnimation;
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public PanelExpandedFraction getPanelExpandFraction() {
        return (PanelExpandedFraction) this.panelExpandFraction$delegate.getValue();
    }

    public float getThresholdForTranslationY() {
        Integer statusBarTopSpacing;
        if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return OplusQSFakeStatusController.Companion.getDefault_setting_translationy();
        }
        OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class);
        return ((OplusSimpleQSManagerImpl) this.oplusSimpleQSManagerImpl.get()).getHeaderContainerToStatusbarMargin((oplusSeparateNotificationAndQSEx == null || (statusBarTopSpacing = oplusSeparateNotificationAndQSEx.getStatusBarTopSpacing()) == null) ? this.context.getResources().getDimensionPixelSize(2131170599) : statusBarTopSpacing.intValue());
    }

    public PanelTranslationYValue getTranslationYValue() {
        return (PanelTranslationYValue) this.translationYValue$delegate.getValue();
    }

    public boolean getUseLayeredAnimation() {
        return FeatureOption.getUseLayeredPanelAnimation();
    }

    public final boolean isBreakCollapse() {
        return this.isBreakCollapse;
    }

    public final boolean isElementShowing() {
        return this.isElementShowing;
    }

    public final boolean isEndHeightAnimator(float f, boolean z) {
        OplusSpringAnimation oplusSpringAnimation;
        if (z && (oplusSpringAnimation = this.oplusSpringAnimation) != null && oplusSpringAnimation.isAnimationRunning) {
            return getPanelExpandFraction().getRawFraction() < 0.001f;
        }
        return super.isEndHeightAnimator(f, z);
    }

    public final boolean isFirstElementShowing() {
        return this.isFirstElementShowing;
    }

    public final boolean isFixedElementShowing() {
        return this.isFixedElementShowing;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x003e  */
    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    /* JADX WARN: Code duplicated, block: B:26:0x0070 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x0072  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a7  */
    public boolean isInterceptTouchEvent(float f, float f2, float f3, boolean z, NotificationStackScrollLayoutController notificationStackScrollLayoutController, boolean z2) {
        ExpandableView expandableView;
        SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
        boolean z3 = false;
        if (separateModeHelper.getSeparateMode() && this.statusBarStateController.getState() == 0) {
            if (notificationStackScrollLayoutController != null) {
                ExpandHelper expandHelper = notificationStackScrollLayoutController.mView.getExpandHelper();
                if (!expandHelper.mEnabled || !expandHelper.mWatchingForPull || (expandableView = expandHelper.mResizedView) == null || ExpandHelper.isFullyExpanded(expandableView)) {
                    if (LogUtil.panicEnabled) {
                        LogBuffer logBuffer = this.logBuffer;
                        LogLevel logLevel = LogLevel.DEBUG;
                        boolean separateMode = separateModeHelper.getSeparateMode();
                        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("isInterceptTouchEvent xDiff=", f, ", yDiff=", f2, ", touchSlop=");
                        sbM.append(f3);
                        sbM.append(", isNotificationOnTop=");
                        sbM.append(z);
                        sbM.append(", separateMode=");
                        logBuffer.log(TAG, logLevel, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, separateMode, ", isTouchInNotificationArea=", z2), (Throwable) null);
                    }
                    if (z2) {
                        if (z) {
                            if (f2 > f3 && f2 > Math.abs(f)) {
                                z3 = true;
                            }
                            if (z3 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(NotificationPanelViewController$.ExternalSyntheticOutline0.m("isInterceptTouchEvent result ", z3, " yDiff ", f2, " xDiff "), f, " touchSlop ", f3, "PanelAnimationEx-->"));
                            }
                            return z3;
                        }
                    } else if (Math.abs(f2) <= f3 && Math.abs(f2) > Math.abs(f)) {
                        return true;
                    }
                } else if (LogUtil.panicEnabled) {
                    this.logBuffer.log(TAG, LogLevel.DEBUG, "isInterceptTouchEvent canNotificationPullExpanded", (Throwable) null);
                    return false;
                }
            } else {
                if (LogUtil.panicEnabled) {
                    LogBuffer logBuffer2 = this.logBuffer;
                    LogLevel logLevel2 = LogLevel.DEBUG;
                    boolean separateMode2 = separateModeHelper.getSeparateMode();
                    StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("isInterceptTouchEvent xDiff=", f, ", yDiff=", f2, ", touchSlop=");
                    sbM2.append(f3);
                    sbM2.append(", isNotificationOnTop=");
                    sbM2.append(z);
                    sbM2.append(", separateMode=");
                    logBuffer2.log(TAG, logLevel2, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM2, separateMode2, ", isTouchInNotificationArea=", z2), (Throwable) null);
                }
                if (z2) {
                    if (z) {
                        if (f2 > f3) {
                            z3 = true;
                        }
                        if (z3) {
                            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(NotificationPanelViewController$.ExternalSyntheticOutline0.m("isInterceptTouchEvent result ", z3, " yDiff ", f2, " xDiff "), f, " touchSlop ", f3, "PanelAnimationEx-->"));
                        }
                        return z3;
                    }
                } else if (Math.abs(f2) <= f3) {
                }
            }
        }
        return false;
    }

    public boolean isNotInterceptTouchEvent(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        if (!((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnLockscreenNtfCenter() || notificationStackScrollLayoutController == null) {
            return false;
        }
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        if (notificationStackScrollLayout.mScrollAdapter.isScrolledToTop()) {
            return false;
        }
        NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayout.mScrollAdapter.this$0;
        return notificationStackScrollLayout2.getOwnScrollY() >= notificationStackScrollLayout2.getScrollRange();
    }

    public final boolean isRunningAnimation() {
        return getPanelExpandFraction().isRunningAnimation();
    }

    public final boolean isSeamlessRunning() {
        return getTranslationYValue().getKeyguardHAnimation().isRunning();
    }

    public final boolean isShadeShowing() {
        return this.isShadeShowing;
    }

    public final boolean isStarPullDown() {
        return this.isStarPullDown;
    }

    public void onTouchMoveForKeyguardQs(boolean z, float f, VelocityTracker velocityTracker, MotionEvent motionEvent) {
        if (!getUseLayeredAnimation() || motionEvent == null || SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return;
        }
        getTranslationYValue().updatePanelTranslationYForKeyguardQs(getThresholdForElementAppearance(), f, z);
        boolean z2 = false;
        if (!z ? f <= getThresholdForElementAppearance() : f >= getThresholdForElementAppearance()) {
            z2 = true;
        }
        setElementShowing(z2);
        if (LogUtil.panicEnabled) {
            LogBuffer logBuffer = this.logBuffer;
            LogLevel logLevel = LogLevel.DEBUG;
            boolean z3 = this.isElementShowing;
            boolean z4 = this.isBreakCollapse;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("onTouchMove, panelClosedOnDown=", z, ",moveDistanceOnDown=", f, ",velocityTracker=");
            sbM.append(velocityTracker);
            sbM.append(",isElementShowing=");
            sbM.append(z3);
            sbM.append(",isBreakCollapse=");
            sbM.append(z4);
            logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        }
    }

    public final void pullDownNotificationPanelAnimatorEnd() {
        boolean z;
        if (getPanelExpandFraction().getRawFraction() != 0.0f && (z = this.isStarPullDown)) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, OplusLockIconView$.ExternalSyntheticOutline0.m("pullDownAnimatorEnd, isStarPullDown ", " ", z), null, 8);
            setStarPullDown(false);
            int gfxScene = getGfxScene();
            String gfxSceneDes = getGfxSceneDes();
            JankUtils.gfxSceneEnd(this.context, new int[]{gfxScene});
            Trace.asyncTraceEnd(8L, gfxSceneDes, gfxScene);
            restrainNavigationBarInvertColor$default(this, false);
            ((NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class)).stopWaitAction(2);
            LogUtil.setLogBufferDisabled(false);
        }
    }

    public final void releaseNavigationBarInvertColor() {
        if (this.panelExpanded || !this.isNBInvertColor) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("releaseNavigationBarInvertColor valid~ isStarPullDown:", this.isStarPullDown), null, 8);
        restrainNavigationBarInvertColor$default(this, false);
    }

    public final void resetHeightAnimator() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "resetHeightAnimator oplusSpringAnimation", null, 8);
        ((QSPanelOverScrollAnimationEx) this.qSPanelOverScrollAnimationEx.get()).resetTouchState();
        OplusSpringAnimation oplusSpringAnimation = this.oplusSpringAnimation;
        if (oplusSpringAnimation != null) {
            oplusSpringAnimation.removeCallback();
        }
        this.oplusSpringAnimation = null;
        setFlingToHeight(false);
    }

    public void setBreakCollapse(boolean z) {
        if (this.isBreakCollapse == z) {
            return;
        }
        this.isBreakCollapse = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isBreakCollapse ", z), null, 8);
        if (LogUtil.panicEnabled) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("isBreakCollapse: ", this.isBreakCollapse), (Throwable) null);
        }
    }

    public void setCurShadeExpanded(boolean z) {
        setPanelExpanded(z);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setCurShadeExpanded isPanelExpanded ", z), null, 8);
        if (z) {
            return;
        }
        this.hasResetNotificationRowsTranslationOnPullDown = false;
        LevelPositionAlgorithm levelPositionAlgorithm = getLevelPositionAlgorithm();
        if (levelPositionAlgorithm != null) {
            LevelPositionAlgorithmImpl levelPositionAlgorithmImpl = (LevelPositionAlgorithmImpl) levelPositionAlgorithm;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "LevelPositionAlgorithmImpl", Protection$.ExternalSyntheticOutline0.m(levelPositionAlgorithmImpl.currentFraction, "onPanelCollapsed currentFraction ="), null, 8);
            if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
                levelPositionAlgorithmImpl.getSeparatePanelAnimation().resetSpringAnimation();
            } else {
                levelPositionAlgorithmImpl.currentFraction = 0.0f;
            }
        }
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        if (notificationStackScrollLayoutExt != null) {
            ((NotificationStackScrollLayoutExtImpl) notificationStackScrollLayoutExt).resetSpringChain();
        }
        SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
        if (!separateModeHelper.getSeparateMode()) {
            QSFragmentHelper.getInstance().onQsClosingEnd();
        }
        restrainNavigationBarInvertColor$default(this, false);
        LogUtil.setLogBufferDisabled(false);
        ViewGroup view = ((NotificationPanelViewController) this.notificationPanelViewController.get()).getView();
        if (view == null || !separateModeHelper.getSeparateMode() || this.statusBarStateController.getState() != 0 || view.getTranslationX() == 0.0f) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(view.getTranslationX(), "setCurShadeExpanded translationX ", " reset"), null, 8);
        view.setTranslationX(0.0f);
    }

    public void setEndMotionVel(float f) {
        super.setEndMotionVel(f);
    }

    public void setKeyguardMiniChange(boolean z) {
        getTranslationYValue().setKeyguardMiniChange(z);
    }

    public void setKeyguardStartValue(float f) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(f, "keyguardHAnimation.setKeyguardStartValue:"), null, 8);
        getTranslationYValue().getKeyguardHAnimation().setStartValue(f);
    }

    public void setStarPullDown(boolean z) {
        this.isStarPullDown = z;
    }

    public void setupPanelCloseAnimationDuration(Animator animator) {
        if (!getUseLayeredAnimation() || this.statusBarStateController.getState() == 1) {
            return;
        }
        long jAbs = (long) ((Math.abs(0.0f - getPanelExpandFraction().getRawFraction()) / 1.0f) * 350.0f);
        if (jAbs < 200) {
            jAbs = 200;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(jAbs, "setupPanelCloseAnimationDuration, duration=", "PanelAnimationEx-->", "SystemUi--Notification");
        }
        animator.setDuration(jAbs);
    }

    public final boolean shouldExpandedWhenNoFling(float f) {
        if (!getUseLayeredAnimation()) {
            return super.shouldExpandedWhenNoFling(f);
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "shouldExpandedWhenNoFling: expandedFraction=" + f + ", isElementShowing=" + this.isElementShowing + ", isFirstElementShowing=" + this.isFirstElementShowing, null, 8);
        return (SeparateModeHelper.INSTANCE.getSeparateMode() || this.statusBarStateController.getState() == 0) ? this.isFirstElementShowing : this.isElementShowing;
    }

    public final boolean shouldSnapHideAfterCollapse() {
        if (this.isElementShowing) {
            return false;
        }
        if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
            boolean z = getPanelExpandFraction().getRawFraction() == 0.0f;
            if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "PanelAnimationEx-->unifiedMode shouldSnapHideAfterCollapse snapHide.");
            }
            return z;
        }
        if (getPanelExpandFraction().getRawFraction() > 0.05f) {
            return false;
        }
        boolean zIsFullyCollapsed = ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).isFullyCollapsed();
        boolean z2 = !zIsFullyCollapsed;
        if (!zIsFullyCollapsed && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "PanelAnimationEx-->separateMode shouldSnapHideAfterCollapse snapHide.");
        }
        return z2;
    }

    public final boolean shouldUpdateFlingOnLayout(boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("shouldUpdateFlingOnLayout, mUpdateFlingOnLayout:", ", expand=", "PanelAnimationEx-->", z, z2));
        }
        if (getUseLayeredAnimation()) {
            return z && z2;
        }
        return z;
    }

    public final void starPullDownNotificationPanel() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "starPullDownNotificationPanel", null, 8);
        setStarPullDown(true);
        if (!this.hasResetNotificationRowsTranslationOnPullDown) {
            this.hasResetNotificationRowsTranslationOnPullDown = true;
            for (ExpandableNotificationRow expandableNotificationRow : CollectionsKt___CollectionsKt.toList(((NotificationStackScrollLayout) this.nssl.get()).getVisibleChildren())) {
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
                if (expandableNotificationRow2 != null) {
                    NotificationEntry entry = expandableNotificationRow2.getEntry();
                    String key = entry != null ? entry.getKey() : null;
                    ExpandableViewState viewState = expandableNotificationRow2.getViewState();
                    float xTranslation = viewState != null ? viewState.getXTranslation() : 0.0f;
                    if (expandableNotificationRow2.getTranslationX() != 0.0f) {
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "reset notification translationX key=" + key + " value=" + expandableNotificationRow2.getTranslationX(), null, 8);
                        expandableNotificationRow2.setTranslationX(0.0f);
                    }
                    if (xTranslation != 0.0f) {
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "reset notification viewState.xTranslation key=" + key + " value=" + xTranslation, null, 8);
                        expandableNotificationRow2.getViewState().setXTranslation(0.0f);
                    }
                }
            }
        }
        LogUtil.setLogBufferDisabled(true);
        getTranslationYValue().getTranslationYAnimation().setStartValue(-getThresholdForTranslationY());
        getTranslationYValue().setTranslationYValue(-getThresholdForTranslationY());
        getTranslationYValue().notifyTranslationYChange();
        this.oplusGcSupressionExt.callGcSupression(3, 3000);
        int gfxScene = getGfxScene();
        String gfxSceneDes = getGfxSceneDes();
        restrainNavigationBarInvertColor$default(this, true);
        ((NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class)).startWaitAction(2);
        JankUtils.gfxSceneEnd(this.context, new int[]{40, 42});
        Trace.asyncTraceBegin(8L, gfxSceneDes, gfxScene);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0057  */
    public final void updateElementShowing(float f, float f2) {
        boolean z = true;
        setFirstElementShowing(this.statusBarStateController.getState() == 0 && (getPanelClosedOnDown() || this.isBreakCollapse ? !(!getPanelExpandFraction().isQuicklyElementShow ? f >= 0.5f : f >= 0.2f) : f >= 0.86f));
        setFixedElementShowing(this.statusBarStateController.getState() == 0 && f > 0.05f);
        if (this.statusBarStateController.getState() != 0) {
            z = false;
        } else if (!getPanelExpandFraction().isQuicklyElementShow || !getPanelClosedOnDown()) {
            boolean z2 = f2 > 0.0f && f >= f2;
            if ((!this.isElementShowing || !z2) && f < 0.86f) {
                z = false;
            }
        } else if (f < 0.2f) {
            z = false;
        }
        setElementShowing(z);
    }

    public final void updateKeyguardTranslationY() {
        PanelTranslationYValue translationYValue = getTranslationYValue();
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
        translationYValue.updateKeyguardTranslationY(keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded, this.isElementShowing);
    }

    /* JADX WARN: Code duplicated, block: B:59:0x0147  */
    /* JADX WARN: Code duplicated, block: B:65:0x0160  */
    public final void updatePanelAnimatorFraction(float f, boolean z, boolean z2, float f2) {
        boolean z3;
        boolean z4;
        boolean z5;
        KeyguardStateControllerImpl keyguardStateControllerImpl;
        OplusSpringAnimation oplusSpringAnimation;
        LevelPositionAlgorithm.LevelPosition reboundAnimation;
        LevelPositionAlgorithm.LevelPosition separatePanelAnimation;
        int i = 0;
        int i2 = 1;
        if (!this.hasAddExpandFractionCallback) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onTouchMove addCallback", null, 8);
            getPanelExpandFraction().addCallback((PanelAnimationEx.ExpandedFractionChangeListener) this.expandedFractionChangeListener);
            LevelPositionAlgorithm levelPositionAlgorithm = getLevelPositionAlgorithm();
            if (levelPositionAlgorithm != null && (separatePanelAnimation = levelPositionAlgorithm.getSeparatePanelAnimation()) != null) {
                C01941 c01941 = new C01941(i);
                c01941.this$0 = this;
                VarHandle.storeStoreFence();
                separatePanelAnimation.addCallback(c01941);
            }
            LevelPositionAlgorithm levelPositionAlgorithm2 = getLevelPositionAlgorithm();
            if (levelPositionAlgorithm2 != null && (reboundAnimation = levelPositionAlgorithm2.getReboundAnimation()) != null) {
                C01941 c01942 = new C01941(i2);
                c01942.this$0 = this;
                VarHandle.storeStoreFence();
                reboundAnimation.addCallback(c01942);
            }
            this.hasAddExpandFractionCallback = true;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (LogUtil.panicEnabled) {
            LogBuffer logBuffer = this.logBuffer;
            LogLevel logLevel = LogLevel.DEBUG;
            boolean panelClosedOnDown = getPanelClosedOnDown();
            OplusSpringAnimation oplusSpringAnimation2 = this.oplusSpringAnimation;
            Boolean boolValueOf = oplusSpringAnimation2 != null ? Boolean.valueOf(oplusSpringAnimation2.isAnimationRunning) : null;
            Float finalPosition = getPanelExpandFraction().getFinalPosition();
            boolean z6 = getTranslationYValue().isExpandAnimationRunning;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("updatePanelAnimatorFraction, panelClosedOnDown=", panelClosedOnDown, ",finalFraction=", fCoerceIn, ",oplusSpringAnimation?.isRunning=");
            sbM.append(boolValueOf);
            sbM.append(",panelExpandFraction.finalPosition ");
            sbM.append(finalPosition);
            sbM.append(" isSpringTransition ");
            KeyguardLogger$.ExternalSyntheticOutline0.m("  translationYValue.isExpandAnimationRunning ", " needHandleOverLongFirstFrame=", sbM, z, z6);
            sbM.append(z2);
            logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        }
        if (f == 0) {
            setKeyguardStartValue(((NotificationPanelViewController) this.notificationPanelViewController.get()).getQsController().getMinExpansionHeight());
        }
        if (z || (oplusSpringAnimation = this.oplusSpringAnimation) == null || !oplusSpringAnimation.isAnimationRunning) {
            if (fCoerceIn > 0.6f && getPanelClosedOnDown() && SeparateModeHelper.INSTANCE.getSeparateMode()) {
                if (Math.abs(f2) >= 1000.0f && !getPanelExpandFraction().isQuicklyElementShow) {
                    getPanelExpandFraction().setQuicklyElementShow(true);
                }
                if (f2 <= -1000.0f || f2 >= -400.0f) {
                    z3 = false;
                } else {
                    z3 = false;
                    getPanelExpandFraction().setQuicklyElementShow(false);
                }
            } else {
                z3 = false;
                getPanelExpandFraction().setQuicklyElementShow(false);
            }
            PanelExpandedFraction panelExpandFraction = getPanelExpandFraction();
            getPanelClosedOnDown();
            panelExpandFraction.updateFraction(fCoerceIn, z);
        } else {
            z3 = false;
        }
        if (z) {
            z4 = true;
            PanelTranslationYValue translationYValue = getTranslationYValue();
            z5 = z3;
            boolean panelClosedOnDown2 = getPanelClosedOnDown();
            keyguardStateControllerImpl = this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing || keyguardStateControllerImpl.mOccluded) {
                z4 = z5;
            }
            translationYValue.updatePanelTranslationY(panelClosedOnDown2, z, fCoerceIn, z4, this.isElementShowing);
        } else if (!getTranslationYValue().isExpandAnimationRunning) {
            OplusSpringAnimation oplusSpringAnimation3 = this.oplusSpringAnimation;
            if (oplusSpringAnimation3 != null) {
                z4 = true;
                if (!oplusSpringAnimation3.isAnimationRunning) {
                }
            } else {
                z4 = true;
            }
            PanelTranslationYValue translationYValue2 = getTranslationYValue();
            z5 = z3;
            boolean panelClosedOnDown3 = getPanelClosedOnDown();
            keyguardStateControllerImpl = this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing) {
                z4 = z5;
            } else {
                z4 = z5;
            }
            translationYValue2.updatePanelTranslationY(panelClosedOnDown3, z, fCoerceIn, z4, this.isElementShowing);
        }
        if (z2) {
            View view = (View) this.nssl.get();
            OplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1 oplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1 = new OplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1();
            oplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1.this$0 = this;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(view, oplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1);
        }
    }

    public void updateVisibility(boolean z, View view) {
        ViewRootImpl viewRootImpl;
        ArrayList<WeakReference<RenderBooster>> arrayListBoosterViews;
        List list;
        if (this.isShadeShowing != z && !z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "PanelAnimationEx-->[updateVisibility] isShadeShowing=false, requestStop renderBooster.");
            }
            if (view != null && (viewRootImpl = view.getViewRootImpl()) != null && (arrayListBoosterViews = RenderBooster.Companion.boosterViews(viewRootImpl)) != null && (list = CollectionsKt___CollectionsKt.toList(arrayListBoosterViews)) != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    RenderBooster renderBooster = (RenderBooster) ((WeakReference) it.next()).get();
                    if (renderBooster != null) {
                        renderBooster.requestStop(0);
                    }
                }
            }
            OplusPhysicsPropertyCache.INSTANCE.clear();
        }
        setShadeShowing(z);
        if (z) {
            if (view == null || view.getVisibility() != 0) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updateVisibility alpha " + (view != null ? Float.valueOf(view.getAlpha()) : null) + " scaleX " + (view != null ? Float.valueOf(view.getScaleX()) : null) + " scaleY " + (view != null ? Float.valueOf(view.getScaleY()) : null), null, 8);
                if (view != null) {
                    view.setAlpha(1.0f);
                }
                if (view != null) {
                    view.setScaleX(1.0f);
                }
                if (view != null) {
                    view.setScaleY(1.0f);
                }
            }
        }
    }

    public void onTouchMoveForKeyguardQs(float f, Function2 function2) {
        if (getUseLayeredAnimation() && !SeparateModeHelper.INSTANCE.getSeparateMode()) {
            getTranslationYValue().setMblock(function2);
            getTranslationYValue().updateKeyguardQs(f);
        }
    }
}
