package p0;

import android.os.Parcel;
import android.os.Parcelable;
import f0.x1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q implements Parcelable, x, List, RandomAccess, c4.b {
    public static final Parcelable.Creator<q> CREATOR = new p();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w f4730d;

    public q() {
        this(j0.j.f3207e);
    }

    public q(j0.c cVar) {
        f fVarJ = m.j();
        w wVar = new w(fVarJ.g(), cVar);
        if (!(fVarJ instanceof a)) {
            wVar.f4768b = new w(1, cVar);
        }
        this.f4730d = wVar;
    }

    @Override // p0.x
    public final z a() {
        return this.f4730d;
    }

    @Override // java.util.List
    public final void add(int i5, Object obj) {
        int i6;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i6 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarB = cVar.b(i5, obj);
            if (cVarB.equals(cVar)) {
                return;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i6, cVarB, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarC = cVar.c(obj);
            if (cVarC.equals(cVar)) {
                return false;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i5, cVarC, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(final int i5, final Collection collection) {
        return r.h(this, new a4.c() { // from class: p0.o
            @Override // a4.c
            public final Object c(Object obj) {
                return Boolean.valueOf(((List) obj).addAll(i5, collection));
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarD = cVar.d(collection);
            if (b4.l.a(cVarD, cVar)) {
                return false;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i5, cVarD, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return true;
    }

    @Override // p0.x
    public final void c(z zVar) {
        zVar.f4768b = this.f4730d;
        this.f4730d = (w) zVar;
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        f fVarJ;
        w wVar = this.f4730d;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar);
        synchronized (m.f4719c) {
            fVarJ = m.j();
            w wVar2 = (w) m.w(wVar, this, fVarJ);
            synchronized (r.f4731a) {
                wVar2.f4763c = j0.j.f3207e;
                wVar2.f4764d++;
                wVar2.f4765e++;
            }
        }
        m.n(fVarJ, this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return r.e(this).f4763c.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return r.e(this).f4763c.containsAll(collection);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i5) {
        return r.e(this).f4763c.get(i5);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return r.e(this).f4763c.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return r.e(this).f4763c.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return r.e(this).f4763c.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new v(this, 0);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i5) {
        return new v(this, i5);
    }

    @Override // java.util.List
    public final Object remove(int i5) {
        int i6;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        Object obj = get(i5);
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i6 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarG = cVar.g(i5);
            if (cVarG.equals(cVar)) {
                break;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i6, cVarG, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return obj;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            int iIndexOf = cVar.indexOf(obj);
            j0.c cVarG = iIndexOf != -1 ? cVar.g(iIndexOf) : cVar;
            if (cVarG.equals(cVar)) {
                return false;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i5, cVarG, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i5;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i5 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarF = cVar.f(new j0.b(0, collection));
            if (b4.l.a(cVarF, cVar)) {
                return false;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i5, cVarF, true);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        return r.h(this, new j0.b(2, collection));
    }

    @Override // java.util.List
    public final Object set(int i5, Object obj) {
        int i6;
        j0.c cVar;
        f fVarJ;
        boolean zB;
        Object obj2 = get(i5);
        do {
            synchronized (r.f4731a) {
                w wVar = this.f4730d;
                b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>", wVar);
                w wVar2 = (w) m.h(wVar);
                i6 = wVar2.f4764d;
                cVar = wVar2.f4763c;
            }
            b4.l.b(cVar);
            j0.c cVarH = cVar.h(i5, obj);
            if (cVarH.equals(cVar)) {
                break;
            }
            w wVar3 = this.f4730d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>", wVar3);
            synchronized (m.f4719c) {
                fVarJ = m.j();
                zB = r.b((w) m.w(wVar3, this, fVarJ), i6, cVarH, false);
            }
            m.n(fVarJ, this);
        } while (!zB);
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return r.e(this).f4763c.a();
    }

    @Override // java.util.List
    public final List subList(int i5, int i6) {
        if (!(i5 >= 0 && i5 <= i6 && i6 <= size())) {
            x1.a("fromIndex or toIndex are out of bounds");
        }
        return new b0(this, i5, i6);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return b4.k.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return b4.k.b(this, objArr);
    }

    public final String toString() {
        w wVar = this.f4730d;
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>", wVar);
        return "SnapshotStateList(value=" + ((w) m.h(wVar)).f4763c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        j0.c cVar = r.e(this).f4763c;
        int iA = cVar.a();
        parcel.writeInt(iA);
        for (int i6 = 0; i6 < iA; i6++) {
            parcel.writeValue(cVar.get(i6));
        }
    }
}
