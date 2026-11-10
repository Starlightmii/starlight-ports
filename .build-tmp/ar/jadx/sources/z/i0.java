package z;

import android.os.Build;
import androidx.compose.foundation.MagnifierElement;
import f0.i1;
import n.b1;
import n.q0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ k2.b f6969f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ i1 f6970g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i0(k2.b bVar, i1 i1Var, int i5) {
        super(1);
        this.f6968e = i5;
        this.f6969f = bVar;
        this.f6970g = i1Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f6968e) {
            case 0:
                long j5 = ((k2.g) obj).f3408a;
                float fIntBitsToFloat = Float.intBitsToFloat((int) (j5 >> 32));
                k2.b bVar = this.f6969f;
                this.f6970g.setValue(new k2.j(a.a.f(bVar.M(fIntBitsToFloat), bVar.M(Float.intBitsToFloat((int) (j5 & 4294967295L))))));
                return p3.n.f5026a;
            default:
                w1.l lVar = new w1.l(3, (a4.a) obj);
                i0 i0Var = new i0(this.f6969f, this.f6970g, 0);
                if (q0.a()) {
                    return q0.a() ? new MagnifierElement(lVar, i0Var, Build.VERSION.SDK_INT == 28 ? b1.f3873b : b1.f3874c) : r0.m.f5680a;
                }
                throw new UnsupportedOperationException("Magnifier is only supported on API level 28 and higher.");
        }
    }
}
