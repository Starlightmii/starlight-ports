package v1;

import c0.o4;
import p3.n;
import z1.t;
import z1.y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f6202b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6203c;

    public g(int i5, o4 o4Var) {
        this.f6201a = i5;
        this.f6203c = o4Var;
    }

    public g(y yVar) {
        this.f6203c = yVar;
        this.f6201a = -1;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001d  */
    public float a(int i5, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        y yVar = (y) this.f6203c;
        int i6 = 1;
        if (z2) {
            int iC = t.c(yVar.f7155e, i5, z2);
            int lineStart = yVar.f7155e.getLineStart(iC);
            int iF = yVar.f(iC);
            if (i5 == lineStart || i5 == iF) {
                z5 = true;
            } else {
                z5 = false;
            }
        } else {
            z5 = false;
        }
        int i7 = i5 * 4;
        if (!z4) {
            i6 = z5 ? 2 : 3;
        } else if (z5) {
            i6 = 0;
        }
        int i8 = i7 + i6;
        if (this.f6201a == i8) {
            return this.f6202b;
        }
        float fH = z4 ? yVar.h(i5, z2) : yVar.i(i5, z2);
        if (z3) {
            this.f6201a = i8;
            this.f6202b = fH;
        }
        return fH;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public Object b(float f5, v3.c cVar) {
        f fVar;
        g gVar;
        if (cVar instanceof f) {
            fVar = (f) cVar;
            int i5 = fVar.f6200g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                fVar.f6200g = i5 - Integer.MIN_VALUE;
            } else {
                fVar = new f(this, cVar);
            }
        } else {
            fVar = new f(this, cVar);
        }
        Object objInvoke = fVar.f6198e;
        int i6 = fVar.f6200g;
        if (i6 == 0) {
            p3.j.O(objInvoke);
            o4 o4Var = (o4) this.f6203c;
            Float f6 = new Float(f5);
            fVar.f6197d = this;
            fVar.f6200g = 1;
            objInvoke = o4Var.invoke(f6, fVar);
            u3.a aVar = u3.a.f5838d;
            if (objInvoke == aVar) {
                return aVar;
            }
            gVar = this;
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            gVar = fVar.f6197d;
            p3.j.O(objInvoke);
        }
        gVar.f6202b += ((Number) objInvoke).floatValue();
        return n.f5026a;
    }
}
