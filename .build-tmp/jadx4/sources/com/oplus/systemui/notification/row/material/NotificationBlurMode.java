package com.oplus.systemui.notification.row.material;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.notification.row.material.NotificationBlurMode[], still in use, count: 1, list:
  (r0v1 com.oplus.systemui.notification.row.material.NotificationBlurMode[]) from 0x0076: INVOKE (r0v1 com.oplus.systemui.notification.row.material.NotificationBlurMode[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
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
public final class NotificationBlurMode {
    UNINITIATED(false, true, 0),
    SHADE_LIGHT(1 == true ? 1 : 0),
    SHADE_DARK(false),
    NO_BLUR_LIGHT(false, true, 3),
    NO_BLUR_DARK(false, true, 4),
    WALLPAPER_A(false, false, 5),
    WALLPAPER_B(false, false, 6),
    HEADS_UP_MATERIAL_LIGHT(true, false, 7),
    HEADS_UP_MATERIAL_DARK(true, false, 8);

    public static final /* synthetic */ EnumEntries $ENTRIES;
    private final boolean light;
    private final boolean noBlur;
    private final boolean shade;

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(notificationBlurModeArr);
    }

    public /* synthetic */ NotificationBlurMode(boolean z) {
        this(true, false, i);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static NotificationBlurMode valueOf(String str) {
        return (NotificationBlurMode) Enum.valueOf(NotificationBlurMode.class, str);
    }

    public static NotificationBlurMode[] values() {
        return (NotificationBlurMode[]) $VALUES.clone();
    }

    public final boolean getLight() {
        return this.light;
    }

    public final boolean getNoBlur() {
        return this.noBlur;
    }

    public final boolean getShade() {
        return this.shade;
    }

    public final boolean isHeadsUpBlurDark() {
        return this == HEADS_UP_MATERIAL_DARK;
    }

    public NotificationBlurMode(boolean z, boolean z2, int i) {
        super(str, i);
        this.light = z;
        this.shade = z;
        this.noBlur = z2;
    }
}
