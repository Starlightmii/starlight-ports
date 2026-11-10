package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.lang.reflect.Constructor;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s0 f281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f282c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j3.e f284e;

    public p0(Application application, MainActivity mainActivity, Bundle bundle) {
        s0 s0Var;
        this.f284e = mainActivity.getSavedStateRegistry();
        this.f283d = mainActivity.getLifecycle();
        this.f282c = bundle;
        this.f280a = application;
        if (application != null) {
            if (s0.f289c == null) {
                s0.f289c = new s0(application);
            }
            s0Var = s0.f289c;
            b4.l.b(s0Var);
        } else {
            s0Var = new s0(null);
        }
        this.f281b = s0Var;
    }

    @Override // androidx.lifecycle.t0
    public final r0 a(Class cls) {
        AutoCloseable autoCloseable;
        Application application;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        q qVar = this.f283d;
        if (qVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || this.f280a == null) ? q0.a(cls, q0.f286b) : q0.a(cls, q0.f285a);
        if (constructorA == null) {
            if (this.f280a != null) {
                return this.f281b.a(cls);
            }
            if (u0.f292a == null) {
                u0.f292a = new u0();
            }
            b4.l.b(u0.f292a);
            return d4.a.s(cls);
        }
        j3.e eVar = this.f284e;
        b4.l.b(eVar);
        h0 h0VarB = l0.b(eVar.a(canonicalName), this.f282c);
        i0 i0Var = new i0(canonicalName, h0VarB);
        i0Var.e(qVar, eVar);
        p pVar = ((x) qVar).f298c;
        if (pVar == p.f275e || pVar.compareTo(p.f277g) >= 0) {
            eVar.c();
        } else {
            qVar.a(new h(qVar, eVar));
        }
        r0 r0VarB = (!zIsAssignableFrom || (application = this.f280a) == null) ? q0.b(cls, constructorA, h0VarB) : q0.b(cls, constructorA, application, h0VarB);
        r0VarB.getClass();
        h3.a aVar = r0VarB.f287a;
        if (aVar == null) {
            return r0VarB;
        }
        if (aVar.f2622d) {
            h3.a.a(i0Var);
            return r0VarB;
        }
        synchronized (aVar.f2619a) {
            autoCloseable = (AutoCloseable) aVar.f2620b.put("androidx.lifecycle.savedstate.vm.tag", i0Var);
        }
        h3.a.a(autoCloseable);
        return r0VarB;
    }
}
