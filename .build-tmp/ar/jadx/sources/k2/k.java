package k2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f3416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f3417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ k[] f3418f;

    static {
        k kVar = new k("Ltr", 0);
        f3416d = kVar;
        k kVar2 = new k("Rtl", 1);
        f3417e = kVar2;
        f3418f = new k[]{kVar, kVar2};
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) f3418f.clone();
    }
}
