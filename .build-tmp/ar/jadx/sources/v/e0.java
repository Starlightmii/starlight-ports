package v;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f5895d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e0 f5896e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e0 f5897f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ e0[] f5898g;

    static {
        e0 e0Var = new e0("Cursor", 0);
        f5895d = e0Var;
        e0 e0Var2 = new e0("SelectionStart", 1);
        f5896e = e0Var2;
        e0 e0Var3 = new e0("SelectionEnd", 2);
        f5897f = e0Var3;
        f5898g = new e0[]{e0Var, e0Var2, e0Var3};
    }

    public static e0 valueOf(String str) {
        return (e0) Enum.valueOf(e0.class, str);
    }

    public static e0[] values() {
        return (e0[]) f5898g.clone();
    }
}
