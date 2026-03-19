# SREI App — Functional Specification

## Overview

SREI App is a mobile tool for field agents (Jaan Pehchaan / JP agents) who process rural loan applications for tractor and equipment financing. Agents use the app to capture customer details, track application status, and calculate loan installments on-site.

**Package:** `jaanpehchan.rural.srei`

## User Persona

**JP Field Agent** — A field investigation officer who visits rural customers to collect loan application details, verify identity documents, assess creditworthiness, and submit applications for approval.

## Application Flow

```
Login (SM Code + Mobile)
  → OTP Verification
    → OTP Success
      → Dashboard (NavMenu)
          ├── Lead Details → Enter customer info → Submit for verification
          ├── JP Queue → List of leads → Checklist → Loan Application Forms
          │     ├── Applicant Details
          │     ├── Co-Applicant Details
          │     ├── Guarantor Details
          │     ├── Transaction Details
          │     └── Asset & Dealer Details
          └── Installment Calculator
```

## Screens & Activities

### 1. Login — `MainActivity`
- **Fields:** SM Code, Mobile Number
- **Behavior:** Validates both fields are non-empty, navigates to OTP screen
- **Session:** Checks SharedPreferences for prior successful login; skips to dashboard if found

### 2. OTP Verification — `Main2Activity`
- **Fields:** OTP input
- **Behavior:** Verifies OTP (mock), saves login state to SharedPreferences on success

### 3. OTP Success — `OtpSuccessfulActivity`
- Confirmation screen after successful OTP verification

### 4. Dashboard — `NavMenuActivity`
- **Navigation options:**
  - Lead Details
  - JP Queue
  - Installment Calculator
- **Behavior:** `onBackPressed` calls `finishAffinity()` (exits app)

### 5. Lead Details — `LeadDetailsActivity`
- **Fields:** Customer Name, Aadhaar Number, Mobile Number, Date of Birth (date picker)
- **Action:** "Send for Verification" button (closes activity)

### 6. JP Queue — `JpQueue`
- **Display:** RecyclerView with loan applications
- **Data model:** `FakeData(id, status, remark)` — hardcoded entries
- **Statuses:** ACCEPTED, REJECTED
- **Action:** Tapping a row opens Checklist

### 7. Checklist — `Checklist`
- Verification checklist for field investigation
- Uses SharedPreferences (`CHECK_LIST_PREFERENCE`) to track completion
- Gates access to detailed application forms

### 8. Applicant Details — `ApplicantDetailsActivity`
The most complex form. Captures:

**Personal Information:**
- Name, Age, Gender (Male/Female radio), Father's Name
- Address, PIN Code, State, City, Village
- Nearest Police Station, Nearest Landmark
- Mobile Number, Email
- Aadhaar Number, PAN, Driving License

**Family Details:**
- Number of Family Members, Dependents, Earning Members (number picker dialogs)
- Marital Status

**Dropdown Selections (ExpandableListView):**
- Customer Type (Agricultural / Commercial)
- Traceability
- Applicant Background
- Residential Status
- House Type (Semipakka / Pakka / Kaccha)
- Relationship with House Owner (Self / Father / Mother / Blood Relative / Other)
- Bank Details (ICICI / SBI / UBI)
- Margin Money Details (Cash / Cheque / Exchange of Tractor)
- First Reference Remarks (Good / Bad)
- Second Reference Remarks (Good / Bad)

**Financial Details:**
- Total Agriculture Land, Family Land
- Crops Grown
- Income from Agriculture, Other Sources, Tractor
- KMC Limit, KMC Utilization
- Installment Amount, Vehicle Owned
- Proposed Loan Amount, Reference Check

**Reference Checks:**
- First Reference: Name, Phone, Stay Period, Remarks
- Second Reference: Name, Phone, Stay Period, Remarks

### 9. Co-Applicant Details — `CoApplicantActivityDetails`
- Similar structure to Applicant Details for a co-applicant

### 10. Guarantor Details — `GuarantorDetailsActivity`
- Guarantor personal and financial information

### 11. Transaction Details — `TransactionDetails`
- Loan transaction specifics

### 12. Asset & Dealer Details — `AssetAndDealerDetail`
- Asset being financed (tractor/equipment) and dealer information

### 13. Installment Calculator — `InstallmentCalculatorActivity`
- **Inputs:** Asset Cost, Financed Amount, IRR, Tenure, Payment Frequency, Moratorium
- **Output:** Calculated installment amount
- **Current state:** Shows a 4-second fake loading animation, displays a static result

### 14. Feedback Form — `FeedbackFormActivity`
- Agent feedback collection

## Data Models

### `FakeData`
```java
int id;
String status;   // "ACCEPTED" or "REJECTED"
String remark;    // "Completed"
```

### `Proof`
```java
String s;         // Label text (e.g., "Agricultural", "Cash")
Drawable drawable; // Optional icon
```

## Data Storage

| Storage | Usage |
|---------|-------|
| SharedPreferences (`"abcdef"`) | Login session persistence |
| SharedPreferences (`CHECK_LIST_PREFERENCE`) | Checklist completion state |
| In-memory ArrayList | JP Queue entries (hardcoded) |

No SQLite, Room, or remote database.

## Custom UI Components

- **CustomFontEditText** — EditText with Avenir-Book font
- **CustomFontTextView** — TextView with Avenir-Book font
- **ExpandableListAdapter** — Dropdown selectors for categorized options
- **LeadListAdapter** — RecyclerView adapter for JP Queue
- **SpinnerAdapterWithInitialText** — Spinner with placeholder text
- **NumberPickerDialog** — Dialog for selecting numeric values

## Known Gaps

1. **No actual calculations** — Installment calculator is purely cosmetic
2. **No data persistence** — Form data is lost when activities close
3. **No input validation** — Most fields accept any input without validation
4. **No backend integration** — No API calls, no data sync
5. **No offline storage** — No local database for form drafts
6. **No image/document capture** — No camera or file upload for KYC documents
7. **No geolocation** — No GPS tracking of field visits
8. **Missing switch-case break** — `LeadDetailsActivity.onClick()` line 84 falls through from `edittext_date_of_birth` to `calender_icon`
9. **Month name typos** — "july" (lowercase), "Novermber" (misspelled) in date formatter
10. **Deprecated AsyncTask** — Used in InstallmentCalculatorActivity
