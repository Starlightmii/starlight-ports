package i4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final w f2779d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w f2780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f2781f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w f2782g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ w[] f2783h;

    static {
        w wVar = new w("DEFAULT", 0);
        f2779d = wVar;
        w wVar2 = new w("LAZY", 1);
        f2780e = wVar2;
        w wVar3 = new w("ATOMIC", 2);
        f2781f = wVar3;
        w wVar4 = new w("UNDISPATCHED", 3);
        f2782g = wVar4;
        f2783h = new w[]{wVar, wVar2, wVar3, wVar4};
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) f2783h.clone();
    }
}
