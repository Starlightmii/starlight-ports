package com.oplus.systemui.plugins.qs.customize.viewmodel;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.base.util.SeparateTileInfoData;
import com.oplus.systemui.qs.tiles.AliPayTile;
import com.oplus.systemui.qs.tiles.AliScanTile;
import com.oplus.systemui.qs.tiles.CalculatorTile;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplus.systemui.qs.tiles.ScreenshotTile;
import com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile;
import com.oplus.systemui.qs.tiles.WeChatScanTile;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolderKt;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatisticHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatisticHandler";
    private final ContextInstance contextInstance;
    private TileEditViewModel editViewModel;
    private final List<String> entryTile = CollectionsKt__CollectionsKt.listOf(new String[]{AliPayTile.TILE_SPEC, AliScanTile.TILE_SPEC, CalculatorTile.TILE_SPEC, "clear", LockTile.TILE_SPEC, ScreenshotTile.TILE_SPEC, SuperPowerSaveModeTile.TILE_SPEC, WeChatScanTile.TILE_SPEC, "weixinpay", "QRCodeScanner"});
    private final List<String> noneTile = CollectionsKt__CollectionsKt.listOf(new String[]{OplusInnerFakeTile.SPEC_DEVICE, OplusInnerFakeTile.SPEC_MEDIA, OplusInnerFakeTile.SPEC_BRIGHTNESS_VOLUME});
    private String beforeTilesMessage = "";
    private final StatisticHandler$receiver$1 receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler$receiver$1
        public final String getTileString(List list, int i, Integer num) {
            String str;
            if (i == 0) {
                return "";
            }
            List list2 = list;
            StatisticHandler statisticHandler = this.this$0;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            int i2 = 0;
            int i3 = 0;
            for (Object obj : list2) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                GridData gridData = (GridData) obj;
                if (gridData.getData() != null) {
                    int iIntValue = num != null ? num.intValue() : i3 / i;
                    int i5 = i3 % i;
                    int i6 = i5 / 4;
                    int i7 = i5 % 4;
                    TileDataViewModelMapper.Companion companion = TileDataViewModelMapper.Companion;
                    String specFromGridData = companion.getSpecFromGridData(gridData);
                    int iAccess$getStatisticState = StatisticHandler.access$getStatisticState(statisticHandler, specFromGridData, companion.getStateFromGridData(gridData));
                    if (StringsKt__StringsKt.contains(specFromGridData, "custom(", false)) {
                        specFromGridData = statisticHandler.getStaticSpec(specFromGridData);
                    }
                    str = specFromGridData + "-(1,1)-(" + iIntValue + OplusPreventModeData.SEPARATOR + i7 + OplusPreventModeData.SEPARATOR + i6 + ")-" + iAccess$getStatisticState;
                } else {
                    str = "";
                }
                arrayList.add(str);
                i3 = i4;
            }
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            while (i2 < size) {
                Object obj2 = arrayList.get(i2);
                i2++;
                if (((String) obj2).length() > 0) {
                    arrayList2.add(obj2);
                }
            }
            return CollectionsKt___CollectionsKt.joinToString$default(arrayList2, ";", (CharSequence) null, (CharSequence) null, (Function1) null, 62);
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String string;
            String string2;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onReceive:", intent != null ? intent.getAction() : null, "StatisticHandler-->", "SystemUi--QuickSettings");
            }
            TileEditViewModel tileEditViewModel = this.this$0.editViewModel;
            if (tileEditViewModel != null) {
                StatisticHandler statisticHandler = this.this$0;
                TileEditState.Normal normalState = tileEditViewModel.getNormalState();
                int size = normalState.getOtherAddedTiles().size() + normalState.getAllPersonalTiles().size();
                SeparateTileInfoData separateTileInfoData = SeparateQSStatisicUtil.getSeparateTileInfoData();
                StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(size, "tilesSize:", "  A:");
                sbM.append(normalState.getAllPersonalTiles().size());
                sbM.append("  B:");
                sbM.append(normalState.getOtherAddedTiles().size());
                separateTileInfoData.setQsTilesCount(sbM.toString());
                List<GridData> allPersonalTiles = normalState.getAllPersonalTiles();
                List<Loc> allPersonalTilesLoc = normalState.getAllPersonalTilesLoc();
                Iterator<T> it = allPersonalTiles.iterator();
                Iterator<T> it2 = allPersonalTilesLoc.iterator();
                ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(allPersonalTiles, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(allPersonalTilesLoc, 10)));
                while (it.hasNext() && it2.hasNext()) {
                    Object next = it.next();
                    Loc loc = (Loc) it2.next();
                    GridData gridData = (GridData) next;
                    if (gridData.getData() != null) {
                        SpanSize spanSizeRevertToTwoCols = DataConvertorKt.revertToTwoCols(gridData.getSize());
                        TileDataViewModelMapper.Companion companion = TileDataViewModelMapper.Companion;
                        String specFromGridData = companion.getSpecFromGridData(gridData);
                        int stateFromGridData = companion.getStateFromGridData(gridData);
                        int iAccess$getStatisticState = (statisticHandler.noneTile.contains(specFromGridData) && stateFromGridData == 0) ? 5 : StatisticHandler.access$getStatisticState(statisticHandler, specFromGridData, stateFromGridData);
                        if (StringsKt__StringsKt.contains(specFromGridData, "custom(", false)) {
                            specFromGridData = statisticHandler.getStaticSpec(specFromGridData);
                        }
                        StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(specFromGridData, "-");
                        sbM2.append(MutableVectorKt$.ExternalSyntheticOutline0.m("(", spanSizeRevertToTwoCols.getColSpan(), spanSizeRevertToTwoCols.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
                        sbM2.append("-");
                        int pageIndex = loc.getPageIndex();
                        int startCol = loc.getStartCol();
                        int startRow = loc.getStartRow();
                        StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("(", pageIndex, startCol, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR);
                        sbM3.append(startRow);
                        sbM3.append(")");
                        sbM2.append(sbM3.toString());
                        sbM2.append("-");
                        sbM2.append(iAccess$getStatisticState);
                        string2 = sbM2.toString();
                    } else {
                        string2 = "";
                    }
                    arrayList.add(string2);
                }
                String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ";", (CharSequence) null, (CharSequence) null, (Function1) null, 62);
                if (context != null) {
                    CellCalculatorManager companion2 = CellCalculatorManager.Companion.getInstance(context);
                    if (context.getResources().getConfiguration().orientation == 1) {
                        string = getTileString(normalState.getOtherAddedTiles(), companion2.getNoPersonalRowCountPort() * 4, null);
                    } else {
                        int noPersonalRowCountLandLeft = companion2.getNoPersonalRowCountLandLeft() * 4;
                        List listTake = CollectionsKt___CollectionsKt.take(normalState.getOtherAddedTiles(), noPersonalRowCountLandLeft);
                        List listDrop = CollectionsKt___CollectionsKt.drop(normalState.getOtherAddedTiles(), noPersonalRowCountLandLeft);
                        int noPersonalRowCountLandRight = companion2.getNoPersonalRowCountLandRight() * 4;
                        StringBuilder sb = new StringBuilder();
                        if (noPersonalRowCountLandLeft > 0) {
                            sb.append(getTileString(listTake, noPersonalRowCountLandLeft, 99));
                            sb.append(";");
                        }
                        sb.append(getTileString(listDrop, noPersonalRowCountLandRight, null));
                        string = sb.toString();
                    }
                    SeparateQSStatisicUtil.getSeparateTileInfoData().setQsTilesSpec(strJoinToString$default + ";" + string);
                }
                if (context != null) {
                    SeparateQSStatisicUtil.uploadQsPersonalizedEditStatisticsEvent(context);
                    SeparateQSStatisicUtil.uploadLeftToRightSwitchEvent(context);
                    SeparateQSStatisicUtil.collectQsTopCapsuleClickTypeEvent(context);
                }
            }
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler$registerReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Context $context;
        public /* synthetic */ TileEditViewModel $editViewModel;
        public /* synthetic */ StatisticHandler this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.editViewModel = this.$editViewModel;
            this.$context.registerReceiverAsUser(this.this$0.receiver, UserHandle.ALL, new IntentFilter("android.intent.action.DATE_CHANGED"), null, SystemUIThreadUtil.getWorkHandler());
        }
    }

    /* JADX WARN: Type inference failed for: r11v7, types: [com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler$receiver$1] */
    public StatisticHandler(ContextInstance contextInstance) {
        this.contextInstance = contextInstance;
    }

    public static final int access$getStatisticState(StatisticHandler statisticHandler, String str, int i) {
        if (statisticHandler.entryTile.contains(str)) {
            if (i == 0) {
                return 3;
            }
            if (i == 1) {
                return 4;
            }
        }
        return i;
    }

    public final void addOneTile(String str, SpanSize spanSize, IntentTarget intentTarget) {
        SeparateQSStatisicUtil.getEditTitleData().setType(str);
        SeparateQSStatisicUtil.setContext(this.contextInstance.getSysUiContext());
        if (intentTarget != IntentTarget.PERSONAL_LIST) {
            SeparateQSStatisicUtil.getEditTitleData().setAreaAfter("B");
            SeparateQSStatisicUtil.getEditTitleData().setSizeAfter(MutableVectorKt$.ExternalSyntheticOutline0.m("(", spanSize.getColSpan(), spanSize.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
            return;
        }
        SeparateQSStatisicUtil.getEditTitleData().setAreaAfter("A");
        if (OplusInnerFakeTile.SPEC_DEVICE.equals(str)) {
            SeparateQSStatisicUtil.getEditTitleData().setSizeAfter(MutableVectorKt$.ExternalSyntheticOutline0.m("(", spanSize.getColSpan(), spanSize.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
        } else {
            SeparateQSStatisicUtil.getEditTitleData().setSizeAfter(MutableVectorKt$.ExternalSyntheticOutline0.m("(", DataConvertorKt.revertToTwoCols(spanSize).getColSpan(), spanSize.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
        }
    }

    public final ContextInstance getContextInstance() {
        return this.contextInstance;
    }

    public final String getStaticSpec(String str) {
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(str, '.');
        int iLastIndexOf$default2 = StringsKt__StringsKt.lastIndexOf$default(str, ')');
        String strSubstring = "";
        String strSubstring2 = (iLastIndexOf$default == -1 || iLastIndexOf$default2 == -1) ? "" : str.substring(iLastIndexOf$default + 1, iLastIndexOf$default2);
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default(str, '(', 0, false, 6);
        int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default(str, '/', 0, false, 6);
        if (iIndexOf$default2 != -1 && iIndexOf$default != -1) {
            strSubstring = str.substring(iIndexOf$default + 1, iIndexOf$default2);
        }
        return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(SeparateQSStatisicUtil.getPackageNameHash(strSubstring), ":", strSubstring2);
    }

    public final String getTilesMessage(TileEditState.Normal normal, PanelPluginInteractor panelPluginInteractor) {
        String specFromGridData;
        Object next;
        List<GridData> allPersonalTiles = normal.getAllPersonalTiles();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allPersonalTiles, 10));
        for (GridData gridDataCopy$default : allPersonalTiles) {
            if (gridDataCopy$default.getData() == null) {
                Iterator it = ((Iterable) panelPluginInteractor.getDataFlow().getValue()).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (GridViewHolderKt.toUniqueLong(((QsPanelPlugin) next).getSpec()) != gridDataCopy$default.getUniqueId());
                gridDataCopy$default = GridData.copy$default(gridDataCopy$default, null, next, 27);
            }
            arrayList.add(gridDataCopy$default);
        }
        TileEditState.Normal normalCopy$default = TileEditState.Normal.copy$default(normal, arrayList, null, null, false, false, 30);
        List<GridData> allPersonalTiles2 = normalCopy$default.getAllPersonalTiles();
        List<Loc> allPersonalTilesLoc = normalCopy$default.getAllPersonalTilesLoc();
        int size = allPersonalTiles2.size();
        String str = "";
        for (int i = 0; i < size; i++) {
            GridData gridData = allPersonalTiles2.get(i);
            try {
                specFromGridData = TileDataViewModelMapper.Companion.getSpecFromGridData(gridData);
            } catch (IllegalStateException e) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "StatisticHandler-->".concat("e:" + e));
                }
                specFromGridData = Unit.INSTANCE;
            }
            String str2 = str + ((Object) specFromGridData);
            Loc loc = allPersonalTilesLoc.get(i);
            StringBuilder sb = new StringBuilder("-");
            sb.append(loc.getPageIndex());
            sb.append("-");
            sb.append(loc.getStartCol());
            sb.append("-");
            sb.append(loc.getStartRow());
            sb.append("-");
            sb.append(gridData.getSize().getRowSpan());
            sb.append("-");
            sb.append(gridData.getSize().getColSpan());
            if (i != CollectionsKt__CollectionsKt.getLastIndex(allPersonalTiles2)) {
                sb.append(";");
            }
            str = str2 + ((Object) sb);
        }
        return str;
    }

    public final void registerReceiver(Context context, TileEditViewModel tileEditViewModel) {
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$editViewModel = tileEditViewModel;
        anonymousClass1.$context = context;
        VarHandle.storeStoreFence();
        workHandler.post(anonymousClass1);
    }

    public final void removeOneTile(String str, SpanSize spanSize, IntentTarget intentTarget) {
        SeparateQSStatisicUtil.getEditTitleData().setType(str);
        if (intentTarget == IntentTarget.PERSONAL_LIST) {
            SeparateQSStatisicUtil.getEditTitleData().setAreaBefore("A");
            SeparateQSStatisicUtil.getEditTitleData().setSizeBefore(MutableVectorKt$.ExternalSyntheticOutline0.m("(", DataConvertorKt.revertToTwoCols(spanSize).getColSpan(), spanSize.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
        } else {
            SeparateQSStatisicUtil.getEditTitleData().setAreaBefore("B");
            SeparateQSStatisicUtil.getEditTitleData().setSizeBefore(MutableVectorKt$.ExternalSyntheticOutline0.m("(", spanSize.getColSpan(), spanSize.getRowSpan(), OplusPreventModeData.SEPARATOR, ")"));
        }
        SeparateQSStatisicUtil.collectEditTitleTypeEvent(this.contextInstance.getSysUiContext(), "del_type", false);
    }

    public final void setBeforeTilesMessage(String str) {
        this.beforeTilesMessage = str;
    }

    public final void uploadStatistic(TileEditState.Normal normal, PanelPluginInteractor panelPluginInteractor) {
        String tilesMessage = getTilesMessage(normal, panelPluginInteractor);
        if (Intrinsics.areEqual(this.beforeTilesMessage, tilesMessage)) {
            return;
        }
        SeparateQSStatisicUtil.collectQsTileEditChangeEvent(this.contextInstance.getSysUiContext(), this.beforeTilesMessage, tilesMessage);
    }
}
