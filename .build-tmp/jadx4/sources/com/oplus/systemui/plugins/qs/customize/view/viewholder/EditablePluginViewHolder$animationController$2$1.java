package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditablePluginViewHolder$animationController$2$1 implements IDeformationAnimationController {
    public Lazy computeResult$delegate;
    public boolean isResizing;
    public /* synthetic */ EditablePluginViewHolder this$0;

    private final IDeformationAnimationController.ComputeResult getComputeResult() {
        return (IDeformationAnimationController.ComputeResult) this.computeResult$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final IDeformationAnimationController.ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4) {
        return getComputeResult();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void onFlingStarted(ViewGroup viewGroup, SpanSize spanSize) {
        QsPanelPlugin qsPanelPlugin = this.this$0.panelPlugin;
        if (qsPanelPlugin != null) {
            qsPanelPlugin.onFlingStarted(new SpanSizeInPlugin(spanSize.getColSpan(), spanSize.getRowSpan()));
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void prepare(ViewGroup viewGroup, Map map, Map map2, boolean z, boolean z2) {
        this.isResizing = true;
        QsPanelPlugin qsPanelPlugin = this.this$0.panelPlugin;
        if (qsPanelPlugin != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map2.size()));
            for (Map.Entry entry : map2.entrySet()) {
                linkedHashMap.put(new SpanSizeInPlugin(((SpanSize) entry.getKey()).getColSpan(), ((SpanSize) entry.getKey()).getRowSpan()), entry.getValue());
            }
            qsPanelPlugin.prepareDeform(linkedHashMap);
        }
    }

    public final void setResizing(boolean z) {
        this.isResizing = z;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void squeezeOrStretch(int i, int i2, int i3) {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void stop(Function1 function1) {
        this.isResizing = false;
        QsPanelPlugin qsPanelPlugin = this.this$0.panelPlugin;
        if (qsPanelPlugin != null) {
            qsPanelPlugin.onDeformStop();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void updateFrame(IDeformationAnimationController.ComputeResult computeResult) {
    }
}
