package c0;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x3 implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a4.e f1433a;

    public x3(a4.e eVar) {
        this.f1433a = eVar;
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        n1.m0 m0VarC;
        if (this.f1433a != null) {
            int size = list.size();
            int i5 = 0;
            while (true) {
                if (i5 >= size) {
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
                n1.e0 e0Var = (n1.e0) list.get(i5);
                if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var), "text")) {
                    m0VarC = e0Var.c(k2.a.b(j5, 0, 0, 0, 0, 11));
                    break;
                }
                i5++;
            }
        } else {
            m0VarC = null;
        }
        int iMax = Math.max(m0VarC != null ? m0VarC.f4130d : 0, 0);
        int iMax2 = Math.max(h0Var.M(a4.f746a), h0Var.E(a4.f750e) + 0 + (m0VarC != null ? m0VarC.f4131e : 0));
        return h0Var.F(iMax, iMax2, q3.t.f5478d, new w3(m0VarC, null, h0Var, iMax, iMax2, m0VarC != null ? Integer.valueOf(m0VarC.f0(n1.c.f4106a)) : null, m0VarC != null ? Integer.valueOf(m0VarC.f0(n1.c.f4107b)) : null));
    }
}
