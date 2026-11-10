package q1;

import android.view.DragEvent;
import android.view.View;
import androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1;
import io.github.libxposed.service.IXposedService;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o1 implements View.OnDragListener, u0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u0.c f5297a = new u0.c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.g f5298b = new j.g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DragAndDropModifierOnDragListener$modifier$1 f5299c = new p1.q0() { // from class: androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1
        public final boolean equals(Object obj) {
            return obj == this;
        }

        @Override // p1.q0
        public final o h() {
            return this.f209a.f5297a;
        }

        public final int hashCode() {
            return this.f209a.f5297a.hashCode();
        }

        @Override // p1.q0
        public final /* bridge */ /* synthetic */ void i(o oVar) {
        }
    };

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnDragListener
    public final boolean onDrag(View view, DragEvent dragEvent) {
        a1.d dVar = new a1.d(19, dragEvent);
        int action = dragEvent.getAction();
        p1.n1 n1Var = p1.n1.f4938d;
        u0.c cVar = this.f5297a;
        switch (action) {
            case 1:
                b4.s sVar = new b4.s();
                j1.m mVar = new j1.m(dVar, cVar, sVar);
                if (mVar.c(cVar) == n1Var) {
                    p1.f.x(cVar, mVar);
                }
                boolean z2 = sVar.f693d;
                j.g gVar = this.f5298b;
                gVar.getClass();
                j.b bVar = new j.b(gVar);
                while (bVar.hasNext()) {
                    ((u0.c) bVar.next()).C0(dVar);
                }
                return z2;
            case 2:
                cVar.B0(dVar);
                return false;
            case 3:
                return cVar.y0(dVar);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                j1.n nVar = new j1.n(1, dVar);
                if (nVar.c(cVar) == n1Var) {
                    p1.f.x(cVar, nVar);
                    return false;
                }
                return false;
            case 5:
                cVar.z0(dVar);
                return false;
            case 6:
                cVar.A0(dVar);
                return false;
            default:
                return false;
        }
    }
}
