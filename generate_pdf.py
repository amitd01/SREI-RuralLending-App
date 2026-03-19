#!/usr/bin/env python3
"""Generate a PDF document showcasing the SREI Rural Lending App screens."""

import os
from fpdf import FPDF
from PIL import Image

BASE = os.path.dirname(os.path.abspath(__file__))
DRAWABLE = os.path.join(BASE, "app/src/main/res/drawable-mdpi")
DRAWABLE_MAIN = os.path.join(BASE, "app/src/main/res/drawable")
DRAWABLE_XXXHDPI = os.path.join(BASE, "app/src/main/res/drawable-xxxhdpi")
MIPMAP = os.path.join(BASE, "app/src/main/res/mipmap-xxxhdpi")
TMP = os.path.join(BASE, "tmp_pdf_imgs")
os.makedirs(TMP, exist_ok=True)


def to_rgb_png(src_path, name=None):
    """Convert RGBA/P images to RGB PNG for fpdf compatibility."""
    if name is None:
        name = os.path.basename(src_path)
    out = os.path.join(TMP, name.replace(".png", "_rgb.png"))
    img = Image.open(src_path)
    if img.mode in ("RGBA", "P", "LA"):
        bg = Image.new("RGB", img.size, (255, 255, 255))
        if img.mode == "P":
            img = img.convert("RGBA")
        if img.mode in ("RGBA", "LA"):
            bg.paste(img, mask=img.split()[-1])
        else:
            bg.paste(img)
        bg.save(out, "PNG")
    else:
        img.save(out, "PNG")
    return out


def to_rgb_png_dark(src_path, name=None):
    """Convert with dark background (for white-on-transparent assets)."""
    if name is None:
        name = os.path.basename(src_path)
    out = os.path.join(TMP, name.replace(".png", "_dark.png"))
    img = Image.open(src_path)
    if img.mode == "P":
        img = img.convert("RGBA")
    if img.mode in ("RGBA", "LA"):
        bg = Image.new("RGB", img.size, (44, 62, 80))
        bg.paste(img, mask=img.split()[-1])
        bg.save(out, "PNG")
    else:
        img.save(out, "PNG")
    return out


class AppPDF(FPDF):
    def header(self):
        if self.page_no() > 1:
            self.set_font("Helvetica", "I", 8)
            self.set_text_color(120, 120, 120)
            self.cell(0, 6, "SREI Rural Lending App - Screen Reference", align="R")
            self.ln(8)

    def footer(self):
        self.set_y(-15)
        self.set_font("Helvetica", "I", 8)
        self.set_text_color(150, 150, 150)
        self.cell(0, 10, f"Page {self.page_no()}/{{nb}}", align="C")

    def section_title(self, num, title):
        self.set_font("Helvetica", "B", 16)
        self.set_text_color(26, 82, 118)
        self.cell(0, 12, f"{num}. {title}", new_x="LMARGIN", new_y="NEXT")
        self.set_draw_color(26, 82, 118)
        self.line(10, self.get_y(), 200, self.get_y())
        self.ln(4)

    def one_liner(self, text):
        self.set_font("Helvetica", "I", 11)
        self.set_text_color(60, 60, 60)
        self.multi_cell(0, 6, text)
        self.ln(3)

    def detail_label(self, label):
        self.set_font("Helvetica", "B", 10)
        self.set_text_color(80, 80, 80)
        self.cell(0, 6, label, new_x="LMARGIN", new_y="NEXT")

    def detail_text(self, text):
        self.set_font("Helvetica", "", 10)
        self.set_text_color(50, 50, 50)
        self.multi_cell(0, 5, text)
        self.ln(2)

    def icon_row(self, icons, labels, max_icon_w=18, max_icon_h=18):
        """Place a row of icons with labels below them."""
        start_x = self.get_x()
        y = self.get_y()
        spacing = 38
        for i, (icon_path, label) in enumerate(zip(icons, labels)):
            x = start_x + i * spacing
            try:
                img = Image.open(icon_path)
                w, h = img.size
                scale = min(max_icon_w / w, max_icon_h / h, 1)
                iw, ih = w * scale, h * scale
                rgb = to_rgb_png(icon_path, f"icon_{label.replace(' ', '_')}.png")
                self.image(rgb, x + (max_icon_w - iw) / 2, y, iw, ih)
            except Exception:
                pass
            self.set_xy(x - 2, y + max_icon_h + 1)
            self.set_font("Helvetica", "", 7)
            self.set_text_color(100, 100, 100)
            self.cell(max_icon_w + 4, 4, label, align="C")
        self.set_xy(start_x, y + max_icon_h + 7)


def build_pdf():
    pdf = AppPDF()
    pdf.alias_nb_pages()
    pdf.set_auto_page_break(auto=True, margin=20)

    # ── Cover Page ──
    pdf.add_page()
    pdf.ln(30)

    # App icon
    ic = to_rgb_png(os.path.join(MIPMAP, "ic_launcher.png"))
    pdf.image(ic, x=85, w=40)
    pdf.ln(45)

    pdf.set_font("Helvetica", "B", 28)
    pdf.set_text_color(26, 82, 118)
    pdf.cell(0, 14, "SREI Rural Lending App", align="C", new_x="LMARGIN", new_y="NEXT")

    pdf.set_font("Helvetica", "", 14)
    pdf.set_text_color(100, 100, 100)
    pdf.cell(0, 10, "Screen Reference & UI Asset Guide", align="C", new_x="LMARGIN", new_y="NEXT")
    pdf.ln(5)

    # Logo on dark bg
    logo_dark = to_rgb_png_dark(os.path.join(DRAWABLE_MAIN, "srei_logo_trans_white.png"))
    pdf.image(logo_dark, x=55, w=100)
    pdf.ln(60)

    pdf.set_font("Helvetica", "", 10)
    pdf.set_text_color(120, 120, 120)
    pdf.cell(0, 6, "Package: jaanpehchan.rural.srei", align="C", new_x="LMARGIN", new_y="NEXT")
    pdf.cell(0, 6, "Platform: Android (Java) | Architecture: Activity-based", align="C", new_x="LMARGIN", new_y="NEXT")
    pdf.cell(0, 6, "Generated: March 2026", align="C", new_x="LMARGIN", new_y="NEXT")

    # ── Screens ──
    screens = [
        {
            "num": 1,
            "title": "Login Screen",
            "layout": "activity_main.xml",
            "activity": "MainActivity.java",
            "desc": "Entry point where field agents enter their SM Code and mobile number to authenticate into the app.",
            "elements": "SM Code input, Mobile Number input, REGISTER button, SREI logo",
            "icons": ["srei_logo_trans_white", "user_icon", "phone_icon", "right_arrow_circle_icon", "bg"],
            "icon_labels": ["SREI Logo", "User", "Phone", "Arrow", "Background"],
        },
        {
            "num": 2,
            "title": "OTP Verification",
            "layout": "activity2_main.xml",
            "activity": "Main2Activity.java",
            "desc": "Second auth step where agents enter the OTP sent to their mobile for identity verification.",
            "elements": "OTP input field, VERIFY & CONTINUE button, lock icon, SREI logo",
            "icons": ["lock_icon", "right_arrow_circle_icon"],
            "icon_labels": ["Lock", "Arrow"],
        },
        {
            "num": 3,
            "title": "Welcome / Congratulations",
            "layout": "congratulations.xml",
            "activity": "OtpSuccessfulActivity.java",
            "desc": "Animated success splash shown for 1 second after OTP verification before transitioning to dashboard.",
            "elements": "Large checkmark, WELCOME ONBOARD text, decorative rectangles",
            "icons": ["tickmark"],
            "icon_labels": ["Checkmark"],
        },
        {
            "num": 4,
            "title": "Dashboard / Navigation Menu",
            "layout": "nav_menu.xml",
            "activity": "NavMenuActivity.java",
            "desc": "Main hub with 8 navigation tiles in a 2x4 grid - New Lead, JP Queue, Installment Calculator, etc.",
            "elements": "8 clickable tiles with icons, SREI logo, background overlay image",
            "icons": ["group", "menu_icon"],
            "icon_labels": ["Tile Icon", "Menu"],
        },
        {
            "num": 5,
            "title": "Lead Details (New Lead)",
            "layout": "activity_lead_details.xml",
            "activity": "LeadDetailsActivity.java",
            "desc": "Initial form to capture lead info - name, mobile, Aadhar number, DOB, and document photo uploads.",
            "elements": "Name/Mobile/Aadhar/DOB fields, Aadhar & PAN card upload areas, SEND FOR VERIFICATION button",
            "icons": ["user_icon", "phone_icon", "fingerprint_icon", "calendar_icon", "upload_photo_icon", "camera_icon"],
            "icon_labels": ["User", "Phone", "Fingerprint", "Calendar", "Upload", "Camera"],
        },
        {
            "num": 6,
            "title": "Applicant Details Form",
            "layout": "activity_applicant_details.xml",
            "activity": "ApplicantDetailsActivity.java",
            "desc": "Most complex form (~470 lines) capturing personal, family, financial, and credit history details of the applicant.",
            "elements": "Expandable sections for Customer Type, Banking, Residence, Crops, Margin Money; radio buttons for Gender & FI Status; 30+ input fields",
            "icons": ["dropdown_icon", "dropdown_open", "btn_radio_on", "btn_radio_off", "submit_button"],
            "icon_labels": ["Dropdown", "Open", "Radio On", "Radio Off", "Submit"],
        },
        {
            "num": 7,
            "title": "Co-Applicant Details",
            "layout": "activity_co_applicant_details.xml",
            "activity": "CoApplicantDetailsActivity.java",
            "desc": "Form for co-applicant (spouse/family) who shares liability - captures address, relationship, and background.",
            "elements": "Name, Father/Husband name, Mobile, Address section, Relationship dropdown, FI Status radio buttons",
            "icons": ["dropdown_icon", "submit_button"],
            "icon_labels": ["Dropdown", "Submit"],
        },
        {
            "num": 8,
            "title": "Guarantor Details",
            "layout": "activity_guarantor_details.xml",
            "activity": "GuarantorDetailsActivity.java",
            "desc": "Captures guarantor information - a third party who backs the loan in case of applicant default.",
            "elements": "Guarantor name/address, tractor & CV details, land & property owned, ITR/Form 16, FI Status",
            "icons": ["submit_button"],
            "icon_labels": ["Submit"],
        },
        {
            "num": 9,
            "title": "Asset & Dealer Details",
            "layout": "activity_asset_and_dealer_detail.xml",
            "activity": "AssetAndDealerDetailsActivity.java",
            "desc": "Captures tractor/equipment asset details and dealer info - make, model, engine/chassis numbers, delivery status.",
            "elements": "Asset Make/Model dropdowns, Manufacturing Year, Engine/Chassis No., Dealer Location, Delivery Status radio",
            "icons": ["dropdown_icon", "submit_button"],
            "icon_labels": ["Dropdown", "Submit"],
        },
        {
            "num": 10,
            "title": "Transaction Details",
            "layout": "activity_transaction_details.xml",
            "activity": "TransactionDetailsActivity.java",
            "desc": "Financial form for loan terms - asset MRP, loan amount, tenure, IRR, moratorium, charges, and insurance premiums.",
            "elements": "Asset MRP, Loan Amount, Tenure, Moratorium, Flat Rate, Processing/Documentation charges, Insurance fields",
            "icons": ["dropdown_icon", "submit_button"],
            "icon_labels": ["Dropdown", "Submit"],
        },
        {
            "num": 11,
            "title": "Checklist / Review",
            "layout": "activity_checklist.xml",
            "activity": "ChecklistActivity.java",
            "desc": "Review screen listing all 5 form sections with completion status and edit buttons before final submission.",
            "elements": "5 checklist items (Applicant, Co-Applicant, Guarantor, Asset, Transaction), edit buttons, SUBMIT APPLICATION",
            "icons": ["current_active_menu_indicator", "edit_button", "submit_button"],
            "icon_labels": ["Active Dot", "Edit", "Submit"],
        },
        {
            "num": 12,
            "title": "Feedback Form",
            "layout": "activity_feedback_form.xml",
            "activity": "FeedbackFormActivity.java",
            "desc": "Post-submission feedback survey with thumbs up/down reaction and 4 checkbox-based feedback items.",
            "elements": "Thumbs up/down buttons, 4 feedback checkboxes, SUBMIT FEEDBACK button",
            "icons": ["thumbs_up_icon", "thumbs_down_icon", "checkbox_empty_icon", "checkbox_checked_icon"],
            "icon_labels": ["Thumbs Up", "Thumbs Down", "Unchecked", "Checked"],
        },
        {
            "num": 13,
            "title": "Installment Calculator",
            "layout": "installment_calculator.xml",
            "activity": "InstallmentCalculatorActivity.java",
            "desc": "Financial tool computing monthly installments from asset cost, IRR, tenure, and moratorium (uses fake 4s delay).",
            "elements": "Asset Cost, Financed Amount, IRR, Tenure, Payment Frequency, Moratorium inputs, CALCULATE button",
            "icons": ["submit_button"],
            "icon_labels": ["Calculate"],
        },
        {
            "num": 14,
            "title": "JP Queue (Lead Queue)",
            "layout": "jp_queue.xml",
            "activity": "JpQueue.java",
            "desc": "List view of all saved loan applications showing Lead ID, approval status, and remarks for agent follow-up.",
            "elements": "Table with Lead ID (sortable), Status, Remark columns; RecyclerView of queue items",
            "icons": ["sort_icon", "jp_queue_status_accepted", "jp_queue_status_rejected", "jp_queue_dot_accepted"],
            "icon_labels": ["Sort", "Accepted", "Rejected", "Dot"],
        },
    ]

    # Helper to resolve icon path
    def icon_path(name):
        for ext in [".png"]:
            p = os.path.join(DRAWABLE, name + ext)
            if os.path.exists(p):
                return p
            p = os.path.join(DRAWABLE_MAIN, name + ext)
            if os.path.exists(p):
                return p
            p = os.path.join(DRAWABLE_XXXHDPI, name + ext)
            if os.path.exists(p):
                return p
        return None

    for scr in screens:
        pdf.add_page()
        pdf.section_title(scr["num"], scr["title"])
        pdf.one_liner(scr["desc"])

        pdf.detail_label("Layout XML:")
        pdf.detail_text(scr["layout"])

        pdf.detail_label("Activity:")
        pdf.detail_text(scr["activity"])

        pdf.detail_label("Key UI Elements:")
        pdf.detail_text(scr["elements"])

        # Show icons
        pdf.detail_label("UI Assets Used:")
        pdf.ln(2)

        valid_icons = []
        valid_labels = []
        for ic_name, lbl in zip(scr["icons"], scr["icon_labels"]):
            p = icon_path(ic_name)
            if p:
                valid_icons.append(p)
                valid_labels.append(lbl)

        if valid_icons:
            # Render icons in rows of 5
            row_size = 5
            for i in range(0, len(valid_icons), row_size):
                chunk_icons = valid_icons[i:i + row_size]
                chunk_labels = valid_labels[i:i + row_size]
                pdf.icon_row(chunk_icons, chunk_labels, max_icon_w=20, max_icon_h=20)
                pdf.ln(3)

        # For login & OTP, show the background image as a preview
        if scr["num"] in (1, 2):
            pdf.ln(3)
            pdf.detail_label("Background Preview:")
            bg = to_rgb_png(os.path.join(DRAWABLE, "bg.png"))
            pdf.image(bg, x=60, w=90)

        # For dashboard, show the nav overlay
        if scr["num"] == 4:
            pdf.ln(3)
            pdf.detail_label("Dashboard Background:")
            overlay = to_rgb_png(os.path.join(DRAWABLE_XXXHDPI, "nav_menu_bg_overlay.png"))
            pdf.image(overlay, x=60, w=90)

        # For congratulations, show tickmark larger
        if scr["num"] == 3:
            pdf.ln(3)
            pdf.detail_label("Success Checkmark (Full Size):")
            tick = to_rgb_png(os.path.join(DRAWABLE, "tickmark.png"))
            pdf.image(tick, x=85, w=40)

    # ── Asset Gallery Page ──
    pdf.add_page()
    pdf.set_font("Helvetica", "B", 20)
    pdf.set_text_color(26, 82, 118)
    pdf.cell(0, 14, "Complete UI Asset Gallery", align="C", new_x="LMARGIN", new_y="NEXT")
    pdf.ln(2)
    pdf.set_draw_color(26, 82, 118)
    pdf.line(10, pdf.get_y(), 200, pdf.get_y())
    pdf.ln(6)

    # Show all icons in a grid
    all_icons = sorted([f for f in os.listdir(DRAWABLE) if f.endswith(".png")])
    col = 0
    row_y = pdf.get_y()
    col_w = 45
    max_h = 22

    for fname in all_icons:
        if fname == "bg.png" or fname == "navbar_bg.png":
            continue  # Skip large bg images

        fpath = os.path.join(DRAWABLE, fname)
        img = Image.open(fpath)
        w, h = img.size
        scale = min(20 / w, max_h / h, 1)
        iw, ih = w * scale, h * scale

        x = 10 + col * col_w

        if pdf.get_y() + max_h + 12 > 270:
            pdf.add_page()
            pdf.set_font("Helvetica", "B", 14)
            pdf.set_text_color(26, 82, 118)
            pdf.cell(0, 10, "UI Asset Gallery (continued)", align="C", new_x="LMARGIN", new_y="NEXT")
            pdf.ln(4)
            col = 0
            row_y = pdf.get_y()

        try:
            rgb = to_rgb_png(fpath, f"gallery_{fname}")
            pdf.image(rgb, x + (20 - iw) / 2, row_y, iw, ih)
        except Exception:
            pass

        pdf.set_xy(x, row_y + max_h)
        pdf.set_font("Helvetica", "", 6)
        pdf.set_text_color(80, 80, 80)
        name_short = fname.replace(".png", "").replace("_", " ")
        pdf.cell(col_w - 2, 4, name_short[:20], align="L")

        col += 1
        if col >= 4:
            col = 0
            row_y = row_y + max_h + 12
            pdf.set_y(row_y)

    # ── Queue status badges ──
    pdf.add_page()
    pdf.set_font("Helvetica", "B", 16)
    pdf.set_text_color(26, 82, 118)
    pdf.cell(0, 12, "Status Badges & Indicators", align="C", new_x="LMARGIN", new_y="NEXT")
    pdf.ln(6)

    badges = [
        ("jp_queue_status_accepted.png", "Queue Status: Accepted"),
        ("jp_queue_status_rejected.png", "Queue Status: Rejected"),
        ("saved.png", "Saved Badge (Blue)"),
        ("saved_red.png", "Saved Badge (Red)"),
    ]
    for fname, label in badges:
        fpath = os.path.join(DRAWABLE, fname)
        if os.path.exists(fpath):
            rgb = to_rgb_png(fpath, f"badge_{fname}")
            pdf.set_font("Helvetica", "", 10)
            pdf.set_text_color(60, 60, 60)
            pdf.cell(70, 10, label)
            pdf.image(rgb, x=85, y=pdf.get_y(), w=50)
            pdf.ln(16)

    # Save
    out_path = os.path.join(BASE, "SREI_App_Screen_Reference.pdf")
    pdf.output(out_path)
    print(f"PDF saved to: {out_path}")


if __name__ == "__main__":
    build_pdf()
