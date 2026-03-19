# CLAUDE.md — SREI App

## Project Context

Android prototype for rural lending field agents in India. Built 2018, Java-only, no backend. The app captures loan application details for tractor/equipment financing through multi-step forms.

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
| Android manifest | `app/src/main/AndroidManifest.xml` |
| Dependencies | `app/build.gradle` |
| Layouts | `app/src/main/res/layout/` |
| Strings/Colors/Styles | `app/src/main/res/values/` |

## Code Conventions

- **Language:** Java (no Kotlin)
- **UI framework:** Android Support Library 27.1.1 (not AndroidX)
- **Architecture:** Activity-based, no fragments, no MVVM/MVP
- **Navigation:** Direct `Intent` launches between Activities
- **Custom fonts:** Applied via `Typeface.createFromAsset()` with `fonts/Avenir-Book-01.ttf`
- **Custom views:** `CustomFontEditText` and `CustomFontTextView` for consistent typography
- **Dropdowns:** `ExpandableListView` with `ExpandableListAdapter` (not Spinner for most selections)
- **Data:** All in-memory via `ArrayList<FakeData>`, no database
- **Session:** `SharedPreferences` for login state and checklist progress

## Known Issues

- `MainActivity.LOGIN` is hardcoded as `"abcdef"` — used as both the SharedPreferences file name and an implicit credential check
- `InstallmentCalculatorActivity` fakes a 4-second calculation with `AsyncTask` + `Thread.sleep(4000)` — no real math
- `LeadDetailsActivity.onClick()` has a missing `break` causing fall-through from date field to calendar icon case
- Month names in `LeadDetailsActivity.mDateSetListener` contain typos ("july", "Novermber")
- `ApplicantDetailsActivity` is ~470 lines with all UI setup in `onCreate` — heavy but functional
- No input validation on Aadhaar (12 digits), PAN, mobile (10 digits), or email fields
- `Helpers.Animations` is in a non-standard package path (`java/Helpers/` instead of under the main package)

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
