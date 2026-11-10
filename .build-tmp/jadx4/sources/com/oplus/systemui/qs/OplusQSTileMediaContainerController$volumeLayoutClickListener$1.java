package com.oplus.systemui.qs;

import android.view.View;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.oplus.systemui.qs.slider.OplusQSBrightnessDialogController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController$volumeLayoutClickListener$1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSTileMediaContainerController this$0;

    public /* synthetic */ OplusQSTileMediaContainerController$volumeLayoutClickListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                OplusQSTileMediaContainerController.access$showVolumeDetailDialog(this.this$0);
                break;
            default:
                Object obj = this.this$0.brightnessDialogController.get();
                OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.this$0;
                OplusQSBrightnessDialogController oplusQSBrightnessDialogController = (OplusQSBrightnessDialogController) obj;
                oplusQSBrightnessDialogController.setFromSliderController(oplusQSTileMediaContainerController.mBrightnessSliderController);
                IBrightnessSliderController iBrightnessSliderController = oplusQSTileMediaContainerController.mBrightnessSliderController;
                OplusQsBrightnessSliderController oplusQsBrightnessSliderController = iBrightnessSliderController instanceof OplusQsBrightnessSliderController ? (OplusQsBrightnessSliderController) iBrightnessSliderController : null;
                oplusQSBrightnessDialogController.showDialog(oplusQsBrightnessSliderController != null ? oplusQsBrightnessSliderController.getExpandableView() : null);
                break;
        }
    }
}
