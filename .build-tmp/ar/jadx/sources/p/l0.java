package p;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final l0 f4525d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final l0 f4526e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ l0[] f4527f;

    static {
        l0 l0Var = new l0("Vertical", 0);
        f4525d = l0Var;
        l0 l0Var2 = new l0("Horizontal", 1);
        f4526e = l0Var2;
        f4527f = new l0[]{l0Var, l0Var2};
    }

    public static l0 valueOf(String str) {
        return (l0) Enum.valueOf(l0.class, str);
    }

    public static l0[] values() {
        return (l0[]) f4527f.clone();
    }
}
