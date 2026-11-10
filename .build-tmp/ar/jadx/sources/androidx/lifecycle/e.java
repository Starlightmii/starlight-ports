package androidx.lifecycle;

import android.os.Bundle;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f248d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f249e;

    public /* synthetic */ e(int i5, Object obj) {
        this.f248d = i5;
        this.f249e = obj;
    }

    @Override // androidx.lifecycle.t
    public final void b(v vVar, o oVar) {
        switch (this.f248d) {
            case 0:
                new HashMap();
                j[] jVarArr = (j[]) this.f249e;
                if (jVarArr.length > 0) {
                    j jVar = jVarArr[0];
                    throw null;
                }
                if (jVarArr.length <= 0) {
                    return;
                }
                j jVar2 = jVarArr[0];
                throw null;
            default:
                if (oVar != o.ON_CREATE) {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + oVar).toString());
                }
                vVar.getLifecycle().b(this);
                n0 n0Var = (n0) this.f249e;
                if (n0Var.f270b) {
                    return;
                }
                Bundle bundleA = n0Var.f269a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
                Bundle bundleG = q.f.g((p3.f[]) Arrays.copyOf(new p3.f[0], 0));
                Bundle bundle = n0Var.f271c;
                if (bundle != null) {
                    bundleG.putAll(bundle);
                }
                if (bundleA != null) {
                    bundleG.putAll(bundleA);
                }
                n0Var.f271c = bundleG;
                n0Var.f270b = true;
                return;
        }
    }
}
