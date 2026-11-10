package f0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g2 f2177d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g2 f2178e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g2 f2179f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final g2 f2180g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final g2 f2181h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final g2 f2182i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ g2[] f2183j;

    static {
        g2 g2Var = new g2("ShutDown", 0);
        f2177d = g2Var;
        g2 g2Var2 = new g2("ShuttingDown", 1);
        f2178e = g2Var2;
        g2 g2Var3 = new g2("Inactive", 2);
        f2179f = g2Var3;
        g2 g2Var4 = new g2("InactivePendingWork", 3);
        f2180g = g2Var4;
        g2 g2Var5 = new g2("Idle", 4);
        f2181h = g2Var5;
        g2 g2Var6 = new g2("PendingWork", 5);
        f2182i = g2Var6;
        f2183j = new g2[]{g2Var, g2Var2, g2Var3, g2Var4, g2Var5, g2Var6};
    }

    public static g2 valueOf(String str) {
        return (g2) Enum.valueOf(g2.class, str);
    }

    public static g2[] values() {
        return (g2[]) f2183j.clone();
    }
}
