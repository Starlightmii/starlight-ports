package com.oplus.systemui.navigationbar;

import com.android.systemui.navigationbar.NavigationBarGestureEx;
import com.android.systemui.navigationbar.NavigationBarSubDisplayEx;
import com.oplus.systemui.navigationbar.eximpl.NavigationBarGestureExImpl;
import com.oplus.systemui.navigationbar.eximpl.NavigationBarSubDisplayExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NavigationBarControllerModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NavigationBarGestureEx providesNavigationBarGesture() {
            return new NavigationBarGestureExImpl();
        }

        public final NavigationBarSubDisplayEx providesNavigationBarSubDisplay() {
            return new NavigationBarSubDisplayExImpl();
        }
    }
}
