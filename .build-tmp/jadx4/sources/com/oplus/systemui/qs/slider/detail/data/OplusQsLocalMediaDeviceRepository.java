package com.oplus.systemui.qs.slider.detail.data;

import android.media.session.MediaSession;
import com.android.settingslib.media.LocalMediaManager;
import com.android.systemui.media.controls.util.LocalMediaManagerFactory;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsLocalMediaDeviceRepository {
    private final MutableSharedFlow _mediaDeviceUpdateEvent;
    private final CoroutineScope backgroundScope;
    private final OplusQsLocalMediaDeviceRepository$callback$1 callback;
    private final StateFlow connectedMediaDevice;
    private LocalMediaManager localMediaManager;
    private final LocalMediaManagerFactory localMediaManagerFactory;
    private final SharedFlow mediaDeviceUpdateEvent;

    public OplusQsLocalMediaDeviceRepository(LocalMediaManagerFactory localMediaManagerFactory, CoroutineScope coroutineScope) {
        this.localMediaManagerFactory = localMediaManagerFactory;
        this.backgroundScope = coroutineScope;
        OplusQsLocalMediaDeviceRepository$callback$1 oplusQsLocalMediaDeviceRepository$callback$1 = new OplusQsLocalMediaDeviceRepository$callback$1();
        oplusQsLocalMediaDeviceRepository$callback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.callback = oplusQsLocalMediaDeviceRepository$callback$1;
        MutableSharedFlow MutableSharedFlow = SharedFlowKt.MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST);
        this._mediaDeviceUpdateEvent = MutableSharedFlow;
        SharedFlow sharedFlowAsSharedFlow = FlowKt.asSharedFlow(MutableSharedFlow);
        this.mediaDeviceUpdateEvent = sharedFlowAsSharedFlow;
        this.connectedMediaDevice = FlowKt.stateIn(FlowKt.mapLatest(sharedFlowAsSharedFlow, new OplusQsLocalMediaDeviceRepository$connectedMediaDevice$1(this, null)), coroutineScope, SharingStarted.Companion.getEagerly(), (Object) null);
    }

    public final StateFlow getConnectedMediaDevice() {
        return this.connectedMediaDevice;
    }

    public final LocalMediaManager getLocalMediaManager() {
        return this.localMediaManager;
    }

    public final SharedFlow getMediaDeviceUpdateEvent() {
        return this.mediaDeviceUpdateEvent;
    }

    public final void setLocalMediaManager(LocalMediaManager localMediaManager) {
        this.localMediaManager = localMediaManager;
    }

    public final void startCollectMediaDevice() {
        if (this.localMediaManager != null) {
            return;
        }
        LocalMediaManager localMediaManagerCreate = this.localMediaManagerFactory.create("", (MediaSession.Token) null);
        this.localMediaManager = localMediaManagerCreate;
        localMediaManagerCreate.registerCallback(this.callback);
        updateMediaDevice();
    }

    public final void updateMediaDevice() {
        BuildersKt.launch$default(this.backgroundScope, (CoroutineContext) null, (CoroutineStart) null, new updateMediaDevice.1(this, (Continuation) null), 3);
    }
}
