package com.oplus.systemui.notification.icdf.helper;

import android.content.Context;
import com.oplus.systemui.common.observer.ICDFSmartConnectionSwitchObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ICDFConnectionSwitchStateImpl implements ICDFConnectionSwitchState {
    private final Context context;

    public ICDFConnectionSwitchStateImpl(Context context) {
        this.context = context;
    }

    @Override // com.oplus.systemui.notification.icdf.helper.ICDFConnectionSwitchState
    public boolean getConnectionPadSwitchOn() {
        ICDFSmartConnectionSwitchObserver companion = ICDFSmartConnectionSwitchObserver.Companion.getInstance();
        if (companion != null) {
            return companion.getConnectionPadSwitchOn();
        }
        return false;
    }

    @Override // com.oplus.systemui.notification.icdf.helper.ICDFConnectionSwitchState
    public boolean getConnectionPcSwitchOn() {
        ICDFSmartConnectionSwitchObserver companion = ICDFSmartConnectionSwitchObserver.Companion.getInstance();
        if (companion != null) {
            return companion.getConnectionPCSwitchOn();
        }
        return false;
    }
}
