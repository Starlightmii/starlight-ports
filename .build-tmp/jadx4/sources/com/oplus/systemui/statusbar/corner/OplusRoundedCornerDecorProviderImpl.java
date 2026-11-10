package com.oplus.systemui.statusbar.corner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.car.app.model.TimerSpan$;
import com.android.systemui.decor.CornerDecorProvider;
import com.android.systemui.decor.RoundedCornerResDelegate;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRoundedCornerDecorProviderImpl extends CornerDecorProvider {
    public static final Companion Companion = new Companion();
    private static final float[] ROTATION = {0.0f, 270.0f, 180.0f, 90.0f};
    private static final String TAG = "OplusRoundedCornerDecorProviderImpl";
    public final int alignedBound1;
    public final int alignedBound2;
    private final RoundedCornerResDelegate roundedCornerResDelegate;
    public final int viewId;
    public final boolean isTop = getAlignedBounds().contains(1);
    public final boolean isLeft = getAlignedBounds().contains(0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusRoundedCornerDecorProviderImpl(int i, int i2, int i3, RoundedCornerResDelegate roundedCornerResDelegate) {
        this.viewId = i;
        this.alignedBound1 = i2;
        this.alignedBound2 = i3;
        this.roundedCornerResDelegate = roundedCornerResDelegate;
    }

    public int getAlignedBound1() {
        return this.alignedBound1;
    }

    public int getAlignedBound2() {
        return this.alignedBound2;
    }

    public int getViewId() {
        return this.viewId;
    }

    public View inflateView(Context context, ViewGroup viewGroup, int i, int i2) {
        ImageView imageView = new ImageView(context);
        imageView.setId(getViewId());
        initView$2(imageView, i, i2);
        RoundedCornerResDelegate roundedCornerResDelegate = this.roundedCornerResDelegate;
        Size topRoundedSize = this.isTop ? roundedCornerResDelegate.getTopRoundedSize() : roundedCornerResDelegate.getBottomRoundedSize();
        viewGroup.addView(imageView, new FrameLayout.LayoutParams(topRoundedSize.getWidth(), topRoundedSize.getHeight(), OplusRoundedCornerDecorProviderImplKt.access$toLayoutGravity(getAlignedBound2(), i) | OplusRoundedCornerDecorProviderImplKt.access$toLayoutGravity(getAlignedBound1(), i)));
        return imageView;
    }

    public final void initView$2(ImageView imageView, int i, int i2) {
        Drawable bottomLeftRoundedDrawable;
        RoundedCornerResDelegate roundedCornerResDelegate = this.roundedCornerResDelegate;
        boolean z = this.isTop;
        boolean z2 = this.isLeft;
        if (z) {
            bottomLeftRoundedDrawable = z2 ? roundedCornerResDelegate.getTopLeftRoundedDrawable() : roundedCornerResDelegate.getTopRightRoundedDrawable();
        } else {
            bottomLeftRoundedDrawable = z2 ? roundedCornerResDelegate.getBottomLeftRoundedDrawable() : roundedCornerResDelegate.getBottomRightRoundedDrawable();
        }
        imageView.setImageDrawable(bottomLeftRoundedDrawable);
        imageView.setRotation(ROTATION[i]);
        imageView.setImageTintList(ColorStateList.valueOf(i2));
    }

    public void onReloadResAndMeasure(View view, int i, int i2, int i3, String str) {
        this.roundedCornerResDelegate.updateDisplayUniqueId(str, Integer.valueOf(i));
        ImageView imageView = (ImageView) view;
        initView$2(imageView, i2, i3);
        RoundedCornerResDelegate roundedCornerResDelegate = this.roundedCornerResDelegate;
        boolean z = this.isTop;
        Size topRoundedSize = z ? roundedCornerResDelegate.getTopRoundedSize() : roundedCornerResDelegate.getBottomRoundedSize();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            int alignedBound1 = getAlignedBound1();
            int alignedBound2 = getAlignedBound2();
            StringBuilder sb = new StringBuilder("onReloadResAndMeasure: ");
            sb.append(str);
            sb.append(", ");
            sb.append(view);
            sb.append(" isTop: ");
            sb.append(z);
            sb.append(", layoutSize: ");
            sb.append(topRoundedSize);
            sb.append(", (");
            Log.d("SystemUi--Statusbar", "OplusRoundedCornerDecorProviderImpl-->".concat(TimerSpan$.ExternalSyntheticOutline0.m(alignedBound1, alignedBound2, ", ", ")", sb)));
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = topRoundedSize.getWidth();
        layoutParams.height = topRoundedSize.getHeight();
        layoutParams.gravity = OplusRoundedCornerDecorProviderImplKt.access$toLayoutGravity(getAlignedBound2(), i2) | OplusRoundedCornerDecorProviderImplKt.access$toLayoutGravity(getAlignedBound1(), i2);
        view.setLayoutParams(layoutParams);
    }
}
