package com.oplus.systemui.volume.repository;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AudioDataRepository$deviceListener$1 implements AudioManager.OnCommunicationDeviceChangedListener {
    public /* synthetic */ AudioDataRepository this$0;

    @Override // android.media.AudioManager.OnCommunicationDeviceChangedListener
    public final void onCommunicationDeviceChanged(AudioDeviceInfo audioDeviceInfo) {
        BuildersKt.launch$default(this.this$0.repositoryScope, (CoroutineContext) null, (CoroutineStart) null, new 1(this.this$0, audioDeviceInfo, (Continuation) null), 3);
    }
}
