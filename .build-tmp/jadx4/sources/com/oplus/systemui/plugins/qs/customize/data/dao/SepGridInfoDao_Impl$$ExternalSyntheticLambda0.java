package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.sqlite.SQLiteConnection;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepGridInfoDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SepGridInfoDao_Impl f$0;
    public /* synthetic */ GridInfoEntity f$1;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                SepGridInfoDao_Impl sepGridInfoDao_Impl = this.f$0;
                GridInfoEntity gridInfoEntity = this.f$1;
                sepGridInfoDao_Impl.__updateAdapterOfGridInfoEntity.handle((SQLiteConnection) obj, gridInfoEntity);
                break;
            default:
                SepGridInfoDao_Impl sepGridInfoDao_Impl2 = this.f$0;
                GridInfoEntity gridInfoEntity2 = this.f$1;
                sepGridInfoDao_Impl2.__insertAdapterOfGridInfoEntity.insert((SQLiteConnection) obj, gridInfoEntity2);
                break;
        }
        return Unit.INSTANCE;
    }
}
