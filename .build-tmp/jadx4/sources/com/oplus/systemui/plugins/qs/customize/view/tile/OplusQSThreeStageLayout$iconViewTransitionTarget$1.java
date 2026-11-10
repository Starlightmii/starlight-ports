package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.res.ColorStateList;
import android.widget.ImageView;
import com.oplus.systemui.qs.base.springtransition.IIntTarget;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLayout$iconViewTransitionTarget$1 implements IIntTarget {
    public /* synthetic */ OplusQSThreeStageLayout this$0;

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public int get() {
        OplusQSThreeStageLayout oplusQSThreeStageLayout = this.this$0;
        ColorStateList imageTintList = ((ImageView) oplusQSThreeStageLayout.iconViews.get(oplusQSThreeStageLayout.selectedIndex)).getImageTintList();
        if (imageTintList != null) {
            return imageTintList.getDefaultColor();
        }
        return 0;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public void set(int i) {
        OplusQSThreeStageLayout oplusQSThreeStageLayout = this.this$0;
        ColorStateList imageTintList = ((ImageView) oplusQSThreeStageLayout.iconViews.get(oplusQSThreeStageLayout.selectedIndex)).getImageTintList();
        if (imageTintList == null || imageTintList.getDefaultColor() != i) {
            ((ImageView) oplusQSThreeStageLayout.iconViews.get(oplusQSThreeStageLayout.selectedIndex)).setImageTintList(ColorStateList.valueOf(i));
        }
    }
}
