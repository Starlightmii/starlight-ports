package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.util.Log;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.data.model.TileTypeConfig;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.BrightnessVolumeViewModel;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.MediaViewModel;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.TileViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.GridDataCache;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.QSTileAndConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.TileUiType;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolderKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileDataViewModelMapper {
    private static final String BRIGHTNESS_VOLUME_SPEC = "innerFake_brightness_volume";
    public static final Companion Companion = new Companion();
    private static final String MEDIA_SPEC = "innerFake_media";
    private static final String RINGERMODE_SPEC = "ringermode";
    private static final String TAG = "TileDataViewModelMapper";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Object convertTileConfigToViewModel(QSTileAndConfig qSTileAndConfig, List<TileTypeConfig> list) {
            String tileSpec = qSTileAndConfig.getUiConfig().getTileSpec();
            int iHashCode = tileSpec.hashCode();
            if (iHashCode != -1999972780) {
                if (iHashCode != -1480077968) {
                    if (iHashCode == 1105097290 && tileSpec.equals(OplusInnerFakeTile.SPEC_DEVICE)) {
                        return null;
                    }
                } else if (tileSpec.equals("innerFake_media")) {
                    return MediaViewModel.INSTANCE;
                }
            } else if (tileSpec.equals("innerFake_brightness_volume")) {
                return BrightnessVolumeViewModel.INSTANCE;
            }
            return GridDataCache.INSTANCE.tileViewModel(qSTileAndConfig.getQsTile(), true, SepTileType.OTHERS, qSTileAndConfig.getQsTile().getTileSpec(), tileSpec.equals("ringermode") ? TileUiType.THREE_STAGE : TileUiType.DEFAULT);
        }

        public final String getSpecFromGridData(GridData gridData) {
            Object data = gridData.getData();
            if (data instanceof TileViewModel) {
                return ((TileViewModel) data).getSpec();
            }
            if (data instanceof MediaViewModel) {
                return "innerFake_media";
            }
            if (data instanceof BrightnessVolumeViewModel) {
                return "innerFake_brightness_volume";
            }
            if (data instanceof QsPanelPlugin) {
                return ((QsPanelPlugin) data).getSpec();
            }
            LogUtil.e$default(8, LogUtil.TAG_QS, TileDataViewModelMapper.TAG, "getSpecFromGridData cannot get valid spec string");
            return "";
        }

        public final int getStateFromGridData(GridData gridData) {
            Object data = gridData.getData();
            if (!(data instanceof TileViewModel)) {
                return 0;
            }
            TileViewModel tileViewModel = (TileViewModel) data;
            return tileViewModel.getUiType() == TileUiType.THREE_STAGE ? tileViewModel.getQsTile().getState().threeStageMode : tileViewModel.getQsTile().getState().state;
        }
    }

    public Pair<List<GridData>, List<Loc>> map(Pair<? extends List<QSTileAndConfig>, ? extends List<TileTypeConfig>> pair) {
        Iterable<QSTileAndConfig> iterable = (Iterable) pair.getFirst();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (QSTileAndConfig qSTileAndConfig : iterable) {
            arrayList.add(GridDataCache.INSTANCE.gridData(GridViewHolderKt.toUniqueLong(qSTileAndConfig.getUiConfig().getTileSpec()), qSTileAndConfig.getUiConfig().getSize(), Companion.convertTileConfigToViewModel(qSTileAndConfig, (List) pair.getSecond()), qSTileAndConfig.getUiConfig().getSupportSizes(), true));
        }
        Iterable iterable2 = (Iterable) pair.getFirst();
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable2, 10));
        Iterator it = iterable2.iterator();
        while (it.hasNext()) {
            arrayList2.add(((QSTileAndConfig) it.next()).getUiConfig().getLocation());
        }
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            if (!Intrinsics.areEqual((Loc) obj, LocCache.INSTANCE.getINVALID_LOC())) {
                arrayList3.add(obj);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileDataViewModelMapper-->".concat("[map] allValidLoc=" + arrayList3));
        }
        return new Pair<>(arrayList, arrayList3);
    }
}
