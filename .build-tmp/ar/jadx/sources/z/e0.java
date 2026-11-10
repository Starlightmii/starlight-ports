package z;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Parcel;
import android.text.Annotation;
import android.text.Spanned;
import android.util.Base64;
import android.view.ActionMode;
import f0.s1;
import java.util.ArrayList;
import java.util.List;
import q1.l2;
import q1.m2;
import q1.s0;
import v.e1;
import v.f1;
import v.o0;
import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f1 f6927a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o0 f6930d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q1.f1 f6932f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public l2 f6933g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f1.a f6934h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public w0.n f6935i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final s1 f6936j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final s1 f6937k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f6938l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f6939m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f6940n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final s1 f6941o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final s1 f6942p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6943q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public e2.x f6944r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public w0.s f6945s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final c0 f6946t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a1.d f6947u;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e2.r f6928b = v.l0.f5960c;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a4.c f6929c = w.f7020h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f6931e = f0.l.s(new e2.x((String) null, 0, 7));

    public e0(f1 f1Var) {
        this.f6927a = f1Var;
        Boolean bool = Boolean.TRUE;
        this.f6936j = f0.l.s(bool);
        this.f6937k = f0.l.s(bool);
        this.f6938l = 0L;
        this.f6940n = 0L;
        this.f6941o = f0.l.s(null);
        this.f6942p = f0.l.s(null);
        this.f6943q = -1;
        this.f6944r = new e2.x((String) null, 0L, 7);
        this.f6946t = new c0(this, 1);
        this.f6947u = new a1.d(25, this);
    }

    /* JADX WARN: Code duplicated, block: B:80:0x0151  */
    public static final long a(e0 e0Var, e2.x xVar, long j5, boolean z2, boolean z3, e2.f0 f0Var, boolean z4) {
        e1 e1VarD;
        long j6;
        n nVar;
        n nVar2;
        boolean z5;
        f1.a aVar;
        m mVarE;
        m mVarE2;
        n nVar3;
        n nVarA;
        o0 o0Var = e0Var.f6930d;
        if (o0Var == null || (e1VarD = o0Var.d()) == null) {
            return y1.g0.f6768b;
        }
        e2.r rVar = e0Var.f6928b;
        long j7 = xVar.f2063b;
        y1.f fVar = xVar.f2062a;
        int i5 = y1.g0.f6769c;
        long jG = p3.j.g(rVar.b((int) (j7 >> 32)), e0Var.f6928b.b((int) (j7 & 4294967295L)));
        int iB = e1VarD.b(j5, false);
        int i6 = (z3 || z2) ? iB : (int) (jG >> 32);
        int i7 = (!z3 || z2) ? iB : (int) (jG & 4294967295L);
        w0.s sVar = e0Var.f6945s;
        int i8 = -1;
        if (z2 || sVar == null) {
            j6 = 4294967295L;
        } else {
            j6 = 4294967295L;
            int i9 = e0Var.f6943q;
            if (i9 != -1) {
                i8 = i9;
            }
        }
        y1.e0 e0Var2 = e1VarD.f5899a;
        if (z2) {
            nVar = null;
        } else {
            int i10 = (int) (jG >> 32);
            int i11 = (int) (jG & j6);
            nVar = new n(new m(p3.p.t(e0Var2, i10), i10, 1L), new m(p3.p.t(e0Var2, i11), i11, 1L), y1.g0.f(jG));
        }
        w0.s sVar2 = new w0.s(z3, nVar, new e2.l(i6, i7, i8, e0Var2));
        if (nVar != null && sVar != null && z3 == sVar.f6266b) {
            e2.l lVar = (e2.l) sVar.f6268d;
            if (i6 == lVar.f2033b && i7 == lVar.f2034c) {
                return j7;
            }
        }
        e0Var.f6945s = sVar2;
        e0Var.f6943q = iB;
        int i12 = f0Var.f2021d;
        Object obj = sVar2.f6268d;
        switch (i12) {
            case 6:
                e2.l lVar2 = (e2.l) obj;
                nVar2 = new n(lVar2.a(lVar2.f2033b), lVar2.a(lVar2.f2034c), sVar2.c() == 1);
                break;
            case 7:
                nVar2 = q.f.b(sVar2, o.f6986c);
                break;
            case 8:
                nVar2 = q.f.b(sVar2, o.f6985b);
                break;
            default:
                Object obj2 = sVar2.f6267c;
                nVar2 = (n) obj2;
                if (nVar2 != null) {
                    m mVar = nVar2.f6983b;
                    m mVar2 = nVar2.f6982a;
                    e2.l lVar3 = (e2.l) obj;
                    if (sVar2.f6266b) {
                        mVarE2 = q.f.e(sVar2, lVar3, mVar2);
                        mVarE = mVar;
                        mVar = mVar2;
                        mVar2 = mVarE2;
                    } else {
                        mVarE = q.f.e(sVar2, lVar3, mVar);
                        mVarE2 = mVarE;
                    }
                    if (!b4.l.a(mVarE2, mVar)) {
                        n nVar4 = new n(mVar2, mVarE, sVar2.c() == 1 || (sVar2.c() == 3 && mVar2.f6980b > mVarE.f6980b));
                        e2.l lVar4 = (e2.l) obj;
                        m mVar3 = nVar4.f6982a;
                        long j8 = mVar3.f6981c;
                        m mVar4 = nVar4.f6983b;
                        if (j8 != mVar4.f6981c) {
                            boolean z6 = nVar4.f6984c;
                            if ((z6 ? mVar3 : mVar4).f6980b == 0) {
                                if (((y1.e0) lVar4.f2036e).f6753a.f6743a.f6759d.length() == (z6 ? mVar4 : mVar3).f6980b) {
                                    String str = ((y1.e0) lVar4.f2036e).f6753a.f6743a.f6759d;
                                    nVar3 = (n) obj2;
                                    boolean z7 = sVar2.f6266b;
                                    if (nVar3 == null) {
                                    }
                                }
                            }
                        } else if (mVar3.f6980b == mVar4.f6980b) {
                            String str2 = ((y1.e0) lVar4.f2036e).f6753a.f6743a.f6759d;
                            nVar3 = (n) obj2;
                            boolean z8 = sVar2.f6266b;
                            if (nVar3 == null && str2.length() != 0) {
                                String str3 = ((y1.e0) lVar4.f2036e).f6753a.f6743a.f6759d;
                                int i13 = lVar4.f2033b;
                                int length = str3.length();
                                if (i13 == 0) {
                                    int iM = v.l0.m(0, str3);
                                    nVarA = z8 ? n.a(nVar4, q.f.i(mVar3, lVar4, iM), null, true, 2) : n.a(nVar4, null, q.f.i(mVar4, lVar4, iM), false, 1);
                                } else if (i13 == length) {
                                    int iP = v.l0.p(length, str3);
                                    nVarA = z8 ? n.a(nVar4, q.f.i(mVar3, lVar4, iP), null, false, 2) : n.a(nVar4, null, q.f.i(mVar4, lVar4, iP), true, 1);
                                } else {
                                    boolean z9 = nVar3.f6984c;
                                    int iP2 = z8 ^ z9 ? v.l0.p(i13, str3) : v.l0.m(i13, str3);
                                    nVarA = z8 ? n.a(nVar4, q.f.i(mVar3, lVar4, iP2), null, z9, 2) : n.a(nVar4, null, q.f.i(mVar4, lVar4, iP2), z9, 1);
                                }
                                nVar2 = nVarA;
                                break;
                            }
                        }
                        nVar2 = nVar4;
                    }
                } else {
                    nVar2 = q.f.b(sVar2, o.f6986c);
                }
                break;
        }
        long jG2 = p3.j.g(e0Var.f6928b.a(nVar2.f6982a.f6980b), e0Var.f6928b.a(nVar2.f6983b.f6980b));
        if (y1.g0.a(jG2, j7)) {
            return j7;
        }
        boolean z10 = y1.g0.f(jG2) != y1.g0.f(j7) && y1.g0.a(p3.j.g((int) (jG2 & j6), (int) (jG2 >> 32)), j7);
        boolean z11 = y1.g0.b(jG2) && y1.g0.b(j7);
        if (z4 && fVar.f6759d.length() > 0 && !z10 && !z11 && (aVar = e0Var.f6934h) != null) {
            ((f1.b) aVar).a();
        }
        e0Var.f6929c.c(c(fVar, jG2));
        if (!z4) {
            e0Var.p(!y1.g0.b(jG2));
        }
        o0 o0Var2 = e0Var.f6930d;
        if (o0Var2 != null) {
            o0Var2.f6022q.setValue(Boolean.valueOf(z4));
        }
        o0 o0Var3 = e0Var.f6930d;
        if (o0Var3 != null) {
            o0Var3.f6018m.setValue(Boolean.valueOf(!y1.g0.b(jG2) && p3.p.v(e0Var, true)));
        }
        o0 o0Var4 = e0Var.f6930d;
        if (o0Var4 == null) {
            z5 = false;
        } else {
            z5 = false;
            o0Var4.f6019n.setValue(Boolean.valueOf(!y1.g0.b(jG2) && p3.p.v(e0Var, false)));
        }
        o0 o0Var5 = e0Var.f6930d;
        if (o0Var5 == null) {
            return jG2;
        }
        if (y1.g0.b(jG2) && p3.p.v(e0Var, true)) {
            z5 = true;
        }
        o0Var5.f6020o.setValue(Boolean.valueOf(z5));
        return jG2;
    }

    public static e2.x c(y1.f fVar, long j5) {
        return new e2.x(fVar, j5, (y1.g0) null);
    }

    public final void b(boolean z2) {
        if (y1.g0.b(j().f2063b)) {
            return;
        }
        q1.f1 f1Var = this.f6932f;
        if (f1Var != null) {
            ((q1.h) f1Var).a(a.a.z(j()));
        }
        if (z2) {
            int iD = y1.g0.d(j().f2063b);
            this.f6929c.c(c(j().f2062a, p3.j.g(iD, iD)));
            n(v.f0.f5917d);
        }
    }

    public final void d() {
        if (y1.g0.b(j().f2063b)) {
            return;
        }
        q1.f1 f1Var = this.f6932f;
        if (f1Var != null) {
            ((q1.h) f1Var).a(a.a.z(j()));
        }
        y1.f fVarB = a.a.B(j(), j().f2062a.f6759d.length());
        y1.f fVarA = a.a.A(j(), j().f2062a.f6759d.length());
        y1.c cVar = new y1.c(fVarB);
        cVar.b(fVarA);
        y1.f fVarC = cVar.c();
        int iE = y1.g0.e(j().f2063b);
        this.f6929c.c(c(fVarC, p3.j.g(iE, iE)));
        n(v.f0.f5917d);
        this.f6927a.f5925e = true;
    }

    public final void e(x0.c cVar) {
        if (!y1.g0.b(j().f2063b)) {
            o0 o0Var = this.f6930d;
            e1 e1VarD = o0Var != null ? o0Var.d() : null;
            int iD = (cVar == null || e1VarD == null) ? y1.g0.d(j().f2063b) : this.f6928b.a(e1VarD.b(cVar.f6532a, true));
            this.f6929c.c(e2.x.a(j(), null, p3.j.g(iD, iD), 5));
        }
        n((cVar == null || j().f2062a.f6759d.length() <= 0) ? v.f0.f5917d : v.f0.f5919f);
        p(false);
    }

    public final void f(boolean z2) {
        w0.n nVar;
        o0 o0Var = this.f6930d;
        if (o0Var != null && !o0Var.b() && (nVar = this.f6935i) != null) {
            nVar.a(w0.h.f6239i);
        }
        this.f6944r = j();
        p(z2);
        n(v.f0.f5918e);
    }

    public final x0.c g() {
        return (x0.c) this.f6942p.getValue();
    }

    public final boolean h() {
        return ((Boolean) this.f6937k.getValue()).booleanValue();
    }

    public final long i(boolean z2) {
        e1 e1VarD;
        long j5;
        o0 o0Var = this.f6930d;
        if (o0Var == null || (e1VarD = o0Var.d()) == null) {
            return 9205357640488583168L;
        }
        y1.e0 e0Var = e1VarD.f5899a;
        o0 o0Var2 = this.f6930d;
        y1.f fVar = o0Var2 != null ? o0Var2.f6006a.f6098a : null;
        if (fVar == null) {
            return 9205357640488583168L;
        }
        if (!b4.l.a(fVar.f6759d, e0Var.f6753a.f6743a.f6759d)) {
            return 9205357640488583168L;
        }
        e2.x xVarJ = j();
        if (z2) {
            long j6 = xVarJ.f2063b;
            int i5 = y1.g0.f6769c;
            j5 = j6 >> 32;
        } else {
            long j7 = xVarJ.f2063b;
            int i6 = y1.g0.f6769c;
            j5 = j7 & 4294967295L;
        }
        int iB = this.f6928b.b((int) j5);
        boolean zF = y1.g0.f(j().f2063b);
        int iE = e0Var.e(iB);
        long j8 = e0Var.f6755c;
        y1.m mVar = e0Var.f6754b;
        if (iE >= mVar.f6794f) {
            return 9205357640488583168L;
        }
        boolean z3 = e0Var.a(((!z2 || zF) && (z2 || !zF)) ? Math.max(iB + (-1), 0) : iB) == e0Var.i(iB);
        ArrayList arrayList = mVar.f6796h;
        mVar.i(iB);
        y1.o oVar = (y1.o) arrayList.get(iB == ((y1.f) mVar.f6789a.f2449a).f6759d.length() ? p3.j.z(arrayList) : p3.p.l(iB, arrayList));
        y1.a aVar = oVar.f6799a;
        int iB2 = oVar.b(iB);
        z1.y yVar = aVar.f6706d;
        return p3.j.b(d4.a.l(z3 ? yVar.h(iB2, false) : yVar.i(iB2, false), 0.0f, (int) (j8 >> 32)), d4.a.l(mVar.b(iE), 0.0f, (int) (4294967295L & j8)));
    }

    public final e2.x j() {
        return (e2.x) this.f6931e.getValue();
    }

    public final void k() {
        l2 l2Var = this.f6933g;
        if ((l2Var != null ? ((s0) l2Var).f5360d : 0) != 1 || l2Var == null) {
            return;
        }
        s0 s0Var = (s0) l2Var;
        s0Var.f5360d = 2;
        ActionMode actionMode = s0Var.f5358b;
        if (actionMode != null) {
            actionMode.finish();
        }
        s0Var.f5358b = null;
    }

    /* JADX WARN: Code duplicated, block: B:70:0x015c  */
    public final void l() {
        int i5;
        Spanned spanned;
        char c5;
        int i6;
        q1.f1 f1Var = this.f6932f;
        if (f1Var != null) {
            ClipData primaryClip = ((q1.h) f1Var).f5215a.getPrimaryClip();
            y1.f fVar = null;
            if (primaryClip == null || primaryClip.getItemCount() <= 0) {
                fVar = null;
            } else {
                int i7 = 0;
                ClipData.Item itemAt = primaryClip.getItemAt(0);
                CharSequence text = itemAt != null ? itemAt.getText() : null;
                if (text != null) {
                    char c6 = 6;
                    if (text instanceof Spanned) {
                        Spanned spanned2 = (Spanned) text;
                        Annotation[] annotationArr = (Annotation[]) spanned2.getSpans(0, text.length(), Annotation.class);
                        ArrayList arrayList = new ArrayList();
                        b4.l.e("<this>", annotationArr);
                        int length = annotationArr.length - 1;
                        if (length >= 0) {
                            int i8 = 0;
                            while (true) {
                                Annotation annotation = annotationArr[i8];
                                if (b4.l.a(annotation.getKey(), "androidx.compose.text.SpanStyle")) {
                                    int spanStart = spanned2.getSpanStart(annotation);
                                    int spanEnd = spanned2.getSpanEnd(annotation);
                                    String value = annotation.getValue();
                                    Parcel parcelObtain = Parcel.obtain();
                                    byte[] bArrDecode = Base64.decode(value, i7);
                                    parcelObtain.unmarshall(bArrDecode, i7, bArrDecode.length);
                                    parcelObtain.setDataPosition(i7);
                                    long j5 = y0.u.f6694g;
                                    long j6 = j5;
                                    long jB = k2.m.f3421c;
                                    long jB2 = jB;
                                    d2.k kVar = null;
                                    d2.i iVar = null;
                                    d2.j jVar = null;
                                    String string = null;
                                    j2.a aVar = null;
                                    j2.n nVar = null;
                                    j2.j jVar2 = null;
                                    p0 p0Var = null;
                                    while (true) {
                                        if (parcelObtain.dataAvail() > 1) {
                                            byte b5 = parcelObtain.readByte();
                                            if (b5 != 1) {
                                                i5 = i7;
                                                spanned = spanned2;
                                                int i9 = 2;
                                                if (b5 != 2) {
                                                    i8 = i8;
                                                    if (b5 == 3) {
                                                        if (parcelObtain.dataAvail() < 4) {
                                                            c5 = 6;
                                                            break;
                                                        }
                                                        kVar = new d2.k(parcelObtain.readInt());
                                                        i7 = i5;
                                                        spanned2 = spanned;
                                                        i8 = i8;
                                                        c6 = 6;
                                                    } else if (b5 == 4) {
                                                        if (parcelObtain.dataAvail() < 1) {
                                                            c5 = 6;
                                                            break;
                                                        }
                                                        byte b6 = parcelObtain.readByte();
                                                        iVar = new d2.i((b6 != 0 && b6 == 1) ? 1 : i5);
                                                        i7 = i5;
                                                        spanned2 = spanned;
                                                        i8 = i8;
                                                        c6 = 6;
                                                    } else if (b5 != 5) {
                                                        c5 = 6;
                                                        if (b5 == 6) {
                                                            string = parcelObtain.readString();
                                                        } else if (b5 == 7) {
                                                            if (parcelObtain.dataAvail() < 5) {
                                                                break;
                                                            }
                                                            byte b7 = parcelObtain.readByte();
                                                            long j7 = b7 == 1 ? 4294967296L : b7 == 2 ? 8589934592L : 0L;
                                                            jB2 = k2.n.a(j7, 0L) ? k2.m.f3421c : d4.a.B(parcelObtain.readFloat(), j7);
                                                        } else if (b5 == 8) {
                                                            if (parcelObtain.dataAvail() < 4) {
                                                                break;
                                                            } else {
                                                                aVar = new j2.a(parcelObtain.readFloat());
                                                            }
                                                        } else if (b5 == 9) {
                                                            if (parcelObtain.dataAvail() < 8) {
                                                                break;
                                                            } else {
                                                                nVar = new j2.n(parcelObtain.readFloat(), parcelObtain.readFloat());
                                                            }
                                                        } else if (b5 == 10) {
                                                            if (parcelObtain.dataAvail() < 8) {
                                                                break;
                                                            }
                                                            j6 = parcelObtain.readLong();
                                                            int i10 = y0.u.f6695h;
                                                        } else if (b5 == 11) {
                                                            if (parcelObtain.dataAvail() < 4) {
                                                                break;
                                                            }
                                                            int i11 = parcelObtain.readInt();
                                                            int i12 = (i11 & 2) != 0 ? 1 : i5;
                                                            int i13 = (i11 & 1) != 0 ? 1 : i5;
                                                            j2.j jVar3 = j2.j.f3324d;
                                                            j2.j jVar4 = j2.j.f3323c;
                                                            if (i12 != 0 && i13 != 0) {
                                                                List listH = p3.j.H(jVar3, jVar4);
                                                                Integer numValueOf = Integer.valueOf(i5);
                                                                int size = listH.size();
                                                                for (int i14 = i5; i14 < size; i14++) {
                                                                    numValueOf = Integer.valueOf(numValueOf.intValue() | ((j2.j) listH.get(i14)).f3325a);
                                                                }
                                                                jVar2 = new j2.j(numValueOf.intValue());
                                                            } else if (i12 != 0) {
                                                                jVar2 = jVar3;
                                                            } else {
                                                                jVar2 = i13 != 0 ? jVar4 : j2.j.f3322b;
                                                            }
                                                        } else if (b5 == 12) {
                                                            if (parcelObtain.dataAvail() < 20) {
                                                                break;
                                                            }
                                                            long j8 = parcelObtain.readLong();
                                                            int i15 = y0.u.f6695h;
                                                            c6 = 6;
                                                            i7 = i5;
                                                            spanned2 = spanned;
                                                            i8 = i8;
                                                            p0Var = new p0(parcelObtain.readFloat(), j8, p3.j.b(parcelObtain.readFloat(), parcelObtain.readFloat()));
                                                        }
                                                        c6 = 6;
                                                        i7 = i5;
                                                        spanned2 = spanned;
                                                        i8 = i8;
                                                    } else {
                                                        if (parcelObtain.dataAvail() < 1) {
                                                            c5 = 6;
                                                            break;
                                                        }
                                                        byte b8 = parcelObtain.readByte();
                                                        if (b8 == 0) {
                                                            i9 = i5;
                                                        } else if (b8 == 1) {
                                                            i9 = 1;
                                                        } else if (b8 == 3) {
                                                            i9 = 3;
                                                        } else if (b8 != 2) {
                                                            i9 = i5;
                                                        }
                                                        jVar = new d2.j(i9);
                                                        i7 = i5;
                                                        spanned2 = spanned;
                                                        i8 = i8;
                                                        c6 = 6;
                                                    }
                                                } else {
                                                    if (parcelObtain.dataAvail() < 5) {
                                                        i8 = i8;
                                                        c5 = 6;
                                                        break;
                                                    }
                                                    byte b9 = parcelObtain.readByte();
                                                    long j9 = b9 == 1 ? 4294967296L : b9 == 2 ? 8589934592L : 0L;
                                                    jB = k2.n.a(j9, 0L) ? k2.m.f3421c : d4.a.B(parcelObtain.readFloat(), j9);
                                                    i7 = i5;
                                                    spanned2 = spanned;
                                                    i8 = i8;
                                                    c6 = 6;
                                                }
                                            } else if (parcelObtain.dataAvail() >= 8) {
                                                j5 = parcelObtain.readLong();
                                                int i16 = y0.u.f6695h;
                                            }
                                        }
                                        i5 = i7;
                                        spanned = spanned2;
                                        c5 = c6;
                                        i8 = i8;
                                        break;
                                    }
                                    arrayList.add(new y1.d(spanStart, spanEnd, new y1.a0(j5, jB, kVar, iVar, jVar, (d2.p) null, string, jB2, aVar, nVar, (f2.b) null, j6, jVar2, p0Var, 49152)));
                                    i6 = i8;
                                } else {
                                    i5 = i7;
                                    spanned = spanned2;
                                    c5 = c6;
                                    i6 = i8;
                                }
                                if (i6 == length) {
                                    break;
                                }
                                i8 = i6 + 1;
                                c6 = c5;
                                i7 = i5;
                                spanned2 = spanned;
                            }
                        }
                        fVar = new y1.f(text.toString(), arrayList, 4);
                    } else {
                        fVar = new y1.f(text.toString(), null, 6);
                    }
                }
            }
            if (fVar == null) {
                return;
            }
            y1.c cVar = new y1.c(a.a.B(j(), j().f2062a.f6759d.length()));
            cVar.b(fVar);
            y1.f fVarC = cVar.c();
            y1.f fVarA = a.a.A(j(), j().f2062a.f6759d.length());
            y1.c cVar2 = new y1.c(fVarC);
            cVar2.b(fVarA);
            y1.f fVarC2 = cVar2.c();
            int length2 = fVar.f6759d.length() + y1.g0.e(j().f2063b);
            this.f6929c.c(c(fVarC2, p3.j.g(length2, length2)));
            n(v.f0.f5917d);
            this.f6927a.f5925e = true;
        }
    }

    public final void m() {
        e2.x xVarC = c(j().f2062a, p3.j.g(0, j().f2062a.f6759d.length()));
        this.f6929c.c(xVarC);
        this.f6944r = e2.x.a(this.f6944r, null, xVarC.f2063b, 5);
        f(true);
    }

    public final void n(v.f0 f0Var) {
        o0 o0Var = this.f6930d;
        if (o0Var != null) {
            if (o0Var.a() == f0Var) {
                o0Var = null;
            }
            if (o0Var != null) {
                o0Var.f6016k.setValue(f0Var);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0080  */
    /* JADX WARN: Code duplicated, block: B:74:0x018a  */
    public final void o() {
        v.t tVar;
        x0.d dVar;
        float fE;
        n1.p pVarC;
        n1.p pVarC2;
        n1.p pVarC3;
        n1.p pVarC4;
        q1.f1 f1Var;
        if (h()) {
            o0 o0Var = this.f6930d;
            if (o0Var == null || ((Boolean) o0Var.f6022q.getValue()).booleanValue()) {
                v.t tVar2 = !y1.g0.b(j().f2063b) ? new v.t(this, 4) : null;
                boolean zB = y1.g0.b(j().f2063b);
                s1 s1Var = this.f6936j;
                v.t tVar3 = (zB || !((Boolean) s1Var.getValue()).booleanValue()) ? null : new v.t(this, 5);
                if (!((Boolean) s1Var.getValue()).booleanValue() || (f1Var = this.f6932f) == null) {
                    tVar = null;
                } else {
                    ClipDescription primaryClipDescription = ((q1.h) f1Var).f5215a.getPrimaryClipDescription();
                    if (primaryClipDescription != null ? primaryClipDescription.hasMimeType("text/*") : false) {
                        tVar = new v.t(this, 6);
                    } else {
                        tVar = null;
                    }
                }
                v.t tVar4 = y1.g0.c(j().f2063b) != j().f2062a.f6759d.length() ? new v.t(this, 7) : null;
                l2 l2Var = this.f6933g;
                if (l2Var != null) {
                    o0 o0Var2 = this.f6930d;
                    if (o0Var2 == null) {
                        l2Var = l2Var;
                        dVar = x0.d.f6533e;
                    } else {
                        o0 o0Var3 = o0Var2.f6021p ? null : o0Var2;
                        if (o0Var3 != null) {
                            int iB = this.f6928b.b((int) (j().f2063b >> 32));
                            int iB2 = this.f6928b.b((int) (j().f2063b & 4294967295L));
                            o0 o0Var4 = this.f6930d;
                            long jZ = 0;
                            long jZ2 = (o0Var4 == null || (pVarC4 = o0Var4.c()) == null) ? 0L : pVarC4.Z(i(true));
                            o0 o0Var5 = this.f6930d;
                            if (o0Var5 != null && (pVarC3 = o0Var5.c()) != null) {
                                jZ = pVarC3.Z(i(false));
                            }
                            o0 o0Var6 = this.f6930d;
                            float fE2 = 0.0f;
                            if (o0Var6 == null || (pVarC2 = o0Var6.c()) == null) {
                                fE = 0.0f;
                            } else {
                                e1 e1VarD = o0Var3.d();
                                fE = x0.c.e(pVarC2.Z(p3.j.b(0.0f, e1VarD != null ? e1VarD.f5899a.c(iB).f6535b : 0.0f)));
                            }
                            o0 o0Var7 = this.f6930d;
                            if (o0Var7 != null && (pVarC = o0Var7.c()) != null) {
                                e1 e1VarD2 = o0Var3.d();
                                fE2 = x0.c.e(pVarC.Z(p3.j.b(0.0f, e1VarD2 != null ? e1VarD2.f5899a.c(iB2).f6535b : 0.0f)));
                            }
                            dVar = new x0.d(Math.min(x0.c.d(jZ2), x0.c.d(jZ)), Math.min(fE, fE2), Math.max(x0.c.d(jZ2), x0.c.d(jZ)), (o0Var3.f6006a.f6104g.a() * 25) + Math.max(x0.c.e(jZ2), x0.c.e(jZ)));
                        } else {
                            l2Var = l2Var;
                            dVar = x0.d.f6533e;
                        }
                    }
                    s0 s0Var = (s0) l2Var;
                    s1.b bVar = s0Var.f5359c;
                    bVar.f5770b = dVar;
                    bVar.f5771c = tVar2;
                    bVar.f5773e = tVar3;
                    bVar.f5772d = tVar;
                    bVar.f5774f = tVar4;
                    ActionMode actionMode = s0Var.f5358b;
                    if (actionMode != null) {
                        actionMode.invalidate();
                    } else {
                        s0Var.f5360d = 1;
                        s0Var.f5358b = m2.f5265a.b(s0Var.f5357a, new s1.a(bVar), 1);
                    }
                }
            }
        }
    }

    public final void p(boolean z2) {
        o0 o0Var = this.f6930d;
        if (o0Var != null) {
            o0Var.f6017l.setValue(Boolean.valueOf(z2));
        }
        if (z2) {
            o();
        } else {
            k();
        }
    }
}
