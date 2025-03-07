package edu.mcw.scge.process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {

    public static String correctInitiative(String initiative)  {
        switch (initiative) {
            case "Rodent Testing Center":
            case "Large Animal Reporter":
                return "Animal Reporter and Testing Center";
            case "Large Animal Testing Center":
                return "Large Animal Testing Centers (LATC)";
            case "Cell & Tissue Platform":
            case "In Vivo Cell Tracking":
            case "Biological Systems":
                return "Biological Effects";
            case "Delivery Vehicle":
                return "Delivery Systems";
            case "New Editors":
                return "Genome Editors";
        }

        return initiative;

    }


    public static String formatFASTA(String str) {



        if (str == null) {
            return "";
        }

        if (str.length()< 79) {
            return str;
        }


        int start =0;
        int end = 79;

        int runs = str.length() / 80;


        String ret = "";
        for (int i=0; i<runs; i++)  {
            ret +=str.substring(start,end) + "\n";
            start +=80;
            end +=80;

        }
        if (str.length() > start) {
            ret += str.substring(start);
        }

        return ret;

    }

    public static String replacePhiSymbol(String namePre){

        if (namePre == null) {
            return "";
        }
        String namePost = "";
        for (int i =0; i< namePre.length(); i++) {

            char c = namePre.charAt(i);
            int code = (int) c;

            if (code == 934) {
                namePost += "&#x3D5;";
            }else {
                namePost += c;
            }
        }
        return namePost;

    }


    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if(date!=null)
            return sdf.format(date);
        return "";

    }
    public static String formatName(String name){
        String formattedName=new String();
        if(name!=null){

            Pattern p = Pattern.compile(".*,\\s*(.*)");
            Matcher m = p.matcher(name);

            if (m.find()){
                name= name.replace(m.group(1), "").replace(",","");

                System.out.println(m.group(1)+"\t" +name +"\tLast Name:"+ name.trim().substring(name.trim().lastIndexOf(" ") + 1));
                formattedName= (name.trim().substring(name.trim().lastIndexOf(" ") + 1))
                        +", "
                        +name.substring(0,name.trim().lastIndexOf(" ")) ;
            }
        }
        if(!formattedName.equals(""))
            return formattedName;
        else return name;
    }

    public static String getRGBValue(String scale, int currentValue, int maxValue) {

        if (maxValue < 1) {
            maxValue=1;
        }

        double value = ((double) currentValue/ (double) maxValue );   // first you should normalize to a number between 0 and 1

        //red scale
        int aR = 232;
        int aG = 228;
        int aB=213;  // rgb for our 1st color (blue in this case)
        int bR = 128;
        int bG = 0;
        int bB=0;    // rbg for our 2nd color (red in this case)

        if (scale.equals("blue")) {
            aR = 232;
            aG = 228;
            aB=213;  // rgb for our 1st color (blue in this case)
            bR = 0;
            bG = 128;
            bB=0;    // rbg for our 2nd color (red in this case)

        }else if (scale.equals("green")) {
            aR = 232;
            aG = 228;
            aB=213;  // rgb for our 1st color (blue in this case)
            bR = 0;
            bG = 0;
            bB=128;    // rbg for our 2nd color (red in this case)
        }


        double red = (bR - aR) * value + aR;      // evaluated as -255*value + 255
        double green = (bG - aG) * value + aG;      // evaluates as 0
        double blue  = (bB - aB) * value + aB;      // evaluates as 255*value + 0

        return "rgb(" + (int) red + "," + (int) green + "," + (int) blue + ")";

    }

    public static String buildMap() {
        String map = "";
        return map;

    }

    public static String formatNumber(String number, int digits) {

        int pos = number.indexOf(".");
        if (pos == -1) {
            return number;
        }

        pos = number.indexOf("E");
        if (pos == -1) {
            return number;
        }

        //double d = Double.parseDouble(number);
        BigDecimal bd = new BigDecimal(number);
        bd = bd.round(new MathContext(pos + 3));
        return bd.toPlainString();

        //double rounded = bd.doubleValue();

        //return String.format(number, "%.2f");
    }
//    public static String getLabel(String l){
//        if(l.equalsIgnoreCase("Cell & Tissue Platform"))
//            return "Biological Systems";
//        if(l.equalsIgnoreCase("New Editors Initiative"))
//            return  "Genome Editors";
//        if(l.equalsIgnoreCase("Large Animal Reporter"))
//            return "Large Animal Reporter (LAR)";
//        if(l.equalsIgnoreCase("Large Animal Testing Center"))
//            return  "Large Animal Testing Center (LATC)";
//        if(l.equalsIgnoreCase("Delivery Vehicle Initiative"))
//            return  "Delivery Systems Initiative";
//        if(l.equalsIgnoreCase("Rodent Testing Center"))
//            return "Small Animal Testing Center (SATC)";
//        if(l.equalsIgnoreCase("In Vivo Cell Tracking"))
//            return "In Vivo Cell Tracking";
//        return l;
//    }

}