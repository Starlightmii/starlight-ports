package com.oplus.systemui.statusbar.pipeline.mobile.data.model;

import androidx.core.provider.FontRequest$;
import androidx.exifinterface.media.ExifInterface$;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SimStateModel {
    private final boolean[] simForbidden;
    private final boolean[] simInserted;
    private final boolean[] simLoaded;
    private final boolean[] simStateIMSI;

    public /* synthetic */ SimStateModel() {
        this(new boolean[]{false, false}, new boolean[]{false, false}, new boolean[]{false, false}, new boolean[]{false, false});
    }

    public final boolean[] component1() {
        return this.simInserted;
    }

    public final boolean[] component2() {
        return this.simForbidden;
    }

    public final boolean[] component3() {
        return this.simStateIMSI;
    }

    public final boolean[] component4() {
        return this.simLoaded;
    }

    public final SimStateModel copy(boolean[] zArr, boolean[] zArr2, boolean[] zArr3, boolean[] zArr4) {
        return new SimStateModel(zArr, zArr2, zArr3, zArr4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!SimStateModel.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        SimStateModel simStateModel = (SimStateModel) obj;
        return Arrays.equals(this.simInserted, simStateModel.simInserted) && Arrays.equals(this.simForbidden, simStateModel.simForbidden) && Arrays.equals(this.simStateIMSI, simStateModel.simStateIMSI) && Arrays.equals(this.simLoaded, simStateModel.simLoaded);
    }

    public final boolean[] getSimForbidden() {
        return this.simForbidden;
    }

    public final boolean[] getSimInserted() {
        return this.simInserted;
    }

    public final boolean[] getSimLoaded() {
        return this.simLoaded;
    }

    public final boolean[] getSimStateIMSI() {
        return this.simStateIMSI;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.simLoaded) + ((Arrays.hashCode(this.simStateIMSI) + ((Arrays.hashCode(this.simForbidden) + (Arrays.hashCode(this.simInserted) * 31)) * 31)) * 31);
    }

    public final boolean isAnySimInserted() {
        boolean[] zArr = this.simInserted;
        return zArr[0] || zArr[1];
    }

    public final boolean isNoSim() {
        boolean[] zArr = this.simInserted;
        return (!zArr[0] || this.simForbidden[0]) && (!zArr[1] || this.simForbidden[1]);
    }

    public String toString() {
        String string = Arrays.toString(this.simInserted);
        String string2 = Arrays.toString(this.simForbidden);
        return FontRequest$.ExternalSyntheticOutline0.m(ExifInterface$.ExternalSyntheticOutline1.m("SimStateModel(simInserted=", string, ", simForbidden=", string2, ", simStateIMSI="), Arrays.toString(this.simStateIMSI), ", simLoaded=", Arrays.toString(this.simLoaded), ")");
    }

    public SimStateModel(boolean[] zArr, boolean[] zArr2, boolean[] zArr3, boolean[] zArr4) {
        this.simInserted = zArr;
        this.simForbidden = zArr2;
        this.simStateIMSI = zArr3;
        this.simLoaded = zArr4;
    }
}
