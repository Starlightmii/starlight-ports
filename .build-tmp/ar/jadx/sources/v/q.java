package v;

import f0.b2;
import io.github.libxposed.service.IXposedService;
import java.util.List;
import q1.k1;
import q1.k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o0 f6052f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(o0 o0Var, int i5) {
        super(1);
        this.f6051e = i5;
        this.f6052f = o0Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(o0 o0Var, w1.i iVar) {
        super(1);
        this.f6051e = 3;
        this.f6052f = o0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        k2 k2Var;
        int i5 = this.f6051e;
        boolean z2 = false;
        p3.n nVar = null;
        p3.n nVar2 = p3.n.f5026a;
        o0 o0Var = this.f6052f;
        switch (i5) {
            case 0:
                n1.p pVar = (n1.p) obj;
                e1 e1VarD = o0Var.d();
                if (e1VarD != null) {
                    e1VarD.f5901c = pVar;
                }
                return nVar2;
            case 1:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                o0Var.f6022q.setValue(bool);
                return nVar2;
            case 2:
                List list = (List) obj;
                if (o0Var.d() != null) {
                    e1 e1VarD2 = o0Var.d();
                    b4.l.b(e1VarD2);
                    list.add(e1VarD2.f5899a);
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            case 3:
                y1.f fVar = (y1.f) obj;
                e2.d0 d0Var = o0Var.f6010e;
                q qVar = o0Var.f6025t;
                if (d0Var != null) {
                    e2.x xVarP = o0Var.f6009d.p(p3.j.H(new e2.f(), new e2.a(fVar, 1)));
                    d0Var.a(null, xVarP);
                    qVar.c(xVarP);
                    nVar = nVar2;
                }
                if (nVar == null) {
                    String str = fVar.f6759d;
                    int length = str.length();
                    qVar.c(new e2.x(str, p3.j.g(length, length), 4));
                }
                return Boolean.TRUE;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                int i6 = ((e2.m) obj).f2037a;
                a1.b bVar = o0Var.f6023r;
                bVar.getClass();
                if (i6 == 7 || i6 == 2 || i6 == 6 || i6 == 5 || i6 == 3 || i6 == 4) {
                    bVar.s();
                } else if (i6 != 1 && i6 != 0) {
                    throw new IllegalStateException("invalid ImeAction");
                }
                if (i6 == 6) {
                    w0.g gVar = (w0.g) bVar.f25f;
                    if (gVar == null) {
                        b4.l.j("focusManager");
                        throw null;
                    }
                    ((androidx.compose.ui.focus.b) gVar).d(1);
                } else if (i6 == 5) {
                    w0.g gVar2 = (w0.g) bVar.f25f;
                    if (gVar2 == null) {
                        b4.l.j("focusManager");
                        throw null;
                    }
                    ((androidx.compose.ui.focus.b) gVar2).d(2);
                } else if (i6 == 7 && (k2Var = (k2) bVar.f23d) != null) {
                    ((k1) k2Var).a();
                }
                return nVar2;
            default:
                e2.x xVar = (e2.x) obj;
                String str2 = xVar.f2062a.f6759d;
                y1.f fVar2 = o0Var.f6015j;
                if (!b4.l.a(str2, fVar2 != null ? fVar2.f6759d : null)) {
                    o0Var.f6016k.setValue(f0.f5917d);
                }
                long j5 = y1.g0.f6768b;
                o0Var.f(j5);
                o0Var.e(j5);
                o0Var.f6024s.c(xVar);
                b2 b2Var = o0Var.f6007b;
                f0.e0 e0Var = b2Var.f2095a;
                if (e0Var != null) {
                    e0Var.r(b2Var, null);
                }
                return nVar2;
        }
    }
}
