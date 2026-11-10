package com.oplus.systemui.notification.mediapanel.aon;

import com.oplusos.systemui.aod.mediapanel.AodMediaController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IAodMediaAonController {
    public static final Companion Companion = Companion.$$INSTANCE;

    void applyModeOrState(int i);

    void bindAodMediaController(AodMediaController aodMediaController);

    void onAodMediaVisibilityChanged(boolean z);

    void resetFaceGazeHEIfNeed();
}
