package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAnimationFilter extends AnimationFilter {
    public boolean animateHorizontalInset;

    public final void applyCombination(List<AnimationEvent> list) {
        reset();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i);
            combineFilter(list.get(i).getFilter());
        }
    }

    public void combineFilter(AnimationFilter animationFilter) {
        if (animationFilter instanceof OplusAnimationFilter) {
            this.animateHorizontalInset |= ((OplusAnimationFilter) animationFilter).animateHorizontalInset;
        }
        super.combineFilter(animationFilter);
    }

    public final boolean getAnimateHorizontalInset() {
        return this.animateHorizontalInset;
    }

    public final void setAnimateHorizontalInset(boolean z) {
        this.animateHorizontalInset = z;
    }
}
