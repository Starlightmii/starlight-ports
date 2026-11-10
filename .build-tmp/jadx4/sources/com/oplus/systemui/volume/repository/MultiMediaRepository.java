package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.common.receiver.ScreenStateReceiver;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.VolumeListAdapter;
import com.oplus.systemui.volume.observer.MultiMediaSettingsObserver;
import com.oplus.systemui.volume.utils.MultiVolumeKeyStateListener;
import com.oplus.systemui.volume.utils.OplusVolumeExKt;
import com.oplus.systemui.volume.utils.VolumeStatisticsUtil;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiMediaRepository {
    public static final Companion Companion = new Companion();
    public static final String MULTI_MEDIA_PACKAGE_SUFFIX = "_volume";
    public static final String MULTI_MEDIA_SET_TRACK_VOLUME = "OPLUS_AUDIO_SET_TRACKVOLUME:";
    private static final String SETTINGS_MULTI_MEDIA_HINT_STATE = "multi_app_volume_hint_state";
    public static final String TAG = "MultiMediaRepository";
    private final Lazy audioPlaybackCallback$delegate;
    private final Context context;
    public boolean deferPanelRefresh;
    public volatile boolean destroyed;
    private AudioManager mAudioManager;
    private final IObserverListener mMultiMediaSettingObserverListener;
    private final HashMap<Integer, AppInfoCacheEntry> pidToAppInfoCache;
    private final Lazy powerManager$delegate;
    private final IReceiverListener screenStateListener;
    public boolean started;
    private final Runnable syncPlaybackUiRunnable;
    private final OplusVolumeDialogImpl volumeInteractor;
    private final Lazy workerHandler$delegate;
    private final List<OplusVolumeRow> multiMediaRows = new ArrayList();
    public boolean needRecheckMultiMediaState = true;
    private HashMap<Integer, HashSet<Integer>> multiMediaMap = new HashMap<>();
    private volatile Set<Integer> lastActivePidSet = SetsKt__SetsKt.emptySet();
    private volatile Map<Integer, ? extends Set<Integer>> playerIdsSnapshot = MapsKt__MapsKt.emptyMap();
    private volatile List<AudioPlaybackConfiguration> pendingActivePlaybackConfigs = CollectionsKt__CollectionsKt.emptyList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.repository.MultiMediaRepository$stop$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ MultiMediaRepository this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            SparseArray sparseArray;
            int i = 0;
            switch (this.$r8$classId) {
                case 0:
                    MultiMediaRepository.access$syncMultiMediaUiWithActivePlaybackConfigs(this.this$0, CollectionsKt__CollectionsKt.emptyList(), null, true);
                    break;
                case 1:
                    MultiMediaRepository multiMediaRepository = this.this$0;
                    multiMediaRepository.needRecheckMultiMediaState = true;
                    multiMediaRepository.volumeInteractor.mDialogShowingForMultiMedia = false;
                    Iterator it = this.this$0.multiMediaMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        int iIntValue = ((Number) entry.getKey()).intValue();
                        HashSet hashSet = (HashSet) entry.getValue();
                        if (hashSet == null || hashSet.isEmpty()) {
                            it.remove();
                            Iterator<OplusVolumeRow> it2 = this.this$0.getMultiMediaRows().iterator();
                            while (it2.hasNext()) {
                                OplusVolumeRow next = it2.next();
                                if (next.getStream() == iIntValue) {
                                    this.this$0.volumeInteractor.getVolumeListAdapter().removeItem(next);
                                    it2.remove();
                                    VolumeDialogController.State state = this.this$0.volumeInteractor.mState;
                                    if (state != null && (sparseArray = state.states) != null) {
                                        sparseArray.remove(next.getStream());
                                    }
                                }
                            }
                        }
                    }
                    MultiVolumeKeyStateListener.Companion.getInstance(this.this$0.getContext()).setKeyEventState(false);
                    break;
                case 2:
                    MultiMediaRepository multiMediaRepository2 = this.this$0;
                    MultiMediaRepository$$ExternalSyntheticLambda0 multiMediaRepository$$ExternalSyntheticLambda0 = new MultiMediaRepository$$ExternalSyntheticLambda0(3);
                    multiMediaRepository$$ExternalSyntheticLambda0.f$0 = multiMediaRepository2;
                    VarHandle.storeStoreFence();
                    if (!multiMediaRepository2.destroyed) {
                        List listQueryActiveMediaPlaybackConfigsFromService = multiMediaRepository2.queryActiveMediaPlaybackConfigsFromService();
                        MultiMediaRepository$prepareMediaConfigs$2 multiMediaRepository$prepareMediaConfigs$2 = new MultiMediaRepository$prepareMediaConfigs$2(i);
                        multiMediaRepository$prepareMediaConfigs$2.this$0 = multiMediaRepository2;
                        multiMediaRepository$prepareMediaConfigs$2.$activeConfigs = listQueryActiveMediaPlaybackConfigsFromService;
                        multiMediaRepository$prepareMediaConfigs$2.$onComplete = multiMediaRepository$$ExternalSyntheticLambda0;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(multiMediaRepository$prepareMediaConfigs$2);
                    } else {
                        prepareMediaConfigs.1 r6 = new prepareMediaConfigs.1(0);
                        r6.$onComplete = multiMediaRepository$$ExternalSyntheticLambda0;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(r6);
                    }
                    break;
                default:
                    List list = this.this$0.pendingActivePlaybackConfigs;
                    this.this$0.pendingActivePlaybackConfigs = CollectionsKt__CollectionsKt.emptyList();
                    if (!list.isEmpty() && !this.this$0.destroyed) {
                        MultiMediaRepository multiMediaRepository3 = this.this$0;
                        RunnableC03091 runnableC03091 = new RunnableC03091(2);
                        runnableC03091.this$0 = multiMediaRepository3;
                        runnableC03091.$onComplete = list;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(runnableC03091);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.repository.MultiMediaRepository$updateHMultiMediaRows$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC03091 implements Runnable {
        public /* synthetic */ Object $onComplete;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ RunnableC03091(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            switch (this.$r8$classId) {
                case 0:
                    MultiMediaRepository multiMediaRepository = (MultiMediaRepository) this.this$0;
                    Function0 function0 = (Function0) this.$onComplete;
                    int i = 1;
                    if (!multiMediaRepository.destroyed) {
                        List listQueryActiveMediaPlaybackConfigsFromService = multiMediaRepository.queryActiveMediaPlaybackConfigsFromService();
                        MultiMediaRepository$prepareMediaConfigs$2 multiMediaRepository$prepareMediaConfigs$2 = new MultiMediaRepository$prepareMediaConfigs$2(i);
                        multiMediaRepository$prepareMediaConfigs$2.this$0 = multiMediaRepository;
                        multiMediaRepository$prepareMediaConfigs$2.$activeConfigs = listQueryActiveMediaPlaybackConfigsFromService;
                        multiMediaRepository$prepareMediaConfigs$2.$onComplete = function0;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(multiMediaRepository$prepareMediaConfigs$2);
                    } else {
                        prepareMediaConfigs.1 r0 = new prepareMediaConfigs.1(1);
                        r0.$onComplete = function0;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(r0);
                    }
                    break;
                case 1:
                    ((MultiMediaRepository$.ExternalSyntheticLambda3) this.this$0).invoke((List) this.$onComplete);
                    break;
                default:
                    if (!((MultiMediaRepository) this.this$0).destroyed) {
                        MultiMediaRepository.access$syncMultiMediaUiWithActivePlaybackConfigs((MultiMediaRepository) this.this$0, (List) this.$onComplete, null, false);
                        break;
                    }
                    break;
            }
        }
    }

    public static PowerManager $r8$lambda$5CzcRE5hCJHNNM4BO_veLrLMU4w(MultiMediaRepository multiMediaRepository) {
        Object systemService = multiMediaRepository.context.getSystemService("power");
        if (systemService instanceof PowerManager) {
            return (PowerManager) systemService;
        }
        return null;
    }

    public MultiMediaRepository(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(3);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.syncPlaybackUiRunnable = anonymousClass1;
        this.pidToAppInfoCache = new HashMap<>();
        MultiMediaRepository$$ExternalSyntheticLambda0 multiMediaRepository$$ExternalSyntheticLambda0 = new MultiMediaRepository$$ExternalSyntheticLambda0(0);
        multiMediaRepository$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.workerHandler$delegate = LazyKt__LazyJVMKt.lazy(multiMediaRepository$$ExternalSyntheticLambda0);
        MultiMediaRepository$$ExternalSyntheticLambda0 multiMediaRepository$$ExternalSyntheticLambda1 = new MultiMediaRepository$$ExternalSyntheticLambda0(1);
        multiMediaRepository$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.powerManager$delegate = LazyKt__LazyJVMKt.lazy(multiMediaRepository$$ExternalSyntheticLambda1);
        MultiMediaRepository$screenStateListener$1 multiMediaRepository$screenStateListener$1 = new MultiMediaRepository$screenStateListener$1();
        multiMediaRepository$screenStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.screenStateListener = multiMediaRepository$screenStateListener$1;
        MultiMediaRepository$$ExternalSyntheticLambda0 multiMediaRepository$$ExternalSyntheticLambda2 = new MultiMediaRepository$$ExternalSyntheticLambda0(2);
        multiMediaRepository$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.audioPlaybackCallback$delegate = LazyKt__LazyJVMKt.lazy(multiMediaRepository$$ExternalSyntheticLambda2);
        MultiMediaRepository$mMultiMediaSettingObserverListener$1 multiMediaRepository$mMultiMediaSettingObserverListener$1 = new MultiMediaRepository$mMultiMediaSettingObserverListener$1();
        multiMediaRepository$mMultiMediaSettingObserverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMultiMediaSettingObserverListener = multiMediaRepository$mMultiMediaSettingObserverListener$1;
        Object systemService = context.getSystemService("audio");
        this.mAudioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            MultiMediaSettingsObserver.Companion companion = MultiMediaSettingsObserver.Companion;
            companion.getInstance().registerObserver(context, multiMediaRepository$mMultiMediaSettingObserverListener$1);
            ScreenStateReceiver.getInstance().register(context);
            ScreenStateReceiver.getInstance().addListener(multiMediaRepository$screenStateListener$1);
            if (!companion.getInstance().isMultiMediaSettingsEnabled) {
                stop();
            } else if (isScreenInteractive()) {
                start();
            } else {
                stop();
            }
        }
    }

    public static final void access$pruneExpiredCache(MultiMediaRepository multiMediaRepository, long j) {
        Iterator<Map.Entry<Integer, AppInfoCacheEntry>> it = multiMediaRepository.pidToAppInfoCache.entrySet().iterator();
        while (it.hasNext()) {
            if (j - it.next().getValue().getCachedAt() > 30000) {
                it.remove();
            }
        }
    }

    public static final void access$syncMultiMediaUiWithActivePlaybackConfigs(MultiMediaRepository multiMediaRepository, List list, Function0 function0, boolean z) throws Throwable {
        multiMediaRepository.getClass();
        HashSet hashSet = new HashSet(list.size());
        HashMap map = new HashMap(list.size());
        HashMap map2 = new HashMap(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AudioPlaybackConfiguration audioPlaybackConfiguration = (AudioPlaybackConfiguration) it.next();
            int clientPid = audioPlaybackConfiguration.getClientPid();
            hashSet.add(Integer.valueOf(clientPid));
            if (!map.containsKey(Integer.valueOf(clientPid))) {
                map.put(Integer.valueOf(clientPid), audioPlaybackConfiguration);
            }
            Integer numValueOf = Integer.valueOf(clientPid);
            Object hashSet2 = map2.get(numValueOf);
            if (hashSet2 == null) {
                hashSet2 = new HashSet();
                map2.put(numValueOf, hashSet2);
            }
            ((HashSet) hashSet2).add(Integer.valueOf(audioPlaybackConfiguration.getPlayerInterfaceId()));
        }
        multiMediaRepository.multiMediaMap.clear();
        multiMediaRepository.multiMediaMap.putAll(map2);
        multiMediaRepository.lastActivePidSet = hashSet;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map2.size()));
        for (Map.Entry entry : map2.entrySet()) {
            linkedHashMap.put(entry.getKey(), CollectionsKt___CollectionsKt.toSet((Iterable) entry.getValue()));
        }
        multiMediaRepository.playerIdsSnapshot = linkedHashMap;
        HashSet hashSet3 = new HashSet(multiMediaRepository.multiMediaRows.size());
        Iterator<OplusVolumeRow> it2 = multiMediaRepository.multiMediaRows.iterator();
        while (it2.hasNext()) {
            hashSet3.add(Integer.valueOf(it2.next().getStream()));
        }
        boolean zIsShowing = multiMediaRepository.volumeInteractor.isShowing();
        List<OplusVolumeRow> list2 = multiMediaRepository.multiMediaRows;
        boolean z2 = false;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it3 = list2.iterator();
            while (it3.hasNext()) {
                if (!hashSet.contains(Integer.valueOf(((OplusVolumeRow) it3.next()).getStream())) && (z || !zIsShowing)) {
                    z2 = true;
                    break;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : hashSet) {
            if (!hashSet3.contains(Integer.valueOf(((Number) obj).intValue()))) {
                arrayList.add(obj);
            }
        }
        if (!z2 && arrayList.isEmpty()) {
            multiMediaRepository.volumeInteractor.updateAppVolumeAdjust();
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        if (arrayList.isEmpty()) {
            multiMediaRepository.syncMultiMediaUiWithPreparedData(CollectionsKt__CollectionsKt.emptyList(), hashSet, z, zIsShowing, function0);
            return;
        }
        MultiMediaRepository$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new MultiMediaRepository$.ExternalSyntheticLambda3();
        externalSyntheticLambda3.f$0 = multiMediaRepository;
        externalSyntheticLambda3.f$1 = hashSet;
        externalSyntheticLambda3.f$2 = z;
        externalSyntheticLambda3.f$3 = zIsShowing;
        externalSyntheticLambda3.f$4 = function0;
        VarHandle.storeStoreFence();
        Handler workerHandler = multiMediaRepository.getWorkerHandler();
        Runnable runnable = new prepareMediaAppDataAsync.1();
        ((prepareMediaAppDataAsync.1) runnable).this$0 = multiMediaRepository;
        ((prepareMediaAppDataAsync.1) runnable).$pidsToAdd = arrayList;
        ((prepareMediaAppDataAsync.1) runnable).$sampleConfigByPid = map;
        ((prepareMediaAppDataAsync.1) runnable).$callback = externalSyntheticLambda3;
        VarHandle.storeStoreFence();
        workerHandler.post(runnable);
    }

    public static void addMultiMediaRow$default(MultiMediaRepository multiMediaRepository, MediaAppInfo mediaAppInfo) {
        VolumeDialogController.StreamState streamState;
        int pid = mediaAppInfo.getPid();
        String pkg = mediaAppInfo.getPkg();
        String label = mediaAppInfo.getLabel();
        int uid = mediaAppInfo.getUid();
        int playerId = mediaAppInfo.getPlayerId();
        int oldLevel = mediaAppInfo.getOldLevel();
        OplusVolumeRow oplusVolumeRow = new OplusVolumeRow(multiMediaRepository.context, multiMediaRepository.volumeInteractor, pid, 2131234630, 2131234630, false, false);
        VolumeDialogController.State state = multiMediaRepository.volumeInteractor.mState;
        if (state != null) {
            streamState = (VolumeDialogController.StreamState) state.states.get(pid);
            if (streamState == null) {
                streamState = new VolumeDialogController.StreamState();
                streamState.level = oldLevel == -100 ? 100 : oldLevel;
                streamState.levelMax = 100;
                streamState.levelMin = 0;
                multiMediaRepository.volumeInteractor.mState.states.put(pid, streamState);
            }
        } else {
            streamState = null;
        }
        oplusVolumeRow.setStartTimestamp(SystemClock.elapsedRealtime());
        oplusVolumeRow.setSs(streamState);
        oplusVolumeRow.setMediaPackageName(pkg);
        oplusVolumeRow.setMultiMediaVolume(true);
        oplusVolumeRow.setUid(uid);
        oplusVolumeRow.setPlayerId(playerId);
        VolumeDialogController.StreamState ss = oplusVolumeRow.getSs();
        if (ss != null) {
            ss.remoteLabel = label;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "MultiMediaRepository-->".concat("addMultiMediaRow stream: " + oplusVolumeRow.getStream() + " ,labelName: " + label));
        }
        oplusVolumeRow.stream = pid;
        oplusVolumeRow.iconRes = 2131234630;
        oplusVolumeRow.iconMuteRes = 2131234630;
        oplusVolumeRow.important = false;
        oplusVolumeRow.defaultStream = false;
        oplusVolumeRow.initRow();
        multiMediaRepository.multiMediaRows.add(oplusVolumeRow);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            int stream = oplusVolumeRow.getStream();
            int uid2 = oplusVolumeRow.getUid();
            int size = multiMediaRepository.multiMediaRows.size();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("addMultiMediaRow add row.stream:", stream, uid2, ",row.uid:", ",pid:");
            RectList$.ExternalSyntheticOutline0.m(pid, oldLevel, ",oldLevel:", ",mMultiMediaRows size:=", sbM);
            MobileInputLogger$.ExternalSyntheticOutline0.m(size, "MultiMediaRepository-->", "SystemUi--Volume", sbM);
        }
        multiMediaRepository.volumeInteractor.updateAppVolumeAdjust();
        if (oldLevel != -100) {
            Handler workerHandler = multiMediaRepository.getWorkerHandler();
            addMultiMediaRow.3 r4 = new addMultiMediaRow.3();
            r4.$oldLevel = oldLevel;
            r4.this$0 = multiMediaRepository;
            r4.$row = oplusVolumeRow;
            VarHandle.storeStoreFence();
            workerHandler.post(r4);
        }
        OplusVolumeRow oplusVolumeRowFindRow = multiMediaRepository.volumeInteractor.findRow(3);
        OplusVolumeRow oplusVolumeRowFindRow2 = multiMediaRepository.volumeInteractor.findRow(2);
        int iIndexOf = oplusVolumeRowFindRow != null ? multiMediaRepository.volumeInteractor.getVolumeListAdapter().indexOf(oplusVolumeRowFindRow) : 0;
        int iIndexOf2 = (iIndexOf != -1 || oplusVolumeRowFindRow2 == null) ? iIndexOf + 1 : multiMediaRepository.volumeInteractor.getVolumeListAdapter().indexOf(oplusVolumeRowFindRow2);
        multiMediaRepository.volumeInteractor.mOplusVolumeDialogView.convertRowLayout(oplusVolumeRow, true);
        View view = oplusVolumeRow.getView();
        if (view != null) {
            OplusVolumeExKt.setVisOrGone(view, true);
        }
        multiMediaRepository.volumeInteractor.getVolumeListAdapter().addItem(oplusVolumeRow, Integer.valueOf(iIndexOf2));
        if (!multiMediaRepository.deferPanelRefresh) {
            multiMediaRepository.notifyExpandedPanelRowsChangedIfNeeded();
        }
        StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m(oplusVolumeRow.getStream(), "addMultiMediaRow addView end stream: ", ",row.mediaPackageName: ", oplusVolumeRow.getMediaPackageName(), " multiMediaIndex:");
        sbM2.append(iIndexOf2);
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, sbM2.toString(), null, 8);
    }

    public static List filterActiveMediaPlaybackConfigs(List list) {
        int usage;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            AudioPlaybackConfiguration audioPlaybackConfiguration = (AudioPlaybackConfiguration) obj;
            if (audioPlaybackConfiguration.getPlayerState() == 2 && ((usage = audioPlaybackConfiguration.getAudioAttributes().getUsage()) == 1 || usage == 14 || usage == 0)) {
                if (audioPlaybackConfiguration.getAudioAttributes().getVolumeControlStream() == 3 && audioPlaybackConfiguration.getSampleRate() > 0 && audioPlaybackConfiguration.getChannelMask() > 0) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    private final AudioManager.AudioPlaybackCallback getAudioPlaybackCallback() {
        return (AudioManager.AudioPlaybackCallback) this.audioPlaybackCallback$delegate.getValue();
    }

    private final PowerManager getPowerManager() {
        return (PowerManager) this.powerManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler getWorkerHandler() {
        return (Handler) this.workerHandler$delegate.getValue();
    }

    public final void destroy() {
        this.destroyed = true;
        getWorkerHandler().removeCallbacks(this.syncPlaybackUiRunnable);
        this.pendingActivePlaybackConfigs = CollectionsKt__CollectionsKt.emptyList();
        MultiMediaSettingsObserver.Companion.getInstance().removeListener(this.mMultiMediaSettingObserverListener);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            ScreenStateReceiver.getInstance().removeListener(this.screenStateListener);
        }
        stop();
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<OplusVolumeRow> getMultiMediaRows() {
        return this.multiMediaRows;
    }

    public final void initMultiMediaRows() {
        if (this.multiMediaRows.isEmpty()) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "initDialog mMultiMediaRows isEmpty", null, 8);
            return;
        }
        for (OplusVolumeRow oplusVolumeRow : this.multiMediaRows) {
            oplusVolumeRow.stream = oplusVolumeRow.getStream();
            oplusVolumeRow.iconRes = 2131234630;
            oplusVolumeRow.iconMuteRes = 2131234630;
            oplusVolumeRow.important = false;
            oplusVolumeRow.defaultStream = false;
            oplusVolumeRow.initRow();
        }
    }

    public final boolean isScreenInteractive() {
        PowerManager powerManager = getPowerManager();
        return powerManager != null && powerManager.isInteractive();
    }

    public final void multiMediaProgressChange(OplusVolumeSeekBar oplusVolumeSeekBar, int i, OplusVolumeRow oplusVolumeRow) {
        float max = i / oplusVolumeSeekBar.getMax();
        Context context = this.context;
        String mediaPackageName = oplusVolumeRow.getMediaPackageName();
        VolumeDialogController.StreamState ss = oplusVolumeRow.getSs();
        Integer numValueOf = ss != null ? Integer.valueOf(ss.level) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(numValueOf);
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i);
        VolumeStatisticsUtil.uploadAppVolumeEvent(context, mediaPackageName, string, sb2.toString());
        VolumeDialogController.StreamState ss2 = oplusVolumeRow.getSs();
        if (ss2 != null) {
            ss2.level = i;
        }
        oplusVolumeRow.setLastLevel(i);
        Handler workerHandler = getWorkerHandler();
        multiMediaProgressChange.1 r6 = new multiMediaProgressChange.1();
        r6.this$0 = this;
        r6.$mRow = oplusVolumeRow;
        r6.$mProgress = max;
        VarHandle.storeStoreFence();
        workerHandler.post(r6);
        Settings.Global.putFloat(this.context.getContentResolver(), oplusVolumeRow.getMediaPackageName() + MULTI_MEDIA_PACKAGE_SUFFIX, max);
        if (Settings.System.getInt(this.context.getContentResolver(), SETTINGS_MULTI_MEDIA_HINT_STATE, -1) == -1) {
            Settings.System.putInt(this.context.getContentResolver(), SETTINGS_MULTI_MEDIA_HINT_STATE, 1);
            OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
            oplusVolumeDialogImpl.mOplusVolumeTipsControler.showSuperVolumeToast(oplusVolumeDialogImpl.mContext, oplusVolumeDialogImpl.mExpanded, 2131955152);
        }
    }

    public final void notifyExpandedPanelRowsChangedIfNeeded() {
        VolumeListAdapter volumeListAdapter = this.volumeInteractor.getVolumeListAdapter();
        if (this.volumeInteractor.getExpandedState().booleanValue() && !volumeListAdapter.isSystemPanel && volumeListAdapter.getCurrentDataSource() == 1) {
            volumeListAdapter.requestPanelMediaRefresh();
        }
    }

    public final List queryActiveMediaPlaybackConfigsFromService() {
        List<AudioPlaybackConfiguration> listEmptyList;
        try {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager == null || (listEmptyList = audioManager.getActivePlaybackConfigurations()) == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            return filterActiveMediaPlaybackConfigs(listEmptyList);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "getActivePlaybackConfigurations error: " + e);
            return CollectionsKt__CollectionsKt.emptyList();
        }
    }

    public final void start() {
        if (this.started) {
            return;
        }
        try {
            AudioManager audioManager = this.mAudioManager;
            if (audioManager != null) {
                audioManager.registerAudioPlaybackCallback(getAudioPlaybackCallback(), getWorkerHandler());
            }
            this.started = true;
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "registerAudioPlaybackCallback", null, 8);
            Handler workerHandler = getWorkerHandler();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            workerHandler.post(anonymousClass1);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "registerAudioPlaybackCallback error: " + e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void stop() {
        if (this.started) {
            boolean z = false;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            try {
                AudioManager audioManager = this.mAudioManager;
                if (audioManager != null) {
                    audioManager.unregisterAudioPlaybackCallback(getAudioPlaybackCallback());
                }
                updateLevelToAudio();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(objArr == true ? 1 : 0);
                anonymousClass1.this$0 = this;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(anonymousClass1);
                LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "unregisterAudioPlaybackCallback", null, 8);
            } catch (Exception e) {
                LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "unregisterAudioPlaybackCallback error: " + e);
            } finally {
                this.started = false;
            }
        }
    }

    public final void syncMultiMediaUiWithPreparedData(List list, Set set, boolean z, boolean z2, Function0 function0) throws Throwable {
        Throwable th;
        boolean z3;
        SparseArray sparseArray;
        this.deferPanelRefresh = true;
        try {
            Iterator<OplusVolumeRow> it = this.multiMediaRows.iterator();
            z3 = false;
            while (it.hasNext()) {
                try {
                    OplusVolumeRow next = it.next();
                    if (!set.contains(Integer.valueOf(next.getStream())) && (z || !z2)) {
                        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "playback snapshot remove: " + next.getStream() + ", playerId" + next.getPlayerId(), null, 8);
                        it.remove();
                        this.volumeInteractor.getVolumeListAdapter().removeItem(next);
                        VolumeDialogController.State state = this.volumeInteractor.mState;
                        if (state != null && (sparseArray = state.states) != null) {
                            sparseArray.remove(next.getStream());
                        }
                        z3 = true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    this.deferPanelRefresh = false;
                    if (!z3) {
                        throw th;
                    }
                    notifyExpandedPanelRowsChangedIfNeeded();
                    throw th;
                }
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                addMultiMediaRow$default(this, (MediaAppInfo) it2.next());
                z3 = true;
            }
            this.deferPanelRefresh = false;
            if (z3) {
                notifyExpandedPanelRowsChangedIfNeeded();
            }
            this.volumeInteractor.updateAppVolumeAdjust();
            if (function0 != null) {
                function0.invoke();
            }
        } catch (Throwable th3) {
            th = th3;
            z3 = false;
        }
    }

    public final void updateHMultiMediaRows(Function0 function0) {
        if (!this.needRecheckMultiMediaState) {
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        this.needRecheckMultiMediaState = false;
        Handler workerHandler = getWorkerHandler();
        RunnableC03091 runnableC03091 = new RunnableC03091(0);
        runnableC03091.this$0 = this;
        runnableC03091.$onComplete = function0;
        VarHandle.storeStoreFence();
        workerHandler.post(runnableC03091);
    }

    public final void updateLevelToAudio() {
        boolean z = MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled;
        if (this.multiMediaRows.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "MultiMediaRepository-->multi media settings state change multiMediaRows.size == 0 return");
                return;
            }
            return;
        }
        for (OplusVolumeRow oplusVolumeRow : this.multiMediaRows) {
            if (oplusVolumeRow.isMultiMediaVolume) {
                float f = Settings.Global.getFloat(this.context.getContentResolver(), oplusVolumeRow.getMediaPackageName() + MULTI_MEDIA_PACKAGE_SUFFIX, -1.0f);
                if (f != -1.0f) {
                    Handler workerHandler = getWorkerHandler();
                    updateLevelToAudio.2 r5 = new updateLevelToAudio.2();
                    r5.this$0 = this;
                    r5.$isMultiMediaSettingsEnable = z;
                    r5.$level = f;
                    r5.$row = oplusVolumeRow;
                    VarHandle.storeStoreFence();
                    workerHandler.post(r5);
                }
            }
        }
    }

    public final void updateRowsHMultiMediaRows() {
        SparseArray sparseArray;
        OplusVolumeSeekBar slider;
        Iterator<OplusVolumeRow> it = this.multiMediaRows.iterator();
        while (it.hasNext()) {
            OplusVolumeRow next = it.next();
            if (MultiMediaSettingsObserver.Companion.getInstance().isMultiMediaSettingsEnabled) {
                int i = (int) (Settings.Global.getFloat(this.context.getContentResolver(), next.getMediaPackageName() + MULTI_MEDIA_PACKAGE_SUFFIX, -1.0f) * 100.0f);
                if (i != -100 && ((slider = next.getSlider()) == null || slider.getProgress() != i)) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        StringBuilder sb = new StringBuilder("MultiMediaRepository-->");
                        TextView nameText = next.getNameText();
                        sb.append("updateRowsH setProgress " + ((Object) (nameText != null ? nameText.getText() : null)) + " " + i);
                        Log.d("SystemUi--Volume", sb.toString());
                    }
                    OplusVolumeSeekBar slider2 = next.getSlider();
                    if (slider2 != null) {
                        slider2.setProgress(i);
                    }
                }
                if (this.volumeInteractor.getVolumeListAdapter().indexOf(next) < 0) {
                    this.volumeInteractor.getVolumeListAdapter().addItem(next, null);
                }
                View view = next.getView();
                if (view == null || view.getVisibility() != 0) {
                    View view2 = next.getView();
                    if (view2 != null) {
                        OplusVolumeExKt.setVisOrGone(view2, true);
                    }
                }
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(next.getStream(), "updateRowsH removeView stream :", "MultiMediaRepository-->", "SystemUi--Volume");
                }
                this.multiMediaMap.remove(Integer.valueOf(next.getStream()));
                VolumeDialogController.State state = this.volumeInteractor.mState;
                if (state != null && (sparseArray = state.states) != null) {
                    sparseArray.remove(next.getStream());
                }
                this.volumeInteractor.getVolumeListAdapter().removeItem(next);
                it.remove();
            }
        }
    }
}
