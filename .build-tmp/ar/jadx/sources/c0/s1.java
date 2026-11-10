package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s1 extends b4.r implements f4.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1276k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s1(Object obj, Class cls, String str, String str2, int i5, int i6) {
        super(i5, cls, obj, str, str2);
        this.f1276k = i6;
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
        switch (this.f1276k) {
            case 0:
                return ((f0.g3) this.f681e).getValue();
            default:
                return this.f681e.getClass().getSimpleName();
        }
    }
}
