package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.DependencyEx;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavGestureProcessObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static volatile NavGestureProcessObserver sInstance;
    private String navGestureGear = "";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NavGestureProcessObserver getInstance() {
            if (NavGestureProcessObserver.sInstance == null) {
                synchronized (NavGestureProcessObserver.class) {
                    if (NavGestureProcessObserver.sInstance == null) {
                        NavGestureProcessObserver.sInstance = new NavGestureProcessObserver();
                    }
                }
            }
            NavGestureProcessObserver navGestureProcessObserver = NavGestureProcessObserver.sInstance;
            navGestureProcessObserver.getClass();
            return navGestureProcessObserver;
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NavBarSettingsValueProxy.KEY_NAV_TYPE_PROGRESS_GESTURE_GEAR_USER_TYPE;
    }

    public final String getNavGestureProgressState() {
        return this.navGestureGear;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getSecureUri(getKey());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        this.navGestureGear = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNavBarGestureGearEx().getNavGestureGear();
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.navGestureGear = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNavBarGestureGearEx().getNavGestureGear();
    }
}
