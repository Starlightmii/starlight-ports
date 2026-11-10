package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistantApp {
    public static final int $stable = 8;
    private final String detail;
    private final y0.c0 icon;
    private final String label;
    private final String packageName;

    public AssistantApp(String str, String str2, String str3, y0.c0 c0Var) {
        b4.l.e("packageName", str);
        b4.l.e("label", str2);
        b4.l.e("detail", str3);
        this.packageName = str;
        this.label = str2;
        this.detail = str3;
        this.icon = c0Var;
    }

    public static /* synthetic */ AssistantApp copy$default(AssistantApp assistantApp, String str, String str2, String str3, y0.c0 c0Var, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            str = assistantApp.packageName;
        }
        if ((i5 & 2) != 0) {
            str2 = assistantApp.label;
        }
        if ((i5 & 4) != 0) {
            str3 = assistantApp.detail;
        }
        if ((i5 & 8) != 0) {
            c0Var = assistantApp.icon;
        }
        return assistantApp.copy(str, str2, str3, c0Var);
    }

    public final String component1() {
        return this.packageName;
    }

    public final String component2() {
        return this.label;
    }

    public final String component3() {
        return this.detail;
    }

    public final y0.c0 component4() {
        return this.icon;
    }

    public final AssistantApp copy(String str, String str2, String str3, y0.c0 c0Var) {
        b4.l.e("packageName", str);
        b4.l.e("label", str2);
        b4.l.e("detail", str3);
        return new AssistantApp(str, str2, str3, c0Var);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AssistantApp)) {
            return false;
        }
        AssistantApp assistantApp = (AssistantApp) obj;
        return b4.l.a(this.packageName, assistantApp.packageName) && b4.l.a(this.label, assistantApp.label) && b4.l.a(this.detail, assistantApp.detail) && b4.l.a(this.icon, assistantApp.icon);
    }

    public final String getDetail() {
        return this.detail;
    }

    public final y0.c0 getIcon() {
        return this.icon;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        int iHashCode = (this.detail.hashCode() + ((this.label.hashCode() + (this.packageName.hashCode() * 31)) * 31)) * 31;
        y0.c0 c0Var = this.icon;
        return iHashCode + (c0Var == null ? 0 : c0Var.hashCode());
    }

    public String toString() {
        return "AssistantApp(packageName=" + this.packageName + ", label=" + this.label + ", detail=" + this.detail + ", icon=" + this.icon + ")";
    }
}
