package com.oplusos.systemui.common.rendercache;

import android.content.Context;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier[], still in use, count: 1, list:
  (r0v1 com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier[]) from 0x001b: INVOKE (r0v1 com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RenderBoosterDeviceTier {
    HIGH { // from class: com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier.HIGH
        private final RenderBoosterGroupStrategy groupStrategy = new ChunkGroupStrategy(Integer.MAX_VALUE, 1);

        @Override // com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier
        public RenderBoosterGroupStrategy getGroupStrategy() {
            return this.groupStrategy;
        }
    },
    MEDIUM { // from class: com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier.MEDIUM
        private final RenderBoosterGroupStrategy groupStrategy = new ChunkGroupStrategy(5, 3);

        @Override // com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier
        public RenderBoosterGroupStrategy getGroupStrategy() {
            return this.groupStrategy;
        }
    },
    LOW { // from class: com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier.LOW
        private final RenderBoosterGroupStrategy groupStrategy = new ChunkGroupStrategy(3, 5);

        @Override // com.oplusos.systemui.common.rendercache.RenderBoosterDeviceTier
        public RenderBoosterGroupStrategy getGroupStrategy() {
            return this.groupStrategy;
        }
    };

    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final RenderBoosterDeviceTier resolveDeviceTier(Context context) {
            if (ScrimUtil.isHighGaussianLevel(context)) {
                return RenderBoosterDeviceTier.HIGH;
            }
            if (ScrimUtil.isMiddleGaussianLevel(context)) {
                return RenderBoosterDeviceTier.MEDIUM;
            }
            return ScrimUtil.isLowGaussianLevel(context) ? RenderBoosterDeviceTier.LOW : RenderBoosterDeviceTier.HIGH;
        }
    }

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(new RenderBoosterDeviceTier[]{r0, r1, r2});
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static RenderBoosterDeviceTier valueOf(String str) {
        return (RenderBoosterDeviceTier) Enum.valueOf(RenderBoosterDeviceTier.class, str);
    }

    public static RenderBoosterDeviceTier[] values() {
        return (RenderBoosterDeviceTier[]) $VALUES.clone();
    }

    public abstract RenderBoosterGroupStrategy getGroupStrategy();
}
