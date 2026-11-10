package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavStateObserver extends AbstractObserver {
    public static final NavStateObserver INSTANCE = new NavStateObserver();
    private static final String TAG = "NavStateObserver";
    public static int mNavState;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.observer.NavStateObserver$onChange$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // java.lang.Runnable
        public final void run() {
            OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.UI_MODE_CHANGED);
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NavBarSettingsValueProxy.KEY_NAV_STATE;
    }

    public final int getNavState() {
        return mNavState;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getSecureUri(getKey());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.UI_MODE_CHANGED);
        int navState = NavBarSettingsValueProxy.Companion.getNavState(context);
        mNavState = navState;
        OplusLockIconView$.ExternalSyntheticOutline0.m(navState, "onChange mNavState", TAG);
        super.onChange(z);
        SystemUIThreadUtil.getMainHandler().postDelayed(AnonymousClass1.INSTANCE, 500L);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        int navState = NavBarSettingsValueProxy.Companion.getNavState(context);
        mNavState = navState;
        OplusLockIconView$.ExternalSyntheticOutline0.m(navState, "onRegister mNavState", TAG);
    }
}
