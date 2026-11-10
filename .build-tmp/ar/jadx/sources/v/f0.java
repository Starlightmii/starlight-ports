package v;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f0 f5917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f0 f5918e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f0 f5919f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ f0[] f5920g;

    static {
        f0 f0Var = new f0("None", 0);
        f5917d = f0Var;
        f0 f0Var2 = new f0("Selection", 1);
        f5918e = f0Var2;
        f0 f0Var3 = new f0("Cursor", 2);
        f5919f = f0Var3;
        f5920g = new f0[]{f0Var, f0Var2, f0Var3};
    }

    public static f0 valueOf(String str) {
        return (f0) Enum.valueOf(f0.class, str);
    }

    public static f0[] values() {
        return (f0[]) f5920g.clone();
    }
}
