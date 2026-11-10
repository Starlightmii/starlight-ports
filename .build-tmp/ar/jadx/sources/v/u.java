package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f6109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o0 f6110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e2.x f6111g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(boolean z2, o0 o0Var, w1.i iVar, e2.x xVar) {
        super(1);
        this.f6109e = z2;
        this.f6110f = o0Var;
        this.f6111g = xVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        y1.f fVar = (y1.f) obj;
        if (!this.f6109e) {
            return Boolean.FALSE;
        }
        o0 o0Var = this.f6110f;
        e2.d0 d0Var = o0Var.f6010e;
        q qVar = o0Var.f6025t;
        p3.n nVar = null;
        if (d0Var != null) {
            e2.x xVarP = o0Var.f6009d.p(p3.j.H(new e2.k(), new e2.a(fVar, 1)));
            d0Var.a(null, xVarP);
            qVar.c(xVarP);
            nVar = p3.n.f5026a;
        }
        if (nVar == null) {
            e2.x xVar = this.f6111g;
            y1.f fVar2 = xVar.f2062a;
            long j5 = xVar.f2063b;
            String str = fVar2.f6759d;
            int i5 = y1.g0.f6769c;
            int i6 = (int) (j5 >> 32);
            int i7 = (int) (j5 & 4294967295L);
            b4.l.e("<this>", str);
            b4.l.e("replacement", fVar);
            if (i7 < i6) {
                throw new IndexOutOfBoundsException("End index (" + i7 + ") is less than start index (" + i6 + ").");
            }
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) str, 0, i6);
            sb.append((CharSequence) fVar);
            sb.append((CharSequence) str, i7, str.length());
            String string = sb.toString();
            int length = fVar.f6759d.length() + i6;
            qVar.c(new e2.x(string, p3.j.g(length, length), 4));
        }
        return Boolean.TRUE;
    }
}
