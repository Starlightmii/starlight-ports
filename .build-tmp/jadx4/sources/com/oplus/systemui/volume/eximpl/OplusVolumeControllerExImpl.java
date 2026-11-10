package com.oplus.systemui.volume.eximpl;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.accessibility.CaptioningManager;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.model.SysUiState;
import com.android.systemui.model.SysUiStateImpl;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.android.systemui.volume.VolumeDialogControllerImpl;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.common.receiver.HeadsetPlugReceiver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeControllerExImpl implements OplusVolumeControllerEx {
    private static final String TAG = "OplusVolumeControllerExImpl";
    private final IReceiverListener headsetPlugReceiver;
    private AudioManager mAudio;
    private VolumeDialogControllerImpl.C mCallbacks;
    private Context mContext;
    private Integer mDisplayId;
    private final SparseIntArray mLastKnownStreamLevel;
    private final VolumeDialogController.State mState;
    private SysUiState mSysUiState;
    public boolean mUserSwitch;
    private VolumeDialogController mVolumeDialogControllerImpl;
    public int musicDevicesForStreamCache;
    public int voiceCallDevicesForStreamCache;
    private Handler workHandler;
    public static final Companion Companion = new Companion();
    private static final List<Integer> VOLUME_KEY_UP_STATE = CollectionsKt__CollectionsKt.listOf(new Integer[]{4112, 4116});
    public static final AudioAttributes sAudioAttributesForMusic = new AudioAttributes.Builder().setUsage(1).build();
    public static final AudioAttributes sAudioAttributesForCalls = new AudioAttributes.Builder().setUsage(2).build();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusVolumeSettingObserver extends ContentObserver {
        private final VolumeDialogControllerImpl.C callbacks;
        private final Context context;
        private final Uri mVibrateWhenRingingURI;
        private final VolumeDialogController.State state;

        public OplusVolumeSettingObserver(Context context, VolumeDialogControllerImpl.C c, Handler handler, VolumeDialogController.State state) {
            super(handler);
            this.context = context;
            this.callbacks = c;
            this.state = state;
            Uri uriFor = Settings.System.getUriFor("ring_vibration_intensity");
            this.mVibrateWhenRingingURI = uriFor;
            ObserverDispatcher.getInstance().registerObserver(uriFor, this, context.getUserId());
        }

        public final VolumeDialogControllerImpl.C getCallbacks() {
            return this.callbacks;
        }

        public final Context getContext() {
            return this.context;
        }

        public final VolumeDialogController.State getState() {
            return this.state;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (Intrinsics.areEqual(this.mVibrateWhenRingingURI, uri)) {
                int intForUser = Settings.System.getIntForUser(this.context.getContentResolver(), "ring_vibration_intensity", 0, this.context.getUserId());
                VolumeDialogController.State state = this.state;
                if (state == null || state.ringingModeState != intForUser) {
                    if (state != null) {
                        state.ringingModeState = intForUser;
                    }
                    this.callbacks.onStateChanged(state);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.eximpl.OplusVolumeControllerExImpl$updateAudibleStreamVolume$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Collection $streams;
        public /* synthetic */ OplusVolumeControllerExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            AudioManager audioManager;
            int lastAudibleStreamVolume;
            SparseArray sparseArray = this.this$0.mState.states;
            if (sparseArray != null) {
                Collection collection = this.$streams;
                OplusVolumeControllerExImpl oplusVolumeControllerExImpl = this.this$0;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    VolumeDialogController.StreamState streamState = (VolumeDialogController.StreamState) sparseArray.get(iIntValue);
                    if (streamState != null && (audioManager = oplusVolumeControllerExImpl.mAudio) != null && streamState.level != (lastAudibleStreamVolume = audioManager.getLastAudibleStreamVolume(iIntValue))) {
                        streamState.level = lastAudibleStreamVolume;
                        oplusVolumeControllerExImpl.mCallbacks.onStateChanged(oplusVolumeControllerExImpl.mState, iIntValue);
                    }
                }
            }
        }
    }

    public OplusVolumeControllerExImpl(Context context, VolumeDialogController volumeDialogController) {
        Display display;
        this.mVolumeDialogControllerImpl = volumeDialogController;
        this.mContext = context;
        VolumeDialogController.State myState = volumeDialogController.getMyState();
        this.mState = myState;
        this.mCallbacks = new VolumeDialogControllerImpl.C();
        this.mLastKnownStreamLevel = new SparseIntArray();
        this.mAudio = this.mVolumeDialogControllerImpl.getAudioManager();
        new OplusVolumeSettingObserver(context, this.mCallbacks, this.workHandler, myState);
        this.mSysUiState = (SysUiState) Dependency.sDependency.getDependencyInner(SysUiState.class);
        Context context2 = this.mContext;
        this.mDisplayId = (context2 == null || (display = context2.getDisplay()) == null) ? null : Integer.valueOf(display.getDisplayId());
        OplusVolumeControllerExImpl$headsetPlugReceiver$1 oplusVolumeControllerExImpl$headsetPlugReceiver$1 = new OplusVolumeControllerExImpl$headsetPlugReceiver$1();
        oplusVolumeControllerExImpl$headsetPlugReceiver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.headsetPlugReceiver = oplusVolumeControllerExImpl$headsetPlugReceiver$1;
    }

    public static final void access$notifyStateChanged(OplusVolumeControllerExImpl oplusVolumeControllerExImpl) {
        Handler handler = oplusVolumeControllerExImpl.workHandler;
        if (handler != null) {
            notifyStateChanged.1 r1 = new notifyStateChanged.1();
            r1.this$0 = oplusVolumeControllerExImpl;
            VarHandle.storeStoreFence();
            handler.post(r1);
        }
    }

    public void addCallback(VolumeDialogController.Callbacks callbacks, Handler handler) {
        VolumeDialogControllerImpl.C c = this.mCallbacks;
        c.getClass();
        if (callbacks == null || handler == null) {
            Path$.ExternalSyntheticBUOutline0.m$1();
        } else {
            c.mCallbackMap.put(callbacks, handler);
        }
    }

    public final void afterSetStreamVolume(int i, int i2) {
        this.mLastKnownStreamLevel.put(i, i2);
    }

    public final boolean areCaptionsEnabled() {
        Context context = this.mContext;
        context.getClass();
        return Settings.Secure.getIntForUser(context.getContentResolver(), "odi_captions_enabled", 0, -2) == 1;
    }

    public final int getDevicesForStreamWithCache(int i, boolean z) {
        AudioManager audioManager = this.mAudio;
        int internalType = 0;
        if (audioManager != null) {
            if (i == 0) {
                if (!z) {
                    Iterator it = audioManager.getDevicesForAttributes(sAudioAttributesForCalls).iterator();
                    while (it.hasNext()) {
                        internalType |= ((AudioDeviceAttributes) it.next()).getInternalType();
                    }
                    this.voiceCallDevicesForStreamCache = internalType;
                }
                return this.voiceCallDevicesForStreamCache;
            }
            if (i == 3 || i == 11) {
                if (!z) {
                    Iterator it2 = audioManager.getDevicesForAttributes(sAudioAttributesForMusic).iterator();
                    while (it2.hasNext()) {
                        internalType |= ((AudioDeviceAttributes) it2.next()).getInternalType();
                    }
                    this.musicDevicesForStreamCache = internalType;
                }
                return this.musicDevicesForStreamCache;
            }
        }
        return 0;
    }

    public final Integer getMDisplayId() {
        return this.mDisplayId;
    }

    public final boolean getMUserSwitch() {
        return this.mUserSwitch;
    }

    public boolean getUserSwitch() {
        return this.mUserSwitch;
    }

    public void onGetCaptionsComponentStateW(boolean z, CaptioningManager captioningManager) {
        try {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isExpRegion()) {
                this.mCallbacks.onCaptionComponentStateChanged(Boolean.FALSE, Boolean.valueOf(z));
                return;
            }
            if (captioningManager == null) {
                LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "onGetCaptionsComponentStateW(), null captioningManager");
                return;
            }
            boolean zIsSystemAudioCaptioningUiEnabled = captioningManager.isSystemAudioCaptioningUiEnabled();
            this.mCallbacks.onCaptionComponentStateChanged(Boolean.valueOf(zIsSystemAudioCaptioningUiEnabled), Boolean.valueOf(z));
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "onGetCaptionsComponentStateW audioCaptioningUiEnable= " + zIsSystemAudioCaptioningUiEnabled, null, 8);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_VOLUME, TAG, "isCaptionsServiceEnabled failed to check for captions component", e);
            this.mCallbacks.onCaptionComponentStateChanged(Boolean.FALSE, Boolean.valueOf(z));
        }
    }

    public final void onVolumeChangeW(int i, boolean z, boolean z2, boolean z3) {
        if (!z) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice() && !VOLUME_KEY_UP_STATE.contains(Integer.valueOf(i))) {
                this.mCallbacks.onVolumeKeyStateChange(z3);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", "OplusVolumeControllerExImpl-->onVolumeKeyStateChange");
                }
            }
        }
        if (z || !z2) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeControllerExImpl-->onVolumeKeyUp");
        }
        this.mCallbacks.onVolumeKeyStateChange(false);
        this.mCallbacks.onVolumeKeyUp();
    }

    public void register(Handler handler) {
        this.workHandler = handler;
        Context context = this.mContext;
        if (context != null) {
            HeadsetPlugReceiver.Companion.getInstance().register(context, UserHandle.ALL, false);
        }
        HeadsetPlugReceiver.Companion.getInstance().addListener(this.headsetPlugReceiver);
    }

    public void setCaptionsEnabled(boolean z) {
        Context context = this.mContext;
        context.getClass();
        Settings.Secure.putIntForUser(context.getContentResolver(), "odi_captions_enabled", z ? 1 : 0, -2);
    }

    public final void setMDisplayId(Integer num) {
        this.mDisplayId = num;
    }

    public final void setMUserSwitch(boolean z) {
        this.mUserSwitch = z;
    }

    public void setPanelExpandedSysUiFlag(boolean z) {
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.getClass();
        SysUiStateImpl flag = sysUiState.setFlag(2199023255552L, z);
        Integer num = this.mDisplayId;
        num.getClass();
        flag.commitUpdate(num.intValue());
    }

    public void setScoMaxLevel(boolean z) {
        SparseArray sparseArray;
        VolumeDialogController.StreamState streamState;
        SparseArray sparseArray2;
        VolumeDialogController.StreamState streamState2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setScoMaxLevel sco", "OplusVolumeControllerExImpl-->", "SystemUi--Volume", z);
        }
        VolumeDialogController.State state = this.mState;
        if (z) {
            if (state != null && (sparseArray2 = state.states) != null && (streamState2 = (VolumeDialogController.StreamState) sparseArray2.get(0)) != null) {
                streamState2.levelMax = 15;
            }
        } else if (state != null && (sparseArray = state.states) != null && (streamState = (VolumeDialogController.StreamState) sparseArray.get(0)) != null) {
            streamState.levelMax = 9;
        }
        this.mCallbacks.onStateChanged(this.mState, 0);
    }

    public void setUserSwitch(boolean z) {
        this.mUserSwitch = z;
    }

    public final boolean shouldSkipSetStreamVolume(int i, int i2) {
        if (this.mLastKnownStreamLevel.get(i, -1) != i2) {
            return false;
        }
        AudioManager audioManager = this.mAudio;
        int lastAudibleStreamVolume = audioManager != null ? audioManager.getLastAudibleStreamVolume(i) : -1;
        if (lastAudibleStreamVolume != i2) {
            this.mLastKnownStreamLevel.put(i, lastAudibleStreamVolume);
            return false;
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            return true;
        }
        Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("shouldSkipSetStreamVolume skip same level: stream=", i, i2, " level=", "OplusVolumeControllerExImpl-->"));
        return true;
    }

    public void updateAudibleStreamVolume(Collection<Integer> collection) {
        Handler handler = this.workHandler;
        if (handler != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$streams = collection;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }

    public final boolean updateStreamRoutedToWiredW(int i, boolean z) {
        VolumeDialogController.StreamState streamState = (VolumeDialogController.StreamState) this.mState.states.get(i);
        if (streamState == null) {
            streamState = new VolumeDialogController.StreamState();
            this.mState.states.put(i, streamState);
        }
        if (streamState.routedToWired == z) {
            return false;
        }
        streamState.routedToWired = z;
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            return true;
        }
        Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("updateStreamRoutedToWiredW stream= ", i, ", routedToWired=", "OplusVolumeControllerExImpl-->", z));
        return true;
    }
}
