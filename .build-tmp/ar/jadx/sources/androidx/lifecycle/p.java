package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p f274d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p f275e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p f276f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p f277g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p f278h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ p[] f279i;

    static {
        p pVar = new p("DESTROYED", 0);
        f274d = pVar;
        p pVar2 = new p("INITIALIZED", 1);
        f275e = pVar2;
        p pVar3 = new p("CREATED", 2);
        f276f = pVar3;
        p pVar4 = new p("STARTED", 3);
        f277g = pVar4;
        p pVar5 = new p("RESUMED", 4);
        f278h = pVar5;
        f279i = new p[]{pVar, pVar2, pVar3, pVar4, pVar5};
    }

    public static p valueOf(String str) {
        return (p) Enum.valueOf(p.class, str);
    }

    public static p[] values() {
        return (p[]) f279i.clone();
    }
}
