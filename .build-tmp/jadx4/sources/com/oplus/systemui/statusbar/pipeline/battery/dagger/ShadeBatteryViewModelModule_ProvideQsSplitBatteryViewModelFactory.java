package com.oplus.systemui.statusbar.pipeline.battery.dagger;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.BatteryForceStyleRepository;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import dagger.internal.Factory;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShadeBatteryViewModelModule_ProvideQsSplitBatteryViewModelFactory implements Factory {
    public static LocationBasedBatteryViewModel provideQsSplitBatteryViewModel(LocationBasedBatteryViewModel.Factory factory, Context context, ConfigurationController configurationController, CoroutineScope coroutineScope, BatteryForceStyleRepository batteryForceStyleRepository) {
        LocationBasedBatteryViewModel locationBasedBatteryViewModelProvideQsSplitBatteryViewModel = ShadeBatteryViewModelModule.INSTANCE.provideQsSplitBatteryViewModel(factory, context, configurationController, coroutineScope, batteryForceStyleRepository);
        locationBasedBatteryViewModelProvideQsSplitBatteryViewModel.getClass();
        return locationBasedBatteryViewModelProvideQsSplitBatteryViewModel;
    }
}
