package com.oplus.systemui.plugins.qs.customize.view.animation.pull;

import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditablePluginViewHolder;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PullAnimationProxy$$ExternalSyntheticLambda1 implements Function2 {
    public /* synthetic */ Ref.ObjectRef f$0;

    public final Object invoke(Object obj, Object obj2) {
        Ref.ObjectRef objectRef = this.f$0;
        ((Integer) obj).getClass();
        objectRef.element = ((RecyclerView.ViewHolder) ((EditablePluginViewHolder) obj2)).itemView;
        return Unit.INSTANCE;
    }
}
