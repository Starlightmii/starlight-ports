package com.oplus.systemui.statusbar.pipeline.mobile.data.model;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.android.systemui.biometrics.ui.PromptIconState$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapabilityTypeModel {
    public final boolean hasHideVolteOperator;
    public final boolean imsRegistered;
    public final boolean isCrossSimCallingEnabledByUser;
    public final boolean isVolteSwitchOn;
    public final boolean isWfcEnabledByUser;
    public final int registerTech;
    public final boolean videoCapable;
    public final boolean voiceCapable;

    public CapabilityTypeModel(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, boolean z6, boolean z7) {
        this.isVolteSwitchOn = z;
        this.isWfcEnabledByUser = z2;
        this.imsRegistered = z3;
        this.voiceCapable = z4;
        this.videoCapable = z5;
        this.registerTech = i;
        this.isCrossSimCallingEnabledByUser = z6;
        this.hasHideVolteOperator = z7;
    }

    public static /* synthetic */ CapabilityTypeModel copy$default(CapabilityTypeModel capabilityTypeModel, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, boolean z6, boolean z7, int i2) {
        if ((i2 & 1) != 0) {
            z = capabilityTypeModel.isVolteSwitchOn;
        }
        boolean z8 = z;
        if ((i2 & 2) != 0) {
            z2 = capabilityTypeModel.isWfcEnabledByUser;
        }
        boolean z9 = z2;
        if ((i2 & 4) != 0) {
            z3 = capabilityTypeModel.imsRegistered;
        }
        boolean z10 = z3;
        if ((i2 & 8) != 0) {
            z4 = capabilityTypeModel.voiceCapable;
        }
        boolean z11 = z4;
        if ((i2 & 16) != 0) {
            z5 = capabilityTypeModel.videoCapable;
        }
        return capabilityTypeModel.copy(z8, z9, z10, z11, z5, i, z6, (i2 & 128) != 0 ? capabilityTypeModel.hasHideVolteOperator : z7);
    }

    public final CapabilityTypeModel copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, boolean z6, boolean z7) {
        return new CapabilityTypeModel(z, z2, z3, z4, z5, i, z6, z7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!CapabilityTypeModel.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        CapabilityTypeModel capabilityTypeModel = (CapabilityTypeModel) obj;
        return this.isVolteSwitchOn == capabilityTypeModel.isVolteSwitchOn && this.isWfcEnabledByUser == capabilityTypeModel.isWfcEnabledByUser && this.imsRegistered == capabilityTypeModel.imsRegistered && this.voiceCapable == capabilityTypeModel.voiceCapable && this.videoCapable == capabilityTypeModel.videoCapable && this.registerTech == capabilityTypeModel.registerTech && this.isCrossSimCallingEnabledByUser == capabilityTypeModel.isCrossSimCallingEnabledByUser && this.hasHideVolteOperator == capabilityTypeModel.hasHideVolteOperator;
    }

    public final boolean getHasHideVolteOperator() {
        return this.hasHideVolteOperator;
    }

    public final boolean getImsRegistered() {
        return this.imsRegistered;
    }

    public final int getRegisterTech() {
        return this.registerTech;
    }

    public final boolean getVideoCapable() {
        return this.videoCapable;
    }

    public final boolean getVoiceCapable() {
        return this.voiceCapable;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.hasHideVolteOperator) + ChangeSize$.ExternalSyntheticOutline0.m((ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.isVolteSwitchOn) * 31, 31, this.isWfcEnabledByUser), 31, this.imsRegistered), 31, this.voiceCapable), 31, this.videoCapable) + this.registerTech) * 31, 31, this.isCrossSimCallingEnabledByUser);
    }

    public String toString() {
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("CapabilityTypeModel(isVolteSwitchOn=", ", isWfcEnabledByUser=", ", imsRegistered=", this.isVolteSwitchOn, this.isWfcEnabledByUser);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", voiceCapable=", ", videoCapable=", sbM, this.imsRegistered, this.voiceCapable);
        PromptIconState$.ExternalSyntheticOutline0.m(this.registerTech, ", registerTech=", ", isCrossSimCallingEnabledByUser=", sbM, this.videoCapable);
        return DynamicRouteAttributes$.ExternalSyntheticOutline0.m(", hasHideVolteOperator=", ")", sbM, this.isCrossSimCallingEnabledByUser, this.hasHideVolteOperator);
    }

    public /* synthetic */ CapabilityTypeModel() {
        this(false, false, false, false, false, -1, false, false);
    }
}
