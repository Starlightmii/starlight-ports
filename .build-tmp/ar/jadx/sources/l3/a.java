package l3;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import i4.p;
import io.github.andrea_lyz.assistrestore.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile a f3505d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f3506e = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f3509c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f3508b = new HashSet();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f3507a = new HashMap();

    public a(Context context) {
        this.f3509c = context.getApplicationContext();
    }

    public static a c(Context context) {
        if (f3505d == null) {
            synchronized (f3506e) {
                try {
                    if (f3505d == null) {
                        f3505d = new a(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f3505d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.f3509c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = this.f3508b;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (b.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e5) {
                throw new p(e5);
            }
        }
    }

    public final Object b(Class cls, HashSet hashSet) {
        Object objB;
        HashMap map = this.f3507a;
        if (d4.a.y()) {
            try {
                d4.a.g(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
        }
        if (map.containsKey(cls)) {
            objB = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                b bVar = (b) cls.getDeclaredConstructor(null).newInstance(null);
                List<Class> listA = bVar.a();
                if (!listA.isEmpty()) {
                    for (Class cls2 : listA) {
                        if (!map.containsKey(cls2)) {
                            b(cls2, hashSet);
                        }
                    }
                }
                objB = bVar.b(this.f3509c);
                hashSet.remove(cls);
                map.put(cls, objB);
            } catch (Throwable th2) {
                throw new p(th2);
            }
        }
        Trace.endSection();
        return objB;
    }
}
