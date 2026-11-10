package x1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f6547d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f6548e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ a[] f6549f;

    static {
        a aVar = new a("On", 0);
        f6547d = aVar;
        a aVar2 = new a("Off", 1);
        f6548e = aVar2;
        f6549f = new a[]{aVar, aVar2, new a("Indeterminate", 2)};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f6549f.clone();
    }
}
