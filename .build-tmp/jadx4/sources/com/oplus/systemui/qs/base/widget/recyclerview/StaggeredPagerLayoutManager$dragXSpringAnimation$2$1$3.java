package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3 implements COUIDynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ StaggeredPagerLayoutManager this$0;

    public /* synthetic */ StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$3(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.this$0;
                String str = staggeredPagerLayoutManager.TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    int scrollState = staggeredPagerLayoutManager.getRecyclerView().getScrollState();
                    StringBuilder sbM2 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[dragXSpringAnimation] onAnimationEnd(", z, ", ", f, ", ");
                    sbM2.append(f2);
                    sbM2.append("),srcollState= ");
                    sbM2.append(scrollState);
                    sbM.append(sbM2.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                    break;
                }
                break;
            default:
                StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = this.this$0;
                String str2 = staggeredPagerLayoutManager2.TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
                    int scrollState2 = staggeredPagerLayoutManager2.getRecyclerView().getScrollState();
                    StringBuilder sbM4 = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[dragYSpringAnimation] onAnimationEnd(", z, ", ", f, ", ");
                    sbM4.append(f2);
                    sbM4.append("),srcollState= ");
                    sbM4.append(scrollState2);
                    sbM3.append(sbM4.toString());
                    Log.d("SystemUi--QuickSettings", sbM3.toString());
                    break;
                }
                break;
        }
    }
}
