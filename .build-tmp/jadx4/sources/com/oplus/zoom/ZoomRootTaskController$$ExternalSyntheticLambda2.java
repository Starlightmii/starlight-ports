package com.oplus.zoom;

import android.app.ActivityManager;
import android.content.res.Configuration;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomRootTaskController$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ ZoomRootTaskController$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((ZoomRootTaskManager) obj).onTaskVanished((ActivityManager.RunningTaskInfo) obj2);
                break;
            default:
                ((ZoomRootTaskManager) obj).onConfigChanged((Configuration) obj2);
                break;
        }
    }
}
