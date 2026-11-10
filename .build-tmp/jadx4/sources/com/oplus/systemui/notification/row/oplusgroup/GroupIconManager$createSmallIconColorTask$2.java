package com.oplus.systemui.notification.row.oplusgroup;

import android.app.Notification;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class GroupIconManager$createSmallIconColorTask$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationEntry $entry;
    final /* synthetic */ String $key;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    final /* synthetic */ GroupIconManager this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.oplusgroup.GroupIconManager$createSmallIconColorTask$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ GroupIconManager.IconInfo $iconInfo;
        final /* synthetic */ String $key;
        int label;
        final /* synthetic */ GroupIconManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(GroupIconManager groupIconManager, String str, GroupIconManager.IconInfo iconInfo, Continuation continuation) {
            super(2, continuation);
            this.this$0 = groupIconManager;
            this.$key = str;
            this.$iconInfo = iconInfo;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$key, this.$iconInfo, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.iconInfoMap.put(this.$key, this.$iconInfo);
            GroupIconManager.IconInfo iconInfo = this.$iconInfo;
            String str = this.$key;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String hexString = Integer.toHexString(iconInfo.getLightColor());
                String hexString2 = Integer.toHexString(iconInfo.getDarkColor());
                String hexString3 = Integer.toHexString(iconInfo.getSeedColor());
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("createSmallIconColorTask: lightColor=", hexString, " darkColor=", hexString2, " seedColor=");
                sbM.append(hexString3);
                sbM.append(" key=");
                sbM.append(str);
                Log.d("SystemUi--Notification", "GroupIconManager-->".concat(sbM.toString()));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GroupIconManager$createSmallIconColorTask$2(NotificationEntry notificationEntry, GroupIconManager groupIconManager, String str, Continuation continuation) {
        super(2, continuation);
        this.$entry = notificationEntry;
        this.this$0 = groupIconManager;
        this.$key = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new GroupIconManager$createSmallIconColorTask$2(this.$entry, this.this$0, this.$key, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Icon smallIcon;
        Drawable drawableLoadDrawable;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            GroupIconManager.IconInfo iconInfo = (GroupIconManager.IconInfo) this.L$3;
            ResultKt.throwOnFailure(obj);
            return iconInfo;
        }
        ResultKt.throwOnFailure(obj);
        Notification notification = this.$entry.getSbn().getNotification();
        if (notification == null || (smallIcon = notification.getSmallIcon()) == null || (drawableLoadDrawable = smallIcon.loadDrawable(this.this$0.context)) == null) {
            return null;
        }
        boolean zIsGrayscaleIcon = this.this$0.oplusContrastColorUtil.isGrayscaleIcon(drawableLoadDrawable);
        Triple<Integer, Integer, Integer> expandedPillBgColor = GroupIconManager.Companion.getExpandedPillBgColor(this.this$0.context, drawableLoadDrawable);
        GroupIconManager.IconInfo iconInfo2 = new GroupIconManager.IconInfo(((Number) expandedPillBgColor.getFirst()).intValue(), ((Number) expandedPillBgColor.getSecond()).intValue(), ((Number) expandedPillBgColor.getThird()).intValue(), Boxing.boxBoolean(zIsGrayscaleIcon));
        MainCoroutineDispatcher main = Dispatchers.getMain();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$key, iconInfo2, null);
        this.L$0 = SpillingKt.nullOutSpilledVariable(smallIcon);
        this.L$1 = SpillingKt.nullOutSpilledVariable(drawableLoadDrawable);
        this.L$2 = SpillingKt.nullOutSpilledVariable(expandedPillBgColor);
        this.L$3 = iconInfo2;
        this.Z$0 = zIsGrayscaleIcon;
        this.label = 1;
        return BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended ? coroutine_suspended : iconInfo2;
    }
}
