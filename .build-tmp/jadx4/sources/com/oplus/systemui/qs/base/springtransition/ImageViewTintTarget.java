package com.oplus.systemui.qs.base.springtransition;

import android.content.res.ColorStateList;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageViewTintTarget implements IIntTarget {
    private final WeakReference<ImageView> imageViewWeakRef;

    public ImageViewTintTarget(ImageView imageView) {
        this.imageViewWeakRef = new WeakReference<>(imageView);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public int get() {
        ColorStateList imageTintList;
        ImageView imageView = this.imageViewWeakRef.get();
        if (imageView == null || (imageTintList = imageView.getImageTintList()) == null) {
            return 0;
        }
        return imageTintList.getColorForState(imageView.getDrawableState(), imageTintList.getDefaultColor());
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public void set(int i) {
        ImageView imageView = this.imageViewWeakRef.get();
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(i));
        }
    }
}
