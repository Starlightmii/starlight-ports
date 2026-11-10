package p3;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements d, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a4.a f5019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f5020e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f5021f;

    public k(a4.a aVar) {
        b4.l.e("initializer", aVar);
        this.f5019d = aVar;
        this.f5020e = m.f5025a;
        this.f5021f = this;
    }

    @Override // p3.d
    public final Object getValue() {
        Object objA;
        Object obj = this.f5020e;
        m mVar = m.f5025a;
        if (obj != mVar) {
            return obj;
        }
        synchronized (this.f5021f) {
            objA = this.f5020e;
            if (objA == mVar) {
                a4.a aVar = this.f5019d;
                b4.l.b(aVar);
                objA = aVar.a();
                this.f5020e = objA;
                this.f5019d = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.f5020e != m.f5025a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
