package q0;

import b4.l;
import f0.o;
import f0.v0;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f5096a = new ArrayList();

    public j(Object obj) {
    }

    public final boolean a(int i5, v0 v0Var, Object obj) {
        ArrayList arrayList = v0Var.f2380a;
        if (arrayList == null) {
            b(i5, v0Var, null);
            return true;
        }
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            Object obj2 = arrayList.get(i6);
            if (obj2 instanceof f0.a) {
                if (obj2.equals(obj)) {
                    b(0, v0Var, obj2);
                    return true;
                }
            } else {
                if (!(obj2 instanceof v0)) {
                    throw new IllegalStateException(("Unexpected child source info " + obj2).toString());
                }
                if (a(i5, (v0) obj2, obj)) {
                    b(0, v0Var, obj2);
                    return true;
                }
            }
        }
        return false;
    }

    public final void b(int i5, v0 v0Var, Object obj) {
        this.f5096a.add(new b(i5, null, null));
    }

    public final void c(int i5, Object obj, v0 v0Var, Object obj2) {
        if (l.a(obj, o.f2281a)) {
            b(i5, v0Var, null);
        }
    }
}
