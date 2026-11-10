package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class PastedIntent {
    private final String action;
    private final String category;
    private final String className;
    private final String extra;
    private final String packageName;

    public PastedIntent() {
        this(null, null, null, null, null, 31, null);
    }

    public PastedIntent(String str, String str2, String str3, String str4, String str5) {
        b4.l.e("action", str);
        b4.l.e("packageName", str2);
        b4.l.e("className", str3);
        b4.l.e("category", str4);
        b4.l.e("extra", str5);
        this.action = str;
        this.packageName = str2;
        this.className = str3;
        this.category = str4;
        this.extra = str5;
    }

    public /* synthetic */ PastedIntent(String str, String str2, String str3, String str4, String str5, int i5, b4.g gVar) {
        this((i5 & 1) != 0 ? "" : str, (i5 & 2) != 0 ? "" : str2, (i5 & 4) != 0 ? "" : str3, (i5 & 8) != 0 ? "" : str4, (i5 & 16) != 0 ? "" : str5);
    }

    public static /* synthetic */ PastedIntent copy$default(PastedIntent pastedIntent, String str, String str2, String str3, String str4, String str5, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            str = pastedIntent.action;
        }
        if ((i5 & 2) != 0) {
            str2 = pastedIntent.packageName;
        }
        if ((i5 & 4) != 0) {
            str3 = pastedIntent.className;
        }
        if ((i5 & 8) != 0) {
            str4 = pastedIntent.category;
        }
        if ((i5 & 16) != 0) {
            str5 = pastedIntent.extra;
        }
        String str6 = str5;
        String str7 = str3;
        return pastedIntent.copy(str, str2, str7, str4, str6);
    }

    public final String component1() {
        return this.action;
    }

    public final String component2() {
        return this.packageName;
    }

    public final String component3() {
        return this.className;
    }

    public final String component4() {
        return this.category;
    }

    public final String component5() {
        return this.extra;
    }

    public final PastedIntent copy(String str, String str2, String str3, String str4, String str5) {
        b4.l.e("action", str);
        b4.l.e("packageName", str2);
        b4.l.e("className", str3);
        b4.l.e("category", str4);
        b4.l.e("extra", str5);
        return new PastedIntent(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PastedIntent)) {
            return false;
        }
        PastedIntent pastedIntent = (PastedIntent) obj;
        return b4.l.a(this.action, pastedIntent.action) && b4.l.a(this.packageName, pastedIntent.packageName) && b4.l.a(this.className, pastedIntent.className) && b4.l.a(this.category, pastedIntent.category) && b4.l.a(this.extra, pastedIntent.extra);
    }

    public final String getAction() {
        return this.action;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getExtra() {
        return this.extra;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return this.extra.hashCode() + ((this.category.hashCode() + ((this.className.hashCode() + ((this.packageName.hashCode() + (this.action.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "PastedIntent(action=" + this.action + ", packageName=" + this.packageName + ", className=" + this.className + ", category=" + this.category + ", extra=" + this.extra + ")";
    }
}
