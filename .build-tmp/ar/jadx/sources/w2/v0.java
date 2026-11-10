package w2;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class v0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z0 f6440b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f6441a;

    static {
        l0 f0Var;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 36) {
            f0Var = new k0();
        } else if (i5 >= 35) {
            f0Var = new j0();
        } else if (i5 >= 34) {
            f0Var = new i0();
        } else if (i5 >= 31) {
            f0Var = new h0();
        } else if (i5 >= 30) {
            f0Var = new g0();
        } else {
            f0Var = i5 >= 29 ? new f0() : new e0();
        }
        f6440b = f0Var.b().f6456a.a().f6456a.b().f6456a.c();
    }

    public v0(z0 z0Var) {
        this.f6441a = z0Var;
    }

    public z0 a() {
        return this.f6441a;
    }

    public z0 b() {
        return this.f6441a;
    }

    public z0 c() {
        return this.f6441a;
    }

    public void d(View view) {
    }

    public List<Rect> e(int i5) {
        return Collections.EMPTY_LIST;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v0)) {
            return false;
        }
        v0 v0Var = (v0) obj;
        return r() == v0Var.r() && q() == v0Var.q() && Objects.equals(m(), v0Var.m()) && Objects.equals(k(), v0Var.k()) && Objects.equals(g(), v0Var.g());
    }

    public List<Rect> f(int i5) {
        return Collections.EMPTY_LIST;
    }

    public c g() {
        return null;
    }

    public r2.c h(int i5) {
        return r2.c.f5696e;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(r()), Boolean.valueOf(q()), m(), k(), g());
    }

    public r2.c i(int i5) {
        if ((i5 & 8) == 0) {
            return r2.c.f5696e;
        }
        throw new IllegalArgumentException("Unable to query the maximum insets for IME");
    }

    public r2.c j() {
        return m();
    }

    public r2.c k() {
        return r2.c.f5696e;
    }

    public r2.c l() {
        return m();
    }

    public r2.c m() {
        return r2.c.f5696e;
    }

    public r2.c n() {
        return m();
    }

    public void o(View view) {
    }

    public void p() {
    }

    public boolean q() {
        return false;
    }

    public boolean r() {
        return false;
    }

    public boolean s(int i5) {
        return true;
    }

    public void t(e eVar) {
    }

    public void u(r2.c[] cVarArr) {
    }

    public void v(z0 z0Var) {
    }

    public void w(r2.c cVar) {
    }

    public void x(int i5) {
    }

    public void y(Rect[][] rectArr) {
    }

    public void z(Rect[][] rectArr) {
    }
}
