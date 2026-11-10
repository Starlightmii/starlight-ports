package com.oplus.systemui.notification.icdf;

import android.content.Intent;
import android.os.Handler;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.synergynotificationsdk.callback.INotificationIntentCallback;
import com.oplus.synergynotificationsdk.icdf.IcdfNotificationManagerImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SynergyCallBackHelper$implCallBack$1 implements INotificationIntentCallback, IcdfNotificationManagerImpl.CallBack {
    public /* synthetic */ SynergyCallBackHelper this$0;

    public Intent getNotificationIntent(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getNotificationIntent, key", str, "PC_Synergy-->", "SystemUi--Notification");
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt.runBlocking$default(new SynergyCallBackHelper.mINotificationIntentCallback.1.getNotificationIntent.2(objectRef, this.this$0, str, (Continuation) null));
        return (Intent) objectRef.element;
    }

    public void onActionClicked(String str, int i) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onActionClicked.1 r1 = new onActionClicked.1(0);
        r1.this$0 = synergyCallBackHelper;
        r1.$key = str;
        r1.$actionId = i;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onAddNotification(IcdfNotificationManagerImpl.Invoker invoker) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onError.1 r1 = new onError.1(1);
        r1.this$0 = synergyCallBackHelper;
        r1.$iv = invoker;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onConnectCanceled(int i, IcdfNotificationManagerImpl.Invoker invoker) {
        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, SynergyCallBackHelper.TAG, "onConnectCanceled");
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        SynergyCallBackHelper$updateRanking$1 synergyCallBackHelper$updateRanking$1 = new SynergyCallBackHelper$updateRanking$1(1);
        synergyCallBackHelper$updateRanking$1.this$0 = synergyCallBackHelper;
        VarHandle.storeStoreFence();
        mainHandler.post(synergyCallBackHelper$updateRanking$1);
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        onActionClicked.1 r1 = new onActionClicked.1(1);
        r1.this$0 = synergyCallBackHelper;
        r1.$actionId = i;
        r1.$key = invoker;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }

    public void onConsumerDelete(String str) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onConsumerDelete.1 r1 = new onConsumerDelete.1(0);
        r1.this$0 = synergyCallBackHelper;
        r1.$key = str;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onDeleteNotification(IcdfNotificationManagerImpl.Invoker invoker) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        onError.1 r1 = new onError.1(2);
        r1.this$0 = synergyCallBackHelper;
        r1.$iv = invoker;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }

    public void onError(IcdfNotificationManagerImpl.Invoker invoker) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        onError.1 r1 = new onError.1(0);
        r1.this$0 = synergyCallBackHelper;
        r1.$iv = invoker;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }

    public void onHeadsUpUnpinned(String str) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onConsumerDelete.1 r1 = new onConsumerDelete.1(1);
        r1.this$0 = synergyCallBackHelper;
        r1.$key = str;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onNotificationClicked(String str) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onConsumerDelete.1 r1 = new onConsumerDelete.1(2);
        r1.this$0 = synergyCallBackHelper;
        r1.$key = str;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onReplyMessage(String str, String str2, int i) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        onReplyMessage.1 r1 = new onReplyMessage.1();
        r1.this$0 = synergyCallBackHelper;
        r1.$key = str;
        r1.$replyMsg = str2;
        r1.$actionId = i;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }

    public void onUpdateRanking(IcdfNotificationManagerImpl.Invoker invoker) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        onError.1 r1 = new onError.1(3);
        r1.this$0 = synergyCallBackHelper;
        r1.$iv = invoker;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }
}
