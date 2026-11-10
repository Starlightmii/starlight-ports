package com.oplus.systemui.statusbar.dagger;

import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.PulseExpansionHandlerExt;
import com.android.systemui.statusbar.phone.OplusCentralSurfacesEx;
import com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl;
import com.oplus.systemui.statusbar.PulseExpansionHandlerExtImpl;
import com.oplus.systemui.statusbar.phone.OplusCentralSurfacesExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CentralSurfacesModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final OplusCentralSurfacesEx provideCentralSurfacesEx(OplusCentralSurfacesExImpl oplusCentralSurfacesExImpl) {
            return oplusCentralSurfacesExImpl;
        }

        public final OplusLockscreenShadeTransitionControllerEx provideLockscreenShadeTransitionControllerEx(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl) {
            return oplusLockscreenShadeTransitionControllerExImpl;
        }

        public final PulseExpansionHandlerExt providePulseExpansionHandlerExt(PulseExpansionHandlerExtImpl pulseExpansionHandlerExtImpl) {
            return pulseExpansionHandlerExtImpl;
        }
    }
}
