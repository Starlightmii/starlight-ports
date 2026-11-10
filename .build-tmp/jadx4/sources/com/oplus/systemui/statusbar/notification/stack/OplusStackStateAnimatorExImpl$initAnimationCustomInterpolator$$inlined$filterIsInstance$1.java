package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1 INSTANCE$1 = new OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1(1);
    public static final OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1 INSTANCE = new OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1(0);

    public /* synthetic */ OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                break;
        }
        return Boolean.valueOf(obj instanceof ExpandableView);
    }
}
