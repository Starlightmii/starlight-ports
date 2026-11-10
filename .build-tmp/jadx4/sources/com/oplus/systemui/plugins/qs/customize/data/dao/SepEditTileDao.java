package com.oplus.systemui.plugins.qs.customize.data.dao;

import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SepEditTileDao {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String TILE_TABLE_NAME = "sep_edit_info_table";

    Object deleteAll(Continuation continuation);

    Object deleteTileInfoBySpec(int i, String str, Continuation continuation);

    Object deleteTileInfoByUserID(int i, Continuation continuation);

    Object insertTileInfo(SepTileEntity sepTileEntity, Continuation continuation);

    Flow queryTileInfoByFlow(int i);

    List<SepTileEntity> queryTileInfoByUserId(int i);

    SepTileEntity queryTileInfoByUserIdAndSpec(int i, String str);

    Object updateTileInfo(SepTileEntity sepTileEntity, Continuation continuation);
}
