package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.QSQuickEntrance;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

    public /* synthetic */ OplusSeparateQSQuickEntranceManager$onUserInfoChanged$1$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                CopyOnWriteArrayList copyOnWriteArrayList = this.this$0.qsQuickEntranceBacks;
                OplusSeparateQSQuickEntranceManager oplusSeparateQSQuickEntranceManager = this.this$0;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((QSQuickEntrance.CallBack) it.next()).onUserInfoChanged(oplusSeparateQSQuickEntranceManager.description, oplusSeparateQSQuickEntranceManager.icon);
                }
                break;
            default:
                Iterator it2 = this.this$0.qsQuickEntranceBacks.iterator();
                while (it2.hasNext()) {
                    ((QSQuickEntrance.CallBack) it2.next()).onShowUserSwitcherRefreshRequested();
                }
                break;
        }
    }
}
