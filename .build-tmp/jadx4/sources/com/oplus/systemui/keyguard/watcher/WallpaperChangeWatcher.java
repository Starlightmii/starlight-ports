package com.oplus.systemui.keyguard.watcher;

import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.statusbar.policy.CallbackController;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WallpaperChangeWatcher implements CallbackController {
    public ConcurrentLinkedQueue mObserver;
    public int mPreWallpaperType;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Inner {
        public static final WallpaperChangeWatcher INSTANCE;

        static {
            WallpaperChangeWatcher wallpaperChangeWatcher = new WallpaperChangeWatcher();
            wallpaperChangeWatcher.mObserver = new ConcurrentLinkedQueue();
            wallpaperChangeWatcher.mPreWallpaperType = -1;
            INSTANCE = wallpaperChangeWatcher;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Observer {
        default void onWallpaperChange(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        }

        default void onWallpaperTypeChange(int i) {
        }
    }

    public static WallpaperChangeWatcher getInstance() {
        return Inner.INSTANCE;
    }

    public final void addCallback(Observer observer) {
        if (observer == null || this.mObserver.contains(observer)) {
            return;
        }
        this.mObserver.add(observer);
    }

    public final void removeCallback(Observer observer) {
        if (observer == null || !this.mObserver.contains(observer)) {
            return;
        }
        this.mObserver.remove(observer);
    }
}
