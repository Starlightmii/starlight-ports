package com.oplus.systemui.notification.lockscreen.seedling;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleSeedingData extends CapsuleItemData {
    public static final Companion Companion = new Companion();
    public static final String KEY_LOCK_CAPSULE_LEVEL = "lockCapsuleLevel";
    private static final String TAG = "CapsuleSeedingData";
    private final Lazy bgType$delegate;
    private final Lazy cardLevel$delegate;
    private final CustomRowEntry customRowEntry;
    private final View miniCardView;
    public boolean remind;
    private final OplusCustomRow row;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final CapsuleSeedingData from(CustomRowEntry customRowEntry, Context context, OplusCustomRow oplusCustomRow) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleSeedingData-->".concat("ParseCapsuleItemData: entry=" + customRowEntry.getKey() + ", miniCardView=" + customRowEntry.getMiniCardView() + " ,remind: " + customRowEntry.getMiniCardRemind() + " ,row: " + oplusCustomRow + " ,index:" + customRowEntry.getMiniCardViewIndex() + ", bundle:" + customRowEntry.getExtras()));
            }
            View miniCardView = customRowEntry.getMiniCardView();
            if (miniCardView == null) {
                Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, context, true, 4);
                contextUpdateDisplayConfiguration$default.getClass();
                miniCardView = LayoutInflater.from(contextUpdateDisplayConfiguration$default).inflate(2131559272, (ViewGroup) null, false);
                ((TextView) miniCardView.findViewById(2131365800)).setText("流体云[" + customRowEntry.getMiniCardViewIndex() + "]");
                TextView textView = (TextView) miniCardView.findViewById(2131365799);
                String packageName = customRowEntry.getPackageName();
                if (packageName == null) {
                    packageName = "";
                }
                textView.setText(packageName);
                Companion companion = CapsuleSeedingData.Companion;
                ViewGroup viewGroup = miniCardView instanceof ViewGroup ? (ViewGroup) miniCardView : null;
                String key = customRowEntry.getKey();
                companion.getClass();
                if (viewGroup != null) {
                    ImageView imageView = (ImageView) viewGroup.findViewById(2131363499);
                    if (imageView != null) {
                        CapsuleSeedingData$Companion$setupSeedlingClickListener$1 capsuleSeedingData$Companion$setupSeedlingClickListener$1 = new CapsuleSeedingData$Companion$setupSeedlingClickListener$1();
                        capsuleSeedingData$Companion$setupSeedlingClickListener$1.$key = key;
                        VarHandle.storeStoreFence();
                        imageView.setOnClickListener(capsuleSeedingData$Companion$setupSeedlingClickListener$1);
                    }
                    ImageView imageView2 = (ImageView) viewGroup.findViewById(2131362377);
                    if (imageView2 != null) {
                        CapsuleSeedingData$Companion$setupSeedlingClickListener$2 capsuleSeedingData$Companion$setupSeedlingClickListener$2 = new CapsuleSeedingData$Companion$setupSeedlingClickListener$2();
                        capsuleSeedingData$Companion$setupSeedlingClickListener$2.$btnTest = imageView2;
                        capsuleSeedingData$Companion$setupSeedlingClickListener$2.$key = key;
                        VarHandle.storeStoreFence();
                        imageView2.setOnClickListener(capsuleSeedingData$Companion$setupSeedlingClickListener$2);
                    }
                }
            }
            return new CapsuleSeedingData(oplusCustomRow, customRowEntry, miniCardView, customRowEntry.getMiniCardRemind());
        }
    }

    public static int $r8$lambda$7MmU_4AoBTB_eo71eA7joFb3CaI(CapsuleSeedingData capsuleSeedingData) {
        Object obj;
        try {
            obj = Result.constructor-impl(Integer.valueOf(capsuleSeedingData.customRowEntry.getExtras().getInt(KEY_LOCK_CAPSULE_LEVEL, -1)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public static int $r8$lambda$Vn4eufgXLMmwFFu5zTWhLPO5CDY(CapsuleSeedingData capsuleSeedingData) {
        Object obj;
        try {
            obj = Result.constructor-impl(Integer.valueOf(LockScreenSeedingInteractor.Companion.getBgType(capsuleSeedingData.customRowEntry)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public CapsuleSeedingData(OplusCustomRow oplusCustomRow, CustomRowEntry customRowEntry, View view, boolean z) {
        super(customRowEntry.getKey(), view, true);
        this.row = oplusCustomRow;
        this.customRowEntry = customRowEntry;
        this.miniCardView = view;
        this.remind = z;
        CapsuleSeedingData$$ExternalSyntheticLambda0 capsuleSeedingData$$ExternalSyntheticLambda0 = new CapsuleSeedingData$$ExternalSyntheticLambda0(0);
        capsuleSeedingData$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.cardLevel$delegate = LazyKt__LazyJVMKt.lazy(capsuleSeedingData$$ExternalSyntheticLambda0);
        CapsuleSeedingData$$ExternalSyntheticLambda0 capsuleSeedingData$$ExternalSyntheticLambda1 = new CapsuleSeedingData$$ExternalSyntheticLambda0(1);
        capsuleSeedingData$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.bgType$delegate = LazyKt__LazyJVMKt.lazy(capsuleSeedingData$$ExternalSyntheticLambda1);
    }

    public final OplusCustomRow component1() {
        return this.row;
    }

    public final CustomRowEntry component2() {
        return this.customRowEntry;
    }

    public final View component3() {
        return this.miniCardView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CapsuleSeedingData)) {
            return false;
        }
        CapsuleSeedingData capsuleSeedingData = (CapsuleSeedingData) obj;
        return Intrinsics.areEqual(this.row, capsuleSeedingData.row) && Intrinsics.areEqual(this.customRowEntry, capsuleSeedingData.customRowEntry) && Intrinsics.areEqual(this.miniCardView, capsuleSeedingData.miniCardView) && this.remind == capsuleSeedingData.remind;
    }

    public final int getBgType() {
        return ((Number) this.bgType$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData
    public int getCardLevel() {
        return ((Number) this.cardLevel$delegate.getValue()).intValue();
    }

    public final CustomRowEntry getCustomRowEntry() {
        return this.customRowEntry;
    }

    public final View getMiniCardView() {
        return this.miniCardView;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData
    public int getOrderPriority() {
        Integer numValueOf = Integer.valueOf(this.customRowEntry.getMiniCardViewIndex());
        if (numValueOf.intValue() < 0) {
            numValueOf = null;
        }
        return numValueOf != null ? numValueOf.intValue() : Math.abs(this.customRowEntry.getKey().hashCode());
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData
    public Integer getPrimaryColor() {
        return Integer.valueOf(this.customRowEntry.getPrimaryColor());
    }

    public final boolean getRemind() {
        return this.remind;
    }

    public final OplusCustomRow getRow() {
        return this.row;
    }

    public final int hashCode() {
        LaunchableView launchableView = this.row;
        return Boolean.hashCode(this.remind) + ((this.miniCardView.hashCode() + ((this.customRowEntry.hashCode() + ((launchableView == null ? 0 : launchableView.hashCode()) * 31)) * 31)) * 31);
    }

    public final void setRemind(boolean z) {
        this.remind = z;
    }

    public String toString() {
        String key = getKey();
        int cardLevel = getCardLevel();
        int orderPriority = getOrderPriority();
        int miniCardViewIndex = this.customRowEntry.getMiniCardViewIndex();
        boolean miniCardRemind = this.customRowEntry.getMiniCardRemind();
        CustomRowEntry customRowEntry = this.customRowEntry;
        View view = this.miniCardView;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(cardLevel, "CapsuleSeedingData(key=", key, ", cardLevel:", ", orderPriority=");
        RectList$.ExternalSyntheticOutline0.m(orderPriority, miniCardViewIndex, ", miniCardViewIndex=", ", remind:", sbM);
        sbM.append(miniCardRemind);
        sbM.append(",, customRowEntry=");
        sbM.append(customRowEntry);
        sbM.append(", miniCardView=");
        sbM.append(view);
        sbM.append(")");
        return sbM.toString();
    }
}
