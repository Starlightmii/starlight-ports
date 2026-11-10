package com.oplus.systemui.media.seedling.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Trace;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.DependencyEx;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.systemui.statusbar.util.OpImageUtils;
import com.oplus.wrapper.os.SystemProperties;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.File;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();
    public static final boolean LOG_STACK = SystemProperties.getBoolean("persist.debug.mc_log_stack", false);
    private static final String TAG = "FileUtil";

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.utils.FileUtil$loadAppIconToIcon$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref.ObjectRef<Icon> $icon;
        final /* synthetic */ String $packageName;
        final /* synthetic */ Integer $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Integer num, Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$userId = num;
            this.$icon = objectRef;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$packageName, this.$userId, this.$icon, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
                String str = this.$packageName;
                Integer num = this.$userId;
                this.label = 1;
                obj = notificationResolverEx.getPackageIconWithUserId(str, num, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Drawable drawable = (Drawable) obj;
            if (drawable == null) {
                return null;
            }
            this.$icon.element = Icon.createWithBitmap(OpImageUtils.drawableToBitmap(drawable));
            return Unit.INSTANCE;
        }
    }

    public static final Uri access$convertDrawableToBitmap(Context context, Drawable drawable, String str, int i, boolean z) {
        Bitmap bitmapDrawableToBitmap = BitmapUtils.drawableToBitmap(drawable);
        Bitmap bitmapAddPaddingToBitmap = bitmapDrawableToBitmap != null ? OpImageUtils.addPaddingToBitmap(bitmapDrawableToBitmap, (int) context.getResources().getDimension(2131167954), (int) context.getResources().getDimension(2131167953)) : null;
        String str2 = (z ? "second" : "default") + str + (i % 4) + ".png";
        if (bitmapAddPaddingToBitmap != null) {
            return convertBitmapToUri(context, bitmapAddPaddingToBitmap, str2);
        }
        return null;
    }

    public static final Bitmap blur(Bitmap bitmap, float f, int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        FileUtil$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new FileUtil$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = bitmap;
        externalSyntheticLambda1.f$1 = i;
        externalSyntheticLambda1.f$2 = f;
        externalSyntheticLambda1.f$3 = jCurrentTimeMillis;
        VarHandle.storeStoreFence();
        return (Bitmap) runWithTracing("FileUtil.blur", externalSyntheticLambda1);
    }

    public static final Object convertActionIconToUri(Context context, Icon icon, String str, int i, CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        Drawable drawableLoadDrawableAsUser = icon.loadDrawableAsUser(context, OsBinderCacheUtils.getCurrentUserId());
        return drawableLoadDrawableAsUser == null ? new Pair((Object) null, (Object) null) : convertDrawableToUri(context, drawableLoadDrawableAsUser, str, i, coroutineDispatcher, continuation);
    }

    public static final Uri convertBitmapToUri(Context context, Bitmap bitmap, String str) {
        FileUtil$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new FileUtil$.ExternalSyntheticLambda2(0);
        externalSyntheticLambda2.f$0 = str;
        externalSyntheticLambda2.f$1 = context;
        externalSyntheticLambda2.f$2 = bitmap;
        VarHandle.storeStoreFence();
        return (Uri) runWithTracing("FileUtil.convertBitmapToUri", externalSyntheticLambda2);
    }

    public static final Bitmap convertDrawableToBitmap(Context context, Drawable drawable) {
        INSTANCE.getClass();
        Drawable drawableCopyForIndependentTint = copyForIndependentTint(drawable);
        drawableCopyForIndependentTint.setColorFilter(context.getColor(2131102739), PorterDuff.Mode.SRC_IN);
        Bitmap bitmapDrawableToBitmap = BitmapUtils.drawableToBitmap(drawableCopyForIndependentTint);
        if (bitmapDrawableToBitmap != null) {
            return OpImageUtils.addPaddingToBitmap(bitmapDrawableToBitmap, (int) context.getResources().getDimension(2131167954), (int) context.getResources().getDimension(2131167953));
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0114  */
    /* JADX WARN: Code duplicated, block: B:33:0x013c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0184  */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public static final Object convertDrawableToUri(Context context, Drawable drawable, String str, int i, CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        convertDrawableToUri.1 r3;
        CoroutineDispatcher coroutineDispatcher2;
        int i2;
        Drawable drawable2;
        String str2;
        Context context2;
        Drawable drawable3;
        String str3;
        Drawable drawable4;
        int i3;
        Uri uri;
        Object objWithContext;
        Uri uri2;
        Context context3;
        CoroutineDispatcher coroutineDispatcher3;
        Uri uri3;
        if (continuation instanceof convertDrawableToUri.1) {
            r3 = (convertDrawableToUri.1) continuation;
            int i4 = r3.label;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                r3.label = i4 - Integer.MIN_VALUE;
            } else {
                r3 = new convertDrawableToUri.1(continuation);
            }
        } else {
            r3 = new convertDrawableToUri.1(continuation);
        }
        Object objWithContext2 = r3.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = r3.label;
        Uri uri4 = null;
        if (i5 == 0) {
            ResultKt.throwOnFailure(objWithContext2);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            convertDrawableToUri.drawableDefault.1 r5 = new convertDrawableToUri.drawableDefault.1(drawable, context, (Continuation) null);
            r3.L$0 = context;
            r3.L$1 = drawable;
            r3.L$2 = str;
            coroutineDispatcher2 = coroutineDispatcher;
            r3.L$3 = coroutineDispatcher2;
            i2 = i;
            r3.I$0 = i2;
            r3.label = 1;
            objWithContext2 = BuildersKt.withContext(main, r5, r3);
            if (objWithContext2 != coroutine_suspended) {
                drawable2 = drawable;
                str2 = str;
                context2 = context;
            }
            return coroutine_suspended;
        }
        if (i5 == 1) {
            int i6 = r3.I$0;
            CoroutineDispatcher coroutineDispatcher4 = (CoroutineDispatcher) r3.L$3;
            str2 = (String) r3.L$2;
            Drawable drawable5 = (Drawable) r3.L$1;
            context2 = (Context) r3.L$0;
            ResultKt.throwOnFailure(objWithContext2);
            i2 = i6;
            coroutineDispatcher2 = coroutineDispatcher4;
            drawable2 = drawable5;
        } else {
            if (i5 == 2) {
                i3 = r3.I$0;
                Drawable drawable6 = (Drawable) r3.L$4;
                CoroutineDispatcher coroutineDispatcher5 = (CoroutineDispatcher) r3.L$3;
                str3 = (String) r3.L$2;
                drawable3 = (Drawable) r3.L$1;
                context2 = (Context) r3.L$0;
                ResultKt.throwOnFailure(objWithContext2);
                coroutineDispatcher2 = coroutineDispatcher5;
                drawable4 = drawable6;
                uri = (Uri) objWithContext2;
                com.oplusos.systemui.common.feature.FeatureOption.INSTANCE.getClass();
                if (com.oplusos.systemui.common.feature.FeatureOption.isFoldRemapDisabled()) {
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    convertDrawableToUri.drawableSecond.1 r13 = new convertDrawableToUri.drawableSecond.1(drawable3, context2, (Continuation) null);
                    r3.L$0 = context2;
                    r3.L$1 = SpillingKt.nullOutSpilledVariable(drawable3);
                    r3.L$2 = str3;
                    r3.L$3 = coroutineDispatcher2;
                    r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable4);
                    r3.L$5 = uri;
                    r3.I$0 = i3;
                    r3.label = 3;
                    objWithContext = BuildersKt.withContext(main2, r13, r3);
                    if (objWithContext != coroutine_suspended) {
                        objWithContext2 = objWithContext;
                        uri2 = uri;
                        context3 = context2;
                        coroutineDispatcher3 = coroutineDispatcher2;
                        Drawable drawable7 = (Drawable) objWithContext2;
                        Context context4 = context3;
                        convertDrawableToUri.2 r11 = new convertDrawableToUri.2(context4, drawable7, str3, i3, (Continuation) null);
                        r3.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        r3.L$1 = SpillingKt.nullOutSpilledVariable(drawable3);
                        r3.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                        r3.L$3 = SpillingKt.nullOutSpilledVariable(coroutineDispatcher3);
                        r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable4);
                        r3.L$5 = uri2;
                        r3.L$6 = SpillingKt.nullOutSpilledVariable(drawable7);
                        r3.I$0 = i3;
                        r3.label = 4;
                        objWithContext2 = BuildersKt.withContext(coroutineDispatcher3, r11, r3);
                        if (objWithContext2 != coroutine_suspended) {
                            uri3 = uri2;
                        }
                    }
                    return coroutine_suspended;
                }
                return new Pair(uri, uri4);
            }
            if (i5 == 3) {
                i3 = r3.I$0;
                uri2 = (Uri) r3.L$5;
                drawable4 = (Drawable) r3.L$4;
                coroutineDispatcher3 = (CoroutineDispatcher) r3.L$3;
                str3 = (String) r3.L$2;
                drawable3 = (Drawable) r3.L$1;
                context3 = (Context) r3.L$0;
                ResultKt.throwOnFailure(objWithContext2);
                Drawable drawable8 = (Drawable) objWithContext2;
                Context context5 = context3;
                convertDrawableToUri.2 r12 = new convertDrawableToUri.2(context5, drawable8, str3, i3, (Continuation) null);
                r3.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                r3.L$1 = SpillingKt.nullOutSpilledVariable(drawable3);
                r3.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                r3.L$3 = SpillingKt.nullOutSpilledVariable(coroutineDispatcher3);
                r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable4);
                r3.L$5 = uri2;
                r3.L$6 = SpillingKt.nullOutSpilledVariable(drawable8);
                r3.I$0 = i3;
                r3.label = 4;
                objWithContext2 = BuildersKt.withContext(coroutineDispatcher3, r12, r3);
                if (objWithContext2 != coroutine_suspended) {
                    uri3 = uri2;
                }
                return coroutine_suspended;
            }
            if (i5 != 4) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            uri3 = (Uri) r3.L$5;
            ResultKt.throwOnFailure(objWithContext2);
        }
        uri4 = (Uri) objWithContext2;
        uri = uri3;
        return new Pair(uri, uri4);
        Drawable drawable9 = (Drawable) objWithContext2;
        convertDrawableToUri.uriDefault.1 r0 = new convertDrawableToUri.uriDefault.1(context2, drawable9, str2, i2, (Continuation) null);
        r3.L$0 = context2;
        r3.L$1 = drawable2;
        r3.L$2 = str2;
        r3.L$3 = coroutineDispatcher2;
        r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable9);
        r3.I$0 = i2;
        r3.label = 2;
        Object objWithContext3 = BuildersKt.withContext(coroutineDispatcher2, r0, r3);
        if (objWithContext3 != coroutine_suspended) {
            drawable3 = drawable2;
            str3 = str2;
            drawable4 = drawable9;
            objWithContext2 = objWithContext3;
            i3 = i2;
            uri = (Uri) objWithContext2;
            com.oplusos.systemui.common.feature.FeatureOption.INSTANCE.getClass();
            if (com.oplusos.systemui.common.feature.FeatureOption.isFoldRemapDisabled()) {
                MainCoroutineDispatcher main3 = Dispatchers.getMain();
                convertDrawableToUri.drawableSecond.1 r14 = new convertDrawableToUri.drawableSecond.1(drawable3, context2, (Continuation) null);
                r3.L$0 = context2;
                r3.L$1 = SpillingKt.nullOutSpilledVariable(drawable3);
                r3.L$2 = str3;
                r3.L$3 = coroutineDispatcher2;
                r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable4);
                r3.L$5 = uri;
                r3.I$0 = i3;
                r3.label = 3;
                objWithContext = BuildersKt.withContext(main3, r14, r3);
                if (objWithContext != coroutine_suspended) {
                    objWithContext2 = objWithContext;
                    uri2 = uri;
                    context3 = context2;
                    coroutineDispatcher3 = coroutineDispatcher2;
                    Drawable drawable10 = (Drawable) objWithContext2;
                    Context context6 = context3;
                    convertDrawableToUri.2 r15 = new convertDrawableToUri.2(context6, drawable10, str3, i3, (Continuation) null);
                    r3.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                    r3.L$1 = SpillingKt.nullOutSpilledVariable(drawable3);
                    r3.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                    r3.L$3 = SpillingKt.nullOutSpilledVariable(coroutineDispatcher3);
                    r3.L$4 = SpillingKt.nullOutSpilledVariable(drawable4);
                    r3.L$5 = uri2;
                    r3.L$6 = SpillingKt.nullOutSpilledVariable(drawable10);
                    r3.I$0 = i3;
                    r3.label = 4;
                    objWithContext2 = BuildersKt.withContext(coroutineDispatcher3, r15, r3);
                    if (objWithContext2 != coroutine_suspended) {
                        uri3 = uri2;
                        uri4 = (Uri) objWithContext2;
                        uri = uri3;
                    }
                }
            }
            return new Pair(uri, uri4);
        }
        return coroutine_suspended;
    }

    public static Drawable copyForIndependentTint(Drawable drawable) {
        Drawable drawableNewDrawable;
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null && (drawableNewDrawable = constantState.newDrawable()) != null) {
            drawable = drawableNewDrawable;
        }
        return drawable.mutate();
    }

    public static final Bitmap getBitmapFromIcon(Icon icon) {
        Object obj;
        try {
            Class[] clsArr = new Class[0];
            Method declaredMethod = Class.forName("android.graphics.drawable.Icon").getDeclaredMethod("getBitmap", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(icon, null);
            obj = Result.constructor-impl(objInvoke instanceof Bitmap ? (Bitmap) objInvoke : null);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "getBitmapFromIcon exception " + th2.getClass() + " " + th2.getMessage());
        }
        return (Bitmap) (obj instanceof Result.Failure ? null : obj);
    }

    public static final Icon loadAppIconToIcon(String str, Integer num) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt.runBlocking$default(new AnonymousClass1(str, num, objectRef, null));
        return (Icon) objectRef.element;
    }

    public static final Uri loadAppIconToUri(Context context, SeedlingMediaData seedlingMediaData) {
        String strM = Bundler$.ExternalSyntheticOutline0.m(seedlingMediaData.getPackageName(), "_app_icon.png");
        String strConcat = "FileUtil.loadAppIconToUri ".concat(strM);
        FileUtil$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new FileUtil$.ExternalSyntheticLambda2(1);
        externalSyntheticLambda2.f$2 = seedlingMediaData;
        externalSyntheticLambda2.f$1 = context;
        externalSyntheticLambda2.f$0 = strM;
        VarHandle.storeStoreFence();
        return (Uri) runWithTracing(strConcat, externalSyntheticLambda2);
    }

    public static final Object runWithTracing(String str, Function0 function0) {
        String str2 = Trace.isTagEnabled(4096L) ? str : null;
        boolean z = str2 != null;
        if (z) {
            TraceUtilsKt.beginSlice(4096L, str2);
        }
        try {
            if (LOG_STACK) {
                Log.d(TAG, str, new Throwable(TAG));
            }
            return function0.invoke();
        } finally {
            if (z) {
                Trace.traceEnd(4096L);
            }
        }
    }

    public static final void saveBitmapToFile(Bitmap bitmap, File file) {
        FileUtil$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FileUtil$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = file;
        externalSyntheticLambda0.f$1 = bitmap;
        VarHandle.storeStoreFence();
        runWithTracing("FileUtil.saveBitmapToFile", externalSyntheticLambda0);
    }
}
