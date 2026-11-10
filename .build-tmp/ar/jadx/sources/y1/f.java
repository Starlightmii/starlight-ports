package y1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements CharSequence {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6759d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f6760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f6761f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f6762g;

    static {
        a2.h hVar = z.f6871a;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public f(String str, ArrayList arrayList, int i5) {
        List list = (i5 & 2) != 0 ? q3.s.f5477d : arrayList;
        this(str, list.isEmpty() ? null : list, null, null);
    }

    public f(String str, List list, List list2, List list3) {
        List listAsList;
        this.f6759d = str;
        this.f6760e = list;
        this.f6761f = list2;
        this.f6762g = list3;
        if (list2 != null) {
            e eVar = new e();
            int i5 = 0;
            if (list2.size() <= 1) {
                listAsList = q3.l.f0(list2);
            } else {
                Object[] array = list2.toArray(new Object[0]);
                b4.l.e("<this>", array);
                if (array.length > 1) {
                    Arrays.sort(array, eVar);
                }
                listAsList = Arrays.asList(array);
                b4.l.d("asList(...)", listAsList);
            }
            int size = listAsList.size();
            int i6 = -1;
            while (i5 < size) {
                d dVar = (d) listAsList.get(i5);
                int i7 = dVar.f6740b;
                int i8 = dVar.f6741c;
                if (i7 < i6) {
                    throw new IllegalArgumentException("ParagraphStyle should not overlap");
                }
                if (i8 > this.f6759d.length()) {
                    throw new IllegalArgumentException(("ParagraphStyle range [" + dVar.f6740b + ", " + i8 + ") is out of boundary").toString());
                }
                i5++;
                i6 = i8;
            }
        }
    }

    public final List a() {
        List list = this.f6760e;
        return list == null ? q3.s.f5477d : list;
    }

    @Override // java.lang.CharSequence
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final f subSequence(int i5, int i6) {
        if (i5 > i6) {
            throw new IllegalArgumentException(("start (" + i5 + ") should be less or equal to end (" + i6 + ')').toString());
        }
        String str = this.f6759d;
        if (i5 == 0 && i6 == str.length()) {
            return this;
        }
        String strSubstring = str.substring(i5, i6);
        b4.l.d("this as java.lang.String…ing(startIndex, endIndex)", strSubstring);
        return new f(strSubstring, g.a(this.f6760e, i5, i6), g.a(this.f6761f, i5, i6), g.a(this.f6762g, i5, i6));
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i5) {
        return this.f6759d.charAt(i5);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return b4.l.a(this.f6759d, fVar.f6759d) && b4.l.a(this.f6760e, fVar.f6760e) && b4.l.a(this.f6761f, fVar.f6761f) && b4.l.a(this.f6762g, fVar.f6762g);
    }

    public final int hashCode() {
        int iHashCode = this.f6759d.hashCode() * 31;
        List list = this.f6760e;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        List list2 = this.f6761f;
        int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List list3 = this.f6762g;
        return iHashCode3 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f6759d.length();
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f6759d;
    }
}
