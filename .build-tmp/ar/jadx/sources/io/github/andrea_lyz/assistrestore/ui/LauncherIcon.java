package io.github.andrea_lyz.assistrestore.ui;

import android.content.ComponentName;
import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class LauncherIcon {
    public static final int $stable = 0;
    public static final LauncherIcon INSTANCE = new LauncherIcon();
    private static final String LAUNCHER_ENTRY = ".LauncherEntry";

    private LauncherIcon() {
    }

    private final ComponentName component(Context context) {
        return new ComponentName(context.getPackageName(), context.getPackageName() + LAUNCHER_ENTRY);
    }

    public final boolean isHidden(Context context) {
        b4.l.e("context", context);
        return context.getPackageManager().getComponentEnabledSetting(component(context)) == 2;
    }

    public final void setHidden(Context context, boolean z2) {
        b4.l.e("context", context);
        context.getPackageManager().setComponentEnabledSetting(component(context), z2 ? 2 : 1, 1);
    }
}
