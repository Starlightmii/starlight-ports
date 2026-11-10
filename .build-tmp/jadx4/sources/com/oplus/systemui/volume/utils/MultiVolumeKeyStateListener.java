package com.oplus.systemui.volume.utils;

import android.content.Context;
import android.os.OplusKeyEventManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import androidx.collection.FloatList$;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.systemui.Dependency;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiVolumeKeyStateListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MultiVolumeKeyListener";
    private static volatile MultiVolumeKeyStateListener instance;
    private Context context;
    private KeyEventCallBack mKeyEventCallBack;
    public boolean mVolumeKeyPressed;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MultiVolumeKeyStateListener getInstance(Context context) {
            MultiVolumeKeyStateListener multiVolumeKeyStateListener;
            MultiVolumeKeyStateListener multiVolumeKeyStateListener2 = MultiVolumeKeyStateListener.instance;
            if (multiVolumeKeyStateListener2 != null) {
                return multiVolumeKeyStateListener2;
            }
            synchronized (this) {
                multiVolumeKeyStateListener = MultiVolumeKeyStateListener.instance;
                if (multiVolumeKeyStateListener == null) {
                    multiVolumeKeyStateListener = new MultiVolumeKeyStateListener(context);
                    MultiVolumeKeyStateListener.instance = multiVolumeKeyStateListener;
                }
            }
            return multiVolumeKeyStateListener;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class KeyEventCallBack implements OplusKeyEventManager.OnKeyEventObserver {
        private final Context context;
        public long lastKeyUpdateTime;

        public KeyEventCallBack(Context context) {
            this.context = context;
        }

        public void onKeyEvent(KeyEvent keyEvent) {
            if (((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() != 2) {
                MultiVolumeKeyStateListener.this.setMVolumeKeyPressed(false);
                return;
            }
            int action = keyEvent.getAction();
            switch (keyEvent.getKeyCode()) {
                case 24:
                case 25:
                    if (action == 0) {
                        MultiVolumeKeyStateListener.this.setMVolumeKeyPressed(true);
                    }
                    break;
                case 26:
                    MultiVolumeKeyStateListener.this.setMVolumeKeyPressed(false);
                    break;
            }
            MultiVolumeKeyStateListener multiVolumeKeyStateListener = MultiVolumeKeyStateListener.this;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                int keyCode = keyEvent.getKeyCode();
                int action2 = keyEvent.getAction();
                long jUptimeMillis = SystemClock.uptimeMillis();
                long j = this.lastKeyUpdateTime;
                boolean mVolumeKeyPressed = multiVolumeKeyStateListener.getMVolumeKeyPressed();
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onKeyEvent keyCode: ", keyCode, action2, ", action: ", " SystemClock.uptimeMillis(): ");
                sbM.append(jUptimeMillis);
                DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, " lastKeyUpdateTime: ", j, " mVolumeKeyPressed: ");
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MultiVolumeKeyListener-->", "SystemUi--Volume", sbM, mVolumeKeyPressed);
            }
            if (action == 0 && SystemClock.uptimeMillis() - this.lastKeyUpdateTime < 60) {
                MultiVolumeKeyStateListener.this.setMVolumeKeyPressed(false);
            }
            if (action == 0) {
                this.lastKeyUpdateTime = SystemClock.uptimeMillis();
            }
        }
    }

    public MultiVolumeKeyStateListener(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getKeyEventState() {
        return this.mVolumeKeyPressed;
    }

    public final int getListenPowerKeyEventValue() {
        return 1;
    }

    public final int getListenVolumeDownKeyEventValue() {
        return 4;
    }

    public final int getListenVolumeUpKeyEventValue() {
        return 2;
    }

    public final boolean getMVolumeKeyPressed() {
        return this.mVolumeKeyPressed;
    }

    public final void registerKeyEvent(Context context) {
        try {
            if (this.mKeyEventCallBack == null) {
                this.mKeyEventCallBack = new KeyEventCallBack(context);
            }
            boolean zRegisterKeyEventObserver = OplusKeyEventManager.getInstance().registerKeyEventObserver(context, this.mKeyEventCallBack, getListenVolumeUpKeyEventValue() | getListenVolumeDownKeyEventValue() | getListenPowerKeyEventValue());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "MultiVolumeKeyListener-->".concat("registerKeyEvent result = " + zRegisterKeyEventObserver));
            }
        } catch (RemoteException e) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("registerKeyEvent failed. error = ", e.getMessage(), TAG);
        }
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setKeyEventState(boolean z) {
        this.mVolumeKeyPressed = z;
    }

    public final void setMVolumeKeyPressed(boolean z) {
        this.mVolumeKeyPressed = z;
    }

    public final void unregisterKeyEvent(Context context) {
        try {
            if (this.mKeyEventCallBack != null) {
                OplusKeyEventManager.getInstance().unregisterKeyEventObserver(context, this.mKeyEventCallBack);
                this.mKeyEventCallBack = null;
            }
        } catch (RemoteException e) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("unregisterKeyEvent failed. error = ", e.getMessage(), TAG);
        }
    }
}
