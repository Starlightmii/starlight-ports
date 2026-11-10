package com.oplus.systemui.qs.tileex;

import android.os.SystemClock;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.oplus.systemui.common.OplusSwitchCameraPreviewStartable;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CameraTileEx extends QSTileExImpl {
    private final OplusSwitchCameraPreviewStartable cameraPreviewStartable;

    public CameraTileEx(QSTileExParams qSTileExParams, OplusSwitchCameraPreviewStartable oplusSwitchCameraPreviewStartable) {
        super(qSTileExParams);
        this.cameraPreviewStartable = oplusSwitchCameraPreviewStartable;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public final void onClick() {
        QSTileExImpl.requestUxForTileThread();
        if (this.cameraPreviewStartable.getHasCalledPreviewStatusFalse()) {
            String tag = getTAG();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->onClick: Camera tile clicked", "SystemUi--QuickSettings");
            }
            this.cameraPreviewStartable.updateCameraPreviewStatus(1, SystemClock.uptimeMillis(), true);
        }
    }
}
