package c0;

import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements l4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f888e;

    public /* synthetic */ h(int i5, Object obj) {
        this.f887d = i5;
        this.f888e = obj;
    }

    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        switch (this.f887d) {
            case 0:
                q.i iVar = (q.i) obj;
                p0.q qVar = (p0.q) this.f888e;
                if (iVar instanceof q.g) {
                    qVar.add(iVar);
                } else if (iVar instanceof q.h) {
                    qVar.remove(((q.h) iVar).f5081a);
                } else if (iVar instanceof q.d) {
                    qVar.add(iVar);
                } else if (iVar instanceof q.e) {
                    qVar.remove(((q.e) iVar).f5080a);
                } else if (iVar instanceof q.m) {
                    qVar.add(iVar);
                } else if (iVar instanceof q.n) {
                    qVar.remove(((q.n) iVar).f5085a);
                } else if (iVar instanceof q.l) {
                    qVar.remove(((q.l) iVar).f5083a);
                }
                break;
            case 1:
                ((q1.v1) this.f888e).f5436d.h(((Number) obj).floatValue());
                break;
            default:
                x.p pVar = (x.p) this.f888e;
                if (Build.VERSION.SDK_INT >= 34) {
                    x.g.f6470a.a(pVar.a(), pVar.f6477b);
                }
                break;
        }
        return p3.n.f5026a;
    }
}
