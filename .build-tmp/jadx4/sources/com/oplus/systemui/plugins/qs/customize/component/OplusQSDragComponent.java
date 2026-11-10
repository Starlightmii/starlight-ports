package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.core.view.ViewGroupKt;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.IQsComponent;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSDragComponent extends ViewController implements IQsComponent {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSDragComponent";
    private final List<ITransitionDesc> currPageTransitionDesc;
    private final Lazy transitionProperties$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSDragComponent(OplusQSRootView oplusQSRootView) {
        super(oplusQSRootView);
        this.transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSDragComponent$$ExternalSyntheticLambda0());
        this.currPageTransitionDesc = new ArrayList();
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        this.currPageTransitionDesc.clear();
        ViewGroup viewGroup = (ViewGroup) this.mView;
        List<ITransitionDesc> list = this.currPageTransitionDesc;
        for (KeyEvent.Callback callback : ViewGroupKt.getChildren(viewGroup)) {
            if (callback instanceof ITransitionDesc) {
                list.add((ITransitionDesc) callback);
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.currPageTransitionDesc.size(), "[refreshAllTransitionDesc] currPageTransitionDesc.size="), null, 8);
        for (SpringTransition springTransition : set) {
            for (ITransitionDesc iTransitionDesc : this.currPageTransitionDesc) {
                String transitionName = springTransition.getTransitionName();
                ITransitionDesc.Companion companion = ITransitionDesc.Companion;
                Iterator<T> it = iTransitionDesc.collectProperties(transitionName, new ArrayList()).iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    Object second = pair.getSecond();
                    Class cls = second instanceof Class ? (Class) second : null;
                    if (cls != null) {
                        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[bindTargetProperties] for " + pair.getFirst() + ", " + cls, null, 8);
                        springTransition.addTargetProperty(getTransitionProperties().fetchTransitionProperty(pair.getFirst(), cls));
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final boolean checkEditModeAbnormal() {
        return false;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        return super.createSpringTransition(str);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void destroyComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public View getAnimatorView(int i) {
        return super.getAnimatorView(i);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public int getSubSize() {
        return 0;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
        super.injectHostAbility(iComponentHostAbility);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final boolean isNeedAddResponse() {
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onCollapseQSPanelStart(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
        super.onEnterAddingMode(z, z2, bundle);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterDragDisableMode() {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                ((DeformationFrameLayout) view).setDragDisable(true);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void onEnterDragMode(boolean z, Bundle bundle) {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                DeformationFrameLayout deformationFrameLayout = (DeformationFrameLayout) view;
                deformationFrameLayout.setImmersiveOperation(true);
                deformationFrameLayout.setDragMode(true);
                deformationFrameLayout.setBeingDragged(true);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onEnterEditMode(boolean z) {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                ((DeformationFrameLayout) view).setEditMode(true);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitAddingMode(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragDisableMode() {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                ((DeformationFrameLayout) view).setDragDisable(false);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitDragMode() {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                DeformationFrameLayout deformationFrameLayout = (DeformationFrameLayout) view;
                deformationFrameLayout.setImmersiveOperation(false);
                deformationFrameLayout.setDragMode(false);
                deformationFrameLayout.setBeingDragged(false);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onExitEditMode(boolean z) {
        for (View view : ViewGroupKt.getChildren((ViewGroup) this.mView)) {
            if (view instanceof DeformationFrameLayout) {
                ((DeformationFrameLayout) view).setEditMode(false);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void onThemeApplied(boolean z) {
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void resetNonCurrPageToExpanded() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void saveLatestNoEditModeLocList(List<Loc> list) {
        super.saveLatestNoEditModeLocList(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void setCusTranslationY(float f, int i, int i2) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setEmptyTouchListener(Function1 function1) {
        super.setEmptyTouchListener(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setLayerType(boolean z) {
        super.setLayerType(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void setListening(boolean z) {
        super.setListening(z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent, com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController, com.oplus.systemui.plugins.qs.IQsComponent
    public final void startComponent() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        super.updateColor(colorType);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateDrawable(List<SpanSize> list, boolean z) {
        super.updateDrawable(list, z);
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconDrawable(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateIconTint() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateNodeTranslationY() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateOutline(List<SpanSize> list) {
        super.updateOutline(list);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updatePivotXY(int i, int i2, boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public final void updateQuickEntranceDrawable() {
    }

    @Override // com.oplus.systemui.plugins.qs.IQsComponent
    public void updateResource(boolean z, Context context) {
        super.updateResource(z, context);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    public final void updateState(int i, int i2, float f, float f2, float f3) {
    }
}
