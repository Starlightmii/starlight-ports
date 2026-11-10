package f3;

import android.text.Layout;
import android.text.TextUtils;
import b4.l;
import d2.d;
import h1.c;
import h4.g;
import j.y;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import k2.b;
import l4.d0;
import q3.s;
import y1.f;
import y1.h0;
import y1.n;
import y1.p;
import y1.q;
import y1.r;
import z1.m;
import z1.t;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f2450b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2451c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f2452d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2453e;

    public a() {
        this.f2449a = new Object();
        this.f2451c = new n0.a(0);
        this.f2452d = new y();
        this.f2453e = new y();
    }

    public a(Layout layout) {
        this.f2449a = layout;
        ArrayList arrayList = new ArrayList();
        int length = 0;
        do {
            int iS = g.S(((Layout) this.f2449a).getText(), '\n', length, 4);
            length = iS < 0 ? ((Layout) this.f2449a).getText().length() : iS + 1;
            arrayList.add(Integer.valueOf(length));
        } while (length < ((Layout) this.f2449a).getText().length());
        this.f2450b = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i5 = 0; i5 < size; i5++) {
            arrayList2.add(null);
        }
        this.f2451c = arrayList2;
        this.f2452d = new boolean[((ArrayList) this.f2450b).size()];
        ((ArrayList) this.f2450b).size();
    }

    public a(Map map) {
        l.e("initialState", map);
        this.f2449a = new LinkedHashMap(map);
        this.f2450b = new LinkedHashMap();
        this.f2451c = new LinkedHashMap();
        this.f2452d = new LinkedHashMap();
        this.f2453e = new b.g(1, this);
    }

    public a(f fVar, h0 h0Var, List list, b bVar, d dVar) {
        String strSubstring;
        int i5;
        f fVar2 = fVar;
        h0 h0Var2 = h0Var;
        this.f2449a = fVar2;
        this.f2450b = list;
        this.f2451c = c.D(new n(this, 1));
        this.f2452d = c.D(new n(this, 0));
        r rVar = h0Var2.f6781b;
        f fVar3 = y1.g.f6767a;
        int length = fVar2.f6759d.length();
        List list2 = fVar2.f6761f;
        list2 = list2 == null ? s.f5477d : list2;
        ArrayList arrayList = new ArrayList();
        int size = list2.size();
        int i6 = 0;
        int i7 = 0;
        while (i6 < size) {
            y1.d dVar2 = (y1.d) list2.get(i6);
            r rVar2 = (r) dVar2.f6739a;
            int i8 = dVar2.f6740b;
            int i9 = dVar2.f6741c;
            if (i8 != i7) {
                arrayList.add(new y1.d(i7, i8, rVar));
            }
            arrayList.add(new y1.d(i8, i9, rVar.a(rVar2)));
            i6++;
            i7 = i9;
        }
        if (i7 != length) {
            arrayList.add(new y1.d(i7, length, rVar));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new y1.d(0, 0, rVar));
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        int i10 = 0;
        while (i10 < size2) {
            y1.d dVar3 = (y1.d) arrayList.get(i10);
            int i11 = dVar3.f6740b;
            int i12 = dVar3.f6741c;
            if (i11 != i12) {
                strSubstring = fVar2.f6759d.substring(i11, i12);
                l.d("this as java.lang.String…ing(startIndex, endIndex)", strSubstring);
            } else {
                strSubstring = "";
            }
            String str = strSubstring;
            f fVar4 = new f(str, y1.g.b(fVar2, i11, i12), null, null);
            r rVar3 = (r) dVar3.f6739a;
            if (rVar3.f6810b == Integer.MIN_VALUE) {
                rVar3 = new r(rVar3.f6809a, rVar.f6810b, rVar3.f6811c, rVar3.f6812d, rVar3.f6813e, rVar3.f6814f, rVar3.f6815g, rVar3.f6816h, rVar3.f6817i);
            }
            h0 h0Var3 = new h0(h0Var2.f6780a, rVar.a(rVar3));
            List listA = fVar4.a();
            List list3 = (List) this.f2450b;
            ArrayList arrayList3 = new ArrayList(list3.size());
            int size3 = list3.size();
            int i13 = 0;
            while (i13 < size3) {
                Object obj = list3.get(i13);
                y1.d dVar4 = (y1.d) obj;
                r rVar4 = rVar;
                if (y1.g.c(i11, i12, dVar4.f6740b, dVar4.f6741c)) {
                    arrayList3.add(obj);
                }
                i13++;
                rVar = rVar4;
            }
            r rVar5 = rVar;
            ArrayList arrayList4 = new ArrayList(arrayList3.size());
            int i14 = 0;
            for (int size4 = arrayList3.size(); i14 < size4; size4 = size4) {
                y1.d dVar5 = (y1.d) arrayList3.get(i14);
                int i15 = dVar5.f6740b;
                if (i11 > i15 || (i5 = dVar5.f6741c) > i12) {
                    throw new IllegalArgumentException("placeholder can not overlap with paragraph.");
                }
                arrayList4.add(new y1.d(i15 - i11, i5 - i11, dVar5.f6739a));
                i14++;
            }
            p pVar = new p(new g2.d(str, h0Var3, listA, arrayList4, dVar, bVar), i11, i12);
            ArrayList arrayList5 = arrayList2;
            arrayList5.add(pVar);
            i10++;
            fVar2 = fVar;
            arrayList2 = arrayList5;
            rVar = rVar5;
            h0Var2 = h0Var;
        }
        this.f2453e = arrayList2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, p3.d] */
    @Override // y1.q
    public float a() {
        return ((Number) this.f2451c.getValue()).floatValue();
    }

    @Override // y1.q
    public boolean b() {
        ArrayList arrayList = (ArrayList) this.f2453e;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (((p) arrayList.get(i5)).f6806a.b()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, p3.d] */
    @Override // y1.q
    public float c() {
        return ((Number) this.f2452d.getValue()).floatValue();
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0076  */
    public Bidi d(int i5) {
        Bidi bidi;
        Layout layout = (Layout) this.f2449a;
        ArrayList arrayList = (ArrayList) this.f2450b;
        ArrayList arrayList2 = (ArrayList) this.f2451c;
        boolean[] zArr = (boolean[]) this.f2452d;
        if (zArr[i5]) {
            return (Bidi) arrayList2.get(i5);
        }
        int iIntValue = i5 == 0 ? 0 : ((Number) arrayList.get(i5 - 1)).intValue();
        int iIntValue2 = ((Number) arrayList.get(i5)).intValue();
        int i6 = iIntValue2 - iIntValue;
        char[] cArr = (char[]) this.f2453e;
        if (cArr == null || cArr.length < i6) {
            cArr = new char[i6];
        }
        char[] cArr2 = cArr;
        TextUtils.getChars(layout.getText(), iIntValue, iIntValue2, cArr2, 0);
        if (Bidi.requiresBidi(cArr2, 0, i6)) {
            bidi = new Bidi(cArr2, 0, null, 0, i6, layout.getParagraphDirection(layout.getLineForOffset(i(i5))) == -1 ? 1 : 0);
            if (bidi.getRunCount() == 1) {
                bidi = null;
            }
        } else {
            bidi = null;
        }
        arrayList2.set(i5, bidi);
        zArr[i5] = true;
        if (bidi != null) {
            char[] cArr3 = (char[]) this.f2453e;
            cArr2 = cArr2 == cArr3 ? null : cArr3;
        }
        this.f2453e = cArr2;
        return bidi;
    }

    public void e(a4.c cVar) {
        int i5;
        synchronized (this.f2449a) {
            try {
                y yVar = (y) this.f2452d;
                this.f2452d = (y) this.f2453e;
                this.f2453e = yVar;
                n0.a aVar = (n0.a) this.f2451c;
                do {
                    i5 = aVar.get();
                } while (!aVar.compareAndSet(i5, ((((i5 >>> 27) & 15) + 1) & 15) << 27));
                int i6 = yVar.f3175b;
                for (int i7 = 0; i7 < i6; i7++) {
                    cVar.c(yVar.d(i7));
                }
                yVar.b();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public float f(int i5, boolean z2) {
        Layout layout = (Layout) this.f2449a;
        int lineEnd = layout.getLineEnd(layout.getLineForOffset(i5));
        if (i5 > lineEnd) {
            i5 = lineEnd;
        }
        return z2 ? layout.getPrimaryHorizontal(i5) : layout.getSecondaryHorizontal(i5);
    }

    public float g(int i5, boolean z2, boolean z3) {
        int i6;
        int i7;
        int iJ = i5;
        Layout layout = (Layout) this.f2449a;
        if (!z3) {
            return f(i5, z2);
        }
        int iC = t.c(layout, iJ, z3);
        int lineStart = layout.getLineStart(iC);
        int lineEnd = layout.getLineEnd(iC);
        if (iJ != lineStart && iJ != lineEnd) {
            return f(i5, z2);
        }
        if (iJ == 0 || iJ == layout.getText().length()) {
            return f(i5, z2);
        }
        int iH = h(iJ, z3);
        boolean z4 = layout.getParagraphDirection(layout.getLineForOffset(i(iH))) == -1;
        int iJ2 = j(lineEnd, lineStart);
        int i8 = i(iH);
        int i9 = lineStart - i8;
        int i10 = iJ2 - i8;
        Bidi bidiD = d(iH);
        Bidi bidiCreateLineBidi = bidiD != null ? bidiD.createLineBidi(i9, i10) : null;
        if (bidiCreateLineBidi == null || bidiCreateLineBidi.getRunCount() == 1) {
            boolean zIsRtlCharAt = layout.isRtlCharAt(lineStart);
            if (z2 || z4 == zIsRtlCharAt) {
                z4 = !z4;
            }
            return iJ == lineStart ? z4 : !z4 ? layout.getLineLeft(iC) : layout.getLineRight(iC);
        }
        int runCount = bidiCreateLineBidi.getRunCount();
        m[] mVarArr = new m[runCount];
        for (int i11 = 0; i11 < runCount; i11++) {
            mVarArr[i11] = new m(bidiCreateLineBidi.getRunStart(i11) + lineStart, bidiCreateLineBidi.getRunLimit(i11) + lineStart, bidiCreateLineBidi.getRunLevel(i11) % 2 == 1);
        }
        int runCount2 = bidiCreateLineBidi.getRunCount();
        byte[] bArr = new byte[runCount2];
        for (int i12 = 0; i12 < runCount2; i12++) {
            bArr[i12] = (byte) bidiCreateLineBidi.getRunLevel(i12);
        }
        Bidi.reorderVisually(bArr, 0, mVarArr, 0, runCount);
        if (iJ == lineStart) {
            int i13 = 0;
            while (true) {
                if (i13 >= runCount) {
                    i7 = -1;
                    break;
                }
                if (mVarArr[i13].f7122a == iJ) {
                    i7 = i13;
                    break;
                }
                i13++;
            }
            boolean z5 = (z2 || z4 == mVarArr[i7].f7124c) ? !z4 : z4;
            if (i7 == 0 && z5) {
                return layout.getLineLeft(iC);
            }
            if (i7 != runCount - 1 || z5) {
                return z5 ? layout.getPrimaryHorizontal(mVarArr[i7 - 1].f7122a) : layout.getPrimaryHorizontal(mVarArr[i7 + 1].f7122a);
            }
            return layout.getLineRight(iC);
        }
        if (iJ > iJ2) {
            iJ = j(iJ, lineStart);
        }
        int i14 = 0;
        while (true) {
            if (i14 >= runCount) {
                i6 = -1;
                break;
            }
            if (mVarArr[i14].f7123b == iJ) {
                i6 = i14;
                break;
            }
            i14++;
        }
        boolean z6 = (z2 || z4 == mVarArr[i6].f7124c) ? z4 : !z4;
        if (i6 == 0 && z6) {
            return layout.getLineLeft(iC);
        }
        if (i6 != runCount - 1 || z6) {
            return z6 ? layout.getPrimaryHorizontal(mVarArr[i6 - 1].f7123b) : layout.getPrimaryHorizontal(mVarArr[i6 + 1].f7123b);
        }
        return layout.getLineRight(iC);
    }

    public int h(int i5, boolean z2) {
        int i6;
        ArrayList arrayList = (ArrayList) this.f2450b;
        Integer numValueOf = Integer.valueOf(i5);
        int size = arrayList.size();
        l.e("<this>", arrayList);
        int size2 = arrayList.size();
        if (size < 0) {
            throw new IllegalArgumentException("fromIndex (0) is greater than toIndex (" + size + ").");
        }
        if (size > size2) {
            throw new IndexOutOfBoundsException("toIndex (" + size + ") is greater than size (" + size2 + ").");
        }
        int i7 = size - 1;
        int i8 = 0;
        while (true) {
            if (i8 > i7) {
                i6 = -(i8 + 1);
                break;
            }
            i6 = (i8 + i7) >>> 1;
            int iJ = p3.p.j((Comparable) arrayList.get(i6), numValueOf);
            if (iJ >= 0) {
                if (iJ <= 0) {
                    break;
                }
                i7 = i6 - 1;
            } else {
                i8 = i6 + 1;
            }
        }
        int i9 = i6 < 0 ? -(i6 + 1) : i6 + 1;
        if (z2 && i9 > 0) {
            int i10 = i9 - 1;
            if (i5 == ((Number) arrayList.get(i10)).intValue()) {
                return i10;
            }
        }
        return i9;
    }

    public int i(int i5) {
        if (i5 == 0) {
            return 0;
        }
        return ((Number) ((ArrayList) this.f2450b).get(i5 - 1)).intValue();
    }

    public int j(int i5, int i6) {
        while (i5 > i6) {
            char cCharAt = ((Layout) this.f2449a).getText().charAt(i5 - 1);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != 5760 && ((l.f(cCharAt, 8192) < 0 || l.f(cCharAt, 8202) > 0 || cCharAt == 8199) && cCharAt != 8287 && cCharAt != 12288)) {
                return i5;
            }
            i5--;
        }
        return i5;
    }

    public void k(String str, Object obj) {
        b3.r rVar = m4.c.f3818b;
        l.e("key", str);
        ((LinkedHashMap) this.f2449a).put(str, obj);
        l4.p pVar = (l4.p) ((LinkedHashMap) this.f2451c).get(str);
        if (pVar != null) {
            ((d0) pVar).i(null, obj == null ? rVar : obj);
        }
        l4.p pVar2 = (l4.p) ((LinkedHashMap) this.f2452d).get(str);
        if (pVar2 != null) {
            d0 d0Var = (d0) pVar2;
            if (obj == null) {
                obj = rVar;
            }
            d0Var.i(null, obj);
        }
    }
}
