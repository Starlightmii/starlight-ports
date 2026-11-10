package com.oplus.systemui.qs.media;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.collection.FloatList$;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaItemViewCache implements ConfigurationController.ConfigurationListener {
    public static final OplusQsMediaItemViewCache INSTANCE;
    private static final String TAG = "OplusQsMediaItemViewCache";
    private static WeakReference<Context> cacheContext;
    public static long cacheTag;
    public static volatile boolean isLoading;
    public static boolean isShowMedia;
    private static final LinkedList<View> itemCache;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InnerOnInflateFinishedListener implements AsyncLayoutInflater.OnInflateFinishedListener {
        public long cacheTag;

        public final long getCacheTag() {
            return this.cacheTag;
        }

        public void onInflateFinished(View view, int i, ViewGroup viewGroup) {
            long j = this.cacheTag;
            if (j == OplusQsMediaItemViewCache.cacheTag) {
                view.setTag(2131364332, Long.valueOf(j));
                ImageButton imageButton = (ImageButton) view.findViewById(2131364338);
                if (imageButton != null) {
                    imageButton.setImageDrawable(null);
                }
                AppCompatImageView appCompatImageViewFindViewById = view.findViewById(2131364354);
                if (appCompatImageViewFindViewById != null) {
                    appCompatImageViewFindViewById.setImageDrawable((Drawable) null);
                }
                ImageButton imageButton2 = (ImageButton) view.findViewById(2131364350);
                if (imageButton2 != null) {
                    imageButton2.setImageDrawable(null);
                }
                ImageButton imageButton3 = (ImageButton) view.findViewById(2131364348);
                if (imageButton3 != null) {
                    imageButton3.setImageDrawable(null);
                }
                ImageButton imageButton4 = (ImageButton) view.findViewById(2131364347);
                if (imageButton4 != null) {
                    imageButton4.setImageDrawable(null);
                }
                ImageButton imageButton5 = (ImageButton) view.findViewById(2131364353);
                if (imageButton5 != null) {
                    imageButton5.setImageDrawable(null);
                }
                ImageButton imageButton6 = (ImageButton) view.findViewById(2131364355);
                if (imageButton6 != null) {
                    imageButton6.setImageDrawable(null);
                }
                OplusQsMediaItemViewCache.itemCache.add(view);
                if (OplusQsMediaItemViewCache.itemCache.size() == 1) {
                    OplusQsMediaItemViewCache.isLoading = false;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(OplusQsMediaItemViewCache.cacheTag, "initCache: inflate finish! tag = ", "OplusQsMediaItemViewCache-->", "SystemUi--QuickSettings");
                    }
                }
            }
        }
    }

    static {
        OplusQsMediaItemViewCache oplusQsMediaItemViewCache = new OplusQsMediaItemViewCache();
        INSTANCE = oplusQsMediaItemViewCache;
        itemCache = new LinkedList<>();
        ((ConfigurationController) DependencyEx.sDependency.getDependency(ConfigurationController.class)).addCallback(oplusQsMediaItemViewCache);
    }

    public static void initCacheInternal() {
        WeakReference<Context> weakReference;
        Context context;
        Context contextCreateDarkColorContext;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z = isShowMedia;
            WeakReference<Context> weakReference2 = cacheContext;
            Log.d("SystemUi--QuickSettings", "OplusQsMediaItemViewCache-->".concat("initCacheInternal: isShowMedia = " + z + ", context = " + (weakReference2 != null ? weakReference2.get() : null)));
        }
        if (!isShowMedia || (weakReference = cacheContext) == null || (context = weakReference.get()) == null || (contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false))) == null) {
            return;
        }
        isLoading = true;
        itemCache.clear();
        cacheTag = SystemClock.elapsedRealtime();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m(cacheTag, "initCache: cacheTag = ", "OplusQsMediaItemViewCache-->", "SystemUi--QuickSettings");
        }
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(contextCreateDarkColorContext);
        FrameLayout frameLayout = new FrameLayout(contextCreateDarkColorContext);
        long j = cacheTag;
        InnerOnInflateFinishedListener innerOnInflateFinishedListener = new InnerOnInflateFinishedListener();
        innerOnInflateFinishedListener.cacheTag = j;
        VarHandle.storeStoreFence();
        asyncLayoutInflater.inflate(2131559522, frameLayout, innerOnInflateFinishedListener);
    }

    public final View getItemView() {
        if (isLoading) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQsMediaItemViewCache-->getItemView: isLoading = true");
            }
            return null;
        }
        LinkedList<View> linkedList = itemCache;
        View viewPollLast = linkedList.pollLast();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("OplusQsMediaItemViewCache-->");
            int iHashCode = viewPollLast != null ? viewPollLast.hashCode() : 0;
            int size = linkedList.size();
            Object tag = viewPollLast != null ? viewPollLast.getTag(2131364332) : null;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getItemView: itemView = ", iHashCode, size, ", size = ", ", tag = ");
            sbM.append(tag);
            sb.append(sbM.toString());
            Log.d("SystemUi--QuickSettings", sb.toString());
        }
        if (viewPollLast == null) {
            return null;
        }
        ViewParent parent = viewPollLast.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(viewPollLast);
        }
        return viewPollLast;
    }

    public final void initContext(Context context) {
        cacheContext = new WeakReference<>(context);
        setShowMedia(false);
    }

    public void onConfigChanged(Configuration configuration) {
        initCacheInternal();
    }

    public final void saveItemView(View view) {
        if (isLoading) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQsMediaItemViewCache-->saveItemView: isLoading = true");
                return;
            }
            return;
        }
        Object tag = view.getTag(2131364332);
        long j = cacheTag;
        if ((tag instanceof Long) && j == ((Number) tag).longValue()) {
            LinkedList<View> linkedList = itemCache;
            if (linkedList.size() >= 1 || linkedList.contains(view)) {
                return;
            }
            linkedList.offer(view);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("saveItemView: itemView = ", view.hashCode(), linkedList.size(), ", size = ", "OplusQsMediaItemViewCache-->"));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsMediaItemViewCache-->".concat("saveItemView: cacheTag changed! curTag = " + cacheTag + ", viewTag = " + tag));
        }
    }

    public final void setShowMedia(boolean z) {
        if (isShowMedia == z) {
            return;
        }
        isShowMedia = z;
        if (z) {
            initCacheInternal();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsMediaItemViewCache-->clearCache: ");
        }
        itemCache.clear();
        cacheTag = 0L;
    }
}
