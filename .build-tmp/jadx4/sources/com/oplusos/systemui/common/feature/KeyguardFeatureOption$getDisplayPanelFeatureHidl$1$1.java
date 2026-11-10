package com.oplusos.systemui.common.feature;

import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.jvm.internal.Ref;
import vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardFeatureOption$getDisplayPanelFeatureHidl$1$1 implements IDisplayPanelFeature.getDisplayPanelFeatureValueCallback {
    public /* synthetic */ Ref.ObjectRef $feature;
    public /* synthetic */ int $featureCode;

    public final void onValues(ArrayList arrayList, int i) {
        Ref.ObjectRef objectRef = this.$feature;
        if (i < 0 || arrayList == null) {
            objectRef.element = new int[1];
            return;
        }
        int[] iArr = new int[arrayList.size()];
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = ((Number) arrayList.get(i2)).intValue();
        }
        LogUtil.i$default(LogUtil.TAG_KEYGUARD, "FeatureOption", "getDisplayPanelFeatureHidl featureCode=" + this.$featureCode + ", values=" + iArr, null, 8);
        objectRef.element = iArr;
    }
}
