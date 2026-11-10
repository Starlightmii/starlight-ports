package com.oplus.systemui.notification.lockscreen.notification;

import android.app.Notification;
import android.app.Person;
import android.app.WallpaperColors;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.internal.widget.PeopleHelper;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.oplus.materialsdk.uxcolor.UxWallpaperColors;
import com.oplus.materialsdk.uxcolor.color.Material3ColorManager;
import com.oplus.materialsdk.uxcolor.color.MaterialStyle;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.notification.util.RoundRectDrawableUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplus.view.OplusView;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationUtils {
    public static final CapsuleNotificationUtils INSTANCE = new CapsuleNotificationUtils();
    private static final String TAG = "CapsuleNotificationIconUtils";
    private static PeopleHelper mPeopleHelper;

    public static final Drawable getBadgedAppIcon(Context context, String str, int i) {
        Object obj;
        try {
            PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(context, i);
            obj = Result.constructor-impl(packageManagerForUser.getUserBadgedIcon(packageManagerForUser.getApplicationIcon(str), UserHandle.of(i)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "loadBadgedAppIcon failed: pkg=", str, " userId=", ", ");
            sbM.append(th2);
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, sbM.toString());
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        return (Drawable) obj;
    }

    public static final int getIconColor(Context context, Drawable drawable) {
        int argb = WallpaperColors.fromDrawable(drawable).getPrimaryColor().toArgb();
        int i = UxWallpaperColors.COLOR_MIDDLE_GRAY;
        return new Material3ColorManager().getUxSn(context, argb, false, 70.0d, MaterialStyle.CONTENT).intValue();
    }

    public static final NotificationEntry getLatestEntry(List<? extends PipelineEntry> list) {
        Object obj;
        List listEmptyList;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            GroupEntry groupEntry = (PipelineEntry) it.next();
            if (groupEntry instanceof NotificationEntry) {
                listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(groupEntry);
            } else if (groupEntry instanceof GroupEntry) {
                NotificationEntry summary = groupEntry.getSummary();
                if (summary == null || (listEmptyList = summary.getAttachedNotifChildren()) == null) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
            } else {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, listEmptyList);
        }
        Iterator it2 = arrayList.iterator();
        if (it2.hasNext()) {
            Object next = it2.next();
            if (it2.hasNext()) {
                long updateTime = ((NotificationEntry) next).mNotificationEntryEx.getUpdateTime();
                do {
                    Object next2 = it2.next();
                    long updateTime2 = ((NotificationEntry) next2).mNotificationEntryEx.getUpdateTime();
                    if (updateTime < updateTime2) {
                        next = next2;
                        updateTime = updateTime2;
                    }
                } while (it2.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (NotificationEntry) obj;
    }

    public static final boolean isAutoSummary(NotificationEntry notificationEntry) {
        Notification notification;
        Notification notification2 = notificationEntry.getSbn().getNotification();
        return notification2 != null && (notification2.flags & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) > 0 && (notification = notificationEntry.getSbn().getNotification()) != null && notification.isGroupSummary();
    }

    public static final void setElevationToViewFromOPlusView(View view, float f, int i, float f2, float f3, float f4, float f5, float f6) {
        view.setOutlineAmbientShadowColor(i);
        view.setOutlineSpotShadowColor(i);
        view.setElevation(f);
        try {
            new OplusView(view).setOverrideLightSourceGeometry(f2, f3, f4, f5, f6);
        } catch (Exception e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "setOverrideLightSourceGeometry error:", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0040  */
    public static final Drawable tryLoadMessagingAvatar(Context context, NotificationEntry notificationEntry) {
        Notification.MessagingStyle.Message messageFromBundle;
        Icon iconCreateAvatarSymbol;
        Drawable drawableLoadDrawable;
        Parcelable[] parcelableArray = notificationEntry.getSbn().getNotification().extras.getParcelableArray("android.messages");
        if (parcelableArray != null && parcelableArray.length != 0 && (messageFromBundle = Notification.MessagingStyle.Message.getMessageFromBundle((Bundle) ArraysKt___ArraysKt.last(parcelableArray))) != null) {
            Notification notification = notificationEntry.getSbn().getNotification();
            if (notification != null) {
                Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(context, notification);
                Notification.Style style = builderRecoverBuilder.getStyle();
                Notification.MessagingStyle messagingStyle = style instanceof Notification.MessagingStyle ? (Notification.MessagingStyle) style : null;
                if (messagingStyle == null) {
                    iconCreateAvatarSymbol = null;
                } else if (messagingStyle.isGroupConversation()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CapsuleNotificationIconUtils-->loadConversationAvatar isGroupConversation");
                    }
                    iconCreateAvatarSymbol = messagingStyle.getShortcutIcon();
                    if (iconCreateAvatarSymbol == null) {
                        iconCreateAvatarSymbol = notification.getLargeIcon();
                    }
                } else {
                    Person senderPerson = messageFromBundle.getSenderPerson();
                    if (senderPerson != null) {
                        Icon icon = senderPerson.getIcon();
                        if (icon != null) {
                            iconCreateAvatarSymbol = icon;
                        } else {
                            if (mPeopleHelper == null) {
                                PeopleHelper peopleHelper = new PeopleHelper();
                                peopleHelper.init(context);
                                mPeopleHelper = peopleHelper;
                            }
                            PeopleHelper peopleHelper2 = mPeopleHelper;
                            if (peopleHelper2 != null) {
                                CharSequence name = senderPerson.getName();
                                String string = name != null ? name.toString() : null;
                                if (string == null) {
                                    string = "";
                                }
                                if (string.length() == 0) {
                                    iconCreateAvatarSymbol = null;
                                } else {
                                    PeopleHelper.NameToPrefixMap nameToPrefixMapMapUniqueNamesToPrefixWithGroupList = peopleHelper2.mapUniqueNamesToPrefixWithGroupList(CollectionsKt__CollectionsJVMKt.listOf(CollectionsKt__CollectionsJVMKt.listOf(messageFromBundle)));
                                    String prefix = nameToPrefixMapMapUniqueNamesToPrefixWithGroupList != null ? nameToPrefixMapMapUniqueNamesToPrefixWithGroupList.getPrefix(string) : null;
                                    String str = prefix != null ? prefix : "";
                                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                        Log.d("SystemUi--Notification", "CapsuleNotificationIconUtils-->loadConversationAvatar createAvatarSymbol");
                                    }
                                    iconCreateAvatarSymbol = peopleHelper2.createAvatarSymbol(string, str, builderRecoverBuilder.getSmallIconColor(false));
                                }
                            } else {
                                iconCreateAvatarSymbol = null;
                            }
                        }
                    } else {
                        iconCreateAvatarSymbol = null;
                    }
                }
            } else {
                iconCreateAvatarSymbol = null;
            }
            if (iconCreateAvatarSymbol != null && (drawableLoadDrawable = iconCreateAvatarSymbol.loadDrawable(context)) != null) {
                return RoundRectDrawableUtil.getRoundRectDrawable(drawableLoadDrawable, 0.0f, context.getResources().getDimensionPixelSize(2131165910), context.getResources().getDimensionPixelSize(2131165910), context);
            }
        }
        return null;
    }

    public static final Drawable wrapWithWhiteBg(Drawable drawable, Context context, float f) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(-1), drawable});
        int iDp2px = DeviceUtil.dp2px(context, f);
        layerDrawable.setLayerInset(1, iDp2px, iDp2px, iDp2px, iDp2px);
        return layerDrawable;
    }
}
