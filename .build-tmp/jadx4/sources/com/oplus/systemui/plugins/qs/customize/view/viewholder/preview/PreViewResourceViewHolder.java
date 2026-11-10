package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.widget.OplusPreviewTileViewInfoProvider;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.QsViewBackgroundProxy;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PreViewResourceViewHolder extends BasePreviewViewHolder {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Lazy bgViewBackgroundProxy$delegate;
    private final Lazy bgViewInfoProvider$delegate;
    public ImageView resourceHolderView;

    public PreViewResourceViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, 2131559365, viewGroup, tileEditViewModel);
        PreViewResourceViewHolder$$ExternalSyntheticLambda0 preViewResourceViewHolder$$ExternalSyntheticLambda0 = new PreViewResourceViewHolder$$ExternalSyntheticLambda0(0);
        preViewResourceViewHolder$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.bgViewInfoProvider$delegate = LazyKt__LazyJVMKt.lazy(preViewResourceViewHolder$$ExternalSyntheticLambda0);
        PreViewResourceViewHolder$$ExternalSyntheticLambda0 preViewResourceViewHolder$$ExternalSyntheticLambda1 = new PreViewResourceViewHolder$$ExternalSyntheticLambda0(1);
        preViewResourceViewHolder$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.bgViewBackgroundProxy$delegate = LazyKt__LazyJVMKt.lazy(preViewResourceViewHolder$$ExternalSyntheticLambda1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QsViewBackgroundProxy getBgViewBackgroundProxy() {
        return (QsViewBackgroundProxy) this.bgViewBackgroundProxy$delegate.getValue();
    }

    private final OplusPreviewTileViewInfoProvider getBgViewInfoProvider() {
        return (OplusPreviewTileViewInfoProvider) this.bgViewInfoProvider$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        super.bindData(gridData);
        ImageView imageView = this.resourceHolderView;
        if (imageView == null) {
            imageView = null;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        BasePreviewViewHolder.Companion companion = BasePreviewViewHolder.Companion;
        Context context = getContext();
        SpanSize size = gridData.getSize();
        boolean z = gridData.isEnabled;
        layoutParams.height = companion.calculateCapsulePx(context, size.getRowSpan());
        layoutParams.width = companion.calculateCapsulePx(getContext(), gridData.getSize().getColSpan());
        ImageView imageView2 = this.resourceHolderView;
        if (imageView2 == null) {
            imageView2 = null;
        }
        imageView2.setLayoutParams(layoutParams);
        ImageView imageView3 = this.resourceHolderView;
        if (imageView3 == null) {
            imageView3 = null;
        }
        imageView3.setImageDrawable(((ResourcePreviewData) gridData.getData()).getDrawable());
        boolean zAreEqual = Intrinsics.areEqual(((ResourcePreviewData) gridData.getData()).getSpec(), OplusInnerFakeTile.SPEC_DEVICE);
        ImageView imageView4 = this.resourceHolderView;
        if (zAreEqual) {
            if (imageView4 == null) {
                imageView4 = null;
            }
            imageView4.setColorFilter(QsColorUtil.QS_ICON_INACTIVE_DARK_COLOR);
            if (QsColorUtil.isAddDialogLowGaussianColor(getContext()) && !OplusDarkModeUtil.isNightMode(getContext())) {
                ImageView imageView5 = this.resourceHolderView;
                if (imageView5 == null) {
                    imageView5 = null;
                }
                imageView5.setColorFilter(QsColorUtil.QS_ICON_SEP_ADD_DIALOG_LIGHT_LOW_COLOR);
            }
        } else {
            if (imageView4 == null) {
                imageView4 = null;
            }
            imageView4.clearColorFilter();
        }
        ImageView imageView6 = this.resourceHolderView;
        if (imageView6 == null) {
            imageView6 = null;
        }
        imageView6.setEnabled(z);
        ImageView imageView7 = this.resourceHolderView;
        if (imageView7 == null) {
            imageView7 = null;
        }
        imageView7.setAlpha(z ? 1.0f : 0.4f);
        if (Intrinsics.areEqual(((ResourcePreviewData) gridData.getData()).getSpec(), OplusInnerFakeTile.SPEC_DEVICE)) {
            getBgViewBackgroundProxy().refreshViewBackground();
            return;
        }
        ImageView imageView8 = this.resourceHolderView;
        if (imageView8 == null) {
            imageView8 = null;
        }
        imageView8.setBackground(null);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewViewHolder, com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        ResourcePreviewData resourcePreviewData;
        super.onViewCreated();
        ImageView imageView = (ImageView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131364795);
        this.resourceHolderView = imageView;
        if (imageView == null) {
            imageView = null;
        }
        PressFeedbackHelper.attachPressFeedback(((RecyclerView.ViewHolder) this).itemView, imageView);
        if (imageView.isAttachedToWindow()) {
            GridData gridData = (GridData) getData();
            if (Intrinsics.areEqual((gridData == null || (resourcePreviewData = (ResourcePreviewData) gridData.getData()) == null) ? null : resourcePreviewData.getSpec(), OplusInnerFakeTile.SPEC_DEVICE)) {
                getBgViewBackgroundProxy().onBackgroundAttach();
            } else {
                ImageView imageView2 = this.resourceHolderView;
                if (imageView2 == null) {
                    imageView2 = null;
                }
                imageView2.setBackground(null);
            }
        } else {
            PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1 preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1 = new PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1(0);
            preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1.$this_doOnAttach = imageView;
            preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1.this$0 = this;
            VarHandle.storeStoreFence();
            imageView.addOnAttachStateChangeListener(preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1);
        }
        if (!imageView.isAttachedToWindow()) {
            getBgViewBackgroundProxy().onBackgroundDetach();
            return;
        }
        PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1 preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$2 = new PreViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$1(1);
        preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$2.$this_doOnAttach = imageView;
        preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$2.this$0 = this;
        VarHandle.storeStoreFence();
        imageView.addOnAttachStateChangeListener(preViewResourceViewHolder$onViewCreated$lambda$4$$inlined$doOnAttach$2);
    }
}
