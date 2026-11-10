package f0;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f2319a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2320b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2321c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashSet f2322d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashSet f2323e = new LinkedHashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f2324f = new s1(n0.h.f4069g, h.f2187h);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ v f2325g;

    public t(v vVar, long j5, boolean z2, boolean z3, a1.d dVar) {
        this.f2325g = vVar;
        this.f2319a = j5;
        this.f2320b = z2;
        this.f2321c = z3;
    }

    @Override // f0.b0
    public final void a(e0 e0Var, n0.d dVar) {
        this.f2325g.f2355b.a(e0Var, dVar);
    }

    @Override // f0.b0
    public final void b() {
        this.f2325g.A--;
    }

    @Override // f0.b0
    public final boolean c() {
        return this.f2325g.f2355b.c();
    }

    @Override // f0.b0
    public final boolean d() {
        return this.f2320b;
    }

    @Override // f0.b0
    public final boolean e() {
        return this.f2321c;
    }

    @Override // f0.b0
    public final long f() {
        return this.f2319a;
    }

    @Override // f0.b0
    public final a0 g() {
        return this.f2325g.f2361h;
    }

    @Override // f0.b0
    public final w1 h() {
        return (w1) this.f2324f.getValue();
    }

    @Override // f0.b0
    public final t3.h i() {
        return this.f2325g.f2355b.i();
    }

    @Override // f0.b0
    public final boolean j() {
        return this.f2325g.f2355b.j();
    }

    @Override // f0.b0
    public final void k(e0 e0Var) {
        v vVar = this.f2325g;
        vVar.f2355b.k(vVar.f2361h);
        vVar.f2355b.k(e0Var);
    }

    @Override // f0.b0
    public final f1 l(g1 g1Var) {
        return this.f2325g.f2355b.l(g1Var);
    }

    @Override // f0.b0
    public final void m(Set set) {
        HashSet hashSet = this.f2322d;
        if (hashSet == null) {
            hashSet = new HashSet();
            this.f2322d = hashSet;
        }
        hashSet.add(set);
    }

    @Override // f0.b0
    public final void n(v vVar) {
        this.f2323e.add(vVar);
    }

    @Override // f0.b0
    public final void o(e0 e0Var) {
        this.f2325g.f2355b.o(e0Var);
    }

    @Override // f0.b0
    public final void p() {
        this.f2325g.A++;
    }

    @Override // f0.b0
    public final void q(p pVar) {
        HashSet<Set> hashSet = this.f2322d;
        if (hashSet != null) {
            for (Set set : hashSet) {
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl", pVar);
                set.remove(((v) pVar).v());
            }
        }
        LinkedHashSet linkedHashSet = this.f2323e;
        b4.z.a(linkedHashSet);
        linkedHashSet.remove(pVar);
    }

    @Override // f0.b0
    public final void r(e0 e0Var) {
        this.f2325g.f2355b.r(e0Var);
    }

    public final void s() {
        LinkedHashSet<v> linkedHashSet = this.f2323e;
        if (linkedHashSet.isEmpty()) {
            return;
        }
        HashSet hashSet = this.f2322d;
        if (hashSet != null) {
            for (v vVar : linkedHashSet) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ((Set) it.next()).remove(vVar.v());
                }
            }
        }
        linkedHashSet.clear();
    }
}
