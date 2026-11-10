package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l4 implements y0.v, b4.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s1 f1036a;

    public l4(s1 s1Var) {
        this.f1036a = s1Var;
    }

    @Override // b4.h
    public final p3.c a() {
        return this.f1036a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y0.v) || !(obj instanceof b4.h)) {
            return false;
        }
        return this.f1036a.equals(((b4.h) obj).a());
    }

    public final int hashCode() {
        return this.f1036a.hashCode();
    }
}
