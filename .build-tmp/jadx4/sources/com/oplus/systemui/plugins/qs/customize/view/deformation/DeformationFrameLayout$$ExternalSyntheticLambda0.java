package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.widget.ImageView;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeformationFrameLayout$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ DeformationFrameLayout f$0;

    public /* synthetic */ DeformationFrameLayout$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        DeformationFrameLayout deformationFrameLayout = this.f$0;
        switch (i) {
            case 0:
                return DeformationFrameLayout.m166$r8$lambda$zpA7cGlsITLfLSWLUtyOOSDVM(deformationFrameLayout);
            case 1:
                return DeformationFrameLayout.$r8$lambda$eH2lPouLPBpSlyrez5R3hzy4Mes(deformationFrameLayout);
            case 2:
                DeformationFrameLayout.Companion companion = DeformationFrameLayout.Companion;
                return (ImageView) deformationFrameLayout.requireViewById(2131364884);
            case 3:
                DeformationFrameLayout.Companion companion2 = DeformationFrameLayout.Companion;
                return (ImageView) deformationFrameLayout.requireViewById(2131362896);
            case 4:
                DeformationFrameLayout.Companion companion3 = DeformationFrameLayout.Companion;
                DragBarView dragBarView = (DragBarView) deformationFrameLayout.requireViewById(2131362945);
                DeformationFrameLayout.PressDragBarTouchListener pressDragBarTouchListener = new DeformationFrameLayout.PressDragBarTouchListener();
                pressDragBarTouchListener.this$0 = deformationFrameLayout;
                VarHandle.storeStoreFence();
                dragBarView.setOnTouchListener(pressDragBarTouchListener);
                return dragBarView;
            default:
                DeformationFrameLayout.Companion companion4 = DeformationFrameLayout.Companion;
                return (QsLabelView) deformationFrameLayout.requireViewById(2131365664);
        }
    }
}
