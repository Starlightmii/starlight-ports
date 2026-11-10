package j1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f3260d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f3261e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j f3262f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ j[] f3263g;

    static {
        j jVar = new j("Initial", 0);
        f3260d = jVar;
        j jVar2 = new j("Main", 1);
        f3261e = jVar2;
        j jVar3 = new j("Final", 2);
        f3262f = jVar3;
        f3263g = new j[]{jVar, jVar2, jVar3};
    }

    public static j valueOf(String str) {
        return (j) Enum.valueOf(j.class, str);
    }

    public static j[] values() {
        return (j[]) f3263g.clone();
    }
}
