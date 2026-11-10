package m4;

import b4.w;
import i4.s0;
import i4.v;
import i4.y;
import j1.x;
import v.l0;
import v.o0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements l4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f3838e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3839f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3840g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3841h;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, Object obj4, int i5) {
        this.f3837d = i5;
        this.f3838e = obj;
        this.f3839f = obj2;
        this.f3840g = obj3;
        this.f3841h = obj4;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00d6  */
    @Override // l4.e
    public final Object d(Object obj, t3.c cVar) {
        i iVar;
        j jVar;
        switch (this.f3837d) {
            case 0:
                if (cVar instanceof i) {
                    iVar = (i) cVar;
                    int i5 = iVar.f3836h;
                    if ((i5 & Integer.MIN_VALUE) != 0) {
                        iVar.f3836h = i5 - Integer.MIN_VALUE;
                    } else {
                        iVar = new i(this, cVar);
                    }
                } else {
                    iVar = new i(this, cVar);
                }
                Object obj2 = iVar.f3834f;
                int i6 = iVar.f3836h;
                if (i6 == 0) {
                    p3.j.O(obj2);
                    s0 s0Var = (s0) ((w) this.f3838e).f697d;
                    if (s0Var != null) {
                        s0Var.b(new x("Child of the scoped flow was cancelled", 2));
                        iVar.f3832d = this;
                        iVar.f3833e = obj;
                        iVar.f3836h = 1;
                        Object objQ = s0Var.q(iVar);
                        u3.a aVar = u3.a.f5838d;
                        if (objQ == aVar) {
                            return aVar;
                        }
                    }
                    jVar = this;
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj = iVar.f3833e;
                    jVar = iVar.f3832d;
                    p3.j.O(obj2);
                }
                ((w) jVar.f3838e).f697d = y.n((v) jVar.f3839f, null, new h((k) jVar.f3840g, (l4.e) jVar.f3841h, obj, null), 1);
                return p3.n.f5026a;
            case 1:
                q.i iVar2 = (q.i) obj;
                b4.u uVar = (b4.u) this.f3840g;
                b4.u uVar2 = (b4.u) this.f3839f;
                b4.u uVar3 = (b4.u) this.f3838e;
                boolean z2 = true;
                if (iVar2 instanceof q.m) {
                    uVar3.f695d++;
                } else if ((iVar2 instanceof q.n) || (iVar2 instanceof q.l)) {
                    uVar3.f695d--;
                } else if (iVar2 instanceof q.g) {
                    uVar2.f695d++;
                } else if (iVar2 instanceof q.h) {
                    uVar2.f695d--;
                } else if (iVar2 instanceof q.d) {
                    uVar.f695d++;
                } else if (iVar2 instanceof q.e) {
                    uVar.f695d--;
                }
                int i7 = uVar3.f695d;
                boolean z3 = false;
                boolean z4 = i7 > 0;
                boolean z5 = uVar2.f695d > 0;
                boolean z6 = uVar.f695d > 0;
                n.w wVar = (n.w) this.f3841h;
                if (wVar.f4037r != z4) {
                    wVar.f4037r = z4;
                    z3 = true;
                }
                if (wVar.f4038s != z5) {
                    wVar.f4038s = z5;
                    z3 = true;
                }
                if (wVar.f4039t != z6) {
                    wVar.f4039t = z6;
                } else {
                    z2 = z3;
                }
                if (z2) {
                    p1.f.l(wVar);
                }
                return p3.n.f5026a;
            default:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                e0 e0Var = (e0) this.f3840g;
                o0 o0Var = (o0) this.f3838e;
                if (zBooleanValue && o0Var.b()) {
                    l0.j((e2.y) this.f3839f, o0Var, e0Var.j(), (e2.n) this.f3841h, e0Var.f6928b);
                } else {
                    l0.g(o0Var);
                }
                return p3.n.f5026a;
        }
    }
}
