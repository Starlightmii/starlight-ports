package com.oplus.zoom.ui;

import android.app.ActivityManager;
import android.app.OplusActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.shared.annotations.ExternalThread;
import com.oplus.zoom.IZoom;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.ui.common.ZoomUiConfigManager;
import com.oplus.zoom.ui.common.ZoomUiObserver;
import com.oplus.zoom.ui.floathandle.FloatHandleController;
import com.oplus.zoom.ui.floathandle.FloatHandleInfo;
import com.oplus.zoom.ui.tips.TipsController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomUiManager {
    private static final String TAG = "ZoomUIManager";
    private ZoomUiConfigManager mConfigurationManager;
    private final Context mContext;
    private final FloatHandleController mFloatHandleController;
    private Handler mMainHandler;
    private ZoomUiObserver mSettingObserver;
    private final TipsController mTipsController;
    private IZoom mZoomController;
    private final ZoomUiManagerImpl mImpl = new ZoomUiManagerImpl(this, 0);
    private final OplusActivityManager mAms = new OplusActivityManager();

    public ZoomUiManager(Context context, SyncTransactionQueue syncTransactionQueue, Handler handler, IZoom iZoom, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mMainHandler = handler;
        this.mZoomController = iZoom;
        this.mTipsController = new TipsController(context, handler, this, shellExecutor);
        this.mFloatHandleController = new FloatHandleController(context, syncTransactionQueue, handler, this);
        this.mSettingObserver = new ZoomUiObserver(context, this);
        this.mConfigurationManager = new ZoomUiConfigManager(context, this);
    }

    public IZoomUiManager asZoomUi() {
        return this.mImpl;
    }

    public OplusActivityManager getActivityManager() {
        return this.mAms;
    }

    public FloatHandleController getFloatHandleController() {
        return this.mFloatHandleController;
    }

    public TipsController getTipsController() {
        return this.mTipsController;
    }

    public void requestChangeZoomState(int i) {
        this.mZoomController.requestChangeZoomState(-1, i);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    @ExternalThread
    class ZoomUiManagerImpl implements IZoomUiManager {
        private ZoomUiManagerImpl() {
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void addFloatHandle(FloatHandleInfo floatHandleInfo) {
            ZoomUiManager.this.mFloatHandleController.addFloatHandle(floatHandleInfo);
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void dismissSnackBar() {
            ZoomUiManager.this.mTipsController.dismissSnackBar();
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public FloatHandleInfo getFloatHandleInfo(int i) {
            return ZoomUiManager.this.mFloatHandleController.getFloatHandleInfo();
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public Rect getFloatHandleRegion(Rect rect, float f) {
            return ZoomUiManager.this.mFloatHandleController.getFloatHandleRegion(rect, f);
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void hideAllTipsView() {
            ZoomUiManager.this.mTipsController.hideAllTipsView();
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void onConfigChanged(Configuration configuration) {
            ZoomUiManager.this.mConfigurationManager.onConfigChanged(configuration);
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
            ZoomUiManager.this.mFloatHandleController.onTaskMovedToFront(runningTaskInfo);
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void removeFloatHandle(int i) {
            ZoomUiManager.this.mFloatHandleController.removeFloatHandle();
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void showCompatibilityToast(int i, int i2, String str, String str2, Bundle bundle) {
            ZoomUiManager.this.mTipsController.showCompatibilityToast(i, i2, str, str2, bundle);
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void showTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, boolean z) {
            ZoomUiManager.this.mTipsController.showTipsOnZoomWindowShow(zoomPositionInfo, Boolean.valueOf(z));
        }

        public /* synthetic */ ZoomUiManagerImpl(ZoomUiManager zoomUiManager, int i) {
            this();
        }

        @Override // com.oplus.zoom.ui.IZoomUiManager
        public void showTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, boolean z, boolean z2) {
            ZoomUiManager.this.mTipsController.showMiniTipsOnZoomWindowShow(zoomPositionInfo, z, z2);
        }
    }
}
