package com.oplus.systemui.plugins.qs.editmodeswitch;

import android.util.Log;
import androidx.core.view.OneShotPreDrawListener;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.SpringTransitionManager;
import com.oplus.systemui.qs.base.springtransition.SpringTransitionSceneManager;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditModeSwitchAnimationManager {
    public static final Companion Companion = new Companion();
    public static final String SCENE_ADDING = "SCENE_ADDING";
    public static final String SCENE_DRAG = "SCENE_DRAG";
    public static final String SCENE_DRAG_DISABLE = "SCENE_DRAG_DISABLE";
    public static final String SCENE_EDIT = "SCENE_EDIT";
    private static final String TAG = "EditModeSwitchAnimationManager";
    private final Lazy addingTransitionManager$delegate;
    private final List<IEditModeSwitcher> allComponents;
    private final Lazy dragDisableTransitionManager$delegate;
    private final Lazy dragTransitionManager$delegate;
    private final OplusQSRootView rootView;
    private final Lazy springTransitionSceneManager$delegate;
    private final Lazy transitionManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static SpringTransitionSceneManager $r8$lambda$7aE4bjC4S8E48ft_NqNFgxv142k(EditModeSwitchAnimationManager editModeSwitchAnimationManager) {
        SpringTransitionSceneManager springTransitionSceneManager = new SpringTransitionSceneManager();
        springTransitionSceneManager.registerScene(SCENE_ADDING, editModeSwitchAnimationManager.getAddingTransitionManager());
        springTransitionSceneManager.registerScene(SCENE_DRAG, editModeSwitchAnimationManager.getDragTransitionManager());
        springTransitionSceneManager.registerScene(SCENE_EDIT, editModeSwitchAnimationManager.getTransitionManager());
        springTransitionSceneManager.registerScene(SCENE_DRAG_DISABLE, editModeSwitchAnimationManager.getDragDisableTransitionManager());
        springTransitionSceneManager.startTransitionStartAndEnd("adding_transition_position", 255.0f, 0.0f);
        return springTransitionSceneManager;
    }

    /* JADX INFO: renamed from: $r8$lambda$AXF5FopUV5bIRFX_07_BNObVo-g, reason: not valid java name */
    public static SpringTransitionManager m221$r8$lambda$AXF5FopUV5bIRFX_07_BNObVog(EditModeSwitchAnimationManager editModeSwitchAnimationManager) {
        SpringTransitionManager springTransitionManager = new SpringTransitionManager(editModeSwitchAnimationManager.rootView, SCENE_EDIT);
        IEditModeSwitcher.Companion companion = IEditModeSwitcher.Companion;
        springTransitionManager.addTransition(companion.createTransition("enter_exit_edit_mode_main_transition"));
        springTransitionManager.addTransition(companion.createTransition("parent_translation_transition"));
        Iterator<T> it = editModeSwitchAnimationManager.allComponents.iterator();
        while (it.hasNext()) {
            for (SpringTransition springTransition : ((IEditModeSwitcher) it.next()).createSpringTransition(SCENE_EDIT)) {
                springTransitionManager.addTransition(springTransition);
            }
        }
        return springTransitionManager;
    }

    public static SpringTransitionManager $r8$lambda$GrYxfrmYjJyRqPOgLi9BO11q57A(EditModeSwitchAnimationManager editModeSwitchAnimationManager) {
        SpringTransitionManager springTransitionManager = new SpringTransitionManager(editModeSwitchAnimationManager.rootView, SCENE_DRAG);
        springTransitionManager.addTransition(IEditModeSwitcher.Companion.createTransition("drag_transition"));
        Iterator<T> it = editModeSwitchAnimationManager.allComponents.iterator();
        while (it.hasNext()) {
            for (SpringTransition springTransition : ((IEditModeSwitcher) it.next()).createSpringTransition(SCENE_DRAG)) {
                springTransitionManager.addTransition(springTransition);
            }
        }
        return springTransitionManager;
    }

    public static SpringTransitionManager $r8$lambda$M79N1EyGkN9irgxGSgeUJI6ieyw(EditModeSwitchAnimationManager editModeSwitchAnimationManager) {
        SpringTransitionManager springTransitionManager = new SpringTransitionManager(editModeSwitchAnimationManager.rootView, SCENE_DRAG_DISABLE);
        springTransitionManager.addTransition(IEditModeSwitcher.Companion.createTransition("drag_disable_transition"));
        return springTransitionManager;
    }

    public static SpringTransitionManager $r8$lambda$XZCP6J52ZDcXyi1zbutCW0PBKGM(EditModeSwitchAnimationManager editModeSwitchAnimationManager) {
        SpringTransitionManager springTransitionManager = new SpringTransitionManager(editModeSwitchAnimationManager.rootView, SCENE_ADDING);
        IEditModeSwitcher.Companion companion = IEditModeSwitcher.Companion;
        springTransitionManager.addTransition(companion.createTransition("drag_transition"));
        springTransitionManager.addTransition(companion.createTransition("adding_transition"));
        springTransitionManager.addTransition(companion.createTransition("adding_transition_position"));
        springTransitionManager.addTransition(companion.createTransition("adding_transition_tile_alpha"));
        Iterator<T> it = editModeSwitchAnimationManager.allComponents.iterator();
        while (it.hasNext()) {
            for (SpringTransition springTransition : ((IEditModeSwitcher) it.next()).createSpringTransition(SCENE_ADDING)) {
                springTransitionManager.addTransition(springTransition);
            }
        }
        return springTransitionManager;
    }

    public EditModeSwitchAnimationManager(OplusQSRootView oplusQSRootView) {
        this.rootView = oplusQSRootView;
        EditModeSwitchAnimationManager$$ExternalSyntheticLambda0 editModeSwitchAnimationManager$$ExternalSyntheticLambda0 = new EditModeSwitchAnimationManager$$ExternalSyntheticLambda0(0);
        editModeSwitchAnimationManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.transitionManager$delegate = LazyKt__LazyJVMKt.lazy(editModeSwitchAnimationManager$$ExternalSyntheticLambda0);
        EditModeSwitchAnimationManager$$ExternalSyntheticLambda0 editModeSwitchAnimationManager$$ExternalSyntheticLambda1 = new EditModeSwitchAnimationManager$$ExternalSyntheticLambda0(1);
        editModeSwitchAnimationManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragTransitionManager$delegate = LazyKt__LazyJVMKt.lazy(editModeSwitchAnimationManager$$ExternalSyntheticLambda1);
        EditModeSwitchAnimationManager$$ExternalSyntheticLambda0 editModeSwitchAnimationManager$$ExternalSyntheticLambda2 = new EditModeSwitchAnimationManager$$ExternalSyntheticLambda0(2);
        editModeSwitchAnimationManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.dragDisableTransitionManager$delegate = LazyKt__LazyJVMKt.lazy(editModeSwitchAnimationManager$$ExternalSyntheticLambda2);
        EditModeSwitchAnimationManager$$ExternalSyntheticLambda0 editModeSwitchAnimationManager$$ExternalSyntheticLambda3 = new EditModeSwitchAnimationManager$$ExternalSyntheticLambda0(3);
        editModeSwitchAnimationManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.addingTransitionManager$delegate = LazyKt__LazyJVMKt.lazy(editModeSwitchAnimationManager$$ExternalSyntheticLambda3);
        this.allComponents = new ArrayList();
        EditModeSwitchAnimationManager$$ExternalSyntheticLambda0 editModeSwitchAnimationManager$$ExternalSyntheticLambda4 = new EditModeSwitchAnimationManager$$ExternalSyntheticLambda0(4);
        editModeSwitchAnimationManager$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.springTransitionSceneManager$delegate = LazyKt__LazyJVMKt.lazy(editModeSwitchAnimationManager$$ExternalSyntheticLambda4);
    }

    private final SpringTransitionManager getAddingTransitionManager() {
        return (SpringTransitionManager) this.addingTransitionManager$delegate.getValue();
    }

    private final SpringTransitionManager getDragDisableTransitionManager() {
        return (SpringTransitionManager) this.dragDisableTransitionManager$delegate.getValue();
    }

    private final SpringTransitionManager getDragTransitionManager() {
        return (SpringTransitionManager) this.dragTransitionManager$delegate.getValue();
    }

    private final SpringTransitionSceneManager getSpringTransitionSceneManager() {
        return (SpringTransitionSceneManager) this.springTransitionSceneManager$delegate.getValue();
    }

    private final SpringTransitionManager getTransitionManager() {
        return (SpringTransitionManager) this.transitionManager$delegate.getValue();
    }

    public static void updateSpringConfig(SpringTransitionManager springTransitionManager) {
        for (SpringTransition springTransition : springTransitionManager.getTransitions()) {
            Spring springConfig = IEditModeSwitcher.Companion.getSpringConfig(springTransition.getTransitionName());
            if (springConfig != null) {
                springTransition.setBounce(springConfig.bounce());
                springTransition.setResponse(springConfig.response());
                springTransition.setStartVelocity(springConfig.velocity());
            }
        }
    }

    public final void allowLayoutForOnce() {
        if (this.rootView.isLayoutSuppressed()) {
            this.rootView.suppressLayout(false);
            OplusQSRootView oplusQSRootView = this.rootView;
            EditModeSwitchAnimationManager$allowLayoutForOnce$.inlined.doOnPreDraw.1 r1 = new EditModeSwitchAnimationManager$allowLayoutForOnce$.inlined.doOnPreDraw.1();
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(oplusQSRootView, r1);
        }
    }

    public final void bindTargetProperties(SpringTransitionManager springTransitionManager) {
        Iterator<T> it = springTransitionManager.getTransitions().iterator();
        while (it.hasNext()) {
            ((SpringTransition) it.next()).clearSkipToEndPropertyHash();
        }
        Iterator<T> it2 = this.allComponents.iterator();
        while (it2.hasNext()) {
            ((IEditModeSwitcher) it2.next()).bindTargetProperties(springTransitionManager.getTransitions());
        }
    }

    public final void enterAddingMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[enterAddingMode]");
        }
        bindTargetProperties(getAddingTransitionManager());
        getSpringTransitionSceneManager().enterScene(SCENE_ADDING);
        updateSpringConfig(getAddingTransitionManager());
    }

    public final void enterDragDisableMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[enterDragDisableMode]");
        }
        bindTargetProperties(getDragDisableTransitionManager());
        getSpringTransitionSceneManager().enterScene(SCENE_DRAG_DISABLE);
        updateSpringConfig(getDragDisableTransitionManager());
    }

    public final void enterDragMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[enterDragMode]");
        }
        bindTargetProperties(getDragTransitionManager());
        getSpringTransitionSceneManager().enterScene(SCENE_DRAG);
        updateSpringConfig(getDragTransitionManager());
    }

    public final void enterEditMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[enterEditMode]");
        }
        bindTargetProperties(getTransitionManager());
        getSpringTransitionSceneManager().enterScene(SCENE_EDIT);
        updateSpringConfig(getTransitionManager());
    }

    public final void exitAddingMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[exitAddingMode]");
        }
        bindTargetProperties(getAddingTransitionManager());
        getSpringTransitionSceneManager().exitScene(SCENE_ADDING);
        updateSpringConfig(getAddingTransitionManager());
    }

    public final void exitDragDisableMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[exitDragDisableMode]");
        }
        bindTargetProperties(getDragDisableTransitionManager());
        getSpringTransitionSceneManager().exitScene(SCENE_DRAG_DISABLE);
        updateSpringConfig(getDragDisableTransitionManager());
    }

    public final void exitDragMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[exitDragMode]");
        }
        bindTargetProperties(getDragTransitionManager());
        getSpringTransitionSceneManager().exitScene(SCENE_DRAG);
        updateSpringConfig(getDragTransitionManager());
    }

    public final void exitEditMode() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[exitEditMode]");
        }
        bindTargetProperties(getTransitionManager());
        getSpringTransitionSceneManager().exitScene(SCENE_EDIT);
        updateSpringConfig(getTransitionManager());
    }

    public final void init(List<? extends IEditModeSwitcher> list) {
        this.allComponents.clear();
        this.allComponents.addAll(list);
    }

    public final boolean isAnyRunning() {
        return getSpringTransitionSceneManager().isAnyRunning();
    }

    public final void skipToEnd() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EditModeSwitchAnimationManager-->[skipToEnd]");
        }
        getAddingTransitionManager().endTransition();
        getDragTransitionManager().endTransition();
        getTransitionManager().endTransition();
        getDragDisableTransitionManager().endTransition();
    }
}
