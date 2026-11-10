package com.oplus.systemui.notification.statistic;

import android.app.AlarmManager;
import android.content.Context;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomRowTrackLinkHelper$startScheduleAlarm$1 implements AlarmManager.OnAlarmListener {
    public /* synthetic */ Context $context;
    public /* synthetic */ CustomRowTrackLinkHelper this$0;

    @Override // android.app.AlarmManager.OnAlarmListener
    public final void onAlarm() {
        CustomRowTrackLinkHelper customRowTrackLinkHelper = this.this$0;
        Context context = this.$context;
        ThreadManager companion = ThreadManager.Companion.getInstance();
        CustomRowTrackLinkHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new CustomRowTrackLinkHelper$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = customRowTrackLinkHelper;
        externalSyntheticLambda1.f$1 = context;
        VarHandle.storeStoreFence();
        companion.postInThread(externalSyntheticLambda1);
        this.this$0.scheduleAlarm();
    }
}
