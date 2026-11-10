package com.oplus.systemui.statusbar.pipeline.wifi.interactor;

import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepository;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractor;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiInteractorImpl implements OplusWifiInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusWifiInteractorImpl";
    private final StateFlow activity;
    private final Flow bandLabelOff;
    private final Flow isUseWifiStyle17;
    private final StateFlow isValidated;
    private final Flow oplusWifiModel;
    private final Flow technicalEnable;
    private final Flow wifiConnected;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractorImpl$isValidated$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function4 {
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
            anonymousClass1.Z$0 = zBooleanValue;
            anonymousClass1.L$0 = (DefaultConnectionModel) obj2;
            anonymousClass1.Z$1 = zBooleanValue2;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            DefaultConnectionModel defaultConnectionModel = (DefaultConnectionModel) this.L$0;
            boolean z2 = this.Z$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            FeatureOption.INSTANCE.getClass();
            boolean z3 = false;
            boolean z4 = FeatureOption.isExpRegion() && OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusWifiSignalEx().isShowWifiIndicatorWhenEnabled();
            if (z && !z2) {
                FlavorTwoFeatureOption.INSTANCE.getClass();
                if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp() || !FeatureOption.isExpRegion() || z4) {
                    DefaultConnectionModel.Wifi wifi = defaultConnectionModel.getWifi();
                    boolean z5 = defaultConnectionModel.isValidated;
                    if (wifi.isDefault && z5) {
                        z3 = true;
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" + expRegionWifiShow:", "OplusWifiInteractorImpl-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("isValidated:", "  isWiFiDefault:", " default validated:", z3, defaultConnectionModel.getWifi().isDefault), z5, z4));
                    }
                } else {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusWifiInteractorImpl-->", "SystemUi--Statusbar", TextFieldImplKt$.ExternalSyntheticOutline0.m("isValidated true wifiConnected:", "isWifiToData:", " expRegionWifiShow:", z, z2), z4);
                    }
                    z3 = true;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusWifiInteractorImpl-->".concat(IconProvider$.ExternalSyntheticOutline0.m("isValidated false wifiConnected:", " isWifiToData:", " ", z, z2)));
            }
            return Boxing.boxBoolean(z3);
        }
    }

    public OplusWifiInteractorImpl(WifiInteractor wifiInteractor, OplusWifiRepository oplusWifiRepository, CoroutineScope coroutineScope, ConnectivityRepository connectivityRepository) {
        this.activity = wifiInteractor.getActivity();
        this.bandLabelOff = oplusWifiRepository.getWlanBandLabelOff();
        this.oplusWifiModel = oplusWifiRepository.getOplusWifiModel();
        this.technicalEnable = oplusWifiRepository.getWifiTechnicalEnable();
        Flow wifiNetwork = wifiInteractor.getWifiNetwork();
        OplusWifiInteractorImpl$special$$inlined$map$1 oplusWifiInteractorImpl$special$$inlined$map$1 = new OplusWifiInteractorImpl$special$$inlined$map$1(0);
        oplusWifiInteractorImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = wifiNetwork;
        VarHandle.storeStoreFence();
        this.wifiConnected = oplusWifiInteractorImpl$special$$inlined$map$1;
        StateFlow wifiThemeAdapterState = oplusWifiRepository.getWifiThemeAdapterState();
        OplusWifiInteractorImpl$special$$inlined$map$1 oplusWifiInteractorImpl$special$$inlined$map$2 = new OplusWifiInteractorImpl$special$$inlined$map$1(1);
        oplusWifiInteractorImpl$special$$inlined$map$2.$this_unsafeTransform$inlined = wifiThemeAdapterState;
        VarHandle.storeStoreFence();
        this.isUseWifiStyle17 = FlowKt.distinctUntilChanged(oplusWifiInteractorImpl$special$$inlined$map$2);
        this.isValidated = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(getWifiConnected(), connectivityRepository.getDefaultConnections(), oplusWifiRepository.isWifiToData(), new AnonymousClass1(4, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), Boolean.FALSE);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public StateFlow getActivity() {
        return this.activity;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public Flow getBandLabelOff() {
        return this.bandLabelOff;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public Flow getOplusWifiModel() {
        return this.oplusWifiModel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public Flow getTechnicalEnable() {
        return this.technicalEnable;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public Flow getWifiConnected() {
        return this.wifiConnected;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public Flow isUseWifiStyle17() {
        return this.isUseWifiStyle17;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.wifi.interactor.OplusWifiInteractor
    public StateFlow isValidated() {
        return this.isValidated;
    }
}
