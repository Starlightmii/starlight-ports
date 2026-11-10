package com.oplus.systemui.statusbar.pipeline.battery.dagger;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.BatteryForceStyleRepository;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeBatteryViewModelModule {
    public static final ShadeBatteryViewModelModule INSTANCE = new ShadeBatteryViewModelModule();

    public final LocationBasedBatteryViewModel provideKeyguardBatteryViewModel(LocationBasedBatteryViewModel.Factory factory, Context context, ConfigurationController configurationController, CoroutineScope coroutineScope, BatteryForceStyleRepository batteryForceStyleRepository) {
        return factory.create(context, configurationController, SeparateQSStatisicUtil.VALUE_KEYGUARD, batteryForceStyleRepository.getKeyguardInteractor(), coroutineScope);
    }

    public final LocationBasedBatteryViewModel provideQsBatteryViewModel(LocationBasedBatteryViewModel.Factory factory, Context context, ConfigurationController configurationController, CoroutineScope coroutineScope, BatteryForceStyleRepository batteryForceStyleRepository) {
        return factory.create(context, configurationController, SeparateQSStatisicUtil.VALUE_PANEL_QS, batteryForceStyleRepository.getQsInteractor(), coroutineScope);
    }

    public final LocationBasedBatteryViewModel provideQsSplitBatteryViewModel(LocationBasedBatteryViewModel.Factory factory, Context context, ConfigurationController configurationController, CoroutineScope coroutineScope, BatteryForceStyleRepository batteryForceStyleRepository) {
        return factory.create(context, configurationController, "qsSplit", batteryForceStyleRepository.getQsInteractor(), coroutineScope);
    }
}
