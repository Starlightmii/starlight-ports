package com.oplus.systemui.notification.stackednotification.ruler;

import android.content.Context;
import android.view.View;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationRectManager;
import com.oplus.systemui.notification.lockscreen.statemanager.NotificationDisplayRectCallbackWrapper;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.notification.stackednotification.NotificationStackedRuler;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import com.oplusos.systemui.common.feature.FeatureOption;
import dagger.Lazy;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardNotificationStackedRuler extends FlowDumperImpl implements NotificationStackedRuler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardNotificationStackedRuler";
    private final OplusCloseAllController closeController;
    private final Context context;
    public final boolean currentIsStackedNotification;
    private final Lazy lockScreenExtStateController;
    private final Lazy lockScreenNotificationRectManager;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final kotlin.Lazy visibleExpandableViews$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardNotificationStackedRuler(Context context, OplusCloseAllController oplusCloseAllController, Lazy lazy, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, Lazy lazy2, DumpManager dumpManager) {
        super(dumpManager, (String) null);
        this.context = context;
        this.closeController = oplusCloseAllController;
        this.lockScreenExtStateController = lazy;
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.lockScreenNotificationRectManager = lazy2;
        this.currentIsStackedNotification = true;
        this.visibleExpandableViews$delegate = LazyKt__LazyJVMKt.lazy(new KeyguardNotificationStackedRuler$$ExternalSyntheticLambda0());
    }

    private final int getScreenHeight() {
        return this.context.getResources().getConfiguration().windowConfiguration.getBounds().height();
    }

    private final List<ExpandableView> getVisibleExpandableViews() {
        return (List) this.visibleExpandableViews$delegate.getValue();
    }

    public final void fixSectionHeaderPosition(float f, List list, boolean z) {
        ExpandableView expandableView;
        ExpandableViewState viewState;
        RowAnimationStateEx rowAnimationStateEx;
        ScaleState rowScale$default;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion() || (expandableView = (ExpandableView) CollectionsKt___CollectionsKt.firstOrNull(list)) == null || (viewState = expandableView.getViewState()) == null) {
            return;
        }
        float yTranslation = viewState.getYTranslation();
        if (z) {
            getVisibleExpandableViews().clear();
        }
        Iterator it = list.iterator();
        float yTranslation2 = -1.0f;
        float scaleY = 1.0f;
        float f2 = 0.0f;
        while (it.hasNext()) {
            ExpandableView expandableView2 = (ExpandableView) it.next();
            if (!((ViewState) expandableView2.getViewState()).hidden && !(expandableView2 instanceof EmptyShadeView)) {
                boolean z2 = expandableView2 instanceof SectionHeaderView;
                ExpandableViewState viewState2 = expandableView2.getViewState();
                if (z2) {
                    float f3 = viewState2.height;
                    f2 += f3 + f;
                    float f4 = yTranslation2 - f3;
                    if (f4 < yTranslation) {
                        f4 = yTranslation;
                    }
                    viewState2.setYTranslation(f4, "KeyguardNotificationStackedRuler.fixSectionHeaderPosition.isSectionHeader");
                    viewState2.setAlpha(0.0f, "KeyguardNotificationStackedRuler.fixSectionHeaderPosition");
                    NotificationStackedRuler.Companion.requestStackedScale(viewState2, scaleY);
                } else {
                    yTranslation2 = viewState2.getYTranslation() + viewState2.height;
                    OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState2.mExpandableViewStateEx;
                    if (oplusExpandableViewStateEx != null && (rowAnimationStateEx = oplusExpandableViewStateEx.getRowAnimationStateEx()) != null && (rowScale$default = RowAnimationStateEx.getRowScale$default(rowAnimationStateEx)) != null) {
                        scaleY = rowScale$default.getScaleY();
                    }
                    if (z) {
                        viewState2.setYTranslation(viewState2.getYTranslation() - f2, "KeyguardNotificationStackedRuler.fixSectionHeaderPosition");
                        getVisibleExpandableViews().add(expandableView2);
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public boolean getCurrentIsStackedNotification() {
        return this.currentIsStackedNotification;
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public StateFlow isStackedNotification() {
        return StateFlowKt.MutableStateFlow(Boolean.TRUE);
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public void prepareAddOrRemoveAnimationForOverlay(View view, View view2, boolean z) {
        ExpandableView expandableView = view instanceof ExpandableView ? (ExpandableView) view : null;
        if (expandableView == null) {
            return;
        }
        if (!z) {
            ExpandableView expandableView2 = view2 instanceof ExpandableView ? (ExpandableView) view2 : null;
            if (expandableView2 == null || !this.overlayInteractor.isOverlayShowing(expandableView2, true) || expandableView2.isVisibleToUser()) {
                return;
            }
            OplusNotificationOverlayInteractor.applyOverlayInfo$default(this.overlayInteractor, 0L, 1);
            return;
        }
        if (this.overlayInteractor.isOverlayShowing(expandableView, true)) {
            float yTranslation = expandableView.getViewState().getYTranslation();
            NotificationStackedRuler.Companion companion = NotificationStackedRuler.Companion;
            companion.requestStackedScale(expandableView.getViewState(), 0.9f);
            expandableView.getViewState().setYTranslation((-200.0f) + yTranslation, "KeyguardNotificationStackedRuler.prepareAddOrRemoveAnimationForOverlay.tmp");
            expandableView.getViewState().applyToView(expandableView);
            expandableView.getViewState().setYTranslation(yTranslation, "KeyguardNotificationStackedRuler.prepareAddOrRemoveAnimationForOverlay.restore");
            companion.requestStackedScale(expandableView.getViewState(), 0.9f);
        }
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public float shelfPosition(AmbientState ambientState) {
        if (!this.shadeTransitionControllerEx.isAnimatingToCollapse()) {
            return getScreenHeight();
        }
        float screenHeight = getScreenHeight();
        float fViewTop = this.closeController.viewTop();
        return screenHeight > fViewTop ? fViewTop : screenHeight;
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public void updateStackedState(List<? extends ExpandableView> list, AmbientState ambientState, float f) {
        NotificationDisplayRectCallbackWrapper notificationDisplayRectCallbackWrapper;
        if (list.isEmpty()) {
            LockScreenNotificationRectManager.tryRequestNewHeight$default((LockScreenNotificationRectManager) this.lockScreenNotificationRectManager.get(), 3);
            Object obj = this.lockScreenExtStateController.get();
            LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = obj instanceof LockScreenNotificationExtStateManager ? (LockScreenNotificationExtStateManager) obj : null;
            if (lockScreenNotificationExtStateManager == null || (notificationDisplayRectCallbackWrapper = lockScreenNotificationExtStateManager.getNotificationDisplayRectCallbackWrapper()) == null) {
                return;
            }
            notificationDisplayRectCallbackWrapper.dispatchDisplayRectChanged(-1.0f, -1.0f);
            return;
        }
        getVisibleExpandableViews().clear();
        Collection visibleExpandableViews = getVisibleExpandableViews();
        for (Object obj2 : list) {
            ExpandableView expandableView = (ExpandableView) obj2;
            if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView)) {
                visibleExpandableViews.add(obj2);
            }
        }
        fixSectionHeaderPosition(f, list, true);
        LockScreenNotificationRectManager.tryRequestNewHeight$default((LockScreenNotificationRectManager) this.lockScreenNotificationRectManager.get(), 3);
        ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).updateViewStateToCurrentExtState(1, getVisibleExpandableViews());
        getVisibleExpandableViews().clear();
        fixSectionHeaderPosition(f, list, false);
    }
}
