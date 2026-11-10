package com.oplus.systemui.statusbar.policy;

import com.android.systemui.DependencyEx;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.policy.BrightnessMirrorControllerEx;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrightnessMirrorControllerExImpl extends BrightnessMirrorControllerEx {
    public BrightnessMirrorController createBrightnessMirrorController(NotificationShadeWindowView notificationShadeWindowView, ShadeViewController shadeViewController, NotificationShadeDepthController notificationShadeDepthController, BrightnessSliderController.Factory factory, Consumer<Boolean> consumer) {
        QSFeatureOption.INSTANCE.getClass();
        return (QSFeatureOption.isSupportVolumeSeekBar() || ((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) ? new OplusQsBrightnessSliderMirrorController(notificationShadeWindowView, shadeViewController, notificationShadeDepthController, factory, consumer) : new OplusBrightnessMirrorController(notificationShadeWindowView, shadeViewController, notificationShadeDepthController, factory, consumer);
    }
}
