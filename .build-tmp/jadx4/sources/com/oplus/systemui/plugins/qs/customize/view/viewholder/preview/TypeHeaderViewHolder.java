package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.viewholder.BaseViewHolder;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TypeHeaderViewHolder extends BaseViewHolder {
    public TextView headerTv;

    public TypeHeaderViewHolder(Context context, ViewGroup viewGroup) {
        super(context, 2131559324, viewGroup, null);
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(String str) {
        super.bindData((Object) str);
        TextView textView = this.headerTv;
        if (textView == null) {
            textView = null;
        }
        textView.setText(str);
        boolean zIsAddDialogLowGaussianColor = QsColorUtil.isAddDialogLowGaussianColor(getContext());
        TextView textView2 = this.headerTv;
        if (zIsAddDialogLowGaussianColor) {
            (textView2 != null ? textView2 : null).setTextColor(getContext().getColorStateList(2131103190));
        } else {
            (textView2 != null ? textView2 : null).setTextColor(getContext().getColorStateList(2131103191));
        }
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public final void onViewCreated() {
        this.headerTv = (TextView) ((RecyclerView.ViewHolder) this).itemView.requireViewById(2131361989);
    }
}
