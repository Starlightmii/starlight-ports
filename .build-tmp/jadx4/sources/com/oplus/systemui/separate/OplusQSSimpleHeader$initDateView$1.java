package com.oplus.systemui.separate;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.keyguard.settings.KeyguardSettingUtil;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$initDateView$1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSSimpleHeader this$0;

    public /* synthetic */ OplusQSSimpleHeader$initDateView$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->Date view clicked");
                }
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.APP_CALENDAR");
                String string = this.this$0.getContext().getString(2131952542);
                KeyguardSettingUtil.Companion companion = KeyguardSettingUtil.Companion;
                if (companion.isPackageInstalled(this.this$0.getContext(), string)) {
                    intent.setPackage(string);
                    intent.putExtra(OplusQSSimpleHeader.KEY_CHECK_PASSWORD, true);
                    intent.putExtra(OplusQSSimpleHeader.KEY_CALENDAR_ENTER_TYPE, 2);
                } else {
                    QSFeatureOption.INSTANCE.getClass();
                    if (QSFeatureOption.isCalendarCn || !companion.isPackageInstalled(this.this$0.getContext(), OplusQSSimpleHeader.GOOGLE_CALENDAR_PKG_NAME)) {
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, OplusQSSimpleHeader.TAG, "No expected calendar package installed");
                    } else {
                        intent.setPackage(OplusQSSimpleHeader.GOOGLE_CALENDAR_PKG_NAME);
                    }
                }
                intent.addFlags(32768);
                intent.addFlags(268435456);
                ActivityStarter activityStarter = this.this$0.activityStarter;
                if (activityStarter != null) {
                    activityStarter.postStartActivityDismissingKeyguard(intent, 0);
                }
                break;
            case 1:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusQSSimpleHeader-->Clock view clicked.");
                }
                this.this$0.setStatusBarAnimateChangeDisabled(true);
                Intent intent2 = new Intent();
                intent2.setAction(this.this$0.getContext().getString(2131952094));
                intent2.addFlags(603979776);
                ActivityStarter activityStarter2 = this.this$0.activityStarter;
                if (activityStarter2 != null) {
                    activityStarter2.postStartActivityDismissingKeyguard(intent2, 0);
                }
                break;
            default:
                Intent intent3 = new Intent();
                intent3.setAction("com.oplus.notificationmanager.app");
                intent3.addFlags(268435456);
                OplusQSActivityStarter oplusQSActivityStarter = this.this$0.getOplusQSActivityStarter();
                view.getClass();
                oplusQSActivityStarter.onAnimationButtonClicked(view, intent3, OplusQSActivityStarter.QSTransitionAnimator.SETTINGS);
                break;
        }
    }
}
