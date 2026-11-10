package com.oplus.systemui.qs.media;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.oplus.graphics.OplusOutlineAdapter;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1 extends ViewOutlineProvider {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OplusMediaViewPagerAdapter.OplusMediaViewHolder this$0;

    public /* synthetic */ OplusMediaViewPagerAdapter$OplusMediaViewHolder$applyAppIconOutlineProvider$1(OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder, int i) {
        this.$r8$classId = i;
        this.this$0 = oplusMediaViewHolder;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        switch (this.$r8$classId) {
            case 0:
                if (view.getWidth() > 0 && view.getHeight() > 0) {
                    if (this.this$0.appIconOplusOutlineAdapter == null || !Intrinsics.areEqual(this.this$0.appIconOutlineLast, outline)) {
                        this.this$0.appIconOutlineLast = outline;
                        this.this$0.appIconOplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
                    }
                    OplusOutlineAdapter oplusOutlineAdapter = this.this$0.appIconOplusOutlineAdapter;
                    if (oplusOutlineAdapter != null) {
                        int width = view.getWidth();
                        int height = view.getHeight();
                        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.this$0;
                        oplusOutlineAdapter.setSmoothRoundRect(0, 0, width, height, oplusMediaViewHolder.appIconOutlineRadius, oplusMediaViewHolder.appIconOutlineWeight);
                    }
                } else {
                    outline.setEmpty();
                }
                break;
            case 1:
                if (this.this$0.itemViewOplusOutlineAdapter == null || !Intrinsics.areEqual(this.this$0.itemViewOutlineLast, outline)) {
                    this.this$0.itemViewOutlineLast = outline;
                    this.this$0.itemViewOplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
                }
                OplusOutlineAdapter oplusOutlineAdapter2 = this.this$0.itemViewOplusOutlineAdapter;
                if (oplusOutlineAdapter2 != null) {
                    int width2 = view.getWidth();
                    int height2 = view.getHeight();
                    OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder2 = this.this$0;
                    oplusOutlineAdapter2.setSmoothRoundRect(0, 0, width2, height2, oplusMediaViewHolder2.itemViewOutlineRadius, oplusMediaViewHolder2.itemViewOutlineWeight);
                }
                break;
            default:
                if (this.this$0.lightOverlayOplusOutlineAdapter == null || !Intrinsics.areEqual(this.this$0.lightOverlayOutlineLast, outline)) {
                    this.this$0.lightOverlayOutlineLast = outline;
                    this.this$0.lightOverlayOplusOutlineAdapter = new OplusOutlineAdapter(outline, 1);
                }
                OplusOutlineAdapter oplusOutlineAdapter3 = this.this$0.lightOverlayOplusOutlineAdapter;
                if (oplusOutlineAdapter3 != null) {
                    int width3 = view.getWidth();
                    int height3 = view.getHeight();
                    OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder3 = this.this$0;
                    oplusOutlineAdapter3.setSmoothRoundRect(0, 0, width3, height3, oplusMediaViewHolder3.lightOverlayOutlineRadius, oplusMediaViewHolder3.lightOverlayOutlineWeight);
                }
                break;
        }
    }
}
