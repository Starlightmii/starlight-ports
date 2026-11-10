package f0;

import io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt;
import io.github.andrea_lyz.assistrestore.ui.SettingsStore;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2290d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2291e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2292f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2293g;

    public /* synthetic */ q(v vVar, g0.a aVar, s2 s2Var, g1 g1Var) {
        this.f2290d = 0;
        this.f2291e = vVar;
        this.f2292f = aVar;
        this.f2293g = s2Var;
    }

    public /* synthetic */ q(Object obj, Object obj2, Object obj3, int i5) {
        this.f2290d = i5;
        this.f2291e = obj;
        this.f2292f = obj2;
        this.f2293g = obj3;
    }

    @Override // a4.a
    public final Object a() {
        List listG;
        int i5;
        switch (this.f2290d) {
            case 0:
                v vVar = (v) this.f2291e;
                g0.a aVar = (g0.a) this.f2292f;
                s2 s2Var = (s2) this.f2293g;
                g0.b bVar = vVar.M;
                g0.a aVar2 = bVar.f2457b;
                try {
                    bVar.f2457b = aVar;
                    s2 s2Var2 = vVar.G;
                    int[] iArr = vVar.f2368o;
                    j.r rVar = vVar.f2375v;
                    vVar.f2368o = null;
                    vVar.f2375v = null;
                    try {
                        vVar.G = s2Var;
                        boolean z2 = bVar.f2460e;
                        try {
                            bVar.f2460e = false;
                            throw null;
                        } catch (Throwable th) {
                            bVar.f2460e = z2;
                            throw th;
                        }
                    } catch (Throwable th2) {
                        vVar.G = s2Var2;
                        vVar.f2368o = iArr;
                        vVar.f2375v = rVar;
                        throw th2;
                    }
                } catch (Throwable th3) {
                    bVar.f2457b = aVar2;
                    throw th3;
                }
            case 1:
                a aVar3 = (a) this.f2291e;
                w2 w2Var = (w2) this.f2292f;
                g0.j0 j0Var = (g0.j0) this.f2293g;
                if (aVar3 != null) {
                    w2Var.a(w2Var.c(aVar3) - w2Var.f2409t);
                }
                List listN = p3.j.n(w2Var, null, w2Var.f2409t, null);
                q0.b bVar2 = (q0.b) q3.l.c0(listN);
                Integer num = bVar2 != null ? bVar2.f5088b : null;
                List listD = j0Var.d(num);
                if (num != null && !listD.isEmpty()) {
                    q0.b bVar3 = (q0.b) q3.l.W(listD);
                    int size = listD.size() - 1;
                    if (size <= 0) {
                        listG = q3.s.f5477d;
                    } else if (size == 1) {
                        listG = p3.j.G(q3.l.b0(listD));
                    } else {
                        ArrayList arrayList = new ArrayList(size);
                        if (listD instanceof RandomAccess) {
                            int size2 = listD.size();
                            for (int i6 = 1; i6 < size2; i6++) {
                                arrayList.add(listD.get(i6));
                            }
                        } else {
                            ListIterator listIterator = listD.listIterator(1);
                            while (listIterator.hasNext()) {
                                arrayList.add(listIterator.next());
                            }
                        }
                        listG = arrayList;
                    }
                    listD = q3.l.e0(p3.j.G(new q0.b(bVar3.f5087a, null, num)), listG);
                }
                return new q0.a(q3.l.e0(listN, listD));
            case 2:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$51$0((SettingsStore) this.f2291e, (i1) this.f2292f, (i1) this.f2293g);
            default:
                e eVar = (e) this.f2291e;
                f3.a aVar4 = (f3.a) this.f2292f;
                b4.u uVar = (b4.u) this.f2293g;
                eVar.f2129b = null;
                eVar.f2128a = null;
                n0.a aVar5 = (n0.a) aVar4.f2451c;
                int i7 = uVar.f695d;
                do {
                    i5 = aVar5.get();
                } while (!aVar5.compareAndSet(i5, ((i5 >>> 27) & 15) == i7 ? i5 - 1 : i5));
                return p3.n.f5026a;
        }
    }
}
