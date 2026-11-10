package com.oplus.systemui.notification.row.menupanel.snoozepanel;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NormalSettingsContent extends LinearLayout {
    private SettingsPanelListContentAdapter listContentAdapter;
    private RecyclerView recyclerView;

    public NormalSettingsContent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = getResources();
        if (resources != null) {
            resources.getDimensionPixelSize(2131172482);
        }
        Resources resources2 = getResources();
        if (resources2 != null) {
            resources2.getDimensionPixelSize(2131172483);
        }
    }

    public final void applyModel(SettingsPanelModel settingsPanelModel) {
        List listEmptyList;
        if (settingsPanelModel == null || !settingsPanelModel.isLargeSpaceMargin) {
            Resources resources = getResources();
            if (resources != null) {
                resources.getDimensionPixelSize(2131172507);
            }
        } else {
            Resources resources2 = getResources();
            if (resources2 != null) {
                resources2.getDimensionPixelSize(2131172508);
            }
        }
        if (settingsPanelModel != null) {
            settingsPanelModel.setPanelContent(this);
        }
        SettingsPanelListContentAdapter settingsPanelListContentAdapter = this.listContentAdapter;
        if (settingsPanelListContentAdapter != null) {
            if (settingsPanelModel == null || (listEmptyList = settingsPanelModel.getActions()) == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            settingsPanelListContentAdapter.setSrc(listEmptyList);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.listContentAdapter = new SettingsPanelListContentAdapter(getContext());
        this.recyclerView = findViewById(2131364866);
        getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(1);
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(this.listContentAdapter);
        }
    }

    public NormalSettingsContent(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NormalSettingsContent(Context context) {
        this(context, null);
    }
}
