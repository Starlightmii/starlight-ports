package f0;

import io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt;
import io.github.libxposed.service.IXposedService;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2420d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2421e;

    public /* synthetic */ y(int i5, Object obj) {
        this.f2420d = i5;
        this.f2421e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:117:0x021b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x021d A[Catch: all -> 0x0210, LOOP:6: B:101:0x01e1->B:118:0x021d, LOOP_END, TryCatch #0 {all -> 0x0210, blocks: (B:94:0x01be, B:96:0x01ce, B:98:0x01d4, B:101:0x01e1, B:103:0x01ed, B:105:0x01f7, B:107:0x01fd, B:109:0x0206, B:114:0x0212, B:115:0x0215, B:118:0x021d, B:128:0x0243, B:119:0x0221, B:120:0x0227, B:122:0x022d, B:124:0x0235, B:127:0x023f), top: B:150:0x01be }] */
    /* JADX WARN: Code duplicated, block: B:177:0x0243 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0091  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x00ac  */
    /* JADX WARN: Instruction removed from duplicated block: B:43:0x00ac, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v45, types: [a4.c, b4.m] */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        char c5;
        Collection collectionE0;
        char c6 = 7;
        Object objW = null;
        switch (this.f2420d) {
            case 0:
                n0.j jVar = (n0.j) this.f2421e;
                ((Integer) obj).getClass();
                if (obj2 instanceof m) {
                    m mVar = (m) obj2;
                    j.a0 a0Var = jVar.f4079h;
                    if (a0Var == null) {
                        int i5 = j.g0.f3106a;
                        a0Var = new j.a0();
                        jVar.f4079h = a0Var;
                    }
                    a0Var.f3067b[a0Var.d(mVar)] = mVar;
                    jVar.f4077f.b(mVar);
                }
                if (obj2 instanceof o2) {
                    jVar.d((o2) obj2);
                }
                if (obj2 instanceof b2) {
                    ((b2) obj2).d();
                }
                return p3.n.f5026a;
            case 1:
                l2 l2Var = (l2) this.f2421e;
                Set set = (Set) obj;
                synchronized (l2Var.f2245c) {
                    try {
                        if (((g2) l2Var.f2263u.getValue()).compareTo(g2.f2181h) >= 0) {
                            j.a0 a0Var2 = l2Var.f2250h;
                            if (set instanceof h0.h) {
                                j.a0 a0Var3 = ((h0.h) set).f2589d;
                                Object[] objArr = a0Var3.f3067b;
                                long[] jArr = a0Var3.f3066a;
                                int length = jArr.length - 2;
                                if (length >= 0) {
                                    int i6 = 0;
                                    while (true) {
                                        long j5 = jArr[i6];
                                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i7 = 8 - ((~(i6 - length)) >>> 31);
                                            for (int i8 = 0; i8 < i7; i8++) {
                                                if ((j5 & 255) < 128) {
                                                    Object obj3 = objArr[(i6 << 3) + i8];
                                                    if (!(obj3 instanceof p0.y) || ((p0.y) obj3).e(1)) {
                                                        a0Var2.a(obj3);
                                                    }
                                                }
                                                j5 >>= 8;
                                            }
                                            if (i7 == 8) {
                                                if (i6 != length) {
                                                    i6++;
                                                }
                                            }
                                        } else if (i6 != length) {
                                            i6++;
                                        }
                                    }
                                }
                            } else {
                                for (Object obj4 : set) {
                                    if (!(obj4 instanceof p0.y) || ((p0.y) obj4).e(1)) {
                                        a0Var2.a(obj4);
                                    }
                                }
                            }
                            objW = l2Var.w();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                if (objW != null) {
                    ((i4.h) objW).resumeWith(p3.n.f5026a);
                }
                return p3.n.f5026a;
            case 2:
                k4.c cVar = (k4.c) this.f2421e;
                Set set2 = (Set) obj;
                if (set2 instanceof h0.h) {
                    j.a0 a0Var4 = ((h0.h) set2).f2589d;
                    Object[] objArr2 = a0Var4.f3067b;
                    long[] jArr2 = a0Var4.f3066a;
                    int length2 = jArr2.length - 2;
                    if (length2 >= 0) {
                        int i9 = 0;
                        while (true) {
                            long j6 = jArr2[i9];
                            if ((((~j6) << c6) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                int i11 = 0;
                                while (true) {
                                    if (i11 < i10) {
                                        if ((j6 & 255) < 128) {
                                            Object obj5 = objArr2[(i9 << 3) + i11];
                                            if (!(obj5 instanceof p0.y) || ((p0.y) obj5).e(4)) {
                                            }
                                        }
                                        j6 >>= 8;
                                        i11++;
                                        c6 = c6;
                                    } else {
                                        c5 = c6;
                                        if (i10 == 8) {
                                        }
                                    }
                                }
                            } else {
                                c5 = c6;
                            }
                            if (i9 != length2) {
                                i9++;
                                c6 = c5;
                            }
                        }
                        cVar.o(set2);
                    }
                } else {
                    Set set3 = set2;
                    if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                        for (Object obj6 : set3) {
                            if (!(obj6 instanceof p0.y) || ((p0.y) obj6).e(4)) {
                                cVar.o(set2);
                            }
                        }
                    }
                }
                return p3.n.f5026a;
            case 3:
                return AssistRestoreAppKt.AppScreen$lambda$1((a4.e) this.f2421e, (p) obj, ((Integer) obj2).intValue());
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return AssistRestoreAppKt.AppScreen$lambda$2((c0.c3) this.f2421e, (p) obj, ((Integer) obj2).intValue());
            case 5:
                m4.p pVar = (m4.p) this.f2421e;
                int iIntValue = ((Integer) obj).intValue();
                t3.f fVar = (t3.f) obj2;
                t3.g key = fVar.getKey();
                t3.f fVarJ = pVar.f3848e.j(key);
                if (key == i4.s.f2774e) {
                    i4.s0 s0Var = (i4.s0) fVarJ;
                    i4.s0 parent = (i4.s0) fVar;
                    while (parent != null) {
                        if (parent != s0Var && (parent instanceof n4.p)) {
                            i4.k kVar = (i4.k) i4.a1.f2716e.get((n4.p) parent);
                            parent = kVar != null ? kVar.getParent() : null;
                        } else {
                            objW = parent;
                            if (objW == s0Var) {
                                throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + objW + ", expected child of " + s0Var + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
                            }
                            if (s0Var != null) {
                                iIntValue++;
                            }
                        }
                    }
                    if (objW == s0Var) {
                        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + objW + ", expected child of " + s0Var + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
                    }
                    if (s0Var != null) {
                        iIntValue++;
                    }
                } else if (fVar != fVarJ) {
                    iIntValue = Integer.MIN_VALUE;
                } else {
                    iIntValue++;
                }
                return Integer.valueOf(iIntValue);
            default:
                p0.t tVar = (p0.t) this.f2421e;
                Collection collection = (Set) obj;
                AtomicReference atomicReference = tVar.f4746b;
                while (true) {
                    Object obj7 = atomicReference.get();
                    if (obj7 == null) {
                        collectionE0 = collection;
                    } else if (obj7 instanceof Set) {
                        collectionE0 = p3.j.H(obj7, collection);
                    } else {
                        if (!(obj7 instanceof List)) {
                            z.b("Unexpected notification");
                            throw new i4.p();
                        }
                        collectionE0 = q3.l.e0((Collection) obj7, p3.j.G(collection));
                    }
                    do {
                        if (atomicReference.compareAndSet(obj7, collectionE0)) {
                            if (tVar.b()) {
                                tVar.f4745a.c(new d2(3, tVar));
                            }
                            return p3.n.f5026a;
                        }
                    } while (atomicReference.get() == obj7);
                }
                break;
        }
    }
}
