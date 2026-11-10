package com.oplus.systemui.qs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.oplus.systemui.qs.base.util.QsDateClickUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterViewController$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSFooterViewController f$0;
    public /* synthetic */ Context f$1;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                OplusQSFooterViewController oplusQSFooterViewController = this.f$0;
                Context context = this.f$1;
                Intent intent = new Intent();
                intent.setAction(context.getString(2131952094));
                intent.addFlags(603979776);
                oplusQSFooterViewController.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
                QsStatisticUtil.collectShortcutsCenterEvent(context, "clock_click");
                break;
            default:
                OplusQSFooterViewController oplusQSFooterViewController2 = this.f$0;
                Context context2 = this.f$1;
                QsDateClickUtil.onDateClick(context2, oplusQSFooterViewController2.mActivityStarter);
                QsStatisticUtil.collectShortcutsCenterEvent(context2, "date_click");
                break;
        }
    }
}
