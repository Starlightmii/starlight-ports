package com.oplus.systemui.plugins.qs.addtile;

import com.android.launcher3.icons.IconProvider$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AddTileComponent$endListener$1 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ AddTileComponent this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        AddTileComponent addTileComponent = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, "AddTileComponent", IconProvider$.ExternalSyntheticOutline0.m("[onAnimationEnd]canceled=", ", isAddingMode=", " ", z, addTileComponent.isAddingMode), null, 8);
        if (!addTileComponent.isAddingMode) {
            addTileComponent.addPanelContainer.setVisibility(4);
            addTileComponent.addPanelContainer.setTranslationY(0.0f);
        }
        if (z) {
            return;
        }
        cOUIDynamicAnimation.removeEndListener(this);
    }
}
