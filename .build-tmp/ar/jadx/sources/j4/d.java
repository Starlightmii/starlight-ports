package j4;

import android.os.Handler;
import android.os.Looper;
import b4.l;
import f0.e2;
import i4.b0;
import i4.e0;
import i4.h;
import i4.r;
import i4.s;
import i4.s0;
import java.util.concurrent.CancellationException;
import n4.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends r implements b0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f3348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f3349g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3350h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f3351i;

    public d(Handler handler) {
        this(handler, null, false);
    }

    public d(Handler handler, String str, boolean z2) {
        this.f3348f = handler;
        this.f3349g = str;
        this.f3350h = z2;
        this.f3351i = z2 ? this : new d(handler, str, true);
    }

    @Override // i4.b0
    public final void d(long j5, h hVar) {
        c cVar = new c(hVar, this);
        if (j5 > 4611686018427387903L) {
            j5 = 4611686018427387903L;
        }
        if (this.f3348f.postDelayed(cVar, j5)) {
            hVar.s(new e2(this, 2, cVar));
        } else {
            n(hVar.f2736h, cVar);
        }
    }

    @Override // i4.r
    public final void e(t3.h hVar, Runnable runnable) {
        if (this.f3348f.post(runnable)) {
            return;
        }
        n(hVar, runnable);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return dVar.f3348f == this.f3348f && dVar.f3350h == this.f3350h;
    }

    @Override // i4.r
    public final boolean h(t3.h hVar) {
        return (this.f3350h && l.a(Looper.myLooper(), this.f3348f.getLooper())) ? false : true;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f3348f) ^ (this.f3350h ? 1231 : 1237);
    }

    public final void n(t3.h hVar, Runnable runnable) {
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        s0 s0Var = (s0) hVar.j(s.f2774e);
        if (s0Var != null) {
            s0Var.b(cancellationException);
        }
        p4.e eVar = e0.f2727a;
        p4.d.f5058f.e(hVar, runnable);
    }

    @Override // i4.r
    public final String toString() {
        d dVar;
        String str;
        p4.e eVar = e0.f2727a;
        d dVar2 = m.f4303a;
        if (this == dVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                dVar = dVar2.f3351i;
            } catch (UnsupportedOperationException unused) {
                dVar = null;
            }
            str = this == dVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.f3349g;
        if (string == null) {
            string = this.f3348f.toString();
        }
        if (!this.f3350h) {
            return string;
        }
        return string + ".immediate";
    }
}
