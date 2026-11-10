package com.oplus.zoom.common;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.SparseArray;
import com.oplus.OplusTrackManager;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.util.OplusTrackProxyUtil;
import com.oplus.zoom.ZoomTrack;
import java.lang.invoke.VarHandle;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomDCSManager {
    private static final String AFTER_SCALE = "after_scale";
    private static final String BEFORE_SCALE = "before_scale";
    private static final String CALL_PKG = "call_pkg";
    public static final String CLOSE_BUTTON = "button";
    public static final String CLOSE_DOUBLE_TAP_IN_SIMPLE_MODE = "double_click";
    public static final String CLOSE_SIMPLE_MODE_BUTTON = "simple_button";
    private static final String CLOSE_TYPE = "close_type";
    private static final String DURATION = "duration";
    private static final String EVENT_ID_PS_TRIGGER = "ps_trigger";
    private static final String EXIT_MINI_ZOOM_WINDOW = "exit_mini_zoom_window";
    private static final String EXIT_ZOOM_FLOAT_HANDLE = "exit_zoom_float_handle";
    private static final String EXIT_ZOOM_WINDOW = "exit_zoom_window";
    private static final String FLOATWINDOW_ALPHA = "floatwindow_alpha";
    private static final String FLOATWINDOW_EXCHANGE = "floatwindow_exchange";
    private static final String FLOATWINDOW_REDIRECT = "floatwindow_redirect";
    private static final String GAME_SHRINK_SWITCH = "zoom_window_game_mode";
    private static final String KEY_SPLIT_COUNT = "split_count";
    private static final String KEY_TRRIGER_ACTION = "trigger_action";
    protected static final String LAUNCHER_GESTURE = "com.android.launcher.gesture";
    private static final int MSG_EXIT_FLOAT_HANDLE = 6;
    private static final int MSG_EXIT_MINI = 4;
    private static final int MSG_EXIT_ZOOM = 2;
    private static final int MSG_SHOW_FLOAT_HANDLE = 5;
    private static final int MSG_START_MINI = 3;
    private static final int MSG_START_ZOOM = 1;
    private static final String ON_DELETE_ZOOM_BUTTON_SELECTED = "on_delete_zoom_button_selected";
    private static final String POCKET_STUDIO_TAG = "20126006";
    private static final String RESIZE_WINDOW = "resize_window";
    private static final String SCREEN_STATE = "screen_state";
    private static final String SHOW_ZOOM_FLOAT_HANDLE = "show_zoom_float_handle";
    private static final String SIMPLE_MODE_SWITCH = "floatwindow_mode";
    private static final String SIMPLE_SHARE_SWITCH = "floatwindow_share";
    private static final String START_MINI_ZOOM_WINDOW = "start_mini_zoom_window";
    private static final String START_TIME = "start_time";
    private static final String START_ZOOM_WINDOW = "start_zoom_window";
    public static final String STATUS = "status";
    private static final String STATUS_FALSE = "false";
    private static final String STATUS_TRUE = "true";
    private static final String TAG = "ZoomDCSManager";
    public static final String TYPE = "type";
    public static final String VALUE_ACTION_ZOOM_DRAG = "zoom_drag";
    public static final String VALUE_SPLIT_COUNT_THREE = "three";
    public static final String VALUE_SPLIT_COUNT_TWO = "two";
    private static final String ZOOM_NEXT_PKG = "zoom_next_pkg";
    private static final String ZOOM_PKG = "zoom_pkg";
    private static final String ZOOM_WINDOW_APP_ID = "20126";
    private static final String ZOOM_WINDOW_TAG = "20126007";
    private static volatile ZoomDCSManager sInstance;
    private Context mContext;
    private Handler mHandler;
    private OplusTrackManager mOplusTrackManager;
    private final SparseArray<ZoomStateRecorder> mRecorders = new SparseArray<>();
    private OplusTrackManager.TrackListener mTrackListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class ZoomStateRecorder {
        private String callPkg;
        private long floatHandleStartTime;
        private long miniZoomStartTime;
        private int taskId;
        private String zoomPkg;
        private long zoomStartTime;

        public ZoomStateRecorder(int i, String str, String str2) {
            this.taskId = i;
            this.zoomPkg = str;
            this.callPkg = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onExitMiniZoomWindow$3() {
            HashMap map = new HashMap();
            long jCurrentTimeMillis = (System.currentTimeMillis() - this.miniZoomStartTime) / 1000;
            this.miniZoomStartTime = 0L;
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.DURATION, String.valueOf(jCurrentTimeMillis));
            LogD.d(ZoomDCSManager.TAG, "onExitMiniZoomWindow taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.EXIT_MINI_ZOOM_WINDOW, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onExitZoomFloatHandle$5() {
            HashMap map = new HashMap();
            long jCurrentTimeMillis = (System.currentTimeMillis() - this.floatHandleStartTime) / 1000;
            this.floatHandleStartTime = 0L;
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.DURATION, String.valueOf(jCurrentTimeMillis));
            LogD.d(ZoomDCSManager.TAG, "onExitZoomFloatHandle taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.EXIT_ZOOM_FLOAT_HANDLE, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onExitZoomWindow$1() {
            HashMap map = new HashMap();
            long jCurrentTimeMillis = (System.currentTimeMillis() - this.zoomStartTime) / 1000;
            this.zoomStartTime = 0L;
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.DURATION, String.valueOf(jCurrentTimeMillis));
            LogD.d(ZoomDCSManager.TAG, "onExitZoomWindow taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.EXIT_ZOOM_WINDOW, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onStartMiniZoomWindow$2() {
            HashMap map = new HashMap();
            this.miniZoomStartTime = System.currentTimeMillis();
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.ZOOM_NEXT_PKG, "");
            map.put(ZoomDCSManager.CALL_PKG, this.callPkg);
            map.put(ZoomDCSManager.START_TIME, ZoomDCSManager.this.getFormatSimpleDate(this.miniZoomStartTime));
            LogD.d(ZoomDCSManager.TAG, "onStartMiniZoomWindow taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.START_MINI_ZOOM_WINDOW, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onStartZoomFloatHandle$4() {
            HashMap map = new HashMap();
            this.floatHandleStartTime = System.currentTimeMillis();
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            LogD.d(ZoomDCSManager.TAG, "onStartZoomFloatHandle taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.SHOW_ZOOM_FLOAT_HANDLE, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onStartZoomWindow$0() {
            HashMap map = new HashMap();
            this.zoomStartTime = System.currentTimeMillis();
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.ZOOM_NEXT_PKG, "");
            map.put(ZoomDCSManager.START_TIME, ZoomDCSManager.this.getFormatSimpleDate(this.zoomStartTime));
            map.put(ZoomDCSManager.CALL_PKG, this.callPkg);
            LogD.d(ZoomDCSManager.TAG, "onStartZoomWindow taskid = " + this.taskId + ", eventMap = " + map);
            updateZoomDCS(ZoomDCSManager.START_ZOOM_WINDOW, map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onZoomCloseWithType$7(String str) {
            HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m(ZoomDCSManager.CLOSE_TYPE, str);
            LogD.d(ZoomDCSManager.TAG, "uploadZoomCloseType: evenMap = " + mapM);
            updateZoomDCS(ZoomDCSManager.ON_DELETE_ZOOM_BUTTON_SELECTED, mapM);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onZoomScaleChange$6(float f, float f2) {
            HashMap map = new HashMap();
            if (ZoomDisplayController.getInstance().getRotation() == 1 || ZoomDisplayController.getInstance().getRotation() == 3) {
                map.put(ZoomDCSManager.SCREEN_STATE, "landscape");
            } else {
                map.put(ZoomDCSManager.SCREEN_STATE, "portrait");
            }
            map.put(ZoomDCSManager.BEFORE_SCALE, Float.toString(f));
            map.put(ZoomDCSManager.AFTER_SCALE, Float.toString(f2));
            map.put(ZoomDCSManager.ZOOM_PKG, this.zoomPkg);
            map.put(ZoomDCSManager.ZOOM_NEXT_PKG, "");
            LogD.d(ZoomDCSManager.TAG, "onZoomScaleChange: evenMap = " + map);
            updateZoomDCS(ZoomDCSManager.RESIZE_WINDOW, map);
        }

        private void updateZoomDCS(String str, Map<String, String> map) {
            OplusTrackProxyUtil.onCommonWindowManager(ZoomDCSManager.this.mContext, ZoomDCSManager.ZOOM_WINDOW_APP_ID, "001", str, map);
        }

        public void onExitMiniZoomWindow() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(3);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onExitZoomFloatHandle() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(2);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onExitZoomWindow() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(1);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onStartMiniZoomWindow() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(5);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onStartZoomFloatHandle() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(6);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onStartZoomWindow() {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(4);
            zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
        }

        public void onZoomCloseWithType(String str) {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$ZoomStateRecorder$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new ZoomDCSManager$ZoomStateRecorder$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = this;
            externalSyntheticLambda1.f$1 = str;
            VarHandle.storeStoreFence();
            handler.post(externalSyntheticLambda1);
        }

        public void onZoomScaleChange(float f, float f2) {
            Handler handler = ZoomDCSManager.this.mHandler;
            ZoomDCSManager$ZoomStateRecorder$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new ZoomDCSManager$ZoomStateRecorder$.ExternalSyntheticLambda4();
            externalSyntheticLambda4.f$0 = this;
            externalSyntheticLambda4.f$1 = f;
            externalSyntheticLambda4.f$2 = f2;
            VarHandle.storeStoreFence();
            handler.post(externalSyntheticLambda4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFormatSimpleDate(long j) {
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) new Time(j));
        LogD.d(TAG, "formatDate = " + str);
        return str;
    }

    public static ZoomDCSManager getInstance() {
        if (sInstance == null) {
            synchronized (ZoomDCSManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomDCSManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private void initZoomTrack() {
        this.mOplusTrackManager = OplusTrackManager.getInstance(this.mContext, this.mHandler);
        this.mTrackListener = new ZoomTrack();
        updateTrackEvent();
        LogD.i(TAG, "initZoomTrack: ");
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda0 zoomDCSManager$$ExternalSyntheticLambda0 = new ZoomDCSManager$$ExternalSyntheticLambda0(0);
        zoomDCSManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initZoomTrack$0() {
        this.mOplusTrackManager.addTrackListener(this.mTrackListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAlphaAdjustSwitch$6(boolean z) {
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("type", "5");
        mapM.put(STATUS, z ? "1" : "0");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, ZOOM_WINDOW_TAG, FLOATWINDOW_ALPHA, mapM);
        LogD.d(TAG, "onAlphaAdjustSwitch: evenMap = " + mapM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onExchangeAppSwitch$5(boolean z) {
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("type", "4");
        mapM.put(STATUS, z ? "1" : "0");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, ZOOM_WINDOW_TAG, FLOATWINDOW_EXCHANGE, mapM);
        LogD.d(TAG, "onExchangeAppSwitch: evenMap = " + mapM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGameRedirectSwitch$4(boolean z) {
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("type", "3");
        mapM.put(STATUS, z ? "1" : "0");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, ZOOM_WINDOW_TAG, FLOATWINDOW_REDIRECT, mapM);
        LogD.d(TAG, "onGameRedirectSwitch: evenMap = " + mapM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGameShrinkSwitch$2(boolean z) {
        HashMap map = new HashMap();
        map.put(STATUS, z ? "true" : "false");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, "001", GAME_SHRINK_SWITCH, map);
        LogD.d(TAG, "onGameShrinkSwitch: evenMap = " + map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSimpleModeSwitch$1(boolean z) {
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("type", "1");
        mapM.put(STATUS, z ? "0" : "1");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, ZOOM_WINDOW_TAG, SIMPLE_MODE_SWITCH, mapM);
        LogD.d(TAG, "onSimpleModeSwitch: evenMap = " + mapM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onZoomShareSwitch$3(boolean z) {
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("type", ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH);
        mapM.put(STATUS, z ? "1" : "0");
        this.mTrackListener.setTrackEvent(ZOOM_WINDOW_APP_ID, ZOOM_WINDOW_TAG, SIMPLE_SHARE_SWITCH, mapM);
        LogD.d(TAG, "onZoomShareSwitch: evenMap = " + mapM);
    }

    public static void onPocketStudioTrigger(String str, String str2) {
        HashMap map = new HashMap();
        map.put(KEY_TRRIGER_ACTION, str);
        map.put(KEY_SPLIT_COUNT, str2);
        LogD.d(TAG, "onPocketStudioTrigger(), eventID: ps_triggerlogMap: " + map);
        OplusTrackProxyUtil.onCommonWindowManager(getInstance().mContext, ZOOM_WINDOW_APP_ID, POCKET_STUDIO_TAG, EVENT_ID_PS_TRIGGER, map);
    }

    private void updateTrackEvent() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6 = false;
        try {
            z = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_SIMPLE_MODE, 1, -2) == 1;
            try {
                z2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_GAME_SHRINK_SETTINGS, 0, -2) == 1;
                try {
                    z3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_SHARE_BEHAVIOR, 1, -2) == 1;
                    try {
                        z4 = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_GAME_REDIRECT_BEHAVIOR, 1, -2) == 1;
                        try {
                            z5 = Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_EXCHANGE_APPLICATION, 0, -2) == 1;
                            try {
                                if (Settings.System.getIntForUser(this.mContext.getContentResolver(), ZoomSettingDispatcher.ZOOM_ALPHA_ADJUST, 0, -2) == 1) {
                                    z6 = true;
                                }
                            } catch (SecurityException e) {
                                e = e;
                                LogD.e(TAG, "updateTrackEvent error, e: " + e);
                            }
                        } catch (SecurityException e2) {
                            e = e2;
                            z5 = false;
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                        z4 = false;
                        z5 = z4;
                        LogD.e(TAG, "updateTrackEvent error, e: " + e);
                        onSimpleModeSwitch(z);
                        onGameShrinkSwitch(z2);
                        onZoomShareSwitch(z3);
                        onGameRedirectSwitch(z4);
                        onExchangeAppSwitch(z5);
                        onAlphaAdjustSwitch(z6);
                    }
                } catch (SecurityException e4) {
                    e = e4;
                    z3 = false;
                    z4 = z3;
                    z5 = z4;
                    LogD.e(TAG, "updateTrackEvent error, e: " + e);
                    onSimpleModeSwitch(z);
                    onGameShrinkSwitch(z2);
                    onZoomShareSwitch(z3);
                    onGameRedirectSwitch(z4);
                    onExchangeAppSwitch(z5);
                    onAlphaAdjustSwitch(z6);
                }
            } catch (SecurityException e5) {
                e = e5;
                z2 = false;
                z3 = z2;
                z4 = z3;
                z5 = z4;
                LogD.e(TAG, "updateTrackEvent error, e: " + e);
                onSimpleModeSwitch(z);
                onGameShrinkSwitch(z2);
                onZoomShareSwitch(z3);
                onGameRedirectSwitch(z4);
                onExchangeAppSwitch(z5);
                onAlphaAdjustSwitch(z6);
            }
        } catch (SecurityException e6) {
            e = e6;
            z = false;
            z2 = false;
        }
        onSimpleModeSwitch(z);
        onGameShrinkSwitch(z2);
        onZoomShareSwitch(z3);
        onGameRedirectSwitch(z4);
        onExchangeAppSwitch(z5);
        onAlphaAdjustSwitch(z6);
    }

    public void init(Context context) {
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
        initZoomTrack();
    }

    public void onAlphaAdjustSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(4);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }

    public void onExchangeAppSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(2);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }

    public void onExitMiniZoomWindow(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onExitMiniZoomWindow();
        }
    }

    public void onExitZoomFloatHandle(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onExitZoomFloatHandle();
        }
    }

    public void onExitZoomWindow(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onExitZoomWindow();
        }
    }

    public void onGameRedirectSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(5);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }

    public void onGameShrinkSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(1);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }

    public void onSimpleModeSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(0);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }

    public void onStartMiniZoomWindow(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onStartMiniZoomWindow();
        }
    }

    public void onStartZoomFloatHandle(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onStartZoomFloatHandle();
        }
    }

    public void onStartZoomWindow(int i) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onStartZoomWindow();
        }
    }

    public void onUserSwitch(int i) {
        updateTrackEvent();
    }

    public void onZoomCloseWithType(int i, String str) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onZoomCloseWithType(str);
        }
    }

    public void onZoomEnter(int i, String str, String str2) {
        if (this.mRecorders.get(i) != null) {
            return;
        }
        this.mRecorders.put(i, new ZoomStateRecorder(i, str, str2));
        LogD.d(TAG, "onZoomEnter taskid = " + i);
    }

    public void onZoomExit(int i) {
        if (this.mRecorders.get(i) == null) {
            return;
        }
        this.mRecorders.remove(i);
        LogD.d(TAG, "onZoomExit taskid = " + i);
    }

    public void onZoomScaleChange(int i, float f, float f2) {
        ZoomStateRecorder zoomStateRecorder = this.mRecorders.get(i);
        if (zoomStateRecorder != null) {
            zoomStateRecorder.onZoomScaleChange(f, f2);
        }
    }

    public void onZoomShareSwitch(boolean z) {
        Handler handler = this.mHandler;
        ZoomDCSManager$$ExternalSyntheticLambda1 zoomDCSManager$$ExternalSyntheticLambda1 = new ZoomDCSManager$$ExternalSyntheticLambda1(3);
        zoomDCSManager$$ExternalSyntheticLambda1.f$0 = this;
        zoomDCSManager$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        handler.post(zoomDCSManager$$ExternalSyntheticLambda1);
    }
}
