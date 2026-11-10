package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.Converters;
import com.oplus.utrace.lib.HLogConst;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SepEditTileDaoWrapper_Impl f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ Object f$2;

    public /* synthetic */ SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invoke(Object obj) throws Exception {
        switch (this.$r8$classId) {
            case 0:
                SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl = this.f$0;
                int i = this.f$1;
                String str = (String) this.f$2;
                Converters converters = sepEditTileDaoWrapper_Impl.__converters;
                SQLiteStatement sQLiteStatementPrepare = ((SQLiteConnection) obj).prepare("select * from sep_edit_info_table where user_id = ? and tile_spec = ?");
                try {
                    sQLiteStatementPrepare.bindLong(1, i);
                    if (str == null) {
                        sQLiteStatementPrepare.bindNull(2);
                    } else {
                        sQLiteStatementPrepare.bindText(2, str);
                    }
                    int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "user_id");
                    int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "tile_spec");
                    int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "loc");
                    int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, HLogConst.KEY_SIZE);
                    int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info");
                    int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info_two");
                    int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info_three");
                    Object sepTileEntity = null;
                    if (sQLiteStatementPrepare.step()) {
                        sepTileEntity = new SepTileEntity((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.isNull(columnIndexOrThrow2) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow2), converters.stringToLoc(sQLiteStatementPrepare.isNull(columnIndexOrThrow3) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow3)), converters.stringToSpanSize(sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4)), sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7));
                    }
                    return sepTileEntity;
                } finally {
                    sQLiteStatementPrepare.close();
                }
            default:
                return super/*com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper*/.setTileInfoByTransaction(this.f$1, (List) this.f$2, (Continuation) obj);
        }
    }
}
