package j2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f3318d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f3319e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ h[] f3320f;

    static {
        h hVar = new h("Ltr", 0);
        f3318d = hVar;
        h hVar2 = new h("Rtl", 1);
        f3319e = hVar2;
        f3320f = new h[]{hVar, hVar2};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f3320f.clone();
    }
}
