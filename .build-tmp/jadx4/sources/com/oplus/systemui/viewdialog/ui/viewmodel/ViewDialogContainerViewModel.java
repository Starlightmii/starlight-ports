package com.oplus.systemui.viewdialog.ui.viewmodel;

import android.view.View;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.viewdialog.ui.SysUIViewDialogTouchController;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerViewModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ViewDialogContainerViewModel";
    private final SysUIViewDialogInteractor dialogInteractor;
    private final MutableSharedFlow dismissDialogEvent = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
    private final KeyguardViewMediator keyguardViewMediator;
    private final View.OnClickListener outsideTouchListener;
    private final ScrimControllerEx scrimControllerEx;
    private final ShadeController shadeController;
    private final ShadeInteractor shadeInteractor;
    private final SharedFlow showDialogEvent;
    private final SysUIViewDialogTouchController touchListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ViewDialogContainerViewModel(SysUIViewDialogInteractor sysUIViewDialogInteractor, ShadeController shadeController, ScrimControllerEx scrimControllerEx, ShadeInteractor shadeInteractor, KeyguardViewMediator keyguardViewMediator) {
        this.dialogInteractor = sysUIViewDialogInteractor;
        this.shadeController = shadeController;
        this.scrimControllerEx = scrimControllerEx;
        this.shadeInteractor = shadeInteractor;
        this.keyguardViewMediator = keyguardViewMediator;
        this.showDialogEvent = sysUIViewDialogInteractor.getShowDialogEvent();
        ViewDialogContainerViewModel$outsideTouchListener$1 viewDialogContainerViewModel$outsideTouchListener$1 = new ViewDialogContainerViewModel$outsideTouchListener$1();
        viewDialogContainerViewModel$outsideTouchListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.outsideTouchListener = viewDialogContainerViewModel$outsideTouchListener$1;
        this.touchListener = new SysUIViewDialogTouchController(sysUIViewDialogInteractor, shadeController);
    }

    public final MutableSharedFlow getDismissDialogEvent() {
        return this.dismissDialogEvent;
    }

    public final View.OnClickListener getOutsideTouchListener() {
        return this.outsideTouchListener;
    }

    public final SharedFlow getShowDialogEvent() {
        return this.showDialogEvent;
    }

    public final SysUIViewDialogTouchController getTouchListener() {
        return this.touchListener;
    }

    public final boolean isNotificationShadeExpanded() {
        return ((Boolean) this.shadeInteractor.isAnyExpanded().getValue()).booleanValue();
    }

    public final boolean isStartedGoingToSleep() {
        return this.keyguardViewMediator.mGoingToSleep;
    }

    public final Object removeView(Continuation continuation) {
        MutableSharedFlow mutableSharedFlow = this.dismissDialogEvent;
        Unit unit = Unit.INSTANCE;
        Object objEmit = mutableSharedFlow.emit(unit, continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : unit;
    }

    public final void setDialogDismiss() {
        this.scrimControllerEx.setQsDialogVisible(false);
        GlobalUtils.updateDialogShowing(false, true);
    }

    public final void setDialogShowing(boolean z) {
        this.scrimControllerEx.setQsDialogVisible(true);
        GlobalUtils.updateDialogShowing(true, z);
    }
}
