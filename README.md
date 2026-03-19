# SREI App — Rural Lending Field Agent Tool

An Android application for field agents (Jaan Pehchaan / JP agents) to process rural loan applications for tractor and equipment financing in the Indian market.

## Status

**Prototype / Demo** — Built in 2018, modernized in 2026. No backend API or persistent database. Serves as a UI demonstration of the loan origination workflow.

## Tech Stack

| Component | Version |
|-----------|---------|
| Language | Java |
| Android SDK | Compile 34 (Android 14), Min 21 (Android 5.0) |
| Build System | Gradle 8.2, AGP 8.2.2 |
| UI Libraries | AndroidX AppCompat 1.6.1, Material 1.11.0, ConstraintLayout 2.1.4 |
| Dialogs | SweetAlert 1.5.1 |
| Testing | JUnit 4.13.2, Espresso 3.5.1, AndroidX Test Runner 1.5.2 |

## Project Structure

```
srei-app/
├── app/
│   └── src/main/
│       ├── java/jaanpehchan/rural/srei/   # 25 Java source files
│       ├── res/
│       │   ├── layout/                     # 18 XML layouts
│       │   ├── drawable/                   # Icons, shapes, animations
│       │   ├── values/                     # Strings, colors, styles, arrays
│       │   └── anim/, animator/, mipmap/
│       └── assets/fonts/                   # Avenir-Book-01.ttf
├── build.gradle                            # Root build config
├── app/build.gradle                        # App module dependencies
└── gradle/                                 # Gradle wrapper
```

## Core Features

1. **Login** — SM Code + mobile number entry, OTP verification
2. **Dashboard** — Navigation hub for Lead Details, JP Queue, Installment Calculator
3. **Lead Capture** — Customer name, Aadhaar, mobile, date of birth with input validation
4. **JP Queue** — List of loan applications with ACCEPTED/REJECTED status
5. **Loan Application Forms** — Applicant, Co-Applicant, Guarantor, Transaction, Asset & Dealer details
6. **Installment Calculator** — Asset cost, financed amount, IRR, tenure, moratorium inputs
7. **Checklist** — Verification checklist for field investigation
8. **Feedback Form** — Agent feedback collection

## Building

```bash
# Debug build
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

Requires Android SDK with build tools for API 34.

## Known Limitations

- **No backend** — All data is mock/hardcoded (FakeData class)
- **No database** — Only SharedPreferences for login session persistence
- **Hardcoded credentials** — SM Code validation uses hardcoded string
- **No ProGuard/R8** — Release builds have minification disabled
- **Installment calculator** — Shows a fake 4-second loading delay, no actual calculation logic
