package com.oplus.systemui.shade;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.qs.QSPanelOverScrollAnimationEx;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.PulseExpansionHandlerExt;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.keyguard.ui.view.OplusKeyguardBottomAreaView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout;
import com.oplus.systemui.notification.clearall.ClearAllController;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import dagger.Lazy;
import dagger.internal.Factory;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationPanelViewControllerExImp_Factory implements Factory {
    public static NotificationPanelViewControllerExImp newInstance(Context context, ClearAllController clearAllController, OplusCloseAllController oplusCloseAllController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Lazy lazy, PulseExpansionHandlerExt pulseExpansionHandlerExt, AmbientState ambientState, HeadsUpManager headsUpManager, KeyguardSliderLayout keyguardSliderLayout, Handler handler, PictorialMediator pictorialMediator, PanelAnimationEx panelAnimationEx, QSPanelOverScrollAnimationEx qSPanelOverScrollAnimationEx, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, Lazy lazy13, Lazy lazy14, FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy15, Lazy lazy16, OplusKeyguardBottomAreaView oplusKeyguardBottomAreaView, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, CoroutineScope coroutineScope, ShadeInteractor shadeInteractor, SysUiStateControllerImpl sysUiStateControllerImpl, ConfigurationController configurationController, ActiveNotificationsInteractor activeNotificationsInteractor, Lazy lazy17, Lazy lazy18, Lazy lazy19, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy20, Lazy lazy21, Lazy lazy22, Lazy lazy23, Lazy lazy24) {
        return new NotificationPanelViewControllerExImp(context, clearAllController, oplusCloseAllController, notificationStackScrollLayoutController, lazy, pulseExpansionHandlerExt, ambientState, headsUpManager, keyguardSliderLayout, handler, pictorialMediator, panelAnimationEx, qSPanelOverScrollAnimationEx, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, fullScreenBannerHelper, lazy15, lazy16, oplusKeyguardBottomAreaView, sharedNotificationContainerViewModel, coroutineScope, shadeInteractor, sysUiStateControllerImpl, configurationController, activeNotificationsInteractor, lazy17, lazy18, lazy19, sysuiStatusBarStateController, lazy20, lazy21, lazy22, lazy23, lazy24);
    }
}
