package b3;

import androidx.lifecycle.k0;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f636b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f637c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f638d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f639e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f640f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f641g;

    public s() {
        this.f635a = 1;
        this.f639e = new k0(17);
        this.f640f = new HashMap(0, 0.75f);
        this.f641g = new LinkedHashSet();
    }

    public s(v vVar) {
        this.f635a = 0;
        this.f636b = 1;
        this.f639e = vVar;
        this.f640f = vVar;
    }

    public Object a(Object obj) {
        synchronized (((k0) this.f639e)) {
            Object obj2 = ((HashMap) this.f640f).get(obj);
            if (obj2 == null) {
                this.f638d++;
                return null;
            }
            ((LinkedHashSet) this.f641g).remove(obj);
            ((LinkedHashSet) this.f641g).add(obj);
            this.f637c++;
            return obj2;
        }
    }

    public Object b(Object obj, Object obj2) {
        Object objPut;
        Object next;
        Object obj3;
        if (obj == null) {
            throw null;
        }
        synchronized (((k0) this.f639e)) {
            try {
                this.f636b = e() + 1;
                objPut = ((HashMap) this.f640f).put(obj, obj2);
                if (objPut != null) {
                    this.f636b = e() - 1;
                }
                if (((LinkedHashSet) this.f641g).contains(obj)) {
                    ((LinkedHashSet) this.f641g).remove(obj);
                }
                ((LinkedHashSet) this.f641g).add(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        while (true) {
            synchronized (((k0) this.f639e)) {
                try {
                    if (e() >= 0 && (!((HashMap) this.f640f).isEmpty() || e() == 0)) {
                        if (((HashMap) this.f640f).isEmpty() != ((LinkedHashSet) this.f641g).isEmpty()) {
                            break;
                        }
                        if (e() <= 16 || ((HashMap) this.f640f).isEmpty()) {
                            next = null;
                            obj3 = null;
                        } else {
                            Collection collection = (LinkedHashSet) this.f641g;
                            b4.l.e("<this>", collection);
                            if (collection instanceof List) {
                                next = q3.l.W((List) collection);
                            } else {
                                Iterator it = collection.iterator();
                                if (!it.hasNext()) {
                                    throw new NoSuchElementException("Collection is empty.");
                                }
                                next = it.next();
                            }
                            obj3 = ((HashMap) this.f640f).get(next);
                            if (obj3 == null) {
                                throw new IllegalStateException("inconsistent state");
                            }
                            b4.z.b((HashMap) this.f640f).remove(next);
                            LinkedHashSet linkedHashSet = (LinkedHashSet) this.f641g;
                            b4.z.a(linkedHashSet);
                            linkedHashSet.remove(next);
                            int iE = e();
                            b4.l.b(next);
                            this.f636b = iE - 1;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            if (next == null && obj3 == null) {
                return objPut;
            }
            b4.l.b(next);
            b4.l.b(obj3);
        }
        throw new IllegalStateException("map/keySet size inconsistency");
    }

    public void c() {
        this.f636b = 1;
        this.f640f = (v) this.f639e;
        this.f638d = 0;
    }

    public boolean d() {
        c3.a aVarB = ((v) this.f640f).f655b.b();
        int iA = aVarB.a(6);
        return !(iA == 0 || ((ByteBuffer) aVarB.f1504g).get(iA + aVarB.f1501d) == 0) || this.f637c == 65039;
    }

    public int e() {
        int i5;
        synchronized (((k0) this.f639e)) {
            i5 = this.f636b;
        }
        return i5;
    }

    public String toString() {
        String str;
        switch (this.f635a) {
            case 1:
                synchronized (((k0) this.f639e)) {
                    try {
                        int i5 = this.f637c;
                        int i6 = this.f638d + i5;
                        str = "LruCache[maxSize=16,hits=" + this.f637c + ",misses=" + this.f638d + ",hitRate=" + (i6 != 0 ? (i5 * 100) / i6 : 0) + "%]";
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str;
            default:
                return super.toString();
        }
    }
}
