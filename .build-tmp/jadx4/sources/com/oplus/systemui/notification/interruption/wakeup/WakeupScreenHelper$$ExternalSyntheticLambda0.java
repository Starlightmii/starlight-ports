package com.oplus.systemui.notification.interruption.wakeup;

import android.hardware.SensorManager;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WakeupScreenHelper$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ WakeupScreenHelper f$0;

    @Override // java.lang.Runnable
    public final void run() {
        WakeupScreenHelper wakeupScreenHelper = this.f$0;
        List list = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "init start");
        wakeupScreenHelper.mSensorManager = (SensorManager) wakeupScreenHelper.mContext.getSystemService("sensor");
        wakeupScreenHelper.mZenModeKeyguardSettingsObserver.registerForUserSwitch(wakeupScreenHelper.mContext, -1, false);
        wakeupScreenHelper.mZenModeSettingsObserver.register(wakeupScreenHelper.mContext, false);
        ((NotifPipeline) wakeupScreenHelper.mNotifPipeline.get()).addCollectionListener(wakeupScreenHelper);
        ((NotifPipeline) wakeupScreenHelper.mNotifPipeline.get()).addOnBeforeRenderListListener(wakeupScreenHelper);
        wakeupScreenHelper.mKeyguardHelper = (NotificationKeyguardHelper) DependencyEx.sDependency.getDependency(NotificationKeyguardHelper.class);
        List list2 = wakeupScreenHelper.mFilters;
        WakeupScreenHelper$$ExternalSyntheticLambda3 wakeupScreenHelper$$ExternalSyntheticLambda3 = new WakeupScreenHelper$$ExternalSyntheticLambda3(1);
        wakeupScreenHelper$$ExternalSyntheticLambda3.f$0 = wakeupScreenHelper;
        VarHandle.storeStoreFence();
        list2.add(wakeupScreenHelper$$ExternalSyntheticLambda3);
        wakeupScreenHelper.mFilters.add(new WakeupScreenHelper$$ExternalSyntheticLambda2());
        List list3 = wakeupScreenHelper.mFilters;
        WakeupScreenHelper$$ExternalSyntheticLambda3 wakeupScreenHelper$$ExternalSyntheticLambda4 = new WakeupScreenHelper$$ExternalSyntheticLambda3(0);
        wakeupScreenHelper$$ExternalSyntheticLambda4.f$0 = wakeupScreenHelper;
        VarHandle.storeStoreFence();
        list3.add(wakeupScreenHelper$$ExternalSyntheticLambda4);
    }
}
