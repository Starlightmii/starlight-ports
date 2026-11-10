package j0;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g f3203f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3204g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public k f3205h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3206i;

    public i(g gVar, int i5) {
        super(i5, gVar.f3200k);
        this.f3203f = gVar;
        this.f3204g = gVar.e();
        this.f3206i = -1;
        b();
    }

    public final void a() {
        if (this.f3204g != this.f3203f.e()) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // j0.a, java.util.ListIterator
    public final void add(Object obj) {
        a();
        int i5 = this.f3182d;
        g gVar = this.f3203f;
        gVar.add(i5, obj);
        this.f3182d++;
        this.f3183e = gVar.a();
        this.f3204g = gVar.e();
        this.f3206i = -1;
        b();
    }

    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v4 */
    public final void b() {
        g gVar = this.f3203f;
        Object[] objArr = gVar.f3198i;
        if (objArr == null) {
            this.f3205h = null;
            return;
        }
        int i5 = (gVar.f3200k - 1) & (-32);
        int i6 = this.f3182d;
        if (i6 > i5) {
            i6 = i5;
        }
        int i7 = (gVar.f3196g / 5) + 1;
        k kVar = this.f3205h;
        if (kVar == null) {
            this.f3205h = new k(objArr, i6, i5, i7);
            return;
        }
        kVar.f3182d = i6;
        kVar.f3183e = i5;
        kVar.f3209f = i7;
        if (kVar.f3210g.length < i7) {
            kVar.f3210g = new Object[i7];
        }
        kVar.f3210g[0] = objArr;
        ?? r6 = i6 == i5 ? 1 : 0;
        kVar.f3211h = r6;
        kVar.b(i6 - r6, 1);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        a();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i5 = this.f3182d;
        this.f3206i = i5;
        k kVar = this.f3205h;
        g gVar = this.f3203f;
        if (kVar == null) {
            Object[] objArr = gVar.f3199j;
            this.f3182d = i5 + 1;
            return objArr[i5];
        }
        if (kVar.hasNext()) {
            this.f3182d++;
            return kVar.next();
        }
        Object[] objArr2 = gVar.f3199j;
        int i6 = this.f3182d;
        this.f3182d = i6 + 1;
        return objArr2[i6 - kVar.f3183e];
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        a();
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i5 = this.f3182d;
        this.f3206i = i5 - 1;
        k kVar = this.f3205h;
        g gVar = this.f3203f;
        if (kVar == null) {
            Object[] objArr = gVar.f3199j;
            int i6 = i5 - 1;
            this.f3182d = i6;
            return objArr[i6];
        }
        int i7 = kVar.f3183e;
        if (i5 <= i7) {
            this.f3182d = i5 - 1;
            return kVar.previous();
        }
        Object[] objArr2 = gVar.f3199j;
        int i8 = i5 - 1;
        this.f3182d = i8;
        return objArr2[i8 - i7];
    }

    @Override // j0.a, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        a();
        int i5 = this.f3206i;
        if (i5 == -1) {
            throw new IllegalStateException();
        }
        g gVar = this.f3203f;
        gVar.b(i5);
        int i6 = this.f3206i;
        if (i6 < this.f3182d) {
            this.f3182d = i6;
        }
        this.f3183e = gVar.a();
        this.f3204g = gVar.e();
        this.f3206i = -1;
        b();
    }

    @Override // j0.a, java.util.ListIterator
    public final void set(Object obj) {
        a();
        int i5 = this.f3206i;
        if (i5 == -1) {
            throw new IllegalStateException();
        }
        g gVar = this.f3203f;
        gVar.set(i5, obj);
        this.f3204g = gVar.e();
        b();
    }
}
