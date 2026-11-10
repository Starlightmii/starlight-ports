package n;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public q4.a f4007d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f4008e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p.m f4009f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public t0 f4010g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4011h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f4012i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f4013j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ t0 f4014k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b0.d f4015l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p.m f4016m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(int i5, t0 t0Var, b0.d dVar, p.m mVar, t3.c cVar) {
        super(2, cVar);
        this.f4013j = i5;
        this.f4014k = t0Var;
        this.f4015l = dVar;
        this.f4016m = mVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        s0 s0Var = new s0(this.f4013j, this.f4014k, this.f4015l, this.f4016m, cVar);
        s0Var.f4012i = obj;
        return s0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((s0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        a4.e eVar;
        p.m mVar;
        r0 r0Var;
        t0 t0Var;
        q4.a aVar;
        r0 r0Var2;
        q4.a aVar2;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        int i5 = this.f4011h;
        u3.a aVar3 = u3.a.f5838d;
        try {
            try {
                if (i5 == 0) {
                    p3.j.O(obj);
                    t3.f fVarJ = ((i4.v) this.f4012i).h().j(i4.s.f2774e);
                    b4.l.b(fVarJ);
                    r0 r0Var3 = new r0(this.f4013j, (i4.s0) fVarJ);
                    t0 t0Var2 = this.f4014k;
                    AtomicReference atomicReference3 = t0Var2.f4025a;
                    loop2: while (true) {
                        r0 r0Var4 = (r0) atomicReference3.get();
                        if (r0Var4 != null && m.h.a(r0Var3.f4001a, r0Var4.f4001a) < 0) {
                            throw new CancellationException("Current mutation had a higher priority");
                        }
                        do {
                            if (atomicReference3.compareAndSet(r0Var4, r0Var3)) {
                                if (r0Var4 != null) {
                                    r0Var4.f4002b.b(new j1.x("Mutation interrupted", 3));
                                }
                                q4.c cVar = t0Var2.f4026b;
                                this.f4012i = r0Var3;
                                this.f4007d = cVar;
                                eVar = this.f4015l;
                                this.f4008e = eVar;
                                p.m mVar2 = this.f4016m;
                                this.f4009f = mVar2;
                                this.f4010g = t0Var2;
                                this.f4011h = 1;
                                if (cVar.c(this) != aVar3) {
                                    mVar = mVar2;
                                    r0Var = r0Var3;
                                    t0Var = t0Var2;
                                    aVar = cVar;
                                    break loop2;
                                }
                                return aVar3;
                            }
                        } while (atomicReference3.get() == r0Var4);
                    }
                } else {
                    if (i5 != 1) {
                        if (i5 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        t0Var = (t0) this.f4008e;
                        q4.a aVar4 = this.f4007d;
                        r0Var2 = (r0) this.f4012i;
                        try {
                            p3.j.O(obj);
                            aVar2 = aVar4;
                            atomicReference2 = t0Var.f4025a;
                            while (!atomicReference2.compareAndSet(r0Var2, null) && atomicReference2.get() == r0Var2) {
                            }
                            ((q4.c) aVar2).d(null);
                            return obj;
                        } catch (Throwable th) {
                            th = th;
                            atomicReference = t0Var.f4025a;
                            while (!atomicReference.compareAndSet(r0Var2, null)) {
                            }
                            throw th;
                        }
                    }
                    t0Var = this.f4010g;
                    mVar = this.f4009f;
                    a4.e eVar2 = (a4.e) this.f4008e;
                    q4.a aVar5 = this.f4007d;
                    r0Var = (r0) this.f4012i;
                    p3.j.O(obj);
                    eVar = eVar2;
                    aVar = aVar5;
                }
                this.f4012i = r0Var;
                this.f4007d = aVar;
                this.f4008e = t0Var;
                this.f4009f = null;
                this.f4010g = null;
                this.f4011h = 2;
                obj = eVar.invoke(mVar, this);
                if (obj != aVar3) {
                    aVar2 = aVar;
                    r0Var2 = r0Var;
                    atomicReference2 = t0Var.f4025a;
                    while (!atomicReference2.compareAndSet(r0Var2, null)) {
                    }
                    ((q4.c) aVar2).d(null);
                    return obj;
                }
                return aVar3;
            } catch (Throwable th2) {
                th = th2;
                r0Var2 = r0Var;
                atomicReference = t0Var.f4025a;
                while (!atomicReference.compareAndSet(r0Var2, null) && atomicReference.get() == r0Var2) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            ((q4.c) 2).d(null);
            throw th3;
        }
    }
}
