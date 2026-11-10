package com.oplus.systemui.statusbar.seeding;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.statusbar.seeding.DIRECTION[], still in use, count: 1, list:
  (r0v1 com.oplus.systemui.statusbar.seeding.DIRECTION[]) from 0x001b: INVOKE (r0v1 com.oplus.systemui.statusbar.seeding.DIRECTION[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
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
public final class DIRECTION {
    LEFT(1),
    RIGHT(-1);

    public static final /* synthetic */ EnumEntries $ENTRIES;
    private final int value;

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(directionArr);
    }

    public DIRECTION(int i) {
        super(str, i);
        this.value = i;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static DIRECTION valueOf(String str) {
        return (DIRECTION) Enum.valueOf(DIRECTION.class, str);
    }

    public static DIRECTION[] values() {
        return (DIRECTION[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }
}
