package l4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final w f3591d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w f3592e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f3593f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ w[] f3594g;

    static {
        w wVar = new w("START", 0);
        f3591d = wVar;
        w wVar2 = new w("STOP", 1);
        f3592e = wVar2;
        w wVar3 = new w("STOP_AND_RESET_REPLAY_CACHE", 2);
        f3593f = wVar3;
        f3594g = new w[]{wVar, wVar2, wVar3};
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) f3594g.clone();
    }
}
