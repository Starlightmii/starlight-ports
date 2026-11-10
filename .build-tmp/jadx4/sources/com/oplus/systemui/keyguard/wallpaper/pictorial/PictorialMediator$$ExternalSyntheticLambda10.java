package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Message;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Pair;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialCursorHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda10 implements Runnable {
    public /* synthetic */ PictorialMediator f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ boolean f$2;
    public /* synthetic */ boolean f$3;
    public /* synthetic */ String f$4;
    public /* synthetic */ String f$5;
    public /* synthetic */ boolean f$6;

    @Override // java.lang.Runnable
    public final void run() {
        List pictorialImageDataList;
        PictorialMediator pictorialMediator = this.f$0;
        int i = this.f$1;
        boolean z = this.f$2;
        boolean z2 = this.f$3;
        String str = this.f$4;
        String str2 = this.f$5;
        boolean z3 = this.f$6;
        AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
        Trace.traceBegin(8L, "obtainPictorialWallpaper");
        if (i != KeyguardUpdateMonitor.getCurrentUser()) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "obtainPictorialWallpaper not same userID in runnable, return, userId = ", ", KeyguardUpdateMonitor.getCurrentUser() = ");
            sbM.append(KeyguardUpdateMonitor.getCurrentUser());
            KgdLogUtil.i("PictorialMediator", sbM.toString());
            if (Boolean.TRUE.equals(pictorialMediator.mWaitingPictorialData.getValue())) {
                pictorialMediator.mWaitingPictorialData.postValue(Boolean.FALSE);
                return;
            }
            return;
        }
        if (z) {
            boolean zEquals = TextUtils.equals(str, "keyguard_hide");
            long jCurrentTimeMillis = System.currentTimeMillis();
            Uri pictorialImageUri = pictorialMediator.mPictorialAppDataImpl.getPictorialImageUri();
            Resources pictorialResources = pictorialMediator.getPictorialResources();
            OplusPictorialCursorHelper.Params params = new OplusPictorialCursorHelper.Params();
            params.setContentResolver(pictorialMediator.mContentResolver);
            params.setImageUri(pictorialImageUri);
            params.setPictorialWallpaperLoader(pictorialMediator.mPictorialWallpaperLoader);
            params.setContext(pictorialMediator.mContext);
            params.setDefaultDensityContext(pictorialMediator.mDefaultDensityContext);
            params.setSupportPictorialTips(pictorialMediator.isPictorialTipsFeatherSupport() && pictorialMediator.isSupportPictorialTips());
            params.setPictorialResources(pictorialResources);
            params.setGetBlur(false);
            params.setLightFetch(z2);
            params.setPAodMode(true);
            params.setDismissKeyguardGetImage(zEquals);
            params.setPicParams(str2);
            pictorialImageDataList = pictorialMediator.mPictorialCursorHelper.getPictorialImageDataList(params);
            KgdLogUtil.i("PictorialMediator", "getPictorialImageDataList cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis) + ", isLightFetch = " + z2 + ", isPAodMode = true, isDismissKeyguardGetImage = " + zEquals);
        } else {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Uri pictorialImageUri2 = pictorialMediator.mPictorialAppDataImpl.getPictorialImageUri();
            Resources pictorialResources2 = pictorialMediator.getPictorialResources();
            OplusPictorialCursorHelper.Params params2 = new OplusPictorialCursorHelper.Params();
            params2.setContentResolver(pictorialMediator.mContentResolver);
            params2.setImageUri(pictorialImageUri2);
            params2.setPictorialWallpaperLoader(pictorialMediator.mPictorialWallpaperLoader);
            params2.setContext(pictorialMediator.mContext);
            params2.setDefaultDensityContext(pictorialMediator.mDefaultDensityContext);
            params2.setSupportPictorialTips(pictorialMediator.isPictorialTipsFeatherSupport() && pictorialMediator.isSupportPictorialTips());
            params2.setPictorialResources(pictorialResources2);
            params2.setGetBlur(false);
            params2.setLightFetch(z2);
            params2.setPicParams(str2);
            pictorialImageDataList = pictorialMediator.mPictorialCursorHelper.getPictorialImageDataList(params2);
            KgdLogUtil.i("PictorialMediator", "getPictorialImageDataList cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis2) + ", isLightFetch = " + z2);
        }
        if (Boolean.TRUE.equals(pictorialMediator.mWaitingPictorialData.getValue())) {
            pictorialMediator.mWaitingPictorialData.postValue(Boolean.FALSE);
        }
        if (pictorialImageDataList == null || pictorialImageDataList.isEmpty()) {
            KgdLogUtil.i("PictorialMediator", "result is null");
            PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(5);
            pictorialMediator$$ExternalSyntheticLambda1.f$0 = pictorialMediator;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(pictorialMediator$$ExternalSyntheticLambda1);
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportOplusGLPictorial && KeyguardFeatureOption.isKeepHeyTapPictorial) {
                KgdLogUtil.i("PictorialMediator", "setPictorialAppSwitching false");
                pictorialMediator.setPictorialAppSwitching(false);
            }
        } else {
            KgdLogUtil.i("PictorialMediator", "get first pictorial image: " + pictorialImageDataList.get(0));
            if (pictorialImageDataList.size() > 1) {
                KgdLogUtil.i("PictorialMediator", "second pictorial image: " + pictorialImageDataList.get(1));
            }
        }
        if (pictorialMediator.mHandler.hasMessages(1)) {
            pictorialMediator.mHandler.removeMessages(1);
        }
        if (z && pictorialMediator.mNeedReleaseForFestivalPaod && !((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().isConfigPackageAsGalleryClock()) {
            KgdLogUtil.d("PictorialMediator", "obtainPictorialWallpaper releaseDrawableAndInfo for PAOD festival wallpaper scene");
            pictorialMediator.mPictorialFactory.getPictorialLoader().releaseDrawableAndInfo();
            pictorialMediator.mNeedReleaseForFestivalPaod = false;
        }
        Message messageObtainMessage = pictorialMediator.mHandler.obtainMessage(1);
        messageObtainMessage.obj = new Pair(Boolean.valueOf(z2), pictorialImageDataList);
        messageObtainMessage.arg1 = i;
        messageObtainMessage.arg2 = (PictorialWallpaperLoader.getInstance().mIsForceUpdate || !z2 || z3 || pictorialMediator.isGlanceStyle() || pictorialMediator.isOplusGLStyle()) ? 1 : 0;
        PictorialWallpaperLoader.getInstance().setForceUpdate(false);
        pictorialMediator.mHandler.sendMessage(messageObtainMessage);
        Trace.traceEnd(8L);
    }
}
