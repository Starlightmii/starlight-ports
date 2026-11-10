package com.oplus.systemui.qs.slider;

import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.databinding.ObservableField;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.QSVolume;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.qs.OplusNoDisturbListener;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.volume.manager.VolumeManager;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeController implements QSVolume {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsVolumeController";
    private final NonNullObservableField<Boolean> a11yStreamShow;
    private final OplusQsVolumeController$bgHandler$1 bgHandler;
    private final Context context;
    public boolean hasDownEventSet;
    private NonNullObservableField<Boolean> isAllowedProgressUpdate;
    private final AudioManager mAudioManager;
    private final OplusQsVolumeController$mEnableVolumeDialogCallBack$1 mEnableVolumeDialogCallBack;
    private final VolumeDialogController.Callbacks mVolumeCallback;
    private final NonNullObservableField<String> musicStreamLabel;
    private final NonNullObservableField<Boolean> musicStreamZenMuted;
    private final NonNullObservableField<QSVolume.VolumeState> musicVolumeState;
    private final Lazy notificationManager$delegate;
    private AtomicInteger preparedSetVolumeLevel;
    private final NonNullObservableField<Boolean> ringModeChanged;
    private final Runnable setAllowedProgressUpdateRunnable;
    private final Runnable setDisAllowedProgressUpdateRunnable;
    public int simulateDirection;
    private final Lazy supportSuperVolume$delegate;
    private final VolumeDialogController volumeController;
    private final VolumeManager volumeManager;
    private final NonNullObservableField<Integer> volumeUnchange;
    private final Lazy volumeVolumeUnchangeCallback$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Factory {
        private final Looper bgLooper;
        private final Context mContext;
        private final VolumeDialogController volumeDialogController;
        private final VolumeManager volumeManager;

        public Factory(Context context, Looper looper, VolumeDialogController volumeDialogController, VolumeManager volumeManager) {
            this.mContext = context;
            this.bgLooper = looper;
            this.volumeDialogController = volumeDialogController;
            this.volumeManager = volumeManager;
        }

        public final QSVolume create() {
            return new OplusQsVolumeController(this.mContext, this.volumeDialogController, this.bgLooper, this.volumeManager);
        }

        public final Looper getBgLooper() {
            return this.bgLooper;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface VolumeCallbackAdapter extends VolumeDialogController.Callbacks {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.slider.OplusQsVolumeController$updateHint$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQsVolumeController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    TemporarilyInfoManager.Companion.getInstance().show(this.this$0.context.getString(2131956974), null, String.valueOf(2131956974), new TemporarilyInfoManager.InfoShowStyle(QsColorUtil.getFooterTextColor(this.this$0.context), 11));
                    break;
                case 1:
                    this.this$0.isAllowedProgressUpdate().set(Boolean.TRUE);
                    break;
                default:
                    this.this$0.isAllowedProgressUpdate().set(Boolean.FALSE);
                    OplusSysUiSchedMgr.Companion.getInstance().schedForVolumeDialog(true);
                    break;
            }
        }
    }

    public static NotificationManager $r8$lambda$09x4K7qmauwkyaPOuCHLTpEVIzo(OplusQsVolumeController oplusQsVolumeController) {
        return (NotificationManager) oplusQsVolumeController.context.getSystemService(NotificationManager.class);
    }

    /* JADX WARN: Type inference failed for: r12v5, types: [com.oplus.systemui.qs.slider.OplusQsVolumeController$bgHandler$1] */
    public OplusQsVolumeController(Context context, VolumeDialogController volumeDialogController, final Looper looper, VolumeManager volumeManager) {
        this.context = context;
        this.volumeController = volumeDialogController;
        this.volumeManager = volumeManager;
        OplusQsVolumeController$$ExternalSyntheticLambda0 oplusQsVolumeController$$ExternalSyntheticLambda0 = new OplusQsVolumeController$$ExternalSyntheticLambda0(0);
        oplusQsVolumeController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeVolumeUnchangeCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVolumeController$$ExternalSyntheticLambda0);
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
        int i = 1;
        OplusQsVolumeController$$ExternalSyntheticLambda0 oplusQsVolumeController$$ExternalSyntheticLambda1 = new OplusQsVolumeController$$ExternalSyntheticLambda0(i);
        oplusQsVolumeController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.notificationManager$delegate = LazyKt__LazyJVMKt.lazy(oplusQsVolumeController$$ExternalSyntheticLambda1);
        this.bgHandler = new Handler(looper) { // from class: com.oplus.systemui.qs.slider.OplusQsVolumeController$bgHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                OplusQsVolumeController oplusQsVolumeController = this;
                switch (i2) {
                    case 1001:
                        oplusQsVolumeController.setMusicVolumeLevelInternal(message.arg1);
                        break;
                    case 1002:
                        oplusQsVolumeController.setMusicVolumeLevelInternal(oplusQsVolumeController.preparedSetVolumeLevel.getAndSet(-1));
                        break;
                    case 1003:
                        oplusQsVolumeController.getMusicVolumeDataInternal();
                        break;
                }
            }
        };
        OplusQsVolumeController$mVolumeCallback$1 oplusQsVolumeController$mVolumeCallback$1 = new OplusQsVolumeController$mVolumeCallback$1();
        oplusQsVolumeController$mVolumeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mVolumeCallback = oplusQsVolumeController$mVolumeCallback$1;
        this.mEnableVolumeDialogCallBack = new OplusQsVolumeController$mEnableVolumeDialogCallBack$1();
        this.supportSuperVolume$delegate = LazyKt__LazyJVMKt.lazy(new OplusQsVolumeController$$ExternalSyntheticLambda2());
        this.musicVolumeState = new NonNullObservableField<>(new QSVolume.VolumeState(0, 16, -1, -1, 0, false), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.volumeUnchange = new NonNullObservableField<>(-1, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        Boolean bool = Boolean.FALSE;
        this.musicStreamZenMuted = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicStreamLabel = new NonNullObservableField<>("", (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.a11yStreamShow = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.ringModeChanged = new NonNullObservableField<>(bool, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.isAllowedProgressUpdate = new NonNullObservableField<>(Boolean.TRUE, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.setAllowedProgressUpdateRunnable = anonymousClass1;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(2);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.setDisAllowedProgressUpdateRunnable = anonymousClass2;
        this.preparedSetVolumeLevel = new AtomicInteger(-1);
    }

    private final void getInitMusicVolumeData() {
        sendMessage(obtainMessage(1003));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x0061  */
    /* JADX WARN: Code duplicated, block: B:31:0x0079  */
    public final void getMusicVolumeDataInternal() {
        boolean z;
        boolean z2;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getMusicVolumeDataInternal begin", null, 8);
        VolumeDialogController.StreamState streamState = new VolumeDialogController.StreamState();
        AudioManager audioManager = this.mAudioManager;
        boolean z3 = false;
        streamState.level = audioManager != null ? audioManager.getLastAudibleStreamVolume(3) : 0;
        AudioManager audioManager2 = this.mAudioManager;
        streamState.levelMin = audioManager2 != null ? audioManager2.getStreamMinVolumeInt(3) : 0;
        AudioManager audioManager3 = this.mAudioManager;
        streamState.levelMax = Math.max(1, audioManager3 != null ? audioManager3.getStreamMaxVolume(3) : 16);
        AudioManager audioManager4 = this.mAudioManager;
        streamState.muted = audioManager4 != null && audioManager4.isStreamMute(3);
        AudioManager audioManager5 = this.mAudioManager;
        if (audioManager5 != null) {
            Companion.getClass();
            if ((audioManager5.getDevicesForStream(3) & 536871920) != 0) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        streamState.routedToBluetooth = z;
        AudioManager audioManager6 = this.mAudioManager;
        if (audioManager6 != null) {
            Companion.getClass();
            if ((audioManager6.getDevicesForStream(3) & 67108876) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        streamState.routedToWired = z2;
        VolumeDialogController.State state = new VolumeDialogController.State();
        state.zenMode = Settings.Global.getInt(this.context.getContentResolver(), OplusNoDisturbListener.ZEN_MODE_SETTING, 0);
        NotificationManager notificationManager = getNotificationManager();
        if (notificationManager != null) {
            Companion.getClass();
            if ((notificationManager.getConsolidatedNotificationPolicy().priorityCategories & 64) == 0) {
                z3 = true;
            }
        }
        state.disallowMedia = z3;
        state.states.put(3, streamState);
        this.mVolumeCallback.onStateChanged(state);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getMusicVolumeDataInternal: state=" + state, null, 8);
    }

    private final NotificationManager getNotificationManager() {
        return (NotificationManager) this.notificationManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSupportSuperVolume() {
        return ((Boolean) this.supportSuperVolume$delegate.getValue()).booleanValue();
    }

    private final VolumeDialogController.VolumeUnchangedCallBack getVolumeVolumeUnchangeCallback() {
        return (VolumeDialogController.VolumeUnchangedCallBack) this.volumeVolumeUnchangeCallback$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMusicVolumeLevelInternal(int i) {
        if (i < 0) {
            return;
        }
        if (hasMessages(1002)) {
            this.preparedSetVolumeLevel.set(i);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setMusicVolumeLevelInternal: volumeLevel="), null, 8);
        sendMessageDelayed(obtainMessage(1002), 50L);
        this.volumeController.setActiveStream(3, false);
        this.volumeController.setStreamVolume(3, i, false);
    }

    public final void disableShowVolumeDialog(boolean z) {
        VolumeManager volumeManager = this.volumeManager;
        if (z) {
            volumeManager.addCallBack(VolumeManager.INTERCEPT_VOLUME_QS_TAG, this.mEnableVolumeDialogCallBack);
        } else {
            volumeManager.removeCallBack(VolumeManager.INTERCEPT_VOLUME_QS_TAG);
        }
    }

    public final void forceVolumeControlStream() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.forceVolumeControlStream(-1);
        }
    }

    public NonNullObservableField<Boolean> getA11yStreamShow() {
        return this.a11yStreamShow;
    }

    public NonNullObservableField<String> getMusicStreamLabel() {
        return this.musicStreamLabel;
    }

    public NonNullObservableField<Boolean> getMusicStreamZenMuted() {
        return this.musicStreamZenMuted;
    }

    public int getMusicVolumeLevel() {
        return ((QSVolume.VolumeState) getMusicVolumeState().get()).getLevel();
    }

    public NonNullObservableField<QSVolume.VolumeState> getMusicVolumeState() {
        return this.musicVolumeState;
    }

    public NonNullObservableField<Boolean> getRingModeChanged() {
        return this.ringModeChanged;
    }

    public NonNullObservableField<Integer> getVolumeUnchange() {
        return this.volumeUnchange;
    }

    public NonNullObservableField<Boolean> isAllowedProgressUpdate() {
        return this.isAllowedProgressUpdate;
    }

    public final void registerCallbacks() {
        this.volumeController.addCallback(this.mVolumeCallback, this.bgHandler);
        this.volumeController.addVolumeUnchangeCallback(getVolumeVolumeUnchangeCallback());
        getInitMusicVolumeData();
    }

    public void setAllowProgressUpdate(long j) {
        removeCallbacks(this.setAllowedProgressUpdateRunnable);
        removeCallbacks(this.setDisAllowedProgressUpdateRunnable);
        postDelayed(this.setAllowedProgressUpdateRunnable, j);
    }

    public void setAllowedProgressUpdate(NonNullObservableField<Boolean> nonNullObservableField) {
        this.isAllowedProgressUpdate = nonNullObservableField;
    }

    public void setDisAllowProgressUpdate(long j) {
        removeCallbacks(this.setAllowedProgressUpdateRunnable);
        removeCallbacks(this.setDisAllowedProgressUpdateRunnable);
        postDelayed(this.setDisAllowedProgressUpdateRunnable, j);
    }

    public void setMusicVolumeLevel(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "sendSetMusicVolumeLevelMsg: volumeLevel="), null, 8);
        Message messageObtainMessage = obtainMessage(1001);
        messageObtainMessage.arg1 = i;
        sendMessageDelayed(messageObtainMessage, 0L);
    }

    public final void unregisterCallbacks() {
        this.volumeController.removeCallback(this.mVolumeCallback);
        this.volumeController.removeVolumeUnchangeCallback(getVolumeVolumeUnchangeCallback());
    }

    public final void updateHint() {
        NotificationManager notificationManager;
        if (OplusNoDisturbListener.Companion.getInstance().getZenModeTemp() != 1 || (notificationManager = getNotificationManager()) == null) {
            return;
        }
        Companion.getClass();
        if ((notificationManager.getConsolidatedNotificationPolicy().priorityCategories & 64) == 0) {
            Handler mainThreadHandler = this.context.getMainThreadHandler();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            mainThreadHandler.postDelayed(anonymousClass1, SeparateModeHelper.INSTANCE.getSeparateMode() ? 300L : 0L);
        }
    }
}
