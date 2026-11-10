package c0;

import android.graphics.Matrix;
import android.graphics.Path;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f764f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f765g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f766h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b1(int i5, n1.m0 m0Var, int i6) {
        super(1);
        this.f763e = 0;
        this.f765g = i5;
        this.f764f = m0Var;
        this.f766h = i6;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b1(Object obj, int i5, int i6, int i7) {
        super(1);
        this.f763e = i7;
        this.f764f = obj;
        this.f765g = i5;
        this.f766h = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f763e) {
            case 0:
                n1.m0 m0Var = (n1.m0) this.f764f;
                n1.l0.d((n1.l0) obj, m0Var, d4.a.H((this.f765g - m0Var.f4130d) / 2.0f), d4.a.H((this.f766h - m0Var.f4131e) / 2.0f));
                return p3.n.f5026a;
            case 1:
                n1.l0.d((n1.l0) obj, (n1.m0) this.f764f, this.f765g, this.f766h);
                return p3.n.f5026a;
            case 2:
                n1.l0.d((n1.l0) obj, (n1.m0) this.f764f, -this.f765g, -this.f766h);
                return p3.n.f5026a;
            default:
                y1.o oVar = (y1.o) obj;
                y0.j jVar = (y0.j) this.f764f;
                y1.a aVar = oVar.f6799a;
                int iB = oVar.b(this.f765g);
                int iB2 = oVar.b(this.f766h);
                CharSequence charSequence = aVar.f6707e;
                if (iB < 0 || iB > iB2 || iB2 > charSequence.length()) {
                    throw new IllegalArgumentException(("start(" + iB + ") or end(" + iB2 + ") is out of range [0.." + charSequence.length() + "], or start > end!").toString());
                }
                Path path = new Path();
                z1.y yVar = aVar.f6706d;
                yVar.f7155e.getSelectionPath(iB, iB2, path);
                int i5 = yVar.f7157g;
                if (i5 != 0 && !path.isEmpty()) {
                    path.offset(0.0f, i5);
                }
                long jB = p3.j.b(0.0f, oVar.f6804f);
                Matrix matrix = new Matrix();
                matrix.setTranslate(x0.c.d(jB), x0.c.e(jB));
                path.transform(matrix);
                jVar.f6638a.addPath(path, x0.c.d(0L), x0.c.e(0L));
                return p3.n.f5026a;
        }
    }
}
