package f0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final y0 f2422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final y0 f2423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final y0 f2424f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final y0 f2425g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ y0[] f2426h;

    static {
        y0 y0Var = new y0("IGNORED", 0);
        f2422d = y0Var;
        y0 y0Var2 = new y0("SCHEDULED", 1);
        f2423e = y0Var2;
        y0 y0Var3 = new y0("DEFERRED", 2);
        f2424f = y0Var3;
        y0 y0Var4 = new y0("IMMINENT", 3);
        f2425g = y0Var4;
        f2426h = new y0[]{y0Var, y0Var2, y0Var3, y0Var4};
    }

    public static y0 valueOf(String str) {
        return (y0) Enum.valueOf(y0.class, str);
    }

    public static y0[] values() {
        return (y0[]) f2426h.clone();
    }
}
