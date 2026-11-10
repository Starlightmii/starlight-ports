package n0;

import b4.z;
import f0.b2;
import f0.f0;
import f0.p;
import f0.v;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4057d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4058e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f4059f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b2 f4060g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f4061h;

    public d(int i5, boolean z2, Object obj) {
        this.f4057d = i5;
        this.f4058e = z2;
        this.f4059f = obj;
    }

    @Override // a4.f
    public final /* bridge */ /* synthetic */ Object b(Object obj, Object obj2, Object obj3) {
        return e(obj, (p) obj2, ((Number) obj3).intValue());
    }

    public final Object d(p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(this.f4057d);
        f(vVar);
        int iA = i5 | (vVar.f(this) ? i.a(2, 0) : i.a(1, 0));
        Object obj = this.f4059f;
        b4.l.c("null cannot be cast to non-null type kotlin.Function2<@[ParameterName(name = \"c\")] androidx.compose.runtime.Composer, @[ParameterName(name = \"changed\")] kotlin.Int, kotlin.Any?>", obj);
        z.c(2, obj);
        Object objInvoke = ((a4.e) obj).invoke(vVar, Integer.valueOf(iA));
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c(2, d.class, this, "invoke", "invoke(Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;");
        }
        return objInvoke;
    }

    public final Object e(Object obj, p pVar, int i5) {
        v vVar = (v) pVar;
        vVar.X(this.f4057d);
        f(vVar);
        int iA = vVar.f(this) ? i.a(2, 1) : i.a(1, 1);
        Object obj2 = this.f4059f;
        b4.l.c("null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"p1\")] kotlin.Any?, @[ParameterName(name = \"c\")] androidx.compose.runtime.Composer, @[ParameterName(name = \"changed\")] kotlin.Int, kotlin.Any?>", obj2);
        z.c(3, obj2);
        Object objB = ((a4.f) obj2).b(obj, vVar, Integer.valueOf(iA | i5));
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f0(i5, 2, this, obj);
        }
        return objB;
    }

    public final void f(p pVar) {
        v vVar;
        b2 b2VarW;
        if (!this.f4058e || (b2VarW = (vVar = (v) pVar).w()) == null) {
            return;
        }
        vVar.getClass();
        b2VarW.f2096b |= 1;
        if (i.d(this.f4060g, b2VarW)) {
            this.f4060g = b2VarW;
            return;
        }
        ArrayList arrayList = this.f4061h;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            this.f4061h = arrayList2;
            arrayList2.add(b2VarW);
            return;
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (i.d((b2) arrayList.get(i5), b2VarW)) {
                arrayList.set(i5, b2VarW);
                return;
            }
        }
        arrayList.add(b2VarW);
    }

    @Override // a4.e
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return d((p) obj, ((Number) obj2).intValue());
    }
}
