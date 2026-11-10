package m;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f3662a;

    static {
        Float fValueOf = Float.valueOf(0.5f);
        a1 a1Var = b1.f3621b;
        Float fValueOf2 = Float.valueOf(1.0f);
        p3.f fVar = new p3.f(a1Var, fValueOf2);
        p3.f fVar2 = new p3.f(b1.f3627h, fValueOf2);
        p3.f fVar3 = new p3.f(b1.f3626g, fValueOf2);
        p3.f fVar4 = new p3.f(b1.f3620a, Float.valueOf(0.01f));
        p3.f fVar5 = new p3.f(b1.f3628i, fValueOf);
        p3.f fVar6 = new p3.f(b1.f3624e, fValueOf);
        p3.f fVar7 = new p3.f(b1.f3625f, fValueOf);
        a1 a1Var2 = b1.f3622c;
        Float fValueOf3 = Float.valueOf(0.1f);
        p3.f[] fVarArr = {fVar, fVar2, fVar3, fVar4, fVar5, fVar6, fVar7, new p3.f(a1Var2, fValueOf3), new p3.f(b1.f3623d, fValueOf3)};
        LinkedHashMap linkedHashMap = new LinkedHashMap(q3.v.F(9));
        q3.v.G(linkedHashMap, fVarArr);
        f3662a = linkedHashMap;
    }
}
