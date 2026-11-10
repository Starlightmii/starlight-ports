package com.oplus.systemui.qs.pipeline.domain.autoaddable;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.DefaultTilesRepository;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticPromoteTileAutoAddable extends PromoteTileAutoAddable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StaticPromoteTileAutoAddable";
    public final boolean isPromoteTile;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Set<AutoAddable> parsePromoteTileResource(Resources resources, Factory factory) {
            List tilesList = TilesSettingConverter.INSTANCE.toTilesList(resources.getString(2131955866));
            LogUtil.i$default(LogUtil.TAG_QS, StaticPromoteTileAutoAddable.TAG, "parsePromotionTileResource: promotionTiles=" + tilesList, null, 8);
            List list = tilesList;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(factory.create((TileSpec) it.next()));
            }
            return CollectionsKt___CollectionsKt.toSet(arrayList);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        StaticPromoteTileAutoAddable create(TileSpec tileSpec);
    }

    public StaticPromoteTileAutoAddable(DefaultTilesRepository defaultTilesRepository, TileSpec tileSpec) {
        super(tileSpec);
        this.isPromoteTile = defaultTilesRepository.getDefaultTiles(false).contains(tileSpec);
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public String getDescription() {
        return "StaticPromoteTileAutoAddable: " + getSpec() + " (" + getAutoAddTracking() + ")";
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public final boolean isPromoteTile() {
        return this.isPromoteTile;
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public /* bridge */ /* synthetic */ Object preProcessRestore(RestoreData restoreData, Continuation continuation) {
        return super.preProcessRestore(restoreData, continuation);
    }
}
