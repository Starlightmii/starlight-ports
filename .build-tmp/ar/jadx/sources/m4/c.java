package m4;

import b4.z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t3.c[] f3817a = new t3.c[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b3.r f3818b = new b3.r("NULL", 1);

    public static final Object a(t3.h hVar, Object obj, Object obj2, a4.e eVar, t3.c cVar) {
        Object objInvoke;
        Object objL = n4.a.l(hVar, obj2);
        try {
            t tVar = new t(cVar, hVar);
            if (eVar == null) {
                objInvoke = p3.j.R(eVar, obj, tVar);
            } else {
                z.c(2, eVar);
                objInvoke = eVar.invoke(obj, tVar);
            }
            n4.a.g(hVar, objL);
            if (objInvoke == u3.a.f5838d) {
                b4.l.e("frame", cVar);
            }
            return objInvoke;
        } catch (Throwable th) {
            n4.a.g(hVar, objL);
            throw th;
        }
    }
}
