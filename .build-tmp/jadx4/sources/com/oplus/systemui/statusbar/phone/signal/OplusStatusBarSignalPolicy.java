package com.oplus.systemui.statusbar.phone.signal;

import android.content.Context;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractor;
import com.oplus.systemui.statusbar.phone.signal.model.OplusIMSModel;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractor;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepositoryImpl;
import com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplus.systemui.statusbar.policy.TelephonyIcons;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarSignalPolicy implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarSignalPolicy";
    private final AirplaneModeInteractor airplaneModeInteractor;
    private final Context context;
    private final StateFlow hasNoSubs;
    public int hdPlusNewIcon;
    private final StatusBarIconController iconController;
    private final StateFlow imsIcon;
    public boolean isCollecting;
    private final CoroutineDispatcher mainDispatcher;
    private final MobileIconsInteractor mobileIconsInteractor;
    private final StateFlow noSimIcon;
    private final OplusMobileConnectionsRepository oplusMobileConnectionsRepository;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final OplusWifiInteractor oplusWifiInteractor;
    private final OplusWifiRepositoryImpl oplusWifiRepositoryImpl;
    private final CoroutineScope scope;
    private final StateFlow simOneImsModel;
    private final StateFlow simOneMobileIconInteractor;
    private final StateFlow simTwoImsModel;
    private final StateFlow simTwoMobileIconInteractor;
    private String slotNoSim;
    private String slotNoSimOne;
    private String slotNoSimTwo;
    private String slotNoVolte;
    private String slotVolte;
    private String slotVowifi;
    public int vo5GIcon;
    public int volteIcon;
    public int vowifiIcon;
    private String volteContenDescription = "";
    private String vowifiContenDescription = "";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NoSimIcon {
        public final int noOneSimIcon;
        public final int noSimIcon;
        public final int noTwoSimIcon;

        public NoSimIcon(int i, int i2, int i3) {
            this.noSimIcon = i;
            this.noOneSimIcon = i2;
            this.noTwoSimIcon = i3;
        }

        public final NoSimIcon copy(int i, int i2, int i3) {
            return new NoSimIcon(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NoSimIcon)) {
                return false;
            }
            NoSimIcon noSimIcon = (NoSimIcon) obj;
            return this.noSimIcon == noSimIcon.noSimIcon && this.noOneSimIcon == noSimIcon.noOneSimIcon && this.noTwoSimIcon == noSimIcon.noTwoSimIcon;
        }

        public final int getNoOneSimIcon() {
            return this.noOneSimIcon;
        }

        public final int getNoSimIcon() {
            return this.noSimIcon;
        }

        public final int getNoTwoSimIcon() {
            return this.noTwoSimIcon;
        }

        public final int hashCode() {
            return Integer.hashCode(this.noTwoSimIcon) + MonoSpline$.ExternalSyntheticOutline0.m(this.noOneSimIcon, Integer.hashCode(this.noSimIcon) * 31, 31);
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.noTwoSimIcon, ")", FloatList$.ExternalSyntheticOutline0.m("NoSimIcon(noSimIcon=", this.noSimIcon, this.noOneSimIcon, ", noOneSimIcon=", ", noTwoSimIcon="));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01411 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ OplusStatusBarSignalPolicy this$0;

            public /* synthetic */ C01411(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                int i;
                switch (this.$r8$classId) {
                    case 0:
                        IMSIcon iMSIcon = (IMSIcon) obj;
                        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusStatusBarSignalPolicy.TAG, "imsIcon: " + iMSIcon, null, 8);
                        OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy = this.this$0;
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy, oplusStatusBarSignalPolicy.slotVowifi, iMSIcon.getVowifiIcon(), iMSIcon.getVowifiContenDescription());
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy, oplusStatusBarSignalPolicy.slotVolte, iMSIcon.getVolteIcon(), iMSIcon.getVolteContenDescription());
                        break;
                    case 1:
                        NoSimIcon noSimIcon = (NoSimIcon) obj;
                        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusStatusBarSignalPolicy.TAG, "noSimIcon: " + noSimIcon, null, 8);
                        OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy2 = this.this$0;
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy2, oplusStatusBarSignalPolicy2.slotNoSim, noSimIcon.getNoSimIcon(), "");
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy2, oplusStatusBarSignalPolicy2.slotNoSimOne, noSimIcon.getNoOneSimIcon(), "");
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy2, oplusStatusBarSignalPolicy2.slotNoSimTwo, noSimIcon.getNoTwoSimIcon(), "");
                        break;
                    default:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy3 = this.this$0;
                        if (zBooleanValue) {
                            int[][] iArr = TelephonyIcons.DATA_TYPE;
                            i = 2131236932;
                        } else {
                            i = 0;
                        }
                        OplusStatusBarSignalPolicy.access$updateSlotIconVisibility(oplusStatusBarSignalPolicy3, "airplane", i, oplusStatusBarSignalPolicy3.context.getString(2131951705));
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusStatusBarSignalPolicy.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy = OplusStatusBarSignalPolicy.this;
                oplusStatusBarSignalPolicy.isCollecting = true;
                StateFlow stateFlow = oplusStatusBarSignalPolicy.imsIcon;
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy2 = OplusStatusBarSignalPolicy.this;
                C01411 c01411 = new C01411(0);
                c01411.this$0 = oplusStatusBarSignalPolicy2;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlow.collect(c01411, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$start$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusStatusBarSignalPolicy.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = OplusStatusBarSignalPolicy.this.noSimIcon;
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy = OplusStatusBarSignalPolicy.this;
                AnonymousClass1.C01411 c01411 = new AnonymousClass1.C01411(i2);
                c01411.this$0 = oplusStatusBarSignalPolicy;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlow.collect(c01411, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$start$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusStatusBarSignalPolicy.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsAirplaneMode = OplusStatusBarSignalPolicy.this.airplaneModeInteractor.isAirplaneMode();
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy = OplusStatusBarSignalPolicy.this;
                AnonymousClass1.C01411 c01411 = new AnonymousClass1.C01411(2);
                c01411.this$0 = oplusStatusBarSignalPolicy;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsAirplaneMode.collect(c01411, this) == coroutine_suspended) {
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

    public OplusStatusBarSignalPolicy(Context context, StatusBarIconController statusBarIconController, MobileIconsInteractor mobileIconsInteractor, OplusMobileIconsInteractor oplusMobileIconsInteractor, AirplaneModeInteractor airplaneModeInteractor, OplusWifiRepositoryImpl oplusWifiRepositoryImpl, OplusWifiInteractor oplusWifiInteractor, OplusMobileConnectionsRepository oplusMobileConnectionsRepository, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.iconController = statusBarIconController;
        this.mobileIconsInteractor = mobileIconsInteractor;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
        this.airplaneModeInteractor = airplaneModeInteractor;
        this.oplusWifiRepositoryImpl = oplusWifiRepositoryImpl;
        this.oplusWifiInteractor = oplusWifiInteractor;
        this.oplusMobileConnectionsRepository = oplusMobileConnectionsRepository;
        this.scope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.slotVolte = context.getString(2131956645);
        this.slotVowifi = context.getString(2131956647);
        this.slotNoVolte = context.getString(2131956622);
        this.slotNoSim = context.getString(2131956618);
        this.slotNoSimOne = context.getString(2131956619);
        this.slotNoSimTwo = context.getString(2131956620);
        StateFlow mobileIconInteractors = oplusMobileIconsInteractor.getMobileIconInteractors();
        OplusStatusBarSignalPolicy$special$$inlined$map$1 oplusStatusBarSignalPolicy$special$$inlined$map$1 = new OplusStatusBarSignalPolicy$special$$inlined$map$1(0);
        oplusStatusBarSignalPolicy$special$$inlined$map$1.$this_unsafeTransform$inlined = mobileIconInteractors;
        VarHandle.storeStoreFence();
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(oplusStatusBarSignalPolicy$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.simOneMobileIconInteractor = stateFlowStateIn;
        StateFlow mobileIconInteractors2 = oplusMobileIconsInteractor.getMobileIconInteractors();
        OplusStatusBarSignalPolicy$special$$inlined$map$1 oplusStatusBarSignalPolicy$special$$inlined$map$2 = new OplusStatusBarSignalPolicy$special$$inlined$map$1(1);
        oplusStatusBarSignalPolicy$special$$inlined$map$2.$this_unsafeTransform$inlined = mobileIconInteractors2;
        VarHandle.storeStoreFence();
        StateFlow stateFlowStateIn2 = FlowKt.stateIn(oplusStatusBarSignalPolicy$special$$inlined$map$2, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
        this.simTwoMobileIconInteractor = stateFlowStateIn2;
        StateFlow stateFlowStateIn3 = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn, new OplusStatusBarSignalPolicy$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new OplusIMSModel());
        this.simOneImsModel = stateFlowStateIn3;
        StateFlow stateFlowStateIn4 = FlowKt.stateIn(FlowKt.transformLatest(stateFlowStateIn2, new OplusStatusBarSignalPolicy$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new OplusIMSModel());
        this.simTwoImsModel = stateFlowStateIn4;
        Flow filteredSubscriptions = mobileIconsInteractor.getFilteredSubscriptions();
        OplusStatusBarSignalPolicy$special$$inlined$map$1 oplusStatusBarSignalPolicy$special$$inlined$map$3 = new OplusStatusBarSignalPolicy$special$$inlined$map$1(2);
        oplusStatusBarSignalPolicy$special$$inlined$map$3.$this_unsafeTransform$inlined = filteredSubscriptions;
        VarHandle.storeStoreFence();
        StateFlow stateFlowStateIn5 = FlowKt.stateIn(oplusStatusBarSignalPolicy$special$$inlined$map$3, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.TRUE);
        this.hasNoSubs = stateFlowStateIn5;
        Flow[] flowArr = {stateFlowStateIn3, stateFlowStateIn4, stateFlowStateIn5, airplaneModeInteractor.isAirplaneMode(), oplusWifiInteractor.getWifiConnected(), oplusMobileConnectionsRepository.is5GNewCall()};
        OplusStatusBarSignalPolicy$special$$inlined$combine$1 oplusStatusBarSignalPolicy$special$$inlined$combine$1 = new OplusStatusBarSignalPolicy$special$$inlined$combine$1();
        oplusStatusBarSignalPolicy$special$$inlined$combine$1.$flows$inlined = flowArr;
        oplusStatusBarSignalPolicy$special$$inlined$combine$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.imsIcon = FlowKt.stateIn(FlowKt.flowOn(oplusStatusBarSignalPolicy$special$$inlined$combine$1, coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new IMSIcon(0, 0, null, null, 31, 0));
        this.noSimIcon = FlowKt.stateIn(FlowKt.flowOn(FlowKt.combine(oplusMobileIconsInteractor.getSimState(), stateFlowStateIn5, airplaneModeInteractor.isAirplaneMode(), new OplusStatusBarSignalPolicy$noSimIcon$1(this, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new NoSimIcon(0, 0, 0));
    }

    public static final void access$updateHDPlusNewIcon(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, OplusIMSModel oplusIMSModel, OplusIMSModel oplusIMSModel2, boolean z, boolean z2) {
        oplusStatusBarSignalPolicy.getClass();
        boolean shouldShowVolte = oplusIMSModel.getShouldShowVolte();
        boolean shouldShowVolte2 = oplusIMSModel2.getShouldShowVolte();
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            if ((CustomizeFeatureOption.isSupportShowHDPLUS(0) || CustomizeFeatureOption.isSupportShowHDPLUS(1)) && z2 && (shouldShowVolte || shouldShowVolte2)) {
                boolean zIsSupportShowHDPLUS = CustomizeFeatureOption.isSupportShowHDPLUS(0);
                boolean zIsSupportShowHDPLUS2 = CustomizeFeatureOption.isSupportShowHDPLUS(1);
                boolean z3 = shouldShowVolte && shouldShowVolte2;
                if (z3 && zIsSupportShowHDPLUS && zIsSupportShowHDPLUS2 && z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236801;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
                    return;
                }
                if (z3 && zIsSupportShowHDPLUS && !zIsSupportShowHDPLUS2 && z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236802;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
                    return;
                }
                if (z3 && !zIsSupportShowHDPLUS && zIsSupportShowHDPLUS2 && z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236803;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
                    return;
                }
                if (zIsSupportShowHDPLUS && shouldShowVolte && !shouldShowVolte2 && z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236804;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951967);
                    return;
                }
                if (zIsSupportShowHDPLUS2 && !shouldShowVolte && shouldShowVolte2 && z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236805;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951968);
                    return;
                } else if ((!zIsSupportShowHDPLUS && !zIsSupportShowHDPLUS2) || z) {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 0;
                    return;
                } else {
                    oplusStatusBarSignalPolicy.hdPlusNewIcon = 2131236800;
                    oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951969);
                    return;
                }
            }
        }
        oplusStatusBarSignalPolicy.hdPlusNewIcon = 0;
        oplusStatusBarSignalPolicy.volteContenDescription = "";
    }

    public static final void access$updateSlotIconVisibility(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, String str, int i, String str2) {
        StatusBarIconControllerImpl statusBarIconControllerImpl = oplusStatusBarSignalPolicy.iconController;
        if (i <= 0) {
            statusBarIconControllerImpl.setIconVisibility(str, false);
        } else {
            statusBarIconControllerImpl.setIcon(str, i, str2);
            oplusStatusBarSignalPolicy.iconController.setIconVisibility(str, true);
        }
    }

    public static final void access$updateVo5GIcon(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, OplusIMSModel oplusIMSModel, OplusIMSModel oplusIMSModel2, boolean z) {
        oplusStatusBarSignalPolicy.getClass();
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            oplusStatusBarSignalPolicy.vo5GIcon = 0;
        }
        boolean z2 = true;
        if (!(!CustomizeFeatureOption.checkSlotIdValid(0) ? false : CustomizeFeatureOption.sIsSupportShowVoNR[0])) {
            if (!(!CustomizeFeatureOption.checkSlotIdValid(1) ? false : CustomizeFeatureOption.sIsSupportShowVoNR[1])) {
                z2 = false;
            }
        }
        if (oplusIMSModel.getShouldShowVo5G() && oplusIMSModel2.getShouldShowVo5G()) {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236906 : 2131236889;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
            return;
        }
        if (oplusIMSModel.getShouldShowVo5G() && !oplusIMSModel2.getShouldShowVo5G() && !oplusIMSModel2.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236907 : 2131236890;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951967);
            return;
        }
        if (oplusIMSModel2.getShouldShowVo5G() && !oplusIMSModel.getShouldShowVo5G() && !oplusIMSModel.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236908 : 2131236891;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951968);
            return;
        }
        if (oplusIMSModel.getShouldShowVo5G() && !oplusIMSModel2.getShouldShowVo5G() && oplusIMSModel2.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236905 : 2131236888;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
            return;
        }
        if (oplusIMSModel2.getShouldShowVo5G() && !oplusIMSModel.getShouldShowVo5G() && oplusIMSModel.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236896 : 2131236895;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
        } else if (!oplusIMSModel.getShouldShowVo5G() && !oplusIMSModel2.getShouldShowVo5G()) {
            oplusStatusBarSignalPolicy.vo5GIcon = 0;
        } else {
            oplusStatusBarSignalPolicy.vo5GIcon = z2 ? 2131236904 : 2131236887;
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951969);
        }
    }

    public static final void access$updateVoWifiIcon(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, OplusIMSModel oplusIMSModel, OplusIMSModel oplusIMSModel2, boolean z, boolean z2) {
        oplusStatusBarSignalPolicy.getClass();
        boolean z3 = true;
        if (oplusIMSModel.getShouldShowVodata() && oplusIMSModel.getShouldShowVowifi() && z) {
            oplusStatusBarSignalPolicy.vowifiIcon = 2131236892;
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951997);
        } else if (oplusIMSModel2.getShouldShowVodata() && oplusIMSModel2.getShouldShowVowifi() && z) {
            oplusStatusBarSignalPolicy.vowifiIcon = 2131236893;
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951997);
        } else if (oplusIMSModel.getShouldShowVowifi() && oplusIMSModel2.getShouldShowVowifi()) {
            MobileIconSets.Companion.getClass();
            oplusStatusBarSignalPolicy.vowifiIcon = MobileIconSets.Companion.getVowifiIcon(3);
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951996);
        } else if (oplusIMSModel.getShouldShowVowifi() && z) {
            MobileIconSets.Companion.getClass();
            oplusStatusBarSignalPolicy.vowifiIcon = MobileIconSets.Companion.getVowifiIcon(1);
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951996);
        } else if (oplusIMSModel2.getShouldShowVowifi() && z) {
            MobileIconSets.Companion.getClass();
            oplusStatusBarSignalPolicy.vowifiIcon = MobileIconSets.Companion.getVowifiIcon(2);
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951996);
        } else if (oplusIMSModel.getShouldShowVowifi() || oplusIMSModel2.getShouldShowVowifi()) {
            MobileIconSets.Companion.getClass();
            oplusStatusBarSignalPolicy.vowifiIcon = MobileIconSets.Companion.getVowifiIcon(0);
            oplusStatusBarSignalPolicy.vowifiContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951996);
        } else {
            oplusStatusBarSignalPolicy.vowifiIcon = 0;
            oplusStatusBarSignalPolicy.vowifiContenDescription = "";
        }
        boolean z4 = oplusIMSModel.getShouldShowVodata() || oplusIMSModel2.getShouldShowVodata();
        if (oplusStatusBarSignalPolicy.vowifiIcon == 0 || (!z2 && !QSFeatureOption.INSTANCE.isVirtualCommConsumerDevice(oplusStatusBarSignalPolicy.context) && !z4)) {
            z3 = false;
        }
        if (!z3) {
            oplusStatusBarSignalPolicy.vowifiIcon = 0;
            oplusStatusBarSignalPolicy.vowifiContenDescription = "";
        }
        int i = oplusStatusBarSignalPolicy.vowifiIcon;
        StringBuilder sb = new StringBuilder("imsIcon: ");
        sb.append(oplusIMSModel);
        sb.append(", ");
        sb.append(oplusIMSModel2);
        sb.append("visible: ");
        KeyguardLogger$.ExternalSyntheticOutline0.m(" ,wifiConnect:", " , vowifiIcon:", sb, z3, z2);
        sb.append(i);
        sb.append(" , voDataStatus:");
        sb.append(z4);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sb.toString(), null, 8);
    }

    public static final void access$updateVolteIcon(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, OplusIMSModel oplusIMSModel, OplusIMSModel oplusIMSModel2, boolean z) {
        oplusStatusBarSignalPolicy.getClass();
        if (oplusIMSModel.getShouldShowVolte() && oplusIMSModel2.getShouldShowVolte()) {
            oplusStatusBarSignalPolicy.volteIcon = MobileIconSets.Companion.getVolteIcon(oplusStatusBarSignalPolicy.context, 3);
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951966);
            return;
        }
        if (oplusIMSModel.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.volteIcon = MobileIconSets.Companion.getVolteIcon(oplusStatusBarSignalPolicy.context, 1);
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951967);
            return;
        }
        if (oplusIMSModel2.getShouldShowVolte() && z) {
            oplusStatusBarSignalPolicy.volteIcon = MobileIconSets.Companion.getVolteIcon(oplusStatusBarSignalPolicy.context, 2);
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951968);
        } else if (oplusIMSModel.getShouldShowVolte() || oplusIMSModel2.getShouldShowVolte()) {
            oplusStatusBarSignalPolicy.volteIcon = MobileIconSets.Companion.getVolteIcon(oplusStatusBarSignalPolicy.context, 0);
            oplusStatusBarSignalPolicy.volteContenDescription = oplusStatusBarSignalPolicy.context.getString(2131951969);
        } else {
            oplusStatusBarSignalPolicy.volteIcon = 0;
            oplusStatusBarSignalPolicy.volteContenDescription = "";
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isCollecting=", this.isCollecting);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("Last imsIcon: ", this.imsIcon.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("Last simOneImsModel: ", this.simOneImsModel.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("Last simTwoImsModel: ", this.simTwoImsModel.getValue(), printWriter);
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("Last noSimIcon: ", this.noSimIcon.getValue(), printWriter);
        OplusMobileIconInteractor oplusMobileIconInteractor = (OplusMobileIconInteractor) this.simOneMobileIconInteractor.getValue();
        if (oplusMobileIconInteractor != null) {
            oplusMobileIconInteractor.dump(printWriter, strArr, 0);
        }
        OplusMobileIconInteractor oplusMobileIconInteractor2 = (OplusMobileIconInteractor) this.simTwoMobileIconInteractor.getValue();
        if (oplusMobileIconInteractor2 != null) {
            oplusMobileIconInteractor2.dump(printWriter, strArr, 1);
        }
    }

    public final boolean getVoWifiState() {
        return ((OplusIMSModel) this.simOneImsModel.getValue()).getVowifiState() || ((OplusIMSModel) this.simTwoImsModel.getValue()).getVowifiState();
    }

    public final boolean isVoWifiState(int i) {
        if (i == 0) {
            return ((OplusIMSModel) this.simOneImsModel.getValue()).getVowifiState();
        }
        if (i != 1) {
            return false;
        }
        return ((OplusIMSModel) this.simTwoImsModel.getValue()).getVowifiState();
    }

    public final void start() {
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(null), 3);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class IMSIcon {
        public final int noVolteIcon;
        private final String volteContenDescription;
        public final int volteIcon;
        private final String vowifiContenDescription;
        public final int vowifiIcon;

        public /* synthetic */ IMSIcon(int i, int i2, String str, String str2, int i3, int i4) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? "" : str2, 0);
        }

        public final String component2() {
            return this.volteContenDescription;
        }

        public final String component4() {
            return this.vowifiContenDescription;
        }

        public final IMSIcon copy(int i, String str, int i2, String str2, int i3) {
            return new IMSIcon(i, str, i2, str2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IMSIcon)) {
                return false;
            }
            IMSIcon iMSIcon = (IMSIcon) obj;
            return this.volteIcon == iMSIcon.volteIcon && Intrinsics.areEqual(this.volteContenDescription, iMSIcon.volteContenDescription) && this.vowifiIcon == iMSIcon.vowifiIcon && Intrinsics.areEqual(this.vowifiContenDescription, iMSIcon.vowifiContenDescription) && this.noVolteIcon == iMSIcon.noVolteIcon;
        }

        public final int getNoVolteIcon() {
            return this.noVolteIcon;
        }

        public final String getVolteContenDescription() {
            return this.volteContenDescription;
        }

        public final int getVolteIcon() {
            return this.volteIcon;
        }

        public final String getVowifiContenDescription() {
            return this.vowifiContenDescription;
        }

        public final int getVowifiIcon() {
            return this.vowifiIcon;
        }

        public final int hashCode() {
            return Integer.hashCode(this.noVolteIcon) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.vowifiIcon, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.volteIcon) * 31, 31, this.volteContenDescription), 31), 31, this.vowifiContenDescription);
        }

        public String toString() {
            String str = this.volteContenDescription;
            String str2 = this.vowifiContenDescription;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(this.volteIcon, "IMSIcon(volteIcon=", ", volteContenDescription=", str, ", vowifiIcon=");
            BluetoothUtils$.ExternalSyntheticOutline0.m(this.vowifiIcon, ", vowifiContenDescription=", str2, ", noVolteIcon=", sbM);
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.noVolteIcon, ")", sbM);
        }

        public IMSIcon(int i, String str, int i2, String str2, int i3) {
            this.volteIcon = i;
            this.volteContenDescription = str;
            this.vowifiIcon = i2;
            this.vowifiContenDescription = str2;
            this.noVolteIcon = i3;
        }
    }
}
