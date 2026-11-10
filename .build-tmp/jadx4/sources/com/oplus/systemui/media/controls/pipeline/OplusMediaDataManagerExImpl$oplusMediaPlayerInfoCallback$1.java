package com.oplus.systemui.media.controls.pipeline;

import com.oplus.media.OplusMediaPlayerInfo;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1 implements OplusMediaPlayerManager.MediaPlayerDataInfoCallback {
    public /* synthetic */ OplusMediaDataManagerExImpl this$0;

    @Override // com.oplus.systemui.media.controls.pipeline.OplusMediaPlayerManager.MediaPlayerDataInfoCallback
    public final void onMediaPlayerDataInfoChanged(int i, List list) {
        OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl = this.this$0;
        OplusNotificationLogger.logMediaPanelNormal(OplusMediaDataManagerExImpl.TAG, "onMediaPlayerDataInfoChanged playerInfo:" + list);
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((OplusMediaPlayerInfo) it.next()).getPlayerId());
        }
        Set setEntrySet = oplusMediaDataManagerExImpl.playerInfoMap.entrySet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : setEntrySet) {
            if (!arrayList.contains(((Map.Entry) obj).getKey())) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
        int size = arrayList2.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj2 = arrayList2.get(i3);
            i3++;
            arrayList3.add((MediaPlayerDataInfo) ((Map.Entry) obj2).getValue());
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            OplusMediaDataManagerExImpl.access$onMediaPlayerInfoAdded(oplusMediaDataManagerExImpl, new MediaPlayerDataInfo((OplusMediaPlayerInfo) it2.next(), i));
        }
        int size2 = arrayList3.size();
        while (i2 < size2) {
            Object obj3 = arrayList3.get(i2);
            i2++;
            OplusMediaDataManagerExImpl.access$onMediaPlayerInfoRemoved(oplusMediaDataManagerExImpl, (MediaPlayerDataInfo) obj3);
        }
    }
}
