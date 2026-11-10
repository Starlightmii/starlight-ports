package b;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q3.j f341b = new q3.j();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.d f342c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final OnBackInvokedCallback f343d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OnBackInvokedDispatcher f344e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f345f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f346g;

    public i0(e eVar) {
        OnBackInvokedCallback onBackInvokedCallbackA;
        this.f340a = eVar;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 33) {
            if (i5 >= 34) {
                onBackInvokedCallbackA = e0.f324a.a(new z(this, 0), new z(this, 1), new a0(this, 0), new a0(this, 1));
            } else {
                onBackInvokedCallbackA = c0.f317a.a(new a0(this, 2));
            }
            this.f343d = onBackInvokedCallbackA;
        }
    }

    public final void a() {
        Object objPrevious;
        if (this.f342c == null) {
            q3.j jVar = this.f341b;
            ListIterator listIterator = jVar.listIterator(jVar.a());
            if (listIterator.hasPrevious()) {
                objPrevious = listIterator.previous();
                ((c.d) objPrevious).getClass();
            } else {
                objPrevious = null;
            }
        }
        this.f342c = null;
    }

    public final void b() {
        Object objPrevious;
        c.d dVar = this.f342c;
        if (dVar == null) {
            q3.j jVar = this.f341b;
            ListIterator listIterator = jVar.listIterator(jVar.a());
            if (listIterator.hasPrevious()) {
                objPrevious = listIterator.previous();
                ((c.d) objPrevious).getClass();
            } else {
                objPrevious = null;
            }
            dVar = (c.d) objPrevious;
        }
        this.f342c = null;
        if (dVar != null) {
            ((a4.a) dVar.f709c.getValue()).a();
        } else {
            this.f340a.run();
        }
    }

    public final void c(boolean z2) {
        OnBackInvokedCallback onBackInvokedCallback;
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f344e;
        if (onBackInvokedDispatcher == null || (onBackInvokedCallback = this.f343d) == null) {
            return;
        }
        c0 c0Var = c0.f317a;
        if (z2 && !this.f345f) {
            c0Var.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f345f = true;
        } else {
            if (z2 || !this.f345f) {
                return;
            }
            c0Var.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f345f = false;
        }
    }

    public final void d() {
        boolean z2 = this.f346g;
        boolean z3 = false;
        q3.j jVar = this.f341b;
        if (jVar == null || !jVar.isEmpty()) {
            Iterator it = jVar.iterator();
            if (it.hasNext()) {
                ((c.d) it.next()).getClass();
                z3 = true;
            }
        }
        this.f346g = z3;
        if (z3 == z2 || Build.VERSION.SDK_INT < 33) {
            return;
        }
        c(z3);
    }
}
