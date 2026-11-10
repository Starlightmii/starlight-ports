package com.oplus.systemui.qs.external;

import android.os.IBinder;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InnerCustomTileConnection$deathRecipient$1 implements IBinder.DeathRecipient {
    public /* synthetic */ InnerCustomTileConnection this$0;

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        String str = this.this$0.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->binderDied", "SystemUi--QuickSettings");
        }
        this.this$0.handleDeath();
    }
}
