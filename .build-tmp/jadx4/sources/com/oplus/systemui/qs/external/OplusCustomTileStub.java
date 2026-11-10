package com.oplus.systemui.qs.external;

import android.app.OplusActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.oplusos.systemui.common.util.IconDrawableUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.TileHandlerThread;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomTileStub {
    private static final String TAG = "OplusCustomTileStub";
    private static final String WINDOWS_LINK = "com.microsoft.appmanager.quicksettings.WindowsLinkTileService";
    public static final OplusCustomTileStub INSTANCE = new OplusCustomTileStub();
    private static Map<String, Object> dumpInfos = new LinkedHashMap();

    public static final Supplier<QSTile.Icon> buildTileIconSupplier(Context context, Icon icon, Icon icon2, CustomTile customTile) {
        Object drawalbe;
        try {
            drawalbe = Result.constructor-impl(IconDrawableUtil.getDrawalbe(icon, context, customTile));
        } catch (Throwable th) {
            drawalbe = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(drawalbe) != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "getTileIconSupplier, Invalid icon, forcing into unavailable state");
            drawalbe = IconDrawableUtil.getDrawalbe(icon2, context, customTile);
        }
        Drawable drawable = (Drawable) drawalbe;
        if (drawable == null) {
            return null;
        }
        drawable.mutate();
        drawable.setTint(context.getColor(2131103681));
        OplusCustomTileStub oplusCustomTileStub = INSTANCE;
        int customTileIconSize = oplusCustomTileStub.getCustomTileIconSize(context, customTile.getComponent());
        Drawable drawableCompoundBitmap = oplusCustomTileStub.compoundBitmap(drawable, customTileIconSize, customTileIconSize, oplusCustomTileStub.getCustomTileIconRealSize(context), context, customTile.getTileSpec());
        buildTileIconSupplier.1 r8 = new buildTileIconSupplier.1();
        r8.$drawableF = drawableCompoundBitmap;
        VarHandle.storeStoreFence();
        return r8;
    }

    public static final Drawable tintDrawable(Context context, Drawable drawable, int i, ComponentName componentName) {
        if (drawable == null) {
            return null;
        }
        if (drawable.getAlpha() < 255) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "alpha= " + drawable.getAlpha() + " component= " + componentName, null, 8);
        }
        OplusCustomTileStub oplusCustomTileStub = INSTANCE;
        int customTileIconSize = oplusCustomTileStub.getCustomTileIconSize(context, componentName);
        return oplusCustomTileStub.compoundBitmap(drawable, customTileIconSize, customTileIconSize, oplusCustomTileStub.getCustomTileIconRealSize(context), context, String.valueOf(componentName != null ? componentName.getClassName() : null));
    }

    public final Drawable compoundBitmap(Drawable drawable, int i, int i2, int i3, Context context, String str) {
        Looper looperMyLooper = Looper.myLooper();
        TileHandlerThread.Companion companion = TileHandlerThread.Companion;
        if (!Intrinsics.areEqual(looperMyLooper, companion.getInstance().getLooper())) {
            Path$.ExternalSyntheticBUOutline0.m("compoundBitmap expect to execute on ", companion.getInstance().getLooper(), " but now on ", Looper.myLooper());
            return null;
        }
        Bitmap bitmapDrawableToBitmap = drawableToBitmap(drawable, i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_8888);
        float width = i / bitmapDrawableToBitmap.getWidth();
        float height = i2 / bitmapDrawableToBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(width, height);
        if (bitmapDrawableToBitmap.isRecycled()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "bitmap is recycle or null");
            return null;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapDrawableToBitmap, 0, 0, bitmapDrawableToBitmap.getWidth(), bitmapDrawableToBitmap.getHeight(), matrix, true);
        if (bitmapCreateBitmap2.isRecycled()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "canvas fail， invalid bitmap!");
            return null;
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int i4 = (i3 - i) / 2;
        int i5 = (i3 - i2) / 2;
        Rect rect = new Rect(i4, i5, i + i4, i2 + i5);
        Map<String, Object> map = dumpInfos;
        String strM = Bundler$.ExternalSyntheticOutline0.m(str, " compoundBitmap: ");
        Rect bounds = drawable.getBounds();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("width=", i, i3, ", bottomDrawableSize=", ", top.bounds=");
        sbM.append(bounds);
        sbM.append(", rect=");
        sbM.append(rect);
        map.put(strM, sbM.toString());
        canvas.drawBitmap(bitmapCreateBitmap2, (Rect) null, rect, (Paint) null);
        if (!bitmapCreateBitmap2.equals(bitmapDrawableToBitmap)) {
            bitmapCreateBitmap2.recycle();
        }
        return context == null ? new BitmapDrawable((Resources) null, bitmapCreateBitmap) : new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
    }

    public final Bitmap drawableToBitmap(Drawable drawable, int i) {
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if ((bitmapDrawable != null ? bitmapDrawable.getBitmap() : null) != null) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        for (Map.Entry<String, Object> entry : dumpInfos.entrySet()) {
            String key = entry.getKey();
            printWriter.println(", " + ((Object) key) + ": " + entry.getValue());
        }
    }

    public final int getCustomTileIconRealSize(Context context) {
        return ((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS() ? context.getResources().getDimensionPixelSize(2131172473) : context.getResources().getDimensionPixelSize(2131172173);
    }

    public final int getCustomTileIconSize(Context context, ComponentName componentName) {
        String strEmptyIfNull = TextUtils.emptyIfNull(componentName != null ? componentName.getPackageName() : null);
        if (!StringsKt__StringsKt.contains(TextUtils.emptyIfNull(componentName != null ? componentName.getClassName() : null), WINDOWS_LINK, false)) {
            Set set = AppInfoUtil.BLACK_DND_PACKAGES;
            if ((TextUtils.isEmpty(strEmptyIfNull) || (!strEmptyIfNull.contains("com.oplus") && !strEmptyIfNull.contains("com.oppo") && !strEmptyIfNull.contains("com.heytap"))) && !AppInfoUtil.isSystemApk(context, strEmptyIfNull)) {
                return ((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS() ? context.getResources().getDimensionPixelSize(2131172474) : context.getResources().getDimensionPixelSize(2131172179);
            }
        }
        return getCustomTileIconRealSize(context);
    }

    public final void handleAppFromControlCenter(Context context, String str) {
        Object obj;
        OplusActivityManager oplusActivityManager = new OplusActivityManager();
        try {
            int packageUidAsUser = context.getPackageManager().getPackageUidAsUser(str, Binder.getCallingUserHandle().getIdentifier());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusCustomTileStub-->".concat("handleAppFromControlCenter: uid=" + packageUidAsUser));
            }
            oplusActivityManager.handleAppFromControlCenter(str, packageUidAsUser);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "handleAppFromControlCenter error");
        }
    }

    public static final Drawable tintDrawable(Context context, Drawable drawable, int i) {
        return tintDrawable(context, drawable, i, null);
    }
}
