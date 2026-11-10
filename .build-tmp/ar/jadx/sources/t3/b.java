package t3;

import b4.l;
import f0.k;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements h, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f5804d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f5805e;

    public b(f fVar, h hVar) {
        l.e("left", hVar);
        l.e("element", fVar);
        this.f5804d = hVar;
        this.f5805e = fVar;
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            int i5 = 2;
            b bVar2 = bVar;
            int i6 = 2;
            while (true) {
                h hVar = bVar2.f5804d;
                bVar2 = hVar instanceof b ? (b) hVar : null;
                if (bVar2 == null) {
                    break;
                }
                i6++;
            }
            b bVar3 = this;
            while (true) {
                h hVar2 = bVar3.f5804d;
                bVar3 = hVar2 instanceof b ? (b) hVar2 : null;
                if (bVar3 == null) {
                    break;
                }
                i5++;
            }
            if (i6 == i5) {
                b bVar4 = this;
                while (true) {
                    f fVar = bVar4.f5805e;
                    if (!l.a(bVar.j(fVar.getKey()), fVar)) {
                        zA = false;
                        break;
                    }
                    h hVar3 = bVar4.f5804d;
                    if (!(hVar3 instanceof b)) {
                        l.c("null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element", hVar3);
                        f fVar2 = (f) hVar3;
                        zA = l.a(bVar.j(fVar2.getKey()), fVar2);
                        break;
                    }
                    bVar4 = (b) hVar3;
                }
                if (zA) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // t3.h
    public final h f(g gVar) {
        l.e("key", gVar);
        f fVar = this.f5805e;
        f fVarJ = fVar.j(gVar);
        h hVar = this.f5804d;
        if (fVarJ != null) {
            return hVar;
        }
        h hVarF = hVar.f(gVar);
        if (hVarF == hVar) {
            return this;
        }
        return hVarF == i.f5807d ? fVar : new b(fVar, hVarF);
    }

    public final int hashCode() {
        return this.f5805e.hashCode() + this.f5804d.hashCode();
    }

    @Override // t3.h
    public final f j(g gVar) {
        l.e("key", gVar);
        b bVar = this;
        while (true) {
            f fVarJ = bVar.f5805e.j(gVar);
            if (fVarJ != null) {
                return fVarJ;
            }
            h hVar = bVar.f5804d;
            if (!(hVar instanceof b)) {
                return hVar.j(gVar);
            }
            bVar = (b) hVar;
        }
    }

    @Override // t3.h
    public final h k(h hVar) {
        l.e("context", hVar);
        return hVar == i.f5807d ? this : (h) hVar.t(new k(29), this);
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return eVar.invoke(this.f5804d.t(eVar, obj), this.f5805e);
    }

    public final String toString() {
        return "[" + ((String) t(new k(28), "")) + ']';
    }
}
