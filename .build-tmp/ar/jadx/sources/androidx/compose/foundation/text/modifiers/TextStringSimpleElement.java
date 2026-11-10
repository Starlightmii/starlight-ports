package androidx.compose.foundation.text.modifiers;

import a.a;
import b.b;
import b4.l;
import d2.d;
import h1.c;
import m.h;
import p1.f;
import p1.q0;
import r0.o;
import y.g;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class TextStringSimpleElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f144c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f145d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f146e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f147f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f148g;

    public TextStringSimpleElement(String str, h0 h0Var, d dVar, int i5, boolean z2, int i6, int i7) {
        this.f142a = str;
        this.f143b = h0Var;
        this.f144c = dVar;
        this.f145d = i5;
        this.f146e = z2;
        this.f147f = i6;
        this.f148g = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStringSimpleElement)) {
            return false;
        }
        TextStringSimpleElement textStringSimpleElement = (TextStringSimpleElement) obj;
        return l.a(this.f142a, textStringSimpleElement.f142a) && l.a(this.f143b, textStringSimpleElement.f143b) && l.a(this.f144c, textStringSimpleElement.f144c) && this.f145d == textStringSimpleElement.f145d && this.f146e == textStringSimpleElement.f146e && this.f147f == textStringSimpleElement.f147f && this.f148g == textStringSimpleElement.f148g;
    }

    @Override // p1.q0
    public final o h() {
        g gVar = new g();
        gVar.f6605q = this.f142a;
        gVar.f6606r = this.f143b;
        gVar.f6607s = this.f144c;
        gVar.f6608t = this.f145d;
        gVar.f6609u = this.f146e;
        gVar.f6610v = this.f147f;
        gVar.f6611w = this.f148g;
        return gVar;
    }

    public final int hashCode() {
        return (((b.c(h.b(this.f145d, (this.f144c.hashCode() + ((this.f143b.hashCode() + (this.f142a.hashCode() * 31)) * 31)) * 31, 31), 31, this.f146e) + this.f147f) * 31) + this.f148g) * 31;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0029  */
    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    /* JADX WARN: Code duplicated, block: B:16:0x003f  */
    /* JADX WARN: Code duplicated, block: B:19:0x0048  */
    /* JADX WARN: Code duplicated, block: B:22:0x0051  */
    /* JADX WARN: Code duplicated, block: B:25:0x005e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0067  */
    /* JADX WARN: Code duplicated, block: B:29:0x0069  */
    /* JADX WARN: Code duplicated, block: B:32:0x006f  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:43:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:? A[RETURN, SYNTHETIC] */
    @Override // p1.q0
    public final void i(o oVar) {
        boolean z2;
        String str;
        String str2;
        boolean z3;
        boolean z4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z5;
        boolean z6;
        d dVar;
        d dVar2;
        int i9;
        int i10;
        g gVar = (g) oVar;
        gVar.getClass();
        h0 h0Var = gVar.f6606r;
        boolean z7 = true;
        h0 h0Var2 = this.f143b;
        if (h0Var2 != h0Var) {
            if (!h0Var2.f6780a.b(h0Var.f6780a)) {
                z2 = true;
            }
            str = gVar.f6605q;
            str2 = this.f142a;
            if (l.a(str, str2)) {
                z3 = false;
            } else {
                gVar.f6605q = str2;
                gVar.A = null;
                z3 = true;
            }
            z4 = !gVar.f6606r.c(h0Var2);
            gVar.f6606r = h0Var2;
            i5 = gVar.f6611w;
            i6 = this.f148g;
            if (i5 != i6) {
                gVar.f6611w = i6;
                z4 = true;
            }
            i7 = gVar.f6610v;
            i8 = this.f147f;
            if (i7 != i8) {
                gVar.f6610v = i8;
                z4 = true;
            }
            z5 = gVar.f6609u;
            z6 = this.f146e;
            if (z5 != z6) {
                gVar.f6609u = z6;
                z4 = true;
            }
            dVar = gVar.f6607s;
            dVar2 = this.f144c;
            if (!l.a(dVar, dVar2)) {
                gVar.f6607s = dVar2;
                z4 = true;
            }
            i9 = gVar.f6608t;
            i10 = this.f145d;
            if (i9 == i10) {
                z7 = z4;
            } else {
                gVar.f6608t = i10;
            }
            if (z3 || z7) {
                y.d dVarY0 = gVar.y0();
                String str3 = gVar.f6605q;
                h0 h0Var3 = gVar.f6606r;
                d dVar3 = gVar.f6607s;
                int i11 = gVar.f6608t;
                boolean z8 = gVar.f6609u;
                int i12 = gVar.f6610v;
                int i13 = gVar.f6611w;
                dVarY0.f6581a = str3;
                dVarY0.f6582b = h0Var3;
                dVarY0.f6583c = dVar3;
                dVarY0.f6584d = i11;
                dVarY0.f6585e = z8;
                dVarY0.f6586f = i12;
                dVarY0.f6587g = i13;
                dVarY0.f6590j = null;
                dVarY0.f6594n = null;
                dVarY0.f6595o = null;
                dVarY0.f6597q = -1;
                dVarY0.f6598r = -1;
                dVarY0.f6596p = c.r(0, 0, 0, 0);
                dVarY0.f6592l = a.f(0, 0);
                dVarY0.f6591k = false;
            }
            if (gVar.f5693p) {
                if (z3 || (z2 && gVar.f6614z != null)) {
                    f.n(gVar);
                }
                if (z3 || z7) {
                    f.m(gVar);
                    f.l(gVar);
                }
                if (z2) {
                    f.l(gVar);
                }
            }
            return;
        }
        h0Var2.getClass();
        z2 = false;
        str = gVar.f6605q;
        str2 = this.f142a;
        if (l.a(str, str2)) {
            z3 = false;
        } else {
            gVar.f6605q = str2;
            gVar.A = null;
            z3 = true;
        }
        z4 = !gVar.f6606r.c(h0Var2);
        gVar.f6606r = h0Var2;
        i5 = gVar.f6611w;
        i6 = this.f148g;
        if (i5 != i6) {
            gVar.f6611w = i6;
            z4 = true;
        }
        i7 = gVar.f6610v;
        i8 = this.f147f;
        if (i7 != i8) {
            gVar.f6610v = i8;
            z4 = true;
        }
        z5 = gVar.f6609u;
        z6 = this.f146e;
        if (z5 != z6) {
            gVar.f6609u = z6;
            z4 = true;
        }
        dVar = gVar.f6607s;
        dVar2 = this.f144c;
        if (!l.a(dVar, dVar2)) {
            gVar.f6607s = dVar2;
            z4 = true;
        }
        i9 = gVar.f6608t;
        i10 = this.f145d;
        if (i9 == i10) {
            z7 = z4;
        } else {
            gVar.f6608t = i10;
        }
        if (z3) {
            y.d dVarY1 = gVar.y0();
            String str4 = gVar.f6605q;
            h0 h0Var4 = gVar.f6606r;
            d dVar4 = gVar.f6607s;
            int i14 = gVar.f6608t;
            boolean z9 = gVar.f6609u;
            int i15 = gVar.f6610v;
            int i16 = gVar.f6611w;
            dVarY1.f6581a = str4;
            dVarY1.f6582b = h0Var4;
            dVarY1.f6583c = dVar4;
            dVarY1.f6584d = i14;
            dVarY1.f6585e = z9;
            dVarY1.f6586f = i15;
            dVarY1.f6587g = i16;
            dVarY1.f6590j = null;
            dVarY1.f6594n = null;
            dVarY1.f6595o = null;
            dVarY1.f6597q = -1;
            dVarY1.f6598r = -1;
            dVarY1.f6596p = c.r(0, 0, 0, 0);
            dVarY1.f6592l = a.f(0, 0);
            dVarY1.f6591k = false;
        } else {
            y.d dVarY2 = gVar.y0();
            String str5 = gVar.f6605q;
            h0 h0Var5 = gVar.f6606r;
            d dVar5 = gVar.f6607s;
            int i17 = gVar.f6608t;
            boolean z10 = gVar.f6609u;
            int i18 = gVar.f6610v;
            int i19 = gVar.f6611w;
            dVarY2.f6581a = str5;
            dVarY2.f6582b = h0Var5;
            dVarY2.f6583c = dVar5;
            dVarY2.f6584d = i17;
            dVarY2.f6585e = z10;
            dVarY2.f6586f = i18;
            dVarY2.f6587g = i19;
            dVarY2.f6590j = null;
            dVarY2.f6594n = null;
            dVarY2.f6595o = null;
            dVarY2.f6597q = -1;
            dVarY2.f6598r = -1;
            dVarY2.f6596p = c.r(0, 0, 0, 0);
            dVarY2.f6592l = a.f(0, 0);
            dVarY2.f6591k = false;
        }
        if (gVar.f5693p) {
            return;
        }
        if (z3) {
            f.n(gVar);
        } else {
            f.n(gVar);
        }
        if (z3) {
            f.m(gVar);
            f.l(gVar);
        } else {
            f.m(gVar);
            f.l(gVar);
        }
        if (z2) {
            f.l(gVar);
        }
    }
}
