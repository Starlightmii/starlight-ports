package d2;

import android.graphics.Typeface;
import androidx.lifecycle.k0;
import b3.s;
import c0.g1;
import i4.i1;
import i4.y;
import java.util.HashMap;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k0 f1603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a2.h f1605c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f1606d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a1.d f1607e;

    public e(k0 k0Var, a aVar) {
        a2.h hVar = f.f1608a;
        a2.h hVar2 = f.f1608a;
        h hVar3 = new h();
        g gVar = h.f1609a;
        j4.d dVar = g2.i.f2542a;
        gVar.getClass();
        y.a(p3.p.A(gVar, dVar).k(t3.i.f5807d).k(new i1(null)));
        a1.d dVar2 = new a1.d(3);
        this.f1603a = k0Var;
        this.f1604b = aVar;
        this.f1605c = hVar;
        this.f1606d = hVar3;
        this.f1607e = dVar2;
        new g1(2, this);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0085  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a6 A[Catch: Exception -> 0x00ae, TRY_ENTER, TryCatch #1 {Exception -> 0x00ae, blocks: (B:25:0x0048, B:30:0x0061, B:31:0x0063, B:32:0x0066, B:37:0x007c, B:55:0x00a6, B:56:0x00ad, B:33:0x006c, B:34:0x0072, B:36:0x0076, B:28:0x005d), top: B:64:0x0048 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final r a(q qVar) {
        r rVar;
        Typeface typefaceJ;
        Object objRemove;
        a2.h hVar = this.f1605c;
        synchronized (((k0) hVar.f49e)) {
            try {
                r rVar2 = (r) ((s) hVar.f50f).a(qVar);
                if (rVar2 != null) {
                    if (rVar2.f1626e) {
                        return rVar2;
                    }
                    s sVar = (s) hVar.f50f;
                    synchronized (((k0) sVar.f639e)) {
                        objRemove = ((HashMap) sVar.f640f).remove(qVar);
                        ((LinkedHashSet) sVar.f641g).remove(qVar);
                        if (objRemove != null) {
                            sVar.f636b = sVar.e() - 1;
                        }
                    }
                }
                try {
                    this.f1606d.getClass();
                    p pVar = qVar.f1620a;
                    k0 k0Var = (k0) this.f1607e.f31e;
                    int i5 = qVar.f1622c;
                    k kVar = qVar.f1621b;
                    if (!(pVar == null ? true : pVar instanceof b)) {
                        if (pVar instanceof m) {
                            typefaceJ = k0Var.j((m) pVar, kVar, i5);
                        } else {
                            rVar = null;
                        }
                        if (rVar != null) {
                            throw new IllegalStateException("Could not load font");
                        }
                        synchronized (((k0) hVar.f49e)) {
                            if (((s) hVar.f50f).a(qVar) == null && rVar.f1626e) {
                                ((s) hVar.f50f).b(qVar, rVar);
                            }
                        }
                        return rVar;
                    }
                    switch (k0Var.f261d) {
                        case 14:
                            typefaceJ = k0.e(null, kVar, i5);
                            break;
                        default:
                            typefaceJ = k0.g(null, kVar, i5);
                            break;
                    }
                    rVar = new r(typefaceJ);
                    if (rVar != null) {
                        throw new IllegalStateException("Could not load font");
                    }
                    synchronized (((k0) hVar.f49e)) {
                        if (((s) hVar.f50f).a(qVar) == null) {
                            ((s) hVar.f50f).b(qVar, rVar);
                        }
                        return rVar;
                    }
                } catch (Exception e5) {
                    throw new IllegalStateException("Could not load font", e5);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final r b(p pVar, k kVar, int i5, int i6) {
        a aVar = this.f1604b;
        aVar.getClass();
        int i7 = aVar.f1602d;
        k kVar2 = (i7 == 0 || i7 == Integer.MAX_VALUE) ? kVar : new k(d4.a.m(kVar.f1615d + i7, 1, 1000));
        this.f1603a.getClass();
        return a(new q(pVar, kVar2, i5, i6, null));
    }
}
