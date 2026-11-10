package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyViewHolder extends BaseEditableViewHolder {
    public EmptyViewHolder(Context context, ViewGroup viewGroup, boolean z, Function1 function1) {
        super(context, viewGroup, null);
        DeformationFrameLayout deformationContainer = getDeformationContainer();
        SpanSize spanSize = SpanCache.INSTANCE.spanSize(1, 1);
        1 r3 = new 1();
        r3.$context = context;
        r3.this$0 = this;
        r3.$onClick = function1;
        VarHandle.storeStoreFence();
        DeformationFrameLayout.init$default(deformationContainer, spanSize, r3, null, z, 4);
        setPersonalArea(z);
        setEditMode(true);
        setImmersiveOperation(true);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
    }
}
