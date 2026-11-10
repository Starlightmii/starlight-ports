package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import android.os.Handler;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SeparateQsGuideComponent {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Builder {
        SeparateQsGuideComponent build();

        Builder inject(Context context);

        Builder inject(Handler handler);
    }

    OplusQsGuideSpiritualManager getGuideManager();
}
