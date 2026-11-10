package com.oplus.systemui.notification.interruption.wakeup;

import android.hardware.Sensor;
import android.os.OplusPowerManager;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.OplusCommonSensorEx;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WakeupScreenHelper$$ExternalSyntheticLambda5 implements Function0 {
    public /* synthetic */ WakeupScreenHelper f$0;

    /* JADX WARN: Code duplicated, block: B:13:0x0032  */
    public final Object invoke() {
        WakeupScreenHelper wakeupScreenHelper = this.f$0;
        List list = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--wake up directly when proximate sensor under screen and AOD working");
            wakeupScreenHelper.powerOnScreen();
        } else {
            if (FeatureOption.isSupportUnderScreenProximity()) {
                boolean displayAodStatus = FeatureOption.isOplusAodSupport() ? new OplusPowerManager().getDisplayAodStatus() : false;
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("Notification--isDisplayAod  = ", LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", displayAodStatus);
                if (displayAodStatus) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--wake up directly when proximate sensor under screen and AOD working");
                    wakeupScreenHelper.powerOnScreen();
                }
            }
            synchronized (wakeupScreenHelper) {
                try {
                    if (!wakeupScreenHelper.mHasRegisteredSensor) {
                        boolean zIsSupportPocketSensor = FeatureOption.isSupportPocketSensor();
                        Sensor pocketTypeSensor = zIsSupportPocketSensor ? ((OplusCommonSensorEx) DependencyEx.sDependency.getDependency(OplusCommonSensorEx.class)).getPocketTypeSensor() : ((OplusCommonSensorEx) DependencyEx.sDependency.getDependency(OplusCommonSensorEx.class)).getProximityTypeSensor();
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "start register::pocketSensor=" + zIsSupportPocketSensor);
                        wakeupScreenHelper.mHasRegisteredSensor = wakeupScreenHelper.mSensorManager.registerListener(wakeupScreenHelper.mSensorListener, pocketTypeSensor, 3);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
