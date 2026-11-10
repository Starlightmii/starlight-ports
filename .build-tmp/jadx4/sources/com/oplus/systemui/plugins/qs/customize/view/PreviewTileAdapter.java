package com.oplus.systemui.plugins.qs.customize.view;

import android.content.Context;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.PreViewBigTileViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.PreViewResourceViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.PreViewSeekBarViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.PreViewTileViewHolder;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.ResourcePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.SeekBarPreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.TilePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.TypeHeaderViewHolder;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplusos.systemui.common.adapter.BaseRecyclerAdapter;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.BaseViewHolder;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.IViewHolderCreator;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PreviewTileAdapter extends BaseRecyclerAdapter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PreviewTileAdapter";
    private RecyclerView attachedRecyclerView;

    public PreviewTileAdapter(Context context, TileEditViewModel tileEditViewModel, Function1 function1) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$tileClickListener = function1;
        anonymousClass1.viewModel = tileEditViewModel;
        VarHandle.storeStoreFence();
        super(context, anonymousClass1);
    }

    private final void setMaxRecycledViews(RecyclerView recyclerView) {
        RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(1001, 50);
        recycledViewPool.setMaxRecycledViews(1002, 20);
        recycledViewPool.setMaxRecycledViews(1003, 1);
        recycledViewPool.setMaxRecycledViews(1004, 1);
        recycledViewPool.setMaxRecycledViews(1000, 1);
    }

    @Override // com.oplusos.systemui.common.adapter.BaseRecyclerAdapter
    public final int getItemViewType(int i) {
        Object orNull = CollectionsKt___CollectionsKt.getOrNull(getDataList(), i);
        if (orNull == null) {
            return 0;
        }
        if (!(orNull instanceof GridData)) {
            return 1000;
        }
        GridData gridData = (GridData) orNull;
        Object data = gridData.getData();
        if (data instanceof TilePreviewData) {
            return gridData.getSize().getColSpan() == 2 ? 1002 : 1001;
        }
        if (data instanceof SeekBarPreviewData) {
            return 1003;
        }
        return data instanceof ResourcePreviewData ? 1004 : 1001;
    }

    public final SpanSize getSpanSize(int i, int i2) {
        Object orNull = CollectionsKt___CollectionsKt.getOrNull(getDataList(), i);
        if (orNull == null) {
            return SpanCache.INSTANCE.spanSize(1, 1);
        }
        if (!(orNull instanceof GridData)) {
            return SpanCache.INSTANCE.spanSize(i2, 1);
        }
        GridData gridData = (GridData) orNull;
        return SpanCache.INSTANCE.spanSize(gridData.getSize().getColSpan(), gridData.getSize().getRowSpan());
    }

    public final Integer getSpecialSize(int i, Context context) {
        if (getItemViewType(i) == 1000) {
            return Integer.valueOf(context.getResources().getDimensionPixelSize(2131171526));
        }
        return null;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.attachedRecyclerView = recyclerView;
        setMaxRecycledViews(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.attachedRecyclerView = null;
    }

    public final void submitList(List<? extends Object> list) {
        PreviewTileAdapter$submitList$diffResult$1 previewTileAdapter$submitList$diffResult$1 = new PreviewTileAdapter$submitList$diffResult$1();
        previewTileAdapter$submitList$diffResult$1.this$0 = this;
        previewTileAdapter$submitList$diffResult$1.$newData = list;
        VarHandle.storeStoreFence();
        DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(previewTileAdapter$submitList$diffResult$1);
        setDataList(list);
        diffResultCalculateDiff.dispatchUpdatesTo(this);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(list.size(), "submitList newData="), null, 8);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.view.PreviewTileAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IViewHolderCreator {
        public /* synthetic */ Function1 $tileClickListener;
        public TileEditViewModel viewModel;

        @Override // com.oplusos.systemui.common.viewholder.IViewHolderCreator
        public final BaseViewHolder create(Context context, int i, ViewGroup viewGroup) {
            PreViewTileViewHolder typeHeaderViewHolder;
            switch (i) {
                case 1000:
                    typeHeaderViewHolder = new TypeHeaderViewHolder(context, viewGroup);
                    break;
                case 1001:
                    typeHeaderViewHolder = new PreViewTileViewHolder(context, viewGroup, getViewModel());
                    break;
                case 1002:
                    typeHeaderViewHolder = new PreViewBigTileViewHolder(context, viewGroup, getViewModel());
                    break;
                case 1003:
                    typeHeaderViewHolder = new PreViewSeekBarViewHolder(context, viewGroup, getViewModel());
                    break;
                case 1004:
                    typeHeaderViewHolder = new PreViewResourceViewHolder(context, viewGroup, getViewModel());
                    break;
                default:
                    typeHeaderViewHolder = new PreViewTileViewHolder(context, viewGroup, getViewModel());
                    break;
            }
            if (typeHeaderViewHolder instanceof BasePreviewViewHolder) {
                ((BasePreviewViewHolder) typeHeaderViewHolder).setTileClickListener(this.$tileClickListener);
            }
            return typeHeaderViewHolder;
        }

        @Override // com.oplusos.systemui.common.viewholder.IViewHolderCreator
        public TileEditViewModel getViewModel() {
            return this.viewModel;
        }
    }
}
