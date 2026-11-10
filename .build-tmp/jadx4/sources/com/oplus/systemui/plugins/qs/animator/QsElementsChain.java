package com.oplus.systemui.plugins.qs.animator;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.compose.animation.core.MonoSpline$;
import androidx.core.view.insets.Protection$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.springchain.COUISpringChain;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.quickentrance.OplusQSQuickEntranceComponent;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsElementsChain {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSPanelSpringChain";
    private final float[] curTranslationYArray;
    private final Lazy gridSpringChain$delegate;
    public boolean isMotionMove;
    public boolean isRelease;
    private final float[] lastTranslationYArray;
    public final float maxResponse;
    private final List<IQSExpandOrCollapseAnimation> qsComponents;
    private final OplusQSRootView qsRootView;
    private final FrameLayout separateQsFakeLayout;
    private Function0 springEndAction;
    public final SpringSystem springSystem = SpringSystem.create();
    private final QsElementsChain$statusAndQuickEntranceSpringListener$1 statusAndQuickEntranceSpringListener;
    private final ViewGroup statusBarContainer;
    private final Lazy translationYOverAnimation$delegate;
    public float translationYOverValue;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class QsSpringListener extends SimpleSpringListener {
        public final int nodeIndex;
        public final int nodeSubIndex;
        private final IQSExpandOrCollapseAnimation qsNode;

        public QsSpringListener(IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation, int i, int i2) {
            this.qsNode = iQSExpandOrCollapseAnimation;
            this.nodeIndex = i;
            this.nodeSubIndex = i2;
        }

        public final int getNodeIndex() {
            return this.nodeIndex;
        }

        public final int getNodeSubIndex() {
            return this.nodeSubIndex;
        }

        public final IQSExpandOrCollapseAnimation getQsNode() {
            return this.qsNode;
        }
    }

    public QsElementsChain(OplusQSRootView oplusQSRootView, ViewGroup viewGroup, FrameLayout frameLayout, Function0 function0) {
        this.qsRootView = oplusQSRootView;
        this.statusBarContainer = viewGroup;
        this.separateQsFakeLayout = frameLayout;
        this.springEndAction = function0;
        QsElementsChain$$ExternalSyntheticLambda0 qsElementsChain$$ExternalSyntheticLambda0 = new QsElementsChain$$ExternalSyntheticLambda0(0);
        qsElementsChain$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.gridSpringChain$delegate = LazyKt__LazyJVMKt.lazy(qsElementsChain$$ExternalSyntheticLambda0);
        this.curTranslationYArray = new float[50];
        this.lastTranslationYArray = new float[50];
        this.maxResponse = 0.35f;
        this.qsComponents = new ArrayList();
        QsElementsChain$$ExternalSyntheticLambda0 qsElementsChain$$ExternalSyntheticLambda1 = new QsElementsChain$$ExternalSyntheticLambda0(1);
        qsElementsChain$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.translationYOverAnimation$delegate = LazyKt__LazyJVMKt.lazy(qsElementsChain$$ExternalSyntheticLambda1);
        QsElementsChain$statusAndQuickEntranceSpringListener$1 qsElementsChain$statusAndQuickEntranceSpringListener$1 = new QsElementsChain$statusAndQuickEntranceSpringListener$1();
        qsElementsChain$statusAndQuickEntranceSpringListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusAndQuickEntranceSpringListener = qsElementsChain$statusAndQuickEntranceSpringListener$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringChain getGridSpringChain() {
        return (COUISpringChain) this.gridSpringChain$delegate.getValue();
    }

    private final int getRootViewHeight() {
        return this.qsRootView.getMeasuredHeight();
    }

    private final COUISpringAnimation getTranslationYOverAnimation() {
        return (COUISpringAnimation) this.translationYOverAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTranslationYOverValue(float f) {
        if (this.translationYOverValue == f) {
            return;
        }
        this.translationYOverValue = f;
        int subSize = 0;
        for (IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation : this.qsComponents) {
            if (iQSExpandOrCollapseAnimation instanceof OplusQSQuickEntranceComponent) {
                ((OplusQSQuickEntranceComponent) iQSExpandOrCollapseAnimation).setCusTranslationY(this.curTranslationYArray[0], subSize, 0);
            } else {
                int subSize2 = iQSExpandOrCollapseAnimation.getSubSize();
                for (int i = 0; i < subSize2; i++) {
                    int i2 = subSize + i;
                    float[] fArr = this.curTranslationYArray;
                    Companion companion = Companion;
                    int rootViewHeight = getRootViewHeight();
                    companion.getClass();
                    float f2 = i2;
                    float f3 = (2000.0f * f2) + (rootViewHeight * 1.0f);
                    fArr[i2] = (Math.signum(f) * (1.0f - (1.0f / (((Math.abs(f) * ((f2 * 0.02f) + 0.25f)) / f3) + 1.0f))) * f3) + ((float) ((1.0d - Math.exp(((double) (-f)) / 120.0d)) * 108.0d)) + this.lastTranslationYArray[i2];
                    if (i2 != 0) {
                        iQSExpandOrCollapseAnimation.setCusTranslationY(this.curTranslationYArray[i2], subSize, i);
                    } else if (AnimatorUtils.INSTANCE.getExtraFakeTranslationY() <= 0.0f) {
                        float fMax = Math.max(this.curTranslationYArray[i2], 0.0f);
                        this.statusBarContainer.setTranslationY(fMax);
                        this.separateQsFakeLayout.setTranslationY(fMax);
                        iQSExpandOrCollapseAnimation.setCusTranslationY(fMax, subSize, i);
                    }
                }
                subSize += iQSExpandOrCollapseAnimation.getSubSize();
            }
        }
    }

    public final void cancelSpring() {
        if (this.isRelease && this.springSystem.getIsIdle()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[cancelSpring]", null, 8);
        this.isRelease = true;
        Iterator it = getGridSpringChain().getAllSprings().iterator();
        while (it.hasNext()) {
            ((Spring) it.next()).setAtRest();
        }
    }

    public final Function0 getSpringEndAction() {
        return this.springEndAction;
    }

    public final void initQSComponent(Collection<? extends IQSExpandOrCollapseAnimation> collection) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSPanelSpringChain-->[initQSComponent]");
        }
        this.qsComponents.clear();
        this.qsComponents.addAll(collection);
    }

    public final void initSpringListener() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "initSpringListener", null, 8);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QSPanelSpringChain-->[resetAndRemove]");
        }
        reset();
        for (Spring spring : getGridSpringChain().getAllSprings()) {
            spring.mListeners.clear();
            BaseSpringSystem baseSpringSystem = spring.mSpringSystem;
            baseSpringSystem.mActiveSprings.remove(spring);
            baseSpringSystem.mSpringRegistry.remove(spring.getId());
        }
        getGridSpringChain().getAllSprings().clear();
        getGridSpringChain().getAllListeners().clear();
        getGridSpringChain().addSpring(this.statusAndQuickEntranceSpringListener);
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        List<IQSExpandOrCollapseAnimation> list = this.qsComponents;
        for (final IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation : list.subList(2, list.size())) {
            int subSize = iQSExpandOrCollapseAnimation.getSubSize();
            for (final int i = 0; i < subSize; i++) {
                getGridSpringChain().addSpring(new QsSpringListener(iQSExpandOrCollapseAnimation, intRef, i, this) { // from class: com.oplus.systemui.plugins.qs.animator.QsElementsChain$initSpringListener$simpleSpringListener$1
                    public final /* synthetic */ QsElementsChain this$0;

                    {
                        this.this$0 = this;
                        int i2 = intRef.element;
                    }

                    public final void onSpringUpdate(Spring spring2) {
                        Function0 springEndAction;
                        QsElementsChain qsElementsChain = this.this$0;
                        if (!qsElementsChain.isRelease) {
                            float currentValue = (float) spring2.getCurrentValue();
                            getQsNode().setCusTranslationY(currentValue, getNodeIndex(), getNodeSubIndex());
                            qsElementsChain.curTranslationYArray[getNodeSubIndex() + getNodeIndex()] = currentValue;
                            qsElementsChain.lastTranslationYArray[getNodeSubIndex() + getNodeIndex()] = currentValue;
                        }
                        if (qsElementsChain.getGridSpringChain().getAllSprings().indexOf(spring2) == qsElementsChain.getGridSpringChain().getAllSprings().size() - 1 && spring2.getCurrentValue() == 0.0d && (springEndAction = qsElementsChain.getSpringEndAction()) != null) {
                            springEndAction.invoke();
                        }
                    }
                });
            }
            intRef.element = iQSExpandOrCollapseAnimation.getSubSize() + intRef.element;
        }
    }

    public final void onMoveOver(float f, boolean z, boolean z2, float f2) {
        if (!((COUIDynamicAnimation) getTranslationYOverAnimation()).mRunning) {
            getTranslationYOverAnimation().cancel();
        }
        cancelSpring();
        float fM = 0.3f;
        if (!z || f2 <= 1000.0f) {
            getTranslationYOverAnimation().setMinimumVisibleChange(1.0f);
        } else {
            getTranslationYOverAnimation().setMinimumVisibleChange(0.001f);
            float fCoerceIn = RangesKt___RangesKt.coerceIn(this.translationYOverValue / (getRootViewHeight() * 0.2f), 0.0f, 1.0f);
            float f3 = this.maxResponse;
            fM = MonoSpline$.ExternalSyntheticOutline0.m(f3, 0.3f, fCoerceIn, f3);
        }
        float fResponseToStiffness = AnimatorUtils.responseToStiffness(fM);
        if (getTranslationYOverAnimation().getSpring().getStiffness() != fResponseToStiffness) {
            getTranslationYOverAnimation().getSpring().setStiffness(fResponseToStiffness);
        }
        float translationY = this.separateQsFakeLayout.getTranslationY();
        if (this.isMotionMove && z2) {
            float f4 = this.translationYOverValue;
            float f5 = translationY - this.lastTranslationYArray[0];
            if (f4 < f5) {
                f4 = f5;
            }
            getTranslationYOverAnimation().setStartValue(f4);
            float f6 = (translationY + f) - this.lastTranslationYArray[0];
            if (f6 >= f) {
                f = f6;
            }
        }
        getTranslationYOverAnimation().animateToFinalPosition(f);
        this.isMotionMove = true;
    }

    public final void reset() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[reset]", null, 8);
        getTranslationYOverAnimation().cancelComplete();
        getTranslationYOverAnimation().setStartValue(0.0f);
        cancelSpring();
        ArraysKt___ArraysJvmKt.fill$default(0.0f, this.curTranslationYArray);
        ArraysKt___ArraysJvmKt.fill$default(0.0f, this.lastTranslationYArray);
        this.statusBarContainer.setTranslationY(0.0f);
        this.separateQsFakeLayout.setTranslationY(0.0f);
        int subSize = 0;
        for (IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation : this.qsComponents) {
            int subSize2 = iQSExpandOrCollapseAnimation.getSubSize();
            for (int i = 0; i < subSize2; i++) {
                iQSExpandOrCollapseAnimation.setCusTranslationY(0.0f, subSize, i);
            }
            if (!(iQSExpandOrCollapseAnimation instanceof OplusQSQuickEntranceComponent)) {
                subSize += iQSExpandOrCollapseAnimation.getSubSize();
            }
        }
    }

    public final void setSpringEndAction(Function0 function0) {
        this.springEndAction = function0;
    }

    public final void startRebound() {
        this.isMotionMove = false;
        getTranslationYOverAnimation().cancelComplete();
        getTranslationYOverAnimation().setStartValue(0.0f);
        if (this.statusBarContainer.getTranslationY() <= 0.0f || getGridSpringChain().getAllSprings().isEmpty()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(this.translationYOverValue, "[startRebound] translationYOverValue="), null, 8);
        this.isRelease = false;
        int i = 0;
        for (Spring spring : getGridSpringChain().getAllSprings()) {
            spring.setCurrentValue(this.curTranslationYArray[i], false);
            spring.setVelocity(0.0d);
            spring.setRestSpeedThreshold(3.0d);
            spring.setRestDisplacementThreshold(0.5d);
            i++;
        }
        getGridSpringChain().setControlSpringIndex(0).getControlSpring().setEndValue(0.0d);
    }
}
