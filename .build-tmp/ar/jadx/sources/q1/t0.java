package q1;

import android.os.Looper;
import android.view.Choreographer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 extends ThreadLocal {
    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        Choreographer choreographer = Choreographer.getInstance();
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            throw new IllegalStateException("no Looper on this thread");
        }
        v0 v0Var = new v0(choreographer, p3.j.p(looperMyLooper));
        return p3.p.A(v0Var, v0Var.f5435o);
    }
}
