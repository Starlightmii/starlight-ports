package o;

import f0.p;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f4344e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f4345f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a f4346g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ b4.m f4347h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4348i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public l(String str, boolean z2, a aVar, a4.a aVar2, int i5) {
        super(2);
        this.f4344e = str;
        this.f4345f = z2;
        this.f4346g = aVar;
        this.f4347h = (b4.m) aVar2;
        this.f4348i = i5;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [a4.a, b4.m] */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        m.b(this.f4344e, this.f4345f, this.f4346g, this.f4347h, (p) obj, f0.l.z(this.f4348i | 1));
        return n.f5026a;
    }
}
