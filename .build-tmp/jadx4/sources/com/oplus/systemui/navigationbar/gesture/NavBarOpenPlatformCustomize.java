package com.oplus.systemui.navigationbar.gesture;

import android.content.Context;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarOpenPlatformCustomize {
    public static final Companion Companion = new Companion();
    private static final String NAVIGATION_BAR_DISABLE = "persist.sys.oplus.nav_bar_disable";
    private static volatile NavBarOpenPlatformCustomize sInstance;
    private final OplusDevicepolicyManager.OplusDevicePolicyObserver isNavBarDisabledObserver;
    public volatile boolean isNavigationBarDisabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NavBarOpenPlatformCustomize getInstance(Context context) {
            NavBarOpenPlatformCustomize navBarOpenPlatformCustomize;
            NavBarOpenPlatformCustomize navBarOpenPlatformCustomize2 = NavBarOpenPlatformCustomize.sInstance;
            if (navBarOpenPlatformCustomize2 != null) {
                return navBarOpenPlatformCustomize2;
            }
            synchronized (NavBarOpenPlatformCustomize.class) {
                navBarOpenPlatformCustomize = NavBarOpenPlatformCustomize.sInstance;
                if (navBarOpenPlatformCustomize == null) {
                    navBarOpenPlatformCustomize = new NavBarOpenPlatformCustomize(context);
                    NavBarOpenPlatformCustomize.sInstance = navBarOpenPlatformCustomize;
                }
            }
            return navBarOpenPlatformCustomize;
        }
    }

    public NavBarOpenPlatformCustomize(Context context) {
        NavBarOpenPlatformCustomize$isNavBarDisabledObserver$1 navBarOpenPlatformCustomize$isNavBarDisabledObserver$1 = new NavBarOpenPlatformCustomize$isNavBarDisabledObserver$1();
        navBarOpenPlatformCustomize$isNavBarDisabledObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.isNavBarDisabledObserver = navBarOpenPlatformCustomize$isNavBarDisabledObserver$1;
        boolean z = false;
        if (context != null && OplusCustomizeRestrictionManager.getInstance(context).isNavigationBarDisabled()) {
            z = true;
        }
        this.isNavigationBarDisabled = z;
        OplusDevicepolicyManager.getInstance().registerOplusDevicepolicyObserver(NAVIGATION_BAR_DISABLE, navBarOpenPlatformCustomize$isNavBarDisabledObserver$1);
    }
}
