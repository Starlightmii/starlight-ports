package g4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import q3.s;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends i {
    public static List T(f fVar) {
        Iterator it = fVar.iterator();
        if (!it.hasNext()) {
            return s.f5477d;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return p3.j.G(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
