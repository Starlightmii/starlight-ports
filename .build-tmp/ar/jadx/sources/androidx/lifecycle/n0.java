package androidx.lifecycle;

import android.os.Bundle;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 implements j3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j3.e f269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Bundle f271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p3.k f272d;

    public n0(j3.e eVar, MainActivity mainActivity) {
        b4.l.e("savedStateRegistry", eVar);
        this.f269a = eVar;
        this.f272d = h1.c.E(new m0(mainActivity, 0));
    }

    @Override // j3.d
    public final Bundle a() {
        Bundle bundleG = q.f.g((p3.f[]) Arrays.copyOf(new p3.f[0], 0));
        Bundle bundle = this.f271c;
        if (bundle != null) {
            bundleG.putAll(bundle);
        }
        for (Map.Entry entry : ((o0) this.f272d.getValue()).f273b.entrySet()) {
            String str = (String) entry.getKey();
            Bundle bundleA = ((b.g) ((h0) entry.getValue()).f256a.f2453e).a();
            if (!bundleA.isEmpty()) {
                b4.l.e("key", str);
                bundleG.putBundle(str, bundleA);
            }
        }
        this.f270b = false;
        return bundleG;
    }
}
