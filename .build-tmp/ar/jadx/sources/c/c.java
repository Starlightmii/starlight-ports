package c;

import b4.m;
import f0.p;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f705e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f706f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(boolean z2, a4.a aVar, int i5) {
        super(2);
        this.f705e = z2;
        this.f706f = aVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        a4.a aVar = this.f706f;
        h1.c.a(this.f705e, aVar, (p) obj, 49);
        return n.f5026a;
    }
}
