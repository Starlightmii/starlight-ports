package j3;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import q3.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f3338a = new LinkedHashSet();

    public a(e eVar) {
        eVar.b("androidx.savedstate.Restarter", this);
    }

    @Override // j3.d
    public final Bundle a() {
        Bundle bundleG = q.f.g((p3.f[]) Arrays.copyOf(new p3.f[0], 0));
        List listF0 = l.f0(this.f3338a);
        bundleG.putStringArrayList("classes_to_restore", listF0 instanceof ArrayList ? (ArrayList) listF0 : new ArrayList<>(listF0));
        return bundleG;
    }
}
