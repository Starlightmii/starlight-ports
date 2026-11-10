package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.util.Log;
import android.view.ViewTreeObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerAnimatorViewHelper$childrenUpdater$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ ContainerAnimatorViewHelper this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->onPreDraw: Capule");
        }
        this.this$0.onBeforeChildrenUpdate();
        ContainerAnimatorViewHelper.access$updateChildren(this.this$0);
        ContainerAnimatorViewHelper containerAnimatorViewHelper = this.this$0;
        containerAnimatorViewHelper.childrenUpdateRequested = false;
        containerAnimatorViewHelper.hostView.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
