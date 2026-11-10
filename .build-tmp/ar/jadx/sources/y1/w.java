package y1;

import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.List;
import y0.l0;
import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b4.m implements a4.e {
    public static final w A;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f6825f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w f6826g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final w f6827h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final w f6828i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final w f6829j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final w f6830k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final w f6831l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final w f6832m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final w f6833n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final w f6834o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final w f6835p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final w f6836q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final w f6837r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final w f6838s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final w f6839t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final w f6840u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final w f6841v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final w f6842w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final w f6843x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final w f6844y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final w f6845z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6846e;

    static {
        int i5 = 2;
        f6825f = new w(i5, 0);
        f6826g = new w(i5, 1);
        f6827h = new w(i5, 2);
        f6828i = new w(i5, 3);
        f6829j = new w(i5, 4);
        f6830k = new w(i5, 5);
        f6831l = new w(i5, 6);
        f6832m = new w(i5, 7);
        f6833n = new w(i5, 8);
        f6834o = new w(i5, 9);
        f6835p = new w(i5, 10);
        f6836q = new w(i5, 11);
        f6837r = new w(i5, 12);
        f6838s = new w(i5, 13);
        f6839t = new w(i5, 14);
        f6840u = new w(i5, 15);
        f6841v = new w(i5, 16);
        f6842w = new w(i5, 17);
        f6843x = new w(i5, 18);
        f6844y = new w(i5, 19);
        f6845z = new w(i5, 20);
        A = new w(i5, 21);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(int i5, int i6) {
        super(i5);
        this.f6846e = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        h hVar;
        int i5 = 0;
        switch (this.f6846e) {
            case 0:
                o0.b bVar = (o0.b) obj;
                f fVar = (f) obj2;
                String str = fVar.f6759d;
                a2.h hVar2 = z.f6871a;
                List listA = fVar.a();
                a2.h hVar3 = z.f6871a;
                Object objA = z.a(listA, hVar3, bVar);
                Object obj3 = fVar.f6761f;
                if (obj3 == null) {
                    obj3 = q3.s.f5477d;
                }
                return p3.j.l(str, objA, z.a(obj3, hVar3, bVar), z.a(fVar.f6762g, hVar3, bVar));
            case 1:
                o0.b bVar2 = (o0.b) obj;
                List list = (List) obj2;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                while (i5 < size) {
                    arrayList.add(z.a((d) list.get(i5), z.f6872b, bVar2));
                    i5++;
                }
                return arrayList;
            case 2:
                o0.b bVar3 = (o0.b) obj;
                d dVar = (d) obj2;
                Object objA2 = dVar.f6739a;
                if (objA2 instanceof r) {
                    hVar = h.f6771d;
                } else if (objA2 instanceof a0) {
                    hVar = h.f6772e;
                } else if (objA2 instanceof j0) {
                    hVar = h.f6773f;
                } else if (objA2 instanceof i0) {
                    hVar = h.f6774g;
                } else if (objA2 instanceof k) {
                    hVar = h.f6775h;
                } else {
                    hVar = objA2 instanceof j ? h.f6776i : h.f6777j;
                }
                switch (hVar.ordinal()) {
                    case 0:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle", objA2);
                        objA2 = z.a((r) objA2, z.f6877g, bVar3);
                        break;
                    case 1:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle", objA2);
                        objA2 = z.a((a0) objA2, z.f6878h, bVar3);
                        break;
                    case 2:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation", objA2);
                        objA2 = z.a((j0) objA2, z.f6873c, bVar3);
                        break;
                    case 3:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation", objA2);
                        objA2 = z.a((i0) objA2, z.f6874d, bVar3);
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url", objA2);
                        objA2 = z.a((k) objA2, z.f6875e, bVar3);
                        break;
                    case 5:
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable", objA2);
                        objA2 = z.a((j) objA2, z.f6876f, bVar3);
                        break;
                    case 6:
                        a2.h hVar4 = z.f6871a;
                        break;
                    default:
                        throw new i4.p();
                }
                return p3.j.l(hVar, objA2, Integer.valueOf(dVar.f6740b), Integer.valueOf(dVar.f6741c), dVar.f6742d);
            case 3:
                return Float.valueOf(((j2.a) obj2).f3305a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                j jVar = (j) obj2;
                String str2 = jVar.f6784a;
                a2.h hVar5 = z.f6871a;
                return p3.j.l(str2, z.a(jVar.f6785b, z.f6879i, (o0.b) obj));
            case 5:
                long j5 = ((y0.u) obj2).f6696a;
                return j5 == 16 ? Boolean.FALSE : Integer.valueOf(l0.w(j5));
            case 6:
                return Integer.valueOf(((d2.k) obj2).f1615d);
            case 7:
                k kVar = (k) obj2;
                String str3 = kVar.f6787a;
                a2.h hVar6 = z.f6871a;
                return p3.j.l(str3, z.a(kVar.f6788b, z.f6879i, (o0.b) obj));
            case 8:
                o0.b bVar4 = (o0.b) obj;
                List list2 = ((f2.b) obj2).f2446d;
                ArrayList arrayList2 = new ArrayList(list2.size());
                int size2 = list2.size();
                while (i5 < size2) {
                    arrayList2.add(z.a((f2.a) list2.get(i5), z.f6890t, bVar4));
                    i5++;
                }
                return arrayList2;
            case 9:
                return ((f2.a) obj2).f2444a.toLanguageTag();
            case 10:
                long j6 = ((x0.c) obj2).f6532a;
                if (x0.c.b(j6, 9205357640488583168L)) {
                    return Boolean.FALSE;
                }
                Float fValueOf = Float.valueOf(x0.c.d(j6));
                a2.h hVar7 = z.f6871a;
                return p3.j.l(fValueOf, Float.valueOf(x0.c.e(j6)));
            case 11:
                o0.b bVar5 = (o0.b) obj;
                r rVar = (r) obj2;
                j2.i iVar = new j2.i(rVar.f6809a);
                a2.h hVar8 = z.f6871a;
                j2.k kVar2 = new j2.k(rVar.f6810b);
                Object objA3 = z.a(new k2.m(rVar.f6811c), z.f6887q, bVar5);
                j2.o oVar = rVar.f6812d;
                j2.o oVar2 = j2.o.f3331c;
                return p3.j.l(iVar, kVar2, objA3, z.a(oVar, z.f6882l, bVar5));
            case 12:
                o0.b bVar6 = (o0.b) obj;
                p0 p0Var = (p0) obj2;
                return p3.j.l(z.a(new y0.u(p0Var.f6669a), z.f6886p, bVar6), z.a(new x0.c(p0Var.f6670b), z.f6888r, bVar6), Float.valueOf(p0Var.f6671c));
            case 13:
                o0.b bVar7 = (o0.b) obj;
                a0 a0Var = (a0) obj2;
                y0.u uVar = new y0.u(a0Var.f6709a.b());
                y yVar = z.f6886p;
                Object objA4 = z.a(uVar, yVar, bVar7);
                k2.m mVar = new k2.m(a0Var.f6710b);
                y yVar2 = z.f6887q;
                Object objA5 = z.a(mVar, yVar2, bVar7);
                d2.k kVar3 = a0Var.f6711c;
                d2.k kVar4 = d2.k.f1612e;
                Object objA6 = z.a(kVar3, z.f6883m, bVar7);
                d2.i iVar2 = a0Var.f6712d;
                d2.j jVar2 = a0Var.f6713e;
                String str4 = a0Var.f6715g;
                Object objA7 = z.a(new k2.m(a0Var.f6716h), yVar2, bVar7);
                Object objA8 = z.a(a0Var.f6717i, z.f6884n, bVar7);
                Object objA9 = z.a(a0Var.f6718j, z.f6881k, bVar7);
                f2.b bVar8 = a0Var.f6719k;
                f2.b bVar9 = f2.b.f2445f;
                Object objA10 = z.a(bVar8, z.f6889s, bVar7);
                Object objA11 = z.a(new y0.u(a0Var.f6720l), yVar, bVar7);
                Object objA12 = z.a(a0Var.f6721m, z.f6880j, bVar7);
                p0 p0Var2 = a0Var.f6722n;
                p0 p0Var3 = p0.f6668d;
                return p3.j.l(objA4, objA5, objA6, iVar2, jVar2, -1, str4, objA7, objA8, objA9, objA10, objA11, objA12, z.a(p0Var2, z.f6885o, bVar7));
            case 14:
                return Integer.valueOf(((j2.j) obj2).f3325a);
            case 15:
                j2.n nVar = (j2.n) obj2;
                return p3.j.l(Float.valueOf(nVar.f3329a), Float.valueOf(nVar.f3330b));
            case 16:
                o0.b bVar10 = (o0.b) obj;
                j2.o oVar3 = (j2.o) obj2;
                k2.m mVar2 = new k2.m(oVar3.f3332a);
                y yVar3 = z.f6887q;
                return p3.j.l(z.a(mVar2, yVar3, bVar10), z.a(new k2.m(oVar3.f3333b), yVar3, bVar10));
            case 17:
                o0.b bVar11 = (o0.b) obj;
                f0 f0Var = (f0) obj2;
                a0 a0Var2 = f0Var.f6763a;
                a2.h hVar9 = z.f6878h;
                return p3.j.l(z.a(a0Var2, hVar9, bVar11), z.a(f0Var.f6764b, hVar9, bVar11), z.a(f0Var.f6765c, hVar9, bVar11), z.a(f0Var.f6766d, hVar9, bVar11));
            case 18:
                long j7 = ((g0) obj2).f6770a;
                int i6 = g0.f6769c;
                Integer numValueOf = Integer.valueOf((int) (j7 >> 32));
                a2.h hVar10 = z.f6871a;
                return p3.j.l(numValueOf, Integer.valueOf((int) (j7 & 4294967295L)));
            case 19:
                long j8 = ((k2.m) obj2).f3422a;
                if (k2.m.a(j8, k2.m.f3421c)) {
                    return Boolean.FALSE;
                }
                Float fValueOf2 = Float.valueOf(k2.m.c(j8));
                a2.h hVar11 = z.f6871a;
                return p3.j.l(fValueOf2, new k2.n(k2.m.b(j8)));
            case 20:
                String str5 = ((i0) obj2).f6783a;
                a2.h hVar12 = z.f6871a;
                return str5;
            default:
                String str6 = ((j0) obj2).f6786a;
                a2.h hVar13 = z.f6871a;
                return str6;
        }
    }
}
