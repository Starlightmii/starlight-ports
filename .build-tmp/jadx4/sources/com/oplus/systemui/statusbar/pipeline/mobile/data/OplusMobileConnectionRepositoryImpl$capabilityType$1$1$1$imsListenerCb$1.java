package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.telephony.ims.feature.MmTelFeature;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.runtime.OpaqueKey$;
import com.android.ims.ImsManager;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.statusbar.pipeline.mobile.data.ims.IImsListenerCb;
import com.oplus.systemui.statusbar.pipeline.mobile.data.ims.ImsListenerProxy;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.CapabilityTypeModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1 implements IImsListenerCb {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ Ref.ObjectRef $model;
    public /* synthetic */ int $phoneId;
    public /* synthetic */ OplusMobileConnectionRepositoryImpl this$0;

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.ims.IImsListenerCb
    public final void onImsCapabilitiesStatusChanged(int i, MmTelFeature.MmTelCapabilities mmTelCapabilities, ImsManager imsManager) {
        OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl = this.this$0;
        Ref.ObjectRef objectRef = this.$model;
        CapabilityTypeModel capabilityTypeModel = (CapabilityTypeModel) objectRef.element;
        boolean zContains = false;
        boolean zIsEnhanced4gLteModeSettingEnabledByUser = imsManager != null ? imsManager.isEnhanced4gLteModeSettingEnabledByUser() : false;
        boolean zIsWfcEnabledByUser = imsManager != null ? imsManager.isWfcEnabledByUser() : false;
        boolean zIsCapable = mmTelCapabilities != null ? mmTelCapabilities.isCapable(1) : false;
        boolean zIsCapable2 = mmTelCapabilities != null ? mmTelCapabilities.isCapable(2) : false;
        int registrationTech = imsManager != null ? imsManager.getRegistrationTech() : -1;
        boolean zIsCrossSimCallingEnabledByUser = imsManager != null ? imsManager.isCrossSimCallingEnabledByUser() : false;
        int i2 = oplusMobileConnectionRepositoryImpl.subId;
        if (CustomizeFeatureOption.sHideVolteOperator.size() > 0) {
            zContains = CustomizeFeatureOption.sHideVolteOperator.contains(oplusMobileConnectionRepositoryImpl.telephonyManager.createForSubscriptionId(i2).getSimOperator());
        }
        objectRef.element = CapabilityTypeModel.copy$default(capabilityTypeModel, zIsEnhanced4gLteModeSettingEnabledByUser, zIsWfcEnabledByUser, false, zIsCapable, zIsCapable2, registrationTech, zIsCrossSimCallingEnabledByUser, zContains, 4);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, oplusMobileConnectionRepositoryImpl.logTag, OpaqueKey$.ExternalSyntheticOutline0.m(objectRef.element, "onCapabilitiesStatusChanged model: "), null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
        oplusMobileConnectionRepositoryImpl.mobileConnectionLogger.logChange(OplusMobileConnectionRepositoryImpl.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.$phoneId, i, "onImsCapabilitiesStatusChanged_phoneId_", "_slotId_"), ((CapabilityTypeModel) objectRef.element).toString());
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.ims.IImsListenerCb
    public final void onImsRegistrationStatusChanged(int i, int i2, ImsManager imsManager) {
        int i3 = this.$phoneId;
        OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl = this.this$0;
        Ref.ObjectRef objectRef = this.$model;
        if (i2 == 0) {
            objectRef.element = CapabilityTypeModel.copy$default((CapabilityTypeModel) objectRef.element, false, false, false, false, false, imsManager != null ? imsManager.getRegistrationTech() : -1, imsManager != null ? imsManager.isCrossSimCallingEnabledByUser() : false, false, 155);
        } else if (i2 == 1) {
            objectRef.element = CapabilityTypeModel.copy$default((CapabilityTypeModel) objectRef.element, imsManager != null ? imsManager.isEnhanced4gLteModeSettingEnabledByUser() : false, imsManager != null ? imsManager.isWfcEnabledByUser() : false, false, false, false, imsManager != null ? imsManager.getRegistrationTech() : -1, imsManager != null ? imsManager.isCrossSimCallingEnabledByUser() : false, false, 152);
        } else if (i2 == 2) {
            objectRef.element = CapabilityTypeModel.copy$default((CapabilityTypeModel) objectRef.element, imsManager != null ? imsManager.isEnhanced4gLteModeSettingEnabledByUser() : false, imsManager != null ? imsManager.isWfcEnabledByUser() : false, true, false, false, imsManager != null ? imsManager.getRegistrationTech() : -1, imsManager != null ? imsManager.isCrossSimCallingEnabledByUser() : false, false, 152);
        }
        String str = oplusMobileConnectionRepositoryImpl.logTag;
        ImsListenerProxy.Companion companion = ImsListenerProxy.Companion;
        String resStatusString = companion.getResStatusString(i2);
        Object obj = objectRef.element;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i3, "phoneId:", " onImsRegistrationStatusChanged:", resStatusString, " model:");
        sbM.append(obj);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, str, sbM.toString(), null, 8);
        oplusMobileConnectionRepositoryImpl.mobileConnectionLogger.logChange(OplusMobileConnectionRepositoryImpl.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i3, i, "onImsRegistrationStatusChanged_phoneId_", "_slotId_"), companion.getResStatusString(i2));
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.ims.IImsListenerCb
    public final void onImsStatusChanged(int i, int i2, MmTelFeature.MmTelCapabilities mmTelCapabilities, ImsManager imsManager) {
        OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl = this.this$0;
        Ref.ObjectRef objectRef = this.$model;
        CapabilityTypeModel capabilityTypeModel = (CapabilityTypeModel) objectRef.element;
        boolean zContains = false;
        boolean z = i2 == 2;
        boolean zIsEnhanced4gLteModeSettingEnabledByUser = imsManager != null ? imsManager.isEnhanced4gLteModeSettingEnabledByUser() : false;
        boolean zIsWfcEnabledByUser = imsManager != null ? imsManager.isWfcEnabledByUser() : false;
        int registrationTech = imsManager != null ? imsManager.getRegistrationTech() : -1;
        boolean zIsCrossSimCallingEnabledByUser = imsManager != null ? imsManager.isCrossSimCallingEnabledByUser() : false;
        boolean zIsCapable = mmTelCapabilities != null ? mmTelCapabilities.isCapable(1) : false;
        boolean zIsCapable2 = mmTelCapabilities != null ? mmTelCapabilities.isCapable(2) : false;
        int i3 = oplusMobileConnectionRepositoryImpl.subId;
        if (CustomizeFeatureOption.sHideVolteOperator.size() > 0) {
            zContains = CustomizeFeatureOption.sHideVolteOperator.contains(oplusMobileConnectionRepositoryImpl.telephonyManager.createForSubscriptionId(i3).getSimOperator());
        }
        objectRef.element = capabilityTypeModel.copy(zIsEnhanced4gLteModeSettingEnabledByUser, zIsWfcEnabledByUser, z, zIsCapable, zIsCapable2, registrationTech, zIsCrossSimCallingEnabledByUser, zContains);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, oplusMobileConnectionRepositoryImpl.logTag, OpaqueKey$.ExternalSyntheticOutline0.m(objectRef.element, "onImsStatusChanged model: "), null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
        oplusMobileConnectionRepositoryImpl.mobileConnectionLogger.logChange(OplusMobileConnectionRepositoryImpl.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.$phoneId, i, "onImsStatusChanged_phoneId_", "_slotId_"), ((CapabilityTypeModel) objectRef.element).toString());
    }
}
