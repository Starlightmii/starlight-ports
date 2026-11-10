package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.navigationbar.NavigationModeControllerEx;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationModeControllerExImpl extends NavigationModeControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NavigationModeControllerExImpl";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NavigationModeControllerExImpl(Context context) {
        super(context);
        this.context = context;
    }

    public static void updateNavState(int i, Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "updateNavState, state=", "NavigationModeControllerExImpl-->", "SystemUi--NavBar");
        }
        NavBarSettingsValueProxy.Companion.setNavState(context, i);
    }

    public final Context getContext() {
        return this.context;
    }

    public void updateNavStateFromMode(Context context, int i) {
        NavBarSettingsValueProxy.Companion companion = NavBarSettingsValueProxy.Companion;
        if (companion.getKIDSMode(context, OsBinderCacheUtils.getCurrentUserId()) == 1 || OpUtils.sIsClosedSuperFirewall) {
            int navState = companion.getNavState(context);
            if (i == 0) {
                if (navState != 0) {
                    updateNavState(0, context);
                }
            } else {
                if (i != 2 || navState == 2 || navState == 3) {
                    return;
                }
                updateNavState(3, context);
            }
        }
    }
}
