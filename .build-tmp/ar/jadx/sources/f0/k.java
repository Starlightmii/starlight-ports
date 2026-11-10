package f0;

import io.github.andrea_lyz.assistrestore.ui.ComposableSingletons$AssistRestoreAppKt;
import io.github.andrea_lyz.assistrestore.ui.ComposableSingletons$MainActivityKt;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2216d;

    public /* synthetic */ k(int i5) {
        this.f2216d = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        o0.c cVar;
        t3.b bVar;
        switch (this.f2216d) {
            case 0:
                p pVar = (p) obj;
                int iIntValue = ((Integer) obj2).intValue();
                v vVar = (v) pVar;
                if (!vVar.N(iIntValue & 1, (iIntValue & 3) != 2)) {
                    vVar.Q();
                }
                return p3.n.f5026a;
            case 1:
                p pVar2 = (p) obj;
                int iIntValue2 = ((Integer) obj2).intValue();
                v vVar2 = (v) pVar2;
                if (!vVar2.N(iIntValue2 & 1, (iIntValue2 & 3) != 2)) {
                    vVar2.Q();
                }
                return p3.n.f5026a;
            case 2:
                return ((t3.h) obj).k((t3.f) obj2);
            case 3:
                return ((t3.h) obj).k((t3.f) obj2);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 5:
                return ComposableSingletons$AssistRestoreAppKt.lambda_474000685$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 6:
                return ComposableSingletons$AssistRestoreAppKt.lambda__11935203$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 7:
                return ComposableSingletons$AssistRestoreAppKt.lambda__1855570913$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 8:
                return ComposableSingletons$AssistRestoreAppKt.lambda_1577810288$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 9:
                return ComposableSingletons$AssistRestoreAppKt.lambda__782868474$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 10:
                return ComposableSingletons$AssistRestoreAppKt.lambda_1304707464$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 11:
                return ComposableSingletons$AssistRestoreAppKt.lambda__2045808999$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 12:
                return ComposableSingletons$AssistRestoreAppKt.lambda__124485595$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 13:
                return ComposableSingletons$AssistRestoreAppKt.lambda_1963090343$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 14:
                return ComposableSingletons$AssistRestoreAppKt.lambda__1387426120$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 15:
                return ComposableSingletons$AssistRestoreAppKt.lambda_533897284$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 16:
                return ComposableSingletons$AssistRestoreAppKt.lambda__1673494074$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 17:
                return ComposableSingletons$AssistRestoreAppKt.lambda__729043241$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 18:
                return ComposableSingletons$AssistRestoreAppKt.lambda__1855166647$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 19:
                return ComposableSingletons$AssistRestoreAppKt.lambda_1387262298$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 20:
                return ComposableSingletons$MainActivityKt.lambda_1662132675$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 21:
                return ComposableSingletons$MainActivityKt.lambda_1706964085$lambda$0((p) obj, ((Integer) obj2).intValue());
            case 22:
                return Integer.valueOf(((Integer) obj).intValue() + 1);
            case 23:
                t3.f fVar = (t3.f) obj2;
                if (!(fVar instanceof i4.j1)) {
                    return obj;
                }
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int iIntValue3 = num != null ? num.intValue() : 1;
                return iIntValue3 == 0 ? fVar : Integer.valueOf(iIntValue3 + 1);
            case 24:
                i4.j1 j1Var = (i4.j1) obj;
                t3.f fVar2 = (t3.f) obj2;
                if (j1Var != null) {
                    return j1Var;
                }
                if (fVar2 instanceof i4.j1) {
                    return (i4.j1) fVar2;
                }
                return null;
            case 25:
                return (n4.u) obj;
            case 26:
                o0.b bVar2 = (o0.b) obj;
                v.c1 c1Var = (v.c1) obj2;
                List listH = p3.j.H(Float.valueOf(c1Var.f5869a.g()), Boolean.valueOf(((p.l0) c1Var.f5873e.getValue()) == p.l0.f4525d));
                int size = listH.size();
                for (int i5 = 0; i5 < size; i5++) {
                    Object obj3 = listH.get(i5);
                    if (obj3 != null && (cVar = bVar2.f4358e) != null && !cVar.a(obj3)) {
                        throw new IllegalArgumentException(("item at index " + i5 + " can't be saved: " + obj3).toString());
                    }
                }
                if (listH.isEmpty()) {
                    return null;
                }
                return new ArrayList(listH);
            case 27:
                return obj2;
            case 28:
                String str = (String) obj;
                t3.f fVar3 = (t3.f) obj2;
                b4.l.e("acc", str);
                b4.l.e("element", fVar3);
                if (str.length() == 0) {
                    return fVar3.toString();
                }
                return str + ", " + fVar3;
            default:
                t3.h hVar = (t3.h) obj;
                t3.f fVar4 = (t3.f) obj2;
                b4.l.e("acc", hVar);
                b4.l.e("element", fVar4);
                t3.h hVarF = hVar.f(fVar4.getKey());
                t3.i iVar = t3.i.f5807d;
                if (hVarF == iVar) {
                    return fVar4;
                }
                t3.d dVar = t3.d.f5806d;
                t3.e eVar = (t3.e) hVarF.j(dVar);
                if (eVar == null) {
                    bVar = new t3.b(fVar4, hVarF);
                } else {
                    t3.h hVarF2 = hVarF.f(dVar);
                    if (hVarF2 == iVar) {
                        return new t3.b(eVar, fVar4);
                    }
                    bVar = new t3.b(eVar, new t3.b(fVar4, hVarF2));
                }
                return bVar;
        }
    }
}
