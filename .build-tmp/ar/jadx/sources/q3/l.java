package q3;

import c0.g1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class l extends q {
    public static boolean V(Iterable iterable, Object obj) {
        int iIndexOf;
        b4.l.e("<this>", iterable);
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        if (iterable instanceof List) {
            iIndexOf = ((List) iterable).indexOf(obj);
        } else {
            int i5 = 0;
            for (Object obj2 : iterable) {
                if (i5 < 0) {
                    p3.j.N();
                    throw null;
                }
                if (b4.l.a(obj, obj2)) {
                    iIndexOf = i5;
                } else {
                    i5++;
                }
            }
            iIndexOf = -1;
        }
        return iIndexOf >= 0;
    }

    public static Object W(List list) {
        b4.l.e("<this>", list);
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object X(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final void Y(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, a4.c cVar) {
        b4.l.e("<this>", iterable);
        sb.append(charSequence2);
        Iterator it = iterable.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i5++;
            if (i5 > 1) {
                sb.append(charSequence);
            }
            if (cVar != null) {
                sb.append((CharSequence) cVar.c(next));
            } else {
                if (next != null ? next instanceof CharSequence : true) {
                    sb.append((CharSequence) next);
                } else if (next instanceof Character) {
                    sb.append(((Character) next).charValue());
                } else {
                    sb.append((CharSequence) next.toString());
                }
            }
        }
        sb.append(charSequence3);
    }

    public static /* synthetic */ void Z(List list, StringBuilder sb, g1 g1Var, int i5) {
        if ((i5 & 64) != 0) {
            g1Var = null;
        }
        Y(list, sb, "\n", "", "", "...", g1Var);
    }

    public static String a0(Collection collection, String str, String str2, a4.c cVar, int i5) {
        String str3 = (i5 & 2) != 0 ? "" : str;
        String str4 = (i5 & 4) != 0 ? "" : str2;
        if ((i5 & 32) != 0) {
            cVar = null;
        }
        b4.l.e("<this>", collection);
        StringBuilder sb = new StringBuilder();
        Y(collection, sb, ", ", str3, str4, "...", cVar);
        return sb.toString();
    }

    public static Object b0(List list) {
        b4.l.e("<this>", list);
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(p3.j.z(list));
    }

    public static Object c0(List list) {
        b4.l.e("<this>", list);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static ArrayList d0(Collection collection, Object obj) {
        b4.l.e("<this>", collection);
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList e0(Collection collection, List list) {
        b4.l.e("<this>", collection);
        b4.l.e("elements", list);
        ArrayList arrayList = new ArrayList(list.size() + collection.size());
        arrayList.addAll(collection);
        arrayList.addAll(list);
        return arrayList;
    }

    public static List f0(Iterable iterable) {
        ArrayList arrayListG0;
        b4.l.e("<this>", iterable);
        boolean z2 = iterable instanceof Collection;
        s sVar = s.f5477d;
        if (z2) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return sVar;
            }
            if (size != 1) {
                return g0(collection);
            }
            return p3.j.G(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
        }
        if (z2) {
            arrayListG0 = g0((Collection) iterable);
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            arrayListG0 = arrayList;
        }
        int size2 = arrayListG0.size();
        if (size2 != 0) {
            return size2 != 1 ? arrayListG0 : p3.j.G(arrayListG0.get(0));
        }
        return sVar;
    }

    public static ArrayList g0(Collection collection) {
        b4.l.e("<this>", collection);
        return new ArrayList(collection);
    }
}
