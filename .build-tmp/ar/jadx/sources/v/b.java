package v;

import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import c0.p4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5857e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f5858f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(long j5, int i5) {
        super(1);
        this.f5857e = i5;
        this.f5858f = j5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f5857e) {
            case 0:
                v0.c cVar = (v0.c) obj;
                float fD = x0.f.d(cVar.f6165d.b()) / 2.0f;
                y0.c0 c0VarT = p3.j.t(cVar, fD);
                int i5 = Build.VERSION.SDK_INT;
                long j5 = this.f5858f;
                return cVar.c(new p4(fD, c0VarT, new y0.m(j5, 5, i5 >= 29 ? y0.n.f6649a.a(j5, 5) : new PorterDuffColorFilter(y0.l0.w(j5), y0.l0.z(5)))));
            default:
                ((w1.i) obj).c(z.v.f7017c, new z.u(e0.f5895d, this.f5858f, 2, true));
                return p3.n.f5026a;
        }
    }
}
