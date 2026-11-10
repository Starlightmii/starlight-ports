package j1;

import c0.g1;
import c0.q4;
import i4.h1;
import java.util.ArrayList;
import p1.j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends r0.o implements k2.b, j1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object f3232q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f3233r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.e f3234s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public h1 f3235t;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public i f3239x;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public i f3236u = z.f3303a;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final h0.e f3237v = new h0.e(new c0[16]);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final h0.e f3238w = new h0.e(new c0[16]);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f3240y = 0;

    public d0(Object obj, Object obj2, a4.e eVar) {
        this.f3232q = obj;
        this.f3233r = obj2;
        this.f3234s = eVar;
    }

    public final void A0() {
        h1 h1Var = this.f3235t;
        if (h1Var != null) {
            h1Var.y(new x("Pointer input was reset", 0));
            this.f3235t = null;
        }
    }

    @Override // p1.j1
    public final void T() {
        A0();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // p1.j1
    public final void W() {
        i iVar = this.f3239x;
        if (iVar == null) {
            return;
        }
        ?? r1 = iVar.f3257a;
        int size = r1.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (((u) r1.get(i5)).f3277d) {
                ArrayList arrayList = new ArrayList(r1.size());
                int size2 = r1.size();
                for (int i6 = 0; i6 < size2; i6++) {
                    u uVar = (u) r1.get(i6);
                    long j5 = uVar.f3274a;
                    long j6 = uVar.f3276c;
                    long j7 = uVar.f3275b;
                    float f5 = uVar.f3278e;
                    boolean z2 = uVar.f3277d;
                    arrayList.add(new u(j5, j7, j6, false, f5, j7, j6, z2, z2, 1, 0L));
                }
                i iVar2 = new i(arrayList, null);
                this.f3236u = iVar2;
                z0(iVar2, j.f3260d);
                z0(iVar2, j.f3261e);
                z0(iVar2, j.f3262f);
                this.f3239x = null;
                return;
            }
        }
    }

    @Override // k2.b
    public final float a() {
        return p1.f.s(this).f4796t.a();
    }

    @Override // p1.j1
    public final void h() {
        A0();
    }

    @Override // k2.b
    public final float j() {
        return p1.f.s(this).f4796t.j();
    }

    @Override // r0.o
    public final void s0() {
        A0();
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, java.util.List] */
    @Override // p1.j1
    public final void y(i iVar, j jVar, long j5) {
        this.f3240y = j5;
        if (jVar == j.f3260d) {
            this.f3236u = iVar;
        }
        t3.c cVar = null;
        if (this.f3235t == null) {
            this.f3235t = i4.y.n(n0(), null, new q4(this, cVar, 1), 1);
        }
        z0(iVar, jVar);
        ?? r4 = iVar.f3257a;
        int size = r4.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (!s.c((u) r4.get(i5))) {
                this.f3239x = iVar;
            }
        }
        iVar = null;
        this.f3239x = iVar;
    }

    public final Object y0(a4.e eVar, t3.c cVar) {
        i4.h hVar = new i4.h(1, p3.j.B(cVar));
        hVar.p();
        c0 c0Var = new c0(this, hVar);
        synchronized (this.f3237v) {
            this.f3237v.b(c0Var);
            new t3.j(p3.j.B(p3.j.q(eVar, c0Var, c0Var))).resumeWith(p3.n.f5026a);
        }
        hVar.s(new g1(5, c0Var));
        return hVar.o();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x003c A[Catch: all -> 0x003a, TryCatch #1 {all -> 0x003a, blocks: (B:6:0x000d, B:13:0x001b, B:15:0x0021, B:16:0x0024, B:18:0x002c, B:20:0x0030, B:21:0x0035, B:26:0x003c, B:28:0x0042, B:29:0x0045, B:31:0x004d, B:33:0x0051), top: B:45:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0042 A[Catch: all -> 0x003a, TryCatch #1 {all -> 0x003a, blocks: (B:6:0x000d, B:13:0x001b, B:15:0x0021, B:16:0x0024, B:18:0x002c, B:20:0x0030, B:21:0x0035, B:26:0x003c, B:28:0x0042, B:29:0x0045, B:31:0x004d, B:33:0x0051), top: B:45:0x000d }] */
    public final void z0(i iVar, j jVar) {
        h0.e eVar;
        int i5;
        Object[] objArr;
        int i6;
        c0 c0Var;
        i4.h hVar;
        i4.h hVar2;
        synchronized (this.f3237v) {
            h0.e eVar2 = this.f3238w;
            eVar2.c(eVar2.f2578f, this.f3237v);
        }
        try {
            int iOrdinal = jVar.ordinal();
            if (iOrdinal == 0) {
                eVar = this.f3238w;
                i5 = eVar.f2578f;
                if (i5 > 0) {
                    objArr = eVar.f2576d;
                    i6 = 0;
                    do {
                        c0Var = (c0) objArr[i6];
                        if (jVar == c0Var.f3227g && (hVar = c0Var.f3226f) != null) {
                            c0Var.f3226f = null;
                            hVar.resumeWith(iVar);
                        }
                        i6++;
                    } while (i6 < i5);
                }
            } else if (iOrdinal == 1) {
                h0.e eVar3 = this.f3238w;
                int i7 = eVar3.f2578f;
                if (i7 > 0) {
                    int i8 = i7 - 1;
                    Object[] objArr2 = eVar3.f2576d;
                    do {
                        c0 c0Var2 = (c0) objArr2[i8];
                        if (jVar == c0Var2.f3227g && (hVar2 = c0Var2.f3226f) != null) {
                            c0Var2.f3226f = null;
                            hVar2.resumeWith(iVar);
                        }
                        i8--;
                    } while (i8 >= 0);
                }
            } else if (iOrdinal == 2) {
                eVar = this.f3238w;
                i5 = eVar.f2578f;
                if (i5 > 0) {
                    objArr = eVar.f2576d;
                    i6 = 0;
                    do {
                        c0Var = (c0) objArr[i6];
                        if (jVar == c0Var.f3227g) {
                            c0Var.f3226f = null;
                            hVar.resumeWith(iVar);
                        }
                        i6++;
                    } while (i6 < i5);
                }
            }
        } finally {
            this.f3238w.g();
        }
    }
}
