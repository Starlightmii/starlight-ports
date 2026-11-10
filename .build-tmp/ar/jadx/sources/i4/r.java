package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends t3.a implements t3.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q f2770e = new q(t3.d.f5806d, new f0.k1(2));

    public r() {
        super(t3.d.f5806d);
    }

    public abstract void e(t3.h hVar, Runnable runnable);

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
    
        if (((t3.f) r3.f2766d.c(r2)) == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
    
        if (t3.d.f5806d == r3) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0026, code lost:
    
        return t3.i.f5807d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0027, code lost:
    
        return r2;
     */
    @Override // t3.a, t3.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final t3.h f(t3.g r3) {
        /*
            r2 = this;
            java.lang.String r0 = "key"
            b4.l.e(r0, r3)
            boolean r0 = r3 instanceof i4.q
            if (r0 == 0) goto L20
            i4.q r3 = (i4.q) r3
            t3.g r0 = r2.f5803d
            if (r0 == r3) goto L15
            t3.g r1 = r3.f2767e
            if (r1 != r0) goto L14
            goto L15
        L14:
            return r2
        L15:
            a4.c r3 = r3.f2766d
            java.lang.Object r3 = r3.c(r2)
            t3.f r3 = (t3.f) r3
            if (r3 == 0) goto L27
            goto L24
        L20:
            t3.d r0 = t3.d.f5806d
            if (r0 != r3) goto L27
        L24:
            t3.i r3 = t3.i.f5807d
            return r3
        L27:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: i4.r.f(t3.g):t3.h");
    }

    public boolean h(t3.h hVar) {
        return !(this instanceof m1);
    }

    @Override // t3.a, t3.h
    public final t3.f j(t3.g gVar) {
        t3.f fVar;
        b4.l.e("key", gVar);
        if (gVar instanceof q) {
            q qVar = (q) gVar;
            t3.g gVar2 = this.f5803d;
            if ((gVar2 == qVar || qVar.f2767e == gVar2) && (fVar = (t3.f) qVar.f2766d.c(this)) != null) {
                return fVar;
            }
        } else if (t3.d.f5806d == gVar) {
            return this;
        }
        return null;
    }

    public r l(int i5) {
        n4.a.a(i5);
        return new n4.g(this, i5);
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + y.g(this);
    }
}
