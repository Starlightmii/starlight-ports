package com.oplus.systemui.qs.base.springtransition;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageViewColorFilterTarget implements IIntTarget {
    private final WeakReference<ImageView> imageViewWeakRef;
    private final PorterDuff.Mode porterDuffMode;

    public ImageViewColorFilterTarget(ImageView imageView, PorterDuff.Mode mode) {
        this.porterDuffMode = mode;
        this.imageViewWeakRef = new WeakReference<>(imageView);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public int get() {
        ImageView imageView = this.imageViewWeakRef.get();
        ColorFilter colorFilter = imageView != null ? imageView.getColorFilter() : null;
        PorterDuffColorFilter porterDuffColorFilter = colorFilter instanceof PorterDuffColorFilter ? (PorterDuffColorFilter) colorFilter : null;
        if (porterDuffColorFilter != null) {
            return porterDuffColorFilter.getColor();
        }
        return 0;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.IIntTarget
    public void set(int i) {
        ImageView imageView = this.imageViewWeakRef.get();
        if (imageView != null) {
            imageView.setColorFilter(i, this.porterDuffMode);
        }
    }
}
