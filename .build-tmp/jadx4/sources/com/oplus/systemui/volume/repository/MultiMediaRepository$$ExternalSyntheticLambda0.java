package com.oplus.systemui.volume.repository;

import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class MultiMediaRepository$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MultiMediaRepository f$0;

    public /* synthetic */ MultiMediaRepository$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        final MultiMediaRepository multiMediaRepository = this.f$0;
        switch (i) {
            case 0:
                return multiMediaRepository.volumeInteractor.mController.getWorkerHandler();
            case 1:
                return MultiMediaRepository.$r8$lambda$5CzcRE5hCJHNNM4BO_veLrLMU4w(multiMediaRepository);
            case 2:
                return new AudioManager.AudioPlaybackCallback() { // from class: com.oplus.systemui.volume.repository.MultiMediaRepository$audioPlaybackCallback$2$1
                    @Override // android.media.AudioManager.AudioPlaybackCallback
                    public final void onPlaybackConfigChanged(List list) {
                        super.onPlaybackConfigChanged(list);
                        if (multiMediaRepository.destroyed) {
                            return;
                        }
                        List<AudioPlaybackConfiguration> listFilterActiveMediaPlaybackConfigs = MultiMediaRepository.filterActiveMediaPlaybackConfigs(list);
                        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterActiveMediaPlaybackConfigs, 10));
                        Iterator it = listFilterActiveMediaPlaybackConfigs.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Integer.valueOf(((AudioPlaybackConfiguration) it.next()).getClientPid()));
                        }
                        Set set = CollectionsKt___CollectionsKt.toSet(arrayList);
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (AudioPlaybackConfiguration audioPlaybackConfiguration : listFilterActiveMediaPlaybackConfigs) {
                            Integer numValueOf = Integer.valueOf(audioPlaybackConfiguration.getClientPid());
                            Object arrayList2 = linkedHashMap.get(numValueOf);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                                linkedHashMap.put(numValueOf, arrayList2);
                            }
                            ((List) arrayList2).add(Integer.valueOf(audioPlaybackConfiguration.getPlayerInterfaceId()));
                        }
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size()));
                        for (Map.Entry entry : linkedHashMap.entrySet()) {
                            linkedHashMap2.put(entry.getKey(), CollectionsKt___CollectionsKt.toSet((Iterable) entry.getValue()));
                        }
                        if (Intrinsics.areEqual(set, multiMediaRepository.lastActivePidSet) && linkedHashMap2.equals(multiMediaRepository.playerIdsSnapshot)) {
                            return;
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(list.size(), "configs ", "MultiMediaRepository-->", "SystemUi--Volume");
                        }
                        multiMediaRepository.pendingActivePlaybackConfigs = listFilterActiveMediaPlaybackConfigs;
                        multiMediaRepository.getWorkerHandler().removeCallbacks(multiMediaRepository.syncPlaybackUiRunnable);
                        multiMediaRepository.getWorkerHandler().postDelayed(multiMediaRepository.syncPlaybackUiRunnable, 100L);
                    }
                };
            default:
                multiMediaRepository.updateLevelToAudio();
                return Unit.INSTANCE;
        }
    }
}
