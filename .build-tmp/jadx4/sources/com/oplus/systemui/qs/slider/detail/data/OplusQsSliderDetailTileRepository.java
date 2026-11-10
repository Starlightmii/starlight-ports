package com.oplus.systemui.qs.slider.detail.data;

import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.qs.slider.detail.tile.QsDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl;
import com.oplus.systemui.qs.slider.detail.tile.volume.MediaAudioMuteDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.volume.NoiseReductionDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.volume.SpatialAudioDetailTile;
import com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile;
import com.oplus.systemui.qs.tiles.DarkModeTile;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSliderDetailTileRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsSliderDetailTileRepository";
    private final MutableStateFlow _tiles;
    public int detailType;
    private final CopyOnWriteArrayList<QsDetailTile> tempTiles;
    private final OplusQsDetailTileFactory tileFactory;
    private final Map<Integer, List<String>> tileSpecs;
    private final StateFlow tiles;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsSliderDetailTileRepository(OplusQsDetailTileFactory oplusQsDetailTileFactory) {
        this.tileFactory = oplusQsDetailTileFactory;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(1, CollectionsKt__CollectionsKt.listOf(new String[]{"autobrightness", DarkModeTile.TILE_SPEC, AutoScreenTemperatureTile.TILE_SPEC}));
        linkedHashMap.put(2, CollectionsKt__CollectionsKt.listOf(new String[]{MediaAudioMuteDetailTile.TILE_SPEC, NoiseReductionDetailTile.TILE_SPEC, SpatialAudioDetailTile.TILE_SPEC}));
        this.tileSpecs = linkedHashMap;
        this.detailType = 1;
        this.tempTiles = new CopyOnWriteArrayList<>();
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList());
        this._tiles = MutableStateFlow;
        this.tiles = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final void createTiles(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "createTiles: detailType = ", "OplusQsSliderDetailTileRepository-->", "SystemUi--QuickSettings");
        }
        this.detailType = i;
        Iterator<T> it = this.tempTiles.iterator();
        while (it.hasNext()) {
            ((QsDetailTile) it.next()).setListening(false);
        }
        this.tempTiles.clear();
        List<String> list = this.tileSpecs.get(Integer.valueOf(i));
        if (list != null) {
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                QsDetailTile qsDetailTileCreateTile = this.tileFactory.createTile((String) it2.next());
                if (qsDetailTileCreateTile != null) {
                    ((QsDetailTileImpl) qsDetailTileCreateTile).initialize$1();
                    this.tempTiles.add(qsDetailTileCreateTile);
                }
            }
        }
        MutableStateFlow mutableStateFlow = this._tiles;
        CopyOnWriteArrayList<QsDetailTile> copyOnWriteArrayList = this.tempTiles;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            if (((QsDetailTile) obj).isAvailable()) {
                arrayList.add(obj);
            }
        }
        mutableStateFlow.setValue(arrayList);
    }

    public final StateFlow getTiles() {
        return this.tiles;
    }

    public final void release() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsSliderDetailTileRepository-->release: ");
        }
        Iterator<T> it = this.tempTiles.iterator();
        while (it.hasNext()) {
            ((QsDetailTileImpl) ((QsDetailTile) it.next())).destroy();
        }
        this.tempTiles.clear();
        this._tiles.setValue(CollectionsKt__CollectionsKt.emptyList());
    }

    public final void updateTiles() {
        CopyOnWriteArrayList<QsDetailTile> copyOnWriteArrayList = this.tempTiles;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            QsDetailTile qsDetailTile = (QsDetailTile) obj;
            if (qsDetailTile.getDetailType() == this.detailType && qsDetailTile.isAvailable()) {
                arrayList.add(obj);
            } else {
                ((QsDetailTileImpl) qsDetailTile).destroy();
            }
        }
        List list = (List) this.tiles.getValue();
        int i = 0;
        boolean z = arrayList.size() != list.size();
        if (!z) {
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj2 = arrayList.get(i2);
                i2++;
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                if (!Intrinsics.areEqual(((QsDetailTile) obj2).getTileSpec(), ((QsDetailTile) list.get(i)).getTileSpec())) {
                    z = true;
                }
                i = i3;
            }
        }
        if (z) {
            this._tiles.setValue(arrayList);
        }
    }
}
