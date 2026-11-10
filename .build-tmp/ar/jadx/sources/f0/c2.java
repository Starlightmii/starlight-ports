package f0;

import android.content.Context;
import io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt;
import io.github.libxposed.service.IXposedService;
import java.util.Map;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c2 implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2107d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2108e;

    public /* synthetic */ c2(int i5, Object obj) {
        this.f2107d = i5;
        this.f2108e = obj;
    }

    public /* synthetic */ c2(q4.c cVar, q4.b bVar) {
        this.f2107d = 8;
        this.f2108e = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2107d) {
            case 0:
                ((e0) this.f2108e).y(obj);
                return p3.n.f5026a;
            case 1:
                l2 l2Var = (l2) this.f2108e;
                Throwable th = (Throwable) obj;
                CancellationException cancellationException = new CancellationException("Recomposer effect job completed");
                cancellationException.initCause(th);
                synchronized (l2Var.f2245c) {
                    try {
                        i4.s0 s0Var = l2Var.f2246d;
                        if (s0Var != null) {
                            l4.d0 d0Var = l2Var.f2263u;
                            g2 g2Var = g2.f2178e;
                            d0Var.getClass();
                            d0Var.i(null, g2Var);
                            s0Var.b(cancellationException);
                            l2Var.f2260r = null;
                            s0Var.g(new e2(l2Var, 1, th));
                        } else {
                            l2Var.f2247e = cancellationException;
                            l4.d0 d0Var2 = l2Var.f2263u;
                            g2 g2Var2 = g2.f2177d;
                            d0Var2.getClass();
                            d0Var2.i(null, g2Var2);
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                return p3.n.f5026a;
            case 2:
                j.a0 a0Var = (j.a0) this.f2108e;
                if (obj instanceof p0.y) {
                    ((p0.y) obj).f(4);
                }
                a0Var.a(obj);
                return p3.n.f5026a;
            case 3:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$41$0((Context) this.f2108e, ((Boolean) obj).booleanValue());
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$46$0((h1) this.f2108e, ((Integer) obj).intValue());
            case 5:
                p0.t tVar = (p0.t) this.f2108e;
                synchronized (tVar.f4751g) {
                    p0.s sVar = tVar.f4753i;
                    b4.l.b(sVar);
                    Object obj2 = sVar.f4733b;
                    b4.l.b(obj2);
                    int i5 = sVar.f4735d;
                    j.x xVar = sVar.f4734c;
                    if (xVar == null) {
                        xVar = new j.x();
                        sVar.f4734c = xVar;
                        sVar.f4737f.i(obj2, xVar);
                    }
                    sVar.b(obj, i5, obj2, xVar);
                }
                return p3.n.f5026a;
            case 6:
                return obj == ((q3.a) this.f2108e) ? "(this Collection)" : String.valueOf(obj);
            case 7:
                q3.e eVar = (q3.e) this.f2108e;
                Map.Entry entry = (Map.Entry) obj;
                b4.l.e("it", entry);
                StringBuilder sb = new StringBuilder();
                Object key = entry.getKey();
                sb.append(key == eVar ? "(this Map)" : String.valueOf(key));
                sb.append('=');
                Object value = entry.getValue();
                sb.append(value != eVar ? String.valueOf(value) : "(this Map)");
                return sb.toString();
            default:
                ((q4.c) this.f2108e).d(null);
                return p3.n.f5026a;
        }
    }
}
