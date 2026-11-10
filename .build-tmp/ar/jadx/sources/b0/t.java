package b0;

import android.view.View;
import c0.z2;
import f0.i1;
import f0.l2;
import i4.s0;
import io.github.libxposed.service.IXposedService;
import j1.d0;
import p.a1;
import q1.r0;
import q1.t1;
import q1.v1;
import v.u0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f449d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f450e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f451f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f452g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(Object obj, Object obj2, t3.c cVar, int i5) {
        super(2, cVar);
        this.f449d = i5;
        this.f451f = obj;
        this.f452g = obj2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(Object obj, t3.c cVar, int i5) {
        super(2, cVar);
        this.f449d = i5;
        this.f452g = obj;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f449d) {
            case 0:
                t tVar = new t((u) this.f452g, cVar, 0);
                tVar.f451f = obj;
                return tVar;
            case 1:
                return new t((a0) this.f451f, (m.k) this.f452g, cVar, 1);
            case 2:
                return new t((q.j) this.f451f, (p0.q) this.f452g, cVar, 2);
            case 3:
                return new t((z2) this.f451f, (q1.f) this.f452g, cVar, 3);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                t tVar2 = new t((l4.e) this.f452g, cVar, 4);
                tVar2.f451f = obj;
                return tVar2;
            case 5:
                return new t((q.k) this.f451f, (q.g) this.f452g, cVar, 5);
            case 6:
                return new t((q.k) this.f451f, (q.h) this.f452g, cVar, 6);
            case 7:
                t tVar3 = new t((n.t) this.f452g, cVar, 7);
                tVar3.f451f = obj;
                return tVar3;
            case 8:
                t tVar4 = new t((n.g) this.f452g, cVar, 8);
                tVar4.f451f = obj;
                return tVar4;
            case 9:
                t tVar5 = new t((n2.p) this.f452g, cVar, 9);
                tVar5.f451f = obj;
                return tVar5;
            case 10:
                t tVar6 = new t((o.j) this.f452g, cVar, 10);
                tVar6.f451f = obj;
                return tVar6;
            case 11:
                t tVar7 = new t((a1) this.f452g, cVar, 11);
                tVar7.f451f = obj;
                return tVar7;
            case 12:
                return new t((q.j) this.f451f, (i1) this.f452g, cVar, 12);
            case 13:
                t tVar8 = new t((r0) this.f452g, cVar, 13);
                tVar8.f451f = obj;
                return tVar8;
            case 14:
                return new t((l2) this.f451f, (View) this.f452g, cVar, 14);
            case 15:
                return new t((l4.b0) this.f451f, (v1) this.f452g, cVar, 15);
            case 16:
                return new t((s.g) this.f451f, (n.i) this.f452g, cVar, 16);
            case 17:
                return new t((d0) this.f451f, (e0) this.f452g, cVar, 17);
            case 18:
                return new t((v1.c) this.f451f, (Runnable) this.f452g, cVar, 18);
            case 19:
                t tVar9 = new t((w.b) this.f452g, cVar, 19);
                tVar9.f451f = obj;
                return tVar9;
            case 20:
                return new t((x.c) this.f451f, (x.p) this.f452g, cVar, 20);
            case 21:
                return new t((s0) this.f451f, (x.j) this.f452g, cVar, 21);
            case 22:
                return new t((x.q) this.f451f, (l4.n) this.f452g, cVar, 22);
            case 23:
                t tVar10 = new t((a4.c) this.f452g, cVar, 23);
                tVar10.f451f = obj;
                return tVar10;
            default:
                t tVar11 = new t((u0) this.f452g, cVar, 24);
                tVar11.f451f = obj;
                return tVar11;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        switch (this.f449d) {
            case 0:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 2:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 3:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return ((t) create(obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 5:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 6:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 7:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 8:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 9:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 10:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 11:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 12:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 13:
                ((t) create((t1) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
            case 14:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 15:
                ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
            case 16:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 17:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 18:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 19:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 20:
                return ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 21:
                ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
            case 22:
                ((t) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
                return u3.a.f5838d;
            case 23:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((t) create((d0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    /* JADX WARN: Code duplicated, block: B:225:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:227:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:229:0x03dd  */
    /* JADX WARN: Code duplicated, block: B:232:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:234:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:235:0x03f9  */
    /* JADX WARN: Code duplicated, block: B:322:0x0537  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:228:0x03db -> B:230:0x03df). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x00e2 -> B:57:0x00c7). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b0.t.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
