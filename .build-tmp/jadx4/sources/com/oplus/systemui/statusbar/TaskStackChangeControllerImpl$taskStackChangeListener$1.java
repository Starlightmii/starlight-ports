package com.oplus.systemui.statusbar;

import android.app.ActivityManager;
import com.android.systemui.plugins.statusbar.TaskStackChangeController;
import com.android.systemui.shared.system.TaskStackChangeListener;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TaskStackChangeControllerImpl$taskStackChangeListener$1 implements TaskStackChangeListener {
    public /* synthetic */ TaskStackChangeControllerImpl this$0;

    public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        super.onTaskMovedToFront(runningTaskInfo);
        Iterator it = this.this$0.listeners.iterator();
        while (it.hasNext()) {
            ((TaskStackChangeController.ITaskChangedListener) it.next()).onTaskMovedToFront(runningTaskInfo);
        }
    }

    public final void onTaskRemoved(int i) {
        Iterator it = this.this$0.listeners.iterator();
        while (it.hasNext()) {
            ((TaskStackChangeController.ITaskChangedListener) it.next()).onTaskRemoved(i);
        }
    }
}
