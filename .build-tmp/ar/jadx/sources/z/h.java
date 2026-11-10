package z;

import android.graphics.PorterDuffColorFilter;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ long f6960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f6961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f6962g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(long j5, a4.a aVar, boolean z2) {
        super(1);
        this.f6960e = j5;
        this.f6961f = aVar;
        this.f6962g = z2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        v0.c cVar = (v0.c) obj;
        y0.c0 c0VarT = p3.j.t(cVar, x0.f.d(cVar.f6165d.b()) / 2.0f);
        int i5 = Build.VERSION.SDK_INT;
        long j5 = this.f6960e;
        return cVar.c(new g(this.f6961f, this.f6962g, c0VarT, new y0.m(j5, 5, i5 >= 29 ? y0.n.f6649a.a(j5, 5) : new PorterDuffColorFilter(y0.l0.w(j5), y0.l0.z(5)))));
    }
}
