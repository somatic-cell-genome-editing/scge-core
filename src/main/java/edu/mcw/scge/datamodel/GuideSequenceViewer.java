package edu.mcw.scge.datamodel;

import java.util.Map;

public class GuideSequenceViewer {
    private String targetSequence;
    private String        targetLocus;
     private String        description;
      private String      type;
       private String      fmax;
       private String      pam;
       private String      strand;
       private String assembly;
       private String name;
       private Map<String, String> guide_ids;//     '"guide_ids":{"meta":{"number":[-1],"description":["The ID of the Guide"],"id":["guide_ids"],"type":["UNBOUNDED"]},"values":["\\"AAVS1_site_02\\""]},\n' +
        private String fmin;
        private String seqId; // chrmosome    '"seqId":"'+ guide["chr"] +'",\n' +
        private long scge_id;

    public String getTargetSequence() {
        return targetSequence;
    }

    public void setTargetSequence(String targetSequence) {
        this.targetSequence = targetSequence;
    }

    public String getTargetLocus() {
        return targetLocus;
    }

    public void setTargetLocus(String targetLocus) {
        this.targetLocus = targetLocus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFmax() {
        return fmax;
    }

    public void setFmax(String fmax) {
        this.fmax = fmax;
    }

    public String getPam() {
        return pam;
    }

    public void setPam(String pam) {
        this.pam = pam;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getGuide_ids() {
        return guide_ids;
    }

    public void setGuide_ids(Map<String, String> guide_ids) {
        this.guide_ids = guide_ids;
    }

    public String getFmin() {
        return fmin;
    }

    public void setFmin(String fmin) {
        this.fmin = fmin;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public long getScge_id() {
        return scge_id;
    }

    public void setScge_id(long scge_id) {
        this.scge_id = scge_id;
    }
}
