package com.oplus.systemui.viewdialog;

import android.view.ViewGroup;
import com.android.systemui.CoreStartable;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.systemui.viewdialog.ui.binder.ViewDialogContainerBinder;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUIViewDialogConfigurator implements CoreStartable {
    private final ViewDialogContainerViewModel containerViewModel;
    private final ViewDialogContentViewModel.Factory contentViewModelFactory;
    private final DisposableHandles disposables = new DisposableHandles();
    private final NotificationShadeWindowView rootView;

    public SysUIViewDialogConfigurator(NotificationShadeWindowView notificationShadeWindowView, ViewDialogContainerViewModel viewDialogContainerViewModel, ViewDialogContentViewModel.Factory factory) {
        this.rootView = notificationShadeWindowView;
        this.containerViewModel = viewDialogContainerViewModel;
        this.contentViewModelFactory = factory;
    }

    public final void start() {
        this.disposables.dispose();
        this.disposables.plusAssign(ViewDialogContainerBinder.INSTANCE.bind((ViewGroup) this.rootView.findViewById(2131365966), this.containerViewModel, this.contentViewModelFactory));
    }
}
