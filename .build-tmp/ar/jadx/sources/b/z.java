package b;

import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f369e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i0 f370f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(i0 i0Var, int i5) {
        super(1);
        this.f369e = i5;
        this.f370f = i0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        Object objPrevious;
        Object objPrevious2;
        switch (this.f369e) {
            case 0:
                b4.l.e("backEvent", (c) obj);
                i0 i0Var = this.f370f;
                q3.j jVar = i0Var.f341b;
                ListIterator listIterator = jVar.listIterator(jVar.a());
                if (listIterator.hasPrevious()) {
                    objPrevious = listIterator.previous();
                    ((c.d) objPrevious).getClass();
                } else {
                    objPrevious = null;
                }
                c.d dVar = (c.d) objPrevious;
                if (i0Var.f342c != null) {
                    i0Var.a();
                }
                i0Var.f342c = dVar;
                break;
            default:
                b4.l.e("backEvent", (c) obj);
                i0 i0Var2 = this.f370f;
                if (i0Var2.f342c == null) {
                    q3.j jVar2 = i0Var2.f341b;
                    ListIterator listIterator2 = jVar2.listIterator(jVar2.a());
                    if (listIterator2.hasPrevious()) {
                        objPrevious2 = listIterator2.previous();
                        ((c.d) objPrevious2).getClass();
                    } else {
                        objPrevious2 = null;
                    }
                }
                break;
        }
        return p3.n.f5026a;
    }
}
