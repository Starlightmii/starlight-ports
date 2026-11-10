package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSpecialModeProvider$bindStub$1$2 implements Runnable {
    public /* synthetic */ Context $this_apply;
    public /* synthetic */ OplusQSSpecialModeProvider this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.colorfulSettingEnable = Settings.Secure.getIntForUser(this.$this_apply.getContentResolver(), "qs_tile_colorful_config", 0, this.$this_apply.getUserId()) == 0;
        this.this$0.updateColorfulConfigEnable();
    }
}
