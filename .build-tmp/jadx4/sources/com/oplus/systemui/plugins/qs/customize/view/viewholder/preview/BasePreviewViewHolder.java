package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.customize.view.tile.TextSizeConfig;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import com.oplusos.systemui.common.viewholder.GridViewHolder;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BasePreviewViewHolder extends GridViewHolder {
    public static final Companion Companion = new Companion();
    private TextView labelTv;
    private Function1 tileClickListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int calculateCapsulePx(Context context, int i) {
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            return (int) (AddTileCalculator.INSTANCE.getScale() * (((i - 1) * companion.lockDpi(context, 2131171520)) + companion.lockDpi(context, 2131171515)));
        }
    }

    public BasePreviewViewHolder(Context context, int i, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, i, viewGroup, tileEditViewModel);
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void bindData(GridData gridData) {
        CharSequence text;
        StringBuilder sb;
        super.bindData((Object) gridData);
        Object data = gridData.getData();
        boolean z = gridData.isEnabled;
        CharSequence secondaryLabel = ((BasePreviewData) data).getSecondaryLabel();
        CharSequence label = ((BasePreviewData) gridData.getData()).getLabel();
        if (secondaryLabel != null && secondaryLabel.length() > 0) {
            TextView textView = this.labelTv;
            if (textView == null || textView.getLayoutDirection() != 1) {
                sb = new StringBuilder();
                sb.append((Object) label);
                sb.append("-");
                sb.append((Object) secondaryLabel);
            } else {
                sb = new StringBuilder();
                sb.append((Object) secondaryLabel);
                sb.append("-");
                sb.append((Object) label);
            }
            label = sb.toString();
        }
        TextView textView2 = this.labelTv;
        if (textView2 != null) {
            textView2.setText(label);
        }
        TextView textView3 = this.labelTv;
        if (textView3 != null) {
            textView3.setAlpha(z ? 1.0f : 0.4f);
        }
        TextView textView4 = this.labelTv;
        if (textView4 != null) {
            textView4.setEnabled(z);
        }
        boolean zIsAddDialogLowGaussianColor = QsColorUtil.isAddDialogLowGaussianColor(getContext());
        TextView textView5 = this.labelTv;
        if (zIsAddDialogLowGaussianColor) {
            if (textView5 != null) {
                textView5.setTextColor(getContext().getColorStateList(2131103190));
            }
        } else if (textView5 != null) {
            textView5.setTextColor(getContext().getColorStateList(2131103191));
        }
        View view = ((RecyclerView.ViewHolder) this).itemView;
        Context context = getContext();
        TextView textView6 = this.labelTv;
        if (textView6 == null || (text = textView6.getText()) == null) {
            text = "";
        }
        view.setContentDescription(context.getString(2131955187, text, ""));
    }

    public final TextView getLabelTv() {
        return this.labelTv;
    }

    public final Function1 getTileClickListener() {
        return this.tileClickListener;
    }

    @Override // com.oplusos.systemui.common.viewholder.BaseViewHolder
    public void onViewCreated() {
        TextView textView = (TextView) ((RecyclerView.ViewHolder) this).itemView.findViewById(2131364790);
        this.labelTv = textView;
        if (textView != null) {
            CellCalculatorManager.Companion companion = CellCalculatorManager.Companion;
            int iLockDpi = companion.lockDpi(getContext(), 2131171518);
            int iLockDpi2 = companion.lockDpi(getContext(), 2131171517);
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            if (marginLayoutParams != null) {
                marginLayoutParams.setMargins(iLockDpi2, iLockDpi, iLockDpi2, iLockDpi);
                textView.setLayoutParams(marginLayoutParams);
            }
            TextSizeConfig.Companion companion2 = TextSizeConfig.Companion;
            textView.setLineHeight(companion2.getInstance(getContext()).getPreviewConfig().getTextLineHeight());
            textView.setLineSpacing(0.0f, 1.0f);
            textView.setTextSize(0, companion2.getInstance(getContext()).getPreviewConfig().getTextSize());
        }
        View view = ((RecyclerView.ViewHolder) this).itemView;
        onViewCreated.2 r1 = new onViewCreated.2();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        view.setOnClickListener(r1);
    }

    public final void setLabelTv(TextView textView) {
        this.labelTv = textView;
    }

    public final void setTileClickListener(Function1 function1) {
        this.tileClickListener = function1;
    }
}
