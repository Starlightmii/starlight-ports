package m;

import android.view.View;
import f0.i1;
import f0.l2;
import q1.a3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3651d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f3652e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3653f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f3654g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f3655h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f3656i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f3657j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f3658k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(b4.w wVar, l2 l2Var, androidx.lifecycle.v vVar, a3 a3Var, View view, t3.c cVar) {
        super(2, cVar);
        this.f3654g = wVar;
        this.f3655h = l2Var;
        this.f3656i = vVar;
        this.f3657j = a3Var;
        this.f3658k = view;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(k4.g gVar, c cVar, i1 i1Var, i1 i1Var2, t3.c cVar2) {
        super(2, cVar2);
        this.f3655h = gVar;
        this.f3656i = cVar;
        this.f3657j = i1Var;
        this.f3658k = i1Var2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public e(e0 e0Var, a4.c cVar, t3.c cVar2) {
        super(2, cVar2);
        this.f3657j = e0Var;
        this.f3658k = (v3.i) cVar;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [a4.c, v3.i] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3651d) {
            case 0:
                e eVar = new e((k4.g) this.f3655h, (c) this.f3656i, (i1) this.f3657j, (i1) this.f3658k, cVar);
                eVar.f3652e = obj;
                return eVar;
            case 1:
                e eVar2 = new e((e0) this.f3657j, (v3.i) this.f3658k, cVar);
                eVar2.f3656i = obj;
                return eVar2;
            default:
                e eVar3 = new e((b4.w) this.f3654g, (l2) this.f3655h, (androidx.lifecycle.v) this.f3656i, (a3) this.f3657j, (View) this.f3658k, cVar);
                eVar3.f3652e = obj;
                return eVar3;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3651d) {
            case 0:
                break;
            case 1:
                break;
        }
        return ((e) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:113:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:116:0x020c  */
    /* JADX WARN: Code duplicated, block: B:118:0x020f  */
    /* JADX WARN: Code duplicated, block: B:119:0x0211  */
    /* JADX WARN: Code duplicated, block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [a4.c] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x01f3 -> B:111:0x01f6). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
