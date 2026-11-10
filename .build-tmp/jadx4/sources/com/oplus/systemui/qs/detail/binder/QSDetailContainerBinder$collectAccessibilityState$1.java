package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailContainerBinder$collectAccessibilityState$1 implements Runnable {
    public /* synthetic */ View $container;
    public /* synthetic */ QSDetailContainerViewModel $viewModel;

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.$container;
        view.announceForAccessibility(view.getContext().getString(2131951925, this.$viewModel.getTitle()));
        this.$container.sendAccessibilityEvent(32);
        this.$container.requestAccessibilityFocus();
    }
}
