package y0;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r3.c f6642a = new r3.c(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f6643b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f6644c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f6645d;

    /* JADX WARN: Code duplicated, block: B:6:0x000d A[PHI: r0
      0x000d: PHI (r0v2 float) = (r0v1 float), (r0v0 float) binds: [B:11:0x001c, B:5:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x000f  */
    public static final int A(float f5, float[] fArr, int i5) {
        float f6 = 0.0f;
        if (f5 >= 0.0f) {
            f6 = 1.0f;
            if (f5 > 1.0f) {
                if (f5 <= 1.0000008f) {
                    f5 = f6;
                } else {
                    f5 = Float.NaN;
                }
            }
        } else if (f5 >= -8.34465E-7f) {
            f5 = f6;
        } else {
            f5 = Float.NaN;
        }
        fArr[i5] = f5;
        return !Float.isNaN(f5) ? 1 : 0;
    }

    public static final d a(g gVar) {
        Canvas canvas = e.f6621a;
        d dVar = new d();
        dVar.f6617a = new Canvas(k(gVar));
        return dVar;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0152  */
    /* JADX WARN: Code duplicated, block: B:106:0x0159  */
    /* JADX WARN: Code duplicated, block: B:109:0x0166 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:110:0x0168  */
    /* JADX WARN: Code duplicated, block: B:112:0x016d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0171  */
    /* JADX WARN: Code duplicated, block: B:115:0x0175 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:116:0x0177 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x0179  */
    /* JADX WARN: Code duplicated, block: B:119:0x0182  */
    /* JADX WARN: Code duplicated, block: B:121:0x0187  */
    /* JADX WARN: Code duplicated, block: B:122:0x0189  */
    /* JADX WARN: Code duplicated, block: B:124:0x018f  */
    /* JADX WARN: Code duplicated, block: B:126:0x0198  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:135:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:72:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:79:0x0105 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x0107  */
    /* JADX WARN: Code duplicated, block: B:81:0x010a  */
    /* JADX WARN: Code duplicated, block: B:83:0x010d  */
    /* JADX WARN: Code duplicated, block: B:85:0x0111  */
    /* JADX WARN: Code duplicated, block: B:86:0x0115 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x0117 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0119  */
    /* JADX WARN: Code duplicated, block: B:90:0x0122  */
    /* JADX WARN: Code duplicated, block: B:92:0x0128  */
    /* JADX WARN: Code duplicated, block: B:93:0x012b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0131  */
    /* JADX WARN: Code duplicated, block: B:97:0x013b  */
    public static final long b(float f5, float f6, float f7, float f8, z0.c cVar) {
        int i5;
        int i6;
        int i7;
        float fB;
        float fA;
        int iFloatToRawIntBits;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        float fB2;
        float fA2;
        int iFloatToRawIntBits2;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        float f9;
        if (cVar.c()) {
            float f10 = f8 < 0.0f ? 0.0f : f8;
            if (f10 > 1.0f) {
                f10 = 1.0f;
            }
            int i24 = ((int) ((f10 * 255.0f) + 0.5f)) << 24;
            float f11 = f5 < 0.0f ? 0.0f : f5;
            if (f11 > 1.0f) {
                f11 = 1.0f;
            }
            int i25 = i24 | (((int) ((f11 * 255.0f) + 0.5f)) << 16);
            float f12 = f6 < 0.0f ? 0.0f : f6;
            if (f12 > 1.0f) {
                f12 = 1.0f;
            }
            int i26 = i25 | (((int) ((f12 * 255.0f) + 0.5f)) << 8);
            f9 = f7 >= 0.0f ? f7 : 0.0f;
            long j5 = ((long) (i26 | ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 255.0f) + 0.5f)))) << 32;
            int i27 = u.f6695h;
            return j5;
        }
        long j6 = cVar.f7038b;
        int i28 = z0.b.f7036e;
        if (((int) (j6 >> 32)) != 3) {
            throw new IllegalArgumentException("Color only works with ColorSpaces with 3 components");
        }
        int i29 = cVar.f7039c;
        if (i29 == -1) {
            throw new IllegalArgumentException("Unknown color space, please use a color space in ColorSpaces");
        }
        int i30 = 0;
        float fB3 = cVar.b(0);
        float fA3 = cVar.a(0);
        if (f5 >= fB3) {
            fB3 = f5;
        }
        if (fB3 <= fA3) {
            fA3 = fB3;
        }
        int iFloatToRawIntBits3 = Float.floatToRawIntBits(fA3);
        int i31 = iFloatToRawIntBits3 >>> 31;
        int i32 = (iFloatToRawIntBits3 >>> 23) & 255;
        int i33 = iFloatToRawIntBits3 & 8388607;
        if (i32 == 255) {
            i6 = i33 != 0 ? 512 : 0;
            i5 = 31;
        } else {
            i5 = i32 - 112;
            if (i5 >= 31) {
                i6 = 0;
                i5 = 49;
            } else {
                if (i5 > 0) {
                    int i34 = i33 >> 13;
                    if ((iFloatToRawIntBits3 & 4096) != 0) {
                        i7 = (((i5 << 10) | i34) + 1) | (i31 << 15);
                    } else {
                        i6 = i34;
                    }
                    short s4 = (short) i7;
                    fB = cVar.b(1);
                    fA = cVar.a(1);
                    if (f6 >= fB) {
                        fB = f6;
                    }
                    if (fB <= fA) {
                        fA = fB;
                    }
                    iFloatToRawIntBits = Float.floatToRawIntBits(fA);
                    i8 = iFloatToRawIntBits >>> 31;
                    i9 = (iFloatToRawIntBits >>> 23) & 255;
                    i10 = iFloatToRawIntBits & 8388607;
                    if (i9 == 255) {
                        if (i10 != 0) {
                            i13 = 512;
                        } else {
                            i13 = 0;
                        }
                        i11 = 31;
                    } else {
                        i11 = i9 - 112;
                        if (i11 >= 31) {
                            i13 = 0;
                            i11 = 49;
                        } else {
                            if (i11 <= 0) {
                                i12 = i10 >> 13;
                                if ((iFloatToRawIntBits & 4096) != 0) {
                                    i14 = (((i11 << 10) | i12) + 1) | (i8 << 15);
                                } else {
                                    i13 = i12;
                                }
                                short s5 = (short) i14;
                                fB2 = cVar.b(2);
                                fA2 = cVar.a(2);
                                if (f7 >= fB2) {
                                    fB2 = f7;
                                }
                                if (fB2 <= fA2) {
                                    fA2 = fB2;
                                }
                                iFloatToRawIntBits2 = Float.floatToRawIntBits(fA2);
                                i16 = iFloatToRawIntBits2 >>> 31;
                                i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                                i18 = 8388607 & iFloatToRawIntBits2;
                                if (i17 == 255) {
                                    i21 = i18 != 0 ? 512 : 0;
                                    i30 = 31;
                                } else {
                                    i19 = i17 - 112;
                                    if (i19 >= 31) {
                                        i21 = 0;
                                        i30 = 49;
                                    } else {
                                        if (i19 <= 0) {
                                            i20 = i18 >> 13;
                                            if ((iFloatToRawIntBits2 & 4096) != 0) {
                                                i22 = (((i19 << 10) | i20) + 1) | (i16 << 15);
                                            } else {
                                                i21 = i20;
                                                i30 = i19;
                                            }
                                            short s6 = (short) i22;
                                            f9 = f8 >= 0.0f ? f8 : 0.0f;
                                            long j7 = (((long) i29) & 63) | ((((long) s4) & 65535) << 48) | ((((long) s5) & 65535) << 32) | ((65535 & ((long) s6)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                                            int i35 = u.f6695h;
                                            return j7;
                                        }
                                        if (i19 >= -10) {
                                            i23 = (i18 | 8388608) >> (1 - i19);
                                            if ((i23 & 4096) != 0) {
                                                i23 += 8192;
                                            }
                                            i21 = i23 >> 13;
                                        } else {
                                            i21 = 0;
                                        }
                                    }
                                }
                                i22 = i21 | (i16 << 15) | (i30 << 10);
                                short s7 = (short) i22;
                                if (f8 >= 0.0f) {
                                }
                                long j8 = (((long) i29) & 63) | ((((long) s4) & 65535) << 48) | ((((long) s5) & 65535) << 32) | ((65535 & ((long) s7)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                                int i36 = u.f6695h;
                                return j8;
                            }
                            if (i11 >= -10) {
                                i15 = (i10 | 8388608) >> (1 - i11);
                                if ((i15 & 4096) != 0) {
                                    i15 += 8192;
                                }
                                i13 = i15 >> 13;
                                i11 = 0;
                            } else {
                                i13 = 0;
                                i11 = 0;
                            }
                        }
                    }
                    i14 = i13 | (i8 << 15) | (i11 << 10);
                    short s8 = (short) i14;
                    fB2 = cVar.b(2);
                    fA2 = cVar.a(2);
                    if (f7 >= fB2) {
                        fB2 = f7;
                    }
                    if (fB2 <= fA2) {
                        fA2 = fB2;
                    }
                    iFloatToRawIntBits2 = Float.floatToRawIntBits(fA2);
                    i16 = iFloatToRawIntBits2 >>> 31;
                    i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                    i18 = 8388607 & iFloatToRawIntBits2;
                    if (i17 == 255) {
                        i21 = i18 != 0 ? 512 : 0;
                        i30 = 31;
                    } else {
                        i19 = i17 - 112;
                        if (i19 >= 31) {
                            i21 = 0;
                            i30 = 49;
                        } else {
                            if (i19 <= 0) {
                                i20 = i18 >> 13;
                                if ((iFloatToRawIntBits2 & 4096) != 0) {
                                    i22 = (((i19 << 10) | i20) + 1) | (i16 << 15);
                                } else {
                                    i21 = i20;
                                    i30 = i19;
                                }
                                short s9 = (short) i22;
                                if (f8 >= 0.0f) {
                                }
                                long j9 = (((long) i29) & 63) | ((((long) s4) & 65535) << 48) | ((((long) s8) & 65535) << 32) | ((65535 & ((long) s9)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                                int i37 = u.f6695h;
                                return j9;
                            }
                            if (i19 >= -10) {
                                i23 = (i18 | 8388608) >> (1 - i19);
                                if ((i23 & 4096) != 0) {
                                    i23 += 8192;
                                }
                                i21 = i23 >> 13;
                            } else {
                                i21 = 0;
                            }
                        }
                    }
                    i22 = i21 | (i16 << 15) | (i30 << 10);
                    short s10 = (short) i22;
                    if (f8 >= 0.0f) {
                    }
                    long j10 = (((long) i29) & 63) | ((((long) s4) & 65535) << 48) | ((((long) s8) & 65535) << 32) | ((65535 & ((long) s10)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                    int i38 = u.f6695h;
                    return j10;
                }
                if (i5 >= -10) {
                    int i39 = (i33 | 8388608) >> (1 - i5);
                    if ((i39 & 4096) != 0) {
                        i39 += 8192;
                    }
                    i6 = i39 >> 13;
                    i5 = 0;
                } else {
                    i6 = 0;
                    i5 = 0;
                }
            }
        }
        i7 = i6 | (i31 << 15) | (i5 << 10);
        short s11 = (short) i7;
        fB = cVar.b(1);
        fA = cVar.a(1);
        if (f6 >= fB) {
            fB = f6;
        }
        if (fB <= fA) {
            fA = fB;
        }
        iFloatToRawIntBits = Float.floatToRawIntBits(fA);
        i8 = iFloatToRawIntBits >>> 31;
        i9 = (iFloatToRawIntBits >>> 23) & 255;
        i10 = iFloatToRawIntBits & 8388607;
        if (i9 == 255) {
            if (i10 != 0) {
                i13 = 512;
            } else {
                i13 = 0;
            }
            i11 = 31;
        } else {
            i11 = i9 - 112;
            if (i11 >= 31) {
                i13 = 0;
                i11 = 49;
            } else {
                if (i11 <= 0) {
                    i12 = i10 >> 13;
                    if ((iFloatToRawIntBits & 4096) != 0) {
                        i14 = (((i11 << 10) | i12) + 1) | (i8 << 15);
                    } else {
                        i13 = i12;
                    }
                    short s12 = (short) i14;
                    fB2 = cVar.b(2);
                    fA2 = cVar.a(2);
                    if (f7 >= fB2) {
                        fB2 = f7;
                    }
                    if (fB2 <= fA2) {
                        fA2 = fB2;
                    }
                    iFloatToRawIntBits2 = Float.floatToRawIntBits(fA2);
                    i16 = iFloatToRawIntBits2 >>> 31;
                    i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                    i18 = 8388607 & iFloatToRawIntBits2;
                    if (i17 == 255) {
                        i21 = i18 != 0 ? 512 : 0;
                        i30 = 31;
                    } else {
                        i19 = i17 - 112;
                        if (i19 >= 31) {
                            i21 = 0;
                            i30 = 49;
                        } else {
                            if (i19 <= 0) {
                                i20 = i18 >> 13;
                                if ((iFloatToRawIntBits2 & 4096) != 0) {
                                    i22 = (((i19 << 10) | i20) + 1) | (i16 << 15);
                                } else {
                                    i21 = i20;
                                    i30 = i19;
                                }
                                short s13 = (short) i22;
                                if (f8 >= 0.0f) {
                                }
                                long j11 = (((long) i29) & 63) | ((((long) s11) & 65535) << 48) | ((((long) s12) & 65535) << 32) | ((65535 & ((long) s13)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                                int i310 = u.f6695h;
                                return j11;
                            }
                            if (i19 >= -10) {
                                i23 = (i18 | 8388608) >> (1 - i19);
                                if ((i23 & 4096) != 0) {
                                    i23 += 8192;
                                }
                                i21 = i23 >> 13;
                            } else {
                                i21 = 0;
                            }
                        }
                    }
                    i22 = i21 | (i16 << 15) | (i30 << 10);
                    short s14 = (short) i22;
                    if (f8 >= 0.0f) {
                    }
                    long j12 = (((long) i29) & 63) | ((((long) s11) & 65535) << 48) | ((((long) s12) & 65535) << 32) | ((65535 & ((long) s14)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                    int i311 = u.f6695h;
                    return j12;
                }
                if (i11 >= -10) {
                    i15 = (i10 | 8388608) >> (1 - i11);
                    if ((i15 & 4096) != 0) {
                        i15 += 8192;
                    }
                    i13 = i15 >> 13;
                    i11 = 0;
                } else {
                    i13 = 0;
                    i11 = 0;
                }
            }
        }
        i14 = i13 | (i8 << 15) | (i11 << 10);
        short s15 = (short) i14;
        fB2 = cVar.b(2);
        fA2 = cVar.a(2);
        if (f7 >= fB2) {
            fB2 = f7;
        }
        if (fB2 <= fA2) {
            fA2 = fB2;
        }
        iFloatToRawIntBits2 = Float.floatToRawIntBits(fA2);
        i16 = iFloatToRawIntBits2 >>> 31;
        i17 = (iFloatToRawIntBits2 >>> 23) & 255;
        i18 = 8388607 & iFloatToRawIntBits2;
        if (i17 == 255) {
            i21 = i18 != 0 ? 512 : 0;
            i30 = 31;
        } else {
            i19 = i17 - 112;
            if (i19 >= 31) {
                i21 = 0;
                i30 = 49;
            } else {
                if (i19 <= 0) {
                    i20 = i18 >> 13;
                    if ((iFloatToRawIntBits2 & 4096) != 0) {
                        i22 = (((i19 << 10) | i20) + 1) | (i16 << 15);
                    } else {
                        i21 = i20;
                        i30 = i19;
                    }
                    short s16 = (short) i22;
                    if (f8 >= 0.0f) {
                    }
                    long j13 = (((long) i29) & 63) | ((((long) s11) & 65535) << 48) | ((((long) s15) & 65535) << 32) | ((65535 & ((long) s16)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
                    int i312 = u.f6695h;
                    return j13;
                }
                if (i19 >= -10) {
                    i23 = (i18 | 8388608) >> (1 - i19);
                    if ((i23 & 4096) != 0) {
                        i23 += 8192;
                    }
                    i21 = i23 >> 13;
                } else {
                    i21 = 0;
                }
            }
        }
        i22 = i21 | (i16 << 15) | (i30 << 10);
        short s17 = (short) i22;
        if (f8 >= 0.0f) {
        }
        long j14 = (((long) i29) & 63) | ((((long) s11) & 65535) << 48) | ((((long) s15) & 65535) << 32) | ((65535 & ((long) s17)) << 16) | ((((long) ((int) (((f9 <= 1.0f ? f9 : 1.0f) * 1023.0f) + 0.5f))) & 1023) << 6);
        int i313 = u.f6695h;
        return j14;
    }

    public static final long c(int i5) {
        long j5 = ((long) i5) << 32;
        int i6 = u.f6695h;
        return j5;
    }

    public static final long d(long j5) {
        long j6 = j5 << 32;
        int i5 = u.f6695h;
        return j6;
    }

    public static long e(int i5, int i6, int i7) {
        return c(((i5 & 255) << 16) | (-16777216) | ((i6 & 255) << 8) | (i7 & 255));
    }

    public static g f(int i5, int i6, int i7) {
        z0.q qVar = z0.d.f7042c;
        x(i7);
        return new g(l.b(i5, i6, i7, true, qVar));
    }

    public static final h g() {
        return new h(new Paint(7));
    }

    public static final j h() {
        return new j(new Path());
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0093 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x0095  */
    /* JADX WARN: Code duplicated, block: B:32:0x0097  */
    /* JADX WARN: Code duplicated, block: B:34:0x009a  */
    /* JADX WARN: Code duplicated, block: B:36:0x009e  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x00a3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:46:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:48:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:52:0x00df A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:56:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:59:0x00eb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x00ed A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:65:0x0100  */
    /* JADX WARN: Code duplicated, block: B:66:0x0102  */
    /* JADX WARN: Code duplicated, block: B:68:0x0108  */
    /* JADX WARN: Code duplicated, block: B:70:0x0112  */
    public static final long i(float f5, float f6, float f7, float f8, z0.c cVar) {
        int i5;
        int i6;
        int i7;
        int iFloatToRawIntBits;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int iFloatToRawIntBits2;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        if (cVar.c()) {
            long j5 = ((long) ((((((int) ((f8 * 255.0f) + 0.5f)) << 24) | (((int) ((f5 * 255.0f) + 0.5f)) << 16)) | (((int) ((f6 * 255.0f) + 0.5f)) << 8)) | ((int) ((255.0f * f7) + 0.5f)))) << 32;
            int i22 = u.f6695h;
            return j5;
        }
        int iFloatToRawIntBits3 = Float.floatToRawIntBits(f5);
        int i23 = iFloatToRawIntBits3 >>> 31;
        int i24 = (iFloatToRawIntBits3 >>> 23) & 255;
        int i25 = iFloatToRawIntBits3 & 8388607;
        int i26 = 49;
        int i27 = 0;
        if (i24 == 255) {
            i6 = i25 != 0 ? 512 : 0;
            i5 = 31;
        } else {
            i5 = i24 - 112;
            if (i5 >= 31) {
                i5 = 49;
                i6 = 0;
            } else {
                if (i5 > 0) {
                    int i28 = i25 >> 13;
                    if ((iFloatToRawIntBits3 & 4096) != 0) {
                        i7 = (((i5 << 10) | i28) + 1) | (i23 << 15);
                    } else {
                        i6 = i28;
                    }
                    short s4 = (short) i7;
                    iFloatToRawIntBits = Float.floatToRawIntBits(f6);
                    i8 = iFloatToRawIntBits >>> 31;
                    i9 = (iFloatToRawIntBits >>> 23) & 255;
                    i10 = iFloatToRawIntBits & 8388607;
                    if (i9 == 255) {
                        if (i10 != 0) {
                            i13 = 512;
                        } else {
                            i13 = 0;
                        }
                        i11 = 31;
                    } else {
                        i11 = i9 - 112;
                        if (i11 >= 31) {
                            i11 = 49;
                            i13 = 0;
                        } else {
                            if (i11 <= 0) {
                                i12 = i10 >> 13;
                                if ((iFloatToRawIntBits & 4096) != 0) {
                                    i14 = (((i11 << 10) | i12) + 1) | (i8 << 15);
                                } else {
                                    i13 = i12;
                                }
                                short s5 = (short) i14;
                                iFloatToRawIntBits2 = Float.floatToRawIntBits(f7);
                                i16 = iFloatToRawIntBits2 >>> 31;
                                i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                                i18 = 8388607 & iFloatToRawIntBits2;
                                if (i17 == 255) {
                                    i19 = i17 - 112;
                                    if (i19 < 31) {
                                        if (i19 <= 0) {
                                            i27 = i18 >> 13;
                                            if ((iFloatToRawIntBits2 & 4096) != 0) {
                                                i20 = (((i19 << 10) | i27) + 1) | (i16 << 15);
                                            } else {
                                                i26 = i19;
                                            }
                                        } else if (i19 >= -10) {
                                            i21 = (i18 | 8388608) >> (1 - i19);
                                            if ((i21 & 4096) != 0) {
                                                i21 += 8192;
                                            }
                                            i26 = 0;
                                            i27 = i21 >> 13;
                                        } else {
                                            i26 = 0;
                                        }
                                    }
                                    long jMax = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s4) & 65535) << 48) | ((((long) s5) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                                    int i29 = u.f6695h;
                                    return jMax;
                                }
                                i27 = i18 == 0 ? 0 : 512;
                                i26 = 31;
                                i20 = (i16 << 15) | (i26 << 10) | i27;
                                long jMax2 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s4) & 65535) << 48) | ((((long) s5) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                                int i210 = u.f6695h;
                                return jMax2;
                            }
                            if (i11 >= -10) {
                                i15 = (i10 | 8388608) >> (1 - i11);
                                if ((i15 & 4096) != 0) {
                                    i15 += 8192;
                                }
                                i13 = i15 >> 13;
                                i11 = 0;
                            } else {
                                i13 = 0;
                                i11 = 0;
                            }
                        }
                    }
                    i14 = i13 | (i8 << 15) | (i11 << 10);
                    short s6 = (short) i14;
                    iFloatToRawIntBits2 = Float.floatToRawIntBits(f7);
                    i16 = iFloatToRawIntBits2 >>> 31;
                    i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                    i18 = 8388607 & iFloatToRawIntBits2;
                    if (i17 == 255) {
                        i19 = i17 - 112;
                        if (i19 < 31) {
                            if (i19 <= 0) {
                                i27 = i18 >> 13;
                                if ((iFloatToRawIntBits2 & 4096) != 0) {
                                    i20 = (((i19 << 10) | i27) + 1) | (i16 << 15);
                                } else {
                                    i26 = i19;
                                }
                            } else if (i19 >= -10) {
                                i21 = (i18 | 8388608) >> (1 - i19);
                                if ((i21 & 4096) != 0) {
                                    i21 += 8192;
                                }
                                i26 = 0;
                                i27 = i21 >> 13;
                            } else {
                                i26 = 0;
                            }
                        }
                        long jMax3 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s4) & 65535) << 48) | ((((long) s6) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                        int i211 = u.f6695h;
                        return jMax3;
                    }
                    i27 = i18 == 0 ? 0 : 512;
                    i26 = 31;
                    i20 = (i16 << 15) | (i26 << 10) | i27;
                    long jMax4 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s4) & 65535) << 48) | ((((long) s6) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                    int i212 = u.f6695h;
                    return jMax4;
                }
                if (i5 >= -10) {
                    int i30 = (i25 | 8388608) >> (1 - i5);
                    if ((i30 & 4096) != 0) {
                        i30 += 8192;
                    }
                    i6 = i30 >> 13;
                    i5 = 0;
                } else {
                    i6 = 0;
                    i5 = 0;
                }
            }
        }
        i7 = i6 | (i23 << 15) | (i5 << 10);
        short s7 = (short) i7;
        iFloatToRawIntBits = Float.floatToRawIntBits(f6);
        i8 = iFloatToRawIntBits >>> 31;
        i9 = (iFloatToRawIntBits >>> 23) & 255;
        i10 = iFloatToRawIntBits & 8388607;
        if (i9 == 255) {
            if (i10 != 0) {
                i13 = 512;
            } else {
                i13 = 0;
            }
            i11 = 31;
        } else {
            i11 = i9 - 112;
            if (i11 >= 31) {
                i11 = 49;
                i13 = 0;
            } else {
                if (i11 <= 0) {
                    i12 = i10 >> 13;
                    if ((iFloatToRawIntBits & 4096) != 0) {
                        i14 = (((i11 << 10) | i12) + 1) | (i8 << 15);
                    } else {
                        i13 = i12;
                    }
                    short s8 = (short) i14;
                    iFloatToRawIntBits2 = Float.floatToRawIntBits(f7);
                    i16 = iFloatToRawIntBits2 >>> 31;
                    i17 = (iFloatToRawIntBits2 >>> 23) & 255;
                    i18 = 8388607 & iFloatToRawIntBits2;
                    if (i17 == 255) {
                        i19 = i17 - 112;
                        if (i19 < 31) {
                            if (i19 <= 0) {
                                i27 = i18 >> 13;
                                if ((iFloatToRawIntBits2 & 4096) != 0) {
                                    i20 = (((i19 << 10) | i27) + 1) | (i16 << 15);
                                } else {
                                    i26 = i19;
                                }
                            } else if (i19 >= -10) {
                                i21 = (i18 | 8388608) >> (1 - i19);
                                if ((i21 & 4096) != 0) {
                                    i21 += 8192;
                                }
                                i26 = 0;
                                i27 = i21 >> 13;
                            } else {
                                i26 = 0;
                            }
                        }
                        long jMax5 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s7) & 65535) << 48) | ((((long) s8) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                        int i213 = u.f6695h;
                        return jMax5;
                    }
                    i27 = i18 == 0 ? 0 : 512;
                    i26 = 31;
                    i20 = (i16 << 15) | (i26 << 10) | i27;
                    long jMax6 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s7) & 65535) << 48) | ((((long) s8) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
                    int i214 = u.f6695h;
                    return jMax6;
                }
                if (i11 >= -10) {
                    i15 = (i10 | 8388608) >> (1 - i11);
                    if ((i15 & 4096) != 0) {
                        i15 += 8192;
                    }
                    i13 = i15 >> 13;
                    i11 = 0;
                } else {
                    i13 = 0;
                    i11 = 0;
                }
            }
        }
        i14 = i13 | (i8 << 15) | (i11 << 10);
        short s9 = (short) i14;
        iFloatToRawIntBits2 = Float.floatToRawIntBits(f7);
        i16 = iFloatToRawIntBits2 >>> 31;
        i17 = (iFloatToRawIntBits2 >>> 23) & 255;
        i18 = 8388607 & iFloatToRawIntBits2;
        if (i17 == 255) {
            i19 = i17 - 112;
            if (i19 < 31) {
                if (i19 <= 0) {
                    i27 = i18 >> 13;
                    if ((iFloatToRawIntBits2 & 4096) != 0) {
                        i20 = (((i19 << 10) | i27) + 1) | (i16 << 15);
                    } else {
                        i26 = i19;
                    }
                } else if (i19 >= -10) {
                    i21 = (i18 | 8388608) >> (1 - i19);
                    if ((i21 & 4096) != 0) {
                        i21 += 8192;
                    }
                    i26 = 0;
                    i27 = i21 >> 13;
                } else {
                    i26 = 0;
                }
            }
            long jMax7 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s7) & 65535) << 48) | ((((long) s9) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
            int i215 = u.f6695h;
            return jMax7;
        }
        i27 = i18 == 0 ? 0 : 512;
        i26 = 31;
        i20 = (i16 << 15) | (i26 << 10) | i27;
        long jMax8 = ((((long) ((short) i20)) & 65535) << 16) | ((((long) s7) & 65535) << 48) | ((((long) s9) & 65535) << 32) | ((((long) ((int) ((Math.max(0.0f, Math.min(f8, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) cVar.f7039c) & 63);
        int i216 = u.f6695h;
        return jMax8;
    }

    public static final float j(float[] fArr, int i5, float[] fArr2, int i6) {
        int i7 = i5 * 4;
        return (fArr[i7 + 3] * fArr2[12 + i6]) + (fArr[i7 + 2] * fArr2[8 + i6]) + (fArr[i7 + 1] * fArr2[4 + i6]) + (fArr[i7] * fArr2[i6]);
    }

    public static final Bitmap k(c0 c0Var) {
        if (c0Var instanceof g) {
            return ((g) c0Var).f6627a;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    public static final long l(long j5, long j6) {
        float f5;
        float f6;
        long jA = u.a(j5, u.f(j6));
        float fD = u.d(j6);
        float fD2 = u.d(jA);
        float f7 = 1.0f - fD2;
        float f8 = (fD * f7) + fD2;
        float fH = u.h(jA);
        float fH2 = u.h(j6);
        float f9 = 0.0f;
        if (f8 == 0.0f) {
            f5 = 0.0f;
        } else {
            f5 = (((fH2 * fD) * f7) + (fH * fD2)) / f8;
        }
        float fG = u.g(jA);
        float fG2 = u.g(j6);
        if (f8 == 0.0f) {
            f6 = 0.0f;
        } else {
            f6 = (((fG2 * fD) * f7) + (fG * fD2)) / f8;
        }
        float fE = u.e(jA);
        float fE2 = u.e(j6);
        if (f8 != 0.0f) {
            f9 = (((fE2 * fD) * f7) + (fE * fD2)) / f8;
        }
        return i(f5, f6, f9, f8, u.f(j6));
    }

    public static void m(a1.f fVar, j0 j0Var, long j5) {
        if (j0Var instanceof h0) {
            x0.d dVar = ((h0) j0Var).f6633a;
            fVar.L(j5, p3.j.b(dVar.f6534a, dVar.f6535b), p3.j.f(dVar.c(), dVar.b()), 3);
            return;
        }
        boolean z2 = j0Var instanceof i0;
        a1.i iVar = a1.i.f33a;
        if (!z2) {
            if (!(j0Var instanceof g0)) {
                throw new i4.p();
            }
            fVar.t(((g0) j0Var).f6628a, j5, iVar);
            return;
        }
        i0 i0Var = (i0) j0Var;
        j jVar = i0Var.f6637b;
        if (jVar != null) {
            fVar.t(jVar, j5, iVar);
            return;
        }
        x0.e eVar = i0Var.f6636a;
        float fB = x0.a.b(eVar.f6545h);
        fVar.q(j5, p3.j.b(eVar.f6538a, eVar.f6539b), p3.j.f(eVar.b(), eVar.a()), p3.p.a(fB, fB));
    }

    public static void n(Canvas canvas, boolean z2) {
        Method method;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 29) {
            t.f6685a.a(canvas, z2);
            return;
        }
        if (!f6645d) {
            try {
                if (i5 == 28) {
                    Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass());
                    f6643b = (Method) declaredMethod.invoke(Canvas.class, "insertReorderBarrier", new Class[0]);
                    f6644c = (Method) declaredMethod.invoke(Canvas.class, "insertInorderBarrier", new Class[0]);
                } else {
                    f6643b = Canvas.class.getDeclaredMethod("insertReorderBarrier", null);
                    f6644c = Canvas.class.getDeclaredMethod("insertInorderBarrier", null);
                }
                Method method2 = f6643b;
                if (method2 != null) {
                    method2.setAccessible(true);
                }
                Method method3 = f6644c;
                if (method3 != null) {
                    method3.setAccessible(true);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            f6645d = true;
        }
        if (z2) {
            try {
                Method method4 = f6643b;
                if (method4 != null) {
                    method4.invoke(canvas, null);
                }
            } catch (IllegalAccessException | InvocationTargetException unused2) {
                return;
            }
        }
        if (z2 || (method = f6644c) == null) {
            return;
        }
        method.invoke(canvas, null);
    }

    public static final long o(float f5, long j5, long j6) {
        z0.l lVar = z0.d.f7059t;
        long jA = u.a(j5, lVar);
        long jA2 = u.a(j6, lVar);
        float fD = u.d(jA);
        float fH = u.h(jA);
        float fG = u.g(jA);
        float fE = u.e(jA);
        float fD2 = u.d(jA2);
        float fH2 = u.h(jA2);
        float fG2 = u.g(jA2);
        float fE2 = u.e(jA2);
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        return u.a(i(a.a.D(fH, fH2, f5), a.a.D(fG, fG2, f5), a.a.D(fE, fE2, f5), a.a.D(fD, fD2, f5), lVar), u.f(j6));
    }

    public static final float p(long j5) {
        z0.c cVarF = u.f(j5);
        if (!z0.b.a(cVarF.f7038b, z0.b.f7032a)) {
            throw new IllegalArgumentException("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) z0.b.b(cVarF.f7038b)));
        }
        z0.m mVar = ((z0.q) cVarF).f7100p;
        double dB = mVar.b(u.h(j5));
        float fB = (float) ((mVar.b(u.e(j5)) * 0.0722d) + (mVar.b(u.g(j5)) * 0.7152d) + (dB * 0.2126d));
        if (fB < 0.0f) {
            fB = 0.0f;
        }
        if (fB > 1.0f) {
            return 1.0f;
        }
        return fB;
    }

    public static final void q(Matrix matrix, float[] fArr) {
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = fArr[2];
        float f8 = fArr[3];
        float f9 = fArr[4];
        float f10 = fArr[5];
        float f11 = fArr[6];
        float f12 = fArr[7];
        float f13 = fArr[8];
        float f14 = fArr[12];
        float f15 = fArr[13];
        float f16 = fArr[15];
        fArr[0] = f5;
        fArr[1] = f9;
        fArr[2] = f14;
        fArr[3] = f6;
        fArr[4] = f10;
        fArr[5] = f15;
        fArr[6] = f8;
        fArr[7] = f12;
        fArr[8] = f16;
        matrix.setValues(fArr);
        fArr[0] = f5;
        fArr[1] = f6;
        fArr[2] = f7;
        fArr[3] = f8;
        fArr[4] = f9;
        fArr[5] = f10;
        fArr[6] = f11;
        fArr[7] = f12;
        fArr[8] = f13;
    }

    public static final void r(Matrix matrix, float[] fArr) {
        matrix.getValues(fArr);
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = fArr[2];
        float f8 = fArr[3];
        float f9 = fArr[4];
        float f10 = fArr[5];
        float f11 = fArr[6];
        float f12 = fArr[7];
        float f13 = fArr[8];
        fArr[0] = f5;
        fArr[1] = f8;
        fArr[2] = 0.0f;
        fArr[3] = f11;
        fArr[4] = f6;
        fArr[5] = f9;
        fArr[6] = 0.0f;
        fArr[7] = f12;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = f7;
        fArr[13] = f10;
        fArr[14] = 0.0f;
        fArr[15] = f13;
    }

    public static final BlendMode s(int i5) {
        if (i5 == 0) {
            return BlendMode.CLEAR;
        }
        if (i5 == 1) {
            return BlendMode.SRC;
        }
        if (i5 == 2) {
            return BlendMode.DST;
        }
        if (i5 == 3) {
            return BlendMode.SRC_OVER;
        }
        if (i5 == 4) {
            return BlendMode.DST_OVER;
        }
        if (i5 == 5) {
            return BlendMode.SRC_IN;
        }
        if (i5 == 6) {
            return BlendMode.DST_IN;
        }
        if (i5 == 7) {
            return BlendMode.SRC_OUT;
        }
        if (i5 == 8) {
            return BlendMode.DST_OUT;
        }
        if (i5 == 9) {
            return BlendMode.SRC_ATOP;
        }
        if (i5 == 10) {
            return BlendMode.DST_ATOP;
        }
        if (i5 == 11) {
            return BlendMode.XOR;
        }
        if (i5 == 12) {
            return BlendMode.PLUS;
        }
        if (i5 == 13) {
            return BlendMode.MODULATE;
        }
        if (i5 == 14) {
            return BlendMode.SCREEN;
        }
        if (i5 == 15) {
            return BlendMode.OVERLAY;
        }
        if (i5 == 16) {
            return BlendMode.DARKEN;
        }
        if (i5 == 17) {
            return BlendMode.LIGHTEN;
        }
        if (i5 == 18) {
            return BlendMode.COLOR_DODGE;
        }
        if (i5 == 19) {
            return BlendMode.COLOR_BURN;
        }
        if (i5 == 20) {
            return BlendMode.HARD_LIGHT;
        }
        if (i5 == 21) {
            return BlendMode.SOFT_LIGHT;
        }
        if (i5 == 22) {
            return BlendMode.DIFFERENCE;
        }
        if (i5 == 23) {
            return BlendMode.EXCLUSION;
        }
        if (i5 == 24) {
            return BlendMode.MULTIPLY;
        }
        if (i5 == 25) {
            return BlendMode.HUE;
        }
        if (i5 == 26) {
            return BlendMode.SATURATION;
        }
        if (i5 == 27) {
            return BlendMode.COLOR;
        }
        return i5 == 28 ? BlendMode.LUMINOSITY : BlendMode.SRC_OVER;
    }

    public static final Rect t(k2.i iVar) {
        return new Rect(iVar.f3411a, iVar.f3412b, iVar.f3413c, iVar.f3414d);
    }

    public static final Rect u(x0.d dVar) {
        return new Rect((int) dVar.f6534a, (int) dVar.f6535b, (int) dVar.f6536c, (int) dVar.f6537d);
    }

    public static final RectF v(x0.d dVar) {
        return new RectF(dVar.f6534a, dVar.f6535b, dVar.f6536c, dVar.f6537d);
    }

    public static final int w(long j5) {
        float[] fArr = z0.d.f7040a;
        return (int) (u.a(j5, z0.d.f7042c) >>> 32);
    }

    public static final Bitmap.Config x(int i5) {
        if (i5 == 0) {
            return Bitmap.Config.ARGB_8888;
        }
        if (i5 == 1) {
            return Bitmap.Config.ALPHA_8;
        }
        if (i5 == 2) {
            return Bitmap.Config.RGB_565;
        }
        if (i5 == 3) {
            return Bitmap.Config.RGBA_F16;
        }
        return i5 == 4 ? Bitmap.Config.HARDWARE : Bitmap.Config.ARGB_8888;
    }

    public static final x0.d y(RectF rectF) {
        return new x0.d(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public static final PorterDuff.Mode z(int i5) {
        if (i5 == 0) {
            return PorterDuff.Mode.CLEAR;
        }
        if (i5 == 1) {
            return PorterDuff.Mode.SRC;
        }
        if (i5 == 2) {
            return PorterDuff.Mode.DST;
        }
        if (i5 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i5 == 4) {
            return PorterDuff.Mode.DST_OVER;
        }
        if (i5 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i5 == 6) {
            return PorterDuff.Mode.DST_IN;
        }
        if (i5 == 7) {
            return PorterDuff.Mode.SRC_OUT;
        }
        if (i5 == 8) {
            return PorterDuff.Mode.DST_OUT;
        }
        if (i5 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        if (i5 == 10) {
            return PorterDuff.Mode.DST_ATOP;
        }
        if (i5 == 11) {
            return PorterDuff.Mode.XOR;
        }
        if (i5 == 12) {
            return PorterDuff.Mode.ADD;
        }
        if (i5 == 14) {
            return PorterDuff.Mode.SCREEN;
        }
        if (i5 == 15) {
            return PorterDuff.Mode.OVERLAY;
        }
        if (i5 == 16) {
            return PorterDuff.Mode.DARKEN;
        }
        if (i5 == 17) {
            return PorterDuff.Mode.LIGHTEN;
        }
        return i5 == 13 ? PorterDuff.Mode.MULTIPLY : PorterDuff.Mode.SRC_OVER;
    }
}
