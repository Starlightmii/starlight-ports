package com.oplusos.systemui.keyguard.watcher;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusKeyguardWatcher {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Inner {
        public static final OplusKeyguardWatcher INSTANCE = new OplusKeyguardWatcher(0);
    }

    public /* synthetic */ OplusKeyguardWatcher(int i) {
        this();
    }

    public static OplusKeyguardWatcher getInstance() {
        return Inner.INSTANCE;
    }

    public void dispatchSetBackground(Drawable drawable) {
        com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher.Companion.getInstance().dispatchSetBackground(drawable);
    }

    private OplusKeyguardWatcher() {
    }
}
