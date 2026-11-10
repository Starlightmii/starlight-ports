package b0;

import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f446e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i1 f447f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(i1 i1Var, int i5) {
        super(0);
        this.f446e = i5;
        this.f447f = i1Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f446e) {
            case 0:
                return (i) this.f447f.getValue();
            default:
                Boolean bool = (Boolean) this.f447f.getValue();
                bool.booleanValue();
                return bool;
        }
    }
}
