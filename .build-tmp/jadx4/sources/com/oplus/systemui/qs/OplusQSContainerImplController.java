package com.oplus.systemui.qs;

import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.TransCalculator;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.IQSContainerImpl;
import com.android.systemui.qs.IQSContainerImplController;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.qs.QSNode;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.RepeatableExecutor;
import com.android.systemui.util.time.SystemClock;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.panelanimation.LevelPositionAlgorithmImpl;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController;
import com.oplus.systemui.qs.media.OplusQsMediaControllerExImpl;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1;
import com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController extends ViewController implements ExpandableNode.OnStateListener, Dumpable, IQSContainerImplController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSContainerImplController";
    private final NotificationPanelChildAppearAnimationManager appearAnimationManager;
    private final OplusQSContainerImplController$elementShowStateChangeListener$1 elementShowStateChangeListener;
    private final FalsingManager falsingManager;
    public final boolean isLowGaussianLevel;
    private final RepeatableExecutor mBgExecutor;
    private ConfigurationController mConfigurationController;
    private final ConfigurationController.ConfigurationListener mConfigurationListener;
    private final DialogTransitionAnimator mDialogLaunchAnimator;
    public float mExpansion;
    private FalsingManager mFalsingManager;
    private final OplusQSTileMediaContainerController mMediaQSTileMediaContainerController;
    private final CopyOnWriteArrayList<QSNode> mNodes;
    private final IObserverListener mObserverListener;
    private final PanelAnimationEx mPanelAnimationEx;
    public float mPanelExpandedFraction;
    public float mPanelHeight;
    private final PanelAnimationEx.PanelTranslationYChangeListener mPanelTranslationYChangeListener;
    private final OplusQsColorManagerEx.QsColorStateObserver mQsColorStateObserver;
    private OplusQSPanelContainerController mQsPanelContainerController;
    private QSPanelController mQsPanelController;
    private OplusQuickStatusBarHeaderController mQuickStatusBarHeaderController;
    private final SystemClock mSystemClock;
    private final OplusQSFakeStatusController oplusQSFakeStatusController;
    private final OplusQsColorManager oplusQsColorManager;
    private final OplusQsMediaControllerExImpl oplusQsMediaControllerEx;
    private final PanelAnimationEx.ExpandedFractionChangeListener panelExpandFractionListener;
    private final SysuiStatusBarStateController statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSContainerImplController$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusQsMediaControllerExImpl.IMediaModeChangeListener {
        public /* synthetic */ OplusQSContainerImplController this$0;

        public final void onChanged(boolean z) {
            OplusNotificationLogger.logMediaPanel(OplusQSContainerImplController.TAG, "setQsMediaPanelShowState: onChanged isMediaMode=" + z);
            OplusQSContainerImplController oplusQSContainerImplController = this.this$0;
            ((OplusQSContainerImpl) ((ViewController) oplusQSContainerImplController).mView).setQsMediaPanelShown(z);
            oplusQSContainerImplController.mMediaQSTileMediaContainerController.setQsMediaPanelShown(z);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSContainerImplController$onViewAttached$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnLayoutChangeListener {
        public /* synthetic */ OplusQSContainerImplController this$0;

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.this$0.updatePosition$2();
            OplusQSContainerImplController.access$setViewTranslationY(this.this$0);
        }
    }

    public static Unit $r8$lambda$boQAW8Fm_hNmZLMw28rwI8ZFJ0M(OplusQSContainerImplController oplusQSContainerImplController, float f, float f2) {
        for (QSNode qSNode : oplusQSContainerImplController.mNodes) {
            if (!(qSNode instanceof OplusQuickStatusBarHeaderController) && !(qSNode instanceof OplusQSFooterViewController) && !qSNode.isRemoved()) {
                oplusQSContainerImplController.updateNodeState(qSNode, f, f2);
            }
        }
        return Unit.INSTANCE;
    }

    public OplusQSContainerImplController(OplusQSContainerImpl oplusQSContainerImpl, QSPanelController qSPanelController, OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController, OplusQSFakeStatusController oplusQSFakeStatusController, OplusQSPanelContainerController oplusQSPanelContainerController, ConfigurationController configurationController, FalsingManager falsingManager, DialogTransitionAnimator dialogTransitionAnimator, RepeatableExecutor repeatableExecutor, SystemClock systemClock, OplusQsColorManager oplusQsColorManager, OplusQSTileMediaContainerController oplusQSTileMediaContainerController, OplusQsMediaControllerExImpl oplusQsMediaControllerExImpl, PanelAnimationEx panelAnimationEx, SysuiStatusBarStateController sysuiStatusBarStateController) {
        super(oplusQSContainerImpl);
        this.oplusQSFakeStatusController = oplusQSFakeStatusController;
        this.falsingManager = falsingManager;
        this.mDialogLaunchAnimator = dialogTransitionAnimator;
        this.mBgExecutor = repeatableExecutor;
        this.mSystemClock = systemClock;
        this.oplusQsColorManager = oplusQsColorManager;
        this.mMediaQSTileMediaContainerController = oplusQSTileMediaContainerController;
        this.oplusQsMediaControllerEx = oplusQsMediaControllerExImpl;
        this.mPanelAnimationEx = panelAnimationEx;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.mQsPanelController = qSPanelController;
        this.mQuickStatusBarHeaderController = oplusQuickStatusBarHeaderController;
        this.mQsPanelContainerController = oplusQSPanelContainerController;
        this.mConfigurationController = configurationController;
        this.mFalsingManager = falsingManager;
        this.mNodes = new CopyOnWriteArrayList<>();
        this.appearAnimationManager = NotificationPanelChildAppearAnimationManager.Companion.create(true);
        this.isLowGaussianLevel = ScrimUtil.isLowGaussianLevel(oplusQSContainerImpl.getContext());
        OplusQSContainerImplController$mConfigurationListener$1 oplusQSContainerImplController$mConfigurationListener$1 = new OplusQSContainerImplController$mConfigurationListener$1();
        oplusQSContainerImplController$mConfigurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mConfigurationListener = oplusQSContainerImplController$mConfigurationListener$1;
        OplusQSContainerImplController$mPanelTranslationYChangeListener$1 oplusQSContainerImplController$mPanelTranslationYChangeListener$1 = new OplusQSContainerImplController$mPanelTranslationYChangeListener$1();
        oplusQSContainerImplController$mPanelTranslationYChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPanelTranslationYChangeListener = oplusQSContainerImplController$mPanelTranslationYChangeListener$1;
        OplusQSContainerImplController$mQsColorStateObserver$1 oplusQSContainerImplController$mQsColorStateObserver$1 = new OplusQSContainerImplController$mQsColorStateObserver$1();
        oplusQSContainerImplController$mQsColorStateObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mQsColorStateObserver = oplusQSContainerImplController$mQsColorStateObserver$1;
        OplusQSContainerImplController$elementShowStateChangeListener$1 oplusQSContainerImplController$elementShowStateChangeListener$1 = new OplusQSContainerImplController$elementShowStateChangeListener$1();
        oplusQSContainerImplController$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = oplusQSContainerImplController$elementShowStateChangeListener$1;
        OplusQSContainerImplController$panelExpandFractionListener$1 oplusQSContainerImplController$panelExpandFractionListener$1 = new OplusQSContainerImplController$panelExpandFractionListener$1();
        oplusQSContainerImplController$panelExpandFractionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panelExpandFractionListener = oplusQSContainerImplController$panelExpandFractionListener$1;
        oplusQSContainerImpl.setController(this);
        TemporarilyInfoManager companion = TemporarilyInfoManager.Companion.getInstance();
        companion.setQsRootView(oplusQSContainerImpl);
        companion.setStatusBarContainerView((ViewGroup) oplusQSContainerImpl.requireViewById(2131363334));
        companion.setQsQuickEntranceContainerView((ViewGroup) oplusQSContainerImpl.requireViewById(2131364763));
        companion.setStarted(false);
        if (panelAnimationEx.getUseLayeredAnimation()) {
            companion.setStarted(1.0f == panelAnimationEx.getPanelExpandFraction().getRawFraction());
        } else {
            companion.setStarted(true);
        }
        companion.clearViewPool();
        oplusQSFakeStatusController.init();
        OplusQSContainerImplController$mObserverListener$1 oplusQSContainerImplController$mObserverListener$1 = new OplusQSContainerImplController$mObserverListener$1();
        oplusQSContainerImplController$mObserverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mObserverListener = oplusQSContainerImplController$mObserverListener$1;
    }

    public static final void access$setViewTranslationY(OplusQSContainerImplController oplusQSContainerImplController) {
        if (oplusQSContainerImplController.statusBarStateController.getCurrentOrUpcomingState() == 2 || LargeScreenUtils.shouldUseSplitNotificationShade(oplusQSContainerImplController.getResources())) {
            if (((OplusQSContainerImpl) ((ViewController) oplusQSContainerImplController).mView).getTranslationY() != 0.0f && oplusQSContainerImplController.mPanelAnimationEx.getTranslationYValue().getTranslationYValue() == 0.0f) {
                ((OplusQSContainerImpl) ((ViewController) oplusQSContainerImplController).mView).setTranslationY(0.0f);
                return;
            }
            return;
        }
        float thresholdForTranslationY = oplusQSContainerImplController.mPanelAnimationEx.getThresholdForTranslationY();
        View view = ((ViewController) oplusQSContainerImplController).mView;
        if (thresholdForTranslationY <= 0.0f) {
            ((OplusQSContainerImpl) view).setTranslationY(-oplusQSContainerImplController.mPanelAnimationEx.getThresholdForTranslationY());
        } else {
            ((OplusQSContainerImpl) view).setTranslationY(oplusQSContainerImplController.mPanelAnimationEx.getTranslationYValue().getTranslationYValue());
            oplusQSContainerImplController.oplusQSFakeStatusController.reverseTranslationY(oplusQSContainerImplController.mPanelAnimationEx.getTranslationYValue().getTranslationYValue());
        }
    }

    public void attchQsNode(QSNode qSNode) {
        this.mNodes.add(qSNode);
        qSNode.setContainer(this);
    }

    public final int calcExpandMeasureHeightExceptQsPanel() {
        float bottomGap;
        float f = 0.0f;
        for (QSNode qSNode : this.mNodes) {
            View view = qSNode.getView();
            if (view.getVisibility() != 8) {
                if (view.equals(this.mQsPanelContainerController.getView())) {
                    bottomGap = qSNode.getBottomGap() + qSNode.getTopGap(1.0f);
                } else if (!qSNode.shouldHideInSplitMode() || !LargeScreenUtils.shouldUseSplitNotificationShade(getResources())) {
                    bottomGap = qSNode.getBottomGap() + qSNode.getTopGap(1.0f) + view.getMeasuredHeight();
                }
                f += bottomGap;
            }
        }
        return (int) f;
    }

    public final float calcExpansionHeight(float f) {
        float bottomGap = 0.0f;
        for (QSNode qSNode : this.mNodes) {
            if (!qSNode.isRemoved()) {
                bottomGap += qSNode.getBottomGap() + qSNode.getTopGap(f) + ((int) MathUtils.lerp(qSNode.getCollapseHeight(), qSNode.getExpandHeight(), f));
            }
        }
        return bottomGap;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusQSContainerImplController: ");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    mExpansion: ", this.mExpansion, printWriter);
        CopyOnWriteArrayList<QSNode> copyOnWriteArrayList = this.mNodes;
        dump.1 r0 = new dump.1();
        r0.$pw = printWriter;
        r0.$args = strArr;
        VarHandle.storeStoreFence();
        copyOnWriteArrayList.forEach(r0);
    }

    public IQSContainerImpl getView() {
        return ((ViewController) this).mView;
    }

    public final void onViewAttached() {
        this.mPanelAnimationEx.getPanelExpandFraction().addCallback(this.panelExpandFractionListener);
        this.mConfigurationController.addCallback(this.mConfigurationListener);
        this.mPanelAnimationEx.getTranslationYValue().addCallback(this.mPanelTranslationYChangeListener);
        this.oplusQsColorManager.addQsColorStateObserver(this.mQsColorStateObserver);
        this.mPanelAnimationEx.getElementShowState().addCallback(this.elementShowStateChangeListener);
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isMediaShowSupport) {
            OplusNotificationLogger.logMediaPanel(TAG, "onViewAttached isMediaShowSupport");
            ((OplusQSContainerImpl) ((ViewController) this).mView).setQsMediaPanelShown(true);
            this.mMediaQSTileMediaContainerController.setQsMediaPanelShown(true);
        } else {
            OplusQsMediaControllerExImpl oplusQsMediaControllerExImpl = this.oplusQsMediaControllerEx;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQsMediaControllerExImpl.setMediaModeChangeListener(anonymousClass1);
        }
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.mObserverListener);
        OplusQSContainerImpl oplusQSContainerImpl = (OplusQSContainerImpl) ((ViewController) this).mView;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        oplusQSContainerImpl.addOnLayoutChangeListener(anonymousClass2);
    }

    public final void onViewDetached() {
        this.mPanelAnimationEx.getPanelExpandFraction().removeCallback(this.panelExpandFractionListener);
        this.mConfigurationController.removeCallback(this.mConfigurationListener);
        this.mPanelAnimationEx.getTranslationYValue().removeCallback(this.mPanelTranslationYChangeListener);
        this.oplusQsColorManager.removeQsColorStateObserver(this.mQsColorStateObserver);
        this.mPanelAnimationEx.getElementShowState().removeCallback(this.elementShowStateChangeListener);
        QSFeatureOption.INSTANCE.getClass();
        if (!QSFeatureOption.isMediaShowSupport) {
            this.oplusQsMediaControllerEx.setMediaModeChangeListener(null);
        }
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.mObserverListener);
    }

    public final void runAnimation(QSNode qSNode, boolean z, boolean z2, long j, boolean z3, Function2 function2) {
        View animatorView = qSNode.getAnimatorView();
        if (animatorView == null) {
            animatorView = qSNode.getView();
        }
        View view = animatorView;
        NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager = this.appearAnimationManager;
        PanelAnimationEx panelAnimationEx = this.mPanelAnimationEx;
        if (z) {
            ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager).show(view, z2, j, 0, (120 & 16) != 0 ? false : z3, (120 & 32) != 0 ? 0.0f : panelAnimationEx.getEndMotionVel(), new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1(), function2);
        } else {
            NotificationPanelChildAppearAnimationManager.hide$default(notificationPanelChildAppearAnimationManager, view, z2, panelAnimationEx.getEndMotionVel(), function2, 44);
        }
    }

    public void setCustomizing(boolean z) {
        Iterator<QSNode> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().setCustomizing(z);
        }
    }

    public void setExpanded(boolean z) {
        Iterator<QSNode> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().setExpanded(z);
        }
    }

    public void setExpansion(float f) {
        this.mExpansion = f;
        Iterator<QSNode> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().setExpansion(f);
        }
        updatePosition$2();
    }

    public void setListening(boolean z) {
        Iterator<QSNode> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().setListening(z);
        }
    }

    public final void updateNodeState(QSNode qSNode, float f, float f2) {
        qSNode.updateViewState(f, f2);
        if ((qSNode instanceof OplusQSPanelContainerController ? (OplusQSPanelContainerController) qSNode : null) != null) {
            this.oplusQSFakeStatusController.updateViewState(f, f2);
        }
    }

    public final void updatePanelExpansion(float f, boolean z) {
        Object next;
        this.mPanelExpandedFraction = f;
        boolean z2 = (this.mPanelAnimationEx.isElementShowing() && f > 0.0f) || z;
        boolean z3 = f > 0.0f;
        int floorCount = 0;
        for (QSNode qSNode : this.mNodes) {
            if (!qSNode.isRemoved()) {
                floorCount += qSNode.getFloorCount();
            }
        }
        if (f == 0.0f) {
            ((LevelPositionAlgorithmImpl) ((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class))).qsViewsSize = floorCount;
        }
        if (this.isLowGaussianLevel) {
            Iterator<T> it = this.mNodes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                QSNode qSNode2 = (QSNode) next;
                if (!(qSNode2 instanceof OplusQuickStatusBarHeaderController) && !(qSNode2 instanceof OplusQSFooterViewController) && !qSNode2.isRemoved()) {
                    break;
                }
            }
            QSNode qSNode3 = (QSNode) next;
            if (qSNode3 != null) {
                OplusQSContainerImplController$$ExternalSyntheticLambda0 oplusQSContainerImplController$$ExternalSyntheticLambda0 = new OplusQSContainerImplController$$ExternalSyntheticLambda0();
                oplusQSContainerImplController$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                runAnimation(qSNode3, z2, z3, 0L, true, oplusQSContainerImplController$$ExternalSyntheticLambda0);
                return;
            }
        }
        boolean z4 = true;
        int i = 0;
        for (QSNode qSNode4 : this.mNodes) {
            if ((qSNode4 instanceof OplusQuickStatusBarHeaderController) || (qSNode4 instanceof OplusQSFooterViewController)) {
                i++;
            } else if (!qSNode4.isRemoved()) {
                OplusQSContainerImplController$$ExternalSyntheticLambda1 oplusQSContainerImplController$$ExternalSyntheticLambda1 = new OplusQSContainerImplController$$ExternalSyntheticLambda1();
                oplusQSContainerImplController$$ExternalSyntheticLambda1.f$0 = this;
                oplusQSContainerImplController$$ExternalSyntheticLambda1.f$1 = qSNode4;
                VarHandle.storeStoreFence();
                runAnimation(qSNode4, z2, z3, 16 * ((long) i), z4, oplusQSContainerImplController$$ExternalSyntheticLambda1);
                i++;
                z4 = false;
            }
        }
    }

    public final void updatePosition$2() {
        float space;
        float paddingWithIndex;
        boolean zShouldUseSplitNotificationShade = LargeScreenUtils.shouldUseSplitNotificationShade(getResources());
        float bottomGap = 0.0f;
        int floorCount = 0;
        for (QSNode qSNode : this.mNodes) {
            if (qSNode.isRemoved()) {
                qSNode.getView().setTranslationY(0.0f);
            } else {
                float topGap = qSNode.getTopGap(this.mExpansion) + bottomGap;
                if (zShouldUseSplitNotificationShade) {
                    space = 0.0f;
                    paddingWithIndex = 0.0f;
                } else {
                    TransCalculator transCalculator = TransCalculator.INSTANCE;
                    float f = this.mPanelHeight;
                    transCalculator.getClass();
                    space = TransCalculator.getSpace(f, floorCount);
                    paddingWithIndex = ((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class)).getPaddingWithIndex(floorCount, 0, (View) null);
                }
                float f2 = topGap + space + paddingWithIndex;
                OplusQSContainerImplController$$ExternalSyntheticLambda2 oplusQSContainerImplController$$ExternalSyntheticLambda2 = new OplusQSContainerImplController$$ExternalSyntheticLambda2();
                oplusQSContainerImplController$$ExternalSyntheticLambda2.f$0 = zShouldUseSplitNotificationShade;
                oplusQSContainerImplController$$ExternalSyntheticLambda2.f$1 = this;
                VarHandle.storeStoreFence();
                float translationY = qSNode.setTranslationY(f2, floorCount, oplusQSContainerImplController$$ExternalSyntheticLambda2);
                if (qSNode instanceof OplusQuickStatusBarHeaderController) {
                    this.oplusQSFakeStatusController.setTranslationY(f2, floorCount, (Function1) null);
                }
                if (qSNode instanceof OplusQSFooterViewController) {
                    this.oplusQSFakeStatusController.setLeftTranslationY(f2, this.mExpansion);
                }
                floorCount += qSNode.getFloorCount();
                bottomGap = qSNode.getBottomGap() + ((int) MathUtils.lerp(qSNode.getCollapseHeight(), qSNode.getExpandHeight(), this.mExpansion)) + space + translationY + paddingWithIndex + topGap;
            }
        }
    }
}
