package com.oplus.systemui.qs.media;

import android.app.NotificationManager;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.collection.FloatList$;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.VolumeDialogController;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSeekBarViewModel implements SeekBar.OnSeekBarChangeListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "VolumeSeekBarViewModel";
    private final Context context;
    public int curVolumeLevel;
    public boolean disallowHandleStateChange;
    private final Runnable disallowHandleStateChangeRunnable;
    private final VolumeSeekBarViewModel$mAccessibilityDelegate$1 mAccessibilityDelegate;
    private final AudioManager mAudioManager;
    private SeekBar mBar;
    public boolean mIsUserTouchTracking;
    public boolean mIsZenModeOn;
    public long mLastVolumeUpdateTime;
    private final AtomicInteger mMusicVolumeLevel = new AtomicInteger(-1);
    private final NotificationManager mNoMan;
    private final VolumeSeekBarViewModel$mSeekBarHandler$1 mSeekBarHandler;
    private final VolumeDialogController.Callbacks mVolumeCallback;
    private final VolumeDialogController mVolumeController;
    private ContentObserver mZenModeObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final int access$convertProgressToVolumeLevel(Companion companion, SeekBar seekBar, int i) {
            companion.getClass();
            int max = seekBar.getMax();
            int i2 = max / 100;
            int i3 = i2 - 1;
            if (i == 0) {
                return 0;
            }
            return i == max ? i2 : ((int) ((i / max) * i3)) + 1;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface VolumeControllerCallbackAdapter extends VolumeDialogController.Callbacks {
        default void onAccessibilityModeChanged(Boolean bool) {
        }

        default void onCaptionComponentStateChanged(Boolean bool, Boolean bool2) {
        }

        default void onConfigurationChanged() {
        }

        default void onDismissRequested(int i) {
        }

        default void onLayoutDirectionChanged(int i) {
        }

        default void onScreenOff() {
        }

        default void onShowCsdWarning(int i, int i2) {
        }

        default void onShowRequested(int i, boolean z, int i2) {
        }

        default void onShowSafetyWarning(int i) {
        }

        default void onShowSilentHint() {
        }

        default void onShowVibrateHint() {
        }

        default void onStateChanged(VolumeDialogController.State state) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.VolumeSeekBarViewModel$initVolumeBar$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnTouchListener {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.oplus.systemui.qs.media.VolumeSeekBarViewModel$mAccessibilityDelegate$1] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.oplus.systemui.qs.media.VolumeSeekBarViewModel$mSeekBarHandler$1] */
    public VolumeSeekBarViewModel(Context context, Handler handler) {
        this.context = context;
        VolumeSeekBarViewModel$disallowHandleStateChangeRunnable$1 volumeSeekBarViewModel$disallowHandleStateChangeRunnable$1 = new VolumeSeekBarViewModel$disallowHandleStateChangeRunnable$1();
        volumeSeekBarViewModel$disallowHandleStateChangeRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.disallowHandleStateChangeRunnable = volumeSeekBarViewModel$disallowHandleStateChangeRunnable$1;
        this.mZenModeObserver = new ContentObserver() { // from class: com.oplus.systemui.qs.media.VolumeSeekBarViewModel$mZenModeObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                boolean zIsZenModeOn = this.this$0.isZenModeOn();
                VolumeSeekBarViewModel volumeSeekBarViewModel = this.this$0;
                if (volumeSeekBarViewModel.mIsZenModeOn != zIsZenModeOn) {
                    volumeSeekBarViewModel.mIsZenModeOn = zIsZenModeOn;
                    SeekBar seekBar = volumeSeekBarViewModel.mBar;
                    if (seekBar != null) {
                        this.this$0.initVolumeBar(seekBar);
                    }
                }
            }
        };
        this.mAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: com.oplus.systemui.qs.media.VolumeSeekBarViewModel$mAccessibilityDelegate$1
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                accessibilityNodeInfo.setTraversalBefore(this.this$0.mBar);
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            }
        };
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.mVolumeController = (VolumeDialogController) Dependency.sDependency.getDependencyInner(VolumeDialogController.class);
        this.mNoMan = (NotificationManager) context.getSystemService("notification");
        VolumeSeekBarViewModel$mVolumeCallback$1 volumeSeekBarViewModel$mVolumeCallback$1 = new VolumeSeekBarViewModel$mVolumeCallback$1();
        volumeSeekBarViewModel$mVolumeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mVolumeCallback = volumeSeekBarViewModel$mVolumeCallback$1;
        final Looper looper = handler.getLooper();
        this.mSeekBarHandler = new Handler(looper) { // from class: com.oplus.systemui.qs.media.VolumeSeekBarViewModel$mSeekBarHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1001) {
                    this.this$0.updateStreamMusicVolumeLevel(message.arg1);
                }
            }
        };
    }

    private final int getSavedMusicVolumeLevel() {
        return this.mMusicVolumeLevel.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVolumeBarProgress(int i) {
        SeekBar seekBar;
        SeekBar seekBar2 = this.mBar;
        Integer numValueOf = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
        OplusNotificationLogger.logMediaPanel(TAG, "setVolumeBarProgress: newProgress=" + i + " current=" + numValueOf + " disallowHandleStateChange:" + this.disallowHandleStateChange + " mIsUserTouchTracking:" + this.mIsUserTouchTracking);
        SeekBar seekBar3 = this.mBar;
        if ((seekBar3 == null || seekBar3.getProgress() != i) && (seekBar = this.mBar) != null) {
            seekBar.setProgress(i);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void initVolumeBar(SeekBar seekBar) {
        AudioManager audioManager;
        this.mBar = seekBar;
        Companion companion = Companion;
        AudioManager audioManager2 = this.mAudioManager;
        companion.getClass();
        seekBar.setMax((audioManager2 != null ? audioManager2.getStreamMaxVolume(3) : 16) * 100);
        int lastAudibleStreamVolume = 0;
        seekBar.setMin(0);
        boolean zIsVolumeBarUnavailable = isVolumeBarUnavailable();
        seekBar.setEnabled(!zIsVolumeBarUnavailable);
        seekBar.setAlpha(zIsVolumeBarUnavailable ? 0.5f : 1.0f);
        Drawable thumb = seekBar.getThumb();
        if (thumb != null) {
            thumb.setAlpha(0);
        }
        if (!zIsVolumeBarUnavailable && (audioManager = this.mAudioManager) != null) {
            lastAudibleStreamVolume = audioManager.getLastAudibleStreamVolume(3);
        }
        seekBar.setProgress(lastAudibleStreamVolume * 100);
        this.curVolumeLevel = seekBar.getProgress();
        savedMusicVolumeLevel(-1);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setOnTouchListener(AnonymousClass1.INSTANCE);
        seekBar.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public final boolean isVolumeBarUnavailable() {
        NotificationManager.Policy consolidatedNotificationPolicy = this.mNoMan.getConsolidatedNotificationPolicy();
        return this.mIsZenModeOn && consolidatedNotificationPolicy != null && (consolidatedNotificationPolicy.priorityCategories & 64) == 0;
    }

    public final boolean isZenModeOn() {
        return Settings.Global.getInt(this.context.getContentResolver(), OplusNoDisturbListener.ZEN_MODE_SETTING, 0) > 0;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Drawable thumb;
        int iAccess$convertProgressToVolumeLevel;
        if (z && this.mIsUserTouchTracking && getSavedMusicVolumeLevel() != (iAccess$convertProgressToVolumeLevel = Companion.access$convertProgressToVolumeLevel(Companion, seekBar, i))) {
            Message messageObtainMessage = obtainMessage(1001);
            messageObtainMessage.arg1 = iAccess$convertProgressToVolumeLevel;
            sendMessageDelayed(messageObtainMessage, 0L);
        }
        SeekBar seekBar2 = this.mBar;
        if (seekBar2 == null || (thumb = seekBar2.getThumb()) == null) {
            return;
        }
        thumb.setAlpha(z ? 255 : 0);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        SystemUIThreadUtil.removeCallbacksInUiThread(this.disallowHandleStateChangeRunnable);
        this.mIsUserTouchTracking = true;
        this.disallowHandleStateChange = true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        Drawable thumb;
        int iAccess$convertProgressToVolumeLevel = Companion.access$convertProgressToVolumeLevel(Companion, seekBar, seekBar.getProgress());
        int i = iAccess$convertProgressToVolumeLevel * 100;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onStopTrackingTouch: volumeLevel=", iAccess$convertProgressToVolumeLevel, seekBar.getProgress(), " currentProgress=", " quantizedProgress=");
        sbM.append(i);
        OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
        SeekBar seekBar2 = this.mBar;
        if (seekBar2 != null && (thumb = seekBar2.getThumb()) != null) {
            thumb.setAlpha(0);
        }
        removeMessages(1001);
        updateStreamMusicVolumeLevel(iAccess$convertProgressToVolumeLevel);
        this.mIsUserTouchTracking = false;
        this.curVolumeLevel = i;
        setVolumeBarProgress(i);
        SystemUIThreadUtil.removeCallbacksInUiThread(this.disallowHandleStateChangeRunnable);
        SystemUIThreadUtil.runOnUiThreadDelay(this.disallowHandleStateChangeRunnable, 500L);
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.forceVolumeControlStream(-1);
        }
    }

    public final void onViewAttached(SeekBar seekBar) {
        this.mVolumeController.addCallback(this.mVolumeCallback, new Handler(Looper.getMainLooper()));
        this.mIsZenModeOn = isZenModeOn();
        initVolumeBar(seekBar);
        ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(OplusNoDisturbListener.ZEN_MODE_SETTING), this.mZenModeObserver);
    }

    public final void onViewDetached() {
        this.mVolumeController.removeCallback(this.mVolumeCallback);
        this.context.getContentResolver().unregisterContentObserver(this.mZenModeObserver);
        SystemUIThreadUtil.removeCallbacksInUiThread(this.disallowHandleStateChangeRunnable);
        this.mBar = null;
    }

    public final void savedMusicVolumeLevel(int i) {
        this.mMusicVolumeLevel.set(i);
    }

    public final void updateStreamMusicVolumeLevel(int i) {
        if (SystemClock.uptimeMillis() - this.mLastVolumeUpdateTime >= 50) {
            this.mLastVolumeUpdateTime = SystemClock.uptimeMillis();
            this.mVolumeController.setActiveStream(3, true);
            this.mVolumeController.setStreamVolume(3, i, true);
            savedMusicVolumeLevel(i);
            return;
        }
        removeMessages(1001);
        if (getSavedMusicVolumeLevel() == i) {
            return;
        }
        Message messageObtainMessage = obtainMessage(1001);
        messageObtainMessage.arg1 = i;
        sendMessageDelayed(messageObtainMessage, 50L);
    }
}
