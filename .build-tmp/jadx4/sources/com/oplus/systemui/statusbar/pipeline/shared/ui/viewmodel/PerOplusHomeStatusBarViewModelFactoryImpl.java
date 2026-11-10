package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.HomeStatusBarViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.HomeStatusBarViewModelImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PerOplusHomeStatusBarViewModelFactoryImpl implements HomeStatusBarViewModel.HomeStatusBarViewModelFactory {
    private final HomeStatusBarViewModelImpl.HomeStatusBarViewModelFactoryImpl homeStatusBarViewModelFactory;
    private final OplusHomeStatusBarViewModel.OplusHomeStatusBarViewModelFactory oplusHomeStatusBarViewModelFactory;

    public PerOplusHomeStatusBarViewModelFactoryImpl(HomeStatusBarViewModelImpl.HomeStatusBarViewModelFactoryImpl homeStatusBarViewModelFactoryImpl, OplusHomeStatusBarViewModel.OplusHomeStatusBarViewModelFactory oplusHomeStatusBarViewModelFactory) {
        this.homeStatusBarViewModelFactory = homeStatusBarViewModelFactoryImpl;
        this.oplusHomeStatusBarViewModelFactory = oplusHomeStatusBarViewModelFactory;
    }

    public HomeStatusBarViewModel create() {
        return this.oplusHomeStatusBarViewModelFactory.create(this.homeStatusBarViewModelFactory.create());
    }
}
