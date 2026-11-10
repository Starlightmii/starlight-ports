package k4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f3435d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f3436e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f3437f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ a[] f3438g;

    static {
        a aVar = new a("SUSPEND", 0);
        f3435d = aVar;
        a aVar2 = new a("DROP_OLDEST", 1);
        f3436e = aVar2;
        a aVar3 = new a("DROP_LATEST", 2);
        f3437f = aVar3;
        f3438g = new a[]{aVar, aVar2, aVar3};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f3438g.clone();
    }
}
