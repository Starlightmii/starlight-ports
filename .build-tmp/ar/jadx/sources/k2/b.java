package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    default int E(long j5) {
        return Math.round(U(j5));
    }

    default float I(long j5) {
        float fC;
        float fJ;
        if (!n.a(m.b(j5), 4294967296L)) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        float[] fArr = l2.b.f3500a;
        if (j() >= 1.03f) {
            l2.a aVarA = l2.b.a(j());
            fC = m.c(j5);
            if (aVarA != null) {
                return aVarA.b(fC);
            }
            fJ = j();
        } else {
            fC = m.c(j5);
            fJ = j();
        }
        return fJ * fC;
    }

    default int M(float f5) {
        float fV = v(f5);
        if (Float.isInfinite(fV)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(fV);
    }

    default long R(long j5) {
        if (j5 != 9205357640488583168L) {
            return p3.j.f(v(Float.intBitsToFloat((int) (j5 >> 32))), v(Float.intBitsToFloat((int) (j5 & 4294967295L))));
        }
        return 9205357640488583168L;
    }

    default float U(long j5) {
        if (n.a(m.b(j5), 4294967296L)) {
            return v(I(j5));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    float a();

    default long d0(float f5) {
        return s(j0(f5));
    }

    default float i0(int i5) {
        return i5 / a();
    }

    float j();

    default float j0(float f5) {
        return f5 / a();
    }

    default long s(float f5) {
        float[] fArr = l2.b.f3500a;
        if (j() < 1.03f) {
            return d4.a.B(f5 / j(), 4294967296L);
        }
        l2.a aVarA = l2.b.a(j());
        return d4.a.B(aVarA != null ? aVarA.a(f5) : f5 / j(), 4294967296L);
    }

    default long u(long j5) {
        if (j5 != 9205357640488583168L) {
            return a.a.d(j0(x0.f.d(j5)), j0(x0.f.b(j5)));
        }
        return 9205357640488583168L;
    }

    default float v(float f5) {
        return a() * f5;
    }
}
