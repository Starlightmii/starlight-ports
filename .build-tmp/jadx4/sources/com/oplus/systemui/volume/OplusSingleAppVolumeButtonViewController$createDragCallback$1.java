package com.oplus.systemui.volume;

import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.systemui.volume.touch.SingleAppVolumeButtonTouchHandler;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSingleAppVolumeButtonViewController$createDragCallback$1 implements SingleAppVolumeButtonTouchHandler.DragCallback {
    public /* synthetic */ OplusSingleAppVolumeButtonViewController this$0;

    public final void onDragEnd(float f, float f2) {
        OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->".concat(ListPopupWindow$.ExternalSyntheticOutline0.m("onDragEnd velocity=(", f, ", ", f2, ")")));
        }
        OplusSingleAppVolumeButtonViewController.access$performSnapToEdge(oplusSingleAppVolumeButtonViewController, f, f2);
    }

    public final void onUp() {
        this.this$0.setStatusBarExpandable(true);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->onUp");
        }
    }
}
