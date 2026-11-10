package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.content.Context;
import android.util.Log;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler;
import com.oplus.systemui.navigationbar.gesture.NavBarOpenPlatformCustomize;
import com.oplus.systemui.navigationbar.gesture.manager.OplusNavigationBarManager;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSideGesturePreProcess {
    public static final OplusSideGesturePreProcess INSTANCE = new OplusSideGesturePreProcess();

    public static final boolean checkSideGesture(Context context, int i, boolean z) {
        OplusNavigationBarManager.getInstance().setSideGestureDetectToView(false);
        if ((AppSettingsHandler.getInstance(context).mDisableFlags & 16) != 0 || (AppSettingsHandler.getInstance(context).mDisableFlags & 2) != 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "NoBackGesture-->disable side gesture because disable flag");
            }
            OplusNavigationBarManager.getInstance().setSideGestureDetectToView(true);
            return false;
        }
        if (NavBarOpenPlatformCustomize.Companion.getInstance(context).isNavigationBarDisabled) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "NoBackGesture-->NavigationBar is disabled by customized API");
            }
            OplusNavigationBarManager.getInstance().setSideGestureDetectToView(true);
            return false;
        }
        if (NavStateObserver.INSTANCE.getNavState() != 2) {
            if (i >= 0) {
                return true;
            }
            OplusNavigationBarManager.getInstance().setSideGestureDetectToView(true);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "NoBackGesture-->disable side gesture because point is not side gesture range");
            }
        }
        return false;
    }
}
