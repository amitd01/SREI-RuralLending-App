# CLAUDE.md — SREI App

## Project Context

Android prototype for rural lending field agents in India. Built 2018, modernized 2026. Java-only, no backend. The app captures loan application details for tractor/equipment financing through multi-step forms.

**Package:** `jaanpehchan.rural.srei`

## Build Commands

```bash
./gradlew assembleDebug        # Build debug APK
./gradlew installDebug         # Install on device/emulator
./gradlew test                 # Run unit tests
./gradlew connectedAndroidTest # Run instrumented tests
```

## Key File Locations

| Purpose | Path |
|---------|------|
| Entry point / Login | `app/src/main/java/jaanpehchan/rural/srei/MainActivity.java` |
| Dashboard / Navigation | `app/src/main/java/jaanpehchan/rural/srei/NavMenuActivity.java` |
| Main form (most complex) | `app/src/main/java/jaanpehchan/rural/srei/ApplicantDetailsActivity.java` |
| Loan queue | `app/src/main/java/jaanpehchan/rural/srei/JpQueue.java` |
| Mock data model | `app/src/main/java/jaanpehchan/rural/srei/FakeData.java` |
| Shared UI utilities | `app/src/main/java/jaanpehchan/rural/srei/ViewUtils.java` |
| Animation utilities | `app/src/main/java/jaanpehchan/rural/srei/Animations.java` |
| Android manifest | `app/src/main/AndroidManifest.xml` |
| Dependencies | `app/build.gradle` |
| Layouts | `app/src/main/res/layout/` |
| Strings/Colors/Styles | `app/src/main/res/values/` |

## Code Conventions

- **Language:** Java (no Kotlin)
- **UI framework:** AndroidX AppCompat 1.6.1 + Material 1.11.0
- **Architecture:** Activity-based, no fragments, no MVVM/MVP
- **Navigation:** Direct `Intent` launches between Activities
- **Custom fonts:** Applied via `Typeface.createFromAsset()` with `fonts/Avenir-Book-01.ttf`
- **Custom views:** `CustomFontEditText` and `CustomFontTextView` for consistent typography
- **Dropdowns:** `ExpandableListView` with `ExpandableListAdapter` (not Spinner for most selections)
- **Data:** All in-memory via `ArrayList<FakeData>`, no database
- **Session:** `SharedPreferences` for login state and checklist progress
- **Utilities:** `ViewUtils.dpToPx()` for density-independent pixel conversion

## Known Issues

- `MainActivity.LOGIN` is hardcoded as `"abcdef"` — used as both the SharedPreferences file name and an implicit credential check
- `InstallmentCalculatorActivity` fakes a 4-second calculation with `Handler.postDelayed()` — no real math
- `ApplicantDetailsActivity` is ~470 lines with all UI setup in `onCreate` — heavy but functional

## Domain Terms

| Term | Meaning |
|------|---------|
| JP | Jaan Pehchaan — field agent identity/verification role |
| SM Code | Sales Manager code — agent identifier |
| Lead | Potential loan customer |
| FI | Field Investigation |
| KMC | Kisan Credit Card (agricultural credit facility) |
| IRR | Internal Rate of Return |
| Moratorium | Grace period before loan repayment begins |
| Pakka/Kaccha | House construction quality (permanent/temporary) |
| Margin Money | Down payment / borrower's contribution |
