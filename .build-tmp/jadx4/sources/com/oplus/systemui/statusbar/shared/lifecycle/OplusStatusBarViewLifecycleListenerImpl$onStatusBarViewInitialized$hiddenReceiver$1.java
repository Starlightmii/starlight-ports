package com.oplus.systemui.statusbar.shared.lifecycle;

import android.graphics.Rect;
import android.util.Log;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1 implements HiddenIconDispatcher.HiddenReceiver {
    public /* synthetic */ NotificationIconContainer $notificationIconContainer;
    public /* synthetic */ Ref.BooleanRef $shouldScale;
    public ArrayList hiddenAreas;

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println(OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$hiddenReceiver$1.class.getSimpleName().concat("state: "));
        printWriter.println("\thiddenArea=" + this.hiddenAreas);
    }

    public final ArrayList<Rect> getHiddenAreas() {
        return this.hiddenAreas;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public final void onHiddenChanged(ArrayList arrayList, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusStatusBarViewLifecycleListenerImpl-->".concat("onHiddenChanged: hiddenAreas:" + this.hiddenAreas + " areas:" + arrayList));
        }
        this.hiddenAreas.clear();
        this.hiddenAreas.addAll(arrayList);
        this.$shouldScale.element = z;
        this.$notificationIconContainer.updateState();
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }
}
