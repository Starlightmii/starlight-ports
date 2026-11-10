package com.oplus.systemui.qs.dagger;

import android.view.View;
import com.android.systemui.qs.FooterActionsController;
import com.android.systemui.qs.IQSContainerImplController;
import com.android.systemui.qs.animator.IQSAnimator;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.QuickQSPanelController;
import com.android.systemui.qs.deprecated.customize.IQSCustomizerController;
import com.android.systemui.qs.deprecated.dagger.QSComponent;
import com.oplus.systemui.qs.OplusQSBottomViewController;
import com.oplus.systemui.qs.OplusQSFooterViewController;
import com.oplus.systemui.qs.OplusQSPanelContainerController;
import com.oplus.systemui.qs.OplusQSTileMediaContainerController;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusQSFragmentComponent extends QSComponent {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        OplusQSFragmentComponent create(View view);
    }

    /* synthetic */ IQSAnimator getIQSAnimator();

    /* synthetic */ IQSContainerImplController getIQSContainerImplController();

    /* synthetic */ IQSCustomizerController getIQSCustomizerController();

    OplusQSFakeStatusController getQSFakeStatusController();

    /* synthetic */ OplusQSFooterViewController getQSFooter();

    /* synthetic */ FooterActionsController getQSFooterActionController();

    OplusQuickStatusBarHeaderController getQSHeaderController();

    /* synthetic */ QSPanelController getQSPanelController();

    OplusQSTileMediaContainerController getQSTileMediaContainerController();

    OplusQSBottomViewController getQsBottomController();

    OplusQSPanelContainerController getQsPanelContainerController();

    /* synthetic */ QuickQSPanelController getQuickQSPanelController();

    /* synthetic */ View getRootView();
}
