package com.oplus.systemui.keyguard.watcher;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.statusbar.policy.CallbackController;
import com.oplus.keyguard.utils.KeyguardUtils;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardWatcher implements CallbackController {
    public static final Companion Companion = new Companion();
    private final ConcurrentLinkedQueue<Observer> mObserver;
    public boolean mOplusKeyguardLoad;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusKeyguardWatcher getInstance() {
            return Inner.INSTANCE.getINSTANCE();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Inner {
        public static final Inner INSTANCE = new Inner();
        private static final OplusKeyguardWatcher INSTANCE$1 = new OplusKeyguardWatcher();

        public final OplusKeyguardWatcher getINSTANCE() {
            return INSTANCE$1;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Observer {
        default void onOplusKeyguardLoad(boolean z) {
        }

        default void onSetBackground(Drawable drawable) {
        }
    }

    public OplusKeyguardWatcher() {
        com.oplusos.systemui.keyguard.watcher.OplusKeyguardWatcher.getInstance();
        this.mObserver = new ConcurrentLinkedQueue<>();
    }

    public void addCallback(Observer observer) {
        if (this.mObserver.contains(observer)) {
            return;
        }
        this.mObserver.add(observer);
    }

    public final void dispatchOplusKeyguardLoad(boolean z, Context context) {
        if (z == this.mOplusKeyguardLoad) {
            return;
        }
        this.mOplusKeyguardLoad = z;
        KeyguardUtils.Companion.getKeyguardUtils(context).setOplusKeyguardLoad(z);
        Iterator<Observer> it = this.mObserver.iterator();
        while (it.hasNext()) {
            it.next().onOplusKeyguardLoad(z);
        }
    }

    public final void dispatchSetBackground(Drawable drawable) {
        Iterator<Observer> it = this.mObserver.iterator();
        while (it.hasNext()) {
            it.next().onSetBackground(drawable);
        }
    }

    public void removeCallback(Observer observer) {
        if (this.mObserver.contains(observer)) {
            this.mObserver.remove(observer);
        }
    }
}
