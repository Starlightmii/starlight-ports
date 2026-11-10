package com.oplus.systemui.volume.view;

import android.content.DialogInterface;
import android.view.ViewGroup;
import com.oplus.systemui.volume.observer.AnimationScaleSettingsObserver;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$setDialogListener$1 implements DialogInterface.OnShowListener {
    public /* synthetic */ OplusVolumeDialogView this$0;

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        VolumeAnimUtil.sAnimationScale = AnimationScaleSettingsObserver.Companion.getInstance().getAnimationScale();
        LogUtil.d(LogUtil.TAG_VOLUME, "OplusVolume-Anim", "resetAnimationScale : sAnimationScale:" + VolumeAnimUtil.sAnimationScale);
        ViewGroup viewGroup = this.this$0.mDialogView;
        if (viewGroup != null) {
            viewGroup.setAlpha(1.0f);
        }
        ViewGroup viewGroup2 = this.this$0.mDialogView;
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(0);
        }
    }
}
