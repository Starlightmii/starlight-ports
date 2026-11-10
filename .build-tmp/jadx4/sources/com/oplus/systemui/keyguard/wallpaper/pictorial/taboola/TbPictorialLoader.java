package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.customize.KeyguardCustomizeManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$$ExternalSyntheticLambda1;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialLoader$$ExternalSyntheticLambda1;
import com.oplus.systemui.utils.OplusImageUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialLoader extends RmPictorialLoader {
    public PictorialLoader.IPictorialLoaderCallback mCallback;
    public List mCoverInfoList;
    public Drawable mCurrentLockScreenDrawable;
    public PictorialWallpaperInfo mCurrentLockScreenWallpaperInfo;
    public Drawable mCurrentPreloadDrawable;
    public PictorialWallpaperInfo mCurrentPreloadWallpaperInfo;
    public boolean mEnable;
    public Pair mLockScreenNextDrawableCache;
    public PictorialWallpaperInfo mNextLockScreenWallpaperInfo;
    public PictorialWallpaperInfo mNextPreloadWallpaperInfo;
    public PictorialMediator mPictorialMediator;
    public Pair mPreloadNextDrawableCache;
    public KeyguardUpdateMonitor mUpdateMonitor;

    public final void asyncUpdateWallpaper$1(Context context, PictorialWallpaperInfo pictorialWallpaperInfo, boolean z) throws Throwable {
        if (pictorialWallpaperInfo == null) {
            return;
        }
        PictorialWallpaperLoader.WallpaperLoadRequest gauss = new PictorialWallpaperLoader.WallpaperLoadRequest().setPictorialWallpaperInfo(pictorialWallpaperInfo).setGauss(false);
        PictorialWallpaperLoader.WallpaperLoadListener externalSyntheticLambda0 = new TbPictorialLoader$.ExternalSyntheticLambda0(1);
        ((TbPictorialLoader$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
        ((TbPictorialLoader$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = z;
        VarHandle.storeStoreFence();
        PictorialWallpaperLoader.getInstance().loadWallpaper(context, gauss, externalSyntheticLambda0);
    }

    public final boolean checkCurrentLockScreenDrawable$1(PictorialWallpaperInfo pictorialWallpaperInfo) {
        if (pictorialWallpaperInfo == null) {
            return false;
        }
        String filePath = pictorialWallpaperInfo.getFilePath();
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        Drawable wallpaperDrawable = pictorialWallpaperInfo.getWallpaperDrawable();
        this.mCurrentLockScreenDrawable = wallpaperDrawable;
        if (wallpaperDrawable == null && TextUtils.equals(filePath, (CharSequence) this.mLockScreenNextDrawableCache.first)) {
            this.mCurrentLockScreenDrawable = (Drawable) this.mLockScreenNextDrawableCache.second;
        }
        KgdLogUtil.i("TbPictorialLoader", "checkCurrentLockScreenDrawable info.getWallpaperDrawable() change:" + this.mCurrentLockScreenDrawable);
        return this.mCurrentLockScreenDrawable != null;
    }

    public final boolean checkCurrentPreloadDrawable$1(PictorialWallpaperInfo pictorialWallpaperInfo) {
        if (pictorialWallpaperInfo == null) {
            return false;
        }
        String filePath = pictorialWallpaperInfo.getFilePath();
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        Drawable wallpaperDrawable = pictorialWallpaperInfo.getWallpaperDrawable();
        this.mCurrentPreloadDrawable = wallpaperDrawable;
        if (wallpaperDrawable == null && TextUtils.equals(filePath, (CharSequence) this.mPreloadNextDrawableCache.first)) {
            this.mCurrentPreloadDrawable = (Drawable) this.mPreloadNextDrawableCache.second;
        }
        return this.mCurrentPreloadDrawable != null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void clearWallpapers() {
        List list = this.mCoverInfoList;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void disable() {
        this.mLockScreenNextDrawableCache = new Pair(null, null);
        this.mPreloadNextDrawableCache = new Pair(null, null);
        if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
            KgdLogUtil.i("TbPictorialLoader", "user Switched to the sub user clear the mCoverInfoList");
            List list = this.mCoverInfoList;
            if (list != null) {
                list.clear();
            }
        }
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
        Drawable drawable = this.mCurrentPreloadDrawable;
        if (drawable != null) {
            return drawable;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo = this.mCurrentPreloadWallpaperInfo;
        if (pictorialWallpaperInfo == null) {
            KgdLogUtil.i("TbPictorialLoader", "getPreloadWallpaper mCurrentPreloadWallpaperInfo is null");
            return null;
        }
        if (checkCurrentPreloadDrawable$1(pictorialWallpaperInfo)) {
            return this.mCurrentPreloadDrawable;
        }
        asyncUpdateWallpaper$1(context, this.mCurrentPreloadWallpaperInfo, false);
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final Drawable getWallpaper(Context context) throws Throwable {
        KgdLogUtil.i("TbPictorialLoader", "start getWallpaper");
        if (this.mCurrentLockScreenDrawable != null) {
            KgdLogUtil.i("TbPictorialLoader", "getWallpaper mCurrentLockScreenDrawable != null mCurrentLockScreenDrawable=" + this.mCurrentLockScreenDrawable);
            return this.mCurrentLockScreenDrawable;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo = this.mCurrentLockScreenWallpaperInfo;
        if (pictorialWallpaperInfo == null) {
            KgdLogUtil.i("TbPictorialLoader", "getWallpaper mCurrentLockScreenWallpaperInfo is null");
        } else {
            if (checkCurrentLockScreenDrawable$1(pictorialWallpaperInfo)) {
                KgdLogUtil.i("TbPictorialLoader", "getWallpaper sync return drawable");
                return this.mCurrentLockScreenDrawable;
            }
            KgdLogUtil.i("TbPictorialLoader", "getWallpaper asyncUpdateWallpaper");
            asyncUpdateWallpaper$1(context, this.mCurrentLockScreenWallpaperInfo, true);
        }
        KgdLogUtil.i("TbPictorialLoader", "getWallpaper  return null");
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean hasAlreadyFetchFromSlidePage() {
        return false;
    }

    public final boolean isEnable() {
        if (this.mPictorialMediator == null) {
            this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        if (!this.mPictorialMediator.isMainSwitchOn() || KeyguardUpdateMonitor.getCurrentUser() != 0) {
            return false;
        }
        this.mPictorialMediator.getClass();
        return PictorialMediator.isUserUnlocked$1() && this.mUpdateMonitor.mDeviceProvisioned;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean isEnableLockScreen(Context context) {
        if (this.mPictorialMediator == null) {
            this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        }
        PictorialMediator pictorialMediator = this.mPictorialMediator;
        if (!pictorialMediator.mPictorialExist || !pictorialMediator.isMainSwitchOn() || !this.mPictorialMediator.isAutoPlaySwitchOn() || KeyguardUpdateMonitor.getCurrentUser() != 0) {
            return false;
        }
        this.mPictorialMediator.getClass();
        return PictorialMediator.isUserUnlocked$1() && this.mUpdateMonitor.mDeviceProvisioned && KeyguardUtils.isDefaultKeyguardInSettings(context) && !KeyguardCustomizeManager.Companion.getInstance().needDisablePictorial();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final boolean isWallpaperLoaded() {
        return this.mCurrentLockScreenDrawable != null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void onWallpaperChange(List list) {
        this.mCoverInfoList.clear();
        this.mCoverInfoList = list;
    }

    public final void preloadNextWallpaper$1(Context context, boolean z) throws Throwable {
        List list;
        boolean zIsEnableLockScreen = isEnableLockScreen(context);
        if (zIsEnableLockScreen || !z) {
            PictorialWallpaperInfo pictorialWallpaperInfo = null;
            if (z) {
                if (zIsEnableLockScreen && (list = this.mCoverInfoList) != null) {
                    int size = list.size();
                    if (size > 1) {
                        pictorialWallpaperInfo = (PictorialWallpaperInfo) this.mCoverInfoList.get(1);
                    } else if (size == 1) {
                        pictorialWallpaperInfo = (PictorialWallpaperInfo) this.mCoverInfoList.get(0);
                    }
                }
                this.mNextLockScreenWallpaperInfo = pictorialWallpaperInfo;
            } else {
                PictorialWallpaperInfo pictorialWallpaperInfo2 = this.mNextLockScreenWallpaperInfo;
                if (pictorialWallpaperInfo2 == null || pictorialWallpaperInfo2.getPreloadWallpaper() == null) {
                    List list2 = this.mCoverInfoList;
                    if (list2 != null && list2.size() > 1) {
                        PictorialWallpaperInfo pictorialWallpaperInfo3 = (PictorialWallpaperInfo) this.mCoverInfoList.get(!zIsEnableLockScreen ? 1 : 0);
                        if (pictorialWallpaperInfo3 != null) {
                            pictorialWallpaperInfo = new PictorialWallpaperInfo(pictorialWallpaperInfo3.getPreloadWallpaper());
                        }
                    }
                } else {
                    pictorialWallpaperInfo = new PictorialWallpaperInfo(this.mNextLockScreenWallpaperInfo.getPreloadWallpaper());
                }
                this.mNextPreloadWallpaperInfo = pictorialWallpaperInfo;
            }
            if (pictorialWallpaperInfo != null) {
                PictorialWallpaperLoader.WallpaperLoadRequest gauss = new PictorialWallpaperLoader.WallpaperLoadRequest().setPictorialWallpaperInfo(new PictorialWallpaperInfo(pictorialWallpaperInfo)).setGauss(false);
                PictorialWallpaperLoader.WallpaperLoadListener externalSyntheticLambda0 = new TbPictorialLoader$.ExternalSyntheticLambda0(0);
                ((TbPictorialLoader$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
                ((TbPictorialLoader$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = z;
                VarHandle.storeStoreFence();
                PictorialWallpaperLoader.getInstance().loadWallpaper(context, gauss, externalSyntheticLambda0);
            }
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseCurrentDrawable() {
        KgdLogUtil.d("TbPictorialLoader", "releaseCurrentDrawable recycleBitmap" + this.mCurrentLockScreenDrawable);
        OplusImageUtils.recycleBitmap(this.mCurrentLockScreenDrawable);
        this.mCurrentLockScreenDrawable = null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseDrawableAndInfo() {
        OplusImageUtils.recycleBitmap(this.mCurrentLockScreenDrawable);
        this.mCurrentLockScreenDrawable = null;
        PictorialWallpaperInfo pictorialWallpaperInfo = this.mCurrentLockScreenWallpaperInfo;
        if (pictorialWallpaperInfo != null) {
            OplusImageUtils.recycleBitmap(pictorialWallpaperInfo.getWallpaperDrawable());
            this.mCurrentLockScreenWallpaperInfo.setWallpaperDrawable(null);
            this.mCurrentLockScreenWallpaperInfo = null;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo2 = this.mCurrentPreloadWallpaperInfo;
        if (pictorialWallpaperInfo2 != null) {
            pictorialWallpaperInfo2.setWallpaperDrawable(null);
            this.mCurrentPreloadWallpaperInfo = null;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo3 = this.mNextLockScreenWallpaperInfo;
        if (pictorialWallpaperInfo3 != null) {
            pictorialWallpaperInfo3.setWallpaperDrawable(null);
            this.mNextLockScreenWallpaperInfo = null;
        }
        PictorialWallpaperInfo pictorialWallpaperInfo4 = this.mNextPreloadWallpaperInfo;
        if (pictorialWallpaperInfo4 != null) {
            pictorialWallpaperInfo4.setWallpaperDrawable(null);
            this.mNextPreloadWallpaperInfo = null;
        }
        this.mPreloadNextDrawableCache = new Pair(null, null);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void releaseDrawableCache() {
        Pair pair = this.mLockScreenNextDrawableCache;
        if (pair != null) {
            OplusImageUtils.recycleBitmap((Drawable) pair.second);
        }
        Pair pair2 = this.mPreloadNextDrawableCache;
        if (pair2 != null) {
            OplusImageUtils.recycleBitmap((Drawable) pair2.second);
        }
        this.mLockScreenNextDrawableCache = new Pair(null, null);
        this.mPreloadNextDrawableCache = new Pair(null, null);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setEnable(boolean z) {
        this.mEnable = z;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setHasAlreadyFetchFromSlidePage(boolean z) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public void setPictorialLoaderCallback(PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback) {
        this.mCallback = iPictorialLoaderCallback;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateCurrentPreloadWallpaper(Context context) throws Throwable {
        updatePreloadWallpaper$1(context, isEnable(), isEnableLockScreen(context));
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateCurrentWallpaper(Context context) throws Throwable {
        PictorialWallpaperInfo pictorialWallpaperInfo;
        if (isEnableLockScreen(context)) {
            List list = this.mCoverInfoList;
            if (list == null || list.size() <= 0) {
                pictorialWallpaperInfo = null;
            } else {
                pictorialWallpaperInfo = (PictorialWallpaperInfo) this.mCoverInfoList.get(0);
                if (this.mPictorialMediator == null) {
                    this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
                }
            }
            if (pictorialWallpaperInfo != null) {
                KgdLogUtil.i("TbPictorialLoader", "updateLockScreenWallpaper info is not null =".concat(pictorialWallpaperInfo.toString()));
                this.mCurrentLockScreenWallpaperInfo = null;
                if (checkCurrentLockScreenDrawable$1(pictorialWallpaperInfo)) {
                    KgdLogUtil.i("TbPictorialLoader", "updateLockScreenWallpaper drawable is not null , sync update");
                    this.mCurrentLockScreenWallpaperInfo = pictorialWallpaperInfo;
                    PictorialLoader.IPictorialLoaderCallback iPictorialLoaderCallback = this.mCallback;
                    if (iPictorialLoaderCallback != null) {
                        ((PictorialWallpaperHelper$$ExternalSyntheticLambda1) iPictorialLoaderCallback).onTypeChange();
                    }
                } else {
                    KgdLogUtil.i("TbPictorialLoader", "updateLockScreenWallpaper drawable is null, async update");
                    asyncUpdateWallpaper$1(context, pictorialWallpaperInfo, true);
                }
            }
        }
        updatePreloadWallpaper$1(context, isEnable(), isEnableLockScreen(context));
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateNextWallpaper(Context context) throws Throwable {
        preloadNextWallpaper$1(context, true);
        preloadNextWallpaper$1(context, false);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x003c  */
    public final void updatePreloadWallpaper$1(Context context, boolean z, boolean z2) throws Throwable {
        PictorialWallpaperInfo pictorialWallpaperInfo;
        if (z) {
            List list = this.mCoverInfoList;
            if (list == null || list.isEmpty()) {
                pictorialWallpaperInfo = null;
            } else {
                this.mCoverInfoList.size();
                List list2 = this.mCoverInfoList;
                if (!z2) {
                    PictorialWallpaperInfo pictorialWallpaperInfo2 = (PictorialWallpaperInfo) list2.get(0);
                    if (pictorialWallpaperInfo2 != null) {
                        KgdLogUtil.i("TbPictorialLoader", "fetchPreloadWallpaperInfo,index = 0");
                        pictorialWallpaperInfo = new PictorialWallpaperInfo(pictorialWallpaperInfo2);
                    } else {
                        pictorialWallpaperInfo = null;
                    }
                } else if (list2 != null) {
                    pictorialWallpaperInfo = ((PictorialWallpaperInfo) list2.get(0)).getPreloadWallpaper();
                } else {
                    pictorialWallpaperInfo = null;
                }
            }
            if (pictorialWallpaperInfo == null) {
                return;
            }
            this.mCurrentPreloadWallpaperInfo = null;
            if (!checkCurrentPreloadDrawable$1(pictorialWallpaperInfo)) {
                KgdLogUtil.i("TbPictorialLoader", "updatePreloadWallpaper : drawable is null, async update");
                asyncUpdateWallpaper$1(context, pictorialWallpaperInfo, false);
                return;
            }
            KgdLogUtil.i("TbPictorialLoader", "updatePreloadWallpaper : drawable is not null , sync update");
            this.mCurrentPreloadWallpaperInfo = pictorialWallpaperInfo;
            if (this.mPreloadChangeListeners.isEmpty()) {
                return;
            }
            ((ArrayList) this.mPreloadChangeListeners).forEach(new OplusPictorialLoader$$ExternalSyntheticLambda1(1));
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader
    public final void updateWelcomePreloadWallpaper(Context context) {
        this.mCurrentPreloadWallpaperInfo = null;
        this.mCurrentPreloadDrawable = null;
        KgdLogUtil.i("TbPictorialLoader", "updateWelcomePreloadWallpaper : drawable is null, async update");
        PictorialWallpaperLoader.getInstance().loadWallpaperWelcome(context, new PictorialWallpaperLoader.WallpaperLoadRequest().setPictorialWallpaperInfo(new PictorialWallpaperInfo()).setGauss(false), new TbPictorialLoader$$ExternalSyntheticLambda2());
    }
}
