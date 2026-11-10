package com.oplus.systemui.notification.row.oplusgroup;

import android.app.Notification;
import android.app.WallpaperColors;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.widget.CachingIconView;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.coui.appcompat.uiutil.ShadowUtils;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.materialsdk.uxcolor.UxWallpaperColors;
import com.oplus.materialsdk.uxcolor.color.Material3ColorManager;
import com.oplus.materialsdk.uxcolor.color.MaterialStyle;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupIconManager {
    public static final Companion Companion = new Companion();
    public static final String KEY_SMALL_ICON = "small_icon";
    private static final String TAG = "GroupIconManager";
    private final AppIconGroupColorManager appIconGroupColorManager;
    private final CoroutineDispatcher bgDispatcher;
    private IconInfo cacheInfo;
    private final Context context;
    private final Map<String, IconInfo> iconInfoMap = new LinkedHashMap();
    private final Map<String, Deferred> iconInfoTask = new LinkedHashMap();
    private Job lastJob;
    private final Lazy notificationMaterialColorEx;
    private final OplusContrastColorUtil oplusContrastColorUtil;
    private Integer originalIconColor;
    private final CoroutineScope scope;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Triple<Integer, Integer, Integer> getExpandedPillBgColor(Context context, Drawable drawable) {
            int argb = WallpaperColors.fromDrawable(drawable).getPrimaryColor().toArgb();
            int i = UxWallpaperColors.COLOR_MIDDLE_GRAY;
            Material3ColorManager material3ColorManager = new Material3ColorManager();
            MaterialStyle materialStyle = MaterialStyle.CONTENT;
            return new Triple<>(material3ColorManager.getUxSn(context, argb, false, 95.0d, materialStyle), new Material3ColorManager().getUxSn(context, argb, true, 20.0d, materialStyle), Integer.valueOf(argb));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class IconInfo {
        public final int darkColor;
        private final Boolean isGraysIcon;
        public final int lightColor;
        public final int seedColor;

        public IconInfo(int i, int i2, int i3, Boolean bool) {
            this.lightColor = i;
            this.darkColor = i2;
            this.seedColor = i3;
            this.isGraysIcon = bool;
        }

        public final Boolean component4() {
            return this.isGraysIcon;
        }

        public final IconInfo copy(int i, int i2, int i3, Boolean bool) {
            return new IconInfo(i, i2, i3, bool);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IconInfo)) {
                return false;
            }
            IconInfo iconInfo = (IconInfo) obj;
            return this.lightColor == iconInfo.lightColor && this.darkColor == iconInfo.darkColor && this.seedColor == iconInfo.seedColor && Intrinsics.areEqual(this.isGraysIcon, iconInfo.isGraysIcon);
        }

        public final int getDarkColor() {
            return this.darkColor;
        }

        public final int getLightColor() {
            return this.lightColor;
        }

        public final int getSeedColor() {
            return this.seedColor;
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.seedColor, MonoSpline$.ExternalSyntheticOutline0.m(this.darkColor, Integer.hashCode(this.lightColor) * 31, 31), 31);
            Boolean bool = this.isGraysIcon;
            return iM + (bool == null ? 0 : bool.hashCode());
        }

        public final Boolean isGraysIcon() {
            return this.isGraysIcon;
        }

        public String toString() {
            Boolean bool = this.isGraysIcon;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("IconInfo(lightColor=", this.lightColor, this.darkColor, ", darkColor=", ", seedColor=");
            sbM.append(this.seedColor);
            sbM.append(", isGraysIcon=");
            sbM.append(bool);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.oplusgroup.GroupIconManager$initEntryIconDrawable$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationEntry $entry;
        final /* synthetic */ FrameLayout $expandedPill;
        final /* synthetic */ Function1 $finishCallback;
        final /* synthetic */ boolean $isCapsule;
        final /* synthetic */ TextView $numberView;
        int I$0;
        int label;
        final /* synthetic */ GroupIconManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationEntry notificationEntry, GroupIconManager groupIconManager, FrameLayout frameLayout, TextView textView, boolean z, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$entry = notificationEntry;
            this.this$0 = groupIconManager;
            this.$expandedPill = frameLayout;
            this.$numberView = textView;
            this.$isCapsule = z;
            this.$finishCallback = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$entry, this.this$0, this.$expandedPill, this.$numberView, this.$isCapsule, this.$finishCallback, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            int i;
            IconInfo iconInfo;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                OplusNotificationEntryEx ex = this.$entry.getEx();
                int i3 = (ex == null || !((OplusNotificationEntryExImpl) ex).useAppIconForSmallIcon()) ? 0 : 1;
                GroupIconManager groupIconManager = this.this$0;
                NotificationEntry notificationEntry = this.$entry;
                if (i3 != 0) {
                    this.I$0 = i3;
                    this.label = 1;
                    Object objAccess$loadUseAppIconInfo = GroupIconManager.access$loadUseAppIconInfo(groupIconManager, notificationEntry, this);
                    if (objAccess$loadUseAppIconInfo != coroutine_suspended) {
                        i = i3;
                        obj = objAccess$loadUseAppIconInfo;
                        iconInfo = (IconInfo) obj;
                    }
                } else {
                    this.I$0 = i3;
                    this.label = 2;
                    Object objAccess$loadSmallIconInfo = GroupIconManager.access$loadSmallIconInfo(groupIconManager, notificationEntry, this);
                    if (objAccess$loadSmallIconInfo != coroutine_suspended) {
                        i = i3;
                        obj = objAccess$loadSmallIconInfo;
                        iconInfo = (IconInfo) obj;
                    }
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                i = this.I$0;
                ResultKt.throwOnFailure(obj);
                iconInfo = (IconInfo) obj;
            } else {
                if (i2 != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                i = this.I$0;
                ResultKt.throwOnFailure(obj);
                iconInfo = (IconInfo) obj;
            }
            if (iconInfo != null) {
                GroupIconManager groupIconManager2 = this.this$0;
                FrameLayout frameLayout = this.$expandedPill;
                TextView textView = this.$numberView;
                boolean z = this.$isCapsule;
                Function1 function1 = this.$finishCallback;
                groupIconManager2.cacheInfo = iconInfo;
                groupIconManager2.initPillBgAndNumberColor(frameLayout, iconInfo, textView);
                groupIconManager2.updateGroupBadgePillShadow(frameLayout, z, true, 1.0f);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    String hexString = Integer.toHexString(iconInfo.getLightColor());
                    String hexString2 = Integer.toHexString(iconInfo.getDarkColor());
                    String hexString3 = Integer.toHexString(iconInfo.getSeedColor());
                    boolean z2 = i != 0;
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("initEntryIconDrawable: set pill color:lightColor=", hexString, " darkColor=", hexString2, " seedColor=");
                    sbM.append(hexString3);
                    sbM.append(" useAppIcon=");
                    sbM.append(z2);
                    Log.d("SystemUi--Notification", "GroupIconManager-->".concat(sbM.toString()));
                }
                if (function1 != null) {
                    Boolean boolIsGraysIcon = iconInfo.isGraysIcon();
                    function1.invoke(Boxing.boxBoolean(boolIsGraysIcon != null ? boolIsGraysIcon.booleanValue() : false));
                }
            }
            return Unit.INSTANCE;
        }
    }

    public GroupIconManager(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, AppIconGroupColorManager appIconGroupColorManager, Lazy lazy) {
        this.context = context;
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.appIconGroupColorManager = appIconGroupColorManager;
        this.notificationMaterialColorEx = lazy;
        this.oplusContrastColorUtil = OplusContrastColorUtil.getInstance(context);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$loadSmallIconInfo(GroupIconManager groupIconManager, NotificationEntry notificationEntry, ContinuationImpl continuationImpl) {
        GroupIconManager$loadSmallIconInfo$1 groupIconManager$loadSmallIconInfo$1;
        String str;
        groupIconManager.getClass();
        if (continuationImpl instanceof GroupIconManager$loadSmallIconInfo$1) {
            groupIconManager$loadSmallIconInfo$1 = (GroupIconManager$loadSmallIconInfo$1) continuationImpl;
            int i = groupIconManager$loadSmallIconInfo$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                groupIconManager$loadSmallIconInfo$1.label = i - Integer.MIN_VALUE;
            } else {
                groupIconManager$loadSmallIconInfo$1 = new GroupIconManager$loadSmallIconInfo$1(groupIconManager, continuationImpl);
            }
        } else {
            groupIconManager$loadSmallIconInfo$1 = new GroupIconManager$loadSmallIconInfo$1(groupIconManager, continuationImpl);
        }
        Object obj = groupIconManager$loadSmallIconInfo$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = groupIconManager$loadSmallIconInfo$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            String smallIconKey = getSmallIconKey(notificationEntry);
            if (groupIconManager.iconInfoMap.containsKey(smallIconKey)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("initEntryIconDrawable: use cache data key=", smallIconKey, ",useAppIcon=false", "GroupIconManager-->", "SystemUi--Notification");
                }
                return groupIconManager.iconInfoMap.get(smallIconKey);
            }
            Deferred deferredAsync$default = groupIconManager.iconInfoTask.get(smallIconKey);
            if (deferredAsync$default == null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createSmallIconColorTask: key=", smallIconKey, "GroupIconManager-->", "SystemUi--Notification");
                }
                deferredAsync$default = BuildersKt.async$default(groupIconManager.scope, groupIconManager.bgDispatcher, 2, new GroupIconManager$createSmallIconColorTask$2(notificationEntry, groupIconManager, smallIconKey, null));
                groupIconManager.iconInfoTask.put(smallIconKey, deferredAsync$default);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("initEntryIconDrawable: use task key=", smallIconKey, ",useAppIcon=false", "GroupIconManager-->", "SystemUi--Notification");
            }
            try {
                groupIconManager$loadSmallIconInfo$1.L$0 = SpillingKt.nullOutSpilledVariable(notificationEntry);
                groupIconManager$loadSmallIconInfo$1.L$1 = smallIconKey;
                groupIconManager$loadSmallIconInfo$1.L$2 = SpillingKt.nullOutSpilledVariable(deferredAsync$default);
                groupIconManager$loadSmallIconInfo$1.label = 1;
                Object objAwait = deferredAsync$default.await(groupIconManager$loadSmallIconInfo$1);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objAwait;
                str = smallIconKey;
            } catch (CancellationException unused) {
                str = smallIconKey;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("initEntryIconDrawable: cancel task key=", str, ",useAppIcon=false", "GroupIconManager-->", "SystemUi--Notification");
                }
                return null;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            str = (String) groupIconManager$loadSmallIconInfo$1.L$1;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (CancellationException unused2) {
                if (LogUtil.getNormalDebug()) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("initEntryIconDrawable: cancel task key=", str, ",useAppIcon=false", "GroupIconManager-->", "SystemUi--Notification");
                }
                return null;
            }
        }
        return (IconInfo) obj;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public static final Object access$loadUseAppIconInfo(GroupIconManager groupIconManager, NotificationEntry notificationEntry, ContinuationImpl continuationImpl) {
        GroupIconManager$loadUseAppIconInfo$1 groupIconManager$loadUseAppIconInfo$1;
        String str;
        groupIconManager.getClass();
        if (continuationImpl instanceof GroupIconManager$loadUseAppIconInfo$1) {
            groupIconManager$loadUseAppIconInfo$1 = (GroupIconManager$loadUseAppIconInfo$1) continuationImpl;
            int i = groupIconManager$loadUseAppIconInfo$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                groupIconManager$loadUseAppIconInfo$1.label = i - Integer.MIN_VALUE;
            } else {
                groupIconManager$loadUseAppIconInfo$1 = new GroupIconManager$loadUseAppIconInfo$1(groupIconManager, continuationImpl);
            }
        } else {
            groupIconManager$loadUseAppIconInfo$1 = new GroupIconManager$loadUseAppIconInfo$1(groupIconManager, continuationImpl);
        }
        Object obj = groupIconManager$loadUseAppIconInfo$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = groupIconManager$loadUseAppIconInfo$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            String strBuildKey = groupIconManager.appIconGroupColorManager.buildKey(notificationEntry);
            try {
                Deferred orCreateTask = groupIconManager.appIconGroupColorManager.getOrCreateTask(notificationEntry);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "GroupIconManager-->".concat("initEntryIconDrawable: await appIcon task key=" + strBuildKey));
                }
                groupIconManager$loadUseAppIconInfo$1.L$0 = SpillingKt.nullOutSpilledVariable(notificationEntry);
                groupIconManager$loadUseAppIconInfo$1.L$1 = strBuildKey;
                groupIconManager$loadUseAppIconInfo$1.L$2 = SpillingKt.nullOutSpilledVariable(orCreateTask);
                groupIconManager$loadUseAppIconInfo$1.label = 1;
                Object objAwait = orCreateTask.await(groupIconManager$loadUseAppIconInfo$1);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objAwait;
                str = strBuildKey;
            } catch (CancellationException unused) {
                str = strBuildKey;
                if (LogUtil.getNormalDebug()) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("initEntryIconDrawable: cancel await appIcon task key=", str, "GroupIconManager-->", "SystemUi--Notification");
                }
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            str = (String) groupIconManager$loadUseAppIconInfo$1.L$1;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (CancellationException unused2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("initEntryIconDrawable: cancel await appIcon task key=", str, "GroupIconManager-->", "SystemUi--Notification");
                }
            }
        }
        AppIconGroupColorManager.AppIconColorConfig appIconColorConfig = (AppIconGroupColorManager.AppIconColorConfig) obj;
        if (appIconColorConfig == null) {
            return null;
        }
        return new IconInfo(appIconColorConfig.getLightColor(), appIconColorConfig.getDarkColor(), appIconColorConfig.getSeedColor(), Boxing.boxBoolean(false));
    }

    public static String getSmallIconKey(NotificationEntry notificationEntry) {
        String key = notificationEntry.getKey();
        Notification notification = notificationEntry.getSbn().getNotification();
        return key + ":small_icon:" + (notification != null ? notification.getSmallIcon() : null);
    }

    public final Integer getOriginalIconColor() {
        return this.originalIconColor;
    }

    public final void initEntryIconDrawable(NotificationEntry notificationEntry, CachingIconView cachingIconView, TextView textView, FrameLayout frameLayout, boolean z, Function1 function1) {
        Job job = this.lastJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.lastJob = BuildersKt.launch$default(this.scope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(notificationEntry, this, frameLayout, textView, z, function1, null), 2);
    }

    public final void initPillBgAndNumberColor(FrameLayout frameLayout, IconInfo iconInfo, TextView textView) {
        NotificationBlurMode notificationBlurMode = (NotificationBlurMode) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).getBlurMode().getValue();
        boolean zIsNightMode = (notificationBlurMode == NotificationBlurMode.WALLPAPER_A || notificationBlurMode == NotificationBlurMode.WALLPAPER_B) ? false : OplusDarkModeUtil.isNightMode(this.context);
        Drawable background = frameLayout != null ? frameLayout.getBackground() : null;
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setColor(zIsNightMode ? iconInfo.getDarkColor() : iconInfo.getLightColor());
        if (textView != null) {
            if (zIsNightMode) {
                textView.setTextColor(-1);
                textView.setAlpha(0.9f);
            } else {
                textView.setTextColor(-16777216);
                textView.setAlpha(0.7f);
            }
        }
    }

    public final void setOriginalIconColor(Integer num) {
        this.originalIconColor = num;
    }

    public final void updateGroupBadgePillShadow(FrameLayout frameLayout, boolean z, boolean z2, float f) {
        if (frameLayout == null || z || !NotificationMaterialColorExImpl.Companion.isSupported(this.context)) {
            return;
        }
        if (!z2 && frameLayout.getElevation() > 0.0f) {
            ShadowUtils.clearShadow(frameLayout);
            return;
        }
        int i = (int) (12.0f * (f > 0.9f ? 1.0f : 0.0f));
        if (((int) frameLayout.getElevation()) == i) {
            return;
        }
        if (i == 0) {
            ShadowUtils.clearShadow(frameLayout);
        } else {
            ShadowUtils.setElevationToViewFromOPlusView(frameLayout, i, Color.argb(65, 0, 0, 0), 0, 0, 0, 20);
        }
    }

    public final void updatePillBgAndNumberColor(FrameLayout frameLayout, TextView textView, NotificationEntry notificationEntry) {
        OplusNotificationEntryEx ex = notificationEntry.getEx();
        if (ex == null || !((OplusNotificationEntryExImpl) ex).useAppIconForSmallIcon()) {
            IconInfo iconInfo = this.iconInfoMap.get(getSmallIconKey(notificationEntry));
            if (iconInfo != null) {
                initPillBgAndNumberColor(frameLayout, iconInfo, textView);
                return;
            }
            return;
        }
        AppIconGroupColorManager.AppIconColorConfig cached = this.appIconGroupColorManager.getCached(this.appIconGroupColorManager.buildKey(notificationEntry));
        IconInfo iconInfo2 = cached != null ? new IconInfo(cached.getLightColor(), cached.getDarkColor(), cached.getSeedColor(), Boolean.FALSE) : null;
        if (iconInfo2 == null) {
            iconInfo2 = this.cacheInfo;
        }
        if (iconInfo2 != null) {
            initPillBgAndNumberColor(frameLayout, iconInfo2, textView);
        }
    }
}
