package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class IconSource {
    public final boolean iconTintable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ResourceIconSource extends IconSource {
        public final int iconResId;

        public ResourceIconSource(int i, boolean z) {
            super(z);
            this.iconResId = i;
        }

        public final int getIconResId() {
            return this.iconResId;
        }
    }

    public IconSource(boolean z) {
        this.iconTintable = z;
    }

    public final boolean getIconTintable() {
        return this.iconTintable;
    }
}
