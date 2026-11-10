package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialLoader {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IPictorialLoaderCallback {
    }

    public abstract void clearWallpapers();

    public abstract void disable();

    public abstract PictorialWallpaperInfo getCurrentLockScreenWallpaperInfo();

    public abstract PictorialWallpaperInfo getCurrentPreloadWallpaperInfo();

    public abstract Drawable getPreloadWallpaper(Context context);

    public abstract Drawable getWallpaper(Context context);

    public abstract boolean hasAlreadyFetchFromSlidePage();

    public abstract boolean isEnableLockScreen(Context context);

    public abstract boolean isWallpaperLoaded();

    public abstract void onWallpaperChange(List list);

    public abstract void releaseCurrentDrawable();

    public abstract void releaseDrawableAndInfo();

    public abstract void releaseDrawableCache();

    public abstract void releasePreloadCache();

    public abstract void removePreloadWallpaperChangeListener(PictorialPreloadView pictorialPreloadView);

    public abstract void setEnable(boolean z);

    public abstract void setHasAlreadyFetchFromSlidePage(boolean z);

    public abstract void setPictorialLoaderCallback(IPictorialLoaderCallback iPictorialLoaderCallback);

    public abstract void setPreloadWallpaperChangeListener(PictorialWallpaperHelper.IPictorialPreloadListener iPictorialPreloadListener);

    public abstract void showWallpaperAddRotaDialog(Context context, Drawable drawable);

    public abstract void updateCurrentPreloadWallpaper(Context context);

    public abstract void updateCurrentWallpaper(Context context);

    public abstract void updateNextWallpaper(Context context);

    public abstract void updateWelcomePreloadWallpaper(Context context);
}
