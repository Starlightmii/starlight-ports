package com.oplus.systemui.keyguard.wallpaper.uiengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserManager;
import androidx.collection.FloatList$;
import androidx.core.graphics.drawable.DrawableKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.OplusKeyguardViewMediatorExImpl;
import com.oplus.systemui.keyguard.wallpaper.BaseWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.File;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EngineWallpaperHelper extends BaseWallpaperHelper implements OplusKeyguardWatcher.Observer {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EngineWallpaperHelper";
    public boolean isUserSwtitching;
    private Drawable mDrawable;
    public boolean mEnable;
    public boolean mFirstEnable;
    private final OplusKeyguardWatcher mOplusKeyguardWatcher;
    private final KeyguardUpdateMonitorCallback userSwitchingCallback;
    private final EngineWallpaperInfo wallpaperInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EngineWallpaperHelper(Context context) {
        super(context);
        OplusKeyguardWatcher companion = OplusKeyguardWatcher.Companion.getInstance();
        this.mOplusKeyguardWatcher = companion;
        this.mFirstEnable = true;
        EngineWallpaperHelper$userSwitchingCallback$1 engineWallpaperHelper$userSwitchingCallback$1 = new EngineWallpaperHelper$userSwitchingCallback$1();
        engineWallpaperHelper$userSwitchingCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userSwitchingCallback = engineWallpaperHelper$userSwitchingCallback$1;
        companion.addCallback((OplusKeyguardWatcher.Observer) this);
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        if (keyguardUpdateMonitor != null) {
            keyguardUpdateMonitor.registerCallback(engineWallpaperHelper$userSwitchingCallback$1);
        }
        this.mFirstEnable = true;
        this.wallpaperInfo = new EngineWallpaperInfo();
    }

    public Bitmap copyWallpaperBitmapSafely() {
        Bitmap bitmapOrNull$default;
        Drawable wallpaper = getWallpaper();
        if (wallpaper != null && (bitmapOrNull$default = DrawableKt.toBitmapOrNull$default(wallpaper)) != null) {
            if (bitmapOrNull$default.isRecycled()) {
                bitmapOrNull$default = null;
            }
            if (bitmapOrNull$default != null) {
                Bitmap.Config config = bitmapOrNull$default.getConfig();
                config.getClass();
                return bitmapOrNull$default.copy(config, bitmapOrNull$default.isMutable());
            }
        }
        return null;
    }

    public void disable(BaseWallpaperHelper baseWallpaperHelper) {
        super.disable(baseWallpaperHelper);
        KgdLogUtil.i(TAG, "disable");
        this.mDrawable = null;
        this.mEnable = false;
    }

    public void enable(BaseWallpaperHelper baseWallpaperHelper) {
        super.enable(baseWallpaperHelper);
        KgdLogUtil.i(TAG, PictorialAppInfo.TAG_ENABLE);
        this.mEnable = true;
    }

    public final OplusKeyguardWatcher getMOplusKeyguardWatcher() {
        return this.mOplusKeyguardWatcher;
    }

    public Drawable getWallpaper() {
        return this.mDrawable;
    }

    @Override // com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher.Observer
    public void onSetBackground(Drawable drawable) {
        super.onSetBackground(drawable);
        OplusKeyguardViewMediatorExImpl oplusKeyguardViewMediatorEx = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardViewMediatorEx();
        String keyguardLockResPath = KeyguardUtils.Companion.getKeyguardLockResPath(((BaseWallpaperHelper) this).mContext);
        if (keyguardLockResPath == null || !new File(keyguardLockResPath).exists()) {
            return;
        }
        if (drawable == null) {
            this.mDrawable = null;
            KgdLogUtil.i(TAG, "onSetBackground drawable = null");
        } else if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap == null || bitmap.isRecycled()) {
                this.mDrawable = null;
                KgdLogUtil.i(TAG, "onSetBackground drawable = null or isRecycled");
            } else {
                this.mDrawable = new BitmapDrawable(((BaseWallpaperHelper) this).mContext.getResources(), bitmap);
            }
        } else {
            this.mDrawable = drawable;
        }
        if (drawable != null) {
            OplusChargeAnimController$.ExternalSyntheticOutline0.m(TAG, FloatList$.ExternalSyntheticOutline0.m("drawable: ", drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), ", ", ", mFirstEnable:"), this.mFirstEnable);
            if (this.mFirstEnable) {
                this.mFirstEnable = false;
            }
        }
        onWallpaperChange(2);
        String str = TAG;
        KgdLogUtil.i(str, "try to set lock screen drawable: cur:" + this.mDrawable + " update:" + drawable);
        if (this.mEnable) {
            if (!UserManager.get(((BaseWallpaperHelper) this).mContext).isUserUnlocked(0)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->try to set lock screen drawable: is not user unlock", "SystemUi--Keyguard");
                    return;
                }
                return;
            }
            if (this.isUserSwtitching || oplusKeyguardViewMediatorEx.isMultiUserBeginSwitch()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->is userSwitch, no need set lockscreen wallpaper", "SystemUi--Keyguard");
                }
                this.isUserSwtitching = false;
                return;
            }
            int currentUser = KeyguardUpdateMonitor.getCurrentUser();
            if (drawable != null) {
                Bitmap bitmapOrNull$default = DrawableKt.toBitmapOrNull$default(drawable);
                onSetBackground.3 r1 = new onSetBackground.3();
                r1.this$0 = this;
                r1.$bitmap = bitmapOrNull$default;
                r1.$curUser = currentUser;
                VarHandle.storeStoreFence();
                PictorialUtils.runOnWorkThread(r1);
                return;
            }
            KgdLogUtil.i(str, "clear lockscreen wallpaper, curUser=" + currentUser);
            onSetBackground.4 r10 = new onSetBackground.4();
            r10.this$0 = this;
            r10.$curUser = currentUser;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(r10);
        }
    }

    public final int type() {
        return 2;
    }

    public EngineWallpaperInfo getWallpaperInfo() {
        return this.wallpaperInfo;
    }
}
