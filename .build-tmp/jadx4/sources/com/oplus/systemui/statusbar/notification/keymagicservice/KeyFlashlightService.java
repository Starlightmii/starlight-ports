package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.os.Looper;
import com.android.systemui.statusbar.policy.FlashlightController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyFlashlightService extends KeyBaseService {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyFlashlightService";
    private final KeyFlashlightController flashlightController;
    private final FlashlightController flashlightControllerInit;

    public KeyFlashlightService(Looper looper, KeyFlashlightController keyFlashlightController, FlashlightController flashlightController) {
        super(looper, keyFlashlightController);
        this.flashlightController = keyFlashlightController;
        this.flashlightControllerInit = flashlightController;
    }
}
