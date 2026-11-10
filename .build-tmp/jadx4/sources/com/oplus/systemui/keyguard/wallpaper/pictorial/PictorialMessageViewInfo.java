package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.graphics.drawable.Drawable;
import androidx.collection.FloatList$;
import com.google.gson.ExclusionStrategy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialMessageViewInfo {
    public Drawable mEntryDrawable;
    public int mEntryDrawableRes;
    public boolean mEntryShow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SkipStrategy implements ExclusionStrategy {
    }

    public Drawable getEntryDrawable() {
        return this.mEntryDrawable;
    }

    public int getEntryDrawableRes() {
        return this.mEntryDrawableRes;
    }

    public final void setEntryDrawable(Drawable drawable) {
        this.mEntryDrawable = drawable;
    }

    public final void setEntryDrawableRes(int i) {
        this.mEntryDrawableRes = i;
    }

    public final void setEntryShow(boolean z) {
        this.mEntryShow = z;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PictorialMessageViewInfo { , mEntryShow=");
        sb.append(this.mEntryShow);
        sb.append(", mEntryDrawable=");
        sb.append(this.mEntryDrawable);
        sb.append(", mEntryDrawableRes=");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.mEntryDrawableRes, '}');
    }
}
