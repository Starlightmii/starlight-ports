package com.oplus.systemui.volume.view;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplusos.systemui.common.util.GlobalActionsUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeRow$Companion$appIconOutlineProvider$1 extends ViewOutlineProvider {
    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        VolumeBlurManager.INSTANCE.setOutlineRect(outline, new Rect(0, 0, view.getWidth(), view.getHeight()), GlobalActionsUtils.dp2px(view.getContext(), 5.5f), view.getResources().getFloat(2131173847));
    }
}
