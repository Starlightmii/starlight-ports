package com.oplusos.systemui.common.concurrency;

import android.os.HandlerThread;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusThreadFactory {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String TAG = "OplusThreadFactory";

    HandlerThread buildNewBgThread(String str);

    HandlerThread buildNewThread(String str);

    HandlerThread buildNewThread(String str, int i);
}
