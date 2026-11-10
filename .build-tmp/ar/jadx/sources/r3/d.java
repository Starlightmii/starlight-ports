package r3;

import b4.l;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends c3.c implements Iterator, c4.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f5720h;

    public d(f fVar, int i5) {
        this.f5720h = i5;
        l.e("map", fVar);
        this.f1504g = fVar;
        this.f1502e = -1;
        this.f1503f = fVar.f5732k;
        c();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f5720h) {
            case 0:
                b();
                int i5 = this.f1501d;
                f fVar = (f) this.f1504g;
                if (i5 >= fVar.f5730i) {
                    throw new NoSuchElementException();
                }
                this.f1501d = i5 + 1;
                this.f1502e = i5;
                e eVar = new e(fVar, i5);
                c();
                return eVar;
            case 1:
                b();
                int i6 = this.f1501d;
                f fVar2 = (f) this.f1504g;
                if (i6 >= fVar2.f5730i) {
                    throw new NoSuchElementException();
                }
                this.f1501d = i6 + 1;
                this.f1502e = i6;
                Object obj = fVar2.f5725d[i6];
                c();
                return obj;
            default:
                b();
                int i7 = this.f1501d;
                f fVar3 = (f) this.f1504g;
                if (i7 >= fVar3.f5730i) {
                    throw new NoSuchElementException();
                }
                this.f1501d = i7 + 1;
                this.f1502e = i7;
                Object[] objArr = fVar3.f5726e;
                l.b(objArr);
                Object obj2 = objArr[this.f1502e];
                c();
                return obj2;
        }
    }
}
