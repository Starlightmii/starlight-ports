package com.oplus.systemui.separate.guide;

import android.database.ContentObserver;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.GuideEvent;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsGuideEventController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsGuideEventController f$0;

    public /* synthetic */ OplusQsGuideEventController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        final OplusQsGuideEventController oplusQsGuideEventController = this.f$0;
        switch (i) {
            case 0:
                OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
                OplusQsGuideEventController$appSwitchObserver$2$1 oplusQsGuideEventController$appSwitchObserver$2$1 = new OplusQsGuideEventController$appSwitchObserver$2$1();
                oplusQsGuideEventController$appSwitchObserver$2$1.this$0 = oplusQsGuideEventController;
                VarHandle.storeStoreFence();
                return oplusQsGuideEventController$appSwitchObserver$2$1;
            case 1:
                OplusQsGuideEventController.Companion companion2 = OplusQsGuideEventController.Companion;
                OplusQsGuideEventController$specialModeUpdateMonitorListener$2$1 oplusQsGuideEventController$specialModeUpdateMonitorListener$2$1 = new OplusQsGuideEventController$specialModeUpdateMonitorListener$2$1();
                oplusQsGuideEventController$specialModeUpdateMonitorListener$2$1.this$0 = oplusQsGuideEventController;
                VarHandle.storeStoreFence();
                return oplusQsGuideEventController$specialModeUpdateMonitorListener$2$1;
            case 2:
                OplusQsGuideEventController.Companion companion3 = OplusQsGuideEventController.Companion;
                OplusQsGuideEventController$headsUpChangedListener$2$1 oplusQsGuideEventController$headsUpChangedListener$2$1 = new OplusQsGuideEventController$headsUpChangedListener$2$1();
                oplusQsGuideEventController$headsUpChangedListener$2$1.this$0 = oplusQsGuideEventController;
                VarHandle.storeStoreFence();
                return oplusQsGuideEventController$headsUpChangedListener$2$1;
            case 3:
                OplusQsGuideEventController.Companion companion4 = OplusQsGuideEventController.Companion;
                OplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1 oplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1 = new OplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1();
                oplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1.this$0 = oplusQsGuideEventController;
                VarHandle.storeStoreFence();
                return oplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1;
            case 4:
                OplusQsGuideEventController.Companion companion5 = OplusQsGuideEventController.Companion;
                return new ContentObserver() { // from class: com.oplus.systemui.separate.guide.OplusQsGuideEventController$provisionCompletedObserver$2$1
                    {
                        super(null);
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        super.onChange(z);
                        boolean zIsProvisionComplete = oplusQsGuideEventController.isProvisionComplete();
                        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideEventController", KeyguardLogger$.ExternalSyntheticOutline0.m(" provision change : ", zIsProvisionComplete), null, 8);
                        if (zIsProvisionComplete) {
                            oplusQsGuideEventController.notifyGuideEvent(GuideEvent.PROVISION_COMPLETE);
                        }
                    }
                };
            default:
                OplusQsGuideEventController.Companion companion6 = OplusQsGuideEventController.Companion;
                OplusQsGuideEventController$guideExportListener$2$1 oplusQsGuideEventController$guideExportListener$2$1 = new OplusQsGuideEventController$guideExportListener$2$1();
                oplusQsGuideEventController$guideExportListener$2$1.this$0 = oplusQsGuideEventController;
                VarHandle.storeStoreFence();
                return oplusQsGuideEventController$guideExportListener$2$1;
        }
    }
}
