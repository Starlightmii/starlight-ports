package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.customize.KeyguardCustomizeManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$$ExternalSyntheticLambda1;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialLoader$$ExternalSyntheticLambda0;
import com.oplus.systemui.utils.OplusImageUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.BrandUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPictorialLoader extends PictorialLoader {
    public static final Object LIST_LOCK = new Object();
    public PictorialLoader.IPictorialLoaderCallback mCallback;
    public volatile Drawable mCurrentLockScreenDrawable;
    public volatile PictorialWallpaperInfo mCurrentLockScreenWallpaperInfo;
    public volatile Drawable mCurrentPreloadDrawable;
    public volatile PictorialWallpaperInfo mCurrentPreloadWallpaperInfo;
    public boolean mHasAlreadyFetchFromSlidePage;
    public PictorialMediator mPictorialMediator;
    public volatile Drawable mPreloadWelcomeDrawable;
    public volatile List mCoverInfoList = new ArrayList();
    public Pair mLockScreenNextDrawableCache = new Pair(null, null);
    public boolean mEnable = true;
    public final List mPreloadChangeListeners = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SetWallpaperRunnable implements Runnable {
        public PictorialLoader.IPictorialLoaderCallback mCallback;
        public boolean mIsLockScreen;
        public OplusPictorialLoader mPictorialLoader;
        public List mPreloadChangeListeners;
        public PictorialWallpaperLoader.WallpaperLoadRequest mRequest;
        public Drawable mResult;

        @Override // java.lang.Runnable
        public final void run() {
            PictorialWallpaperInfo pictorialWallpaperInfo = this.mRequest.getPictorialWallpaperInfo();
            StringBuilder sb = new StringBuilder("asyncUpdateWallpaper load success , requestInfo = ");
            sb.append(pictorialWallpaperInfo.getFileUri().toSafeString());
            sb.append(", needUpdateWallpaper = true, isLockScreen = ");
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialLoader-oplus", sb, this.mIsLockScreen);
            boolean z = this.mIsLockScreen;
            OplusPictorialLoader oplusPictorialLoader = this.mPictorialLoader;
            if (z) {
                oplusPictorialLoader.mCurrentLockScreenWallpaperInfo = pictorialWallpaperInfo;
                this.mPictorialLoader.mCurrentLockScreenDrawable = this.mResult;
                if (!this.mPictorialLoader.mEnable) {
                    KgdLogUtil.w("PictorialLoader-oplus", "asyncUpdateWallpaper mEnable is false and is in lock screen, key is" + this.mRequest.getCacheKey());
                    return;
                } else {
                    PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback = this.mCallback;
                    if (iPictorialLoaderCallback != null) {
                        ((PictorialWallpaperHelper$$ExternalSyntheticLambda1) iPictorialLoaderCallback).onTypeChange();
                    }
                }
            } else {
                oplusPictorialLoader.mCurrentPreloadWallpaperInfo = pictorialWallpaperInfo;
                this.mPictorialLoader.mCurrentPreloadDrawable = this.mResult;
                if (!this.mPreloadChangeListeners.isEmpty()) {
                    ((ArrayList) this.mPreloadChangeListeners).forEach(new OplusPictorialLoader$$ExternalSyntheticLambda1(1));
                    this.mPictorialLoader.finishAsyncUpdateCurrentPreloadWallpaper();
                }
            }
            this.mRequest = null;
            this.mPictorialLoader = null;
            this.mPreloadChangeListeners = null;
            this.mResult = null;
        }
    }

    private PictorialMediator getSavePictorialMediator() {
        if (this.mPictorialMediator == null) {
            this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        return this.mPictorialMediator;
    }

    public final void asyncUpdateWallpaper$2(Context context, PictorialWallpaperInfo pictorialWallpaperInfo, boolean z) throws Throwable {
        if (pictorialWallpaperInfo == null) {
            return;
        }
        PictorialWallpaperLoader.WallpaperLoadRequest gauss = new PictorialWallpaperLoader.WallpaperLoadRequest().setPictorialWallpaperInfo(pictorialWallpaperInfo).setUri(pictorialWallpaperInfo.getFileUri()).setGauss(false);
        OplusPictorialLoader$$ExternalSyntheticLambda0 oplusPictorialLoader$$ExternalSyntheticLambda0 = new OplusPictorialLoader$$ExternalSyntheticLambda0();
        oplusPictorialLoader$$ExternalSyntheticLambda0.f$0 = this;
        oplusPictorialLoader$$ExternalSyntheticLambda0.f$1 = z;
        VarHandle.storeStoreFence();
        PictorialWallpaperLoader.getInstance().loadWallpaper(context, gauss, oplusPictorialLoader$$ExternalSyntheticLambda0);
    }

    public final boolean checkCurrentLockScreenDrawable$2(PictorialWallpaperInfo pictorialWallpaperInfo) {
        if (pictorialWallpaperInfo == null) {
            return false;
        }
        String string = pictorialWallpaperInfo.getFileUri().toString();
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        this.mCurrentLockScreenDrawable = pictorialWallpaperInfo.getWallpaperDrawable();
        if (this.mCurrentLockScreenDrawable == null && TextUtils.equals(string, (CharSequence) this.mLockScreenNextDrawableCache.first)) {
            this.mCurrentLockScreenDrawable = (Drawable) this.mLockScreenNextDrawableCache.second;
        }
        return this.mCurrentLockScreenDrawable != null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void clearWallpapers() {
        synchronized (LIST_LOCK) {
            this.mCoverInfoList.clear();
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void disable() {
        Pair pair = this.mLockScreenNextDrawableCache;
        if (pair != null) {
            OplusImageUtils.recycleBitmap((Drawable) pair.second);
        }
        this.mLockScreenNextDrawableCache = new Pair(null, null);
        if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
            KgdLogUtil.i("PictorialLoader-oplus", "user Switched to the sub user clear the mCoverInfoList");
            synchronized (LIST_LOCK) {
                this.mCoverInfoList.clear();
            }
        }
    }

    public final void finishAsyncUpdateCurrentPreloadWallpaper() {
        if (!SmoothTransitionController.isPanoramicAodEnable(getSavePictorialMediator().getContext()) || ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).isKeyguardVisible$1() || this.mCurrentPreloadDrawable == null) {
            return;
        }
        KgdLogUtil.d("PictorialLoader-oplus", "finishAsyncUpdateCurrentPreloadWallpaper recycleBitmap");
        OplusImageUtils.recycleBitmap(this.mCurrentPreloadDrawable);
        this.mCurrentPreloadDrawable = null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public PictorialWallpaperInfo getCurrentLockScreenWallpaperInfo() {
        return this.mCurrentLockScreenWallpaperInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public PictorialWallpaperInfo getCurrentPreloadWallpaperInfo() {
        return this.mCurrentPreloadWallpaperInfo;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final Drawable getPreloadWallpaper(Context context) throws Throwable {
        if (this.mCurrentPreloadDrawable != null) {
            return this.mCurrentPreloadDrawable;
        }
        if (this.mCurrentPreloadWallpaperInfo == null) {
            KgdLogUtil.i("PictorialLoader-oplus", "getPreloadWallpaper mCurrentPreloadWallpaperInfo is null");
            return null;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo = this.mCurrentPreloadWallpaperInfo;
        if (pictorialWallpaperInfo != null && !TextUtils.isEmpty(pictorialWallpaperInfo.getFileUri().toString())) {
            this.mCurrentPreloadDrawable = pictorialWallpaperInfo.getWallpaperDrawable();
            if (this.mCurrentPreloadDrawable != null) {
                return this.mCurrentPreloadDrawable;
            }
        }
        asyncUpdateWallpaper$2(context, this.mCurrentPreloadWallpaperInfo, false);
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final Drawable getWallpaper(Context context) throws Throwable {
        if (this.mCurrentLockScreenDrawable != null) {
            return this.mCurrentLockScreenDrawable;
        }
        if (this.mCurrentLockScreenWallpaperInfo == null) {
            KgdLogUtil.i("PictorialLoader-oplus", "getWallpaper mCurrentLockScreenWallpaperInfo is null");
            return null;
        }
        if (checkCurrentLockScreenDrawable$2(this.mCurrentLockScreenWallpaperInfo)) {
            KgdLogUtil.i("PictorialLoader-oplus", "getWallpaper sync return drawable");
            return this.mCurrentLockScreenDrawable;
        }
        asyncUpdateWallpaper$2(context, this.mCurrentLockScreenWallpaperInfo, true);
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean hasAlreadyFetchFromSlidePage() {
        return this.mHasAlreadyFetchFromSlidePage;
    }

    public final boolean isEnable() {
        if (this.mPictorialMediator == null) {
            this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        if (!this.mPictorialMediator.isMainSwitchOn() || KeyguardUpdateMonitor.getCurrentUser() != 0) {
            return false;
        }
        this.mPictorialMediator.getClass();
        return PictorialMediator.isUserUnlocked$1() && this.mPictorialMediator.isDeviceProvisioned();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0045  */
    /* JADX WARN: Code duplicated, block: B:20:0x004b  */
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean isEnableLockScreen(Context context) {
        if (this.mPictorialMediator == null) {
            this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        PictorialMediator pictorialMediator = this.mPictorialMediator;
        if (pictorialMediator.mPictorialExist && pictorialMediator.isMainSwitchOn()) {
            if (!this.mPictorialMediator.isAutoPlaySwitchOn()) {
                PictorialMediator pictorialMediator2 = this.mPictorialMediator;
                if ((!pictorialMediator2.mIsSupportPictorialFestivalWallpaper ? false : pictorialMediator2.mIsPictorialFestivalWallpaperReady) && !((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().isConfigPackageAsGalleryClock()) {
                    if (KeyguardUpdateMonitor.getCurrentUser() == 0) {
                        this.mPictorialMediator.getClass();
                        if (!PictorialMediator.isUserUnlocked$1() && this.mPictorialMediator.isDeviceProvisioned() && KeyguardUtils.Companion.isDefaultKeyguardInSettings(context) && !KeyguardCustomizeManager.Companion.getInstance().needDisablePictorial()) {
                            return true;
                        }
                    }
                }
            } else if (KeyguardUpdateMonitor.getCurrentUser() == 0) {
                this.mPictorialMediator.getClass();
                if (!PictorialMediator.isUserUnlocked$1()) {
                }
            }
        }
        return false;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean isWallpaperLoaded() {
        return this.mCurrentLockScreenDrawable != null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void onWallpaperChange(List list) {
        synchronized (LIST_LOCK) {
            try {
                this.mCoverInfoList.clear();
                if (list != null) {
                    this.mCoverInfoList = list;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseCurrentDrawable() {
        KgdLogUtil.d("PictorialLoader-oplus", "releaseCurrentDrawable recycleBitmap" + this.mCurrentLockScreenDrawable);
        OplusImageUtils.recycleBitmap(this.mCurrentLockScreenDrawable);
        this.mCurrentLockScreenDrawable = null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseDrawableAndInfo() {
        OplusImageUtils.recycleBitmap(this.mCurrentLockScreenDrawable);
        OplusImageUtils.recycleBitmap(this.mCurrentPreloadDrawable);
        this.mCurrentLockScreenDrawable = null;
        this.mCurrentPreloadDrawable = null;
        this.mPreloadWelcomeDrawable = null;
        if (this.mCurrentLockScreenWallpaperInfo != null) {
            OplusImageUtils.recycleBitmap(this.mCurrentLockScreenWallpaperInfo.getWallpaperDrawable());
            this.mCurrentLockScreenWallpaperInfo.setWallpaperDrawable(null);
            this.mCurrentLockScreenWallpaperInfo = null;
        }
        if (this.mCurrentPreloadWallpaperInfo != null) {
            OplusImageUtils.recycleBitmap(this.mCurrentPreloadWallpaperInfo.getWallpaperDrawable());
            this.mCurrentPreloadWallpaperInfo.setWallpaperDrawable(null);
            this.mCurrentPreloadWallpaperInfo = null;
        }
        synchronized (LIST_LOCK) {
            this.mCoverInfoList.forEach(new OplusPictorialLoader$$ExternalSyntheticLambda1(0));
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseDrawableCache() {
        Pair pair = this.mLockScreenNextDrawableCache;
        if (pair != null) {
            OplusImageUtils.recycleBitmap((Drawable) pair.second);
        }
        this.mLockScreenNextDrawableCache = new Pair(null, null);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releasePreloadCache() {
        if (this.mPreloadChangeListeners.isEmpty()) {
            return;
        }
        ((ArrayList) this.mPreloadChangeListeners).forEach(new RmPictorialLoader$$ExternalSyntheticLambda0());
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void removePreloadWallpaperChangeListener(PictorialPreloadView pictorialPreloadView) {
        this.mPreloadChangeListeners.remove(pictorialPreloadView);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setEnable(boolean z) {
        this.mEnable = z;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setHasAlreadyFetchFromSlidePage(boolean z) {
        this.mHasAlreadyFetchFromSlidePage = z;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setPictorialLoaderCallback(PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback) {
        this.mCallback = iPictorialLoaderCallback;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setPreloadWallpaperChangeListener(PictorialWallpaperHelper.IPictorialPreloadListener iPictorialPreloadListener) {
        if (this.mPreloadChangeListeners.contains(iPictorialPreloadListener)) {
            return;
        }
        this.mPreloadChangeListeners.add(iPictorialPreloadListener);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void showWallpaperAddRotaDialog(Context context, Drawable drawable) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateCurrentPreloadWallpaper(Context context) throws Throwable {
        updatePreloadWallpaper$2(context, isEnable(), isEnableLockScreen(context));
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateCurrentWallpaper(Context context) throws Throwable {
        PictorialWallpaperInfo pictorialWallpaperInfo;
        int size;
        if (isEnableLockScreen(context)) {
            if (this.mCoverInfoList == null || (size = this.mCoverInfoList.size()) <= 0) {
                pictorialWallpaperInfo = null;
            } else {
                pictorialWallpaperInfo = (PictorialWallpaperInfo) this.mCoverInfoList.get(0);
                if (this.mHasAlreadyFetchFromSlidePage && size > 1) {
                    pictorialWallpaperInfo = (PictorialWallpaperInfo) this.mCoverInfoList.get(1);
                }
            }
            if (pictorialWallpaperInfo != null) {
                if (this.mCurrentLockScreenWallpaperInfo != null) {
                    PictorialWallpaperInfo pictorialWallpaperInfo2 = this.mCurrentLockScreenWallpaperInfo;
                    OplusImageUtils.recycleBitmap(pictorialWallpaperInfo2.getWallpaperDrawable());
                    pictorialWallpaperInfo2.setWallpaperDrawable(null);
                    PictorialWallpaperInfo preloadWallpaper = pictorialWallpaperInfo2.getPreloadWallpaper();
                    if (preloadWallpaper != null) {
                        OplusImageUtils.recycleBitmap(preloadWallpaper.getWallpaperDrawable());
                        preloadWallpaper.setWallpaperDrawable(null);
                    }
                }
                this.mCurrentLockScreenWallpaperInfo = null;
                if (checkCurrentLockScreenDrawable$2(pictorialWallpaperInfo)) {
                    KgdLogUtil.i("PictorialLoader-oplus", "updateLockScreenWallpaper drawable is not null , sync update");
                    this.mCurrentLockScreenWallpaperInfo = pictorialWallpaperInfo;
                    PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback = this.mCallback;
                    if (iPictorialLoaderCallback != null) {
                        ((PictorialWallpaperHelper$$ExternalSyntheticLambda1) iPictorialLoaderCallback).onTypeChange();
                    }
                } else {
                    KgdLogUtil.i("PictorialLoader-oplus", "updateLockScreenWallpaper drawable is null, async update");
                    asyncUpdateWallpaper$2(context, pictorialWallpaperInfo, true);
                }
            }
        } else {
            KgdLogUtil.i("PictorialLoader-oplus", "updateLockScreenWallpaper return, lock screen disable");
        }
        updatePreloadWallpaper$2(context, isEnable(), isEnableLockScreen(context));
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateNextWallpaper(Context context) {
    }

    /* JADX WARN: Code duplicated, block: B:27:0x006f  */
    public final void updatePreloadWallpaper$2(Context context, boolean z, boolean z2) throws Throwable {
        PictorialWallpaperInfo pictorialWallpaperInfo;
        if (z) {
            int i = 1;
            if (this.mCoverInfoList == null || this.mCoverInfoList.isEmpty()) {
                pictorialWallpaperInfo = null;
            } else {
                int size = this.mCoverInfoList.size();
                if (!z2) {
                    int i2 = (!this.mHasAlreadyFetchFromSlidePage || this.mCoverInfoList.size() < 2) ? 0 : 1;
                    PictorialWallpaperInfo pictorialWallpaperInfo2 = (PictorialWallpaperInfo) this.mCoverInfoList.get(i2);
                    if (pictorialWallpaperInfo2 != null) {
                        KgdLogUtil.i("PictorialLoader-oplus", "fetchPreloadWallpaperInfo,index = " + i2);
                        pictorialWallpaperInfo = new PictorialWallpaperInfo(pictorialWallpaperInfo2);
                    } else {
                        pictorialWallpaperInfo = null;
                    }
                } else if (this.mCoverInfoList != null) {
                    PictorialWallpaperInfo pictorialWallpaperInfo3 = (PictorialWallpaperInfo) this.mCoverInfoList.get(0);
                    if (this.mHasAlreadyFetchFromSlidePage && size > 1) {
                        pictorialWallpaperInfo3 = (PictorialWallpaperInfo) this.mCoverInfoList.get(1);
                    }
                    pictorialWallpaperInfo = pictorialWallpaperInfo3.getPreloadWallpaper();
                } else {
                    pictorialWallpaperInfo = null;
                }
            }
            if (pictorialWallpaperInfo == null) {
                return;
            }
            this.mCurrentPreloadWallpaperInfo = null;
            if (!TextUtils.isEmpty(pictorialWallpaperInfo.getFileUri().toString())) {
                this.mCurrentPreloadDrawable = pictorialWallpaperInfo.getWallpaperDrawable();
                if (this.mCurrentPreloadDrawable != null) {
                    KgdLogUtil.i("PictorialLoader-oplus", "updatePreloadWallpaper : drawable is not null , sync update");
                    this.mCurrentPreloadWallpaperInfo = pictorialWallpaperInfo;
                    if (this.mPreloadChangeListeners.isEmpty()) {
                        return;
                    }
                    ((ArrayList) this.mPreloadChangeListeners).forEach(new OplusPictorialLoader$$ExternalSyntheticLambda1(i));
                    return;
                }
            }
            KgdLogUtil.i("PictorialLoader-oplus", "updatePreloadWallpaper : drawable is null, async update");
            asyncUpdateWallpaper$2(context, pictorialWallpaperInfo, false);
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateWelcomePreloadWallpaper(Context context) {
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isOneplus && !getSavePictorialMediator().getCanUpdateWelcomePreloadWallpaper()) {
            KgdLogUtil.i("PictorialLoader-oplus", "updateWelcomePreloadWallpaper : isOnePlus true and mCanUpdateWelcomePreloadWallpaper is false");
            return;
        }
        this.mCurrentPreloadWallpaperInfo = null;
        this.mCurrentPreloadDrawable = this.mPreloadWelcomeDrawable;
        if (this.mCurrentPreloadDrawable == null) {
            KgdLogUtil.i("PictorialLoader-oplus", "updateWelcomePreloadWallpaper : drawable is null, async update");
            PictorialWallpaperLoader.WallpaperLoadRequest gauss = new PictorialWallpaperLoader.WallpaperLoadRequest().setPictorialWallpaperInfo(new PictorialWallpaperInfo()).setGauss(false);
            PictorialWallpaperLoader.WallpaperLoadListener externalSyntheticLambda3 = new OplusPictorialLoader$.ExternalSyntheticLambda3();
            ((OplusPictorialLoader$.ExternalSyntheticLambda3) externalSyntheticLambda3).f$0 = this;
            VarHandle.storeStoreFence();
            PictorialWallpaperLoader.getInstance().loadWallpaperWelcome(context, gauss, externalSyntheticLambda3);
            return;
        }
        KgdLogUtil.i("PictorialLoader-oplus", "updateWelcomePreloadWallpaper : drawable is not null , sync update");
        this.mCurrentPreloadWallpaperInfo = new PictorialWallpaperInfo();
        if (this.mPreloadChangeListeners.isEmpty()) {
            return;
        }
        ((ArrayList) this.mPreloadChangeListeners).forEach(new OplusPictorialLoader$$ExternalSyntheticLambda1(1));
    }
}
