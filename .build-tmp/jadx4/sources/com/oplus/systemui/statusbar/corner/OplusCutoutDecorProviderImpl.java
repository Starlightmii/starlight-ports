package com.oplus.systemui.statusbar.corner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.systemui.decor.BoundDecorProvider;
import com.android.systemui.decor.RoundedCornerResDelegate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCutoutDecorProviderImpl extends BoundDecorProvider {
    public static final Companion Companion = new Companion();
    private static final float[] ROTATION = {0.0f, 270.0f, 180.0f, 90.0f};
    public final int alignedBound;
    private final RoundedCornerResDelegate roundedCornerResDelegate;
    public final int viewId = 2131362905;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusCutoutDecorProviderImpl(int i, RoundedCornerResDelegate roundedCornerResDelegate) {
        this.alignedBound = i;
        this.roundedCornerResDelegate = roundedCornerResDelegate;
    }

    public int getAlignedBound() {
        return this.alignedBound;
    }

    public int getViewId() {
        return this.viewId;
    }

    public View inflateView(Context context, ViewGroup viewGroup, int i, int i2) {
        ImageView imageView = new ImageView(context);
        imageView.setId(getViewId());
        imageView.setImageDrawable(this.roundedCornerResDelegate.getCutoutDrawable());
        imageView.setRotation(ROTATION[i]);
        imageView.setImageTintList(ColorStateList.valueOf(i2));
        Size topRoundedSize = this.roundedCornerResDelegate.getTopRoundedSize();
        viewGroup.addView(imageView, new FrameLayout.LayoutParams(topRoundedSize.getWidth(), topRoundedSize.getHeight(), OplusCutoutDecorProviderImplKt.access$toLayoutGravity(getAlignedBound(), i)));
        return imageView;
    }

    public void onReloadResAndMeasure(View view, int i, int i2, int i3, String str) {
        this.roundedCornerResDelegate.updateDisplayUniqueId(str, Integer.valueOf(i));
        ImageView imageView = (ImageView) view;
        imageView.setImageDrawable(this.roundedCornerResDelegate.getCutoutDrawable());
        imageView.setRotation(ROTATION[i2]);
        imageView.setImageTintList(ColorStateList.valueOf(i3));
        Size topRoundedSize = this.roundedCornerResDelegate.getTopRoundedSize();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = topRoundedSize.getWidth();
        layoutParams.height = topRoundedSize.getHeight();
        layoutParams.gravity = OplusCutoutDecorProviderImplKt.access$toLayoutGravity(getAlignedBound(), i2);
        view.setLayoutParams(layoutParams);
    }
}
