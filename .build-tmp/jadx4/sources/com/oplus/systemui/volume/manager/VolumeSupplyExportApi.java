package com.oplus.systemui.volume.manager;

import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSupplyExportApi {
    public static final VolumeSupplyExportApi INSTANCE = new VolumeSupplyExportApi();
    private static final String TAG = "VolumeSupplyExportApi";
    private static KeyguardStateController mKeyguardStateController;
    private static OplusVolumeDialogImpl mOplusVolumeDialogImpl;

    private final KeyguardStateController getKeyguardStateController() {
        if (mKeyguardStateController == null) {
            mKeyguardStateController = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        }
        return mKeyguardStateController;
    }

    public final boolean getVolumeDialogIsExpand() {
        OplusVolumeDialogImpl oplusVolumeDialogImpl = mOplusVolumeDialogImpl;
        if (oplusVolumeDialogImpl == null) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "VolumeSupplyExportApi-->".concat(" getVolumeDialog mExpanded = " + oplusVolumeDialogImpl.getExpandedState()));
        }
        return oplusVolumeDialogImpl.getExpandedState().booleanValue();
    }

    public final void notifyVolumeDialogIsExpandChanged() {
        KeyguardStateController keyguardStateController = getKeyguardStateController();
        if (keyguardStateController == null || !keyguardStateController.isVisible()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, " notifyVolumeDialogIsExpandChanged ", null, 8);
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).updateFingerprintListeningState(2);
    }

    public final void setOplusVolumeDialogImpl(OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        mOplusVolumeDialogImpl = oplusVolumeDialogImpl;
    }
}
