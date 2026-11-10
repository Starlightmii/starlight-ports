package androidx.lifecycle;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f296a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i.a f297b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public p f298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f299d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f301f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f302g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f303h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l4.d0 f304i;

    public x(v vVar) {
        new AtomicReference(null);
        this.f296a = true;
        this.f297b = new i.a();
        p pVar = p.f275e;
        this.f298c = pVar;
        this.f303h = new ArrayList();
        this.f299d = new WeakReference(vVar);
        this.f304i = new l4.d0(pVar);
    }

    @Override // androidx.lifecycle.q
    public final void a(u uVar) {
        t hVar;
        w wVar;
        v vVar;
        o oVar;
        b4.l.e("observer", uVar);
        d("addObserver");
        p pVar = this.f298c;
        p pVar2 = p.f274d;
        if (pVar != pVar2) {
            pVar2 = p.f275e;
        }
        w wVar2 = new w();
        HashMap map = y.f305a;
        boolean z2 = uVar instanceof t;
        boolean z3 = uVar instanceof f;
        int i5 = 0;
        if (z2 && z3) {
            hVar = new h((f) uVar, (t) uVar);
        } else if (z3) {
            hVar = new h((f) uVar, (t) null);
        } else if (z2) {
            hVar = (t) uVar;
        } else {
            Class<?> cls = uVar.getClass();
            if (y.c(cls) == 2) {
                Object obj = y.f306b.get(cls);
                b4.l.b(obj);
                List list = (List) obj;
                if (list.size() == 1) {
                    y.a((Constructor) list.get(0), uVar);
                    throw null;
                }
                int size = list.size();
                j[] jVarArr = new j[size];
                if (size > 0) {
                    y.a((Constructor) list.get(0), uVar);
                    throw null;
                }
                hVar = new e(i5, jVarArr);
            } else {
                hVar = new h(uVar);
            }
        }
        wVar2.f295b = hVar;
        wVar2.f294a = pVar2;
        i.a aVar = this.f297b;
        i.c cVar = (i.c) aVar.f2632h.get(uVar);
        if (cVar != null) {
            wVar = cVar.f2637e;
        } else {
            HashMap map2 = aVar.f2632h;
            i.c cVar2 = new i.c(uVar, wVar2);
            aVar.f2631g++;
            i.c cVar3 = aVar.f2629e;
            if (cVar3 == null) {
                aVar.f2628d = cVar2;
                aVar.f2629e = cVar2;
            } else {
                cVar3.f2638f = cVar2;
                cVar2.f2639g = cVar3;
                aVar.f2629e = cVar2;
            }
            map2.put(uVar, cVar2);
            wVar = null;
        }
        if (wVar == null && (vVar = (v) this.f299d.get()) != null) {
            i5 = (this.f300e != 0 || this.f301f) ? 1 : 0;
            p pVarC = c(uVar);
            this.f300e++;
            while (wVar2.f294a.compareTo(pVarC) < 0 && this.f297b.f2632h.containsKey(uVar)) {
                p pVar3 = wVar2.f294a;
                ArrayList arrayList = this.f303h;
                arrayList.add(pVar3);
                m mVar = o.Companion;
                p pVar4 = wVar2.f294a;
                mVar.getClass();
                b4.l.e("state", pVar4);
                int iOrdinal = pVar4.ordinal();
                if (iOrdinal == 1) {
                    oVar = o.ON_CREATE;
                } else if (iOrdinal != 2) {
                    oVar = iOrdinal != 3 ? null : o.ON_RESUME;
                } else {
                    oVar = o.ON_START;
                }
                if (oVar == null) {
                    throw new IllegalStateException("no event up from " + wVar2.f294a);
                }
                wVar2.a(vVar, oVar);
                arrayList.remove(arrayList.size() - 1);
                pVarC = c(uVar);
            }
            if (i5 == 0) {
                g();
            }
            this.f300e--;
        }
    }

    @Override // androidx.lifecycle.q
    public final void b(u uVar) {
        b4.l.e("observer", uVar);
        d("removeObserver");
        i.a aVar = this.f297b;
        WeakHashMap weakHashMap = aVar.f2630f;
        HashMap map = aVar.f2632h;
        i.c cVar = (i.c) map.get(uVar);
        if (cVar != null) {
            aVar.f2631g--;
            if (!weakHashMap.isEmpty()) {
                Iterator it = weakHashMap.keySet().iterator();
                while (it.hasNext()) {
                    ((i.e) it.next()).a(cVar);
                }
            }
            i.c cVar2 = cVar.f2639g;
            if (cVar2 != null) {
                cVar2.f2638f = cVar.f2638f;
            } else {
                aVar.f2628d = cVar.f2638f;
            }
            i.c cVar3 = cVar.f2638f;
            if (cVar3 != null) {
                cVar3.f2639g = cVar2;
            } else {
                aVar.f2629e = cVar2;
            }
            cVar.f2638f = null;
            cVar.f2639g = null;
        }
        map.remove(uVar);
    }

    public final p c(u uVar) {
        HashMap map = this.f297b.f2632h;
        i.c cVar = map.containsKey(uVar) ? ((i.c) map.get(uVar)).f2639g : null;
        p pVar = cVar != null ? cVar.f2637e.f294a : null;
        ArrayList arrayList = this.f303h;
        p pVar2 = arrayList.isEmpty() ? null : (p) arrayList.get(arrayList.size() - 1);
        p pVar3 = this.f298c;
        b4.l.e("state1", pVar3);
        if (pVar == null || pVar.compareTo(pVar3) >= 0) {
            pVar = pVar3;
        }
        return (pVar2 == null || pVar2.compareTo(pVar) >= 0) ? pVar : pVar2;
    }

    public final void d(String str) {
        h.a aVar;
        if (this.f296a) {
            if (h.a.f2566m != null) {
                aVar = h.a.f2566m;
            } else {
                synchronized (h.a.class) {
                    try {
                        if (h.a.f2566m == null) {
                            h.a.f2566m = new h.a(0);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                aVar = h.a.f2566m;
            }
            ((h.a) aVar.f2567l).getClass();
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                return;
            }
            throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
        }
    }

    public final void e(o oVar) {
        b4.l.e("event", oVar);
        d("handleLifecycleEvent");
        f(oVar.a());
    }

    public final void f(p pVar) {
        if (this.f298c == pVar) {
            return;
        }
        v vVar = (v) this.f299d.get();
        p pVar2 = this.f298c;
        b4.l.e("current", pVar2);
        p pVar3 = p.f275e;
        p pVar4 = p.f274d;
        if (pVar2 == pVar3 && pVar == pVar4) {
            throw new IllegalStateException(("State must be at least '" + p.f276f + "' to be moved to '" + pVar + "' in component " + vVar).toString());
        }
        if (pVar2 == pVar4 && pVar2 != pVar) {
            throw new IllegalStateException(("State is '" + pVar4 + "' and cannot be moved to `" + pVar + "` in component " + vVar).toString());
        }
        this.f298c = pVar;
        if (this.f301f || this.f300e != 0) {
            this.f302g = true;
            return;
        }
        this.f301f = true;
        g();
        this.f301f = false;
        if (this.f298c == pVar4) {
            this.f297b = new i.a();
        }
    }

    public final void g() {
        o oVar;
        o oVar2;
        v vVar = (v) this.f299d.get();
        if (vVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            i.a aVar = this.f297b;
            if (aVar.f2631g != 0) {
                i.c cVar = aVar.f2628d;
                b4.l.b(cVar);
                p pVar = cVar.f2637e.f294a;
                i.c cVar2 = this.f297b.f2629e;
                b4.l.b(cVar2);
                p pVar2 = cVar2.f2637e.f294a;
                if (pVar == pVar2 && this.f298c == pVar2) {
                    break;
                }
                this.f302g = false;
                p pVar3 = this.f298c;
                i.c cVar3 = this.f297b.f2628d;
                b4.l.b(cVar3);
                int iCompareTo = pVar3.compareTo(cVar3.f2637e.f294a);
                ArrayList arrayList = this.f303h;
                if (iCompareTo < 0) {
                    i.a aVar2 = this.f297b;
                    i.b bVar = new i.b(aVar2.f2629e, aVar2.f2628d, 1);
                    aVar2.f2630f.put(bVar, Boolean.FALSE);
                    while (bVar.hasNext() && !this.f302g) {
                        Map.Entry entry = (Map.Entry) bVar.next();
                        b4.l.b(entry);
                        u uVar = (u) entry.getKey();
                        w wVar = (w) entry.getValue();
                        while (wVar.f294a.compareTo(this.f298c) > 0 && !this.f302g && this.f297b.f2632h.containsKey(uVar)) {
                            m mVar = o.Companion;
                            p pVar4 = wVar.f294a;
                            mVar.getClass();
                            b4.l.e("state", pVar4);
                            int iOrdinal = pVar4.ordinal();
                            if (iOrdinal == 2) {
                                oVar2 = o.ON_DESTROY;
                            } else if (iOrdinal != 3) {
                                oVar2 = iOrdinal != 4 ? null : o.ON_PAUSE;
                            } else {
                                oVar2 = o.ON_STOP;
                            }
                            if (oVar2 == null) {
                                throw new IllegalStateException("no event down from " + wVar.f294a);
                            }
                            arrayList.add(oVar2.a());
                            wVar.a(vVar, oVar2);
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                }
                i.c cVar4 = this.f297b.f2629e;
                if (!this.f302g && cVar4 != null && this.f298c.compareTo(cVar4.f2637e.f294a) > 0) {
                    i.a aVar3 = this.f297b;
                    aVar3.getClass();
                    i.d dVar = new i.d(aVar3);
                    aVar3.f2630f.put(dVar, Boolean.FALSE);
                    while (dVar.hasNext() && !this.f302g) {
                        Map.Entry entry2 = (Map.Entry) dVar.next();
                        u uVar2 = (u) entry2.getKey();
                        w wVar2 = (w) entry2.getValue();
                        while (wVar2.f294a.compareTo(this.f298c) < 0 && !this.f302g && this.f297b.f2632h.containsKey(uVar2)) {
                            arrayList.add(wVar2.f294a);
                            m mVar2 = o.Companion;
                            p pVar5 = wVar2.f294a;
                            mVar2.getClass();
                            b4.l.e("state", pVar5);
                            int iOrdinal2 = pVar5.ordinal();
                            if (iOrdinal2 == 1) {
                                oVar = o.ON_CREATE;
                            } else if (iOrdinal2 != 2) {
                                oVar = iOrdinal2 != 3 ? null : o.ON_RESUME;
                            } else {
                                oVar = o.ON_START;
                            }
                            if (oVar == null) {
                                throw new IllegalStateException("no event up from " + wVar2.f294a);
                            }
                            wVar2.a(vVar, oVar);
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                }
            } else {
                break;
            }
        }
        this.f302g = false;
        Object obj = this.f298c;
        l4.d0 d0Var = this.f304i;
        d0Var.getClass();
        if (obj == null) {
            obj = m4.c.f3818b;
        }
        d0Var.i(null, obj);
    }
}
