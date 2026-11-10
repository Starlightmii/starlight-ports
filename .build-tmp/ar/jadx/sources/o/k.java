package o;

import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4341e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f4342f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ p3.c f4343g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public k(a4.a aVar, boolean z2) {
        super(0);
        this.f4342f = z2;
        this.f4343g = (b4.m) aVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(a4.c cVar, boolean z2) {
        super(0);
        this.f4343g = cVar;
        this.f4342f = z2;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [a4.a, b4.m] */
    @Override // a4.a
    public final Object a() {
        switch (this.f4341e) {
            case 0:
                if (this.f4342f) {
                    ((b4.m) this.f4343g).a();
                }
                break;
            default:
                ((a4.c) this.f4343g).c(Boolean.valueOf(!this.f4342f));
                break;
        }
        return n.f5026a;
    }
}
