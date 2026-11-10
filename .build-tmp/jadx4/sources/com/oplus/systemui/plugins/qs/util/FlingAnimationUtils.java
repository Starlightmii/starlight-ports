package com.oplus.systemui.plugins.qs.util;

import android.util.DisplayMetrics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlingAnimationUtils {
    public float mHighVelocityPxPerSecond;
    public float mMinVelocityPxPerSecond;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class Builder {
        public final DisplayMetrics mDisplayMetrics;

        public Builder(DisplayMetrics displayMetrics) {
            this.mDisplayMetrics = displayMetrics;
        }

        public Builder setMaxLengthSeconds(float f) {
            return this;
        }

        public Builder setSpeedUpFactor(float f) {
            return this;
        }

        public Builder setX2(float f) {
            return this;
        }

        public Builder setY2(float f) {
            return this;
        }
    }

    public float getHighVelocityPxPerSecond() {
        return this.mHighVelocityPxPerSecond;
    }

    public float getMinVelocityPxPerSecond() {
        return this.mMinVelocityPxPerSecond;
    }
}
