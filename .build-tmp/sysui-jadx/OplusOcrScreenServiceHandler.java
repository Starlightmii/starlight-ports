package com.oplus.systemui.navigationbar.ocrscreen;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticOutline0;
import com.android.systemui.application.impl.SystemUIApplicationImpl$$ExternalSyntheticOutline0;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$$ExternalSyntheticOutline0;
import com.oplus.infocollection.collection.IEntranceServiceInterface;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.navigationbar.gesture.manager.GestureBGThreadManager;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.systemui.navigationbar.utils.OplusNavBarVibrationHelper;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class OplusOcrScreenServiceHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OcrScreenServiceHandler";
    private final Context context;
    private final Handler gestureBGHandler = GestureBGThreadManager.INSTANCE.getGestureBGHandler();
    public boolean isCalledLongPress;
    public boolean isCancelLongPress;
    private OplusOcrScreenService ocrScreenService;
    private final Function1 unbindCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    /* JADX INFO: loaded from: classes9.dex */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler$onLongPressed$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusOcrScreenServiceHandler this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Object objM10804constructorimpl;
            Object objM10804constructorimpl2;
            Object objM10804constructorimpl3;
            Unit unit = null;
            switch (this.$r8$classId) {
                case 0:
                    OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler = this.this$0;
                    try {
                        if (oplusOcrScreenServiceHandler.getEntranceServiceInterface() != null) {
                            oplusOcrScreenServiceHandler.handleLongPressAction();
                            unit = Unit.INSTANCE;
                        }
                        objM10804constructorimpl = Result.m10804constructorimpl(unit);
                    } catch (Throwable th) {
                        objM10804constructorimpl = Result.m10804constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable thM10805exceptionOrNullimpl = Result.m10805exceptionOrNullimpl(objM10804constructorimpl);
                    if (thM10805exceptionOrNullimpl != null) {
                        GestureInteractor$getTopActivity$2$$ExternalSyntheticOutline0.m(8, "onLongPressed startService e = ", thM10805exceptionOrNullimpl.getMessage(), LogUtil.TAG_NAVBAR, OplusOcrScreenServiceHandler.TAG);
                    }
                    break;
                case 1:
                    OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler2 = this.this$0;
                    try {
                        IEntranceServiceInterface entranceServiceInterface = oplusOcrScreenServiceHandler2.getEntranceServiceInterface();
                        if (entranceServiceInterface != null) {
                            entranceServiceInterface.cancelTouch();
                        }
                        oplusOcrScreenServiceHandler2.unbindService();
                        objM10804constructorimpl2 = Result.m10804constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        objM10804constructorimpl2 = Result.m10804constructorimpl(ResultKt.createFailure(th2));
                    }
                    Throwable thM10805exceptionOrNullimpl2 = Result.m10805exceptionOrNullimpl(objM10804constructorimpl2);
                    if (thM10805exceptionOrNullimpl2 != null) {
                        GestureInteractor$getTopActivity$2$$ExternalSyntheticOutline0.m(8, "onCancelOrUp onLongPressCancel error: ", thM10805exceptionOrNullimpl2.getMessage(), LogUtil.TAG_NAVBAR, OplusOcrScreenServiceHandler.TAG);
                    }
                    break;
                default:
                    OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler3 = this.this$0;
                    try {
                        OplusOcrScreenService oplusOcrScreenService = oplusOcrScreenServiceHandler3.ocrScreenService;
                        if (oplusOcrScreenService != null) {
                            OplusOcrScreenServiceHandler$onPreLongPress$2$$ExternalSyntheticLambda0 oplusOcrScreenServiceHandler$onPreLongPress$2$$ExternalSyntheticLambda0 = new OplusOcrScreenServiceHandler$onPreLongPress$2$$ExternalSyntheticLambda0();
                            oplusOcrScreenServiceHandler$onPreLongPress$2$$ExternalSyntheticLambda0.f$0 = oplusOcrScreenServiceHandler3;
                            VarHandle.storeStoreFence();
                            oplusOcrScreenService.bindService(oplusOcrScreenServiceHandler$onPreLongPress$2$$ExternalSyntheticLambda0);
                            unit = Unit.INSTANCE;
                        }
                        objM10804constructorimpl3 = Result.m10804constructorimpl(unit);
                    } catch (Throwable th3) {
                        objM10804constructorimpl3 = Result.m10804constructorimpl(ResultKt.createFailure(th3));
                    }
                    Throwable thM10805exceptionOrNullimpl3 = Result.m10805exceptionOrNullimpl(objM10804constructorimpl3);
                    if (thM10805exceptionOrNullimpl3 != null) {
                        GestureInteractor$getTopActivity$2$$ExternalSyntheticOutline0.m(8, "onPreLongPress invoke e = ", thM10805exceptionOrNullimpl3.getMessage(), LogUtil.TAG_NAVBAR, OplusOcrScreenServiceHandler.TAG);
                    }
                    break;
            }
        }
    }

    public OplusOcrScreenServiceHandler(Context context, Function1 function1) {
        this.context = context;
        this.unbindCallback = function1;
        this.ocrScreenService = new OplusOcrScreenService(context);
    }

    public static final void access$handleServiceConnected(OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler) {
        oplusOcrScreenServiceHandler.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            StringBuilder sbM = TextFieldImplKt$$ExternalSyntheticOutline0.m("handleServiceConnected isCancelLongPress ", ", hadCalledLongPress ", ", class = ", oplusOcrScreenServiceHandler.isCancelLongPress, oplusOcrScreenServiceHandler.isCalledLongPress);
            sbM.append(oplusOcrScreenServiceHandler);
            Log.d("SystemUi--NavBar", "OcrScreenServiceHandler-->".concat(sbM.toString()));
        }
        if (oplusOcrScreenServiceHandler.isCancelLongPress) {
            oplusOcrScreenServiceHandler.unbindService();
            return;
        }
        IEntranceServiceInterface entranceServiceInterface = oplusOcrScreenServiceHandler.getEntranceServiceInterface();
        if (entranceServiceInterface != null) {
            entranceServiceInterface.preload();
        }
        if (oplusOcrScreenServiceHandler.isCalledLongPress) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OcrScreenServiceHandler-->onPreLongPress invoke start");
            }
            oplusOcrScreenServiceHandler.handleLongPressAction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IEntranceServiceInterface getEntranceServiceInterface() {
        OplusOcrScreenService oplusOcrScreenService = this.ocrScreenService;
        IEntranceServiceInterface entranceServiceInterface = oplusOcrScreenService != null ? oplusOcrScreenService.getEntranceServiceInterface() : null;
        if (entranceServiceInterface == null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenServiceHandler-->service unConnected");
        }
        return entranceServiceInterface;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Function1 getUnbindCallback() {
        return this.unbindCallback;
    }

    public final void handleLongPressAction() {
        IEntranceServiceInterface entranceServiceInterface = getEntranceServiceInterface();
        if (entranceServiceInterface != null) {
            entranceServiceInterface.start();
        }
        unbindService();
        if (OplusNavBarUtils.isHomeHandleEnableOcrDialog()) {
            NavBarSettingsValueProxy.Companion.setNavbarHandlerOcrDialogEnable(this.context, 0);
        } else if (OplusNavBarUtils.isHomeButtonEnableOcrDialog()) {
            NavBarSettingsValueProxy.Companion.setVirtualKeyLongPressHomeOcrDialogEnable(this.context, 0);
        }
    }

    public final void onLongPressCancel() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", SystemUIApplicationImpl$$ExternalSyntheticOutline0.m("onLongPressCancel hadCalledLongPress ", " ,isCancelLongPress ", "OcrScreenServiceHandler-->", this.isCalledLongPress, this.isCancelLongPress));
        }
        if (CustomizeFeatureOption.sIsSupportHomeWaveVibration) {
            ((OplusNavBarVibrationHelper) OplusNavBarVibrationHelper.Companion.get(this.context)).cancelVibrate();
        }
        this.isCancelLongPress = true;
        Handler handler = this.gestureBGHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void onLongPressed() {
        this.isCalledLongPress = true;
        if (!CustomizeFeatureOption.sIsSupportHomeWaveVibration) {
            VibrationHelper.getInstance(this.context).onPerformHapticFeedback();
        }
        Handler handler = this.gestureBGHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void onPreLongPress() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenServiceHandler-->".concat("hadCalledLongPress " + this.isCalledLongPress + " ,class = " + this));
        }
        Handler handler = this.gestureBGHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void onShowPress() {
        if (CustomizeFeatureOption.sIsSupportHomeWaveVibration) {
            ((OplusNavBarVibrationHelper) OplusNavBarVibrationHelper.Companion.get(this.context)).performVibrate();
        }
    }

    public final void unbindService() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenServiceHandler-->unbindService");
        }
        OplusOcrScreenService oplusOcrScreenService = this.ocrScreenService;
        if (oplusOcrScreenService != null) {
            oplusOcrScreenService.unbindService();
        }
        this.ocrScreenService = null;
        this.unbindCallback.invoke(this);
        this.isCancelLongPress = false;
        this.isCalledLongPress = false;
    }
}
