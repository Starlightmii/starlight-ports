package com.oplus.systemui.notification.lockscreen.config;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.notification.lockscreen.config.CapsuleFoldableDimen[], still in use, count: 1, list:
  (r0v1 com.oplus.systemui.notification.lockscreen.config.CapsuleFoldableDimen[]) from 0x002d: INVOKE (r0v1 com.oplus.systemui.notification.lockscreen.config.CapsuleFoldableDimen[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
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
public final class CapsuleFoldableDimen {
    ITEM_WIDTH_SINGLE(2131165926),
    ITEM_WIDTH_MULTI(2131165925),
    ITEM_HEIGHT(2131165915);

    public static final /* synthetic */ EnumEntries $ENTRIES;
    private static final String TAG = "CapsuleConfig";
    private final int dimenId;
    private int realValue;
    public static final Companion Companion = new Companion();
    private static final Lazy mContext$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleFoldableDimen$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final Context getMContext() {
            return (Context) CapsuleFoldableDimen.mContext$delegate.getValue();
        }

        public final void loadFoldableDimens(Context context, String str) {
            Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, getMContext(), true, 4);
            contextUpdateDisplayConfiguration$default.getClass();
            for (CapsuleFoldableDimen capsuleFoldableDimen : CapsuleFoldableDimen.values()) {
                CapsuleFoldableDimen.access$loadDimen(capsuleFoldableDimen, contextUpdateDisplayConfiguration$default);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileInputLogger$.ExternalSyntheticOutline0.m(capsuleFoldableDimen.getValue(), "CapsuleConfig-->", "SystemUi--Notification", ExifInterface$.ExternalSyntheticOutline1.m("loadFoldableDimens[loadReason:", str, "] ", capsuleFoldableDimen.name(), " - "));
                }
            }
        }
    }

    static {
        $ENTRIES = EnumEntriesKt.enumEntries(new CapsuleFoldableDimen[]{r0, r1, r2});
    }

    public CapsuleFoldableDimen(int i) {
        super(str, i);
        this.dimenId = i;
    }

    public static final void access$loadDimen(CapsuleFoldableDimen capsuleFoldableDimen, Context context) {
        Object obj;
        capsuleFoldableDimen.getClass();
        try {
            obj = Result.constructor-impl(Integer.valueOf(context.getResources().getDimensionPixelSize(capsuleFoldableDimen.dimenId)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = 0;
        }
        capsuleFoldableDimen.realValue = ((Number) obj).intValue();
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static CapsuleFoldableDimen valueOf(String str) {
        return (CapsuleFoldableDimen) Enum.valueOf(CapsuleFoldableDimen.class, str);
    }

    public static CapsuleFoldableDimen[] values() {
        return (CapsuleFoldableDimen[]) $VALUES.clone();
    }

    public final int getDimenId() {
        return this.dimenId;
    }

    public final int getValue() {
        return this.realValue;
    }
}
