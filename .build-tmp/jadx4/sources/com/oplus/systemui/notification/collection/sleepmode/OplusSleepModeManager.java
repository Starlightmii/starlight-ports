package com.oplus.systemui.notification.collection.sleepmode;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSleepModeManager implements CoreStartable {
    private final Context context;
    private final NotifPipeline pipeline;

    public OplusSleepModeManager(Context context, NotifPipeline notifPipeline) {
        this.context = context;
        this.pipeline = notifPipeline;
    }

    public final void start() {
        SleepModeHelper.Companion.getInstance().init$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(this.context);
    }
}
