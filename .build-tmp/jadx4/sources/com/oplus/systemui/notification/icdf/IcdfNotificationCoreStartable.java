package com.oplus.systemui.notification.icdf;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.DependencyEx;
import com.android.systemui.InitController;
import com.oplus.synergynotificationsdk.icdf.IcdfReceiverHelper;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfNotificationCoreStartable implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "IcdfNotificationInit";
    private final Context context;
    private final Lazy headsUpDecider;
    private final InitController initController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.icdf.IcdfNotificationCoreStartable$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ IcdfNotificationCoreStartable this$0;

        @Override // java.lang.Runnable
        public final void run() {
            NotificationFeatureOption.INSTANCE.getClass();
            if (!NotificationFeatureOption.isPcSynergyEnable) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, IcdfNotificationCoreStartable.TAG, "ICDF registerReceiver fail, reason: pc synergy enable is false", null, 8);
                return;
            }
            ((IcdfSynergyHeadsUpDecider) this.this$0.headsUpDecider.get()).warmPeekFilters$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            IcdfReceiverHelper.INSTANCE.registerReceivers(this.this$0.context);
            ((SynergyCallBackHelper) DependencyEx.sDependency.getDependency(SynergyCallBackHelper.class)).registerListeners();
            ((SynergyCallBackHelper) DependencyEx.sDependency.getDependency(SynergyCallBackHelper.class)).synergyNotificationSDKInit(this.this$0.context.getApplicationContext());
        }
    }

    public IcdfNotificationCoreStartable(InitController initController, Context context, Lazy lazy) {
        this.initController = initController;
        this.context = context;
        this.headsUpDecider = lazy;
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}
