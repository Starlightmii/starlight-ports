package y1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f6771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f6772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final h f6773f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f6774g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f6775h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f6776i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final h f6777j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ h[] f6778k;

    static {
        h hVar = new h("Paragraph", 0);
        f6771d = hVar;
        h hVar2 = new h("Span", 1);
        f6772e = hVar2;
        h hVar3 = new h("VerbatimTts", 2);
        f6773f = hVar3;
        h hVar4 = new h("Url", 3);
        f6774g = hVar4;
        h hVar5 = new h("Link", 4);
        f6775h = hVar5;
        h hVar6 = new h("Clickable", 5);
        f6776i = hVar6;
        h hVar7 = new h("String", 6);
        f6777j = hVar7;
        f6778k = new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6, hVar7};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f6778k.clone();
    }
}
