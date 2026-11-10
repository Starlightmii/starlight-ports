package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f305a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f306b = new HashMap();

    public static void a(Constructor constructor, u uVar) {
        try {
            b4.l.b(constructor.newInstance(uVar));
            throw new ClassCastException();
        } catch (IllegalAccessException e5) {
            throw new RuntimeException(e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException(e6);
        } catch (InvocationTargetException e7) {
            throw new RuntimeException(e7);
        }
    }

    public static final String b(String str) {
        StringBuilder sb = new StringBuilder();
        int iR = h4.g.R(str, ".", 0, false);
        if (iR >= 0) {
            int length = str.length();
            if (length < 0) {
                throw new OutOfMemoryError();
            }
            StringBuilder sb2 = new StringBuilder(length);
            int i5 = 0;
            do {
                sb2.append((CharSequence) str, i5, iR);
                sb2.append("_");
                i5 = iR + 1;
                if (iR >= str.length()) {
                    break;
                }
                iR = h4.g.R(str, ".", i5, false);
            } while (iR > 0);
            sb2.append((CharSequence) str, i5, str.length());
            str = sb2.toString();
            b4.l.d("toString(...)", str);
        }
        sb.append(str);
        sb.append("_LifecycleAdapter");
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0105  */
    /* JADX WARN: Code duplicated, block: B:66:0x0115  */
    /* JADX WARN: Code duplicated, block: B:72:0x0123 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x0125  */
    /* JADX WARN: Code duplicated, block: B:76:0x0139  */
    /* JADX WARN: Code duplicated, block: B:86:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x013e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00ff A[SYNTHETIC] */
    public static int c(Class cls) {
        Constructor<?> declaredConstructor;
        boolean zBooleanValue;
        b4.b bVarH;
        Class cls2;
        boolean z2;
        HashMap map = f305a;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i5 = 1;
        if (cls.getCanonicalName() != null) {
            ArrayList arrayList = null;
            try {
                Package r4 = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                String name = r4 != null ? r4.getName() : "";
                b4.l.b(name);
                if (name.length() != 0) {
                    b4.l.b(canonicalName);
                    canonicalName = canonicalName.substring(name.length() + 1);
                    b4.l.d("substring(...)", canonicalName);
                }
                b4.l.b(canonicalName);
                String strB = b(canonicalName);
                if (name.length() != 0) {
                    strB = name + '.' + strB;
                }
                declaredConstructor = Class.forName(strB).getDeclaredConstructor(cls);
                if (!declaredConstructor.isAccessible()) {
                    declaredConstructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                declaredConstructor = null;
            } catch (NoSuchMethodException e5) {
                throw new RuntimeException(e5);
            }
            HashMap map2 = f306b;
            if (declaredConstructor != null) {
                map2.put(cls, p3.j.G(declaredConstructor));
            } else {
                d dVar = d.f236c;
                HashMap map3 = dVar.f238b;
                Boolean bool = (Boolean) map3.get(cls);
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    try {
                        Method[] declaredMethods = cls.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i6 = 0;
                        while (true) {
                            if (i6 >= length) {
                                map3.put(cls, Boolean.FALSE);
                                zBooleanValue = false;
                                break;
                            }
                            if (((z) declaredMethods[i6].getAnnotation(z.class)) != null) {
                                dVar.a(cls, declaredMethods);
                                zBooleanValue = true;
                                break;
                            }
                            i6++;
                        }
                    } catch (NoClassDefFoundError e6) {
                        throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e6);
                    }
                }
                if (!zBooleanValue) {
                    Class superclass = cls.getSuperclass();
                    if (superclass != null && u.class.isAssignableFrom(superclass)) {
                        b4.l.b(superclass);
                        if (c(superclass) != 1) {
                            Object obj = map2.get(superclass);
                            b4.l.b(obj);
                            arrayList = new ArrayList((Collection) obj);
                            bVarH = b4.l.h(cls.getInterfaces());
                            while (bVarH.hasNext()) {
                                cls2 = (Class) bVarH.next();
                                if (cls2 == null && u.class.isAssignableFrom(cls2)) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    b4.l.b(cls2);
                                    if (c(cls2) == 1) {
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        Object obj2 = map2.get(cls2);
                                        b4.l.b(obj2);
                                        arrayList.addAll((Collection) obj2);
                                    }
                                }
                            }
                            if (arrayList != null) {
                                map2.put(cls, arrayList);
                            }
                        }
                    } else {
                        bVarH = b4.l.h(cls.getInterfaces());
                        while (bVarH.hasNext()) {
                            cls2 = (Class) bVarH.next();
                            if (cls2 == null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                b4.l.b(cls2);
                                if (c(cls2) == 1) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    Object obj3 = map2.get(cls2);
                                    b4.l.b(obj3);
                                    arrayList.addAll((Collection) obj3);
                                }
                            }
                        }
                        if (arrayList != null) {
                            map2.put(cls, arrayList);
                        }
                    }
                }
            }
            i5 = 2;
        }
        map.put(cls, Integer.valueOf(i5));
        return i5;
    }
}
