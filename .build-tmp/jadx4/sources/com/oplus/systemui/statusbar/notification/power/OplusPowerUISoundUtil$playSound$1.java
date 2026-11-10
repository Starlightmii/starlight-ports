package com.oplus.systemui.statusbar.notification.power;

import android.media.SoundPool;
import android.os.Handler;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPowerUISoundUtil$playSound$1 implements SoundPool.OnLoadCompleteListener {
    public /* synthetic */ OplusPowerUISoundUtil this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.power.OplusPowerUISoundUtil$playSound$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ int $sampleId;
        public /* synthetic */ int $status;
        public /* synthetic */ OplusPowerUISoundUtil this$0;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.this$0.soundUtil != null) {
                boolean z = this.$status == 0;
                this.this$0.soundPreparedMap.put(Integer.valueOf(this.$sampleId), Boolean.valueOf(z));
                this.this$0.playSoundId(this.$sampleId, z);
            }
        }
    }

    @Override // android.media.SoundPool.OnLoadCompleteListener
    public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
        Handler handler = this.this$0.bgHandler;
        if (handler != null) {
            OplusPowerUISoundUtil oplusPowerUISoundUtil = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = oplusPowerUISoundUtil;
            anonymousClass1.$status = i2;
            anonymousClass1.$sampleId = i;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }
}
