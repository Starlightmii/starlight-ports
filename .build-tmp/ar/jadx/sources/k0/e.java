package k0;

import b4.z;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final n0.g f3366g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f3367h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3368i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3369j;

    public e(n0.g gVar, n[] nVarArr) {
        super(gVar.f4064e, nVarArr);
        this.f3366g = gVar;
        this.f3369j = gVar.f4066g;
    }

    public final void c(int i5, m mVar, Object obj, int i6) {
        int i7 = i6 * 5;
        n[] nVarArr = this.f3363d;
        if (i7 <= 30) {
            int iZ = 1 << h1.c.z(i5, i7);
            if (mVar.h(iZ)) {
                nVarArr[i6].a(mVar.f3383d, Integer.bitCount(mVar.f3380a) * 2, mVar.f(iZ));
                this.f3364e = i6;
                return;
            } else {
                int iT = mVar.t(iZ);
                m mVarS = mVar.s(iT);
                nVarArr[i6].a(mVar.f3383d, Integer.bitCount(mVar.f3380a) * 2, iT);
                c(i5, mVarS, obj, i6 + 1);
                return;
            }
        }
        n nVar = nVarArr[i6];
        Object[] objArr = mVar.f3383d;
        nVar.a(objArr, objArr.length, 0);
        while (true) {
            n nVar2 = nVarArr[i6];
            if (b4.l.a(nVar2.f3384d[nVar2.f3386f], obj)) {
                this.f3364e = i6;
                return;
            } else {
                nVarArr[i6].f3386f += 2;
            }
        }
    }

    @Override // k0.d, java.util.Iterator
    public final Object next() {
        if (this.f3366g.f4066g != this.f3369j) {
            throw new ConcurrentModificationException();
        }
        if (!this.f3365f) {
            throw new NoSuchElementException();
        }
        n nVar = this.f3363d[this.f3364e];
        this.f3367h = nVar.f3384d[nVar.f3386f];
        this.f3368i = true;
        return super.next();
    }

    @Override // k0.d, java.util.Iterator
    public final void remove() {
        if (!this.f3368i) {
            throw new IllegalStateException();
        }
        boolean z2 = this.f3365f;
        n0.g gVar = this.f3366g;
        if (!z2) {
            z.b(gVar).remove(this.f3367h);
        } else {
            if (!z2) {
                throw new NoSuchElementException();
            }
            n nVar = this.f3363d[this.f3364e];
            Object obj = nVar.f3384d[nVar.f3386f];
            z.b(gVar).remove(this.f3367h);
            c(obj != null ? obj.hashCode() : 0, gVar.f4064e, obj, 0);
        }
        this.f3367h = null;
        this.f3368i = false;
        this.f3369j = gVar.f4066g;
    }
}
