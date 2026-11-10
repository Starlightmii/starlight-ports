package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.os.Looper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyDndService extends KeyBaseService {
    public static final Companion Companion = new Companion();
    private static final String TAG = "keyDndService";
    private final KeyDndController keyDndController;

    public KeyDndService(KeyDndController keyDndController, Looper looper) {
        super(looper, keyDndController);
        this.keyDndController = keyDndController;
    }
}
