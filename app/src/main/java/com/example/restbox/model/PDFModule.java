package com.example.restbox.model;

import android.content.Context;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.restbox.objects.Manager;
import com.example.restbox.objects.Person;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDCheckbox;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDRadioButton;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PDFModule {

    File root;
    Context context;

    private RestboxModel model;
    String[] fields;

    public PDFModule(Context applicationContext) {
        initPDFFiller(applicationContext);
        context = applicationContext;
    }

    public void initPDFFiller(Context applicationContext) {
        PDFBoxResourceLoader.init(applicationContext);
        root = android.os.Environment.getExternalStorageDirectory();

        fields = new String[18];
        model = RestboxModel.getInstance();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null)
                fields[i] = (i < 9) ? "00" + (i + 1) : "0" + (i + 1);
        }
    }

    public boolean fillPDF(Manager manager, String date) {
        for (Person s : model.getQueue()) {
            try {
//                new File("./werkgever.pdf")
//                PDDocument pDDocument = PDDocument.load(new FileInputStream("werkgever.pdf"));
                PDDocument pDDocument = PDDocument.load(context.getAssets().open("werkgever.pdf"));


                PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
                for (String value : fields) {
                    System.out.println(value);
                    if (value.equals("010")) {
                        PDRadioButton rd = (PDRadioButton) pDAcroForm.getField(value);
                        rd.setValue("Geef zo precies mogelijk aan op welke dag(en) en tijdstip(pen) uw werknemer buiten moet zijn:");
                    } else if (value.equals("015")) {
                        PDCheckbox rd = (PDCheckbox) pDAcroForm.getField(value);
                        rd.setValue("Ja");
                    } else {
                        PDTextField field = (PDTextField) pDAcroForm.getField(value);
                        setVals(field, value, s, manager, date);
                    }
                }
                pDDocument.getCurrentAccessPermission().setReadOnly();
                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), date + "_" + s.getName() + ".pdf");
                try {
                    outputFile.createNewFile();
                    OutputStream out = new FileOutputStream(outputFile);
                    pDDocument.save(out);
                    pDDocument.close();
                    out.close();
                    Toast toast = Toast.makeText(context, "Documenten opgeslagen", Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                String path = root.getAbsolutePath() + "/Download/";
//                pDDocument.save(new File(path + date + "_" + s.getName() + ".pdf"));
//                pDDocument.close();


                model.emptyQueue();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setVals(PDTextField field, String val, Person person, Manager manager, String date) throws IOException {
        switch (val) {
            case "001":
                field.setValue(manager.getName());
                break;
            case "002":
                field.setValue(manager.getFunction());
                break;
            case "003":
                field.setValue("Humphrey's Enschede");
                break;
            case "004":
                field.setValue("Oude Markt 3A, 7511 GA");
                break;
            case "005":
            case "016":
                field.setValue("Enschede, Nederland");
                break;
            case "006":
                field.setValue(manager.getPhone());
                break;
            case "007":
                field.setValue(person.getName());
                break;
            case "008":
                field.setValue(person.getFunction());
                break;
            case "009":
                field.setValue(person.getDateOfBirth());
                break;
            case "010":
                field.setValue("Geef zo precies mogelijk aan op welke dag(en) en tijdstip(pen) uw werknemer buiten moet zijn:");
                break;
            case "011":
                field.setValue(date + " tussen 21:00 en 23:00");
                break;
            case "012":
            case "013":
            case "014":
            case "018":
                break;
            case "015":
                field.setValue("Ja");
                break;
            case "017":
                SimpleDateFormat dateFormat;
                String currentdate;
                Calendar calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                currentdate = dateFormat.format(calendar.getTime());
                field.setValue(currentdate);
//
//
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//                LocalDateTime now = LocalDateTime.now();
//                field.setValue(dtf.format(now));
                break;
            default:
                System.out.println("No case found for this item.");
                break;
        }
    }

//    public void setVals(PDCheckbox field, String val, Person person, Manager manager, String date) throws IOException {
//        switch (val) {
//            case "010":
//                field.setValue("Geef zo precies mogelijk aan op welke dag(en) en tijdstip(pen) uw werknemer buiten moet zijn:");
//                break;
//            case "015":
//                field.setValue("Ja");
//                break;
//            default:
//                System.out.println("No case found for this item.");
//                break;
//        }
//    }
}