package n0;

import f0.j3;
import f0.w1;
import f0.y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends k0.c implements w1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f4069g = new h(k0.m.f3379e, 0);

    public final h b(y1 y1Var, j3 j3Var) {
        k0.l lVarU = this.f3361d.u(y1Var.hashCode(), 0, y1Var, j3Var);
        return lVarU == null ? this : new h((k0.m) lVarU.f3378b, this.f3362e + lVarU.f3377a);
    }

    @Override // k0.c, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof y1) {
            return super.containsKey((y1) obj);
        }
        return false;
    }

    @Override // q3.e, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof j3) {
            return super.containsValue((j3) obj);
        }
        return false;
    }

    @Override // k0.c, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof y1) {
            return (j3) super.get((y1) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof y1) ? obj2 : (j3) super.getOrDefault((y1) obj, (j3) obj2);
    }
}
