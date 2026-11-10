package com.oplus.systemui.volume;

import android.os.Trace;
import androidx.slice.widget.RowView$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.topui.TopUiController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ OplusVolumeDialogImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        boolean z = false;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                OplusVolumeDialogImpl oplusVolumeDialogImpl = (OplusVolumeDialogImpl) obj;
                oplusVolumeDialogImpl.mShowA11yStream = oplusVolumeDialogImpl.mAccessibilityMgr.mAccessibilityManager.isAccessibilityVolumeStreamActive();
                return;
            case 1:
                OplusVolumeDialogImpl oplusVolumeDialogImpl2 = (OplusVolumeDialogImpl) obj;
                if (!oplusVolumeDialogImpl2.isShowing() || oplusVolumeDialogImpl2.mPreventSeekBar) {
                    return;
                }
                if (!oplusVolumeDialogImpl2.mOplusVolumeDialogView.isSliderShowing() || oplusVolumeDialogImpl2.mOplusVolumeDialogView.isDismissing()) {
                    LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", "completeUserSwitchDelayedSliderShow");
                    oplusVolumeDialogImpl2.mOplusVolumeDialogView.showSliderVolume(oplusVolumeDialogImpl2.getActiveRow());
                    VolumeDialogController volumeDialogController = oplusVolumeDialogImpl2.mController;
                    volumeDialogController.notifyVisible(true);
                    volumeDialogController.getCaptionsComponentState(false);
                    return;
                }
                return;
            case 2:
                OplusVolumeDialogImpl oplusVolumeDialogImpl3 = (OplusVolumeDialogImpl) obj;
                AccessibilityManagerWrapper accessibilityManagerWrapper = oplusVolumeDialogImpl3.mAccessibilityMgr;
                if (accessibilityManagerWrapper != null && accessibilityManagerWrapper.mAccessibilityManager.isAccessibilityVolumeStreamActive()) {
                    z = true;
                }
                ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_VOLUME, "OplusVolumeDialogImpl", RowView$.ExternalSyntheticOutline0.m("onA11yServicesStateChanged#isA11yStreamActive=", ", mShowA11yStream=", z), oplusVolumeDialogImpl3.mShowA11yStream);
                if (z != oplusVolumeDialogImpl3.mShowA11yStream) {
                    oplusVolumeDialogImpl3.onAccessibilityModeChanged(Boolean.valueOf(z));
                    return;
                }
                return;
            case 3:
                ((OplusVolumeDialogImpl) obj).mOplusVolumeDialogView.tryFlushPendingSideStructuralUpdate();
                return;
            default:
                OplusVolumeDialogImpl.SetTopUiRunnable setTopUiRunnable = (OplusVolumeDialogImpl.SetTopUiRunnable) obj;
                Trace.traceBegin(8L, "setHasTopUi:" + setTopUiRunnable.mHasTopUi);
                try {
                    TopUiController topUiController = setTopUiRunnable.mTopUiController;
                    if (topUiController != null) {
                        topUiController.setRequestTopUi(setTopUiRunnable.mHasTopUi, "OplusVolumeDialogImpl");
                        break;
                    }
                    return;
                } finally {
                    Trace.traceEnd(8L);
                }
        }
    }
}
