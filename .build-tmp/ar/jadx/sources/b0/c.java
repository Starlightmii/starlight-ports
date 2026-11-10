package b0;

import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.LinkedHashMap;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends u implements p {
    public o A;
    public q B;

    @Override // b0.u
    public final void B0(q.m mVar) {
        q qVar = this.B;
        if (qVar != null) {
            qVar.d();
        }
    }

    @Override // b0.p
    public final void b0() {
        this.B = null;
        p1.f.l(this);
    }

    @Override // r0.o
    public final void s0() {
        o oVar = this.A;
        if (oVar != null) {
            b0();
            a2.h hVar = oVar.f437g;
            q qVar = (q) ((LinkedHashMap) hVar.f49e).get(this);
            if (qVar != null) {
                qVar.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) hVar.f49e;
                q qVar2 = (q) linkedHashMap.get(this);
                if (qVar2 != null) {
                }
                linkedHashMap.remove(this);
                oVar.f436f.add(qVar);
            }
        }
    }

    @Override // b0.u
    public final void y0(q.m mVar, long j5, float f5) {
        o oVarA = this.A;
        if (oVarA == null) {
            oVarA = y.a(y.b((View) p1.f.h(this, AndroidCompositionLocals_androidKt.f208f)));
            this.A = oVarA;
        }
        q qVarA = oVarA.a(this);
        int iH = d4.a.H(f5);
        long jA = this.f456t.a();
        this.f457u.a();
        qVarA.b(mVar, this.f454r, j5, iH, jA, 0.1f, new a(1, this));
        this.B = qVarA;
        p1.f.l(this);
    }

    @Override // b0.u
    public final void z0(f0 f0Var) {
        y0.r rVarR = f0Var.f4828d.f27e.r();
        q qVar = this.B;
        if (qVar != null) {
            long j5 = this.f460x;
            long jA = this.f456t.a();
            this.f457u.a();
            qVar.e(0.1f, j5, jA);
            qVar.draw(y0.e.a(rVarR));
        }
    }
}
