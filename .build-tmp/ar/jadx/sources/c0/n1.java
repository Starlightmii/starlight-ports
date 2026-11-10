package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f1101e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f1102f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n1(a4.a aVar, boolean z2) {
        super(1);
        this.f1101e = z2;
        this.f1102f = aVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        ((y0.n0) obj).c(this.f1101e ? 1.0f : ((Number) this.f1102f.a()).floatValue());
        return p3.n.f5026a;
    }
}
