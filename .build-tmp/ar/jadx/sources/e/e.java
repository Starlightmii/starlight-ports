package e;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1643a = new LinkedHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1644b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1645c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient LinkedHashMap f1646d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f1647e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f1648f;

    public e() {
        new LinkedHashMap();
        this.f1645c = new ArrayList();
        this.f1646d = new LinkedHashMap();
        this.f1647e = new LinkedHashMap();
        this.f1648f = new Bundle();
    }

    public final boolean a(int i5, int i6, Intent intent) {
        String str = (String) this.f1643a.get(Integer.valueOf(i5));
        if (str == null) {
            return false;
        }
        b.b.l(this.f1646d.get(str));
        this.f1647e.remove(str);
        this.f1648f.putParcelable(str, new b(intent, i6));
        return true;
    }
}
