package com.oplus.systemui.utils;

import android.graphics.Matrix;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MatrixUtil {
    public static final MatrixUtil INSTANCE = new MatrixUtil();
    private static final String TAG = "MatrixUtil";

    public static final Matrix stringToMatrix(String str, int i, int i2) {
        Object obj;
        Object obj2;
        if (str == null || str.length() == 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "MatrixUtil-->stringToMatrix matrixString is null or empty, return null");
            }
            return null;
        }
        if (StringsKt__StringsJVMKt.startsWith(str, "Matrix{", false) || StringsKt__StringsKt.contains(str, "Matrix{", false)) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            try {
                List listSplit$default = StringsKt__StringsKt.split$default(StringsKt__StringsJVMKt.replace(StringsKt__StringsJVMKt.replace(StringsKt__StringsJVMKt.replace(str.substring(8, str.length() - 2), "[", OplusPreventModeData.SEPARATOR, false), "]", "", false), " ", "", false), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
                Matrix matrix = new Matrix();
                List list = listSplit$default;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(Float.valueOf(Float.parseFloat((String) it.next())));
                }
                matrix.setValues(CollectionsKt___CollectionsKt.toFloatArray(arrayList));
                objectRef.element = matrix;
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("oldStringToMatrix parse matrix fail ", th2.getMessage(), "MatrixUtil-->", "SystemUi--Keyguard");
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Matrix matrix2 = (Matrix) objectRef.element;
                Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("oldStringToMatrix origin matrix str=", str, " matrix= ", matrix2 != null ? matrix2.toShortString() : null, "MatrixUtil-->"));
            }
            return (Matrix) objectRef.element;
        }
        List listSplit$default2 = StringsKt__StringsKt.split$default(str, new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
        if (listSplit$default2 == null || listSplit$default2.size() != 9) {
            return null;
        }
        try {
            List list2 = listSplit$default2;
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Float.valueOf(Float.parseFloat(StringsKt__StringsKt.trim((String) it2.next()).toString())));
            }
            float[] floatArray = CollectionsKt___CollectionsKt.toFloatArray(arrayList2);
            Matrix matrix3 = new Matrix();
            matrix3.setValues(floatArray);
            int i3 = ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP;
            float f = (i > 0 ? i : 480) / 480.0f;
            if (i2 > 0) {
                i3 = i2;
            }
            matrix3.postScale(f, i3 / 480.0f);
            obj2 = Result.constructor-impl(matrix3);
        } catch (Throwable th3) {
            obj2 = Result.constructor-impl(ResultKt.createFailure(th3));
        }
        Throwable th4 = Result.exceptionOrNull-impl(obj2);
        if (th4 != null) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                return null;
            }
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("newStringToMatrix parse matrix numbers fail: ", th4.getMessage(), ", matrixString=", str, "MatrixUtil-->"));
            return null;
        }
        Matrix matrix4 = (Matrix) obj2;
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            return matrix4;
        }
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "newStringToMatrix originMatrix=", str, " matrixWidth= ", " matrixHeight = ");
        sbM.append(i2);
        sbM.append(", resultMatrix=");
        sbM.append(matrix4);
        Log.d("SystemUi--Keyguard", "MatrixUtil-->".concat(sbM.toString()));
        return matrix4;
    }
}
