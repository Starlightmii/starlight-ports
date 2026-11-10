package e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends b0 implements Iterable, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f1971d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f1972e;

    public z(List list, ArrayList arrayList) {
        this.f1971d = list;
        this.f1972e = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return b4.l.a(this.f1971d, zVar.f1971d) && this.f1972e.equals(zVar.f1972e);
    }

    public final int hashCode() {
        return this.f1972e.hashCode() + ((this.f1971d.hashCode() + b.b.a(0.0f, b.b.a(0.0f, b.b.a(1.0f, b.b.a(1.0f, b.b.a(0.0f, b.b.a(0.0f, Float.hashCode(0.0f) * 31, 31), 31), 31), 31), 31), 31)) * 31);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new y(this);
    }
}
