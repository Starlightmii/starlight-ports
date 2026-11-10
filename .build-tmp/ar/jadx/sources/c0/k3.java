package c0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k3 f984d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ k3[] f985e;

    static {
        k3 k3Var = new k3("Dismissed", 0);
        f984d = k3Var;
        f985e = new k3[]{k3Var, new k3("ActionPerformed", 1)};
    }

    public static k3 valueOf(String str) {
        return (k3) Enum.valueOf(k3.class, str);
    }

    public static k3[] values() {
        return (k3[]) f985e.clone();
    }
}
