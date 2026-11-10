package f0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k1 implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2217d;

    public /* synthetic */ k1(int i5) {
        this.f2217d = i5;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.Collection, java.util.List] */
    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2217d) {
            case 0:
                obj.getClass();
                throw new ClassCastException();
            case 1:
                return Boolean.valueOf(obj == null);
            case 2:
                t3.f fVar = (t3.f) obj;
                if (fVar instanceof i4.r) {
                    return (i4.r) fVar;
                }
                return null;
            case 3:
                return obj;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                synchronized (p0.m.f4719c) {
                    ?? r4 = p0.m.f4725i;
                    int size = r4.size();
                    for (int i5 = 0; i5 < size; i5++) {
                        ((a4.c) r4.get(i5)).c(obj);
                    }
                    break;
                }
                break;
            default:
                k1 k1Var = p0.m.f4717a;
                break;
        }
        return p3.n.f5026a;
    }
}
