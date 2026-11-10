package com.oplus.systemui.opthreekey.utils;

import android.util.SparseArray;
import androidx.exifinterface.media.ExifInterface$;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ConvertThreeKeyStateUtil {
    public static final ConvertThreeKeyStateUtil INSTANCE = new ConvertThreeKeyStateUtil();
    private static final String TAG = "ConvertThreeKeyStateUtil";
    private static final SparseArray<Integer> mStealthModeStateMap;

    static {
        ConvertThreeKeyStateUtil$mStealthModeStateMap$1 convertThreeKeyStateUtil$mStealthModeStateMap$1 = new ConvertThreeKeyStateUtil$mStealthModeStateMap$1();
        convertThreeKeyStateUtil$mStealthModeStateMap$1.put(5, 1);
        convertThreeKeyStateUtil$mStealthModeStateMap$1.put(1, 2);
        convertThreeKeyStateUtil$mStealthModeStateMap$1.put(3, 3);
        mStealthModeStateMap = convertThreeKeyStateUtil$mStealthModeStateMap$1;
    }

    public static final int convertThreeKeyStatus(int i) {
        Integer num;
        SparseArray<Integer> sparseArray = mStealthModeStateMap;
        VolumeFeatureOption.INSTANCE.getClass();
        if (!VolumeFeatureOption.isSupportsStealthMode()) {
            sparseArray = null;
        }
        int iIntValue = (sparseArray == null || (num = sparseArray.get(i)) == null) ? i : num.intValue();
        ExifInterface$.ExternalSyntheticOutline1.m("convertThreeKeyStatus: state is ", i, iIntValue, " and map state is ", TAG);
        return iIntValue;
    }

    private static /* synthetic */ void getMStealthModeStateMap$annotations() {
    }
}
