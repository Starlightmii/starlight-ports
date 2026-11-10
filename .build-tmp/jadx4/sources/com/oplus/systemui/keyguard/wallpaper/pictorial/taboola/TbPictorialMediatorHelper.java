package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.Context;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialMediatorHelper extends RmPictorialMediatorHelper {
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void adjustForVerticalApp(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.adjustForVerticalApp(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean calculateAutoPlayState(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.calculateAutoPlayState(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void initStatusWhenShowLocked(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.initStatusWhenShowLocked(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean isAutoPlaySwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.isAutoPlaySwitchOn(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.app.RmPictorialMediatorHelper, com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final boolean isMainSwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return switchStateParams != null && !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState && switchStateParams.getMainSwitchOn() && switchStateParams.isPictorialEnable;
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
