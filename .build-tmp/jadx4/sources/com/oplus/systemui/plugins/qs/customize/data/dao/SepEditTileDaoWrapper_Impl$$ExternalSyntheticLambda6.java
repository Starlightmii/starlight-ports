package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6 implements Function1 {
    public /* synthetic */ int f$0;

    public final Object invoke(Object obj) throws Exception {
        int i = this.f$0;
        SQLiteStatement sQLiteStatementPrepare = ((SQLiteConnection) obj).prepare("delete from sep_edit_info_table where user_id = ?");
        try {
            sQLiteStatementPrepare.bindLong(1, i);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }
}
