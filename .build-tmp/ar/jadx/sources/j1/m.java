package j1;

import p1.n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3266e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ b4.s f3267f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(a1.d dVar, u0.c cVar, b4.s sVar) {
        super(1);
        this.f3267f = sVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(b4.s sVar) {
        super(1);
        this.f3267f = sVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3266e) {
            case 0:
                if (!((o) obj).f3269q) {
                    return n1.f4938d;
                }
                this.f3267f.f693d = false;
                return n1.f4940f;
            default:
                if (!((u0.c) obj).f5693p) {
                    return n1.f4939e;
                }
                b4.s sVar = this.f3267f;
                sVar.f693d = sVar.f693d;
                return n1.f4938d;
        }
    }
}
