package com.oplus.systemui.notification.aon;

import android.hardware.biometrics.CryptoObject;
import android.hardware.face.FaceAuthenticateOptions;
import android.hardware.face.FaceManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.keyguard.shared.model.SysUiFaceAuthenticateOptions;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FaceAuth$faceAuthHandlerCallback$1 implements Handler.Callback {
    public /* synthetic */ FaceAuth this$0;

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (this.this$0.getFaceManager() != null) {
            FaceAuth faceAuth = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("[handleMessage] ", message.what, ", faceRunning=", "FaceAuth-->", faceAuth.faceRunning));
            }
            int i = message.what;
            if (i == 0) {
                FaceAuth faceAuth2 = this.this$0;
                if (!faceAuth2.faceRunning) {
                    CancellationSignal cancellationSignal = faceAuth2.faceCancelSignal;
                    if (cancellationSignal != null) {
                        cancellationSignal.cancel();
                    }
                    this.this$0.faceCancelSignal = new CancellationSignal();
                    FaceAuthenticateOptions faceAuthenticateOptions = new SysUiFaceAuthenticateOptions(KeyguardUpdateMonitor.getCurrentUser(), FaceAuthUiEvent.FACE_AUTH_UPDATED_ON_FACE_AUTHENTICATED, 0).toFaceAuthenticateOptions();
                    FaceManager faceManager = this.this$0.getFaceManager();
                    if (faceManager != null) {
                        faceManager.authenticate((CryptoObject) null, this.this$0.faceCancelSignal, this.this$0.faceAuthCallback, this.this$0.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(), faceAuthenticateOptions);
                    }
                    this.this$0.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(2);
                    this.this$0.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().sendMessageDelayed(this.this$0.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().obtainMessage(2), 5000L);
                    this.this$0.authResult = -1;
                    this.this$0.faceRunning = true;
                }
            } else if (i == 1 || i == 2) {
                this.this$0.getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(2);
                FaceAuth faceAuth3 = this.this$0;
                if (faceAuth3.faceRunning) {
                    CancellationSignal cancellationSignal2 = faceAuth3.faceCancelSignal;
                    if (cancellationSignal2 != null) {
                        cancellationSignal2.cancel();
                    }
                    this.this$0.faceCancelSignal = null;
                    this.this$0.faceRunning = false;
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
