package com.oplus.systemui.statusbar.notification.collection;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.OplusBaseConfigurationAddon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.LruCache;
import androidx.car.app.hardware.common.CarZone$;
import androidx.palette.graphics.Palette;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.TargetSdkResolver;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.notification.row.oplusgroup.AppIconGroupColorManager;
import com.oplus.systemui.qs.receiver.PackageChangeReceiver;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.utils.DrawableUtilsKt;
import java.lang.invoke.VarHandle;
import java.util.function.BiConsumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationResolverExImpl extends NotificationResolverEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationResolverExImpl";
    private final AppIconGroupColorManager appIconGroupColorManager;
    private final ConfigurationController configurationController;
    private final NotificationResolverExImpl$configurationListener$1 configurationListener;
    private final Context context;
    private final LruCache<String, Drawable> headerIconBadgeCache;
    private IconDrawableFactory mIconDrawableFactory;
    public long mLastIconConfiguration;
    public long mLastThemeChange;
    public long mLastThemeChangeFlag;
    private final IReceiverListener mPackageChangeReceiver;
    private final Handler mainHandler;
    private final LruCache<String, NotificationPackage> packages;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.NotificationResolverExImpl$clearAllPackageIcons$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BiConsumer {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // java.util.function.BiConsumer
        public final void accept(Object obj, Object obj2) {
            ((NotificationPackage) obj2).setPackageIcon(null);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.NotificationResolverExImpl$getPackageIconWithUserId$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $identifier;
        final /* synthetic */ NotificationPackage $packageInfo;
        final /* synthetic */ String $pkg;
        final /* synthetic */ Integer $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i, String str, NotificationPackage notificationPackage, Integer num, Continuation continuation) {
            super(2, continuation);
            this.$identifier = i;
            this.$pkg = str;
            this.$packageInfo = notificationPackage;
            this.$userId = num;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationResolverExImpl.this.new AnonymousClass2(this.$identifier, this.$pkg, this.$packageInfo, this.$userId, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Drawable.ConstantState constantState;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(NotificationResolverExImpl.this.getContext(), this.$identifier);
            try {
                ApplicationInfo applicationInfo = packageManagerForUser.getApplicationInfo(this.$pkg, 0);
                Drawable drawable = packageManagerForUser.getResourcesForApplication(applicationInfo).getDrawable(applicationInfo.icon, null);
                if (drawable == null) {
                    IconDrawableFactory iconDrawableFactory = NotificationResolverExImpl.this.mIconDrawableFactory;
                    drawable = iconDrawableFactory != null ? iconDrawableFactory.getBadgedIcon(applicationInfo) : null;
                }
                Drawable drawableAccess$downScaleIcon = NotificationResolverExImpl.access$downScaleIcon(NotificationResolverExImpl.this, drawable);
                if (drawableAccess$downScaleIcon != null) {
                    drawable = drawableAccess$downScaleIcon;
                }
                if (drawable != null && FlavorTwoFeatureOption.INSTANCE.shouldUseOpaquePlateIcon(this.$pkg)) {
                    drawable = NotificationResolverExImpl.access$toOpaquePlateIcon(NotificationResolverExImpl.this, drawable);
                }
                NotificationPackage notificationPackage = this.$packageInfo;
                if (notificationPackage != null) {
                    notificationPackage.setPackageIcon(drawable);
                }
                if (drawable != null && (constantState = drawable.getConstantState()) != null) {
                    return constantState.newDrawable();
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(NotificationResolverExImpl.TAG, "Failed looking up ApplicationInfo for pkg:" + this.$pkg + ", userId:" + this.$userId);
            } catch (Resources.NotFoundException e) {
                Log.e(NotificationResolverExImpl.TAG, "Failed found resource for pkg:" + this.$pkg + ", userId:" + this.$userId, e);
            } catch (Exception e2) {
                Log.e(NotificationResolverExImpl.TAG, "Exception happened for pkg:" + this.$pkg + ", userId:" + this.$userId, e2);
            }
            return null;
        }
    }

    public NotificationResolverExImpl(Context context, ConfigurationController configurationController, UserTracker userTracker, AppIconGroupColorManager appIconGroupColorManager, Handler handler) {
        this.context = context;
        this.configurationController = configurationController;
        this.userTracker = userTracker;
        this.appIconGroupColorManager = appIconGroupColorManager;
        this.mainHandler = handler;
        NotificationResolverExImpl$mPackageChangeReceiver$1 notificationResolverExImpl$mPackageChangeReceiver$1 = new NotificationResolverExImpl$mPackageChangeReceiver$1();
        notificationResolverExImpl$mPackageChangeReceiver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPackageChangeReceiver = notificationResolverExImpl$mPackageChangeReceiver$1;
        NotificationResolverExImpl$configurationListener$1 notificationResolverExImpl$configurationListener$1 = new NotificationResolverExImpl$configurationListener$1();
        notificationResolverExImpl$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = notificationResolverExImpl$configurationListener$1;
        this.packages = new LruCache<>(150);
        this.headerIconBadgeCache = new LruCache<>(32);
    }

    public static final Drawable access$downScaleIcon(NotificationResolverExImpl notificationResolverExImpl, Drawable drawable) {
        notificationResolverExImpl.getClass();
        if (drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return drawable;
        }
        int iDp2px = DeviceUtil.dp2px(notificationResolverExImpl.context, 32.0f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return new BitmapDrawable(DrawableUtilsKt.getScaleDrawable(bitmapCreateBitmap, iDp2px, iDp2px));
    }

    public static final Drawable access$toOpaquePlateIcon(NotificationResolverExImpl notificationResolverExImpl, Drawable drawable) {
        Bitmap bitmap;
        notificationResolverExImpl.getClass();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
            if (bitmap.isRecycled() || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                bitmap = null;
            }
            if (bitmap != null) {
                int iDp2px = DeviceUtil.dp2px(notificationResolverExImpl.context, 32.0f);
                Palette.Builder builderFrom = Palette.from(bitmap);
                builderFrom.mFilters.clear();
                Palette.Swatch swatch = builderFrom.generate().mDominantSwatch;
                int rgb = swatch != null ? swatch.getRgb() : -1;
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iDp2px, iDp2px, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.drawColor(rgb);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                return new BitmapDrawable(notificationResolverExImpl.context.getResources(), bitmapCreateBitmap);
            }
        }
        return drawable;
    }

    public static NotificationPackage loadOrMergePackageFromPm$default(NotificationResolverExImpl notificationResolverExImpl, int i, String str) {
        String label;
        if (str.length() != 0) {
            String str2 = i + str;
            NotificationPackage notificationPackage = notificationResolverExImpl.packages.get(str2);
            if (notificationPackage != null && notificationPackage.getTargetSdk() != -1 && (label = notificationPackage.getLabel()) != null && label.length() != 0) {
                return notificationPackage;
            }
            PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(notificationResolverExImpl.context, i);
            try {
                ApplicationInfo applicationInfo = packageManagerForUser.getApplicationInfo(str, 0);
                if (notificationPackage == null) {
                    notificationPackage = new NotificationPackage(-1, null, null);
                }
                if (notificationPackage.getTargetSdk() == -1) {
                    notificationPackage.setTargetSdk(applicationInfo.targetSdkVersion);
                }
                String label2 = notificationPackage.getLabel();
                if (label2 == null || label2.length() == 0) {
                    notificationPackage.setLabel(applicationInfo.loadLabel(packageManagerForUser).toString());
                }
                notificationResolverExImpl.packages.put(str2, notificationPackage);
                return notificationPackage;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("Failed looking up ApplicationInfo for ", str, " user=", i), e);
            } catch (Resources.NotFoundException e2) {
                Log.e(TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("Failed found resource for ", str, " user=", i), e2);
            } catch (Exception e3) {
                Log.e(TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("Exception loading package ", str, " user=", i), e3);
            }
        }
        return null;
    }

    public final void clearAllPackageIcons() {
        this.packages.snapshot().forEach(AnonymousClass1.INSTANCE);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.packages.size(), "clearAllPackageIcons: done, size=", "NotificationResolverExImpl-->", "SystemUi--Notification");
        }
    }

    public String getAppName(Context context, Notification notification) {
        Bundle bundle = notification.extras;
        Parcelable parcelable = bundle != null ? bundle.getParcelable("android.appInfo") : null;
        boolean z = parcelable instanceof ApplicationInfo;
        String str = z ? ((ApplicationInfo) parcelable).packageName : "";
        int userId = this.userTracker.getUserId();
        str.getClass();
        NotificationPackage notificationPackageLoadOrMergePackageFromPm$default = loadOrMergePackageFromPm$default(this, userId, str);
        try {
            if (notificationPackageLoadOrMergePackageFromPm$default != null) {
                String strLoadHeaderAppName = loadHeaderAppName(context, notification, str);
                return strLoadHeaderAppName == null ? "" : strLoadHeaderAppName;
            }
            String strLoadHeaderAppName2 = Notification.Builder.recoverBuilder(context, notification).loadHeaderAppName();
            strLoadHeaderAppName2.getClass();
            return strLoadHeaderAppName2;
        } catch (RuntimeException e) {
            Log.e(TAG, "Unable to recover builder", e);
            if (notificationPackageLoadOrMergePackageFromPm$default == null || notificationPackageLoadOrMergePackageFromPm$default.getLabel() == null) {
                return z ? ((ApplicationInfo) parcelable).loadLabel(context.getPackageManager()).toString() : "";
            }
            return String.valueOf(notificationPackageLoadOrMergePackageFromPm$default.getLabel());
        }
    }

    public final ConfigurationController getConfigurationController() {
        return this.configurationController;
    }

    public final Context getContext() {
        return this.context;
    }

    public Drawable getHeaderIconBadgeDrawable(String str) {
        return this.headerIconBadgeCache.get(str);
    }

    public String getLabelByPkg(String str) {
        String label;
        NotificationPackage notificationPackageLoadOrMergePackageFromPm$default = loadOrMergePackageFromPm$default(this, this.userTracker.getUserId(), str);
        return (notificationPackageLoadOrMergePackageFromPm$default == null || (label = notificationPackageLoadOrMergePackageFromPm$default.getLabel()) == null) ? "" : label;
    }

    public Object getPackageIcon(String str, Continuation continuation) {
        return getPackageIconWithUserId(str, Boxing.boxInt(this.userTracker.getUserId()), continuation);
    }

    public Object getPackageIconForNotification(StatusBarNotification statusBarNotification, Continuation continuation) {
        if (statusBarNotification == null) {
            return null;
        }
        Object packageIconWithUserId = getPackageIconWithUserId(statusBarNotification.getPackageName(), Boxing.boxInt(statusBarNotification.getUserId()), continuation);
        return packageIconWithUserId == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? packageIconWithUserId : (Drawable) packageIconWithUserId;
    }

    public Object getPackageIconWithUserId(String str, Integer num, Continuation continuation) {
        Drawable packageIcon;
        Drawable.ConstantState constantState;
        Bitmap bitmap;
        int iIntValue = num != null ? num.intValue() : this.userTracker.getUserId();
        NotificationPackage notificationPackageLoadOrMergePackageFromPm$default = loadOrMergePackageFromPm$default(this, iIntValue, str);
        if ((notificationPackageLoadOrMergePackageFromPm$default != null ? notificationPackageLoadOrMergePackageFromPm$default.getPackageIcon() : null) != null) {
            Drawable packageIcon2 = notificationPackageLoadOrMergePackageFromPm$default != null ? notificationPackageLoadOrMergePackageFromPm$default.getPackageIcon() : null;
            if (packageIcon2 == null) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "isInvalidBitmapDrawable: drawable is null", null, 8);
            } else {
                if (!(packageIcon2 instanceof BitmapDrawable) || ((bitmap = ((BitmapDrawable) packageIcon2).getBitmap()) != null && !bitmap.isRecycled())) {
                    if (notificationPackageLoadOrMergePackageFromPm$default == null || (packageIcon = notificationPackageLoadOrMergePackageFromPm$default.getPackageIcon()) == null || (constantState = packageIcon.getConstantState()) == null) {
                        return null;
                    }
                    return constantState.newDrawable();
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "isInvalidBitmapDrawable: bitmap is null or recycled", null, 8);
            }
        }
        return BuildersKt.withContext(Dispatchers.getDefault(), new AnonymousClass2(iIntValue, str, notificationPackageLoadOrMergePackageFromPm$default, num, null), continuation);
    }

    public int getTargetSdkByPkg(TargetSdkResolver targetSdkResolver, StatusBarNotification statusBarNotification) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = (ApplicationInfo) statusBarNotification.getNotification().extras.getParcelable("android.appInfo", ApplicationInfo.class);
        } catch (RuntimeException e) {
            Log.e(TAG, "Failed to get application info for " + statusBarNotification.getKey(), e);
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            return applicationInfo.targetSdkVersion;
        }
        NotificationPackage notificationPackageLoadOrMergePackageFromPm$default = loadOrMergePackageFromPm$default(this, statusBarNotification.getUser().getIdentifier(), statusBarNotification.getPackageName());
        int targetSdk = notificationPackageLoadOrMergePackageFromPm$default != null ? notificationPackageLoadOrMergePackageFromPm$default.getTargetSdk() : -1;
        if (targetSdk == -1) {
            targetSdk = super.getTargetSdkByPkg(targetSdkResolver, statusBarNotification);
            updatePackage(statusBarNotification.getPackageName(), statusBarNotification.getUser().getIdentifier(), targetSdk);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationResolverExImpl-->".concat("onEntryBind find sbn.packageName=" + statusBarNotification.getPackageName() + " targetSdk=" + targetSdk));
        }
        return targetSdk;
    }

    public final UserTracker getUserTracker() {
        return this.userTracker;
    }

    public final void initialize() {
        ThreadManager.Companion companion = ThreadManager.Companion;
        companion.getInstance().start();
        Handler threadHandler = companion.getInstance().getThreadHandler();
        PackageChangeReceiver packageChangeReceiver = PackageChangeReceiver.INSTANCE;
        packageChangeReceiver.register(this.context, UserHandle.ALL, threadHandler);
        packageChangeReceiver.addListener(this.mPackageChangeReceiver);
        this.configurationController.addCallback(this.configurationListener);
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(this.context);
        this.mLastIconConfiguration = OplusBaseConfigurationAddon.getUxIconConfig(ActivityManager.getService().getConfiguration());
        this.mLastThemeChange = OplusBaseConfigurationAddon.getThemeChanged(ActivityManager.getService().getConfiguration());
        this.mLastThemeChangeFlag = OplusBaseConfigurationAddon.getThemeChangedFlags(ActivityManager.getService().getConfiguration());
    }

    public final String loadHeaderAppName(Context context, Notification notification, String str) {
        String labelByPkg;
        PackageManager packageManager = context.getPackageManager();
        if (notification.extras.containsKey("android.substName")) {
            String packageName = context.getPackageName();
            labelByPkg = notification.extras.getString("android.substName");
            if (labelByPkg == null) {
                labelByPkg = "";
            }
            if (packageManager.checkPermission("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME", packageName) != 0) {
                Log.w(TAG, CarZone$.ExternalSyntheticOutline0.m("warning: pkg ", packageName, " attempting to substitute app name '", labelByPkg, "' without holding perm android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME"));
                labelByPkg = null;
            }
        } else {
            labelByPkg = null;
        }
        if (TextUtils.isEmpty(labelByPkg)) {
            labelByPkg = getLabelByPkg(str);
        }
        if (TextUtils.isEmpty(labelByPkg)) {
            return null;
        }
        return String.valueOf(labelByPkg);
    }

    public void putHeaderIconBadgeDrawable(String str, Drawable drawable) {
        Drawable drawableMutate;
        Drawable drawableNewDrawable;
        LruCache<String, Drawable> lruCache = this.headerIconBadgeCache;
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null || (drawableNewDrawable = constantState.newDrawable()) == null || (drawableMutate = drawableNewDrawable.mutate()) == null) {
            drawableMutate = drawable.mutate();
        }
        lruCache.put(str, drawableMutate);
    }

    public void removeHeaderIconBadgeDrawable(String str) {
        this.headerIconBadgeCache.remove(str);
    }

    public void updatePackage(String str, int i, int i2) {
        ThreadManager companion = ThreadManager.Companion.getInstance();
        NotificationResolverExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationResolverExImpl$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = i;
        externalSyntheticLambda0.f$2 = str;
        externalSyntheticLambda0.f$3 = i2;
        VarHandle.storeStoreFence();
        companion.postInThread(externalSyntheticLambda0);
    }

    public int getTargetSdkByPkg(String str, int i) {
        NotificationPackage notificationPackageLoadOrMergePackageFromPm$default = loadOrMergePackageFromPm$default(this, i, str);
        if (notificationPackageLoadOrMergePackageFromPm$default != null) {
            return notificationPackageLoadOrMergePackageFromPm$default.getTargetSdk();
        }
        return -1;
    }
}
