package b;

import android.window.OnBackInvokedDispatcher;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements androidx.lifecycle.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f337d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f338e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f339f;

    public /* synthetic */ i(Object obj, int i5, Object obj2) {
        this.f337d = i5;
        this.f338e = obj;
        this.f339f = obj2;
    }

    @Override // androidx.lifecycle.t
    public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        androidx.lifecycle.o oVar2;
        switch (this.f337d) {
            case 0:
                i0 i0Var = (i0) this.f338e;
                r rVar = (r) this.f339f;
                b4.l.e("$dispatcher", i0Var);
                if (oVar == androidx.lifecycle.o.ON_CREATE) {
                    OnBackInvokedDispatcher onBackInvokedDispatcherA = j.f347a.a(rVar);
                    b4.l.e("invoker", onBackInvokedDispatcherA);
                    i0Var.f344e = onBackInvokedDispatcherA;
                    i0Var.c(i0Var.f346g);
                }
                break;
            default:
                w2.h hVar = (w2.h) this.f338e;
                androidx.lifecycle.p pVar = (androidx.lifecycle.p) this.f339f;
                hVar.getClass();
                e eVar = hVar.f6395a;
                CopyOnWriteArrayList copyOnWriteArrayList = hVar.f6396b;
                androidx.lifecycle.o.Companion.getClass();
                int iOrdinal = pVar.ordinal();
                if (iOrdinal == 2) {
                    oVar2 = androidx.lifecycle.o.ON_CREATE;
                } else if (iOrdinal != 3) {
                    oVar2 = iOrdinal != 4 ? null : androidx.lifecycle.o.ON_RESUME;
                } else {
                    oVar2 = androidx.lifecycle.o.ON_START;
                }
                if (oVar != oVar2) {
                    androidx.lifecycle.o oVar3 = androidx.lifecycle.o.ON_DESTROY;
                    if (oVar != oVar3) {
                        int iOrdinal2 = pVar.ordinal();
                        if (iOrdinal2 != 2) {
                            oVar3 = iOrdinal2 != 3 ? iOrdinal2 != 4 ? null : androidx.lifecycle.o.ON_PAUSE : androidx.lifecycle.o.ON_STOP;
                        }
                        if (oVar == oVar3) {
                            copyOnWriteArrayList.remove((Object) null);
                            eVar.run();
                        }
                    } else {
                        hVar.a();
                    }
                } else {
                    copyOnWriteArrayList.add(null);
                    eVar.run();
                }
                break;
        }
    }
}
