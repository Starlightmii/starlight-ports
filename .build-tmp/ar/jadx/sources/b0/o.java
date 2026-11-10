package b0;

import android.content.Context;
import android.view.ViewGroup;
import io.github.andrea_lyz.assistrestore.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f434d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f435e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f436f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a2.h f437g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f438h;

    public o(Context context) {
        super(context);
        this.f434d = 5;
        ArrayList arrayList = new ArrayList();
        this.f435e = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.f436f = arrayList2;
        this.f437g = new a2.h(1);
        setClipChildren(false);
        q qVar = new q(context);
        addView(qVar);
        arrayList.add(qVar);
        arrayList2.add(qVar);
        this.f438h = 1;
        setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    public final q a(p pVar) {
        a2.h hVar = this.f437g;
        LinkedHashMap linkedHashMap = (LinkedHashMap) hVar.f49e;
        LinkedHashMap linkedHashMap2 = (LinkedHashMap) hVar.f49e;
        LinkedHashMap linkedHashMap3 = (LinkedHashMap) hVar.f50f;
        q qVar = (q) linkedHashMap.get(pVar);
        if (qVar != null) {
            return qVar;
        }
        ArrayList arrayList = this.f436f;
        b4.l.e("<this>", arrayList);
        q qVar2 = (q) (arrayList.isEmpty() ? null : arrayList.remove(0));
        if (qVar2 == null) {
            int i5 = this.f438h;
            ArrayList arrayList2 = this.f435e;
            if (i5 > p3.j.z(arrayList2)) {
                qVar2 = new q(getContext());
                addView(qVar2);
                arrayList2.add(qVar2);
            } else {
                qVar2 = (q) arrayList2.get(this.f438h);
                p pVar2 = (p) linkedHashMap3.get(qVar2);
                if (pVar2 != null) {
                    pVar2.b0();
                    q qVar3 = (q) linkedHashMap2.get(pVar2);
                    if (qVar3 != null) {
                    }
                    linkedHashMap2.remove(pVar2);
                    qVar2.c();
                }
            }
            int i6 = this.f438h;
            if (i6 < this.f434d - 1) {
                this.f438h = i6 + 1;
            } else {
                this.f438h = 0;
            }
        }
        linkedHashMap2.put(pVar, qVar2);
        linkedHashMap3.put(qVar2, pVar);
        return qVar2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
    }
}
