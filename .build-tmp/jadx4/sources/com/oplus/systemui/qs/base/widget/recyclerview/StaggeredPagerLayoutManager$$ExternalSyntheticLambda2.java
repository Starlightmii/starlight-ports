package com.oplus.systemui.qs.base.widget.recyclerview;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class StaggeredPagerLayoutManager$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ StaggeredPagerLayoutManager f$0;

    public /* synthetic */ StaggeredPagerLayoutManager$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.f$0;
        switch (i) {
            case 0:
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                COUISpringForce cOUISpringForce = new COUISpringForce();
                cOUISpringForce.setBounce(0.0f);
                cOUISpringForce.setResponse(0.1f);
                cOUISpringAnimation.setSpring(cOUISpringForce);
                int i2 = 0;
                StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2 staggeredPagerLayoutManager$dragXSpringAnimation$2$1$2 = new StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2(i2);
                staggeredPagerLayoutManager$dragXSpringAnimation$2$1$2.this$0 = staggeredPagerLayoutManager;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(staggeredPagerLayoutManager$dragXSpringAnimation$2$1$2);
                StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3 staggeredPagerLayoutManager$dragXSpringAnimation$2$1$3 = new StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3(i2);
                staggeredPagerLayoutManager$dragXSpringAnimation$2$1$3.this$0 = staggeredPagerLayoutManager;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(staggeredPagerLayoutManager$dragXSpringAnimation$2$1$3);
                return cOUISpringAnimation;
            case 1:
                COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
                COUISpringForce cOUISpringForce2 = new COUISpringForce();
                cOUISpringForce2.setBounce(0.0f);
                cOUISpringForce2.setResponse(0.1f);
                cOUISpringAnimation2.setSpring(cOUISpringForce2);
                int i3 = 1;
                StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2 staggeredPagerLayoutManager$dragXSpringAnimation$2$1$4 = new StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2(i3);
                staggeredPagerLayoutManager$dragXSpringAnimation$2$1$4.this$0 = staggeredPagerLayoutManager;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addUpdateListener(staggeredPagerLayoutManager$dragXSpringAnimation$2$1$4);
                StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3 staggeredPagerLayoutManager$dragXSpringAnimation$2$1$5 = new StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3(i3);
                staggeredPagerLayoutManager$dragXSpringAnimation$2$1$5.this$0 = staggeredPagerLayoutManager;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addEndListener(staggeredPagerLayoutManager$dragXSpringAnimation$2$1$5);
                return cOUISpringAnimation2;
            default:
                return StaggeredPagerLayoutManager.$r8$lambda$lg6CJsN1bnMnz0UXV67Wh1CaoTw(staggeredPagerLayoutManager);
        }
    }
}
