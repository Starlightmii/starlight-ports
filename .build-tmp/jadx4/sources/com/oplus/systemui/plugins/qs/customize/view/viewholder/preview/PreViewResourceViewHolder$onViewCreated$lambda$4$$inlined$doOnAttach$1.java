package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.view.View;
import android.widget.ImageView;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplusos.systemui.common.viewholder.GridData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ View $this_doOnAttach;
    public /* synthetic */ PreViewResourceViewHolder this$0;

    public /* synthetic */ PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1(int i) {
        this.$r8$classId = i;
    }

    private final void onViewAttachedToWindow$com$oplus$systemui$plugins$qs$customize$view$viewholder$preview$PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnDetach$1(View view) {
    }

    private final void onViewDetachedFromWindow$com$oplus$systemui$plugins$qs$customize$view$viewholder$preview$PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        ResourcePreviewData resourcePreviewData;
        switch (this.$r8$classId) {
            case 0:
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                PreViewResourceViewHolder preViewResourceViewHolder = this.this$0;
                int i = PreViewResourceViewHolder.$r8$clinit;
                GridData gridData = (GridData) preViewResourceViewHolder.getData();
                boolean zAreEqual = Intrinsics.areEqual((gridData == null || (resourcePreviewData = (ResourcePreviewData) gridData.getData()) == null) ? null : resourcePreviewData.getSpec(), OplusInnerFakeTile.SPEC_DEVICE);
                PreViewResourceViewHolder preViewResourceViewHolder2 = this.this$0;
                if (!zAreEqual) {
                    ImageView imageView = preViewResourceViewHolder2.resourceHolderView;
                    if (imageView == null) {
                        imageView = null;
                    }
                    imageView.setBackground(null);
                } else {
                    preViewResourceViewHolder2.getBgViewBackgroundProxy().onBackgroundAttach();
                }
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.$r8$classId) {
            case 0:
                break;
            default:
                this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
                this.this$0.getBgViewBackgroundProxy().onBackgroundDetach();
                break;
        }
    }
}
