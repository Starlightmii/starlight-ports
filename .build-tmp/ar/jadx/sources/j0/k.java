package j0;

import b4.l;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object[] f3210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3211h;

    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v3 */
    public k(Object[] objArr, int i5, int i6, int i7) {
        super(i5, i6);
        this.f3209f = i7;
        Object[] objArr2 = new Object[i7];
        this.f3210g = objArr2;
        ?? r5 = i5 == i6 ? 1 : 0;
        this.f3211h = r5;
        objArr2[0] = objArr;
        b(i5 - r5, 1);
    }

    public final Object a() {
        int i5 = this.f3182d & 31;
        Object obj = this.f3210g[this.f3209f - 1];
        l.c("null cannot be cast to non-null type kotlin.Array<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.TrieIterator>", obj);
        return ((Object[]) obj)[i5];
    }

    public final void b(int i5, int i6) {
        int i7 = (this.f3209f - i6) * 5;
        while (i6 < this.f3209f) {
            Object[] objArr = this.f3210g;
            Object obj = objArr[i6 - 1];
            l.c("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", obj);
            objArr[i6] = ((Object[]) obj)[d4.a.x(i5, i7)];
            i7 -= 5;
            i6++;
        }
    }

    public final void c(int i5) {
        int i6 = 0;
        while (d4.a.x(this.f3182d, i6) == i5) {
            i6 += 5;
        }
        if (i6 > 0) {
            b(this.f3182d, ((this.f3209f - 1) - (i6 / 5)) + 1);
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objA = a();
        int i5 = this.f3182d + 1;
        this.f3182d = i5;
        if (i5 == this.f3183e) {
            this.f3211h = true;
            return objA;
        }
        c(0);
        return objA;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        this.f3182d--;
        if (this.f3211h) {
            this.f3211h = false;
            return a();
        }
        c(31);
        return a();
    }
}
