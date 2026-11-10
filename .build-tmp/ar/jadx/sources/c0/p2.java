package c0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p2 f1155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p2 f1156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p2 f1157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p2 f1158g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p2 f1159h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ p2[] f1160i;

    static {
        p2 p2Var = new p2("TopBar", 0);
        f1155d = p2Var;
        p2 p2Var2 = new p2("MainContent", 1);
        f1156e = p2Var2;
        p2 p2Var3 = new p2("Snackbar", 2);
        f1157f = p2Var3;
        p2 p2Var4 = new p2("Fab", 3);
        f1158g = p2Var4;
        p2 p2Var5 = new p2("BottomBar", 4);
        f1159h = p2Var5;
        f1160i = new p2[]{p2Var, p2Var2, p2Var3, p2Var4, p2Var5};
    }

    public static p2 valueOf(String str) {
        return (p2) Enum.valueOf(p2.class, str);
    }

    public static p2[] values() {
        return (p2[]) f1160i.clone();
    }
}
