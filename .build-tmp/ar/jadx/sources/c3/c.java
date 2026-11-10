package c3;

import androidx.lifecycle.k0;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;
import r3.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1503f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1504g;

    public c() {
        if (k0.f260e == null) {
            k0.f260e = new k0(10);
        }
    }

    public int a(int i5) {
        if (i5 < this.f1503f) {
            return ((ByteBuffer) this.f1504g).getShort(this.f1502e + i5);
        }
        return 0;
    }

    public void b() {
        if (((f) this.f1504g).f5732k != this.f1503f) {
            throw new ConcurrentModificationException();
        }
    }

    public void c() {
        while (true) {
            int i5 = this.f1501d;
            f fVar = (f) this.f1504g;
            if (i5 >= fVar.f5730i || fVar.f5727f[i5] >= 0) {
                return;
            } else {
                this.f1501d = i5 + 1;
            }
        }
    }

    public boolean hasNext() {
        return this.f1501d < ((f) this.f1504g).f5730i;
    }

    public void remove() {
        f fVar = (f) this.f1504g;
        b();
        if (this.f1502e == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        fVar.b();
        fVar.k(this.f1502e);
        this.f1502e = -1;
        this.f1503f = fVar.f5732k;
    }
}
