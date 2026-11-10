package w1;

import x.w;
import y0.n0;
import y0.r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6312e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f6313f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i5, Object obj) {
        super(1);
        this.f6312e = i5;
        this.f6313f = obj;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f6312e) {
            case 0:
                s.d((i) obj, ((f) this.f6313f).f6279a);
                return p3.n.f5026a;
            case 1:
                ((w) this.f6313f).a((e2.i) obj);
                return p3.n.f5026a;
            case 2:
                n0 n0Var = (n0) obj;
                r0 r0Var = (r0) this.f6313f;
                n0Var.e(r0Var.f6673q);
                n0Var.g(r0Var.f6674r);
                n0Var.c(r0Var.f6675s);
                n0Var.h(r0Var.f6676t);
                float f5 = r0Var.f6677u;
                if (n0Var.f6657k != f5) {
                    n0Var.f6650d |= 2048;
                    n0Var.f6657k = f5;
                }
                n0Var.m(r0Var.f6678v);
                n0Var.i(r0Var.f6679w);
                boolean z2 = r0Var.f6680x;
                if (n0Var.f6660n != z2) {
                    n0Var.f6650d |= 16384;
                    n0Var.f6660n = z2;
                }
                n0Var.d(r0Var.f6681y);
                n0Var.k(r0Var.f6682z);
                return p3.n.f5026a;
            default:
                return new x0.c(((x0.c) ((a4.a) this.f6313f).a()).f6532a);
        }
    }
}
