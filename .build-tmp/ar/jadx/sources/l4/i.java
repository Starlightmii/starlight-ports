package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3544d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ e f3545e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3546f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n f3547g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(n nVar, t3.c cVar) {
        super(3, cVar);
        this.f3547g = nVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        i iVar = new i(this.f3547g, (t3.c) obj3);
        iVar.f3545e = (e) obj;
        iVar.f3546f = obj2;
        return iVar.invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
    
        if (r0.d(r5, r4) == r3) goto L15;
     */
    @Override // v3.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.f3544d
            r1 = 2
            r2 = 1
            u3.a r3 = u3.a.f5838d
            if (r0 == 0) goto L1e
            if (r0 == r2) goto L18
            if (r0 != r1) goto L10
            p3.j.O(r5)
            goto L3e
        L10:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L18:
            l4.e r0 = r4.f3545e
            p3.j.O(r5)
            goto L32
        L1e:
            p3.j.O(r5)
            l4.e r0 = r4.f3545e
            java.lang.Object r5 = r4.f3546f
            r4.f3545e = r0
            r4.f3544d = r2
            l4.n r2 = r4.f3547g
            java.lang.Object r5 = r2.invoke(r5, r4)
            if (r5 != r3) goto L32
            goto L3d
        L32:
            r2 = 0
            r4.f3545e = r2
            r4.f3544d = r1
            java.lang.Object r5 = r0.d(r5, r4)
            if (r5 != r3) goto L3e
        L3d:
            return r3
        L3e:
            p3.n r5 = p3.n.f5026a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: l4.i.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
