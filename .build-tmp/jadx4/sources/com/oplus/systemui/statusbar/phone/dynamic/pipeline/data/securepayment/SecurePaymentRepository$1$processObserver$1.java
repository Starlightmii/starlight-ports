package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.securepayment;

import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.statusbar.CommonProcessObserverEx;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecurePaymentRepository$1$processObserver$1 implements CommonProcessObserverEx.ICommonProcessObserver {
    public /* synthetic */ SecurePaymentRepository this$0;

    public final void onProcessDied(int i, int i2, String str) {
        if (str != null) {
            SecurePaymentRepository securePaymentRepository = this.this$0;
            if (str.length() <= 0 || !Intrinsics.areEqual(securePaymentRepository.getSecurePayPkg(), str)) {
                return;
            }
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, "SecurePaymentRepository", TextFieldBuffer$.ExternalSyntheticOutline0.m(i2, i, "com.oplus.securepay died uid:", " pid:"));
            securePaymentRepository.updateDetectionState(1005);
        }
    }
}
