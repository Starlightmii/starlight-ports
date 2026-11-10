package com.oplus.systemui.qs.utils;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import androidx.collection.FloatList$;
import androidx.core.view.DisplayShapeCompat$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.tiles.AliPayTile;
import com.oplus.systemui.qs.tiles.AliScanTile;
import com.oplus.systemui.qs.tiles.CalculatorTile;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplus.systemui.qs.tiles.ScreenshotTile;
import com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile;
import com.oplus.systemui.qs.tiles.WeChatScanTile;
import com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import com.oplusos.systemui.qs.util.TileInfoData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsSeparateDailyStatisticProxy extends AbsDailyStatisticProxy {
    public static final Companion Companion = new Companion();
    private final QSHost qsHost;
    private final List<String> entryTile = CollectionsKt__CollectionsKt.listOf(new String[]{AliPayTile.TILE_SPEC, AliScanTile.TILE_SPEC, CalculatorTile.TILE_SPEC, "clear", LockTile.TILE_SPEC, ScreenshotTile.TILE_SPEC, SuperPowerSaveModeTile.TILE_SPEC, WeChatScanTile.TILE_SPEC, "weixinpay", "QRCodeScanner"});
    private final TileInfoData tileInfo = QsStatisticUtil.getTileInfoData();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QsSeparateDailyStatisticProxy(QSHost qSHost) {
        this.qsHost = qSHost;
    }

    public final String getTilesSpec(List list, List list2, boolean z) {
        QSTile.State state;
        QsSeparateDailyStatisticProxy qsSeparateDailyStatisticProxy = this;
        int qsTilesRows = qsSeparateDailyStatisticProxy.tileInfo.getQsTilesRows();
        int qsTilesColumns = qsSeparateDailyStatisticProxy.tileInfo.getQsTilesColumns();
        if (list.size() != list2.size() || qsTilesRows == 0 || qsTilesColumns == 0) {
            return "";
        }
        int i = qsTilesRows * qsTilesColumns;
        String str = z ? "2,1" : "1,1";
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String strM = (String) obj;
            QSTile qSTile = (QSTile) CollectionsKt___CollectionsKt.getOrNull(list2, i2);
            int i4 = (qSTile == null || (state = qSTile.getState()) == null) ? 0 : state.state;
            if (qsSeparateDailyStatisticProxy.entryTile.contains(strM)) {
                if (i4 == 0) {
                    i4 = 3;
                } else if (i4 == 1) {
                    i4 = 4;
                }
            }
            if (StringsKt__StringsKt.contains(strM, "custom(", false)) {
                int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(strM, '.');
                int iLastIndexOf$default2 = StringsKt__StringsKt.lastIndexOf$default(strM, ')');
                String strSubstring = (iLastIndexOf$default == -1 || iLastIndexOf$default2 == -1) ? "" : strM.substring(iLastIndexOf$default + 1, iLastIndexOf$default2);
                int iIndexOf$default = StringsKt__StringsKt.indexOf$default(strM, '(', 0, false, 6);
                int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default(strM, '/', 0, false, 6);
                strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(SeparateQSStatisicUtil.getPackageNameHash((iIndexOf$default2 == -1 || iIndexOf$default == -1) ? "" : strM.substring(iIndexOf$default + 1, iIndexOf$default2)), ":", strSubstring);
            }
            int i5 = i2 / i;
            int i6 = i2 % i;
            StringBuilder sb = new StringBuilder();
            sb.append(strM);
            sb.append("-(");
            sb.append(str);
            sb.append(")-(");
            sb.append(i5);
            DisplayShapeCompat$.ExternalSyntheticOutline0.m(i6 % qsTilesColumns, i6 / qsTilesColumns, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, sb);
            sb.append(")-");
            sb.append(i4);
            arrayList.add(sb.toString());
            qsSeparateDailyStatisticProxy = this;
            i2 = i3;
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, ";", (CharSequence) null, (CharSequence) null, (Function1) null, 62);
    }

    @Override // com.oplusos.systemui.common.statistic.AbsDailyStatisticProxy
    public void onStatistic(Context context) {
        int qsTilesColumns = this.tileInfo.getQsTilesColumns() * this.tileInfo.getQsTilesRows();
        if (QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || qsTilesColumns <= 0) {
            return;
        }
        List specs = this.qsHost.getSpecs();
        if (specs == null) {
            specs = CollectionsKt__CollectionsKt.emptyList();
        }
        List tiles = this.qsHost.getTiles();
        if (tiles == null) {
            tiles = CollectionsKt__CollectionsKt.emptyList();
        }
        int iCoerceIn = RangesKt___RangesKt.coerceIn(2, 0, Math.min(specs.size(), tiles.size()));
        int size = specs.size();
        TileInfoData tileInfoData = this.tileInfo;
        int i = size - iCoerceIn;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("tilesSize:", size, iCoerceIn, "  A:", "  B:");
        sbM.append(i);
        tileInfoData.setQsTilesCount(sbM.toString());
        tileInfoData.setQsTilesDotsCount(SeparateQSStatisicUtil.getSeparateTileInfoData().getQsTilesDotsCount());
        List list = specs;
        List listTake = CollectionsKt___CollectionsKt.take(list, iCoerceIn);
        List listDrop = CollectionsKt___CollectionsKt.drop(list, iCoerceIn);
        Collection collection = tiles;
        List listTake2 = CollectionsKt___CollectionsKt.take(collection, iCoerceIn);
        List listDrop2 = CollectionsKt___CollectionsKt.drop(collection, iCoerceIn);
        String tilesSpec = getTilesSpec(listTake, listTake2, true);
        String tilesSpec2 = getTilesSpec(listDrop, listDrop2, false);
        if (tilesSpec.length() <= 0) {
            tilesSpec = null;
        }
        if (tilesSpec2.length() <= 0) {
            tilesSpec2 = null;
        }
        this.tileInfo.setQsTilesSpec(CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt__CollectionsKt.listOfNotNull(new String[]{tilesSpec, tilesSpec2}), ";", (CharSequence) null, (CharSequence) null, (Function1) null, 62));
        QsStatisticUtil.uploadQsPersonalizedEditStatisticsEvent(context);
    }
}
