package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSeedlingService$bindService$1 implements Runnable {
    public /* synthetic */ Object $context;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ StartSeedlingService this$0;

    public /* synthetic */ StartSeedlingService$bindService$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        Object obj2;
        Unit unit;
        switch (this.$r8$classId) {
            case 0:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "StartSeedlingService-->bindService");
                }
                Intent intent = new Intent();
                intent.setAction("com.oplus.seedlingservice.action.SEEDLING_SERVICE");
                intent.setPackage("com.android.systemui");
                Context context = (Context) this.$context;
                StartSeedlingService startSeedlingService = this.this$0;
                try {
                    boolean zBindService = context.bindService(intent, startSeedlingService.conn, 1);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                        Log.d("SystemUi--Common", "StartSeedlingService-->".concat("result: " + zBindService));
                    }
                    if (!zBindService) {
                        context.unbindService(startSeedlingService.conn);
                    }
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Context context2 = (Context) this.$context;
                StartSeedlingService startSeedlingService2 = this.this$0;
                if (obj instanceof Result.Failure) {
                    LogUtil.e(LogUtil.TAG_COMMON, "StartSeedlingService", "bind service error", Result.exceptionOrNull-impl(obj));
                    if (Result.exceptionOrNull-impl(obj) instanceof SecurityException) {
                        context2.unbindService(startSeedlingService2.conn);
                    }
                }
                break;
            default:
                StartSeedlingService startSeedlingService3 = this.this$0;
                Bundle bundle = (Bundle) this.$context;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "StartSeedlingService-->".concat("sendDestroySeedling, serverMessenger=" + startSeedlingService3.serverMessenger + ", data=" + bundle));
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.setData((Bundle) this.$context);
                try {
                    Messenger messenger = this.this$0.serverMessenger;
                    if (messenger != null) {
                        messenger.send(messageObtain);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    obj2 = Result.constructor-impl(unit);
                } catch (Throwable th2) {
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
                }
                if (obj2 instanceof Result.Failure) {
                    LogUtil.e(LogUtil.TAG_COMMON, "StartSeedlingService", "send message error", Result.exceptionOrNull-impl(obj2));
                }
                break;
        }
    }
}
