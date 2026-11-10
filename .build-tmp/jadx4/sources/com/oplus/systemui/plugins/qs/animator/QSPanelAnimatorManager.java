package com.oplus.systemui.plugins.qs.animator;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.OplusQSRootViewComponent$qsElementEndListener$1;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSLeftBTilesComponent;
import com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent$;
import com.oplus.systemui.plugins.qs.quickentrance.ui.view.OplusQSQuickEntranceContainerView;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelAnimatorManager implements QSScenarioAnimListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSPanelAnimatorManager";
    private final QSPanelAnimatorManager$elementAttachStateChangeListener$1 elementAttachStateChangeListener;
    private QSPanelExpandFraction.ElementEndListener elementEndListener;
    public final boolean isLowGaussianLevel;
    private Function0 numChangeCancelAction;
    private final OplusQSQuickEntranceContainerView oplusQSQuickEntranceContainerView;
    private final List<IQSExpandOrCollapseAnimation> qsComponents;
    private final Map<View, QSElementAnimator> qsElementAnimators;
    private final QSPanelAnimatorManager$qsElementShowingChangeListener$1 qsElementShowingChangeListener;
    private final Lazy qsElementsChain$delegate;
    private final QSPanelExpandFraction qsPanelExpandFraction;
    private final QSPanelTranslationY qsPanelTranslationY;
    private final OplusQSRootView qsRootView;
    private final CopyOnWriteArraySet<QSScenarioAnimListener> qsScenarioAnimListeners;
    private final Lazy qsScenarioPullAnimator$delegate;
    private Function0 springEndAction;
    private final OplusSeparateQSPlugin.IStub stub;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager$initNodes$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IQSExpandOrCollapseAnimation.QSNodeChangeListener, IQSExpandOrCollapseAnimation.QSNodePageSelectedListener {
        public /* synthetic */ QSPanelAnimatorManager this$0;

        public void onNodeNumChanged() {
            QSPanelAnimatorManager qSPanelAnimatorManager = this.this$0;
            LogUtil.i$default(LogUtil.TAG_QS, QSPanelAnimatorManager.TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("[onNodeNumChanged] qsRootView.isVisible:", " fRunning=", qSPanelAnimatorManager.qsRootView.getVisibility() == 0, qSPanelAnimatorManager.qsPanelExpandFraction.isFractionAnimationRunning()), null, 8);
            Function0 numChangeCancelAction = qSPanelAnimatorManager.getNumChangeCancelAction();
            if (numChangeCancelAction != null) {
                numChangeCancelAction.invoke();
            }
            qSPanelAnimatorManager.getQsElementsChain().initSpringListener();
            QSPanelAnimatorManager.access$updateTileStatus(qSPanelAnimatorManager);
            if (qSPanelAnimatorManager.qsRootView.getVisibility() == 0) {
                qSPanelAnimatorManager.updatePivotXY(false);
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$-oVsziBAGGMqXw7kDhaInRpLPoA, reason: not valid java name */
    public static Unit m119$r8$lambda$oVsziBAGGMqXw7kDhaInRpLPoA(QSPanelAnimatorManager qSPanelAnimatorManager) {
        QSPanelExpandFraction.ElementEndListener elementEndListener = qSPanelAnimatorManager.elementEndListener;
        if (elementEndListener != null) {
            ((OplusQSRootViewComponent$qsElementEndListener$1) elementEndListener).elementAnimatorEnd();
        }
        return Unit.INSTANCE;
    }

    public static QsElementsChain $r8$lambda$D1er78yuiWQgQtG8ss92rRYaL58(QSPanelAnimatorManager qSPanelAnimatorManager, ViewGroup viewGroup, FrameLayout frameLayout) {
        return new QsElementsChain(qSPanelAnimatorManager.qsRootView, viewGroup, frameLayout, qSPanelAnimatorManager.springEndAction);
    }

    public static QSScenarioPullAnimator $r8$lambda$fEBJH4_MnA6AIYPXYACgWAjYQl8(QSPanelAnimatorManager qSPanelAnimatorManager) {
        return new QSScenarioPullAnimator(qSPanelAnimatorManager, qSPanelAnimatorManager.stub);
    }

    public static Unit $r8$lambda$mwvaL_eZDLR46siEk_fgd9dqfQw(QSPanelAnimatorManager qSPanelAnimatorManager, View view, float f) {
        qSPanelAnimatorManager.qsComponents.get(1).updateState(0, 0, f, view.getScaleX(), -1.0f);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$z1tA4rGCyIGPEsQkBLISqRem65s(QSPanelAnimatorManager qSPanelAnimatorManager, View view, float f) {
        qSPanelAnimatorManager.qsComponents.get(1).updateState(0, 0, view.getTransitionAlpha(), f, -1.0f);
        return Unit.INSTANCE;
    }

    public QSPanelAnimatorManager(OplusQSRootView oplusQSRootView, OplusQSQuickEntranceContainerView oplusQSQuickEntranceContainerView, OplusSeparateQSPlugin.IStub iStub, ViewGroup viewGroup, FrameLayout frameLayout) {
        this.qsRootView = oplusQSRootView;
        this.oplusQSQuickEntranceContainerView = oplusQSQuickEntranceContainerView;
        this.stub = iStub;
        QSPanelAnimatorManager$$ExternalSyntheticLambda2 qSPanelAnimatorManager$$ExternalSyntheticLambda2 = new QSPanelAnimatorManager$$ExternalSyntheticLambda2(1);
        qSPanelAnimatorManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsScenarioPullAnimator$delegate = LazyKt__LazyJVMKt.lazy(qSPanelAnimatorManager$$ExternalSyntheticLambda2);
        QSPanelAnimatorManager$$ExternalSyntheticLambda5 qSPanelAnimatorManager$$ExternalSyntheticLambda5 = new QSPanelAnimatorManager$$ExternalSyntheticLambda5();
        qSPanelAnimatorManager$$ExternalSyntheticLambda5.f$0 = this;
        qSPanelAnimatorManager$$ExternalSyntheticLambda5.f$1 = viewGroup;
        qSPanelAnimatorManager$$ExternalSyntheticLambda5.f$2 = frameLayout;
        VarHandle.storeStoreFence();
        this.qsElementsChain$delegate = LazyKt__LazyJVMKt.lazy(qSPanelAnimatorManager$$ExternalSyntheticLambda5);
        this.qsPanelTranslationY = new QSPanelTranslationY(oplusQSRootView);
        this.isLowGaussianLevel = ScrimUtil.isLowGaussianLevel(oplusQSRootView.getContext());
        QSPanelAnimatorManager$$ExternalSyntheticLambda2 qSPanelAnimatorManager$$ExternalSyntheticLambda3 = new QSPanelAnimatorManager$$ExternalSyntheticLambda2(2);
        qSPanelAnimatorManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        QSPanelExpandFraction qSPanelExpandFraction = new QSPanelExpandFraction(iStub, qSPanelAnimatorManager$$ExternalSyntheticLambda3);
        this.qsPanelExpandFraction = qSPanelExpandFraction;
        this.qsElementAnimators = new LinkedHashMap();
        this.qsComponents = new ArrayList();
        QSPanelAnimatorManager$elementAttachStateChangeListener$1 qSPanelAnimatorManager$elementAttachStateChangeListener$1 = new QSPanelAnimatorManager$elementAttachStateChangeListener$1();
        qSPanelAnimatorManager$elementAttachStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementAttachStateChangeListener = qSPanelAnimatorManager$elementAttachStateChangeListener$1;
        this.qsScenarioAnimListeners = new CopyOnWriteArraySet<>();
        QSPanelAnimatorManager$qsElementShowingChangeListener$1 qSPanelAnimatorManager$qsElementShowingChangeListener$1 = new QSPanelAnimatorManager$qsElementShowingChangeListener$1();
        qSPanelAnimatorManager$qsElementShowingChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsElementShowingChangeListener = qSPanelAnimatorManager$qsElementShowingChangeListener$1;
        if (!oplusQSRootView.isAttachedToWindow()) {
            QSPanelAnimatorManager$special$$inlined$doOnAttach$1 qSPanelAnimatorManager$special$$inlined$doOnAttach$1 = new QSPanelAnimatorManager$special$$inlined$doOnAttach$1();
            qSPanelAnimatorManager$special$$inlined$doOnAttach$1.$this_doOnAttach = oplusQSRootView;
            qSPanelAnimatorManager$special$$inlined$doOnAttach$1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSRootView.addOnAttachStateChangeListener(qSPanelAnimatorManager$special$$inlined$doOnAttach$1);
            return;
        }
        qSPanelExpandFraction.addCallback(qSPanelAnimatorManager$qsElementShowingChangeListener$1);
        if (oplusQSRootView.isAttachedToWindow()) {
            oplusQSRootView.addOnAttachStateChangeListener(new QSPanelAnimatorManager$special$$inlined$doOnAttach$1(oplusQSRootView, this));
            return;
        }
        qSPanelExpandFraction.removeCallback(qSPanelAnimatorManager$qsElementShowingChangeListener$1);
        qSPanelExpandFraction.cancel();
        getQsElementsChain().cancelSpring();
    }

    public static final void access$updateTileStatus(QSPanelAnimatorManager qSPanelAnimatorManager) {
        if (qSPanelAnimatorManager.qsRootView.getVisibility() == 0) {
            QSPanelExpandFraction qSPanelExpandFraction = qSPanelAnimatorManager.qsPanelExpandFraction;
            qSPanelAnimatorManager.setSecondElementVisible(qSPanelAnimatorManager.qsPanelExpandFraction.isSecondElementVisible, qSPanelExpandFraction.isSecondElementVisible && qSPanelExpandFraction.isFractionAnimationRunning() && !qSPanelAnimatorManager.isLowGaussianLevel);
        }
        Iterator<T> it = qSPanelAnimatorManager.qsComponents.iterator();
        while (it.hasNext()) {
            ((IQSExpandOrCollapseAnimation) it.next()).resetNonCurrPageToExpanded();
        }
    }

    public static /* synthetic */ void animateToWithProxyLogic$default(QSPanelAnimatorManager qSPanelAnimatorManager, View view, boolean z, boolean z2, Function2 function2, Function0 function0, float f, float f2, OplusQSQuickEntranceComponent$.ExternalSyntheticLambda9 externalSyntheticLambda9, int i) {
        if ((i & 32) != 0) {
            f = 0.0f;
        }
        if ((i & 64) != 0) {
            f2 = z ? 0.2f : 0.15f;
        }
        float f3 = (i & 128) == 0 ? 0.16f : 0.0f;
        if ((i & 256) != 0) {
            externalSyntheticLambda9 = null;
        }
        qSPanelAnimatorManager.animateToWithProxyLogic(view, z, z2, function2, function0, f, f2, f3, externalSyntheticLambda9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QsElementsChain getQsElementsChain() {
        return (QsElementsChain) this.qsElementsChain$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setSecondElementVisible$lambda$17$lambda$14(QSPanelAnimatorManager qSPanelAnimatorManager) {
        QSPanelExpandFraction.ElementEndListener elementEndListener = qSPanelAnimatorManager.elementEndListener;
        if (elementEndListener != null) {
            ((OplusQSRootViewComponent$qsElementEndListener$1) elementEndListener).elementAnimatorEnd();
        }
        return Unit.INSTANCE;
    }

    public final void addCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        this.qsScenarioAnimListeners.add(qSScenarioAnimListener);
    }

    public final void addElementEndListener(QSPanelExpandFraction.ElementEndListener elementEndListener) {
        this.elementEndListener = elementEndListener;
    }

    public final void addElementVisibleChangeListener(QSPanelExpandFraction.ElementVisibleChangeListener elementVisibleChangeListener) {
        this.qsPanelExpandFraction.addCallback(elementVisibleChangeListener);
    }

    public final void addExpandFractionCallback(QSPanelExpandFraction.ExpandedFractionChangeListener expandedFractionChangeListener) {
        this.qsPanelExpandFraction.addCallback(expandedFractionChangeListener);
    }

    public final void addTranslationYCallback(QSPanelTranslationY.PanelTranslationYChangeListener panelTranslationYChangeListener) {
        this.qsPanelTranslationY.addCallback(panelTranslationYChangeListener);
    }

    public final void animateTo(View view, boolean z, boolean z2) {
        fetchElementAnimator(view).animateTo(z, z2, null, null, 0.0f, z ? 0.2f : 0.15f, 0.0f, null);
    }

    public final void animateToWithProxyLogic(View view, boolean z, boolean z2, Function2 function2, Function0 function0, float f, float f2, float f3, Function1 function1) {
        fetchElementAnimator(view).animateToWithProxyLogic(z, z2, function2, function0, f, f2, f3, function1);
    }

    public final void animateToWithProxyLogicForLow(View view, boolean z, boolean z2, Function3 function3) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[animateToWithProxyLogicForLow] toShow=", " shouldAnimation=", "QSPanelAnimatorManager-->", z, z2));
        }
        float f = z ? 0.3f : 0.15f;
        QSElementAnimator qSElementAnimatorFetchElementAnimator = fetchElementAnimator(view);
        int i = 0;
        QSPanelAnimatorManager$$ExternalSyntheticLambda0 qSPanelAnimatorManager$$ExternalSyntheticLambda0 = new QSPanelAnimatorManager$$ExternalSyntheticLambda0(i);
        qSPanelAnimatorManager$$ExternalSyntheticLambda0.f$0 = function3;
        qSPanelAnimatorManager$$ExternalSyntheticLambda0.f$1 = view;
        VarHandle.storeStoreFence();
        qSElementAnimatorFetchElementAnimator.alphaTo(z, z2, f, 0.0f, qSPanelAnimatorManager$$ExternalSyntheticLambda0);
        QSPanelAnimatorManager$$ExternalSyntheticLambda0 qSPanelAnimatorManager$$ExternalSyntheticLambda1 = new QSPanelAnimatorManager$$ExternalSyntheticLambda0(1);
        qSPanelAnimatorManager$$ExternalSyntheticLambda1.f$0 = function3;
        qSPanelAnimatorManager$$ExternalSyntheticLambda1.f$1 = view;
        VarHandle.storeStoreFence();
        qSElementAnimatorFetchElementAnimator.scaleTo(z, z2, 0.8f, 0.35f, 0.0f, qSPanelAnimatorManager$$ExternalSyntheticLambda1);
        QSPanelAnimatorManager$$ExternalSyntheticLambda2 qSPanelAnimatorManager$$ExternalSyntheticLambda2 = new QSPanelAnimatorManager$$ExternalSyntheticLambda2(i);
        qSPanelAnimatorManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        QSPanelAnimatorManager$$ExternalSyntheticLambda0 qSPanelAnimatorManager$$ExternalSyntheticLambda3 = new QSPanelAnimatorManager$$ExternalSyntheticLambda0(2);
        qSPanelAnimatorManager$$ExternalSyntheticLambda3.f$0 = function3;
        qSPanelAnimatorManager$$ExternalSyntheticLambda3.f$1 = view;
        VarHandle.storeStoreFence();
        qSElementAnimatorFetchElementAnimator.negativeOffsetYTo(z, z2, 0.35f, 0.0f, qSPanelAnimatorManager$$ExternalSyntheticLambda2, qSPanelAnimatorManager$$ExternalSyntheticLambda3);
    }

    public final void cancelElementAnimators(View view) {
        removeElementAnimator(view);
    }

    public final QSElementAnimator fetchElementAnimator(View view) {
        QSElementAnimator qSElementAnimator = this.qsElementAnimators.get(view);
        if (qSElementAnimator != null) {
            return qSElementAnimator;
        }
        QSElementAnimator qSElementAnimator2 = new QSElementAnimator(view);
        this.qsElementAnimators.put(view, qSElementAnimator2);
        view.addOnAttachStateChangeListener(this.elementAttachStateChangeListener);
        return qSElementAnimator2;
    }

    public final void flingToScenario(Boolean bool) {
        getQsScenarioPullAnimator().flingScenario(bool);
    }

    public final float getBlurFraction() {
        if (this.qsRootView.isAttachedToWindow()) {
            return this.qsPanelExpandFraction.getBlurFraction();
        }
        return 0.0f;
    }

    public final float getDefaultSettingEngine() {
        return this.qsPanelTranslationY.getDefaultSettingEngine();
    }

    public final float getGetCurCalculateFinalPosition() {
        return this.qsPanelExpandFraction.getGetCurCalculateFinalPosition();
    }

    public final Function0 getNumChangeCancelAction() {
        return this.numChangeCancelAction;
    }

    public final OplusQSQuickEntranceContainerView getOplusQSQuickEntranceContainerView() {
        return this.oplusQSQuickEntranceContainerView;
    }

    public final float getPanelDestTranslationY() {
        return this.qsPanelTranslationY.getDestTranslationY();
    }

    public final QSScenarioPullAnimator getQsScenarioPullAnimator() {
        return (QSScenarioPullAnimator) this.qsScenarioPullAnimator$delegate.getValue();
    }

    public final float getRawFraction() {
        return this.qsPanelExpandFraction.getRawFraction();
    }

    public final Function0 getSpringEndAction() {
        return this.springEndAction;
    }

    public final void initNodes(Collection<? extends IQSExpandOrCollapseAnimation> collection) {
        this.qsComponents.clear();
        this.qsComponents.addAll(collection);
        getQsElementsChain().initQSComponent(collection);
        getQsElementsChain().initSpringListener();
        for (IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation : this.qsComponents) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            iQSExpandOrCollapseAnimation.setNodeNumChange(anonymousClass1);
            AnonymousClass1 anonymousClass2 = new AnonymousClass1();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            iQSExpandOrCollapseAnimation.setPageSelectedListener(anonymousClass2);
        }
    }

    public final void initStatusWhenExpandStarted() {
        QSPanelTranslationY qSPanelTranslationY = this.qsPanelTranslationY;
        qSPanelTranslationY.trySetCurrValue(-qSPanelTranslationY.destTranslationY);
        if (this.isLowGaussianLevel) {
            return;
        }
        if (!this.qsPanelExpandFraction.isFirstElementVisible) {
            setFirstElementVisible(false, false);
        }
        if (this.qsPanelExpandFraction.isSecondElementVisible) {
            return;
        }
        setSecondElementVisible(false, false);
    }

    public final boolean isAnimating(View view) {
        return fetchElementAnimator(view).isRunning();
    }

    public final boolean isBlurFractionAnimationRunning() {
        return this.qsPanelExpandFraction.isBlurFractionAnimationRunning();
    }

    public final boolean isFirstElementVisible() {
        return this.qsPanelExpandFraction.isFirstElementVisible;
    }

    public final boolean isFractionAnimationRunning() {
        return this.qsPanelExpandFraction.isFractionAnimationRunning();
    }

    public final boolean isSecondElementVisible() {
        return this.qsPanelExpandFraction.isSecondElementVisible;
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorEnd(float f) {
        Iterator<T> it = this.qsScenarioAnimListeners.iterator();
        while (it.hasNext()) {
            ((QSScenarioAnimListener) it.next()).onAnimatorEnd(f);
        }
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorStart(float f) {
        Iterator<T> it = this.qsScenarioAnimListeners.iterator();
        while (it.hasNext()) {
            ((QSScenarioAnimListener) it.next()).onAnimatorStart(f);
        }
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorUpdate(float f, float f2, boolean z) {
        Iterator<T> it = this.qsScenarioAnimListeners.iterator();
        while (it.hasNext()) {
            ((QSScenarioAnimListener) it.next()).onAnimatorUpdate(f, this.oplusQSQuickEntranceContainerView.getTransitionAlpha(), z);
        }
    }

    public final void onFling(float f, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSPanelAnimatorManager-->".concat("[onFling] toExpand=" + z + " yVelocity=" + f));
        }
        this.qsPanelExpandFraction.onFling(f, z);
        this.qsPanelTranslationY.onFling(f, z);
    }

    public final void onMoveOver(float f, boolean z, boolean z2, float f2) {
        if (this.stub.hasScenario() && !TemporarilyInfoManager.Companion.getInstance().isShowing) {
            getQsScenarioPullAnimator().moveDistance(f, z);
        }
        getQsScenarioPullAnimator().setTracking(true);
        getQsElementsChain().onMoveOver(f, z, z2, f2);
    }

    public final void onTouchMove(float f, float f2, boolean z) {
        this.qsPanelExpandFraction.onMove(f, f2, z);
        this.qsPanelTranslationY.onMove(f, f2, z);
    }

    public final void removeCallback(QSScenarioAnimListener qSScenarioAnimListener) {
        this.qsScenarioAnimListeners.remove(qSScenarioAnimListener);
    }

    public final boolean removeElementAnimator(View view) {
        Object tag = view.getTag(2131364396);
        Runnable runnable = tag instanceof Runnable ? (Runnable) tag : null;
        if (runnable != null) {
            view.removeCallbacks(runnable);
            view.setTag(2131364396, null);
        }
        view.removeOnAttachStateChangeListener(this.elementAttachStateChangeListener);
        QSElementAnimator qSElementAnimatorRemove = this.qsElementAnimators.remove(view);
        if (qSElementAnimatorRemove == null) {
            return false;
        }
        qSElementAnimatorRemove.cancelAll();
        return true;
    }

    public final void removeElementEndListener(QSPanelExpandFraction.ElementEndListener elementEndListener) {
        this.elementEndListener = null;
    }

    public final void removeElementVisibleChangeListener(QSPanelExpandFraction.ElementVisibleChangeListener elementVisibleChangeListener) {
        this.qsPanelExpandFraction.removeCallback(elementVisibleChangeListener);
    }

    public final void removeExpandFractionCallback(QSPanelExpandFraction.ExpandedFractionChangeListener expandedFractionChangeListener) {
        this.qsPanelExpandFraction.removeCallback(expandedFractionChangeListener);
    }

    public final void removeTranslationYCallback(QSPanelTranslationY.PanelTranslationYChangeListener panelTranslationYChangeListener) {
        this.qsPanelTranslationY.removeCallback(panelTranslationYChangeListener);
    }

    public final void resetSpring() {
        getQsElementsChain().reset();
    }

    public final void setAnimationStartValue(float f) {
        this.qsPanelExpandFraction.setAnimationStartValue(f);
    }

    public final void setFirstElementVisible(boolean z, boolean z2) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("[setFirstElementVisible] visible=", ", shouldAnimation=", z, z2), null, 8);
        int i = 1;
        int i2 = 0;
        View animatorView = this.qsComponents.get(1).getAnimatorView(0);
        if (animatorView != null) {
            float f = z ? 0.3f : 0.2f;
            float f2 = z ? 0.0f : 0.15f;
            QSElementAnimator qSElementAnimatorFetchElementAnimator = fetchElementAnimator(animatorView);
            QSPanelAnimatorManager$$ExternalSyntheticLambda10 qSPanelAnimatorManager$$ExternalSyntheticLambda10 = new QSPanelAnimatorManager$$ExternalSyntheticLambda10(i2);
            qSPanelAnimatorManager$$ExternalSyntheticLambda10.f$0 = this;
            qSPanelAnimatorManager$$ExternalSyntheticLambda10.f$1 = animatorView;
            VarHandle.storeStoreFence();
            qSElementAnimatorFetchElementAnimator.alphaTo(z, z2, f, f2, qSPanelAnimatorManager$$ExternalSyntheticLambda10);
            QSElementAnimator qSElementAnimatorFetchElementAnimator2 = fetchElementAnimator(animatorView);
            QSPanelAnimatorManager$$ExternalSyntheticLambda10 qSPanelAnimatorManager$$ExternalSyntheticLambda11 = new QSPanelAnimatorManager$$ExternalSyntheticLambda10(i);
            qSPanelAnimatorManager$$ExternalSyntheticLambda11.f$0 = this;
            qSPanelAnimatorManager$$ExternalSyntheticLambda11.f$1 = animatorView;
            VarHandle.storeStoreFence();
            qSElementAnimatorFetchElementAnimator2.scaleTo(z, z2, 0.72f, 0.4f, 0.15f, qSPanelAnimatorManager$$ExternalSyntheticLambda11);
        }
    }

    public final void setNumChangeCancelAction(Function0 function0) {
        this.numChangeCancelAction = function0;
    }

    public final void setPanelDestTranslationY(float f) {
        if (this.qsPanelTranslationY.getDestTranslationY() == f) {
            return;
        }
        this.qsPanelTranslationY.setDestTranslationY(f);
    }

    public final void setSecondElementVisible(boolean z, boolean z2) {
        int i;
        Object obj;
        int i2;
        Function0 function0;
        boolean z3 = z;
        boolean z4 = z2;
        boolean zBooleanValue = ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("[setSecondElementVisible] visible=", ", shouldAnimation=", " isLandSplitMode=", z3, z4);
        sbM.append(zBooleanValue);
        Object obj2 = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        float f = z3 ? 0.35f : 0.2f;
        List<IQSExpandOrCollapseAnimation> list = this.qsComponents;
        Iterator<IQSExpandOrCollapseAnimation> it = list.subList(2, list.size()).iterator();
        float f2 = 0.3f;
        float f3 = 0.3f;
        int subSize = 0;
        int subSize2 = 0;
        while (it.hasNext()) {
            IQSExpandOrCollapseAnimation next = it.next();
            boolean z5 = next instanceof OplusQSPersonalComponent;
            if (z5 || (next instanceof OplusQSLeftBTilesComponent)) {
                subSize += next.getSubSize();
            }
            int i3 = subSize;
            boolean z6 = (next instanceof OplusQSLeftBTilesComponent) && zBooleanValue;
            boolean z7 = z5 || !zBooleanValue;
            int subSize3 = next.getSubSize();
            int i4 = 0;
            while (i4 < subSize3) {
                int i5 = subSize2 + i4;
                View animatorView = next.getAnimatorView(i4);
                if (animatorView != null) {
                    float f4 = 0.8f - (i5 * 0.05f);
                    float f5 = f2;
                    QSElementAnimator qSElementAnimatorFetchElementAnimator = fetchElementAnimator(animatorView);
                    QSPanelAnimatorManager$setSecondElementVisible$1$action$1 qSPanelAnimatorManager$setSecondElementVisible$1$action$1 = new QSPanelAnimatorManager$setSecondElementVisible$1$action$1();
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$elementAnimator = qSElementAnimatorFetchElementAnimator;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$visible = z3;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$withAnimation = z4;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$responseAlpha = f;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$component = next;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$it = animatorView;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$subIndex = i4;
                    qSPanelAnimatorManager$setSecondElementVisible$1$action$1.$nodeIndex = i5;
                    VarHandle.storeStoreFence();
                    long j = ((z7 || z6) ? i5 : i5 - i3) * 16;
                    if (next.isNeedAddResponse()) {
                        f5 += 0.05f;
                        f3 += 0.05f;
                    }
                    float f6 = f3;
                    int i6 = subSize3;
                    float f7 = f5;
                    if (!z4 || !z || fetchElementAnimator(animatorView).isRunning()) {
                        j = 0;
                    }
                    QSPanelAnimatorManager$$ExternalSyntheticLambda7 qSPanelAnimatorManager$$ExternalSyntheticLambda7 = new QSPanelAnimatorManager$$ExternalSyntheticLambda7(0);
                    qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$0 = next;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$1 = animatorView;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$2 = i4;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$3 = i5;
                    VarHandle.storeStoreFence();
                    long j2 = j;
                    i2 = i4;
                    i = i6;
                    int i7 = 1;
                    qSElementAnimatorFetchElementAnimator.scaleTo(z, z4, f4, f7, 0.25f, qSPanelAnimatorManager$$ExternalSyntheticLambda7);
                    if (next.isNeedAddResponse()) {
                        function0 = null;
                    } else {
                        QSPanelAnimatorManager$$ExternalSyntheticLambda2 qSPanelAnimatorManager$$ExternalSyntheticLambda2 = new QSPanelAnimatorManager$$ExternalSyntheticLambda2(3);
                        qSPanelAnimatorManager$$ExternalSyntheticLambda2.f$0 = this;
                        VarHandle.storeStoreFence();
                        function0 = qSPanelAnimatorManager$$ExternalSyntheticLambda2;
                    }
                    QSPanelAnimatorManager$$ExternalSyntheticLambda7 qSPanelAnimatorManager$$ExternalSyntheticLambda8 = new QSPanelAnimatorManager$$ExternalSyntheticLambda7(i7);
                    qSPanelAnimatorManager$$ExternalSyntheticLambda8.f$0 = next;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda8.f$1 = animatorView;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda8.f$2 = i2;
                    qSPanelAnimatorManager$$ExternalSyntheticLambda8.f$3 = i5;
                    VarHandle.storeStoreFence();
                    f3 = f6;
                    qSElementAnimatorFetchElementAnimator.negativeOffsetYTo(z, z2, f3, 0.2f, function0, qSPanelAnimatorManager$$ExternalSyntheticLambda8);
                    Object tag = animatorView.getTag(2131364396);
                    Runnable runnable = tag instanceof Runnable ? (Runnable) tag : null;
                    if (runnable != null) {
                        animatorView.removeCallbacks(runnable);
                        obj = null;
                        animatorView.setTag(2131364396, null);
                    } else {
                        obj = null;
                    }
                    if (0 == j2) {
                        qSPanelAnimatorManager$setSecondElementVisible$1$action$1.run();
                    } else {
                        animatorView.postDelayed(qSPanelAnimatorManager$setSecondElementVisible$1$action$1, j2);
                        animatorView.setTag(2131364396, qSPanelAnimatorManager$setSecondElementVisible$1$action$1);
                    }
                    f2 = f7;
                } else {
                    i = subSize3;
                    obj = null;
                    i2 = i4;
                }
                i4 = i2 + 1;
                z4 = z2;
                obj2 = obj;
                subSize3 = i;
                it = it;
                zBooleanValue = zBooleanValue;
                f = f;
                z3 = z;
            }
            subSize2 += next.getSubSize();
            z4 = z2;
            f2 = f2;
            subSize = i3;
            z3 = z;
        }
    }

    public final void setSpringEndAction(Function0 function0) {
        this.springEndAction = function0;
        getQsElementsChain().setSpringEndAction(function0);
    }

    public final void skipToEnd(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[skipToEnd] toExpand=", z), null, 8);
        getQsElementsChain().reset();
        this.qsPanelExpandFraction.skipToEnd(z);
        this.qsPanelTranslationY.skipToEnd(z);
    }

    public final void startRebound() {
        getQsScenarioPullAnimator().setTracking(false);
        getQsElementsChain().startRebound();
    }

    public final boolean syncBlurFractionFromExternal(float f) {
        return this.qsPanelExpandFraction.syncBlurFractionFromExternal(f);
    }

    public final void updatePivotXY(boolean z) {
        List<IQSExpandOrCollapseAnimation> list = this.qsComponents;
        int subSize = 0;
        for (IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation : list.subList(2, list.size())) {
            int subSize2 = iQSExpandOrCollapseAnimation.getSubSize();
            for (int i = 0; i < subSize2; i++) {
                iQSExpandOrCollapseAnimation.updatePivotXY(i, subSize + i, z);
            }
            subSize += iQSExpandOrCollapseAnimation.getSubSize();
        }
    }
}
