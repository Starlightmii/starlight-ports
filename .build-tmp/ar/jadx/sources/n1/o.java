package n1;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o implements h0, m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k2.k f4141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m f4142e;

    public o(m mVar, k2.k kVar) {
        this.f4141d = kVar;
        this.f4142e = mVar;
    }

    @Override // k2.b
    public final int E(long j5) {
        return this.f4142e.E(j5);
    }

    @Override // k2.b
    public final float I(long j5) {
        return this.f4142e.I(j5);
    }

    @Override // k2.b
    public final int M(float f5) {
        return this.f4142e.M(f5);
    }

    @Override // k2.b
    public final long R(long j5) {
        return this.f4142e.R(j5);
    }

    @Override // n1.h0
    public final g0 S(int i5, int i6, Map map, a4.c cVar) {
        if (i5 < 0) {
            i5 = 0;
        }
        if (i6 < 0) {
            i6 = 0;
        }
        if ((i5 & (-16777216)) == 0 && ((-16777216) & i6) == 0) {
            return new n(i5, i6, map);
        }
        d4.a.L("Size(" + i5 + " x " + i6 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    @Override // k2.b
    public final float U(long j5) {
        return this.f4142e.U(j5);
    }

    @Override // k2.b
    public final float a() {
        return this.f4142e.a();
    }

    @Override // k2.b
    public final long d0(float f5) {
        return this.f4142e.d0(f5);
    }

    @Override // n1.m
    public final k2.k getLayoutDirection() {
        return this.f4141d;
    }

    @Override // k2.b
    public final float i0(int i5) {
        return this.f4142e.i0(i5);
    }

    @Override // k2.b
    public final float j() {
        return this.f4142e.j();
    }

    @Override // k2.b
    public final float j0(float f5) {
        return this.f4142e.j0(f5);
    }

    @Override // n1.m
    public final boolean o() {
        return this.f4142e.o();
    }

    @Override // k2.b
    public final long s(float f5) {
        return this.f4142e.s(f5);
    }

    @Override // k2.b
    public final long u(long j5) {
        return this.f4142e.u(j5);
    }

    @Override // k2.b
    public final float v(float f5) {
        return this.f4142e.v(f5);
    }
}
