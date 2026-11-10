package com.oplus.systemui.qs.detail.flashlight;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.oplus.systemui.OplusVibrateHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFlashlightDetailCommandDispatcher implements FlashlightDetailCommandDispatcher {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusFlashlightDetailCmdDispatcher";
    private final OplusFlashlightDetailCommandDispatcher$bgHandler$1 bgHandler;
    private final FlashlightController flashlightController;
    private final AtomicInteger preparedSetFlashlightLevel = new AtomicInteger(-1);
    private final OplusVibrateHelper vibrateHelper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.qs.detail.flashlight.OplusFlashlightDetailCommandDispatcher$bgHandler$1] */
    public OplusFlashlightDetailCommandDispatcher(FlashlightController flashlightController, OplusVibrateHelper oplusVibrateHelper, final Looper looper) {
        this.flashlightController = flashlightController;
        this.vibrateHelper = oplusVibrateHelper;
        this.bgHandler = new Handler(looper) { // from class: com.oplus.systemui.qs.detail.flashlight.OplusFlashlightDetailCommandDispatcher$bgHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                OplusFlashlightDetailCommandDispatcher oplusFlashlightDetailCommandDispatcher = this;
                if (i == 1) {
                    oplusFlashlightDetailCommandDispatcher.setFlashlightEnableInternal(((Boolean) message.obj).booleanValue());
                } else if (i == 2) {
                    oplusFlashlightDetailCommandDispatcher.setFlashlightLevelInternal(message.arg1);
                } else {
                    if (i != 3) {
                        return;
                    }
                    oplusFlashlightDetailCommandDispatcher.setFlashlightLevelInternal(oplusFlashlightDetailCommandDispatcher.preparedSetFlashlightLevel.getAndSet(-1));
                }
            }
        };
    }

    public static final void access$applyFlashlightLevelImmediately(OplusFlashlightDetailCommandDispatcher oplusFlashlightDetailCommandDispatcher, int i) {
        if (i < 1) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "applyFlashlightLevelImmediately: level=", "OplusFlashlightDetailCmdDispatcher-->", "SystemUi--QuickSettings");
        }
        oplusFlashlightDetailCommandDispatcher.flashlightController.setFlashlightLevel(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFlashlightEnableInternal(boolean z) {
        clearPendingLevelCommands();
        this.flashlightController.setFlashlightWithUsageTrack(z);
        if (z) {
            OplusVibrateHelper.vibrate$default(this.vibrateHelper, TAG, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFlashlightLevelInternal(int i) {
        if (i < 1) {
            return;
        }
        if (hasMessages(3)) {
            this.preparedSetFlashlightLevel.set(i);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setFlashlightLevelInternal: level="), null, 8);
        OplusFlashlightDetailCommandDispatcher$bgHandler$1 oplusFlashlightDetailCommandDispatcher$bgHandler$1 = this.bgHandler;
        oplusFlashlightDetailCommandDispatcher$bgHandler$1.sendMessageDelayed(oplusFlashlightDetailCommandDispatcher$bgHandler$1.obtainMessage(3), 50L);
        this.flashlightController.setFlashlightLevel(i, true);
    }

    public final void clearPendingLevelCommands() {
        removeMessages(2);
        removeMessages(3);
        this.preparedSetFlashlightLevel.set(-1);
    }

    public final void flushPendingLevelOnTrackingEnded() {
        OplusFlashlightDetailCommandDispatcher$bgHandler$1 oplusFlashlightDetailCommandDispatcher$bgHandler$1 = this.bgHandler;
        flushPendingLevelOnTrackingEnded.1 r1 = new flushPendingLevelOnTrackingEnded.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        oplusFlashlightDetailCommandDispatcher$bgHandler$1.post(r1);
    }

    @Override // com.oplus.systemui.qs.detail.flashlight.FlashlightDetailCommandDispatcher
    public void setFlashlightEnable(boolean z) {
        removeMessages(1);
        obtainMessage(1, Boolean.valueOf(z)).sendToTarget();
    }

    @Override // com.oplus.systemui.qs.detail.flashlight.FlashlightDetailCommandDispatcher
    public void setFlashlightLevel(int i, Integer num) {
        if (num != null) {
            OplusVibrateHelper.vibrate$default(this.vibrateHelper, TAG, num.intValue());
        }
        Message messageObtainMessage = obtainMessage(2);
        messageObtainMessage.arg1 = i;
        sendMessage(messageObtainMessage);
    }
}
