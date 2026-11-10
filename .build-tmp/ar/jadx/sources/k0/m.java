package k0;

import f0.x1;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f3379e = new m(0, 0, new Object[0], null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3381b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m0.b f3382c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object[] f3383d;

    public m(int i5, int i6, Object[] objArr, m0.b bVar) {
        this.f3380a = i5;
        this.f3381b = i6;
        this.f3382c = bVar;
        this.f3383d = objArr;
    }

    public static m j(int i5, Object obj, Object obj2, int i6, Object obj3, Object obj4, int i7, m0.b bVar) {
        if (i7 > 30) {
            return new m(0, 0, new Object[]{obj, obj2, obj3, obj4}, bVar);
        }
        int iZ = h1.c.z(i5, i7);
        int iZ2 = h1.c.z(i6, i7);
        if (iZ != iZ2) {
            return new m((1 << iZ) | (1 << iZ2), 0, iZ < iZ2 ? new Object[]{obj, obj2, obj3, obj4} : new Object[]{obj3, obj4, obj, obj2}, bVar);
        }
        return new m(0, 1 << iZ, new Object[]{j(i5, obj, obj2, i6, obj3, obj4, i7 + 5, bVar)}, bVar);
    }

    public final Object[] a(int i5, int i6, int i7, Object obj, Object obj2, int i8, m0.b bVar) {
        Object obj3 = this.f3383d[i5];
        m mVarJ = j(obj3 != null ? obj3.hashCode() : 0, obj3, x(i5), i7, obj, obj2, i8 + 5, bVar);
        int iT = t(i6);
        int i9 = iT + 1;
        Object[] objArr = this.f3383d;
        Object[] objArr2 = new Object[objArr.length - 1];
        q3.k.C(objArr, objArr2, 0, i5, 6);
        q3.k.A(objArr, objArr2, i5, i5 + 2, i9);
        objArr2[iT - 1] = mVarJ;
        q3.k.A(objArr, objArr2, iT, i9, objArr.length);
        return objArr2;
    }

    public final int b() {
        if (this.f3381b == 0) {
            return this.f3383d.length / 2;
        }
        int iBitCount = Integer.bitCount(this.f3380a);
        int length = this.f3383d.length;
        for (int i5 = iBitCount * 2; i5 < length; i5++) {
            iBitCount += s(i5).b();
        }
        return iBitCount;
    }

    public final boolean c(Object obj) {
        e4.b bVarI = d4.a.I(d4.a.N(0, this.f3383d.length));
        int i5 = bVarI.f2074d;
        int i6 = bVarI.f2075e;
        int i7 = bVarI.f2076f;
        if ((i7 > 0 && i5 <= i6) || (i7 < 0 && i6 <= i5)) {
            while (!b4.l.a(obj, this.f3383d[i5])) {
                if (i5 != i6) {
                    i5 += i7;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean d(int i5, int i6, Object obj) {
        int iZ = 1 << h1.c.z(i5, i6);
        if (h(iZ)) {
            return b4.l.a(obj, this.f3383d[f(iZ)]);
        }
        if (!i(iZ)) {
            return false;
        }
        m mVarS = s(t(iZ));
        return i6 == 30 ? mVarS.c(obj) : mVarS.d(i5, i6 + 5, obj);
    }

    public final boolean e(m mVar) {
        if (this == mVar) {
            return true;
        }
        if (this.f3381b == mVar.f3381b && this.f3380a == mVar.f3380a) {
            int length = this.f3383d.length;
            for (int i5 = 0; i5 < length; i5++) {
                if (this.f3383d[i5] == mVar.f3383d[i5]) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f(int i5) {
        return Integer.bitCount((i5 - 1) & this.f3380a) * 2;
    }

    public final Object g(int i5, int i6, Object obj) {
        int iZ = 1 << h1.c.z(i5, i6);
        if (h(iZ)) {
            int iF = f(iZ);
            if (b4.l.a(obj, this.f3383d[iF])) {
                return x(iF);
            }
            return null;
        }
        if (!i(iZ)) {
            return null;
        }
        m mVarS = s(t(iZ));
        if (i6 != 30) {
            return mVarS.g(i5, i6 + 5, obj);
        }
        e4.b bVarI = d4.a.I(d4.a.N(0, mVarS.f3383d.length));
        int i7 = bVarI.f2074d;
        int i8 = bVarI.f2075e;
        int i9 = bVarI.f2076f;
        if ((i9 <= 0 || i7 > i8) && (i9 >= 0 || i8 > i7)) {
            return null;
        }
        while (!b4.l.a(obj, mVarS.f3383d[i7])) {
            if (i7 == i8) {
                return null;
            }
            i7 += i9;
        }
        return mVarS.x(i7);
    }

    public final boolean h(int i5) {
        return (i5 & this.f3380a) != 0;
    }

    public final boolean i(int i5) {
        return (i5 & this.f3381b) != 0;
    }

    public final m k(int i5, n0.g gVar) {
        gVar.e(gVar.f4067h - 1);
        gVar.f4065f = x(i5);
        Object[] objArr = this.f3383d;
        if (objArr.length == 2) {
            return null;
        }
        if (this.f3382c != gVar.f4063d) {
            return new m(0, 0, h1.c.i(i5, objArr), gVar.f4063d);
        }
        this.f3383d = h1.c.i(i5, objArr);
        return this;
    }

    public final m l(int i5, Object obj, Object obj2, int i6, n0.g gVar) {
        n0.g gVar2;
        m mVarL;
        int iZ = 1 << h1.c.z(i5, i6);
        boolean zH = h(iZ);
        m0.b bVar = this.f3382c;
        if (zH) {
            int iF = f(iZ);
            if (!b4.l.a(obj, this.f3383d[iF])) {
                gVar.e(gVar.f4067h + 1);
                m0.b bVar2 = gVar.f4063d;
                if (bVar != bVar2) {
                    return new m(this.f3380a ^ iZ, this.f3381b | iZ, a(iF, iZ, i5, obj, obj2, i6, bVar2), bVar2);
                }
                this.f3383d = a(iF, iZ, i5, obj, obj2, i6, bVar2);
                this.f3380a ^= iZ;
                this.f3381b |= iZ;
                return this;
            }
            gVar.f4065f = x(iF);
            if (x(iF) == obj2) {
                return this;
            }
            if (bVar == gVar.f4063d) {
                this.f3383d[iF + 1] = obj2;
                return this;
            }
            gVar.f4066g++;
            Object[] objArr = this.f3383d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            b4.l.d("copyOf(...)", objArrCopyOf);
            objArrCopyOf[iF + 1] = obj2;
            return new m(this.f3380a, this.f3381b, objArrCopyOf, gVar.f4063d);
        }
        if (!i(iZ)) {
            gVar.e(gVar.f4067h + 1);
            m0.b bVar3 = gVar.f4063d;
            int iF2 = f(iZ);
            if (bVar != bVar3) {
                return new m(this.f3380a | iZ, this.f3381b, h1.c.g(this.f3383d, iF2, obj, obj2), bVar3);
            }
            this.f3383d = h1.c.g(this.f3383d, iF2, obj, obj2);
            this.f3380a |= iZ;
            return this;
        }
        int iT = t(iZ);
        m mVarS = s(iT);
        if (i6 == 30) {
            e4.b bVarI = d4.a.I(d4.a.N(0, mVarS.f3383d.length));
            int i7 = bVarI.f2074d;
            int i8 = bVarI.f2075e;
            int i9 = bVarI.f2076f;
            if ((i9 > 0 && i7 <= i8) || (i9 < 0 && i8 <= i7)) {
                while (true) {
                    if (!b4.l.a(obj, mVarS.f3383d[i7])) {
                        if (i7 == i8) {
                            gVar.e(gVar.f4067h + 1);
                            mVarL = new m(0, 0, h1.c.g(mVarS.f3383d, 0, obj, obj2), gVar.f4063d);
                            break;
                        }
                        i7 += i9;
                    } else {
                        gVar.f4065f = mVarS.x(i7);
                        if (mVarS.f3382c != gVar.f4063d) {
                            gVar.f4066g++;
                            Object[] objArr2 = mVarS.f3383d;
                            Object[] objArrCopyOf2 = Arrays.copyOf(objArr2, objArr2.length);
                            b4.l.d("copyOf(...)", objArrCopyOf2);
                            objArrCopyOf2[i7 + 1] = obj2;
                            mVarL = new m(0, 0, objArrCopyOf2, gVar.f4063d);
                            break;
                        }
                        mVarS.f3383d[i7 + 1] = obj2;
                        mVarL = mVarS;
                        break;
                    }
                }
            } else {
                gVar.e(gVar.f4067h + 1);
                mVarL = new m(0, 0, h1.c.g(mVarS.f3383d, 0, obj, obj2), gVar.f4063d);
                break;
            }
            gVar2 = gVar;
        } else {
            gVar2 = gVar;
            mVarL = mVarS.l(i5, obj, obj2, i6 + 5, gVar2);
        }
        return mVarS == mVarL ? this : r(iT, mVarL, gVar2.f4063d);
    }

    public final m m(m mVar, int i5, m0.a aVar, n0.g gVar) {
        m mVar2;
        Object[] objArr;
        m mVarJ;
        if (this == mVar) {
            aVar.f3810a += b();
            return this;
        }
        int i6 = 0;
        if (i5 > 30) {
            m0.b bVar = gVar.f4063d;
            int i7 = mVar.f3381b;
            Object[] objArr2 = this.f3383d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length + mVar.f3383d.length);
            b4.l.d("copyOf(...)", objArrCopyOf);
            int length = this.f3383d.length;
            e4.b bVarI = d4.a.I(d4.a.N(0, mVar.f3383d.length));
            int i8 = bVarI.f2074d;
            int i9 = bVarI.f2075e;
            int i10 = bVarI.f2076f;
            if ((i10 > 0 && i8 <= i9) || (i10 < 0 && i9 <= i8)) {
                while (true) {
                    if (c(mVar.f3383d[i8])) {
                        aVar.f3810a++;
                    } else {
                        Object[] objArr3 = mVar.f3383d;
                        objArrCopyOf[length] = objArr3[i8];
                        objArrCopyOf[length + 1] = objArr3[i8 + 1];
                        length += 2;
                    }
                    if (i8 == i9) {
                        break;
                    }
                    i8 += i10;
                }
            }
            if (length != this.f3383d.length) {
                if (length == mVar.f3383d.length) {
                    return mVar;
                }
                if (length == objArrCopyOf.length) {
                    return new m(0, 0, objArrCopyOf, bVar);
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, length);
                b4.l.d("copyOf(...)", objArrCopyOf2);
                return new m(0, 0, objArrCopyOf2, bVar);
            }
        } else {
            int i11 = this.f3381b | mVar.f3381b;
            int i12 = this.f3380a;
            int i13 = mVar.f3380a;
            int i14 = (i12 ^ i13) & (~i11);
            int i15 = i12 & i13;
            int i16 = i14;
            while (i15 != 0) {
                int iLowestOneBit = Integer.lowestOneBit(i15);
                if (b4.l.a(this.f3383d[f(iLowestOneBit)], mVar.f3383d[mVar.f(iLowestOneBit)])) {
                    i16 |= iLowestOneBit;
                } else {
                    i11 |= iLowestOneBit;
                }
                i15 ^= iLowestOneBit;
            }
            if ((i11 & i16) != 0) {
                x1.b("Check failed.");
            }
            if (b4.l.a(this.f3382c, gVar.f4063d) && this.f3380a == i16 && this.f3381b == i11) {
                mVar2 = this;
            } else {
                mVar2 = new m(i16, i11, new Object[Integer.bitCount(i11) + (Integer.bitCount(i16) * 2)], null);
            }
            int i17 = i11;
            int i18 = 0;
            while (i17 != 0) {
                int iLowestOneBit2 = Integer.lowestOneBit(i17);
                Object[] objArr4 = mVar2.f3383d;
                int length2 = (objArr4.length - 1) - i18;
                if (i(iLowestOneBit2)) {
                    mVarJ = s(t(iLowestOneBit2));
                    if (mVar.i(iLowestOneBit2)) {
                        mVarJ = mVarJ.m(mVar.s(mVar.t(iLowestOneBit2)), i5 + 5, aVar, gVar);
                        objArr = objArr4;
                    } else if (mVar.h(iLowestOneBit2)) {
                        int iF = mVar.f(iLowestOneBit2);
                        Object obj = mVar.f3383d[iF];
                        Object objX = mVar.x(iF);
                        int i19 = gVar.f4067h;
                        objArr = objArr4;
                        mVarJ = mVarJ.l(obj != null ? obj.hashCode() : i6, obj, objX, i5 + 5, gVar);
                        if (gVar.f4067h == i19) {
                            aVar.f3810a++;
                        }
                    } else {
                        objArr = objArr4;
                    }
                } else {
                    objArr = objArr4;
                    if (mVar.i(iLowestOneBit2)) {
                        m mVarS = mVar.s(mVar.t(iLowestOneBit2));
                        if (h(iLowestOneBit2)) {
                            int iF2 = f(iLowestOneBit2);
                            Object obj2 = this.f3383d[iF2];
                            int i20 = i5 + 5;
                            if (mVarS.d(obj2 != null ? obj2.hashCode() : 0, i20, obj2)) {
                                aVar.f3810a++;
                                mVarJ = mVarS;
                            } else {
                                mVarJ = mVarS.l(obj2 != null ? obj2.hashCode() : 0, obj2, x(iF2), i20, gVar);
                            }
                        } else {
                            mVarJ = mVarS;
                        }
                    } else {
                        int iF3 = f(iLowestOneBit2);
                        Object obj3 = this.f3383d[iF3];
                        Object objX2 = x(iF3);
                        int iF4 = mVar.f(iLowestOneBit2);
                        Object obj4 = mVar.f3383d[iF4];
                        mVarJ = j(obj3 != null ? obj3.hashCode() : 0, obj3, objX2, obj4 != null ? obj4.hashCode() : 0, obj4, mVar.x(iF4), i5 + 5, gVar.f4063d);
                    }
                }
                objArr[length2] = mVarJ;
                i18++;
                i17 ^= iLowestOneBit2;
                i6 = 0;
            }
            int i21 = 0;
            while (i16 != 0) {
                int iLowestOneBit3 = Integer.lowestOneBit(i16);
                int i22 = i21 * 2;
                if (mVar.h(iLowestOneBit3)) {
                    int iF5 = mVar.f(iLowestOneBit3);
                    Object[] objArr5 = mVar2.f3383d;
                    objArr5[i22] = mVar.f3383d[iF5];
                    objArr5[i22 + 1] = mVar.x(iF5);
                    if (h(iLowestOneBit3)) {
                        aVar.f3810a++;
                    }
                } else {
                    int iF6 = f(iLowestOneBit3);
                    Object[] objArr6 = mVar2.f3383d;
                    objArr6[i22] = this.f3383d[iF6];
                    objArr6[i22 + 1] = x(iF6);
                }
                i21++;
                i16 ^= iLowestOneBit3;
            }
            if (!e(mVar2)) {
                return mVar.e(mVar2) ? mVar : mVar2;
            }
        }
        return this;
    }

    public final m n(int i5, Object obj, int i6, n0.g gVar) {
        m mVarN;
        int iZ = 1 << h1.c.z(i5, i6);
        if (h(iZ)) {
            int iF = f(iZ);
            if (b4.l.a(obj, this.f3383d[iF])) {
                return p(iF, iZ, gVar);
            }
        } else if (i(iZ)) {
            int iT = t(iZ);
            m mVarS = s(iT);
            if (i6 == 30) {
                e4.b bVarI = d4.a.I(d4.a.N(0, mVarS.f3383d.length));
                int i7 = bVarI.f2074d;
                int i8 = bVarI.f2075e;
                int i9 = bVarI.f2076f;
                if ((i9 > 0 && i7 <= i8) || (i9 < 0 && i8 <= i7)) {
                    while (true) {
                        if (!b4.l.a(obj, mVarS.f3383d[i7])) {
                            if (i7 == i8) {
                                mVarN = mVarS;
                                break;
                            }
                            i7 += i9;
                        } else {
                            mVarN = mVarS.k(i7, gVar);
                            break;
                        }
                    }
                } else {
                    mVarN = mVarS;
                    break;
                }
            } else {
                mVarN = mVarS.n(i5, obj, i6 + 5, gVar);
            }
            return q(mVarS, mVarN, iT, iZ, gVar.f4063d);
        }
        return this;
    }

    public final m o(int i5, Object obj, Object obj2, int i6, n0.g gVar) {
        m mVar;
        m mVarO;
        int iZ = 1 << h1.c.z(i5, i6);
        if (h(iZ)) {
            int iF = f(iZ);
            if (b4.l.a(obj, this.f3383d[iF]) && b4.l.a(obj2, x(iF))) {
                return p(iF, iZ, gVar);
            }
        } else if (i(iZ)) {
            int iT = t(iZ);
            m mVarS = s(iT);
            if (i6 == 30) {
                e4.b bVarI = d4.a.I(d4.a.N(0, mVarS.f3383d.length));
                int i7 = bVarI.f2074d;
                int i8 = bVarI.f2075e;
                int i9 = bVarI.f2076f;
                if ((i9 > 0 && i7 <= i8) || (i9 < 0 && i8 <= i7)) {
                    while (true) {
                        if (!b4.l.a(obj, mVarS.f3383d[i7]) || !b4.l.a(obj2, mVarS.x(i7))) {
                            if (i7 == i8) {
                                mVarO = mVarS;
                                break;
                            }
                            i7 += i9;
                        } else {
                            mVarO = mVarS.k(i7, gVar);
                            break;
                        }
                    }
                } else {
                    mVarO = mVarS;
                    break;
                }
                mVar = mVarS;
            } else {
                mVar = mVarS;
                mVarO = mVar.o(i5, obj, obj2, i6 + 5, gVar);
            }
            return q(mVar, mVarO, iT, iZ, gVar.f4063d);
        }
        return this;
    }

    public final m p(int i5, int i6, n0.g gVar) {
        gVar.e(gVar.f4067h - 1);
        gVar.f4065f = x(i5);
        Object[] objArr = this.f3383d;
        if (objArr.length == 2) {
            return null;
        }
        if (this.f3382c != gVar.f4063d) {
            return new m(i6 ^ this.f3380a, this.f3381b, h1.c.i(i5, objArr), gVar.f4063d);
        }
        this.f3383d = h1.c.i(i5, objArr);
        this.f3380a ^= i6;
        return this;
    }

    public final m q(m mVar, m mVar2, int i5, int i6, m0.b bVar) {
        m0.b bVar2 = this.f3382c;
        if (mVar2 != null) {
            return (bVar2 == bVar || mVar != mVar2) ? r(i5, mVar2, bVar) : this;
        }
        Object[] objArr = this.f3383d;
        if (objArr.length == 1) {
            return null;
        }
        if (bVar2 != bVar) {
            return new m(this.f3380a, i6 ^ this.f3381b, h1.c.j(i5, objArr), bVar);
        }
        this.f3383d = h1.c.j(i5, objArr);
        this.f3381b ^= i6;
        return this;
    }

    public final m r(int i5, m mVar, m0.b bVar) {
        Object[] objArr = this.f3383d;
        if (objArr.length == 1 && mVar.f3383d.length == 2 && mVar.f3381b == 0) {
            mVar.f3380a = this.f3381b;
            return mVar;
        }
        if (this.f3382c == bVar) {
            objArr[i5] = mVar;
            return this;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        b4.l.d("copyOf(...)", objArrCopyOf);
        objArrCopyOf[i5] = mVar;
        return new m(this.f3380a, this.f3381b, objArrCopyOf, bVar);
    }

    public final m s(int i5) {
        Object obj = this.f3383d[i5];
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>", obj);
        return (m) obj;
    }

    public final int t(int i5) {
        return (this.f3383d.length - 1) - Integer.bitCount((i5 - 1) & this.f3381b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d0, code lost:
    
        if (r14 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d9, code lost:
    
        if (r14 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00dc, code lost:
    
        r14.f3378b = w(r12, r4, (k0.m) r14.f3378b);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e6, code lost:
    
        return r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final k0.l u(int r12, int r13, java.lang.Object r14, java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k0.m.u(int, int, java.lang.Object, java.lang.Object):k0.l");
    }

    public final m v(int i5, int i6, Object obj) {
        m mVarV;
        int iZ = 1 << h1.c.z(i5, i6);
        if (h(iZ)) {
            int iF = f(iZ);
            if (b4.l.a(obj, this.f3383d[iF])) {
                Object[] objArr = this.f3383d;
                if (objArr.length != 2) {
                    return new m(this.f3380a ^ iZ, this.f3381b, h1.c.i(iF, objArr), null);
                }
                return null;
            }
            return this;
        }
        if (i(iZ)) {
            int iT = t(iZ);
            m mVarS = s(iT);
            if (i6 == 30) {
                e4.b bVarI = d4.a.I(d4.a.N(0, mVarS.f3383d.length));
                int i7 = bVarI.f2074d;
                int i8 = bVarI.f2075e;
                int i9 = bVarI.f2076f;
                if ((i9 > 0 && i7 <= i8) || (i9 < 0 && i8 <= i7)) {
                    while (true) {
                        if (!b4.l.a(obj, mVarS.f3383d[i7])) {
                            if (i7 == i8) {
                                mVarV = mVarS;
                                break;
                            }
                            i7 += i9;
                        } else {
                            Object[] objArr2 = mVarS.f3383d;
                            if (objArr2.length != 2) {
                                mVarV = new m(0, 0, h1.c.i(i7, objArr2), null);
                                break;
                            }
                            mVarV = null;
                            break;
                        }
                    }
                } else {
                    mVarV = mVarS;
                    break;
                }
            } else {
                mVarV = mVarS.v(i5, i6 + 5, obj);
            }
            if (mVarV == null) {
                Object[] objArr3 = this.f3383d;
                if (objArr3.length != 1) {
                    return new m(this.f3380a, iZ ^ this.f3381b, h1.c.j(iT, objArr3), null);
                }
                return null;
            }
            if (mVarS != mVarV) {
                return w(iT, iZ, mVarV);
            }
        }
        return this;
    }

    public final m w(int i5, int i6, m mVar) {
        Object[] objArr = mVar.f3383d;
        if (objArr.length != 2 || mVar.f3381b != 0) {
            Object[] objArr2 = this.f3383d;
            Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length);
            b4.l.d("copyOf(...)", objArrCopyOf);
            objArrCopyOf[i5] = mVar;
            return new m(this.f3380a, this.f3381b, objArrCopyOf, null);
        }
        if (this.f3383d.length == 1) {
            mVar.f3380a = this.f3381b;
            return mVar;
        }
        int iF = f(i6);
        Object[] objArr3 = this.f3383d;
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr3, objArr3.length + 1);
        b4.l.d("copyOf(...)", objArrCopyOf2);
        q3.k.A(objArrCopyOf2, objArrCopyOf2, i5 + 2, i5 + 1, objArr3.length);
        q3.k.A(objArrCopyOf2, objArrCopyOf2, iF + 2, iF, i5);
        objArrCopyOf2[iF] = obj;
        objArrCopyOf2[iF + 1] = obj2;
        return new m(this.f3380a ^ i6, i6 ^ this.f3381b, objArrCopyOf2, null);
    }

    public final Object x(int i5) {
        return this.f3383d[i5 + 1];
    }
}
