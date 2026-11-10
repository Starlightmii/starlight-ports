package y1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Appendable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringBuilder f6733d = new StringBuilder(16);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f6734e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f6735f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f6736g = new ArrayList();

    public c(f fVar) {
        new ArrayList();
        b(fVar);
    }

    public final void a(a0 a0Var, int i5, int i6) {
        this.f6734e.add(new b(i5, i6, a0Var));
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c5) {
        this.f6733d.append(c5);
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence) {
        if (charSequence instanceof f) {
            b((f) charSequence);
            return this;
        }
        this.f6733d.append(charSequence);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.util.ArrayList] */
    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i5, int i6) {
        ?? arrayList;
        ?? arrayList2;
        boolean z2 = charSequence instanceof f;
        ?? r1 = this.f6733d;
        if (!z2) {
            r1.append(charSequence, i5, i6);
            return this;
        }
        f fVar = (f) charSequence;
        int length = r1.length();
        String str = fVar.f6759d;
        r1.append(str, i5, i6);
        List listB = g.b(fVar, i5, i6);
        if (listB != null) {
            int size = listB.size();
            for (int i7 = 0; i7 < size; i7++) {
                d dVar = (d) listB.get(i7);
                a((a0) dVar.f6739a, dVar.f6740b + length, dVar.f6741c + length);
            }
        }
        ?? r4 = 0;
        r4 = 0;
        if (i5 == i6 || (arrayList = fVar.f6761f) == 0) {
            arrayList = 0;
        } else if (i5 != 0 || i6 < str.length()) {
            ArrayList arrayList3 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i8 = 0; i8 < size2; i8++) {
                Object obj = arrayList.get(i8);
                d dVar2 = (d) obj;
                if (g.c(i5, i6, dVar2.f6740b, dVar2.f6741c)) {
                    arrayList3.add(obj);
                }
            }
            arrayList = new ArrayList(arrayList3.size());
            int size3 = arrayList3.size();
            for (int i9 = 0; i9 < size3; i9++) {
                d dVar3 = (d) arrayList3.get(i9);
                arrayList.add(new d(d4.a.m(dVar3.f6740b, i5, i6) - i5, d4.a.m(dVar3.f6741c, i5, i6) - i5, dVar3.f6739a));
            }
        }
        if (arrayList != 0) {
            int size4 = arrayList.size();
            for (int i10 = 0; i10 < size4; i10++) {
                d dVar4 = (d) arrayList.get(i10);
                this.f6735f.add(new b(dVar4.f6740b + length, dVar4.f6741c + length, (r) dVar4.f6739a));
            }
        }
        if (i5 != i6 && (arrayList2 = fVar.f6762g) != 0) {
            if (i5 != 0 || i6 < str.length()) {
                ArrayList arrayList4 = new ArrayList(arrayList2.size());
                int size5 = arrayList2.size();
                for (int i11 = 0; i11 < size5; i11++) {
                    Object obj2 = arrayList2.get(i11);
                    d dVar5 = (d) obj2;
                    if (g.c(i5, i6, dVar5.f6740b, dVar5.f6741c)) {
                        arrayList4.add(obj2);
                    }
                }
                arrayList2 = new ArrayList(arrayList4.size());
                int size6 = arrayList4.size();
                for (int i12 = 0; i12 < size6; i12++) {
                    d dVar6 = (d) arrayList4.get(i12);
                    arrayList2.add(new d(dVar6.f6739a, d4.a.m(dVar6.f6740b, i5, i6) - i5, d4.a.m(dVar6.f6741c, i5, i6) - i5, dVar6.f6742d));
                }
            }
            r4 = arrayList2;
        }
        if (r4 != 0) {
            int size7 = r4.size();
            for (int i13 = 0; i13 < size7; i13++) {
                d dVar7 = (d) r4.get(i13);
                this.f6736g.add(new b(dVar7.f6739a, dVar7.f6740b + length, dVar7.f6741c + length, dVar7.f6742d));
            }
        }
        return this;
    }

    public final void b(f fVar) {
        StringBuilder sb = this.f6733d;
        int length = sb.length();
        sb.append(fVar.f6759d);
        List list = fVar.f6760e;
        if (list != null) {
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                d dVar = (d) list.get(i5);
                a((a0) dVar.f6739a, dVar.f6740b + length, dVar.f6741c + length);
            }
        }
        List list2 = fVar.f6761f;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i6 = 0; i6 < size2; i6++) {
                d dVar2 = (d) list2.get(i6);
                this.f6735f.add(new b(dVar2.f6740b + length, dVar2.f6741c + length, (r) dVar2.f6739a));
            }
        }
        List list3 = fVar.f6762g;
        if (list3 != null) {
            int size3 = list3.size();
            for (int i7 = 0; i7 < size3; i7++) {
                d dVar3 = (d) list3.get(i7);
                this.f6736g.add(new b(dVar3.f6739a, dVar3.f6740b + length, dVar3.f6741c + length, dVar3.f6742d));
            }
        }
    }

    public final f c() {
        StringBuilder sb = this.f6733d;
        String string = sb.toString();
        ArrayList arrayList = this.f6734e;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            arrayList2.add(((b) arrayList.get(i5)).a(sb.length()));
        }
        if (arrayList2.isEmpty()) {
            arrayList2 = null;
        }
        ArrayList arrayList3 = this.f6735f;
        ArrayList arrayList4 = new ArrayList(arrayList3.size());
        int size2 = arrayList3.size();
        for (int i6 = 0; i6 < size2; i6++) {
            arrayList4.add(((b) arrayList3.get(i6)).a(sb.length()));
        }
        if (arrayList4.isEmpty()) {
            arrayList4 = null;
        }
        ArrayList arrayList5 = this.f6736g;
        ArrayList arrayList6 = new ArrayList(arrayList5.size());
        int size3 = arrayList5.size();
        for (int i7 = 0; i7 < size3; i7++) {
            arrayList6.add(((b) arrayList5.get(i7)).a(sb.length()));
        }
        return new f(string, arrayList2, arrayList4, arrayList6.isEmpty() ? null : arrayList6);
    }
}
