package com.oplus.zoom.common;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.oplus.systemui.volume.observer.VolumeSettingsValueProxy;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomSettingsObserver implements ZoomSettingDispatcher.CallbackListener {
    private static final String SETTING_CHANGE = "setting-change";
    private static final String TAG = "ZoomSettingObserver";
    private static volatile ZoomSettingsObserver sInstance;
    private Context mContext;
    private final Uri mSimpleModeSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_SIMPLE_MODE);
    private final Uri mGameShrinkSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_GAME_SHRINK_SETTINGS);
    private final Uri mZoomShareSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_SHARE_BEHAVIOR);
    private final Uri mGameRedirectSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_GAME_REDIRECT_BEHAVIOR);
    private final Uri mExchangeAppSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_EXCHANGE_APPLICATION);
    private final Uri mAlphaAdjustSettings = Settings.System.getUriFor(ZoomSettingDispatcher.ZOOM_ALPHA_ADJUST);
    private final Uri mAnimationDurationScaleUri = Settings.Global.getUriFor(VolumeSettingsValueProxy.SETTINGS_ANIMATION_SCALE);
    private final Uri mFoldAngleSetting = Settings.Global.getUriFor(ZoomSettingDispatcher.SYSTEM_FOLDING_ANGLE);
    private final Uri mFoldStateSetting = Settings.Global.getUriFor(ZoomSettingDispatcher.SYSTEM_FOLDING_STATE);
    private int mCurrentUid = 0;
    private float mAnimationDurationSetting = -1.0f;
    private final HashMap<Integer, Boolean> mEnableSimpleMode = new HashMap<>();
    private int mFoldAngle = 0;
    private int mFoldDeviceState = 0;

    public static ZoomSettingsObserver getInstance() {
        if (sInstance == null) {
            synchronized (ZoomSettingsObserver.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomSettingsObserver();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private void updateAlphaAdjustEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_ALPHA_ADJUST, 0, -2) == 1;
            ZoomDCSManager.getInstance().onAlphaAdjustSwitch(z);
            LogD.d("ZoomSettingObserver", "updateGameRedirectEnable enable:" + z);
        } catch (SecurityException e) {
            LogD.e("ZoomSettingObserver", "updateGameRedirectEnable error, e: " + e);
        }
    }

    private void updateAnimationDuration(String str) {
        try {
            this.mAnimationDurationSetting = Settings.Global.getFloat(this.mContext.getContentResolver(), VolumeSettingsValueProxy.SETTINGS_ANIMATION_SCALE, 1.0f);
            LogD.d("ZoomSettingObserver", "updateAnimationDuration mAnimationDurationSetting:" + this.mAnimationDurationSetting + " reason:" + str);
        } catch (Exception e) {
            LogD.e("ZoomSettingObserver", "updateAnimationDuration error, e: " + e);
        }
    }

    private void updateExchangeAppEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_EXCHANGE_APPLICATION, 0, -2) == 1;
            ZoomDCSManager.getInstance().onExchangeAppSwitch(z);
            LogD.d("ZoomSettingObserver", "updateGameRedirectEnable enable:" + z);
        } catch (SecurityException e) {
            LogD.e("ZoomSettingObserver", "updateGameRedirectEnable error, e: " + e);
        }
    }

    private void updateFoldAngleValue(String str) {
        try {
            this.mFoldAngle = Settings.Global.getInt(this.mContext.getContentResolver(), ZoomSettingDispatcher.SYSTEM_FOLDING_ANGLE, 0);
            LogD.d("ZoomSettingObserver", "updateFoldAngle mFoldAngle:" + this.mFoldAngle + " reason:" + str);
        } catch (Exception e) {
            LogD.e("ZoomSettingObserver", "updateFoldAngle error, e: " + e);
        }
    }

    private void updateGameRedirectEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_GAME_REDIRECT_BEHAVIOR, 0, -2) == 1;
            ZoomDCSManager.getInstance().onGameRedirectSwitch(z);
            LogD.d("ZoomSettingObserver", "updateGameRedirectEnable enable:" + z);
        } catch (SecurityException e) {
            LogD.e("ZoomSettingObserver", "updateGameRedirectEnable error, e: " + e);
        }
    }

    private void updateGameShrinkEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_GAME_SHRINK_SETTINGS, 0, -2) == 1;
            ZoomDCSManager.getInstance().onGameShrinkSwitch(z);
            LogD.d("ZoomSettingObserver", "updateGameShrinkEnable enable:" + z);
        } catch (SecurityException e) {
            LogD.e("ZoomSettingObserver", "updateGameShrinkEnable error, e: " + e);
        }
    }

    private void updateSimpleModeEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_SIMPLE_MODE, 0, -2) == 1;
            this.mEnableSimpleMode.put(Integer.valueOf(this.mCurrentUid), Boolean.valueOf(z));
            ZoomDCSManager.getInstance().onSimpleModeSwitch(z);
            LogD.d("ZoomSettingObserver", "updateSimpleModeEnable enable:" + z);
        } catch (Exception e) {
            LogD.e("ZoomSettingObserver", "updateSimpleModeEnable error, e: " + e);
        }
    }

    private void updateZoomShareEnable(String str) {
        try {
            boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_SHARE_BEHAVIOR, 0, -2) == 1;
            ZoomDCSManager.getInstance().onZoomShareSwitch(z);
            LogD.d("ZoomSettingObserver", "updateZoomShareEnable enable:" + z);
        } catch (SecurityException e) {
            LogD.e("ZoomSettingObserver", "updateZoomShareEnable error, e: " + e);
        }
    }

    public float getAnimationDuration() {
        if (this.mAnimationDurationSetting == -1.0f) {
            updateAnimationDuration("re-init");
        }
        return this.mAnimationDurationSetting;
    }

    public int getFoldAngleValue() {
        return this.mFoldAngle;
    }

    public int getFoldDeviceState() {
        return this.mFoldDeviceState;
    }

    public boolean getSimpleModeEnable() {
        Boolean bool = this.mEnableSimpleMode.get(Integer.valueOf(this.mCurrentUid));
        if (bool != null) {
            return bool.booleanValue();
        }
        updateSimpleModeEnable("re-init");
        if (this.mEnableSimpleMode.get(Integer.valueOf(this.mCurrentUid)) != null) {
            return this.mEnableSimpleMode.get(Integer.valueOf(this.mCurrentUid)).booleanValue();
        }
        return false;
    }

    public void init(Context context) {
        this.mContext = context;
        ZoomSettingDispatcher.getInstance().addCallbackListener(this);
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onSettingChanged(boolean z, Uri uri, int i) {
        if (uri == null) {
            return;
        }
        if (uri.equals(this.mSimpleModeSettings)) {
            updateSimpleModeEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mGameShrinkSettings)) {
            updateGameShrinkEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mZoomShareSettings)) {
            updateZoomShareEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mGameRedirectSettings)) {
            updateGameRedirectEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mExchangeAppSettings)) {
            updateExchangeAppEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mAlphaAdjustSettings)) {
            updateAlphaAdjustEnable(SETTING_CHANGE);
            return;
        }
        if (uri.equals(this.mAnimationDurationScaleUri)) {
            updateAnimationDuration(SETTING_CHANGE);
        } else if (uri.equals(this.mFoldAngleSetting)) {
            updateFoldAngleValue(SETTING_CHANGE);
        } else if (uri.equals(Integer.valueOf(this.mFoldDeviceState))) {
            updateFoldDeviceState(SETTING_CHANGE);
        }
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onUserSwitched(int i) {
        this.mCurrentUid = i;
        ZoomDCSManager.getInstance().onUserSwitch(i);
    }

    public void updateFoldDeviceState(String str) {
        try {
            this.mFoldDeviceState = Settings.Global.getInt(this.mContext.getContentResolver(), ZoomSettingDispatcher.SYSTEM_FOLDING_STATE, 0);
            LogD.d("ZoomSettingObserver", "updateFoldDeviceState mFoldDeviceState:" + this.mFoldDeviceState + " reason:" + str);
        } catch (Exception e) {
            LogD.e("ZoomSettingObserver", "updateFoldDeviceState error, e: " + e);
        }
    }
}
