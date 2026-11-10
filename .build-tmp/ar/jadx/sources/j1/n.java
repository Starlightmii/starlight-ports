package j1;

import p1.n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3268e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i5, Object obj) {
        super(1);
        this.f3268e = i5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3268e) {
            case 0:
                ((o) obj).getClass();
                return Boolean.TRUE;
            default:
                u0.c cVar = (u0.c) obj;
                if (!cVar.f5681d.f5693p) {
                    return n1.f4939e;
                }
                cVar.f5818q = null;
                return n1.f4938d;
        }
    }
}
