package com.oplus.systemui.separate;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.PanelType;
import com.oplus.systemui.separate.guide.OplusQsGuideEventExternalTransfer;
import dagger.Lazy;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateNotificationAndQSExImpl extends OplusSeparateNotificationAndQSEx {
    private final Context context;
    private final OplusQsGuideEventExternalTransfer guideEventTransfer;
    private final Lazy panelViewPager;
    private final Lazy panelViewPagerController;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final OplusSeparateQSManager separateQSManager;

    public OplusSeparateNotificationAndQSExImpl(Context context, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, Lazy lazy, Lazy lazy2, OplusSeparateQSManager oplusSeparateQSManager, OplusQsGuideEventExternalTransfer oplusQsGuideEventExternalTransfer) {
        super(context);
        this.context = context;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.panelViewPager = lazy;
        this.panelViewPagerController = lazy2;
        this.separateQSManager = oplusSeparateQSManager;
        this.guideEventTransfer = oplusQsGuideEventExternalTransfer;
    }

    public boolean canSlideHToQSOnStatusBar(PointF pointF) {
        return ((OplusPanelViewPagerController) this.panelViewPagerController.get()).canSlideHToQSOnStatusBar(pointF);
    }

    public void clickTile(ComponentName componentName) {
        this.separateQSManager.clickTile(componentName);
    }

    public final void collapseQSPanel(boolean z, boolean z2, boolean z3) {
        OplusSeparateQSManager.collapseQSPanel$default(this.separateQSManager, z, z2, z3, null, 8);
    }

    public final boolean enableSeparateNotificationAndQS() {
        return ((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
    }

    public final void expandQSPanel() {
        this.separateQSManager.expandQSPanel(true, false);
    }

    public float getBlurFraction() {
        return this.separateQSManager.getBlurFraction();
    }

    public Integer getBrightnessMirrorLayout() {
        return this.separateQSManager.getBrightnessMirrorLayout();
    }

    public IBinder getExcludeWindowTokens() {
        return this.separateQSManager.getExcludeWindowTokens();
    }

    public View getQSPanelView() {
        return ((OplusPanelViewPagerController) this.panelViewPagerController.get()).getQSPanelView();
    }

    public Integer getStatusBarTopSpacing() {
        return this.separateQSManager.getStatusBarTopSpacing();
    }

    public boolean handleExternalTouch(MotionEvent motionEvent) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).stopHorizontalSlideSwitchOnCollapse(null);
        return ((OplusPanelViewPager) this.panelViewPager.get()).dispatchTouchEvent(motionEvent);
    }

    public final void initPanelViewPagerController() {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).init();
    }

    public final boolean isBlurFractionAnimationRunning() {
        return this.separateQSManager.isBlurFractionAnimationRunning();
    }

    public final boolean isClosing() {
        return this.separateQSManager.isClosing();
    }

    public final boolean isDraggingX() {
        return ((OplusPanelViewPagerController) this.panelViewPagerController.get()).isDraggingX();
    }

    public final boolean isFractionAnimationRunning() {
        return this.separateQSManager.isFractionAnimationRunning();
    }

    public final boolean isFullyCollapsed() {
        return this.separateQSManager.isFullyCollapsed();
    }

    public final boolean isFullyExpanded() {
        return this.separateQSManager.isFullyExpanded();
    }

    public final boolean isInXOverscroll() {
        return ((OplusPanelViewPagerController) this.panelViewPagerController.get()).isInXOverScroll();
    }

    public final boolean isInputOnQsArea(float f) {
        OplusPanelViewPagerController oplusPanelViewPagerController = (OplusPanelViewPagerController) this.panelViewPagerController.get();
        boolean zIsRightArea = oplusPanelViewPagerController.isRightArea(f);
        if (oplusPanelViewPagerController.isQsOnLeft()) {
            return !zIsRightArea;
        }
        return zIsRightArea;
    }

    public final void notifyNTEndMotionEvent(float f, boolean z) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).notifyNTEndMotionEvent(f, z);
    }

    public final void notifySeparateQSEndMotionEvent(float f, boolean z) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).notifySeparateQSEndMotionEvent(f, z);
    }

    public final boolean onBackPressed() {
        return this.separateQSManager.onBackPressed();
    }

    public final void onScreenTurnedOff() {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).onScreenTurnedOff();
        this.guideEventTransfer.notifyScreenTurnedOff();
    }

    public final void onSeparateNotifyExpandChange(boolean z) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).onSeparateNotifyExpandChange(z);
    }

    public final void onSystemIconScaleChanged(float f) {
        this.separateQSManager.onSystemIconScaleChanged(f);
    }

    public void setBrightnessMirrorController(BrightnessMirrorController brightnessMirrorController) {
        this.separateQSManager.setBrightnessMirrorController(brightnessMirrorController);
    }

    public void setBrightnessMirrorVisible(boolean z) {
        this.separateQSManager.setBrightnessMirrorVisible(z);
    }

    public void setPanelViewAlpha(float f) {
        ((OplusPanelViewPager) this.panelViewPager.get()).setAlpha(f);
    }

    public void setTouchAndAnimationDisabled(boolean z) {
        this.separateQSManager.setTouchAndAnimationDisabled(z);
    }

    public StateFlow shouldHideStatusBar() {
        return this.separateQSManager.shouldHideStatusBar();
    }

    public final void showDeviceMonitoringDialog() {
        this.separateQSManager.showDeviceMonitoringDialog();
    }

    public void stopHorizontalSlideSwitchOnCollapse(PanelType panelType) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).stopHorizontalSlideSwitchOnCollapse(panelType);
    }

    public final void syncBlurFractionFromExternal(float f) {
        this.separateQSManager.syncBlurFractionFromExternal(f);
    }

    public final void updateNotifyExpandFraction(float f, boolean z) {
        ((OplusPanelViewPagerController) this.panelViewPagerController.get()).updateNotifyExpandFraction(f, false);
        this.guideEventTransfer.notifySeparateNotificationExpandState(f, z);
    }

    public final void updateQSExpandFraction(float f, float f2, float f3, boolean z) {
        boolean zIsKeyguardVisible$1 = ((OplusPanelViewPagerController) this.panelViewPagerController.get()).isKeyguardVisible$1();
        Lazy lazy = this.panelViewPagerController;
        if (zIsKeyguardVisible$1) {
            ((OplusPanelViewPagerController) lazy.get()).transferQNSwitchForKeyguard(f, f3, z);
        } else {
            ((OplusPanelViewPagerController) lazy.get()).updateQSExpandFraction(f, z, false, f2);
        }
        this.guideEventTransfer.notifySeparateQSExpandState(f, z);
    }
}
