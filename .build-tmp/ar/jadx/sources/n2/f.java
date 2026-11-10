package n2;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4215e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ p f4216f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(p pVar, int i5) {
        super(1);
        this.f4215e = i5;
        this.f4216f = pVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f4215e) {
            case 0:
                n1.p pVarI = ((n1.p) obj).i();
                b4.l.b(pVarI);
                this.f4216f.k(pVarI);
                break;
            case 1:
                k2.j jVar = new k2.j(((k2.j) obj).f3415a);
                p pVar = this.f4216f;
                pVar.m12setPopupContentSizefhxjrPA(jVar);
                pVar.l();
                break;
            default:
                a4.a aVar = (a4.a) obj;
                p pVar2 = this.f4216f;
                Handler handler = pVar2.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    aVar.a();
                } else {
                    Handler handler2 = pVar2.getHandler();
                    if (handler2 != null) {
                        handler2.post(new n(aVar, 0));
                    }
                }
                break;
        }
        return p3.n.f5026a;
    }
}
