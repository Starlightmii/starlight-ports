package c0;

import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import io.github.andrea_lyz.assistrestore.R;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f1438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.e f1439f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ k2.b f1440g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ k4 f1441h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f1442i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ a4.c f1443j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ boolean f1444k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ y1.h0 f1445l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ v.n0 f1446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v.m0 f1447n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f1448o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f1449p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f1450q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ e2.f0 f1451r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ q.k f1452s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ a4.e f1453t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ a4.e f1454u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1455v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y1(r0.p pVar, a4.e eVar, k2.b bVar, k4 k4Var, String str, a4.c cVar, boolean z2, y1.h0 h0Var, v.n0 n0Var, v.m0 m0Var, boolean z3, int i5, int i6, e2.f0 f0Var, q.k kVar, a4.e eVar2, a4.e eVar3, y0.q0 q0Var) {
        super(2);
        this.f1438e = pVar;
        this.f1439f = eVar;
        this.f1440g = bVar;
        this.f1441h = k4Var;
        this.f1442i = str;
        this.f1443j = cVar;
        this.f1444k = z2;
        this.f1445l = h0Var;
        this.f1446m = n0Var;
        this.f1447n = m0Var;
        this.f1448o = z3;
        this.f1449p = i5;
        this.f1450q = i6;
        this.f1451r = f0Var;
        this.f1452s = kVar;
        this.f1453t = eVar2;
        this.f1454u = eVar3;
        this.f1455v = q0Var;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0026  */
    /* JADX WARN: Code duplicated, block: B:11:0x0040  */
    /* JADX WARN: Code duplicated, block: B:8:0x0022  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        r0.p pVarI;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                if (this.f1439f != null) {
                    k kVar = k.f960h;
                    AtomicInteger atomicInteger = w1.k.f6311a;
                    pVarI = androidx.compose.foundation.layout.a.i(new AppendedSemanticsElement(kVar, true), 0.0f, this.f1440g.I(d2.f799b), 0.0f, 13);
                } else {
                    pVarI = r0.m.f5680a;
                }
                r0.p pVarE = this.f1438e.e(pVarI);
                f0.v vVar2 = (f0.v) pVar;
                vVar2.j(AndroidCompositionLocals_androidKt.f203a);
                ((Context) vVar2.j(AndroidCompositionLocals_androidKt.f204b)).getResources().getString(R.string.default_error_message);
                float f5 = d0.u.f1580b;
                r0.p pVarA = androidx.compose.foundation.layout.c.a(pVarE, v1.f1368c, v1.f1367b);
                k4 k4Var = this.f1441h;
                y0.s0 s0Var = new y0.s0(k4Var.f994i);
                a4.e eVar = this.f1454u;
                y0.q0 q0Var = this.f1455v;
                String str = this.f1442i;
                boolean z2 = this.f1444k;
                boolean z3 = this.f1448o;
                e2.f0 f0Var = this.f1451r;
                q.k kVar2 = this.f1452s;
                v.g.a(str, this.f1443j, pVarA, z2, this.f1445l, this.f1446m, this.f1447n, z3, this.f1449p, this.f1450q, f0Var, null, kVar2, s0Var, n0.i.c(1474611661, new x1(str, z2, z3, f0Var, kVar2, this.f1439f, this.f1453t, eVar, k4Var, q0Var), pVar), pVar, 0);
            }
        } else {
            if (this.f1439f != null) {
                k kVar3 = k.f960h;
                AtomicInteger atomicInteger2 = w1.k.f6311a;
                pVarI = androidx.compose.foundation.layout.a.i(new AppendedSemanticsElement(kVar3, true), 0.0f, this.f1440g.I(d2.f799b), 0.0f, 13);
            } else {
                pVarI = r0.m.f5680a;
            }
            r0.p pVarE2 = this.f1438e.e(pVarI);
            f0.v vVar3 = (f0.v) pVar;
            vVar3.j(AndroidCompositionLocals_androidKt.f203a);
            ((Context) vVar3.j(AndroidCompositionLocals_androidKt.f204b)).getResources().getString(R.string.default_error_message);
            float f6 = d0.u.f1580b;
            r0.p pVarA2 = androidx.compose.foundation.layout.c.a(pVarE2, v1.f1368c, v1.f1367b);
            k4 k4Var2 = this.f1441h;
            y0.s0 s0Var2 = new y0.s0(k4Var2.f994i);
            a4.e eVar2 = this.f1454u;
            y0.q0 q0Var2 = this.f1455v;
            String str2 = this.f1442i;
            boolean z4 = this.f1444k;
            boolean z5 = this.f1448o;
            e2.f0 f0Var2 = this.f1451r;
            q.k kVar4 = this.f1452s;
            v.g.a(str2, this.f1443j, pVarA2, z4, this.f1445l, this.f1446m, this.f1447n, z5, this.f1449p, this.f1450q, f0Var2, null, kVar4, s0Var2, n0.i.c(1474611661, new x1(str2, z4, z5, f0Var2, kVar4, this.f1439f, this.f1453t, eVar2, k4Var2, q0Var2), pVar), pVar, 0);
        }
        return p3.n.f5026a;
    }
}
