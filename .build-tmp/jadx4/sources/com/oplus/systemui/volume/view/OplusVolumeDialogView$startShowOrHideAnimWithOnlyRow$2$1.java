package com.oplus.systemui.volume.view;

import android.view.View;
import android.view.ViewGroup;
import com.oplus.systemui.volume.utils.OplusVolumeFrameRateUtilKt;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplus.systemui.volume.utils.VolumePropertyAnimationUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1 implements Runnable {
    public /* synthetic */ View $it;
    public /* synthetic */ int $reason;
    public /* synthetic */ OplusVolumeRow $row;
    public /* synthetic */ Long $sideEnterGeneration;
    public /* synthetic */ boolean $toShow;
    public /* synthetic */ float $translationX;
    public /* synthetic */ OplusVolumeDialogView this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeDialogView$startShowOrHideAnimWithOnlyRow$2$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ View $it;
        public /* synthetic */ OplusVolumeRow $row;
        public /* synthetic */ Long $sideEnterGeneration;
        public /* synthetic */ boolean $toShow;
        public /* synthetic */ OplusVolumeDialogView this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.mIsRowParentAnimRunning = false;
            this.$row.setTouchable(true);
            OplusVolumeFrameRateUtilKt.endFrameRateElevate(this.$it, 20501);
            if (this.$toShow) {
                this.this$0.notifySideEnterAnimationEnded(this.$sideEnterGeneration);
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.blurHostHelper.applyVolumeBarBackgroundFromCache(this.$row, this.$it);
        VolumePropertyAnimationUtil.setPivotYBottom(this.$it);
        OplusVolumeDialogView oplusVolumeDialogView = this.this$0;
        View view = this.$it;
        boolean z = this.$toShow;
        long j = z ? VolumeAnimUtil.DURATION_ONE_ROW_SHOW : VolumeAnimUtil.DURATION_ROW_HIDE;
        float f = this.$translationX;
        int i = this.$reason;
        OplusVolumeRow oplusVolumeRow = this.$row;
        Runnable runnable = new 1();
        ((1) runnable).this$0 = oplusVolumeDialogView;
        ((1) runnable).$row = oplusVolumeRow;
        ((1) runnable).$toShow = z;
        ((1) runnable).$it = view;
        VarHandle.storeStoreFence();
        Long l = this.$sideEnterGeneration;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = oplusVolumeDialogView;
        anonymousClass2.$row = oplusVolumeRow;
        anonymousClass2.$it = view;
        anonymousClass2.$toShow = z;
        anonymousClass2.$sideEnterGeneration = l;
        VarHandle.storeStoreFence();
        oplusVolumeDialogView.doShowOrHideAnimate(view, j, 0L, f, z, i, runnable, anonymousClass2, true);
        ViewGroup mVerticalRowIconParent = this.this$0.getMVerticalRowIconParent();
        if (mVerticalRowIconParent != null) {
            OplusVolumeDialogView oplusVolumeDialogView2 = this.this$0;
            boolean z2 = this.$toShow;
            float f2 = this.$translationX;
            int i2 = this.$reason;
            VolumePropertyAnimationUtil.setPivotYTop(mVerticalRowIconParent);
            long j2 = z2 ? VolumeAnimUtil.DURATION_ONE_ROW_SHOW : VolumeAnimUtil.DURATION_ROW_HIDE;
            OplusVolumeDialogView.showOrHideAnimation.1.3.1.1 r11 = new OplusVolumeDialogView.showOrHideAnimation.1.3.1.1(1);
            r11.$toShow = z2;
            r11.$row = oplusVolumeDialogView2;
            VarHandle.storeStoreFence();
            oplusVolumeDialogView2.doShowOrHideAnimate(mVerticalRowIconParent, j2, 0L, f2, z2, i2, null, r11, false);
        }
    }
}
