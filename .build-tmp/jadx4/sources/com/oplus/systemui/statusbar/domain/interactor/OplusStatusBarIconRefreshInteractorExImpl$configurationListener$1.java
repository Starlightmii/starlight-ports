package com.oplus.systemui.statusbar.domain.interactor;

import android.content.res.Configuration;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.utils.OplusThemeControllerKt;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIconRefreshInteractorExImpl$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusStatusBarIconRefreshInteractorExImpl this$0;

    public final void onConfigChanged(Configuration configuration) {
        boolean zIsOplusCustomTheme;
        int i = configuration.uiMode & 48;
        OplusStatusBarIconRefreshInteractorExImpl oplusStatusBarIconRefreshInteractorExImpl = this.this$0;
        if (oplusStatusBarIconRefreshInteractorExImpl.uiMode != i) {
            zIsOplusCustomTheme = OplusThemeControllerKt.isOplusCustomTheme(configuration);
            oplusStatusBarIconRefreshInteractorExImpl.uiMode = i;
        } else {
            zIsOplusCustomTheme = false;
        }
        int iOplusThirdThemeChange = OplusThemeControllerKt.oplusThirdThemeChange(configuration);
        int i2 = oplusStatusBarIconRefreshInteractorExImpl.oplusThemeChanged;
        if (i2 != iOplusThirdThemeChange || zIsOplusCustomTheme) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusStatusBarIconRefreshInteractorExImpl", TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, iOplusThirdThemeChange, "notifyThemeChanged: oplusThemeChanged=", ", currentThemeChanged="), null, 8);
            oplusStatusBarIconRefreshInteractorExImpl.oplusThemeChanged = iOplusThirdThemeChange;
            oplusStatusBarIconRefreshInteractorExImpl.statusBarIconController.refreshIconGroupsExcludeKeyGuard(oplusStatusBarIconRefreshInteractorExImpl.displayId);
        }
    }
}
