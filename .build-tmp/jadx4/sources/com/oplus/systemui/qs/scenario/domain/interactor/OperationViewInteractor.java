package com.oplus.systemui.qs.scenario.domain.interactor;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.qs.scenario.domain.model.ScenarioTileTemplateResolver;
import com.oplus.systemui.qs.scenario.domain.tile.ScenarioDetailTile;
import com.oplus.systemui.qs.scenario.ui.template.ScenarioTileAdapter;
import com.oplus.systemui.qs.scenario.ui.template.ScenarioTileGridSizeManager;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OperationViewInteractor {
    public final View createDialogView(Context context, List<ScenarioDetailTile> list) {
        ScenarioTileAdapter scenarioTileAdapter = new ScenarioTileAdapter(ScenarioTileGridSizeManager.INSTANCE.create(context));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131172377);
        if (list == null) {
            return null;
        }
        COUIRecyclerView cOUIRecyclerView = new COUIRecyclerView(context);
        cOUIRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        cOUIRecyclerView.setOverScrollMode(0);
        cOUIRecyclerView.setLayoutManager(new createTileTemplateView.1.1(context));
        cOUIRecyclerView.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
        cOUIRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        cOUIRecyclerView.setClipChildren(false);
        cOUIRecyclerView.setClipToPadding(false);
        cOUIRecyclerView.setAdapter(scenarioTileAdapter);
        scenarioTileAdapter.submitList(ScenarioTileTemplateResolver.INSTANCE.resolve(list));
        return cOUIRecyclerView;
    }
}
