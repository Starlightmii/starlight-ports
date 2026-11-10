package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.pm.PackageManager;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialFactory {
    public WeakReference mMessageViewHelperWeak = null;
    public WeakReference mCursorHelperWeak = null;
    public WeakReference mPreloadHelperWeak = null;
    public WeakReference mLoaderHelperWeak = null;
    public WeakReference mPictorialBuriedHelperWeak = null;
    public WeakReference mPictorialMediatorHelperWeak = null;

    public PictorialFactory() {
        KgdLogUtil.d("PictorialFactory", getClass().getSimpleName().concat(" start"));
    }

    public void checkIsSupportSwipeLeft(PackageManager packageManager, String str, String str2) {
    }

    public void destory() {
    }

    public PictorialBottomViewHelper getPictorialBottomViewHelper() {
        WeakReference weakReference = this.mMessageViewHelperWeak;
        PictorialBottomViewHelper pictorialBottomViewHelper = weakReference != null ? (PictorialBottomViewHelper) weakReference.get() : null;
        if (pictorialBottomViewHelper != null) {
            return pictorialBottomViewHelper;
        }
        PictorialBottomViewHelper pictorialBottomViewHelperNewPictorialBottomViewHelper = newPictorialBottomViewHelper();
        this.mMessageViewHelperWeak = new WeakReference(pictorialBottomViewHelperNewPictorialBottomViewHelper);
        return pictorialBottomViewHelperNewPictorialBottomViewHelper;
    }

    public PictorialBuriedHelper getPictorialBuriedHelper() {
        WeakReference weakReference = this.mPictorialBuriedHelperWeak;
        PictorialBuriedHelper pictorialBuriedHelper = weakReference != null ? (PictorialBuriedHelper) weakReference.get() : null;
        if (pictorialBuriedHelper != null) {
            return pictorialBuriedHelper;
        }
        PictorialBuriedHelper pictorialBuriedHelperNewPictorialBuriedHelper = newPictorialBuriedHelper();
        this.mPictorialBuriedHelperWeak = new WeakReference(pictorialBuriedHelperNewPictorialBuriedHelper);
        return pictorialBuriedHelperNewPictorialBuriedHelper;
    }

    public PictorialCursorHelper getPictorialCursorHelper() {
        WeakReference weakReference = this.mCursorHelperWeak;
        PictorialCursorHelper pictorialCursorHelper = weakReference != null ? (PictorialCursorHelper) weakReference.get() : null;
        if (pictorialCursorHelper != null) {
            return pictorialCursorHelper;
        }
        PictorialCursorHelper pictorialCursorHelperNewPictorialCursorHelper = newPictorialCursorHelper();
        this.mCursorHelperWeak = new WeakReference(pictorialCursorHelperNewPictorialCursorHelper);
        return pictorialCursorHelperNewPictorialCursorHelper;
    }

    public PictorialLoader getPictorialLoader() {
        WeakReference weakReference = this.mLoaderHelperWeak;
        PictorialLoader pictorialLoader = weakReference != null ? (PictorialLoader) weakReference.get() : null;
        if (pictorialLoader != null) {
            return pictorialLoader;
        }
        PictorialLoader pictorialLoaderNewPictorialLoader = newPictorialLoader();
        this.mLoaderHelperWeak = new WeakReference(pictorialLoaderNewPictorialLoader);
        return pictorialLoaderNewPictorialLoader;
    }

    public PictorialMediatorHelper getPictorialMediatorHelper() {
        WeakReference weakReference = this.mPictorialMediatorHelperWeak;
        PictorialMediatorHelper pictorialMediatorHelper = weakReference != null ? (PictorialMediatorHelper) weakReference.get() : null;
        if (pictorialMediatorHelper != null) {
            return pictorialMediatorHelper;
        }
        PictorialMediatorHelper pictorialMediatorHelperNewPictorialMediatorHelper = newPictorialMediatorHelper();
        this.mPictorialMediatorHelperWeak = new WeakReference(pictorialMediatorHelperNewPictorialMediatorHelper);
        return pictorialMediatorHelperNewPictorialMediatorHelper;
    }

    public PictorialPreloadHelper getPictorialPreloadHelper() {
        WeakReference weakReference = this.mPreloadHelperWeak;
        PictorialPreloadHelper pictorialPreloadHelper = weakReference != null ? (PictorialPreloadHelper) weakReference.get() : null;
        if (pictorialPreloadHelper != null) {
            return pictorialPreloadHelper;
        }
        PictorialPreloadHelper pictorialPreloadHelperNewPictorialPreloadHelper = newPictorialPreloadHelper();
        this.mPreloadHelperWeak = new WeakReference(pictorialPreloadHelperNewPictorialPreloadHelper);
        return pictorialPreloadHelperNewPictorialPreloadHelper;
    }

    public abstract PictorialBottomViewHelper newPictorialBottomViewHelper();

    public abstract PictorialBuriedHelper newPictorialBuriedHelper();

    public abstract PictorialCursorHelper newPictorialCursorHelper();

    public abstract PictorialLoader newPictorialLoader();

    public abstract PictorialMediatorHelper newPictorialMediatorHelper();

    public abstract PictorialPreloadHelper newPictorialPreloadHelper();

    public void registerFingerprintTipsShowCallback() {
    }
}
