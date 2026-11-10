package com.oplus.systemui.qs.customize;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.qs.base.customizer.IQSCustomizer$EditType;
import com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter;
import com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter;
import com.oplus.systemui.qs.tileimpl.OplusQSIconViewImpl;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileAdapter extends OplusQSPagerDragAdapter {
    private final IQSCustomizer$EditType editType;
    private Context mContext;
    private final String mTag;

    public OplusTileAdapter(Context context, IQSCustomizer$EditType iQSCustomizer$EditType) {
        super(context, iQSCustomizer$EditType);
        this.mContext = context;
        this.editType = iQSCustomizer$EditType;
        this.mTag = iQSCustomizer$EditType.name();
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter
    public void onBind(OplusQSBaseDragAdapter.BaseHolder baseHolder) {
        Holder holder = baseHolder instanceof Holder ? (Holder) baseHolder : null;
        if (holder != null) {
            holder.setHolderTileCallback(getMEditModeCallback());
        }
    }

    public OplusQSBaseDragAdapter.BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(2131559508, viewGroup, false);
        context.getClass();
        OplusCustomizeTileViewImpl oplusCustomizeTileViewImpl = new OplusCustomizeTileViewImpl(context, new OplusQSIconViewImpl(context));
        oplusCustomizeTileViewImpl.setEditType(this.editType);
        frameLayout.addView(oplusCustomizeTileViewImpl);
        ImageView imageView = new ImageView(context);
        imageView.setVisibility(0);
        imageView.setId(2131364405);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(2131171604);
        int dimensionPixelSize2 = (KeyguardUtils.Companion.isSimpleMode(this.mContext) || OplusQSDisplayModeUtils.isBigDisplayMode(context)) ? this.mContext.getResources().getDimensionPixelSize(2131171613) : this.mContext.getResources().getDimensionPixelSize(2131171605);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        if (this.editType != IQSCustomizer$EditType.HIGHLIGHT) {
            OplusQSPagerDragAdapter.Companion.getClass();
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                layoutParams.gravity = 51;
            } else {
                layoutParams.gravity = 53;
            }
            layoutParams.setMarginsRelative(0, 0, dimensionPixelSize2, 0);
            int dimensionPixelSize3 = this.mContext.getResources().getDimensionPixelSize(2131172137);
            oplusCustomizeTileViewImpl.getIconWithBackground().setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize3, dimensionPixelSize3));
            oplusCustomizeTileViewImpl.getBackgroundView().setLayoutParams(new FrameLayout.LayoutParams(dimensionPixelSize3, dimensionPixelSize3, 17));
        } else {
            oplusCustomizeTileViewImpl.getIconWithBackground().setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            oplusCustomizeTileViewImpl.getBackgroundView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
            OplusQSPagerDragAdapter.Companion.getClass();
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                layoutParams.gravity = 51;
            } else {
                layoutParams.gravity = 53;
            }
            layoutParams.setMarginsRelative(0, 0, 0, 0);
        }
        oplusCustomizeTileViewImpl.getIconWithBackground().addView(imageView, layoutParams);
        return new Holder(frameLayout, this.mTag, this.editType);
    }

    @Override // com.oplus.systemui.qs.base.customizer.OplusQSPagerDragAdapter, com.oplus.systemui.qs.base.customizer.OplusQSBaseDragAdapter
    public final void setTileVisible(int i, boolean z) {
        super.setTileVisible(i, z);
        QSTileQueryHelper.OplusTileInfo tile = getTile(i);
        if (tile == null) {
            return;
        }
        String strName = this.editType.name();
        if (z) {
            QsStatisticUtil.getEditTitleData().setAreaAfter(strName);
            QsStatisticUtil.getEditTitleData().setPositionAfter(i);
        } else {
            QsStatisticUtil.getEditTitleData().setAreaBefore(strName);
            QsStatisticUtil.getEditTitleData().setPositionBefore(i);
        }
        QsStatisticUtil.getEditTitleData().setType(String.valueOf(tile.getSpec()));
    }
}
