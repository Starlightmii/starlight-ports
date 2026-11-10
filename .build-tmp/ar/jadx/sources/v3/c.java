package v3;

import b4.l;
import i4.r;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {
    private final t3.h _context;
    private transient t3.c intercepted;

    public c(t3.c cVar) {
        this(cVar, cVar != null ? cVar.getContext() : null);
    }

    public c(t3.c cVar, t3.h hVar) {
        super(cVar);
        this._context = hVar;
    }

    @Override // t3.c
    public t3.h getContext() {
        t3.h hVar = this._context;
        l.b(hVar);
        return hVar;
    }

    public final t3.c intercepted() {
        t3.c cVar = this.intercepted;
        if (cVar != null) {
            return cVar;
        }
        t3.e eVar = (t3.e) getContext().j(t3.d.f5806d);
        t3.c fVar = eVar != null ? new n4.f((r) eVar, this) : this;
        this.intercepted = fVar;
        return fVar;
    }

    @Override // v3.a
    public void releaseIntercepted() {
        t3.c cVar = this.intercepted;
        if (cVar != null && cVar != this) {
            t3.f fVarJ = getContext().j(t3.d.f5806d);
            l.b(fVarJ);
            n4.f fVar = (n4.f) cVar;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.f.f4279k;
            while (atomicReferenceFieldUpdater.get(fVar) == n4.a.f4269c) {
            }
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            i4.h hVar = obj instanceof i4.h ? (i4.h) obj : null;
            if (hVar != null) {
                hVar.l();
            }
        }
        this.intercepted = b.f6209d;
    }
}
