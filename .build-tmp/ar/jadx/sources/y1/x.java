package y1;

import android.util.Log;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import y0.l0;
import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends b4.m implements a4.c {
    public static final x A;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final x f6847f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final x f6848g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final x f6849h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final x f6850i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final x f6851j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final x f6852k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final x f6853l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final x f6854m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final x f6855n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final x f6856o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final x f6857p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final x f6858q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final x f6859r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final x f6860s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final x f6861t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final x f6862u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final x f6863v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final x f6864w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final x f6865x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final x f6866y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final x f6867z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6868e;

    static {
        int i5 = 1;
        f6847f = new x(i5, 0);
        f6848g = new x(i5, 1);
        f6849h = new x(i5, 2);
        f6850i = new x(i5, 3);
        f6851j = new x(i5, 4);
        f6852k = new x(i5, 5);
        f6853l = new x(i5, 6);
        f6854m = new x(i5, 7);
        f6855n = new x(i5, 8);
        f6856o = new x(i5, 9);
        f6857p = new x(i5, 10);
        f6858q = new x(i5, 11);
        f6859r = new x(i5, 12);
        f6860s = new x(i5, 13);
        f6861t = new x(i5, 14);
        f6862u = new x(i5, 15);
        f6863v = new x(i5, 16);
        f6864w = new x(i5, 17);
        f6865x = new x(i5, 18);
        f6866y = new x(i5, 19);
        f6867z = new x(i5, 20);
        A = new x(i5, 21);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(int i5, int i6) {
        super(i5);
        this.f6868e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        d dVar;
        k2.m mVar;
        x0.c cVar;
        k2.m mVar2;
        k2.m mVar3;
        j2.n nVar;
        int i5 = this.f6868e;
        x xVar = f6849h;
        x xVar2 = f6864w;
        x xVar3 = f6860s;
        int i6 = 0;
        r rVar = null;
        a0Var = null;
        a0 a0Var = null;
        p0Var = null;
        p0 p0Var = null;
        oVar = null;
        j2.o oVar = null;
        f0Var = null;
        f0 f0Var = null;
        f0Var = null;
        f0 f0Var2 = null;
        jVar = null;
        j jVar = null;
        kVar = null;
        k kVar = null;
        a0Var = null;
        a0 a0Var2 = null;
        rVar = null;
        switch (i5) {
            case 0:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list = (List) obj;
                Object obj2 = list.get(1);
                a2.h hVar = z.f6871a;
                Boolean bool = Boolean.FALSE;
                if (b4.l.a(obj2, bool) || obj2 == null) {
                    arrayList = null;
                } else {
                    List list2 = (List) obj2;
                    arrayList = new ArrayList(list2.size());
                    int size = list2.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        Object obj3 = list2.get(i7);
                        a2.h hVar2 = z.f6872b;
                        d dVar2 = (b4.l.a(obj3, Boolean.FALSE) || obj3 == null) ? null : (d) xVar.c(obj3);
                        b4.l.b(dVar2);
                        arrayList.add(dVar2);
                    }
                }
                Object obj4 = list.get(2);
                if (b4.l.a(obj4, bool) || obj4 == null) {
                    arrayList2 = null;
                } else {
                    List list3 = (List) obj4;
                    arrayList2 = new ArrayList(list3.size());
                    int size2 = list3.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Object obj5 = list3.get(i8);
                        a2.h hVar3 = z.f6872b;
                        d dVar3 = (b4.l.a(obj5, Boolean.FALSE) || obj5 == null) ? null : (d) xVar.c(obj5);
                        b4.l.b(dVar3);
                        arrayList2.add(dVar3);
                    }
                }
                Object obj6 = list.get(0);
                String str = obj6 != null ? (String) obj6 : null;
                b4.l.b(str);
                if (arrayList == null || arrayList.isEmpty()) {
                    arrayList = null;
                }
                if (arrayList2 == null || arrayList2.isEmpty()) {
                    arrayList2 = null;
                }
                Object obj7 = list.get(3);
                if (b4.l.a(obj7, bool) || obj7 == null) {
                    arrayList3 = null;
                } else {
                    List list4 = (List) obj7;
                    arrayList3 = new ArrayList(list4.size());
                    int size3 = list4.size();
                    while (i6 < size3) {
                        Object obj8 = list4.get(i6);
                        a2.h hVar4 = z.f6872b;
                        d dVar4 = (b4.l.a(obj8, Boolean.FALSE) || obj8 == null) ? null : (d) xVar.c(obj8);
                        b4.l.b(dVar4);
                        arrayList3.add(dVar4);
                        i6++;
                    }
                }
                return new f(str, arrayList, arrayList2, arrayList3);
            case 1:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list5 = (List) obj;
                ArrayList arrayList4 = new ArrayList(list5.size());
                int size4 = list5.size();
                while (i6 < size4) {
                    Object obj9 = list5.get(i6);
                    a2.h hVar5 = z.f6872b;
                    d dVar5 = (b4.l.a(obj9, Boolean.FALSE) || obj9 == null) ? null : (d) xVar.c(obj9);
                    b4.l.b(dVar5);
                    arrayList4.add(dVar5);
                    i6++;
                }
                return arrayList4;
            case 2:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list6 = (List) obj;
                Object obj10 = list6.get(0);
                h hVar6 = obj10 != null ? (h) obj10 : null;
                b4.l.b(hVar6);
                Object obj11 = list6.get(2);
                Integer num = obj11 != null ? (Integer) obj11 : null;
                b4.l.b(num);
                int iIntValue = num.intValue();
                Object obj12 = list6.get(3);
                Integer num2 = obj12 != null ? (Integer) obj12 : null;
                b4.l.b(num2);
                int iIntValue2 = num2.intValue();
                Object obj13 = list6.get(4);
                String str2 = obj13 != null ? (String) obj13 : null;
                b4.l.b(str2);
                switch (hVar6.ordinal()) {
                    case 0:
                        Object obj14 = list6.get(1);
                        a2.h hVar7 = z.f6877g;
                        if (!b4.l.a(obj14, Boolean.FALSE) && obj14 != null) {
                            rVar = (r) f6858q.c(obj14);
                        }
                        r rVar2 = rVar;
                        b4.l.b(rVar2);
                        return new d(rVar2, iIntValue, iIntValue2, str2);
                    case 1:
                        Object obj15 = list6.get(1);
                        a2.h hVar8 = z.f6878h;
                        if (!b4.l.a(obj15, Boolean.FALSE) && obj15 != null) {
                            a0Var2 = (a0) xVar3.c(obj15);
                        }
                        a0 a0Var3 = a0Var2;
                        b4.l.b(a0Var3);
                        return new d(a0Var3, iIntValue, iIntValue2, str2);
                    case 2:
                        Object obj16 = list6.get(1);
                        a2.h hVar9 = z.f6873c;
                        j0 j0Var = (b4.l.a(obj16, Boolean.FALSE) || obj16 == null) ? null : new j0((String) obj16);
                        b4.l.b(j0Var);
                        dVar = new d(j0Var, iIntValue, iIntValue2, str2);
                        break;
                    case 3:
                        Object obj17 = list6.get(1);
                        a2.h hVar10 = z.f6874d;
                        i0 i0Var = (b4.l.a(obj17, Boolean.FALSE) || obj17 == null) ? null : new i0((String) obj17);
                        b4.l.b(i0Var);
                        dVar = new d(i0Var, iIntValue, iIntValue2, str2);
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        Object obj18 = list6.get(1);
                        a2.h hVar11 = z.f6875e;
                        if (!b4.l.a(obj18, Boolean.FALSE) && obj18 != null) {
                            kVar = (k) f6854m.c(obj18);
                        }
                        k kVar2 = kVar;
                        b4.l.b(kVar2);
                        return new d(kVar2, iIntValue, iIntValue2, str2);
                    case 5:
                        Object obj19 = list6.get(1);
                        a2.h hVar12 = z.f6876f;
                        if (!b4.l.a(obj19, Boolean.FALSE) && obj19 != null) {
                            jVar = (j) f6851j.c(obj19);
                        }
                        j jVar2 = jVar;
                        b4.l.b(jVar2);
                        return new d(jVar2, iIntValue, iIntValue2, str2);
                    case 6:
                        Object obj20 = list6.get(1);
                        String str3 = obj20 != null ? (String) obj20 : null;
                        b4.l.b(str3);
                        return new d(str3, iIntValue, iIntValue2, str2);
                    default:
                        throw new i4.p();
                }
                return dVar;
            case 3:
                b4.l.c("null cannot be cast to non-null type kotlin.Float", obj);
                return new j2.a(((Float) obj).floatValue());
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list7 = (List) obj;
                Object obj21 = list7.get(0);
                String str4 = obj21 != null ? (String) obj21 : null;
                b4.l.b(str4);
                Object obj22 = list7.get(1);
                a2.h hVar13 = z.f6879i;
                if (!b4.l.a(obj22, Boolean.FALSE) && obj22 != null) {
                    f0Var2 = (f0) xVar2.c(obj22);
                }
                return new j(str4, f0Var2);
            case 5:
                if (b4.l.a(obj, Boolean.FALSE)) {
                    return new y0.u(y0.u.f6694g);
                }
                b4.l.c("null cannot be cast to non-null type kotlin.Int", obj);
                return new y0.u(l0.c(((Integer) obj).intValue()));
            case 6:
                b4.l.c("null cannot be cast to non-null type kotlin.Int", obj);
                return new d2.k(((Integer) obj).intValue());
            case 7:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list8 = (List) obj;
                Object obj23 = list8.get(0);
                String str5 = obj23 != null ? (String) obj23 : null;
                b4.l.b(str5);
                Object obj24 = list8.get(1);
                a2.h hVar14 = z.f6879i;
                if (!b4.l.a(obj24, Boolean.FALSE) && obj24 != null) {
                    f0Var = (f0) xVar2.c(obj24);
                }
                return new k(str5, f0Var);
            case 8:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list9 = (List) obj;
                ArrayList arrayList5 = new ArrayList(list9.size());
                int size5 = list9.size();
                while (i6 < size5) {
                    Object obj25 = list9.get(i6);
                    a2.h hVar15 = z.f6890t;
                    f2.a aVar = (b4.l.a(obj25, Boolean.FALSE) || obj25 == null) ? null : (f2.a) f6856o.c(obj25);
                    b4.l.b(aVar);
                    arrayList5.add(aVar);
                    i6++;
                }
                return new f2.b(arrayList5);
            case 9:
                b4.l.c("null cannot be cast to non-null type kotlin.String", obj);
                String str6 = (String) obj;
                f2.c.f2448a.getClass();
                Locale localeForLanguageTag = Locale.forLanguageTag(str6);
                if (b4.l.a(localeForLanguageTag.toLanguageTag(), "und")) {
                    Log.e("Locale", "The language tag " + str6 + " is not well-formed. Locale is resolved to Undetermined. Note that underscore '_' is not a valid subtag delimiter and must be replaced with '-'.");
                }
                return new f2.a(localeForLanguageTag);
            case 10:
                if (b4.l.a(obj, Boolean.FALSE)) {
                    return new x0.c(9205357640488583168L);
                }
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list10 = (List) obj;
                Object obj26 = list10.get(0);
                Float f5 = obj26 != null ? (Float) obj26 : null;
                b4.l.b(f5);
                float fFloatValue = f5.floatValue();
                Object obj27 = list10.get(1);
                Float f6 = obj27 != null ? (Float) obj27 : null;
                b4.l.b(f6);
                return new x0.c(p3.j.b(fFloatValue, f6.floatValue()));
            case 11:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list11 = (List) obj;
                Object obj28 = list11.get(0);
                j2.i iVar = obj28 != null ? (j2.i) obj28 : null;
                b4.l.b(iVar);
                int i9 = iVar.f3321a;
                Object obj29 = list11.get(1);
                j2.k kVar3 = obj29 != null ? (j2.k) obj29 : null;
                b4.l.b(kVar3);
                int i10 = kVar3.f3326a;
                Object obj30 = list11.get(2);
                k2.n[] nVarArr = k2.m.f3420b;
                a2.h hVar16 = z.f6871a;
                Boolean bool2 = Boolean.FALSE;
                b4.l.a(obj30, bool2);
                if (obj30 == null) {
                    mVar = null;
                } else if (obj30.equals(bool2)) {
                    mVar = new k2.m(k2.m.f3421c);
                } else {
                    List list12 = (List) obj30;
                    Object obj31 = list12.get(0);
                    Float f7 = obj31 != null ? (Float) obj31 : null;
                    b4.l.b(f7);
                    float fFloatValue2 = f7.floatValue();
                    Object obj32 = list12.get(1);
                    k2.n nVar2 = obj32 != null ? (k2.n) obj32 : null;
                    b4.l.b(nVar2);
                    mVar = new k2.m(d4.a.B(fFloatValue2, nVar2.f3423a));
                }
                b4.l.b(mVar);
                long j5 = mVar.f3422a;
                Object obj33 = list11.get(3);
                j2.o oVar2 = j2.o.f3331c;
                a2.h hVar17 = z.f6882l;
                if (!b4.l.a(obj33, bool2) && obj33 != null) {
                    oVar = (j2.o) f6863v.c(obj33);
                }
                return new r(i9, i10, j5, oVar, null, null, 0, Integer.MIN_VALUE, null);
            case 12:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list13 = (List) obj;
                Object obj34 = list13.get(0);
                int i11 = y0.u.f6695h;
                a2.h hVar18 = z.f6871a;
                Boolean bool3 = Boolean.FALSE;
                b4.l.a(obj34, bool3);
                y0.u uVar = obj34 != null ? b4.l.a(obj34, Boolean.FALSE) ? new y0.u(y0.u.f6694g) : new y0.u(l0.c(((Integer) obj34).intValue())) : null;
                b4.l.b(uVar);
                long j6 = uVar.f6696a;
                Object obj35 = list13.get(1);
                b4.l.a(obj35, bool3);
                if (obj35 == null) {
                    cVar = null;
                } else if (obj35.equals(bool3)) {
                    cVar = new x0.c(9205357640488583168L);
                } else {
                    List list14 = (List) obj35;
                    Object obj36 = list14.get(0);
                    Float f8 = obj36 != null ? (Float) obj36 : null;
                    b4.l.b(f8);
                    float fFloatValue3 = f8.floatValue();
                    Object obj37 = list14.get(1);
                    Float f9 = obj37 != null ? (Float) obj37 : null;
                    b4.l.b(f9);
                    cVar = new x0.c(p3.j.b(fFloatValue3, f9.floatValue()));
                }
                b4.l.b(cVar);
                long j7 = cVar.f6532a;
                Object obj38 = list13.get(2);
                Float f10 = obj38 != null ? (Float) obj38 : null;
                b4.l.b(f10);
                return new p0(f10.floatValue(), j6, j7);
            case 13:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list15 = (List) obj;
                Object obj39 = list15.get(0);
                int i12 = y0.u.f6695h;
                a2.h hVar19 = z.f6871a;
                Boolean bool4 = Boolean.FALSE;
                b4.l.a(obj39, bool4);
                y0.u uVar2 = obj39 != null ? obj39.equals(bool4) ? new y0.u(y0.u.f6694g) : new y0.u(l0.c(((Integer) obj39).intValue())) : null;
                b4.l.b(uVar2);
                long j8 = uVar2.f6696a;
                Object obj40 = list15.get(1);
                k2.n[] nVarArr2 = k2.m.f3420b;
                b4.l.a(obj40, bool4);
                if (obj40 == null) {
                    mVar2 = null;
                } else if (obj40.equals(bool4)) {
                    mVar2 = new k2.m(k2.m.f3421c);
                } else {
                    List list16 = (List) obj40;
                    Object obj41 = list16.get(0);
                    Float f11 = obj41 != null ? (Float) obj41 : null;
                    b4.l.b(f11);
                    float fFloatValue4 = f11.floatValue();
                    Object obj42 = list16.get(1);
                    k2.n nVar3 = obj42 != null ? (k2.n) obj42 : null;
                    b4.l.b(nVar3);
                    mVar2 = new k2.m(d4.a.B(fFloatValue4, nVar3.f3423a));
                }
                b4.l.b(mVar2);
                long j9 = mVar2.f3422a;
                Object obj43 = list15.get(2);
                d2.k kVar4 = d2.k.f1612e;
                a2.h hVar20 = z.f6883m;
                d2.k kVar5 = (b4.l.a(obj43, bool4) || obj43 == null) ? null : new d2.k(((Integer) obj43).intValue());
                Object obj44 = list15.get(3);
                d2.i iVar2 = obj44 != null ? (d2.i) obj44 : null;
                Object obj45 = list15.get(4);
                d2.j jVar3 = obj45 != null ? (d2.j) obj45 : null;
                Object obj46 = list15.get(6);
                String str7 = obj46 != null ? (String) obj46 : null;
                Object obj47 = list15.get(7);
                b4.l.a(obj47, bool4);
                if (obj47 == null) {
                    mVar3 = null;
                } else if (obj47.equals(bool4)) {
                    mVar3 = new k2.m(k2.m.f3421c);
                } else {
                    List list17 = (List) obj47;
                    Object obj48 = list17.get(0);
                    Float f12 = obj48 != null ? (Float) obj48 : null;
                    b4.l.b(f12);
                    float fFloatValue5 = f12.floatValue();
                    Object obj49 = list17.get(1);
                    k2.n nVar4 = obj49 != null ? (k2.n) obj49 : null;
                    b4.l.b(nVar4);
                    mVar3 = new k2.m(d4.a.B(fFloatValue5, nVar4.f3423a));
                }
                b4.l.b(mVar3);
                long j10 = mVar3.f3422a;
                Object obj50 = list15.get(8);
                a2.h hVar21 = z.f6884n;
                j2.a aVar2 = (b4.l.a(obj50, bool4) || obj50 == null) ? null : new j2.a(((Float) obj50).floatValue());
                Object obj51 = list15.get(9);
                a2.h hVar22 = z.f6881k;
                if (b4.l.a(obj51, bool4) || obj51 == null) {
                    nVar = null;
                } else {
                    List list18 = (List) obj51;
                    nVar = new j2.n(((Number) list18.get(0)).floatValue(), ((Number) list18.get(1)).floatValue());
                }
                Object obj52 = list15.get(10);
                f2.b bVar = f2.b.f2445f;
                a2.h hVar23 = z.f6889s;
                f2.b bVar2 = (b4.l.a(obj52, bool4) || obj52 == null) ? null : (f2.b) f6855n.c(obj52);
                Object obj53 = list15.get(11);
                b4.l.a(obj53, bool4);
                y0.u uVar3 = obj53 != null ? obj53.equals(bool4) ? new y0.u(y0.u.f6694g) : new y0.u(l0.c(((Integer) obj53).intValue())) : null;
                b4.l.b(uVar3);
                long j11 = uVar3.f6696a;
                Object obj54 = list15.get(12);
                a2.h hVar24 = z.f6880j;
                j2.j jVar4 = (b4.l.a(obj54, bool4) || obj54 == null) ? null : new j2.j(((Integer) obj54).intValue());
                Object obj55 = list15.get(13);
                p0 p0Var2 = p0.f6668d;
                a2.h hVar25 = z.f6885o;
                if (!b4.l.a(obj55, bool4) && obj55 != null) {
                    p0Var = (p0) f6859r.c(obj55);
                }
                return new a0(j8, j9, kVar5, iVar2, jVar3, (d2.p) null, str7, j10, aVar2, nVar, bVar2, j11, jVar4, p0Var, 49184);
            case 14:
                b4.l.c("null cannot be cast to non-null type kotlin.Int", obj);
                return new j2.j(((Integer) obj).intValue());
            case 15:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>", obj);
                List list19 = (List) obj;
                return new j2.n(((Number) list19.get(0)).floatValue(), ((Number) list19.get(1)).floatValue());
            case 16:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list20 = (List) obj;
                Object obj56 = list20.get(0);
                k2.n[] nVarArr3 = k2.m.f3420b;
                a2.h hVar26 = z.f6871a;
                Boolean bool5 = Boolean.FALSE;
                b4.l.a(obj56, bool5);
                x xVar4 = f6866y;
                k2.m mVar4 = obj56 != null ? (k2.m) xVar4.c(obj56) : null;
                b4.l.b(mVar4);
                long j12 = mVar4.f3422a;
                Object obj57 = list20.get(1);
                b4.l.a(obj57, bool5);
                k2.m mVar5 = obj57 != null ? (k2.m) xVar4.c(obj57) : null;
                b4.l.b(mVar5);
                return new j2.o(j12, mVar5.f3422a);
            case 17:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>", obj);
                List list21 = (List) obj;
                Object obj58 = list21.get(0);
                a2.h hVar27 = z.f6878h;
                Boolean bool6 = Boolean.FALSE;
                a0 a0Var4 = (b4.l.a(obj58, bool6) || obj58 == null) ? null : (a0) xVar3.c(obj58);
                Object obj59 = list21.get(1);
                a0 a0Var5 = (b4.l.a(obj59, bool6) || obj59 == null) ? null : (a0) xVar3.c(obj59);
                Object obj60 = list21.get(2);
                a0 a0Var6 = (b4.l.a(obj60, bool6) || obj60 == null) ? null : (a0) xVar3.c(obj60);
                Object obj61 = list21.get(3);
                if (!b4.l.a(obj61, bool6) && obj61 != null) {
                    a0Var = (a0) xVar3.c(obj61);
                }
                return new f0(a0Var4, a0Var5, a0Var6, a0Var);
            case 18:
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list22 = (List) obj;
                Object obj62 = list22.get(0);
                Integer num3 = obj62 != null ? (Integer) obj62 : null;
                b4.l.b(num3);
                int iIntValue3 = num3.intValue();
                Object obj63 = list22.get(1);
                Integer num4 = obj63 != null ? (Integer) obj63 : null;
                b4.l.b(num4);
                return new g0(p3.j.g(iIntValue3, num4.intValue()));
            case 19:
                if (b4.l.a(obj, Boolean.FALSE)) {
                    return new k2.m(k2.m.f3421c);
                }
                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>", obj);
                List list23 = (List) obj;
                Object obj64 = list23.get(0);
                Float f13 = obj64 != null ? (Float) obj64 : null;
                b4.l.b(f13);
                float fFloatValue6 = f13.floatValue();
                Object obj65 = list23.get(1);
                k2.n nVar5 = obj65 != null ? (k2.n) obj65 : null;
                b4.l.b(nVar5);
                return new k2.m(d4.a.B(fFloatValue6, nVar5.f3423a));
            case 20:
                String str8 = obj != null ? (String) obj : null;
                b4.l.b(str8);
                return new i0(str8);
            default:
                String str9 = obj != null ? (String) obj : null;
                b4.l.b(str9);
                return new j0(str9);
        }
    }
}
