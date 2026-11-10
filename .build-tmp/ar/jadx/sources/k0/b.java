package k0;

import e1.y;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y f3358f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f3359g;

    public b(y yVar, Object obj, Object obj2) {
        super(obj, obj2);
        this.f3358f = yVar;
        this.f3359g = obj2;
    }

    @Override // k0.a, java.util.Map.Entry
    public final Object getValue() {
        return this.f3359g;
    }

    @Override // k0.a, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.f3359g;
        this.f3359g = obj;
        e eVar = (e) this.f3358f.f1970e;
        n0.g gVar = eVar.f3366g;
        Object obj3 = this.f3356d;
        if (!gVar.containsKey(obj3)) {
            return obj2;
        }
        boolean z2 = eVar.f3365f;
        if (!z2) {
            gVar.put(obj3, obj);
        } else {
            if (!z2) {
                throw new NoSuchElementException();
            }
            n nVar = eVar.f3363d[eVar.f3364e];
            Object obj4 = nVar.f3384d[nVar.f3386f];
            gVar.put(obj3, obj);
            eVar.c(obj4 != null ? obj4.hashCode() : 0, gVar.f4064e, obj4, 0);
        }
        eVar.f3369j = gVar.f4066g;
        return obj2;
    }
}
