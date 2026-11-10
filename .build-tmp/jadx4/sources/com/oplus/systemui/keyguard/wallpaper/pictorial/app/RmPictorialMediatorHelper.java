package com.oplus.systemui.keyguard.wallpaper.pictorial.app;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.multiapp.OplusMultiAppManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RmPictorialMediatorHelper implements PictorialMediatorHelper {
    public static void closePictorialEnableSwitch(Context context) {
        Settings.System.putIntForUser(context.getContentResolver(), "oplus_customize_pictorial_enable_play", 0, KeyguardUpdateMonitor.getCurrentUser());
    }

    public static void enableLeftSwipeSwitch(Context context) {
        if (OplusMultiAppManager.getInstance().isMultiAppUserId(context.getUserId())) {
            return;
        }
        Settings.System.putIntForUser(context.getContentResolver(), "swipe_left_display_content", 1, KeyguardUpdateMonitor.getCurrentUser());
    }

    public static void enablePictorialEnableSwitch(Context context) {
        Settings.System.putIntForUser(context.getContentResolver(), "oplus_customize_pictorial_enable_play", 1, KeyguardUpdateMonitor.getCurrentUser());
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void adjustForVerticalApp(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.adjustForVerticalApp(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean calculateAutoPlayState(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.calculateAutoPlayState(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void initStatusWhenShowLocked(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.initStatusWhenShowLocked(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean isAutoPlaySwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.isAutoPlaySwitchOn(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final boolean isLeftSwipeSwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        if (switchStateParams == null) {
            KgdLogUtil.d("RmPictorialMediatorHelper", "isLeftSwipeSwitchOn  params is null so return false");
            return false;
        }
        if (switchStateParams.getLeftSwipeSwitchKeyState() == -1) {
            switchStateParams.setLeftSwipeSwitchKeyState(1);
        }
        if (switchStateParams.getMainSwitchOn()) {
            return switchStateParams.getLeftSwipeSwitchKeyState() == 1;
        }
        return switchStateParams.getLeftSwipeSwitchKeyState() == 1 && !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean isMainSwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        return super.isMainSwitchOn(switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final boolean isRightSwipeSwitchOn(PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        if (switchStateParams == null) {
            KgdLogUtil.d("RmPictorialMediatorHelper", "isRightSwipeSwitchOn  params is null so return false");
            return false;
        }
        if (switchStateParams.getRightSwipeSwitchKeyState() == -1) {
            switchStateParams.setRightSwipeSwitchKeyState(0);
        }
        return switchStateParams.getRightSwipeSwitchKeyState() == 1 && !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final boolean isSupportDisableAlive(Context context, Bundle bundle) {
        if (context == null) {
            return false;
        }
        if (context.getUserId() != 999) {
            Settings.System.putIntForUser(context.getContentResolver(), "oplus_disable_pictorial_alive", 1, KeyguardUpdateMonitor.getCurrentUser());
        }
        return true;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void onChangeAutoSwitchState(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.onChangeAutoSwitchState(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ void onChangeEnableSwitchState(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        super.onChangeEnableSwitchState(context, switchStateParams);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final void onChangeMainSwitchState(Context context, PictorialMediatorHelper.SwitchStateParams switchStateParams) {
        if (context == null || switchStateParams == null || !KeyguardUtils.Companion.isDefaultKeyguardInSettings(context)) {
            return;
        }
        if (switchStateParams.getMainSwitchKeyState() == 1) {
            enablePictorialEnableSwitch(context);
        } else {
            closePictorialEnableSwitch(context);
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public final void onPictorialServicesConnect() {
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        pictorialMediator.checkSupportTaglineMsg();
        pictorialMediator.updatePictorialGuideEntryInfo();
        pictorialMediator.getPictorialBuriedHelper().uploadSwitch();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper
    public /* bridge */ /* synthetic */ boolean onPictorialServicesDisconnect(Context context) {
        return super.onPictorialServicesDisconnect(context);
    }
}
