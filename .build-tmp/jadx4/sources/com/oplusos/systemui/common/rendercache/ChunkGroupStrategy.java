package com.oplusos.systemui.common.rendercache;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChunkGroupStrategy implements RenderBoosterGroupStrategy {
    public static final Companion Companion = new Companion();
    public final int totalFrames;
    public final int viewsPerGroup;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ChunkGroupStrategy(int i, int i2) {
        this.viewsPerGroup = i;
        this.totalFrames = i2;
    }

    @Override // com.oplusos.systemui.common.rendercache.RenderBoosterGroupStrategy
    public int getTotalFrames() {
        return this.totalFrames;
    }

    @Override // com.oplusos.systemui.common.rendercache.RenderBoosterGroupStrategy
    public int getViewsPerGroup() {
        return this.viewsPerGroup;
    }

    @Override // com.oplusos.systemui.common.rendercache.RenderBoosterGroupStrategy
    public List<List<RenderBooster>> partition(List<RenderBooster> list) {
        if (list.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<List<RenderBooster>> listChunked = CollectionsKt___CollectionsKt.chunked(list, getViewsPerGroup());
        if (listChunked.size() <= getTotalFrames()) {
            return listChunked;
        }
        List<List<RenderBooster>> list2 = listChunked;
        return CollectionsKt___CollectionsKt.plus(CollectionsKt___CollectionsKt.take(list2, getTotalFrames() - 1), CollectionsKt__CollectionsJVMKt.listOf(CollectionsKt__IterablesKt.flatten(CollectionsKt___CollectionsKt.drop(list2, getTotalFrames() - 1))));
    }
}
