package r0;

import i4.s;
import i4.s0;
import i4.u0;
import i4.v;
import i4.y;
import j1.x;
import n.a0;
import p1.b1;
import p1.y0;
import q1.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class o implements p1.l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public n4.c f5682e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5683f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public o f5685h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public o f5686i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public b1 f5687j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y0 f5688k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5689l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5690m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5691n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5692o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5693p;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f5681d = this;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5684g = -1;

    public final v n0() {
        n4.c cVar = this.f5682e;
        if (cVar != null) {
            return cVar;
        }
        n4.c cVarA = y.a(((u) p1.f.t(this)).getCoroutineContext().k(new u0((s0) ((u) p1.f.t(this)).getCoroutineContext().j(s.f2774e))));
        this.f5682e = cVarA;
        return cVarA;
    }

    public boolean o0() {
        return !(this instanceof a0);
    }

    public void p0() {
        if (this.f5693p) {
            d4.a.L("node attached multiple times");
            throw null;
        }
        if (this.f5688k == null) {
            d4.a.L("attach invoked on a node without a coordinator");
            throw null;
        }
        this.f5693p = true;
        this.f5691n = true;
    }

    public void q0() {
        if (!this.f5693p) {
            d4.a.L("Cannot detach a node that is not attached");
            throw null;
        }
        if (this.f5691n) {
            d4.a.L("Must run runAttachLifecycle() before markAsDetached()");
            throw null;
        }
        if (this.f5692o) {
            d4.a.L("Must run runDetachLifecycle() before markAsDetached()");
            throw null;
        }
        this.f5693p = false;
        n4.c cVar = this.f5682e;
        if (cVar != null) {
            y.b(cVar, new x("The Modifier.Node was detached", 4));
            this.f5682e = null;
        }
    }

    public void r0() {
    }

    public void s0() {
    }

    public void t0() {
        if (this.f5693p) {
            return;
        }
        d4.a.L("reset() called on an unattached node");
        throw null;
    }

    public void u0() {
        if (!this.f5693p) {
            d4.a.L("Must run markAsAttached() prior to runAttachLifecycle");
            throw null;
        }
        if (!this.f5691n) {
            d4.a.L("Must run runAttachLifecycle() only once after markAsAttached()");
            throw null;
        }
        this.f5691n = false;
        r0();
        this.f5692o = true;
    }

    public void v0() {
        if (!this.f5693p) {
            d4.a.L("node detached multiple times");
            throw null;
        }
        if (this.f5688k == null) {
            d4.a.L("detach invoked on a node without a coordinator");
            throw null;
        }
        if (!this.f5692o) {
            d4.a.L("Must run runDetachLifecycle() once after runAttachLifecycle() and before markAsDetached()");
            throw null;
        }
        this.f5692o = false;
        s0();
    }

    public void w0(o oVar) {
        this.f5681d = oVar;
    }

    public void x0(y0 y0Var) {
        this.f5688k = y0Var;
    }
}
