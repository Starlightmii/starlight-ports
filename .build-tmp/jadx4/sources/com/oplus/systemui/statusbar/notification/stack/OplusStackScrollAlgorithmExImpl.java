package com.oplus.systemui.statusbar.notification.stack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.DependencyEx;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.OplusStackScrollAlgorithmEx;
import com.android.systemui.statusbar.notification.stack.StackScrollAlgorithm;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.blur.OplusNotificationTiltShiftBlurController;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackScrollAlgorithmExImpl extends OplusStackScrollAlgorithmEx implements OplusNotificationEx {
    private final HeadsUpManager headsUpManager;
    private final List<LifecycleListener> listeners = new ArrayList();
    private final OplusLockscreenShadeTransitionControllerEx lockscreenNotificationTransitionController;
    public float mNotificationMarginWithScrim;
    public float mQuickQsOffsetHeight;
    private final Lazy materialBlurStateManager;
    public int notGoneIndexWithOutChildren;
    private final Lazy notificationMaterialColor;
    private final OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController;
    private final Lazy oplusPanoramicAodRepository;
    private final Lazy oplusSimpleQSManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface LifecycleListener {
    }

    public OplusStackScrollAlgorithmExImpl(HeadsUpManager headsUpManager, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.headsUpManager = headsUpManager;
        this.lockscreenNotificationTransitionController = oplusLockscreenShadeTransitionControllerEx;
        this.oplusNotificationTiltShiftBlurController = oplusNotificationTiltShiftBlurController;
        this.oplusPanoramicAodRepository = lazy;
        this.materialBlurStateManager = lazy2;
        this.notificationMaterialColor = lazy3;
        this.oplusSimpleQSManager = lazy4;
    }

    public final void addListener(LifecycleListener lifecycleListener) {
        if (this.listeners.contains(lifecycleListener)) {
            return;
        }
        this.listeners.add(lifecycleListener);
    }

    public void afterResetViewStates(ViewGroup viewGroup, StackScrollAlgorithm.StackScrollAlgorithmState stackScrollAlgorithmState, AmbientState ambientState) {
        OplusExpandableViewEx oplusExpandableViewEx;
        ((NotificationMaterialColorEx) this.notificationMaterialColor.get()).updateNotificationColorMode(stackScrollAlgorithmState.visibleChildren, ambientState);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableView childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ExpandableView) && (oplusExpandableViewEx = childAt.mExpandableViewEx) != null) {
                ((OplusExpandableViewExImpl) oplusExpandableViewEx).invalidateResetPassCache(false);
            }
        }
    }

    public float calculateNotificationStackDrawStart(ViewGroup viewGroup, AmbientState ambientState) {
        int ntfTopPaddingInLockscreenNtfCenter;
        if (ambientState == null) {
            return 0.0f;
        }
        if (!ambientState.isOnKeyguard()) {
            if (!(viewGroup instanceof NotificationStackScrollLayout)) {
                return super.calculateNotificationStackDrawStart(viewGroup, ambientState);
            }
            float stackY = ambientState.getStackY() - ambientState.getScrollY();
            float notificationMarginWithScrim = getNotificationMarginWithScrim();
            float f = stackY + notificationMarginWithScrim;
            if (((NotificationStackScrollLayout) viewGroup).mShouldUseSplitNotificationShade) {
                if (f < notificationMarginWithScrim) {
                    return notificationMarginWithScrim;
                }
            } else if (this.lockscreenNotificationTransitionController.isOnLockscreenNtfCenter()) {
                ntfTopPaddingInLockscreenNtfCenter = this.lockscreenNotificationTransitionController.getNtfTopPaddingInLockscreenNtfCenter();
            } else {
                float quickQsOffsetHeight = getQuickQsOffsetHeight() + notificationMarginWithScrim;
                if (f < quickQsOffsetHeight) {
                    return quickQsOffsetHeight;
                }
            }
            return f;
        }
        if (!ambientState.mDozing || !((OplusPanoramicAodCoreStartable) ((OplusPanoramicAodRepository) this.oplusPanoramicAodRepository.get())).isPanoramicAod) {
            return 0.0f;
        }
        ntfTopPaddingInLockscreenNtfCenter = this.lockscreenNotificationTransitionController.getNtfTopPaddingInLockscreenNtfCenter();
        return ntfTopPaddingInLockscreenNtfCenter;
    }

    public int getClipBottomAmount(boolean z, ExpandableView expandableView) {
        if (z) {
            return 0;
        }
        return expandableView.getClipBottomAmount();
    }

    public float getMNotificationMarginWithScrim() {
        return this.mNotificationMarginWithScrim;
    }

    public float getMQuickQsOffsetHeight() {
        return this.mQuickQsOffsetHeight;
    }

    public float getNotificationMarginWithScrim() {
        return SeparateModeHelper.INSTANCE.getSeparateMode() ? ((OplusSimpleQSManager) this.oplusSimpleQSManager.get()).getTopMargin() : getMNotificationMarginWithScrim();
    }

    public float getNotificationScrimPadding(Context context) {
        return SeparateModeHelper.INSTANCE.getSeparateMode() ? ((OplusSimpleQSManager) this.oplusSimpleQSManager.get()).getTopMargin() : context.getResources().getDimensionPixelSize(2131170783);
    }

    public float getQuickQsOffsetHeight() {
        return SeparateModeHelper.INSTANCE.getSeparateMode() ? ((OplusSimpleQSManager) this.oplusSimpleQSManager.get()).getQSOffsetHeight() : getMQuickQsOffsetHeight();
    }

    public final float getShelfStart(float f, float f2, int i) {
        return f - i;
    }

    public boolean getTiltShiftBlurEnabled() {
        return ((MaterialBlurStateManager) this.materialBlurStateManager.get()).getTiltShiftBlurEnabled() && this.oplusNotificationTiltShiftBlurController.isShiftBlurViewVisible();
    }

    public final boolean hasPinnedHeadsUp() {
        return this.headsUpManager.hasPinnedHeadsUp();
    }

    public void hidingSeedlingContainerIfNeeded(View view, ViewGroup viewGroup) {
        if (isSeedlingContainer(view) && (viewGroup instanceof NotificationStackScrollLayout)) {
            NotificationPanelViewController notificationPanelController = ((NotificationStackScrollLayout) viewGroup).getNotificationPanelController();
            if (notificationPanelController.mHeadsUpAnimatingAway || notificationPanelController.mExpandingFromHeadsUp || (this.headsUpManager.hasPinnedHeadsUp() && notificationPanelController.isFullyCollapsed())) {
                ((ViewState) ((ExpandableView) view).getViewState()).hidden = true;
            }
        }
    }

    public final void initAlgorithmStateStart() {
        this.notGoneIndexWithOutChildren = 0;
    }

    public void initView(Context context) {
        setMNotificationMarginWithScrim(context.getResources().getDimensionPixelSize(2131170783));
        setMQuickQsOffsetHeight(context.getResources().getDimensionPixelSize(2131170763));
    }

    public boolean isSeedlingContainer(View view) {
        return view instanceof OplusCustomRow;
    }

    public final void removeListener(LifecycleListener lifecycleListener) {
        this.listeners.remove(lifecycleListener);
    }

    public void setMNotificationMarginWithScrim(float f) {
        this.mNotificationMarginWithScrim = f;
    }

    public void setMQuickQsOffsetHeight(float f) {
        this.mQuickQsOffsetHeight = f;
    }

    public void setYTranslation(ExpandableViewState expandableViewState, float f, ExpandableNotificationRow expandableNotificationRow, ViewGroup viewGroup) {
        if (expandableNotificationRow.mIsHeadsUp) {
            return;
        }
        float yTranslation = -expandableViewState.height;
        if (yTranslation > 0.0f) {
            yTranslation = 0.0f;
        }
        if (viewGroup instanceof NotificationStackScrollLayout) {
            NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) viewGroup;
            if (notificationStackScrollLayout.mShouldUseSplitNotificationShade) {
                yTranslation = expandableViewState.getYTranslation() - notificationStackScrollLayout.getNotificationPanelController().getExt().getSplitStatusBarHeight(true, notificationStackScrollLayout.getContext().getResources());
            }
        }
        expandableViewState.setYTranslation(yTranslation, "OSSAEI.setYTranslation");
    }

    public final void updateLifecycle() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((NotificationAodTransition) ((LifecycleListener) it.next())).onStateChange();
        }
    }

    public final void updateNotGoneCount() {
        ((NotificationStackScrollLayoutExtImpl) ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class))).updateScrollGridCount();
    }

    public void updateNotGoneIndex(StackScrollAlgorithm.StackScrollAlgorithmState stackScrollAlgorithmState, ExpandableView expandableView) {
        expandableView.getViewState().mExpandableViewStateEx.setNotGoneIndexWithOutChildren(this.notGoneIndexWithOutChildren);
        this.notGoneIndexWithOutChildren++;
    }
}
