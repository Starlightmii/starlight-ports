package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.graphics.Rect;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconSetViewModel$isTipHidden$1$receiver$1 implements HiddenIconDispatcher.HiddenReceiver {
    public /* synthetic */ ProducerScope $$this$callbackFlow;
    public /* synthetic */ DynamicIconSetViewModel this$0;

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("DynamicIconSetViewModel");
        DynamicIconSetViewModel dynamicIconSetViewModel = this.this$0;
        printWriter.println("isTipHidden=" + dynamicIconSetViewModel.isTipHidden().getValue());
        printWriter.println("activeModelFlow=" + dynamicIconSetViewModel.activeModelFlow.getValue());
        printWriter.println("tipFlow=" + dynamicIconSetViewModel.getTipFlow().getValue());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("iconFlow=", ((Map) dynamicIconSetViewModel.getIconMapFlow().getValue()).size(), printWriter);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public final void onHiddenChanged(ArrayList arrayList, boolean z) {
        boolean z2 = false;
        if (arrayList == null || !arrayList.isEmpty()) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                Rect rect = (Rect) obj;
                if (rect.right > rect.left) {
                    z2 = true;
                    break;
                }
            }
        }
        this.$$this$callbackFlow.trySend-JP2dKIU(Boolean.valueOf(z2));
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }
}
