package p1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final n1 f4938d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n1 f4939e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n1 f4940f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ n1[] f4941g;

    static {
        n1 n1Var = new n1("ContinueTraversal", 0);
        f4938d = n1Var;
        n1 n1Var2 = new n1("SkipSubtreeAndContinueTraversal", 1);
        f4939e = n1Var2;
        n1 n1Var3 = new n1("CancelTraversal", 2);
        f4940f = n1Var3;
        f4941g = new n1[]{n1Var, n1Var2, n1Var3};
    }

    public static n1 valueOf(String str) {
        return (n1) Enum.valueOf(n1.class, str);
    }

    public static n1[] values() {
        return (n1[]) f4941g.clone();
    }
}
