package com.oplus.zoom.common;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomDCSManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ZoomDCSManager f$0;
    public /* synthetic */ boolean f$1;

    public /* synthetic */ ZoomDCSManager$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.lambda$onSimpleModeSwitch$1(this.f$1);
                break;
            case 1:
                this.f$0.lambda$onGameShrinkSwitch$2(this.f$1);
                break;
            case 2:
                this.f$0.lambda$onExchangeAppSwitch$5(this.f$1);
                break;
            case 3:
                this.f$0.lambda$onZoomShareSwitch$3(this.f$1);
                break;
            case 4:
                this.f$0.lambda$onAlphaAdjustSwitch$6(this.f$1);
                break;
            default:
                this.f$0.lambda$onGameRedirectSwitch$4(this.f$1);
                break;
        }
    }
}
