package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import androidx.car.app.serialization.Bundler$;
import androidx.exifinterface.media.ExifInterface$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAlgorithm;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.LockScreenAmbientState;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleDataBaseHandler {
    public NotificationCapsuleContainer capsuleContainer;
    public CapsuleNotificationDataController notifDataController;

    public final void bindContainer(NotificationCapsuleContainer notificationCapsuleContainer, CapsuleNotificationDataController capsuleNotificationDataController) {
        setCapsuleContainer(notificationCapsuleContainer);
        setNotifDataController(capsuleNotificationDataController);
    }

    public final ContainerAlgorithm.CapsuleAlgorithmState getAlgorithmState() {
        return getAnimHelper().getContainerScrollAlgorithm().getTempAlgorithmState();
    }

    public final LockScreenAmbientState getAmbientState() {
        return getAnimHelper().getAmbientState();
    }

    public final ContainerAnimatorViewHelper getAnimHelper() {
        return getCapsuleContainer().getAnimatorHelper();
    }

    public final NotificationCapsuleContainer getCapsuleContainer() {
        NotificationCapsuleContainer notificationCapsuleContainer = this.capsuleContainer;
        if (notificationCapsuleContainer != null) {
            return notificationCapsuleContainer;
        }
        return null;
    }

    public final CapsuleItemView getCurBaseCard() {
        return getAmbientState().getBaseCard();
    }

    public final CapsuleNotificationDataController getNotifDataController() {
        CapsuleNotificationDataController capsuleNotificationDataController = this.notifDataController;
        if (capsuleNotificationDataController != null) {
            return capsuleNotificationDataController;
        }
        return null;
    }

    public final void log(String str, Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            String str2 = (String) function0.invoke();
            if (str2 == null) {
                str2 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str2, "SystemUi--Notification");
        }
    }

    public final void setCapsuleContainer(NotificationCapsuleContainer notificationCapsuleContainer) {
        this.capsuleContainer = notificationCapsuleContainer;
    }

    public final void setNotifDataController(CapsuleNotificationDataController capsuleNotificationDataController) {
        this.notifDataController = capsuleNotificationDataController;
    }
}
