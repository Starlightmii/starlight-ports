package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x1 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f1417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1418f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1419g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ e2.f0 f1420h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q.k f1421i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ a4.e f1422j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ a4.e f1423k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a4.e f1424l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ k4 f1425m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1426n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x1(String str, boolean z2, boolean z3, e2.f0 f0Var, q.k kVar, a4.e eVar, a4.e eVar2, a4.e eVar3, k4 k4Var, y0.q0 q0Var) {
        super(3);
        this.f1417e = str;
        this.f1418f = z2;
        this.f1419g = z3;
        this.f1420h = f0Var;
        this.f1421i = kVar;
        this.f1422j = eVar;
        this.f1423k = eVar2;
        this.f1424l = eVar3;
        this.f1425m = k4Var;
        this.f1426n = q0Var;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0032  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        a4.e eVar = (a4.e) obj;
        f0.p pVar = (f0.p) obj2;
        int iIntValue = ((Number) obj3).intValue();
        if ((iIntValue & 6) == 0) {
            iIntValue |= ((f0.v) pVar).h(eVar) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                int i5 = iIntValue;
                v1 v1Var = v1.f1366a;
                y0.q0 q0Var = this.f1426n;
                boolean z2 = this.f1418f;
                q.k kVar = this.f1421i;
                k4 k4Var = this.f1425m;
                v1Var.b(this.f1417e, eVar, z2, this.f1419g, this.f1420h, kVar, this.f1422j, this.f1423k, this.f1424l, k4Var, null, n0.i.c(2108828640, new w1(z2, kVar, k4Var, q0Var), pVar), pVar, (i5 << 3) & 112);
            }
        } else {
            int i6 = iIntValue;
            v1 v1Var2 = v1.f1366a;
            y0.q0 q0Var2 = this.f1426n;
            boolean z3 = this.f1418f;
            q.k kVar2 = this.f1421i;
            k4 k4Var2 = this.f1425m;
            v1Var2.b(this.f1417e, eVar, z3, this.f1419g, this.f1420h, kVar2, this.f1422j, this.f1423k, this.f1424l, k4Var2, null, n0.i.c(2108828640, new w1(z3, kVar2, k4Var2, q0Var2), pVar), pVar, (i6 << 3) & 112);
        }
        return p3.n.f5026a;
    }
}
