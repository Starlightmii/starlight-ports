package com.oplus.systemui.qs.base.temporarilyinfo;

import java.util.ArrayDeque;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IRemoveDuplicateStrategy {
    void handleDuplicate(TemporarilyInfoManager.Element element, ArrayDeque<TemporarilyInfoManager.Element> arrayDeque);
}
