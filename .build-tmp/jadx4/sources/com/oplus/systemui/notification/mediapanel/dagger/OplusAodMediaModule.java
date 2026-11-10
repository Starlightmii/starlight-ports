package com.oplus.systemui.notification.mediapanel.dagger;

import com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAodMediaModule {
    public IAodMediaAonController provideAodMediaAonController(Lazy lazy, Lazy lazy2) {
        NotificationFeatureOption.INSTANCE.getClass();
        return NotificationFeatureOption.isSensorhubAonAodEnable() ? (IAodMediaAonController) lazy2.get() : (IAodMediaAonController) lazy.get();
    }
}
