package com.oplus.systemui.notification.lockscreen.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.systemui.media.MediaSeedlingControlEx;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.media.controls.ui.controller.MediaControlPanel;
import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMediaPlayerDataHelper {
    private final Lazy keyguardMediaController;
    private final Lazy mediaSeedlingControlEx;
    private final Lazy notificationResolverEx;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleMediaPlayerDataHelper$getMediaNotificationIcon$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CapsuleMediaPlayerDataHelper.this.getMediaNotificationIcon(null, this);
        }
    }

    public CapsuleMediaPlayerDataHelper(Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.notificationResolverEx = lazy;
        this.keyguardMediaController = lazy2;
        this.mediaSeedlingControlEx = lazy3;
    }

    public final CapsuleNotificationDataController.CapsuleNotificationInnerData buildMediaSingleIfNeed(Context context, List<? extends PipelineEntry> list, CapsuleNotificationDataController.NotificationIconData notificationIconData, int i, int i2, List<CapsuleNotificationDataController.CapsuleNotificationReportData> list2) {
        if (i == 0) {
            return null;
        }
        boolean zIsEmpty = list.isEmpty();
        return new CapsuleNotificationDataController.CapsuleNotificationInnerData(notificationIconData, AppInfoUtil.getAppNameByPkgName(context, notificationIconData != null ? notificationIconData.getPackageName() : null), context.getResources().getQuantityString(2131820563, i2, Integer.valueOf(i2)), zIsEmpty ? CapsuleNotificationDataController.CardType.SINGLE : CapsuleNotificationDataController.CardType.GROUP, zIsEmpty ? null : Integer.valueOf(i2), null, null, i2, i, list2, 96);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object getMediaNotificationIcon(Context context, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object next;
        String packageName;
        int userId;
        Context context2;
        Drawable badgedAppIcon;
        int i;
        Drawable drawable;
        int i2;
        int i3;
        boolean z;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i4 = anonymousClass1.label;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i4 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object packageIconWithUserId = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = anonymousClass1.label;
        Drawable drawable2 = null;
        if (i5 == 0) {
            ResultKt.throwOnFailure(packageIconWithUserId);
            if (isShowMediaNotification()) {
                Iterator it = MediaPlayerData.INSTANCE.playerEntries().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (((MediaControlPanel) ((Pair) next).getSecond()).getController() == null);
                Pair pair = (Pair) next;
                if (pair != null) {
                    MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) pair.component1();
                    packageName = mediaSortKey.getData().getPackageName();
                    userId = mediaSortKey.getData().getUserId();
                    if (OplusMultiAppUtils.isOplusMultiAppUser(Boxing.boxInt(userId))) {
                        context2 = context;
                        badgedAppIcon = CapsuleNotificationUtils.getBadgedAppIcon(context2, packageName, userId);
                    } else {
                        context2 = context;
                        badgedAppIcon = null;
                    }
                    i = badgedAppIcon != null ? 1 : 0;
                    if (badgedAppIcon == null) {
                        NotificationResolverEx notificationResolverEx = (NotificationResolverEx) this.notificationResolverEx.get();
                        if (notificationResolverEx != null) {
                            Integer numBoxInt = Boxing.boxInt(userId);
                            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(context2);
                            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(mediaSortKey);
                            anonymousClass1.L$2 = packageName;
                            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(badgedAppIcon);
                            anonymousClass1.I$0 = userId;
                            anonymousClass1.I$1 = i;
                            anonymousClass1.label = 1;
                            packageIconWithUserId = notificationResolverEx.getPackageIconWithUserId(packageName, numBoxInt, anonymousClass1);
                            if (packageIconWithUserId == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i2 = userId;
                            i3 = i;
                        }
                        drawable = drawable2;
                    } else {
                        drawable = badgedAppIcon;
                    }
                    String str = packageName;
                    String strM = PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(userId, str, "-");
                    if (i != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    return new CapsuleNotificationDataController.NotificationIconData(strM, str, drawable, (Icon) null, z, (Integer) null, (NotificationEntry) null, true);
                }
            }
            return null;
        }
        if (i5 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        i3 = anonymousClass1.I$1;
        i2 = anonymousClass1.I$0;
        packageName = (String) anonymousClass1.L$2;
        ResultKt.throwOnFailure(packageIconWithUserId);
        drawable2 = (Drawable) packageIconWithUserId;
        i = i3;
        userId = i2;
        drawable = drawable2;
        String str2 = packageName;
        String strM2 = PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(userId, str2, "-");
        if (i != 0) {
            z = true;
        } else {
            z = false;
        }
        return new CapsuleNotificationDataController.NotificationIconData(strM2, str2, drawable, (Icon) null, z, (Integer) null, (NotificationEntry) null, true);
    }

    public final List<String> getMediaPackageNames() {
        if (!isShowMediaNotification()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<Pair> listPlayerEntries = MediaPlayerData.INSTANCE.playerEntries();
        ArrayList arrayList = new ArrayList();
        for (Pair pair : listPlayerEntries) {
            String packageName = ((MediaControlPanel) pair.component2()).getController() != null ? ((MediaPlayerData.MediaSortKey) pair.component1()).getData().getPackageName() : null;
            if (packageName != null) {
                arrayList.add(packageName);
            }
        }
        return CollectionsKt___CollectionsKt.distinct(arrayList);
    }

    public final int getMediaPlayerCount() {
        if (isShowMediaNotification()) {
            return MediaPlayerData.INSTANCE.players().size();
        }
        return 0;
    }

    public final List<String> getMediaSourceKeys() {
        if (!isShowMediaNotification()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<Pair> listPlayerEntries = MediaPlayerData.INSTANCE.playerEntries();
        ArrayList arrayList = new ArrayList();
        for (Pair pair : listPlayerEntries) {
            MediaPlayerData.MediaSortKey mediaSortKey = (MediaPlayerData.MediaSortKey) pair.component1();
            String strM = ((MediaControlPanel) pair.component2()).getController() != null ? PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(mediaSortKey.getData().getUserId(), mediaSortKey.getData().getPackageName(), "-") : null;
            if (strM != null) {
                arrayList.add(strM);
            }
        }
        return CollectionsKt___CollectionsKt.distinct(arrayList);
    }

    public final boolean isShowMediaNotification() {
        KeyguardMediaController keyguardMediaController = (KeyguardMediaController) this.keyguardMediaController.get();
        boolean visible = keyguardMediaController != null ? keyguardMediaController.getVisible() : false;
        MediaSeedlingControlEx mediaSeedlingControlEx = (MediaSeedlingControlEx) this.mediaSeedlingControlEx.get();
        return visible && (mediaSeedlingControlEx != null && !mediaSeedlingControlEx.isSupportMediaSeedlingOnNotification());
    }
}
