package com.oplus.systemui.media.controls.model;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.Barrier;
import com.android.systemui.media.controls.models.player.MediaViewHolderExt;
import com.android.systemui.media.controls.ui.view.GutsViewHolder;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewHolderExt extends MediaViewHolderExt {
    public OplusMediaViewHolderExt(Context context) {
        super(context);
    }

    public Barrier getActionsTopBarrier(View view) {
        return null;
    }

    public GutsViewHolder getGutsViewHolder(View view) {
        return new OplusGutViewHolder(view);
    }
}
