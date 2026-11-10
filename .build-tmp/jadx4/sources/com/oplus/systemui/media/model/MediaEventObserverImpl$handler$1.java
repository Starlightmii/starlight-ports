package com.oplus.systemui.media.model;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaEventObserverImpl$handler$1 implements Handler.Callback {
    public /* synthetic */ MediaEventObserverImpl this$0;

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        return this.this$0.dealHandlerMsg(message);
    }
}
