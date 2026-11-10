package com.oplus.systemui.qs.base.widget.recyclerview;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ParallelCOUIRecyclerView$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() throws NoSuchMethodException {
        ParallelCOUIRecyclerView.Companion companion = ParallelCOUIRecyclerView.Companion;
        Method declaredMethod = View.class.getDeclaredMethod("draw", Canvas.class, ViewGroup.class, Long.TYPE);
        if (declaredMethod == null) {
            return null;
        }
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
