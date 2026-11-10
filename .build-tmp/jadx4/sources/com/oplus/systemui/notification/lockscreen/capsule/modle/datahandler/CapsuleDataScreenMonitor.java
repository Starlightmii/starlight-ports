package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import android.content.Context;
import android.hidl.base.V1_0.DebugInfo$;
import android.util.Log;
import android.view.Display;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.BaseDisplayUtil;
import com.oplus.systemui.aod.proxy.AodSettingsValueProxy;
import com.oplus.systemui.aod.utils.AodStyleTypeSettingsObserver;
import com.oplus.systemui.aod.utils.AodSwitchEnableObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataScreenMonitor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleDataHandler";
    private final CapsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1 aodSwitchOrStyleTypeChangedListener;
    private final Context context;
    private final KeyguardStateController keyguardStateController;
    private final CoroutineScope mainScope;
    private final PowerInteractor powerInteractor;
    private final ScreenLifecycle screenLifecycle;
    private final StateFlow showStateFlow;
    private final MutableStateFlow showStateFlowHolder;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleShowControlState {
        public final int aodDisplayState;
        public final boolean aodEnable;
        public final int aodStyle;
        public final boolean keyguardShown;
        public final boolean screenOn;

        public CapsuleShowControlState(boolean z, boolean z2, boolean z3, int i, int i2) {
            this.screenOn = z;
            this.keyguardShown = z2;
            this.aodEnable = z3;
            this.aodStyle = i;
            this.aodDisplayState = i2;
        }

        public static /* synthetic */ CapsuleShowControlState copy$default(CapsuleShowControlState capsuleShowControlState, boolean z, boolean z2, boolean z3, int i, int i2, int i3) {
            if ((i3 & 1) != 0) {
                z = capsuleShowControlState.screenOn;
            }
            boolean z4 = z;
            if ((i3 & 2) != 0) {
                z2 = capsuleShowControlState.keyguardShown;
            }
            boolean z5 = z2;
            if ((i3 & 4) != 0) {
                z3 = capsuleShowControlState.aodEnable;
            }
            boolean z6 = z3;
            if ((i3 & 8) != 0) {
                i = capsuleShowControlState.aodStyle;
            }
            int i4 = i;
            if ((i3 & 16) != 0) {
                i2 = capsuleShowControlState.aodDisplayState;
            }
            return capsuleShowControlState.copy(z4, z5, z6, i4, i2);
        }

        public final Boolean checkIfCapsuleScreenOn() {
            if (this.keyguardShown) {
                return Boolean.valueOf(this.screenOn);
            }
            return null;
        }

        public final CapsuleShowControlState copy(boolean z, boolean z2, boolean z3, int i, int i2) {
            return new CapsuleShowControlState(z, z2, z3, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleShowControlState)) {
                return false;
            }
            CapsuleShowControlState capsuleShowControlState = (CapsuleShowControlState) obj;
            return this.screenOn == capsuleShowControlState.screenOn && this.keyguardShown == capsuleShowControlState.keyguardShown && this.aodEnable == capsuleShowControlState.aodEnable && this.aodStyle == capsuleShowControlState.aodStyle && this.aodDisplayState == capsuleShowControlState.aodDisplayState;
        }

        public final int getAodDisplayState() {
            return this.aodDisplayState;
        }

        public final boolean getAodEnable() {
            return this.aodEnable;
        }

        public final int getAodStyle() {
            return this.aodStyle;
        }

        public final boolean getKeyguardShown() {
            return this.keyguardShown;
        }

        public final boolean getScreenOn() {
            return this.screenOn;
        }

        public final int hashCode() {
            return Integer.hashCode(this.aodDisplayState) + MonoSpline$.ExternalSyntheticOutline0.m(this.aodStyle, ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.screenOn) * 31, 31, this.keyguardShown), 31, this.aodEnable), 31);
        }

        public String toString() {
            String strValueOf;
            Object obj;
            int i = this.aodStyle;
            if (i == 0) {
                strValueOf = "经典简约";
            } else if (i != 1) {
                strValueOf = i != 2 ? String.valueOf(i) : "全景沉浸";
            } else {
                strValueOf = "灵感接续";
            }
            int i2 = this.aodDisplayState;
            try {
                obj = Result.constructor-impl(Display.stateToString(i2));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            String strValueOf2 = (String) obj;
            if (strValueOf2 == null) {
                strValueOf2 = String.valueOf(i2);
            }
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("CapsuleShowControlState(screenOn=", ", keyguardShown=", ", aodEnable=", this.screenOn, this.keyguardShown);
            DeviceItem$.ExternalSyntheticOutline0.m(", aodStyle=", strValueOf, ", aodDisplayState=", sbM, this.aodEnable);
            return DebugInfo$.ExternalSyntheticOutline0.m(sbM, strValueOf2, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BaseDisplayUtil.OplusDisplayStateListener {
        public /* synthetic */ CapsuleDataScreenMonitor this$0;

        public final void onDisplayChanged(int i) {
            CapsuleDataScreenMonitor capsuleDataScreenMonitor = this.this$0;
            CapsuleDataScreenMonitor.access$updateShowState(capsuleDataScreenMonitor, CapsuleShowControlState.copy$default((CapsuleShowControlState) capsuleDataScreenMonitor.getShowStateFlow().getValue(), false, false, false, 0, i, 15));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor$init$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ CapsuleDataScreenMonitor this$0;

            public final Object emit(Object obj, Continuation continuation) {
                CapsuleDataScreenMonitor capsuleDataScreenMonitor = this.this$0;
                CapsuleDataScreenMonitor.access$updateShowState(capsuleDataScreenMonitor, CapsuleShowControlState.copy$default((CapsuleShowControlState) capsuleDataScreenMonitor.getShowStateFlow().getValue(), ((WakefulnessModel) obj).isAwake(), false, false, 0, 0, 30));
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CapsuleDataScreenMonitor.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow detailedWakefulness = CapsuleDataScreenMonitor.this.powerInteractor.getDetailedWakefulness();
                CapsuleDataScreenMonitor capsuleDataScreenMonitor = CapsuleDataScreenMonitor.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = capsuleDataScreenMonitor;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (detailedWakefulness.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor$init$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements KeyguardStateController.Callback {
        public /* synthetic */ CapsuleDataScreenMonitor this$0;

        public final void onKeyguardShowingChanged() {
            CapsuleDataScreenMonitor capsuleDataScreenMonitor = this.this$0;
            CapsuleDataScreenMonitor.access$updateShowState(capsuleDataScreenMonitor, CapsuleShowControlState.copy$default((CapsuleShowControlState) capsuleDataScreenMonitor.getShowStateFlow().getValue(), false, capsuleDataScreenMonitor.keyguardStateController.mShowing, false, 0, 0, 29));
        }
    }

    public CapsuleDataScreenMonitor(CoroutineScope coroutineScope, Context context, ScreenLifecycle screenLifecycle, PowerInteractor powerInteractor, KeyguardStateController keyguardStateController) {
        this.mainScope = coroutineScope;
        this.context = context;
        this.screenLifecycle = screenLifecycle;
        this.powerInteractor = powerInteractor;
        this.keyguardStateController = keyguardStateController;
        boolean z = true;
        if (screenLifecycle.getScreenState() != 2) {
            z = false;
        }
        CapsuleShowControlState capsuleShowControlState = new CapsuleShowControlState(z, ((KeyguardStateControllerImpl) keyguardStateController).mShowing, AodSettingsValueProxy.getAodSwitchEnable(context) == z, AodSettingsValueProxy.getAodType(context), AODDisplayUtil.getInstance(context).getDeviceDisplayState());
        CapsuleDataPinHandler.INSTANCE.initCapsuleScreenState(capsuleShowControlState.checkIfCapsuleScreenOn(), capsuleShowControlState.getKeyguardShown());
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(capsuleShowControlState);
        this.showStateFlowHolder = MutableStateFlow;
        this.showStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        CapsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1 capsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1 = new CapsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1();
        capsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.aodSwitchOrStyleTypeChangedListener = capsuleDataScreenMonitor$aodSwitchOrStyleTypeChangedListener$1;
    }

    public static final void access$updateShowState(CapsuleDataScreenMonitor capsuleDataScreenMonitor, CapsuleShowControlState capsuleShowControlState) {
        capsuleDataScreenMonitor.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataHandler-->".concat("newState: " + capsuleShowControlState));
        }
        capsuleDataScreenMonitor.showStateFlowHolder.setValue(capsuleShowControlState);
        CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
        capsuleDataPinHandler.getNeedKeepAodDisplayDoze().set(!capsuleShowControlState.getScreenOn() && capsuleShowControlState.getKeyguardShown() && capsuleShowControlState.getAodEnable() && capsuleShowControlState.getAodStyle() == 2 && (capsuleShowControlState.getAodDisplayState() == 3 || capsuleShowControlState.getAodDisplayState() == 4));
        capsuleDataPinHandler.onCapsuleScreenChanged(capsuleShowControlState.checkIfCapsuleScreenOn(), capsuleShowControlState.getKeyguardShown());
    }

    public final StateFlow getShowStateFlow() {
        return this.showStateFlow;
    }

    public final void init() {
        AODDisplayUtil aODDisplayUtil = AODDisplayUtil.getInstance(this.context);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        aODDisplayUtil.registerOplusDisplayStateListener(anonymousClass1);
        BuildersKt.launch$default(this.mainScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass2(null), 2);
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(anonymousClass3);
        AodSwitchEnableObserver.Companion.getInstance().addListener(this.aodSwitchOrStyleTypeChangedListener);
        AodStyleTypeSettingsObserver.Companion.getInstance().addListener(this.aodSwitchOrStyleTypeChangedListener);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("initState: ", this.showStateFlow.getValue(), "CapsuleDataHandler-->", "SystemUi--Notification");
        }
    }
}
