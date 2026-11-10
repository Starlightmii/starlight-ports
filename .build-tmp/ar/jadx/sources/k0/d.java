package k0;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n[] f3363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3365f = true;

    public d(m mVar, n[] nVarArr) {
        this.f3363d = nVarArr;
        nVarArr[0].a(mVar.f3383d, Integer.bitCount(mVar.f3380a) * 2, 0);
        this.f3364e = 0;
        a();
    }

    public final void a() {
        int i5 = this.f3364e;
        n[] nVarArr = this.f3363d;
        n nVar = nVarArr[i5];
        if (nVar.f3386f < nVar.f3385e) {
            return;
        }
        while (-1 < i5) {
            int iB = b(i5);
            if (iB == -1) {
                n nVar2 = nVarArr[i5];
                int i6 = nVar2.f3386f;
                Object[] objArr = nVar2.f3384d;
                if (i6 < objArr.length) {
                    int length = objArr.length;
                    nVar2.f3386f = i6 + 1;
                    iB = b(i5);
                }
            }
            if (iB != -1) {
                this.f3364e = iB;
                return;
            }
            if (i5 > 0) {
                n nVar3 = nVarArr[i5 - 1];
                int i7 = nVar3.f3386f;
                int length2 = nVar3.f3384d.length;
                nVar3.f3386f = i7 + 1;
            }
            nVarArr[i5].a(m.f3379e.f3383d, 0, 0);
            i5--;
        }
        this.f3365f = false;
    }

    public final int b(int i5) {
        n[] nVarArr = this.f3363d;
        n nVar = nVarArr[i5];
        int i6 = nVar.f3386f;
        if (i6 < nVar.f3385e) {
            return i5;
        }
        Object[] objArr = nVar.f3384d;
        if (i6 >= objArr.length) {
            return -1;
        }
        int length = objArr.length;
        Object obj = objArr[i6];
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator>", obj);
        m mVar = (m) obj;
        if (i5 == 6) {
            n nVar2 = nVarArr[i5 + 1];
            Object[] objArr2 = mVar.f3383d;
            nVar2.a(objArr2, objArr2.length, 0);
        } else {
            nVarArr[i5 + 1].a(mVar.f3383d, Integer.bitCount(mVar.f3380a) * 2, 0);
        }
        return b(i5 + 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f3365f;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!this.f3365f) {
            throw new NoSuchElementException();
        }
        Object next = this.f3363d[this.f3364e].next();
        a();
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
