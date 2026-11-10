package com.oplus.systemui.notification.aon;

import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonRuleManager$loadDataFromSettingProvider$2$1 implements Runnable {
    public /* synthetic */ long $intervalTime;
    public /* synthetic */ boolean $isEnabled;
    public /* synthetic */ List $rules;
    public /* synthetic */ AonRuleManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        AonRuleManager aonRuleManager = this.this$0;
        boolean z = this.$isEnabled;
        long j = this.$intervalTime;
        List list = this.$rules;
        synchronized (aonRuleManager) {
            aonRuleManager.setEnabled(z);
            aonRuleManager.setCountIntervalTime(j);
            aonRuleManager.ruleDetail.clear();
            aonRuleManager.ruleDetail.addAll(list);
        }
    }
}
