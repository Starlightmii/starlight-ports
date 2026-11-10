package com.oplus.systemui.notification.headsup;

import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHeadsUpViewModel$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ NotificationStackInteractor f$0;
    public /* synthetic */ ShadeInteractor f$1;

    public final Object invoke() {
        Flow[] flowArr = {this.f$0.isShowingOnLockscreen(), this.f$1.isShadeFullyCollapsed()};
        OplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1 oplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1 = new OplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1();
        oplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1.$flows$inlined = flowArr;
        VarHandle.storeStoreFence();
        return oplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1;
    }
}
