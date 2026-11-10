package com.oplus.systemui.navigationbar.ocrscreen;

import android.content.Context;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class OplusOcrScreenBusiness implements IOtherBusiness, GestureHomeHandleEventController.HomeHandleTouchEvent {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OcrScreenBusiness";
    private final Context context;
    private OplusOcrScreenServiceHandler currentServiceHandler;
    private final Lazy serviceHandlerList$delegate = LazyKt__LazyJVMKt.lazy(new OplusOcrScreenBusiness$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$FmIByazypCwkDo5dAPAx8QadTEQ(OplusOcrScreenBusiness oplusOcrScreenBusiness, OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler) {
        if (oplusOcrScreenBusiness.getServiceHandlerList().contains(oplusOcrScreenServiceHandler)) {
            oplusOcrScreenBusiness.getServiceHandlerList().remove(oplusOcrScreenServiceHandler);
        }
        return Unit.INSTANCE;
    }

    public OplusOcrScreenBusiness(Context context) {
        this.context = context;
    }

    private final CopyOnWriteArrayList<OplusOcrScreenServiceHandler> getServiceHandlerList() {
        return (CopyOnWriteArrayList) this.serviceHandlerList$delegate.getValue();
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness
    public final void init() {
        GestureHomeHandleEventController.INSTANCE.addHomeHandlerLongClickListener(this);
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onDown() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenBusiness-->onDown");
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onLongPressCancel() {
        OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler = this.currentServiceHandler;
        if (oplusOcrScreenServiceHandler != null) {
            oplusOcrScreenServiceHandler.onLongPressCancel();
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onLongPressed() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenBusiness-->onLongPressed");
        }
        OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler = this.currentServiceHandler;
        if (oplusOcrScreenServiceHandler != null) {
            oplusOcrScreenServiceHandler.onLongPressed();
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onPreLongPress() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "OcrScreenBusiness-->onPreLongPress");
        }
        OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler = this.currentServiceHandler;
        if (oplusOcrScreenServiceHandler != null) {
            oplusOcrScreenServiceHandler.onPreLongPress();
        }
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController.HomeHandleTouchEvent
    public final void onShowPress() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0.m(getServiceHandlerList().size(), "onShowPress serviceHandlerList size = ", "OcrScreenBusiness-->", "SystemUi--NavBar");
        }
        if (getServiceHandlerList().size() >= 2) {
            Iterator<T> it = getServiceHandlerList().iterator();
            while (it.hasNext()) {
                ((OplusOcrScreenServiceHandler) it.next()).unbindService();
            }
            getServiceHandlerList().clear();
        }
        this.currentServiceHandler = null;
        Context context = this.context;
        OplusOcrScreenBusiness$$ExternalSyntheticLambda1 oplusOcrScreenBusiness$$ExternalSyntheticLambda1 = new OplusOcrScreenBusiness$$ExternalSyntheticLambda1();
        oplusOcrScreenBusiness$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        OplusOcrScreenServiceHandler oplusOcrScreenServiceHandler = new OplusOcrScreenServiceHandler(context, oplusOcrScreenBusiness$$ExternalSyntheticLambda1);
        oplusOcrScreenServiceHandler.onShowPress();
        getServiceHandlerList().add(oplusOcrScreenServiceHandler);
        this.currentServiceHandler = oplusOcrScreenServiceHandler;
    }

    @Override // com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness
    public final void release() {
        GestureHomeHandleEventController.INSTANCE.removeHomeHandlerLongClickListener(this);
    }
}
