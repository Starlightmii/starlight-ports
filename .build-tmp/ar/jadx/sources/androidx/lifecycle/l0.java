package androidx.lifecycle;

import android.os.Bundle;
import android.view.View;
import io.github.andrea_lyz.assistrestore.R;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k0 f262a = new k0(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k0 f263b = new k0(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k0 f264c = new k0(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k0 f265d = new k0(4);

    public static final void a(r0 r0Var, j3.e eVar, q qVar) {
        AutoCloseable autoCloseable;
        b4.l.e("registry", eVar);
        b4.l.e("lifecycle", qVar);
        h3.a aVar = r0Var.f287a;
        if (aVar != null) {
            synchronized (aVar.f2619a) {
                autoCloseable = (AutoCloseable) aVar.f2620b.get("androidx.lifecycle.savedstate.vm.tag");
            }
        } else {
            autoCloseable = null;
        }
        i0 i0Var = (i0) autoCloseable;
        if (i0Var == null || i0Var.f259f) {
            return;
        }
        i0Var.e(qVar, eVar);
        p pVar = ((x) qVar).f298c;
        if (pVar == p.f275e || pVar.compareTo(p.f277g) >= 0) {
            eVar.c();
        } else {
            qVar.a(new h(qVar, eVar));
        }
    }

    public static h0 b(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            bundle = bundle2;
        }
        if (bundle == null) {
            h0 h0Var = new h0();
            new LinkedHashMap();
            h0Var.f256a = new f3.a(q3.t.f5478d);
            return h0Var;
        }
        ClassLoader classLoader = h0.class.getClassLoader();
        b4.l.b(classLoader);
        bundle.setClassLoader(classLoader);
        r3.f fVar = new r3.f(bundle.size());
        for (String str : bundle.keySet()) {
            b4.l.b(str);
            fVar.put(str, bundle.get(str));
        }
        fVar.b();
        fVar.f5737p = true;
        if (fVar.f5733l <= 0) {
            fVar = r3.f.f5724q;
            b4.l.c("null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>", fVar);
        }
        h0 h0Var2 = new h0();
        new LinkedHashMap();
        h0Var2.f256a = new f3.a(fVar);
        return h0Var2;
    }

    public static final v c(View view) {
        b4.l.e("<this>", view);
        while (view != null) {
            Object tag = view.getTag(R.id.view_tree_lifecycle_owner);
            v vVar = tag instanceof v ? (v) tag : null;
            if (vVar != null) {
                return vVar;
            }
            Object objN = q.f.n(view);
            view = objN instanceof View ? (View) objN : null;
        }
        return null;
    }

    public static final w0 d(View view) {
        b4.l.e("<this>", view);
        while (view != null) {
            Object tag = view.getTag(R.id.view_tree_view_model_store_owner);
            w0 w0Var = tag instanceof w0 ? (w0) tag : null;
            if (w0Var != null) {
                return w0Var;
            }
            Object objN = q.f.n(view);
            view = objN instanceof View ? (View) objN : null;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00ab A[Catch: all -> 0x006d, TRY_LEAVE, TryCatch #1 {all -> 0x006d, blocks: (B:8:0x0031, B:10:0x004a, B:16:0x0067, B:19:0x006f, B:20:0x0083, B:26:0x009c, B:28:0x00ab, B:22:0x008a, B:24:0x0093, B:11:0x0053, B:13:0x0059, B:14:0x0061), top: B:36:0x0031, inners: #2 }] */
    public static final o0 e(w0 w0Var) {
        r0 o0Var;
        boolean zIsInstance;
        r0 r0VarA;
        r0 r0Var;
        j0 j0Var = new j0();
        g3.b defaultViewModelCreationExtras = w0Var instanceof k ? ((k) w0Var).getDefaultViewModelCreationExtras() : g3.a.f2546b;
        b4.l.e("extras", defaultViewModelCreationExtras);
        v0 viewModelStore = w0Var.getViewModelStore();
        b4.l.e("store", viewModelStore);
        k0 k0Var = new k0(18);
        b4.f fVarA = b4.x.a(o0.class);
        synchronized (k0Var) {
            try {
                o0Var = (r0) viewModelStore.f293a.get("androidx.lifecycle.internal.SavedStateHandlesVM");
                Class clsY = fVarA.f687a;
                Map map = b4.f.f686b;
                b4.l.c("null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>", map);
                Integer num = (Integer) map.get(clsY);
                if (num != null) {
                    zIsInstance = b4.z.d(num.intValue(), o0Var);
                } else {
                    if (clsY.isPrimitive()) {
                        clsY = p3.j.y(b4.x.a(clsY));
                    }
                    zIsInstance = clsY.isInstance(o0Var);
                }
                if (zIsInstance) {
                    b4.l.c("null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel", o0Var);
                } else {
                    LinkedHashMap linkedHashMap = defaultViewModelCreationExtras.f2547a;
                    b4.l.e("initialExtras", linkedHashMap);
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    linkedHashMap2.putAll(linkedHashMap);
                    linkedHashMap2.put(f265d, "androidx.lifecycle.internal.SavedStateHandlesVM");
                    try {
                        try {
                            o0Var = new o0();
                        } catch (AbstractMethodError unused) {
                            r0VarA = j0Var.a(p3.j.x(fVarA));
                            o0Var = r0VarA;
                            b4.l.e("viewModel", o0Var);
                            r0Var = (r0) viewModelStore.f293a.put("androidx.lifecycle.internal.SavedStateHandlesVM", o0Var);
                            if (r0Var != null) {
                                r0Var.a();
                            }
                            return (o0) o0Var;
                        }
                    } catch (AbstractMethodError unused2) {
                        r0VarA = j0Var.a(p3.j.x(fVarA));
                        o0Var = r0VarA;
                    }
                    b4.l.e("viewModel", o0Var);
                    r0Var = (r0) viewModelStore.f293a.put("androidx.lifecycle.internal.SavedStateHandlesVM", o0Var);
                    if (r0Var != null) {
                        r0Var.a();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return (o0) o0Var;
    }
}
