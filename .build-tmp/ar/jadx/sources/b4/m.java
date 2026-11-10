package b4;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m implements i, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f690d;

    public m(int i5) {
        this.f690d = i5;
    }

    @Override // b4.i
    public final int getArity() {
        return this.f690d;
    }

    public final String toString() {
        x.f698a.getClass();
        String strA = y.a(this);
        l.d("renderLambdaToString(...)", strA);
        return strA;
    }
}
