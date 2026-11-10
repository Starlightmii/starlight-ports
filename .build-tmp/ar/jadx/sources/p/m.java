package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m implements p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f4531a;

    public m(n nVar) {
        this.f4531a = nVar;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [a4.c, b4.m] */
    @Override // p.p0
    public final float a(float f5) {
        if (Float.isNaN(f5)) {
            return 0.0f;
        }
        n nVar = this.f4531a;
        float fFloatValue = ((Number) nVar.f4538a.c(Float.valueOf(f5))).floatValue();
        nVar.f4542e.setValue(Boolean.valueOf(fFloatValue > 0.0f));
        nVar.f4543f.setValue(Boolean.valueOf(fFloatValue < 0.0f));
        return fFloatValue;
    }
}
