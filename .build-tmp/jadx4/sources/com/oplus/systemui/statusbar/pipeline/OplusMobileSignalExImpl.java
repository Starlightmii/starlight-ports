package com.oplus.systemui.statusbar.pipeline;

import android.content.Context;
import android.telephony.OplusTelephonyManager;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.settingslib.SignalIcon;
import com.android.settingslib.Utils;
import com.android.settingslib.mobile.MobileMappings;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.OplusMobileSignalEx;
import com.android.systemui.statusbar.pipeline.mobile.data.OplusNetworkTypeText;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernStatusBarMobileView;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepository;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.keyguard.telephony.SimStateHelper;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplus.systemui.statusbar.phone.signal.network.OplusCustMobileTypeSets;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.MobileThemeAdapterState;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.OplusMobileThemeManagerRepository;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.view.ICustStatusBarMobileViewBinder;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.view.OplusMobileViewBinderFactory;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import com.oplus.systemui.statusbar.policy.OplusNetworkTypeMappingManager;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.statusbar.util.NetworkServiceProxy;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileSignalExImpl extends OplusMobileSignalEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMobileSignalExImpl";
    private int[] cardType = {-1, -1};
    private final ConnectivityRepository connectivityRepository;
    private final Context context;
    private final OplusMobileConnectionsRepository oplusMobileConnectionsRepository;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final OplusMobileThemeManagerRepository oplusMobileThemeManagerRepository;
    private final OplusMobileViewBinderFactory oplusMobileViewBinderFactory;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMobileSignalExImpl(Context context, OplusMobileConnectionsRepository oplusMobileConnectionsRepository, ConnectivityRepository connectivityRepository, OplusMobileIconsInteractor oplusMobileIconsInteractor, OplusMobileViewBinderFactory oplusMobileViewBinderFactory, OplusMobileThemeManagerRepository oplusMobileThemeManagerRepository) {
        this.context = context;
        this.oplusMobileConnectionsRepository = oplusMobileConnectionsRepository;
        this.connectivityRepository = connectivityRepository;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
        this.oplusMobileViewBinderFactory = oplusMobileViewBinderFactory;
        this.oplusMobileThemeManagerRepository = oplusMobileThemeManagerRepository;
    }

    private final MobileIconSets getMobileIconSets() {
        return this.oplusMobileIconsInteractor.getCurrentMobileIconSets();
    }

    private final ICustStatusBarMobileViewBinder getMobileViewBinder() {
        return this.oplusMobileViewBinderFactory.getMobileViewBinder(((MobileThemeAdapterState) this.oplusMobileThemeManagerRepository.getThemeAdapterState().getValue()).isUnstackedUseTheme17());
    }

    public OplusMobileSignalEx.OplusMobileViewBinding bindEx(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Flow flow, StateFlow stateFlow, MobileViewLogger mobileViewLogger) {
        return getMobileViewBinder().bindCustEx(locationBasedMobileViewModel, viewGroup, flow, stateFlow, mobileViewLogger);
    }

    public MobileIconInteractor createOplusMobileIconInteractor(int i, CoroutineScope coroutineScope, MobileIconInteractor mobileIconInteractor, Flow flow) {
        return new OplusMobileIconInteractorImpl(coroutineScope, mobileIconInteractor, this.oplusMobileConnectionsRepository.getRepoForSubId(i, flow), this.oplusMobileConnectionsRepository, this.connectivityRepository, this.oplusMobileIconsInteractor, flow, this.context);
    }

    public StateFlow get5GCellCount(int i, Flow flow) {
        return this.oplusMobileConnectionsRepository.getRepoForSubId(i, flow).getS5GCellCount();
    }

    public final int getCurrentIconId(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!z) {
            return getMobileIconSets().getNoServiceIcon(this.context, z2);
        }
        boolean z6 = false;
        if (!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsSoftBankVoiceType[i]) {
            return getMobileIconSets().getSignalStrength(this.context, i, z3, i2, z2, z4);
        }
        if (z2 && !z5) {
            z6 = true;
        }
        return getMobileIconSets().getSignalStrength(i2, z6);
    }

    public final int getCurrentStackedIconId(boolean z, int i, int i2, int i3, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (z2) {
            return getMobileIconSets().getStackedSignalStrength(z, z4, i2, z3, z5, i);
        }
        return getMobileIconSets().getStackedNoServiceIcon(this.context, z, z3 && !z6);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x006e  */
    public String getIconKeyEx(int i, TelephonyDisplayInfo telephonyDisplayInfo, ServiceState serviceState, int i2) {
        TelephonyDisplayInfo telephonyDisplayInfo2;
        Integer numValueOf = serviceState != null ? Integer.valueOf(serviceState.getNrState()) : null;
        if (telephonyDisplayInfo.getOverrideNetworkType() == 4 || telephonyDisplayInfo.getOverrideNetworkType() == 1 || telephonyDisplayInfo.getOverrideNetworkType() == 5 || CustomizeFeatureOption.sIsIconNrad5guc[i] || CustomizeFeatureOption.sIsIconNrad5guw[i] || CustomizeFeatureOption.sIsIconNrad5gPlus[i] || CustomizeFeatureOption.sIsIconNrad5ga[i]) {
            telephonyDisplayInfo2 = telephonyDisplayInfo;
        } else {
            if (!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsIconNrad5gPlusPlus[i]) {
                telephonyDisplayInfo2 = telephonyDisplayInfo;
            } else {
                if ((!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsIconNradFiveDot5g[i]) || ((numValueOf == null || numValueOf.intValue() != 3) && (numValueOf == null || numValueOf.intValue() != 2))) {
                    telephonyDisplayInfo2 = telephonyDisplayInfo;
                } else {
                    telephonyDisplayInfo2 = new TelephonyDisplayInfo(telephonyDisplayInfo.getNetworkType(), 3, telephonyDisplayInfo.isRoaming());
                }
            }
        }
        String iconKey = MobileMappings.getIconKey(telephonyDisplayInfo2);
        boolean zIsInService = Utils.isInService(serviceState);
        int overrideNetworkType = telephonyDisplayInfo2.getOverrideNetworkType();
        int networkType = telephonyDisplayInfo2.getNetworkType();
        int nrState = serviceState.getNrState();
        if (!zIsInService || networkType == 0) {
            return MobileIconSets.Companion.toIconKey(0);
        }
        if ((!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsTigo5GIconSupport[i]) && ((networkType == 13 && overrideNetworkType == 3) || (networkType == 20 && overrideNetworkType == 0))) {
            return nrState == 3 ? MobileIconSets.Companion.toDisplayIconKey(108) : MobileIconSets.Companion.toDisplayIconKey(109);
        }
        if (!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sIsIconNrad5gPlusPlus[i]) {
            iconKey.getClass();
            if (networkType == 13 && overrideNetworkType == 0) {
                return MobileIconSets.Companion.toIconKey(13);
            }
            if (networkType == 13 && overrideNetworkType == 3) {
                return i2 > 1 ? MobileIconSets.Companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM) : MobileIconSets.Companion.toDisplayIconKey(3);
            }
            if (networkType == 20 && overrideNetworkType == 0) {
                return MobileIconSets.Companion.toDisplayIconKey(103);
            }
            if (networkType == 20 && overrideNetworkType == 5) {
                return i2 > 2 ? MobileIconSets.Companion.toDisplayIconKey(105) : MobileIconSets.Companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_POCKET_STUDIO_OPEN_TO_ZOOM);
            }
            return iconKey;
        }
        if (overrideNetworkType == 2) {
            if (CustomizeFeatureOption.sIsIconLtead5ge[i]) {
                return MobileIconSets.Companion.toDisplayIconKey(104);
            }
            if (CustomizeFeatureOption.checkSlotIdValid(i) ? CustomizeFeatureOption.sIsIconFourDot5g[i] : false) {
                return MobileIconSets.Companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_FULL);
            }
            iconKey.getClass();
            return iconKey;
        }
        if (overrideNetworkType != 5) {
            if (overrideNetworkType == 1) {
                return CustomizeFeatureOption.checkSlotIdValid(i) ? CustomizeFeatureOption.sIsIconFourDot5g[i] : false ? MobileIconSets.Companion.toDisplayIconKey(ZoomAnimationUtils.TRANSIT_STATE_SWITCH) : MobileIconSets.Companion.toDisplayIconKey(overrideNetworkType);
            }
            if (overrideNetworkType == 4 || overrideNetworkType == 3) {
                return MobileIconSets.Companion.toDisplayIconKey(overrideNetworkType);
            }
            iconKey.getClass();
            return iconKey;
        }
        if (CustomizeFeatureOption.sIsIconNrad5guc[i]) {
            return MobileIconSets.Companion.toDisplayIconKey(101);
        }
        if (CustomizeFeatureOption.sIsIconNrad5guw[i]) {
            return MobileIconSets.Companion.toDisplayIconKey(102);
        }
        if (CustomizeFeatureOption.sIsIconNrad5gPlus[i]) {
            return MobileIconSets.Companion.toDisplayIconKey(103);
        }
        if (CustomizeFeatureOption.sIsIconNrad5ga[i]) {
            return MobileIconSets.Companion.toDisplayIconKey(105);
        }
        if (!(CustomizeFeatureOption.checkSlotIdValid(i) ? CustomizeFeatureOption.sIsIconNradFiveDot5g[i] : false)) {
            return MobileIconSets.Companion.toDisplayIconKey(overrideNetworkType);
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(overrideNetworkType, " overrideNetworkType:", " ,display 5.5G"), null, 8);
        return MobileIconSets.Companion.toDisplayIconKey(107);
    }

    public int getLevel(ServiceState serviceState, SignalStrength signalStrength, int i) {
        int i2;
        if (i < 0 || i > 1) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "mobile signal level getLevel: 0 slotId:" + i);
            return 0;
        }
        this.cardType[i] = OplusTelephonyManager.getInstance(this.context).getCardType(i);
        int[] oSLevel = NetworkServiceProxy.getOSLevel(signalStrength);
        if (oSLevel != null) {
            i2 = (this.cardType[i] == 1 && i == AbsSettingsValueProxy.getGlobalIntValue(this.context, "oplus_customize_multi_sim_network_primary_slot", 0)) ? oSLevel[1] : oSLevel[0];
            if (i2 == 0 && (i2 = oSLevel[0]) == 0) {
                i2 = oSLevel[1];
            }
        } else {
            i2 = 0;
        }
        if (oSLevel != null && oSLevel.length == 2) {
            Integer numValueOf = serviceState != null ? Integer.valueOf(serviceState.getVoiceRegState()) : null;
            Integer numValueOf2 = serviceState != null ? Integer.valueOf(serviceState.getDataRegState()) : null;
            if (this.cardType[i] == 1) {
                if ((numValueOf2 == null || numValueOf2.intValue() != 0) && numValueOf != null && numValueOf.intValue() == 0) {
                    i2 = oSLevel[0];
                }
                return ((numValueOf != null && numValueOf.intValue() == 0) || numValueOf2 == null || numValueOf2.intValue() != 0) ? i2 : oSLevel[1];
            }
            if ((numValueOf == null || numValueOf.intValue() != 0) && numValueOf2 != null && numValueOf2.intValue() == 0) {
                return oSLevel[1];
            }
        }
        return i2;
    }

    public OplusNetworkTypeText getMappingNetworkTypeText(SignalIcon.MobileIconGroup mobileIconGroup) {
        return OplusNetworkTypeMappingManager.INSTANCE.getNetworkTypeText(mobileIconGroup);
    }

    public ModernStatusBarMobileView inflateMobileView(Context context, StatusBarLocation statusBarLocation) {
        Context fixContext;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled() && (fixContext = AodUtils.getFixContext(context)) != null) {
            context = fixContext;
        }
        return getMobileViewBinder().fromContext(context);
    }

    public StateFlow isOplusFeatureChanged(int i, Flow flow) {
        return this.oplusMobileConnectionsRepository.getRepoForSubId(i, flow).getOplusFeatureChanged();
    }

    public boolean isRoamingEx(TelephonyManager telephonyManager, int i) {
        int cdmaEriIconMode = telephonyManager.getCdmaEriIconMode(i);
        if (telephonyManager.getCdmaEriIconIndex(i) != 1) {
            return cdmaEriIconMode == 0 || cdmaEriIconMode == 1;
        }
        return false;
    }

    public Map<String, SignalIcon.MobileIconGroup> mapCustomizeIconSets(Map<String, ? extends SignalIcon.MobileIconGroup> map, int i, boolean z) {
        return OplusCustMobileTypeSets.Companion.getInstance(this.context).getCustomizeNetworkIconSets((HashMap) map, i, z);
    }

    public Map<String, SignalIcon.MobileIconGroup> mapIconSets(MobileMappings.Config config) {
        return getMobileIconSets().mapNetworkIconSets(config);
    }

    public void onServiceStateChanged(int i, ServiceState serviceState) {
        if (serviceState != null) {
            SimStateHelper.Companion.getInstance(this.context).handleServiceStateChanged(i, serviceState);
            ((OplusQSCarrierTextController) DependencyEx.sDependency.getDependency(OplusQSCarrierTextController.class)).updateCarrierTextInBgThread(false);
        }
    }

    public MobileMappings.Config toOplusConfig(MobileMappings.Config config) {
        config.alwaysShowDataRatIcon = this.context.getResources().getBoolean(2131034114);
        return config;
    }
}
