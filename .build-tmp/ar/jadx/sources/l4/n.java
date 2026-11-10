package l4;

import android.graphics.Rect;
import android.view.ScrollCaptureSession;
import f0.h2;
import f0.i1;
import f0.i2;
import i4.s0;
import io.github.libxposed.service.IXposedService;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import m.h0;
import p.o0;
import p.q1;
import p.v1;
import q1.r0;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3560d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3561e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3562f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3563g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f3564h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f3565i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public n(a4.c cVar, AtomicReference atomicReference, a4.e eVar, t3.c cVar2) {
        super(2, cVar2);
        this.f3560d = 5;
        this.f3562f = (b4.m) cVar;
        this.f3563g = atomicReference;
        this.f3564h = eVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Object obj, Object obj2, Object obj3, Object obj4, t3.c cVar, int i5) {
        super(2, cVar);
        this.f3560d = i5;
        this.f3565i = obj;
        this.f3562f = obj2;
        this.f3563g = obj3;
        this.f3564h = obj4;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Object obj, Object obj2, Object obj3, t3.c cVar, int i5) {
        super(2, cVar);
        this.f3560d = i5;
        this.f3562f = obj;
        this.f3563g = obj2;
        this.f3564h = obj3;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a4.c, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3560d) {
            case 0:
                n nVar = new n((d) this.f3562f, (d0) this.f3563g, (Float) this.f3564h, cVar, 0);
                nVar.f3565i = obj;
                return nVar;
            case 1:
                return new n((a0) this.f3565i, (d) this.f3562f, (d0) this.f3563g, (Float) this.f3564h, cVar, 1);
            case 2:
                return new n(this.f3565i, (m.c) this.f3562f, (i1) this.f3563g, (i1) this.f3564h, cVar, 2);
            case 3:
                n nVar2 = new n((p.i) this.f3562f, (v1) this.f3563g, (p.d) this.f3564h, cVar, 3);
                nVar2.f3565i = obj;
                return nVar2;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                n nVar3 = new n((j1.d0) this.f3562f, (a4.f) this.f3563g, (v.j) this.f3564h, cVar, 4);
                nVar3.f3565i = obj;
                return nVar3;
            case 5:
                n nVar4 = new n((b4.m) this.f3562f, (AtomicReference) this.f3563g, (a4.e) this.f3564h, cVar);
                nVar4.f3565i = obj;
                return nVar4;
            case 6:
                return new n((v1.c) this.f3565i, (ScrollCaptureSession) this.f3562f, (Rect) this.f3563g, (Consumer) this.f3564h, cVar, 6);
            default:
                n nVar5 = new n((a4.c) this.f3562f, (x.c) this.f3563g, (x.q) this.f3564h, cVar, 7);
                nVar5.f3565i = obj;
                return nVar5;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f3560d) {
            case 0:
                return ((n) create((w) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 2:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 3:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 5:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 6:
                return ((n) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                ((n) create((r0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x0104  */
    /* JADX WARN: Type inference failed for: r8v7, types: [a4.c, b4.m] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        r0.r rVar;
        Object objInvoke;
        r0.r rVar2;
        Object objA;
        int i5 = this.f3560d;
        r0.r rVar3 = null;
        int i6 = 2;
        Object obj2 = p3.n.f5026a;
        Object obj3 = this.f3564h;
        Object obj4 = this.f3563g;
        Object obj5 = this.f3562f;
        u3.a aVar = u3.a.f5838d;
        int i7 = 1;
        switch (i5) {
            case 0:
                d0 d0Var = (d0) obj4;
                int i8 = this.f3561e;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return obj2;
                }
                p3.j.O(obj);
                int iOrdinal = ((w) this.f3565i).ordinal();
                if (iOrdinal == 0) {
                    this.f3561e = 1;
                    return ((d) obj5).g(d0Var, this) == aVar ? aVar : obj2;
                }
                if (iOrdinal == 1) {
                    return obj2;
                }
                if (iOrdinal != 2) {
                    throw new i4.p();
                }
                Float f5 = (Float) obj3;
                if (f5 != u.f3586a) {
                    d0Var.i(null, f5);
                    return obj2;
                }
                d0Var.getClass();
                throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
            case 1:
                d dVar = (d) obj5;
                d0 d0Var2 = (d0) obj4;
                int i9 = this.f3561e;
                if (i9 != 0) {
                    if (i9 != 1) {
                        if (i9 == 2) {
                            p3.j.O(obj);
                        } else if (i9 != 3 && i9 != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                    p3.j.O(obj);
                    return obj2;
                }
                p3.j.O(obj);
                a0 a0Var = (a0) this.f3565i;
                if (a0Var == x.f3595a) {
                    this.f3561e = 1;
                    if (dVar.g(d0Var2, this) != aVar) {
                        return obj2;
                    }
                } else {
                    t3.c cVar = null;
                    if (a0Var == x.f3596b) {
                        m4.u uVarH = d0Var2.h();
                        m mVar = new m(2, null);
                        this.f3561e = 2;
                        if (u.e(uVarH, mVar, this) != aVar) {
                        }
                    } else {
                        m4.u uVarH2 = d0Var2.h();
                        z zVar = new z(a0Var, null);
                        int i10 = j.f3548a;
                        t3.i iVar = t3.i.f5807d;
                        k4.a aVar2 = k4.a.f3435d;
                        d dVarC = u.c(u.c(new a2.h(new m4.k(zVar, uVarH2, iVar, -2, aVar2), 13, new h2(i6, cVar, i7))));
                        n nVar = new n(dVar, d0Var2, (Float) obj3, cVar, 0);
                        this.f3561e = 4;
                        Object objG = new m4.k(new i(nVar, null), dVarC, iVar, -2, aVar2).c(iVar, 0, aVar2).g(m4.o.f3846d, this);
                        if (objG != aVar) {
                            objG = obj2;
                        }
                        if (objG != aVar) {
                            objG = obj2;
                        }
                        if (objG != aVar) {
                            return obj2;
                        }
                    }
                }
                return aVar;
                this.f3561e = 3;
                if (dVar.g(d0Var2, this) != aVar) {
                    return obj2;
                }
                return aVar;
            case 2:
                Object obj6 = this.f3565i;
                m.c cVar2 = (m.c) obj5;
                int i11 = this.f3561e;
                if (i11 == 0) {
                    p3.j.O(obj);
                    if (b4.l.a(obj6, cVar2.f3633e.getValue())) {
                        return obj2;
                    }
                    h0 h0Var = m.f.f3661a;
                    m.k kVar = (m.k) ((i1) obj4).getValue();
                    this.f3561e = 1;
                    if (m.c.b(cVar2, obj6, kVar, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                h0 h0Var2 = m.f.f3661a;
                a4.c cVar3 = (a4.c) ((i1) obj3).getValue();
                if (cVar3 == null) {
                    return obj2;
                }
                cVar3.c(cVar2.c());
                return obj2;
            case 3:
                p.i iVar2 = (p.i) obj5;
                j1.h hVar = iVar2.f4477t;
                int i12 = this.f3561e;
                try {
                    try {
                        if (i12 == 0) {
                            p3.j.O(obj);
                            s0 s0VarH = i4.y.h(((i4.v) this.f3565i).h());
                            iVar2.f4482y = true;
                            p.i1 i1Var = iVar2.f4475r;
                            i2 i2Var = new i2((v1) obj4, iVar2, (p.d) obj3, s0VarH, null, 1);
                            this.f3561e = 1;
                            if (i1Var.e(1, i2Var, this) == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i12 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            p3.j.O(obj);
                        }
                        hVar.e();
                        iVar2.f4482y = false;
                        hVar.b(null);
                        iVar2.f4480w = false;
                        return obj2;
                    } catch (Throwable th) {
                        iVar2.f4482y = false;
                        hVar.b(null);
                        iVar2.f4480w = false;
                        throw th;
                    }
                } catch (CancellationException e5) {
                    throw e5;
                }
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                j1.d0 d0Var3 = (j1.d0) obj5;
                int i13 = this.f3561e;
                if (i13 != 0) {
                    if (i13 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return obj2;
                }
                p3.j.O(obj);
                q1 q1Var = new q1((i4.v) this.f3565i, (a4.f) obj4, (v.j) obj3, new o0(d0Var3), null);
                this.f3561e = 1;
                return a.a.j(d0Var3, q1Var, this) == aVar ? aVar : obj2;
            case 5:
                AtomicReference atomicReference = (AtomicReference) obj4;
                int i14 = this.f3561e;
                try {
                    if (i14 != 0) {
                        if (i14 == 1) {
                            rVar = (r0.r) this.f3565i;
                            p3.j.O(obj);
                        } else {
                            if (i14 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            rVar3 = (r0.r) this.f3565i;
                            p3.j.O(obj);
                            objInvoke = obj;
                        }
                        rVar2 = rVar3;
                        while (!atomicReference.compareAndSet(rVar2, null) && atomicReference.get() == rVar2) {
                        }
                        return objInvoke;
                    }
                    p3.j.O(obj);
                    i4.v vVar = (i4.v) this.f3565i;
                    rVar3 = new r0.r(i4.y.h(vVar.h()), ((b4.m) obj5).c(vVar));
                    r0.r rVar4 = (r0.r) atomicReference.getAndSet(rVar3);
                    if (rVar4 != null) {
                        s0 s0Var = rVar4.f5694a;
                        this.f3565i = rVar3;
                        this.f3561e = 1;
                        s0Var.b(null);
                        Object objQ = s0Var.q(this);
                        if (objQ == aVar) {
                            obj2 = objQ;
                        }
                        if (obj2 == aVar) {
                            return aVar;
                        }
                        rVar = rVar3;
                    }
                    Object obj7 = rVar3.f5695b;
                    this.f3565i = rVar3;
                    this.f3561e = 2;
                    objInvoke = ((a4.e) obj3).invoke(obj7, this);
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                    rVar2 = rVar3;
                    while (!atomicReference.compareAndSet(rVar2, null)) {
                    }
                    return objInvoke;
                    rVar3 = rVar;
                    Object obj8 = rVar3.f5695b;
                    this.f3565i = rVar3;
                    this.f3561e = 2;
                    objInvoke = ((a4.e) obj3).invoke(obj8, this);
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                    rVar2 = rVar3;
                    while (!atomicReference.compareAndSet(rVar2, null)) {
                    }
                    return objInvoke;
                } catch (Throwable th2) {
                    while (!atomicReference.compareAndSet(rVar3, null) && atomicReference.get() == rVar3) {
                    }
                    throw th2;
                }
            case 6:
                int i15 = this.f3561e;
                if (i15 == 0) {
                    p3.j.O(obj);
                    Rect rect = (Rect) obj4;
                    k2.i iVar3 = new k2.i(rect.left, rect.top, rect.right, rect.bottom);
                    this.f3561e = 1;
                    objA = v1.c.a((v1.c) this.f3565i, (ScrollCaptureSession) obj5, iVar3, this);
                    if (objA == aVar) {
                        return aVar;
                    }
                } else {
                    if (i15 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    objA = obj;
                }
                ((Consumer) obj3).accept(l0.t((k2.i) objA));
                return obj2;
            default:
                int i16 = this.f3561e;
                if (i16 == 0) {
                    p3.j.O(obj);
                    i2 i2Var2 = new i2((r0) this.f3565i, (a4.c) obj5, (x.c) obj4, (x.q) obj3, null, 6);
                    this.f3561e = 1;
                    if (i4.y.c(i2Var2, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i16 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                throw new i4.p();
        }
    }
}
