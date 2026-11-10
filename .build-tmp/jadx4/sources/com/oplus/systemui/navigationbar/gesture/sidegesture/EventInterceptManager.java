package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.view.OplusWindowManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventInterceptManager {
    public static volatile EventInterceptManager sInstance;
    public OplusWindowManager mOplusWindowManager;

    public static EventInterceptManager getInstance() {
        if (sInstance == null) {
            synchronized (EventInterceptManager.class) {
                try {
                    if (sInstance == null) {
                        EventInterceptManager eventInterceptManager = new EventInterceptManager();
                        eventInterceptManager.mOplusWindowManager = null;
                        eventInterceptManager.mOplusWindowManager = new OplusWindowManager();
                        sInstance = eventInterceptManager;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }
}
