package v3;

import b4.l;
import b4.x;
import b4.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i extends c implements b4.i {
    private final int arity;

    public i(int i5, t3.c cVar) {
        super(cVar);
        this.arity = i5;
    }

    @Override // b4.i
    public int getArity() {
        return this.arity;
    }

    @Override // v3.a
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        x.f698a.getClass();
        String strA = y.a(this);
        l.d("renderLambdaToString(...)", strA);
        return strA;
    }
}
