package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditIntent;
import com.oplusos.systemui.common.viewholder.GridData;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IDragHostAbility {
    RecyclerView.ViewHolder createAndBindViewHolder(GridData gridData, ViewGroup viewGroup);

    int findIndexOf(GridData gridData);

    void registerViewHolderAdd(Function2 function2);

    void sendIntent(TileEditIntent tileEditIntent);
}
