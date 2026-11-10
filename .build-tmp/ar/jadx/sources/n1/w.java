package n1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements z0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k2.k f4178d = k2.k.f3417e;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4180f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ b0 f4181g;

    public w(b0 b0Var) {
        this.f4181g = b0Var;
    }

    @Override // n1.z0
    public final List H(a4.e eVar, Object obj) {
        b0 b0Var = this.f4181g;
        b0Var.e();
        p1.d0 d0Var = b0Var.f4091d;
        int i5 = d0Var.f4802z.f4895c;
        Object obj2 = null;
        if (i5 != 1 && i5 != 3 && i5 != 2 && i5 != 4) {
            d4.a.L("subcompose can only be used inside the measure or layout blocks");
            throw null;
        }
        HashMap map = b0Var.f4096i;
        Object objH = map.get(obj);
        if (objH == null) {
            objH = (p1.d0) b0Var.f4099l.remove(obj);
            if (objH != null) {
                int i6 = b0Var.f4104q;
                if (i6 <= 0) {
                    d4.a.L("Check failed.");
                    throw null;
                }
                b0Var.f4104q = i6 - 1;
            } else {
                objH = b0Var.h((Enum) obj);
                if (objH == null) {
                    int i7 = b0Var.f4093f;
                    p1.d0 d0Var2 = new p1.d0(2);
                    d0Var.f4790n = true;
                    d0Var.x(i7, d0Var2);
                    d0Var.f4790n = false;
                    objH = d0Var2;
                }
            }
            map.put(obj, objH);
        }
        p1.d0 d0Var3 = (p1.d0) objH;
        List listO = d0Var.o();
        int i8 = b0Var.f4093f;
        if (i8 >= 0) {
            h0.b bVar = (h0.b) listO;
            if (i8 < bVar.f2570d.f2578f) {
                obj2 = bVar.get(i8);
            }
        }
        if (obj2 != d0Var3) {
            int i9 = ((h0.b) d0Var.o()).f2570d.i(d0Var3);
            int i10 = b0Var.f4093f;
            if (i9 < i10) {
                throw new IllegalArgumentException(("Key \"" + obj + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.").toString());
            }
            if (i10 != i9) {
                d0Var.f4790n = true;
                d0Var.G(i9, i10, 1);
                d0Var.f4790n = false;
            }
        }
        b0Var.f4093f++;
        b0Var.g(d0Var3, (Enum) obj, eVar);
        return (i5 == 1 || i5 == 3) ? d0Var3.l() : d0Var3.k();
    }

    @Override // n1.h0
    public final g0 S(int i5, int i6, Map map, a4.c cVar) {
        if ((i5 & (-16777216)) == 0 && ((-16777216) & i6) == 0) {
            return new v(i5, i6, map, this, this.f4181g, cVar);
        }
        d4.a.L("Size(" + i5 + " x " + i6 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    @Override // k2.b
    public final float a() {
        return this.f4179e;
    }

    @Override // n1.m
    public final k2.k getLayoutDirection() {
        return this.f4178d;
    }

    @Override // k2.b
    public final float j() {
        return this.f4180f;
    }

    @Override // n1.m
    public final boolean o() {
        int i5 = this.f4181g.f4091d.f4802z.f4895c;
        return i5 == 4 || i5 == 2;
    }
}
