package com.oplus.systemui.statusbar;

import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CommandQueueLogger$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        switch (this.$r8$classId) {
            case 0:
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                String str1 = logMessage.getStr1();
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onSystemBarAttributesChanged displayId=", int1, int2, " appearance=", " packageName=");
                sbM.append(str1);
                return sbM.toString();
            case 1:
                return TextFieldBuffer$.ExternalSyntheticOutline0.m(logMessage.getInt1(), logMessage.getInt2(), "abortTransient displayId=", " types=");
            case 2:
                return KeyguardLogger$.ExternalSyntheticOutline0.m("appTransitionPending displayId=", logMessage.getInt1(), " forced=", logMessage.getBool1());
            case 3:
                int int3 = logMessage.getInt1();
                long long1 = logMessage.getLong1();
                long long2 = logMessage.getLong2();
                boolean bool1 = logMessage.getBool1();
                StringBuilder sb = new StringBuilder("appTransitionStarting displayId=");
                sb.append(int3);
                sb.append(" startTime=");
                sb.append(long1);
                DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sb, " duration=", long2, " forced=");
                sb.append(bool1);
                return sb.toString();
            case 4:
                int int4 = logMessage.getInt1();
                int int5 = logMessage.getInt2();
                boolean bool2 = logMessage.getBool1();
                StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("showTransient displayId=", int4, int5, " types=", " isGestureOnSystemBar=");
                sbM2.append(bool2);
                return sbM2.toString();
            default:
                return ListPopupWindow$.ExternalSyntheticOutline0.m(logMessage.getInt1(), "appTransitionCancelled displayId=");
        }
    }
}
