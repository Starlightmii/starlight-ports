# PhonePe login teardown on ColorOS 17 sweetin — diagnosis

- **Date:** 2026-11-15
- **Device:** Redmi Note 10 Pro Max (`sweetin`), ColorOS 17 / Android 17 (API 37),
  `Redmi/sweetin/sweetin:17/CP2A.260605.016:Starlight-test:user/release-keys`
- **App:** PhonePe `26.10.23.0` (`26102302`), UID 10369, targetSdk 36
- **Mode:** read-only analysis. No flashing, no wiping, no PhonePe/Navi/GMS data
  cleared, no APK patched, no app data modified.

## Question

On the ColorOS 17 port, launching PhonePe opens the login screen and then the
app tears the whole task down and the process exits. Is this a Play Store
licensing / Play Integrity / root-detection problem, and can it be fixed
app-side?

## Short answer

It is **not** a Play Store certification, Play Integrity, or root-hiding
problem, and it does not need to be. The PhonePe Login SDK itself completes
successfully on this device — Guardian evaluation passes and the flow reaches
the mobile-number screen. The failure is that PhonePe's own login navigation
code removes the task while the SDK flow is still running.

So: yes, this is fixable without Play Store certification and without hiding
root.

## Verified facts

### The SDK path succeeds

Latest captured run, `Login_SDK_pp_event_cache` (device clock):

| Time | Event |
|---|---|
| 10:08:39.356 | `FETCH_SSO_INSTALLED_EXECUTORS_LIST` |
| 10:08:39.411 | `LOGIN_OPTIONS_RESPONSE_NO_ACCOUNT_FOUND` |
| 10:08:40.187 | `AUTH_LOGOUT` (type `ROLLBACK`) |
| 10:08:40.250 | `ORG_LOGOUT` (type `ROLLBACK`) |
| 10:08:42.035 | `LOGIN_AUTH_FLOW_INIT` |
| 10:08:43.051 | `LOGIN_OPTIONS_RESPONSE_SUCCESS` |
| 10:08:43.454 | `GUARDIAN_EVAL_COMPLETED` — `success: true` |
| 10:08:44.423 | `MOBILE_NUMBER_SCREEN_OPENED` |

`GUARDIAN_EVAL_COMPLETED success:true` and `LOGIN_OPTIONS_RESPONSE_SUCCESS`
are the decisive entries: the Login SDK got a valid answer from the server and
the risk evaluation passed. `MOBILE_NUMBER_SCREEN_OPENED` fires *after* the
task was already destroyed, i.e. the SDK was still progressing in the
background while the UI was gone.

There is **no** `LOGIN_ERROR`, no `LOGIN_OPTIONS_RESPONSE_ERROR`, no
`AUTH_LOGIN_SUCCESS`, and no `LOGIN_OPTIONS_RESPONSE_ERROR` in the cache for
the failing run.

### The task is destroyed mid-flow

From the system event log (`wm_*` events):

| Time | Event |
|---|---|
| 10:08:36.910 | PhonePe process starts (pid 18550) |
| 10:08:38.661 | `MainActivity` created (task 2045) |
| 10:08:38.661 | `Navigator_LoginNavigationActivity` created |
| 10:08:41.399 | `LoginOrchestratorActivity` created |
| 10:08:42.123 | `LoginActivity` created |
| 10:08:42.515 | `wm_finish_activity` — `LoginActivity`, reason **`finish-and-remove-task`** |
| 10:08:42.587 | `wm_on_create_called` — `LoginActivity` (`performCreate`, 88 ms) |
| 10:08:42.646 | `wm_on_resume_called` — `LoginActivity` |
| 10:08:42.995 | `LoginActivity` first frame drawn |
| 10:08:43.031 | `LoginActivity` → `completeFinishing` |
| 10:08:43.859 | `LoginActivity`, `LoginOrchestratorActivity`, `Navigator_LoginNavigationActivity` all destroyed |
| 10:08:45.365 | `am_proc_died` — `com.phonepe.app` |

Two facts matter here:

1. The finish request lands **before** `wm_on_create_called` for
   `LoginActivity`, i.e. the activity is told to finish while its own
   `onCreate` is still running.
2. The reason is `finish-and-remove-task`, not `finish`. That is the task-level
   removal path, not a plain `Activity.finish()`.

### The culprit is in the app's own code

`com.phonepe.app.login.ui.LoginNavigationActivity` (the base class of
`Navigator_LoginNavigationActivity`) observes the login-ViewModel state and
tears down the task on any state that is not the success type:

```java
// LoginNavigationActivity.java
cVar.i.g(this, new icq(new Function1() {   // com.phonepe.app.login.ui.b
    public final Object invoke(Object obj) {
        ktu ktuVar = (ktu) obj;
        if (ktuVar != null) {
            boolean z = ktuVar instanceof utu;      // utu = success
            if (z) {
                ... success path: passkey flags, post-login init ...
            } else {
                aqtVar.finishAffinity();             // <-- kills the whole task
            }
        }
        return Unit.a;
    }
}, 10));
```

`finishAffinity()` finishes every activity in the task, which is why
`MainActivity`, `Navigator_LoginNavigationActivity`,
`LoginOrchestratorActivity` and `LoginActivity` are all torn down together and
the reason is `finish-and-remove-task`.

The state that arrives is written by the login-ViewModel collector
(`com.phonepe.app.login.viewmodel.b`), which maps the SDK's `ktu` state onto
the ViewModel's `dnd0` live values:

- `utu` → success → `cVar.h.k(ktuVar2)` (happy path).
- `csu` (a `LoginError`: `CANCELLED`, `INIT_FAILED`, `MAX_ATTEMPTS_EXHAUSTED`,
  `UNKNOWN`) → `dnd0Var.k(csuVar)` → the observer above sees a non-`utu`
  value → `finishAffinity()`.

The `LoginError` values are produced by
`com.phonepe.loginprovider.loginorchestrator.c`:

- `Y3(new csu(LoginError.CANCELLED))` — `LoginOrchestratorActivity` back-press
  and `c.i4()` when the auth result code is `"CANCELLED"`.
- `Y3(new csu(LoginError.MAX_ATTEMPTS_EXHAUSTED))` — `c.a4()` when
  `login_attempts` reaches the configured maximum.
- `Y3(new csu(LoginError.UNKNOWN))` — `LoginOrchestratorActivity` fallback.

`csu` also reaches the collector through the SDK's own state flow, so a
transient SDK error (for example `NO_ACCOUNT_FOUND` surfacing as a failure,
or the SDK reporting `CANCELLED` while rolling back a previous attempt) is
enough to trigger the affinity finish.

### `LoginActivity` itself is not the trigger

`LoginActivity.onCreate` has three branches:

```java
if (bundle == null || u) {
    if (bundle == null) { v1().logEvent(LZecW); return; }   // fresh start
    return;
}
// saved state present -> log and finish
cVarV1.logEvent(LZecu, ...);
s1();                                                        // plain finish()
```

`s1()` calls plain `finish()`, which would log `finish` — not
`finish-and-remove-task`. The observed reason therefore does **not** come from
the `bundle != null` recreation branch of `LoginActivity`; it comes from the
affinity finish described above. No `wm_on_restore_instance_state_called` was
logged for `LoginActivity` either.

### Root / Play Integrity state is not blocking

From the app's own `ROOT_BEER_DATA` event (latest run):

- `rooted: "false"`, `emuChk: "false"`, `hasDangerousPackages: false`,
  `emulated: "false"`
- `safetyNetScore: 0.5`
- `rck.ksu.lo: ["ovl_sys"]` (low-confidence KernelSU marker only)
- `isDeviceScreenLocked: true` — still unexplained, but Guardian passes with
  it in place
- `iccid` / `imei` / `imsi`: `PERMISSION_DENIED` (the app's own permission
  state, not a block)

No `hasEmuFiles`, no `hasGenyFiles`, no `hasQEmuFiles`, no `hasQEmuDrivers`,
no dangerous packages. The app's own root scan reports a clean device, and
the Login SDK still succeeds. Nothing here blocks login.

## Why the process exits

`ApplicationExitInfo` history for `com.phonepe.app`:

| Time | Reason |
|---|---|
| 10:01:07.997 | `EXIT_SELF`, subreason 26 `UNDELIVERED_BROADCAST`, "Can't deliver broadcast" |
| 10:08:45.382 | `EXIT_SELF`, subreason 0 `UNKNOWN`, status 1, importance 400 |

Both are the app killing itself after it has removed its own task. The
`UNDELIVERED_BROADCAST` variant is secondary: the system tried to deliver
`DeveloperOptionsEnabled` to a receiver in a process that had already died
(`DeadObjectException` → `Killing 12210:com.phonepe.app/u0a369 (adj 0): Can't
deliver broadcast`). Older entries at 09:48–09:49 carry
`AnrInfo(anrId=180, anrType=5, timeoutMillis=110615)` from an earlier
investigation and are not on the current path.

## Ruled out

- Play Store certification / `PLAY_RECOGNIZED` / `LICENSED` — the Login SDK
  reaches success and Guardian passes; these verdicts are irrelevant to the
  login flow.
- Play Integrity DEVICE/STRONG — `MEETS_BASIC_INTEGRITY` is the ceiling on this
  unlocked port (see the Integrity Kit docs) and is not what fails here.
- Root detection / root hiding — `rooted:false`, Guardian success, and no
  dangerous packages.
- `android:always_finish_activities` / "Don't keep activities" — not set.
- `zhu4y5.ierau0()` (called first thing in every PhonePe activity's `onCreate`)
  — decompiled; it only initialises components, it does not finish anything
  and does not detect root.
- `BaseLoginActivity.onCreate` and `eym.onCreate` — no finish logic.
- `LoginActivity`'s saved-state branch — its reason string does not match.
- `LoginActivity` Compose error path (`mr60` → `kqu` case 2 → `t1(...)`) —
  `NO_ACCOUNT_FOUND` maps to `or60(null)`, not to the `mr60` error state, and
  no `LOGIN_OPTIONS_RESPONSE_ERROR` was logged in the failing run.

## The fix (app-side, no root hiding, no Play Store certification)

The task teardown happens **before** the SDK returns success, so the minimal
correct behaviour is: do not finish the task on a transient/rollback SDK state.

Options, in order of preference:

1. **Patch the app's own logic** — in
   `com.phonepe.app.login.ui.LoginNavigationActivity`, only call
   `finishAffinity()` for genuinely terminal errors, and ignore
   `LoginError.CANCELLED` / transient SDK states while the login flow is still
   progressing. Equally valid: in `com.phonepe.app.login.viewmodel.b`, do not
   forward transient `csu` states to `c.i` while `LoginOrchestratorActivity` is
   still active.
2. **Runtime hook** — hook `LoginNavigationActivity.finishAffinity()` (or the
   `csu` branch of the ViewModel collector) so the affinity finish is skipped
   for non-terminal states. The device already has LSPosed
   (`zygisk_lsposed`) available for this.
3. **Reset the attempt counter** — `c.a4()` compares the persisted
   `login_attempts` counter against the configured maximum and raises
   `MAX_ATTEMPTS_EXHAUSTED`, which produces the same `csu` → `finishAffinity()`
   path. Clearing that counter is a legitimate first check, because it is
   sticky across launches.

Whatever route is used, the success criterion is unchanged and testable: the
login task survives, `LoginActivity` stays resumed, and
`MOBILE_NUMBER_SCREEN_OPENED` is followed by a visible mobile-number entry
screen instead of a return to the launcher.

## Honest limits of this report

- The exact `LoginError` instance delivered at `10:08:42.515` was not captured
  at runtime; the code path that produces it is identified, the instance is
  inferred from the `finish-and-remove-task` reason plus the absence of
  `LOGIN_OPTIONS_RESPONSE_ERROR`.
- The device was disconnected from USB at the end of the session, so the
  `login_attempts` counter check and the runtime-hook option were not executed
  on-device.
- `isDeviceScreenLocked: true` on an unlocked screen is unexplained and is not
  ruled out as a contributing factor, but it does not block the SDK flow.
