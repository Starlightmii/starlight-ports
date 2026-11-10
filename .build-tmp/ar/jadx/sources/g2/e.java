package g2;

import b4.m;
import p1.j0;
import p1.n0;
import p3.n;
import y0.o0;
import y0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2528e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f2529f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2530g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i5, long j5, Object obj) {
        super(0);
        this.f2528e = i5;
        this.f2530g = obj;
        this.f2529f = j5;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f2528e) {
            case 0:
                return ((o0) ((p) this.f2530g)).b(this.f2529f);
            default:
                n0 n0VarJ0 = ((j0) this.f2530g).a().J0();
                b4.l.b(n0VarJ0);
                n0VarJ0.c(this.f2529f);
                return n.f5026a;
        }
    }
}
