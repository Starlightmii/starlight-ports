package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.sqlite.SQLiteConnection;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SepEditTileDaoWrapper_Impl f$0;
    public /* synthetic */ SepTileEntity f$1;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl = this.f$0;
                SepTileEntity sepTileEntity = this.f$1;
                sepEditTileDaoWrapper_Impl.__updateAdapterOfSepTileEntity.handle((SQLiteConnection) obj, sepTileEntity);
                break;
            default:
                SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl2 = this.f$0;
                SepTileEntity sepTileEntity2 = this.f$1;
                sepEditTileDaoWrapper_Impl2.__insertAdapterOfSepTileEntity.insert((SQLiteConnection) obj, sepTileEntity2);
                break;
        }
        return Unit.INSTANCE;
    }
}
