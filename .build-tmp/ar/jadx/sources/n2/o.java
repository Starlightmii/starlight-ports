package n2;

import b4.v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ v f4231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ p f4232f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ k2.i f4233g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f4234h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f4235i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(v vVar, p pVar, k2.i iVar, long j5, long j6) {
        super(0);
        this.f4231e = vVar;
        this.f4232f = pVar;
        this.f4233g = iVar;
        this.f4234h = j5;
        this.f4235i = j6;
    }

    @Override // a4.a
    public final Object a() {
        p pVar = this.f4232f;
        this.f4231e.f696d = pVar.getPositionProvider().a(this.f4233g, this.f4234h, pVar.getParentLayoutDirection(), this.f4235i);
        return p3.n.f5026a;
    }
}
