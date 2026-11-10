package com.oplusos.systemui.common.typeface;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$;
import android.util.LruCache;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.compose.ui.text.font.FontWeight;
import androidx.sqlite.SQLite__SQLiteKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.grid.ui.compose.SpannedGridsKt$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemTypefaceHelper implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String ONE_SANS_KEY = "_one_sans";
    private static final String OTHER_TYPEFACE_KEY = "other";
    private static final String ROBOTO_KEY = "_roboto";
    private static final String TAG = "SystemTypefaceHelper";
    private static final String ZHOREN_TYPEFACE_KEY = "ZHOrEN";
    private static volatile SystemTypefaceHelper sInstance;
    private final ConcurrentHashMap<SystemUIFontCallback, Function1> callbackMap;
    public volatile int currentFlipFont;
    private final Function1 defaultFontWeightMapper;
    private final Context mContext;
    private final Lazy sysGoogleSansFlexTypeface$delegate;
    private final NativeTypefaceCache nativeTypefaceCache = new NativeTypefaceCache();
    private final ComposeFontCache composeFontCache = new ComposeFontCache();
    private final Map<String, String> localeMap = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("zh", ZHOREN_TYPEFACE_KEY), TuplesKt.to("en", ZHOREN_TYPEFACE_KEY)});
    public volatile int currentFontStatus = -1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SystemTypefaceHelper getInstance(Context context) {
            SystemTypefaceHelper systemTypefaceHelper;
            SystemTypefaceHelper systemTypefaceHelper2 = SystemTypefaceHelper.sInstance;
            if (systemTypefaceHelper2 != null) {
                return systemTypefaceHelper2;
            }
            synchronized (SystemTypefaceHelper.class) {
                systemTypefaceHelper = SystemTypefaceHelper.sInstance;
                if (systemTypefaceHelper == null) {
                    systemTypefaceHelper = new SystemTypefaceHelper(context);
                    SystemTypefaceHelper.sInstance = systemTypefaceHelper;
                }
            }
            return systemTypefaceHelper;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ComposeFontCache {
        public static final Companion Companion = new Companion();
        private final LruCache<String, ComposeFontInfo> cache = new LruCache<>(20);

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public final ComposeFontInfo getOrPut(Typeface typeface, FontParams fontParams, Function0 function0) {
            StringBuilder sb = new StringBuilder();
            sb.append(typeface.hashCode() + "_" + fontParams.getFlipFont() + "_" + fontParams.getStatus() + "_" + fontParams.getWeight());
            if (fontParams.getWidth() > 0) {
                SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(fontParams.getWidth(), "_", sb);
            }
            if (fontParams.getRond() > 0) {
                SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(fontParams.getRond(), "_", sb);
            }
            String string = sb.toString();
            ComposeFontInfo composeFontInfo = this.cache.get(string);
            if (composeFontInfo != null) {
                return composeFontInfo;
            }
            ComposeFontInfo composeFontInfo2 = (ComposeFontInfo) function0.invoke();
            this.cache.put(string, composeFontInfo2);
            return composeFontInfo2;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Point {
        public final int x;
        public final int y;

        public Point(int i, int i2) {
            this.x = i;
            this.y = i2;
        }

        public final Point copy(int i, int i2) {
            return new Point(i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Point)) {
                return false;
            }
            Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int hashCode() {
            return Integer.hashCode(this.y) + (Integer.hashCode(this.x) * 31);
        }

        public String toString() {
            return MutableVectorKt$.ExternalSyntheticOutline0.m("Point(x=", this.x, this.y, ", y=", ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface SystemUIFontCallback {
        default void setFontTypeface(Typeface typeface, int i) {
        }
    }

    public static Typeface $r8$lambda$F7Uvr30kdCUqoyvBL5iJgzIeecQ(SystemTypefaceHelper systemTypefaceHelper) {
        Object obj;
        try {
            obj = Result.constructor-impl(new Typeface.Builder(systemTypefaceHelper.mContext.getAssets(), "GoogleSansFlexForOPPOVF.ttf").setFontVariationSettings("'wght' 900").build());
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(LogUtil.TAG_COMMON, TAG, "Load googleSansFlexTypeface failed", th2);
        }
        Typeface typeface = Typeface.DEFAULT;
        if (obj instanceof Result.Failure) {
            obj = typeface;
        }
        return (Typeface) obj;
    }

    public SystemTypefaceHelper(Context context) {
        this.mContext = context.getApplicationContext();
        SystemTypefaceHelper$$ExternalSyntheticLambda4 systemTypefaceHelper$$ExternalSyntheticLambda4 = new SystemTypefaceHelper$$ExternalSyntheticLambda4();
        systemTypefaceHelper$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.sysGoogleSansFlexTypeface$delegate = LazyKt__LazyJVMKt.lazy(systemTypefaceHelper$$ExternalSyntheticLambda4);
        this.defaultFontWeightMapper = new SpannedGridsKt$.ExternalSyntheticLambda0();
        this.callbackMap = new ConcurrentHashMap<>();
    }

    private final Typeface getSysGoogleSansFlexTypeface() {
        return (Typeface) this.sysGoogleSansFlexTypeface$delegate.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:15:0x00ca  */
    public static int mapPrecise(int i, int i2, int i3, int i4) {
        Integer numValueOf;
        Pair[] pairArr = {TuplesKt.to(100, Integer.valueOf(i2)), TuplesKt.to(550, Integer.valueOf(i3)), TuplesKt.to(800, Integer.valueOf(i4))};
        int iCoerceIn = RangesKt___RangesKt.coerceIn(i, 100, 800);
        List listSortedWith = ArraysKt___ArraysKt.sortedWith(pairArr, new SystemTypefaceHelper$mapPrecise$$inlined$sortedBy$1());
        List<Pair> list = listSortedWith;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (Pair pair : list) {
            arrayList.add(new Point(((Number) pair.getFirst()).intValue(), ((Number) pair.getSecond()).intValue()));
        }
        int size = arrayList.size() - 1;
        int i5 = 0;
        while (i5 < size) {
            Object obj = arrayList.get(i5);
            i5++;
            Pair pair2 = TuplesKt.to(obj, arrayList.get(i5));
            Point point = (Point) pair2.component1();
            Point point2 = (Point) pair2.component2();
            if (iCoerceIn <= point2.getX()) {
                numValueOf = Integer.valueOf((((point2.getY() - point.getY()) * (iCoerceIn - point.getX())) / (point2.getX() - point.getX())) + point.getY());
                return numValueOf != null ? numValueOf.intValue() : ((Number) ((Pair) CollectionsKt___CollectionsKt.last(listSortedWith)).getSecond()).intValue();
            }
        }
        numValueOf = null;
        if (numValueOf != null) {
        }
    }

    public final void addCallback(SystemUIFontCallback systemUIFontCallback, Function1 function1) {
        if (this.callbackMap.containsKey(systemUIFontCallback)) {
            return;
        }
        this.callbackMap.put(systemUIFontCallback, function1);
        this.currentFontStatus = OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration());
        this.currentFlipFont = OplusBaseConfigurationAddon.getFlipFont(this.mContext.getResources().getConfiguration());
        int iIntValue = ((Number) function1.invoke(Integer.valueOf(getCurrentFontWeight()))).intValue();
        systemUIFontCallback.setFontTypeface(getDefaultTypeface(iIntValue), iIntValue);
    }

    public final Typeface createVariationSettingsTypeface(Typeface typeface, FontParams fontParams) {
        Object obj;
        try {
            Paint paint = new Paint();
            paint.setTypeface(typeface);
            StringBuilder sb = new StringBuilder();
            sb.append(CommonSettingsValueProxy.DYNAMIC_FONT_WEIGHT_CONFIG_STRING + fontParams.getWeight());
            if (fontParams.getWidth() > 0) {
                sb.append(", 'wdth' " + fontParams.getWidth());
            }
            if (fontParams.getRond() > 0) {
                sb.append(", 'rond' " + fontParams.getRond());
            }
            paint.setFontVariationSettings(sb.toString());
            obj = Result.constructor-impl(paint.getTypeface());
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(LogUtil.TAG_COMMON, TAG, "Create VariationSettingsTypeface failed", th2);
        }
        Object obj2 = typeface;
        if (!(obj instanceof Result.Failure)) {
            obj2 = obj;
        }
        return (Typeface) obj2;
    }

    public final Typeface getClockTypeface() {
        return Typeface.DEFAULT;
    }

    public final int getCurrentFontWeight() {
        return RangesKt___RangesKt.coerceIn(OplusBaseConfigurationAddon.getFontVariationSettings(this.mContext.getResources().getConfiguration()) & 4095, 100, 800);
    }

    public final Typeface getDefaultTypeface(int i) {
        if (OplusFontUtils.isFlipFontUsed) {
            return Typeface.SANS_SERIF;
        }
        FontParams fontParams = new FontParams(this.currentFontStatus, this.currentFlipFont, i, 0, 24, 0);
        NativeTypefaceCache nativeTypefaceCache = this.nativeTypefaceCache;
        Typeface typeface = Typeface.SANS_SERIF;
        SystemTypefaceHelper$$ExternalSyntheticLambda0 systemTypefaceHelper$$ExternalSyntheticLambda0 = new SystemTypefaceHelper$$ExternalSyntheticLambda0(0);
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$0 = this;
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$1 = fontParams;
        VarHandle.storeStoreFence();
        return nativeTypefaceCache.getOrPut(typeface, fontParams, systemTypefaceHelper$$ExternalSyntheticLambda0);
    }

    public final Typeface getGoogleSansFlexTypeface(int i, int i2) {
        return getVariationSettingsTypefaceWithCache(getSysGoogleSansFlexTypeface(), new FontParams(0, 0, i, i2, 3, 0));
    }

    public final ComposeFontInfo getLargeNetworkTypeComposeFontFamily(Typeface typeface, int i) {
        Typeface networkIconTextTypeface = getNetworkIconTextTypeface(typeface, i, 25);
        FontParams fontParams = new FontParams(this.currentFontStatus, this.currentFlipFont, i, 25, 10);
        ComposeFontCache composeFontCache = this.composeFontCache;
        SystemTypefaceHelper$$ExternalSyntheticLambda0 systemTypefaceHelper$$ExternalSyntheticLambda0 = new SystemTypefaceHelper$$ExternalSyntheticLambda0(1);
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$0 = networkIconTextTypeface;
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$1 = fontParams;
        VarHandle.storeStoreFence();
        return composeFontCache.getOrPut(networkIconTextTypeface, fontParams, systemTypefaceHelper$$ExternalSyntheticLambda0);
    }

    public final Typeface getNetworkIconTextTypeface(Typeface typeface, int i, int i2) {
        int fontVariationStatus;
        return (OplusFontUtils.isFlipFontUsed || !((fontVariationStatus = OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration())) == 1 || fontVariationStatus == 2)) ? typeface : getGoogleSansFlexTypeface(i, i2);
    }

    public final ComposeFontInfo getNetworkTypeComposeFontFamily(Typeface typeface, int i) {
        FontParams fontParams = new FontParams(this.currentFontStatus, this.currentFlipFont, i, 70, 10);
        ComposeFontCache composeFontCache = this.composeFontCache;
        SystemTypefaceHelper$$ExternalSyntheticLambda0 systemTypefaceHelper$$ExternalSyntheticLambda0 = new SystemTypefaceHelper$$ExternalSyntheticLambda0(1);
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$0 = typeface;
        systemTypefaceHelper$$ExternalSyntheticLambda0.f$1 = fontParams;
        VarHandle.storeStoreFence();
        return composeFontCache.getOrPut(typeface, fontParams, systemTypefaceHelper$$ExternalSyntheticLambda0);
    }

    public final Typeface getSansTypeface(String str, int i) {
        if (str == null || str.length() == 0) {
            return Typeface.DEFAULT;
        }
        String strConcat = this.localeMap.get(str);
        if (strConcat == null) {
            strConcat = OTHER_TYPEFACE_KEY;
        }
        if (OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration()) == 3) {
            strConcat = strConcat.concat(ONE_SANS_KEY);
        } else if (isRobotoStatusTypeface()) {
            strConcat = strConcat.concat(ROBOTO_KEY);
        }
        String str2 = strConcat + i;
        NativeTypefaceCache nativeTypefaceCache = this.nativeTypefaceCache;
        SystemTypefaceHelper$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new SystemTypefaceHelper$.ExternalSyntheticLambda3();
        externalSyntheticLambda3.f$0 = this;
        externalSyntheticLambda3.f$1 = i;
        VarHandle.storeStoreFence();
        return nativeTypefaceCache.getOrPut(str2, externalSyntheticLambda3);
    }

    public final Typeface getVariationSettingsTypefaceWithCache(Typeface typeface, FontParams fontParams) {
        NativeTypefaceCache nativeTypefaceCache = this.nativeTypefaceCache;
        SystemTypefaceHelper$$ExternalSyntheticLambda1 systemTypefaceHelper$$ExternalSyntheticLambda1 = new SystemTypefaceHelper$$ExternalSyntheticLambda1();
        systemTypefaceHelper$$ExternalSyntheticLambda1.f$0 = this;
        systemTypefaceHelper$$ExternalSyntheticLambda1.f$1 = typeface;
        systemTypefaceHelper$$ExternalSyntheticLambda1.f$2 = fontParams;
        VarHandle.storeStoreFence();
        return nativeTypefaceCache.getOrPut(typeface, fontParams, systemTypefaceHelper$$ExternalSyntheticLambda1);
    }

    public final boolean isRobotoStatusTypeface() {
        return OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration()) == 0;
    }

    public final int mapNetworkTypeStatusBarIconWeight(int i) {
        int fontVariationStatus;
        if (OplusFontUtils.isFlipFontUsed || isRobotoStatusTypeface()) {
            return FontWeight.Companion.getBold().getWeight();
        }
        return (OplusFontUtils.isFlipFontUsed || !((fontVariationStatus = OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration())) == 1 || fontVariationStatus == 2)) ? mapStatusCommonTextFontWeight(i) : mapPrecise(i, 600, 700, ZoomParameterHelper.MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP);
    }

    public final int mapStatusCommonTextFontWeight(int i) {
        return (OplusFontUtils.isFlipFontUsed || isRobotoStatusTypeface()) ? FontWeight.Companion.getBold().getWeight() : mapPrecise(i, 500, 632, 799);
    }

    public final void notifyTypefaceChanged() {
        int currentFontWeight = getCurrentFontWeight();
        this.currentFontStatus = OplusBaseConfigurationAddon.getFontVariationStatus(this.mContext.getResources().getConfiguration());
        this.currentFlipFont = OplusBaseConfigurationAddon.getFlipFont(this.mContext.getResources().getConfiguration());
        for (Map.Entry<SystemUIFontCallback, Function1> entry : this.callbackMap.entrySet()) {
            SystemUIFontCallback key = entry.getKey();
            int iIntValue = ((Number) entry.getValue().invoke(Integer.valueOf(currentFontWeight))).intValue();
            key.setFontTypeface(getDefaultTypeface(iIntValue), iIntValue);
        }
    }

    public void onFontChanged(Configuration configuration) {
        boolean z = OplusFontUtils.isFlipFontUsed;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onFontChanged,isFlipFontUsed:", ",isDefault:", z, !z), null, 8);
        notifyTypefaceChanged();
    }

    public final void onFontWeightChanged() {
        notifyTypefaceChanged();
    }

    public final void removeCallback(SystemUIFontCallback systemUIFontCallback) {
        this.callbackMap.remove(systemUIFontCallback);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NativeTypefaceInfo {
        private final Typeface typeface;
        public final long updateTimestamp;
        public final int weight;

        public NativeTypefaceInfo(Typeface typeface, int i, long j) {
            this.typeface = typeface;
            this.weight = i;
            this.updateTimestamp = j;
        }

        public final Typeface component1() {
            return this.typeface;
        }

        public final NativeTypefaceInfo copy(Typeface typeface, int i, long j) {
            return new NativeTypefaceInfo(typeface, i, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NativeTypefaceInfo)) {
                return false;
            }
            NativeTypefaceInfo nativeTypefaceInfo = (NativeTypefaceInfo) obj;
            return Intrinsics.areEqual(this.typeface, nativeTypefaceInfo.typeface) && this.weight == nativeTypefaceInfo.weight && this.updateTimestamp == nativeTypefaceInfo.updateTimestamp;
        }

        public final Typeface getTypeface() {
            return this.typeface;
        }

        public final long getUpdateTimestamp() {
            return this.updateTimestamp;
        }

        public final int getWeight() {
            return this.weight;
        }

        public final int hashCode() {
            return Long.hashCode(this.updateTimestamp) + MonoSpline$.ExternalSyntheticOutline0.m(this.weight, this.typeface.hashCode() * 31, 31);
        }

        public String toString() {
            Typeface typeface = this.typeface;
            StringBuilder sb = new StringBuilder("NativeTypefaceInfo(typeface=");
            sb.append(typeface);
            sb.append(", weight=");
            sb.append(this.weight);
            sb.append(", updateTimestamp=");
            return MediaSessionCompat$QueueItem$.ExternalSyntheticOutline0.m(this.updateTimestamp, ")", sb);
        }

        public /* synthetic */ NativeTypefaceInfo(Typeface typeface, int i) {
            this(typeface, i, System.nanoTime());
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FontParams {
        public final int flipFont;
        public final int rond;
        public final int status;
        public final int weight;
        public final int width;

        public /* synthetic */ FontParams(int i, int i2, int i3, int i4, int i5, int i6) {
            this((i5 & 1) != 0 ? -1 : i, (i5 & 2) != 0 ? 0 : i2, i3, (i5 & 8) != 0 ? 0 : i4, (i5 & 16) == 0 ? 10 : 0);
        }

        public final FontParams copy(int i, int i2, int i3, int i4, int i5) {
            return new FontParams(i, i2, i3, i4, i5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FontParams)) {
                return false;
            }
            FontParams fontParams = (FontParams) obj;
            return this.status == fontParams.status && this.flipFont == fontParams.flipFont && this.weight == fontParams.weight && this.width == fontParams.width && this.rond == fontParams.rond;
        }

        public final int getFlipFont() {
            return this.flipFont;
        }

        public final int getRond() {
            return this.rond;
        }

        public final int getStatus() {
            return this.status;
        }

        public final int getWeight() {
            return this.weight;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int hashCode() {
            return Integer.hashCode(this.rond) + MonoSpline$.ExternalSyntheticOutline0.m(this.width, MonoSpline$.ExternalSyntheticOutline0.m(this.weight, MonoSpline$.ExternalSyntheticOutline0.m(this.flipFont, Integer.hashCode(this.status) * 31, 31), 31), 31);
        }

        public String toString() {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("FontParams(status=", this.status, this.flipFont, ", flipFont=", ", weight=");
            RectList$.ExternalSyntheticOutline0.m(this.weight, this.width, ", width=", ", rond=", sbM);
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.rond, ")", sbM);
        }

        public FontParams(int i, int i2, int i3, int i4, int i5) {
            this.status = i;
            this.flipFont = i2;
            this.weight = i3;
            this.width = i4;
            this.rond = i5;
        }
    }

    public final Typeface getSansTypeface(String str) {
        return getSansTypeface(str, 700);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NativeTypefaceCache {
        public static final Companion Companion = new Companion();
        private final LruCache<String, Typeface> cache = new LruCache<>(20);

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public final Typeface getOrPut(Typeface typeface, FontParams fontParams, Function0 function0) {
            StringBuilder sb = new StringBuilder();
            sb.append(typeface.hashCode() + "_" + fontParams.getFlipFont() + "_" + fontParams.getStatus() + "_" + fontParams.getWeight());
            if (fontParams.getWidth() > 0) {
                SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(fontParams.getWidth(), "_", sb);
            }
            if (fontParams.getRond() > 0) {
                SQLite__SQLiteKt$.ExternalSyntheticOutline0.m(fontParams.getRond(), "_", sb);
            }
            String string = sb.toString();
            Typeface typeface2 = this.cache.get(string);
            if (typeface2 != null) {
                return typeface2;
            }
            Typeface typeface3 = (Typeface) function0.invoke();
            this.cache.put(string, typeface3);
            return typeface3;
        }

        public final Typeface getOrPut(String str, Function0 function0) {
            Typeface typeface = this.cache.get(str);
            if (typeface != null) {
                return typeface;
            }
            Typeface typeface2 = (Typeface) function0.invoke();
            this.cache.put(str, typeface2);
            return typeface2;
        }
    }
}
