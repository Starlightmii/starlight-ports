package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q4.c f783a = new q4.c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0.s1 f784b = f0.l.s(null);

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object a(a3 a3Var, v3.c cVar) {
        b3 b3Var;
        c3 c3Var;
        q4.a aVar;
        a3 a3Var2;
        Throwable th;
        c3 c3Var2;
        q4.a aVar2;
        if (cVar instanceof b3) {
            b3Var = (b3) cVar;
            int i5 = b3Var.f773i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                b3Var.f773i = i5 - Integer.MIN_VALUE;
            } else {
                b3Var = new b3(this, cVar);
            }
        } else {
            b3Var = new b3(this, cVar);
        }
        Object obj = b3Var.f771g;
        int i6 = b3Var.f773i;
        u3.a aVar3 = u3.a.f5838d;
        try {
            try {
                if (i6 == 0) {
                    p3.j.O(obj);
                    b3Var.f768d = this;
                    b3Var.f769e = a3Var;
                    q4.c cVar2 = this.f783a;
                    b3Var.f770f = cVar2;
                    b3Var.f773i = 1;
                    if (cVar2.c(b3Var) != aVar3) {
                        c3Var = this;
                        a3Var2 = a3Var;
                        aVar = cVar2;
                    }
                    return aVar3;
                }
                if (i6 != 1) {
                    if (i6 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    q4.a aVar4 = b3Var.f770f;
                    c3Var2 = b3Var.f768d;
                    try {
                        p3.j.O(obj);
                        aVar2 = aVar4;
                        c3Var2.f784b.setValue(null);
                        ((q4.c) aVar2).d(null);
                        return obj;
                    } catch (Throwable th2) {
                        th = th2;
                        c3Var2.f784b.setValue(null);
                        throw th;
                    }
                }
                q4.a aVar5 = b3Var.f770f;
                a3 a3Var3 = b3Var.f769e;
                c3Var = b3Var.f768d;
                p3.j.O(obj);
                aVar = aVar5;
                a3Var2 = a3Var3;
                b3Var.f768d = c3Var;
                b3Var.f769e = a3Var2;
                b3Var.f770f = aVar;
                b3Var.f773i = 2;
                i4.h hVar = new i4.h(1, p3.j.B(b3Var));
                hVar.p();
                c3Var.f784b.setValue(new z2(a3Var2, hVar));
                Object objO = hVar.o();
                if (objO != aVar3) {
                    q4.a aVar6 = aVar;
                    obj = objO;
                    aVar2 = aVar6;
                    c3Var2 = c3Var;
                    c3Var2.f784b.setValue(null);
                    ((q4.c) aVar2).d(null);
                    return obj;
                }
                return aVar3;
            } catch (Throwable th3) {
                th = th3;
                c3Var2 = c3Var;
                c3Var2.f784b.setValue(null);
                throw th;
            }
        } catch (Throwable th4) {
            ((q4.c) a3Var).d(null);
            throw th4;
        }
    }
}
