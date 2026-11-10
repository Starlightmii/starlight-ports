package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.view.ViewGroup;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLeftAnimationProperty {
    private final Map<ViewGroup, Integer> capsuleLeftValues = new LinkedHashMap();
    private ViewGroup currHostView;
    public float lostFloat;

    public final ViewGroup getCurrHostView() {
        return this.currHostView;
    }

    public final void offsetLeftAndRight(ViewGroup viewGroup, float f) {
        Integer num;
        ViewGroup viewGroup2;
        if (viewGroup == null || (num = this.capsuleLeftValues.get(viewGroup)) == null) {
            return;
        }
        int iIntValue = num.intValue();
        if (-1.0f == f || (viewGroup2 = this.currHostView) == null) {
            return;
        }
        float left = ((iIntValue - viewGroup2.getLeft()) * f) + viewGroup2.getLeft();
        float f2 = this.lostFloat;
        int i = (int) (left + f2);
        this.lostFloat = (left + f2) - i;
        viewGroup2.offsetLeftAndRight(i - viewGroup2.getLeft());
    }

    public final void prepare(Map<ViewGroup, Integer> map) {
        this.lostFloat = 0.0f;
        this.capsuleLeftValues.clear();
        this.capsuleLeftValues.putAll(map);
    }

    public final void setCurrHostView(ViewGroup viewGroup) {
        this.currHostView = viewGroup;
    }
}
