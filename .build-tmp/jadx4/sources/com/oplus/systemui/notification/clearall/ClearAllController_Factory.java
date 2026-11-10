package com.oplus.systemui.notification.clearall;

import android.content.Context;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.oplus.systemui.notification.base.OplusNotificationQsInteractor;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import dagger.Lazy;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ClearAllController_Factory implements Factory {
    public static ClearAllController newInstance(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, PanelAnimationEx panelAnimationEx, OplusNotificationQsInteractor oplusNotificationQsInteractor, ViewBlurManager viewBlurManager, MaterialColorManager materialColorManager, ActiveNotificationsInteractor activeNotificationsInteractor, Lazy lazy, SuperPowerSaveRepository superPowerSaveRepository, Lazy lazy2) {
        return new ClearAllController(context, sysuiStatusBarStateController, panelAnimationEx, oplusNotificationQsInteractor, viewBlurManager, materialColorManager, activeNotificationsInteractor, lazy, superPowerSaveRepository, lazy2);
    }
}
