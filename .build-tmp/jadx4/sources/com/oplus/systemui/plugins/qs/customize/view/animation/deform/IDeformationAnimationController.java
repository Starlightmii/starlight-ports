package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.util.Size;
import android.view.ViewGroup;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IDeformationAnimationController {
    ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4);

    void onFlingStarted(ViewGroup viewGroup, SpanSize spanSize);

    void prepare(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2, boolean z, boolean z2);

    void squeezeOrStretch(int i, int i2, int i3);

    void stop(Function1 function1);

    default void transferFrom(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2) {
    }

    void updateFrame(ComputeResult computeResult);
}
