package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerStateAnimator$1$mDynamicAnimationConsumer$1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ContainerStateAnimator this$0;

    public /* synthetic */ ContainerStateAnimator$1$mDynamicAnimationConsumer$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.animatorSet.add((DynamicAnimation) obj);
                break;
            default:
                this.this$0.animatorSet.add((COUIDynamicAnimation) obj);
                break;
        }
    }
}
