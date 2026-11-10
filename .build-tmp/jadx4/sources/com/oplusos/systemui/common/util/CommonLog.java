package com.oplusos.systemui.common.util;

import android.util.Log;
import androidx.car.app.hardware.common.CarZone$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CommonLog {
    public static final Companion Companion = new Companion();
    public static final String TAG_MODULE = "OSCommon";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void error(String str, String str2, String str3, Throwable th) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] ", str2, "() ");
            sbM.append(str3);
            sbM.append(" , tr: ");
            sbM.append(th.getMessage());
            Log.e(CommonLog.TAG_MODULE, sbM.toString());
        }

        public final void normal(String str, String str2) {
            Log.d(CommonLog.TAG_MODULE, CarZone$.ExternalSyntheticOutline0.m("[", str, "] ", str2, "()"));
        }

        public final void warning(String str, String str2, String str3) {
            ConstraintSet$.ExternalSyntheticOutline0.m(ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] ", str2, "() "), str3, CommonLog.TAG_MODULE);
        }

        public final void normal(String str, String str2, String str3) {
            ExifInterface$.ExternalSyntheticOutline1.m(ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] ", str2, "() "), str3, CommonLog.TAG_MODULE);
        }

        public final void error(String str, String str2, Throwable th) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] ", str2, "() , tr: ");
            sbM.append(th.getMessage());
            Log.e(CommonLog.TAG_MODULE, sbM.toString());
        }

        public final void error(String str, String str2, String str3) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("[", str, "] ", str2, "() ");
            sbM.append(str3);
            Log.e(CommonLog.TAG_MODULE, sbM.toString());
        }
    }
}
