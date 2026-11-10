package m4;

import i4.y;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t3.h f3823d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3824e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k4.a f3825f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final l4.d f3826g;

    public f(l4.d dVar, t3.h hVar, int i5, k4.a aVar) {
        this.f3823d = hVar;
        this.f3824e = i5;
        this.f3825f = aVar;
        this.f3826g = dVar;
    }

    public abstract f a(t3.h hVar, int i5, k4.a aVar);

    public l4.d b() {
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0015  */
    @Override // m4.m
    public final l4.d c(t3.h hVar, int i5, k4.a aVar) {
        t3.h hVar2 = this.f3823d;
        t3.h hVarK = hVar.k(hVar2);
        k4.a aVar2 = k4.a.f3435d;
        k4.a aVar3 = this.f3825f;
        int i6 = this.f3824e;
        if (aVar == aVar2) {
            if (i6 != -3) {
                if (i5 == -3) {
                    i5 = i6;
                } else if (i6 != -2) {
                    if (i5 == -2) {
                        i5 = i6;
                    } else {
                        i5 += i6;
                        if (i5 < 0) {
                            i5 = Integer.MAX_VALUE;
                        }
                    }
                }
            }
            aVar = aVar3;
        }
        return (b4.l.a(hVarK, hVar2) && i5 == i6 && aVar == aVar3) ? this : a(hVarK, i5, aVar);
    }

    public abstract Object d(l4.e eVar, t3.c cVar);

    public final String e() {
        ArrayList arrayList = new ArrayList(4);
        t3.i iVar = t3.i.f5807d;
        t3.h hVar = this.f3823d;
        if (hVar != iVar) {
            arrayList.add("context=" + hVar);
        }
        int i5 = this.f3824e;
        if (i5 != -3) {
            arrayList.add("capacity=" + i5);
        }
        k4.a aVar = k4.a.f3435d;
        k4.a aVar2 = this.f3825f;
        if (aVar2 != aVar) {
            arrayList.add("onBufferOverflow=" + aVar2);
        }
        return getClass().getSimpleName() + '[' + q3.l.a0(arrayList, null, null, null, 62) + ']';
    }

    /* JADX WARN: Code duplicated, block: B:24:0x006f  */
    /* JADX WARN: Code duplicated, block: B:27:0x007c  */
    /* JADX WARN: Code duplicated, block: B:29:0x007f A[RETURN] */
    @Override // l4.d
    public final Object g(l4.e eVar, t3.c cVar) {
        Object objC;
        int i5 = this.f3824e;
        u3.a aVar = u3.a.f5838d;
        t3.c cVar2 = null;
        p3.n nVar = p3.n.f5026a;
        if (i5 == -3) {
            t3.h context = cVar.getContext();
            Boolean bool = Boolean.FALSE;
            f0.k kVar = new f0.k(4);
            t3.h hVar = this.f3823d;
            t3.h hVarK = !((Boolean) hVar.t(kVar, bool)).booleanValue() ? context.k(hVar) : y.f(context, hVar, false);
            if (b4.l.a(hVarK, context)) {
                Object objD = d(eVar, cVar);
                if (objD == aVar) {
                    return objD;
                }
            } else {
                t3.d dVar = t3.d.f5806d;
                if (b4.l.a(hVarK.j(dVar), context.j(dVar))) {
                    t3.h context2 = cVar.getContext();
                    if (!(eVar instanceof s) && !(eVar instanceof o)) {
                        eVar = new l4.h(eVar, context2);
                    }
                    Object objA = c.a(hVarK, eVar, n4.a.k(hVarK), new e(this, cVar2, 1), cVar);
                    if (objA == aVar) {
                        return objA;
                    }
                } else {
                    objC = y.c(new b0.d(eVar, this, cVar2, 3), cVar);
                    if (objC != aVar) {
                        objC = nVar;
                    }
                    if (objC == aVar) {
                        return objC;
                    }
                }
            }
        } else {
            objC = y.c(new b0.d(eVar, this, cVar2, 3), cVar);
            if (objC != aVar) {
                objC = nVar;
            }
            if (objC == aVar) {
                return objC;
            }
        }
        return nVar;
    }

    public final String toString() {
        return this.f3826g + " -> " + e();
    }
}
