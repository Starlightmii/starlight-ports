package com.oplus.systemui.qs.media.multilight;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.qs.media.multilight.PositionEnum[], still in use, count: 1, list:
  (r0v1 com.oplus.systemui.qs.media.multilight.PositionEnum[]) from 0x0043: INVOKE (r0v1 com.oplus.systemui.qs.media.multilight.PositionEnum[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
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
public final class PositionEnum {
    DEFAULT(-1),
    LEFT(0),
    TOP(1),
    RIGHT(2),
    BOTTOM(3),
    SPECIFIED(4);

    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final Companion Companion = new Companion();
    private final int value;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final PositionEnum fromMultiLightValue(int i) {
            return (i == PositionEnum.LEFT.getValue() || i == PositionEnum.TOP.getValue() || i == PositionEnum.RIGHT.getValue() || i == PositionEnum.BOTTOM.getValue()) ? fromProtocolValue(i) : PositionEnum.DEFAULT;
        }

        public final PositionEnum fromProtocolValue(int i) {
            Object next;
            Iterator it = PositionEnum.getEntries().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (((PositionEnum) next).getValue() != i);
            PositionEnum positionEnum = (PositionEnum) next;
            return positionEnum == null ? PositionEnum.DEFAULT : positionEnum;
        }
    }

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(new PositionEnum[]{r0, r1, r2, r3, r4, r5});
    }

    public PositionEnum(int i) {
        super(str, i);
        this.value = i;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static PositionEnum valueOf(String str) {
        return (PositionEnum) Enum.valueOf(PositionEnum.class, str);
    }

    public static PositionEnum[] values() {
        return (PositionEnum[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }
}
