package c0;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e4 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f835e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e4(int i5) {
        super(3);
        this.f835e = i5;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        List list = (List) obj;
        f0.p pVar = (f0.p) obj2;
        ((Number) obj3).intValue();
        int size = list.size();
        int i5 = this.f835e;
        if (i5 < size) {
            d4.f808a.a(r0.a.a(r0.m.f5680a, new s3(1, (b4) list.get(i5))), 0.0f, 0L, pVar, 3072);
        }
        return p3.n.f5026a;
    }
}
