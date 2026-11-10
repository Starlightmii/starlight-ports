package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.Converters;
import com.oplus.utrace.lib.HLogConst;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ int f$1;

    public /* synthetic */ SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) throws Exception {
        switch (this.$r8$classId) {
            case 0:
                SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl = (SepEditTileDaoWrapper_Impl) this.f$0;
                int i = this.f$1;
                Converters converters = sepEditTileDaoWrapper_Impl.__converters;
                SQLiteStatement sQLiteStatementPrepare = ((SQLiteConnection) obj).prepare("select * from sep_edit_info_table where user_id = ?");
                try {
                    sQLiteStatementPrepare.bindLong(1, i);
                    int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "user_id");
                    int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "tile_spec");
                    int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "loc");
                    int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, HLogConst.KEY_SIZE);
                    int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info");
                    int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info_two");
                    int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "expand_info_three");
                    ArrayList arrayList = new ArrayList();
                    while (sQLiteStatementPrepare.step()) {
                        arrayList.add(new SepTileEntity((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.isNull(columnIndexOrThrow2) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow2), converters.stringToLoc(sQLiteStatementPrepare.isNull(columnIndexOrThrow3) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow3)), converters.stringToSpanSize(sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4)), sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7)));
                        break;
                    }
                    return arrayList;
                } finally {
                    sQLiteStatementPrepare.close();
                }
            case 1:
                SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl2 = (SepEditTileDaoWrapper_Impl) this.f$0;
                int i2 = this.f$1;
                Converters converters2 = sepEditTileDaoWrapper_Impl2.__converters;
                SQLiteStatement sQLiteStatementPrepare2 = ((SQLiteConnection) obj).prepare("select * from sep_edit_info_table where user_id = ?");
                try {
                    sQLiteStatementPrepare2.bindLong(1, i2);
                    int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "user_id");
                    int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "tile_spec");
                    int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "loc");
                    int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, HLogConst.KEY_SIZE);
                    int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "expand_info");
                    int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "expand_info_two");
                    int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare2, "expand_info_three");
                    ArrayList arrayList2 = new ArrayList();
                    while (sQLiteStatementPrepare2.step()) {
                        arrayList2.add(new SepTileEntity((int) sQLiteStatementPrepare2.getLong(columnIndexOrThrow8), sQLiteStatementPrepare2.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow9), converters2.stringToLoc(sQLiteStatementPrepare2.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow10)), converters2.stringToSpanSize(sQLiteStatementPrepare2.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow11)), sQLiteStatementPrepare2.isNull(columnIndexOrThrow12) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow12), sQLiteStatementPrepare2.isNull(columnIndexOrThrow13) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow13), sQLiteStatementPrepare2.isNull(columnIndexOrThrow14) ? null : sQLiteStatementPrepare2.getText(columnIndexOrThrow14)));
                        break;
                    }
                    return arrayList2;
                } finally {
                    sQLiteStatementPrepare2.close();
                }
            default:
                int i3 = this.f$1;
                String str = (String) this.f$0;
                SQLiteStatement sQLiteStatementPrepare3 = ((SQLiteConnection) obj).prepare("delete from sep_edit_info_table where user_id = ? and tile_spec = ?");
                try {
                    sQLiteStatementPrepare3.bindLong(1, i3);
                    if (str == null) {
                        sQLiteStatementPrepare3.bindNull(2);
                    } else {
                        sQLiteStatementPrepare3.bindText(2, str);
                    }
                    sQLiteStatementPrepare3.step();
                    return Unit.INSTANCE;
                } finally {
                    sQLiteStatementPrepare3.close();
                }
        }
    }
}
