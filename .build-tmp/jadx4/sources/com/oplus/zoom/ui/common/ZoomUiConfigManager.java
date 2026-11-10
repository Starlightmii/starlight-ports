package com.oplus.zoom.ui.common;

import android.content.Context;
import android.content.res.Configuration;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.ui.ZoomUiManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomUiConfigManager implements ZoomDisplayController.DisplayChangedListener {
    private static final String TAG = "ZoomUiConfiguration";
    private final Context mContext;
    private int mDensityDpi;
    private int mDisplayId;
    private float mFontScale;
    private boolean mNightMode;
    private final ZoomUiManager mUiManager;

    public ZoomUiConfigManager(Context context, ZoomUiManager zoomUiManager) {
        this.mContext = context;
        this.mUiManager = zoomUiManager;
        init();
    }

    private void init() {
        this.mNightMode = isNightMode(this.mContext.getResources().getConfiguration());
        this.mDisplayId = this.mContext.getDisplayId();
        this.mDensityDpi = 0;
        this.mFontScale = this.mContext.getResources().getConfiguration().fontScale;
        ZoomDisplayController.getInstance().registerDisplayChangedListener(this);
    }

    private boolean isNightMode(Configuration configuration) {
        return (configuration.uiMode & 48) == 32;
    }

    public void onConfigChanged(Configuration configuration) {
        this.mDisplayId = this.mContext.getDisplayId();
        UiLogUtils.d("ZoomUiConfiguration", "onConfigChanged()");
        boolean zIsNightMode = isNightMode(configuration);
        int i = configuration.densityDpi;
        float f = configuration.fontScale;
        this.mUiManager.getFloatHandleController().onConfigurationChange(configuration);
        this.mUiManager.getTipsController().hideAllTipsView();
        if (zIsNightMode != this.mNightMode) {
            this.mUiManager.getTipsController().updateUiStyle(configuration);
            this.mUiManager.getFloatHandleController().refreshFloatHandle();
        }
        if (this.mDensityDpi != i) {
            this.mUiManager.getTipsController().updateUiStyle(configuration);
            this.mUiManager.getFloatHandleController().relaunchFloatHandle();
        }
        if (this.mFontScale != f) {
            this.mUiManager.getTipsController().updateUiStyle(configuration);
            this.mUiManager.getFloatHandleController().relaunchFloatHandle();
        }
        refreshConfiguration(configuration);
    }

    @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
    public void onDisplayChanged(int i, int i2, Configuration configuration) {
        if (i != this.mDisplayId) {
            UiLogUtils.d("ZoomUiConfiguration", "onDisplayChanged no changed in current display");
            return;
        }
        this.mUiManager.getTipsController().hideAllTipsView();
        if (this.mDensityDpi == configuration.densityDpi) {
            this.mUiManager.getFloatHandleController().onDisplayChanged(configuration);
        }
    }

    public void refreshConfiguration(Configuration configuration) {
        this.mNightMode = isNightMode(configuration);
        this.mDisplayId = this.mContext.getDisplayId();
        this.mDensityDpi = configuration.densityDpi;
        this.mFontScale = configuration.fontScale;
    }
}
