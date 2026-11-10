package com.oplus.systemui.navigationbar.ocrscreen;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplus.infocollection.collection.IEntranceServiceInterface;
import com.oplus.systemui.navigationbar.gesture.manager.GestureBGThreadManager;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusOcrScreenService {
    public static final Companion Companion = new Companion();
    private static final String EXTRA_ENTRANCE_FUNCTION_NAME = "extra_entrance_function";
    private static final String EXTRA_ENTRANCE_FUNCTION_VALUE = "full_screen_ocr";
    private static final String EXTRA_SKIP_GESTURE_CHECK_NAME = "skip_gesture_check";
    private static final String EXTRA_TRIGGERED_APP_NAME = "triggered_app";
    private static final String EXTRA_TRIGGERED_APP_VALUE = "com.android.systemui";
    private static final String OCR_SCREEN_SERVICE_ACTION = "com.coloros.directui.InvokeEntranceService";
    private static final String SERVER_PACKAGE_NAME = "com.coloros.colordirectservice";
    private static final String START_UP_TYPE = "StartUpType";
    private static final String TAG = "OcrScreenService";
    public boolean bound;
    public final Context context;
    private IEntranceServiceInterface entranceServiceInterface;
    private final Handler gestureBGHandler = GestureBGThreadManager.INSTANCE.getGestureBGHandler();
    private final ocrScreenConnection.1 ocrScreenConnection;
    private Function0 onServiceDisconnected;

    public OplusOcrScreenService(Context context) {
        this.context = context.getApplicationContext();
        ocrScreenConnection.1 r1 = new ocrScreenConnection.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.ocrScreenConnection = r1;
    }

    private final Intent getServiceIntent() {
        Bundle bundle = new Bundle();
        if (OplusNavBarUtils.isHomeHandleEnableOcrDialog()) {
            bundle.putInt(START_UP_TYPE, 2);
        } else if (OplusNavBarUtils.isHomeButtonEnableOcrDialog()) {
            bundle.putInt(START_UP_TYPE, 1);
        } else {
            bundle.putInt(START_UP_TYPE, 0);
        }
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, "getServiceIntent bundle: " + bundle, null, 8);
        Intent intent = new Intent();
        intent.setAction(OCR_SCREEN_SERVICE_ACTION);
        intent.setPackage(SERVER_PACKAGE_NAME);
        intent.putExtra(EXTRA_ENTRANCE_FUNCTION_NAME, EXTRA_ENTRANCE_FUNCTION_VALUE);
        intent.putExtra(EXTRA_TRIGGERED_APP_NAME, "com.android.systemui");
        intent.putExtra(EXTRA_SKIP_GESTURE_CHECK_NAME, true);
        intent.putExtras(bundle);
        return intent;
    }

    public final void bindService(Function0 function0) {
        Object obj;
        try {
            this.onServiceDisconnected = function0;
            unbindService();
            this.bound = this.context.bindServiceAsUser(getServiceIntent(), (ServiceConnection) this.ocrScreenConnection, 67108865, UserHandle.CURRENT);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OcrScreenService-->".concat("bindService bound = " + this.bound));
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "bindService failed ", th2.getMessage(), LogUtil.TAG_NAVBAR, TAG);
        }
    }

    public final IEntranceServiceInterface getEntranceServiceInterface() {
        return this.entranceServiceInterface;
    }

    public final void unbindService() {
        Object obj;
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OcrScreenService-->".concat("unbindService bound " + this.bound));
            }
            if (this.bound) {
                this.context.unbindService(this.ocrScreenConnection);
                this.entranceServiceInterface = null;
                this.bound = false;
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "unbindService error ", th2.getMessage(), LogUtil.TAG_NAVBAR, TAG);
        }
    }
}
