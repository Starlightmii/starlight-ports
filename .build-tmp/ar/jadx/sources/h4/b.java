package h4;

import b4.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p3.j;
import q3.m;
import q3.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends a.a {
    public static String P(String str) {
        List listG;
        Comparable comparable;
        String strSubstring;
        l.e("<this>", str);
        a aVar = new a(str);
        if (aVar.hasNext()) {
            Object next = aVar.next();
            if (aVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (aVar.hasNext()) {
                    arrayList.add(aVar.next());
                }
                listG = arrayList;
            } else {
                listG = j.G(next);
            }
        } else {
            listG = s.f5477d;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listG) {
            if (!g.T((String) obj)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(m.S(arrayList2));
        int size = arrayList2.size();
        int i5 = 0;
        int i6 = 0;
        while (i6 < size) {
            Object obj2 = arrayList2.get(i6);
            i6++;
            String str2 = (String) obj2;
            int length = str2.length();
            int length2 = 0;
            while (true) {
                if (length2 >= length) {
                    length2 = -1;
                    break;
                }
                if (!h1.c.B(str2.charAt(length2))) {
                    break;
                }
                length2++;
            }
            if (length2 == -1) {
                length2 = str2.length();
            }
            arrayList3.add(Integer.valueOf(length2));
        }
        Iterator it = arrayList3.iterator();
        if (it.hasNext()) {
            comparable = (Comparable) it.next();
            while (it.hasNext()) {
                Comparable comparable2 = (Comparable) it.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        } else {
            comparable = null;
        }
        Integer num = (Integer) comparable;
        int iIntValue = num != null ? num.intValue() : 0;
        int length3 = str.length();
        listG.size();
        int iZ = j.z(listG);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : listG) {
            int i7 = i5 + 1;
            if (i5 < 0) {
                j.N();
                throw null;
            }
            String str3 = (String) obj3;
            if ((i5 == 0 || i5 == iZ) && g.T(str3)) {
                strSubstring = null;
            } else {
                l.e("<this>", str3);
                if (iIntValue < 0) {
                    throw new IllegalArgumentException(("Requested character count " + iIntValue + " is less than zero.").toString());
                }
                int length4 = str3.length();
                if (iIntValue <= length4) {
                    length4 = iIntValue;
                }
                strSubstring = str3.substring(length4);
                l.d("substring(...)", strSubstring);
            }
            if (strSubstring != null) {
                arrayList4.add(strSubstring);
            }
            i5 = i7;
        }
        StringBuilder sb = new StringBuilder(length3);
        q3.l.Z(arrayList4, sb, null, 124);
        return sb.toString();
    }
}
