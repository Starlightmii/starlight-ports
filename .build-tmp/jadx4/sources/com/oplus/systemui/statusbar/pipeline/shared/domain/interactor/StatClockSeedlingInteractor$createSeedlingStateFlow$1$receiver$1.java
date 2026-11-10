package com.oplus.systemui.statusbar.pipeline.shared.domain.interactor;

import com.oplus.systemui.statusbar.pipeline.shared.domain.model.StatClockSeedlingState;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1 implements HiddenIconDispatcher.HiddenReceiver {
    public /* synthetic */ ProducerScope $$this$callbackFlow;
    public /* synthetic */ Ref.ObjectRef $currentState;

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("StatClockSeedlingInteractor seedlingState:");
        printWriter.println("  hiddenAreas=" + ((StatClockSeedlingState) this.$currentState.element).getHiddenAreas());
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public final void onHiddenChanged(ArrayList arrayList, boolean z) {
        Ref.ObjectRef objectRef = this.$currentState;
        StatClockSeedlingState statClockSeedlingState = new StatClockSeedlingState(arrayList, z);
        objectRef.element = statClockSeedlingState;
        this.$$this$callbackFlow.trySend-JP2dKIU(statClockSeedlingState);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }
}
