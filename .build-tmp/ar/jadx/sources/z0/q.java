package z0;

import e2.f0;
import java.util.Arrays;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends c {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final f0 f7087r = new f0(12);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f7088d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f7089e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f7090f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r f7091g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f7092h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f7093i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f7094j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final i f7095k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final p f7096l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final m f7097m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f7098n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final p f7099o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final m f7100p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f7101q;

    /* JADX WARN: Illegal instructions before constructor call */
    public q(String str, float[] fArr, s sVar, final double d5, float f5, float f6, int i5) {
        i iVar;
        i iVar2 = f7087r;
        if (d5 == 1.0d) {
            iVar = iVar2;
        } else {
            final int i6 = 0;
            iVar = new i() { // from class: z0.n
                @Override // z0.i
                public final double b(double d6) {
                    switch (i6) {
                        case 0:
                            if (d6 < 0.0d) {
                                d6 = 0.0d;
                            }
                            return Math.pow(d6, 1.0d / d5);
                        default:
                            if (d6 < 0.0d) {
                                d6 = 0.0d;
                            }
                            return Math.pow(d6, d5);
                    }
                }
            };
        }
        if (d5 != 1.0d) {
            final int i7 = 1;
            iVar2 = new i() { // from class: z0.n
                @Override // z0.i
                public final double b(double d6) {
                    switch (i7) {
                        case 0:
                            if (d6 < 0.0d) {
                                d6 = 0.0d;
                            }
                            return Math.pow(d6, 1.0d / d5);
                        default:
                            if (d6 < 0.0d) {
                                d6 = 0.0d;
                            }
                            return Math.pow(d6, d5);
                    }
                }
            };
        }
        this(str, fArr, sVar, null, iVar, iVar2, f5, f6, new r(d5, 1.0d, 0.0d, 0.0d, 0.0d), i5);
    }

    public q(String str, float[] fArr, s sVar, final r rVar, int i5) {
        i iVar;
        i iVar2;
        double d5 = rVar.f7107f;
        double d6 = rVar.f7108g;
        if (d5 == 0.0d && d6 == 0.0d) {
            final int i6 = 0;
            iVar = new i() { // from class: z0.o
                @Override // z0.i
                public final double b(double d7) {
                    switch (i6) {
                        case 0:
                            r rVar2 = rVar;
                            double d8 = rVar2.f7103b;
                            double d9 = rVar2.f7104c;
                            double d10 = rVar2.f7105d;
                            return d7 >= rVar2.f7106e * d10 ? (Math.pow(d7, 1.0d / rVar2.f7102a) - d9) / d8 : d7 / d10;
                        case 1:
                            r rVar3 = rVar;
                            double d11 = rVar3.f7103b;
                            double d12 = rVar3.f7104c;
                            double d13 = rVar3.f7105d;
                            return d7 >= rVar3.f7106e * d13 ? (Math.pow(d7 - rVar3.f7107f, 1.0d / rVar3.f7102a) - d12) / d11 : (d7 - rVar3.f7108g) / d13;
                        case 2:
                            r rVar4 = rVar;
                            double d14 = rVar4.f7103b;
                            return d7 >= rVar4.f7106e ? Math.pow((d14 * d7) + rVar4.f7104c, rVar4.f7102a) : d7 * rVar4.f7105d;
                        default:
                            r rVar5 = rVar;
                            double d15 = rVar5.f7103b;
                            double d16 = rVar5.f7104c;
                            double d17 = rVar5.f7105d;
                            return d7 >= rVar5.f7106e ? Math.pow((d7 * d15) + d16, rVar5.f7102a) + rVar5.f7107f : (d17 * d7) + rVar5.f7108g;
                    }
                }
            };
        } else {
            final int i7 = 1;
            iVar = new i() { // from class: z0.o
                @Override // z0.i
                public final double b(double d7) {
                    switch (i7) {
                        case 0:
                            r rVar2 = rVar;
                            double d8 = rVar2.f7103b;
                            double d9 = rVar2.f7104c;
                            double d10 = rVar2.f7105d;
                            return d7 >= rVar2.f7106e * d10 ? (Math.pow(d7, 1.0d / rVar2.f7102a) - d9) / d8 : d7 / d10;
                        case 1:
                            r rVar3 = rVar;
                            double d11 = rVar3.f7103b;
                            double d12 = rVar3.f7104c;
                            double d13 = rVar3.f7105d;
                            return d7 >= rVar3.f7106e * d13 ? (Math.pow(d7 - rVar3.f7107f, 1.0d / rVar3.f7102a) - d12) / d11 : (d7 - rVar3.f7108g) / d13;
                        case 2:
                            r rVar4 = rVar;
                            double d14 = rVar4.f7103b;
                            return d7 >= rVar4.f7106e ? Math.pow((d14 * d7) + rVar4.f7104c, rVar4.f7102a) : d7 * rVar4.f7105d;
                        default:
                            r rVar5 = rVar;
                            double d15 = rVar5.f7103b;
                            double d16 = rVar5.f7104c;
                            double d17 = rVar5.f7105d;
                            return d7 >= rVar5.f7106e ? Math.pow((d7 * d15) + d16, rVar5.f7102a) + rVar5.f7107f : (d17 * d7) + rVar5.f7108g;
                    }
                }
            };
        }
        if (d5 == 0.0d && d6 == 0.0d) {
            final int i8 = 2;
            iVar2 = new i() { // from class: z0.o
                @Override // z0.i
                public final double b(double d7) {
                    switch (i8) {
                        case 0:
                            r rVar2 = rVar;
                            double d8 = rVar2.f7103b;
                            double d9 = rVar2.f7104c;
                            double d10 = rVar2.f7105d;
                            return d7 >= rVar2.f7106e * d10 ? (Math.pow(d7, 1.0d / rVar2.f7102a) - d9) / d8 : d7 / d10;
                        case 1:
                            r rVar3 = rVar;
                            double d11 = rVar3.f7103b;
                            double d12 = rVar3.f7104c;
                            double d13 = rVar3.f7105d;
                            return d7 >= rVar3.f7106e * d13 ? (Math.pow(d7 - rVar3.f7107f, 1.0d / rVar3.f7102a) - d12) / d11 : (d7 - rVar3.f7108g) / d13;
                        case 2:
                            r rVar4 = rVar;
                            double d14 = rVar4.f7103b;
                            return d7 >= rVar4.f7106e ? Math.pow((d14 * d7) + rVar4.f7104c, rVar4.f7102a) : d7 * rVar4.f7105d;
                        default:
                            r rVar5 = rVar;
                            double d15 = rVar5.f7103b;
                            double d16 = rVar5.f7104c;
                            double d17 = rVar5.f7105d;
                            return d7 >= rVar5.f7106e ? Math.pow((d7 * d15) + d16, rVar5.f7102a) + rVar5.f7107f : (d17 * d7) + rVar5.f7108g;
                    }
                }
            };
        } else {
            final int i9 = 3;
            iVar2 = new i() { // from class: z0.o
                @Override // z0.i
                public final double b(double d7) {
                    switch (i9) {
                        case 0:
                            r rVar2 = rVar;
                            double d8 = rVar2.f7103b;
                            double d9 = rVar2.f7104c;
                            double d10 = rVar2.f7105d;
                            return d7 >= rVar2.f7106e * d10 ? (Math.pow(d7, 1.0d / rVar2.f7102a) - d9) / d8 : d7 / d10;
                        case 1:
                            r rVar3 = rVar;
                            double d11 = rVar3.f7103b;
                            double d12 = rVar3.f7104c;
                            double d13 = rVar3.f7105d;
                            return d7 >= rVar3.f7106e * d13 ? (Math.pow(d7 - rVar3.f7107f, 1.0d / rVar3.f7102a) - d12) / d11 : (d7 - rVar3.f7108g) / d13;
                        case 2:
                            r rVar4 = rVar;
                            double d14 = rVar4.f7103b;
                            return d7 >= rVar4.f7106e ? Math.pow((d14 * d7) + rVar4.f7104c, rVar4.f7102a) : d7 * rVar4.f7105d;
                        default:
                            r rVar5 = rVar;
                            double d15 = rVar5.f7103b;
                            double d16 = rVar5.f7104c;
                            double d17 = rVar5.f7105d;
                            return d7 >= rVar5.f7106e ? Math.pow((d7 * d15) + d16, rVar5.f7102a) + rVar5.f7107f : (d17 * d7) + rVar5.f7108g;
                    }
                }
            };
        }
        this(str, fArr, sVar, null, iVar, iVar2, 0.0f, 1.0f, rVar, i5);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0210  */
    /* JADX WARN: Code duplicated, block: B:45:0x0215  */
    /* JADX WARN: Code duplicated, block: B:47:0x0219  */
    /* JADX WARN: Code duplicated, block: B:53:0x0237  */
    /* JADX WARN: Code duplicated, block: B:56:0x0240  */
    /* JADX WARN: Code duplicated, block: B:63:0x0254  */
    /* JADX WARN: Code duplicated, block: B:65:0x026c  */
    /* JADX WARN: Code duplicated, block: B:68:0x0286 A[EDGE_INSN: B:68:0x0286->B:69:0x0288 BREAK  A[LOOP:1: B:61:0x024e->B:67:0x027f]] */
    /* JADX WARN: Code duplicated, block: B:76:0x0237 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0286 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public q(String str, float[] fArr, s sVar, float[] fArr2, i iVar, i iVar2, float f5, float f6, r rVar, int i5) {
        int i6;
        float f7;
        float f8;
        float[] fArr3;
        q qVar;
        double d5;
        int i7;
        super(str, b.f7032a, i5);
        this.f7088d = sVar;
        this.f7089e = f5;
        this.f7090f = f6;
        this.f7091g = rVar;
        this.f7095k = iVar;
        boolean z2 = 1;
        z2 = 1;
        this.f7096l = new p(this, z2 ? 1 : 0);
        int i8 = 0;
        this.f7097m = new m(this, i8);
        this.f7098n = iVar2;
        this.f7099o = new p(this, i8);
        this.f7100p = new m(this, z2 ? 1 : 0);
        if (fArr.length != 6 && fArr.length != 9) {
            throw new IllegalArgumentException("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
        }
        if (f5 >= f6) {
            throw new IllegalArgumentException("Invalid range: min=" + f5 + ", max=" + f6 + "; min must be strictly < max");
        }
        float[] fArr4 = new float[6];
        if (fArr.length == 9) {
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = f9 + f10 + fArr[2];
            fArr4[0] = f9 / f11;
            fArr4[1] = f10 / f11;
            float f12 = fArr[3];
            float f13 = fArr[4];
            float f14 = f12 + f13 + fArr[5];
            fArr4[2] = f12 / f14;
            fArr4[3] = f13 / f14;
            float f15 = fArr[6];
            float f16 = fArr[7];
            float f17 = f15 + f16 + fArr[8];
            fArr4[4] = f15 / f17;
            fArr4[5] = f16 / f17;
        } else {
            System.arraycopy(fArr, 0, fArr4, 0, 6);
        }
        this.f7092h = fArr4;
        if (fArr2 == null) {
            float f18 = fArr4[0];
            float f19 = fArr4[1];
            float f20 = fArr4[2];
            float f21 = fArr4[3];
            float f22 = fArr4[4];
            float f23 = fArr4[5];
            f7 = 1.0f;
            float f24 = sVar.f7109a;
            i6 = 0;
            float f25 = sVar.f7110b;
            float f26 = 1;
            float f27 = (f26 - f18) / f19;
            float f28 = (f26 - f20) / f21;
            float f29 = (f26 - f22) / f23;
            float f30 = (f26 - f24) / f25;
            float f31 = f18 / f19;
            float f32 = (f20 / f21) - f31;
            float f33 = (f24 / f25) - f31;
            float f34 = f28 - f27;
            float f35 = (f22 / f23) - f31;
            float f36 = (((f30 - f27) * f32) - (f33 * f34)) / (((f29 - f27) * f32) - (f34 * f35));
            float f37 = (f33 - (f35 * f36)) / f32;
            float f38 = (1.0f - f37) - f36;
            float f39 = f38 / f19;
            float f40 = f37 / f21;
            float f41 = f36 / f23;
            this.f7093i = new float[]{f39 * f18, f38, ((1.0f - f18) - f19) * f39, f40 * f20, f37, ((1.0f - f20) - f21) * f40, f41 * f22, f36, ((1.0f - f22) - f23) * f41};
        } else {
            i6 = 0;
            f7 = 1.0f;
            if (fArr2.length != 9) {
                throw new IllegalArgumentException("Transform must have 9 entries! Has " + fArr2.length);
            }
            this.f7093i = fArr2;
        }
        this.f7094j = j.g(this.f7093i);
        float fB = j.b(fArr4);
        float[] fArr5 = d.f7040a;
        if (fB / j.b(d.f7041b) > 0.9f) {
            float[] fArr6 = d.f7040a;
            float f42 = fArr4[i6];
            float f43 = fArr6[i6];
            float f44 = fArr4[1];
            float f45 = fArr6[1];
            float f46 = fArr4[2] - fArr6[2];
            float f47 = fArr4[3] - fArr6[3];
            float f48 = fArr4[4];
            float f49 = fArr6[4];
            float f50 = fArr4[5];
            float f51 = fArr6[5];
            f8 = 0.0f;
            float[] fArr7 = new float[6];
            fArr7[i6] = f42 - f43;
            fArr7[1] = f44 - f45;
            fArr7[2] = f46;
            fArr7[3] = f47;
            fArr7[4] = f48 - f49;
            fArr7[5] = f50 - f51;
            if (j.f(fArr7[i6], fArr7[1], f43 - f49, f45 - f51) < 0.0f || j.f(fArr6[i6] - fArr6[2], fArr6[1] - fArr6[3], fArr7[i6], fArr7[1]) < 0.0f || j.f(fArr7[2], fArr7[3], fArr6[2] - fArr6[i6], fArr6[3] - fArr6[1]) < 0.0f || j.f(fArr6[2] - fArr6[4], fArr6[3] - fArr6[5], fArr7[2], fArr7[3]) < 0.0f || j.f(fArr7[4], fArr7[5], fArr6[4] - fArr6[2], fArr6[5] - fArr6[3]) < 0.0f || j.f(fArr6[4] - fArr6[i6], fArr6[5] - fArr6[1], fArr7[4], fArr7[5]) < 0.0f) {
            }
            if (i5 != 0) {
                fArr3 = d.f7040a;
                if (fArr4 == fArr3) {
                    i7 = i6;
                    while (true) {
                        if (i7 >= 6) {
                            if (j.d(sVar, j.f7072d)) {
                                break;
                            }
                            float[] fArr8 = d.f7040a;
                            qVar = d.f7042c;
                            while (d5 <= 1.0d) {
                                if (Math.abs(iVar.b(d5) - qVar.f7095k.b(d5)) <= 0.001d) {
                                }
                            }
                        } else if (Float.compare(fArr4[i7], fArr3[i7]) != 0 || Math.abs(fArr4[i7] - fArr3[i7]) <= 0.001f) {
                            i7++;
                        }
                        z2 = i6;
                        break;
                    }
                }
                if (j.d(sVar, j.f7072d) || f5 != f8 || f6 != f7) {
                    z2 = i6;
                    break;
                }
                float[] fArr9 = d.f7040a;
                qVar = d.f7042c;
                for (d5 = 0.0d; d5 <= 1.0d; d5 += 0.00392156862745098d) {
                    if (Math.abs(iVar.b(d5) - qVar.f7095k.b(d5)) <= 0.001d || Math.abs(iVar2.b(d5) - qVar.f7098n.b(d5)) > 0.001d) {
                        z2 = i6;
                        break;
                    }
                }
            }
            this.f7101q = z2;
        }
        f8 = 0.0f;
        int i9 = (f5 > f8 ? 1 : (f5 == f8 ? 0 : -1));
        if (i5 != 0) {
            fArr3 = d.f7040a;
            if (fArr4 == fArr3) {
                i7 = i6;
                while (true) {
                    if (i7 >= 6) {
                        if (Float.compare(fArr4[i7], fArr3[i7]) != 0) {
                        }
                        i7++;
                    } else {
                        if (j.d(sVar, j.f7072d)) {
                            break;
                        }
                        float[] fArr10 = d.f7040a;
                        qVar = d.f7042c;
                        while (d5 <= 1.0d) {
                            if (Math.abs(iVar.b(d5) - qVar.f7095k.b(d5)) <= 0.001d) {
                            }
                        }
                    }
                    z2 = i6;
                    break;
                }
            }
            if (j.d(sVar, j.f7072d)) {
                z2 = i6;
                break;
            }
            float[] fArr11 = d.f7040a;
            qVar = d.f7042c;
            while (d5 <= 1.0d) {
                if (Math.abs(iVar.b(d5) - qVar.f7095k.b(d5)) <= 0.001d) {
                }
                z2 = i6;
            }
        }
        this.f7101q = z2;
    }

    @Override // z0.c
    public final float a(int i5) {
        return this.f7090f;
    }

    @Override // z0.c
    public final float b(int i5) {
        return this.f7089e;
    }

    @Override // z0.c
    public final boolean c() {
        return this.f7101q;
    }

    @Override // z0.c
    public final long d(float f5, float f6, float f7) {
        double d5 = f5;
        m mVar = this.f7100p;
        float fB = (float) mVar.b(d5);
        float fB2 = (float) mVar.b(f6);
        float fB3 = (float) mVar.b(f7);
        float[] fArr = this.f7093i;
        float f8 = (fArr[6] * fB3) + (fArr[3] * fB2) + (fArr[0] * fB);
        return (((long) Float.floatToRawIntBits((fArr[7] * fB3) + (fArr[4] * fB2) + (fArr[1] * fB))) & 4294967295L) | (Float.floatToRawIntBits(f8) << 32);
    }

    @Override // z0.c
    public final float e(float f5, float f6, float f7) {
        double d5 = f5;
        m mVar = this.f7100p;
        float fB = (float) mVar.b(d5);
        float fB2 = (float) mVar.b(f6);
        float fB3 = (float) mVar.b(f7);
        float[] fArr = this.f7093i;
        return (fArr[8] * fB3) + (fArr[5] * fB2) + (fArr[2] * fB);
    }

    @Override // z0.c
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        q qVar = (q) obj;
        r rVar = qVar.f7091g;
        if (Float.compare(qVar.f7089e, this.f7089e) != 0 || Float.compare(qVar.f7090f, this.f7090f) != 0 || !b4.l.a(this.f7088d, qVar.f7088d) || !Arrays.equals(this.f7092h, qVar.f7092h)) {
            return false;
        }
        r rVar2 = this.f7091g;
        if (rVar2 != null) {
            return b4.l.a(rVar2, rVar);
        }
        if (rVar == null) {
            return true;
        }
        if (b4.l.a(this.f7095k, qVar.f7095k)) {
            return b4.l.a(this.f7098n, qVar.f7098n);
        }
        return false;
    }

    @Override // z0.c
    public final long f(float f5, float f6, float f7, float f8, c cVar) {
        float[] fArr = this.f7094j;
        float f9 = (fArr[6] * f7) + (fArr[3] * f6) + (fArr[0] * f5);
        float f10 = (fArr[7] * f7) + (fArr[4] * f6) + (fArr[1] * f5);
        float f11 = (fArr[8] * f7) + (fArr[5] * f6) + (fArr[2] * f5);
        m mVar = this.f7097m;
        return l0.b((float) mVar.b(f9), (float) mVar.b(f10), (float) mVar.b(f11), f8, cVar);
    }

    @Override // z0.c
    public final int hashCode() {
        int iHashCode = (Arrays.hashCode(this.f7092h) + ((this.f7088d.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f5 = this.f7089e;
        int iFloatToIntBits = (iHashCode + (f5 == 0.0f ? 0 : Float.floatToIntBits(f5))) * 31;
        float f6 = this.f7090f;
        int iFloatToIntBits2 = (iFloatToIntBits + (f6 == 0.0f ? 0 : Float.floatToIntBits(f6))) * 31;
        r rVar = this.f7091g;
        int iHashCode2 = iFloatToIntBits2 + (rVar != null ? rVar.hashCode() : 0);
        if (rVar == null) {
            return this.f7098n.hashCode() + ((this.f7095k.hashCode() + (iHashCode2 * 31)) * 31);
        }
        return iHashCode2;
    }
}
