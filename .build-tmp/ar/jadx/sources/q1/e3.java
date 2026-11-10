package q1;

import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import io.github.andrea_lyz.assistrestore.R;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f3 f5176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f5177g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e3(f3 f3Var, a4.e eVar, int i5) {
        super(2);
        this.f5175e = i5;
        this.f5176f = f3Var;
        this.f5177g = eVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0021  */
    /* JADX WARN: Code duplicated, block: B:17:0x003c  */
    /* JADX WARN: Code duplicated, block: B:19:0x003f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0047  */
    /* JADX WARN: Code duplicated, block: B:22:0x004a  */
    /* JADX WARN: Code duplicated, block: B:24:0x004d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0052  */
    /* JADX WARN: Code duplicated, block: B:33:0x0063  */
    /* JADX WARN: Code duplicated, block: B:35:0x0066  */
    /* JADX WARN: Code duplicated, block: B:39:0x009b  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:53:0x00fb  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f3 f3Var;
        u uVar;
        Object tag;
        t3.c cVar;
        Set set;
        f0.v vVar;
        boolean zH;
        Object objK;
        boolean zH2;
        Object objK2;
        Object parent;
        View view;
        Object tag2;
        switch (this.f5175e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        AndroidCompositionLocals_androidKt.a(this.f5176f.f5202d, this.f5177g, pVar, 0);
                    }
                } else {
                    AndroidCompositionLocals_androidKt.a(this.f5176f.f5202d, this.f5177g, pVar, 0);
                }
                break;
            default:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar3 = (f0.v) pVar2;
                    if (vVar3.z()) {
                        vVar3.Q();
                    } else {
                        f3Var = this.f5176f;
                        uVar = f3Var.f5202d;
                        tag = uVar.getTag(R.id.inspection_slot_table_set);
                        cVar = null;
                        if ((tag instanceof Set) || ((tag instanceof c4.a) && !(tag instanceof c4.d))) {
                            set = null;
                        } else {
                            set = (Set) tag;
                        }
                        if (set == null) {
                            parent = uVar.getParent();
                            if (parent instanceof View) {
                                view = (View) parent;
                            } else {
                                view = null;
                            }
                            if (view != null) {
                                tag2 = view.getTag(R.id.inspection_slot_table_set);
                            } else {
                                tag2 = null;
                            }
                            if ((tag2 instanceof Set) || ((tag2 instanceof c4.a) && !(tag2 instanceof c4.d))) {
                                set = null;
                            } else {
                                set = (Set) tag2;
                            }
                        }
                        if (set != null) {
                            f0.v vVar4 = (f0.v) pVar2;
                            set.add(vVar4.v());
                            vVar4.f2370q = true;
                            vVar4.C = true;
                            vVar4.f2356c.b();
                            vVar4.H.b();
                            f0.w2 w2Var = vVar4.I;
                            f0.t2 t2Var = w2Var.f2390a;
                            w2Var.f2394e = t2Var.f2342m;
                            w2Var.f2395f = t2Var.f2343n;
                        }
                        vVar = (f0.v) pVar2;
                        zH = vVar.h(f3Var);
                        objK = vVar.K();
                        f0.h hVar = f0.o.f2281a;
                        if (zH || objK == hVar) {
                            objK = new d3(f3Var, cVar, 0);
                            vVar.f0(objK);
                        }
                        f0.l.e((a4.e) objK, vVar, uVar);
                        zH2 = vVar.h(f3Var);
                        objK2 = vVar.K();
                        if (zH2 || objK2 == hVar) {
                            objK2 = new d3(f3Var, cVar, 1);
                            vVar.f0(objK2);
                        }
                        f0.l.e((a4.e) objK2, vVar, uVar);
                        f0.l.a(q0.g.f5093a.a(set), n0.i.c(-1193460702, new e3(f3Var, this.f5177g, 0), vVar), vVar, 56);
                    }
                } else {
                    f3Var = this.f5176f;
                    uVar = f3Var.f5202d;
                    tag = uVar.getTag(R.id.inspection_slot_table_set);
                    cVar = null;
                    if (tag instanceof Set) {
                        set = null;
                    } else {
                        set = null;
                    }
                    if (set == null) {
                        parent = uVar.getParent();
                        if (parent instanceof View) {
                            view = (View) parent;
                        } else {
                            view = null;
                        }
                        if (view != null) {
                            tag2 = view.getTag(R.id.inspection_slot_table_set);
                        } else {
                            tag2 = null;
                        }
                        if (tag2 instanceof Set) {
                            set = null;
                        } else {
                            set = null;
                        }
                    }
                    if (set != null) {
                        f0.v vVar5 = (f0.v) pVar2;
                        set.add(vVar5.v());
                        vVar5.f2370q = true;
                        vVar5.C = true;
                        vVar5.f2356c.b();
                        vVar5.H.b();
                        f0.w2 w2Var2 = vVar5.I;
                        f0.t2 t2Var2 = w2Var2.f2390a;
                        w2Var2.f2394e = t2Var2.f2342m;
                        w2Var2.f2395f = t2Var2.f2343n;
                    }
                    vVar = (f0.v) pVar2;
                    zH = vVar.h(f3Var);
                    objK = vVar.K();
                    f0.h hVar2 = f0.o.f2281a;
                    if (zH) {
                        objK = new d3(f3Var, cVar, 0);
                        vVar.f0(objK);
                    } else {
                        objK = new d3(f3Var, cVar, 0);
                        vVar.f0(objK);
                    }
                    f0.l.e((a4.e) objK, vVar, uVar);
                    zH2 = vVar.h(f3Var);
                    objK2 = vVar.K();
                    if (zH2) {
                        objK2 = new d3(f3Var, cVar, 1);
                        vVar.f0(objK2);
                    } else {
                        objK2 = new d3(f3Var, cVar, 1);
                        vVar.f0(objK2);
                    }
                    f0.l.e((a4.e) objK2, vVar, uVar);
                    f0.l.a(q0.g.f5093a.a(set), n0.i.c(-1193460702, new e3(f3Var, this.f5177g, 0), vVar), vVar, 56);
                }
                break;
        }
        return p3.n.f5026a;
    }
}
