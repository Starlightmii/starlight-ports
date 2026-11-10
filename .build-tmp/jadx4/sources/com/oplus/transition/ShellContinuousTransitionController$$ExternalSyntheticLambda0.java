package com.oplus.transition;

import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import java.util.function.Predicate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ShellContinuousTransitionController$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ ShellContinuousTransitionController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return ShellContinuousTransitionController.lambda$isChangeInTransition$1((TransitionInfo.Change) obj2, (TransitionInfo.Change) obj);
            default:
                return ShellContinuousTransitionController.lambda$showOpenTaskInFinishIfNeeded$0((WindowContainerToken) obj2, (TransitionInfo.Change) obj);
        }
    }
}
