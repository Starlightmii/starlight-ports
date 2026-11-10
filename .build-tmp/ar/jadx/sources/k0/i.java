package k0;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends q3.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f3375e;

    public /* synthetic */ i(c cVar, int i5) {
        this.f3374d = i5;
        this.f3375e = cVar;
    }

    @Override // q3.a
    public final int a() {
        switch (this.f3374d) {
            case 0:
                c cVar = this.f3375e;
                cVar.getClass();
                return cVar.f3362e;
            default:
                c cVar2 = this.f3375e;
                cVar2.getClass();
                return cVar2.f3362e;
        }
    }

    @Override // q3.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        switch (this.f3374d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                c cVar = this.f3375e;
                Object obj2 = cVar.get(key);
                if (obj2 != null) {
                    return obj2.equals(entry.getValue());
                }
                return entry.getValue() == null && cVar.containsKey(entry.getKey());
            default:
                return this.f3375e.containsKey(obj);
        }
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f3374d) {
            case 0:
                m mVar = this.f3375e.f3361d;
                n[] nVarArr = new n[8];
                for (int i5 = 0; i5 < 8; i5++) {
                    nVarArr[i5] = new o(0);
                }
                return new j(mVar, nVarArr);
            default:
                m mVar2 = this.f3375e.f3361d;
                n[] nVarArr2 = new n[8];
                for (int i6 = 0; i6 < 8; i6++) {
                    nVarArr2[i6] = new o(1);
                }
                return new j(mVar2, nVarArr2);
        }
    }
}
