package b4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements f4.b, e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f686b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f687a;

    static {
        Map mapSingletonMap;
        List listH = p3.j.H(a4.a.class, a4.c.class, a4.e.class, a4.f.class, a4.g.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, a4.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, n0.b.class, a4.d.class);
        ArrayList arrayList = new ArrayList(q3.m.S(listH));
        int i5 = 0;
        int i6 = 0;
        for (Object obj : listH) {
            int i7 = i6 + 1;
            if (i6 < 0) {
                p3.j.N();
                throw null;
            }
            arrayList.add(new p3.f((Class) obj, Integer.valueOf(i6)));
            i6 = i7;
        }
        int size = arrayList.size();
        if (size == 0) {
            mapSingletonMap = q3.t.f5478d;
        } else if (size != 1) {
            mapSingletonMap = new LinkedHashMap(q3.v.F(arrayList.size()));
            int size2 = arrayList.size();
            while (i5 < size2) {
                Object obj2 = arrayList.get(i5);
                i5++;
                p3.f fVar = (p3.f) obj2;
                mapSingletonMap.put(fVar.f5014d, fVar.f5015e);
            }
        } else {
            p3.f fVar2 = (p3.f) arrayList.get(0);
            l.e("pair", fVar2);
            mapSingletonMap = Collections.singletonMap(fVar2.f5014d, fVar2.f5015e);
            l.d("singletonMap(...)", mapSingletonMap);
        }
        f686b = mapSingletonMap;
    }

    public f(Class cls) {
        this.f687a = cls;
    }

    @Override // b4.e
    public final Class a() {
        return this.f687a;
    }

    public final String b() {
        String strE;
        Class cls = this.f687a;
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            if (!cls.isArray()) {
                String strE2 = z.e(cls.getName());
                return strE2 == null ? cls.getSimpleName() : strE2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (strE = z.e(componentType.getName())) != null) {
                strConcat = strE.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return h4.g.V(simpleName, enclosingMethod.getName() + '$');
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor != null) {
            return h4.g.V(simpleName, enclosingConstructor.getName() + '$');
        }
        int iS = h4.g.S(simpleName, '$', 0, 6);
        if (iS == -1) {
            return simpleName;
        }
        String strSubstring = simpleName.substring(iS + 1, simpleName.length());
        l.d("substring(...)", strSubstring);
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f) && p3.j.y(this).equals(p3.j.y((f4.b) obj));
    }

    public final int hashCode() {
        return p3.j.y(this).hashCode();
    }

    public final String toString() {
        return this.f687a.toString() + " (Kotlin reflection is not available)";
    }
}
