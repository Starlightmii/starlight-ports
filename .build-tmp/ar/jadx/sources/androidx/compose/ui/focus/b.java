package androidx.compose.ui.focus;

import android.view.KeyEvent;
import b.h0;
import b4.l;
import b4.w;
import d0.n;
import h1.c;
import i4.v0;
import j.f0;
import j.v;
import java.util.ArrayList;
import m.h;
import p1.d0;
import p1.m;
import p1.q0;
import p1.t0;
import q1.o;
import r0.p;
import w0.d;
import w0.e;
import w0.f;
import w0.g;
import w0.j;
import w0.k;
import w0.r;
import w0.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v0 f175b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h0 f176c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h0 f177d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f178e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f180g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public v f183j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r f179f = new r();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final s f181h = new s();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f182i = new FocusPropertiesElement(new k()).e(new q0() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$2
        public final boolean equals(Object obj) {
            return obj == this;
        }

        @Override // p1.q0
        public final r0.o h() {
            return this.f171a.f179f;
        }

        public final int hashCode() {
            return this.f171a.f179f.hashCode();
        }

        @Override // p1.q0
        public final /* bridge */ /* synthetic */ void i(r0.o oVar) {
        }
    });

    public b(v0 v0Var, o oVar, v0 v0Var2, h0 h0Var, h0 h0Var2, n nVar) {
        this.f174a = oVar;
        this.f175b = v0Var2;
        this.f176c = h0Var;
        this.f177d = h0Var2;
        this.f178e = nVar;
        this.f180g = new e(v0Var, new h0(0, this, b.class, "invalidateOwnerFocusState", "invalidateOwnerFocusState()V", 0, 0, 6));
    }

    public final boolean a(int i5, boolean z2, boolean z3) {
        int iC;
        s sVar = this.f181h;
        f fVar = f.f6234g;
        try {
            if (sVar.f6266b) {
                s.a(sVar);
            }
            sVar.f6266b = true;
            ((h0.e) sVar.f6268d).b(fVar);
            r rVar = this.f179f;
            boolean zE = (z2 || !((iC = h.c(d.u(rVar))) == 1 || iC == 2 || iC == 3)) ? d.e(rVar, z2) : false;
            s.b(sVar);
            if (zE && z3) {
                this.f176c.a();
            }
            return zE;
        } catch (Throwable th) {
            s.b(sVar);
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x03bf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:111:0x03c1  */
    /* JADX WARN: Code duplicated, block: B:113:0x03c7  */
    /* JADX WARN: Code duplicated, block: B:115:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:117:0x03d7 A[ADDED_TO_REGION, LOOP:14: B:117:0x03d7->B:146:0x0426, LOOP_START, PHI: r7
      0x03d7: PHI (r7v28 r0.o) = (r7v23 r0.o), (r7v29 r0.o) binds: [B:116:0x03d5, B:146:0x0426] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:118:0x03d9  */
    /* JADX WARN: Code duplicated, block: B:120:0x03df  */
    /* JADX WARN: Code duplicated, block: B:122:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:125:0x03e8  */
    /* JADX WARN: Code duplicated, block: B:156:0x043d  */
    /* JADX WARN: Code duplicated, block: B:157:0x0443  */
    /* JADX WARN: Code duplicated, block: B:159:0x0449  */
    /* JADX WARN: Code duplicated, block: B:161:0x044f  */
    /* JADX WARN: Code duplicated, block: B:163:0x0457  */
    /* JADX WARN: Code duplicated, block: B:165:0x0461 A[ADDED_TO_REGION, LOOP:18: B:165:0x0461->B:194:0x04b0, LOOP_START, PHI: r3
      0x0461: PHI (r3v11 r0.o) = (r3v6 r0.o), (r3v12 r0.o) binds: [B:164:0x045f, B:194:0x04b0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:166:0x0463  */
    /* JADX WARN: Code duplicated, block: B:168:0x0469  */
    /* JADX WARN: Code duplicated, block: B:170:0x046d  */
    /* JADX WARN: Code duplicated, block: B:173:0x0472  */
    /* JADX WARN: Code duplicated, block: B:175:0x0478  */
    /* JADX WARN: Code duplicated, block: B:197:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:204:0x04c7  */
    /* JADX WARN: Code duplicated, block: B:205:0x04cc  */
    /* JADX WARN: Code duplicated, block: B:330:0x0644  */
    /* JADX WARN: Code duplicated, block: B:394:0x0438 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:395:0x0439 A[EDGE_INSN: B:395:0x0439->B:154:0x0439 BREAK  A[LOOP:13: B:114:0x03cb->B:397:0x03cb, LOOP_LABEL: LOOP:13: B:114:0x03cb->B:397:0x03cb], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:401:0x0426 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:413:0x04c0 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:415:0x04c2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:416:0x04c3 A[EDGE_INSN: B:416:0x04c3->B:202:0x04c3 BREAK  A[LOOP:17: B:162:0x0455->B:418:0x0455, LOOP_LABEL: LOOP:17: B:162:0x0455->B:418:0x0455], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:422:0x04b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:424:0x04ab A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r2v14, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v15, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v19, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v20, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v35, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v43 */
    /* JADX WARN: Type inference failed for: r7v81 */
    /* JADX WARN: Type inference failed for: r7v82 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r8v33, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v36 */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r8v91 */
    /* JADX WARN: Type inference failed for: r8v92 */
    /* JADX WARN: Type inference failed for: r8v93 */
    /* JADX WARN: Type inference failed for: r8v94 */
    /* JADX WARN: Type inference failed for: r8v95 */
    /* JADX WARN: Type inference failed for: r8v96 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r9v65 */
    /* JADX WARN: Type inference failed for: r9v66 */
    /* JADX WARN: Type inference failed for: r9v67 */
    /* JADX WARN: Type inference failed for: r9v68 */
    public final boolean b(KeyEvent keyEvent, a4.a aVar) {
        int iNumberOfTrailingZeros;
        r0.o oVar;
        r0.o oVar2;
        d0 d0VarS;
        ?? E;
        Object obj;
        r0.o oVar3;
        t0 t0Var;
        ?? eVar;
        r0.o oVar4;
        d0 d0VarS2;
        ?? E2;
        Object obj2;
        t0 t0Var2;
        ?? eVar2;
        int size;
        t0 t0Var3;
        long j5;
        long j6;
        int iNumberOfTrailingZeros2;
        int i5;
        long[] jArr;
        long[] jArr2;
        if (this.f180g.a()) {
            throw new IllegalStateException("Dispatching key event while focus system is invalidated.");
        }
        long jU = c.u(keyEvent);
        int iY = c.y(keyEvent);
        int i6 = 1;
        int i7 = -862048943;
        long j7 = 255;
        if (iY != 2) {
            if (iY == 1) {
                v vVar = this.f183j;
                if (vVar != null && vVar.a(jU)) {
                    v vVar2 = this.f183j;
                    if (vVar2 != null) {
                        int iHashCode = Long.hashCode(jU) * (-862048943);
                        int i8 = iHashCode ^ (iHashCode << 16);
                        int i9 = i8 & 127;
                        int i10 = vVar2.f3159c;
                        int i11 = (i8 >>> 7) & i10;
                        int i12 = 0;
                        loop24: while (true) {
                            long[] jArr3 = vVar2.f3157a;
                            int i13 = i11 >> 3;
                            int i14 = (i11 & 7) << 3;
                            long j8 = ((jArr3[i13 + 1] << (64 - i14)) & ((-i14) >> 63)) | (jArr3[i13] >>> i14);
                            long j9 = (((long) i9) * 72340172838076673L) ^ j8;
                            for (long j10 = (~j9) & (j9 - 72340172838076673L) & (-9187201950435737472L); j10 != 0; j10 &= j10 - 1) {
                                iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j10) >> 3) + i11) & i10;
                                if (vVar2.f3158b[iNumberOfTrailingZeros] == jU) {
                                    break loop24;
                                }
                            }
                            if ((j8 & ((~j8) << 6) & (-9187201950435737472L)) != 0) {
                                iNumberOfTrailingZeros = -1;
                                break;
                            }
                            i12 += 8;
                            i11 = (i11 + i12) & i10;
                        }
                        if (iNumberOfTrailingZeros >= 0) {
                            vVar2.f3160d--;
                            long[] jArr4 = vVar2.f3157a;
                            int i15 = vVar2.f3159c;
                            int i16 = iNumberOfTrailingZeros >> 3;
                            int i17 = (iNumberOfTrailingZeros & 7) << 3;
                            long j11 = (jArr4[i16] & (~(255 << i17))) | (254 << i17);
                            jArr4[i16] = j11;
                            jArr4[(((iNumberOfTrailingZeros - 7) & i15) + (i15 & 7)) >> 3] = j11;
                        }
                    }
                }
            }
            return false;
        }
        v vVar3 = this.f183j;
        if (vVar3 == null) {
            vVar3 = new v(3);
            this.f183j = vVar3;
        }
        v vVar4 = vVar3;
        int iHashCode2 = Long.hashCode(jU) * (-862048943);
        int i18 = iHashCode2 ^ (iHashCode2 << 16);
        int i19 = i18 >>> 7;
        int i20 = i18 & 127;
        int i21 = vVar4.f3159c;
        int i22 = i19 & i21;
        int i23 = 0;
        loop0: while (true) {
            long[] jArr5 = vVar4.f3157a;
            int i24 = i22 >> 3;
            int i25 = (i22 & 7) << 3;
            int i26 = i7;
            long j12 = (jArr5[i24] >>> i25) | ((jArr5[i24 + i6] << (64 - i25)) & ((-i25) >> 63));
            long j13 = i20;
            int i27 = i6;
            int i28 = i21;
            long j14 = j12 ^ (j13 * 72340172838076673L);
            long j15 = (j14 - 72340172838076673L) & (~j14) & (-9187201950435737472L);
            while (j15 != 0) {
                iNumberOfTrailingZeros2 = (i22 + (Long.numberOfTrailingZeros(j15) >> 3)) & i28;
                int i29 = i27;
                if (vVar4.f3158b[iNumberOfTrailingZeros2] == jU) {
                    break loop0;
                }
                j15 &= j15 - 1;
                i27 = i29;
            }
            int i30 = i27;
            if ((((~j12) << 6) & j12 & (-9187201950435737472L)) != 0) {
                int iB = vVar4.b(i19);
                if (vVar4.f3161e != 0 || ((vVar4.f3157a[iB >> 3] >> ((iB & 7) << 3)) & 255) == 254) {
                    j5 = 255;
                    j6 = 128;
                } else {
                    int i31 = vVar4.f3159c;
                    if (i31 > 8) {
                        j6 = 128;
                        char c5 = '\b';
                        int i32 = i19;
                        if (Long.compareUnsigned(((long) vVar4.f3160d) * 32, ((long) i31) * 25) <= 0) {
                            long[] jArr6 = vVar4.f3157a;
                            int i33 = vVar4.f3159c;
                            long[] jArr7 = vVar4.f3158b;
                            int i34 = (i33 + 7) >> 3;
                            int i35 = 0;
                            while (i35 < i34) {
                                char c6 = c5;
                                long j16 = jArr6[i35] & (-9187201950435737472L);
                                int i36 = i35;
                                jArr6[i36] = (-72340172838076674L) & ((~j16) + (j16 >>> 7));
                                i35 = i36 + 1;
                                i34 = i34;
                                i32 = i32;
                                c5 = c6;
                            }
                            i5 = i32;
                            int iG = q3.k.G(jArr6);
                            int i37 = iG - 1;
                            jArr6[i37] = (jArr6[i37] & 72057594037927935L) | (-72057594037927936L);
                            jArr6[iG] = jArr6[0];
                            int i38 = 0;
                            while (i38 != i33) {
                                int i39 = i38 >> 3;
                                int i40 = (i38 & 7) << 3;
                                long j17 = (jArr6[i39] >> i40) & j7;
                                if (j17 != 128 && j17 == 254) {
                                    int iHashCode3 = Long.hashCode(jArr7[i38]) * i26;
                                    int i41 = iHashCode3 ^ (iHashCode3 << 16);
                                    int i42 = i41 >>> 7;
                                    int iB2 = vVar4.b(i42);
                                    int i43 = i42 & i33;
                                    long j18 = j7;
                                    if (((iB2 - i43) & i33) / 8 == ((i38 - i43) & i33) / 8) {
                                        jArr2 = jArr7;
                                        jArr6[i39] = ((~(j18 << i40)) & jArr6[i39]) | (((long) (i41 & 127)) << i40);
                                        jArr6[jArr6.length - 1] = (jArr6[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    } else {
                                        jArr2 = jArr7;
                                        int i44 = iB2 >> 3;
                                        long j19 = jArr6[i44];
                                        int i45 = (iB2 & 7) << 3;
                                        if (((j19 >> i45) & j18) == 128) {
                                            jArr6[i44] = ((~(j18 << i45)) & j19) | (((long) (i41 & 127)) << i45);
                                            jArr6[i39] = (jArr6[i39] & (~(j18 << i40))) | (128 << i40);
                                            jArr2[iB2] = jArr2[i38];
                                            jArr2[i38] = 0;
                                        } else {
                                            jArr6[i44] = (((long) (i41 & 127)) << i45) | ((~(j18 << i45)) & j19);
                                            long j20 = jArr2[iB2];
                                            jArr2[iB2] = jArr2[i38];
                                            jArr2[i38] = j20;
                                            i38--;
                                        }
                                        jArr6[jArr6.length - 1] = (jArr6[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    }
                                    i38++;
                                    i33 = i33;
                                    jArr7 = jArr2;
                                    j7 = j18;
                                } else {
                                    i38++;
                                }
                            }
                            j5 = j7;
                            vVar4.f3161e = f0.a(vVar4.f3159c) - vVar4.f3160d;
                        } else {
                            i5 = i32;
                        }
                        iB = vVar4.b(i5);
                    } else {
                        i5 = i19;
                        j6 = 128;
                    }
                    j5 = 255;
                    int iB3 = f0.b(vVar4.f3159c);
                    long[] jArr8 = vVar4.f3157a;
                    long[] jArr9 = vVar4.f3158b;
                    int i46 = vVar4.f3159c;
                    vVar4.c(iB3);
                    long[] jArr10 = vVar4.f3157a;
                    long[] jArr11 = vVar4.f3158b;
                    int i47 = vVar4.f3159c;
                    int i48 = 0;
                    while (i48 < i46) {
                        if (((jArr8[i48 >> 3] >> ((i48 & 7) << 3)) & 255) < j6) {
                            long j21 = jArr9[i48];
                            int iHashCode4 = Long.hashCode(j21) * i26;
                            int i49 = iHashCode4 ^ (iHashCode4 << 16);
                            jArr = jArr10;
                            int iB4 = vVar4.b(i49 >>> 7);
                            int i50 = iB4 >> 3;
                            int i51 = (iB4 & 7) << 3;
                            long j22 = (jArr[i50] & (~(255 << i51))) | (((long) (i49 & 127)) << i51);
                            jArr[i50] = j22;
                            jArr[(((iB4 - 7) & i47) + (i47 & 7)) >> 3] = j22;
                            jArr11[iB4] = j21;
                        } else {
                            jArr = jArr10;
                        }
                        i48++;
                        jArr8 = jArr8;
                        jArr10 = jArr;
                        jArr9 = jArr9;
                    }
                    iB = vVar4.b(i5);
                }
                iNumberOfTrailingZeros2 = iB;
                vVar4.f3160d++;
                int i52 = vVar4.f3161e;
                long[] jArr12 = vVar4.f3157a;
                int i53 = iNumberOfTrailingZeros2 >> 3;
                long j23 = jArr12[i53];
                int i54 = (iNumberOfTrailingZeros2 & 7) << 3;
                vVar4.f3161e = i52 - (((j23 >> i54) & j5) == j6 ? i30 : 0);
                int i55 = vVar4.f3159c;
                long j24 = (j23 & (~(j5 << i54))) | (j13 << i54);
                jArr12[i53] = j24;
                jArr12[(((iNumberOfTrailingZeros2 - 7) & i55) + (i55 & 7)) >> 3] = j24;
                break;
            }
            i23 += 8;
            i22 = (i22 + i23) & i28;
            i6 = i30;
            i7 = i26;
            i21 = i28;
        }
        vVar4.f3158b[iNumberOfTrailingZeros2] = jU;
        r rVar = this.f179f;
        r rVarG = d.g(rVar);
        if (rVarG != null) {
            r0.o oVar5 = rVarG.f5681d;
            if (!oVar5.f5693p) {
                d4.a.L("visitLocalDescendants called on an unattached node");
                throw null;
            }
            if ((oVar5.f5684g & 9216) != 0) {
                oVar3 = null;
                for (r0.o oVar6 = oVar5.f5686i; oVar6 != null; oVar6 = oVar6.f5686i) {
                    int i56 = oVar6.f5683f;
                    if ((i56 & 9216) != 0) {
                        if ((i56 & 1024) != 0) {
                            break;
                        }
                        oVar3 = oVar6;
                    }
                }
            } else {
                oVar3 = null;
            }
            if (oVar3 == null) {
                if (rVarG != null) {
                    oVar4 = rVarG.f5681d;
                    if (oVar4.f5693p) {
                        throw new IllegalStateException("visitAncestors called on an unattached node");
                    }
                    d0VarS2 = p1.f.s(rVarG);
                    loop13: while (true) {
                        if (d0VarS2 != null) {
                            E2 = 0;
                            break;
                        }
                        if ((d0VarS2.f4801y.f4972e.f5684g & 8192) != 0) {
                            while (oVar4 != null) {
                                if ((oVar4.f5683f & 8192) != 0) {
                                    eVar2 = 0;
                                    E2 = oVar4;
                                    while (E2 != 0) {
                                        if (E2 instanceof h1.d) {
                                            break loop13;
                                        }
                                        if ((E2.f5683f & 8192) == 0 && (E2 instanceof m)) {
                                            r0.o oVar7 = ((m) E2).f4921r;
                                            int i57 = 0;
                                            while (oVar7 != null) {
                                                if ((oVar7.f5683f & 8192) != 0) {
                                                    i57++;
                                                    if (i57 == 1) {
                                                        E2 = E2;
                                                        eVar2 = eVar2;
                                                        eVar2 = eVar2;
                                                        E2 = oVar7;
                                                    } else {
                                                        if (eVar2 == 0) {
                                                            eVar2 = new h0.e(new r0.o[16]);
                                                        }
                                                        if (E2 != 0) {
                                                            eVar2.b(E2);
                                                            E2 = 0;
                                                        }
                                                        eVar2.b(oVar7);
                                                    }
                                                } else {
                                                    E2 = E2;
                                                    eVar2 = eVar2;
                                                }
                                                oVar7 = oVar7.f5686i;
                                                E2 = E2;
                                                eVar2 = eVar2;
                                            }
                                            E2 = E2;
                                            eVar2 = eVar2;
                                            if (i57 == 1) {
                                            }
                                        }
                                        E2 = p1.f.e(eVar2);
                                    }
                                }
                                oVar4 = oVar4.f5685h;
                            }
                        }
                        d0VarS2 = d0VarS2.r();
                        oVar4 = (d0VarS2 != null || (t0Var2 = d0VarS2.f4801y) == null) ? null : t0Var2.f4971d;
                    }
                    obj2 = (h1.d) E2;
                    if (obj2 != null) {
                        oVar3 = ((r0.o) obj2).f5681d;
                    } else {
                        oVar = rVar.f5681d;
                        if (oVar.f5693p) {
                            throw new IllegalStateException("visitAncestors called on an unattached node");
                        }
                        oVar2 = oVar.f5685h;
                        d0VarS = p1.f.s(rVar);
                        loop17: while (true) {
                            if (d0VarS != null) {
                                E = 0;
                                break;
                            }
                            if ((d0VarS.f4801y.f4972e.f5684g & 8192) != 0) {
                                while (oVar2 != null) {
                                    if ((oVar2.f5683f & 8192) != 0) {
                                        E = oVar2;
                                        eVar = 0;
                                        while (E != 0) {
                                            if (E instanceof h1.d) {
                                                break loop17;
                                            }
                                            if ((E.f5683f & 8192) == 0 && (E instanceof m)) {
                                                r0.o oVar8 = ((m) E).f4921r;
                                                int i58 = 0;
                                                while (oVar8 != null) {
                                                    if ((oVar8.f5683f & 8192) != 0) {
                                                        i58++;
                                                        if (i58 == 1) {
                                                            E = E;
                                                            eVar = eVar;
                                                            eVar = eVar;
                                                            E = oVar8;
                                                        } else {
                                                            if (eVar == 0) {
                                                                eVar = new h0.e(new r0.o[16]);
                                                            }
                                                            if (E != 0) {
                                                                eVar.b(E);
                                                                E = 0;
                                                            }
                                                            eVar.b(oVar8);
                                                        }
                                                    } else {
                                                        E = E;
                                                        eVar = eVar;
                                                    }
                                                    oVar8 = oVar8.f5686i;
                                                    E = E;
                                                    eVar = eVar;
                                                }
                                                E = E;
                                                eVar = eVar;
                                                if (i58 == 1) {
                                                }
                                            }
                                            E = p1.f.e(eVar);
                                        }
                                    }
                                    oVar2 = oVar2.f5685h;
                                }
                            }
                            d0VarS = d0VarS.r();
                            oVar2 = (d0VarS != null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
                        }
                        obj = (h1.d) E;
                        if (obj != null) {
                            oVar3 = ((r0.o) obj).f5681d;
                        } else {
                            oVar3 = null;
                        }
                    }
                } else {
                    oVar = rVar.f5681d;
                    if (oVar.f5693p) {
                        throw new IllegalStateException("visitAncestors called on an unattached node");
                    }
                    oVar2 = oVar.f5685h;
                    d0VarS = p1.f.s(rVar);
                    loop17: while (true) {
                        if (d0VarS != null) {
                            E = 0;
                            break;
                        }
                        if ((d0VarS.f4801y.f4972e.f5684g & 8192) != 0) {
                            while (oVar2 != null) {
                                if ((oVar2.f5683f & 8192) != 0) {
                                    E = oVar2;
                                    eVar = 0;
                                    while (E != 0) {
                                        if (E instanceof h1.d) {
                                            break loop17;
                                            break loop17;
                                        }
                                        if ((E.f5683f & 8192) == 0) {
                                        }
                                        E = p1.f.e(eVar);
                                    }
                                }
                                oVar2 = oVar2.f5685h;
                            }
                        }
                        d0VarS = d0VarS.r();
                        if (d0VarS != null) {
                        }
                    }
                    obj = (h1.d) E;
                    if (obj != null) {
                        oVar3 = ((r0.o) obj).f5681d;
                    } else {
                        oVar3 = null;
                    }
                }
            }
        } else if (rVarG != null) {
            oVar4 = rVarG.f5681d;
            if (oVar4.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            d0VarS2 = p1.f.s(rVarG);
            loop13: while (true) {
                if (d0VarS2 != null) {
                    E2 = 0;
                    break;
                }
                if ((d0VarS2.f4801y.f4972e.f5684g & 8192) != 0) {
                    while (oVar4 != null) {
                        if ((oVar4.f5683f & 8192) != 0) {
                            eVar2 = 0;
                            E2 = oVar4;
                            while (E2 != 0) {
                                if (E2 instanceof h1.d) {
                                    break loop13;
                                    break loop13;
                                }
                                if ((E2.f5683f & 8192) == 0) {
                                }
                                E2 = p1.f.e(eVar2);
                            }
                        }
                        oVar4 = oVar4.f5685h;
                    }
                }
                d0VarS2 = d0VarS2.r();
                if (d0VarS2 != null) {
                }
            }
            obj2 = (h1.d) E2;
            if (obj2 != null) {
                oVar3 = ((r0.o) obj2).f5681d;
            } else {
                oVar = rVar.f5681d;
                if (oVar.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                oVar2 = oVar.f5685h;
                d0VarS = p1.f.s(rVar);
                loop17: while (true) {
                    if (d0VarS != null) {
                        E = 0;
                        break;
                    }
                    if ((d0VarS.f4801y.f4972e.f5684g & 8192) != 0) {
                        while (oVar2 != null) {
                            if ((oVar2.f5683f & 8192) != 0) {
                                E = oVar2;
                                eVar = 0;
                                while (E != 0) {
                                    if (E instanceof h1.d) {
                                        break loop17;
                                        break loop17;
                                    }
                                    if ((E.f5683f & 8192) == 0) {
                                    }
                                    E = p1.f.e(eVar);
                                }
                            }
                            oVar2 = oVar2.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    if (d0VarS != null) {
                    }
                }
                obj = (h1.d) E;
                if (obj != null) {
                    oVar3 = ((r0.o) obj).f5681d;
                } else {
                    oVar3 = null;
                }
            }
        } else {
            oVar = rVar.f5681d;
            if (oVar.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            oVar2 = oVar.f5685h;
            d0VarS = p1.f.s(rVar);
            loop17: while (true) {
                if (d0VarS != null) {
                    E = 0;
                    break;
                }
                if ((d0VarS.f4801y.f4972e.f5684g & 8192) != 0) {
                    while (oVar2 != null) {
                        if ((oVar2.f5683f & 8192) != 0) {
                            E = oVar2;
                            eVar = 0;
                            while (E != 0) {
                                if (E instanceof h1.d) {
                                    break loop17;
                                    break loop17;
                                }
                                if ((E.f5683f & 8192) == 0) {
                                }
                                E = p1.f.e(eVar);
                            }
                        }
                        oVar2 = oVar2.f5685h;
                    }
                }
                d0VarS = d0VarS.r();
                if (d0VarS != null) {
                }
            }
            obj = (h1.d) E;
            if (obj != null) {
                oVar3 = ((r0.o) obj).f5681d;
            } else {
                oVar3 = null;
            }
        }
        if (oVar3 != null) {
            r0.o oVar9 = oVar3.f5681d;
            if (!oVar9.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            r0.o oVar10 = oVar9.f5685h;
            d0 d0VarS3 = p1.f.s(oVar3);
            ArrayList arrayList = null;
            while (d0VarS3 != null) {
                if ((d0VarS3.f4801y.f4972e.f5684g & 8192) != 0) {
                    while (oVar10 != null) {
                        if ((oVar10.f5683f & 8192) != 0) {
                            r0.o oVarE = oVar10;
                            h0.e eVar3 = null;
                            while (oVarE != null) {
                                if (oVarE instanceof h1.d) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(oVarE);
                                } else if ((oVarE.f5683f & 8192) != 0 && (oVarE instanceof m)) {
                                    int i59 = 0;
                                    for (r0.o oVar11 = ((m) oVarE).f4921r; oVar11 != null; oVar11 = oVar11.f5686i) {
                                        if ((oVar11.f5683f & 8192) != 0) {
                                            i59++;
                                            if (i59 == 1) {
                                                oVarE = oVar11;
                                            } else {
                                                if (eVar3 == null) {
                                                    eVar3 = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE != null) {
                                                    eVar3.b(oVarE);
                                                    oVarE = null;
                                                }
                                                eVar3.b(oVar11);
                                            }
                                        }
                                    }
                                    if (i59 == 1) {
                                    }
                                }
                                oVarE = p1.f.e(eVar3);
                            }
                        }
                        oVar10 = oVar10.f5685h;
                    }
                }
                d0VarS3 = d0VarS3.r();
                oVar10 = (d0VarS3 == null || (t0Var3 = d0VarS3.f4801y) == null) ? null : t0Var3.f4971d;
            }
            if (arrayList != null && (size = arrayList.size() - 1) >= 0) {
                while (true) {
                    int i60 = size - 1;
                    if (((h1.d) arrayList.get(size)).i(keyEvent)) {
                        return true;
                    }
                    if (i60 < 0) {
                        break;
                    }
                    size = i60;
                }
            }
            ?? E3 = oVar3.f5681d;
            ?? eVar4 = 0;
            while (E3 != 0) {
                if (E3 instanceof h1.d) {
                    if (((h1.d) E3).i(keyEvent)) {
                        return true;
                    }
                } else if ((E3.f5683f & 8192) != 0 && (E3 instanceof m)) {
                    r0.o oVar12 = ((m) E3).f4921r;
                    int i61 = 0;
                    while (oVar12 != null) {
                        if ((oVar12.f5683f & 8192) != 0) {
                            i61++;
                            if (i61 == 1) {
                                E3 = E3;
                                eVar4 = eVar4;
                                eVar4 = eVar4;
                                E3 = oVar12;
                            } else {
                                if (eVar4 == 0) {
                                    eVar4 = new h0.e(new r0.o[16]);
                                }
                                if (E3 != 0) {
                                    eVar4.b(E3);
                                    E3 = 0;
                                }
                                eVar4.b(oVar12);
                            }
                        } else {
                            E3 = E3;
                            eVar4 = eVar4;
                        }
                        oVar12 = oVar12.f5686i;
                        E3 = E3;
                        eVar4 = eVar4;
                    }
                    E3 = E3;
                    eVar4 = eVar4;
                    if (i61 == 1) {
                    }
                }
                E3 = p1.f.e(eVar4);
            }
            if (((Boolean) aVar.a()).booleanValue()) {
                return true;
            }
            ?? E4 = oVar3.f5681d;
            ?? eVar5 = 0;
            while (E4 != 0) {
                if (E4 instanceof h1.d) {
                    if (((h1.d) E4).N(keyEvent)) {
                        return true;
                    }
                } else if ((E4.f5683f & 8192) != 0 && (E4 instanceof m)) {
                    r0.o oVar13 = ((m) E4).f4921r;
                    int i62 = 0;
                    while (oVar13 != null) {
                        if ((oVar13.f5683f & 8192) != 0) {
                            i62++;
                            if (i62 == 1) {
                                E4 = E4;
                                eVar5 = eVar5;
                                eVar5 = eVar5;
                                E4 = oVar13;
                            } else {
                                if (eVar5 == 0) {
                                    eVar5 = new h0.e(new r0.o[16]);
                                }
                                if (E4 != 0) {
                                    eVar5.b(E4);
                                    E4 = 0;
                                }
                                eVar5.b(oVar13);
                            }
                        } else {
                            E4 = E4;
                            eVar5 = eVar5;
                        }
                        oVar13 = oVar13.f5686i;
                        E4 = E4;
                        eVar5 = eVar5;
                    }
                    E4 = E4;
                    eVar5 = eVar5;
                    if (i62 == 1) {
                    }
                }
                E4 = p1.f.e(eVar5);
            }
            if (arrayList != null) {
                int size2 = arrayList.size();
                for (int i63 = 0; i63 < size2; i63++) {
                    if (((h1.d) arrayList.get(i63)).N(keyEvent)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v7 */
    public final Boolean c(int i5, x0.d dVar, a4.c cVar) {
        Boolean bool;
        boolean zA;
        Object obj;
        t0 t0Var;
        ?? r4;
        r rVar = this.f179f;
        r rVarG = d.g(rVar);
        int i6 = 4;
        n nVar = this.f178e;
        if (rVarG != null) {
            k2.k kVar = (k2.k) nVar.get();
            bool = null;
            j jVarY0 = rVarG.y0();
            w0.n nVar2 = jVarY0.f6249h;
            w0.n nVar3 = jVarY0.f6250i;
            if (i5 == 1) {
                nVar2 = jVarY0.f6243b;
            } else if (i5 == 2) {
                nVar2 = jVarY0.f6244c;
            } else if (i5 == 5) {
                nVar2 = jVarY0.f6245d;
            } else if (i5 == 6) {
                nVar2 = jVarY0.f6246e;
            } else if (i5 == 3) {
                int iOrdinal = kVar.ordinal();
                if (iOrdinal != 0) {
                    if (iOrdinal != 1) {
                        throw new i4.p();
                    }
                    nVar2 = nVar3;
                }
                if (nVar2 == w0.n.f6254b) {
                    nVar2 = null;
                }
                if (nVar2 == null) {
                    nVar2 = jVarY0.f6247f;
                }
            } else if (i5 == 4) {
                int iOrdinal2 = kVar.ordinal();
                if (iOrdinal2 == 0) {
                    nVar2 = nVar3;
                } else if (iOrdinal2 != 1) {
                    throw new i4.p();
                }
                if (nVar2 == w0.n.f6254b) {
                    nVar2 = null;
                }
                if (nVar2 == null) {
                    nVar2 = jVarY0.f6248g;
                }
            } else if (i5 == 7) {
                jVarY0.f6251j.getClass();
                nVar2 = w0.n.f6254b;
            } else {
                if (i5 != 8) {
                    throw new IllegalStateException("invalid FocusDirection");
                }
                jVarY0.f6252k.getClass();
                nVar2 = w0.n.f6254b;
            }
            if (!l.a(nVar2, w0.n.f6255c)) {
                if (!l.a(nVar2, w0.n.f6254b)) {
                    return Boolean.valueOf(nVar2.a(cVar));
                }
            }
            return bool;
        }
        bool = null;
        rVarG = null;
        k2.k kVar2 = (k2.k) nVar.get();
        c.b bVar = new c.b(rVarG, this, cVar);
        if (i5 == 1 || i5 == 2) {
            if (i5 == 1) {
                zA = d.k(rVar, bVar);
            } else {
                if (i5 != 2) {
                    throw new IllegalStateException("This function should only be used for 1-D focus search");
                }
                zA = d.a(rVar, bVar);
            }
            return Boolean.valueOf(zA);
        }
        if (i5 == 3 || i5 == 4 || i5 == 5 || i5 == 6) {
            return d.J(i5, bVar, rVar, dVar);
        }
        if (i5 == 7) {
            int iOrdinal3 = kVar2.ordinal();
            if (iOrdinal3 != 0) {
                if (iOrdinal3 != 1) {
                    throw new i4.p();
                }
                i6 = 3;
            }
            r rVarG2 = d.g(rVar);
            if (rVarG2 != null) {
                return d.J(i6, bVar, rVarG2, dVar);
            }
            return bool;
        }
        if (i5 != 8) {
            throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) w0.b.a(i5))).toString());
        }
        r rVarG3 = d.g(rVar);
        boolean zBooleanValue = false;
        if (rVarG3 == null) {
            obj = bool;
            break;
        }
        r0.o oVar = rVarG3.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Object obj2 = oVar.f5685h;
        d0 d0VarS = p1.f.s(rVarG3);
        loop0: while (true) {
            if (d0VarS == null) {
                obj = bool;
                break;
            }
            if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                while (r4 != 0) {
                    if ((r4.f5683f & 1024) != 0) {
                        ?? E = r4;
                        ?? eVar = bool;
                        while (E != 0) {
                            if (E instanceof r) {
                                r rVar2 = (r) E;
                                if (rVar2.y0().f6242a) {
                                    obj = rVar2;
                                    break loop0;
                                }
                            } else if ((E.f5683f & 1024) != 0 && (E instanceof m)) {
                                r0.o oVar2 = ((m) E).f4921r;
                                int i7 = 0;
                                while (oVar2 != null) {
                                    if ((oVar2.f5683f & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            E = E;
                                            eVar = eVar;
                                            eVar = eVar;
                                            E = oVar2;
                                        } else {
                                            if (eVar == 0) {
                                                eVar = new h0.e(new r0.o[16]);
                                            }
                                            if (E != 0) {
                                                eVar.b(E);
                                                E = bool;
                                            }
                                            eVar.b(oVar2);
                                        }
                                    } else {
                                        E = E;
                                        eVar = eVar;
                                    }
                                    oVar2 = oVar2.f5686i;
                                    E = E;
                                    eVar = eVar;
                                }
                                if (i7 == 1) {
                                    E = E;
                                    eVar = eVar;
                                } else {
                                    E = E;
                                    eVar = eVar;
                                }
                            }
                            E = p1.f.e(eVar);
                        }
                    }
                    r4 = r4.f5685h;
                }
            }
            r4 = obj2;
            d0VarS = d0VarS.r();
            obj2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? bool : t0Var.f4971d;
        }
        if (obj != null && !obj.equals(rVar)) {
            zBooleanValue = ((Boolean) bVar.c(obj)).booleanValue();
        }
        return Boolean.valueOf(zBooleanValue);
    }

    public final boolean d(int i5) {
        w wVar = new w();
        wVar.f697d = Boolean.FALSE;
        Boolean boolC = c(i5, (x0.d) this.f177d.a(), new j1.l(wVar, i5));
        if (boolC != null && wVar.f697d != null) {
            Boolean bool = Boolean.TRUE;
            if (!boolC.equals(bool) || !l.a(wVar.f697d, bool)) {
                if (i5 != 1 && i5 != 2) {
                    return ((Boolean) this.f175b.c(new w0.b(i5))).booleanValue();
                }
                if (a(i5, false, false)) {
                    Boolean boolC2 = c(i5, null, new w0.h(i5));
                    if (boolC2 != null ? boolC2.booleanValue() : false) {
                    }
                }
            }
            return true;
        }
        return false;
    }
}
