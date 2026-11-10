package e1;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements m.r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f1910d;

    public f() {
        this.f1910d = new ArrayList(32);
    }

    public f(float f5, float f6, m.q qVar) {
        e4.d dVarN = d4.a.N(0, qVar.b());
        ArrayList arrayList = new ArrayList(q3.m.S(dVarN));
        Iterator it = dVarN.iterator();
        while (true) {
            e4.c cVar = (e4.c) it;
            if (!cVar.f2079f) {
                this.f1910d = arrayList;
                return;
            }
            arrayList.add(new m.z(f5, f6, qVar.a(cVar.nextInt())));
        }
    }

    public void a() {
        this.f1910d.add(i.f1922b);
    }

    public void b(float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f1910d.add(new j(f5, f6, f7, f8, f9, f10));
    }

    public void c(float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f1910d.add(new o(f5, f6, f7, f8, f9, f10));
    }

    public void d(float f5) {
        this.f1910d.add(new k(f5));
    }

    public void e(float f5) {
        this.f1910d.add(new p(f5));
    }

    public void f(float f5, float f6) {
        this.f1910d.add(new l(f5, f6));
    }

    public void g(float f5, float f6) {
        this.f1910d.add(new q(f5, f6));
    }

    @Override // m.r
    public m.y get(int i5) {
        return (m.z) this.f1910d.get(i5);
    }

    public void h(float f5, float f6) {
        this.f1910d.add(new m(f5, f6));
    }

    public void i(float f5, float f6, float f7, float f8) {
        this.f1910d.add(new n(f5, f6, f7, f8));
    }

    public void j(float f5, float f6, float f7, float f8) {
        this.f1910d.add(new r(f5, f6, f7, f8));
    }

    public void k(float f5) {
        this.f1910d.add(new t(f5));
    }

    public void l(float f5) {
        this.f1910d.add(new s(f5));
    }
}
