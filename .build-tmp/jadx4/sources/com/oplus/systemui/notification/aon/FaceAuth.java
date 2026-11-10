package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.util.Log;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FaceAuth {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FaceAuth";
    public volatile int authResult;
    private final FaceAuth$faceAuthCallback$1 faceAuthCallback;
    private final Lazy faceAuthHandler$delegate;
    private final Handler.Callback faceAuthHandlerCallback;
    private Function0 faceAuthSuccessAction;
    private CancellationSignal faceCancelSignal;
    private final Lazy faceManager$delegate;
    public boolean faceRunning;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Handler $r8$lambda$S5CxGlnQl_DxT52jThnga0v0e2w(FaceAuth faceAuth) {
        return new Handler(OplusThreadFactoryImpl.INSTANCE.buildNewThread("AON Face WorkerThread", 10).getLooper(), faceAuth.faceAuthHandlerCallback);
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [com.oplus.systemui.notification.aon.FaceAuth$faceAuthCallback$1] */
    public FaceAuth(Context context) {
        FaceAuth$$ExternalSyntheticLambda0 faceAuth$$ExternalSyntheticLambda0 = new FaceAuth$$ExternalSyntheticLambda0(0);
        faceAuth$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.faceManager$delegate = LazyKt__LazyJVMKt.lazy(faceAuth$$ExternalSyntheticLambda0);
        this.authResult = -1;
        this.faceAuthCallback = new FaceManager.AuthenticationCallback() { // from class: com.oplus.systemui.notification.aon.FaceAuth$faceAuthCallback$1
            public final void onAuthenticationError(int i, CharSequence charSequence) {
                super.onAuthenticationError(i, charSequence);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "FaceAuth-->".concat("[onAuthenticationError] errorCode: " + i + ",errString: " + ((Object) charSequence)));
                }
                this.this$0.authResult = 0;
                this.this$0.faceRunning = false;
            }

            public final void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                this.this$0.authResult = 1;
                this.this$0.stopFaceAuth();
            }

            public final void onAuthenticationSucceeded(FaceManager.AuthenticationResult authenticationResult) {
                super.onAuthenticationSucceeded(authenticationResult);
                this.this$0.authResult = 2;
                FaceAuth faceAuth = this.this$0;
                faceAuth.faceRunning = false;
                Function0 faceAuthSuccessAction = faceAuth.getFaceAuthSuccessAction();
                if (faceAuthSuccessAction != null) {
                    faceAuthSuccessAction.invoke();
                }
            }
        };
        FaceAuth$faceAuthHandlerCallback$1 faceAuth$faceAuthHandlerCallback$1 = new FaceAuth$faceAuthHandlerCallback$1();
        faceAuth$faceAuthHandlerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.faceAuthHandlerCallback = faceAuth$faceAuthHandlerCallback$1;
        FaceAuth$$ExternalSyntheticLambda0 faceAuth$$ExternalSyntheticLambda1 = new FaceAuth$$ExternalSyntheticLambda0(1);
        faceAuth$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.faceAuthHandler$delegate = LazyKt__LazyJVMKt.lazy(faceAuth$$ExternalSyntheticLambda1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceManager getFaceManager() {
        return (FaceManager) this.faceManager$delegate.getValue();
    }

    public final boolean getAuthSucceeded() {
        return this.authResult == 2;
    }

    public final Handler getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        return (Handler) this.faceAuthHandler$delegate.getValue();
    }

    public final Function0 getFaceAuthSuccessAction() {
        return this.faceAuthSuccessAction;
    }

    public final void setFaceAuthSuccessAction(Function0 function0) {
        this.faceAuthSuccessAction = function0;
    }

    public final void stopFaceAuth() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "FaceAuth-->[stopFaceAuth]");
        }
        getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(0);
        getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().removeMessages(1);
        getFaceAuthHandler$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic().sendEmptyMessage(1);
    }
}
