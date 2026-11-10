package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SwipeUpGestureMistouchPreventionObserver extends AbstractObserver {
    public static final SwipeUpGestureMistouchPreventionObserver INSTANCE = new SwipeUpGestureMistouchPreventionObserver();
    public static int swipeUpGestureMistouchPrevention;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_MISTOUCH_PREVENTION_ENABLE;
    }

    public final int getSwipeUpGestureMistouchPrevention() {
        return swipeUpGestureMistouchPrevention;
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
        swipeUpGestureMistouchPrevention = NavBarSettingsValueProxy.Companion.getSwipeUpGestureMistouchPreventionEnable(context);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        swipeUpGestureMistouchPrevention = NavBarSettingsValueProxy.Companion.getSwipeUpGestureMistouchPreventionEnable(context);
    }
}
