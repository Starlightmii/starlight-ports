package i;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends e implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f2633d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f2634e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f2635f;

    public b(c cVar, c cVar2, int i5) {
        this.f2635f = i5;
        this.f2633d = cVar2;
        this.f2634e = cVar;
    }

    @Override // i.e
    public final void a(c cVar) {
        c cVar2;
        c cVarB = null;
        if (this.f2633d == cVar && cVar == this.f2634e) {
            this.f2634e = null;
            this.f2633d = null;
        }
        c cVar3 = this.f2633d;
        if (cVar3 == cVar) {
            switch (this.f2635f) {
                case 0:
                    cVar2 = cVar3.f2639g;
                    break;
                default:
                    cVar2 = cVar3.f2638f;
                    break;
            }
            this.f2633d = cVar2;
        }
        c cVar4 = this.f2634e;
        if (cVar4 == cVar) {
            c cVar5 = this.f2633d;
            if (cVar4 != cVar5 && cVar5 != null) {
                cVarB = b(cVar4);
            }
            this.f2634e = cVarB;
        }
    }

    public final c b(c cVar) {
        switch (this.f2635f) {
            case 0:
                return cVar.f2638f;
            default:
                return cVar.f2639g;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2634e != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c cVar = this.f2634e;
        c cVar2 = this.f2633d;
        this.f2634e = (cVar == cVar2 || cVar2 == null) ? null : b(cVar);
        return cVar;
    }
}
