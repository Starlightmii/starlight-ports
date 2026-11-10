package com.oplus.systemui.keyguard.wallpaper.pictorial.lehua;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LHPictorialMediatorHelper extends RmPictorialMediatorHelper {
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void adjustForVerticalApp(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.adjustForVerticalApp(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean calculateAutoPlayState(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.calculateAutoPlayState(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final void initStatusWhenShowLocked(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        if (context == null || switchStateParams == null || isLeftSwipeSwitchOn(switchStateParams)) {
            return;
        }
        RmPictorialMediatorHelper.enableLeftSwipeSwitch(context);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean isAutoPlaySwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.isAutoPlaySwitchOn(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final boolean isMainSwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        if (switchStateParams == null || !switchStateParams.isPictorialEnable) {
            return false;
        }
        if (switchStateParams.isSupportLeftSwipe) {
            if (switchStateParams.isNotShowAgain && !switchStateParams.getMainSwitchOn()) {
                return false;
            }
            if (KeyguardUpdateMonitor.getCurrentUser() == 0) {
                return !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
            }
        }
        return switchStateParams.getMainSwitchOn();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void onChangeAutoSwitchState(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.onChangeAutoSwitchState(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void onChangeEnableSwitchState(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.onChangeEnableSwitchState(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean onPictorialServicesDisconnect(Context context) {
        return super.onPictorialServicesDisconnect(context);
    }
}
