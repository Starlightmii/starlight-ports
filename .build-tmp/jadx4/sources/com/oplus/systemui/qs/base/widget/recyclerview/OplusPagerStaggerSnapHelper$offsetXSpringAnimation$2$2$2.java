package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import androidx.compose.ui.spatial.RectList$;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPagerStaggerSnapHelper this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        int[] iArrCurrScrollOffset;
        RecyclerView recyclerView;
        Integer numValueOf;
        int[] iArrCurrScrollOffset2;
        switch (this.$r8$classId) {
            case 0:
                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper = this.this$0;
                RecyclerView recyclerView2 = oplusPagerStaggerSnapHelper.attachedRecyclerView;
                RecyclerView.LayoutManager layoutManager = recyclerView2 != null ? recyclerView2.getLayoutManager() : null;
                IPagerSnapCallback iPagerSnapCallback = layoutManager instanceof IPagerSnapCallback ? (IPagerSnapCallback) layoutManager : null;
                if (iPagerSnapCallback == null || (iArrCurrScrollOffset = iPagerSnapCallback.currScrollOffset()) == null) {
                    iArrCurrScrollOffset = new int[2];
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    int i = oplusPagerStaggerSnapHelper.offsetX;
                    int i2 = iArrCurrScrollOffset[0];
                    int i3 = iArrCurrScrollOffset[1];
                    RecyclerView recyclerView3 = oplusPagerStaggerSnapHelper.attachedRecyclerView;
                    numValueOf = recyclerView3 != null ? Integer.valueOf(recyclerView3.getScrollState()) : null;
                    StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[offsetXSpringAnimation] onAnimationEnd(", z, ", ", f, ", ");
                    ChildHelper$.ExternalSyntheticOutline0.m(f2, i, "), offsetX=", ", scrollOffset=[", sbM);
                    RectList$.ExternalSyntheticOutline0.m(i2, i3, ", ", "] scrollState= ", sbM);
                    sbM.append(numValueOf);
                    Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->".concat(sbM.toString()));
                }
                oplusPagerStaggerSnapHelper.offsetX = 0;
                RecyclerView recyclerView4 = oplusPagerStaggerSnapHelper.attachedRecyclerView;
                if (recyclerView4 != null && recyclerView4.getScrollState() == 2) {
                    oplusPagerStaggerSnapHelper.setScrollState(0);
                    break;
                } else if (!z && (recyclerView = oplusPagerStaggerSnapHelper.attachedRecyclerView) != null && recyclerView.getScrollState() == 0) {
                    oplusPagerStaggerSnapHelper.scrollToCurrPageCenter();
                    break;
                }
                break;
            default:
                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper2 = this.this$0;
                RecyclerView recyclerView5 = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                RecyclerView.LayoutManager layoutManager2 = recyclerView5 != null ? recyclerView5.getLayoutManager() : null;
                IPagerSnapCallback iPagerSnapCallback2 = layoutManager2 instanceof IPagerSnapCallback ? (IPagerSnapCallback) layoutManager2 : null;
                if (iPagerSnapCallback2 == null || (iArrCurrScrollOffset2 = iPagerSnapCallback2.currScrollOffset()) == null) {
                    iArrCurrScrollOffset2 = new int[2];
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    int i4 = oplusPagerStaggerSnapHelper2.offsetX;
                    int i5 = iArrCurrScrollOffset2[0];
                    int i6 = iArrCurrScrollOffset2[1];
                    RecyclerView recyclerView6 = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                    numValueOf = recyclerView6 != null ? Integer.valueOf(recyclerView6.getScrollState()) : null;
                    StringBuilder sbM2 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[offsetYSpringAnimation] onAnimationEnd(", z, ", ", f, ", ");
                    ChildHelper$.ExternalSyntheticOutline0.m(f2, i4, "), offsetX=", ", scrollOffset=[", sbM2);
                    RectList$.ExternalSyntheticOutline0.m(i5, i6, ", ", "] scrollState= ", sbM2);
                    sbM2.append(numValueOf);
                    Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->".concat(sbM2.toString()));
                }
                oplusPagerStaggerSnapHelper2.offsetY = 0;
                RecyclerView recyclerView7 = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                if (recyclerView7 != null && recyclerView7.getScrollState() == 2) {
                    oplusPagerStaggerSnapHelper2.setScrollState(0);
                    break;
                }
                break;
        }
    }
}
