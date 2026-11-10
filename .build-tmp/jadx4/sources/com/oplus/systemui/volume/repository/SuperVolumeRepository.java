package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import androidx.collection.FloatList$;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.utils.OplusSuperVolumeUtil;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperVolumeRepository {
    private static final String SUPER_VOLUME_ALARM_SPEAKER = "super_volume_alarm_speaker";
    private static final String SUPER_VOLUME_CALL_EARPIECE = "super_volume_call_earpiece";
    private static final String SUPER_VOLUME_CALL_SPEAKER = "super_volume_call_speaker";
    private static final String SUPER_VOLUME_MUSIC_SPEAKER = "super_volume_music_speaker";
    private static final String SUPER_VOLUME_NOTIFICATION_SPEAKER = "super_volume_notification_speaker";
    private static final String SUPER_VOLUME_RING_SPEAKER = "super_volume_ring_speaker";
    public static final String TAG = "SuperVolumeRepository";
    private final Context context;
    private final CoroutineScope repositoryScope;
    private final SharedFlow settingChanges;
    private final OplusVolumeDialogImpl volumeInteractor;
    public static final Companion Companion = new Companion();
    private static final int[] mSuperVolumeRows = {3, 2, 5, 4, 0};
    private final Uri mMusicSuperVolumeUri = Settings.System.getUriFor(SUPER_VOLUME_MUSIC_SPEAKER);
    private final Uri mAlarmSuperVolumeUri = Settings.System.getUriFor(SUPER_VOLUME_ALARM_SPEAKER);
    private final Uri mRingSuperVolumeUri = Settings.System.getUriFor(SUPER_VOLUME_RING_SPEAKER);
    private final Uri mNotificationSuperVolumeUri = Settings.System.getUriFor(SUPER_VOLUME_NOTIFICATION_SPEAKER);
    private final Uri mCallSuperVolumeUri = Settings.System.getUriFor(SUPER_VOLUME_CALL_EARPIECE);
    private final Uri mCallSuperVolumeSpeakerUri = Settings.System.getUriFor(SUPER_VOLUME_CALL_SPEAKER);
    public int mSupervolumeStringId = 2131956725;
    private final MutableStateFlow _superVolumeStates = StateFlowKt.MutableStateFlow(MapsKt__MapsKt.emptyMap());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int[] getMSuperVolumeRows() {
            return SuperVolumeRepository.mSuperVolumeRows;
        }

        public final boolean readMusicSuperVolumeState(Context context) {
            return Settings.System.getIntForUser(context.getContentResolver(), SuperVolumeRepository.SUPER_VOLUME_MUSIC_SPEAKER, 0, -2) == 1;
        }
    }

    public SuperVolumeRepository(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getDefault()));
        this.repositoryScope = CoroutineScope;
        SharedFlow sharedFlowShareIn = FlowKt.shareIn(FlowKt.callbackFlow(new SuperVolumeRepository$settingChanges$1(this, null)), CoroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 5000L, 2), 0);
        this.settingChanges = sharedFlowShareIn;
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            FlowKt.launchIn(FlowKt.onEach(sharedFlowShareIn, new SuperVolumeRepository$observeSettings$1(this, null)), CoroutineScope);
        }
    }

    public static Pair cacheKey(int i, int i2) {
        return i == 0 ? TuplesKt.to(Integer.valueOf(i), Integer.valueOf(i2)) : TuplesKt.to(Integer.valueOf(i), 0);
    }

    public final int getSuperCallStreamVolumeDevice(int i, AudioManager audioManager, boolean z) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice() && z) {
            return 0;
        }
        int devicesForStream = audioManager.getDevicesForStream(i);
        boolean z2 = devicesForStream == 1;
        boolean z3 = devicesForStream == 2;
        if (z2) {
            return 1;
        }
        if (FlavorOneFeatureOption.isFlavorOneDevice() && z3) {
            return 2;
        }
        return devicesForStream;
    }

    public final boolean getSuperVolumeState(int i, int i2) {
        Pair pairCacheKey = cacheKey(i, i2);
        Map map = (Map) this._superVolumeStates.getValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Object obj = map.get(pairCacheKey);
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getSuperVolumeState stream=", i, i2, " superCallDevice=", " key=");
            sbM.append(pairCacheKey);
            sbM.append(" currentState=");
            sbM.append(map);
            sbM.append(" cachedValue=");
            sbM.append(obj);
            Log.d("SystemUi--Volume", "SuperVolumeRepository-->".concat(sbM.toString()));
        }
        Boolean bool = (Boolean) map.get(pairCacheKey);
        return bool != null ? bool.booleanValue() : readSuperVolumeStateFromSettings(i, i2);
    }

    public final void handleStreamChange(int i, int i2) {
        Object value;
        Map mutableMap;
        boolean superVolumeStateFromSettings = readSuperVolumeStateFromSettings(i, i2);
        StateFlowImpl stateFlowImpl = this._superVolumeStates;
        do {
            value = stateFlowImpl.getValue();
            mutableMap = MapsKt__MapsKt.toMutableMap((Map) value);
            mutableMap.put(cacheKey(i, i2), Boolean.valueOf(superVolumeStateFromSettings));
        } while (!stateFlowImpl.compareAndSet(value, mutableMap));
        BuildersKt.launch$default(this.repositoryScope, Dispatchers.getMain(), (CoroutineStart) null, new handleStreamChange.2(this, i, (Continuation) null), 2);
        if (superVolumeStateFromSettings) {
            BuildersKt.launch$default(this.repositoryScope, Dispatchers.getMain(), (CoroutineStart) null, new handleStreamChange.3(this, (Continuation) null), 2);
        }
    }

    public final void initSuperVolume(int i) {
        Object value;
        Map mapCreateMapBuilder;
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            StateFlowImpl stateFlowImpl = this._superVolumeStates;
            do {
                value = stateFlowImpl.getValue();
                mapCreateMapBuilder = MapsKt__MapsJVMKt.createMapBuilder();
                mapCreateMapBuilder.putAll((Map) value);
                mapCreateMapBuilder.put(cacheKey(3, 0), Boolean.valueOf(getSuperVolumeState(3, 0)));
                mapCreateMapBuilder.put(cacheKey(4, 0), Boolean.valueOf(getSuperVolumeState(4, 0)));
                mapCreateMapBuilder.put(cacheKey(2, 0), Boolean.valueOf(getSuperVolumeState(2, 0)));
                mapCreateMapBuilder.put(cacheKey(5, 0), Boolean.valueOf(getSuperVolumeState(5, 0)));
                VolumeFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.isOplusSuperVolumeCall()) {
                    mapCreateMapBuilder.put(cacheKey(0, i), Boolean.valueOf(getSuperVolumeState(0, i)));
                }
            } while (!stateFlowImpl.compareAndSet(value, MapsKt__MapsJVMKt.build(mapCreateMapBuilder)));
        }
    }

    public final boolean readSuperVolumeStateFromSettings(int i, int i2) {
        String str;
        if (i != 0) {
            if (i == 2) {
                str = SUPER_VOLUME_RING_SPEAKER;
            } else if (i == 3) {
                str = SUPER_VOLUME_MUSIC_SPEAKER;
            } else if (i == 4) {
                str = SUPER_VOLUME_ALARM_SPEAKER;
            } else {
                if (i != 5) {
                    return false;
                }
                str = SUPER_VOLUME_NOTIFICATION_SPEAKER;
            }
        } else if (i2 != 1) {
            str = i2 != 2 ? "" : SUPER_VOLUME_CALL_SPEAKER;
        } else {
            str = SUPER_VOLUME_CALL_EARPIECE;
        }
        if (str.length() == 0) {
            return false;
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        return (FlavorOneFeatureOption.isFlavorOneDevice() || !SUPER_VOLUME_CALL_SPEAKER.equals(str)) && Settings.System.getIntForUser(this.context.getContentResolver(), str, -1, -2) == 1;
    }

    public final void showSuperVolume(boolean z, OplusVolumeRow oplusVolumeRow) {
        int oplusSuperVolumeLevel;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("showSuperVolume isShow=", oplusVolumeRow.getStream(), " ,row stream=", "SuperVolumeRepository-->", z));
        }
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
            if ((oplusVolumeRow.getStream() != 3 && oplusVolumeRow.getStream() != 2 && oplusVolumeRow.getStream() != 4 && oplusVolumeRow.getStream() != 5) || (oplusSuperVolumeLevel = OplusSuperVolumeUtil.getOplusSuperVolumeLevel()) == 200) {
                this.mSupervolumeStringId = 2131956725;
            } else if (oplusSuperVolumeLevel == 300) {
                this.mSupervolumeStringId = 2131956726;
            } else if (oplusSuperVolumeLevel == 400) {
                this.mSupervolumeStringId = 2131956727;
            }
            if (!z) {
                COUIVerticalSeekBar slider = oplusVolumeRow.getSlider();
                if (slider != null) {
                    slider.mShowText = false;
                    slider.invalidate();
                }
                OplusVolumeSeekBar slider2 = oplusVolumeRow.getSlider();
                if (slider2 != null) {
                    slider2.setIsShowSuperVolume(false);
                    return;
                }
                return;
            }
            OplusVolumeSeekBar slider3 = oplusVolumeRow.getSlider();
            if (slider3 == null || slider3.getThickType() != 0) {
                COUIVerticalSeekBar slider4 = oplusVolumeRow.getSlider();
                if (slider4 != null) {
                    slider4.mShowText = false;
                    slider4.invalidate();
                }
            } else {
                COUIVerticalSeekBar slider5 = oplusVolumeRow.getSlider();
                if (slider5 != null) {
                    slider5.mShowText = true;
                    slider5.invalidate();
                }
                OplusVolumeSeekBar slider6 = oplusVolumeRow.getSlider();
                if (slider6 != null) {
                    slider6.setText(this.context.getString(this.mSupervolumeStringId));
                }
            }
            OplusVolumeSeekBar slider7 = oplusVolumeRow.getSlider();
            if (slider7 != null) {
                slider7.setIsShowSuperVolume(true);
            }
        }
    }
}
