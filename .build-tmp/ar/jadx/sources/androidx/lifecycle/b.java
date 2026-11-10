package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f232a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f233b;

    public b(HashMap map) {
        this.f233b = map;
        for (Map.Entry entry : map.entrySet()) {
            o oVar = (o) entry.getValue();
            List arrayList = (List) this.f232a.get(oVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f232a.put(oVar, arrayList);
            }
            arrayList.add((c) entry.getKey());
        }
    }

    public static void a(List list, v vVar, o oVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                c cVar = (c) list.get(size);
                Method method = cVar.f235b;
                try {
                    int i5 = cVar.f234a;
                    if (i5 == 0) {
                        method.invoke(obj, null);
                    } else if (i5 == 1) {
                        method.invoke(obj, vVar);
                    } else if (i5 == 2) {
                        method.invoke(obj, vVar, oVar);
                    }
                } catch (IllegalAccessException e5) {
                    throw new RuntimeException(e5);
                } catch (InvocationTargetException e6) {
                    throw new RuntimeException("Failed to call observer method", e6.getCause());
                }
            }
        }
    }
}
