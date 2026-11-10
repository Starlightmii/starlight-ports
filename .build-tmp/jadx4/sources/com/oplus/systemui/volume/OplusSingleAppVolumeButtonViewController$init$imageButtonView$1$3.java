package com.oplus.systemui.volume;

import android.util.Log;
import android.view.View;
import com.oplus.systemui.volume.anim.SingleAppVolumeButtonAnimationController;
import com.oplus.systemui.volume.touch.SingleAppVolumeButtonTouchHandler;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3 implements View.OnClickListener {
    public /* synthetic */ OplusSingleAppVolumeButtonViewController this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->onClickListener triggered");
        }
        OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = this.this$0;
        if (!oplusSingleAppVolumeButtonViewController.isVisible) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->Already dismissed.");
                return;
            }
            return;
        }
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = oplusSingleAppVolumeButtonViewController.touchHandler;
        if (singleAppVolumeButtonTouchHandler != null) {
            singleAppVolumeButtonTouchHandler.setEnabled(false);
        }
        SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = this.this$0.animationController;
        if (singleAppVolumeButtonAnimationController != null) {
            OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController2 = this.this$0;
            OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 = new OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0(3);
            oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0.f$0 = oplusSingleAppVolumeButtonViewController2;
            VarHandle.storeStoreFence();
            singleAppVolumeButtonAnimationController.fadeout(oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0);
        }
        OplusSingleAppVolumeButtonViewController.SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = this.this$0.callback;
        if (singleAppVolumeButtonCallback != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0;
            if (oplusVolumeDialogImpl.mPreventSeekBar) {
                oplusVolumeDialogImpl.mOplusVolumeDialogView.onAppVolumeAdjustClick();
            }
        }
    }
}
