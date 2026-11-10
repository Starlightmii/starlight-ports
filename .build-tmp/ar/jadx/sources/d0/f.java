package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f f1530d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f1531e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f1532f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ f[] f1533g;

    static {
        f fVar = new f("Focused", 0);
        f1530d = fVar;
        f fVar2 = new f("UnfocusedEmpty", 1);
        f1531e = fVar2;
        f fVar3 = new f("UnfocusedNotEmpty", 2);
        f1532f = fVar3;
        f1533g = new f[]{fVar, fVar2, fVar3};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f1533g.clone();
    }
}
