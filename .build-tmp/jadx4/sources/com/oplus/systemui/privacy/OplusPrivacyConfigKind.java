package com.oplus.systemui.privacy;

import com.android.systemui.privacy.PrivacyType;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.privacy.OplusPrivacyConfigKind[], still in use, count: 1, list:
  (r0v1 com.oplus.systemui.privacy.OplusPrivacyConfigKind[]) from 0x0038: INVOKE (r0v1 com.oplus.systemui.privacy.OplusPrivacyConfigKind[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
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
final class OplusPrivacyConfigKind {
    CAMERA(3),
    MICROPHONE(2),
    LOCATION(1),
    MEDIA_PROJECTION(3),
    NONE(3);

    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final Companion Companion = new Companion();
    private final int locationFillColorRes;
    private final int microphoneFillColorRes;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusPrivacyConfigKind fromPrivacyType(PrivacyType privacyType) {
            int iOrdinal = privacyType.ordinal();
            if (iOrdinal == 0) {
                return OplusPrivacyConfigKind.CAMERA;
            }
            if (iOrdinal == 1) {
                return OplusPrivacyConfigKind.MICROPHONE;
            }
            if (iOrdinal == 2) {
                return OplusPrivacyConfigKind.LOCATION;
            }
            if (iOrdinal == 3) {
                return OplusPrivacyConfigKind.MEDIA_PROJECTION;
            }
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(new OplusPrivacyConfigKind[]{r0, r1, r2, r4, r5});
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusPrivacyConfigKind(int i) {
        super(str, i);
        int i2 = (i & 1) != 0 ? 2131103174 : 2131103177;
        int i3 = (i & 2) == 0 ? 2131103175 : 2131103174;
        this.microphoneFillColorRes = i2;
        this.locationFillColorRes = i3;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static OplusPrivacyConfigKind valueOf(String str) {
        return (OplusPrivacyConfigKind) Enum.valueOf(OplusPrivacyConfigKind.class, str);
    }

    public static OplusPrivacyConfigKind[] values() {
        return (OplusPrivacyConfigKind[]) $VALUES.clone();
    }

    public final int fillColorRes() {
        int iOrdinal = ordinal();
        if (iOrdinal == 1) {
            return this.microphoneFillColorRes;
        }
        if (iOrdinal != 2) {
            return 2131103174;
        }
        return this.locationFillColorRes;
    }

    public final int getLocationFillColorRes() {
        return this.locationFillColorRes;
    }

    public final int getMicrophoneFillColorRes() {
        return this.microphoneFillColorRes;
    }
}
