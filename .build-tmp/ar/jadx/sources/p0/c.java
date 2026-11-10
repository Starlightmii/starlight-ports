package p0;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final b f4676o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4677p;

    public c(long j5, k kVar, a4.c cVar, a4.c cVar2, b bVar) {
        super(j5, kVar, cVar, cVar2);
        this.f4676o = bVar;
        bVar.k();
    }

    @Override // p0.b, p0.f
    public final void c() {
        if (this.f4695c) {
            return;
        }
        super.c();
        if (this.f4677p) {
            return;
        }
        this.f4677p = true;
        this.f4676o.l();
    }

    @Override // p0.b
    public final r w() throws Throwable {
        c cVar;
        b bVar = this.f4676o;
        if (bVar.f4671m || bVar.f4695c) {
            return new g();
        }
        j.a0 a0Var = this.f4666h;
        long j5 = this.f4694b;
        HashMap mapB = a0Var != null ? m.b(bVar.g(), this, this.f4676o.d()) : null;
        Object obj = m.f4719c;
        synchronized (obj) {
            try {
                m.c(this);
                try {
                    if (a0Var == null || a0Var.f3069d == 0) {
                        cVar = this;
                        a();
                    } else {
                        cVar = this;
                        r rVarZ = cVar.z(this.f4676o.g(), a0Var, mapB, this.f4676o.d());
                        if (!rVarZ.equals(h.f4697b)) {
                            return rVarZ;
                        }
                        j.a0 a0VarX = cVar.f4676o.x();
                        if (a0VarX != null) {
                            a0VarX.i(a0Var);
                        } else {
                            cVar.f4676o.B(a0Var);
                            cVar.f4666h = null;
                        }
                    }
                    if (b4.l.g(cVar.f4676o.g(), j5) < 0) {
                        cVar.f4676o.v();
                    }
                    b bVar2 = cVar.f4676o;
                    bVar2.r(bVar2.d().b(j5).a(cVar.f4668j));
                    cVar.f4676o.A(j5);
                    b bVar3 = cVar.f4676o;
                    int i5 = cVar.f4696d;
                    cVar.f4696d = -1;
                    if (i5 >= 0) {
                        int[] iArr = bVar3.f4669k;
                        b4.l.e("<this>", iArr);
                        int length = iArr.length;
                        int[] iArrCopyOf = Arrays.copyOf(iArr, length + 1);
                        iArrCopyOf[length] = i5;
                        bVar3.f4669k = iArrCopyOf;
                    } else {
                        bVar3.getClass();
                    }
                    b bVar4 = cVar.f4676o;
                    k kVar = cVar.f4668j;
                    bVar4.getClass();
                    synchronized (obj) {
                        bVar4.f4668j = bVar4.f4668j.d(kVar);
                        b bVar5 = cVar.f4676o;
                        int[] iArr2 = cVar.f4669k;
                        bVar5.getClass();
                        if (iArr2.length != 0) {
                            int[] iArr3 = bVar5.f4669k;
                            if (iArr3.length != 0) {
                                int length2 = iArr3.length;
                                int length3 = iArr2.length;
                                int[] iArrCopyOf2 = Arrays.copyOf(iArr3, length2 + length3);
                                System.arraycopy(iArr2, 0, iArrCopyOf2, length2, length3);
                                b4.l.b(iArrCopyOf2);
                                iArr2 = iArrCopyOf2;
                            }
                            bVar5.f4669k = iArr2;
                        }
                    }
                    cVar.f4671m = true;
                    if (!cVar.f4677p) {
                        cVar.f4677p = true;
                        cVar.f4676o.l();
                    }
                    return h.f4697b;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
