package d0;

import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n extends b4.o implements f4.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1549k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Object obj, Class cls, String str, String str2, int i5, int i6) {
        super(i5, cls, obj, str, str2);
        this.f1549k = i6;
    }

    @Override // a4.a
    public final Object a() {
        return get();
    }

    @Override // b4.d
    public final f4.a d() {
        b4.x.f698a.getClass();
        return this;
    }

    @Override // f4.c
    public final Object get() {
        switch (this.f1549k) {
            case 0:
                return ((i1) this.f681e).getValue();
            default:
                return ((q1.u) this.f681e).getLayoutDirection();
        }
    }
}
