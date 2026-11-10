package com.oplusos.systemui.common.util;

import android.content.Context;
import android.util.TypedValue;
import androidx.slice.widget.RowView$;
import com.oplus.view.OplusSmoothRoundedManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.math.MathKt;
import kotlin.text.Regex$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSmoothRoundUtil {
    private static final OplusRoundParams circleParams;
    private static final OplusRoundParams fullCornerParams;
    private static final Map<Integer, OplusRoundParams> map;
    private static final OplusRoundParams normalParams;
    public static final OplusQsSmoothRoundUtil INSTANCE = new OplusQsSmoothRoundUtil();
    private static final ArrayList<Integer> designRadiusList = CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{8, 10, 12, 16, 20, 24, 28, 32, 36});

    static {
        map = OplusSmoothRoundedManager.isSmoothRadiusOn() ? MapsKt__MapsKt.mutableMapOf(new Pair[]{new Pair(4, new OplusRoundParams(5.0f, 0.0f, 3.0f, 10)), new Pair(8, new OplusRoundParams(11.0f, 0.0f, 3.0f, 10)), new Pair(10, new OplusRoundParams(14.0f, 0.0f, 3.0f, 10)), new Pair(12, new OplusRoundParams(17.0f, 0.0f, 3.0f, 10)), new Pair(16, new OplusRoundParams(23.0f, 0.0f, 3.0f, 10)), new Pair(20, new OplusRoundParams(28.0f, 0.0f, 3.0f, 10)), new Pair(24, new OplusRoundParams(34.0f, 0.0f, 3.0f, 10)), new Pair(28, new OplusRoundParams(39.0f, 0.0f, 3.0f, 10)), new Pair(32, new OplusRoundParams(45.0f, 0.0f, 3.0f, 10)), new Pair(36, new OplusRoundParams(53.0f, 0.0f, 3.0f, 10))}) : MapsKt__MapsKt.mutableMapOf(new Pair[]{new Pair(4, new OplusRoundParams(4.0f, 0.0f, 3.0f, 10)), new Pair(8, new OplusRoundParams(8.0f, 0.0f, 3.0f, 10)), new Pair(10, new OplusRoundParams(10.0f, 0.0f, 3.0f, 10)), new Pair(12, new OplusRoundParams(12.0f, 0.0f, 3.0f, 10)), new Pair(16, new OplusRoundParams(16.0f, 0.0f, 3.0f, 10)), new Pair(20, new OplusRoundParams(20.0f, 0.0f, 3.0f, 10)), new Pair(24, new OplusRoundParams(24.0f, 0.0f, 3.0f, 10)), new Pair(28, new OplusRoundParams(28.0f, 0.0f, 3.0f, 10)), new Pair(32, new OplusRoundParams(32.0f, 0.0f, 3.0f, 10)), new Pair(36, new OplusRoundParams(36.0f, 0.0f, 3.0f, 10))});
        circleParams = new OplusRoundParams(0.0f, 0.0f, 0.0f, 7);
        fullCornerParams = new OplusRoundParams(0.0f, 0.0f, 1.0f, 11);
        normalParams = new OplusRoundParams(0.0f, 0.0f, 0.0f, 10);
    }

    public static final OplusRoundParams getCircleRoundParams() {
        return circleParams;
    }

    public static final OplusRoundParams getFullCornerParams(float f) {
        OplusRoundParams oplusRoundParams = fullCornerParams;
        return oplusRoundParams.copy(oplusRoundParams.radiusDp, f, oplusRoundParams.weight, oplusRoundParams.isCircle);
    }

    public static final OplusRoundParams getNormalRoundParams() {
        return normalParams;
    }

    public static final OplusRoundParams getRoundParams(Context context, float f) {
        int iRoundToInt = MathKt.roundToInt(TypedValue.deriveDimension(1, f, context.getResources().getDisplayMetrics()));
        OplusRoundParams oplusRoundParams = map.get(Integer.valueOf(iRoundToInt));
        if (oplusRoundParams != null) {
            oplusRoundParams.setRadius(TypedValue.applyDimension(1, oplusRoundParams.getRadiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(), context.getResources().getDisplayMetrics()));
        }
        if (oplusRoundParams == null && iRoundToInt < 48 && iRoundToInt > 0) {
            Iterator<T> it = designRadiusList.iterator();
            if (!it.hasNext()) {
                Regex$.ExternalSyntheticBUOutline0.m();
                return null;
            }
            Object next = it.next();
            if (it.hasNext()) {
                int iAbs = Math.abs(iRoundToInt - ((Number) next).intValue());
                do {
                    Object next2 = it.next();
                    int iAbs2 = Math.abs(iRoundToInt - ((Number) next2).intValue());
                    if (iAbs > iAbs2) {
                        next = next2;
                        iAbs = iAbs2;
                    }
                } while (it.hasNext());
            }
            int iIntValue = ((Number) next).intValue();
            Map<Integer, OplusRoundParams> map2 = map;
            OplusRoundParams oplusRoundParams2 = map2.get(Integer.valueOf(iIntValue));
            if (oplusRoundParams2 != null) {
                float radiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = oplusRoundParams2.getRadiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() * (iRoundToInt / iIntValue);
                OplusRoundParams oplusRoundParams3 = new OplusRoundParams(radiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon, RowView$.ExternalSyntheticOutline0.m(context, 1, radiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon), oplusRoundParams2.getWeight(), 8);
                map2.put(Integer.valueOf(iRoundToInt), oplusRoundParams3);
                return oplusRoundParams3;
            }
        }
        return oplusRoundParams;
    }
}
