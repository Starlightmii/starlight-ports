package com.oplus.systemui.media.seedling;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.broadcast.ActionReceiver$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplus.multiapp.OplusMultiAppManager;
import com.oplus.systemui.seedlingservice.mediaControl.MediaRecommendationData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.wrapper.os.Trace;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeedlingMediaDataCombineLatest {
    private static final String TAG = "MediaDataCombineLatest";
    private final Runnable cancelSuppressSeekBarUpdate;
    private final Runnable cancelSuppressSort;
    private final Comparator<SeedlingMediaData> comparator;
    private final Context context;
    private final ConcurrentHashMap<String, SeedlingMediaData> entries;
    private final Handler mainHandler;
    private final MediaSeedling mediaSeedling;
    private final ConcurrentHashMap<String, Integer> playingStateMap;
    private final CopyOnWriteArrayList<SeedlingMediaData> sortedEntries;
    public volatile boolean suppressMediaListSort;
    public volatile boolean suppressSeekBarUpdate;
    public static final Companion Companion = new Companion();
    private static final Lazy TEMP_SUPPRESS_MEDIA_SORT_TIME$delegate = LazyKt__LazyJVMKt.lazy(new OplusSeedlingMediaDataCombineLatest$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final long getTEMP_SUPPRESS_MEDIA_SORT_TIME() {
            return ((Number) OplusSeedlingMediaDataCombineLatest.TEMP_SUPPRESS_MEDIA_SORT_TIME$delegate.getValue()).longValue();
        }
    }

    public OplusSeedlingMediaDataCombineLatest(Context context, MediaSeedling mediaSeedling, Handler handler) {
        this.context = context;
        this.mediaSeedling = mediaSeedling;
        this.mainHandler = handler;
        OplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(0);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1;
        VarHandle.storeStoreFence();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$2 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(1);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$2.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1;
        VarHandle.storeStoreFence();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$3 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(2);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$3.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$2;
        VarHandle.storeStoreFence();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$4 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(3);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$4.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$3;
        VarHandle.storeStoreFence();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$5 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(4);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$5.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$4;
        VarHandle.storeStoreFence();
        OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$6 = new OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1(5);
        oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$6.$this_thenByDescending = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$5;
        VarHandle.storeStoreFence();
        this.comparator = oplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$6;
        this.entries = new ConcurrentHashMap<>();
        this.playingStateMap = new ConcurrentHashMap<>();
        this.sortedEntries = new CopyOnWriteArrayList<>();
        OplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1 oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1 = new OplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1(0);
        oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.cancelSuppressSort = oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1;
        OplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1 oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$2 = new OplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1(1);
        oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.cancelSuppressSeekBarUpdate = oplusSeedlingMediaDataCombineLatest$cancelSuppressSort$2;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        Iterator<T> it = this.sortedEntries.iterator();
        while (it.hasNext()) {
            ActionReceiver$.ExternalSyntheticOutline1.m("    sortedEntry: ", ((SeedlingMediaData) it.next()).toShortString(), printWriter);
        }
        for (Map.Entry<String, Integer> entry : this.playingStateMap.entrySet()) {
            String key = entry.getKey();
            printWriter.println("    playingState: " + ((Object) key) + " : " + entry.getValue());
        }
    }

    public final List<SeedlingMediaData> getSortedEntriesExceptNonActive() {
        CopyOnWriteArrayList<SeedlingMediaData> copyOnWriteArrayList = this.sortedEntries;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            if (((SeedlingMediaData) obj).getActive()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean getSuppressMediaListSort() {
        return this.suppressMediaListSort;
    }

    public final boolean getSuppressSeekBarUpdate() {
        return this.suppressSeekBarUpdate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMediaDataLoaded(String str, SeedlingMediaData seedlingMediaData, int i) {
        Object objValueOf;
        String string;
        OplusNotificationLogger.logMediaSeedling(TAG, "onMediaDataLoaded mediaData = " + seedlingMediaData);
        if (TextUtils.isEmpty(seedlingMediaData.getArtist()) && TextUtils.isEmpty(seedlingMediaData.getSong())) {
            return;
        }
        if (OsBinderCacheUtils.getCurrentUserId() == seedlingMediaData.getUserId() || OplusMultiAppManager.getInstance().isMultiAppUserId(seedlingMediaData.getUserId())) {
            SeedlingMediaData seedlingMediaData2 = this.entries.get(str);
            this.entries.put(str, seedlingMediaData);
            boolean z = seedlingMediaData2 == null;
            boolean z2 = seedlingMediaData2 == null;
            String string2 = this.context.getString(2131955896);
            Iterator<T> it = seedlingMediaData.getRecommendations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaRecommendationData mediaRecommendationData = (MediaRecommendationData) it.next();
                CharSequence title = mediaRecommendationData.getMediaItem().getDescription().getTitle();
                if (title == null || (string = title.toString()) == 0) {
                    CharSequence subtitle = mediaRecommendationData.getMediaItem().getDescription().getSubtitle();
                    if (subtitle != null) {
                        string = subtitle.toString();
                    } else {
                        CharSequence description = mediaRecommendationData.getMediaItem().getDescription().getDescription();
                        objValueOf = description != null ? description.toString() : null;
                        string = objValueOf == null ? "" : objValueOf;
                    }
                }
                mediaRecommendationData.setVoiceLabel(string2 + string);
            }
            SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
            boolean zIsPlayState = companion.isPlayState(seedlingMediaData.getState());
            Integer num = this.playingStateMap.get(str);
            objValueOf = num != null ? Boolean.valueOf(companion.isPlayState(num.intValue())) : null;
            boolean zEquals = Boolean.valueOf(zIsPlayState).equals(objValueOf);
            seedlingMediaData.setPlayStateChanged(!zEquals);
            this.playingStateMap.put(str, Integer.valueOf(seedlingMediaData.getState()));
            OplusNotificationLogger.logMediaSeedlingDebug(TAG, "onMediaDataLoaded: isCurrentPlaying=" + zIsPlayState + " isOldPlaying=" + objValueOf + " mediaData.state=" + seedlingMediaData.getState() + "  key=" + str);
            seedlingMediaData.setLastActiveTime(SystemClock.elapsedRealtime());
            boolean z3 = zEquals ? z : true;
            String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.entries.values(), (CharSequence) null, (CharSequence) null, (CharSequence) null, new OplusSeedlingMediaDataCombineLatest$$ExternalSyntheticLambda1(), 31);
            long j = Trace.TRACE_TAG_VIEW;
            boolean z4 = this.suppressMediaListSort;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("OplusSeedlingMediaDataCombineLatest#onMediaDataLoaded, ps=", strJoinToString$default, ", ns=", z3, ", ss=");
            sbM.append(z4);
            Trace.traceBegin(j, sbM.toString());
            Trace.traceEnd(j);
            if (z3 && !this.suppressMediaListSort) {
                this.sortedEntries.clear();
                Iterator<T> it2 = this.entries.values().iterator();
                while (it2.hasNext()) {
                    this.sortedEntries.add((SeedlingMediaData) it2.next());
                }
                CollectionsKt__MutableCollectionsJVMKt.sortWith(this.sortedEntries, this.comparator);
            }
            this.mediaSeedling.onMediaDataLoaded(str, seedlingMediaData, z2, i);
        }
    }

    public final void onMediaDataRemoved(String str) {
        SeedlingMediaData seedlingMediaData = this.entries.get(str);
        OplusNotificationLogger.logMediaSeedlingDebug(TAG, "onMediaDataRemoved seedlingMediaData.packageName :" + (seedlingMediaData != null ? seedlingMediaData.getPackageName() : null));
        if (seedlingMediaData == null || !TextUtils.equals(seedlingMediaData.getPackageName(), "com.spotify.music")) {
            SeedlingMediaData seedlingMediaDataRemove = this.entries.remove(str);
            if (seedlingMediaDataRemove != null) {
                this.sortedEntries.remove(seedlingMediaDataRemove);
            }
            this.playingStateMap.remove(str);
            this.mediaSeedling.onMediaDataRemoved(str);
            return;
        }
        for (String str2 : this.entries.keySet()) {
            OplusNotificationLogger.logMediaSeedlingDebug(TAG, "onMediaDataRemoved key :" + str2);
            SeedlingMediaData seedlingMediaData2 = this.entries.get(str2);
            if (TextUtils.equals("com.spotify.music", seedlingMediaData2 != null ? seedlingMediaData2.getPackageName() : null)) {
                str2.getClass();
                SeedlingMediaData seedlingMediaDataRemove2 = this.entries.remove(str2);
                if (seedlingMediaDataRemove2 != null) {
                    this.sortedEntries.remove(seedlingMediaDataRemove2);
                }
                this.playingStateMap.remove(str2);
                this.mediaSeedling.onMediaDataRemoved(str2);
            }
        }
    }

    public final void release() {
        this.sortedEntries.clear();
        this.playingStateMap.clear();
        this.entries.clear();
        this.mainHandler.removeCallbacks(this.cancelSuppressSort);
        this.mainHandler.removeCallbacks(this.cancelSuppressSeekBarUpdate);
        this.suppressSeekBarUpdate = false;
    }

    public final void setSuppressMediaListSort(boolean z) {
        this.suppressMediaListSort = z;
    }

    public final void setSuppressSeekBarUpdate(boolean z) {
        this.suppressSeekBarUpdate = z;
    }

    public final void tempSuppressMediaListSort() {
        if (this.mediaSeedling.isShowKeyguard) {
            return;
        }
        this.mainHandler.removeCallbacks(this.cancelSuppressSort);
        this.suppressMediaListSort = true;
        this.mainHandler.postDelayed(this.cancelSuppressSort, Companion.getTEMP_SUPPRESS_MEDIA_SORT_TIME());
    }

    public final void tempSuppressSeekBarUpdate() {
        this.mainHandler.removeCallbacks(this.cancelSuppressSeekBarUpdate);
        this.suppressSeekBarUpdate = true;
        this.mainHandler.postDelayed(this.cancelSuppressSeekBarUpdate, Companion.getTEMP_SUPPRESS_MEDIA_SORT_TIME());
    }
}
