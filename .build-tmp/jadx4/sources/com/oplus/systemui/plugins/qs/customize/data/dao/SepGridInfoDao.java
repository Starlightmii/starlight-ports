package com.oplus.systemui.plugins.qs.customize.data.dao;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import java.util.List;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SepGridInfoDao {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String TABLE_NAME = "sep_edit_grid_size_table";

    Object insertGridInfo(GridInfoEntity gridInfoEntity, Continuation continuation);

    GridInfoEntity queryGridInfo(int i);

    List<GridInfoEntity> queryGridInfos();

    Object updateGridInfo(GridInfoEntity gridInfoEntity, Continuation continuation);
}
