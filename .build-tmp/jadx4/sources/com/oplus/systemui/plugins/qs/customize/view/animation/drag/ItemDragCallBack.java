package com.oplus.systemui.plugins.qs.customize.view.animation.drag;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.OutputDragData;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ItemDragCallBack {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    RecyclerView.ViewHolder createAndBindViewHolder(Object obj, ViewGroup viewGroup);

    default long getDragWaitingTime() {
        return 100L;
    }

    RecyclerView.ViewHolder getHolder(Object obj);

    IntentTarget getIntentTarget();

    boolean isFullEmpty(OutputDragData outputDragData, float f, float f2);

    void onDragEnd(OutputDragData outputDragData);

    void onDragLocation(OutputDragData outputDragData, float f, float f2);

    void onDragStart(RecyclerView recyclerView, float f, float f2, float f3, float f4, OutputDragData outputDragData);

    void onMove(RecyclerView recyclerView, OutputDragData outputDragData, float f, float f2, boolean z);

    void registerViewHolderAdd(Object obj, ISwapOrMove iSwapOrMove);
}
