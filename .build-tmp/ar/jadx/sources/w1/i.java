package w1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import q1.m0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i implements Iterable, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f6308d = new LinkedHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6310f;

    public final boolean a(t tVar) {
        return this.f6308d.containsKey(tVar);
    }

    public final Object b(t tVar) {
        Object obj = this.f6308d.get(tVar);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Key not present: " + tVar + " - consider getOrElse or getOrNull");
    }

    public final void c(t tVar, Object obj) {
        boolean z2 = obj instanceof a;
        LinkedHashMap linkedHashMap = this.f6308d;
        if (!z2 || !linkedHashMap.containsKey(tVar)) {
            linkedHashMap.put(tVar, obj);
            return;
        }
        Object obj2 = linkedHashMap.get(tVar);
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", obj2);
        a aVar = (a) obj2;
        a aVar2 = (a) obj;
        String str = aVar2.f6272a;
        if (str == null) {
            str = aVar.f6272a;
        }
        p3.c cVar = aVar2.f6273b;
        if (cVar == null) {
            cVar = aVar.f6273b;
        }
        linkedHashMap.put(tVar, new a(str, cVar));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return b4.l.a(this.f6308d, iVar.f6308d) && this.f6309e == iVar.f6309e && this.f6310f == iVar.f6310f;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f6310f) + b.b.c(this.f6308d.hashCode() * 31, 31, this.f6309e);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f6308d.entrySet().iterator();
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.f6309e) {
            sb.append("mergeDescendants=true");
            str = ", ";
        } else {
            str = "";
        }
        if (this.f6310f) {
            sb.append(str);
            sb.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry entry : this.f6308d.entrySet()) {
            t tVar = (t) entry.getKey();
            Object value = entry.getValue();
            sb.append(str);
            sb.append(tVar.f6364a);
            sb.append(" : ");
            sb.append(value);
            str = ", ";
        }
        return m0.w(this) + "{ " + ((Object) sb) + " }";
    }
}
