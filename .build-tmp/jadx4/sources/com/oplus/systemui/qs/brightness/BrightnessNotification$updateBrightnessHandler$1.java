package com.oplus.systemui.qs.brightness;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrightnessNotification$updateBrightnessHandler$1 implements Runnable {
    public /* synthetic */ boolean $automatic;
    public /* synthetic */ int $brightness;
    public /* synthetic */ BrightnessNotification this$0;

    @Override // java.lang.Runnable
    public final void run() {
        BrightnessNotification.access$updateBrightness(this.this$0, this.$brightness, this.$automatic);
    }
}
