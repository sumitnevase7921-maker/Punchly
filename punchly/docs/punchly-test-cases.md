# Punchly — Core automation test cases

Implemented in `PunchlyCoreScenariosTest` (TC-01 … TC-15).  
**Automation style:** **E2E UI** (Selenium WebDriver against the real browser + dev/staging app).

---

## How to read “Testing types”

| Label | Meaning |
|--------|--------|
| **E2E** | End-to-end: full browser, real URLs, full user journey (not unit/API only). |
| **UI / GUI** | Verifies visible elements, layout controls, not just APIs. |
| **Functional** | Confirms behavior matches requirements (login works, timer starts, etc.). |
| **Smoke** | Fast critical-path check; run on every build or daily. |
| **Regression** | Re-run after changes to catch breaks in existing behavior. |
| **Negative** | Invalid or missing data; expects block, error, or safe failure. |
| **Validation** | App rules (required project/task, messages, disabled actions). |
| **CRUD** | Create / Read / Update / Delete style data actions in the UI. |
| **State / persistence** | UI or session survives refresh/navigation. |
| **Auth / session** | Login, logout, post-login routing. |

---

## Summary table

| ID | Title | TestNG group | Primary testing types |
|----|--------|----------------|------------------------|
| TC-01 | Login email + OTP | smoke | E2E, UI, **Functional**, **Smoke**, **Auth** |
| TC-02 | Time Tracker load | smoke | E2E, UI, **Functional**, **Smoke** |
| TC-03 | Select project | smoke | E2E, UI, **Functional**, **Smoke** |
| TC-04 | Enter task | smoke | E2E, UI, **Functional**, **Smoke** |
| TC-05 | Start timer | smoke | E2E, UI, **Functional**, **Smoke** |
| TC-06 | Stop timer | smoke | E2E, UI, **Functional**, **Smoke** |
| TC-07 | Verify entry | smoke | E2E, UI, **Functional**, **Smoke**, **CRUD** (create + read) |
| TC-08 | Start without project | regression | E2E, UI, **Negative**, **Validation**, **Regression** |
| TC-09 | Start without task | regression | E2E, UI, **Validation**, **Regression** (positive or negative per config) |
| TC-10 | Edit task in row | regression | E2E, UI, **Functional**, **Regression**, **CRUD** (update) |
| TC-11 | Delete entry | regression | E2E, UI, **Functional**, **Regression**, **CRUD** (delete) |
| TC-12 | Duplicate entry | regression | E2E, UI, **Functional**, **Regression** |
| TC-13 | Resume from entry | regression | E2E, UI, **Functional**, **Regression** |
| TC-14 | Timer after refresh | regression | E2E, UI, **Functional**, **Regression**, **State / persistence** |
| TC-15 | Logout | regression | E2E, UI, **Functional**, **Regression**, **Auth / session** |

---

## TC-01: Login with Valid Email & OTP
**Testing types:** E2E · UI · Functional · **Smoke** · Auth (credential flow)  
**Steps:** Open application → Enter email → Continue → Enter OTP `000000`  
**Expected:** User past login; dashboard or authenticated app URL; login form not stuck.

## TC-02: Verify Dashboard / Time Tracker Load
**Testing types:** E2E · UI · Functional · **Smoke**  
**Steps:** Login → Open Time Tracker → Observe page  
**Expected:** Time Tracker visible; task input, Start, project selector visible.

## TC-03: Select Project
**Testing types:** E2E · UI · Functional · **Smoke**  
**Steps:** Open project dropdown → Search → Select  
**Expected:** Project selected and UI still consistent.

## TC-04: Enter Task
**Testing types:** E2E · UI · Functional · **Smoke**  
**Steps:** Type in task input  
**Expected:** Task text accepted in the field.

## TC-05: Start Timer
**Testing types:** E2E · UI · Functional · **Smoke**  
**Steps:** Select project → Enter task → Start  
**Expected:** Timer runs; Stop visible; clock value changes.

## TC-06: Stop Timer
**Testing types:** E2E · UI · Functional · **Smoke**  
**Steps:** Start → Wait → Stop  
**Expected:** Timer stops; new row in list.

## TC-07: Verify Entry Creation
**Testing types:** E2E · UI · Functional · **Smoke** · CRUD (create + read)  
**Steps:** Full Start → Stop flow  
**Expected:** Row visible; task text; duration pattern in row.

## TC-08: Start Without Project (Validation)
**Testing types:** E2E · UI · **Negative** · **Validation** · **Regression**  
**Steps:** No project → Enter task → Start  
**Expected:** Validation / error OR timer does not start (safe behavior).

## TC-09: Start Without Task
**Testing types:** E2E · UI · **Validation** · **Regression** (outcome depends on tenant settings)  
**Steps:** Select project → Empty task → Start  
**Expected:** Either timer allowed or blocked per config.

## TC-10: Edit Task from Entry
**Testing types:** E2E · UI · Functional · **Regression** · CRUD (update)  
**Steps:** Create entry → Edit description in row  
**Expected:** Updated text visible in list.

## TC-11: Delete Entry
**Testing types:** E2E · UI · Functional · **Regression** · CRUD (delete)  
**Steps:** Create entry → Delete  
**Expected:** Entry removed from list.

## TC-12: Duplicate Entry
**Testing types:** E2E · UI · Functional · **Regression**  
**Steps:** Create entry → Duplicate  
**Expected:** Another row with same task data (or equivalent).

## TC-13: Resume Timer from Entry
**Testing types:** E2E · UI · Functional · **Regression**  
**Steps:** Play / resume on existing entry  
**Expected:** Timer runs again; Stop visible.

## TC-14: Timer Persistence (Refresh)
**Testing types:** E2E · UI · Functional · **Regression** · **State / persistence**  
**Steps:** Start timer → Refresh page  
**Expected:** Timer still running; not reset to idle.

## TC-15: Logout (Optional)
**Testing types:** E2E · UI · Functional · **Regression** · Auth / session  
**Steps:** Profile/menu → Logout  
**Expected:** Back to login or login field visible.
