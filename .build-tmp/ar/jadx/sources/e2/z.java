package e2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final z f2067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f2068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final z f2069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final z f2070g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ z[] f2071h;

    static {
        z zVar = new z("StartInput", 0);
        f2067d = zVar;
        z zVar2 = new z("StopInput", 1);
        f2068e = zVar2;
        z zVar3 = new z("ShowKeyboard", 2);
        f2069f = zVar3;
        z zVar4 = new z("HideKeyboard", 3);
        f2070g = zVar4;
        f2071h = new z[]{zVar, zVar2, zVar3, zVar4};
    }

    public static z valueOf(String str) {
        return (z) Enum.valueOf(z.class, str);
    }

    public static z[] values() {
        return (z[]) f2071h.clone();
    }
}
