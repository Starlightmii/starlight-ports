package o2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends d4.a {
    @Override // d4.a
    public final void C(f fVar, f fVar2) {
        fVar.f4389b = fVar2;
    }

    @Override // d4.a
    public final void D(f fVar, Thread thread) {
        fVar.f4388a = thread;
    }

    @Override // d4.a
    public final boolean h(g gVar, c cVar) {
        c cVar2 = c.f4380b;
        synchronized (gVar) {
            try {
                if (gVar.f4395e != cVar) {
                    return false;
                }
                gVar.f4395e = cVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // d4.a
    public final boolean i(g gVar, Object obj, Object obj2) {
        synchronized (gVar) {
            try {
                if (gVar.f4394d != obj) {
                    return false;
                }
                gVar.f4394d = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // d4.a
    public final boolean j(g gVar, f fVar, f fVar2) {
        synchronized (gVar) {
            try {
                if (gVar.f4396f != fVar) {
                    return false;
                }
                gVar.f4396f = fVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
