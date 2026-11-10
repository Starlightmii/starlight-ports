package com.oplus.systemui.volume.anim;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeItemAnimator$runPendingAnimations$adder$1 implements Runnable {
    public /* synthetic */ ArrayList $additions;
    public /* synthetic */ int $expandStaggerTotal;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ VolumeItemAnimator this$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                ArrayList arrayList = this.$additions;
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    this.this$0.animateAddImpl((RecyclerView.ViewHolder) obj, this.$expandStaggerTotal);
                }
                this.$additions.clear();
                this.this$0.getMAdditionsList().remove(this.$additions);
                break;
            default:
                ArrayList arrayList2 = this.$additions;
                int size2 = arrayList2.size();
                while (i < size2) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    VolumeItemAnimator.MoveInfo moveInfo = (VolumeItemAnimator.MoveInfo) obj2;
                    this.this$0.animateMoveImpl(moveInfo.getHolder(), moveInfo.getFromX(), moveInfo.getFromY(), moveInfo.getToX(), moveInfo.getToY(), this.$expandStaggerTotal);
                }
                this.$additions.clear();
                this.this$0.getMMovesList().remove(this.$additions);
                break;
        }
    }
}
