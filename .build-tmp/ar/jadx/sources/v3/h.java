package v3;

import b4.l;
import b4.x;
import b4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class h extends g implements b4.i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6212d;

    public h(t3.c cVar) {
        super(cVar);
        this.f6212d = 2;
    }

    @Override // b4.i
    public final int getArity() {
        return this.f6212d;
    }

    @Override // v3.a
    public final String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        x.f698a.getClass();
        String strA = y.a(this);
        l.d("renderLambdaToString(...)", strA);
        return strA;
    }
}
