package h3;

import androidx.lifecycle.k0;
import b.b;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k0 f2619a = new k0(18);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f2620b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f2621c = new LinkedHashSet();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f2622d;

    public static void a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                b.k(autoCloseable);
            } catch (Exception e5) {
                throw new RuntimeException(e5);
            }
        }
    }
}
