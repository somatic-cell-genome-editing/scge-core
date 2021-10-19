package edu.mcw.scge.process.customLabels;

import edu.mcw.scge.dao.implementation.EditorDao;
import edu.mcw.scge.dao.implementation.ExperimentResultDao;
import edu.mcw.scge.dao.implementation.GuideDao;
import edu.mcw.scge.dao.implementation.VectorDao;
import edu.mcw.scge.datamodel.ExperimentRecord;
import edu.mcw.scge.datamodel.Guide;

import java.util.*;
import java.util.stream.Collectors;

public class CustomUniqueLabels {
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
        if(flag){
            return uniqueFields;}
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
                            for(edu.mcw.scge.datamodel.Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                        if(s.equalsIgnoreCase("name")){
                            uniqueLabels.add( record.getExperimentName() + " ");

                        }
                        if(s.equalsIgnoreCase("sex")){
                            uniqueLabels.add( record.getSex() + " ");

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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                    for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                                        for (edu.mcw.scge.datamodel.Vector v : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
        System.out.println("UNIQUE FIELDS:"+ uniqueFields);
        Set<String> uniqueLabels=new HashSet<>();
            for (ExperimentRecord record : records) {
                StringBuilder label=new StringBuilder();
                for(String s:uniqueFields){
                    if(s.equalsIgnoreCase("delivery"))
                       appendDelivery(record, label);
                    if(s.equalsIgnoreCase("editor"))
                       appendEditor(record, label);
                    if(s.equalsIgnoreCase("vector")) {
                       appendVector(record, label);
                    }
                    if(s.equalsIgnoreCase("guide")) {
                       appendGuide(record, label);
                    }
                    if(s.equalsIgnoreCase("applicationMethod") ){
                        appendDosage(record, label);
                    }
                    if(s.equalsIgnoreCase("model")){
                    appendModel(record, label);
                    }
                    if(s.equalsIgnoreCase("tissue")){
                        appendTissue(record, label);

                    }
                    if(s.equalsIgnoreCase("sex")){
                        appendSex(record, label);

                    }
            }
               uniqueLabels.add(label.toString());
        }
            if(uniqueLabels.size()==records.size()){
                return uniqueFields;
            }else{
                if(records.get(0).getExperimentId()!=18000000011L && records.get(0).getExperimentId()!=18000000018L )
              uniqueFields=new ArrayList<>(Arrays.asList("name"));
            }

        return uniqueFields;
    }
    public Map<String, Integer> getObjectSizeMap(List<ExperimentRecord> records){
        Map<String, Integer> objectSizeMap=new HashMap<>();
        Set<String> names=new HashSet<>();
        if(records.get(0).getExperimentId()!= 18000000018L && records.get(0).getExperimentId()!= 18000000011L)
             names=   records.stream().map(r->r.getExperimentName())
                .filter(name-> !name.contains("Condition")).collect(Collectors.toSet());
        Set<Long> editors=records.stream().map(r->r.getEditorId()).collect(Collectors.toSet());
        Set<Long> deliveries=records.stream().map(d->d.getDeliverySystemId()).collect(Collectors.toSet());
        Set<Long> models=records.stream().map(d->d.getModelId()).collect(Collectors.toSet());
        Set<String> tissueIds=records.stream().map(d->d.getTissueId()).collect(Collectors.toSet());

        Set<String> cellTypes=new HashSet<>();
        if(records.get(0).getCellType()!=null) {
            cellTypes     = records.stream().filter(d -> d != null && !d.getCellType().isEmpty()).map(d -> d.getCellType()).collect(Collectors.toSet());
        }
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
        Set<String> resultTypes=records.stream().map(x -> {
            try {
                return experimentResultDao.getResultsByExperimentRecId(x.getExperimentRecordId()).stream()
                        .map(r -> r.getResultType())
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
     /*  if(resultTypes.size()>0){
           objectSizeMap.put("resultType", resultTypes.size());
       }*/
        if(sex.size()>0){
            objectSizeMap.put("sex", sex.size());
        }
        if(names.size()>0){
            objectSizeMap.put("name", names.size());
        }
        return objectSizeMap;
    }
  /*  public StringBuilder getLabel(ExperimentRecord record,String initiative, Map<String, Integer> objectMapSize) throws Exception {
        StringBuilder label=new StringBuilder();
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
                    for(edu.mcw.scge.datamodel.Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append( v.getName() + " ");

                }
                if(objectMapSize.get("model")!=null && objectMapSize.get("model")>1){
                    label.append( record.getModelId() + " ");

                }

                if(objectMapSize.get("tissue")!=null && objectMapSize.get("tissue")>1){
                    label.append( record.getTissueTerm() + " ");

                }
                if(objectMapSize.get("cellType")!=null && objectMapSize.get("cellType")>1){
                    if(!record.getCellType().isEmpty())
                    label.append( record.getCellTypeTerm() + " ");

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
                    for(edu.mcw.scge.datamodel.Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                        label.append( v.getName() + " ");

                }
                if(objectMapSize.get("delivery")!=null && objectMapSize.get("delivery")>1){
                    label.append( record.getDeliverySystemType()+" ");

                }
                if(objectMapSize.get("applicationMethod")!=null && objectMapSize.get("applicationMethod")>1){
                    label.append(record.getDosage());

                }
                if(objectMapSize.get("cellType")!=null && objectMapSize.get("cellType")>1){
                    label.append( record.getCellTypeTerm() + " ");

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
                    for(edu.mcw.scge.datamodel.Vector v: vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
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
                    if(record.getCellType()!=null)
                    label.append( record.getCellTypeTerm() + " ");

                }
        }

        return label;
    }*/
    public StringBuilder getLabel(ExperimentRecord record,String initiative, Map<String, Integer> objectMapSize, List<String> uniqueFields, long resultId) throws Exception {
        StringBuilder label=new StringBuilder();
        if(record==null){
            return null;
        }
        switch (initiative.toLowerCase()){
            case "rodent testing center":
            case "delivery vehicle initiative":
                if(uniqueFields.contains("delivery")) {
                    appendDelivery(record, label);
                }
                for(String field:uniqueFields) {
                   if(!field.equalsIgnoreCase("delivery")){
                       appendLabel(record, label, field);
                    }
                }

                break;
            case "new editors initiative":
                if(uniqueFields.contains("editor")) {
                   appendEditor(record, label);
                }
                for (String field : uniqueFields) {
                  if(!field.equalsIgnoreCase("editor")){
                      appendLabel(record, label, field);

                  }
                }

                break;
            default:
                for (String field : uniqueFields) {
                    appendLabel(record, label, field);

                }
        }
        if(label.toString().equals("")){
            for (String field : uniqueFields) {
                appendLabel(record, label, field);

            }

        }

        return label;
    }
    public void appendLabel(ExperimentRecord record, StringBuilder label, String field) throws Exception {
        if (field.equalsIgnoreCase("editor")){
            appendEditor(record, label);
        }
        if (field.equalsIgnoreCase("guide")){
            appendGuide(record, label);
        }
        if( field.equalsIgnoreCase("targetLocus")){
            appendTargetLocus(record,label);
        }
        if(field.equalsIgnoreCase("delivery")) {
            appendDelivery(record, label);
        }
        if (field.equalsIgnoreCase("vector")){
            appendVector(record,label);

        }
        if(field.equalsIgnoreCase("applicationMethod") || field.equalsIgnoreCase("dosage") ){

            appendDosage(record, label);
        }
        if(field.equalsIgnoreCase("model")){

            appendModel(record, label);
        }
        if(field.equalsIgnoreCase("tissue")){

            appendTissue(record, label);
        }
        if(field.equalsIgnoreCase("cellType")){
            if(record!=null && record.getCellType()!=null)
            appendCellType(record,label);
        }
    /*    if(field.equalsIgnoreCase("samples")){
            appendSamples(record,label);
        }
        if(field.equalsIgnoreCase("resultType")){
            appendResultType(record, label);
        }*/
        if(field.equalsIgnoreCase("sex")){
            appendSex(record,label);
        }
        if(field.equalsIgnoreCase("name")){
            appendName(record,label);
        }
    }
    public void appendEditor(ExperimentRecord record, StringBuilder label) throws Exception {
        EditorDao editorDao=new EditorDao();
            if(record.getEditorId()==0 || editorDao.getEditorById(record.getEditorId())==null ||
                    editorDao.getEditorById(record.getEditorId()).size()==0 ){
                label.append("No Editor").append(" ");
            }else {
                label.append(record.getEditorSymbol()).append(" ");
            }
      //   label.append(editor).append( " ");

    }
    public void appendDelivery(ExperimentRecord record, StringBuilder label){
         label.append(record.getDeliverySystemName()).append( " ");
    }
    public void appendGuide(ExperimentRecord record, StringBuilder label) throws Exception {
      label.append( guideDao.getGuidesByExpRecId(record.getExperimentRecordId()).stream()
                .map(g->g.getGuide()).collect(Collectors.joining(" "))).append(" ");

    }
    public void appendTargetLocus(ExperimentRecord record, StringBuilder label) throws Exception {
        label.append( guideDao.getGuidesByExpRecId(record.getExperimentRecordId()).stream()
                .map(g->g.getTargetLocus()).collect(Collectors.joining(" "))).append(" ");

    }
    public void appendVector(ExperimentRecord record, StringBuilder label) throws Exception {
       label.append (vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()).stream()
               .map(vector->vector.getName()).collect(Collectors.joining(" "))).append(" ");

    }
    public void  appendModel(ExperimentRecord record, StringBuilder label){
        label.append( record.getModelName()).append(" ");
    }
    public void appendDosage(ExperimentRecord record, StringBuilder label) {
        if(record.getInjectionFrequency()!=null) {
            label.append(record.getInjectionFrequency());
            label.append(" ");
        }
        label.append(record.getDosage()).append(" ");

    }

    public void appendTissue(ExperimentRecord record, StringBuilder label){
      label.append(record.getTissueTerm()).append(" ");
    }
    public void appendCellType(ExperimentRecord record, StringBuilder label){
        if(record.getCellType()!=null && record.getCellTypeTerm()!=null && !record.getCellTypeTerm().equals(""))
        label.append( record.getCellTypeTerm()).append(" ");
    }
    public void appendSex(ExperimentRecord record,StringBuilder label){
        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("F"))
            label.append(  "Female").append(" ");
        if(record.getSex()!=null && record.getSex().equalsIgnoreCase("M"))
            label.append(  "Male").append(" ");

    }
    public void appendName(ExperimentRecord record, StringBuilder label){
        label.append(record.getExperimentName()).append(" ");
    }
 /*   public void appendResultType(ExperimentRecord record,StringBuilder label) throws Exception {

        label.append(" (").append(experimentResultDao.getResultsByExperimentRecId(record.getExperimentRecordId()).get(0).getResultType()).append(") ");

    }*/
    public void appendSamples(ExperimentRecord record,StringBuilder label) throws Exception {
        label.append(experimentResultDao.getResultsByExperimentRecId(record.getExperimentId()).get(0).getNumberOfSamples()).append(" Samples ");

    }
    }

