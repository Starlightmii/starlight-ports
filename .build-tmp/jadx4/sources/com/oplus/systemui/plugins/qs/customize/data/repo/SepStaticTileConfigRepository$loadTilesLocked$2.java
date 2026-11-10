package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.text.TextUtils;
import com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepStaticTileConfigRepository$loadTilesLocked$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SepStaticTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepStaticTileConfigRepository$loadTilesLocked$2(SepStaticTileConfigRepository sepStaticTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepStaticTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepStaticTileConfigRepository$loadTilesLocked$2(this.this$0, continuation);
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
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 8;
        try {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.this$0.contextInstance.getSysUiContext().getAssets().open("septiles_contain_allsystem_config.json"), Charsets.UTF_8), OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
                    SepStaticTileConfigRepository sepStaticTileConfigRepository = this.this$0;
                    try {
                        JSONArray jSONArray = new JSONArray(TextStreamsKt.readText(bufferedReader));
                        int length = jSONArray.length();
                        int i2 = 0;
                        int i3 = 0;
                        while (i3 < length) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i3);
                            String string = jSONObject.getString("spec");
                            if (TextUtils.isEmpty(string)) {
                                LogUtil.w$default(i, LogUtil.TAG_QS, "SepStaticTileConfigRepository", "loadTilesLocked: " + string + " is invalid " + i3 + "!");
                            } else {
                                sepStaticTileConfigRepository.getClass();
                                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("defaultSize");
                                SpanSize spanSize = jSONArrayOptJSONArray != null ? SpanCache.INSTANCE.spanSize(jSONArrayOptJSONArray.getInt(i2), jSONArrayOptJSONArray.getInt(1)) : TileStaticConfig.Companion.getDEFAULT_SIZE();
                                List listAccess$getSupportSizesFromJson = SepStaticTileConfigRepository.access$getSupportSizesFromJson(sepStaticTileConfigRepository, jSONObject);
                                string.getClass();
                                TileStaticConfig tileStaticConfig = new TileStaticConfig(string, spanSize, listAccess$getSupportSizesFromJson);
                                if (tileStaticConfig.isValid()) {
                                    linkedHashMap.put(string, tileStaticConfig);
                                } else {
                                    LogUtil.w$default(8, LogUtil.TAG_QS, "SepStaticTileConfigRepository", "loadTilesLocked: " + string + " is invalid! " + i3 + " " + spanSize);
                                }
                            }
                            i3++;
                            i = 8;
                            i2 = 0;
                        }
                        CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                        return linkedHashMap;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(bufferedReader, th);
                            throw th2;
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    i = 8;
                    LogUtil.e$default(i, LogUtil.TAG_QS, "SepStaticTileConfigRepository", "loadStockTiles failed! " + e);
                    return linkedHashMap;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (JSONException e3) {
            LogUtil.e$default(8, LogUtil.TAG_QS, "SepStaticTileConfigRepository", "loadStockTiles failed! " + e3);
            return linkedHashMap;
        }
    }
}
