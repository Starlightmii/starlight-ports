package w0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q f6258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q f6259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final q f6260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ q[] f6261g;

    static {
        q qVar = new q("Active", 0);
        f6258d = qVar;
        q qVar2 = new q("ActiveParent", 1);
        f6259e = qVar2;
        q qVar3 = new q("Captured", 2);
        q qVar4 = new q("Inactive", 3);
        f6260f = qVar4;
        f6261g = new q[]{qVar, qVar2, qVar3, qVar4};
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) f6261g.clone();
    }

    public final boolean a() {
        int iOrdinal = ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                return false;
            }
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    return false;
                }
                throw new i4.p();
            }
        }
        return true;
    }
}
