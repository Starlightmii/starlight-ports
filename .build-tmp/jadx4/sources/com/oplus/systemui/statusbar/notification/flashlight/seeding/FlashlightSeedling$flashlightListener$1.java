package com.oplus.systemui.statusbar.notification.flashlight.seeding;

import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightSeedling$flashlightListener$1 implements FlashlightController.FlashlightListener {
    public final void onFlashlightAvailabilityChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_COMMON, "FlashlightSeedling", KeyguardLogger$.ExternalSyntheticOutline0.m("flashlightListener, available=", z), null, 8);
        if (z) {
            return;
        }
        FlashlightTip.INSTANCE.setFlashlightOn(false);
    }

    public final void onFlashlightChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_COMMON, "FlashlightSeedling", KeyguardLogger$.ExternalSyntheticOutline0.m("flashlightListener, enabled=", z), null, 8);
        FlashlightTip.INSTANCE.setFlashlightOn(z);
    }

    public final void onFlashlightError() {
    }
}
