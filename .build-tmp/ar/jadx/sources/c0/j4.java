package c0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j4 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j4 f954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j4 f955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j4 f956f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ j4[] f957g;

    static {
        j4 j4Var = new j4("Tabs", 0);
        f954d = j4Var;
        j4 j4Var2 = new j4("Divider", 1);
        f955e = j4Var2;
        j4 j4Var3 = new j4("Indicator", 2);
        f956f = j4Var3;
        f957g = new j4[]{j4Var, j4Var2, j4Var3};
    }

    public static j4 valueOf(String str) {
        return (j4) Enum.valueOf(j4.class, str);
    }

    public static j4[] values() {
        return (j4[]) f957g.clone();
    }
}
