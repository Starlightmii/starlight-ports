package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import com.coui.appcompat.animation.COUISpringDragHelper;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DragResizeHelper$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ DragResizeHelper f$0;

    public final Object invoke() {
        DragResizeHelper dragResizeHelper = this.f$0;
        DragResizeHelper.springDragHelper.2.1 r1 = new DragResizeHelper.springDragHelper.2.1();
        r1.this$0 = dragResizeHelper;
        VarHandle.storeStoreFence();
        COUISpringDragHelper cOUISpringDragHelper = new COUISpringDragHelper(r1, new ArrayList(), 0.15f, 0.15f, 0.4f, 0.2f, 20.0f, 20.0f, 2000.0f, 8.0f);
        cOUISpringDragHelper.setCurveRatio(0.55f);
        return cOUISpringDragHelper;
    }
}
