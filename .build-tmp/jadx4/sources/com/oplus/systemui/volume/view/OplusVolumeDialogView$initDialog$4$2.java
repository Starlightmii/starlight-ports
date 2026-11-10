package com.oplus.systemui.volume.view;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.volume.utils.VolumeLayoutUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$initDialog$4$2 implements View.OnTouchListener {
    public /* synthetic */ RecyclerView $it;
    public /* synthetic */ OplusVolumeDialogView this$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1 || VolumeLayoutUtil.isPointInAnyItem(this.$it, motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        this.this$0.getVolumeInteractor().dismiss(1);
        return false;
    }
}
