package com.oplus.systemui.qs.base.temporarilyinfo;

import java.util.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DefaultDuplicateStrategy implements IRemoveDuplicateStrategy {
    public static final DefaultDuplicateStrategy INSTANCE = new DefaultDuplicateStrategy();

    @Override // com.oplus.systemui.qs.base.temporarilyinfo.IRemoveDuplicateStrategy
    public void handleDuplicate(TemporarilyInfoManager.Element element, ArrayDeque<TemporarilyInfoManager.Element> arrayDeque) {
        String groupId;
        TemporarilyInfoManager.Element element2;
        if (element != null && !TemporarilyInfoManager.GROUP_NON.equals(element.getGroupId())) {
            TemporarilyInfoManager.Element elementPoll = null;
            while (true) {
                TemporarilyInfoManager.Element elementPeek = arrayDeque.peek();
                if (!Intrinsics.areEqual(elementPeek != null ? elementPeek.getGroupId() : null, element.getGroupId())) {
                    break;
                } else {
                    elementPoll = arrayDeque.poll();
                }
            }
            if (elementPoll != null && !Intrinsics.areEqual(elementPoll.getText(), element.getText())) {
                arrayDeque.offerFirst(elementPoll);
            }
        }
        TemporarilyInfoManager.Element elementPeek2 = arrayDeque.peek();
        if (elementPeek2 == null || (groupId = elementPeek2.getGroupId()) == null || TemporarilyInfoManager.GROUP_NON.equals(groupId)) {
            return;
        }
        TemporarilyInfoManager.Element elementPoll2 = arrayDeque.poll();
        while (true) {
            element2 = elementPoll2;
            TemporarilyInfoManager.Element elementPeek3 = arrayDeque.peek();
            if (!Intrinsics.areEqual(elementPeek3 != null ? elementPeek3.getGroupId() : null, groupId)) {
                break;
            } else {
                elementPoll2 = arrayDeque.poll();
            }
        }
        if (element2 != null) {
            arrayDeque.offerFirst(element2);
        }
    }
}
