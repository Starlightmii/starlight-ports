package com.oplus.systemui.qs;

import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import com.android.internal.util.UserIcons;
import com.oplus.systemui.qs.widget.CircleDrawable;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterImpl$$ExternalSyntheticLambda4 implements Function0 {
    public /* synthetic */ OplusQSFooterImpl f$0;
    public /* synthetic */ Drawable f$1;

    public final Object invoke() {
        OplusQSFooterImpl oplusQSFooterImpl = this.f$0;
        Drawable drawable = this.f$1;
        Interpolator interpolator = OplusQSFooterImpl.DATE_X_EXPAND;
        CircleDrawable circleDrawable = new CircleDrawable(oplusQSFooterImpl.getResources(), UserIcons.convertToBitmap(drawable));
        ThreadManager threadManager = ThreadManager.getInstance();
        OplusQSFooterImpl$$ExternalSyntheticLambda5 oplusQSFooterImpl$$ExternalSyntheticLambda5 = new OplusQSFooterImpl$$ExternalSyntheticLambda5();
        oplusQSFooterImpl$$ExternalSyntheticLambda5.f$0 = oplusQSFooterImpl;
        oplusQSFooterImpl$$ExternalSyntheticLambda5.f$1 = circleDrawable;
        VarHandle.storeStoreFence();
        threadManager.postInMainThread(oplusQSFooterImpl$$ExternalSyntheticLambda5);
        return Unit.INSTANCE;
    }
}
