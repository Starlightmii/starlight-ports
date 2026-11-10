package com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator;

import android.view.ViewGroup;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IDeformableView {
    void executeSizeTransformAnimation(SpanSize spanSize, Function1 function1);

    IDeformationAnimationController getController();

    ViewGroup getHostView();

    SpanSize getSpanSize();

    void sizeAnimContinue(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Function1 function1);
}
