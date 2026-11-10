package com.oplus.systemui.notification.aod;

import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1 implements Runnable {
    public /* synthetic */ boolean $isAnim;
    public /* synthetic */ SmoothTransitionController.SmoothTransitionUiState $uiState;
    public /* synthetic */ OplusPanoramicAodCoreStartable this$0;

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", "onUiStateChanged curUiState=" + this.this$0.curUiState + " uiState " + this.$uiState + ", isAnim=" + this.$isAnim, null, 8);
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState = this.this$0.curUiState;
        SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState2 = this.$uiState;
        if (smoothTransitionUiState == smoothTransitionUiState2) {
            return;
        }
        this.this$0.setPanoramicAod(SmoothTransitionController.SmoothTransitionUiState.PANORAMIC_AOD == smoothTransitionUiState2);
        OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable = this.this$0;
        boolean z = oplusPanoramicAodCoreStartable.isPanoramicAod && !oplusPanoramicAodCoreStartable.needAnimation;
        oplusPanoramicAodCoreStartable.curUiState = this.$uiState;
        OplusPanoramicAodCoreStartable.access$onPanoramicAodChanged(this.this$0, z);
        if (this.this$0.isPanoramicAod) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", "onUiStateChanged isPanoramicAod change setAnimationsEnabled", null, 8);
            this.this$0.setAnimationsEnabled(true);
        }
        ((VisualStabilityCoordinator) this.this$0.visualStabilityCoordinator.get()).updateAllowedStates(SeedlingMediaDataHandleUtils.KEY_IS_PANORAMIC_AOD, this.this$0.isPanoramicAod, false);
    }
}
