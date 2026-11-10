package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavButtonsPositionObserver extends AbstractObserver {
    public static final NavButtonsPositionObserver INSTANCE = new NavButtonsPositionObserver();
    public static int navButtonPosition;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NavBarSettingsValueProxy.KEY_NAV_BUTTONS_POSITION;
    }

    public final int getNavButtonsPosition() {
        return navButtonPosition;
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
        navButtonPosition = NavBarSettingsValueProxy.Companion.getNavButtonsPosition(context);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        navButtonPosition = NavBarSettingsValueProxy.Companion.getNavButtonsPosition(context);
    }
}
