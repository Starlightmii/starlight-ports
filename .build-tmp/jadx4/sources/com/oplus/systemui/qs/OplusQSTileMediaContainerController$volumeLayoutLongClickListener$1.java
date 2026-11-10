package com.oplus.systemui.qs;

import android.view.View;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.qs.slider.OplusQSBrightnessDialogController;
import com.oplus.systemui.qs.slider.OplusQsBrightnessSliderController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1 implements View.OnLongClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSTileMediaContainerController this$0;

    public /* synthetic */ OplusQSTileMediaContainerController$volumeLayoutLongClickListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                VibrationHelper.getInstance(this.this$0.getContext()).onPerformHapticFeedback();
                OplusQSTileMediaContainerController.access$showVolumeDetailDialog(this.this$0);
                break;
            default:
                VibrationHelper.getInstance(this.this$0.getContext()).onPerformHapticFeedback();
                Object obj = this.this$0.brightnessDialogController.get();
                OplusQSTileMediaContainerController oplusQSTileMediaContainerController = this.this$0;
                OplusQSBrightnessDialogController oplusQSBrightnessDialogController = (OplusQSBrightnessDialogController) obj;
                IBrightnessSliderController iBrightnessSliderController = oplusQSTileMediaContainerController.mBrightnessSliderController;
                OplusQsBrightnessSliderController oplusQsBrightnessSliderController = iBrightnessSliderController instanceof OplusQsBrightnessSliderController ? (OplusQsBrightnessSliderController) iBrightnessSliderController : null;
                if (oplusQsBrightnessSliderController != null && !oplusQsBrightnessSliderController.getMTracking()) {
                    oplusQSBrightnessDialogController.setFromSliderController(oplusQSTileMediaContainerController.mBrightnessSliderController);
                    IBrightnessSliderController iBrightnessSliderController2 = oplusQSTileMediaContainerController.mBrightnessSliderController;
                    OplusQsBrightnessSliderController oplusQsBrightnessSliderController2 = iBrightnessSliderController2 instanceof OplusQsBrightnessSliderController ? (OplusQsBrightnessSliderController) iBrightnessSliderController2 : null;
                    oplusQSBrightnessDialogController.showDialog(oplusQsBrightnessSliderController2 != null ? oplusQsBrightnessSliderController2.getExpandableView() : null);
                }
                break;
        }
        return true;
    }
}
