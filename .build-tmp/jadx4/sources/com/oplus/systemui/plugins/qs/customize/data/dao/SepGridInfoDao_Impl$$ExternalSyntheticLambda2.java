package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepGridInfoDao_Impl$$ExternalSyntheticLambda2 implements Function1 {
    public /* synthetic */ int f$0;

    public final Object invoke(Object obj) throws Exception {
        int i = this.f$0;
        SQLiteStatement sQLiteStatementPrepare = ((SQLiteConnection) obj).prepare("select * from sep_edit_grid_size_table where user_id =? limit 1");
        try {
            sQLiteStatementPrepare.bindLong(1, i);
            return sQLiteStatementPrepare.step() ? new GridInfoEntity((int) sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "user_id")), (int) sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "grid_col")), (int) sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "grid_row"))) : null;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }
}
