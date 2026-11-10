package com.oplus.systemui.statusbar.pipeline.dagger;

import com.android.systemui.statusbar.pipeline.ui.viewmodel.OplusStackedMobileIconViewModel;
import com.oplus.systemui.statusbar.pipeline.ui.viewmodel.OplusStackedMobileIconViewModelImpl;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class StatusBarPipelineModuleExImpl_Companion_OplusStackedMobileIconViewModelFactoryFactory implements Factory {
    public static OplusStackedMobileIconViewModel.Factory oplusStackedMobileIconViewModelFactory(OplusStackedMobileIconViewModelImpl.Factory factory) {
        OplusStackedMobileIconViewModel.Factory factoryOplusStackedMobileIconViewModelFactory = StatusBarPipelineModuleExImpl.Companion.oplusStackedMobileIconViewModelFactory(factory);
        factoryOplusStackedMobileIconViewModelFactory.getClass();
        return factoryOplusStackedMobileIconViewModelFactory;
    }
}
