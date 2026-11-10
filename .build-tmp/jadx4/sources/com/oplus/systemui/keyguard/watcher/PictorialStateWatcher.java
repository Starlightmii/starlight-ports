package com.oplus.systemui.keyguard.watcher;

import com.android.systemui.statusbar.policy.CallbackController;
import com.oplus.systemui.keyguard.wallpaper.BaseWallpaperHelper;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialStateWatcher implements CallbackController {
    public ConcurrentLinkedQueue mObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Inner {
        public static final PictorialStateWatcher INSTANCE;

        static {
            PictorialStateWatcher pictorialStateWatcher = new PictorialStateWatcher();
            pictorialStateWatcher.mObserver = new ConcurrentLinkedQueue();
            INSTANCE = pictorialStateWatcher;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Observer {
        default void onBackToKeyguard() {
        }

        default void onPictorialActivateChange() {
        }

        default void onPictorialAppEnableChanged() {
        }

        default void onPictorialAppReplace() {
        }

        default void onPictorialAutoSwitchChange(boolean z) {
        }

        default void onPictorialEnableSwitchChange(boolean z) {
        }

        default void onPictorialFestivalWallpaperChange(boolean z) {
        }

        default void onPictorialFestivalWallpaperUsageChange(boolean z) {
        }

        default void onPictorialMainSwitchChange(boolean z) {
        }

        default void onPictorialPreloadViewLoad(boolean z) {
        }

        default void onPictorialSwipeSwitchChange() {
        }

        default void onSuperPowerChanged() {
        }

        default void onWallpaperHelperChanged(BaseWallpaperHelper baseWallpaperHelper) {
        }

        default void startClearWallpaper() {
        }
    }

    public static PictorialStateWatcher getInstance() {
        return Inner.INSTANCE;
    }

    public final void addCallback(Observer observer) {
        if (observer == null || this.mObserver.contains(observer)) {
            return;
        }
        this.mObserver.add(observer);
    }

    public final void dispatchPictorialAutoSwitchChange(boolean z) {
        Iterator it = this.mObserver.iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onPictorialAutoSwitchChange(z);
        }
    }

    public final void dispatchPictorialFestivalWallpaperUsageChange(boolean z) {
        Iterator it = this.mObserver.iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onPictorialFestivalWallpaperUsageChange(z);
        }
    }

    public final void dispatchPictorialPreloadViewLoad(boolean z) {
        Iterator it = this.mObserver.iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onPictorialPreloadViewLoad(z);
        }
    }

    public final void dispatchPictorialSwipeSwitchChange() {
        Iterator it = this.mObserver.iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onPictorialSwipeSwitchChange();
        }
    }

    public final void removeCallback(Observer observer) {
        if (observer == null || !this.mObserver.contains(observer)) {
            return;
        }
        this.mObserver.remove(observer);
    }
}
