package com.oplus.systemui.qs.tiles;

import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScreenshotTile$OplusScreenshotDetailAdapter$updateItem$2 implements OplusQSDetailItems.Callback {
    public /* synthetic */ List $list;
    public /* synthetic */ ScreenshotTile.OplusScreenshotDetailAdapter this$0;

    @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
    public final void onDetailItemClick(OplusQSDetailItems.Item item) {
        ScreenshotTile.OplusScreenshotDetailAdapter.access$onItemClick(this.this$0, CollectionsKt___CollectionsKt.indexOf(this.$list, item));
    }

    @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
    public final void onDetailItemDisconnect(OplusQSDetailItems.Item item) {
    }
}
