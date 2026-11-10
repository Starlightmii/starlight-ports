package com.oplus.systemui.volume.repository;

import java.util.List;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiMediaRepository$prepareMediaConfigs$2 implements Runnable {
    public /* synthetic */ List $activeConfigs;
    public /* synthetic */ Function0 $onComplete;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MultiMediaRepository this$0;

    public /* synthetic */ MultiMediaRepository$prepareMediaConfigs$2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        switch (this.$r8$classId) {
            case 0:
                MultiMediaRepository.access$syncMultiMediaUiWithActivePlaybackConfigs(this.this$0, this.$activeConfigs, (MultiMediaRepository$$ExternalSyntheticLambda0) this.$onComplete, false);
                break;
            default:
                MultiMediaRepository.access$syncMultiMediaUiWithActivePlaybackConfigs(this.this$0, this.$activeConfigs, this.$onComplete, true);
                break;
        }
    }
}
