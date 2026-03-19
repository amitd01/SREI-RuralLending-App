# SREI App — Rural Lending Field Agent Tool

An Android application for field agents (Jaan Pehchaan / JP agents) to process rural loan applications for tractor and equipment financing in the Indian market.

## Status

**Prototype / Demo** — Built in 2018 with mock data. No backend API or persistent database. Serves as a UI demonstration of the loan origination workflow.

## Tech Stack

| Component | Version |
|-----------|---------|
| Language | Java |
| Android SDK | Compile 27 (Android 8.1), Min 16 (Android 4.1) |
| Build System | Gradle 3.1.2 |
| UI Libraries | Android Support Library 27.1.1, ConstraintLayout 1.1.0 |
| Dialogs | SweetAlert 1.5.1 |
| Testing | JUnit 4.12, Espresso 3.0.2 |

## Project Structure

```
srei-app/
├── app/
│   └── src/main/
│       ├── java/jaanpehchan/rural/srei/   # 24 Java source files
│       ├── java/Helpers/                   # Animation utilities
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
3. **Lead Capture** — Customer name, Aadhaar, mobile, date of birth
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

Requires Android SDK with build tools for API 27.

## Known Limitations

- **No backend** — All data is mock/hardcoded (FakeData class)
- **No database** — Only SharedPreferences for login session persistence
- **Hardcoded credentials** — SM Code validation uses hardcoded string
- **Deprecated dependencies** — Uses Android Support Library (should migrate to AndroidX)
- **No ProGuard/R8** — Release builds have minification disabled
- **Installment calculator** — Shows a fake 4-second loading delay, no actual calculation logic
