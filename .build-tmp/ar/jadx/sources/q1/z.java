package q1;

import android.R;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {
    public static final void a(x2.h hVar, w1.n nVar) {
        boolean zH = m0.h(nVar);
        LinkedHashMap linkedHashMap = nVar.f6318d.f6308d;
        if (zH) {
            Object obj = linkedHashMap.get(w1.h.f6303v);
            if (obj == null) {
                obj = null;
            }
            w1.a aVar = (w1.a) obj;
            if (aVar != null) {
                hVar.a(new x2.c(null, R.id.accessibilityActionPageUp, aVar.f6272a, null));
            }
            Object obj2 = linkedHashMap.get(w1.h.f6305x);
            if (obj2 == null) {
                obj2 = null;
            }
            w1.a aVar2 = (w1.a) obj2;
            if (aVar2 != null) {
                hVar.a(new x2.c(null, R.id.accessibilityActionPageDown, aVar2.f6272a, null));
            }
            Object obj3 = linkedHashMap.get(w1.h.f6304w);
            if (obj3 == null) {
                obj3 = null;
            }
            w1.a aVar3 = (w1.a) obj3;
            if (aVar3 != null) {
                hVar.a(new x2.c(null, R.id.accessibilityActionPageLeft, aVar3.f6272a, null));
            }
            Object obj4 = linkedHashMap.get(w1.h.f6306y);
            if (obj4 == null) {
                obj4 = null;
            }
            w1.a aVar4 = (w1.a) obj4;
            if (aVar4 != null) {
                hVar.a(new x2.c(null, R.id.accessibilityActionPageRight, aVar4.f6272a, null));
            }
        }
    }
}
