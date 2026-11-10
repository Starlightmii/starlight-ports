package com.oplus.systemui.utils;

import android.content.res.Configuration;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusThemeController$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusThemeController this$0;

    public final void onConfigChanged(Configuration configuration) {
        boolean z;
        int i = configuration.uiMode & 48;
        OplusThemeController oplusThemeController = this.this$0;
        oplusThemeController.setThirdTheme(oplusThemeController.isCustomTheme(oplusThemeController.context));
        if (oplusThemeController.getUiMode() != i) {
            z = oplusThemeController.isThirdTheme;
            oplusThemeController.setUiMode(i);
        } else {
            z = false;
        }
        int themeChanged = oplusThemeController.getThemeChanged(oplusThemeController.context);
        if (oplusThemeController.getThemeChanged() != themeChanged || z) {
            oplusThemeController.setThemeChanged(themeChanged);
            oplusThemeController.notifyThemeChanged();
        }
    }
}
