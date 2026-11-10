package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m.z0 f4444b = m.d.h(125, 2, new m.t(0.25f, 0.1f, 0.25f));

    @Override // p.d
    public final float a(float f5, float f6, float f7) {
        float fAbs = Math.abs((f6 + f5) - f5);
        float f8 = (0.3f * f7) - (0.0f * fAbs);
        float f9 = f7 - f8;
        if ((fAbs <= f7) && f9 < fAbs) {
            f8 = f7 - fAbs;
        }
        return f5 - f8;
    }

    @Override // p.d
    public final m.k b() {
        return this.f4444b;
    }
}
