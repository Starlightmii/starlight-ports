package e1;

import java.util.List;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends b0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f1897d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s0 f1898e;

    public d0(List list, s0 s0Var) {
        this.f1897d = list;
        this.f1898e = s0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d0.class != obj.getClass()) {
            return false;
        }
        d0 d0Var = (d0) obj;
        return this.f1898e.equals(d0Var.f1898e) && b4.l.a(this.f1897d, d0Var.f1897d);
    }

    public final int hashCode() {
        return Integer.hashCode(0) + b.b.a(0.0f, b.b.a(1.0f, b.b.a(0.0f, b.b.a(1.0f, m.h.b(2, m.h.b(0, b.b.a(1.0f, b.b.a(1.0f, b.b.a(1.0f, (this.f1898e.hashCode() + (this.f1897d.hashCode() * 31)) * 31, 961), 31), 31), 31), 31), 31), 31), 31), 31);
    }
}
