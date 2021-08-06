package edu.mcw.scge.process.customLabels;

import edu.mcw.scge.dao.implementation.*;
import edu.mcw.scge.datamodel.ExperimentRecord;
import edu.mcw.scge.datamodel.Guide;
import edu.mcw.scge.datamodel.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class CustomLabels {
    GuideDao guideDao=new GuideDao();
    VectorDao vectorDao = new VectorDao();
    ExperimentResultDao experimentResultDao = new ExperimentResultDao();

    public List<String> getLabelFields(List<ExperimentRecord> records, Map<String, Integer> objectSizeMap, String initiative) throws Exception {
        List<String> uniqueFields=new ArrayList<>();
        boolean flag=false;
        for(Map.Entry e:objectSizeMap.entrySet()){
            int value= (int) e.getValue();
            String field= (String) e.getKey();
            if(value==records.size()){
                uniqueFields.add(field);
                flag=true;
                break;
            }
        }
        if(flag)
            return uniqueFields;
        else{

            for (String s : objectSizeMap.keySet()) {
                Set<String> uniqueLabels=new HashSet<>();

                for(ExperimentRecord record: records) {
                    if (objectSizeMap.get(s) > 1) {
                        if(s.equalsIgnoreCase("delivery"))
                            uniqueLabels.add(record.getDeliverySystemName());
                        if(s.equalsIgnoreCase("editor"))
                            uniqueLabels.add(record.getEditorSymbol());
                        if(s.equalsIgnoreCase("vector")) {
                            String vector=new String();
                            for(Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                vector+=v.getName() + " ";
                            uniqueLabels.add(vector);
                        }
                        if(s.equalsIgnoreCase("guide")) {
                            String guide = new String();
                            for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                // labels.add("\"" + record.getExperimentName() + "\"");
                                guide+=(g.getGuide())+" ";
                            uniqueLabels.add(guide);
                        }
                        if(s.equalsIgnoreCase("applicationMethod") ){
                            uniqueLabels.add(record.getInjectionFrequency()+" "+record.getDosage());

                        }
                        if(s.equalsIgnoreCase("model")){
                            uniqueLabels.add(String.valueOf(record.getModelId()));

                        }
                        if(s.equalsIgnoreCase("tissue")){
                            uniqueLabels.add( record.getTissueTerm() + " ");

                        }
                    }
                }
                if(uniqueLabels.size()==records.size()){
                    uniqueFields.add(s);
                    return uniqueFields;
                }

            }


            for (String s : objectSizeMap.keySet()) {
                if (objectSizeMap.get(s) > 1) {
                    for (String t : objectSizeMap.keySet()) {
                        if (objectSizeMap.get(t) > 1 && !s.equalsIgnoreCase(t)) {
                            Set<String> uniqueLabels=new HashSet<>();
                            for (ExperimentRecord record : records) {
                                if (s.equalsIgnoreCase("delivery")) {
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(record.getDeliverySystemName()+" "+record.getEditorSymbol());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(record.getDeliverySystemName()+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(record.getDeliverySystemName()+" "+guide);
                                    }
                                    if(t.equalsIgnoreCase("applicationMethod") ){
                                        uniqueLabels.add(record.getDeliverySystemName()+" "+record.getInjectionFrequency()+"-"+record.getDosage());

                                    }
                                    if(t.equalsIgnoreCase("model")){
                                        uniqueLabels.add(record.getDeliverySystemName()+" "+String.valueOf(record.getModelId()));

                                    }
                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add( record.getDeliverySystemName()+" "+record.getTissueTerm());

                                    }
                                }
                                if (s.equalsIgnoreCase("editor")) {
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(record.getEditorSymbol()+" "+ record.getDeliverySystemName());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(record.getEditorSymbol()+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(record.getEditorSymbol()+" "+guide);
                                    }
                                    if(t.equalsIgnoreCase("applicationMethod") ){
                                        uniqueLabels.add(record.getEditorSymbol()+" "+record.getInjectionFrequency()+"-"+record.getDosage());

                                    }
                                    if(t.equalsIgnoreCase("model")){
                                        uniqueLabels.add(record.getEditorSymbol()+" "+String.valueOf(record.getModelId()));

                                    }
                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add(record.getEditorSymbol()+" "+ record.getTissueTerm() + " ");

                                    }
                                }
                                if (s.equalsIgnoreCase("vector")) {
                                    String vector = new String();
                                    for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                        vector += v.getName() + " ";
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(vector+" "+ record.getDeliverySystemName());
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(vector+" "+guide);
                                    }
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(vector+" "+record.getEditorSymbol());
                                    if(t.equalsIgnoreCase("applicationMethod") ){
                                        uniqueLabels.add(vector+" "+record.getInjectionFrequency()+" "+record.getDosage());

                                    }
                                    if(t.equalsIgnoreCase("model")){
                                        uniqueLabels.add(vector+" "+String.valueOf(record.getModelId()));

                                    }
                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add( vector+" "+record.getTissueTerm());

                                    }
                                }
                                if (s.equalsIgnoreCase("guide")) {
                                    String guide = new String();
                                    for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                        // labels.add("\"" + record.getExperimentName() + "\"");
                                        guide += (g.getGuide()) + " ";
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(guide+" "+record.getEditorSymbol());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(guide+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(guide+" "+ record.getDeliverySystemName());
                                    if(t.equalsIgnoreCase("applicationMethod") ){
                                        uniqueLabels.add(guide+" "+record.getInjectionFrequency()+" "+record.getDosage());

                                    }
                                    if(t.equalsIgnoreCase("model")){
                                        uniqueLabels.add(guide+" "+String.valueOf(record.getModelId()));

                                    }
                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add( guide+" "+record.getTissueTerm());

                                    }
                                }
                                if (s.equalsIgnoreCase("model")) {
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(record.getModelId()+" "+guide);
                                    }
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(record.getModelId()+" "+record.getEditorSymbol());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(record.getModelId()+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(record.getModelId()+" "+ record.getDeliverySystemName());
                                    if(t.equalsIgnoreCase("applicationMethod") ){
                                        uniqueLabels.add(record.getModelId()+" "+record.getInjectionFrequency()+" "+record.getDosage());

                                    }

                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add( record.getModelId()+" "+record.getTissueTerm());

                                    }
                                }
                                if (s.equalsIgnoreCase("applicationMethod")) {
                                    String dosage=new String();
                                    if(record.getInjectionFrequency()!=null && !record.getInjectionFrequency().equals(""))
                                        dosage=record.getInjectionFrequency()+" ";
                                    dosage+=record.getDosage();
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(dosage+" "+guide);
                                    }
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(dosage+" "+record.getEditorSymbol());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(dosage+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(dosage+" "+ record.getDeliverySystemName());
                                    if(t.equalsIgnoreCase("model") ){
                                        uniqueLabels.add(dosage+" "+record.getModelId());

                                    }

                                    if(t.equalsIgnoreCase("tissue")){
                                        uniqueLabels.add( dosage+" "+record.getTissueTerm());

                                    }
                                }
                                if (s.equalsIgnoreCase("tissue")) {
                                    if (t.equalsIgnoreCase("guide")) {
                                        String guide = new String();
                                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                                            // labels.add("\"" + record.getExperimentName() + "\"");
                                            guide += (g.getGuide()) + " ";
                                        uniqueLabels.add(record.getTissueTerm()+" "+guide);
                                    }
                                    if (t.equalsIgnoreCase("editor"))
                                        uniqueLabels.add(record.getTissueTerm()+" "+record.getEditorSymbol());
                                    if (t.equalsIgnoreCase("vector")) {
                                        String vector = new String();
                                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                                            vector += v.getName() + " ";
                                        uniqueLabels.add(record.getTissueTerm()+" "+vector);
                                    }
                                    if (t.equalsIgnoreCase("delivery"))
                                        uniqueLabels.add(record.getTissueTerm()+" "+ record.getDeliverySystemName());
                                    if(t.equalsIgnoreCase("model") ){
                                        uniqueLabels.add(record.getTissueTerm()+" "+record.getModelId());

                                    }

                                    if(t.equalsIgnoreCase("applicationMethod")){
                                        uniqueLabels.add( record.getTissueTerm()+" "+record.getInjectionFrequency()+" "+record.getDosage());

                                    }
                                }
                            }
                            if (uniqueLabels.size() == records.size()) {
                                uniqueFields.add(s);
                                uniqueFields.add(t);
                                return uniqueFields;
                            }
                        }
                    }

                }
            }


        }
        for(Map.Entry field:objectSizeMap.entrySet()){
            if((int) field.getValue()>1){
                uniqueFields.add((String) field.getKey());
            }
        }
        return uniqueFields;
    }
    public Map<String, Integer> getObjectSizeMap(List<ExperimentRecord> records){
        Map<String, Integer> objectSizeMap=new HashMap<>();
        Set<Long> editors=records.stream().map(r->r.getEditorId()).collect(Collectors.toSet());
        Set<Long> deliveries=records.stream().map(d->d.getDeliverySystemId()).collect(Collectors.toSet());
        Set<Long> models=records.stream().map(d->d.getModelId()).collect(Collectors.toSet());
        Set<String> tissueIds=records.stream().map(d->d.getTissueId()).collect(Collectors.toSet());
        Set<String> cellTypes=records.stream().map(d->d.getCellType()).collect(Collectors.toSet());

        Set<Integer> applicationMethods=records.stream().map(r->r.getApplicationMethodId()).collect(Collectors.toSet());
        Set<String> dosage=records.stream().map(r->r.getDosage()).filter(p->p!=null && !p.equals("")).collect(Collectors.toSet());
        Set<String> sex=records.stream().map(d->d.getSex()).collect(Collectors.toSet());
        Set<Long> guides= records.stream().map(x -> {
            try {
                return guideDao.getGuidesByExpRecId(x.getExperimentRecordId()).stream()
                        .map(Guide::getGuide_id)
                        .collect(Collectors.toSet());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).flatMap(Set::stream).collect(Collectors.toSet());
        Set<String> targetLocus= records.stream().map(x -> {
            try {
                return guideDao.getGuidesByExpRecId(x.getExperimentRecordId()).stream()
                        .map(Guide::getTargetLocus)
                        .collect(Collectors.toSet());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).flatMap(Set::stream).collect(Collectors.toSet());
        Set<Long> vectors= records.stream().map(x -> {
            try {
                return vectorDao.getVectorsByExpRecId(x.getExperimentRecordId()).stream()
                        .map(g -> g.getVectorId())
                        .collect(Collectors.toSet())
                        ;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).flatMap(Set::stream).collect(Collectors.toSet());
        Set<Integer> samples=records.stream().map(x -> {
            try {
                return experimentResultDao.getResultsByExperimentRecId(x.getExperimentRecordId()).stream()
                        .map(r -> r.getNumberOfSamples())
                        .collect(Collectors.toSet())
                        ;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).flatMap(Set::stream).collect(Collectors.toSet());
        if(editors.size()>0){
            objectSizeMap.put("editor", editors.size());
        }
        if(deliveries.size()>0){
            objectSizeMap.put("delivery", deliveries.size());
        }
        if(guides.size()>0){
            objectSizeMap.put("guide", guides.size());
        }
        if(models.size()>0){
            objectSizeMap.put("model", models.size());
        }
        if(vectors.size()>0){
            objectSizeMap.put("vector", vectors.size());
        }
        if(applicationMethods.size()>0){
            objectSizeMap.put("applicationMethod", applicationMethods.size());
        }
        if(dosage.size()>0){
            objectSizeMap.put("dosage", dosage.size());
        }
        if(tissueIds.size()>0){
            objectSizeMap.put("tissue", tissueIds.size());
        }
        if(cellTypes.size()>0){
            objectSizeMap.put("cellType", cellTypes.size());
        }
        if(targetLocus.size()>0){
            objectSizeMap.put("targetLocus", targetLocus.size());
        }
       /* if(samples.size()>0){
            objectSizeMap.put("samples", samples.size());
        }*/
        if(sex.size()>0){
            objectSizeMap.put("sex", sex.size());
        }
        return objectSizeMap;
    }
    public StringBuilder getLabel(ExperimentRecord record,String initiative, Map<String, Integer> objectMapSize) throws Exception {
        StringBuilder label=new StringBuilder();
        System.out.println("INITIATIVE:"+ initiative);
        switch (initiative.toLowerCase()){
            case "delivery vehicle initiative":
                if(objectMapSize.get("delivery")!=null && objectMapSize.get("delivery")>1){
                    label.append( record.getDeliverySystemName()+" ");

                }
                if(objectMapSize.get("applicationMethod")!=null && objectMapSize.get("applicationMethod")>1){
                    label.append(record.getDosage() + " ");

                }
                if(objectMapSize.get("editor")!=null && objectMapSize.get("editor")>1){
                    label.append(record.getEditorSymbol() + " ");

                }
                if(objectMapSize.get("guide")!=null && objectMapSize.get("guide")>1) {
                    for(Guide g: guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                        // labels.add("\"" + record.getExperimentName() + "\"");
                        label.append(g.getGuide()).append(" ");

                }

                if(objectMapSize.get("vector")!=null && objectMapSize.get("vector")>1){
                    for(Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append( v.getName() + " ");

                }
                if(objectMapSize.get("model")!=null && objectMapSize.get("model")>1){
                    label.append( record.getModelId() + " ");

                }

                if(objectMapSize.get("tissue")!=null && objectMapSize.get("tissue")>1){
                    label.append( record.getTissueTerm() + " ");

                }
                if(objectMapSize.get("cellType")!=null && objectMapSize.get("cellType")>1){
                    label.append( record.getCellType() + " ");

                }
                break;
            case "new editors initiative":
                if(objectMapSize.get("editor")!=null && objectMapSize.get("editor")>1){
                    label.append(record.getEditorSymbol() + " ");

                }
                if(objectMapSize.get("guide")!=null && objectMapSize.get("guide")>1) {
                    for(Guide g: guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                        // labels.add("\"" + record.getExperimentName() + "\"");
                        label.append(g.getGuide()).append(" ");

                }

                if(objectMapSize.get("vector")!=null && objectMapSize.get("vector")>1){
                    for(Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append( v.getName() + " ");

                }
                if(objectMapSize.get("delivery")!=null && objectMapSize.get("delivery")>1){
                    label.append( record.getDeliverySystemType()+" ");

                }
                if(objectMapSize.get("applicationMethod")!=null && objectMapSize.get("applicationMethod")>1){
                    label.append(record.getDosage());

                }
                if(objectMapSize.get("cellType")!=null && objectMapSize.get("cellType")>1){
                    label.append( record.getCellType() + " ");

                }
                break;
            default:
                if(objectMapSize.get("guide")!=null && objectMapSize.get("guide")>1) {
                    for(Guide g: guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                        // labels.add("\"" + record.getExperimentName() + "\"");
                        label.append(g.getGuide()).append(" ");

                }
                if(objectMapSize.get("editor")!=null && objectMapSize.get("editor")>1){
                    label.append(record.getEditorSymbol() + " ");

                }
                if(objectMapSize.get("vector")!=null && objectMapSize.get("vector")>1){
                    for(Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append( v.getName() + " ");

                }
                if(objectMapSize.get("delivery") !=null && objectMapSize.get("delivery")>1){
                    label.append( record.getDeliverySystemType()+" ");

                }
                if(objectMapSize.get("applicationMethod")!=null && objectMapSize.get("applicationMethod")>1){
                    label.append(record.getDosage());

                }
                if(objectMapSize.get("model")!=null && objectMapSize.get("model")>1){
                    label.append(record.getModelId());

                }
                if(objectMapSize.get("tissue")!=null && objectMapSize.get("tissue")>1){
                    label.append( record.getTissueTerm() + " ");

                }
                if(objectMapSize.get("cellType")!=null && objectMapSize.get("cellType")>1){
                    label.append( record.getCellType() + " ");

                }
        }

        System.out.println("label:"+label);
        return label;
    }
    public StringBuilder getLabel(ExperimentRecord record,String initiative, Map<String, Integer> objectMapSize, List<String> uniqueFields) throws Exception {
        StringBuilder label=new StringBuilder();
        switch (initiative.toLowerCase()){
            case "rodent testing center":
            case "delivery vehicle initiative":
                System.out.println("CASE: "+ initiative);
                if(uniqueFields.contains("delivery")) {
                    label.append(record.getDeliverySystemName() + " ");
                }
                for(String s:uniqueFields) {
                    if (s.equalsIgnoreCase("editor")){
                        label.append(record.getEditorSymbol() + " ");

                    }
                    if (s.equalsIgnoreCase("guide") || s.equalsIgnoreCase("targetLocus")){
                        for(Guide g: guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                            // labels.add("\"" + record.getExperimentName() + "\"");
                            label.append(g.getGuide()).append(" ");
                    }
                    if (s.equalsIgnoreCase("vector")){
                        for(Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                            label.append( v.getName() + " ");

                    }
                    if(s.equalsIgnoreCase("applicationMethod") || s.equalsIgnoreCase("dosage") ){

                        if(record.getInjectionFrequency()!=null) {

                            label.append(record.getInjectionFrequency());
                            label.append(" ");
                        }
                        label.append(record.getDosage());
                    }
                    if(s.equalsIgnoreCase("model")){
                        label.append(record.getModelId());

                    }
                    if(s.equalsIgnoreCase("tissue")){
                        label.append( record.getTissueTerm() + " ");

                    }
                    if(s.equalsIgnoreCase("cellType")){
                        label.append( record.getCellType() + " ");

                    }
                    if(s.equalsIgnoreCase("samples")){
                        label.append( experimentResultDao.getResultsByExperimentRecId(record.getExperimentRecordId()).get(0).getNumberOfSamples() + " Samples ");

                    }
                    if(s.equalsIgnoreCase("sex")){
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("F"))
                            label.append(  "Female ");
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("M"))
                            label.append(  "Male ");
                    }
                }

                break;
            case "new editors initiative":
                System.out.println("CASE: "+ initiative);

                if(uniqueFields.contains("editor")) {
                    label.append(record.getEditorSymbol() + " ");
                }
                for (String s : uniqueFields) {
                    if (s.equalsIgnoreCase("delivery")) {
                        label.append(record.getDeliverySystemType() + " ");
                    }
                    if (s.equalsIgnoreCase("guide") || s.equalsIgnoreCase("targetLocus")) {
                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId())) {
                            // labels.add("\"" + record.getExperimentName() + "\"");
                            label.append(g.getGuide()).append(" ");

                        }
                    }
                    if (s.equalsIgnoreCase("vector")) {
                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId())) {

                            label.append(v.getName() + " ");
                        }

                    }
                    if(s.equalsIgnoreCase("applicationMethod") || s.equalsIgnoreCase("dosage") ){

                        if(record.getInjectionFrequency()!=null) {

                            label.append(record.getInjectionFrequency());
                            label.append(" ");
                        }
                        label.append(record.getDosage());
                    }
                    if(s.equalsIgnoreCase("model")){
                        label.append(record.getModelId());

                    }
                    if(s.equalsIgnoreCase("tissue")){
                        label.append( record.getTissueTerm() + " ");

                    }
                    if(s.equalsIgnoreCase("cellType")){
                        label.append( record.getCellType() + " ");

                    }
                    if(s.equalsIgnoreCase("samples")){
                        label.append( experimentResultDao.getResultsByExperimentRecId(record.getExperimentId()).get(0).getNumberOfSamples() + " Samples ");

                    }
                    if(s.equalsIgnoreCase("sex")){
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("F"))
                            label.append(  "Female ");
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("M"))
                            label.append(  "Male ");
                    }
                }

                break;
            default:
                System.out.println("CASE: DEFAULT:"+ initiative);

                for (String s : uniqueFields) {
                    if (s.equalsIgnoreCase("delivery")) {
                        label.append(record.getDeliverySystemType() + " ");
                    }
                    if (s.equalsIgnoreCase("editor")) {
                        label.append(record.getEditorSymbol() + " ");
                    }
                    if (s.equalsIgnoreCase("guide") || s.equalsIgnoreCase("targetLocus")) {
                        for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                            // labels.add("\"" + record.getExperimentName() + "\"");
                            label.append(g.getGuide()).append(" ");
                    }
                    if (s.equalsIgnoreCase("vector")) {
                        for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                            label.append(v.getName() + " ");

                    }
                    if(s.equalsIgnoreCase("applicationMethod") || s.equalsIgnoreCase("dosage")){

                        if(record.getInjectionFrequency()!=null) {

                            label.append(record.getInjectionFrequency());
                            label.append(" ");
                        }
                        label.append(record.getDosage());
                    }
                    if(s.equalsIgnoreCase("model")){
                        label.append(record.getModelId());

                    }
                    if(s.equalsIgnoreCase("tissue")){
                        label.append( record.getTissueTerm() + " ");

                    }
                    if(s.equalsIgnoreCase("cellType")){
                        label.append( record.getCellType() + " ");

                    }
                    if(s.equalsIgnoreCase("samples")){
                        label.append( experimentResultDao.getResultsByExperimentRecId(record.getExperimentId()).get(0).getNumberOfSamples() + " Samples ");

                    }
                    if(s.equalsIgnoreCase("sex")){
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("F"))
                            label.append(  "Female ");
                        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("M"))
                            label.append(  "Male ");
                    }
                }
        }
        if(label.toString().equals("")){
            System.out.println("LABEL EMPTY: "+ initiative);

            for (String s : uniqueFields) {
                if (s.equalsIgnoreCase("delivery")) {
                    label.append(record.getDeliverySystemType() + " ");
                }
                if (s.equalsIgnoreCase("editor")) {
                    label.append(record.getEditorSymbol() + " ");
                }
                if (s.equalsIgnoreCase("guide") || s.equalsIgnoreCase("targetLocus")) {
                    for (Guide g : guideDao.getGuidesByExpRecId(record.getExperimentRecordId()))
                        // labels.add("\"" + record.getExperimentName() + "\"");
                        label.append(g.getGuide()).append(" ");
                }
                if (s.equalsIgnoreCase("vector")) {
                    for (Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append(v.getName() + " ");

                }
                if(s.equalsIgnoreCase("applicationMethod") || s.equalsIgnoreCase("dosage")){
                    if(record.getInjectionFrequency()!=null) {

                        label.append(record.getInjectionFrequency());
                        label.append(" ");
                    }
                    label.append(record.getDosage());

                }
                if(s.equalsIgnoreCase("model")){
                    label.append(record.getModelId());

                }
                if(s.equalsIgnoreCase("tissue")){
                    label.append( record.getTissueTerm() + " ");

                }
                if(s.equalsIgnoreCase("cellType")){
                    label.append( record.getCellType() + " ");

                }
                if(s.equalsIgnoreCase("sex")){
                    if(record.getSex()!=null && record.getSex().equalsIgnoreCase("F"))
                        label.append(  "Female ");
                    if(record.getSex()!=null && record.getSex().equalsIgnoreCase("M"))
                        label.append(  "Male ");
                }
                if(s.equalsIgnoreCase("samples")){
                    label.append( experimentResultDao.getResultsByExperimentRecId(record.getExperimentId()).get(0).getNumberOfSamples() + " Samples ");

                }
            }

        }
        System.out.println("label:"+label);
        return label;
    }
}
