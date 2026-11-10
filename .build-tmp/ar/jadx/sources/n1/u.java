package n1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u implements z0, h0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ w f4164d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ b0 f4165e;

    public u(b0 b0Var) {
        this.f4165e = b0Var;
        this.f4164d = b0Var.f4097j;
    }

    @Override // k2.b
    public final int E(long j5) {
        return this.f4164d.E(j5);
    }

    @Override // n1.h0
    public final g0 F(int i5, int i6, Map map, a4.c cVar) {
        return this.f4164d.S(i5, i6, map, cVar);
    }

    @Override // n1.z0
    public final List H(a4.e eVar, Object obj) {
        w0 a0Var;
        b0 b0Var = this.f4165e;
        p1.d0 d0Var = b0Var.f4091d;
        HashMap map = b0Var.f4096i;
        p1.d0 d0Var2 = (p1.d0) map.get(obj);
        List listL = d0Var2 != null ? d0Var2.l() : null;
        if (listL != null) {
            return listL;
        }
        LinkedHashMap linkedHashMap = b0Var.f4101n;
        HashMap map2 = b0Var.f4099l;
        h0.e eVar2 = b0Var.f4102o;
        int i5 = eVar2.f2578f;
        int i6 = b0Var.f4094g;
        if (i5 < i6) {
            throw new IllegalArgumentException("Error: currentPostLookaheadIndex cannot be greater than the size of thepostLookaheadComposedSlotIds list.");
        }
        if (i5 == i6) {
            eVar2.b(obj);
        } else {
            Object[] objArr = eVar2.f2576d;
            Object obj2 = objArr[i6];
            objArr[i6] = obj;
        }
        b0Var.f4094g++;
        if (!map2.containsKey(obj)) {
            if (d0Var.D()) {
                b0Var.e();
                if (!map.containsKey(obj)) {
                    linkedHashMap.remove(obj);
                    Object objH = map2.get(obj);
                    if (objH == null) {
                        objH = b0Var.h((Enum) obj);
                        if (objH != null) {
                            int i7 = ((h0.b) d0Var.o()).f2570d.i(objH);
                            int i8 = ((h0.b) d0Var.o()).f2570d.f2578f;
                            d0Var.f4790n = true;
                            d0Var.G(i7, i8, 1);
                            d0Var.f4790n = false;
                            b0Var.f4104q++;
                        } else {
                            int i9 = ((h0.b) d0Var.o()).f2570d.f2578f;
                            p1.d0 d0Var3 = new p1.d0(2);
                            d0Var.f4790n = true;
                            d0Var.x(i9, d0Var3);
                            d0Var.f4790n = false;
                            b0Var.f4104q++;
                            objH = d0Var3;
                        }
                        map2.put(obj, objH);
                    }
                    b0Var.g((p1.d0) objH, (Enum) obj, eVar);
                }
                a0Var = new a0(b0Var, (Enum) obj);
            } else {
                a0Var = new z();
            }
            linkedHashMap.put(obj, a0Var);
            if (d0Var.f4802z.f4895c == 3) {
                d0Var.N(true);
            } else {
                p1.d0.O(d0Var, true, 6);
            }
        }
        p1.d0 d0Var4 = (p1.d0) map2.get(obj);
        if (d0Var4 == null) {
            return q3.s.f5477d;
        }
        List listO0 = d0Var4.f4802z.f4910r.o0();
        h0.b bVar = (h0.b) listO0;
        int i10 = bVar.f2570d.f2578f;
        for (int i11 = 0; i11 < i10; i11++) {
            ((p1.i0) bVar.get(i11)).I.f4894b = true;
        }
        return listO0;
    }

    @Override // k2.b
    public final float I(long j5) {
        return this.f4164d.I(j5);
    }

    @Override // k2.b
    public final int M(float f5) {
        return this.f4164d.M(f5);
    }

    @Override // k2.b
    public final long R(long j5) {
        return this.f4164d.R(j5);
    }

    @Override // n1.h0
    public final g0 S(int i5, int i6, Map map, a4.c cVar) {
        return this.f4164d.S(i5, i6, map, cVar);
    }

    @Override // k2.b
    public final float U(long j5) {
        return this.f4164d.U(j5);
    }

    @Override // k2.b
    public final float a() {
        return this.f4164d.f4179e;
    }

    @Override // k2.b
    public final long d0(float f5) {
        return this.f4164d.d0(f5);
    }

    @Override // n1.m
    public final k2.k getLayoutDirection() {
        return this.f4164d.f4178d;
    }

    @Override // k2.b
    public final float i0(int i5) {
        return this.f4164d.i0(i5);
    }

    @Override // k2.b
    public final float j() {
        return this.f4164d.f4180f;
    }

    @Override // k2.b
    public final float j0(float f5) {
        return f5 / this.f4164d.a();
    }

    @Override // n1.m
    public final boolean o() {
        return this.f4164d.o();
    }

    @Override // k2.b
    public final long s(float f5) {
        return this.f4164d.s(f5);
    }

    @Override // k2.b
    public final long u(long j5) {
        return this.f4164d.u(j5);
    }

    @Override // k2.b
    public final float v(float f5) {
        return this.f4164d.a() * f5;
    }
}
