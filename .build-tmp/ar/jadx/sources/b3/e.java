package b3;

import android.os.Build;
import androidx.lifecycle.k0;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends h1.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f f607i;

    public e(f fVar) {
        this.f607i = fVar;
    }

    @Override // h1.c
    public final void I(Throwable th) {
        this.f607i.f608a.e(th);
    }

    @Override // h1.c
    public final void J(w wVar) {
        f fVar = this.f607i;
        fVar.f610c = wVar;
        w wVar2 = fVar.f610c;
        j jVar = fVar.f608a;
        k0 k0Var = jVar.f621g;
        d dVar = jVar.f623i;
        Set<int[]> setA = Build.VERSION.SDK_INT >= 34 ? o.a() : a.a.x();
        a1.b bVar = new a1.b();
        bVar.f23d = k0Var;
        bVar.f24e = wVar2;
        bVar.f25f = dVar;
        if (!setA.isEmpty()) {
            for (int[] iArr : setA) {
                String str = new String(iArr, 0, iArr.length);
                bVar.y(str, 0, str.length(), 1, true, new r(str, 0));
            }
        }
        fVar.f609b = bVar;
        j jVar2 = fVar.f608a;
        jVar2.getClass();
        ArrayList arrayList = new ArrayList();
        jVar2.f615a.writeLock().lock();
        try {
            jVar2.f617c = 1;
            arrayList.addAll(jVar2.f616b);
            jVar2.f616b.clear();
            jVar2.f615a.writeLock().unlock();
            jVar2.f618d.post(new h(arrayList, jVar2.f617c, null));
        } catch (Throwable th) {
            jVar2.f615a.writeLock().unlock();
            throw th;
        }
    }
}
