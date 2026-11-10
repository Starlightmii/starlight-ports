package com.oplus.systemui.keyguard.watcher;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusKgdImmersiveController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusKgdImmersiveController f$0;

    public /* synthetic */ OplusKgdImmersiveController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        int i2 = 0;
        int i3 = 1;
        OplusKgdImmersiveController oplusKgdImmersiveController = this.f$0;
        switch (i) {
            case 0:
                return OplusKgdImmersiveController.m16$r8$lambda$AiPaDSWlkXjWauBt9aAUN3LJ6w(oplusKgdImmersiveController);
            case 1:
                oplusKgdImmersiveController.onMusicLockEnterAnimationFinished();
                return Unit.INSTANCE;
            case 2:
                return OplusKgdImmersiveController.$r8$lambda$NB0MVb1abYZCHPTyj_3l7wrTDf4(oplusKgdImmersiveController);
            case 3:
                return OplusKgdImmersiveController.$r8$lambda$spPdYTcY2xXC5Jk0VHi4X1tAjXQ(oplusKgdImmersiveController);
            case 4:
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.3f));
                cOUISpringAnimation.setMinimumVisibleChange(0.001f);
                OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1 oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1 = new OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1(i2);
                oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1.this$0 = oplusKgdImmersiveController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addUpdateListener(oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1);
                OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2 oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2 = new OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2(i2);
                oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2.this$0 = oplusKgdImmersiveController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2);
                return cOUISpringAnimation;
            case 5:
                COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatValueHolder());
                cOUISpringAnimation2.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.3f));
                cOUISpringAnimation2.setMinimumVisibleChange(0.001f);
                OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1 oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$3 = new OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1(i3);
                oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$3.this$0 = oplusKgdImmersiveController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addUpdateListener(oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$3);
                OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2 oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$4 = new OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2(i3);
                oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$4.this$0 = oplusKgdImmersiveController;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addEndListener(oplusKgdImmersiveController$immersiveAlphaAnimation$2$1$4);
                return cOUISpringAnimation2;
            case 6:
                oplusKgdImmersiveController.releaseMaskViewSurface();
                oplusKgdImmersiveController.clearCropBitmap(false);
                return Unit.INSTANCE;
            default:
                oplusKgdImmersiveController.clearStaleMusicImmersiveState(true);
                return Unit.INSTANCE;
        }
    }
}
