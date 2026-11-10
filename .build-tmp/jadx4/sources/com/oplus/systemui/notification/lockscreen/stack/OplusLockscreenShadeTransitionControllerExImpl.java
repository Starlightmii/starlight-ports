package com.oplus.systemui.notification.lockscreen.stack;

import android.content.Context;
import android.util.IndentingPrintWriter;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.core.view.insets.Protection$;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.keyguard.IKeyguardTipsViewController;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.LockscreenNotificationCenterAnimBreakerEx;
import com.android.systemui.statusbar.LockscreenNotificationCenterAnimBreakerExImpl;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.kotlin.ActivatableFlowDumperImpl;
import com.android.systemui.util.kotlin.FlowDumper;
import com.android.systemui.util.kotlin.UtilsKt;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.statusbar.LockScreenNotificationDispatcherImp;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController;
import com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLockscreenShadeTransitionControllerExImpl extends OplusLockscreenShadeTransitionControllerEx implements FlowDumper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusLockscreenShadeTransitionControllerExImpl";
    public final /* synthetic */ ActivatableFlowDumperImpl $$delegate_0;
    private final MutableStateFlow _isKgNtfCenterExpanded;
    private final MutableStateFlow _lockscreenAnimFraction;
    private final MutableStateFlow _notificationCenterAnimFraction;
    private LockscreenNotificationCenterAnimBreakerExImpl animBreaker;
    private final Set<Runnable> animationFinishedRunnables;
    private final Context context;
    public int endPadding;
    private ExpandHelper.Callback expandCallback;
    private final Lazy fixedContext$delegate;
    public float gotoLockscreenNtfVelocity;
    public long gotoLockscreenNtfVelocitySetTime;
    public float gotoLockscreenVelocity;
    private final LockScreenNotificationDispatcherImp lockScreenNotificationDispatcher;
    private final LogBuffer logBuffer;
    public float notificationDragAmount;
    private final dagger.Lazy notificationMenuPanelControllerLazy;
    private NotificationPanelViewController notificationPanelViewController;
    private NotificationStackScrollLayoutController nsslController;
    public int ntfTopPaddingInLockscreen;
    private final dagger.Lazy oplusCustomPopupMenuControllerLazy;
    private final dagger.Lazy oplusKeyguardTipsViewController;
    public boolean pendingGotoLockscreen;
    public float rawFraction;
    private final COUISpringAnimationWrapper rawFractionAnimation;
    public int startPadding;
    private final SysuiStatusBarStateController statusBarStateController;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements StatusBarStateController.StateListener {
        public /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

        public final void onStateChanged(int i) {
            OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl = this.this$0;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusLockscreenShadeTransitionControllerExImpl.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onStateChanged "), null, 8);
            if (i != 1) {
                oplusLockscreenShadeTransitionControllerExImpl.setNotificationDragAmount(0.0f);
            }
            OplusLockscreenShadeTransitionControllerExImpl.access$resetAnimation(oplusLockscreenShadeTransitionControllerExImpl);
            oplusLockscreenShadeTransitionControllerExImpl.setGotoLockscreenVelocity(0.0f);
            oplusLockscreenShadeTransitionControllerExImpl.setGotoLockscreenNtfVelocity(0.0f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class COUISpringAnimationWrapper {
        public float finalPosition;
        private final COUISpringAnimation springAnimation;

        public COUISpringAnimationWrapper(COUISpringAnimation cOUISpringAnimation, float f) {
            this.springAnimation = cOUISpringAnimation;
            this.finalPosition = f;
        }

        public final void animateToFinalPosition(float f) {
            this.finalPosition = f;
            this.springAnimation.animateToFinalPosition(f);
        }

        public final boolean canSkipToEnd() {
            return this.springAnimation.canSkipToEnd();
        }

        public final float getFinalPosition() {
            return this.finalPosition;
        }

        public final boolean isRunning() {
            return ((COUIDynamicAnimation) this.springAnimation).mRunning;
        }

        public final void skipToEnd() {
            this.springAnimation.skipToEnd();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Context $r8$lambda$lnxRPRf_apDLEcJ9ujdag5maev4(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl) {
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, oplusLockscreenShadeTransitionControllerExImpl.context, false, 6);
        return contextUpdateDisplayConfiguration$default == null ? oplusLockscreenShadeTransitionControllerExImpl.context : contextUpdateDisplayConfiguration$default;
    }

    public OplusLockscreenShadeTransitionControllerExImpl(SysuiStatusBarStateController sysuiStatusBarStateController, Context context, LogBuffer logBuffer, LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp, dagger.Lazy lazy, dagger.Lazy lazy2, dagger.Lazy lazy3, DumpManager dumpManager) {
        this.$$delegate_0 = new ActivatableFlowDumperImpl(dumpManager, TAG);
        this.statusBarStateController = sysuiStatusBarStateController;
        this.context = context;
        this.logBuffer = logBuffer;
        this.lockScreenNotificationDispatcher = lockScreenNotificationDispatcherImp;
        this.notificationMenuPanelControllerLazy = lazy;
        this.oplusCustomPopupMenuControllerLazy = lazy2;
        this.oplusKeyguardTipsViewController = lazy3;
        OplusLockscreenShadeTransitionControllerExImpl$$ExternalSyntheticLambda0 oplusLockscreenShadeTransitionControllerExImpl$$ExternalSyntheticLambda0 = new OplusLockscreenShadeTransitionControllerExImpl$$ExternalSyntheticLambda0();
        oplusLockscreenShadeTransitionControllerExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.fixedContext$delegate = LazyKt__LazyJVMKt.lazy(oplusLockscreenShadeTransitionControllerExImpl$$ExternalSyntheticLambda0);
        this._lockscreenAnimFraction = dumpValue(StateFlowKt.MutableStateFlow(Float.valueOf(1.0f)), "lockscreenAnimFraction");
        this._notificationCenterAnimFraction = dumpValue(StateFlowKt.MutableStateFlow(Float.valueOf(0.0f)), "notificationCenterAnimFraction");
        this.animationFinishedRunnables = new HashSet();
        this._isKgNtfCenterExpanded = dumpValue(StateFlowKt.MutableStateFlow(Boolean.FALSE), "isKgNtfCenterExpanded");
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        this.startPadding = notificationStackScrollLayoutController != null ? notificationStackScrollLayoutController.getTopPadding() : getNtfTopPaddingInLockscreen();
        this.endPadding = getNtfTopPaddingInLockscreenNtfCenter();
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.28f));
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(0.001f);
        OplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$1 oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$1 = new OplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$1();
        oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$1);
        OplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2 oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2 = new OplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2();
        oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(oplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2);
        refreshNotificationPadding();
        this.rawFractionAnimation = new COUISpringAnimationWrapper(cOUISpringAnimation, 0.0f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusLockscreenShadeTransitionControllerExImpl-->init");
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass2);
    }

    public static final void access$resetAnimation(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl) {
        if (oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 1) {
            if (oplusLockscreenShadeTransitionControllerExImpl.rawFraction == 0.0f && !oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.isRunning()) {
                return;
            }
            oplusLockscreenShadeTransitionControllerExImpl.animateLockscreenNotificationCenter(false);
            if (oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.canSkipToEnd()) {
                oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.skipToEnd();
            }
        }
        if (oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 2) {
            if (oplusLockscreenShadeTransitionControllerExImpl.rawFraction == 1.0f && !oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.isRunning()) {
                return;
            }
            oplusLockscreenShadeTransitionControllerExImpl.animateLockscreenNotificationCenter(true);
            if (oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.canSkipToEnd()) {
                oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.skipToEnd();
            }
        }
        if (oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 0) {
            if (oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.canSkipToEnd()) {
                oplusLockscreenShadeTransitionControllerExImpl.rawFractionAnimation.skipToEnd();
            }
            oplusLockscreenShadeTransitionControllerExImpl.setRawFraction(0.0f);
        }
    }

    public static final void access$runAnimationFinishedRunnables(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl) {
        Iterator<Runnable> it = oplusLockscreenShadeTransitionControllerExImpl.animationFinishedRunnables.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        oplusLockscreenShadeTransitionControllerExImpl.animationFinishedRunnables.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCurrentState() {
        return this.statusBarStateController.getState();
    }

    private final Context getFixedContext() {
        return (Context) this.fixedContext$delegate.getValue();
    }

    private void setGotoLockscreenNtfVelocitySetTime(long j) {
        if (this.gotoLockscreenNtfVelocitySetTime != j) {
            this.gotoLockscreenNtfVelocitySetTime = j;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(this.gotoLockscreenNtfVelocitySetTime, "set gotoLockscreenNtfVelocitySetTime to ", "OplusLockscreenShadeTransitionControllerExImpl-->", "SystemUi--Notification");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGotoLockscreenVelocity(float f) {
        if (this.gotoLockscreenVelocity == f) {
            return;
        }
        float fAbs = Math.abs(f);
        if (fAbs > 20000.0f) {
            fAbs = 20000.0f;
        }
        this.gotoLockscreenVelocity = fAbs;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set gotoLockscreenVelocity to ", this.gotoLockscreenVelocity, "OplusLockscreenShadeTransitionControllerExImpl-->", "SystemUi--Notification");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRawFraction(float f) {
        if (this.rawFraction == f) {
            return;
        }
        this.rawFraction = f;
        LogBuffer logBuffer = this.logBuffer;
        LogLevel logLevel = LogLevel.DEBUG;
        int i = this.startPadding;
        int i2 = this.endPadding;
        float notificationDragAmount = getNotificationDragAmount();
        int currentState = getCurrentState();
        StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, i, "onFractionUpdate fraction ", ", startPadding ", ", endPadding ");
        IconProvider$.ExternalSyntheticOutline0.m(notificationDragAmount, i2, ", dragUpAmount ", " currentState ", sbM);
        sbM.append(currentState);
        logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        this._lockscreenAnimFraction.setValue(Float.valueOf(1.0f - f));
        this._notificationCenterAnimFraction.setValue(Float.valueOf(f));
    }

    public final void animateLockscreenNotificationCenter(boolean z) {
        Object value;
        List<ExpandableNotificationRow> visibleChildren;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController;
        float f = z ? 1.0f : 0.0f;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("animateLockscreenNotificationCenter ", z), null, 8);
        this.rawFractionAnimation.animateToFinalPosition(f);
        ((IKeyguardTipsViewController) this.oplusKeyguardTipsViewController.get()).onStatusBarStateAnimtionStart(z);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.nsslController;
        boolean z2 = true;
        if (notificationStackScrollLayoutController2 != null) {
            NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController2.mView;
            if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
                notificationStackScrollLayout.mEverythingNeedsAnimation = true;
                notificationStackScrollLayout.mNeedsAnimation = true;
                notificationStackScrollLayout.requestChildrenUpdate$1();
            }
        }
        NotificationPanelViewController notificationPanelViewController = this.notificationPanelViewController;
        if (notificationPanelViewController != null) {
            notificationPanelViewController.requestScrollerTopPaddingUpdate();
        }
        if (z && (notificationStackScrollLayoutController = this.nsslController) != null) {
            notificationStackScrollLayoutController.mView.updateTopPadding(getNtfTopPaddingInLockscreenNtfCenter(), true);
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = this.nsslController;
        if (notificationStackScrollLayoutController3 != null) {
            if ((getCurrentState() != 1 || isAnimatingToExpand()) && !isAnimatingToCollapse()) {
                z2 = false;
            }
            Log.i("StackScrollerController", "notifyAnimStart onKeyguard " + z2);
            if (z2) {
                notificationStackScrollLayoutController3.mGroupExpansionManager.collapseGroups();
                Log.i("StackScrollerController", "resetAllUserOperateState");
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController3.mView;
                ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_NOTIFICATION, "StackScroller", new StringBuilder("resetAllUserOperateState mOnScrollToTop "), notificationStackScrollLayout2.mOnScrollToTop);
                if (!notificationStackScrollLayout2.mOnScrollToTop) {
                    notificationStackScrollLayout2.resetScrollPosition();
                }
                notificationStackScrollLayout2.clearTemporaryViews();
                for (int i = 0; i < notificationStackScrollLayout2.getChildCount(); i++) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableView) notificationStackScrollLayout2.getChildAt(i);
                    if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                        expandableNotificationRow.setUserSwipingToExpandRow(false);
                    }
                }
                notificationStackScrollLayout2.resetAllSwipeState();
                NotificationStackScrollLayout view = ((NotificationStackScrollLayoutExtImpl) notificationStackScrollLayout2.mExt).getView();
                if (view != null && (visibleChildren = view.getVisibleChildren()) != null) {
                    for (ExpandableNotificationRow expandableNotificationRow2 : visibleChildren) {
                        ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow2 instanceof ExpandableNotificationRow ? expandableNotificationRow2 : null;
                        if (expandableNotificationRow3 != null && !expandableNotificationRow3.mIsSummaryWithChildren && expandableNotificationRow3.mHasUserChangedExpansion && expandableNotificationRow3.isExpanded(false)) {
                            expandableNotificationRow3.setUserExpanded(false);
                        }
                    }
                }
            }
        }
        StateFlowImpl stateFlowImpl = this._isKgNtfCenterExpanded;
        do {
            value = stateFlowImpl.getValue();
            ((Boolean) value).getClass();
        } while (!stateFlowImpl.compareAndSet(value, Boolean.valueOf(z)));
    }

    public final boolean backToKgFirstWhenTouchOnKgNtfCenter() {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController;
        NotificationStackScrollLayout view;
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt;
        return (!((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnLockscreenNtfCenter() || (notificationStackScrollLayoutController = this.nsslController) == null || (view = notificationStackScrollLayoutController.getView()) == null || (notificationStackScrollLayoutExt = view.mExt) == null || notificationStackScrollLayoutExt.getScrollYWhenDown() != 0) ? false : true;
    }

    public final void clearConflictAnimationRunnable() {
        this.animationFinishedRunnables.clear();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        this.$$delegate_0.dump(printWriter, strArr);
    }

    public void dumpFlows(IndentingPrintWriter indentingPrintWriter) {
        this.$$delegate_0.dumpFlows(indentingPrintWriter);
    }

    public <T, F extends SharedFlow> F dumpReplayCache(F f, String str) {
        return (F) this.$$delegate_0.dumpReplayCache(f, str);
    }

    public <T, F extends StateFlow> F dumpValue(F f, String str) {
        return (F) this.$$delegate_0.dumpValue(f, str);
    }

    public <T> Flow dumpWhileCollecting(Flow flow, String str) {
        return this.$$delegate_0.dumpWhileCollecting(flow, str);
    }

    public void executeRunnableAnimationFinish(Runnable runnable) {
        this.animationFinishedRunnables.add(runnable);
    }

    public LockscreenNotificationCenterAnimBreakerEx getAnimBreaker() {
        LockscreenNotificationCenterAnimBreakerExImpl lockscreenNotificationCenterAnimBreakerExImpl;
        LockscreenNotificationCenterAnimBreakerExImpl lockscreenNotificationCenterAnimBreakerExImpl2 = this.animBreaker;
        if (lockscreenNotificationCenterAnimBreakerExImpl2 != null) {
            return lockscreenNotificationCenterAnimBreakerExImpl2;
        }
        synchronized (this) {
            lockscreenNotificationCenterAnimBreakerExImpl = this.animBreaker;
            if (lockscreenNotificationCenterAnimBreakerExImpl == null) {
                lockscreenNotificationCenterAnimBreakerExImpl = new LockscreenNotificationCenterAnimBreakerExImpl(this.context, this, this.logBuffer);
                this.animBreaker = lockscreenNotificationCenterAnimBreakerExImpl;
            }
        }
        return lockscreenNotificationCenterAnimBreakerExImpl;
    }

    public float getAnimFinalPosition() {
        return this.rawFractionAnimation.getFinalPosition();
    }

    public float getGotoLockscreenNtfVelocity() {
        return this.gotoLockscreenNtfVelocity;
    }

    public long getGotoLockscreenNtfVelocitySetTime() {
        return this.gotoLockscreenNtfVelocitySetTime;
    }

    public float getGotoLockscreenVelocity() {
        return this.gotoLockscreenVelocity;
    }

    public StateFlow getLockscreenAnimFraction() {
        return FlowKt.asStateFlow(this._lockscreenAnimFraction);
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public StateFlow getNotificationCenterAnimFraction() {
        return FlowKt.asStateFlow(this._notificationCenterAnimFraction);
    }

    public float getNotificationDragAmount() {
        return this.notificationDragAmount;
    }

    public int getNtfTopPaddingInLockscreen() {
        return this.ntfTopPaddingInLockscreen;
    }

    public int getNtfTopPaddingInLockscreenNtfCenter() {
        return getFixedContext().getResources().getDimensionPixelSize(2131172953);
    }

    public void gotoKeyguardState(boolean z, String str, float f) {
        gotoState(z, 1, str, f);
    }

    public void gotoShadeLockState(boolean z, String str, float f) {
        gotoState(z, 2, str, f);
    }

    public final void gotoState(boolean z, int i, String str, float f) {
        if (i == 1) {
            setGotoLockscreenVelocity(f);
        }
        if (i == 2) {
            setGotoLockscreenNtfVelocity(f);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int currentState = getCurrentState();
            boolean zIsRunning = this.rawFractionAnimation.isRunning();
            boolean z2 = !this.lockScreenNotificationDispatcher.getNotificationListOnKg().isEmpty();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("gotoState ", currentState, i, " -> ", ", animate ");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", anim is running? ", ", from ", sbM, z, zIsRunning);
            sbM.append(str);
            sbM.append(",  hasNotifications ");
            sbM.append(z2);
            Log.d("SystemUi--Notification", "OplusLockscreenShadeTransitionControllerExImpl-->".concat(sbM.toString()));
        }
        if (i == 1) {
            if (getCurrentState() != 2 || this.rawFractionAnimation.isRunning()) {
                return;
            }
            tryGotoLockscreenState();
            return;
        }
        if (i == 2 && getCurrentState() == 1 && !this.rawFractionAnimation.isRunning()) {
            JankUtils.gfxSceneBegin$default(this.context, 81, "Swipe up to enter the notification center", 4L);
            if (getCurrentState() != 2) {
                refreshNotificationPadding();
                animateLockscreenNotificationCenter(true);
            }
            JankUtils.gfxSceneEnd(this.context, new int[]{81});
        }
    }

    public final boolean isAnimRunning() {
        return this.rawFractionAnimation.isRunning();
    }

    public final boolean isAnimatingToCollapse() {
        return this.rawFractionAnimation.isRunning() && this.rawFractionAnimation.getFinalPosition() == 0.0f;
    }

    public final boolean isAnimatingToExpand() {
        return this.rawFractionAnimation.isRunning() && this.rawFractionAnimation.getFinalPosition() == 1.0f;
    }

    public StateFlow isKgNtfCenterExpanded() {
        return this._isKgNtfCenterExpanded;
    }

    public final boolean isOnLockscreenNtfCenter() {
        return getCurrentState() == 2;
    }

    public final boolean isOnOrGoingKeyguard(boolean z) {
        return (z && !isAnimatingToExpand()) || isAnimatingToCollapse();
    }

    public final boolean isOnOrGoingOrLeavingNtfCenter() {
        return isOnLockscreenNtfCenter() || isAnimatingToExpand() || isAnimatingToCollapse();
    }

    public void notifyBreakAnim(String str, float f) {
        if (this.rawFractionAnimation.isRunning()) {
            setNotificationDragAmount(0.0f);
            this.pendingGotoLockscreen = false;
            StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(this.rawFractionAnimation.getFinalPosition(), getCurrentState(), "breakAnimation current state ", " current finalPosition ", " from ");
            sbM.append(str);
            sbM.append(" velocity ");
            sbM.append(f);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
            if (this.rawFractionAnimation.getFinalPosition() == 1.0f) {
                setGotoLockscreenVelocity(f);
                animateLockscreenNotificationCenter(false);
            } else if (this.rawFractionAnimation.getFinalPosition() == 0.0f) {
                setGotoLockscreenNtfVelocity(f);
                animateLockscreenNotificationCenter(true);
            }
        }
    }

    public final void onOverscrollTopChanged() {
        NotificationStackScrollLayout view;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        int ownScrollY = (notificationStackScrollLayoutController == null || (view = notificationStackScrollLayoutController.getView()) == null) ? 0 : view.getOwnScrollY();
        if (ownScrollY < 0 || ownScrollY > UtilsKt.toPx(40, this.context) || !this.pendingGotoLockscreen) {
            return;
        }
        tryGotoLockscreenState();
    }

    public final void refreshNotificationPadding() {
        this.startPadding = getNtfTopPaddingInLockscreen();
        this.endPadding = getNtfTopPaddingInLockscreenNtfCenter();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(getCurrentState(), "OplusLockscreenShadeTransitionControllerExImpl-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("refreshNotificationPadding startPadding ", this.startPadding, this.endPadding, ", endPadding ", ", currentState "));
        }
    }

    public void setGotoLockscreenNtfVelocity(float f) {
        if (this.gotoLockscreenNtfVelocity == f) {
            return;
        }
        float fAbs = Math.abs(f);
        if (fAbs > 20000.0f) {
            fAbs = 20000.0f;
        }
        this.gotoLockscreenNtfVelocity = fAbs;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set gotoLockscreenNtfVelocity to ", this.gotoLockscreenNtfVelocity, "OplusLockscreenShadeTransitionControllerExImpl-->", "SystemUi--Notification");
        }
        setGotoLockscreenNtfVelocitySetTime(System.currentTimeMillis());
    }

    public void setNotificationDragAmount(float f) {
        if (this.notificationDragAmount == f) {
            return;
        }
        this.notificationDragAmount = f;
        this.logBuffer.log(TAG, LogLevel.DEBUG, "set notificationDragAmount: " + f + " statusBarStateController.state: " + this.statusBarStateController.getState(), (Throwable) null);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        if (notificationStackScrollLayoutController != null) {
            notificationStackScrollLayoutController.mView.mAnimateNextTopPaddingChange = true;
        }
        NotificationPanelViewController notificationPanelViewController = this.notificationPanelViewController;
        if (notificationPanelViewController != null) {
            notificationPanelViewController.requestScrollerTopPaddingUpdate();
        }
    }

    public void setNotificationPanelController(NotificationPanelViewController notificationPanelViewController) {
        this.notificationPanelViewController = notificationPanelViewController;
    }

    public void setNtfTopPaddingInLockscreen(int i) {
        if (this.ntfTopPaddingInLockscreen != i) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "set keyguardNotificationStaticPaddingTop to "), (Throwable) null);
            this.ntfTopPaddingInLockscreen = i;
        }
    }

    public void setStackScroller(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        NotificationStackScrollLayout view;
        this.nsslController = notificationStackScrollLayoutController;
        this.expandCallback = notificationStackScrollLayoutController.getExpandHelperCallback();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.nsslController;
        if (notificationStackScrollLayoutController2 == null || (view = notificationStackScrollLayoutController2.getView()) == null) {
            return;
        }
        RepeatWhenAttachedKt.repeatWhenAttached$default(view, (CoroutineContext) null, new OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1(this, null), 3);
    }

    public final void tryGotoLockscreenState() {
        NotificationStackScrollLayout view;
        NotificationStackScrollLayout view2;
        NotificationStackScrollLayout view3;
        NotificationStackScrollLayout view4;
        if (getCurrentState() == 1 || this.rawFractionAnimation.isRunning()) {
            return;
        }
        if (this.pendingGotoLockscreen) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusLockscreenShadeTransitionControllerExImpl-->tryGotoLockscreenState has pending task!");
            }
            refreshNotificationPadding();
            animateLockscreenNotificationCenter(false);
            this.pendingGotoLockscreen = false;
            return;
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        if (notificationStackScrollLayoutController != null && (view4 = notificationStackScrollLayoutController.getView()) != null && view4.mOnScrollToTop) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusLockscreenShadeTransitionControllerExImpl-->tryGotoLockscreenState is scrolling to top now!");
                return;
            }
            return;
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.nsslController;
        Integer numValueOf = (notificationStackScrollLayoutController2 == null || (view3 = notificationStackScrollLayoutController2.getView()) == null) ? null : Integer.valueOf(view3.getOwnScrollY());
        NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = this.nsslController;
        if (notificationStackScrollLayoutController3 != null) {
            notificationStackScrollLayoutController3.mView.scrollToTop();
        }
        if (((NotificationStackScrollLayoutExtImpl) ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class))).isRunningReboundAnimator()) {
            ((NotificationStackScrollLayoutExtImpl) ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class))).cancelScrollAnimator();
        }
        NotificationPopupMenuController.dismissPopupMenu$default((NotificationPopupMenuController) this.notificationMenuPanelControllerLazy.get(), "GotoLockscreenState", false, 4);
        OplusCustomPopupMenuController.dismissPopupMenu$default((OplusCustomPopupMenuController) this.oplusCustomPopupMenuControllerLazy.get(), "GotoLockscreenState", false, 4);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController4 = this.nsslController;
        if (notificationStackScrollLayoutController4 == null || (view = notificationStackScrollLayoutController4.getView()) == null || !view.mOnScrollToTop) {
            refreshNotificationPadding();
            animateLockscreenNotificationCenter(false);
            return;
        }
        this.pendingGotoLockscreen = true;
        setGotoLockscreenVelocity(numValueOf != null ? numValueOf.intValue() / 0.25f : 0.0f);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(getGotoLockscreenVelocity(), "gotoLockscreenVelocity "), null, 8);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController5 = this.nsslController;
        if (notificationStackScrollLayoutController5 == null || (view2 = notificationStackScrollLayoutController5.getView()) == null) {
            return;
        }
        tryGotoLockscreenState.3 r1 = new tryGotoLockscreenState.3();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        view2.setFinishScrollingCallback(r1);
    }
}
