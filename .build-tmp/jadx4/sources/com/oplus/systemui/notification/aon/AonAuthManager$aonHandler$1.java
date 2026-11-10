package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.aiunit.aon.AON.AntiPeeping;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthManager$aonHandler$1 extends Handler {
    public boolean cameraIntentState;
    public final /* synthetic */ AonAuthManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AonAuthManager$aonHandler$1(AonAuthManager aonAuthManager, Looper looper) {
        super(looper);
        this.this$0 = aonAuthManager;
    }

    public final boolean getCameraIntentState() {
        return this.cameraIntentState;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == -1) {
            Object obj = new Object();
            AntiPeeping antiPeeping = this.this$0.antiPeeping;
            Context context = this.this$0.context;
            handleMessage.1 r1 = new handleMessage.1();
            r1.$bindResult = obj;
            VarHandle.storeStoreFence();
            antiPeeping.init(context, r1);
            try {
                synchronized (obj) {
                    obj.wait(2000L);
                }
                Result.constructor-impl(Unit.INSTANCE);
                return;
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
                return;
            }
        }
        boolean z = true;
        if (i == 0) {
            AonAuthManager aonAuthManager = this.this$0;
            synchronized (aonAuthManager) {
                this.cameraIntentState = true;
                if (!aonAuthManager.getCameraOpening() && aonAuthManager.getAonAuthResult() == null) {
                    AonAuthManager.access$startAntiPeeping(aonAuthManager);
                }
            }
            return;
        }
        if (i != 1) {
            return;
        }
        AonAuthManager aonAuthManager2 = this.this$0;
        synchronized (aonAuthManager2) {
            try {
                this.cameraIntentState = false;
                boolean z2 = message.arg1 == 1;
                if (!aonAuthManager2.getCameraOpening() || z2) {
                    z = false;
                }
                if (aonAuthManager2.getCameraOpening()) {
                    AonAuthManager.access$stopAntiPeeping(aonAuthManager2, message);
                }
                aonAuthManager2.setAuthState(null, z);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void setCameraIntentState(boolean z) {
        this.cameraIntentState = z;
    }
}
