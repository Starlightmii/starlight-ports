package d0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f1588d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ v[] f1589e;

    /* JADX INFO: Fake field, exist only in values array */
    v EF0;

    static {
        v vVar = new v("Filled", 0);
        v vVar2 = new v("Outlined", 1);
        f1588d = vVar2;
        f1589e = new v[]{vVar, vVar2};
    }

    public static v valueOf(String str) {
        return (v) Enum.valueOf(v.class, str);
    }

    public static v[] values() {
        return (v[]) f1589e.clone();
    }
}
