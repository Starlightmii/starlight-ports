package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ float f868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f869f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(float f5, long j5) {
        super(1);
        this.f868e = f5;
        this.f869f = j5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        a1.f fVar = (a1.f) obj;
        float f5 = this.f868e;
        float f6 = 2;
        fVar.X(this.f869f, p3.j.b(0.0f, fVar.v(f5) / f6), p3.j.b(x0.f.d(fVar.b()), fVar.v(f5) / f6), fVar.v(f5));
        return p3.n.f5026a;
    }
}
