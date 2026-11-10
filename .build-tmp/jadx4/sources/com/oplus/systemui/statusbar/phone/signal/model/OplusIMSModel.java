package com.oplus.systemui.statusbar.phone.signal.model;

import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusIMSModel {
    public final boolean noSim;
    public final boolean shouldShowVo5G;
    public final boolean shouldShowVodata;
    public final boolean shouldShowVolte;
    public final boolean shouldShowVowifi;
    public final boolean simInserted;
    public final boolean vowifiState;

    public OplusIMSModel(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.shouldShowVolte = z;
        this.shouldShowVowifi = z2;
        this.simInserted = z3;
        this.noSim = z4;
        this.shouldShowVodata = z5;
        this.shouldShowVo5G = z6;
        this.vowifiState = z7;
    }

    public final OplusIMSModel copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return new OplusIMSModel(z, z2, z3, z4, z5, z6, z7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!OplusIMSModel.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        OplusIMSModel oplusIMSModel = (OplusIMSModel) obj;
        return this.shouldShowVolte == oplusIMSModel.shouldShowVolte && this.shouldShowVowifi == oplusIMSModel.shouldShowVowifi && this.simInserted == oplusIMSModel.simInserted && this.shouldShowVodata == oplusIMSModel.shouldShowVodata && this.shouldShowVo5G == oplusIMSModel.shouldShowVo5G && this.vowifiState == oplusIMSModel.vowifiState && this.noSim == oplusIMSModel.noSim;
    }

    public final boolean getNoSim() {
        return this.noSim;
    }

    public final boolean getShouldShowVo5G() {
        return this.shouldShowVo5G;
    }

    public final boolean getShouldShowVodata() {
        return this.shouldShowVodata;
    }

    public final boolean getShouldShowVolte() {
        return this.shouldShowVolte;
    }

    public final boolean getShouldShowVowifi() {
        return this.shouldShowVowifi;
    }

    public final boolean getSimInserted() {
        return this.simInserted;
    }

    public final boolean getVowifiState() {
        return this.vowifiState;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.vowifiState) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.shouldShowVolte) * 31, 31, this.shouldShowVowifi), 31, this.simInserted), 31, this.noSim), 31, this.shouldShowVodata), 31, this.shouldShowVo5G);
    }

    public String toString() {
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("OplusIMSModel(shouldShowVolte=", ", shouldShowVowifi=", ", simInserted=", this.shouldShowVolte, this.shouldShowVowifi);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", noSim=", ", shouldShowVodata=", sbM, this.simInserted, this.noSim);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", shouldShowVo5G=", ", vowifiState=", sbM, this.shouldShowVodata, this.shouldShowVo5G);
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, this.vowifiState);
    }

    public /* synthetic */ OplusIMSModel() {
        this(false, false, false, true, false, false, false);
    }
}
