package com.oplus.systemui.plugins.qs.customize.component;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.component.proxy.IDragHostAbility;
import com.oplus.systemui.plugins.qs.customize.view.IHostContainerAbility;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplusos.systemui.common.viewholder.GridData;
import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$dragCallbackProxy$2$1 implements IHostContainerAbility, IDragHostAbility {
    public /* synthetic */ OplusQSTilesComponent this$0;

    @Override // com.oplus.systemui.plugins.qs.customize.view.IHostContainerAbility
    public void allowLayoutForOnce() {
        this.this$0.editModeSwitchAnimationManager.allowLayoutForOnce();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.component.proxy.IDragHostAbility
    public RecyclerView.ViewHolder createAndBindViewHolder(GridData gridData, ViewGroup viewGroup) {
        return this.this$0.getTileAdapter().createAndBindViewHolder(gridData, viewGroup);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.component.proxy.IDragHostAbility
    public int findIndexOf(GridData gridData) {
        Iterator<Object> it = this.this$0.getTileAdapter().getDataList().iterator();
        int i = 0;
        while (it.hasNext()) {
            GridData gridData2 = (GridData) it.next();
            if (gridData2.getUniqueId() == gridData.getUniqueId()) {
                Object data = gridData2.getData();
                Class<?> cls = data != null ? data.getClass() : null;
                Object data2 = gridData.getData();
                if (Intrinsics.areEqual(cls, data2 != null ? data2.getClass() : null)) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.component.proxy.IDragHostAbility
    public void registerViewHolderAdd(Function2 function2) {
        this.this$0.onAdd = function2;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.component.proxy.IDragHostAbility
    public void sendIntent(TileEditIntent tileEditIntent) {
        this.this$0.editViewModel.sendIntent(tileEditIntent, IntentTarget.OTHER_LIST);
    }
}
