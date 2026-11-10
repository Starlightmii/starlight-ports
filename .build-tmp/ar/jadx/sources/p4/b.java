package p4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f5041d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f5042e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f5043f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f5044g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final b f5045h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ b[] f5046i;

    static {
        b bVar = new b("CPU_ACQUIRED", 0);
        f5041d = bVar;
        b bVar2 = new b("BLOCKING", 1);
        f5042e = bVar2;
        b bVar3 = new b("PARKING", 2);
        f5043f = bVar3;
        b bVar4 = new b("DORMANT", 3);
        f5044g = bVar4;
        b bVar5 = new b("TERMINATED", 4);
        f5045h = bVar5;
        f5046i = new b[]{bVar, bVar2, bVar3, bVar4, bVar5};
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f5046i.clone();
    }
}
