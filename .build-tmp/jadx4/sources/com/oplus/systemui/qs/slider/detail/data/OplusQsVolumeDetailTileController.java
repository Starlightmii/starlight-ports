package com.oplus.systemui.qs.slider.detail.data;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeDetailTileController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsVolumeDetailTileController";
    private final CoroutineScope backgroundScope;
    private final StateFlow connectedMediaDevice;
    private final OplusQsVolumeDetailTileController$earCallback$1 earCallback = new OplusQsVolumeDetailTileController$earCallback$1();
    private final EarphoneController earphoneController;
    private final OplusQsLocalMediaDeviceRepository localMediaDeviceRepository;
    private final OplusQsSliderDetailTileRepository tileRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsVolumeDetailTileController(EarphoneController earphoneController, OplusQsLocalMediaDeviceRepository oplusQsLocalMediaDeviceRepository, OplusQsSliderDetailTileRepository oplusQsSliderDetailTileRepository, CoroutineScope coroutineScope) {
        this.earphoneController = earphoneController;
        this.localMediaDeviceRepository = oplusQsLocalMediaDeviceRepository;
        this.tileRepository = oplusQsSliderDetailTileRepository;
        this.backgroundScope = coroutineScope;
        this.connectedMediaDevice = oplusQsLocalMediaDeviceRepository.getConnectedMediaDevice();
        oplusQsLocalMediaDeviceRepository.startCollectMediaDevice();
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new collectEarAvailableState.1(this, (Continuation) null), 3);
    }

    public final StateFlow getConnectedMediaDevice() {
        return this.connectedMediaDevice;
    }
}
