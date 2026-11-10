package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import android.support.v4.content.FileProvider$;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseActionForAB extends BaseAction {
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseAction
    public final Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        if (gridInfoEntity == null) {
            Path$.ExternalSyntheticBUOutline0.m(FileProvider$.ExternalSyntheticOutline0.m("[apply] gridInfo is null for ", ShareTransitionProperty.Companion.simpleNameAndHash(this)));
            return null;
        }
        if (list == null) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        if (list2 == null) {
            list2 = CollectionsKt__CollectionsKt.emptyList();
        }
        return applyAB(list, gridInfoEntity, list2, continuation);
    }

    public abstract Object applyAB(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation);
}
