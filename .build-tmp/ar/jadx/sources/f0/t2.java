package f0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t2 implements q0.c, Iterable, c4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2334e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2336g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2337h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2339j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2340k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f2342m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j.r f2343n;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f2333d = new int[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object[] f2335f = new Object[0];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2338i = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList f2341l = new ArrayList();

    public final int a(a aVar) {
        if (this.f2339j) {
            z.a("Use active SlotWriter to determine anchor location instead");
        }
        if (!aVar.a()) {
            x1.a("Anchor refers to a group that was removed");
        }
        return aVar.f2082a;
    }

    public final void b() {
        this.f2342m = new HashMap();
    }

    public final s2 c() {
        if (this.f2339j) {
            throw new IllegalStateException("Cannot read while a writer is pending");
        }
        this.f2337h++;
        return new s2(this);
    }

    public final w2 d() {
        if (this.f2339j) {
            z.a("Cannot start a writer when another writer is pending");
        }
        if (this.f2337h > 0) {
            z.a("Cannot start a writer when a reader is pending");
        }
        this.f2339j = true;
        this.f2340k++;
        return new w2(this);
    }

    public final boolean e(a aVar) {
        int iE;
        return aVar.a() && (iE = v2.e(this.f2341l, aVar.f2082a, this.f2334e)) >= 0 && b4.l.a(this.f2341l.get(iE), aVar);
    }

    public final v0 f(int i5) {
        int i6;
        ArrayList arrayList;
        int iE;
        HashMap map = this.f2342m;
        if (map != null) {
            if (this.f2339j) {
                z.a("use active SlotWriter to crate an anchor for location instead");
            }
            a aVar = (i5 < 0 || i5 >= (i6 = this.f2334e) || (iE = v2.e((arrayList = this.f2341l), i5, i6)) < 0) ? null : (a) arrayList.get(iE);
            if (aVar != null) {
                return (v0) map.get(aVar);
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new u0(this, 0, this.f2334e);
    }
}
