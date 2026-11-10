package com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper;

import androidx.compose.runtime.OpaqueKey$;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.CopyViewProxy;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.ISwapOrMove;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.DropAnim;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewDragHelper$notifyDragStart$1$1 implements ISwapOrMove {
    public /* synthetic */ RecyclerViewDragHelper this$0;

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ISwapOrMove
    public final boolean onAfterSwap(Object obj) {
        Object next;
        Iterator it = this.this$0.dropAnimationList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((DropAnim) next).getData(), obj));
        DropAnim dropAnim = (DropAnim) next;
        if (dropAnim == null) {
            return false;
        }
        dropAnim.onDrop();
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ISwapOrMove
    public final void onAnimateAdd(Object obj, RecyclerView.ViewHolder viewHolder) {
        LogUtil.i$default(LogUtil.TAG_QS, "RecyclerViewDragHelper", OpaqueKey$.ExternalSyntheticOutline0.m(obj, "onAnimateAdd, viewHolder: "), null, 8);
        RecyclerViewDragHelper recyclerViewDragHelper = this.this$0;
        CopyViewProxy copyViewProxy = recyclerViewDragHelper.copyViewProxy;
        if (copyViewProxy != null) {
            copyViewProxy.getHostViewHolder().itemView.setVisibility(0);
            if (copyViewProxy.getHostViewHolder() instanceof BaseEditableViewHolder) {
                ((BaseEditableViewHolder) copyViewProxy.getHostViewHolder()).setBeingDragged(false);
            }
            copyViewProxy.setHostViewHolder(viewHolder);
            if (recyclerViewDragHelper.isDragMode) {
                if (copyViewProxy.getHostViewHolder() instanceof BaseEditableViewHolder) {
                    ((BaseEditableViewHolder) copyViewProxy.getHostViewHolder()).setBeingDragged(true);
                }
                viewHolder.itemView.setVisibility(4);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.drag.ISwapOrMove
    public final void onBeforeSwap(List list) {
        RecyclerViewDragHelper recyclerViewDragHelper = this.this$0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RecyclerViewDragHelper.access$findDropAnimation(recyclerViewDragHelper, it.next()).onPreDrop();
        }
    }
}
